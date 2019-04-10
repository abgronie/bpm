/*    */ package com.hotent.core.bpmn20.entity.bpmndi;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="ParticipantBandKind")
/*    */ @XmlEnum
/*    */ public enum ParticipantBandKind
/*    */ {
/* 39 */   TOP_INITIATING("top_initiating"), 
/*    */ 
/* 41 */   MIDDLE_INITIATING("middle_initiating"), 
/*    */ 
/* 43 */   BOTTOM_INITIATING("bottom_initiating"), 
/*    */ 
/* 45 */   TOP_NON_INITIATING("top_non_initiating"), 
/*    */ 
/* 47 */   MIDDLE_NON_INITIATING("middle_non_initiating"), 
/*    */ 
/* 49 */   BOTTOM_NON_INITIATING("bottom_non_initiating");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private ParticipantBandKind(String v) {
/* 54 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 58 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static ParticipantBandKind fromValue(String v) {
/* 62 */     for (ParticipantBandKind c : values()) {
/* 63 */       if (c.value.equals(v)) {
/* 64 */         return c;
/*    */       }
/*    */     }
/* 67 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.ParticipantBandKind
 * JD-Core Version:    0.6.2
 */