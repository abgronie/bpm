/*    */ package com.hotent.core.soap.type;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.soap.SOAPElement;
/*    */ import javax.xml.soap.SOAPException;
/*    */ 
/*    */ abstract class BaseSoapType
/*    */   implements SoapType
/*    */ {
/*    */   public abstract Class<?>[] getBeanTypes();
/*    */ 
/*    */   public abstract String[] getSoapTypes();
/*    */ 
/*    */   private final Class<?> getDefaultClass()
/*    */   {
/* 35 */     Class[] klasses = getBeanTypes();
/* 36 */     if ((klasses == null) || (klasses.length == 0)) {
/* 37 */       return Object.class;
/*    */     }
/*    */ 
/* 40 */     return klasses[0];
/*    */   }
/*    */ 
/*    */   abstract Object convertCurrent(Class<?> paramClass, SOAPElement paramSOAPElement);
/*    */ 
/*    */   public final Object convertToBean(Class<?> klass, SOAPElement[] elements)
/*    */     throws SOAPException
/*    */   {
/* 48 */     if ((elements == null) || (elements.length < 1)) {
/* 49 */       return null;
/*    */     }
/*    */ 
/* 52 */     if (elements.length > 1) {
/* 53 */       List list = new ArrayList();
/* 54 */       for (SOAPElement element : elements) {
/* 55 */         Object obj = convertCurrent(klass, element);
/* 56 */         list.add(obj);
/*    */       }
/* 58 */       return list;
/*    */     }
/* 60 */     return convertCurrent(klass, elements[0]);
/*    */   }
/*    */ 
/*    */   abstract void setCurrentValue(SOAPElement paramSOAPElement, Object paramObject, Class<?> paramClass);
/*    */ 
/*    */   public final void setValue(SOAPElement element, Object obj, Class<?> klass)
/*    */     throws SOAPException
/*    */   {
/* 68 */     if (obj == null) {
/* 69 */       return;
/*    */     }
/* 71 */     if (klass == null)
/* 72 */       klass = getDefaultClass();
/* 73 */     setCurrentValue(element, obj, klass);
/*    */   }
/*    */ 
/*    */   public final Object convertToBean(SOAPElement[] elements) throws SOAPException
/*    */   {
/* 78 */     return convertToBean(getDefaultClass(), elements);
/*    */   }
/*    */ 
/*    */   public final void setValue(SOAPElement element, Object obj) throws SOAPException
/*    */   {
/* 83 */     setValue(element, obj, getDefaultClass());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.BaseSoapType
 * JD-Core Version:    0.6.2
 */