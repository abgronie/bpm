/*     */ package com.hotent.core.excel;
/*     */ 
/*     */ import com.hotent.core.excel.editor.CellEditor;
/*     */ import com.hotent.core.excel.editor.ColumnEditor;
/*     */ import com.hotent.core.excel.editor.IFontEditor;
/*     */ import com.hotent.core.excel.editor.RegionEditor;
/*     */ import com.hotent.core.excel.editor.RowEditor;
/*     */ import com.hotent.core.excel.editor.SheetEditor;
/*     */ import com.hotent.core.excel.style.Align;
/*     */ import com.hotent.core.excel.style.BorderStyle;
/*     */ import com.hotent.core.excel.style.Color;
/*     */ import com.hotent.core.excel.style.font.BoldWeight;
/*     */ import com.hotent.core.excel.style.font.Font;
/*     */ import com.hotent.core.excel.util.ExcelUtil;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Date;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*     */ import org.apache.poi.hssf.usermodel.HSSFFont;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.poifs.filesystem.POIFSFileSystem;
/*     */ import org.apache.poi.ss.usermodel.Name;
/*     */ import org.apache.poi.ss.util.CellRangeAddress;
/*     */ 
/*     */ public class Excel
/*     */ {
/*     */   private ExcelContext ctx;
/*     */ 
/*     */   public Excel()
/*     */   {
/*  60 */     this(new DefaultExcelStyle());
/*     */   }
/*     */ 
/*     */   public Excel(DefaultExcelStyle defaultStyle)
/*     */   {
/*  68 */     this(null, defaultStyle);
/*     */   }
/*     */ 
/*     */   public Excel(String excelPath)
/*     */   {
/*  78 */     this(excelPath, new DefaultExcelStyle());
/*     */   }
/*     */ 
/*     */   public Excel(String excelPath, DefaultExcelStyle defaultStyle)
/*     */   {
/*     */     HSSFWorkbook workBook;
/*     */     //HSSFWorkbook workBook;
/*  93 */     if ((excelPath == null) || (excelPath.trim().equals(""))) {
/*  94 */       workBook = new HSSFWorkbook();
/*     */     } else {
/*  96 */       workBook = readExcel(excelPath);
/*  97 */       if (workBook == null) {
/*  98 */         workBook = new HSSFWorkbook();
/*     */       }
/*     */     }
/* 101 */     this.ctx = new ExcelContext(this, workBook);
/* 102 */     this.ctx.setDefaultStyle(defaultStyle);
/*     */ 
/* 104 */     setWorkingSheet(0);
/* 105 */     HSSFCellStyle tempCellStyle = workBook.createCellStyle();
/* 106 */     this.ctx.setTempCellStyle(tempCellStyle);
/* 107 */     HSSFFont tempFont = workBook.createFont();
/* 108 */     this.ctx.setTempFont(tempFont);
/*     */ 
/* 110 */     HSSFCell cell = ExcelUtil.getHSSFCell(this.ctx.getWorkingSheet(), 0, 0);
/* 111 */     HSSFCellStyle cellStyle = cell.getCellStyle();
/* 112 */     cellStyle.setFillForegroundColor(defaultStyle.getBackgroundColor().getIndex());
/* 113 */     cellStyle.setFillPattern(defaultStyle.getFillPattern().getFillPattern());
/* 114 */     cellStyle.setAlignment(defaultStyle.getAlign().getAlignment());
/* 115 */     cellStyle.setVerticalAlignment(defaultStyle.getVAlign().getAlignment());
/*     */ 
/* 117 */     cellStyle.setBorderBottom(defaultStyle.getBorderStyle().getBorderType());
/* 118 */     cellStyle.setBorderLeft(defaultStyle.getBorderStyle().getBorderType());
/* 119 */     cellStyle.setBorderRight(defaultStyle.getBorderStyle().getBorderType());
/* 120 */     cellStyle.setBorderTop(defaultStyle.getBorderStyle().getBorderType());
/* 121 */     cellStyle.setBottomBorderColor(defaultStyle.getBorderColor().getIndex());
/* 122 */     cellStyle.setTopBorderColor(defaultStyle.getBorderColor().getIndex());
/* 123 */     cellStyle.setLeftBorderColor(defaultStyle.getBorderColor().getIndex());
/* 124 */     cellStyle.setRightBorderColor(defaultStyle.getBorderColor().getIndex());
/*     */ 
/* 126 */     HSSFFont font = cellStyle.getFont(workBook);
/* 127 */     font.setFontHeightInPoints(defaultStyle.getFontSize());
/* 128 */     font.setFontName(defaultStyle.getFontName());
/* 129 */     font.setColor(defaultStyle.getFontColor().getIndex());
/*     */   }
/*     */ 
/*     */   private HSSFWorkbook readExcel(String excelPath)
/*     */   {
/* 141 */     HSSFWorkbook result = null;
			  POIFSFileSystem fs;
/*     */     try
/*     */     {
/* 145 */       fs = new POIFSFileSystem(new FileInputStream(excelPath));
/* 146 */       result = new HSSFWorkbook(fs);
/*     */     }
/*     */     catch (Exception ex) {
/*     */       try {
/* 150 */         fs = new POIFSFileSystem(getClass().getResourceAsStream(excelPath));
/* 151 */         result = new HSSFWorkbook(fs);
/*     */       }
/*     */       catch (Exception e1)
/*     */       {
/*     */         try
/*     */         {
/*     */           //POIFSFileSystem fs;
/* 155 */           InputStream stream = null;
/* 156 */           StackTraceElement[] st = new Throwable().getStackTrace();
/* 157 */           for (int i = 2; i < st.length; i++) {
/* 158 */             stream = Class.forName(st[i].getClassName()).getResourceAsStream(excelPath);
/* 159 */             if (stream != null) {
/* 160 */               fs = new POIFSFileSystem(stream);
/* 161 */               result = new HSSFWorkbook(fs);
/* 162 */               break;
/*     */             }
/*     */           }
/*     */         } catch (Exception e) {
/* 166 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 170 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean saveExcel(String excelPath)
/*     */   {
/*     */     try
/*     */     {
/* 184 */       BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(excelPath));
/* 185 */       return saveExcel(fileOut);
/*     */     } catch (FileNotFoundException e) {
/* 187 */       e.printStackTrace();
/*     */     }
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean saveExcel(OutputStream fileOut)
/*     */   {
/* 200 */     boolean result = false;
/*     */     try {
/* 202 */       this.ctx.getWorkBook().write(fileOut);
/* 203 */       result = true;
/*     */     } catch (Exception e) {
/* 205 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 208 */         fileOut.flush();
/* 209 */         fileOut.close();
/*     */       } catch (Exception e) {
/* 211 */         result = false;
/*     */       }
/*     */     }
/* 214 */     return result;
/*     */   }
/*     */ 
/*     */   public SheetEditor setWorkingSheet(int index)
/*     */   {
/* 226 */     if (index < 0) {
/* 227 */       index = 0;
/*     */     }
/* 229 */     this.ctx.setWorkingSheet(ExcelUtil.getHSSFSheet(this.ctx.getWorkBook(), index));
/* 230 */     return sheet(index);
/*     */   }
/*     */ 
/*     */   public CellEditor cell(int row, int col)
/*     */   {
/* 242 */     CellEditor cellEditor = new CellEditor(row, col, this.ctx);
/* 243 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   public RowEditor row(int row)
/*     */   {
/* 253 */     return new RowEditor(row, this.ctx);
/*     */   }
/*     */ 
/*     */   public RowEditor row(int row, int startCol)
/*     */   {
/* 264 */     return new RowEditor(row, startCol, this.ctx);
/*     */   }
/*     */ 
/*     */   public RowEditor row()
/*     */   {
/* 279 */     int rowNum = ExcelUtil.getLastRowNum(this.ctx.getWorkingSheet());
/* 280 */     if (!checkEmptyRow(rowNum)) {
/* 281 */       rowNum++;
/*     */     }
/* 283 */     return new RowEditor(rowNum, this.ctx);
/*     */   }
/*     */ 
/*     */   private boolean checkEmptyRow(int rowNum)
/*     */   {
/* 292 */     HSSFRow row = this.ctx.getWorkingSheet().getRow(rowNum);
/* 293 */     int lastCell = row != null ? row.getLastCellNum() : 2;
/* 294 */     return (lastCell == 1) || (lastCell == -1);
/*     */   }
/*     */ 
/*     */   public ColumnEditor column(int col)
/*     */   {
/* 304 */     ColumnEditor columnEditor = new ColumnEditor(col, this.ctx);
/* 305 */     return columnEditor;
/*     */   }
/*     */ 
/*     */   public ColumnEditor column(int col, int startRow)
/*     */   {
/* 316 */     ColumnEditor columnEditor = new ColumnEditor(col, startRow, this.ctx);
/* 317 */     return columnEditor;
/*     */   }
/*     */ 
/*     */   public RegionEditor region(int beginRow, int beginCol, int endRow, int endCol)
/*     */   {
/* 329 */     RegionEditor regionEditor = new RegionEditor(beginRow, beginCol, endRow, endCol, this.ctx);
/* 330 */     return regionEditor;
/*     */   }
/*     */ 
/*     */   public RegionEditor region(String ref)
/*     */   {
/* 339 */     RegionEditor regionEditor = new RegionEditor(CellRangeAddress.valueOf(ref), this.ctx);
/* 340 */     return regionEditor;
/*     */   }
/*     */ 
/*     */   public SheetEditor sheet(int index)
/*     */   {
/* 349 */     if (index < 0) {
/* 350 */       index = 0;
/*     */     }
/* 352 */     SheetEditor sheetEditor = new SheetEditor(ExcelUtil.getHSSFSheet(this.ctx.getWorkBook(), index), this.ctx);
/* 353 */     return sheetEditor;
/*     */   }
/*     */ 
/*     */   public SheetEditor sheet()
/*     */   {
/* 361 */     return sheet(this.ctx.getWorkingSheetIndex());
/*     */   }
/*     */ 
/*     */   public HSSFWorkbook getWorkBook()
/*     */   {
/* 369 */     return this.ctx.getWorkBook();
/*     */   }
/*     */ 
/*     */   public int getWorkingSheetIndex()
/*     */   {
/* 377 */     return this.ctx.getWorkingSheetIndex();
/*     */   }
/*     */ 
/*     */   public Name createName(String name, String formulaText)
/*     */   {
/* 388 */     Name refersName = this.ctx.getWorkBook().createName();
/* 389 */     refersName.setNameName(name);
/* 390 */     refersName.setRefersToFormula(formulaText);
/* 391 */     return refersName;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 399 */     Object[] val = { "插入一行数据", Integer.valueOf(123), Character.valueOf('A'), Double.valueOf(3.141592653589793D), new Date(), "hello" };
/*     */ 
/* 401 */     Excel excel = new Excel();
/* 402 */     excel.cell(0, 0).value("Hello World!").align(Align.CENTER).bgColor(Color.LIGHT_YELLOW).height(30.0F).font(new IFontEditor()
/*     */     {
/*     */       public void updateFont(Font font)
/*     */       {
/* 411 */         font.boldweight(BoldWeight.BOLD);
/* 412 */         font.color(Color.BROWN);
/*     */       }
/*     */     });
/* 415 */     excel.region(0, 0, 0, 10).merge();
/* 416 */     excel.region("$A$2:$K$2").merge();
/*     */ 
/* 418 */     ((RowEditor)excel.row(2).value(val).addWidth(2000)).borderOuter(BorderStyle.DASH_DOT_DOT, Color.CORAL);
/*     */ 
/* 423 */     excel.row(4, 1).value(val).borderFull(BorderStyle.DASH_DOT, Color.RED);
/*     */ 
/* 427 */     excel.row(6).value(val, 2).borderTop(BorderStyle.THIN, Color.BLUE);
/*     */ 
/* 431 */     ((ColumnEditor)((ColumnEditor)excel.column(11).value(val).align(Align.CENTER)).borderFull(BorderStyle.THICK, Color.CORNFLOWER_BLUE)).autoWidth();
/*     */ 
/* 437 */     excel.cell(7, 0).value("=IF(B3=123,\"等于\",\"不等于\")");
/* 438 */     excel.cell(7, 1).value(Double.valueOf(0.578923D)).dataFormat("0.00%");
/* 439 */     excel.cell(7, 2).value(Double.valueOf(0.578923D), "0.00%");
/*     */ 
/* 442 */     excel.region(8, 0, 10, 1).image("http://www.jee-soft.cn/htsite/template/ht/images/index_r1_c3.jpg");
/*     */ 
/* 444 */     excel.sheet().freeze(1, 0).sheetName("这是第一个表");
/*     */ 
/* 448 */     excel.cell(8, 5).value("这个单元格设置了备注").comment("这是一条备注");
/*     */ 
/* 451 */     excel.setWorkingSheet(1).sheetName("第二个表");
/* 452 */     excel.row(0).value(val);
/* 453 */     excel.sheet().groupColumn(0, 3);
/*     */ 
/* 455 */     excel.saveExcel("D:/helloworld.xls");
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.Excel
 * JD-Core Version:    0.6.2
 */