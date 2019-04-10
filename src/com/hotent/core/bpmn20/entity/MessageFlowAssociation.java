/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tMessageFlowAssociation")
/*    */ public class MessageFlowAssociation extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName innerMessageFlowRef;
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName outerMessageFlowRef;
/*    */ 
/*    */   public QName getInnerMessageFlowRef()
/*    */   {
/* 57 */     return this.innerMessageFlowRef;
/*    */   }
/*    */ 
/*    */   public void setInnerMessageFlowRef(QName value)
/*    */   {
/* 69 */     this.innerMessageFlowRef = value;
/*    */   }
/*    */ 
/*    */   public QName getOuterMessageFlowRef()
/*    */   {
/* 81 */     return this.outerMessageFlowRef;
/*    */   }
/*    */ 
/*    */   public void setOuterMessageFlowRef(QName value)
/*    */   {
/* 93 */     this.outerMessageFlowRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.MessageFlowAssociation
 * JD-Core Version:    0.6.2
 */