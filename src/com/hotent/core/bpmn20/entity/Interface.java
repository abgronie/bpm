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
/*     */ @XmlType(name="tInterface", propOrder={"operation"})
/*     */ public class Interface extends RootElement
/*     */ {
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<Operation> operation;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName implementationRef;
/*     */ 
/*     */   public List<Operation> getOperation()
/*     */   {
/*  81 */     if (this.operation == null) {
/*  82 */       this.operation = new ArrayList();
/*     */     }
/*  84 */     return this.operation;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  96 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 108 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getImplementationRef()
/*     */   {
/* 120 */     return this.implementationRef;
/*     */   }
/*     */ 
/*     */   public void setImplementationRef(QName value)
/*     */   {
/* 132 */     this.implementationRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Interface
 * JD-Core Version:    0.6.2
 */