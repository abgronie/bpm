/*     */ package com.hotent.core.db.datasource;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/*     */ 
/*     */ public class DynamicDataSource extends AbstractRoutingDataSource
/*     */ {
/*     */   protected Object determineCurrentLookupKey()
/*     */   {
/*  32 */     return DbContextHolder.getDataSource();
/*     */   }
/*     */ 
/*     */   public void setTargetDataSources(Map<Object, Object> targetDataSources)
/*     */   {
/*  37 */     super.setTargetDataSources(targetDataSources);
/*     */ 
/*  39 */     super.afterPropertiesSet();
/*     */   }
/*     */ 
/*     */   private static Object getValue(Object instance, String fieldName) throws IllegalAccessException, NoSuchFieldException {
/*  43 */     Field field = AbstractRoutingDataSource.class.getDeclaredField(fieldName);
/*     */ 
/*  45 */     field.setAccessible(true);
/*  46 */     return field.get(instance);
/*     */   }
/*     */ 
/*     */   public void addDataSource(String key, Object dataSource)
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  58 */     Map targetDataSources = (Map)getValue(this, "targetDataSources");
/*  59 */     boolean rtn = isDataSourceExist(key);
/*  60 */     if (rtn)
/*     */     {
/*  62 */       this.logger.info("datasource name :" + key + " 数据源成功更新");
/*     */     }
/*  64 */     else this.logger.info("datasource name :" + key + " 成功加入数据源");
/*     */ 
/*  66 */     targetDataSources.put(key, dataSource);
/*  67 */     setTargetDataSources(targetDataSources);
/*     */   }
/*     */ 
/*     */   public boolean isDataSourceExist(String key)
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  79 */     Map targetDataSources = (Map)getValue(this, "targetDataSources");
/*     */ 
/*  81 */     return targetDataSources.containsKey(key);
/*     */   }
/*     */ 
/*     */   public void removeDataSource(String key)
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  92 */     Map targetDataSources = (Map)getValue(this, "targetDataSources");
/*     */ 
/*  94 */     if ((key.equals("dataSource")) || (key.equals("dataSource_Default"))) {
/*  95 */       throw new DataSourceException("datasource name :" + key + " can't be removed!");
/*     */     }
/*  97 */     targetDataSources.remove(key);
/*  98 */     setTargetDataSources(targetDataSources);
/*     */   }
/*     */ 
/*     */   public Map<Object, Object> getDataSource()
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/* 109 */     Map targetDataSources = (Map)getValue(this, "targetDataSources");
/* 110 */     return targetDataSources;
/*     */   }
/*     */ 
/*     */   public Logger getParentLogger()
/*     */   {
/* 116 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.datasource.DynamicDataSource
 * JD-Core Version:    0.6.2
 */