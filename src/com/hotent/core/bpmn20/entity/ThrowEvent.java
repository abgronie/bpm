/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tThrowEvent", propOrder={"dataInput", "dataInputAssociation", "inputSet", "eventDefinition", "eventDefinitionRef"})
/*     */ @XmlSeeAlso({IntermediateThrowEvent.class, ImplicitThrowEvent.class, EndEvent.class})
/*     */ public abstract class ThrowEvent extends Event
/*     */ {
/*     */   protected List<DataInput> dataInput;
/*     */   protected List<DataInputAssociation> dataInputAssociation;
/*     */   protected InputSet inputSet;
/*     */ 
/*     */   @XmlElementRef(name="eventDefinition", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends EventDefinition>> eventDefinition;
/*     */   protected List<QName> eventDefinitionRef;
/*     */ 
/*     */   public List<DataInput> getDataInput()
/*     */   {
/*  93 */     if (this.dataInput == null) {
/*  94 */       this.dataInput = new ArrayList();
/*     */     }
/*  96 */     return this.dataInput;
/*     */   }
/*     */ 
/*     */   public List<DataInputAssociation> getDataInputAssociation()
/*     */   {
/* 122 */     if (this.dataInputAssociation == null) {
/* 123 */       this.dataInputAssociation = new ArrayList();
/*     */     }
/* 125 */     return this.dataInputAssociation;
/*     */   }
/*     */ 
/*     */   public InputSet getInputSet()
/*     */   {
/* 137 */     return this.inputSet;
/*     */   }
/*     */ 
/*     */   public void setInputSet(InputSet value)
/*     */   {
/* 149 */     this.inputSet = value;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends EventDefinition>> getEventDefinition()
/*     */   {
/* 185 */     if (this.eventDefinition == null) {
/* 186 */       this.eventDefinition = new ArrayList();
/*     */     }
/* 188 */     return this.eventDefinition;
/*     */   }
/*     */ 
/*     */   public List<QName> getEventDefinitionRef()
/*     */   {
/* 214 */     if (this.eventDefinitionRef == null) {
/* 215 */       this.eventDefinitionRef = new ArrayList();
/*     */     }
/* 217 */     return this.eventDefinitionRef;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ThrowEvent
 * JD-Core Version:    0.6.2
 */