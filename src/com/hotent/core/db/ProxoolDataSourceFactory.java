/*     */ package com.hotent.core.db;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.ibatis.datasource.DataSourceFactory;
/*     */ import org.logicalcobwebs.proxool.ProxoolDataSource;
/*     */ 
/*     */ public class ProxoolDataSourceFactory
/*     */   implements DataSourceFactory
/*     */ {
/*     */   private static ProxoolDataSource dataSource;
/*     */ 
/*     */   public DataSource getDataSource()
/*     */   {
/*  18 */     return dataSource;
/*     */   }
/*     */ 
/*     */   public static DataSource createDataSource(Map map)
/*     */   {
/*  37 */     dataSource = new ProxoolDataSource();
/*     */ 
/*  39 */     String driver = "";
/*  40 */     String driverUrl = "";
/*  41 */     String user = "";
/*  42 */     String alias = "";
/*  43 */     if (map.containsKey("driver"))
/*  44 */       driver = (String)map.get("driver");
/*     */     else {
/*  46 */       driver = (String)map.get("driverClassName");
/*     */     }
/*  48 */     dataSource.setDriver(driver);
/*     */ 
/*  50 */     if (map.containsKey("driverUrl"))
/*  51 */       driverUrl = (String)map.get("driverUrl");
/*     */     else {
/*  53 */       driverUrl = (String)map.get("url");
/*     */     }
/*  55 */     dataSource.setDriverUrl(driverUrl);
/*     */ 
/*  57 */     if (map.containsKey("user"))
/*  58 */       user = (String)map.get("user");
/*     */     else {
/*  60 */       user = (String)map.get("username");
/*     */     }
/*  62 */     dataSource.setUser(user);
/*     */ 
/*  64 */     dataSource.setPassword((String)map.get("password"));
/*     */ 
/*  66 */     if (map.containsKey("alias"))
/*  67 */       alias = (String)map.get("alias");
/*     */     else {
/*  69 */       alias = driverUrl + user;
/*     */     }
/*  71 */     dataSource.setAlias(driverUrl);
/*     */ 
/*  74 */     if (map.containsKey("house-keeping-sleep-time")) {
/*  75 */       dataSource.setHouseKeepingSleepTime(Integer.parseInt(map.get("house-keeping-sleep-time").toString()));
/*     */     }
/*     */ 
/*  78 */     if (map.containsKey("house-keeping-test-sql"))
/*  79 */       dataSource.setHouseKeepingTestSql(map.get("house-keeping-test-sql").toString());
/*     */     else {
/*  81 */       dataSource.setHouseKeepingTestSql("select 1 from SYS_ACCEPT_IP");
/*     */     }
/*     */ 
/*  86 */     if (map.containsKey("maximum-active-time")) {
/*  87 */       dataSource.setMaximumActiveTime(Integer.parseInt(map.get("maximum-active-time").toString()));
/*     */     }
/*     */ 
/*  90 */     if (map.containsKey("maximum-connection-count")) {
/*  91 */       dataSource.setMaximumConnectionCount(Integer.parseInt(map.get("maximum-connection-count").toString()));
/*     */     }
/*     */ 
/*  94 */     if (map.containsKey("maximum-connection-lifetime")) {
/*  95 */       dataSource.setMaximumConnectionLifetime(Integer.parseInt(map.get("maximum-connection-lifetime").toString()));
/*     */     }
/*     */ 
/*  98 */     if (map.containsKey("minimum-connection-count")) {
/*  99 */       dataSource.setMaximumConnectionLifetime(Integer.parseInt(map.get("minimum-connection-count").toString()));
/*     */     }
/*     */ 
/* 102 */     if (map.containsKey("overload-without-refusal-lifetime")) {
/* 103 */       dataSource.setMaximumConnectionLifetime(Integer.parseInt(map.get("overload-without-refusal-lifetime").toString()));
/*     */     }
/* 105 */     return dataSource;
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties paramProperties)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.ProxoolDataSourceFactory
 * JD-Core Version:    0.6.2
 */