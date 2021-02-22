package cn.tool.core.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * 生成excel文件操作的简单封装
 * Created by Chenquanan on 2017/4/10.
 */
public class SimpleExcelWriter implements Closeable {
    private Workbook workbook;
    private int curSheetRowCount;
    private int curRowCellCount;

    public SimpleExcelWriter() {
        this.workbook = new HSSFWorkbook();
    }

    public Workbook getWorkbook() {
        return this.workbook;
    }

    public int getCurSheetRowCount() {
        return curSheetRowCount;
    }

    public int getCurRowCellCount() {
        return curRowCellCount;
    }

    public Sheet newSheet(String sheetName) {
        Sheet sheet = this.workbook.createSheet(sheetName);
        this.curSheetRowCount = 0;
        return sheet;
    }

    public Sheet getOrCreateSheet(String sheetName) {
        Sheet sheet = this.workbook.getSheet(sheetName);
        if(sheet != null) {
            this.curSheetRowCount = sheet.getLastRowNum();
            return sheet;
        }

        return this.newSheet(sheetName);
    }

    public Row appendRow(Sheet sheet) {
        Row row = sheet.createRow(this.curSheetRowCount++);
        this.curRowCellCount = 0;
        return row;
    }

    public Cell appendStringCell(Row row, String cellValue) {
        Cell cell = this.newAppendCell(row);
        cell.setCellValue(cellValue);
        return cell;
    }

    public Cell appendDoubleCell(Row row, double cellValue) {
        Cell cell = this.newAppendCell(row);
        cell.setCellValue(cellValue);
        return cell;
    }

    public Cell appendBooleanCell(Row row, boolean cellValue) {
        Cell cell = this.newAppendCell(row);
        cell.setCellValue(cellValue);
        return cell;
    }

    public Cell appendCalendarCell(Row row, Calendar cellValue) {
        Cell cell = this.newAppendCell(row);
        cell.setCellValue(cellValue);
        return cell;
    }

    public Cell appendDateCell(Row row, Date cellValue) {
        Cell cell = this.newAppendCell(row);
        cell.setCellValue(cellValue);
        return cell;
    }

    private Cell newAppendCell(Row row) {
        Cell cell = row.createCell(this.curRowCellCount++);
        return cell;
    }

    public void write(OutputStream os) throws IOException {
        this.workbook.write(os);
    }

    @Override
    public void close() throws IOException {
        if (this.workbook != null) {
            this.workbook.close();
        }
    }
}
