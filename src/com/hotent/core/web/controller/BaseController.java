/*     */ package com.hotent.core.web.controller;
/*     */ 
/*     */ import com.hotent.core.json.SmartDateEditor;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.page.PageList;
/*     */ import com.hotent.core.util.ConfigUtil;
/*     */ import com.hotent.core.web.ResultMessage;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.beans.propertyeditors.CustomNumberEditor;
/*     */ import org.springframework.validation.BindingResult;
/*     */ import org.springframework.validation.FieldError;
/*     */ import org.springframework.web.bind.ServletRequestDataBinder;
/*     */ import org.springframework.web.bind.annotation.InitBinder;
/*     */ import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import org.springmodules.validation.commons.ConfigurableBeanValidator;
/*     */ 
/*     */ public class BaseController extends GenericController
/*     */ {
/*     */   public static final String Message = "message";
/*     */ 
/*     */   @Resource
/*     */   protected ConfigurableBeanValidator confValidator;
/*     */ 
/*     */   public void addMessage(ResultMessage message, HttpServletRequest request)
/*     */   {
/*  41 */     HttpSession session = request.getSession();
/*     */ 
/*  43 */     session.setAttribute("message", message);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   @InitBinder
/*     */   protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
/*     */   {
/*  60 */     this.logger.debug("init binder ....");
/*  61 */     binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
/*  62 */     binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
/*  63 */     binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
/*  64 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/*  65 */     dateFormat.setLenient(false);
/*  66 */     binder.registerCustomEditor(Date.class, new SmartDateEditor());
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   protected ResultMessage validForm(String form, Object obj, BindingResult result, HttpServletRequest request)
/*     */   {
/*  81 */     ResultMessage resObj = new ResultMessage(1, "");
/*  82 */     this.confValidator.setFormName(form);
/*  83 */     this.confValidator.validate(obj, result);
/*  84 */     if (result.hasErrors())
/*     */     {
/*  86 */       resObj.setResult(0);
/*  87 */       List<FieldError> list = result.getFieldErrors();
/*  88 */       String errMsg = "";
/*  89 */       for (FieldError err : list)
/*     */       {
/*  91 */         String msg = getText(err.getDefaultMessage(), err.getArguments(), request);
/*  92 */         errMsg = errMsg + msg + "\r\n";
/*     */       }
/*  94 */       resObj.setMessage(errMsg);
/*     */     }
/*  96 */     return resObj;
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public ModelAndView getView(String category, String id)
/*     */   {
/* 108 */     String view = ConfigUtil.getVal(category, id);
/* 109 */     return new ModelAndView(view);
/*     */   }
/*     */ 
/*     */   protected Map<String, Object> getMapByPageList(PageList pageList)
/*     */   {
/* 118 */     Map map = new HashMap();
/* 119 */     map.put("rows", pageList);
/* 120 */     map.put("total", Integer.valueOf(pageList.getTotalCount()));
/*     */ 
/* 122 */     return map;
/*     */   }
/*     */ 
/*     */   protected Map<String, Object> getMapByPageListJq(PageList pageList)
/*     */   {
/* 127 */     Map map = new HashMap();
/* 128 */     map.put("rows", pageList);
/* 129 */     map.put("records", Integer.valueOf(pageList.getTotalCount()));
/* 130 */     map.put("page", Integer.valueOf(pageList.getPageBean().getCurrentPage()));
/* 131 */     map.put("total", Integer.valueOf(pageList.getTotalPage()));
/*     */ 
/* 133 */     return map;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.controller.BaseController
 * JD-Core Version:    0.6.2
 */