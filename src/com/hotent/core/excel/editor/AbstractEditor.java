/*    */ package com.hotent.core.excel.editor;
/*    */ 
/*    */ import com.hotent.core.excel.ExcelContext;
/*    */ import com.hotent.core.excel.util.ExcelUtil;
/*    */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*    */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*    */ import org.apache.poi.hssf.usermodel.HSSFFont;
/*    */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*    */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*    */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*    */ 
/*    */ public abstract class AbstractEditor
/*    */ {
/*    */   protected HSSFWorkbook workBook;
/*    */   protected HSSFCellStyle tempCellStyle;
/*    */   protected HSSFFont tempFont;
/*    */   protected HSSFSheet workingSheet;
/*    */   protected ExcelContext ctx;
/*    */ 
/*    */   protected AbstractEditor(ExcelContext context)
/*    */   {
/* 25 */     this.workBook = context.getWorkBook();
/* 26 */     this.workingSheet = context.getWorkingSheet();
/* 27 */     this.tempFont = context.getTempFont();
/* 28 */     this.tempCellStyle = context.getTempCellStyle();
/* 29 */     this.ctx = context;
/*    */   }
/*    */ 
/*    */   protected HSSFRow getRow(int row)
/*    */   {
/* 40 */     return ExcelUtil.getHSSFRow(this.workingSheet, row);
/*    */   }
/*    */ 
/*    */   protected HSSFCell getCell(int row, int col)
/*    */   {
/* 53 */     return ExcelUtil.getHSSFCell(this.workingSheet, row, col);
/*    */   }
/*    */ 
/*    */   protected HSSFCell getCell(HSSFRow row, int col)
/*    */   {
/* 63 */     return ExcelUtil.getHSSFCell(row, col);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.AbstractEditor
 * JD-Core Version:    0.6.2
 */