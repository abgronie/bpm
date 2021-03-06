/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tStartEvent")
/*    */ public class StartEvent extends CatchEvent
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected Boolean isInterrupting;
/*    */ 
/*    */   public boolean isIsInterrupting()
/*    */   {
/* 53 */     if (this.isInterrupting == null) {
/* 54 */       return true;
/*    */     }
/* 56 */     return this.isInterrupting.booleanValue();
/*    */   }
/*    */ 
/*    */   public void setIsInterrupting(Boolean value)
/*    */   {
/* 69 */     this.isInterrupting = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.StartEvent
 * JD-Core Version:    0.6.2
 */