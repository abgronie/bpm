/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tDataState")
/*    */ public class DataState extends BaseElement
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String name;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 53 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String value)
/*    */   {
/* 65 */     this.name = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.DataState
 * JD-Core Version:    0.6.2
 */