package cn.tool.core.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Excel读取工具类
 * @author:Chenquanan
 * @date:2016年8月11日 上午11:31:41
 */
public class ExcelReader implements Closeable {
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	//当前行
	private int rowNum = 0;
	//指定最后一行的位置
	private int endRowIndex = -1;
	//总行数
	private int totalRowNum = -1;
	//行总列数
	private int rowCellNum = 0;
	
	public ExcelReader(String filepath) {
		this(new File(filepath));
	}
	
	public ExcelReader(File file) {
		try {
			this.workbook = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ExcelReader(InputStream is) {
		try {
			this.workbook = WorkbookFactory.create(is);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取工作薄，如果没有指定工作薄，默认取显示在最前面的
	 */
	private Sheet getSheet() {
		if(this.sheet == null) {
			synchronized(this) {
				if(this.sheet == null) {
					this.sheet = this.workbook.getSheetAt(this.workbook.getActiveSheetIndex());
				}
			}
		}
		return this.sheet;
	}

	/**
	 * 获取工作薄名称
	 */
	public String getSheetName() {
		return this.getSheet().getSheetName();
	}
	
	/**
	 * 获取总行数
	 */
	public int getTotalRowNum() {
		if(this.totalRowNum < 0) {
			this.totalRowNum = this.getSheet().getLastRowNum() + 1;
		}
		return this.totalRowNum;
	}
	
	/**
	 * 将起始指针移动到指定行,从0开始
	 */
	public void setStartRowIndex(int startRowIndex) {
		if(startRowIndex < 0 || startRowIndex > this.getTotalRowNum()) {
			throw new IllegalArgumentException("非法的起始指针参数"+startRowIndex+", 允许的范围为[0, "+this.getTotalRowNum()+"]");
		}
		this.rowNum = startRowIndex;
	}
	
	/**
	 * 将结束指针移动到指定行，设置为负数表示倒数第几行
	 */
	public void setEndRowIndex(int endRowIndex) {
		int totalNum = this.getTotalRowNum();
		if(endRowIndex > totalNum || endRowIndex < -totalNum) {
			throw new IllegalArgumentException("非法的结束指针参数"+endRowIndex+", 允许的范围为["+(-totalNum)+", "+totalNum+"]");
		}
		this.rowNum = endRowIndex > 0 ? endRowIndex : (totalNum + endRowIndex);
	}
	
	private int getEndRowIndex() {
		if(this.endRowIndex < 0) {
			this.endRowIndex = this.getTotalRowNum();
		}
		return this.endRowIndex;
	}
	
	/**
	 * 设置工作薄号,从0开始
	 */
	public void setSheetIndex(int sheetIndex) {
		if(sheetIndex < 0) {
			throw new IllegalArgumentException("工作薄号不能小于0, " + sheetIndex);
		}
		this.sheet = this.workbook.getSheetAt(sheetIndex);
	}
	
	/**
	 * 将指针指向下一行，如果没有下一行，返回false，否则返回true
	 */
	public boolean next() {
		if(this.rowNum >= this.getEndRowIndex()) {
			return false;
		}
	
		this.row = this.getSheet().getRow(this.rowNum);
		this.rowNum ++;
		this.rowCellNum = this.row==null?-10:this.row.getLastCellNum();
		return true;
	}
	
	/**
	 * 获取当前行指定列索引的字符串值，列索引从0开始
	 */
	public String getString(int cellIndex, String defaultValue) {
		this.checkCellIncex(cellIndex);
		if(this.rowCellNum < cellIndex - 1) {
			return defaultValue;
		}
		
		Cell cell = this.row.getCell(cellIndex);
		if(cell == null) {
			return defaultValue;
		}
		
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				double dv = cell.getNumericCellValue();
				long iv = (long)dv;
				return dv==iv ? String.valueOf(iv) : String.valueOf(dv);
			default:
				return cell.getStringCellValue();
		}
	}
	
	/**
	 * 获取当前行指定列索引的double值，列索引从0开始
	 */
	public Double getDouble(int cellIndex, Double defaultValue) {
		this.checkCellIncex(cellIndex);
		if(this.rowCellNum < cellIndex - 1) {
			return defaultValue;
		}
		
		Cell cell = this.row.getCell(cellIndex);
		if(cell == null) {
			return defaultValue;
		}
		
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
			default:
				return Double.valueOf(cell.getStringCellValue());
		}
	}
	
	/**
	 * 获取当前行指定列索引的float值，列索引从0开始
	 */
	public Float getFloat(int cellIndex, Float defaultValue) {
		Double doubleValue = this.getDouble(cellIndex, null);
		if(doubleValue == null) {
			return defaultValue;
		}
		return doubleValue.floatValue();
	}
	
	/**
	 * 获取当前行指定列索引的long值，列索引从0开始
	 */
	public Long getLong(int cellIndex, Long defaultValue) {
		Double doubleValue = this.getDouble(cellIndex, null);
		if(doubleValue == null) {
			return defaultValue;
		}
		return doubleValue.longValue();
	}
	
	/**
	 * 获取当前行指定列索引的Integer值，列索引从0开始
	 */
	public Integer getInteger(int cellIndex, Integer defaultValue) {
		Double doubleValue = this.getDouble(cellIndex, null);
		if(doubleValue == null) {
			return defaultValue;
		}
		return doubleValue.intValue();
	}
	
	/**
	 * 获取当前行指定列索引的short值，列索引从0开始
	 */
	public Short getShort(int cellIndex, Short defaultValue) {
		Double doubleValue = this.getDouble(cellIndex, null);
		if(doubleValue == null) {
			return defaultValue;
		}
		return doubleValue.shortValue();
	}
	
	/**
	 * 获取当前行指定列索引的byte值，列索引从0开始
	 */
	public Byte getByte(int cellIndex, Byte defaultValue) {
		Double doubleValue = this.getDouble(cellIndex, null);
		if(doubleValue == null) {
			return defaultValue;
		}
		return doubleValue.byteValue();
	}
	
	/**
	 * 获取当前行指定列索引的boolean值，列索引从0开始
	 */
	public Boolean getBoolean(int cellIndex, Boolean defaultValue) {
		this.checkCellIncex(cellIndex);
		if(this.rowCellNum < cellIndex - 1) {
			return defaultValue;
		}
		
		Cell cell = this.row.getCell(cellIndex);
		if(cell == null) {
			return defaultValue;
		}
		
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return  Boolean.FALSE;
			default:
				return Boolean.valueOf(cell.getStringCellValue());
		}
	}
	
	private void checkCellIncex(int cellIndex) {
		if(cellIndex < 0) {
			throw new IllegalArgumentException("列号不能小于0, " + cellIndex);
		}
	}
	
	@Override
	public void close() throws IOException {
		if(this.workbook != null) {
			this.workbook.close();
		}
	}
}