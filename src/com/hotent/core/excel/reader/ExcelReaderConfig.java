/*    */ package com.hotent.core.excel.reader;
/*    */ 
/*    */ public class ExcelReaderConfig
/*    */ {
/*    */   private String[] columns;
/* 13 */   private int currPosittion = 0;
/*    */ 
/* 15 */   private int colStartPosittion = 0;
/*    */ 
/*    */   public String[] getColumns()
/*    */   {
/* 19 */     return this.columns;
/*    */   }
/*    */ 
/*    */   public void setColumns(String[] columns)
/*    */   {
/* 31 */     this.columns = columns;
/*    */   }
/*    */ 
/*    */   public int getCurrPosittion() {
/* 35 */     return this.currPosittion;
/*    */   }
/*    */ 
/*    */   public void setCurrPosittion(int currPosittion)
/*    */   {
/* 43 */     this.currPosittion = (currPosittion - 1);
/*    */   }
/*    */   public int getColStartPosittion() {
/* 46 */     return this.colStartPosittion;
/*    */   }
/*    */ 
/*    */   public void setColStartPosittion(int colStartPosittion)
/*    */   {
/* 53 */     this.colStartPosittion = colStartPosittion;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.reader.ExcelReaderConfig
 * JD-Core Version:    0.6.2
 */