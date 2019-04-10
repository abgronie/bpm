/*     */ package com.hotent.core.api.util;
/*     */ 
/*     */ import com.hotent.core.api.org.ICurrentContext;
/*     */ import com.hotent.core.api.org.model.IPosition;
/*     */ import com.hotent.core.api.org.model.ISysOrg;
/*     */ import com.hotent.core.api.org.model.ISysUser;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ContextUtil
/*     */ {
/*     */   public static final String CurrentOrg = "CurrentOrg_";
/*     */   public static final String CurrentCompany = "CurrentCompany_";
/*     */   public static final String CurrentPos = "CurrentPos_";
/*     */   private static ICurrentContext context_;
/*     */ 
/*     */   public static String getPositionKey(Long userId)
/*     */   {
/*  23 */     String posKey = "CurrentPos_" + userId;
/*  24 */     return posKey;
/*     */   }
/*     */ 
/*     */   public static String getOrgKey(Long userId) {
/*  28 */     String orgKey = "CurrentOrg_" + userId;
/*  29 */     return orgKey;
/*     */   }
/*     */ 
/*     */   public static String getCompanyKey(Long userId) {
/*  33 */     String orgKey = "CurrentCompany_" + userId;
/*  34 */     return orgKey;
/*     */   }
/*     */ 
/*     */   public static synchronized ICurrentContext getContext()
/*     */   {
/*  40 */     if (context_ == null) {
/*  41 */       context_ = (ICurrentContext)AppUtil.getBean(ICurrentContext.class);
/*     */     }
/*  43 */     return context_;
/*     */   }
/*     */ 
/*     */   public static ISysUser getCurrentUser()
/*     */   {
/*  53 */     ICurrentContext context = getContext();
/*  54 */     return context.getCurrentUser();
/*     */   }
/*     */ 
/*     */   public static Locale getLocale()
/*     */   {
/*  62 */     ICurrentContext context = getContext();
/*  63 */     return context.getLocale();
/*     */   }
/*     */ 
/*     */   public static void setLocale(Locale locale)
/*     */   {
/*  71 */     ICurrentContext context = getContext();
/*  72 */     context.setLocale(locale);
/*     */   }
/*     */ 
/*     */   public static Long getCurrentUserId()
/*     */   {
/*  80 */     ICurrentContext context = getContext();
/*  81 */     return context.getCurrentUserId();
/*     */   }
/*     */ 
/*     */   public static void setCurrentUserAccount(String account)
/*     */   {
/*  89 */     ICurrentContext context = getContext();
/*  90 */     context.setCurrentUserAccount(account);
/*     */   }
/*     */ 
/*     */   public static void setCurrentUser(ISysUser sysUser)
/*     */   {
/*  98 */     ICurrentContext context = getContext();
/*  99 */     context.setCurrentUser(sysUser);
/*     */   }
/*     */ 
/*     */   public static void setCurrentPos(Long posId)
/*     */   {
/* 107 */     ICurrentContext context = getContext();
/* 108 */     context.setCurrentPos(posId);
/*     */   }
/*     */ 
/*     */   public static ISysOrg getCurrentOrg()
/*     */   {
/* 116 */     ICurrentContext context = getContext();
/* 117 */     return context.getCurrentOrg();
/*     */   }
/*     */ 
/*     */   public static ISysOrg getCurrentCompany()
/*     */   {
/* 125 */     ICurrentContext context = getContext();
/* 126 */     return context.getCurrentCompany();
/*     */   }
/*     */ 
/*     */   public static Long getCurrentCompanyId()
/*     */   {
/* 134 */     ICurrentContext context = getContext();
/* 135 */     return context.getCurrentCompanyId();
/*     */   }
/*     */ 
/*     */   public static IPosition getCurrentPos()
/*     */   {
/* 143 */     ICurrentContext context = getContext();
/* 144 */     return context.getCurrentPos();
/*     */   }
/*     */ 
/*     */   public static Long getCurrentPosId()
/*     */   {
/* 152 */     ICurrentContext context = getContext();
/* 153 */     return context.getCurrentPosId();
/*     */   }
/*     */ 
/*     */   public static Long getCurrentOrgId()
/*     */   {
/* 161 */     ICurrentContext context = getContext();
/* 162 */     return context.getCurrentOrgId();
/*     */   }
/*     */ 
/*     */   public static String getCurrentUserSkin(HttpServletRequest request)
/*     */   {
/* 171 */     ICurrentContext context = getContext();
/* 172 */     return context.getCurrentUserSkin(request);
/*     */   }
/*     */ 
/*     */   public static void cleanCurUser()
/*     */   {
/* 179 */     ICurrentContext context = getContext();
/* 180 */     context.cleanCurUser();
/*     */   }
/*     */ 
/*     */   public static void removeCurrentOrg()
/*     */   {
/* 187 */     ICurrentContext context = getContext();
/* 188 */     context.removeCurrentOrg();
/*     */   }
/*     */ 
/*     */   public static void clearAll()
/*     */   {
/* 195 */     ICurrentContext context = getContext();
/* 196 */     context.clearAll();
/*     */   }
/*     */ 
/*     */   public static void removeCurrentUser()
/*     */   {
/* 203 */     ICurrentContext context = getContext();
/* 204 */     context.removeCurrentUser();
/*     */   }
/*     */ 
/*     */   public static boolean isSuperAdmin()
/*     */   {
/* 212 */     ICurrentContext context = getContext();
/* 213 */     return context.isSuperAdmin();
/*     */   }
/*     */ 
/*     */   public static boolean isSuperAdmin(ISysUser user) {
/* 217 */     ICurrentContext context = getContext();
/* 218 */     return context.isSuperAdmin(user);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.util.ContextUtil
 * JD-Core Version:    0.6.2
 */