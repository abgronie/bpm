/*    */ package com.hotent.core.excel.style.font;
/*    */ 
/*    */ public enum TypeOffset
/*    */ {
/* 13 */   NONE((short)0), 
/*    */ 
/* 17 */   SUPER((short)1), 
/*    */ 
/* 21 */   SUB((short)2);
/*    */ 
/*    */   private short offset;
/*    */ 
/*    */   private TypeOffset(short offset) {
/* 26 */     this.offset = offset;
/*    */   }
/*    */ 
/*    */   public short getOffset() {
/* 30 */     return this.offset;
/*    */   }
/*    */ 
/*    */   public static TypeOffset instance(short offset)
/*    */   {
/* 39 */     for (TypeOffset e : values()) {
/* 40 */       if (e.getOffset() == offset) {
/* 41 */         return e;
/*    */       }
/*    */     }
/* 44 */     return NONE;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.font.TypeOffset
 * JD-Core Version:    0.6.2
 */