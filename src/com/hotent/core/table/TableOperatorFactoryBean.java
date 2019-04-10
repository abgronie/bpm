/*     */ package com.hotent.core.table;
/*     */ 
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.table.impl.Db2TableOperator;
/*     */ import com.hotent.core.table.impl.DmTableOperator;
/*     */ import com.hotent.core.table.impl.H2TableOperator;
/*     */ import com.hotent.core.table.impl.KingBaseTableOperator;
/*     */ import com.hotent.core.table.impl.MysqlTableOperator;
/*     */ import com.hotent.core.table.impl.OracleTableOperator;
/*     */ import com.hotent.core.table.impl.SqlserverTableOperator;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class TableOperatorFactoryBean
/*     */   implements FactoryBean<ITableOperator>
/*     */ {
/*     */   private ITableOperator tableOperator;
/*  34 */   private String dbType = "mysql";
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private Dialect dialect;
/*     */ 
/*     */   public ITableOperator getObject()
/*     */     throws Exception
/*     */   {
/*  42 */     if (this.dbType.equals("oracle")) {
/*  43 */       this.tableOperator = new OracleTableOperator();
/*     */     }
/*  45 */     else if (this.dbType.equals("mssql")) {
/*  46 */       this.tableOperator = new SqlserverTableOperator();
/*     */     }
/*  48 */     else if (this.dbType.equals("db2")) {
/*  49 */       this.tableOperator = new Db2TableOperator();
/*     */     }
/*  51 */     else if (this.dbType.equals("mysql")) {
/*  52 */       this.tableOperator = new MysqlTableOperator();
/*     */     }
/*  54 */     else if (this.dbType.equals("h2")) {
/*  55 */       this.tableOperator = new H2TableOperator();
/*     */     }
/*  57 */     else if (this.dbType.equals("kingbase")) {
/*  58 */       this.tableOperator = new KingBaseTableOperator();
/*     */     }
/*  60 */     else if (this.dbType.equals("dm")) {
/*  61 */       this.tableOperator = new DmTableOperator();
/*     */     }
/*     */     else {
/*  64 */       throw new Exception("没有设置合适的数据库类型");
/*     */     }
/*     */ 
/*  67 */     this.tableOperator.setDbType(this.dbType);
/*  68 */     this.tableOperator.setJdbcTemplate(this.jdbcTemplate);
/*  69 */     this.tableOperator.setDialect(this.dialect);
/*  70 */     return this.tableOperator;
/*     */   }
/*     */ 
/*     */   public void setDbType(String dbType)
/*     */   {
/*  80 */     this.dbType = dbType;
/*     */   }
/*     */ 
/*     */   public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
/*     */   {
/*  89 */     this.jdbcTemplate = jdbcTemplate;
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect)
/*     */   {
/*  94 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   public Class<?> getObjectType()
/*     */   {
/* 102 */     return ITableOperator.class;
/*     */   }
/*     */ 
/*     */   public boolean isSingleton()
/*     */   {
/* 108 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.TableOperatorFactoryBean
 * JD-Core Version:    0.6.2
 */