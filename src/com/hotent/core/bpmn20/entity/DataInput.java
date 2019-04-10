/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tDataInput", propOrder={"dataState"})
/*     */ public class DataInput extends BaseElement
/*     */ {
/*     */   protected DataState dataState;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName itemSubjectRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isCollection;
/*     */ 
/*     */   public DataState getDataState()
/*     */   {
/*  66 */     return this.dataState;
/*     */   }
/*     */ 
/*     */   public void setDataState(DataState value)
/*     */   {
/*  78 */     this.dataState = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  90 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 102 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getItemSubjectRef()
/*     */   {
/* 114 */     return this.itemSubjectRef;
/*     */   }
/*     */ 
/*     */   public void setItemSubjectRef(QName value)
/*     */   {
/* 126 */     this.itemSubjectRef = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsCollection()
/*     */   {
/* 138 */     if (this.isCollection == null) {
/* 139 */       return false;
/*     */     }
/* 141 */     return this.isCollection.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsCollection(Boolean value)
/*     */   {
/* 154 */     this.isCollection = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.DataInput
 * JD-Core Version:    0.6.2
 */