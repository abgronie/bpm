/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseDbView;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.MySqlColumnMap;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public class MysqlDbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private static final String sqlAllView = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
/*     */   private static final String SQL_GET_COLUMNS = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";
/*     */   static final String SQL_GET_COLUMNS_BATCH = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */     throws SQLException
/*     */   {
/*  56 */     String sql = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
/*  57 */     if (StringUtil.isNotEmpty(viewName)) {
/*  58 */       sql = sql + " AND TABLE_NAME LIKE '" + viewName + "%'";
/*     */     }
/*  60 */     return this.jdbcTemplate.queryForList(sql, String.class);
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  70 */     String sql = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
/*  71 */     if (StringUtil.isNotEmpty(viewName)) {
/*  72 */       sql = sql + " AND TABLE_NAME LIKE '" + viewName + "%'";
/*     */     }
/*  74 */     return getForList(sql, pageBean, String.class, "mysql");
/*     */   }
/*     */ 
/*     */   public String getType(String type)
/*     */   {
/*  81 */     type = type.toLowerCase();
/*  82 */     if (type.indexOf("number") > -1)
/*  83 */       return "number";
/*  84 */     if ((type.indexOf("date") > -1) || (type.indexOf("time") > -1)) {
/*  85 */       return "date";
/*     */     }
/*  87 */     if (type.indexOf("char") > -1) {
/*  88 */       return "varchar";
/*     */     }
/*  90 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  98 */     String sql = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
/*  99 */     if (StringUtil.isNotEmpty(viewName)) {
/* 100 */       sql = sql + " AND TABLE_NAME LIKE '" + viewName + "%'";
/*     */     }
/*     */ 
/* 103 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 107 */         TableModel tableModel = new TableModel();
/* 108 */         tableModel.setName(rs.getString("table_name"));
/* 109 */         tableModel.setComment(tableModel.getName());
/* 110 */         return tableModel;
/*     */       }
/*     */     };
/* 113 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "mysql");
/*     */ 
/* 115 */     List tableNames = new ArrayList();
/*     */ 
/* 117 */     for (TableModel model : tableModels) {
/* 118 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 121 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 123 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 125 */       for (TableModel model : tableModels)
/* 126 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 127 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     Map.Entry entry;
/* 131 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 141 */     String sql = String.format("SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ", new Object[] { tableName });
/*     */ 
/* 144 */     Map map = new HashMap();
/*     */ 
/* 146 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new MySqlColumnMap());
/* 147 */     for (ColumnModel model : list) {
/* 148 */       model.setTableName(tableName);
/*     */     }
/* 150 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 160 */     String sql = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
/* 161 */     Map map = new HashMap();
/* 162 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 163 */       return map;
/*     */     }
/* 165 */     StringBuffer buf = new StringBuffer();
/* 166 */     for (String str : tableNames) {
/* 167 */       buf.append("'" + str + "',");
/*     */     }
/* 169 */     buf.deleteCharAt(buf.length() - 1);
/* 170 */     sql = sql + " AND TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 174 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new MySqlColumnMap());
/* 175 */     for (ColumnModel columnModel : columnModels) {
/* 176 */       String tableName = columnModel.getTableName();
/* 177 */       if (map.containsKey(tableName)) {
/* 178 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 180 */         List cols = new ArrayList();
/* 181 */         cols.add(columnModel);
/* 182 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 185 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql) throws Exception
/*     */   {
/* 190 */     String getSql = "CREATE OR REPLACE VIEW " + viewName + " as (" + sql + ")";
/*     */ 
/* 192 */     this.jdbcTemplate.execute(getSql);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.MysqlDbView
 * JD-Core Version:    0.6.2
 */