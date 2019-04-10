/*     */ package com.hotent.core.bpmn20.entity.bpmndi;
/*     */ 
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlElementDecl;
/*     */ import javax.xml.bind.annotation.XmlRegistry;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlRegistry
/*     */ public class ObjectFactory
/*     */ {
/*  34 */   private static final QName _BPMNLabelStyle_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabelStyle");
/*  35 */   private static final QName _BPMNLabel_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabel");
/*  36 */   private static final QName _BPMNEdge_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNEdge");
/*  37 */   private static final QName _BPMNDiagram_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNDiagram");
/*  38 */   private static final QName _BPMNShape_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNShape");
/*  39 */   private static final QName _BPMNPlane_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNPlane");
/*     */ 
/*     */   public BPMNShape createBPMNShape()
/*     */   {
/*  53 */     return new BPMNShape();
/*     */   }
/*     */ 
/*     */   public BPMNLabel createBPMNLabel()
/*     */   {
/*  61 */     return new BPMNLabel();
/*     */   }
/*     */ 
/*     */   public BPMNPlane createBPMNPlane()
/*     */   {
/*  69 */     return new BPMNPlane();
/*     */   }
/*     */ 
/*     */   public BPMNDiagram createBPMNDiagram()
/*     */   {
/*  77 */     return new BPMNDiagram();
/*     */   }
/*     */ 
/*     */   public BPMNEdge createBPMNEdge()
/*     */   {
/*  85 */     return new BPMNEdge();
/*     */   }
/*     */ 
/*     */   public BPMNLabelStyle createBPMNLabelStyle()
/*     */   {
/*  93 */     return new BPMNLabelStyle();
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/DI", name="BPMNLabelStyle")
/*     */   public JAXBElement<BPMNLabelStyle> createBPMNLabelStyle(BPMNLabelStyle value)
/*     */   {
/* 102 */     return new JAXBElement(_BPMNLabelStyle_QNAME, BPMNLabelStyle.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/DI", name="BPMNLabel")
/*     */   public JAXBElement<BPMNLabel> createBPMNLabel(BPMNLabel value)
/*     */   {
/* 111 */     return new JAXBElement(_BPMNLabel_QNAME, BPMNLabel.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/DI", name="BPMNEdge", substitutionHeadNamespace="http://www.omg.org/spec/DD/20100524/DI", substitutionHeadName="DiagramElement")
/*     */   public JAXBElement<BPMNEdge> createBPMNEdge(BPMNEdge value)
/*     */   {
/* 120 */     return new JAXBElement(_BPMNEdge_QNAME, BPMNEdge.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/DI", name="BPMNDiagram")
/*     */   public JAXBElement<BPMNDiagram> createBPMNDiagram(BPMNDiagram value)
/*     */   {
/* 129 */     return new JAXBElement(_BPMNDiagram_QNAME, BPMNDiagram.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/DI", name="BPMNShape", substitutionHeadNamespace="http://www.omg.org/spec/DD/20100524/DI", substitutionHeadName="DiagramElement")
/*     */   public JAXBElement<BPMNShape> createBPMNShape(BPMNShape value)
/*     */   {
/* 138 */     return new JAXBElement(_BPMNShape_QNAME, BPMNShape.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/DI", name="BPMNPlane")
/*     */   public JAXBElement<BPMNPlane> createBPMNPlane(BPMNPlane value)
/*     */   {
/* 147 */     return new JAXBElement(_BPMNPlane_QNAME, BPMNPlane.class, null, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.ObjectFactory
 * JD-Core Version:    0.6.2
 */