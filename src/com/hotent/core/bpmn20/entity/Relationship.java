/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tRelationship", propOrder={"source", "target"})
/*     */ public class Relationship extends BaseElement
/*     */ {
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<QName> source;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<QName> target;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String type;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected RelationshipDirection direction;
/*     */ 
/*     */   public List<QName> getSource()
/*     */   {
/*  85 */     if (this.source == null) {
/*  86 */       this.source = new ArrayList();
/*     */     }
/*  88 */     return this.source;
/*     */   }
/*     */ 
/*     */   public List<QName> getTarget()
/*     */   {
/* 114 */     if (this.target == null) {
/* 115 */       this.target = new ArrayList();
/*     */     }
/* 117 */     return this.target;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 129 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String value)
/*     */   {
/* 141 */     this.type = value;
/*     */   }
/*     */ 
/*     */   public RelationshipDirection getDirection()
/*     */   {
/* 153 */     return this.direction;
/*     */   }
/*     */ 
/*     */   public void setDirection(RelationshipDirection value)
/*     */   {
/* 165 */     this.direction = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Relationship
 * JD-Core Version:    0.6.2
 */