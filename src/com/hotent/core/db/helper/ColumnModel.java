/*    */ package com.hotent.core.db.helper;
/*    */ 
/*    */ public class ColumnModel
/*    */ {
/* 10 */   private boolean pk = false;
/* 11 */   private String propery = "";
/* 12 */   private String columnName = "";
/* 13 */   private boolean canUpd = true;
/*    */ 
/*    */   public boolean getPk()
/*    */   {
/* 21 */     return this.pk;
/*    */   }
/*    */   public void setPk(boolean pk) {
/* 24 */     this.pk = pk;
/*    */   }
/*    */ 
/*    */   public String getPropery()
/*    */   {
/* 32 */     return this.propery;
/*    */   }
/*    */   public void setPropery(String propery) {
/* 35 */     this.propery = propery;
/*    */   }
/*    */ 
/*    */   public String getColumnName()
/*    */   {
/* 43 */     return this.columnName;
/*    */   }
/*    */   public void setColumnName(String columnName) {
/* 46 */     this.columnName = columnName;
/*    */   }
/*    */ 
/*    */   public boolean getCanUpd()
/*    */   {
/* 54 */     return this.canUpd;
/*    */   }
/*    */   public void setCanUpd(boolean canUpd) {
/* 57 */     this.canUpd = canUpd;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.helper.ColumnModel
 * JD-Core Version:    0.6.2
 */