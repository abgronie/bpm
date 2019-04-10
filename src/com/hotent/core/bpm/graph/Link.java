/*    */ package com.hotent.core.bpm.graph;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class Link
/*    */ {
/*    */   private ShapeType shapeType;
/*    */   private Shape startNode;
/*    */   private Shape endNode;
/*    */   private Port startPort;
/*    */   private Port endPort;
/*    */   private Point fallbackStartPoint;
/*    */   private Point fallbackEndPoint;
/*    */   private List<Point> intermediatePoints;
/*    */ 
/*    */   public ShapeType getShapeType()
/*    */   {
/* 40 */     return this.shapeType;
/*    */   }
/*    */   public void setShapeType(ShapeType shapeType) {
/* 43 */     this.shapeType = shapeType;
/*    */   }
/*    */   public Shape getStartNode() {
/* 46 */     return this.startNode;
/*    */   }
/*    */   public void setStartNode(Shape startNode) {
/* 49 */     this.startNode = startNode;
/*    */   }
/*    */   public Shape getEndNode() {
/* 52 */     return this.endNode;
/*    */   }
/*    */   public void setEndNode(Shape endNode) {
/* 55 */     this.endNode = endNode;
/*    */   }
/*    */   public Port getStartPort() {
/* 58 */     return this.startPort;
/*    */   }
/*    */   public void setStartPort(Port startPort) {
/* 61 */     this.startPort = startPort;
/*    */   }
/*    */   public Port getEndPort() {
/* 64 */     return this.endPort;
/*    */   }
/*    */   public void setEndPort(Port endPort) {
/* 67 */     this.endPort = endPort;
/*    */   }
/*    */   public Point getFallbackStartPoint() {
/* 70 */     return this.fallbackStartPoint;
/*    */   }
/*    */   public void setFallbackStartPoint(Point fallbackStartPoint) {
/* 73 */     this.fallbackStartPoint = fallbackStartPoint;
/*    */   }
/*    */   public Point getFallbackEndPoint() {
/* 76 */     return this.fallbackEndPoint;
/*    */   }
/*    */   public void setFallbackEndPoint(Point fallbackEndPoint) {
/* 79 */     this.fallbackEndPoint = fallbackEndPoint;
/*    */   }
/*    */   public List<Point> getIntermediatePoints() {
/* 82 */     return this.intermediatePoints;
/*    */   }
/*    */   public void setIntermediatePoints(List<Point> intermediatePoints) {
/* 85 */     this.intermediatePoints = intermediatePoints;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.Link
 * JD-Core Version:    0.6.2
 */