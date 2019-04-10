/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import com.hotent.core.bpmn20.entity.bpmndi.BPMNDiagram;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAnyAttribute;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlElementRef;
/*     */ import javax.xml.bind.annotation.XmlID;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
/*     */ import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tDefinitions", propOrder={"_import", "extension", "rootElement", "bpmnDiagram", "relationship"})
/*     */ public class Definitions
/*     */ {
/*     */ 
/*     */   @XmlElement(name="import")
/*     */   protected List<Import> _import;
/*     */   protected List<Extension> extension;
/*     */ 
/*     */   @XmlElementRef(name="rootElement", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*     */   protected List<JAXBElement<? extends RootElement>> rootElement;
/*     */ 
/*     */   @XmlElement(name="BPMNDiagram", namespace="http://www.omg.org/spec/BPMN/20100524/DI")
/*     */   protected List<BPMNDiagram> bpmnDiagram;
/*     */   protected List<Relationship> relationship;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
/*     */   @XmlID
/*     */   @XmlSchemaType(name="ID")
/*     */   protected String id;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   @XmlSchemaType(name="anyURI")
/*     */   protected String targetNamespace;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlSchemaType(name="anyURI")
/*     */   protected String expressionLanguage;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlSchemaType(name="anyURI")
/*     */   protected String typeLanguage;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String exporter;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String exporterVersion;
/*     */ 
/*     */   @XmlAnyAttribute
/* 101 */   private Map<QName, String> otherAttributes = new HashMap();
/*     */ 
/*     */   public List<Import> getImport()
/*     */   {
/* 127 */     if (this._import == null) {
/* 128 */       this._import = new ArrayList();
/*     */     }
/* 130 */     return this._import;
/*     */   }
/*     */ 
/*     */   public List<Extension> getExtension()
/*     */   {
/* 156 */     if (this.extension == null) {
/* 157 */       this.extension = new ArrayList();
/*     */     }
/* 159 */     return this.extension;
/*     */   }
/*     */ 
/*     */   public List<JAXBElement<? extends RootElement>> getRootElement()
/*     */   {
/* 219 */     if (this.rootElement == null) {
/* 220 */       this.rootElement = new ArrayList();
/*     */     }
/* 222 */     return this.rootElement;
/*     */   }
/*     */ 
/*     */   public List<BPMNDiagram> getBPMNDiagram()
/*     */   {
/* 248 */     if (this.bpmnDiagram == null) {
/* 249 */       this.bpmnDiagram = new ArrayList();
/*     */     }
/* 251 */     return this.bpmnDiagram;
/*     */   }
/*     */ 
/*     */   public List<Relationship> getRelationship()
/*     */   {
/* 277 */     if (this.relationship == null) {
/* 278 */       this.relationship = new ArrayList();
/*     */     }
/* 280 */     return this.relationship;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 292 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String value)
/*     */   {
/* 304 */     this.id = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 316 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 328 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public String getTargetNamespace()
/*     */   {
/* 340 */     return this.targetNamespace;
/*     */   }
/*     */ 
/*     */   public void setTargetNamespace(String value)
/*     */   {
/* 352 */     this.targetNamespace = value;
/*     */   }
/*     */ 
/*     */   public String getExpressionLanguage()
/*     */   {
/* 364 */     if (this.expressionLanguage == null) {
/* 365 */       return "http://www.w3.org/1999/XPath";
/*     */     }
/* 367 */     return this.expressionLanguage;
/*     */   }
/*     */ 
/*     */   public void setExpressionLanguage(String value)
/*     */   {
/* 380 */     this.expressionLanguage = value;
/*     */   }
/*     */ 
/*     */   public String getTypeLanguage()
/*     */   {
/* 392 */     if (this.typeLanguage == null) {
/* 393 */       return "http://www.w3.org/2001/XMLSchema";
/*     */     }
/* 395 */     return this.typeLanguage;
/*     */   }
/*     */ 
/*     */   public void setTypeLanguage(String value)
/*     */   {
/* 408 */     this.typeLanguage = value;
/*     */   }
/*     */ 
/*     */   public String getExporter()
/*     */   {
/* 420 */     return this.exporter;
/*     */   }
/*     */ 
/*     */   public void setExporter(String value)
/*     */   {
/* 432 */     this.exporter = value;
/*     */   }
/*     */ 
/*     */   public String getExporterVersion()
/*     */   {
/* 444 */     return this.exporterVersion;
/*     */   }
/*     */ 
/*     */   public void setExporterVersion(String value)
/*     */   {
/* 456 */     this.exporterVersion = value;
/*     */   }
/*     */ 
/*     */   public Map<QName, String> getOtherAttributes()
/*     */   {
/* 474 */     return this.otherAttributes;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Definitions
 * JD-Core Version:    0.6.2
 */