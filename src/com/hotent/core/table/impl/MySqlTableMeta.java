/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.MySqlColumnMap;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
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
/*     */ public class MySqlTableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  39 */   private final String SQL_GET_COLUMNS = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";
/*     */ 
/*  46 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
/*     */ 
/*  53 */   private final String sqlComment = "select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ";
/*     */ 
/*  55 */   private final String sqlAllTable = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";
/*     */ 
/*  57 */   private final String sqlPk = "SELECT k.column_name name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema=DATABASE() AND t.table_name='%s'";
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/*  72 */     TableModel model = getTableModel(tableName);
/*     */ 
/*  74 */     List columnList = getColumnsByTableName(tableName);
/*  75 */     model.setColumnList(columnList);
/*  76 */     return model;
/*     */   }
/*     */ 
/*     */   private String getPkColumn(String tableName)
/*     */   {
/*  87 */     String sql = String.format("SELECT k.column_name name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema=DATABASE() AND t.table_name='%s'", new Object[] { tableName });
/*  88 */     Object rtn = this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/*  92 */         return rs.getString("name");
/*     */       }
/*     */     });
/*  95 */     if (rtn == null) {
/*  96 */       return "";
/*     */     }
/*  98 */     return rtn.toString();
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 108 */     String sql = String.format("SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ", new Object[] { tableName });
/*     */ 
/* 111 */     Map map = new HashMap();
/*     */ 
/* 113 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new MySqlColumnMap());
/* 114 */     for (ColumnModel model : list) {
/* 115 */       model.setTableName(tableName);
/*     */     }
/* 117 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 126 */     String sql = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH, NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT  FROM  INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA=DATABASE() ";
/* 127 */     Map map = new HashMap();
/* 128 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 129 */       return map;
/*     */     }
/* 131 */     StringBuffer buf = new StringBuffer();
/* 132 */     for (String str : tableNames) {
/* 133 */       buf.append("'" + str + "',");
/*     */     }
/* 135 */     buf.deleteCharAt(buf.length() - 1);
/* 136 */     sql = sql + " AND TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 140 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new MySqlColumnMap());
/* 141 */     for (ColumnModel columnModel : columnModels) {
/* 142 */       String tableName = columnModel.getTableName();
/* 143 */       if (map.containsKey(tableName)) {
/* 144 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 146 */         List cols = new ArrayList();
/* 147 */         cols.add(columnModel);
/* 148 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 151 */     return map;
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(final String tableName)
/*     */   {
/* 163 */     String sql = String.format("select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ", new Object[] { tableName });
/* 164 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 169 */         TableModel tableModel = new TableModel();
/* 170 */         String comments = rs.getString("table_comment");
/* 171 */         comments = MySqlTableMeta.getComments(comments, tableName);
/* 172 */         tableModel.setName(tableName);
/* 173 */         tableModel.setComment(comments);
/* 174 */         return tableModel;
/*     */       }
/*     */     });
/* 177 */     if (BeanUtils.isEmpty(tableModel)) {
/* 178 */       tableModel = new TableModel();
/*     */     }
/* 180 */     return tableModel;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/* 185 */     String sql = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";
/* 186 */     if (StringUtil.isNotEmpty(tableName)) {
/* 187 */       sql = sql + " AND TABLE_NAME LIKE '%" + tableName + "%'";
/*     */     }
/*     */ 
/* 190 */     Map parameter = new HashMap();
/* 191 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 195 */         String tableName = rs.getString("table_name");
/* 196 */         String comments = rs.getString("table_comment");
/* 197 */         Map map = new HashMap();
/* 198 */         map.put("name", tableName);
/* 199 */         map.put("comments", comments);
/* 200 */         return map;
/*     */       }
/*     */     });
/* 203 */     Map map = new LinkedHashMap();
/* 204 */     for (int i = 0; i < list.size(); i++) {
/* 205 */       Map tmp = (Map)list.get(i);
/* 206 */       String name = (String)tmp.get("name");
/* 207 */       String comments = (String)tmp.get("comments");
/* 208 */       comments = getComments(comments, name);
/* 209 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 212 */     return map;
/*     */   }
/*     */ 
/*     */   public static String getComments(String comments, String defaultValue)
/*     */   {
/* 223 */     if (StringUtil.isEmpty(comments)) return defaultValue;
/* 224 */     int idx = comments.indexOf("InnoDB free");
/* 225 */     if (idx > -1) {
/* 226 */       comments = StringUtil.trimSufffix(comments.substring(0, idx).trim(), ";");
/*     */     }
/* 228 */     if (StringUtil.isEmpty(comments)) {
/* 229 */       comments = defaultValue;
/*     */     }
/* 231 */     return comments;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> names)
/*     */   {
/* 236 */     StringBuffer sb = new StringBuffer();
/* 237 */     for (String name : names) {
/* 238 */       sb.append("'");
/* 239 */       sb.append(name);
/* 240 */       sb.append("',");
/*     */     }
/* 242 */     sb.deleteCharAt(sb.length() - 1);
/* 243 */     String sql = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE() and  lower(table_name) in (" + sb.toString().toLowerCase() + ")";
/*     */ 
/* 247 */     Map parameter = new HashMap();
/* 248 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 252 */         String tableName = rs.getString("table_name");
/* 253 */         String comments = rs.getString("table_comment");
/* 254 */         Map map = new HashMap();
/* 255 */         map.put("tableName", tableName);
/* 256 */         map.put("tableComment", comments);
/* 257 */         return map;
/*     */       }
/*     */     });
/* 260 */     Map map = new LinkedHashMap();
/* 261 */     for (int i = 0; i < list.size(); i++) {
/* 262 */       Map tmp = (Map)list.get(i);
/* 263 */       String name = (String)tmp.get("tableName");
/* 264 */       String comments = (String)tmp.get("tableComment");
/* 265 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 268 */     return map;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 275 */     String sql = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";
/* 276 */     if (StringUtil.isNotEmpty(tableName)) {
/* 277 */       sql = sql + " AND TABLE_NAME LIKE '%" + tableName + "%'";
/*     */     }
/* 279 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 283 */         TableModel tableModel = new TableModel();
/* 284 */         tableModel.setName(rs.getString("TABLE_NAME"));
/* 285 */         String comments = rs.getString("TABLE_COMMENT");
/* 286 */         comments = MySqlTableMeta.getComments(comments, tableModel.getName());
/* 287 */         tableModel.setComment(comments);
/* 288 */         return tableModel;
/*     */       }
/*     */     };
/* 291 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "mysql");
/*     */ 
/* 293 */     List tableNames = new ArrayList();
/*     */ 
/* 295 */     for (TableModel model : tableModels) {
/* 296 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 299 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 301 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
				Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 303 */       for (TableModel model : tableModels)
/* 304 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 305 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 309 */     return tableModels;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 315 */     return "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.MySqlTableMeta
 * JD-Core Version:    0.6.2
 */