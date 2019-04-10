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
/*     */ @XmlType(name="tLane", propOrder={"partitionElement", "flowNodeRef", "childLaneSet"})
/*     */ public class Lane extends BaseElement
/*     */ {
/*     */   protected BaseElement partitionElement;
/*     */ 
/*     */   @XmlElementRef(name="flowNodeRef", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<Object>> flowNodeRef;
/*     */   protected LaneSet childLaneSet;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName partitionElementRef;
/*     */ 
/*     */   public BaseElement getPartitionElement()
/*     */   {
/*  74 */     return this.partitionElement;
/*     */   }
/*     */ 
/*     */   public void setPartitionElement(BaseElement value)
/*     */   {
/*  86 */     this.partitionElement = value;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<Object>> getFlowNodeRef()
/*     */   {
/* 112 */     if (this.flowNodeRef == null) {
/* 113 */       this.flowNodeRef = new ArrayList();
/*     */     }
/* 115 */     return this.flowNodeRef;
/*     */   }
/*     */ 
/*     */   public LaneSet getChildLaneSet()
/*     */   {
/* 127 */     return this.childLaneSet;
/*     */   }
/*     */ 
/*     */   public void setChildLaneSet(LaneSet value)
/*     */   {
/* 139 */     this.childLaneSet = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 151 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 163 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public QName getPartitionElementRef()
/*     */   {
/* 175 */     return this.partitionElementRef;
/*     */   }
/*     */ 
/*     */   public void setPartitionElementRef(QName value)
/*     */   {
/* 187 */     this.partitionElementRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Lane
 * JD-Core Version:    0.6.2
 */