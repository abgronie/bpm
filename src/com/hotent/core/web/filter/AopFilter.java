/*    */ package com.hotent.core.web.filter;
/*    */ 
/*    */ import com.hotent.core.api.util.ContextUtil;
/*    */ import com.hotent.core.util.AppUtil;
/*    */ import com.hotent.core.web.util.RequestContext;
/*    */ import java.io.IOException;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*    */ import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/*    */ 
/*    */ public class AopFilter
/*    */   implements Filter
/*    */ {
	
			static final Logger logger = LoggerFactory.getLogger(AopFilter.class);
	
/*    */   public void init(FilterConfig filterConfig)
/*    */     throws ServletException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/*    */     try
/*    */     {
/* 40 */       ContextUtil.clearAll();
/* 41 */       RequestContext.setHttpServletRequest((HttpServletRequest)request);
/* 42 */       RequestContext.setHttpServletResponse((HttpServletResponse)response);
/*    */ 
/* 44 */       SessionLocaleResolver sessionResolver = (SessionLocaleResolver)AppUtil.getBean(SessionLocaleResolver.class);
/* 45 */       Locale local = sessionResolver.resolveLocale((HttpServletRequest)request);
/* 46 */       ContextUtil.setLocale(local);
/*    */ 
/* 48 */       chain.doFilter(request, response);
/*    */     }catch(Exception ex){
					logger.error(ex.getMessage(), ex);
					throw new ServletException(ex);
}
/*    */     finally {
/* 51 */       ContextUtil.clearAll();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.filter.AopFilter
 * JD-Core Version:    0.6.2
 */