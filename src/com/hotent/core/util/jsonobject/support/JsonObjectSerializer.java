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
/*    */ public class JsonObjectSerializer
/*    */   implements JsonDeserializer<String>, JsonSerializer<String>
/*    */ {
/*    */   public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
/*    */     throws JsonParseException
/*    */   {
/* 16 */     String result = "";
/* 17 */     if (json.isJsonPrimitive()) {
/* 18 */       result = json.getAsString();
/*    */     }
/* 20 */     if ((json.isJsonArray()) || (json.isJsonObject())) {
/* 21 */       result = json.toString();
/*    */     }
/* 23 */     return result;
/*    */   }
/*    */ 
/*    */   public JsonElement serialize(String arg0, Type arg1, JsonSerializationContext arg2)
/*    */   {
/* 28 */     return new JsonPrimitive(arg0);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.jsonobject.support.JsonObjectSerializer
 * JD-Core Version:    0.6.2
 */