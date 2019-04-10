/*    */ package com.hotent.core.bpmn20.entity.omgdi;
/*    */ 
/*    */ import com.hotent.core.bpmn20.entity.bpmndi.BPMNLabel;
/*    */ import com.hotent.core.bpmn20.entity.omgdc.Bounds;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlSeeAlso;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="Label", propOrder={"bounds"})
/*    */ @XmlSeeAlso({BPMNLabel.class})
/*    */ public abstract class Label extends Node
/*    */ {
/*    */ 
/*    */   @XmlElement(name="Bounds", namespace="http://www.omg.org/spec/DD/20100524/DC")
/*    */   protected Bounds bounds;
/*    */ 
/*    */   public Bounds getBounds()
/*    */   {
/* 64 */     return this.bounds;
/*    */   }
/*    */ 
/*    */   public void setBounds(Bounds value)
/*    */   {
/* 76 */     this.bounds = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.Label
 * JD-Core Version:    0.6.2
 */