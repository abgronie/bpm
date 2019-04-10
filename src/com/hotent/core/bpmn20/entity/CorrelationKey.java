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
/*     */ @XmlType(name="tCorrelationKey", propOrder={"correlationPropertyRef"})
/*     */ public class CorrelationKey extends BaseElement
/*     */ {
/*     */   protected List<QName> correlationPropertyRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public List<QName> getCorrelationPropertyRef()
/*     */   {
/*  76 */     if (this.correlationPropertyRef == null) {
/*  77 */       this.correlationPropertyRef = new ArrayList();
/*     */     }
/*  79 */     return this.correlationPropertyRef;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  91 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 103 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CorrelationKey
 * JD-Core Version:    0.6.2
 */