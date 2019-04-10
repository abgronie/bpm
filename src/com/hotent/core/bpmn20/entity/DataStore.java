/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tDataStore", propOrder={"dataState"})
/*     */ public class DataStore extends RootElement
/*     */ {
/*     */   protected DataState dataState;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected BigInteger capacity;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isUnlimited;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName itemSubjectRef;
/*     */ 
/*     */   public DataState getDataState()
/*     */   {
/*  70 */     return this.dataState;
/*     */   }
/*     */ 
/*     */   public void setDataState(DataState value)
/*     */   {
/*  82 */     this.dataState = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  94 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 106 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public BigInteger getCapacity()
/*     */   {
/* 118 */     return this.capacity;
/*     */   }
/*     */ 
/*     */   public void setCapacity(BigInteger value)
/*     */   {
/* 130 */     this.capacity = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsUnlimited()
/*     */   {
/* 142 */     if (this.isUnlimited == null) {
/* 143 */       return true;
/*     */     }
/* 145 */     return this.isUnlimited.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsUnlimited(Boolean value)
/*     */   {
/* 158 */     this.isUnlimited = value;
/*     */   }
/*     */ 
/*     */   public QName getItemSubjectRef()
/*     */   {
/* 170 */     return this.itemSubjectRef;
/*     */   }
/*     */ 
/*     */   public void setItemSubjectRef(QName value)
/*     */   {
/* 182 */     this.itemSubjectRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.DataStore
 * JD-Core Version:    0.6.2
 */