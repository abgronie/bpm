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
/*     */ @XmlType(name="tUserTask", propOrder={"rendering"})
/*     */ public class UserTask extends Task
/*     */ {
/*     */   protected List<Rendering> rendering;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String implementation;
/*     */ 
/*     */   public List<Rendering> getRendering()
/*     */   {
/*  75 */     if (this.rendering == null) {
/*  76 */       this.rendering = new ArrayList();
/*     */     }
/*  78 */     return this.rendering;
/*     */   }
/*     */ 
/*     */   public String getImplementation()
/*     */   {
/*  90 */     if (this.implementation == null) {
/*  91 */       return "##unspecified";
/*     */     }
/*  93 */     return this.implementation;
/*     */   }
/*     */ 
/*     */   public void setImplementation(String value)
/*     */   {
/* 106 */     this.implementation = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.UserTask
 * JD-Core Version:    0.6.2
 */