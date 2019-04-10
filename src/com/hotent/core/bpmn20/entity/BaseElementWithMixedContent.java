/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAnyAttribute;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRefs;
/*     */ import javax.xml.bind.annotation.XmlID;
/*     */ import javax.xml.bind.annotation.XmlMixed;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
/*     */ import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tBaseElementWithMixedContent", propOrder={"content"})
/*     */ @XmlSeeAlso({Expression.class})
/*     */ public abstract class BaseElementWithMixedContent
/*     */ {
/*     */ 
/*     */   @XmlElementRefs({@javax.xml.bind.annotation.XmlElementRef(name="documentation", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=javax.xml.bind.JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="extensionElements", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=javax.xml.bind.JAXBElement.class)})
/*     */   @XmlMixed
/*     */   protected List<Serializable> content;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
/*     */   @XmlID
/*     */   @XmlSchemaType(name="ID")
/*     */   protected String id;
/*     */ 
/*     */   @XmlAnyAttribute
/*  75 */   private Map<QName, String> otherAttributes = new HashMap();
/*     */ 
/*     */   public List<Serializable> getContent()
/*     */   {
/* 103 */     if (this.content == null) {
/* 104 */       this.content = new ArrayList();
/*     */     }
/* 106 */     return this.content;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 118 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String value)
/*     */   {
/* 130 */     this.id = value;
/*     */   }
/*     */ 
/*     */   public Map<QName, String> getOtherAttributes()
/*     */   {
/* 148 */     return this.otherAttributes;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.BaseElementWithMixedContent
 * JD-Core Version:    0.6.2
 */