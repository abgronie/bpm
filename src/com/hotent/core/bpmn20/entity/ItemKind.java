/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlEnum;
/*    */ import javax.xml.bind.annotation.XmlEnumValue;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType(name="tItemKind")
/*    */ @XmlEnum
/*    */ public enum ItemKind
/*    */ {
/* 35 */   INFORMATION("Information"), 
/*    */ 
/* 37 */   PHYSICAL("Physical");
/*    */ 
/*    */   private final String value;
/*    */ 
/*    */   private ItemKind(String v) {
/* 42 */     this.value = v;
/*    */   }
/*    */ 
/*    */   public String value() {
/* 46 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static ItemKind fromValue(String v) {
/* 50 */     for (ItemKind c : values()) {
/* 51 */       if (c.value.equals(v)) {
/* 52 */         return c;
/*    */       }
/*    */     }
/* 55 */     throw new IllegalArgumentException(v);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ItemKind
 * JD-Core Version:    0.6.2
 */