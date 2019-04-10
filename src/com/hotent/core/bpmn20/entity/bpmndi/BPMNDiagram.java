/*     */ package com.hotent.core.bpmn20.entity.bpmndi;
/*     */ 
/*     */ import com.hotent.core.bpmn20.entity.omgdi.Diagram;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="BPMNDiagram", propOrder={"bpmnPlane", "bpmnLabelStyle"})
/*     */ public class BPMNDiagram extends Diagram
/*     */ {
/*     */ 
/*     */   @XmlElement(name="BPMNPlane", required=true)
/*     */   protected BPMNPlane bpmnPlane;
/*     */ 
/*     */   @XmlElement(name="BPMNLabelStyle")
/*     */   protected List<BPMNLabelStyle> bpmnLabelStyle;
/*     */ 
/*     */   public BPMNPlane getBPMNPlane()
/*     */   {
/*  64 */     return this.bpmnPlane;
/*     */   }
/*     */ 
/*     */   public void setBPMNPlane(BPMNPlane value)
/*     */   {
/*  76 */     this.bpmnPlane = value;
/*     */   }
/*     */ 
/*     */   public List<BPMNLabelStyle> getBPMNLabelStyle()
/*     */   {
/* 102 */     if (this.bpmnLabelStyle == null) {
/* 103 */       this.bpmnLabelStyle = new ArrayList();
/*     */     }
/* 105 */     return this.bpmnLabelStyle;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.BPMNDiagram
 * JD-Core Version:    0.6.2
 */