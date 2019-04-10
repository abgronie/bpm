/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tSubChoreography", propOrder={"flowElement", "artifact"})
/*     */ public class SubChoreography extends ChoreographyActivity
/*     */ {
/*     */ 
/*     */   @XmlElementRef(name="flowElement", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends FlowElement>> flowElement;
/*     */ 
/*     */   @XmlElementRef(name="artifact", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends Artifact>> artifact;
/*     */ 
/*     */   public List<JAXBElement<? extends FlowElement>> getFlowElement()
/*     */   {
/* 109 */     if (this.flowElement == null) {
/* 110 */       this.flowElement = new ArrayList();
/*     */     }
/* 112 */     return this.flowElement;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends Artifact>> getArtifact()
/*     */   {
/* 141 */     if (this.artifact == null) {
/* 142 */       this.artifact = new ArrayList();
/*     */     }
/* 144 */     return this.artifact;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.SubChoreography
 * JD-Core Version:    0.6.2
 */