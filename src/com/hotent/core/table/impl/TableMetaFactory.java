/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.api.system.ISysDataSourceService;
/*     */ import com.hotent.core.api.system.model.ISysDataSource;
/*     */ import com.hotent.core.db.datasource.DbContextHolder;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.util.AppConfigUtil;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ 
/*     */ public class TableMetaFactory
/*     */ {
/*     */   public static BaseTableMeta getMetaData(String dsName)
/*     */     throws Exception
/*     */   {
/*  38 */     String dbType = getDbTypeBySysDataSourceAlias(dsName);
/*     */ 
/*  40 */     BaseTableMeta meta = null;
/*  41 */     if (dbType.equals("oracle"))
/*     */     {
/*  43 */       meta = (BaseTableMeta)AppUtil.getBean(OracleTableMeta.class);
/*  44 */     } else if (dbType.equals("mysql"))
/*     */     {
/*  46 */       meta = (BaseTableMeta)AppUtil.getBean(MySqlTableMeta.class);
/*  47 */     } else if (dbType.equals("mssql"))
/*  48 */       meta = (BaseTableMeta)AppUtil.getBean(SqlServerTableMeta.class);
/*  49 */     else if (dbType.equals("db2"))
/*  50 */       meta = (BaseTableMeta)AppUtil.getBean(Db2TableMeta.class);
/*  51 */     else if (dbType.equals("h2"))
/*  52 */       meta = (BaseTableMeta)AppUtil.getBean(H2TableMeta.class);
/*  53 */     else if (dbType.equals("dm"))
/*  54 */       meta = (BaseTableMeta)AppUtil.getBean(DmTableMeta.class);
/*  55 */     else if (dbType.equals("kingbase"))
/*  56 */       meta = (BaseTableMeta)AppUtil.getBean(KingBaseTableMeta.class);
/*     */     else {
/*  58 */       throw new Exception("未知的数据库类型");
/*     */     }
/*     */ 
/*  61 */     DbContextHolder.setDataSource(dsName);
/*  62 */     return meta;
/*     */   }
/*     */ 
/*     */   public static IDbView getDbView(String dsName)
/*     */     throws Exception
/*     */   {
/*  73 */     String dbType = getDbTypeBySysDataSourceAlias(dsName);
/*  74 */     IDbView meta = null;
/*  75 */     if (dbType.equals("oracle"))
/*  76 */       meta = (IDbView)AppUtil.getBean(OracleDbView.class);
/*  77 */     else if (dbType.equals("mssql"))
/*  78 */       meta = (IDbView)AppUtil.getBean(SqlserverDbView.class);
/*  79 */     else if (dbType.equals("mysql"))
/*  80 */       meta = (IDbView)AppUtil.getBean(MysqlDbView.class);
/*  81 */     else if (dbType.equals("db2"))
/*  82 */       meta = (IDbView)AppUtil.getBean(Db2DbView.class);
/*  83 */     else if (dbType.equals("h2"))
/*  84 */       meta = (IDbView)AppUtil.getBean(H2DbView.class);
/*  85 */     else if (dbType.equals("dm"))
/*  86 */       meta = (IDbView)AppUtil.getBean(DmDbView.class);
/*  87 */     else if (dbType.equals("kingbase"))
/*  88 */       meta = (IDbView)AppUtil.getBean(KingBaseDbView.class);
/*     */     else {
/*  90 */       throw new Exception("未知的数据库类型");
/*     */     }
/*  92 */     DbContextHolder.setDataSource(dsName);
/*  93 */     return meta;
/*     */   }
/*     */ 
/*     */   private static String getDbTypeBySysDataSourceAlias(String alias)
/*     */   {
/* 105 */     ISysDataSource sysDataSource = null;
/* 106 */     ISysDataSourceService sysDataSourceService = (ISysDataSourceService)AppUtil.getBean(ISysDataSourceService.class);
/* 107 */     sysDataSource = sysDataSourceService.getByAlias(alias);
/*     */ 
/* 109 */     String dbType = AppConfigUtil.get("jdbc.dbType");
/* 110 */     if (sysDataSource != null) {
/* 111 */       dbType = sysDataSource.getDbType();
/*     */     }
/*     */ 
/* 114 */     return dbType;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.TableMetaFactory
 * JD-Core Version:    0.6.2
 */