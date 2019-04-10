/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="tAdHocOrdering")
/*    */ @XmlEnum
/*    */ public enum AdHocOrdering
/*    */ {
/* 35 */   PARALLEL("Parallel"), 
/*    */ 
/* 37 */   SEQUENTIAL("Sequential");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private AdHocOrdering(String v) {
/* 42 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 46 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static AdHocOrdering fromValue(String v) {
/* 50 */     for (AdHocOrdering c : values()) {
/* 51 */       if (c.value.equals(v)) {
/* 52 */         return c;
/*    */       }
/*    */     }
/* 55 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.AdHocOrdering
 * JD-Core Version:    0.6.2
 */