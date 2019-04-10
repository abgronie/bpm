/*    */ package com.hotent.core.engine;
/*    */ 
/*    */ import freemarker.cache.StringTemplateLoader;
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.Template;
/*    */ import freemarker.template.TemplateException;
/*    */ import java.io.IOException;
/*    */ import java.io.StringWriter;
/*    */ import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
/*    */ 
/*    */ public class FreemarkEngine
/*    */ {
/*    */   private Configuration configuration;
/*    */ 
/*    */   public void setConfiguration(Configuration configuration)
/*    */   {
/* 22 */     this.configuration = configuration;
/*    */   }
/*    */ 
/*    */   public String mergeTemplateIntoString(String templateName, Object model)
/*    */     throws IOException, TemplateException
/*    */   {
/* 35 */     Template template = this.configuration.getTemplate(templateName);
/* 36 */     return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
/*    */   }
/*    */ 
/*    */   public String parseByStringTemplate(Object obj, String templateSource)
/*    */     throws TemplateException, IOException
/*    */   {
/* 52 */     Configuration cfg = new Configuration();
/* 53 */     StringTemplateLoader loader = new StringTemplateLoader();
/* 54 */     cfg.setTemplateLoader(loader);
/* 55 */     cfg.setClassicCompatible(true);
/* 56 */     loader.putTemplate("freemaker", templateSource);
/* 57 */     Template template = cfg.getTemplate("freemaker");
/* 58 */     StringWriter writer = new StringWriter();
/* 59 */     template.process(obj, writer);
/* 60 */     return writer.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.engine.FreemarkEngine
 * JD-Core Version:    0.6.2
 */