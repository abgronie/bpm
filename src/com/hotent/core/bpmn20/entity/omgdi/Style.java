/*    */ package com.hotent.core.bpmn20.entity.omgdi;
/*    */ 
/*    */ import com.hotent.core.bpmn20.entity.bpmndi.BPMNLabelStyle;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlID;
/*    */ import javax.xml.bind.annotation.XmlSchemaType;
/*    */ import javax.xml.bind.annotation.XmlSeeAlso;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
/*    */ import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="Style")
/*    */ @XmlSeeAlso({BPMNLabelStyle.class})
/*    */ public abstract class Style
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
/*    */   @XmlID
/*    */   @XmlSchemaType(name="ID")
/*    */   protected String id;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 63 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String value)
/*    */   {
/* 75 */     this.id = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.Style
 * JD-Core Version:    0.6.2
 */