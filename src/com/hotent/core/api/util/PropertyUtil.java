/*    */ package com.hotent.core.api.util;
/*    */ 
/*    */ import com.hotent.core.api.system.IPropertyService;
/*    */ import com.hotent.core.util.AppUtil;
/*    */ 
/*    */ public class PropertyUtil
/*    */ {
/*    */   public static String getByAlias(String alias)
/*    */   {
/* 14 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 15 */     return service.getByAlias(alias);
/*    */   }
/*    */ 
/*    */   public static String getByAlias(String alias, String defaultValue) {
/* 19 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 20 */     return service.getByAlias(alias, defaultValue);
/*    */   }
/*    */ 
/*    */   public static Integer getIntByAlias(String alias) {
/* 24 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 25 */     return service.getIntByAlias(alias);
/*    */   }
/*    */ 
/*    */   public static Integer getIntByAlias(String alias, Integer defaulValue) {
/* 29 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 30 */     return service.getIntByAlias(alias, defaulValue);
/*    */   }
/*    */ 
/*    */   public static Long getLongByAlias(String alias) {
/* 34 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 35 */     return service.getLongByAlias(alias);
/*    */   }
/*    */ 
/*    */   public static boolean getBooleanByAlias(String alias) {
/* 39 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 40 */     return service.getBooleanByAlias(alias);
/*    */   }
/*    */ 
/*    */   public static boolean getBooleanByAlias(String alias, boolean defaulValue) {
/* 44 */     IPropertyService service = (IPropertyService)AppUtil.getBean(IPropertyService.class);
/* 45 */     return service.getBooleanByAlias(alias, defaulValue);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.util.PropertyUtil
 * JD-Core Version:    0.6.2
 */