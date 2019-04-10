/*     */ package com.hotent.core.bpm.graph;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class Shape
/*     */ {
/*  13 */   private float x = 0.0F;
/*  14 */   private float y = 0.0F;
/*  15 */   private float w = 0.0F;
/*  16 */   private float h = 0.0F;
/*  17 */   private String name = "";
/*     */   private List<Port> ports;
/*     */   private DirEnum dir;
/*  24 */   private float offset = 0.0F;
/*     */ 
/*     */   public Shape(String name, float x, float y, float w, float h)
/*     */   {
/*  39 */     this.h = h;
/*  40 */     if ((name.equals("bg:StartEvent")) || (name.equals("bg:EndEvent"))) {
/*  41 */       this.h = w;
/*     */     }
/*     */ 
/*  44 */     this.name = name;
/*     */ 
/*  46 */     this.x = x;
/*  47 */     this.y = y;
/*  48 */     this.w = w;
/*     */   }
/*     */ 
/*     */   public float getOffset()
/*     */   {
/*  53 */     return this.offset;
/*     */   }
/*     */ 
/*     */   public void setOffset(float offset) {
/*  57 */     this.offset = offset;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  62 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  66 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public DirEnum getDirectory()
/*     */   {
/*  97 */     return this.dir;
/*     */   }
/*     */ 
/*     */   public float getX()
/*     */   {
/* 113 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(float x) {
/* 117 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public float getY() {
/* 121 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setY(float y) {
/* 125 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public float getW() {
/* 129 */     return this.w;
/*     */   }
/*     */ 
/*     */   public void setW(float w) {
/* 133 */     this.w = w;
/*     */   }
/*     */ 
/*     */   public float getH() {
/* 137 */     return this.h;
/*     */   }
/*     */ 
/*     */   public void setH(float h) {
/* 141 */     this.h = h;
/*     */   }
/*     */ 
/*     */   public List<Port> getPorts() {
/* 145 */     return this.ports;
/*     */   }
/*     */ 
/*     */   public void setPorts(List<Port> ports) {
/* 149 */     this.ports = ports;
/*     */   }
/*     */ 
/*     */   public float getCenterX()
/*     */   {
/* 229 */     return (this.x + this.w) / 2.0F;
/*     */   }
/*     */   public float getCenterY() {
/* 232 */     return (this.y + this.h) / 2.0F;
/*     */   }
/*     */   public float getBottomRightX() {
/* 235 */     return this.x + this.w;
/*     */   }
/*     */   public float getBottomRightY() {
/* 238 */     return this.y + this.h;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.Shape
 * JD-Core Version:    0.6.2
 */