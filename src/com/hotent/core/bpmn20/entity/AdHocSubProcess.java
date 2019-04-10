/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tAdHocSubProcess", propOrder={"completionCondition"})
/*     */ public class AdHocSubProcess extends SubProcess
/*     */ {
/*     */   protected Expression completionCondition;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean cancelRemainingInstances;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected AdHocOrdering ordering;
/*     */ 
/*     */   public Expression getCompletionCondition()
/*     */   {
/*  62 */     return this.completionCondition;
/*     */   }
/*     */ 
/*     */   public void setCompletionCondition(Expression value)
/*     */   {
/*  74 */     this.completionCondition = value;
/*     */   }
/*     */ 
/*     */   public boolean isCancelRemainingInstances()
/*     */   {
/*  86 */     if (this.cancelRemainingInstances == null) {
/*  87 */       return true;
/*     */     }
/*  89 */     return this.cancelRemainingInstances.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setCancelRemainingInstances(Boolean value)
/*     */   {
/* 102 */     this.cancelRemainingInstances = value;
/*     */   }
/*     */ 
/*     */   public AdHocOrdering getOrdering()
/*     */   {
/* 114 */     return this.ordering;
/*     */   }
/*     */ 
/*     */   public void setOrdering(AdHocOrdering value)
/*     */   {
/* 126 */     this.ordering = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.AdHocSubProcess
 * JD-Core Version:    0.6.2
 */