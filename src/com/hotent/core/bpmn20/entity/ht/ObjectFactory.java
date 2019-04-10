/*    */ package com.hotent.core.bpmn20.entity.ht;
/*    */ 
/*    */ import javax.xml.bind.JAXBElement;
/*    */ import javax.xml.bind.annotation.XmlElementDecl;
/*    */ import javax.xml.bind.annotation.XmlRegistry;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlRegistry
/*    */ public class ObjectFactory
/*    */ {
/* 34 */   private static final QName _Order_QNAME = new QName("http://www.jee-soft.cn/BPMN20EXT", "order");
/*    */ 
/*    */   @XmlElementDecl(namespace="http://www.jee-soft.cn/BPMN20EXT", name="order")
/*    */   public JAXBElement<Integer> createOrder(Integer value)
/*    */   {
/* 49 */     return new JAXBElement(_Order_QNAME, Integer.class, null, value);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ht.ObjectFactory
 * JD-Core Version:    0.6.2
 */