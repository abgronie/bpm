/*     */ package com.hotent.core.bpmn20.entity.bpmndi;
/*     */ 
/*     */ import com.hotent.core.bpmn20.entity.omgdi.LabeledEdge;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="BPMNEdge", propOrder={"bpmnLabel"})
/*     */ public class BPMNEdge extends LabeledEdge
/*     */ {
/*     */ 
/*     */   @XmlElement(name="BPMNLabel")
/*     */   protected BPMNLabel bpmnLabel;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName bpmnElement;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName sourceElement;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName targetElement;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected MessageVisibleKind messageVisibleKind;
/*     */ 
/*     */   public BPMNLabel getBPMNLabel()
/*     */   {
/*  73 */     return this.bpmnLabel;
/*     */   }
/*     */ 
/*     */   public void setBPMNLabel(BPMNLabel value)
/*     */   {
/*  85 */     this.bpmnLabel = value;
/*     */   }
/*     */ 
/*     */   public QName getBpmnElement()
/*     */   {
/*  97 */     return this.bpmnElement;
/*     */   }
/*     */ 
/*     */   public void setBpmnElement(QName value)
/*     */   {
/* 109 */     this.bpmnElement = value;
/*     */   }
/*     */ 
/*     */   public QName getSourceElement()
/*     */   {
/* 121 */     return this.sourceElement;
/*     */   }
/*     */ 
/*     */   public void setSourceElement(QName value)
/*     */   {
/* 133 */     this.sourceElement = value;
/*     */   }
/*     */ 
/*     */   public QName getTargetElement()
/*     */   {
/* 145 */     return this.targetElement;
/*     */   }
/*     */ 
/*     */   public void setTargetElement(QName value)
/*     */   {
/* 157 */     this.targetElement = value;
/*     */   }
/*     */ 
/*     */   public MessageVisibleKind getMessageVisibleKind()
/*     */   {
/* 169 */     return this.messageVisibleKind;
/*     */   }
/*     */ 
/*     */   public void setMessageVisibleKind(MessageVisibleKind value)
/*     */   {
/* 181 */     this.messageVisibleKind = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.BPMNEdge
 * JD-Core Version:    0.6.2
 */