/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ 
/*    */ public class ExceptionUtil
/*    */ {
/*    */   public static String getExceptionMessage(Exception e)
/*    */   {
/* 20 */     if (e == null) return "";
/* 21 */     StringWriter sw = new StringWriter();
/* 22 */     e.printStackTrace(new PrintWriter(sw, true));
/* 23 */     String str = sw.toString();
/*    */ 
/* 25 */     return str;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ExceptionUtil
 * JD-Core Version:    0.6.2
 */