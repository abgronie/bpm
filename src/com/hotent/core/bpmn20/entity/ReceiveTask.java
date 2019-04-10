/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tReceiveTask")
/*     */ public class ReceiveTask extends Task
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String implementation;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean instantiate;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName messageRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName operationRef;
/*     */ 
/*     */   public String getImplementation()
/*     */   {
/*  63 */     if (this.implementation == null) {
/*  64 */       return "##WebService";
/*     */     }
/*  66 */     return this.implementation;
/*     */   }
/*     */ 
/*     */   public void setImplementation(String value)
/*     */   {
/*  79 */     this.implementation = value;
/*     */   }
/*     */ 
/*     */   public boolean isInstantiate()
/*     */   {
/*  91 */     if (this.instantiate == null) {
/*  92 */       return false;
/*     */     }
/*  94 */     return this.instantiate.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setInstantiate(Boolean value)
/*     */   {
/* 107 */     this.instantiate = value;
/*     */   }
/*     */ 
/*     */   public QName getMessageRef()
/*     */   {
/* 119 */     return this.messageRef;
/*     */   }
/*     */ 
/*     */   public void setMessageRef(QName value)
/*     */   {
/* 131 */     this.messageRef = value;
/*     */   }
/*     */ 
/*     */   public QName getOperationRef()
/*     */   {
/* 143 */     return this.operationRef;
/*     */   }
/*     */ 
/*     */   public void setOperationRef(QName value)
/*     */   {
/* 155 */     this.operationRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ReceiveTask
 * JD-Core Version:    0.6.2
 */