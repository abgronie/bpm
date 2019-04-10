/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tInputSet", propOrder={"dataInputRefs", "optionalInputRefs", "whileExecutingInputRefs", "outputSetRefs"})
/*     */ public class InputSet extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlElementRef(name="dataInputRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> dataInputRefs;
/*     */ 
/*     */   @XmlElementRef(name="optionalInputRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> optionalInputRefs;
/*     */ 
/*     */   @XmlElementRef(name="whileExecutingInputRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> whileExecutingInputRefs;
/*     */ 
/*     */   @XmlElementRef(name="outputSetRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> outputSetRefs;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public List<JAXBElement<Object>> getDataInputRefs()
/*     */   {
/*  90 */     if (this.dataInputRefs == null) {
/*  91 */       this.dataInputRefs = new ArrayList();
/*     */     }
/*  93 */     return this.dataInputRefs;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getOptionalInputRefs()
/*     */   {
/* 119 */     if (this.optionalInputRefs == null) {
/* 120 */       this.optionalInputRefs = new ArrayList();
/*     */     }
/* 122 */     return this.optionalInputRefs;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getWhileExecutingInputRefs()
/*     */   {
/* 148 */     if (this.whileExecutingInputRefs == null) {
/* 149 */       this.whileExecutingInputRefs = new ArrayList();
/*     */     }
/* 151 */     return this.whileExecutingInputRefs;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getOutputSetRefs()
/*     */   {
/* 177 */     if (this.outputSetRefs == null) {
/* 178 */       this.outputSetRefs = new ArrayList();
/*     */     }
/* 180 */     return this.outputSetRefs;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 192 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 204 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.InputSet
 * JD-Core Version:    0.6.2
 */