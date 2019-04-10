/*     */ package com.hotent.core.table;
/*     */ 
/*     */ import com.hotent.core.model.TableIndex;
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class AbstractTableOperator
/*     */   implements ITableOperator
/*     */ {
/*     */   protected String dbType;
/*     */   protected JdbcTemplate jdbcTemplate;
/*  24 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */   protected Dialect dialect;
/*     */ 
/*     */   public void setDbType(String dbType)
/*     */   {
/*  33 */     this.dbType = dbType;
/*     */   }
/*     */ 
/*     */   public String getDbType()
/*     */   {
/*  38 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void setJdbcTemplate(JdbcTemplate template)
/*     */   {
/*  43 */     this.jdbcTemplate = template;
/*     */   }
/*     */ 
/*     */   public void createTable(TableModel model) throws SQLException
/*     */   {
/*  48 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/*  53 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/*  59 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/*  65 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/*  71 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/*  77 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/*  82 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index) throws SQLException
/*     */   {
/*  87 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName)
/*     */   {
/*  92 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/*  97 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 102 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 108 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/* 114 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName)
/*     */   {
/* 119 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect)
/*     */   {
/* 125 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   public List<String> getPKColumns(String tableName) throws SQLException
/*     */   {
/* 130 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getPKColumns(List<String> tableNames)
/*     */     throws SQLException
/*     */   {
/* 137 */     throw new UnsupportedOperationException("Current Implement not supported");
/*     */   }
/*     */ 
/*     */   public void createIndex(String tableName, String fieldName)
/*     */   {
/* 142 */     String regex = "(?im)" + TableModel.CUSTOMER_TABLE_PREFIX;
/* 143 */     String shortTableName = tableName.replaceFirst(regex, "");
/* 144 */     String sqlIndex = "create index idx_" + shortTableName + "_" + fieldName + " on " + tableName + "(" + fieldName + ")";
/* 145 */     this.jdbcTemplate.execute(sqlIndex);
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 152 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.AbstractTableOperator
 * JD-Core Version:    0.6.2
 */