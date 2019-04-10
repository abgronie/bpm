/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAnyElement;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlID;
/*     */ import javax.xml.bind.annotation.XmlMixed;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
/*     */ import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tDocumentation", propOrder={"content"})
/*     */ public class Documentation
/*     */ {
/*     */ 
/*     */   @XmlMixed
/*     */   @XmlAnyElement(lax=true)
/*     */   protected List<Object> content;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
/*     */   @XmlID
/*     */   @XmlSchemaType(name="ID")
/*     */   protected String id;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String textFormat;
/*     */ 
/*     */   public List<Object> getContent()
/*     */   {
/*  89 */     if (this.content == null) {
/*  90 */       this.content = new ArrayList();
/*     */     }
/*  92 */     return this.content;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 104 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String value)
/*     */   {
/* 116 */     this.id = value;
/*     */   }
/*     */ 
/*     */   public String getTextFormat()
/*     */   {
/* 128 */     if (this.textFormat == null) {
/* 129 */       return "text/plain";
/*     */     }
/* 131 */     return this.textFormat;
/*     */   }
/*     */ 
/*     */   public void setTextFormat(String value)
/*     */   {
/* 144 */     this.textFormat = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Documentation
 * JD-Core Version:    0.6.2
 */