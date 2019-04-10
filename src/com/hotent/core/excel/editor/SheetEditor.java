/*     */ package com.hotent.core.excel.editor;
/*     */ 
/*     */ import com.hotent.core.excel.ExcelContext;
/*     */ import com.hotent.core.excel.editor.listener.CellValueListener;
/*     */ import com.hotent.core.excel.util.ExcelUtil;
/*     */ import java.util.List;
/*     */ import org.apache.poi.hssf.usermodel.DVConstraint;
/*     */ import org.apache.poi.hssf.usermodel.HSSFDataValidation;
/*     */ import org.apache.poi.hssf.usermodel.HSSFFooter;
/*     */ import org.apache.poi.hssf.usermodel.HSSFHeader;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.usermodel.CellStyle;
/*     */ import org.apache.poi.ss.usermodel.DataFormat;
/*     */ import org.apache.poi.ss.util.CellRangeAddressList;
/*     */ 
/*     */ public class SheetEditor extends AbstractEditor
/*     */ {
/*     */   private HSSFSheet sheet;
/*     */   private int sheetIndex;
/*     */ 
/*     */   public SheetEditor(HSSFSheet sheet, ExcelContext context)
/*     */   {
/*  27 */     super(context);
/*  28 */     this.sheet = sheet;
/*  29 */     this.sheetIndex = this.workBook.getSheetIndex(this.sheet);
/*     */   }
/*     */ 
/*     */   public SheetEditor header(String left, String center, String right)
/*     */   {
/*  41 */     HSSFHeader header = this.sheet.getHeader();
/*  42 */     header.setLeft(left == null ? "" : left);
/*  43 */     header.setCenter(center == null ? "" : center);
/*  44 */     header.setRight(right == null ? "" : right);
/*  45 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor footer(String left, String center, String right)
/*     */   {
/*  57 */     HSSFFooter footer = this.sheet.getFooter();
/*  58 */     footer.setLeft(left == null ? "" : left);
/*  59 */     footer.setCenter(center == null ? "" : center);
/*  60 */     footer.setRight(right == null ? "" : right);
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor sheetName(String name)
/*     */   {
/*  72 */     sheetName(name, false);
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor sheetName(String name, boolean autoRename)
/*     */   {
/*  86 */     if (autoRename) {
/*  87 */       String newName = new String(name);
/*  88 */       HSSFSheet sheet = this.workBook.getSheet(name);
/*  89 */       while (sheet != null) {
/*  90 */         newName = newName + "_";
/*  91 */         sheet = this.workBook.getSheet(newName);
/*     */       }
/*  93 */       this.workBook.setSheetName(this.sheetIndex, newName);
/*     */     } else {
/*  95 */       this.workBook.setSheetName(this.sheetIndex, name);
/*     */     }
/*  97 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor active()
/*     */   {
/* 106 */     this.workBook.setActiveSheet(this.sheetIndex);
/* 107 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor freeze(int row, int col)
/*     */   {
/* 120 */     if (row < 0) {
/* 121 */       row = 0;
/*     */     }
/* 123 */     if (col < 0) {
/* 124 */       col = 0;
/*     */     }
/* 126 */     this.sheet.createFreezePane(col, row, col, row);
/* 127 */     return this;
/*     */   }
/*     */ 
/*     */   public int getLastRowNum()
/*     */   {
/* 136 */     return ExcelUtil.getLastRowNum(this.sheet);
/*     */   }
/*     */ 
/*     */   public SheetEditor displayGridlines(boolean show)
/*     */   {
/* 146 */     this.sheet.setDisplayGridlines(show);
/* 147 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor printGridlines(boolean newPrintGridlines)
/*     */   {
/* 157 */     this.sheet.setPrintGridlines(newPrintGridlines);
/* 158 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor fitToPage(boolean isFit)
/*     */   {
/* 168 */     this.sheet.setFitToPage(isFit);
/* 169 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor horizontallyCenter(boolean isCenter)
/*     */   {
/* 179 */     this.sheet.setHorizontallyCenter(isCenter);
/* 180 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor password(String pw)
/*     */   {
/* 191 */     this.sheet.protectSheet(pw);
/* 192 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor printSetup(IPrintSetup printSetup)
/*     */   {
/* 202 */     printSetup.setup(this.sheet.getPrintSetup());
/* 203 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor autobreaks(boolean b)
/*     */   {
/* 213 */     this.sheet.setAutobreaks(b);
/* 214 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor addCellValueListener(CellValueListener listener)
/*     */   {
/* 224 */     this.ctx.getListenerList(this.sheetIndex).add(listener);
/* 225 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor removeCellValueListener(CellValueListener listener)
/*     */   {
/* 235 */     this.ctx.getListenerList(this.sheetIndex).remove(listener);
/* 236 */     return this;
/*     */   }
/*     */ 
/*     */   public HSSFSheet toHSSFSheet()
/*     */   {
/* 245 */     return this.sheet;
/*     */   }
/*     */ 
/*     */   public int getSheetIndex()
/*     */   {
/* 254 */     return this.sheetIndex;
/*     */   }
/*     */ 
/*     */   public SheetEditor groupRow(int fromRow, int toRow)
/*     */   {
/* 267 */     this.sheet.groupRow(fromRow, toRow);
/* 268 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor groupColumn(int fromColumn, int toColumn)
/*     */   {
/* 281 */     this.sheet.groupColumn(fromColumn, toColumn);
/* 282 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor setDefaultColumnStyle(int column, String format)
/*     */   {
/* 292 */     CellStyle cellStyle = this.workBook.createCellStyle();
/* 293 */     DataFormat dataFormat = this.workBook.createDataFormat();
/* 294 */     cellStyle.setDataFormat(dataFormat.getFormat(format));
/* 295 */     this.sheet.setDefaultColumnStyle(column, cellStyle);
/* 296 */     return this;
/*     */   }
/*     */   public SheetEditor addValidationData(int firstRow, int column, String[] explicitListValues) {
/* 299 */     addValidationData(firstRow, 65535, column, column, explicitListValues);
/* 300 */     return this;
/*     */   }
/*     */ 
/*     */   public SheetEditor addValidationData(int firstRow, int lastRow, int firstColumn, int lastColumn, String[] explicitListValues)
/*     */   {
/* 305 */     DVConstraint constraint = DVConstraint.createExplicitListConstraint(explicitListValues);
/*     */ 
/* 307 */     CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
/*     */ 
/* 310 */     HSSFDataValidation validation = new HSSFDataValidation(regions, constraint);
/*     */ 
/* 313 */     validation.setShowErrorBox(true);
/*     */ 
/* 315 */     this.sheet.addValidationData(validation);
/* 316 */     return this;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.SheetEditor
 * JD-Core Version:    0.6.2
 */