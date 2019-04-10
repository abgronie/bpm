/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import com.hotent.core.api.system.ISysDataSourceService;
/*    */ import com.hotent.core.api.system.model.ISysDataSource;
/*    */ import com.hotent.core.db.datasource.DbContextHolder;
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ import com.hotent.core.mybatis.dialect.DB2Dialect;
/*    */ import com.hotent.core.mybatis.dialect.DmDialect;
/*    */ import com.hotent.core.mybatis.dialect.H2Dialect;
/*    */ import com.hotent.core.mybatis.dialect.KingBaseDialect;
/*    */ import com.hotent.core.mybatis.dialect.MySQLDialect;
/*    */ import com.hotent.core.mybatis.dialect.OracleDialect;
/*    */ import com.hotent.core.mybatis.dialect.SQLServer2005Dialect;
/*    */ 
/*    */ public class DialectUtil
/*    */ {
/*    */   public static Dialect getDialect(String dbType)
/*    */     throws Exception
/*    */   {
/*    */     Dialect dialect;
/* 38 */     if (dbType.equals("oracle")) {
/* 39 */       dialect = new OracleDialect();
/*    */     }
/*    */     else
/*    */     {
/* 40 */       if (dbType.equals("mssql")) {
/* 41 */         dialect = new SQLServer2005Dialect();
/*    */       }
/*    */       else
/*    */       {
/* 42 */         if (dbType.equals("db2")) {
/* 43 */           dialect = new DB2Dialect();
/*    */         }
/*    */         else
/*    */         {
/* 44 */           if (dbType.equals("mysql")) {
/* 45 */             dialect = new MySQLDialect();
/*    */           }
/*    */           else
/*    */           {
/* 46 */             if (dbType.equals("h2")) {
/* 47 */               dialect = new H2Dialect();
/*    */             }
/*    */             else
/*    */             {
/* 48 */               if (dbType.equals("dm")) {
/* 49 */                 dialect = new DmDialect();
/*    */               }
/*    */               else
/*    */               {
/* 50 */                 if (dbType.equals("kingbase"))
/* 51 */                   dialect = new KingBaseDialect();
/*    */                 else
/* 53 */                   throw new Exception("没有设置合适的数据库类型");
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 55 */     return dialect;
/*    */   }
/*    */ 
/*    */   public static Dialect getCurrentDialect()
/*    */     throws Exception
/*    */   {
/* 67 */     return getDialect(DbContextHolder.getDbType());
/*    */   }
/*    */ 
/*    */   public static Dialect getDialect(ISysDataSource sysDataSource) throws Exception {
/* 71 */     return getDialect(sysDataSource.getDbType());
/*    */   }
/*    */ 
/*    */   public static Dialect getDialectByDataSourceAlias(String alias) throws Exception {
/* 75 */     ISysDataSourceService sysDataSourceService = (ISysDataSourceService)AppUtil.getBean(ISysDataSourceService.class);
/* 76 */     ISysDataSource sysDataSource = sysDataSourceService.getByAlias(alias);
/* 77 */     if (sysDataSource == null) {
/* 78 */       return getDialect(AppConfigUtil.get("jdbc.dbType"));
/*    */     }
/* 80 */     return getDialect(sysDataSource);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.DialectUtil
 * JD-Core Version:    0.6.2
 */