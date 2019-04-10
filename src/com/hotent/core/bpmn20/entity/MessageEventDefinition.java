/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tMessageEventDefinition", propOrder={"operationRef"})
/*    */ public class MessageEventDefinition extends EventDefinition
/*    */ {
/*    */   protected QName operationRef;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName messageRef;
/*    */ 
/*    */   public QName getOperationRef()
/*    */   {
/* 60 */     return this.operationRef;
/*    */   }
/*    */ 
/*    */   public void setOperationRef(QName value)
/*    */   {
/* 72 */     this.operationRef = value;
/*    */   }
/*    */ 
/*    */   public QName getMessageRef()
/*    */   {
/* 84 */     return this.messageRef;
/*    */   }
/*    */ 
/*    */   public void setMessageRef(QName value)
/*    */   {
/* 96 */     this.messageRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.MessageEventDefinition
 * JD-Core Version:    0.6.2
 */