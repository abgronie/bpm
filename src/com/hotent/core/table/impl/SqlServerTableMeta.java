/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.SqlServerColumnMap;
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
/*     */ public class SqlServerTableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  42 */   private String sqlPk = "SELECT c.COLUMN_NAME COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE c WHERE \tpk.TABLE_NAME LIKE '%s' and\tCONSTRAINT_TYPE = 'PRIMARY KEY' and\tc.TABLE_NAME = pk.TABLE_NAME and\tc.CONSTRAINT_NAME = pk.CONSTRAINT_NAME ";
/*     */ 
/*  53 */   private String sqlTableComment = "select comment from (select a.name name, cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 union(select name,name comment from sys.tables where type='U' and object_id not in (select DISTINCT major_id from sys.extended_properties where minor_id=0))) t where 1=1 and t.name='%s'";
/*     */ 
/*  58 */   private String SQL_GET_COLUMNS = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE,   (  \t\tSELECT COUNT(*)  \t\tFROM   \t\tSYS.IDENTITY_COLUMNS   \t\tWHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID\t) AS AUTOGEN,  \t(  \t\tSELECT CAST(VALUE AS VARCHAR)  \t\tFROM SYS.EXTENDED_PROPERTIES   \t\tWHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID  \t) AS DESCRIPTION,  \t(  \t\tSELECT COUNT(*)  \t\tFROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu  \t\tWHERE \tpk.TABLE_NAME = B.NAME  \t\t\t AND\tCONSTRAINT_TYPE = 'PRIMARY KEY'   \t\t\t AND\tKCU.TABLE_NAME = PK.TABLE_NAME   \t\t\t AND\tKCU.CONSTRAINT_NAME = PK.CONSTRAINT_NAME  \t\t\t AND \tKCU.COLUMN_NAME =A.NAME  \t) AS IS_PK  FROM SYS.COLUMNS A, SYS.TABLES B, SYS.TYPES C   WHERE A.OBJECT_ID = B.OBJECT_ID AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND B.NAME='%s'  \t\tAND C.NAME<>'SYSNAME' \t\tORDER BY A.COLUMN_ID ";
/*     */ 
/*  84 */   private String SQL_GET_COLUMNS_BATCH = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE,  (  \tSELECT COUNT(*)  \tFROM   \tSYS.IDENTITY_COLUMNS   WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN,  \t(  \t\t\tSELECT CAST(VALUE AS VARCHAR)  \t\t\tFROM SYS.EXTENDED_PROPERTIES   \t\tWHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID  \t) AS DESCRIPTION,  \t(  \t\tSELECT COUNT(*)  \t\tFROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu  \t\tWHERE \tpk.TABLE_NAME = B.NAME  \t\t\t AND\tCONSTRAINT_TYPE = 'PRIMARY KEY'   \t\t\t AND\tKCU.TABLE_NAME = PK.TABLE_NAME   \t\t\t AND\tKCU.CONSTRAINT_NAME = PK.CONSTRAINT_NAME  \t\t\t AND \tKCU.COLUMN_NAME =A.NAME  \t) AS IS_PK  FROM SYS.COLUMNS A, SYS.TABLES B, SYS.TYPES C   WHERE A.OBJECT_ID = B.OBJECT_ID AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID  \t\tAND C.NAME<>'SYSNAME' ";
/*     */ 
/* 113 */   private String sqlAllTables = "select * from (select a.name name, cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 union(select name,name comment from sys.tables where type='U' and object_id not in (select DISTINCT major_id from sys.extended_properties where minor_id=0))) t where 1=1";
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/* 120 */     TableModel model = getTableModel(tableName);
/*     */ 
/* 122 */     List columnList = getColumnsByTableName(tableName);
/* 123 */     model.setColumnList(columnList);
/* 124 */     return model;
/*     */   }
/*     */ 
/*     */   private String getPkColumn(String tableName)
/*     */   {
/* 136 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 137 */     Object rtn = this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 142 */         return rs.getString("COLUMN_NAME");
/*     */       }
/*     */     });
/* 145 */     if (rtn == null) {
/* 146 */       return "";
/*     */     }
/* 148 */     return rtn.toString();
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(final String tableName)
/*     */   {
/* 161 */     String sql = String.format(this.sqlTableComment, new Object[] { tableName });
/* 162 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 168 */         TableModel tableModel = new TableModel();
/* 169 */         tableModel.setName(tableName);
/* 170 */         tableModel.setComment(rs.getString("comment"));
/* 171 */         return tableModel;
/*     */       }
/*     */     });
/* 174 */     if (BeanUtils.isEmpty(tableModel)) {
/* 175 */       tableModel = new TableModel();
/*     */     }
/* 177 */     tableModel.setName(tableName);
/*     */ 
/* 179 */     return tableModel;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/* 188 */     String sql = this.sqlAllTables;
/* 189 */     if (StringUtil.isNotEmpty(tableName)) {
/* 190 */       sql = sql + " and  lower(t.name) like '%" + tableName.toLowerCase() + "%'";
/*     */     }
/*     */ 
/* 196 */     Map parameter = new HashMap();
/* 197 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 202 */         String tableName = rs.getString("name");
/* 203 */         String comments = rs.getString("comment");
/* 204 */         Map map = new HashMap();
/* 205 */         map.put("name", tableName);
/* 206 */         map.put("comments", comments);
/* 207 */         return map;
/*     */       }
/*     */     });
/* 210 */     Map map = new LinkedHashMap();
/* 211 */     for (int i = 0; i < list.size(); i++) {
/* 212 */       Map tmp = (Map)list.get(i);
/* 213 */       String name = (String)tmp.get("name");
/* 214 */       String comments = (String)tmp.get("comments");
/* 215 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 218 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> names)
/*     */   {
/* 224 */     StringBuffer sb = new StringBuffer();
/* 225 */     for (String name : names) {
/* 226 */       sb.append("'");
/* 227 */       sb.append(name);
/* 228 */       sb.append("',");
/*     */     }
/* 230 */     sb.deleteCharAt(sb.length() - 1);
/* 231 */     String sql = this.sqlAllTables + " and  t.name in (" + sb.toString().toLowerCase() + ")";
/*     */ 
/* 236 */     Map parameter = new HashMap();
/* 237 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 241 */         String tableName = rs.getString("name");
/* 242 */         String comments = rs.getString("comment");
/* 243 */         Map map = new HashMap();
/* 244 */         map.put("name", tableName);
/* 245 */         map.put("comments", comments);
/* 246 */         return map;
/*     */       }
/*     */     });
/* 249 */     Map map = new LinkedHashMap();
/* 250 */     for (int i = 0; i < list.size(); i++) {
/* 251 */       Map tmp = (Map)list.get(i);
/* 252 */       String name = (String)tmp.get("name");
/* 253 */       String comments = (String)tmp.get("comments");
/* 254 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 257 */     return map;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 263 */     String sql = this.sqlAllTables;
/* 264 */     if (StringUtil.isNotEmpty(tableName)) {
/* 265 */       sql = sql + " AND  LOWER(t.name) LIKE '%" + tableName.toLowerCase() + "%'";
/*     */     }
/* 267 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 271 */         TableModel tableModel = new TableModel();
/* 272 */         tableModel.setName(rs.getString("NAME"));
/* 273 */         tableModel.setComment(rs.getString("COMMENT"));
/* 274 */         return tableModel;
/*     */       }
/*     */     };
/* 277 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "oracle");
/*     */ 
/* 279 */     List tableNames = new ArrayList();
/*     */ 
/* 281 */     for (TableModel model : tableModels) {
/* 282 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 285 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 287 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 289 */       for (TableModel model : tableModels)
/* 290 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 291 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     Map.Entry entry;
/* 296 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 307 */     String sql = String.format(this.SQL_GET_COLUMNS, new Object[] { tableName });
/*     */ 
/* 310 */     Map map = new HashMap();
/* 311 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new SqlServerColumnMap());
/* 312 */     for (ColumnModel model : list) {
/* 313 */       model.setTableName(tableName);
/*     */     }
/* 315 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 325 */     String sql = this.SQL_GET_COLUMNS_BATCH;
/* 326 */     Map map = new HashMap();
/* 327 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 328 */       return map;
/*     */     }
/* 330 */     StringBuffer buf = new StringBuffer();
/* 331 */     for (String str : tableNames) {
/* 332 */       buf.append("'" + str + "',");
/*     */     }
/* 334 */     buf.deleteCharAt(buf.length() - 1);
/* 335 */     sql = sql + " AND B.NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 339 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new SqlServerColumnMap());
/*     */ 
/* 341 */     for (ColumnModel columnModel : columnModels) {
/* 342 */       String tableName = columnModel.getTableName();
/* 343 */       if (map.containsKey(tableName)) {
/* 344 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 346 */         List cols = new ArrayList();
/* 347 */         cols.add(columnModel);
/* 348 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 351 */     return map;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 356 */     return this.sqlAllTables;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.SqlServerTableMeta
 * JD-Core Version:    0.6.2
 */