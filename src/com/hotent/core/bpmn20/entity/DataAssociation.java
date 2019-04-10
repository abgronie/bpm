/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlIDREF;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tDataAssociation", propOrder={"sourceRef", "targetRef", "transformation", "assignment"})
/*     */ @XmlSeeAlso({DataInputAssociation.class, DataOutputAssociation.class})
/*     */ public class DataAssociation extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlElementRef(name="sourceRef", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> sourceRef;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object targetRef;
/*     */   protected FormalExpression transformation;
/*     */   protected List<Assignment> assignment;
/*     */ 
/*     */   public List<JAXBElement<Object>> getSourceRef()
/*     */   {
/*  94 */     if (this.sourceRef == null) {
/*  95 */       this.sourceRef = new ArrayList();
/*     */     }
/*  97 */     return this.sourceRef;
/*     */   }
/*     */ 
/*     */   public Object getTargetRef()
/*     */   {
/* 109 */     return this.targetRef;
/*     */   }
/*     */ 
/*     */   public void setTargetRef(Object value)
/*     */   {
/* 121 */     this.targetRef = value;
/*     */   }
/*     */ 
/*     */   public FormalExpression getTransformation()
/*     */   {
/* 133 */     return this.transformation;
/*     */   }
/*     */ 
/*     */   public void setTransformation(FormalExpression value)
/*     */   {
/* 145 */     this.transformation = value;
/*     */   }
/*     */ 
/*     */   public List<Assignment> getAssignment()
/*     */   {
/* 171 */     if (this.assignment == null) {
/* 172 */       this.assignment = new ArrayList();
/*     */     }
/* 174 */     return this.assignment;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.DataAssociation
 * JD-Core Version:    0.6.2
 */