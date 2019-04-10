/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class MapUtil
/*     */ {
/*     */   public static String getString(Map<String, Object> map, String field)
/*     */   {
/*  25 */     if (BeanUtils.isEmpty(map)) {
/*  26 */       return "";
/*     */     }
/*  28 */     field = field.toLowerCase();
/*  29 */     Set set = map.keySet();
/*  30 */     Iterator it = set.iterator();
/*  31 */     Hashtable ht = new Hashtable();
/*  32 */     while (it.hasNext()) {
/*  33 */       String key = (String)it.next();
/*  34 */       ht.put(key.toLowerCase(), key);
/*     */     }
/*  36 */     field = (String)ht.get(field);
/*  37 */     Object obj = map.get(field);
/*  38 */     return obj != null ? obj.toString().trim() : "";
/*     */   }
/*     */ 
/*     */   public static long getLong(Map<String, Object> map, String field)
/*     */   {
/*  49 */     String value = getString(map, field);
/*  50 */     if (value.equals(""))
/*  51 */       return -1L;
/*  52 */     return Long.parseLong(value);
/*     */   }
/*     */ 
/*     */   public static int getInt(Map<String, Object> map, String field)
/*     */   {
/*  63 */     String value = getString(map, field);
/*  64 */     if (value.equals(""))
/*  65 */       return -1;
/*  66 */     return Integer.parseInt(value);
/*     */   }
/*     */ 
/*     */   public static float getFloat(Map<String, Object> map, String field)
/*     */   {
/*  77 */     String value = getString(map, field);
/*  78 */     if (value.equals(""))
/*  79 */       return -1.0F;
/*  80 */     return Float.parseFloat(value);
/*     */   }
/*     */ 
/*     */   public static double getDouble(Map<String, Object> map, String field)
/*     */   {
/*  91 */     String value = getString(map, field);
/*  92 */     if (value.equals(""))
/*  93 */       return -1.0D;
/*  94 */     return Double.parseDouble(value);
/*     */   }
/*     */ 
/*     */   public static Object get(Map<String, Object> map, String field) {
/*  98 */     field = field.toLowerCase();
/*  99 */     Set set = map.keySet();
/* 100 */     Iterator it = set.iterator();
/* 101 */     Hashtable ht = new Hashtable();
/* 102 */     while (it.hasNext()) {
/* 103 */       String key = (String)it.next();
/* 104 */       ht.put(key.toLowerCase(), key);
/*     */     }
/* 106 */     field = (String)ht.get(field);
/* 107 */     Object obj = map.get(field);
/* 108 */     return obj;
/*     */   }
/*     */ 
/*     */   public static Object getObject(Map<String, ?> map, String field) {
/* 112 */     field = field.toLowerCase();
/* 113 */     Set set = map.keySet();
/* 114 */     Iterator it = set.iterator();
/* 115 */     Hashtable ht = new Hashtable();
/* 116 */     while (it.hasNext()) {
/* 117 */       String key = (String)it.next();
/* 118 */       ht.put(key.toLowerCase(), key);
/*     */     }
/* 120 */     field = (String)ht.get(field);
/* 121 */     Object obj = map.get(field);
/* 122 */     return obj;
/*     */   }
/*     */ 
/*     */   public static boolean containsKey(Map<String, Object> map, String field)
/*     */   {
/* 135 */     return get(map, field) != null;
/*     */   }
/*     */ 
/*     */   public static void put(Map<String, Object> map, String field, Object val)
/*     */   {
/* 147 */     for (String key : map.keySet())
/* 148 */       if (key.equalsIgnoreCase(field))
/* 149 */         map.put(key, val);
/*     */   }
/*     */ 
/*     */   public static Object transMap2Bean(Map<String, Object> map)
/*     */   {
/* 164 */     Object obj = new Object();
/* 165 */     if (map == null)
/* 166 */       return null;
/*     */     try
/*     */     {
/* 169 */       BeanUtils.populate(obj, map);
/*     */     } catch (Exception e) {
/* 171 */       System.out.println("transMap2Bean2 Error " + e);
/*     */     }
/* 173 */     return obj;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> transBean2Map(Object obj)
/*     */   {
/* 187 */     if (obj == null) {
/* 188 */       return null;
/*     */     }
/* 190 */     Map map = new LinkedHashMap();
/*     */     try {
/* 192 */       BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
/* 193 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 194 */       for (PropertyDescriptor property : propertyDescriptors) {
/* 195 */         String key = property.getName();
/*     */ 
/* 198 */         if (!key.equals("class"))
/*     */         {
/* 200 */           Method getter = property.getReadMethod();
/* 201 */           Object value = getter.invoke(obj, new Object[0]);
/*     */ 
/* 203 */           map.put(key, value);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 208 */       System.out.println("transBean2Map Error " + e);
/*     */     }
/*     */ 
/* 211 */     return map;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.MapUtil
 * JD-Core Version:    0.6.2
 */