/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlIDREF;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tInputOutputBinding")
/*     */ public class InputOutputBinding extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName operationRef;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object inputDataRef;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object outputDataRef;
/*     */ 
/*     */   public QName getOperationRef()
/*     */   {
/*  66 */     return this.operationRef;
/*     */   }
/*     */ 
/*     */   public void setOperationRef(QName value)
/*     */   {
/*  78 */     this.operationRef = value;
/*     */   }
/*     */ 
/*     */   public Object getInputDataRef()
/*     */   {
/*  90 */     return this.inputDataRef;
/*     */   }
/*     */ 
/*     */   public void setInputDataRef(Object value)
/*     */   {
/* 102 */     this.inputDataRef = value;
/*     */   }
/*     */ 
/*     */   public Object getOutputDataRef()
/*     */   {
/* 114 */     return this.outputDataRef;
/*     */   }
/*     */ 
/*     */   public void setOutputDataRef(Object value)
/*     */   {
/* 126 */     this.outputDataRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.InputOutputBinding
 * JD-Core Version:    0.6.2
 */