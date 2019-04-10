/*    */ package com.hotent.core.bpm.graph;
/*    */ 
/*    */ public enum ShapeType
/*    */ {
/* 12 */   STRAIGHT("straight"), 
/* 13 */   FREE("free"), 
/* 14 */   ORTHOGONAL("orthogonal"), 
/* 15 */   OBLIQUE("oblique");
/*    */ 
/*    */   private String text;
/*    */ 
/*    */   private ShapeType(String text) {
/* 20 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public String getText() {
/* 24 */     return this.text;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 29 */     return this.text;
/*    */   }
/*    */ 
/*    */   public static ShapeType fromString(String text) {
/* 33 */     if (text != null) {
/* 34 */       for (ShapeType type : values()) {
/* 35 */         if (text.equalsIgnoreCase(type.text)) {
/* 36 */           return type;
/*    */         }
/*    */       }
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.ShapeType
 * JD-Core Version:    0.6.2
 */