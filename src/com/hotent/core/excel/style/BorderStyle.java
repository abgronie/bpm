/*    */ package com.hotent.core.excel.style;
/*    */ 
/*    */ public enum BorderStyle
/*    */ {
/* 14 */   NONE((short)0), 
/*    */ 
/* 19 */   THIN((short)1), 
/*    */ 
/* 24 */   MEDIUM((short)2), 
/*    */ 
/* 29 */   THICK((short)5), 
/*    */ 
/* 34 */   DASHED((short)3), 
/*    */ 
/* 39 */   HAIR((short)4), 
/*    */ 
/* 44 */   DOUBLE((short)6), 
/*    */ 
/* 49 */   DOTTED((short)7), 
/*    */ 
/* 54 */   MEDIUM_DASHED((short)8), 
/*    */ 
/* 59 */   DASH_DOT((short)9), 
/*    */ 
/* 64 */   MEDIUM_DASH_DOT((short)10), 
/*    */ 
/* 69 */   DASH_DOT_DOT((short)11), 
/*    */ 
/* 74 */   MEDIUM_DASH_DOT_DOT((short)12), 
/*    */ 
/* 79 */   SLANTED_DASH_DOT((short)13);
/*    */ 
/*    */   private short borderType;
/*    */ 
/*    */   private BorderStyle(short borderType) {
/* 84 */     this.borderType = borderType;
/*    */   }
/*    */ 
/*    */   public short getBorderType() {
/* 88 */     return this.borderType;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.BorderStyle
 * JD-Core Version:    0.6.2
 */