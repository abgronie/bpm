/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tAssociation")
/*     */ public class Association extends Artifact
/*     */ {
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName sourceRef;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName targetRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected AssociationDirection associationDirection;
/*     */ 
/*     */   public QName getSourceRef()
/*     */   {
/*  60 */     return this.sourceRef;
/*     */   }
/*     */ 
/*     */   public void setSourceRef(QName value)
/*     */   {
/*  72 */     this.sourceRef = value;
/*     */   }
/*     */ 
/*     */   public QName getTargetRef()
/*     */   {
/*  84 */     return this.targetRef;
/*     */   }
/*     */ 
/*     */   public void setTargetRef(QName value)
/*     */   {
/*  96 */     this.targetRef = value;
/*     */   }
/*     */ 
/*     */   public AssociationDirection getAssociationDirection()
/*     */   {
/* 108 */     if (this.associationDirection == null) {
/* 109 */       return AssociationDirection.NONE;
/*     */     }
/* 111 */     return this.associationDirection;
/*     */   }
/*     */ 
/*     */   public void setAssociationDirection(AssociationDirection value)
/*     */   {
/* 124 */     this.associationDirection = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Association
 * JD-Core Version:    0.6.2
 */