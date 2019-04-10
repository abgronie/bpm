/*    */ package com.hotent.core.excel.style;
/*    */ 
/*    */ public enum VAlign
/*    */ {
/* 14 */   TOP((short)0), 
/*    */ 
/* 19 */   CENTER((short)1), 
/*    */ 
/* 24 */   BOTTOM((short)2), 
/*    */ 
/* 29 */   JUSTIFY((short)3);
/*    */ 
/*    */   private short alignment;
/*    */ 
/*    */   private VAlign(short alignment) {
/* 34 */     this.alignment = alignment;
/*    */   }
/*    */ 
/*    */   public short getAlignment() {
/* 38 */     return this.alignment;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.VAlign
 * JD-Core Version:    0.6.2
 */