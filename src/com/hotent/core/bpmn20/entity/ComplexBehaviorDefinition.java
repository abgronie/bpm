/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tComplexBehaviorDefinition", propOrder={"condition", "event"})
/*    */ public class ComplexBehaviorDefinition extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected FormalExpression condition;
/*    */   protected ImplicitThrowEvent event;
/*    */ 
/*    */   public FormalExpression getCondition()
/*    */   {
/* 60 */     return this.condition;
/*    */   }
/*    */ 
/*    */   public void setCondition(FormalExpression value)
/*    */   {
/* 72 */     this.condition = value;
/*    */   }
/*    */ 
/*    */   public ImplicitThrowEvent getEvent()
/*    */   {
/* 84 */     return this.event;
/*    */   }
/*    */ 
/*    */   public void setEvent(ImplicitThrowEvent value)
/*    */   {
/* 96 */     this.event = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ComplexBehaviorDefinition
 * JD-Core Version:    0.6.2
 */