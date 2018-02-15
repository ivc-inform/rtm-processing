package com.simplesys.actors

import akka.actor._
import com.simplesys.actors.Reaper._
import com.simplesys.advisa.SimilarTerminalCache
import com.simplesys.config.Config
import com.simplesys.log.Logging
import com.simplesys.rtm.common._
import com.simplesys.rtm.scenario._
import org.joda.time.DateTime
import ru.simplesys.dmprocess.classifier.{ClassifierModel, TextType}
import ru.simplesys.dmprocess.common._
import ru.simplesys.dmprocess.templates._
import ru.simplesys.dmprocess.text.ParserContextVal

import scala.collection._
import scala.collection.immutable.Seq
import scalaz.{Failure, Success}

object PhoneHolderActor {
    def props(templateHolder: FixedImmutableTemplateHolder,
              wordSwissTool: WordSwissTool,
              classifierTool: ClassifierModel[Traversable[String], TextType],
              advisaPOSCache: SimilarTerminalCache,
              registry: ScenarioRegistry)(implicit controlBus: ControlBusImpl): Props =
        Props(
            new PhoneHolderActor(
                templateHolder,
                wordSwissTool,
                classifierTool,
                advisaPOSCache,
                registry)
        )
}

class PhoneHolderActor(
                        _templateHolder: FixedImmutableTemplateHolder,
                        _wordSwissTool: WordSwissTool,
                        _classifierTool: ClassifierModel[Traversable[String], TextType],
                        _advisaPOSCache: SimilarTerminalCache,
                        _registry: ScenarioRegistry)(implicit val controlBus: ControlBusImpl) extends Actor with Stash with Logging with Config {

    case class SeqRange(var begin: BigInt, var end: BigInt) {
        def remain = end - begin + 1

        def nextVal(): BigInt = {
            begin += 1
            begin
        }
    }

    private val templateHolder = _templateHolder
    private val wordSwissTool = _wordSwissTool
    private val advisaPOSCache = _advisaPOSCache

    private var seqGen: ActorRef = _

    private val registry = _registry
    private val runningsRegistry = new ScenarioRunningsRegistry(immutable.Seq.empty)

    private var seqRange = SeqRange(0, -1)

    val cacheConfig = getInt(s"${AppConfig.configPrefix}.sequenceCache")
    private val cacheSeqCount = scala.math.max(cacheConfig, 2 * registry.scenarioGroupMap.keys.size)

    override def preStart(): Unit = {
        super.preStart()
        logger.info(s"${self.path} started")
    }

    override def postStop(): Unit = {
        super.postStop()
        logger.info(s"${self.path} stopped")
    }

    def getData(msg: MessageMFMD): Either[ReasonToStage, (FixedTemplate, List[CategoryParamRefWValue])] = {
        implicit val parserCtx = ParserContextVal(msg.timestamp)
        val preprocessedTokensValidation = wordSwissTool.preProcessText(msg.text, None, parserCtx)

        preprocessedTokensValidation match {
            case Success(preprocessedTokens) =>
                val (statOrigin, tryFixed: List[FixedTemplate]) = templateHolder.lookupTemplates(preprocessedTokens)
                val datas = TemplateUtils.getDatas(tryFixed, preprocessedTokens)
                datas match {
                    case (x@(t, vals)) :: Nil =>
                        Right(x)

                    case Nil =>
                        Left(if (tryFixed.nonEmpty) NoDataExtracted else NoTemplatesFound)

                    case head :: tail =>
                        Left(TooManyTemplates)
                }
            case Failure(_) => Left(TokenizerError)
        }
    }

    def awaitingRecovery: Receive = {
        case RunningsRef(seqG) =>
            seqGen = seqG

        // todo that's copypaste from RunningsHolder!
        case scenarioRun: ScenarioRunProps =>
            runningsRegistry.addScenarioRun(scenarioRun, doRecalc = true)

        case FinishRecoverAndRun =>
            runningsRegistry.getScenarioStream.foreach {
                scenarioRunProps =>
                    if (!scenarioRunProps.isEnded)
                        registry.scenarioGroupMap.get(scenarioRunProps.groupName) match {
                            case Some(scenarioGroup) =>
                                registry.scenarioCompanionMap.get(scenarioGroup.name) match {
                                    case Some(seqS) =>
                                        seqS.find(_.scenarioData.scenarioName == scenarioRunProps.scenarioData) match {
                                            case Some(scenarioCompanion) =>
                                                context.actorOf(scenarioCompanion.props(scenarioRunProps, None), scenarioRunProps.getActorName)
                                            case None =>
                                                logger.debug(
                                                    s"${self.path.name}: can be problem: no scenario found by group scenarioMap during recovery of ${scenarioRunProps.toString}!")
                                        }
                                    case None =>
                                        logger.debug(
                                            s"${self.path.name}: can be problem: no group ${scenarioGroup.toString} in scenarioMap during recovery of ${scenarioRunProps.toString}!")
                                }
                            case None =>
                                logger.debug(
                                    s"${self.path.name}: can be problem: requested group ${scenarioRunProps.groupName} during recovery of ${scenarioRunProps.toString} but no such group exists!")
                        }
            }

            seqGen ! SeqGenerator.GetNewLimit(cacheSeqCount, self)
            context.become(awaitingSeq)
            logger.debug(s"${self.path.name}: recovered, requesting ids")

        case other => stash()
    }

    def awaitingShutdown: Receive = {
        case x: ShuttedDown => seqGen ! x
        case Terminated(ref) =>
            if (context.children.isEmpty)
                context stop self


        case msg =>
            logger.debug(s"${self.path.name} message ${msg.toString} ignoring because we are waiting for children shutdown")
    }

    def awaitingSeq: Receive = {

        // todo we are losing previous sequence values not used (end of interval)
        case SeqGenerator.TakeNewLimit(l, u) =>
            seqRange = SeqRange(l, u)
            unstashAll()
            context.become(main)

        case x: ShuttedDown =>
            seqGen ! x

        case msg =>
            stash()
    }

    def main: Receive = {
        case Terminated(ref) =>
            logger.debug(s"${self.path.name}: children ${ref.path.toString} stopped")

        case ShutdownIt =>
            val children = context.children
            if (children.isEmpty)
                context stop self
            else {
                children foreach (context stop _)
                context become awaitingShutdown
            }

        //q8jC3CnOE3Zak
        // todo it's very close to similar in RunningsHolder, refactor

        case ShutdownScenario(idScenario, status) ⇒
            runningsRegistry.getScenarioList.filter(_.scenarioData.scenarioVersion.idScenario == idScenario).foreach {
                runningScenario ⇒
                    context.child(runningScenario.getActorName).foreach {
                        scenario =>
                            scenario ! PoisonPill
                            logger.debug(s"send scenario: ${scenario.path.name} PoisonPill")
                    }
            }
            sender() ! ShutdownScenario(idScenario, status)

        case Request2ScenarioInUse(idScenario) ⇒
            runningsRegistry.getScenarioList.find(_.scenarioData.scenarioVersion.idScenario == idScenario) match {
                case None ⇒ sender() ! ScenarioNotInUse
                case Some(_) ⇒ sender() ! ScenarioInUse
            }

        case x@ShuttedDown(scenarioRun) =>
            runningsRegistry.shutdownScenarioRun(scenarioRun, doRecalc = true)
            seqGen ! x

        case msg: MessageMFMD =>
            val parsedData = getData(msg)

            val parsedEvOpt = parsedData match {
                case Left(reason) =>
                    logger.debug(
                        s"parsing fail: ${reason.key} ${msg.address} ${msg.text}")
                    None
                case Right((tmpl, params)) =>
                    ParsedMessage.unapply(msg, tmpl, params, advisaPOSCache)
            }

            val preProcessedMsg = PreProcessedMessage(msg, parsedEvOpt, msg.address)

            logger.debug(s"processing: ${msg.address} ${msg.text} ${preProcessedMsg.toString}")

            //val scMapOpt = runningsRegistry.getCleanedRunnings(preProcessedMsg.msg.address)

            // already running scenarios
            val runningScenarios: Seq[ScenarioRunProps] = runningsRegistry getCurrentlyRunning preProcessedMsg.msg.address

            runningScenarios.foreach {
                runningScenario =>
                    context.child(runningScenario.getActorName).foreach {
                        scenario =>
                            scenario ! preProcessedMsg
                            logger.debug(s"sent to scenario: ${scenario.path.name} ${msg.text} ${preProcessedMsg.toString}")
                    }
            }

            // looking for new scenarios to run
            val scenariosPossible2Run: Set[ScenarioCompanion] = runningsRegistry.getScenariosPossible2Run(preProcessedMsg.msg.address, registry, preProcessedMsg, true)

            scenariosPossible2Run.foreach {
                scenarioCompanion =>
                    // building anchor
                    val scenarioRunProps = ScenarioRunProps(
                        seq = seqRange.nextVal(),
                        phone = msg.address,
                        groupName = scenarioCompanion.scenarioGroup.name,
                        scenarioData = scenarioCompanion.scenarioData,
                        started = DateTime.now(),
                        endDates = None)

                    // starting actor
                    val newScenarioActor = context.actorOf(scenarioCompanion.props(scenarioRun = scenarioRunProps, human = None), scenarioRunProps.getActorName)

                    // registering anchor
                    runningsRegistry addScenarioRun scenarioRunProps

                    seqGen ! StartedUp(scenarioRunProps)

                    // watching child
                    context watch newScenarioActor

                    logger.debug(s"constructed new scenario: ${scenarioRunProps.getActorName} ${msg.text} ${preProcessedMsg.toString}")

                    newScenarioActor ! preProcessedMsg
            }

            if (seqRange.remain < registry.scenarioGroupMap.size) {
                seqGen ! SeqGenerator.GetNewLimit(cacheSeqCount, self)
                logger.debug(s"${self.path.name}: requesting new sequences, size: $cacheSeqCount")
                context.become(awaitingSeq)
            }
    }

    def receive: Actor.Receive = awaitingRecovery
}
