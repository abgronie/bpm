/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tBoundaryEvent")
/*    */ public class BoundaryEvent extends CatchEvent
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected Boolean cancelActivity;
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected QName attachedToRef;
/*    */ 
/*    */   public boolean isCancelActivity()
/*    */   {
/* 57 */     if (this.cancelActivity == null) {
/* 58 */       return true;
/*    */     }
/* 60 */     return this.cancelActivity.booleanValue();
/*    */   }
/*    */ 
/*    */   public void setCancelActivity(Boolean value)
/*    */   {
/* 73 */     this.cancelActivity = value;
/*    */   }
/*    */ 
/*    */   public QName getAttachedToRef()
/*    */   {
/* 85 */     return this.attachedToRef;
/*    */   }
/*    */ 
/*    */   public void setAttachedToRef(QName value)
/*    */   {
/* 97 */     this.attachedToRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.BoundaryEvent
 * JD-Core Version:    0.6.2
 */