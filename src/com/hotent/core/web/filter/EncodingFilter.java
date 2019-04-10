/*    */ package com.hotent.core.web.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class EncodingFilter
/*    */   implements Filter
/*    */ {
/* 22 */   private String encoding = "UTF-8";
/* 23 */   private String contentType = "text/html;charset=UTF-8";
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest request, ServletResponse httpresponse, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 35 */     HttpServletResponse response = (HttpServletResponse)httpresponse;
/* 36 */     request.setCharacterEncoding(this.encoding);
/* 37 */     response.setCharacterEncoding(this.encoding);
/* 38 */     response.setContentType(this.contentType);
/* 39 */     response.setHeader("Cache-Control", "no-cache");
/* 40 */     response.setHeader("Pragma", "no-cache");
/* 41 */     response.setDateHeader("Expires", -1L);
/* 42 */     response.setHeader("Access-Control-Allow-Headers", "*");
/* 43 */     response.setHeader("Access-Control-Allow-Origin", "*");
/* 44 */     response.setHeader("Access-Control-Allow-Credentials", "true");
/*    */ 
/* 46 */     response.setHeader("Access-Control-Allow-Methods", "POST,GET");
/* 47 */     chain.doFilter(request, response);
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig config)
/*    */     throws ServletException
/*    */   {
/* 53 */     String _encoding = config.getInitParameter("encoding");
/* 54 */     String _contentType = config.getInitParameter("contentType");
/*    */ 
/* 56 */     if (_encoding != null)
/* 57 */       this.encoding = _encoding;
/* 58 */     if (_contentType != null)
/* 59 */       this.contentType = _contentType;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.filter.EncodingFilter
 * JD-Core Version:    0.6.2
 */