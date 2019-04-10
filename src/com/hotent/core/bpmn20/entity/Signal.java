/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tSignal")
/*    */ public class Signal extends RootElement
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String name;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected QName structureRef;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 57 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String value)
/*    */   {
/* 69 */     this.name = value;
/*    */   }
/*    */ 
/*    */   public QName getStructureRef()
/*    */   {
/* 81 */     return this.structureRef;
/*    */   }
/*    */ 
/*    */   public void setStructureRef(QName value)
/*    */   {
/* 93 */     this.structureRef = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Signal
 * JD-Core Version:    0.6.2
 */