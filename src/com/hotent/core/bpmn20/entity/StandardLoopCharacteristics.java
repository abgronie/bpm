/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tStandardLoopCharacteristics", propOrder={"loopCondition"})
/*     */ public class StandardLoopCharacteristics extends LoopCharacteristics
/*     */ {
/*     */   protected Expression loopCondition;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean testBefore;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected BigInteger loopMaximum;
/*     */ 
/*     */   public Expression getLoopCondition()
/*     */   {
/*  63 */     return this.loopCondition;
/*     */   }
/*     */ 
/*     */   public void setLoopCondition(Expression value)
/*     */   {
/*  75 */     this.loopCondition = value;
/*     */   }
/*     */ 
/*     */   public boolean isTestBefore()
/*     */   {
/*  87 */     if (this.testBefore == null) {
/*  88 */       return false;
/*     */     }
/*  90 */     return this.testBefore.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setTestBefore(Boolean value)
/*     */   {
/* 103 */     this.testBefore = value;
/*     */   }
/*     */ 
/*     */   public BigInteger getLoopMaximum()
/*     */   {
/* 115 */     return this.loopMaximum;
/*     */   }
/*     */ 
/*     */   public void setLoopMaximum(BigInteger value)
/*     */   {
/* 127 */     this.loopMaximum = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.StandardLoopCharacteristics
 * JD-Core Version:    0.6.2
 */