/*    */ package com.hotent.core.web.util;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class RequestContext
/*    */ {
/* 19 */   private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal();
/*    */ 
/* 21 */   private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal();
/*    */ 
/*    */   public static void setHttpServletRequest(HttpServletRequest request) {
/* 24 */     requestLocal.set(request);
/*    */   }
/*    */ 
/*    */   public static void clearHttpReqResponse()
/*    */   {
/* 33 */     requestLocal.remove();
/* 34 */     responseLocal.remove();
/*    */   }
/*    */ 
/*    */   public static void setHttpServletResponse(HttpServletResponse response)
/*    */   {
/* 45 */     responseLocal.set(response);
/*    */   }
/*    */ 
/*    */   public static HttpServletRequest getHttpServletRequest()
/*    */   {
/* 54 */     return (HttpServletRequest)requestLocal.get();
/*    */   }
/*    */ 
/*    */   public static HttpServletResponse getHttpServletResponse()
/*    */   {
/* 63 */     return (HttpServletResponse)responseLocal.get();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.util.RequestContext
 * JD-Core Version:    0.6.2
 */