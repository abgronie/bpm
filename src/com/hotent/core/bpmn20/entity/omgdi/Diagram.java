/*     */ package com.hotent.core.bpmn20.entity.omgdi;
/*     */ 
/*     */ import com.hotent.core.bpmn20.entity.bpmndi.BPMNDiagram;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlID;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
/*     */ import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="Diagram")
/*     */ @XmlSeeAlso({BPMNDiagram.class})
/*     */ public abstract class Diagram
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String documentation;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Double resolution;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
/*     */   @XmlID
/*     */   @XmlSchemaType(name="ID")
/*     */   protected String id;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  72 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  84 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public String getDocumentation()
/*     */   {
/*  96 */     return this.documentation;
/*     */   }
/*     */ 
/*     */   public void setDocumentation(String value)
/*     */   {
/* 108 */     this.documentation = value;
/*     */   }
/*     */ 
/*     */   public Double getResolution()
/*     */   {
/* 120 */     return this.resolution;
/*     */   }
/*     */ 
/*     */   public void setResolution(Double value)
/*     */   {
/* 132 */     this.resolution = value;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 144 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String value)
/*     */   {
/* 156 */     this.id = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.Diagram
 * JD-Core Version:    0.6.2
 */