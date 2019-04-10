/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tSubProcess", propOrder={"laneSet", "flowElement", "artifact"})
/*     */ @XmlSeeAlso({AdHocSubProcess.class, Transaction.class})
/*     */ public class SubProcess extends Activity
/*     */ {
/*     */   protected List<LaneSet> laneSet;
/*     */ 
/*     */   @XmlElementRef(name="flowElement", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends FlowElement>> flowElement;
/*     */ 
/*     */   @XmlElementRef(name="artifact", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends Artifact>> artifact;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean triggeredByEvent;
/*     */ 
/*     */   public List<LaneSet> getLaneSet()
/*     */   {
/*  90 */     if (this.laneSet == null) {
/*  91 */       this.laneSet = new ArrayList();
/*     */     }
/*  93 */     return this.laneSet;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends FlowElement>> getFlowElement()
/*     */   {
/* 150 */     if (this.flowElement == null) {
/* 151 */       this.flowElement = new ArrayList();
/*     */     }
/* 153 */     return this.flowElement;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends Artifact>> getArtifact()
/*     */   {
/* 182 */     if (this.artifact == null) {
/* 183 */       this.artifact = new ArrayList();
/*     */     }
/* 185 */     return this.artifact;
/*     */   }
/*     */ 
/*     */   public boolean isTriggeredByEvent()
/*     */   {
/* 197 */     if (this.triggeredByEvent == null) {
/* 198 */       return false;
/*     */     }
/* 200 */     return this.triggeredByEvent.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setTriggeredByEvent(Boolean value)
/*     */   {
/* 213 */     this.triggeredByEvent = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.SubProcess
 * JD-Core Version:    0.6.2
 */