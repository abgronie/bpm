/*     */ package com.hotent.core.bpmn20.entity.omgdc;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="Bounds")
/*     */ public class Bounds
/*     */ {
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected double x;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected double y;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected double width;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected double height;
/*     */ 
/*     */   public double getX()
/*     */   {
/*  55 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(double value)
/*     */   {
/*  63 */     this.x = value;
/*     */   }
/*     */ 
/*     */   public double getY()
/*     */   {
/*  71 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setY(double value)
/*     */   {
/*  79 */     this.y = value;
/*     */   }
/*     */ 
/*     */   public double getWidth()
/*     */   {
/*  87 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(double value)
/*     */   {
/*  95 */     this.width = value;
/*     */   }
/*     */ 
/*     */   public double getHeight()
/*     */   {
/* 103 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(double value)
/*     */   {
/* 111 */     this.height = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdc.Bounds
 * JD-Core Version:    0.6.2
 */