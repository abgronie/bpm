/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tChoreography", propOrder={"flowElement"})
/*     */ @XmlSeeAlso({GlobalChoreographyTask.class})
/*     */ public class Choreography extends Collaboration
/*     */ {
/*     */ 
/*     */   @XmlElementRef(name="flowElement", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends FlowElement>> flowElement;
/*     */ 
/*     */   public List<JAXBElement<? extends FlowElement>> getFlowElement()
/*     */   {
/* 109 */     if (this.flowElement == null) {
/* 110 */       this.flowElement = new ArrayList();
/*     */     }
/* 112 */     return this.flowElement;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Choreography
 * JD-Core Version:    0.6.2
 */