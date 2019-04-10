/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="tProcessType")
/*    */ @XmlEnum
/*    */ public enum ProcessType
/*    */ {
/* 36 */   NONE("None"), 
/*    */ 
/* 38 */   PUBLIC("Public"), 
/*    */ 
/* 40 */   PRIVATE("Private");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private ProcessType(String v) {
/* 45 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 49 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static ProcessType fromValue(String v) {
/* 53 */     for (ProcessType c : values()) {
/* 54 */       if (c.value.equals(v)) {
/* 55 */         return c;
/*    */       }
/*    */     }
/* 58 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ProcessType
 * JD-Core Version:    0.6.2
 */