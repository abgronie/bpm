/*    */ package com.hotent.core.soap.type;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import javax.xml.soap.SOAPElement;
/*    */ import org.apache.xmlbeans.XmlDateTime;
/*    */ import org.apache.xmlbeans.impl.values.XmlDateTimeImpl;
/*    */ 
/*    */ public class DateSoapType extends BaseSoapType
/*    */ {
/*    */   public Class<?>[] getBeanTypes()
/*    */   {
/* 22 */     return new Class[] { Date.class, Calendar.class };
/*    */   }
/*    */ 
/*    */   public String[] getSoapTypes()
/*    */   {
/* 27 */     return new String[] { "date", "dateTime" };
/*    */   }
/*    */ 
/*    */   void setCurrentValue(SOAPElement element, Object obj, Class<?> klass)
/*    */   {
/* 32 */     XmlDateTime xmlDateTime = new XmlDateTimeImpl();
/* 33 */     if ((obj instanceof Date))
/* 34 */       xmlDateTime.setDateValue((Date)obj);
/* 35 */     else if ((obj instanceof Calendar)) {
/* 36 */       xmlDateTime.setCalendarValue((Calendar)obj);
/*    */     }
/* 38 */     element.setTextContent(xmlDateTime.getStringValue());
/*    */   }
/*    */ 
/*    */   Object convertCurrent(Class<?> klass, SOAPElement element)
/*    */   {
/* 44 */     XmlDateTime xmlDateTime = new XmlDateTimeImpl();
/* 45 */     xmlDateTime.setStringValue(element.getTextContent());
/* 46 */     if (klass == Date.class) {
/* 47 */       return xmlDateTime.getDateValue();
/*    */     }
/*    */ 
/* 50 */     if (klass == Calendar.class) {
/* 51 */       return xmlDateTime.getCalendarValue();
/*    */     }
/*    */ 
/* 54 */     return element.getTextContent();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.DateSoapType
 * JD-Core Version:    0.6.2
 */