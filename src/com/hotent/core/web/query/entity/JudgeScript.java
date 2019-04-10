/*    */ package com.hotent.core.web.query.entity;
/*    */ 
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class JudgeScript
/*    */ {
/*    */   protected String value;
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 20 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 24 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 29 */     return new ToStringBuilder(this).append("value", this.value).toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.JudgeScript
 * JD-Core Version:    0.6.2
 */