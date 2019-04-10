/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tTimerEventDefinition", propOrder={"timeDate", "timeDuration", "timeCycle"})
/*     */ public class TimerEventDefinition extends EventDefinition
/*     */ {
/*     */   protected Expression timeDate;
/*     */   protected Expression timeDuration;
/*     */   protected Expression timeCycle;
/*     */ 
/*     */   public Expression getTimeDate()
/*     */   {
/*  61 */     return this.timeDate;
/*     */   }
/*     */ 
/*     */   public void setTimeDate(Expression value)
/*     */   {
/*  73 */     this.timeDate = value;
/*     */   }
/*     */ 
/*     */   public Expression getTimeDuration()
/*     */   {
/*  85 */     return this.timeDuration;
/*     */   }
/*     */ 
/*     */   public void setTimeDuration(Expression value)
/*     */   {
/*  97 */     this.timeDuration = value;
/*     */   }
/*     */ 
/*     */   public Expression getTimeCycle()
/*     */   {
/* 109 */     return this.timeCycle;
/*     */   }
/*     */ 
/*     */   public void setTimeCycle(Expression value)
/*     */   {
/* 121 */     this.timeCycle = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.TimerEventDefinition
 * JD-Core Version:    0.6.2
 */