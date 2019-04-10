/*     */ package com.hotent.core.bpm.graph.activiti;
/*     */ 
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class BPMNShap
/*     */ {
/*     */   private BPMNShapType type;
/*     */   private String name;
/*     */   private String bpmnElement;
/*     */   private Boolean isHorizontal;
/*     */   private Boolean isExpanded;
/*     */   private Boolean isMarkerVisible;
/*     */   private Boolean isMessageVisible;
/*     */   private String participantBandKind;
/*     */   private String choreographyActivityShape;
/*     */   private double x;
/*     */   private double y;
/*     */   private double width;
/*     */   private double height;
/*     */   private Properties properties;
/*     */ 
/*     */   public BPMNShapType getType()
/*     */   {
/*  24 */     return this.type;
/*     */   }
/*     */   public void setType(BPMNShapType type) {
/*  27 */     this.type = type;
/*     */   }
/*     */   public String getName() {
/*  30 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  33 */     this.name = name;
/*     */   }
/*     */   public String getBpmnElement() {
/*  36 */     return this.bpmnElement;
/*     */   }
/*     */   public void setBpmnElement(String bpmnElement) {
/*  39 */     this.bpmnElement = bpmnElement;
/*     */   }
/*     */   public Boolean isHorizontal() {
/*  42 */     return this.isHorizontal;
/*     */   }
/*     */   public void setHorizontal(Boolean isHorizontal) {
/*  45 */     this.isHorizontal = isHorizontal;
/*     */   }
/*     */   public Boolean isExpanded() {
/*  48 */     return this.isExpanded;
/*     */   }
/*     */   public void setExpanded(Boolean isExpanded) {
/*  51 */     this.isExpanded = isExpanded;
/*     */   }
/*     */   public Boolean isMarkerVisible() {
/*  54 */     return this.isMarkerVisible;
/*     */   }
/*     */   public void setMarkerVisible(Boolean isMarkerVisible) {
/*  57 */     this.isMarkerVisible = isMarkerVisible;
/*     */   }
/*     */   public Boolean isMessageVisible() {
/*  60 */     return this.isMessageVisible;
/*     */   }
/*     */   public void setMessageVisible(Boolean isMessageVisible) {
/*  63 */     this.isMessageVisible = isMessageVisible;
/*     */   }
/*     */   public String getParticipantBandKind() {
/*  66 */     return this.participantBandKind;
/*     */   }
/*     */   public void setParticipantBandKind(String participantBandKind) {
/*  69 */     this.participantBandKind = participantBandKind;
/*     */   }
/*     */   public String getChoreographyActivityShape() {
/*  72 */     return this.choreographyActivityShape;
/*     */   }
/*     */   public void setChoreographyActivityShape(String choreographyActivityShape) {
/*  75 */     this.choreographyActivityShape = choreographyActivityShape;
/*     */   }
/*     */   public double getX() {
/*  78 */     return this.x;
/*     */   }
/*     */   public void setX(double x) {
/*  81 */     this.x = x;
/*     */   }
/*     */   public double getY() {
/*  84 */     return this.y;
/*     */   }
/*     */   public void setY(double y) {
/*  87 */     this.y = y;
/*     */   }
/*     */   public double getWidth() {
/*  90 */     return this.width;
/*     */   }
/*     */   public void setWidth(double width) {
/*  93 */     this.width = width;
/*     */   }
/*     */   public double getHeight() {
/*  96 */     return this.height;
/*     */   }
/*     */   public void setHeight(double height) {
/*  99 */     this.height = height;
/*     */   }
/*     */   public Properties getProperties() {
/* 102 */     return this.properties;
/*     */   }
/*     */   public void setProperties(Properties properties) {
/* 105 */     this.properties = properties;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 109 */     return "BPMNShap [type=" + this.type + ", name=" + this.name + ", bpmnElement=" + this.bpmnElement + ", isHorizontal=" + this.isHorizontal + ", isExpanded=" + this.isExpanded + ", isMarkerVisible=" + this.isMarkerVisible + ", isMessageVisible=" + this.isMessageVisible + ", participantBandKind=" + this.participantBandKind + ", choreographyActivityShape=" + this.choreographyActivityShape + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", properties=" + this.properties + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.activiti.BPMNShap
 * JD-Core Version:    0.6.2
 */