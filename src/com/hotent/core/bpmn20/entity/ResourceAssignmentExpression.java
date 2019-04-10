/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.JAXBElement;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElementRef;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tResourceAssignmentExpression", propOrder={"expression"})
/*    */ public class ResourceAssignmentExpression extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlElementRef(name="expression", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*    */   protected JAXBElement<? extends Expression> expression;
/*    */ 
/*    */   public JAXBElement<? extends Expression> getExpression()
/*    */   {
/* 59 */     return this.expression;
/*    */   }
/*    */ 
/*    */   public void setExpression(JAXBElement<? extends Expression> value)
/*    */   {
/* 72 */     this.expression = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ResourceAssignmentExpression
 * JD-Core Version:    0.6.2
 */