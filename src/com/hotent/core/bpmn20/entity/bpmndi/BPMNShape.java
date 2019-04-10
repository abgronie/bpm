/*     */ package com.hotent.core.bpmn20.entity.bpmndi;
/*     */ 
/*     */ import com.hotent.core.bpmn20.entity.omgdi.LabeledShape;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="BPMNShape", propOrder={"bpmnLabel"})
/*     */ public class BPMNShape extends LabeledShape
/*     */ {
/*     */ 
/*     */   @XmlElement(name="BPMNLabel")
/*     */   protected BPMNLabel bpmnLabel;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName bpmnElement;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isHorizontal;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isExpanded;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isMarkerVisible;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isMessageVisible;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected ParticipantBandKind participantBandKind;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName choreographyActivityShape;
/*     */ 
/*     */   public BPMNLabel getBPMNLabel()
/*     */   {
/*  82 */     return this.bpmnLabel;
/*     */   }
/*     */ 
/*     */   public void setBPMNLabel(BPMNLabel value)
/*     */   {
/*  94 */     this.bpmnLabel = value;
/*     */   }
/*     */ 
/*     */   public QName getBpmnElement()
/*     */   {
/* 106 */     return this.bpmnElement;
/*     */   }
/*     */ 
/*     */   public void setBpmnElement(QName value)
/*     */   {
/* 118 */     this.bpmnElement = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsHorizontal()
/*     */   {
/* 130 */     return this.isHorizontal;
/*     */   }
/*     */ 
/*     */   public void setIsHorizontal(Boolean value)
/*     */   {
/* 142 */     this.isHorizontal = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsExpanded()
/*     */   {
/* 154 */     return this.isExpanded;
/*     */   }
/*     */ 
/*     */   public void setIsExpanded(Boolean value)
/*     */   {
/* 166 */     this.isExpanded = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsMarkerVisible()
/*     */   {
/* 178 */     return this.isMarkerVisible;
/*     */   }
/*     */ 
/*     */   public void setIsMarkerVisible(Boolean value)
/*     */   {
/* 190 */     this.isMarkerVisible = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsMessageVisible()
/*     */   {
/* 202 */     return this.isMessageVisible;
/*     */   }
/*     */ 
/*     */   public void setIsMessageVisible(Boolean value)
/*     */   {
/* 214 */     this.isMessageVisible = value;
/*     */   }
/*     */ 
/*     */   public ParticipantBandKind getParticipantBandKind()
/*     */   {
/* 226 */     return this.participantBandKind;
/*     */   }
/*     */ 
/*     */   public void setParticipantBandKind(ParticipantBandKind value)
/*     */   {
/* 238 */     this.participantBandKind = value;
/*     */   }
/*     */ 
/*     */   public QName getChoreographyActivityShape()
/*     */   {
/* 250 */     return this.choreographyActivityShape;
/*     */   }
/*     */ 
/*     */   public void setChoreographyActivityShape(QName value)
/*     */   {
/* 262 */     this.choreographyActivityShape = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.BPMNShape
 * JD-Core Version:    0.6.2
 */