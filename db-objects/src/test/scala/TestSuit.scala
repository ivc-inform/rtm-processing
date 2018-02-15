import com.simplesys.config.Config
import com.simplesys.connectionStack.BoneCPStack
import com.simplesys.export.ExelExport
import org.scalatest.FunSuite
import ru.simplesys.defs.bo.scenarios.ScenarioTraceDS

class TestSuit extends FunSuite with Config with BoneCPStack{
    val ds = OracleDataSource("oracleMFMS")

    test("exel Export") {
        val scenarioTraceDS = ScenarioTraceDS(ds)
        val exelExport= new ExelExport(scenarioTraceDS)
        val res = exelExport.execute()
        println(res.getAbsolutePath)
    }
}
