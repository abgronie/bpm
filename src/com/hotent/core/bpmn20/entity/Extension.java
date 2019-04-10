/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tExtension", propOrder={"documentation"})
/*     */ public class Extension
/*     */ {
/*     */   protected List<Documentation> documentation;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName definition;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean mustUnderstand;
/*     */ 
/*     */   public List<Documentation> getDocumentation()
/*     */   {
/*  76 */     if (this.documentation == null) {
/*  77 */       this.documentation = new ArrayList();
/*     */     }
/*  79 */     return this.documentation;
/*     */   }
/*     */ 
/*     */   public QName getDefinition()
/*     */   {
/*  91 */     return this.definition;
/*     */   }
/*     */ 
/*     */   public void setDefinition(QName value)
/*     */   {
/* 103 */     this.definition = value;
/*     */   }
/*     */ 
/*     */   public boolean isMustUnderstand()
/*     */   {
/* 115 */     if (this.mustUnderstand == null) {
/* 116 */       return false;
/*     */     }
/* 118 */     return this.mustUnderstand.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setMustUnderstand(Boolean value)
/*     */   {
/* 131 */     this.mustUnderstand = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Extension
 * JD-Core Version:    0.6.2
 */