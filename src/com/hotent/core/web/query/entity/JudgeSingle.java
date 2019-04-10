/*    */ package com.hotent.core.web.query.entity;
/*    */ 
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class JudgeSingle extends FieldTable
/*    */ {
/*    */   protected String compare;
/*    */   protected String value;
/*    */ 
/*    */   public String getCompare()
/*    */   {
/* 30 */     return this.compare;
/*    */   }
/*    */ 
/*    */   public void setCompare(String compare) {
/* 34 */     this.compare = compare;
/*    */   }
/*    */ 
/*    */   public String getValue() {
/* 38 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 42 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 47 */     return new ToStringBuilder(this).append("fieldName", this.fieldName).append("compare", this.compare).append("value", this.value).toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.JudgeSingle
 * JD-Core Version:    0.6.2
 */