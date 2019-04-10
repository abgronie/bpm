/*    */ package com.hotent.core.web.query.entity;
/*    */ 
/*    */ public class FieldSort extends FieldTable
/*    */ {
/* 14 */   private String orderBy = "ASC";
/*    */ 
/*    */   public FieldSort()
/*    */   {
/*    */   }
/*    */ 
/*    */   public FieldSort(String tableName, String fieldName, String orderBy) {
/* 21 */     this.tableName = tableName;
/* 22 */     this.fieldName = fieldName;
/* 23 */     this.orderBy = orderBy;
/*    */   }
/*    */ 
/*    */   public String getOrderBy() {
/* 27 */     return this.orderBy;
/*    */   }
/*    */ 
/*    */   public void setOrderBy(String orderBy) {
/* 31 */     this.orderBy = orderBy;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.FieldSort
 * JD-Core Version:    0.6.2
 */