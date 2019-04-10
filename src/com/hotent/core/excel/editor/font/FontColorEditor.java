/*    */ package com.hotent.core.excel.editor.font;
/*    */ 
/*    */ import com.hotent.core.excel.editor.IFontEditor;
/*    */ import com.hotent.core.excel.style.Color;
/*    */ import com.hotent.core.excel.style.font.Font;
/*    */ 
/*    */ public class FontColorEditor
/*    */   implements IFontEditor
/*    */ {
/* 15 */   private Color color = Color.BLACK;
/*    */ 
/*    */   public void updateFont(Font font) {
/* 18 */     font.color(this.color);
/*    */   }
/*    */ 
/*    */   public Color getColor()
/*    */   {
/* 26 */     return this.color;
/*    */   }
/*    */ 
/*    */   public void setColor(Color color)
/*    */   {
/* 34 */     this.color = color;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.font.FontColorEditor
 * JD-Core Version:    0.6.2
 */