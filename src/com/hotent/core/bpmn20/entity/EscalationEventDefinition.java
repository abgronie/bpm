/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tEscalationEventDefinition")
/*    */ public class EscalationEventDefinition extends EventDefinition
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName escalationRef;
/*    */ 
/*    */   public QName getEscalationRef()
/*    */   {
/* 54 */     return this.escalationRef;
/*    */   }
/*    */ 
/*    */   public void setEscalationRef(QName value)
/*    */   {
/* 66 */     this.escalationRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.EscalationEventDefinition
 * JD-Core Version:    0.6.2
 */