/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ public class MyX509TrustManager
/*    */   implements X509TrustManager
/*    */ {
/*    */   public void checkClientTrusted(X509Certificate[] chain, String authType)
/*    */     throws CertificateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void checkServerTrusted(X509Certificate[] chain, String authType)
/*    */     throws CertificateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public X509Certificate[] getAcceptedIssuers()
/*    */   {
/* 45 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.MyX509TrustManager
 * JD-Core Version:    0.6.2
 */