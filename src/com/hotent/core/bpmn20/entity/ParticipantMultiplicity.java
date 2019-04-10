/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tParticipantMultiplicity")
/*     */ public class ParticipantMultiplicity extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Integer minimum;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Integer maximum;
/*     */ 
/*     */   public int getMinimum()
/*     */   {
/*  56 */     if (this.minimum == null) {
/*  57 */       return 0;
/*     */     }
/*  59 */     return this.minimum.intValue();
/*     */   }
/*     */ 
/*     */   public void setMinimum(Integer value)
/*     */   {
/*  72 */     this.minimum = value;
/*     */   }
/*     */ 
/*     */   public int getMaximum()
/*     */   {
/*  84 */     if (this.maximum == null) {
/*  85 */       return 1;
/*     */     }
/*  87 */     return this.maximum.intValue();
/*     */   }
/*     */ 
/*     */   public void setMaximum(Integer value)
/*     */   {
/* 100 */     this.maximum = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ParticipantMultiplicity
 * JD-Core Version:    0.6.2
 */