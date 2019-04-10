/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletContext;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
/*     */ import org.springframework.context.ApplicationEvent;
/*     */ 
/*     */ public class AppUtil
/*     */   implements ApplicationContextAware
/*     */ {
/*     */   private static ApplicationContext applicationContext;
/*     */   private static ServletContext servletContext;
/*     */ 
/*     */   public static void init(ServletContext _servletContext)
/*     */   {
/*  48 */     servletContext = _servletContext;
/*     */   }
/*     */ 
/*     */   public static ServletContext getServletContext()
/*     */     throws Exception
/*     */   {
/*  57 */     return servletContext;
/*     */   }
/*     */ 
/*     */   public void setApplicationContext(ApplicationContext contex)
/*     */     throws BeansException
/*     */   {
/*  64 */     applicationContext = contex;
/*     */   }
/*     */ 
/*     */   public static ApplicationContext getContext()
/*     */   {
/*  72 */     return applicationContext;
/*     */   }
/*     */ 
/*     */   public static List<Class> getImplClass(Class clazz)
/*     */     throws ClassNotFoundException
/*     */   {
/*  83 */     List list = new ArrayList();
/*     */ 
/*  85 */     Map map = applicationContext.getBeansOfType(clazz);
/*  86 */     for (Iterator i$ = map.values().iterator(); i$.hasNext(); ) { Object obj = i$.next();
/*  87 */       String name = obj.getClass().getName();
/*  88 */       int pos = name.indexOf("$$");
/*  89 */       if (pos > 0) {
/*  90 */         name = name.substring(0, name.indexOf("$$"));
/*     */       }
/*  92 */       Class cls = Class.forName(name);
/*     */ 
/*  94 */       list.add(cls);
/*     */     }
/*  96 */     return list;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getImplInstance(Class clazz)
/*     */     throws ClassNotFoundException
/*     */   {
/* 108 */     Map map = applicationContext.getBeansOfType(clazz);
/*     */ 
/* 110 */     return map;
/*     */   }
/*     */ 
/*     */   public static <C> C getBean(Class<C> cls)
/*     */   {
/* 121 */     return applicationContext.getBean(cls);
/*     */   }
/*     */ 
/*     */   public static Object getBean(String beanId)
/*     */   {
/* 130 */     return applicationContext.getBean(beanId);
/*     */   }
/*     */ 
/*     */   public static String getAppAbsolutePath()
/*     */   {
/* 138 */     return servletContext.getRealPath("/");
/*     */   }
/*     */ 
/*     */   public static String getRealPath(String path)
/*     */   {
/* 147 */     return servletContext.getRealPath(path);
/*     */   }
/*     */ 
/*     */   public static String getClasspath()
/*     */   {
/* 159 */     String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
/* 160 */     String rootPath = "";
/*     */ 
/* 162 */     if ("\\".equals(File.separator)) {
/* 163 */       rootPath = classPath.substring(1);
/* 164 */       rootPath = rootPath.replace("/", "\\");
/*     */     }
/*     */ 
/* 167 */     if ("/".equals(File.separator)) {
/* 168 */       rootPath = classPath.substring(1);
/* 169 */       rootPath = rootPath.replace("\\", "/");
/*     */     }
/* 171 */     return rootPath;
/*     */   }
/*     */ 
/*     */   public static void publishEvent(ApplicationEvent applicationEvent)
/*     */   {
/* 179 */     applicationContext.publishEvent(applicationEvent);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.AppUtil
 * JD-Core Version:    0.6.2
 */