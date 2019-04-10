/*     */ package com.hotent.core.table;
/*     */ 
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.mybatis.dialect.DB2Dialect;
/*     */ import com.hotent.core.mybatis.dialect.DmDialect;
/*     */ import com.hotent.core.mybatis.dialect.H2Dialect;
/*     */ import com.hotent.core.mybatis.dialect.KingBaseDialect;
/*     */ import com.hotent.core.mybatis.dialect.MySQLDialect;
/*     */ import com.hotent.core.mybatis.dialect.OracleDialect;
/*     */ import com.hotent.core.mybatis.dialect.SQLServer2005Dialect;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ 
/*     */ public class DialectFactoryBean
/*     */   implements FactoryBean<Dialect>
/*     */ {
/*     */   private Dialect dialect;
/*  41 */   private String dbType = "mysql";
/*     */ 
/*     */   public void setDbType(String dbType)
/*     */   {
/*  38 */     this.dbType = dbType;
/*     */   }
/*     */ 
/*     */   public Dialect getObject()
/*     */     throws Exception
/*     */   {
/*  45 */     this.dialect = getDialect(this.dbType);
/*  46 */     return this.dialect;
/*     */   }
/*     */ 
/*     */   public static Dialect getDialect(String dbType)
/*     */     throws Exception
/*     */   {
/*  57 */     Dialect dialect = null;
/*  58 */     if (dbType.equals("oracle"))
/*  59 */       dialect = new OracleDialect();
/*  60 */     else if (dbType.equals("mssql"))
/*  61 */       dialect = new SQLServer2005Dialect();
/*  62 */     else if (dbType.equals("db2"))
/*  63 */       dialect = new DB2Dialect();
/*  64 */     else if (dbType.equals("mysql"))
/*  65 */       dialect = new MySQLDialect();
/*  66 */     else if (dbType.equals("h2"))
/*  67 */       dialect = new H2Dialect();
/*  68 */     else if (dbType.equals("dm"))
/*  69 */       dialect = new DmDialect();
/*  70 */     else if (dbType.equals("kingbase")) {
/*  71 */       dialect = new KingBaseDialect();
/*     */     }
/*     */     else {
/*  74 */       throw new Exception("没有设置合适的数据库类型");
/*     */     }
/*  76 */     return dialect;
/*     */   }
/*     */ 
/*     */   public static Dialect getDialectByDriverClassName(String driverClassName)
/*     */     throws Exception
/*     */   {
/*  87 */     Dialect dialect = null;
/*  88 */     if (driverClassName.contains("oracle"))
/*  89 */       dialect = new OracleDialect();
/*  90 */     else if (driverClassName.equals("sqlserver"))
/*  91 */       dialect = new SQLServer2005Dialect();
/*  92 */     else if (driverClassName.equals("db2"))
/*  93 */       dialect = new DB2Dialect();
/*  94 */     else if (driverClassName.equals("mysql"))
/*  95 */       dialect = new MySQLDialect();
/*  96 */     else if (driverClassName.equals("h2"))
/*  97 */       dialect = new H2Dialect();
/*  98 */     else if (driverClassName.equals("dm"))
/*  99 */       dialect = new DmDialect();
/* 100 */     else if (driverClassName.equals("kingbase"))
/* 101 */       dialect = new KingBaseDialect();
/*     */     else {
/* 103 */       throw new Exception("没有设置合适的数据库类型");
/*     */     }
/* 105 */     return dialect;
/*     */   }
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/* 110 */     return Dialect.class;
/*     */   }
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/* 115 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.DialectFactoryBean
 * JD-Core Version:    0.6.2
 */