/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tLinkEventDefinition", propOrder={"source", "target"})
/*     */ public class LinkEventDefinition extends EventDefinition
/*     */ {
/*     */   protected List<QName> source;
/*     */   protected QName target;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String name;
/*     */ 
/*     */   public List<QName> getSource()
/*     */   {
/*  79 */     if (this.source == null) {
/*  80 */       this.source = new ArrayList();
/*     */     }
/*  82 */     return this.source;
/*     */   }
/*     */ 
/*     */   public QName getTarget()
/*     */   {
/*  94 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void setTarget(QName value)
/*     */   {
/* 106 */     this.target = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 118 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 130 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.LinkEventDefinition
 * JD-Core Version:    0.6.2
 */