/*    */ package com.hotent.core.excel.editor.font;
/*    */ 
/*    */ import com.hotent.core.excel.editor.IFontEditor;
/*    */ import com.hotent.core.excel.style.font.Font;
/*    */ 
/*    */ public class FontHeightEditor
/*    */   implements IFontEditor
/*    */ {
/* 14 */   private int height = 12;
/*    */ 
/*    */   public void updateFont(Font font) {
/* 17 */     font.fontHeightInPoints(this.height);
/*    */   }
/*    */ 
/*    */   public int getHeight() {
/* 21 */     return this.height;
/*    */   }
/*    */ 
/*    */   public void setHeight(int height) {
/* 25 */     this.height = height;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.font.FontHeightEditor
 * JD-Core Version:    0.6.2
 */