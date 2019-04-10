/*     */ package com.hotent.core.web.filter;
/*     */ 
/*     */ import com.hotent.core.api.org.model.ISysUser;
/*     */ import com.hotent.core.consts.SystemConst;
/*     */ import com.hotent.core.web.ResultMessage;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Collection;
/*     */ import javax.servlet.Filter;
/*     */ import javax.servlet.FilterChain;
/*     */ import javax.servlet.FilterConfig;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.security.access.SecurityMetadataSource;
/*     */ import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
/*     */ import org.springframework.security.access.intercept.InterceptorStatusToken;
/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.security.core.context.SecurityContext;
/*     */ import org.springframework.security.core.context.SecurityContextHolder;
/*     */ import org.springframework.security.web.FilterInvocation;
/*     */ import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/*     */ 
/*     */ public class PermissionFilter extends AbstractSecurityInterceptor
/*     */   implements Filter
/*     */ {
/*     */   private FilterInvocationSecurityMetadataSource securityMetadataSource;
/*     */ 
/*     */   public void destroy()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*     */     throws IOException, ServletException
/*     */   {
/*  49 */     FilterInvocation fi = new FilterInvocation(request, response, chain);
/*  50 */     boolean canInvokeNext = canInvokeNextFilter(request, response, fi);
/*  51 */     if (canInvokeNext)
/*  52 */       invoke(fi);
/*     */   }
/*     */ 
/*     */   private boolean canInvokeNextFilter(ServletRequest request, ServletResponse response, FilterInvocation fi)
/*     */     throws IOException
/*     */   {
/*  67 */     HttpServletRequest httpRequest = (HttpServletRequest)request;
/*  68 */     Collection configAttributes = obtainSecurityMetadataSource().getAttributes(fi);
/*     */ 
/*  71 */     if ((!configAttributes.contains(SystemConst.ROLE_CONFIG_ANONYMOUS)) && (("XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))) || ("true".equalsIgnoreCase(httpRequest.getParameter("isAjaxRequest")))))
/*     */     {
/*  74 */       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
/*  75 */       if ((authentication == null) || (!(authentication.getPrincipal() instanceof ISysUser))) {
/*  76 */         ResultMessage resultMessage = new ResultMessage(0, "登录超时，请重新登录！");
/*  77 */         resultMessage.setCause("nologin");
/*  78 */         response.getWriter().print(resultMessage);
/*  79 */         return false;
/*     */       }
/*     */     }
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
/*  86 */     return this.securityMetadataSource;
/*     */   }
/*     */ 
/*     */   public Class<? extends Object> getSecureObjectClass() {
/*  90 */     return FilterInvocation.class;
/*     */   }
/*     */ 
/*     */   public void invoke(FilterInvocation fi) throws IOException, ServletException {
/*  94 */     super.setRejectPublicInvocations(false);
/*  95 */     InterceptorStatusToken token = super.beforeInvocation(fi);
/*     */     try {
/*  97 */       fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
/*     */     } finally {
/*  99 */       super.afterInvocation(token, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public SecurityMetadataSource obtainSecurityMetadataSource() {
/* 104 */     return this.securityMetadataSource;
/*     */   }
/*     */ 
/*     */   public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource)
/*     */   {
/* 109 */     this.securityMetadataSource = newSource;
/*     */   }
/*     */ 
/*     */   public void init(FilterConfig filterConfig)
/*     */     throws ServletException
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.filter.PermissionFilter
 * JD-Core Version:    0.6.2
 */