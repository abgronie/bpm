/*     */ package com.hotent.core.bpmn20.entity.omgdi;
/*     */ 
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlElementDecl;
/*     */ import javax.xml.bind.annotation.XmlRegistry;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlRegistry
/*     */ public class ObjectFactory
/*     */ {
/*  34 */   private static final QName _Style_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Style");
/*  35 */   private static final QName _LabeledEdge_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "LabeledEdge");
/*  36 */   private static final QName _Plane_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Plane");
/*  37 */   private static final QName _LabeledShape_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "LabeledShape");
/*  38 */   private static final QName _Shape_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Shape");
/*  39 */   private static final QName _Edge_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Edge");
/*  40 */   private static final QName _Node_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Node");
/*  41 */   private static final QName _Diagram_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Diagram");
/*  42 */   private static final QName _Label_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Label");
/*  43 */   private static final QName _DiagramElement_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "DiagramElement");
/*     */ 
/*     */   public DiagramElement.Extension createDiagramElementExtension()
/*     */   {
/*  57 */     return new DiagramElement.Extension();
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Style")
/*     */   public JAXBElement<Style> createStyle(Style value)
/*     */   {
/*  66 */     return new JAXBElement(_Style_QNAME, Style.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="LabeledEdge")
/*     */   public JAXBElement<LabeledEdge> createLabeledEdge(LabeledEdge value)
/*     */   {
/*  75 */     return new JAXBElement(_LabeledEdge_QNAME, LabeledEdge.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Plane")
/*     */   public JAXBElement<Plane> createPlane(Plane value)
/*     */   {
/*  84 */     return new JAXBElement(_Plane_QNAME, Plane.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="LabeledShape")
/*     */   public JAXBElement<LabeledShape> createLabeledShape(LabeledShape value)
/*     */   {
/*  93 */     return new JAXBElement(_LabeledShape_QNAME, LabeledShape.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Shape")
/*     */   public JAXBElement<Shape> createShape(Shape value)
/*     */   {
/* 102 */     return new JAXBElement(_Shape_QNAME, Shape.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Edge")
/*     */   public JAXBElement<Edge> createEdge(Edge value)
/*     */   {
/* 111 */     return new JAXBElement(_Edge_QNAME, Edge.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Node")
/*     */   public JAXBElement<Node> createNode(Node value)
/*     */   {
/* 120 */     return new JAXBElement(_Node_QNAME, Node.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Diagram")
/*     */   public JAXBElement<Diagram> createDiagram(Diagram value)
/*     */   {
/* 129 */     return new JAXBElement(_Diagram_QNAME, Diagram.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="Label")
/*     */   public JAXBElement<Label> createLabel(Label value)
/*     */   {
/* 138 */     return new JAXBElement(_Label_QNAME, Label.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DI", name="DiagramElement")
/*     */   public JAXBElement<DiagramElement> createDiagramElement(DiagramElement value)
/*     */   {
/* 147 */     return new JAXBElement(_DiagramElement_QNAME, DiagramElement.class, null, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.ObjectFactory
 * JD-Core Version:    0.6.2
 */