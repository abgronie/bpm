/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tProcess", propOrder={"auditing", "monitoring", "property", "laneSet", "flowElement", "artifact", "resourceRole", "correlationSubscription", "supports"})
/*     */ public class Process extends CallableElement
/*     */ {
/*     */   protected Auditing auditing;
/*     */   protected Monitoring monitoring;
/*     */   protected List<Property> property;
/*     */   protected List<LaneSet> laneSet;
/*     */ 
/*     */   @XmlElementRef(name="flowElement", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends FlowElement>> flowElement;
/*     */ 
/*     */   @XmlElementRef(name="artifact", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends Artifact>> artifact;
/*     */ 
/*     */   @XmlElementRef(name="resourceRole", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends ResourceRole>> resourceRole;
/*     */   protected List<CorrelationSubscription> correlationSubscription;
/*     */   protected List<QName> supports;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected ProcessType processType;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isClosed;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isExecutable;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName definitionalCollaborationRef;
/*     */ 
/*     */   public Auditing getAuditing()
/*     */   {
/* 100 */     return this.auditing;
/*     */   }
/*     */ 
/*     */   public void setAuditing(Auditing value)
/*     */   {
/* 112 */     this.auditing = value;
/*     */   }
/*     */ 
/*     */   public Monitoring getMonitoring()
/*     */   {
/* 124 */     return this.monitoring;
/*     */   }
/*     */ 
/*     */   public void setMonitoring(Monitoring value)
/*     */   {
/* 136 */     this.monitoring = value;
/*     */   }
/*     */ 
/*     */   public List<Property> getProperty()
/*     */   {
/* 162 */     if (this.property == null) {
/* 163 */       this.property = new ArrayList();
/*     */     }
/* 165 */     return this.property;
/*     */   }
/*     */ 
/*     */   public List<LaneSet> getLaneSet()
/*     */   {
/* 191 */     if (this.laneSet == null) {
/* 192 */       this.laneSet = new ArrayList();
/*     */     }
/* 194 */     return this.laneSet;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends FlowElement>> getFlowElement()
/*     */   {
/* 251 */     if (this.flowElement == null) {
/* 252 */       this.flowElement = new ArrayList();
/*     */     }
/* 254 */     return this.flowElement;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends Artifact>> getArtifact()
/*     */   {
/* 283 */     if (this.artifact == null) {
/* 284 */       this.artifact = new ArrayList();
/*     */     }
/* 286 */     return this.artifact;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends ResourceRole>> getResourceRole()
/*     */   {
/* 315 */     if (this.resourceRole == null) {
/* 316 */       this.resourceRole = new ArrayList();
/*     */     }
/* 318 */     return this.resourceRole;
/*     */   }
/*     */ 
/*     */   public List<CorrelationSubscription> getCorrelationSubscription()
/*     */   {
/* 344 */     if (this.correlationSubscription == null) {
/* 345 */       this.correlationSubscription = new ArrayList();
/*     */     }
/* 347 */     return this.correlationSubscription;
/*     */   }
/*     */ 
/*     */   public List<QName> getSupports()
/*     */   {
/* 373 */     if (this.supports == null) {
/* 374 */       this.supports = new ArrayList();
/*     */     }
/* 376 */     return this.supports;
/*     */   }
/*     */ 
/*     */   public ProcessType getProcessType()
/*     */   {
/* 388 */     if (this.processType == null) {
/* 389 */       return ProcessType.NONE;
/*     */     }
/* 391 */     return this.processType;
/*     */   }
/*     */ 
/*     */   public void setProcessType(ProcessType value)
/*     */   {
/* 404 */     this.processType = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsClosed()
/*     */   {
/* 416 */     if (this.isClosed == null) {
/* 417 */       return false;
/*     */     }
/* 419 */     return this.isClosed.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsClosed(Boolean value)
/*     */   {
/* 432 */     this.isClosed = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsExecutable()
/*     */   {
/* 444 */     return this.isExecutable;
/*     */   }
/*     */ 
/*     */   public void setIsExecutable(Boolean value)
/*     */   {
/* 456 */     this.isExecutable = value;
/*     */   }
/*     */ 
/*     */   public QName getDefinitionalCollaborationRef()
/*     */   {
/* 468 */     return this.definitionalCollaborationRef;
/*     */   }
/*     */ 
/*     */   public void setDefinitionalCollaborationRef(QName value)
/*     */   {
/* 480 */     this.definitionalCollaborationRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Process
 * JD-Core Version:    0.6.2
 */