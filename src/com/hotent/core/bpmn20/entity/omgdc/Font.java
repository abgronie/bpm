/*     */ package com.hotent.core.bpmn20.entity.omgdc;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="Font")
/*     */ public class Font
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Double size;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isBold;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isItalic;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isUnderline;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isStrikeThrough;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  65 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/*  77 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public Double getSize()
/*     */   {
/*  89 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void setSize(Double value)
/*     */   {
/* 101 */     this.size = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsBold()
/*     */   {
/* 113 */     return this.isBold;
/*     */   }
/*     */ 
/*     */   public void setIsBold(Boolean value)
/*     */   {
/* 125 */     this.isBold = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsItalic()
/*     */   {
/* 137 */     return this.isItalic;
/*     */   }
/*     */ 
/*     */   public void setIsItalic(Boolean value)
/*     */   {
/* 149 */     this.isItalic = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsUnderline()
/*     */   {
/* 161 */     return this.isUnderline;
/*     */   }
/*     */ 
/*     */   public void setIsUnderline(Boolean value)
/*     */   {
/* 173 */     this.isUnderline = value;
/*     */   }
/*     */ 
/*     */   public Boolean isIsStrikeThrough()
/*     */   {
/* 185 */     return this.isStrikeThrough;
/*     */   }
/*     */ 
/*     */   public void setIsStrikeThrough(Boolean value)
/*     */   {
/* 197 */     this.isStrikeThrough = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdc.Font
 * JD-Core Version:    0.6.2
 */