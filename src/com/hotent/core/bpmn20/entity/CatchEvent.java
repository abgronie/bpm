/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tCatchEvent", propOrder={"dataOutput", "dataOutputAssociation", "outputSet", "eventDefinition", "eventDefinitionRef"})
/*     */ @XmlSeeAlso({StartEvent.class, IntermediateCatchEvent.class, BoundaryEvent.class})
/*     */ public abstract class CatchEvent extends Event
/*     */ {
/*     */   protected List<DataOutput> dataOutput;
/*     */   protected List<DataOutputAssociation> dataOutputAssociation;
/*     */   protected OutputSet outputSet;
/*     */ 
/*     */   @XmlElementRef(name="eventDefinition", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends EventDefinition>> eventDefinition;
/*     */   protected List<QName> eventDefinitionRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean parallelMultiple;
/*     */ 
/*     */   public List<DataOutput> getDataOutput()
/*     */   {
/*  97 */     if (this.dataOutput == null) {
/*  98 */       this.dataOutput = new ArrayList();
/*     */     }
/* 100 */     return this.dataOutput;
/*     */   }
/*     */ 
/*     */   public List<DataOutputAssociation> getDataOutputAssociation()
/*     */   {
/* 126 */     if (this.dataOutputAssociation == null) {
/* 127 */       this.dataOutputAssociation = new ArrayList();
/*     */     }
/* 129 */     return this.dataOutputAssociation;
/*     */   }
/*     */ 
/*     */   public OutputSet getOutputSet()
/*     */   {
/* 141 */     return this.outputSet;
/*     */   }
/*     */ 
/*     */   public void setOutputSet(OutputSet value)
/*     */   {
/* 153 */     this.outputSet = value;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends EventDefinition>> getEventDefinition()
/*     */   {
/* 189 */     if (this.eventDefinition == null) {
/* 190 */       this.eventDefinition = new ArrayList();
/*     */     }
/* 192 */     return this.eventDefinition;
/*     */   }
/*     */ 
/*     */   public List<QName> getEventDefinitionRef()
/*     */   {
/* 218 */     if (this.eventDefinitionRef == null) {
/* 219 */       this.eventDefinitionRef = new ArrayList();
/*     */     }
/* 221 */     return this.eventDefinitionRef;
/*     */   }
/*     */ 
/*     */   public boolean isParallelMultiple()
/*     */   {
/* 233 */     if (this.parallelMultiple == null) {
/* 234 */       return false;
/*     */     }
/* 236 */     return this.parallelMultiple.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setParallelMultiple(Boolean value)
/*     */   {
/* 249 */     this.parallelMultiple = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CatchEvent
 * JD-Core Version:    0.6.2
 */