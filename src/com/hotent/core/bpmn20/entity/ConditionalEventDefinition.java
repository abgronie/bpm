/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tConditionalEventDefinition", propOrder={"condition"})
/*    */ public class ConditionalEventDefinition extends EventDefinition
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected Expression condition;
/*    */ 
/*    */   public Expression getCondition()
/*    */   {
/* 57 */     return this.condition;
/*    */   }
/*    */ 
/*    */   public void setCondition(Expression value)
/*    */   {
/* 69 */     this.condition = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ConditionalEventDefinition
 * JD-Core Version:    0.6.2
 */