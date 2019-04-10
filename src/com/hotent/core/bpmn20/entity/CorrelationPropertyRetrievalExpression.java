/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tCorrelationPropertyRetrievalExpression", propOrder={"messagePath"})
/*    */ public class CorrelationPropertyRetrievalExpression extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected FormalExpression messagePath;
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName messageRef;
/*    */ 
/*    */   public FormalExpression getMessagePath()
/*    */   {
/* 62 */     return this.messagePath;
/*    */   }
/*    */ 
/*    */   public void setMessagePath(FormalExpression value)
/*    */   {
/* 74 */     this.messagePath = value;
/*    */   }
/*    */ 
/*    */   public QName getMessageRef()
/*    */   {
/* 86 */     return this.messageRef;
/*    */   }
/*    */ 
/*    */   public void setMessageRef(QName value)
/*    */   {
/* 98 */     this.messageRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CorrelationPropertyRetrievalExpression
 * JD-Core Version:    0.6.2
 */