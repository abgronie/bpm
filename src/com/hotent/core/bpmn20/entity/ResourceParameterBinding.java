/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tResourceParameterBinding", propOrder={"expression"})
/*     */ public class ResourceParameterBinding extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlElementRef(name="expression", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected JAXBElement<? extends Expression> expression;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected QName parameterRef;
/*     */ 
/*     */   public JAXBElement<? extends Expression> getExpression()
/*     */   {
/*  64 */     return this.expression;
/*     */   }
/*     */ 
/*     */   public void setExpression(JAXBElement<? extends Expression> value)
/*     */   {
/*  77 */     this.expression = value;
/*     */   }
/*     */ 
/*     */   public QName getParameterRef()
/*     */   {
/*  89 */     return this.parameterRef;
/*     */   }
/*     */ 
/*     */   public void setParameterRef(QName value)
/*     */   {
/* 101 */     this.parameterRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ResourceParameterBinding
 * JD-Core Version:    0.6.2
 */