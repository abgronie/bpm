/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.OracleColumnMap;
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
/*     */ public class OracleTableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  42 */   private String sqlPk = "select column_name from user_constraints c,user_cons_columns col where c.constraint_name=col.constraint_name and c.constraint_type='P'and c.table_name='%s'";
/*     */ 
/*  47 */   private String sqlTableComment = "select TABLE_NAME,DECODE(COMMENTS,null,TABLE_NAME,comments) comments from user_tab_comments  where table_type='TABLE' AND table_name ='%s'";
/*     */ 
/*  52 */   private final String SQL_GET_COLUMNS = "SELECT  \tA.TABLE_NAME TABLE_NAME,  \tA.COLUMN_NAME NAME,  \tA.DATA_TYPE TYPENAME,  \tA.DATA_LENGTH LENGTH,   \tA.DATA_PRECISION PRECISION,  \tA.DATA_SCALE SCALE,  \tA.DATA_DEFAULT,  \tA.NULLABLE,   \tDECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  \t(    \t  SELECT    \t    COUNT(*)    \t  FROM     \t    USER_CONSTRAINTS CONS,     \t   USER_CONS_COLUMNS CONS_C      \t WHERE      \t   CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME     \t   AND CONS.CONSTRAINT_TYPE='P'     \t   AND CONS.TABLE_NAME=B.TABLE_NAME      \t  AND CONS_C.COLUMN_NAME=A.COLUMN_NAME   \t ) AS IS_PK  FROM   \t USER_TAB_COLUMNS A,  \tUSER_COL_COMMENTS B   WHERE   \tA.COLUMN_NAME=B.COLUMN_NAME  \tAND A.TABLE_NAME = B.TABLE_NAME  \tAND A.TABLE_NAME='%s'  ORDER BY   \tA.COLUMN_ID";
/*     */ 
/*  86 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT  \tA.TABLE_NAME TABLE_NAME,  \tA.COLUMN_NAME NAME,  \tA.DATA_TYPE TYPENAME,  \tA.DATA_LENGTH LENGTH,   \tA.DATA_PRECISION PRECISION,  \tA.DATA_SCALE SCALE,  \tA.DATA_DEFAULT,  \tA.NULLABLE,   \tDECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  \t(    \t  SELECT    \t    COUNT(*)    \t  FROM     \t    USER_CONSTRAINTS CONS,     \t   USER_CONS_COLUMNS CONS_C      \t WHERE      \t   CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME     \t   AND CONS.CONSTRAINT_TYPE='P'     \t   AND CONS.TABLE_NAME=B.TABLE_NAME      \t  AND CONS_C.COLUMN_NAME=A.COLUMN_NAME   \t ) AS IS_PK  FROM   \tUSER_TAB_COLUMNS A,  \tUSER_COL_COMMENTS B   WHERE   \tA.COLUMN_NAME=B.COLUMN_NAME  \tAND A.TABLE_NAME = B.TABLE_NAME ";
/*     */ 
/* 118 */   private String sqlAllTables = "select a.TABLE_NAME,DECODE(b.COMMENTS,null,a.TABLE_NAME,b.comments) comments from user_tables a,user_tab_comments b where a.table_name=b.table_name and b.table_type='TABLE'  ";
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/* 126 */     String sql = this.sqlAllTables;
/* 127 */     if (StringUtil.isNotEmpty(tableName)) {
/* 128 */       sql = this.sqlAllTables + " and  lower(a.table_name) like '%" + tableName.toLowerCase() + "%'";
/*     */     }
/*     */ 
/* 131 */     Map parameter = new HashMap();
/* 132 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 136 */         String tableName = rs.getString("table_name");
/* 137 */         String comments = rs.getString("comments");
/* 138 */         Map map = new HashMap();
/* 139 */         map.put("name", tableName);
/* 140 */         map.put("comments", comments);
/* 141 */         return map;
/*     */       }
/*     */     });
/* 144 */     Map map = new LinkedHashMap();
/* 145 */     for (int i = 0; i < list.size(); i++) {
/* 146 */       Map tmp = (Map)list.get(i);
/* 147 */       String name = (String)tmp.get("name");
/* 148 */       String comments = (String)tmp.get("comments");
/* 149 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 152 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> names)
/*     */   {
/* 157 */     StringBuffer sb = new StringBuffer();
/* 158 */     for (String name : names) {
/* 159 */       sb.append("'");
/* 160 */       sb.append(name);
/* 161 */       sb.append("',");
/*     */     }
/* 163 */     sb.deleteCharAt(sb.length() - 1);
/* 164 */     String sql = this.sqlAllTables + " and  lower(table_name) in (" + sb.toString().toLowerCase() + ")";
/*     */ 
/* 168 */     Map parameter = new HashMap();
/* 169 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 173 */         String tableName = rs.getString("TABLE_NAME");
/* 174 */         String comments = rs.getString("COMMENTS");
/* 175 */         Map map = new HashMap();
/* 176 */         map.put("NAME", tableName);
/* 177 */         map.put("COMMENTS", comments);
/* 178 */         return map;
/*     */       }
/*     */     });
/* 181 */     Map map = new LinkedHashMap();
/* 182 */     for (int i = 0; i < list.size(); i++) {
/* 183 */       Map tmp = (Map)list.get(i);
/* 184 */       String name = (String)tmp.get("NAME");
/* 185 */       String comments = (String)tmp.get("COMMENTS");
/* 186 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 189 */     return map;
/*     */   }
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/* 197 */     tableName = tableName.toUpperCase();
/* 198 */     TableModel model = getTableModel(tableName);
/*     */ 
/* 200 */     List columnList = getColumnsByTableName(tableName);
/* 201 */     model.setColumnList(columnList);
/* 202 */     return model;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 208 */     String sql = this.sqlAllTables;
/*     */ 
/* 210 */     if (StringUtil.isNotEmpty(tableName)) {
/* 211 */       sql = sql + " AND  LOWER(table_name) LIKE '%" + tableName.toLowerCase() + "%'";
/*     */     }
/* 213 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 217 */         TableModel tableModel = new TableModel();
/* 218 */         tableModel.setName(rs.getString("TABLE_NAME"));
/* 219 */         tableModel.setComment(rs.getString("COMMENTS"));
/* 220 */         return tableModel;
/*     */       }
/*     */     };
/* 223 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "oracle");
/* 224 */     List tableNames = new ArrayList();
/*     */ 
/* 226 */     for (TableModel model : tableModels) {
/* 227 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 230 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 232 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	 Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 234 */       for (TableModel model : tableModels)
/* 235 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 236 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */    
/* 241 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private String getPkColumn(String tableName)
/*     */   {
/* 251 */     tableName = tableName.toUpperCase();
/*     */ 
/* 254 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 255 */     Object rtn = this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 259 */         return rs.getString("COLUMN_NAME");
/*     */       }
/*     */     });
/* 262 */     if (rtn == null) {
/* 263 */       return "";
/*     */     }
/* 265 */     return rtn.toString();
/*     */   }
/*     */ 
/*     */   private List<String> getPkColumns(String tableName)
/*     */   {
/* 276 */     tableName = tableName.toUpperCase();
/*     */ 
/* 279 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 280 */     List rtn = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 283 */         return rs.getString("column_name");
/*     */       }
/*     */     });
/* 286 */     return rtn;
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(final String tableName)
/*     */   {
/* 298 */     String sql = String.format(this.sqlTableComment, new Object[] { tableName });
/* 299 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 304 */         TableModel tableModel = new TableModel();
/* 305 */         tableModel.setName(tableName);
/* 306 */         tableModel.setComment(rs.getString("comments"));
/* 307 */         return tableModel;
/*     */       }
/*     */     });
/* 310 */     if (BeanUtils.isEmpty(tableModel)) {
/* 311 */       tableModel = new TableModel();
/*     */     }
/* 313 */     return tableModel;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 323 */     String sql = String.format("SELECT  \tA.TABLE_NAME TABLE_NAME,  \tA.COLUMN_NAME NAME,  \tA.DATA_TYPE TYPENAME,  \tA.DATA_LENGTH LENGTH,   \tA.DATA_PRECISION PRECISION,  \tA.DATA_SCALE SCALE,  \tA.DATA_DEFAULT,  \tA.NULLABLE,   \tDECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  \t(    \t  SELECT    \t    COUNT(*)    \t  FROM     \t    USER_CONSTRAINTS CONS,     \t   USER_CONS_COLUMNS CONS_C      \t WHERE      \t   CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME     \t   AND CONS.CONSTRAINT_TYPE='P'     \t   AND CONS.TABLE_NAME=B.TABLE_NAME      \t  AND CONS_C.COLUMN_NAME=A.COLUMN_NAME   \t ) AS IS_PK  FROM   \t USER_TAB_COLUMNS A,  \tUSER_COL_COMMENTS B   WHERE   \tA.COLUMN_NAME=B.COLUMN_NAME  \tAND A.TABLE_NAME = B.TABLE_NAME  \tAND A.TABLE_NAME='%s'  ORDER BY   \tA.COLUMN_ID", new Object[] { tableName });
/*     */ 
/* 326 */     Map map = new HashMap();
/* 327 */     List columnList = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new OracleColumnMap());
/* 328 */     return columnList;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 338 */     String sql = "SELECT  \tA.TABLE_NAME TABLE_NAME,  \tA.COLUMN_NAME NAME,  \tA.DATA_TYPE TYPENAME,  \tA.DATA_LENGTH LENGTH,   \tA.DATA_PRECISION PRECISION,  \tA.DATA_SCALE SCALE,  \tA.DATA_DEFAULT,  \tA.NULLABLE,   \tDECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  \t(    \t  SELECT    \t    COUNT(*)    \t  FROM     \t    USER_CONSTRAINTS CONS,     \t   USER_CONS_COLUMNS CONS_C      \t WHERE      \t   CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME     \t   AND CONS.CONSTRAINT_TYPE='P'     \t   AND CONS.TABLE_NAME=B.TABLE_NAME      \t  AND CONS_C.COLUMN_NAME=A.COLUMN_NAME   \t ) AS IS_PK  FROM   \tUSER_TAB_COLUMNS A,  \tUSER_COL_COMMENTS B   WHERE   \tA.COLUMN_NAME=B.COLUMN_NAME  \tAND A.TABLE_NAME = B.TABLE_NAME ";
/* 339 */     Map map = new HashMap();
/* 340 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 341 */       return map;
/*     */     }
/* 343 */     StringBuffer buf = new StringBuffer();
/* 344 */     for (String str : tableNames) {
/* 345 */       buf.append("'" + str + "',");
/*     */     }
/* 347 */     buf.deleteCharAt(buf.length() - 1);
/* 348 */     sql = sql + " AND A.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 354 */     Long b = Long.valueOf(System.currentTimeMillis());
/* 355 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new OracleColumnMap());
/*     */ 
/* 357 */     for (ColumnModel columnModel : columnModels) {
/* 358 */       String tableName = columnModel.getTableName();
/* 359 */       if (map.containsKey(tableName)) {
/* 360 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 362 */         List cols = new ArrayList();
/* 363 */         cols.add(columnModel);
/* 364 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 367 */     return map;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 372 */     return this.sqlAllTables;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.OracleTableMeta
 * JD-Core Version:    0.6.2
 */