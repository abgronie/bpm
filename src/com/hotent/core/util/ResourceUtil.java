/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.springframework.context.MessageSource;
/*    */ 
/*    */ public class ResourceUtil
/*    */ {
/*    */   public static String getText(String msgKey, Object arg, Locale local)
/*    */   {
/* 24 */     MessageSource messageSource = (MessageSource)AppUtil.getBean(MessageSource.class);
/* 25 */     return messageSource.getMessage(msgKey, new Object[] { arg }, local);
/*    */   }
/*    */ 
/*    */   public static String getText(String msgKey, Object[] args, Locale local)
/*    */   {
/* 36 */     MessageSource messageSource = (MessageSource)AppUtil.getBean(MessageSource.class);
/* 37 */     return messageSource.getMessage(msgKey, args, local);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ResourceUtil
 * JD-Core Version:    0.6.2
 */