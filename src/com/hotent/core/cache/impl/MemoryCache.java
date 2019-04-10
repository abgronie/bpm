/*    */ package com.hotent.core.cache.impl;
/*    */ 
/*    */ import com.hotent.core.cache.ICache;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MemoryCache
/*    */   implements ICache
/*    */ {
/* 19 */   private Map<String, Object> cache = Collections.synchronizedMap(new HashMap());
/*    */ 
/*    */   public synchronized void add(String key, Object obj, int timeout)
/*    */   {
/* 23 */     this.cache.put(key, obj);
/*    */   }
/*    */ 
/*    */   public synchronized void add(String key, Object obj)
/*    */   {
/* 29 */     this.cache.put(key, obj);
/*    */   }
/*    */ 
/*    */   public synchronized void delByKey(String key)
/*    */   {
/* 35 */     this.cache.remove(key);
/*    */   }
/*    */ 
/*    */   public void clearAll()
/*    */   {
/* 41 */     this.cache.clear();
/*    */   }
/*    */ 
/*    */   public synchronized Object getByKey(String key)
/*    */   {
/* 47 */     return this.cache.get(key);
/*    */   }
/*    */ 
/*    */   public boolean containKey(String key)
/*    */   {
/* 52 */     return this.cache.containsKey(key);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.cache.impl.MemoryCache
 * JD-Core Version:    0.6.2
 */