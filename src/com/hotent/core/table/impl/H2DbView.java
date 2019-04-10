/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseDbView;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.H2ColumnMap;
/*     */ import com.hotent.core.util.BeanUtils;
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
/*     */ public class H2DbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private static final String SQL_GET_ALL_VIEW = "SELECT TABLE_NAME ,REMARKS  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'VIEW' AND TABLE_SCHEMA=SCHEMA() ";
/*     */   private static final String SQL_GET_COLUMNS = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.DATA_TYPE, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND UPPER(A.TABLE_NAME)='%s' ";
/*     */   static final String SQL_GET_COLUMNS_BATCH = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.DATA_TYPE, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() ";
/* 304 */   RowMapper<TableModel> tableRowMapper = new RowMapper()
/*     */   {
/*     */     public TableModel mapRow(ResultSet rs, int row) throws SQLException {
/* 307 */       TableModel tableModel = new TableModel();
/* 308 */       String tabName = rs.getString("TABLE_NAME");
/* 309 */       String tabComment = rs.getString("REMARKS");
/* 310 */       tableModel.setName(tabName);
/* 311 */       tableModel.setComment(tabComment);
/* 312 */       return tableModel;
/*     */     }
/* 304 */   };
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */     throws SQLException
/*     */   {
/*  87 */     String sql = "SELECT TABLE_NAME ,REMARKS  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'VIEW' AND TABLE_SCHEMA=SCHEMA() ";
/*  88 */     if (StringUtil.isNotEmpty(viewName)) {
/*  89 */       sql = sql + " AND TABLE_NAME LIKE '%" + viewName + "%'";
/*     */     }
/*     */ 
/*  92 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/*  95 */         String name = rs.getString("TABLE_NAME");
/*  96 */         return name;
/*     */       }
/*     */     };
/*  99 */     return this.jdbcTemplate.query(sql, rowMapper);
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 108 */     String sql = "SELECT TABLE_NAME ,REMARKS  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'VIEW' AND TABLE_SCHEMA=SCHEMA() ";
/* 109 */     if (StringUtil.isNotEmpty(viewName)) {
/* 110 */       sql = sql + " AND TABLE_NAME LIKE '%" + viewName + "%'";
/*     */     }
/* 112 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 115 */         String name = rs.getString("TABLE_NAME");
/* 116 */         return name;
/*     */       }
/*     */     };
/* 119 */     return getForList(sql, pageBean, rowMapper, "h2");
/*     */   }
/*     */   public String getType(String dbtype) {
/* 122 */     dbtype = dbtype.toUpperCase();
/* 123 */     if (dbtype.equals("BIGINT"))
/* 124 */       return "number";
/* 125 */     if (dbtype.equals("INT8"))
/* 126 */       return "number";
/* 127 */     if (dbtype.equals("INT"))
/* 128 */       return "number";
/* 129 */     if (dbtype.equals("INTEGER"))
/* 130 */       return "number";
/* 131 */     if (dbtype.equals("MEDIUMINT"))
/* 132 */       return "number";
/* 133 */     if (dbtype.equals("INT4"))
/* 134 */       return "number";
/* 135 */     if (dbtype.equals("SIGNED"))
/* 136 */       return "number";
/* 137 */     if (dbtype.equals("TINYINT"))
/* 138 */       return "number";
/* 139 */     if (dbtype.equals("SMALLINT"))
/* 140 */       return "number";
/* 141 */     if (dbtype.equals("INT2"))
/* 142 */       return "number";
/* 143 */     if (dbtype.equals("YEAR"))
/* 144 */       return "number";
/* 145 */     if (dbtype.equals("IDENTITY"))
/* 146 */       return "number";
/* 147 */     if (dbtype.equals("DECIMAL"))
/* 148 */       return "number";
/* 149 */     if (dbtype.equals("BOOLEAN"))
/* 150 */       return "varchar";
/* 151 */     if (dbtype.equals("BIT"))
/* 152 */       return "varchar";
/* 153 */     if (dbtype.equals("BOOL"))
/* 154 */       return "varchar";
/* 155 */     if (dbtype.equals("SIGNED")) {
/* 156 */       return "number";
/*     */     }
/* 158 */     if (dbtype.equals("DOUBLE")) {
/* 159 */       return "number";
/*     */     }
/* 161 */     if (dbtype.equals("REAL")) {
/* 162 */       return "number";
/*     */     }
/* 164 */     if (dbtype.equals("TIME")) {
/* 165 */       return "date";
/*     */     }
/* 167 */     if (dbtype.equals("TIMESTAMP")) {
/* 168 */       return "date";
/*     */     }
/* 170 */     if (dbtype.equals("BINARY")) {
/* 171 */       return "clob";
/*     */     }
/* 173 */     if (dbtype.equals("BLOB")) {
/* 174 */       return "clob";
/*     */     }
/* 176 */     if (dbtype.equals("CLOB")) {
/* 177 */       return "clob";
/*     */     }
/* 179 */     if (dbtype.equals("VARCHAR")) {
/* 180 */       return "varchar";
/*     */     }
/* 182 */     if (dbtype.equals("CHAR")) {
/* 183 */       return "varchar";
/*     */     }
/* 185 */     if (dbtype.equals("UUID")) {
/* 186 */       return "varchar";
/*     */     }
/* 188 */     if (dbtype.equals("ARRAY")) {
/* 189 */       return "varchar";
/*     */     }
/*     */ 
/* 192 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public TableModel getModelByViewName(String viewName)
/*     */     throws SQLException
/*     */   {
/* 198 */     String sql = "SELECT TABLE_NAME ,REMARKS  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'VIEW' AND TABLE_SCHEMA=SCHEMA() ";
/* 199 */     sql = sql + " AND UPPER(TABLE_NAME) = '" + viewName.toUpperCase() + "'";
/*     */ 
/* 201 */     TableModel tableModel = null;
/* 202 */     List tableModels = this.jdbcTemplate.query(sql, this.tableRowMapper);
/* 203 */     if (BeanUtils.isEmpty(tableModels)) {
/* 204 */       return null;
/*     */     }
/* 206 */     tableModel = (TableModel)tableModels.get(0);
/*     */ 
/* 209 */     List columnList = getColumnsByTableName(viewName);
/* 210 */     tableModel.setColumnList(columnList);
/* 211 */     return tableModel;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 217 */     String sql = "SELECT TABLE_NAME ,REMARKS  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'VIEW' AND TABLE_SCHEMA=SCHEMA() ";
/* 218 */     if (StringUtil.isNotEmpty(viewName)) {
/* 219 */       sql = sql + " AND TABLE_NAME LIKE '%" + viewName + "%'";
/*     */     }
/* 221 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException {
/* 224 */         TableModel tableModel = new TableModel();
/* 225 */         tableModel.setName(rs.getString("table_name"));
/* 226 */         tableModel.setComment(tableModel.getName());
/* 227 */         return tableModel;
/*     */       }
/*     */     };
/* 230 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "h2");
/* 231 */     List tableNames = new ArrayList();
/*     */ 
/* 233 */     for (TableModel model : tableModels) {
/* 234 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 237 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 239 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 241 */       for (TableModel model : tableModels)
/* 242 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 243 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 247 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 257 */     String sql = String.format("SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.DATA_TYPE, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND UPPER(A.TABLE_NAME)='%s' ", new Object[] { tableName });
/*     */ 
/* 260 */     Map map = new HashMap();
/*     */ 
/* 262 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new H2ColumnMap());
/* 263 */     for (ColumnModel model : list) {
/* 264 */       model.setTableName(tableName);
/*     */     }
/* 266 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 276 */     String sql = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.DATA_TYPE, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() ";
/* 277 */     Map map = new HashMap();
/* 278 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 279 */       return map;
/*     */     }
/* 281 */     StringBuffer buf = new StringBuffer();
/* 282 */     for (String str : tableNames) {
/* 283 */       buf.append("'" + str + "',");
/*     */     }
/* 285 */     buf.deleteCharAt(buf.length() - 1);
/* 286 */     sql = sql + " AND A.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 290 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new H2ColumnMap());
/* 291 */     for (ColumnModel columnModel : columnModels) {
/* 292 */       String tableName = columnModel.getTableName();
/* 293 */       if (map.containsKey(tableName)) {
/* 294 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 296 */         List cols = new ArrayList();
/* 297 */         cols.add(columnModel);
/* 298 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 301 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.H2DbView
 * JD-Core Version:    0.6.2
 */