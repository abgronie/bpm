/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlIDREF;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tSequenceFlow", propOrder={"conditionExpression"})
/*     */ public class SequenceFlow extends FlowElement
/*     */ {
/*     */   protected Expression conditionExpression;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object sourceRef;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlIDREF
/*     */   @XmlSchemaType(name="IDREF")
/*     */   protected Object targetRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isImmediate;
/*     */ 
/*     */   public Expression getConditionExpression()
/*     */   {
/*  71 */     return this.conditionExpression;
/*     */   }
/*     */ 
/*     */   public void setConditionExpression(Expression value)
/*     */   {
/*  83 */     this.conditionExpression = value;
/*     */   }
/*     */ 
/*     */   public Object getSourceRef()
/*     */   {
/*  95 */     return this.sourceRef;
/*     */   }
/*     */ 
/*     */   public void setSourceRef(Object value)
/*     */   {
/* 107 */     this.sourceRef = value;
/*     */   }
/*     */ 
/*     */   public Object getTargetRef()
/*     */   {
/* 119 */     return this.targetRef;
/*     */   }
/*     */ 
/*     */   public void setTargetRef(Object value)
/*     */   {
/* 131 */     this.targetRef = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsImmediate()
/*     */   {
/* 143 */     return this.isImmediate;
/*     */   }
/*     */ 
/*     */   public void setIsImmediate(Boolean value)
/*     */   {
/* 155 */     this.isImmediate = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.SequenceFlow
 * JD-Core Version:    0.6.2
 */