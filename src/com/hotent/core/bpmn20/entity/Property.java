/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tProperty", propOrder={"dataState"})
/*     */ public class Property extends BaseElement
/*     */ {
/*     */   protected DataState dataState;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName itemSubjectRef;
/*     */ 
/*     */   public DataState getDataState()
/*     */   {
/*  63 */     return this.dataState;
/*     */   }
/*     */ 
/*     */   public void setDataState(DataState value)
/*     */   {
/*  75 */     this.dataState = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  87 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  99 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getItemSubjectRef()
/*     */   {
/* 111 */     return this.itemSubjectRef;
/*     */   }
/*     */ 
/*     */   public void setItemSubjectRef(QName value)
/*     */   {
/* 123 */     this.itemSubjectRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Property
 * JD-Core Version:    0.6.2
 */