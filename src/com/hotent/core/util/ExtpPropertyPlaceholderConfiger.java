/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import com.hotent.core.encrypt.EncryptUtil;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Properties;
/*    */ import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/*    */ import org.springframework.util.ObjectUtils;
/*    */ 
/*    */ public class ExtpPropertyPlaceholderConfiger extends PropertyPlaceholderConfigurer
/*    */ {
/* 26 */   private String[] encryptNames = { "jdbc.password" };
/*    */ 
/*    */   protected void convertProperties(Properties props)
/*    */   {
/* 30 */     Enumeration propertyNames = props.propertyNames();
/* 31 */     while (propertyNames.hasMoreElements()) {
/* 32 */       String propertyName = (String)propertyNames.nextElement();
/* 33 */       String propertyValue = props.getProperty(propertyName);
/*    */ 
/* 36 */       String convertedValue = convertPropertyValue(propertyValue);
/* 37 */       if (isEncryptProp(propertyName)) {
/*    */         try {
/* 39 */           convertedValue = EncryptUtil.decrypt(convertedValue);
/*    */         } catch (Exception e) {
/* 41 */           e.printStackTrace();
/*    */         }
/*    */       }
/* 44 */       if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue))
/*    */       {
/* 46 */         props.setProperty(propertyName, convertedValue);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   private boolean isEncryptProp(String propertyName)
/*    */   {
/* 54 */     for (String name : this.encryptNames) {
/* 55 */       if (name.equals(propertyName)) {
/* 56 */         return true;
/*    */       }
/*    */     }
/* 59 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ExtpPropertyPlaceholderConfiger
 * JD-Core Version:    0.6.2
 */