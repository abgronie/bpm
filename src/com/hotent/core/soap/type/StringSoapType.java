/*    */ package com.hotent.core.soap.type;
/*    */ 
/*    */ import javax.xml.soap.SOAPElement;
/*    */ 
/*    */ public class StringSoapType extends BaseSoapType
/*    */ {
/*    */   public Class<?>[] getBeanTypes()
/*    */   {
/* 17 */     return new Class[] { String.class };
/*    */   }
/*    */ 
/*    */   public String[] getSoapTypes()
/*    */   {
/* 22 */     return new String[] { "string" };
/*    */   }
/*    */ 
/*    */   void setCurrentValue(SOAPElement element, Object obj, Class<?> klass)
/*    */   {
/* 27 */     element.setTextContent(obj.toString());
/*    */   }
/*    */ 
/*    */   Object convertCurrent(Class<?> klass, SOAPElement element)
/*    */   {
/* 32 */     return element.getTextContent();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.StringSoapType
 * JD-Core Version:    0.6.2
 */