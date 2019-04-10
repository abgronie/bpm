/*    */ package com.hotent.core.web.servlet;
/*    */ 
/*    */ import com.hotent.core.util.StringUtil;
/*    */ import com.hotent.core.valid.ValidationUtil;
/*    */ import com.hotent.core.web.util.RequestUtil;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class ValidJs extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 50 */     response.setContentType("text/javascript;charset=utf-8");
/* 51 */     String form = RequestUtil.getString(request, "form");
/* 52 */     Locale local = RequestUtil.getLocal(request);
/* 53 */     String str = "";
/* 54 */     if (StringUtil.isNotEmpty(form)) {
/*    */       try
/*    */       {
/* 57 */         str = ValidationUtil.getJs(form, local);
/*    */       } catch (TemplateException e) {
/* 59 */         str = "";
/*    */       }
/*    */     }
/* 62 */     response.getWriter().print(str);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.servlet.ValidJs
 * JD-Core Version:    0.6.2
 */