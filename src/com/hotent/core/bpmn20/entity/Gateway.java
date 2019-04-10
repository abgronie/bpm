/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlSeeAlso;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tGateway")
/*    */ @XmlSeeAlso({ComplexGateway.class, InclusiveGateway.class, EventBasedGateway.class, ParallelGateway.class, ExclusiveGateway.class})
/*    */ public class Gateway extends FlowNode
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected GatewayDirection gatewayDirection;
/*    */ 
/*    */   public GatewayDirection getGatewayDirection()
/*    */   {
/* 61 */     if (this.gatewayDirection == null) {
/* 62 */       return GatewayDirection.UNSPECIFIED;
/*    */     }
/* 64 */     return this.gatewayDirection;
/*    */   }
/*    */ 
/*    */   public void setGatewayDirection(GatewayDirection value)
/*    */   {
/* 77 */     this.gatewayDirection = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Gateway
 * JD-Core Version:    0.6.2
 */