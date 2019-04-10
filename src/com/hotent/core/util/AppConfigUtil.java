/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class AppConfigUtil
/*    */ {
/*    */   public static String get(String propertyKey)
/*    */   {
/* 18 */     Properties properties = (Properties)AppUtil.getBean("configproperties");
/* 19 */     return properties.getProperty(propertyKey);
/*    */   }
/*    */ 
/*    */   public static int getInt(String propertyKey)
/*    */   {
/* 28 */     String val = get(propertyKey);
/* 29 */     return Integer.parseInt(val);
/*    */   }
/*    */ 
/*    */   public static int getInt(String propertyKey, int defaultValue)
/*    */   {
/* 40 */     String val = get(propertyKey);
/* 41 */     if (StringUtil.isEmpty(val)) return defaultValue;
/* 42 */     return Integer.parseInt(val);
/*    */   }
/*    */ 
/*    */   public static String get(String propertyKey, String defultValue)
/*    */   {
/* 52 */     String val = get(propertyKey);
/* 53 */     if (StringUtil.isEmpty(val)) return defultValue;
/* 54 */     return val;
/*    */   }
/*    */ 
/*    */   public static boolean getBoolean(String propertyKey)
/*    */   {
/* 63 */     String val = get(propertyKey);
/* 64 */     return val.equalsIgnoreCase("true");
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.AppConfigUtil
 * JD-Core Version:    0.6.2
 */