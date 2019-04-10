/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import org.apache.commons.beanutils.Converter;
/*    */ import org.apache.commons.lang.time.DateUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class BeanDateConnverter
/*    */   implements Converter
/*    */ {
/* 15 */   private static final Logger logger = LoggerFactory.getLogger(BeanDateConnverter.class);
/* 16 */   public static final String[] ACCEPT_DATE_FORMATS = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
/*    */ 
/*    */   public Object convert(Class arg0, Object value)
/*    */   {
/* 25 */     logger.debug("conver " + value + " to date object");
/* 26 */     if (value == null) return null;
/* 27 */     String dateStr = value.toString();
/* 28 */     dateStr = dateStr.replace("T", " ");
/*    */     try {
/* 30 */       return DateUtils.parseDate(dateStr, ACCEPT_DATE_FORMATS);
/*    */     } catch (Exception ex) {
/* 32 */       logger.debug("parse date error:" + ex.getMessage());
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.BeanDateConnverter
 * JD-Core Version:    0.6.2
 */