/*    */ package com.hotent.core.json;
/*    */ 
/*    */ import com.hotent.core.util.DateUtil;
/*    */ import java.beans.PropertyEditorSupport;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SmartDateEditor extends PropertyEditorSupport
/*    */ {
/*    */   public void setAsText(String text)
/*    */     throws IllegalArgumentException
/*    */   {
/* 11 */     if ((text == null) || (text.length() == 0))
/* 12 */       setValue(null);
/*    */     else
/*    */       try {
/* 15 */         setValue(DateUtil.parseDate(text));
/*    */       } catch (Exception ex) {
/* 17 */         throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
/*    */       }
/*    */   }
/*    */ 
/*    */   public String getAsText()
/*    */   {
/* 28 */     Object obj = getValue();
/* 29 */     if (obj == null) {
/* 30 */       return "";
/*    */     }
/* 32 */     Date value = (Date)obj;
/* 33 */     String dateStr = null;
/*    */     try {
/* 35 */       dateStr = DateUtil.formatEnDate(value);
/* 36 */       if (dateStr.endsWith(" 00:00:00"))
/* 37 */         dateStr = dateStr.substring(0, 10);
/* 38 */       else if (!dateStr.endsWith(":00"));
/* 39 */       return dateStr.substring(0, 16);
/*    */     }
/*    */     catch (Exception ex)
/*    */     {
/* 43 */       throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.json.SmartDateEditor
 * JD-Core Version:    0.6.2
 */