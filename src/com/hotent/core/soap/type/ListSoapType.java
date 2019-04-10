/*     */ package com.hotent.core.soap.type;
/*     */ 
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.soap.SOAPElement;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class ListSoapType extends BaseSoapType
/*     */ {
/*  25 */   private static Logger logger = Logger.getLogger(BaseSoapType.class);
/*     */   private Class<?> currentClass;
/*     */ 
/*     */   public Class<?>[] getBeanTypes()
/*     */   {
/*  28 */     return new Class[] { List.class };
/*     */   }
/*     */ 
/*     */   public String[] getSoapTypes()
/*     */   {
/*  33 */     return new String[] { "List" };
/*     */   }
/*     */ 
/*     */   void setCurrentValue(SOAPElement element, Object obj, Class<?> klass)
/*     */   {
/*  40 */     String tagName = element.getTagName();
/*     */     try {
/*  42 */       List list = (List)obj;
/*  43 */       if (list == null) return;
/*  44 */       String elementName = element.getLocalName();
/*  45 */       SOAPElement parentElement = element.getParentElement();
/*  46 */       NodeList fieldNodeList = parentElement.getElementsByTagName(elementName);
/*  47 */       if (fieldNodeList == null) return;
/*  48 */       int nodeCount = fieldNodeList.getLength();
/*  49 */       if (nodeCount == list.size()) {
/*  50 */         for (int i = 0; i < nodeCount; i++) {
/*  51 */           Object item = list.get(i);
/*  52 */           this.currentClass = item.getClass();
/*  53 */           SOAPElement itemElement = (SOAPElement)fieldNodeList.item(i);
/*  54 */           SoapTypes.getTypeByBean(this.currentClass).setValue(itemElement, item, this.currentClass);
/*     */         }
/*     */       }
/*     */       else {
/*  58 */         Node tempElement = element.cloneNode(true);
/*  59 */         element.detachNode();
				  Iterator i$;
/*  60 */         for (i$ = list.iterator(); i$.hasNext(); ) { 
					Object item = i$.next();
/*  61 */           this.currentClass = item.getClass();
/*  62 */           SOAPElement itemElement = (SOAPElement)tempElement.cloneNode(true);
/*  63 */           parentElement.addChildElement(itemElement);
/*  64 */           SoapTypes.getTypeByBean(this.currentClass).setValue(itemElement, item, this.currentClass);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*     */       SOAPElement parentElement;
/*     */       Node tempElement;
/*  70 */       logger.warn("字段[" + tagName + "]设置失败.", ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   Object convertCurrent(Class<?> klass, SOAPElement element)
/*     */   {
/*  76 */     String tagName = element.getTagName();
/*     */     try {
/*  78 */       SOAPElement parentElement = element.getParentElement();
/*  79 */       NodeList nodeList = parentElement.getElementsByTagName(tagName);
/*  80 */       int size = nodeList.getLength();
/*  81 */       List list = new ArrayList();
/*  82 */       for (int i = 0; i < size; i++) {
/*  83 */         SOAPElement node = (SOAPElement)nodeList.item(i);
/*  84 */         String text = node.getTextContent();
/*  85 */         if (StringUtil.isEmpty(text)) {
/*  86 */           SoapType convert = SoapTypes.getTypeByBean(null);
/*  87 */           Class c = Object.class;
/*     */           try {
/*  89 */             c = Class.forName(node.getTagName());
/*     */           } catch (Exception e) {
/*     */           }
/*  92 */           Object obj = convert.convertToBean(c, new SOAPElement[] { element });
/*  93 */           list.add(obj);
/*     */         }
/*     */         else {
/*  96 */           list.add(text);
/*     */         }
/*     */       }
/*  99 */       return list;
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 103 */       logger.warn("字段[" + tagName + "]设置失败.", ex);
/* 104 */     }return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.ListSoapType
 * JD-Core Version:    0.6.2
 */