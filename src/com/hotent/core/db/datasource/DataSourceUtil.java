/*     */ package com.hotent.core.db.datasource;
/*     */ 
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import java.util.Map;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.commons.dbcp.BasicDataSource;
/*     */ 
/*     */ public class DataSourceUtil
/*     */ {
/*     */   public static final String GLOBAL_DATASOURCE = "dataSource";
/*     */   public static final String DEFAULT_DATASOURCE = "dataSource_Default";
/*     */   public static final String TARGET_DATASOURCES = "targetDataSources";
/*     */ 
/*     */   public static void addDataSource(String key, DataSource dataSource)
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  42 */     DynamicDataSource dynamicDataSource = (DynamicDataSource)AppUtil.getBean("dataSource");
/*  43 */     dynamicDataSource.addDataSource(key, dataSource);
/*     */   }
/*     */ 
/*     */   public static void addDataSource(String alias, String driverClassName, String url, String username, String password)
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  64 */     BasicDataSource ds = createDataSource(driverClassName, url, username, password);
/*  65 */     addDataSource(alias, ds);
/*     */   }
/*     */ 
/*     */   public static void removeDataSource(String key)
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  77 */     DynamicDataSource dynamicDataSource = (DynamicDataSource)AppUtil.getBean("dataSource");
/*  78 */     dynamicDataSource.removeDataSource(key);
/*     */   }
/*     */ 
/*     */   public static Map<Object, Object> getDataSources()
/*     */     throws IllegalAccessException, NoSuchFieldException
/*     */   {
/*  89 */     DynamicDataSource dynamicDataSource = (DynamicDataSource)AppUtil.getBean("dataSource");
/*  90 */     return dynamicDataSource.getDataSource();
/*     */   }
/*     */ 
/*     */   public static BasicDataSource createDataSource(String driverClassName, String url, String username, String password)
/*     */   {
/* 103 */     BasicDataSource dataSource = new BasicDataSource();
/* 104 */     dataSource.setDriverClassName(driverClassName);
/* 105 */     dataSource.setUrl(url);
/* 106 */     dataSource.setUsername(username);
/* 107 */     dataSource.setPassword(password);
/* 108 */     dataSource.setTestWhileIdle(true);
/* 109 */     return dataSource;
/*     */   }
/*     */ 
/*     */   public static DataSource getDataSourceByAlias(String alias)
/*     */     throws Exception
/*     */   {
/* 124 */     Map map = getDataSources();
/* 125 */     DataSource ds = (DataSource)map.get(alias);
/* 126 */     if (ds == null) return (DataSource)map.get("dataSource_Default");
/* 127 */     return ds;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.datasource.DataSourceUtil
 * JD-Core Version:    0.6.2
 */