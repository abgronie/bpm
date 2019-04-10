/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import org.apache.commons.lang.ArrayUtils;
/*    */ 
/*    */ public class ArrayUtil
/*    */ {
/*    */   public static Long[] convertArray(String[] aryStr)
/*    */   {
/* 21 */     if (ArrayUtils.isEmpty(aryStr))
/* 22 */       return ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
/* 23 */     Long[] aryLong = new Long[aryStr.length];
/* 24 */     for (int i = 0; i < aryStr.length; i++) {
/* 25 */       aryLong[i] = Long.valueOf(Long.parseLong(aryStr[i]));
/*    */     }
/* 27 */     return aryLong;
/*    */   }
/*    */ 
/*    */   public static String[] convertArray(Long[] aryLong)
/*    */   {
/* 37 */     if (ArrayUtils.isEmpty(aryLong))
/* 38 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/* 39 */     String[] aryStr = new String[aryLong.length];
/* 40 */     for (int i = 0; i < aryStr.length; i++) {
/* 41 */       aryStr[i] = String.valueOf(aryStr[i]);
/*    */     }
/* 43 */     return aryStr;
/*    */   }
/*    */ 
/*    */   public static String contact(String[] aryStr, String separator)
/*    */   {
/* 53 */     if (aryStr.length == 1) return aryStr[0];
/* 54 */     String out = "";
/* 55 */     for (int i = 0; i < aryStr.length; i++) {
/* 56 */       if (i == 0) {
/* 57 */         out = out + aryStr[i];
/*    */       }
/*    */       else {
/* 60 */         out = out + separator + aryStr[i];
/*    */       }
/*    */     }
/* 63 */     return out;
/*    */   }
/*    */ 
/*    */   public static String addQuote(String val)
/*    */   {
/* 73 */     String[] aryVal = val.split(",");
/*    */ 
/* 75 */     if (aryVal.length == 1) return "'" + val + "'";
/*    */ 
/* 77 */     String tmp = "";
/* 78 */     for (int i = 0; i < aryVal.length; i++) {
/* 79 */       if (i == 0) {
/* 80 */         tmp = tmp + "'" + aryVal[i] + "'";
/*    */       }
/*    */       else {
/* 83 */         tmp = tmp + ",'" + aryVal[i] + "'";
/*    */       }
/*    */     }
/* 86 */     return tmp;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ArrayUtil
 * JD-Core Version:    0.6.2
 */