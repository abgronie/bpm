/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tDataObject", propOrder={"dataState"})
/*     */ public class DataObject extends FlowElement
/*     */ {
/*     */   protected DataState dataState;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName itemSubjectRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isCollection;
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
/*     */   public QName getItemSubjectRef()
/*     */   {
/*  87 */     return this.itemSubjectRef;
/*     */   }
/*     */ 
/*     */   public void setItemSubjectRef(QName value)
/*     */   {
/*  99 */     this.itemSubjectRef = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsCollection()
/*     */   {
/* 111 */     if (this.isCollection == null) {
/* 112 */       return false;
/*     */     }
/* 114 */     return this.isCollection.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsCollection(Boolean value)
/*     */   {
/* 127 */     this.isCollection = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.DataObject
 * JD-Core Version:    0.6.2
 */