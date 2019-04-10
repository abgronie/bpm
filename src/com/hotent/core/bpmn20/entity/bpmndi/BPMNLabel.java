/*    */ package com.hotent.core.bpmn20.entity.bpmndi;
/*    */ 
/*    */ import com.hotent.core.bpmn20.entity.omgdi.Label;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="BPMNLabel")
/*    */ public class BPMNLabel extends Label
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName labelStyle;
/*    */ 
/*    */   public QName getLabelStyle()
/*    */   {
/* 56 */     return this.labelStyle;
/*    */   }
/*    */ 
/*    */   public void setLabelStyle(QName value)
/*    */   {
/* 68 */     this.labelStyle = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.BPMNLabel
 * JD-Core Version:    0.6.2
 */