/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="tMultiInstanceFlowCondition")
/*    */ @XmlEnum
/*    */ public enum MultiInstanceFlowCondition
/*    */ {
/* 37 */   NONE("None"), 
/*    */ 
/* 39 */   ONE("One"), 
/*    */ 
/* 41 */   ALL("All"), 
/*    */ 
/* 43 */   COMPLEX("Complex");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private MultiInstanceFlowCondition(String v) {
/* 48 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 52 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static MultiInstanceFlowCondition fromValue(String v) {
/* 56 */     for (MultiInstanceFlowCondition c : values()) {
/* 57 */       if (c.value.equals(v)) {
/* 58 */         return c;
/*    */       }
/*    */     }
/* 61 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.MultiInstanceFlowCondition
 * JD-Core Version:    0.6.2
 */