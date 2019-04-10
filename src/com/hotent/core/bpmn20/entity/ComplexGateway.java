/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlIDREF;
/*    */ import javax.xml.bind.annotation.XmlSchemaType;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tComplexGateway", propOrder={"activationCondition"})
/*    */ public class ComplexGateway extends Gateway
/*    */ {
/*    */   protected Expression activationCondition;
/*    */ 
/*    */   @XmlAttribute(name="default")
/*    */   @XmlIDREF
/*    */   @XmlSchemaType(name="IDREF")
/*    */   protected Object _default;
/*    */ 
/*    */   public Expression getActivationCondition()
/*    */   {
/* 63 */     return this.activationCondition;
/*    */   }
/*    */ 
/*    */   public void setActivationCondition(Expression value)
/*    */   {
/* 75 */     this.activationCondition = value;
/*    */   }
/*    */ 
/*    */   public Object getDefault()
/*    */   {
/* 87 */     return this._default;
/*    */   }
/*    */ 
/*    */   public void setDefault(Object value)
/*    */   {
/* 99 */     this._default = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ComplexGateway
 * JD-Core Version:    0.6.2
 */