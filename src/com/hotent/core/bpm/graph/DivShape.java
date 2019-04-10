/*    */ package com.hotent.core.bpm.graph;
/*    */ 
/*    */ public class DivShape extends Shape
/*    */ {
/* 13 */   private int zIndex = 0;
/* 14 */   private String id = "";
/* 15 */   private String type = "";
/*    */ 
/*    */   public String getType() {
/* 18 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type)
/*    */   {
/* 23 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public DivShape(String name, float x, float y, float w, float h)
/*    */   {
/* 28 */     super(name, x, y, w, h);
/*    */   }
/*    */ 
/*    */   public DivShape(String name, float x, float y, float w, float h, int zIndex, String id, String type)
/*    */   {
/* 34 */     super(name, x, y, w, h);
/* 35 */     this.zIndex = zIndex;
/* 36 */     this.id = id;
/* 37 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public int getzIndex()
/*    */   {
/* 42 */     return this.zIndex;
/*    */   }
/*    */ 
/*    */   public void setzIndex(int zIndex)
/*    */   {
/* 47 */     this.zIndex = zIndex;
/*    */   }
/*    */ 
/*    */   public String getId()
/*    */   {
/* 52 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id)
/*    */   {
/* 57 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 62 */     return "<div class='flowNode' id='" + this.id + "' " + "style='position:absolute;z-index:" + this.zIndex + "; " + "left:" + getX() + "px;" + "top:" + getY() + "px;" + "width:" + getW() + "px;" + "height:" + getH() + "px;' " + "title='" + getName() + "' " + "type='" + getType() + "'></div>\r\n".replaceAll("\\.0px", "px");
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.graph.DivShape
 * JD-Core Version:    0.6.2
 */