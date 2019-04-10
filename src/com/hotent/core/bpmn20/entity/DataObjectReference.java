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
/*     */ @XmlType(name="tDataObjectReference", propOrder={"dataState"})
/*     */ public class DataObjectReference extends FlowElement
/*     */ {
/*     */   protected DataState dataState;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName itemSubjectRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object dataObjectRef;
/*     */ 
/*     */   public DataState getDataState()
/*     */   {
/*  67 */     return this.dataState;
/*     */   }
/*     */ 
/*     */   public void setDataState(DataState value)
/*     */   {
/*  79 */     this.dataState = value;
/*     */   }
/*     */ 
/*     */   public QName getItemSubjectRef()
/*     */   {
/*  91 */     return this.itemSubjectRef;
/*     */   }
/*     */ 
/*     */   public void setItemSubjectRef(QName value)
/*     */   {
/* 103 */     this.itemSubjectRef = value;
/*     */   }
/*     */ 
/*     */   public Object getDataObjectRef()
/*     */   {
/* 115 */     return this.dataObjectRef;
/*     */   }
/*     */ 
/*     */   public void setDataObjectRef(Object value)
/*     */   {
/* 127 */     this.dataObjectRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.DataObjectReference
 * JD-Core Version:    0.6.2
 */