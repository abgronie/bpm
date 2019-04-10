/*    */ package com.hotent.core.util.jsonobject.support;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class BooleanSerializer
/*    */   implements JsonDeserializer<Boolean>, JsonSerializer<Boolean>
/*    */ {
/*    */   public Boolean deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
/*    */     throws JsonParseException
/*    */   {
/* 15 */     if (json.toString().equals("\"1\""))
/* 16 */       return Boolean.valueOf(true);
/* 17 */     if (json.toString().equals("\"0\"")) {
/* 18 */       return Boolean.valueOf(false);
/*    */     }
/* 20 */     return Boolean.valueOf(json.getAsBoolean());
/*    */   }
/*    */ 
/*    */   public JsonElement serialize(Boolean arg0, Type arg1, JsonSerializationContext arg2) {
/* 24 */     return new JsonPrimitive(arg0.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.jsonobject.support.BooleanSerializer
 * JD-Core Version:    0.6.2
 */