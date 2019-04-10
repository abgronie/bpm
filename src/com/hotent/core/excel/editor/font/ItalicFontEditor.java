/*    */ package com.hotent.core.excel.editor.font;
/*    */ 
/*    */ import com.hotent.core.excel.editor.IFontEditor;
/*    */ import com.hotent.core.excel.style.font.Font;
/*    */ 
/*    */ public class ItalicFontEditor
/*    */   implements IFontEditor
/*    */ {
/*    */   public void updateFont(Font font)
/*    */   {
/* 16 */     font.italic(true);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.font.ItalicFontEditor
 * JD-Core Version:    0.6.2
 */