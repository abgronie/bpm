/*     */ package com.hotent.core.table;
/*     */ 
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.util.DialectUtil;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.sql.DataSource;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public abstract class BaseDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   protected String currentDb;
/*     */ 
/*     */   public abstract String getType(String paramString);
/*     */ 
/*     */   public TableModel getModelByViewName(String viewName)
/*     */     throws SQLException
/*     */   {
/*  57 */     Connection conn = this.jdbcTemplate.getDataSource().getConnection();
/*     */ 
/*  59 */     Statement stmt = null;
/*  60 */     ResultSet rs = null;
/*     */ 
/*  62 */     TableModel tableModel = new TableModel();
/*  63 */     tableModel.setName(viewName);
/*  64 */     tableModel.setComment(viewName);
/*     */     try
/*     */     {
/*  68 */       stmt = conn.createStatement();
/*  69 */       rs = stmt.executeQuery("select * from " + viewName);
/*  70 */       ResultSetMetaData metadata = rs.getMetaData();
/*     */ 
/*  72 */       int count = metadata.getColumnCount();
/*  73 */       for (int i = 1; i <= count; i++) {
/*  74 */         ColumnModel columnModel = new ColumnModel();
/*  75 */         String columnName = metadata.getColumnName(i);
/*  76 */         String typeName = metadata.getColumnTypeName(i);
/*  77 */         String dataType = getType(typeName);
/*  78 */         columnModel.setName(columnName);
/*  79 */         columnModel.setColumnType(dataType);
/*  80 */         columnModel.setComment(columnName);
/*  81 */         tableModel.addColumnModel(columnModel);
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/*  85 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  88 */         if (rs != null) {
/*  89 */           rs.close();
/*     */         }
/*  91 */         if (stmt != null) {
/*  92 */           stmt.close();
/*     */         }
/*  94 */         if (conn != null)
/*  95 */           conn.close();
/*     */       }
/*     */       catch (SQLException e) {
/*  98 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 101 */     return tableModel;
/*     */   }
/*     */ 
/*     */   protected <T> List<T> getForList(String sql, PageBean pageBean, Class<T> elementType, String dbType)
/*     */     throws Exception
/*     */   {
/* 107 */     if (pageBean != null) {
/* 108 */       int pageSize = pageBean.getPageSize();
/* 109 */       int offset = pageBean.getFirst();
/* 110 */       Dialect dialect = DialectUtil.getDialect(dbType);
/* 111 */       String pageSql = dialect.getLimitString(sql, offset, pageSize);
/* 112 */       String totalSql = dialect.getCountSql(sql);
/* 113 */       List list = this.jdbcTemplate.queryForList(pageSql, elementType);
/* 114 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 115 */       pageBean.setTotalCount(total);
/* 116 */       return list;
/*     */     }
/* 118 */     return this.jdbcTemplate.queryForList(sql, elementType);
/*     */   }
/*     */ 
/*     */   protected <T> List<T> getForList(String sql, PageBean pageBean, RowMapper<T> rowMapper, String dbType)
/*     */     throws Exception
/*     */   {
/* 125 */     if (pageBean != null) {
/* 126 */       int pageSize = pageBean.getPageSize();
/* 127 */       int offset = pageBean.getFirst();
/* 128 */       Dialect dialect = DialectUtil.getDialect(dbType);
/* 129 */       String pageSql = dialect.getLimitString(sql, offset, pageSize);
/* 130 */       String totalSql = dialect.getCountSql(sql);
/* 131 */       List list = this.jdbcTemplate.query(pageSql, rowMapper);
/* 132 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 133 */       pageBean.setTotalCount(total);
/* 134 */       return list;
/*     */     }
/* 136 */     return this.jdbcTemplate.query(sql, rowMapper);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.BaseDbView
 * JD-Core Version:    0.6.2
 */