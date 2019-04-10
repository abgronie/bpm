/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tItemDefinition")
/*     */ public class ItemDefinition extends RootElement
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName structureRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isCollection;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected ItemKind itemKind;
/*     */ 
/*     */   public QName getStructureRef()
/*     */   {
/*  60 */     return this.structureRef;
/*     */   }
/*     */ 
/*     */   public void setStructureRef(QName value)
/*     */   {
/*  72 */     this.structureRef = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsCollection()
/*     */   {
/*  84 */     if (this.isCollection == null) {
/*  85 */       return false;
/*     */     }
/*  87 */     return this.isCollection.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsCollection(Boolean value)
/*     */   {
/* 100 */     this.isCollection = value;
/*     */   }
/*     */ 
/*     */   public ItemKind getItemKind()
/*     */   {
/* 112 */     if (this.itemKind == null) {
/* 113 */       return ItemKind.INFORMATION;
/*     */     }
/* 115 */     return this.itemKind;
/*     */   }
/*     */ 
/*     */   public void setItemKind(ItemKind value)
/*     */   {
/* 128 */     this.itemKind = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ItemDefinition
 * JD-Core Version:    0.6.2
 */