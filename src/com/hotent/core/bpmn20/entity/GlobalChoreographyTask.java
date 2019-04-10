/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tGlobalChoreographyTask")
/*    */ public class GlobalChoreographyTask extends Choreography
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName initiatingParticipantRef;
/*    */ 
/*    */   public QName getInitiatingParticipantRef()
/*    */   {
/* 54 */     return this.initiatingParticipantRef;
/*    */   }
/*    */ 
/*    */   public void setInitiatingParticipantRef(QName value)
/*    */   {
/* 66 */     this.initiatingParticipantRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.GlobalChoreographyTask
 * JD-Core Version:    0.6.2
 */