/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tCallableElement", propOrder={"supportedInterfaceRef", "ioSpecification", "ioBinding"})
/*     */ @XmlSeeAlso({Process.class, GlobalTask.class})
/*     */ public class CallableElement extends RootElement
/*     */ {
/*     */   protected List<QName> supportedInterfaceRef;
/*     */   protected InputOutputSpecification ioSpecification;
/*     */   protected List<InputOutputBinding> ioBinding;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public List<QName> getSupportedInterfaceRef()
/*     */   {
/*  87 */     if (this.supportedInterfaceRef == null) {
/*  88 */       this.supportedInterfaceRef = new ArrayList();
/*     */     }
/*  90 */     return this.supportedInterfaceRef;
/*     */   }
/*     */ 
/*     */   public InputOutputSpecification getIoSpecification()
/*     */   {
/* 102 */     return this.ioSpecification;
/*     */   }
/*     */ 
/*     */   public void setIoSpecification(InputOutputSpecification value)
/*     */   {
/* 114 */     this.ioSpecification = value;
/*     */   }
/*     */ 
/*     */   public List<InputOutputBinding> getIoBinding()
/*     */   {
/* 140 */     if (this.ioBinding == null) {
/* 141 */       this.ioBinding = new ArrayList();
/*     */     }
/* 143 */     return this.ioBinding;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 155 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 167 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CallableElement
 * JD-Core Version:    0.6.2
 */