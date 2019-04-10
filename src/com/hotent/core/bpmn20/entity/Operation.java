/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tOperation", propOrder={"inMessageRef", "outMessageRef", "errorRef"})
/*     */ public class Operation extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected QName inMessageRef;
/*     */   protected QName outMessageRef;
/*     */   protected List<QName> errorRef;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName implementationRef;
/*     */ 
/*     */   public QName getInMessageRef()
/*     */   {
/*  73 */     return this.inMessageRef;
/*     */   }
/*     */ 
/*     */   public void setInMessageRef(QName value)
/*     */   {
/*  85 */     this.inMessageRef = value;
/*     */   }
/*     */ 
/*     */   public QName getOutMessageRef()
/*     */   {
/*  97 */     return this.outMessageRef;
/*     */   }
/*     */ 
/*     */   public void setOutMessageRef(QName value)
/*     */   {
/* 109 */     this.outMessageRef = value;
/*     */   }
/*     */ 
/*     */   public List<QName> getErrorRef()
/*     */   {
/* 135 */     if (this.errorRef == null) {
/* 136 */       this.errorRef = new ArrayList();
/*     */     }
/* 138 */     return this.errorRef;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 150 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 162 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getImplementationRef()
/*     */   {
/* 174 */     return this.implementationRef;
/*     */   }
/*     */ 
/*     */   public void setImplementationRef(QName value)
/*     */   {
/* 186 */     this.implementationRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Operation
 * JD-Core Version:    0.6.2
 */