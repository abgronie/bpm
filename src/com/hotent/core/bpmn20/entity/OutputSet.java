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
/*     */ @XmlType(name="tOutputSet", propOrder={"dataOutputRefs", "optionalOutputRefs", "whileExecutingOutputRefs", "inputSetRefs"})
/*     */ public class OutputSet extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlElementRef(name="dataOutputRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> dataOutputRefs;
/*     */ 
/*     */   @XmlElementRef(name="optionalOutputRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> optionalOutputRefs;
/*     */ 
/*     */   @XmlElementRef(name="whileExecutingOutputRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> whileExecutingOutputRefs;
/*     */ 
/*     */   @XmlElementRef(name="inputSetRefs", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> inputSetRefs;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public List<JAXBElement<Object>> getDataOutputRefs()
/*     */   {
/*  90 */     if (this.dataOutputRefs == null) {
/*  91 */       this.dataOutputRefs = new ArrayList();
/*     */     }
/*  93 */     return this.dataOutputRefs;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getOptionalOutputRefs()
/*     */   {
/* 119 */     if (this.optionalOutputRefs == null) {
/* 120 */       this.optionalOutputRefs = new ArrayList();
/*     */     }
/* 122 */     return this.optionalOutputRefs;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getWhileExecutingOutputRefs()
/*     */   {
/* 148 */     if (this.whileExecutingOutputRefs == null) {
/* 149 */       this.whileExecutingOutputRefs = new ArrayList();
/*     */     }
/* 151 */     return this.whileExecutingOutputRefs;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getInputSetRefs()
/*     */   {
/* 177 */     if (this.inputSetRefs == null) {
/* 178 */       this.inputSetRefs = new ArrayList();
/*     */     }
/* 180 */     return this.inputSetRefs;
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
 * Qualified Name:     com.hotent.core.bpmn20.entity.OutputSet
 * JD-Core Version:    0.6.2
 */