/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.H2ColumnMap;
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
/*     */ public class H2TableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  39 */   private final String SQL_GET_COLUMNS = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.TYPE_NAME, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND B.CONSTRAINT_TYPE='PRIMARY KEY' AND UPPER(A.TABLE_NAME)=UPPER('%s') ";
/*     */ 
/*  58 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.TYPE_NAME, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND B.CONSTRAINT_TYPE='PRIMARY KEY' ";
/*     */ 
/*  78 */   private final String SQL_GET_ALL_TABLE = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_TYPE='TABLE' AND T.TABLE_SCHEMA=SCHEMA() ";
/*     */ 
/* 262 */   RowMapper<TableModel> tableRowMapper = new RowMapper()
/*     */   {
/*     */     public TableModel mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 265 */       TableModel model = new TableModel();
/* 266 */       String tableName = rs.getString("TABLE_NAME");
/* 267 */       String tableComment = rs.getString("REMARKS");
/* 268 */       model.setName(tableName);
/* 269 */       model.setComment(tableComment);
/* 270 */       return model;
/*     */     }
/* 262 */   };
/*     */ 
/* 275 */   RowMapper<Map<String, Object>> tableMapRowMapper = new RowMapper()
/*     */   {
/*     */     public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 278 */       Map model = new HashMap();
/* 279 */       String tableName = rs.getString("TABLE_NAME");
/* 280 */       String tableComment = rs.getString("REMARKS");
/* 281 */       model.put("name", tableName);
/* 282 */       model.put("comment", tableComment);
/* 283 */       return model;
/*     */     }
/* 275 */   };
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/*  95 */     TableModel model = getTableModel(tableName);
/*     */ 
/*  97 */     List columnList = getColumnsByTableName(tableName);
/*  98 */     model.setColumnList(columnList);
/*  99 */     return model;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 110 */     String sql = String.format("SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.TYPE_NAME, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND B.CONSTRAINT_TYPE='PRIMARY KEY' AND UPPER(A.TABLE_NAME)=UPPER('%s') ", new Object[] { tableName.toUpperCase() });
/*     */ 
/* 113 */     Map map = new HashMap();
/*     */ 
/* 115 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new H2ColumnMap());
/* 116 */     for (ColumnModel model : list) {
/* 117 */       model.setTableName(tableName);
/*     */     }
/* 119 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 128 */     String sql = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.TYPE_NAME, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND B.CONSTRAINT_TYPE='PRIMARY KEY' ";
/* 129 */     Map map = new HashMap();
/* 130 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 131 */       return map;
/*     */     }
/* 133 */     StringBuffer buf = new StringBuffer();
/* 134 */     for (String str : tableNames) {
/* 135 */       buf.append("'" + str + "',");
/*     */     }
/* 137 */     buf.deleteCharAt(buf.length() - 1);
/* 138 */     sql = sql + " AND A.TABLE_NAME IN (" + buf.toString().toUpperCase() + ") ";
/*     */ 
/* 142 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new H2ColumnMap());
/* 143 */     for (ColumnModel columnModel : columnModels) {
/* 144 */       String tableName = columnModel.getTableName();
/* 145 */       if (map.containsKey(tableName)) {
/* 146 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 148 */         List cols = new ArrayList();
/* 149 */         cols.add(columnModel);
/* 150 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 153 */     return map;
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(String tableName)
/*     */   {
/* 165 */     String sql = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_TYPE='TABLE' AND T.TABLE_SCHEMA=SCHEMA()  AND UPPER(TABLE_NAME) = '" + tableName.toUpperCase() + "'";
/* 166 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, this.tableRowMapper);
/* 167 */     if (BeanUtils.isEmpty(tableModel))
/* 168 */       tableModel = new TableModel();
/* 169 */     return tableModel;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/* 175 */     String sql = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_TYPE='TABLE' AND T.TABLE_SCHEMA=SCHEMA() ";
/* 176 */     if (StringUtil.isNotEmpty(tableName)) {
/* 177 */       sql = sql + " AND UPPER(TABLE_NAME) LIKE '%" + tableName.toUpperCase() + "%'";
/*     */     }
/*     */ 
/* 180 */     Map parameter = new HashMap();
/* 181 */     List list = this.jdbcTemplate.queryForList(sql, new Object[] { parameter, this.tableMapRowMapper });
/* 182 */     Map map = new LinkedHashMap();
/* 183 */     for (int i = 0; i < list.size(); i++) {
/* 184 */       Map tmp = (Map)list.get(i);
/* 185 */       String name = tmp.get("name").toString();
/* 186 */       String comments = tmp.get("comment").toString();
/* 187 */       map.put(name, comments);
/*     */     }
/* 189 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> names)
/*     */   {
/* 197 */     StringBuffer sb = new StringBuffer();
/* 198 */     for (String name : names) {
/* 199 */       sb.append("'");
/* 200 */       sb.append(name);
/* 201 */       sb.append("',");
/*     */     }
/* 203 */     sb.deleteCharAt(sb.length() - 1);
/* 204 */     String sql = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_TYPE='TABLE' AND T.TABLE_SCHEMA=SCHEMA()  AND  UPPER(TABLE_NAME) IN (" + sb.toString().toUpperCase() + ")";
/*     */ 
/* 208 */     Map parameter = new HashMap();
/* 209 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, this.tableMapRowMapper);
/* 210 */     Map map = new LinkedHashMap();
/* 211 */     for (int i = 0; i < list.size(); i++) {
/* 212 */       Map tmp = (Map)list.get(i);
/* 213 */       String name = tmp.get("name").toString();
/* 214 */       String comments = tmp.get("comment").toString();
/* 215 */       map.put(name, comments);
/*     */     }
/* 217 */     return map;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 224 */     String sql = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_TYPE='TABLE' AND T.TABLE_SCHEMA=SCHEMA() ";
/* 225 */     if (StringUtil.isNotEmpty(tableName)) {
/* 226 */       sql = sql + " AND UPPER(TABLE_NAME) LIKE '%" + tableName.toUpperCase() + "%'";
/*     */     }
/* 228 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 232 */         TableModel tableModel = new TableModel();
/* 233 */         tableModel.setName(rs.getString("TABLE_NAME"));
/* 234 */         String comments = rs.getString("REMARKS");
/* 235 */         tableModel.setComment(comments);
/* 236 */         return tableModel;
/*     */       }
/*     */     };
/* 239 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "h2");
/*     */ 
/* 241 */     List tableNames = new ArrayList();
/*     */ 
/* 243 */     for (TableModel model : tableModels) {
/* 244 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 247 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 249 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 251 */       for (TableModel model : tableModels)
/* 252 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 253 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 257 */     return tableModels;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 291 */     return "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_TYPE='TABLE' AND T.TABLE_SCHEMA=SCHEMA() ";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.H2TableMeta
 * JD-Core Version:    0.6.2
 */