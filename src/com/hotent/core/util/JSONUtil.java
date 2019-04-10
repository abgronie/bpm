/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import net.sf.json.JSONArray;
/*    */ import net.sf.json.JSONNull;
/*    */ import net.sf.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class JSONUtil
/*    */ {
/*    */   public static boolean isEmpty(Object obj)
/*    */   {
/* 25 */     if (obj == null)
/* 26 */       return true;
/* 27 */     if ((obj instanceof JSONObject))
/* 28 */       return ((JSONObject)obj).isNullObject();
/* 29 */     if ((obj instanceof JSONArray)) {
/* 30 */       return ((JSONArray)obj).isEmpty();
/*    */     }
/* 32 */     return JSONNull.getInstance().equals(obj);
/*    */   }
/*    */ 
/*    */   public static boolean isNotEmpty(JSONObject obj)
/*    */   {
/* 42 */     return !isEmpty(obj);
/*    */   }
/*    */ 
/*    */   public static String escapeSpecialChar(String str)
/*    */   {
/* 51 */     StringBuffer sb = new StringBuffer();
/* 52 */     for (int i = 0; i < str.length(); i++)
/*    */     {
/* 54 */       char c = str.charAt(i);
/* 55 */       switch (c) {
/*    */       case '"':
/* 57 */         sb.append("\\\"");
/* 58 */         break;
/*    */       case '\\':
/* 60 */         sb.append("\\\\");
/* 61 */         break;
/*    */       case '/':
/* 63 */         sb.append("\\/");
/* 64 */         break;
/*    */       case '\b':
/* 66 */         sb.append("\\b");
/* 67 */         break;
/*    */       case '\f':
/* 69 */         sb.append("\\f");
/* 70 */         break;
/*    */       case '\n':
/* 72 */         sb.append("\\n");
/* 73 */         break;
/*    */       case '\r':
/* 75 */         sb.append("\\r");
/* 76 */         break;
/*    */       case '\t':
/* 78 */         sb.append("\\t");
/* 79 */         break;
/*    */       default:
/* 81 */         sb.append(c);
/*    */       }
/*    */     }
/*    */ 
/* 85 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 90 */     Logger logger = LoggerFactory.getLogger(JSONUtil.class);
/* 91 */     JSONArray jsonAry = new JSONArray();
/* 92 */     JSONObject json = new JSONObject();
/* 93 */     jsonAry.add(json);
/* 94 */     logger.info(String.valueOf(isEmpty(jsonAry)));
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.JSONUtil
 * JD-Core Version:    0.6.2
 */