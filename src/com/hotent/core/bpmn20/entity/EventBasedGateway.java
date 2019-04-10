/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tEventBasedGateway")
/*     */ public class EventBasedGateway extends Gateway
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean instantiate;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected EventBasedGatewayType eventGatewayType;
/*     */ 
/*     */   public boolean isInstantiate()
/*     */   {
/*  56 */     if (this.instantiate == null) {
/*  57 */       return false;
/*     */     }
/*  59 */     return this.instantiate.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setInstantiate(Boolean value)
/*     */   {
/*  72 */     this.instantiate = value;
/*     */   }
/*     */ 
/*     */   public EventBasedGatewayType getEventGatewayType()
/*     */   {
/*  84 */     if (this.eventGatewayType == null) {
/*  85 */       return EventBasedGatewayType.EXCLUSIVE;
/*     */     }
/*  87 */     return this.eventGatewayType;
/*     */   }
/*     */ 
/*     */   public void setEventGatewayType(EventBasedGatewayType value)
/*     */   {
/* 100 */     this.eventGatewayType = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.EventBasedGateway
 * JD-Core Version:    0.6.2
 */