/*    */ package com.hotent.core.excel.style;
/*    */ 
/*    */ public enum Align
/*    */ {
/* 14 */   GENERAL((short)0), 
/*    */ 
/* 19 */   LEFT((short)1), 
/*    */ 
/* 24 */   CENTER((short)2), 
/*    */ 
/* 29 */   RIGHT((short)3), 
/*    */ 
/* 34 */   FILL((short)4), 
/*    */ 
/* 39 */   JUSTIFY((short)5), 
/*    */ 
/* 44 */   CENTER_SELECTION((short)6);
/*    */ 
/*    */   private short alignment;
/*    */ 
/*    */   private Align(short alignment) {
/* 49 */     this.alignment = alignment;
/*    */   }
/*    */ 
/*    */   public short getAlignment() {
/* 53 */     return this.alignment;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.Align
 * JD-Core Version:    0.6.2
 */