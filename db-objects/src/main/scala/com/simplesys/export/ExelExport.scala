package com.simplesys.export

import java.io.{File, FileOutputStream}

import com.simplesys.jdbc.control.DSRequest
import org.apache.poi.ss.usermodel._
import org.apache.poi.ss.util.WorkbookUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import ru.simplesys.defs.bo.scenarios.{ScenarioTraceDS, ScenarioTrace_DebugDS}


trait ExelExportColumn {
    val caption: String
    val name: String
    val index: Int
    val width: Int
}

class ExelExport(val scenarioTraceDS: ScenarioTraceDS, dsRequest: DSRequest = null, dir: File) {

    private def createBorderedStyle(wb: Workbook): CellStyle = {

        val thin: BorderStyle = BorderStyle.THIN
        val black: Short = IndexedColors.BLACK.getIndex
        val style: CellStyle = wb.createCellStyle

        style.setBorderRight(thin)
        style.setRightBorderColor(black)
        style.setBorderBottom(thin)
        style.setBottomBorderColor(black)
        style.setBorderLeft(thin)
        style.setLeftBorderColor(black)
        style.setBorderTop(thin)
        style.setTopBorderColor(black)
        style
    }

    private def createStyles(wb: Workbook): Map[String, CellStyle] =
        Map(
            "header" → {
                val style = createBorderedStyle(wb)
                style setWrapText true
                style setAlignment HorizontalAlignment.CENTER
                style setFillForegroundColor IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 10
                    headerFont setBold true
                    headerFont
                }
                style
            },
            "header1" → {
                val style = createBorderedStyle(wb)
                style setAlignment HorizontalAlignment.CENTER
                style setFillForegroundColor IndexedColors.LIGHT_YELLOW.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 10
                    headerFont
                }
                style
            },
            "cellData" → {
                val style = createBorderedStyle(wb)
                style setAlignment HorizontalAlignment.CENTER
                style setFillForegroundColor IndexedColors.WHITE.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 8
                    headerFont
                }
                style
            },
            "cellDataEvent" → {
                val style = createBorderedStyle(wb)
                style setFillForegroundColor IndexedColors.WHITE.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setWrapText true
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 8
                    headerFont
                }
                style
            }

        )

    val wb = new XSSFWorkbook()
    val styles = createStyles(wb)

    val file = File.createTempFile("exportResulsScenario", ".xlsx", dir)
    file.deleteOnExit()

    val fileOut = new FileOutputStream(file)
    val sheet = wb.createSheet(WorkbookUtil.createSafeSheetName("Таблица #1"))
    sheet setFitToPage true
    sheet setHorizontallyCenter true

    val printSetup = sheet.getPrintSetup()
    printSetup setLandscape true

    sheet setAutobreaks true
    printSetup setFitHeight 1
    printSetup setFitWidth 1

    val headerRow = sheet createRow 0
    headerRow setHeightInPoints 25

    val headerRow1 = sheet createRow 1
    headerRow1 setHeightInPoints 12

    val columns = Seq(
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.timestampScenarioTrace.name
            override val index: Int = 0
            override val width: Int = 256 * 25
            override val caption: String = scenarioTraceDS.timestampScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.addressScenarioTrace.name
            override val index: Int = 1
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.addressScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.eventScenarioTrace.name
            override val index: Int = 2
            override val width: Int = 256 * 100
            override val caption: String = scenarioTraceDS.eventScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.scenarioScenarioTrace.name
            override val index: Int = 3
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.scenarioScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.stageScenarioTrace.name
            override val index: Int = 4
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.stageScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.parentstageScenarioTrace.name
            override val index: Int = 5
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.parentstageScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.bonusbaseScenarioTrace.name
            override val index: Int = 6
            override val width: Int = 256 * 10
            override val caption: String = scenarioTraceDS.bonusbaseScenarioTrace.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.marketingmessageScenarioTrace.name
            override val index: Int = 7
            override val width: Int = 256 * 80
            override val caption: String = scenarioTraceDS.marketingmessageScenarioTrace.caption
        }
    )

    val styleHeader = styles("header")
    columns.foreach {
        column ⇒
            val cell = headerRow createCell column.index
            cell setCellValue column.caption
            cell setCellStyle styleHeader
    }

    val styleHeader1 = styles("header1")
    columns.foreach {
        column ⇒
            val cell = headerRow1 createCell column.index
            cell setCellValue s"${column.index} (${column.name})"
            cell setCellStyle styleHeader1
    }

    columns foreach {
        column ⇒
            sheet setColumnWidth(column.index, column.width)
    }

    val styleCells = styles("cellData")
    val styleCellsEvent = styles("cellDataEvent")

   scenarioTraceDS.selectPList(dsRequest = dsRequest).result match {
        case scalaz.Success(list) ⇒

            list.zipWithIndex.foreach {
                case (item, index) ⇒

                    val row = sheet createRow index + 2
                    if (item.eventScenarioTrace != "InnerEvent")
                        row setHeight (256 * 5).toShort

                {
                    val cell = row createCell 0
                    cell setCellValue item.timestampScenarioTrace.toString("YYYY MM dd HH:mm:ss")
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 1
                    cell setCellValue item.addressScenarioTrace
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 2
                    cell setCellValue item.eventScenarioTrace
                    cell setCellStyle styleCellsEvent
                }
                {
                    val cell = row createCell 3
                    cell setCellValue item.scenarioScenarioTrace
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 4
                    cell setCellValue item.stageScenarioTrace
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 5
                    item.parentstageScenarioTrace.foreach(cell setCellValue _)
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 6
                    item.bonusbaseScenarioTrace.foreach(cell setCellValue _)
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 7
                    item.marketingmessageScenarioTrace.foreach(cell setCellValue _)
                    cell setCellStyle styleCells
                }
            }

        case scalaz.Failure(e) ⇒
            throw e
    }

    def execute(): File = {
        wb write fileOut
        fileOut.close()
        file
    }
}

