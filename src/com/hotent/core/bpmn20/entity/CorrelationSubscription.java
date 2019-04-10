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
/*     */ @XmlType(name="tCorrelationSubscription", propOrder={"correlationPropertyBinding"})
/*     */ public class CorrelationSubscription extends BaseElement
/*     */ {
/*     */   protected List<CorrelationPropertyBinding> correlationPropertyBinding;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName correlationKeyRef;
/*     */ 
/*     */   public List<CorrelationPropertyBinding> getCorrelationPropertyBinding()
/*     */   {
/*  76 */     if (this.correlationPropertyBinding == null) {
/*  77 */       this.correlationPropertyBinding = new ArrayList();
/*     */     }
/*  79 */     return this.correlationPropertyBinding;
/*     */   }
/*     */ 
/*     */   public QName getCorrelationKeyRef()
/*     */   {
/*  91 */     return this.correlationKeyRef;
/*     */   }
/*     */ 
/*     */   public void setCorrelationKeyRef(QName value)
/*     */   {
/* 103 */     this.correlationKeyRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CorrelationSubscription
 * JD-Core Version:    0.6.2
 */