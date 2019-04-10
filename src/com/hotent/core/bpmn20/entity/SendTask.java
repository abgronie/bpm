/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tSendTask")
/*     */ public class SendTask extends Task
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String implementation;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName messageRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName operationRef;
/*     */ 
/*     */   public String getImplementation()
/*     */   {
/*  60 */     if (this.implementation == null) {
/*  61 */       return "##WebService";
/*     */     }
/*  63 */     return this.implementation;
/*     */   }
/*     */ 
/*     */   public void setImplementation(String value)
/*     */   {
/*  76 */     this.implementation = value;
/*     */   }
/*     */ 
/*     */   public QName getMessageRef()
/*     */   {
/*  88 */     return this.messageRef;
/*     */   }
/*     */ 
/*     */   public void setMessageRef(QName value)
/*     */   {
/* 100 */     this.messageRef = value;
/*     */   }
/*     */ 
/*     */   public QName getOperationRef()
/*     */   {
/* 112 */     return this.operationRef;
/*     */   }
/*     */ 
/*     */   public void setOperationRef(QName value)
/*     */   {
/* 124 */     this.operationRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.SendTask
 * JD-Core Version:    0.6.2
 */