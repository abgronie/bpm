/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlIDREF;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tActivity", propOrder={"ioSpecification", "property", "dataInputAssociation", "dataOutputAssociation", "resourceRole", "loopCharacteristics"})
/*     */ @XmlSeeAlso({SubProcess.class, Task.class, CallActivity.class})
/*     */ public abstract class Activity extends FlowNode
/*     */ {
/*     */   protected InputOutputSpecification ioSpecification;
/*     */   protected List<Property> property;
/*     */   protected List<DataInputAssociation> dataInputAssociation;
/*     */   protected List<DataOutputAssociation> dataOutputAssociation;
/*     */ 
/*     */   @XmlElementRef(name="resourceRole", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends ResourceRole>> resourceRole;
/*     */ 
/*     */   @XmlElementRef(name="loopCharacteristics", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected JAXBElement<? extends LoopCharacteristics> loopCharacteristics;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isForCompensation;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected BigInteger startQuantity;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected BigInteger completionQuantity;
/*     */ 
/*     */   @XmlAttribute(name="default")
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object _default;
/*     */ 
/*     */   public InputOutputSpecification getIoSpecification()
/*     */   {
/* 100 */     return this.ioSpecification;
/*     */   }
/*     */ 
/*     */   public void setIoSpecification(InputOutputSpecification value)
/*     */   {
/* 112 */     this.ioSpecification = value;
/*     */   }
/*     */ 
/*     */   public List<Property> getProperty()
/*     */   {
/* 138 */     if (this.property == null) {
/* 139 */       this.property = new ArrayList();
/*     */     }
/* 141 */     return this.property;
/*     */   }
/*     */ 
/*     */   public List<DataInputAssociation> getDataInputAssociation()
/*     */   {
/* 167 */     if (this.dataInputAssociation == null) {
/* 168 */       this.dataInputAssociation = new ArrayList();
/*     */     }
/* 170 */     return this.dataInputAssociation;
/*     */   }
/*     */ 
/*     */   public List<DataOutputAssociation> getDataOutputAssociation()
/*     */   {
/* 196 */     if (this.dataOutputAssociation == null) {
/* 197 */       this.dataOutputAssociation = new ArrayList();
/*     */     }
/* 199 */     return this.dataOutputAssociation;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends ResourceRole>> getResourceRole()
/*     */   {
/* 228 */     if (this.resourceRole == null) {
/* 229 */       this.resourceRole = new ArrayList();
/*     */     }
/* 231 */     return this.resourceRole;
/*     */   }
/*     */ 
/*     */   public JAXBElement<? extends LoopCharacteristics> getLoopCharacteristics()
/*     */   {
/* 245 */     return this.loopCharacteristics;
/*     */   }
/*     */ 
/*     */   public void setLoopCharacteristics(JAXBElement<? extends LoopCharacteristics> value)
/*     */   {
/* 259 */     this.loopCharacteristics = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsForCompensation()
/*     */   {
/* 271 */     if (this.isForCompensation == null) {
/* 272 */       return false;
/*     */     }
/* 274 */     return this.isForCompensation.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsForCompensation(Boolean value)
/*     */   {
/* 287 */     this.isForCompensation = value;
/*     */   }
/*     */ 
/*     */   public BigInteger getStartQuantity()
/*     */   {
/* 299 */     if (this.startQuantity == null) {
/* 300 */       return new BigInteger("1");
/*     */     }
/* 302 */     return this.startQuantity;
/*     */   }
/*     */ 
/*     */   public void setStartQuantity(BigInteger value)
/*     */   {
/* 315 */     this.startQuantity = value;
/*     */   }
/*     */ 
/*     */   public BigInteger getCompletionQuantity()
/*     */   {
/* 327 */     if (this.completionQuantity == null) {
/* 328 */       return new BigInteger("1");
/*     */     }
/* 330 */     return this.completionQuantity;
/*     */   }
/*     */ 
/*     */   public void setCompletionQuantity(BigInteger value)
/*     */   {
/* 343 */     this.completionQuantity = value;
/*     */   }
/*     */ 
/*     */   public Object getDefault()
/*     */   {
/* 355 */     return this._default;
/*     */   }
/*     */ 
/*     */   public void setDefault(Object value)
/*     */   {
/* 367 */     this._default = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Activity
 * JD-Core Version:    0.6.2
 */