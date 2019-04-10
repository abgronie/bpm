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
/*     */ @XmlType(name="tCategory", propOrder={"categoryValue"})
/*     */ public class Category extends RootElement
/*     */ {
/*     */   protected List<CategoryValue> categoryValue;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   public List<CategoryValue> getCategoryValue()
/*     */   {
/*  75 */     if (this.categoryValue == null) {
/*  76 */       this.categoryValue = new ArrayList();
/*     */     }
/*  78 */     return this.categoryValue;
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
 * Qualified Name:     com.hotent.core.bpmn20.entity.Category
 * JD-Core Version:    0.6.2
 */