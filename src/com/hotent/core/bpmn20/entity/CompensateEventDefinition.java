/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tCompensateEventDefinition")
/*    */ public class CompensateEventDefinition extends EventDefinition
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected Boolean waitForCompletion;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName activityRef;
/*    */ 
/*    */   public Boolean isWaitForCompletion()
/*    */   {
/* 57 */     return this.waitForCompletion;
/*    */   }
/*    */ 
/*    */   public void setWaitForCompletion(Boolean value)
/*    */   {
/* 69 */     this.waitForCompletion = value;
/*    */   }
/*    */ 
/*    */   public QName getActivityRef()
/*    */   {
/* 81 */     return this.activityRef;
/*    */   }
/*    */ 
/*    */   public void setActivityRef(QName value)
/*    */   {
/* 93 */     this.activityRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CompensateEventDefinition
 * JD-Core Version:    0.6.2
 */