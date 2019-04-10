/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tGlobalBusinessRuleTask")
/*    */ public class GlobalBusinessRuleTask extends GlobalTask
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String implementation;
/*    */ 
/*    */   public String getImplementation()
/*    */   {
/* 53 */     if (this.implementation == null) {
/* 54 */       return "##unspecified";
/*    */     }
/* 56 */     return this.implementation;
/*    */   }
/*    */ 
/*    */   public void setImplementation(String value)
/*    */   {
/* 69 */     this.implementation = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.GlobalBusinessRuleTask
 * JD-Core Version:    0.6.2
 */