/*    */ package com.hotent.core.web;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import org.springframework.web.servlet.View;
/*    */ import org.springframework.web.servlet.ViewResolver;
/*    */ 
/*    */ public class MixedViewResolver
/*    */   implements ViewResolver
/*    */ {
/*    */   private Map<String, ViewResolver> resolvers;
/*    */ 
/*    */   public void setResolvers(Map<String, ViewResolver> resolvers)
/*    */   {
/* 19 */     this.resolvers = resolvers;
/*    */   }
/*    */ 
/*    */   public View resolveViewName(String viewName, Locale locale) throws Exception
/*    */   {
/* 24 */     int n = viewName.lastIndexOf('.');
/* 25 */     if (n == -1)
/* 26 */       throw new NoSuchViewResolverException();
/* 27 */     String suffix = viewName.substring(n + 1);
/* 28 */     ViewResolver resolver = (ViewResolver)this.resolvers.get(suffix);
/*    */ 
/* 30 */     if (resolver != null)
/* 31 */       return resolver.resolveViewName(viewName, locale);
/* 32 */     throw new NoSuchViewResolverException("No ViewResolver for " + suffix);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.MixedViewResolver
 * JD-Core Version:    0.6.2
 */