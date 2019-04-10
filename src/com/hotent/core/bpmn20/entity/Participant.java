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
/*     */ @XmlType(name="tParticipant", propOrder={"interfaceRef", "endPointRef", "participantMultiplicity"})
/*     */ public class Participant extends BaseElement
/*     */ {
/*     */   protected List<QName> interfaceRef;
/*     */   protected List<QName> endPointRef;
/*     */   protected ParticipantMultiplicity participantMultiplicity;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName processRef;
/*     */ 
/*     */   public List<QName> getInterfaceRef()
/*     */   {
/*  85 */     if (this.interfaceRef == null) {
/*  86 */       this.interfaceRef = new ArrayList();
/*     */     }
/*  88 */     return this.interfaceRef;
/*     */   }
/*     */ 
/*     */   public List<QName> getEndPointRef()
/*     */   {
/* 114 */     if (this.endPointRef == null) {
/* 115 */       this.endPointRef = new ArrayList();
/*     */     }
/* 117 */     return this.endPointRef;
/*     */   }
/*     */ 
/*     */   public ParticipantMultiplicity getParticipantMultiplicity()
/*     */   {
/* 129 */     return this.participantMultiplicity;
/*     */   }
/*     */ 
/*     */   public void setParticipantMultiplicity(ParticipantMultiplicity value)
/*     */   {
/* 141 */     this.participantMultiplicity = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 153 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 165 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getProcessRef()
/*     */   {
/* 177 */     return this.processRef;
/*     */   }
/*     */ 
/*     */   public void setProcessRef(QName value)
/*     */   {
/* 189 */     this.processRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Participant
 * JD-Core Version:    0.6.2
 */