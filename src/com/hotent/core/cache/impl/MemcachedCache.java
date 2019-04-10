/*     */ package com.hotent.core.cache.impl;
/*     */ 
/*     */ import com.hotent.core.cache.ICache;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import net.rubyeye.xmemcached.MemcachedClient;
/*     */ import net.rubyeye.xmemcached.exception.MemcachedException;
/*     */ 
/*     */ public class MemcachedCache
/*     */   implements ICache
/*     */ {
/*  10 */   private int timeOut = 0;
/*     */   private MemcachedClient memcachedClient;
/*     */ 
/*     */   public void setTimeOut(int timeOut)
/*     */   {
/*  15 */     this.timeOut = timeOut;
/*     */   }
/*     */ 
/*     */   public void setMemcachedClient(MemcachedClient tmp) {
/*  19 */     this.memcachedClient = tmp;
/*     */   }
/*     */ 
/*     */   public synchronized void add(String key, Object obj, int timeout)
/*     */   {
/*     */     try {
/*  25 */       this.memcachedClient.set(key, timeout, obj);
/*     */     }
/*     */     catch (TimeoutException e) {
/*  28 */       e.printStackTrace();
/*     */     }
/*     */     catch (InterruptedException e) {
/*  31 */       e.printStackTrace();
/*     */     }
/*     */     catch (MemcachedException e) {
/*  34 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void delByKey(String key)
/*     */   {
/*     */     try
/*     */     {
/*  42 */       this.memcachedClient.delete(key);
/*     */     }
/*     */     catch (TimeoutException e) {
/*  45 */       e.printStackTrace();
/*     */     }
/*     */     catch (InterruptedException e) {
/*  48 */       e.printStackTrace();
/*     */     }
/*     */     catch (MemcachedException e) {
/*  51 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearAll()
/*     */   {
/*     */     try
/*     */     {
/*  59 */       this.memcachedClient.flushAll();
/*     */     }
/*     */     catch (TimeoutException e) {
/*  62 */       e.printStackTrace();
/*     */     }
/*     */     catch (InterruptedException e) {
/*  65 */       e.printStackTrace();
/*     */     }
/*     */     catch (MemcachedException e) {
/*  68 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized Object getByKey(String key)
/*     */   {
/*     */     try
/*     */     {
/*  76 */       return this.memcachedClient.get(key);
/*     */     }
/*     */     catch (TimeoutException e) {
/*  79 */       e.printStackTrace();
/*     */     }
/*     */     catch (InterruptedException e) {
/*  82 */       e.printStackTrace();
/*     */     }
/*     */     catch (MemcachedException e) {
/*  85 */       e.printStackTrace();
/*     */     }
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean containKey(String key)
/*     */   {
/*  92 */     Object obj = getByKey(key);
/*  93 */     if (obj == null)
/*  94 */       return false;
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   public synchronized void add(String key, Object obj)
/*     */   {
/*     */     try
/*     */     {
/* 102 */       this.memcachedClient.set(key, 0, obj);
/*     */     }
/*     */     catch (TimeoutException e) {
/* 105 */       e.printStackTrace();
/*     */     }
/*     */     catch (InterruptedException e) {
/* 108 */       e.printStackTrace();
/*     */     }
/*     */     catch (MemcachedException e) {
/* 111 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.cache.impl.MemcachedCache
 * JD-Core Version:    0.6.2
 */