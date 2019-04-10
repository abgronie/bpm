/*    */ package com.hotent.core.web.servlet;
/*    */ 
/*    */ import com.hotent.core.util.StringUtil;
/*    */ import javax.servlet.RequestDispatcher;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.springframework.web.servlet.DispatcherServlet;
/*    */ 
/*    */ public class SpringMvcServlet extends DispatcherServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response)
/*    */     throws Exception
/*    */   {
/* 53 */     String requestURI = request.getRequestURI();
/* 54 */     this.logger.debug("not foud handle mapping for url: " + requestURI);
/*    */ 
/* 56 */     String contextPath = request.getContextPath();
/*    */ 
/* 58 */     requestURI = requestURI.replace(".ht", "");
/* 59 */     int cxtIndex = requestURI.indexOf(contextPath);
/* 60 */     if (cxtIndex != -1)
/*    */     {
/* 62 */       requestURI = requestURI.substring(cxtIndex + contextPath.length());
/*    */     }
/* 64 */     String[] paths = requestURI.split("[/]");
/* 65 */     String jspPath = null;
/* 66 */     if ((paths != null) && (paths.length == 5))
/* 67 */       jspPath = "/" + paths[1] + "/" + paths[2] + "/" + paths[3] + StringUtil.makeFirstLetterUpperCase(paths[4]) + ".jsp";
/*    */     else {
/* 69 */       jspPath = requestURI + ".jsp";
/*    */     }
/* 71 */     this.logger.debug("requestURI:" + request.getRequestURI() + " and forward to /WEB-INF/view" + jspPath);
/* 72 */     request.getRequestDispatcher("/WEB-INF/view" + jspPath).forward(request, response);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.servlet.SpringMvcServlet
 * JD-Core Version:    0.6.2
 */