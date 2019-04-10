/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tResourceRole", propOrder={"resourceRef", "resourceParameterBinding", "resourceAssignmentExpression"})
/*     */ @XmlSeeAlso({Performer.class})
/*     */ public class ResourceRole extends BaseElement
/*     */ {
/*     */   protected QName resourceRef;
/*     */   protected List<ResourceParameterBinding> resourceParameterBinding;
/*     */   protected ResourceAssignmentExpression resourceAssignmentExpression;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public QName getResourceRef()
/*     */   {
/*  74 */     return this.resourceRef;
/*     */   }
/*     */ 
/*     */   public void setResourceRef(QName value)
/*     */   {
/*  86 */     this.resourceRef = value;
/*     */   }
/*     */ 
/*     */   public List<ResourceParameterBinding> getResourceParameterBinding()
/*     */   {
/* 112 */     if (this.resourceParameterBinding == null) {
/* 113 */       this.resourceParameterBinding = new ArrayList();
/*     */     }
/* 115 */     return this.resourceParameterBinding;
/*     */   }
/*     */ 
/*     */   public ResourceAssignmentExpression getResourceAssignmentExpression()
/*     */   {
/* 127 */     return this.resourceAssignmentExpression;
/*     */   }
/*     */ 
/*     */   public void setResourceAssignmentExpression(ResourceAssignmentExpression value)
/*     */   {
/* 139 */     this.resourceAssignmentExpression = value;
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
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ResourceRole
 * JD-Core Version:    0.6.2
 */