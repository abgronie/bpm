/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tImport")
/*     */ public class Import
/*     */ {
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlSchemaType(name="anyURI")
/*     */   protected String namespace;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String location;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlSchemaType(name="anyURI")
/*     */   protected String importType;
/*     */ 
/*     */   public String getNamespace()
/*     */   {
/*  59 */     return this.namespace;
/*     */   }
/*     */ 
/*     */   public void setNamespace(String value)
/*     */   {
/*  71 */     this.namespace = value;
/*     */   }
/*     */ 
/*     */   public String getLocation()
/*     */   {
/*  83 */     return this.location;
/*     */   }
/*     */ 
/*     */   public void setLocation(String value)
/*     */   {
/*  95 */     this.location = value;
/*     */   }
/*     */ 
/*     */   public String getImportType()
/*     */   {
/* 107 */     return this.importType;
/*     */   }
/*     */ 
/*     */   public void setImportType(String value)
/*     */   {
/* 119 */     this.importType = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Import
 * JD-Core Version:    0.6.2
 */