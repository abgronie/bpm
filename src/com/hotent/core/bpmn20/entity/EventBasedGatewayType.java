/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="tEventBasedGatewayType")
/*    */ @XmlEnum
/*    */ public enum EventBasedGatewayType
/*    */ {
/* 35 */   EXCLUSIVE("Exclusive"), 
/*    */ 
/* 37 */   PARALLEL("Parallel");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private EventBasedGatewayType(String v) {
/* 42 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 46 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static EventBasedGatewayType fromValue(String v) {
/* 50 */     for (EventBasedGatewayType c : values()) {
/* 51 */       if (c.value.equals(v)) {
/* 52 */         return c;
/*    */       }
/*    */     }
/* 55 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.EventBasedGatewayType
 * JD-Core Version:    0.6.2
 */