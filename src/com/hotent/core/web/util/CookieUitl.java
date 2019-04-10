/*     */ package com.hotent.core.web.util;
/*     */ 
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ 
/*     */ public class CookieUitl
/*     */ {
/*     */   public static void addCookie(String name, String value, int maxAge, PageContext context)
/*     */   {
/*  30 */     HttpServletResponse response = (HttpServletResponse)context.getResponse();
/*  31 */     HttpServletRequest req = (HttpServletRequest)context.getRequest();
/*  32 */     addCookie(name, value, maxAge, req, response);
/*     */   }
/*     */ 
/*     */   public static void addCookie(String name, String value, PageContext context)
/*     */   {
/*  43 */     HttpServletResponse response = (HttpServletResponse)context.getResponse();
/*  44 */     HttpServletRequest req = (HttpServletRequest)context.getRequest();
/*  45 */     addCookie(name, value, -1, req, response);
/*     */   }
/*     */ 
/*     */   public static void addCookie(String name, String value, HttpServletRequest req, HttpServletResponse response)
/*     */   {
/*  57 */     addCookie(name, value, -1, req, response);
/*     */   }
/*     */ 
/*     */   public static void addCookie(String name, String value, int maxAge, HttpServletRequest req, HttpServletResponse response)
/*     */   {
/*  70 */     if (response == null) return;
/*  71 */     Cookie cookie = new Cookie(name, value);
/*  72 */     cookie.setPath(req.getContextPath());
/*  73 */     cookie.setMaxAge(maxAge);
/*  74 */     response.addCookie(cookie);
/*     */   }
/*     */ 
/*     */   public static void delCookie(String name, PageContext context)
/*     */   {
/*  84 */     HttpServletResponse response = (HttpServletResponse)context.getResponse();
/*  85 */     HttpServletRequest request = (HttpServletRequest)context.getRequest();
/*  86 */     delCookie(name, request, response);
/*     */   }
/*     */ 
/*     */   public static void delCookie(String name, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  95 */     addCookie(name, "", 0, request, response);
/*     */   }
/*     */ 
/*     */   public static String getValueByName(String name, PageContext context)
/*     */   {
/* 108 */     HttpServletRequest request = (HttpServletRequest)context.getRequest();
/* 109 */     String str = getValueByName(name, request);
/* 110 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getValueByName(String name, HttpServletRequest request)
/*     */   {
/* 121 */     if (request == null) return "";
/* 122 */     Cookie[] cookies = request.getCookies();
/* 123 */     Cookie sCookie = null;
/* 124 */     String svalue = null;
/* 125 */     String sname = null;
/*     */ 
/* 127 */     if (cookies == null)
/* 128 */       return null;
/* 129 */     for (int i = 0; i < cookies.length; i++)
/*     */     {
/* 131 */       sCookie = cookies[i];
/* 132 */       sname = sCookie.getName();
/* 133 */       if (sname.equals(name))
/*     */       {
/* 135 */         svalue = sCookie.getValue();
/* 136 */         break;
/*     */       }
/*     */     }
/* 139 */     return svalue;
/*     */   }
/*     */ 
/*     */   public static boolean isExistByName(String name, PageContext context)
/*     */   {
/* 150 */     HttpServletRequest request = (HttpServletRequest)context.getRequest();
/* 151 */     return isExistByName(name, request);
/*     */   }
/*     */ 
/*     */   public static boolean isExistByName(String name, HttpServletRequest request)
/*     */   {
/* 163 */     Cookie[] cookies = request.getCookies();
/* 164 */     Cookie sCookie = null;
/*     */ 
/* 166 */     String sname = null;
/* 167 */     boolean isExist = false;
/* 168 */     if (cookies == null)
/* 169 */       return false;
/* 170 */     for (int i = 0; i < cookies.length; i++)
/*     */     {
/* 172 */       sCookie = cookies[i];
/* 173 */       sname = sCookie.getName();
/* 174 */       if (sname.equals(name))
/*     */       {
/* 176 */         isExist = true;
/* 177 */         break;
/*     */       }
/*     */     }
/* 180 */     return isExist;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.util.CookieUitl
 * JD-Core Version:    0.6.2
 */