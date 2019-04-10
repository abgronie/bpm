/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.DB2ColumnMap;
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
/*     */ public class Db2TableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  37 */   private final String SQL_GET_COLUMNS = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) = UPPER('%s') ";
/*     */ 
/*  53 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/*     */ 
/*  68 */   private final String SQL_GET_TABLE_COMMENT = "SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) =UPPER('%s')";
/*     */ 
/*  78 */   private final String SQL_GET_ALL_TABLE_COMMENT = "SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";
/*     */ 
/* 248 */   RowMapper<TableModel> tableModelRowMapper = new RowMapper()
/*     */   {
/*     */     public TableModel mapRow(ResultSet rs, int row) throws SQLException {
/* 251 */       TableModel tableModel = new TableModel();
/* 252 */       String tabName = rs.getString("TAB_NAME");
/* 253 */       String tabComment = rs.getString("TAB_COMMENT");
/* 254 */       tableModel.setName(tabName);
/* 255 */       tableModel.setComment(tabComment);
/* 256 */       return tableModel;
/*     */     }
/* 248 */   };
/*     */ 
/* 260 */   RowMapper<Map<String, String>> tableMapRowMapper = new RowMapper()
/*     */   {
/*     */     public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */     {
/* 264 */       String tableName = rs.getString("TAB_NAME");
/* 265 */       String comments = rs.getString("TAB_COMMENT");
/* 266 */       Map map = new HashMap();
/* 267 */       map.put("name", tableName);
/* 268 */       map.put("comments", comments);
/* 269 */       return map;
/*     */     }
/* 260 */   };
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/*  98 */     TableModel model = getTableModel(tableName);
/*  99 */     if (model == null) {
/* 100 */       return null;
/*     */     }
/*     */ 
/* 103 */     List columnList = getColumnsByTableName(tableName);
/* 104 */     model.setColumnList(columnList);
/* 105 */     return model;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 115 */     String sql = String.format("SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) = UPPER('%s') ", new Object[] { tableName });
/*     */ 
/* 117 */     Map map = new HashMap();
/* 118 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new DB2ColumnMap());
/* 119 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 129 */     String sql = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/* 130 */     Map map = new HashMap();
/* 131 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 132 */       return map;
/*     */     }
/* 134 */     StringBuffer buf = new StringBuffer();
/* 135 */     for (String str : tableNames) {
/* 136 */       buf.append("'" + str + "',");
/*     */     }
/* 138 */     buf.deleteCharAt(buf.length() - 1);
/* 139 */     sql = sql + " AND UPPER(TABNAME) IN (" + buf.toString().toUpperCase() + ") ";
/*     */ 
/* 143 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new DB2ColumnMap());
/* 144 */     for (ColumnModel columnModel : columnModels) {
/* 145 */       String tableName = columnModel.getTableName();
/* 146 */       if (map.containsKey(tableName)) {
/* 147 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 149 */         List cols = new ArrayList();
/* 150 */         cols.add(columnModel);
/* 151 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 154 */     return map;
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(String tableName)
/*     */   {
/* 165 */     String sql = String.format("SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) =UPPER('%s')", new Object[] { tableName });
/* 166 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, this.tableModelRowMapper);
/* 167 */     return tableModel;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/* 173 */     String sql = "SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";
/* 174 */     if (StringUtil.isNotEmpty(tableName)) {
/* 175 */       sql = sql + " AND UPPER(TABNAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 178 */     Map parameter = new HashMap();
/* 179 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, this.tableMapRowMapper);
/* 180 */     Map map = new LinkedHashMap();
/* 181 */     for (int i = 0; i < list.size(); i++) {
/* 182 */       Map tmp = (Map)list.get(i);
/* 183 */       String name = (String)tmp.get("name");
/* 184 */       String comments = (String)tmp.get("comments");
/* 185 */       map.put(name, comments);
/*     */     }
/* 187 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> tableNames)
/*     */   {
/* 194 */     Map map = new HashMap();
/* 195 */     String sql = "SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";
/* 196 */     if ((tableNames == null) || (tableNames.size() == 0)) {
/* 197 */       return map;
/*     */     }
/* 199 */     StringBuffer buf = new StringBuffer();
/* 200 */     for (String str : tableNames) {
/* 201 */       buf.append("'" + str + "',");
/*     */     }
/* 203 */     buf.deleteCharAt(buf.length() - 1);
/* 204 */     sql = sql + " AND UPPER(TABNAME) IN (" + buf.toString().toUpperCase() + ") ";
/*     */ 
/* 208 */     Map parameter = new HashMap();
/* 209 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, this.tableMapRowMapper);
/* 210 */     for (int i = 0; i < list.size(); i++) {
/* 211 */       Map tmp = (Map)list.get(i);
/* 212 */       String name = (String)tmp.get("name");
/* 213 */       String comments = (String)tmp.get("comments");
/* 214 */       map.put(name, comments);
/*     */     }
/* 216 */     return map;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 223 */     String sql = "SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";
/* 224 */     if (StringUtil.isNotEmpty(tableName)) {
/* 225 */       sql = sql + " AND UPPER(TABNAME) LIKE '%" + tableName.toUpperCase() + "%'";
/*     */     }
/* 227 */     List<TableModel> tableModels = getForList(sql, pageBean, this.tableModelRowMapper, "db2");
/*     */ 
/* 229 */     List tableNames = new ArrayList();
/*     */ 
/* 231 */     for (TableModel model : tableModels) {
/* 232 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 235 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 237 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
				Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 239 */       for (TableModel model : tableModels)
/* 240 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 241 */           model.setColumnList((List)entry.getValue());
/*     */     }
/* 245 */     return tableModels;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 276 */     return "SELECT TABNAME TAB_NAME, REMARKS TAB_COMMENT FROM SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.Db2TableMeta
 * JD-Core Version:    0.6.2
 */