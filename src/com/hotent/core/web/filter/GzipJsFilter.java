/*    */ package com.hotent.core.web.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class GzipJsFilter
/*    */   implements Filter
/*    */ {
/* 25 */   Map headers = new HashMap();
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
/* 32 */     if ((req instanceof HttpServletRequest))
/* 33 */       doFilter((HttpServletRequest)req, (HttpServletResponse)res, chain);
/*    */     else
/* 35 */       chain.doFilter(req, res);
/*    */   }
/*    */ 
/*    */   public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 42 */     request.setCharacterEncoding("UTF-8");
/* 43 */     for (Iterator it = this.headers.entrySet().iterator(); it.hasNext(); ) {
/* 44 */       Map.Entry entry = (Map.Entry)it.next();
/* 45 */       response.addHeader((String)entry.getKey(), (String)entry.getValue());
/*    */     }
/* 47 */     chain.doFilter(request, response);
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig config) throws ServletException {
/* 51 */     String headersStr = config.getInitParameter("headers");
/* 52 */     String[] headers = headersStr.split(",");
/* 53 */     for (int i = 0; i < headers.length; i++) {
/* 54 */       String[] temp = headers[i].split("=");
/* 55 */       this.headers.put(temp[0].trim(), temp[1].trim());
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.filter.GzipJsFilter
 * JD-Core Version:    0.6.2
 */