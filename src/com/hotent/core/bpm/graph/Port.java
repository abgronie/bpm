/*    */ package com.hotent.core.bpm.graph;
/*    */ 
/*    */ public class Port
/*    */ {
/*    */   private PortType portType;
/*    */   private double x;
/*    */   private double y;
/*    */   private double verticalOffset;
/*    */   private double horizontalOffset;
/*    */   private String nodePartReference;
/*    */   private boolean clipOnShape;
/*    */ 
/*    */   public Port()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Port(PortType portType, double x, double y, double horizontalOffset, double verticalOffset, String nodePartReference, boolean clipOnShape)
/*    */   {
/* 29 */     this.portType = portType;
/* 30 */     this.x = x;
/* 31 */     this.y = y;
/* 32 */     this.verticalOffset = verticalOffset;
/* 33 */     this.horizontalOffset = horizontalOffset;
/* 34 */     this.nodePartReference = nodePartReference;
/* 35 */     this.clipOnShape = clipOnShape;
/*    */   }
/*    */ 
/*    */   public PortType getPortType() {
/* 39 */     return this.portType;
/*    */   }
/*    */ 
/*    */   public void setPortType(PortType portType) {
/* 43 */     this.portType = portType;
/*    */   }
/*    */ 
/*    */   public double getX() {
/* 47 */     return this.x;
/*    */   }
/*    */ 
/*    */   public void setX(double x) {
/* 51 */     this.x = x;
/*    */   }
/*    */ 
/*    */   public double getY() {
/* 55 */     return this.y;
/*    */   }
/*    */ 
/*    */   public void setY(double y) {
/* 59 */     this.y = y;
/*    */   }
/*    */ 
/*    */   public double getVerticalOffset() {
/* 63 */     return this.verticalOffset;
/*    */   }
/*    */ 
/*    */   public void setVerticalOffset(double verticalOffset) {
/* 67 */     this.verticalOffset = verticalOffset;
/*    */   }
/*    */ 
/*    */   public double getHorizontalOffset() {
/* 71 */     return this.horizontalOffset;
/*    */   }
/*    */ 
/*    */   public void setHorizontalOffset(double horizontalOffset) {
/* 75 */     this.horizontalOffset = horizontalOffset;
/*    */   }
/*    */ 
/*    */   public String getNodePartReference() {
/* 79 */     return this.nodePartReference;
/*    */   }
/*    */ 
/*    */   public void setNodePartReference(String nodePartReference) {
/* 83 */     this.nodePartReference = nodePartReference;
/*    */   }
/*    */ 
/*    */   public boolean isClipOnShape() {
/* 87 */     return this.clipOnShape;
/*    */   }
/*    */ 
/*    */   public void setClipOnShape(boolean clipOnShape) {
/* 91 */     this.clipOnShape = clipOnShape;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.Port
 * JD-Core Version:    0.6.2
 */