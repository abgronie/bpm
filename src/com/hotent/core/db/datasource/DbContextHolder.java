/*    */ package com.hotent.core.db.datasource;
/*    */ 
/*    */ import com.hotent.core.api.system.ISysDataSourceService;
/*    */ import com.hotent.core.api.system.model.ISysDataSource;
/*    */ import com.hotent.core.util.AppConfigUtil;
/*    */ import com.hotent.core.util.AppUtil;
/*    */ import com.hotent.core.util.StringUtil;
/*    */ 
/*    */ public class DbContextHolder
/*    */ {
/* 25 */   private static final ThreadLocal<String> contextHolderAlias = new ThreadLocal();
/* 26 */   private static final ThreadLocal<String> contextHolderDbType = new ThreadLocal();
/*    */ 
/*    */   public static void setDataSource(String dbAlias, String dbType)
/*    */   {
/* 38 */     contextHolderAlias.set(dbAlias);
/* 39 */     contextHolderDbType.set(dbType);
/*    */   }
/*    */ 
/*    */   public static void setDefaultDataSource() {
/* 43 */     String dbType = AppConfigUtil.get("jdbc.dbType");
/* 44 */     contextHolderAlias.set("dataSource_Default");
/* 45 */     contextHolderDbType.set(dbType);
/*    */   }
/*    */ 
/*    */   public static String getDataSource()
/*    */   {
/* 54 */     String str = (String)contextHolderAlias.get();
/* 55 */     return str;
/*    */   }
/*    */ 
/*    */   public static String getDbType() {
/* 59 */     String str = (String)contextHolderDbType.get();
/* 60 */     return str;
/*    */   }
/*    */ 
/*    */   public static void clearDataSource()
/*    */   {
/* 67 */     contextHolderAlias.remove();
/* 68 */     contextHolderDbType.remove();
/*    */   }
/*    */ 
/*    */   public static void setDataSource(String alias)
/*    */   {
/* 80 */     if (StringUtil.isEmpty(alias)) return;
/* 81 */     ISysDataSourceService sourceService = (ISysDataSourceService)AppUtil.getBean(ISysDataSourceService.class);
/*    */ 
/* 83 */     ISysDataSource sysDataSource = sourceService.getByAlias(alias);
/* 84 */     if (sysDataSource == null)
/* 85 */       return;
/* 86 */     setDataSource(alias, sysDataSource.getDbType());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.datasource.DbContextHolder
 * JD-Core Version:    0.6.2
 */