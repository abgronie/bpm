/*    */ package com.hotent.core.bpmn20.entity.bpmndi;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="MessageVisibleKind")
/*    */ @XmlEnum
/*    */ public enum MessageVisibleKind
/*    */ {
/* 35 */   INITIATING("initiating"), 
/*    */ 
/* 37 */   NON_INITIATING("non_initiating");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private MessageVisibleKind(String v) {
/* 42 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 46 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static MessageVisibleKind fromValue(String v) {
/* 50 */     for (MessageVisibleKind c : values()) {
/* 51 */       if (c.value.equals(v)) {
/* 52 */         return c;
/*    */       }
/*    */     }
/* 55 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.MessageVisibleKind
 * JD-Core Version:    0.6.2
 */