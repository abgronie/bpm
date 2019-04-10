/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tGroup")
/*    */ public class Group extends Artifact
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName categoryValueRef;
/*    */ 
/*    */   public QName getCategoryValueRef()
/*    */   {
/* 54 */     return this.categoryValueRef;
/*    */   }
/*    */ 
/*    */   public void setCategoryValueRef(QName value)
/*    */   {
/* 66 */     this.categoryValueRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Group
 * JD-Core Version:    0.6.2
 */