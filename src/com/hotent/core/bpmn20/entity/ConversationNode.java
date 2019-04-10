/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tConversationNode", propOrder={"participantRef", "messageFlowRef", "correlationKey"})
/*     */ @XmlSeeAlso({Conversation.class, CallConversation.class, SubConversation.class})
/*     */ public abstract class ConversationNode extends BaseElement
/*     */ {
/*     */   protected List<QName> participantRef;
/*     */   protected List<QName> messageFlowRef;
/*     */   protected List<CorrelationKey> correlationKey;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public List<QName> getParticipantRef()
/*     */   {
/*  88 */     if (this.participantRef == null) {
/*  89 */       this.participantRef = new ArrayList();
/*     */     }
/*  91 */     return this.participantRef;
/*     */   }
/*     */ 
/*     */   public List<QName> getMessageFlowRef()
/*     */   {
/* 117 */     if (this.messageFlowRef == null) {
/* 118 */       this.messageFlowRef = new ArrayList();
/*     */     }
/* 120 */     return this.messageFlowRef;
/*     */   }
/*     */ 
/*     */   public List<CorrelationKey> getCorrelationKey()
/*     */   {
/* 146 */     if (this.correlationKey == null) {
/* 147 */       this.correlationKey = new ArrayList();
/*     */     }
/* 149 */     return this.correlationKey;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 161 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 173 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ConversationNode
 * JD-Core Version:    0.6.2
 */