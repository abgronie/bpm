/*    */ package com.hotent.core.web.controller;
/*    */ 
/*    */ import com.hotent.core.json.SmartDateEditor;
/*    */ import com.hotent.core.web.ResultMessage;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.propertyeditors.CustomNumberEditor;
/*    */ import org.springframework.validation.BindingResult;
/*    */ import org.springframework.web.bind.ServletRequestDataBinder;
/*    */ import org.springframework.web.bind.annotation.InitBinder;
/*    */ import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
/*    */ import org.springmodules.validation.commons.ConfigurableBeanValidator;
/*    */ 
/*    */ public class BaseFormController extends GenericController
/*    */ {
/* 30 */   public Logger logger = LoggerFactory.getLogger(BaseFormController.class);
/*    */ 
/*    */   @Resource
/*    */   protected ConfigurableBeanValidator confValidator;
/*    */ 
/*    */   @InitBinder
/*    */   protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
/*    */   {
/* 46 */     this.logger.debug("init binder ....");
/* 47 */     binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
/* 48 */     binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
/* 49 */     binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
/* 50 */     binder.registerCustomEditor(Date.class, new SmartDateEditor());
/*    */   }
/*    */ 
/*    */   protected ResultMessage validForm(String form, Object obj, BindingResult result, HttpServletRequest request)
/*    */   {
/* 67 */     ResultMessage resObj = new ResultMessage(1, "");
/*    */ 
/* 82 */     return resObj;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.controller.BaseFormController
 * JD-Core Version:    0.6.2
 */