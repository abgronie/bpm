/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tServiceTask")
/*    */ public class ServiceTask extends Task
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String implementation;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName operationRef;
/*    */ 
/*    */   public String getImplementation()
/*    */   {
/* 57 */     if (this.implementation == null) {
/* 58 */       return "##WebService";
/*    */     }
/* 60 */     return this.implementation;
/*    */   }
/*    */ 
/*    */   public void setImplementation(String value)
/*    */   {
/* 73 */     this.implementation = value;
/*    */   }
/*    */ 
/*    */   public QName getOperationRef()
/*    */   {
/* 85 */     return this.operationRef;
/*    */   }
/*    */ 
/*    */   public void setOperationRef(QName value)
/*    */   {
/* 97 */     this.operationRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ServiceTask
 * JD-Core Version:    0.6.2
 */