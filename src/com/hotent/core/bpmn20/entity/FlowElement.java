/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tFlowElement", propOrder={"auditing", "monitoring", "categoryValueRef"})
/*     */ @XmlSeeAlso({SequenceFlow.class, DataObject.class, DataObjectReference.class, DataStoreReference.class, FlowNode.class})
/*     */ public abstract class FlowElement extends BaseElement
/*     */ {
/*     */   protected Auditing auditing;
/*     */   protected Monitoring monitoring;
/*     */   protected List<QName> categoryValueRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public Auditing getAuditing()
/*     */   {
/*  76 */     return this.auditing;
/*     */   }
/*     */ 
/*     */   public void setAuditing(Auditing value)
/*     */   {
/*  88 */     this.auditing = value;
/*     */   }
/*     */ 
/*     */   public Monitoring getMonitoring()
/*     */   {
/* 100 */     return this.monitoring;
/*     */   }
/*     */ 
/*     */   public void setMonitoring(Monitoring value)
/*     */   {
/* 112 */     this.monitoring = value;
/*     */   }
/*     */ 
/*     */   public List<QName> getCategoryValueRef()
/*     */   {
/* 138 */     if (this.categoryValueRef == null) {
/* 139 */       this.categoryValueRef = new ArrayList();
/*     */     }
/* 141 */     return this.categoryValueRef;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 153 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 165 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.FlowElement
 * JD-Core Version:    0.6.2
 */