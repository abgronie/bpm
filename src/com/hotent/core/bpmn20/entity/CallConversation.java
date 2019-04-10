/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tCallConversation", propOrder={"participantAssociation"})
/*     */ public class CallConversation extends ConversationNode
/*     */ {
/*     */   protected List<ParticipantAssociation> participantAssociation;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName calledCollaborationRef;
/*     */ 
/*     */   public List<ParticipantAssociation> getParticipantAssociation()
/*     */   {
/*  76 */     if (this.participantAssociation == null) {
/*  77 */       this.participantAssociation = new ArrayList();
/*     */     }
/*  79 */     return this.participantAssociation;
/*     */   }
/*     */ 
/*     */   public QName getCalledCollaborationRef()
/*     */   {
/*  91 */     return this.calledCollaborationRef;
/*     */   }
/*     */ 
/*     */   public void setCalledCollaborationRef(QName value)
/*     */   {
/* 103 */     this.calledCollaborationRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CallConversation
 * JD-Core Version:    0.6.2
 */