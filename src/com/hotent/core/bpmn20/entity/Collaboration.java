/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tCollaboration", propOrder={"participant", "messageFlow", "artifact", "conversationNode", "conversationAssociation", "participantAssociation", "messageFlowAssociation", "correlationKey", "choreographyRef", "conversationLink"})
/*     */ @XmlSeeAlso({GlobalConversation.class, Choreography.class})
/*     */ public class Collaboration extends RootElement
/*     */ {
/*     */   protected List<Participant> participant;
/*     */   protected List<MessageFlow> messageFlow;
/*     */ 
/*     */   @XmlElementRef(name="artifact", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends Artifact>> artifact;
/*     */ 
/*     */   @XmlElementRef(name="conversationNode", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends ConversationNode>> conversationNode;
/*     */   protected List<ConversationAssociation> conversationAssociation;
/*     */   protected List<ParticipantAssociation> participantAssociation;
/*     */   protected List<MessageFlowAssociation> messageFlowAssociation;
/*     */   protected List<CorrelationKey> correlationKey;
/*     */   protected List<QName> choreographyRef;
/*     */   protected List<ConversationLink> conversationLink;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isClosed;
/*     */ 
/*     */   public List<Participant> getParticipant()
/*     */   {
/* 115 */     if (this.participant == null) {
/* 116 */       this.participant = new ArrayList();
/*     */     }
/* 118 */     return this.participant;
/*     */   }
/*     */ 
/*     */   public List<MessageFlow> getMessageFlow()
/*     */   {
/* 144 */     if (this.messageFlow == null) {
/* 145 */       this.messageFlow = new ArrayList();
/*     */     }
/* 147 */     return this.messageFlow;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends Artifact>> getArtifact()
/*     */   {
/* 176 */     if (this.artifact == null) {
/* 177 */       this.artifact = new ArrayList();
/*     */     }
/* 179 */     return this.artifact;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends ConversationNode>> getConversationNode()
/*     */   {
/* 208 */     if (this.conversationNode == null) {
/* 209 */       this.conversationNode = new ArrayList();
/*     */     }
/* 211 */     return this.conversationNode;
/*     */   }
/*     */ 
/*     */   public List<ConversationAssociation> getConversationAssociation()
/*     */   {
/* 237 */     if (this.conversationAssociation == null) {
/* 238 */       this.conversationAssociation = new ArrayList();
/*     */     }
/* 240 */     return this.conversationAssociation;
/*     */   }
/*     */ 
/*     */   public List<ParticipantAssociation> getParticipantAssociation()
/*     */   {
/* 266 */     if (this.participantAssociation == null) {
/* 267 */       this.participantAssociation = new ArrayList();
/*     */     }
/* 269 */     return this.participantAssociation;
/*     */   }
/*     */ 
/*     */   public List<MessageFlowAssociation> getMessageFlowAssociation()
/*     */   {
/* 295 */     if (this.messageFlowAssociation == null) {
/* 296 */       this.messageFlowAssociation = new ArrayList();
/*     */     }
/* 298 */     return this.messageFlowAssociation;
/*     */   }
/*     */ 
/*     */   public List<CorrelationKey> getCorrelationKey()
/*     */   {
/* 324 */     if (this.correlationKey == null) {
/* 325 */       this.correlationKey = new ArrayList();
/*     */     }
/* 327 */     return this.correlationKey;
/*     */   }
/*     */ 
/*     */   public List<QName> getChoreographyRef()
/*     */   {
/* 353 */     if (this.choreographyRef == null) {
/* 354 */       this.choreographyRef = new ArrayList();
/*     */     }
/* 356 */     return this.choreographyRef;
/*     */   }
/*     */ 
/*     */   public List<ConversationLink> getConversationLink()
/*     */   {
/* 382 */     if (this.conversationLink == null) {
/* 383 */       this.conversationLink = new ArrayList();
/*     */     }
/* 385 */     return this.conversationLink;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 397 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 409 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsClosed()
/*     */   {
/* 421 */     if (this.isClosed == null) {
/* 422 */       return false;
/*     */     }
/* 424 */     return this.isClosed.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsClosed(Boolean value)
/*     */   {
/* 437 */     this.isClosed = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Collaboration
 * JD-Core Version:    0.6.2
 */