/*    */ package com.hotent.core.soap.type;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import javax.xml.soap.SOAPElement;
/*    */ 
/*    */ public class NumberSoapType extends BaseSoapType
/*    */ {
/*    */   public Class<?>[] getBeanTypes()
/*    */   {
/* 18 */     return new Class[] { Integer.TYPE, Integer.class, Long.TYPE, Long.class, Short.TYPE, Short.class, Double.TYPE, Double.class, Float.TYPE, Float.class, BigDecimal.class };
/*    */   }
/*    */ 
/*    */   public String[] getSoapTypes()
/*    */   {
/* 23 */     return new String[] { "int", "long", "short", "integer" };
/*    */   }
/*    */ 
/*    */   void setCurrentValue(SOAPElement element, Object obj, Class<?> klass)
/*    */   {
/* 28 */     element.setTextContent(obj.toString());
/*    */   }
/*    */ 
/*    */   Object convertCurrent(Class<?> klass, SOAPElement element)
/*    */   {
/* 33 */     String value = element.getTextContent();
/* 34 */     if (klass == Integer.class)
/* 35 */       return Integer.valueOf(value);
/* 36 */     if (klass == Long.class)
/* 37 */       return Long.valueOf(value);
/* 38 */     if (klass == Short.class)
/* 39 */       return Short.valueOf(value);
/* 40 */     if (klass == Double.class)
/* 41 */       return Double.valueOf(value);
/* 42 */     if (klass == Float.class)
/* 43 */       return Float.valueOf(value);
/* 44 */     if (klass == BigDecimal.class) {
/* 45 */       return BigDecimal.valueOf(Double.valueOf(value).doubleValue());
/*    */     }
/* 47 */     return value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.NumberSoapType
 * JD-Core Version:    0.6.2
 */