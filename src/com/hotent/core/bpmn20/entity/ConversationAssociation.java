/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tConversationAssociation")
/*    */ public class ConversationAssociation extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName innerConversationNodeRef;
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName outerConversationNodeRef;
/*    */ 
/*    */   public QName getInnerConversationNodeRef()
/*    */   {
/* 57 */     return this.innerConversationNodeRef;
/*    */   }
/*    */ 
/*    */   public void setInnerConversationNodeRef(QName value)
/*    */   {
/* 69 */     this.innerConversationNodeRef = value;
/*    */   }
/*    */ 
/*    */   public QName getOuterConversationNodeRef()
/*    */   {
/* 81 */     return this.outerConversationNodeRef;
/*    */   }
/*    */ 
/*    */   public void setOuterConversationNodeRef(QName value)
/*    */   {
/* 93 */     this.outerConversationNodeRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ConversationAssociation
 * JD-Core Version:    0.6.2
 */