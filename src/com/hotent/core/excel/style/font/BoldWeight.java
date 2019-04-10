/*    */ package com.hotent.core.excel.style.font;
/*    */ 
/*    */ public enum BoldWeight
/*    */ {
/* 13 */   NORMAL((short)400), 
/*    */ 
/* 18 */   BOLD((short)700);
/*    */ 
/*    */   private short weight;
/*    */ 
/*    */   private BoldWeight(short weight) {
/* 23 */     this.weight = weight;
/*    */   }
/*    */ 
/*    */   public short getWeight()
/*    */   {
/* 31 */     return this.weight;
/*    */   }
/*    */ 
/*    */   public static BoldWeight instance(short weight)
/*    */   {
/* 40 */     for (BoldWeight e : values()) {
/* 41 */       if (e.getWeight() == weight) {
/* 42 */         return e;
/*    */       }
/*    */     }
/* 45 */     return NORMAL;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.font.BoldWeight
 * JD-Core Version:    0.6.2
 */