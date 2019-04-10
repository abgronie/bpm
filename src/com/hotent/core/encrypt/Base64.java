/*    */ package com.hotent.core.encrypt;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ 
/*    */ public class Base64
/*    */ {
/*    */   public static String getBase64(String s)
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 18 */     byte[] bytes = org.apache.commons.codec.binary.Base64.encodeBase64(s.getBytes("utf-8"));
/* 19 */     return new String(bytes, "utf-8");
/*    */   }
/*    */ 
/*    */   public static String getFromBase64(String s)
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 30 */     byte[] bytes = s.getBytes("utf-8");
/* 31 */     byte[] convertBytes = org.apache.commons.codec.binary.Base64.decodeBase64(bytes);
/* 32 */     return new String(convertBytes, "utf-8");
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.encrypt.Base64
 * JD-Core Version:    0.6.2
 */