/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tConversationLink")
/*     */ public class ConversationLink extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName sourceRef;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName targetRef;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  60 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  72 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getSourceRef()
/*     */   {
/*  84 */     return this.sourceRef;
/*     */   }
/*     */ 
/*     */   public void setSourceRef(QName value)
/*     */   {
/*  96 */     this.sourceRef = value;
/*     */   }
/*     */ 
/*     */   public QName getTargetRef()
/*     */   {
/* 108 */     return this.targetRef;
/*     */   }
/*     */ 
/*     */   public void setTargetRef(QName value)
/*     */   {
/* 120 */     this.targetRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ConversationLink
 * JD-Core Version:    0.6.2
 */