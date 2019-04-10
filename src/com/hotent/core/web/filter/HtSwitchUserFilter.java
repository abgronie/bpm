/*    */ package com.hotent.core.web.filter;
/*    */ 
/*    */ import com.hotent.core.api.org.model.ISysUser;
/*    */ import com.hotent.core.api.util.ContextUtil;
/*    */ import com.hotent.core.web.util.CookieUitl;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
/*    */ 
/*    */ public class HtSwitchUserFilter extends SwitchUserFilter
/*    */ {
/*    */   public static final String SwitchAccount = "origSwitch";
/*    */ 
/*    */   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 32 */     HttpServletRequest request = (HttpServletRequest)req;
/* 33 */     HttpServletResponse response = (HttpServletResponse)res;
/* 34 */     if (requiresSwitchUser(request)) {
/* 35 */       setAccount(ContextUtil.getCurrentUser().getAccount(), request, response);
/*    */     }
/* 38 */     else if (requiresExitUser(request)) {
/* 39 */       removeAccount(request, response);
/*    */     }
/* 41 */     super.doFilter(req, res, chain);
/*    */   }
/*    */ 
/*    */   private void setAccount(String account, HttpServletRequest req, HttpServletResponse res) {
/* 45 */     CookieUitl.addCookie("origSwitch", account, req, res);
/*    */   }
/*    */ 
/*    */   private void removeAccount(HttpServletRequest req, HttpServletResponse res) {
/* 49 */     CookieUitl.delCookie("origSwitch", req, res);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.filter.HtSwitchUserFilter
 * JD-Core Version:    0.6.2
 */