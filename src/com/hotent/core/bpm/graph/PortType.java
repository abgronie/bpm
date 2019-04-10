/*    */ package com.hotent.core.bpm.graph;
/*    */ 
/*    */ public enum PortType
/*    */ {
/* 12 */   POSITION("position"), 
/* 13 */   NODE_PART_REFERENCE("nodePartReference"), 
/* 14 */   AUTOMATIC_SIDE("automaticSide");
/*    */ 
/*    */   private String text;
/*    */ 
/*    */   private PortType(String text) {
/* 19 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public String getText() {
/* 23 */     return this.text;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 28 */     return this.text;
/*    */   }
/*    */ 
/*    */   public static PortType fromString(String text) {
/* 32 */     if (text != null) {
/* 33 */       for (PortType type : values()) {
/* 34 */         if (text.equalsIgnoreCase(type.text)) {
/* 35 */           return type;
/*    */         }
/*    */       }
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.PortType
 * JD-Core Version:    0.6.2
 */