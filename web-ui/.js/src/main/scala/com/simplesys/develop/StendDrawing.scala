package com.simplesys.develop

import com.simplesys.SmartClient.App.WebApp
import com.simplesys.SmartClient.DataBinding.DSResponse
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.toolStrip.ToolStripButtonProps
import com.simplesys.SmartClient.Layout.props.{ToolStripProps, VLayoutSSProps}
import com.simplesys.SmartClient.System.{ToolStrip, ToolStripButton, ToolStripSeparator, VLayoutSS}
import com.simplesys.System._
import com.simplesys.components.props.ConstructorFormBase
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExport

@JSExport
class StendDrawing extends WebApp{
    self =>

    val idScenario = 2.0
    private lazy val constructorFormBase: ConstructorFormBase = new ConstructorFormBase {

        override protected val saveControl: JSUndefined[Canvas] = saveButton
        override protected val identifier: String = "DC3493B6-F896-800E-CE71-B6AD0559B6ED"
        override protected val idScenario: JSUndefined[Double] = self.idScenario
        override protected val codeCmpgn: JSUndefined[String] = "test"
    }

    override protected val loadSchemas: Boolean = com.simplesys.app.loadSchemas
    private val saveButton = ToolStripButton.create(
        new ToolStripButtonProps {

            import com.simplesys.SmartClient.System.Common

            title = "Сохранить".opt
            icon = Common.iconSave.opt
            click = {
                (thiz: classHandler) =>
                    /*constructorFormBase.getJSONGraph(
                        {
                            (jsonGraph: String) =>
                                isc.OfflineSS.put("jsonGraph", jsonGraph)
                                isc info "Запись выполнена."
                        }.toFunc
                    )*/

                    constructorFormBase.updateInBase(_callback = ((response: DSResponse) => () /*isc ok "Запись выполнена"*/).toFunc)

                    false
            }.toThisFunc.opt
        }
    )
    override protected def mainCanvas: Canvas =
        VLayoutSS.create(
            new VLayoutSSProps {
                members = Seq(
                    ToolStrip.create(
                        new ToolStripProps {
                            height = 25
                            members = Seq(
                                saveButton,
                                ToolStripSeparator.create(),
                                ToolStripButton.create(
                                    new ToolStripButtonProps {

                                        import com.simplesys.SmartClient.System.Common

                                        title = "Восстановить".opt
                                        icon = Common.iconRefresh.opt
                                        click = {
                                            (thiz: classHandler) =>
                                                //constructorFormBase.recoverGraph(isc.OfflineSS.get("jsonGraph", ""), (() => isc info "Восстановление выполнено.").toFunc)

                                                constructorFormBase.recoverGraphFromBase(idScenario, (() => ()/*isc info "Восстановление выполнено."*/).toFunc)
                                                false
                                        }.toThisFunc.opt

                                    }
                                ),
                                ToolStripButton.create(
                                    new ToolStripButtonProps {

                                        import com.simplesys.SmartClient.System.Common

                                        title = "Редактировать JSON".opt
                                        icon = Common.iconRefresh.opt
                                        click = {
                                            (thiz: classHandler) =>
                                                constructorFormBase.editJSONDirectInBase(0.0)

                                                false
                                        }.toThisFunc.opt

                                    }
                                )
                            ).opt
                        }
                    ),
                    constructorFormBase.get
                ).opt
            }
        )
}
