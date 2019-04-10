/*     */ package com.hotent.core.soap.type;
/*     */ 
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.soap.SOAPElement;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class BeanSoapType extends BaseSoapType
/*     */ {
/*  24 */   private static Logger logger = Logger.getLogger(BaseSoapType.class);
/*     */ 
/*     */   public Class<?>[] getBeanTypes()
/*     */   {
/*  28 */     return new Class[] { Object.class };
/*     */   }
/*     */ 
/*     */   public String[] getSoapTypes()
/*     */   {
/*  33 */     return new String[] { "anyType" };
/*     */   }
/*     */ 
/*     */   void setCurrentValue(SOAPElement element, Object obj, Class<?> klass)
/*     */   {
/*  39 */     Class myKlass = obj.getClass();
/*  40 */     if (myKlass != null) {
/*  41 */       klass = myKlass;
/*     */     }
/*  43 */     for (Field field : klass.getDeclaredFields()) {
/*  44 */       field.setAccessible(true);
/*  45 */       Class fieldType = field.getType();
/*  46 */       String fieldName = field.getName();
/*  47 */       fieldName = fieldName.replace("$cglib_prop_", "");
/*  48 */       NodeList fieldNodeList = element.getElementsByTagName(fieldName);
/*  49 */       if ((fieldNodeList != null) && (fieldNodeList.getLength() >= 1))
/*     */       {
/*     */         try
/*     */         {
/*  54 */           Object objValue = field.get(obj);
/*  55 */           SOAPElement targetElement = (SOAPElement)fieldNodeList.item(0);
/*  56 */           if (BeanUtils.isEmpty(objValue)) {
/*  57 */             boolean hasChild = targetElement.hasChildNodes();
/*     */ 
/*  59 */             if (!hasChild)
/*  60 */               targetElement.detachNode();
/*     */           }
/*     */           else
/*     */           {
/*  64 */             SoapTypes.getTypeByBean(fieldType).setValue(targetElement, objValue, fieldType);
/*     */           }
/*     */         } catch (Exception e) {
/*  67 */           logger.warn("字段[" + fieldName + "]设置失败.", e);
/*     */         }
/*     */       }
/*     */     }
/*  71 */     Iterator it = element.getChildElements();
/*  72 */     while (it.hasNext()) {
/*  73 */       SOAPElement child = (SOAPElement)it.next();
/*  74 */       if (!child.hasChildNodes()) {
/*  75 */         String content = child.getTextContent();
/*  76 */         if (StringUtil.isEmpty(content))
/*  77 */           child.detachNode();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   Object convertCurrent(Class<?> klass, SOAPElement element)
/*     */   {
/*     */     Object bean;
/*     */     try {
/*  86 */       bean = klass.newInstance();
/*     */     } catch (Exception e) {
/*  88 */       logger.error("类别[" + klass + "]无法实例化.", e);
/*  89 */       return null;
/*     */     }
/*     */ 
/*  92 */     for (Field field : klass.getDeclaredFields()) {
/*  93 */       field.setAccessible(true);
/*  94 */       Class fieldType = field.getType();
/*  95 */       String fieldName = field.getName();
/*  96 */       NodeList fieldNodeList = element.getElementsByTagName(fieldName);
/*  97 */       if ((fieldNodeList != null) && (fieldNodeList.getLength() >= 1))
/*     */       {
/*     */         try
/*     */         {
/* 101 */           Object obj = SoapTypes.getTypeByBean(fieldType).convertToBean(fieldType, new SOAPElement[] { element });
/* 102 */           field.set(bean, obj);
/*     */         }
/*     */         catch (Exception e) {
/* 105 */           logger.warn("字段[" + fieldName + "]设置失败.", e);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 110 */     return bean;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.BeanSoapType
 * JD-Core Version:    0.6.2
 */