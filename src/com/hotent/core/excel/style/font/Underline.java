/*    */ package com.hotent.core.excel.style.font;
/*    */ 
/*    */ public enum Underline
/*    */ {
/* 13 */   NONE((byte)0), 
/*    */ 
/* 18 */   SINGLE((byte)1), 
/*    */ 
/* 23 */   DOUBLE((byte)2), 
/*    */ 
/* 28 */   SINGLE_ACCOUNTING((byte)33), 
/*    */ 
/* 33 */   DOUBLE_ACCOUNTING((byte)34);
/*    */ 
/*    */   private byte line;
/*    */ 
/*    */   private Underline(byte line) {
/* 38 */     this.line = line;
/*    */   }
/*    */ 
/*    */   public byte getLine() {
/* 42 */     return this.line;
/*    */   }
/*    */ 
/*    */   public static Underline instance(byte line)
/*    */   {
/* 51 */     for (Underline e : values()) {
/* 52 */       if (e.getLine() == line) {
/* 53 */         return e;
/*    */       }
/*    */     }
/* 56 */     return NONE;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.font.Underline
 * JD-Core Version:    0.6.2
 */