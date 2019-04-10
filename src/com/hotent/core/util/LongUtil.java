/*    */ package com.hotent.core.util;
/*    */ 
/*    */ public class LongUtil
/*    */ {
/*    */   public static Long[] transStrsToLongs(String[] strs)
/*    */   {
/*  8 */     Long[] longs = new Long[strs.length];
/*  9 */     for (int i = 0; i < strs.length; i++) {
/* 10 */       longs[i] = Long.valueOf(Long.parseLong(strs[i]));
/*    */     }
/* 12 */     return longs;
/*    */   }
/*    */ 
/*    */   public static Long[] transStrsToLongs(String str)
/*    */   {
/* 17 */     if (StringUtil.isNotEmpty(str)) {
/* 18 */       return transStrsToLongs(str.split(","));
/*    */     }
/* 20 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.LongUtil
 * JD-Core Version:    0.6.2
 */