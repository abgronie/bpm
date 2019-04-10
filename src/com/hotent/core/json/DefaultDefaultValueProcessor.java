/*    */ package com.hotent.core.json;
/*    */ 
/*    */ import net.sf.json.JSONArray;
/*    */ import net.sf.json.JSONNull;
/*    */ import net.sf.json.processors.DefaultValueProcessor;
/*    */ import net.sf.json.util.JSONUtils;
/*    */ 
/*    */ public class DefaultDefaultValueProcessor
/*    */   implements DefaultValueProcessor
/*    */ {
/*    */   public Object getDefaultValue(Class type)
/*    */   {
/* 13 */     if (JSONUtils.isArray(type))
/* 14 */       return new JSONArray();
/* 15 */     if (JSONUtils.isNumber(type)) {
/* 16 */       if (JSONUtils.isDouble(type)) {
/* 17 */         return new Double(0.0D);
/*    */       }
/* 19 */       return new Integer(0);
/*    */     }
/* 21 */     if (JSONUtils.isBoolean(type))
/* 22 */       return Boolean.FALSE;
/* 23 */     if (JSONUtils.isString(type)) {
/* 24 */       return "";
/*    */     }
/* 26 */     return JSONNull.getInstance();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.json.DefaultDefaultValueProcessor
 * JD-Core Version:    0.6.2
 */