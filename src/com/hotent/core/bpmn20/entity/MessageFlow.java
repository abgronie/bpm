/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tMessageFlow")
/*     */ public class MessageFlow extends BaseElement
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
/*     */   @XmlAttribute
/*     */   protected QName messageRef;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  63 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  75 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getSourceRef()
/*     */   {
/*  87 */     return this.sourceRef;
/*     */   }
/*     */ 
/*     */   public void setSourceRef(QName value)
/*     */   {
/*  99 */     this.sourceRef = value;
/*     */   }
/*     */ 
/*     */   public QName getTargetRef()
/*     */   {
/* 111 */     return this.targetRef;
/*     */   }
/*     */ 
/*     */   public void setTargetRef(QName value)
/*     */   {
/* 123 */     this.targetRef = value;
/*     */   }
/*     */ 
/*     */   public QName getMessageRef()
/*     */   {
/* 135 */     return this.messageRef;
/*     */   }
/*     */ 
/*     */   public void setMessageRef(QName value)
/*     */   {
/* 147 */     this.messageRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.MessageFlow
 * JD-Core Version:    0.6.2
 */