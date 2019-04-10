/*     */ package com.hotent.core.util.jsonobject;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import com.hotent.core.util.jsonobject.support.BooleanSerializer;
/*     */ import com.hotent.core.util.jsonobject.support.DateSerializer;
/*     */ import com.hotent.core.util.jsonobject.support.JsonObjectSerializer;
/*     */ import com.hotent.core.util.jsonobject.support.SuperclassExclusionStrategy;
/*     */ import com.hotent.core.web.util.RequestUtil;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class JSONObjectUtil
/*     */ {
/*     */   public static <C> C toBean(JSONObject jsonObject, Class<C> cls)
/*     */   {
/*  54 */     return toBean(jsonObject.toString(), cls);
/*     */   }
/*     */ 
/*     */   public static <C> C toBean(String jsonStr, Class<C> cls)
/*     */   {
/*     */     try
/*     */     {
/*  68 */       GsonBuilder gsonBuilder = new GsonBuilder();
/*  69 */       gsonBuilder.registerTypeAdapter(String.class, new JsonObjectSerializer()).registerTypeAdapter(Date.class, new DateSerializer()).registerTypeAdapter(Boolean.class, new BooleanSerializer()).addDeserializationExclusionStrategy(new SuperclassExclusionStrategy()).addSerializationExclusionStrategy(new SuperclassExclusionStrategy());
/*  70 */       Gson gson = gsonBuilder.create();
/*  71 */       return gson.fromJson(jsonStr, cls);
/*     */     } catch (Exception e) {
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   public static <C> C toBean(JSONObject jsonObject, Class<C> cls, Map<String, Class<?>> map)
/*     */   {
/*  98 */     Map jaMap = new HashMap();
/*  99 */     for (String key : map.keySet()) {
/* 100 */       JSONArray ja = jsonObject.getJSONArray(key);
/* 101 */       jaMap.put(key, ja);
/* 102 */       jsonObject.remove(key);
/*     */     }
/*     */ 
/* 105 */     C main = toBean(jsonObject, cls);
/*     */     try {
/* 107 */       for (String key : map.keySet()) {
/* 108 */         Class c = (Class)map.get(key);
/* 109 */         JSONArray ja = (JSONArray)jaMap.get(key);
/* 110 */         List subList = toBeanList(ja, c);
/* 111 */         Method method = cls.getMethod("set" + StringUtil.makeFirstLetterUpperCase(key), new Class[] { List.class });
/* 112 */         method.invoke(main, new Object[] { subList });
/*     */       }
/*     */     } catch (Exception e) {
/* 115 */       return null;
/*     */     }
/* 117 */     return main;
/*     */   }
/*     */ 
/*     */   public static <C> C toBean(String json, Class<C> cls, Map<String, Class<?>> map)
/*     */   {
/* 133 */     return toBean(JSONObject.fromObject(json), cls, map);
/*     */   }
/*     */ 
/*     */   public static <C> List<C> toBeanList(String arrayStr, Class<C> cls)
/*     */   {
/* 146 */     return toBeanList(JSONArray.fromObject(arrayStr), cls);
/*     */   }
/*     */ 
/*     */   public static <C> List<C> toBeanList(JSONArray jsonArray, Class<C> cls)
/*     */   {
/* 159 */     List list = new ArrayList();
/* 160 */     for (int i = 0; i < jsonArray.size(); i++) {
/* 161 */       JSONObject jsonObject = jsonArray.getJSONObject(i);
/* 162 */       Object o = toBean(jsonObject, cls);
/* 163 */       list.add(o);
/*     */     }
/* 165 */     return list;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getQueryMap(JSONObject jsonObject)
/*     */   {
/* 197 */     Map map = new HashMap();
/*     */ 
/* 199 */     for (Iterator i$ = jsonObject.keySet().iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 200 */       String key = obj.toString();
/* 201 */       String[] values = new String[1];
/* 202 */       values[0] = jsonObject.getString(key);
/*     */ 
/* 204 */       if ((key.equals("sortField")) || (key.equals("orderSeq")) || (key.equals("sortColumns"))) {
/* 205 */         String val = values[0].trim();
/* 206 */         if (StringUtil.isNotEmpty(val)) {
/* 207 */           map.put(key, values[0].trim());
/*     */         }
/*     */       }
/* 210 */       if ((values.length > 0) && (StringUtils.isNotEmpty(values[0]))) {
/* 211 */         if (key.startsWith("Q_")) {
/* 212 */           RequestUtil.addParameter(key, values, map);
/*     */         }
/* 214 */         else if (values.length == 1) {
/* 215 */           String val = values[0].trim();
/* 216 */           if (StringUtil.isNotEmpty(val))
/* 217 */             map.put(key, values[0].trim());
/*     */         }
/*     */         else {
/* 220 */           map.put(key, values);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 226 */     return map;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.jsonobject.JSONObjectUtil
 * JD-Core Version:    0.6.2
 */