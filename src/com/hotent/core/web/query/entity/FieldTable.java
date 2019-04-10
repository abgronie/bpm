/*    */ package com.hotent.core.web.query.entity;
/*    */ 
/*    */ public abstract class FieldTable
/*    */ {
/*    */   protected String tableName;
/*    */   protected String fieldName;
/* 23 */   protected boolean isMain = true;
/*    */   protected String fixFieldName;
/*    */ 
/*    */   public String getTableName()
/*    */   {
/* 31 */     return this.tableName;
/*    */   }
/*    */ 
/*    */   public void setTableName(String tableName) {
/* 35 */     this.tableName = tableName;
/*    */   }
/*    */ 
/*    */   public String getFieldName() {
/* 39 */     return this.fieldName;
/*    */   }
/*    */ 
/*    */   public void setFieldName(String fieldName) {
/* 43 */     this.fieldName = fieldName;
/*    */   }
/*    */ 
/*    */   public boolean isMain() {
/* 47 */     return this.isMain;
/*    */   }
/*    */ 
/*    */   public void setMain(boolean isMain) {
/* 51 */     this.isMain = isMain;
/*    */   }
/*    */ 
/*    */   public String getFixFieldName() {
/* 55 */     return FieldTableUtil.fixFieldName(this.fieldName, this.tableName);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.FieldTable
 * JD-Core Version:    0.6.2
 */