class ExelExport_Debug(val scenarioTraceDS: ScenarioTrace_DebugDS, dsRequest: DSRequest = null, dir: File) {

    private def createBorderedStyle(wb: Workbook): CellStyle = {

        val thin: BorderStyle = BorderStyle.THIN
        val black: Short = IndexedColors.BLACK.getIndex
        val style: CellStyle = wb.createCellStyle

        style.setBorderRight(thin)
        style.setRightBorderColor(black)
        style.setBorderBottom(thin)
        style.setBottomBorderColor(black)
        style.setBorderLeft(thin)
        style.setLeftBorderColor(black)
        style.setBorderTop(thin)
        style.setTopBorderColor(black)
        style
    }

    private def createStyles(wb: Workbook): Map[String, CellStyle] =
        Map(
            "header" → {
                val style = createBorderedStyle(wb)
                style setWrapText true
                style setAlignment HorizontalAlignment.CENTER
                style setFillForegroundColor IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 10
                    headerFont setBold true
                    headerFont
                }
                style
            },
            "header1" → {
                val style = createBorderedStyle(wb)
                style setAlignment HorizontalAlignment.CENTER
                style setFillForegroundColor IndexedColors.LIGHT_YELLOW.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 10
                    headerFont
                }
                style
            },
            "cellData" → {
                val style = createBorderedStyle(wb)
                style setAlignment HorizontalAlignment.CENTER
                style setFillForegroundColor IndexedColors.WHITE.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 8
                    headerFont
                }
                style
            },
            "cellDataEvent" → {
                val style = createBorderedStyle(wb)
                style setFillForegroundColor IndexedColors.WHITE.getIndex
                style setFillPattern FillPatternType.SOLID_FOREGROUND
                style setWrapText true
                style setFont {
                    val headerFont = wb.createFont
                    headerFont setFontHeightInPoints 8
                    headerFont
                }
                style
            }

        )

    val wb = new XSSFWorkbook()
    val styles = createStyles(wb)

    val file = File.createTempFile("exportResulsScenario", ".xlsx", dir)
    file.deleteOnExit()

    val fileOut = new FileOutputStream(file)
    val sheet = wb.createSheet(WorkbookUtil.createSafeSheetName("Таблица #1"))
    sheet setFitToPage true
    sheet setHorizontallyCenter true

    val printSetup = sheet.getPrintSetup()
    printSetup setLandscape true

    sheet setAutobreaks true
    printSetup setFitHeight 1
    printSetup setFitWidth 1

    val headerRow = sheet createRow 0
    headerRow setHeightInPoints 25

    val headerRow1 = sheet createRow 1
    headerRow1 setHeightInPoints 12

    val columns = Seq(
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.timestampScenarioTrace_Debug.name
            override val index: Int = 0
            override val width: Int = 256 * 25
            override val caption: String = scenarioTraceDS.timestampScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.addressScenarioTrace_Debug.name
            override val index: Int = 1
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.addressScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.eventScenarioTrace_Debug.name
            override val index: Int = 2
            override val width: Int = 256 * 100
            override val caption: String = scenarioTraceDS.eventScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.scenarioScenarioTrace_Debug.name
            override val index: Int = 3
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.scenarioScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.stageScenarioTrace_Debug.name
            override val index: Int = 4
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.stageScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.parentstageScenarioTrace_Debug.name
            override val index: Int = 5
            override val width: Int = 256 * 15
            override val caption: String = scenarioTraceDS.parentstageScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.bonusbaseScenarioTrace_Debug.name
            override val index: Int = 6
            override val width: Int = 256 * 10
            override val caption: String = scenarioTraceDS.bonusbaseScenarioTrace_Debug.caption
        },
        new ExelExportColumn {
            override val name: String = scenarioTraceDS.marketingmessageScenarioTrace_Debug.name
            override val index: Int = 7
            override val width: Int = 256 * 80
            override val caption: String = scenarioTraceDS.marketingmessageScenarioTrace_Debug.caption
        }
    )

    val styleHeader = styles("header")
    columns.foreach {
        column ⇒
            val cell = headerRow createCell column.index
            cell setCellValue column.caption
            cell setCellStyle styleHeader
    }

    val styleHeader1 = styles("header1")
    columns.foreach {
        column ⇒
            val cell = headerRow1 createCell column.index
            cell setCellValue s"${column.index} (${column.name})"
            cell setCellStyle styleHeader1
    }

    columns foreach {
        column ⇒
            sheet setColumnWidth(column.index, column.width)
    }

    val styleCells = styles("cellData")
    val styleCellsEvent = styles("cellDataEvent")

   scenarioTraceDS.selectPList(dsRequest = dsRequest).result match {
        case scalaz.Success(list) ⇒

            list.zipWithIndex.foreach {
                case (item, index) ⇒

                    val row = sheet createRow index + 2
                    if (item.eventScenarioTrace_Debug != "InnerEvent")
                        row setHeight (256 * 5).toShort

                {
                    val cell = row createCell 0
                    cell setCellValue item.timestampScenarioTrace_Debug.toString("YYYY MM dd HH:mm:ss")
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 1
                    cell setCellValue item.addressScenarioTrace_Debug
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 2
                    cell setCellValue item.eventScenarioTrace_Debug
                    cell setCellStyle styleCellsEvent
                }
                {
                    val cell = row createCell 3
                    cell setCellValue item.scenarioScenarioTrace_Debug
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 4
                    cell setCellValue item.stageScenarioTrace_Debug
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 5
                    item.parentstageScenarioTrace_Debug.foreach(cell setCellValue _)
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 6
                    item.bonusbaseScenarioTrace_Debug.foreach(cell setCellValue _)
                    cell setCellStyle styleCells
                }
                {
                    val cell = row createCell 7
                    item.marketingmessageScenarioTrace_Debug.foreach(cell setCellValue _)
                    cell setCellStyle styleCells
                }
            }

        case scalaz.Failure(e) ⇒
            throw e
    }

    def execute(): File = {
        wb write fileOut
        fileOut.close()
        file
    }
}
