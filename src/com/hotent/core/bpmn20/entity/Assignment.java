/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tAssignment", propOrder={"from", "to"})
/*    */ public class Assignment extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected Expression from;
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected Expression to;
/*    */ 
/*    */   public Expression getFrom()
/*    */   {
/* 61 */     return this.from;
/*    */   }
/*    */ 
/*    */   public void setFrom(Expression value)
/*    */   {
/* 73 */     this.from = value;
/*    */   }
/*    */ 
/*    */   public Expression getTo()
/*    */   {
/* 85 */     return this.to;
/*    */   }
/*    */ 
/*    */   public void setTo(Expression value)
/*    */   {
/* 97 */     this.to = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Assignment
 * JD-Core Version:    0.6.2
 */