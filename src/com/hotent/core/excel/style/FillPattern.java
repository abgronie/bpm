/*    */ package com.hotent.core.excel.style;
/*    */ 
/*    */ public enum FillPattern
/*    */ {
/* 11 */   NO_FILL((short)0), 
/*    */ 
/* 13 */   SOLID_FOREGROUND((short)1), 
/*    */ 
/* 15 */   FINE_DOTS((short)2), 
/*    */ 
/* 17 */   ALT_BARS((short)3), 
/*    */ 
/* 19 */   SPARSE_DOTS((short)4), 
/*    */ 
/* 21 */   THICK_HORZ_BANDS((short)5), 
/*    */ 
/* 23 */   THICK_VERT_BANDS((short)6), 
/*    */ 
/* 25 */   THICK_BACKWARD_DIAG((short)7), 
/*    */ 
/* 27 */   THICK_FORWARD_DIAG((short)8), 
/*    */ 
/* 29 */   BIG_SPOTS((short)9), 
/*    */ 
/* 31 */   BRICKS((short)10), 
/*    */ 
/* 33 */   THIN_HORZ_BANDS((short)11), 
/*    */ 
/* 35 */   THIN_VERT_BANDS((short)12), 
/*    */ 
/* 37 */   THIN_BACKWARD_DIAG((short)13), 
/*    */ 
/* 39 */   THIN_FORWARD_DIAG((short)14), 
/*    */ 
/* 41 */   SQUARES((short)15), 
/*    */ 
/* 43 */   DIAMONDS((short)16), 
/*    */ 
/* 45 */   LESS_DOTS((short)17), 
/*    */ 
/* 47 */   LEAST_DOTS((short)18);
/*    */ 
/*    */   private short fillPattern;
/*    */ 
/*    */   private FillPattern(short fillPattern) {
/* 52 */     this.fillPattern = fillPattern;
/*    */   }
/*    */ 
/*    */   public short getFillPattern() {
/* 56 */     return this.fillPattern;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.FillPattern
 * JD-Core Version:    0.6.2
 */