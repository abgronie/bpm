/*    */ package com.hotent.core.excel.style.font;
/*    */ 
/*    */ public enum CharSet
/*    */ {
/* 10 */   ANSI((byte)0), 
/*    */ 
/* 12 */   DEFAULT((byte)1), 
/*    */ 
/* 14 */   SYMBOL((byte)2);
/*    */ 
/*    */   private byte charset;
/*    */ 
/*    */   private CharSet(byte charset) {
/* 19 */     this.charset = charset;
/*    */   }
/*    */ 
/*    */   public byte getCharset() {
/* 23 */     return this.charset;
/*    */   }
/*    */ 
/*    */   public static CharSet instance(byte charset)
/*    */   {
/* 32 */     return instance(charset);
/*    */   }
/*    */ 
/*    */   public static CharSet instance(int charset) {
/* 36 */     for (CharSet e : values()) {
/* 37 */       if (e.getCharset() == charset) {
/* 38 */         return e;
/*    */       }
/*    */     }
/* 41 */     return DEFAULT;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.font.CharSet
 * JD-Core Version:    0.6.2
 */