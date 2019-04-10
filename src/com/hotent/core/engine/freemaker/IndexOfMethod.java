/*    */ package com.hotent.core.engine.freemaker;
/*    */ 
/*    */ import freemarker.template.SimpleNumber;
/*    */ import freemarker.template.TemplateMethodModel;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class IndexOfMethod
/*    */   implements TemplateMethodModel
/*    */ {
/*    */   public TemplateModel exec(List args)
/*    */     throws TemplateModelException
/*    */   {
/* 19 */     if (args.size() != 2) {
/* 20 */       throw new TemplateModelException("此访问参数必须为两个.");
/*    */     }
/* 22 */     String str1 = (String)args.get(1);
/* 23 */     String str2 = (String)args.get(0);
/*    */ 
/* 25 */     return new SimpleNumber(str1.indexOf(str2));
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.engine.freemaker.IndexOfMethod
 * JD-Core Version:    0.6.2
 */