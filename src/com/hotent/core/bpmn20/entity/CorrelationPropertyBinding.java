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
/*    */ @XmlType(name="tCorrelationPropertyBinding", propOrder={"dataPath"})
/*    */ public class CorrelationPropertyBinding extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected FormalExpression dataPath;
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName correlationPropertyRef;
/*    */ 
/*    */   public FormalExpression getDataPath()
/*    */   {
/* 62 */     return this.dataPath;
/*    */   }
/*    */ 
/*    */   public void setDataPath(FormalExpression value)
/*    */   {
/* 74 */     this.dataPath = value;
/*    */   }
/*    */ 
/*    */   public QName getCorrelationPropertyRef()
/*    */   {
/* 86 */     return this.correlationPropertyRef;
/*    */   }
/*    */ 
/*    */   public void setCorrelationPropertyRef(QName value)
/*    */   {
/* 98 */     this.correlationPropertyRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CorrelationPropertyBinding
 * JD-Core Version:    0.6.2
 */