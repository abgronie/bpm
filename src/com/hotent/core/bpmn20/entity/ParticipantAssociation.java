/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tParticipantAssociation", propOrder={"innerParticipantRef", "outerParticipantRef"})
/*    */ public class ParticipantAssociation extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected QName innerParticipantRef;
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected QName outerParticipantRef;
/*    */ 
/*    */   public QName getInnerParticipantRef()
/*    */   {
/* 62 */     return this.innerParticipantRef;
/*    */   }
/*    */ 
/*    */   public void setInnerParticipantRef(QName value)
/*    */   {
/* 74 */     this.innerParticipantRef = value;
/*    */   }
/*    */ 
/*    */   public QName getOuterParticipantRef()
/*    */   {
/* 86 */     return this.outerParticipantRef;
/*    */   }
/*    */ 
/*    */   public void setOuterParticipantRef(QName value)
/*    */   {
/* 98 */     this.outerParticipantRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ParticipantAssociation
 * JD-Core Version:    0.6.2
 */