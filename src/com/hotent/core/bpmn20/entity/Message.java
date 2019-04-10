/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tMessage")
/*    */ public class Message extends RootElement
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String name;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName itemRef;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 57 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String value)
/*    */   {
/* 69 */     this.name = value;
/*    */   }
/*    */ 
/*    */   public QName getItemRef()
/*    */   {
/* 81 */     return this.itemRef;
/*    */   }
/*    */ 
/*    */   public void setItemRef(QName value)
/*    */   {
/* 93 */     this.itemRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Message
 * JD-Core Version:    0.6.2
 */