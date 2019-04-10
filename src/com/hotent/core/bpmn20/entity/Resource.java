/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tResource", propOrder={"resourceParameter"})
/*     */ public class Resource extends RootElement
/*     */ {
/*     */   protected List<ResourceParameter> resourceParameter;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String name;
/*     */ 
/*     */   public List<ResourceParameter> getResourceParameter()
/*     */   {
/*  75 */     if (this.resourceParameter == null) {
/*  76 */       this.resourceParameter = new ArrayList();
/*     */     }
/*  78 */     return this.resourceParameter;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  90 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 102 */     this.name = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Resource
 * JD-Core Version:    0.6.2
 */