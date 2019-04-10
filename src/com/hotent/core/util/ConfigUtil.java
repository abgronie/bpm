/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class ConfigUtil
/*    */ {
/* 19 */   private Document doc = null;
/* 20 */   private static ConfigUtil config = null;
/*    */ 
/* 22 */   private static Lock lock = new ReentrantLock();
/*    */ 
/*    */   private ConfigUtil()
/*    */   {
/* 26 */     InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream("conf/viewconfig.xml");
/* 27 */     this.doc = Dom4jUtil.loadXml(is);
/*    */     try {
/* 29 */       is.close();
/*    */     } catch (IOException e) {
/* 31 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static ConfigUtil getInstance()
/*    */   {
/* 41 */     if (config == null)
/*    */     {
/* 43 */       lock.lock();
/*    */       try {
/* 45 */         if (config == null)
/* 46 */           config = new ConfigUtil();
/*    */       }
/*    */       finally {
/* 49 */         lock.unlock();
/*    */       }
/*    */     }
/* 52 */     return config;
/*    */   }
/*    */ 
/*    */   public String getValue(String category, String id)
/*    */   {
/* 63 */     String template = "category[@id='%s']/view[@name='%s']";
/* 64 */     String filter = String.format(template, new Object[] { category, id });
/* 65 */     Element root = this.doc.getRootElement();
/* 66 */     Element el = (Element)root.selectSingleNode(filter);
/* 67 */     if (el != null)
/* 68 */       return el.attributeValue("value");
/* 69 */     return "";
/*    */   }
/*    */ 
/*    */   public static String getVal(String category, String id)
/*    */   {
/* 80 */     return getInstance().getValue(category, id);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ConfigUtil
 * JD-Core Version:    0.6.2
 */