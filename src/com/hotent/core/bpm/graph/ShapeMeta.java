/*    */ package com.hotent.core.bpm.graph;
/*    */ 
/*    */ public class ShapeMeta
/*    */ {
/* 15 */   private String xml = "";
/*    */ 
/* 20 */   private int width = 0;
/*    */ 
/* 25 */   private int height = 0;
/*    */ 
/*    */   public ShapeMeta(int w, int h, String xml)
/*    */   {
/* 29 */     this.width = w;
/* 30 */     this.height = h;
/* 31 */     this.xml = xml;
/*    */   }
/*    */ 
/*    */   public String getXml() {
/* 35 */     return this.xml;
/*    */   }
/*    */ 
/*    */   public void setXml(String xml) {
/* 39 */     this.xml = xml;
/*    */   }
/*    */ 
/*    */   public int getWidth() {
/* 43 */     return this.width;
/*    */   }
/*    */ 
/*    */   public void setWidth(int width) {
/* 47 */     this.width = width;
/*    */   }
/*    */ 
/*    */   public int getHeight() {
/* 51 */     return this.height;
/*    */   }
/*    */ 
/*    */   public void setHeight(int height) {
/* 55 */     this.height = height;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.ShapeMeta
 * JD-Core Version:    0.6.2
 */