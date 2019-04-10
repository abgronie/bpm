/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tResourceParameter")
/*     */ public class ResourceParameter extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName type;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isRequired;
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
/*     */   public QName getType()
/*     */   {
/*  84 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(QName value)
/*     */   {
/*  96 */     this.type = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsRequired()
/*     */   {
/* 108 */     return this.isRequired;
/*     */   }
/*     */ 
/*     */   public void setIsRequired(Boolean value)
/*     */   {
/* 120 */     this.isRequired = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ResourceParameter
 * JD-Core Version:    0.6.2
 */