/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tError")
/*     */ public class Error extends RootElement
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String errorCode;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName structureRef;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  60 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  72 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public String getErrorCode()
/*     */   {
/*  84 */     return this.errorCode;
/*     */   }
/*     */ 
/*     */   public void setErrorCode(String value)
/*     */   {
/*  96 */     this.errorCode = value;
/*     */   }
/*     */ 
/*     */   public QName getStructureRef()
/*     */   {
/* 108 */     return this.structureRef;
/*     */   }
/*     */ 
/*     */   public void setStructureRef(QName value)
/*     */   {
/* 120 */     this.structureRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Error
 * JD-Core Version:    0.6.2
 */