/*     */ package com.hotent.core.web.controller;
/*     */ 
/*     */ import com.hotent.core.api.util.ContextUtil;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import com.hotent.core.web.ResultMessage;
/*     */ import com.hotent.core.web.query.QueryFilter;
/*     */ import com.hotent.core.web.util.RequestContext;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import net.sf.json.JSONArray;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.MessageSource;
/*     */ import org.springframework.context.support.MessageSourceAccessor;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ public class GenericController
/*     */ {
/*  36 */   protected Logger logger = LoggerFactory.getLogger(BaseController.class);
/*     */ 
/*  40 */   public final String SUCCESS = "{success:true}";
/*     */ 
/*  44 */   public final String FAILURE = "{success:false}";
/*     */   private MessageSourceAccessor messages;
/*     */   public static final String STEP1 = "1";
/*     */   public static final String STEP2 = "2";
/*     */   public static final String MESSAGES_KEY = "successMessages";
/*     */   public static final String ERRORS = "errors";
/*     */ 
/*     */   @Resource
/*     */   protected Properties configproperties;
/*     */ 
/*     */   public ModelAndView getAutoView()
/*     */     throws Exception
/*     */   {
/*  71 */     HttpServletRequest request = RequestContext.getHttpServletRequest();
/*  72 */     String requestURI = request.getRequestURI();
/*     */ 
/*  74 */     this.logger.debug("requestURI:" + requestURI);
/*  75 */     String contextPath = request.getContextPath();
/*     */ 
/*  77 */     requestURI = requestURI.replace(".ht", "");
/*  78 */     int cxtIndex = requestURI.indexOf(contextPath);
/*  79 */     if (cxtIndex != -1) {
/*  80 */       requestURI = requestURI.substring(cxtIndex + contextPath.length());
/*     */     }
/*     */ 
/*  83 */     String[] paths = requestURI.split("[/]");
/*  84 */     if ((paths != null) && (paths.length == 5)) {
/*  85 */       String jspPath = "/" + paths[1] + "/" + paths[2] + "/" + paths[3] + StringUtil.makeFirstLetterUpperCase(paths[4]) + ".jsp";
/*     */ 
/*  87 */       return new ModelAndView(jspPath);
/*  88 */     }if ((paths != null) && (paths.length == 4)) {
/*  89 */       String jspPath = "/" + paths[1] + "/" + paths[2] + StringUtil.makeFirstLetterUpperCase(paths[3]) + ".jsp";
/*     */ 
/*  91 */       return new ModelAndView(jspPath);
/*     */     }
/*  93 */     this.logger.error("your request url is not the right pattern, it is not allowed use this getAutoView method");
/*  94 */     throw new Exception("url:[" + requestURI + "] is not in this pattern:[/子系统/包名/表对应实体名/实体操作方法名.ht]");
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setMessages(MessageSource messageSource)
/*     */   {
/* 102 */     this.messages = new MessageSourceAccessor(messageSource);
/*     */   }
/*     */ 
/*     */   public void saveError(HttpServletRequest request, String msg)
/*     */   {
/* 112 */     saveMessage(request, "errors", msg);
/*     */   }
/*     */ 
/*     */   public void saveMessage(HttpServletRequest request, String msg)
/*     */   {
/* 122 */     saveMessage(request, "successMessages", msg);
/*     */   }
/*     */ 
/*     */   public void saveMessage(HttpServletRequest request, String key, String msg)
/*     */   {
/* 133 */     List messages = (List)request.getSession().getAttribute(key);
/*     */ 
/* 135 */     if (messages == null) {
/* 136 */       messages = new ArrayList();
/*     */     }
/* 138 */     messages.add(msg);
/* 139 */     request.getSession().setAttribute(key, messages);
/*     */   }
/*     */ 
/*     */   public String getText(String msgKey, Locale locale)
/*     */   {
/* 150 */     return this.messages.getMessage(msgKey, locale);
/*     */   }
/*     */ 
/*     */   public String getText(String msgKey, String arg, Locale locale)
/*     */   {
/* 162 */     return getText(msgKey, new Object[] { arg }, locale);
/*     */   }
/*     */ 
/*     */   public String getText(String msgKey, Object[] args, Locale locale)
/*     */   {
/* 174 */     return this.messages.getMessage(msgKey, args, locale);
/*     */   }
/*     */ 
/*     */   public String getText(String msgKey, Object... args)
/*     */   {
/* 185 */     return this.messages.getMessage(msgKey, args, ContextUtil.getLocale());
/*     */   }
/*     */ 
/*     */   public String getText(String msgKey)
/*     */   {
/* 195 */     return this.messages.getMessage(msgKey, ContextUtil.getLocale());
/*     */   }
/*     */ 
/*     */   protected String getText(String msgKey, String arg, HttpServletRequest request)
/*     */   {
/* 208 */     Locale locale = ContextUtil.getLocale();
/* 209 */     return getText(msgKey, arg, locale);
/*     */   }
/*     */ 
/*     */   protected String getText(String msgKey, Object[] args, HttpServletRequest request)
/*     */   {
/* 222 */     Locale locale = ContextUtil.getLocale();
/* 223 */     return getText(msgKey, args, locale);
/*     */   }
/*     */ 
/*     */   protected void writeResultMessage(PrintWriter writer, String resultMsg, String cause, int successFail)
/*     */   {
/* 236 */     ResultMessage resultMessage = new ResultMessage(successFail, resultMsg, cause);
/*     */ 
/* 238 */     writeResultMessage(writer, resultMessage);
/*     */   }
/*     */ 
/*     */   protected void writeResultMessage(PrintWriter writer, String resultMsg, int successFail)
/*     */   {
/* 250 */     ResultMessage resultMessage = new ResultMessage(successFail, resultMsg);
/* 251 */     writeResultMessage(writer, resultMessage);
/*     */   }
/*     */ 
/*     */   protected void writeResultMessage(PrintWriter writer, ResultMessage resultMessage)
/*     */   {
/* 262 */     writer.print(resultMessage);
/*     */   }
/*     */ 
/*     */   protected void saveResultMessage(HttpSession session, String msg, int successFail)
/*     */   {
/* 274 */     ResultMessage resultMsg = new ResultMessage(successFail, msg);
/* 275 */     session.setAttribute("message", resultMsg);
/*     */   }
/*     */ 
/*     */   protected void saveSuccessResultMessage(HttpSession session, String msg)
/*     */   {
/* 285 */     saveResultMessage(session, msg, 1);
/*     */   }
/*     */ 
/*     */   protected void saveFailResultMessage(HttpSession session, String msg)
/*     */   {
/* 295 */     saveResultMessage(session, msg, 0);
/*     */   }
/*     */ 
/*     */   public void sendJsonToWeb(Object obj, HttpServletResponse response, QueryFilter queryFilter)
/*     */     throws IOException
/*     */   {
/* 307 */     JSONArray json = new JSONArray();
/* 308 */     json.add(obj);
/* 309 */     json.add(queryFilter.getPageBean());
/* 310 */     PrintWriter out = response.getWriter();
/* 311 */     out.print(json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.controller.GenericController
 * JD-Core Version:    0.6.2
 */