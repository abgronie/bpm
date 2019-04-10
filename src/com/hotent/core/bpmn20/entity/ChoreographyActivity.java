/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tChoreographyActivity", propOrder={"participantRef", "correlationKey"})
/*     */ @XmlSeeAlso({SubChoreography.class, ChoreographyTask.class, CallChoreography.class})
/*     */ public abstract class ChoreographyActivity extends FlowNode
/*     */ {
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<QName> participantRef;
/*     */   protected List<CorrelationKey> correlationKey;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName initiatingParticipantRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected ChoreographyLoopType loopType;
/*     */ 
/*     */   public List<QName> getParticipantRef()
/*     */   {
/*  90 */     if (this.participantRef == null) {
/*  91 */       this.participantRef = new ArrayList();
/*     */     }
/*  93 */     return this.participantRef;
/*     */   }
/*     */ 
/*     */   public List<CorrelationKey> getCorrelationKey()
/*     */   {
/* 119 */     if (this.correlationKey == null) {
/* 120 */       this.correlationKey = new ArrayList();
/*     */     }
/* 122 */     return this.correlationKey;
/*     */   }
/*     */ 
/*     */   public QName getInitiatingParticipantRef()
/*     */   {
/* 134 */     return this.initiatingParticipantRef;
/*     */   }
/*     */ 
/*     */   public void setInitiatingParticipantRef(QName value)
/*     */   {
/* 146 */     this.initiatingParticipantRef = value;
/*     */   }
/*     */ 
/*     */   public ChoreographyLoopType getLoopType()
/*     */   {
/* 158 */     if (this.loopType == null) {
/* 159 */       return ChoreographyLoopType.NONE;
/*     */     }
/* 161 */     return this.loopType;
/*     */   }
/*     */ 
/*     */   public void setLoopType(ChoreographyLoopType value)
/*     */   {
/* 174 */     this.loopType = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ChoreographyActivity
 * JD-Core Version:    0.6.2
 */