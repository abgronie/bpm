/*    */ package com.hotent.core.bpmn20.entity.bpmndi;
/*    */ 
/*    */ import com.hotent.core.bpmn20.entity.omgdi.Plane;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="BPMNPlane")
/*    */ public class BPMNPlane extends Plane
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName bpmnElement;
/*    */ 
/*    */   public QName getBpmnElement()
/*    */   {
/* 56 */     return this.bpmnElement;
/*    */   }
/*    */ 
/*    */   public void setBpmnElement(QName value)
/*    */   {
/* 68 */     this.bpmnElement = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.BPMNPlane
 * JD-Core Version:    0.6.2
 */