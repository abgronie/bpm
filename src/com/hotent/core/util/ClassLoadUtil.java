/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.xml.transform.TransformerFactoryConfigurationError;
/*    */ 
/*    */ public class ClassLoadUtil
/*    */ {
/*    */   public static String transform(String id, String name, String xml)
/*    */     throws TransformerFactoryConfigurationError, Exception
/*    */   {
/* 33 */     if (StringUtil.isEmpty(xml)) return "";
/* 34 */     Map map = new HashMap();
/* 35 */     map.put("id", id);
/* 36 */     map.put("name", name);
/*    */ 
/* 38 */     String xlstPath = FileUtil.getClassesPath() + "com/hotent/core/bpm/graph/transform.xsl".replace("/", File.separator);
/*    */ 
/* 41 */     xml = xml.trim();
/* 42 */     String str = Dom4jUtil.transXmlByXslt(xml, xlstPath, map);
/* 43 */     str = str.replace("&lt;", "<").replace("&gt;", ">").replace("xmlns=\"\"", "").replace("&amp;", "&");
/*    */ 
/* 46 */     Pattern regex = Pattern.compile("name=\".*?\"");
/* 47 */     Matcher regexMatcher = regex.matcher(str);
/* 48 */     while (regexMatcher.find()) {
/* 49 */       String strReplace = regexMatcher.group(0);
/* 50 */       String strReplaceWith = strReplace.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
/*    */ 
/* 52 */       str = str.replace(strReplace, strReplaceWith);
/*    */     }
/*    */ 
/* 55 */     return str;
/*    */   }
/*    */ 
/*    */   public static void contextInitialized(ServletContextEvent event)
/*    */   {
/* 62 */     AppUtil.init(event.getServletContext());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ClassLoadUtil
 * JD-Core Version:    0.6.2
 */