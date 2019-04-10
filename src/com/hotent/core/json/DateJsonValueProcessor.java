/*    */ package com.hotent.core.json;
/*    */ 
/*    */ import com.hotent.core.util.DateFormatUtil;
/*    */ import java.util.Date;
/*    */ import net.sf.json.JsonConfig;
/*    */ import net.sf.json.processors.JsonValueProcessor;
/*    */ 
/*    */ public class DateJsonValueProcessor
/*    */   implements JsonValueProcessor
/*    */ {
/* 21 */   private String format = "yyyy-MM-dd HH:mm:ss";
/*    */ 
/*    */   public DateJsonValueProcessor(String format)
/*    */   {
/* 25 */     this.format = format;
/*    */   }
/*    */ 
/*    */   public DateJsonValueProcessor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Object processArrayValue(Object value, JsonConfig jsonConfig)
/*    */   {
/* 34 */     return process(value);
/*    */   }
/*    */ 
/*    */   public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
/*    */   {
/* 40 */     return process(value);
/*    */   }
/*    */ 
/*    */   private Object process(Object value) {
/* 44 */     if (value == null)
/* 45 */       return "";
/* 46 */     if ((value instanceof Date))
/*    */       try {
/* 48 */         return DateFormatUtil.format((Date)value, this.format);
/*    */       }
/*    */       catch (Exception ex) {
/*    */       }
/* 52 */     return value.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.json.DateJsonValueProcessor
 * JD-Core Version:    0.6.2
 */