/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlSchemaType;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tFormalExpression")
/*    */ public class FormalExpression extends Expression
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   @XmlSchemaType(name="anyURI")
/*    */   protected String language;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName evaluatesToTypeRef;
/*    */ 
/*    */   public String getLanguage()
/*    */   {
/* 59 */     return this.language;
/*    */   }
/*    */ 
/*    */   public void setLanguage(String value)
/*    */   {
/* 71 */     this.language = value;
/*    */   }
/*    */ 
/*    */   public QName getEvaluatesToTypeRef()
/*    */   {
/* 83 */     return this.evaluatesToTypeRef;
/*    */   }
/*    */ 
/*    */   public void setEvaluatesToTypeRef(QName value)
/*    */   {
/* 95 */     this.evaluatesToTypeRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.FormalExpression
 * JD-Core Version:    0.6.2
 */