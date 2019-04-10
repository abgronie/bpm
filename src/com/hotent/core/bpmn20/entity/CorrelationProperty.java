/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tCorrelationProperty", propOrder={"correlationPropertyRetrievalExpression"})
/*     */ public class CorrelationProperty extends RootElement
/*     */ {
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<CorrelationPropertyRetrievalExpression> correlationPropertyRetrievalExpression;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName type;
/*     */ 
/*     */   public List<CorrelationPropertyRetrievalExpression> getCorrelationPropertyRetrievalExpression()
/*     */   {
/*  81 */     if (this.correlationPropertyRetrievalExpression == null) {
/*  82 */       this.correlationPropertyRetrievalExpression = new ArrayList();
/*     */     }
/*  84 */     return this.correlationPropertyRetrievalExpression;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  96 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 108 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getType()
/*     */   {
/* 120 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(QName value)
/*     */   {
/* 132 */     this.type = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CorrelationProperty
 * JD-Core Version:    0.6.2
 */