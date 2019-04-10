/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseTableMeta;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.DmColumnMap;
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
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public class DmTableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  41 */   protected Logger logger = LoggerFactory.getLogger(DmTableMeta.class);
/*     */ 
/*  45 */   private String sqlPk = "SELECT  CONS_C.COLUMN_NAME FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME='%s'";
/*     */ 
/*  50 */   private String sqlTableComment = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE  TABLE_NAME ='%s'";
/*     */ 
/*  55 */   private final String SQL_GET_COLUMNS = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S'  ORDER BY COLUMN_ID ";
/*     */ 
/*  68 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T WHERE 1=1 ";
/*     */ 
/*  81 */   private String sqlAllTables = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE 1=1";
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/*  90 */     String sql = this.sqlAllTables;
/*  91 */     if (StringUtil.isNotEmpty(tableName)) {
/*  92 */       sql = this.sqlAllTables + " and  lower(TABLE_NAME) like '%" + tableName.toLowerCase() + "%'";
/*     */     }
/*     */ 
/*  95 */     Map parameter = new HashMap();
/*  96 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 100 */         String tableName = rs.getString("table_name");
/* 101 */         String comments = rs.getString("comments");
/* 102 */         Map map = new HashMap();
/* 103 */         map.put("name", tableName);
/* 104 */         map.put("comments", comments);
/* 105 */         return map;
/*     */       }
/*     */     });
/* 108 */     Map map = new LinkedHashMap();
/* 109 */     for (int i = 0; i < list.size(); i++) {
/* 110 */       Map tmp = (Map)list.get(i);
/* 111 */       String name = (String)tmp.get("name");
/* 112 */       String comments = (String)tmp.get("comments");
/* 113 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 116 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> names)
/*     */   {
/* 121 */     StringBuffer sb = new StringBuffer();
/* 122 */     for (String name : names) {
/* 123 */       sb.append("'");
/* 124 */       sb.append(name);
/* 125 */       sb.append("',");
/*     */     }
/* 127 */     sb.deleteCharAt(sb.length() - 1);
/* 128 */     String sql = this.sqlAllTables + " and  lower(TABLE_NAME) in (" + sb.toString().toLowerCase() + ")";
/*     */ 
/* 133 */     Map parameter = new HashMap();
/* 134 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 138 */         String tableName = rs.getString("TABLE_NAME");
/* 139 */         String comments = rs.getString("COMMENTS");
/* 140 */         Map map = new HashMap();
/* 141 */         map.put("NAME", tableName);
/* 142 */         map.put("COMMENTS", comments);
/* 143 */         return map;
/*     */       }
/*     */     });
/* 146 */     Map map = new LinkedHashMap();
/* 147 */     for (int i = 0; i < list.size(); i++) {
/* 148 */       Map tmp = (Map)list.get(i);
/* 149 */       String name = (String)tmp.get("NAME");
/* 150 */       String comments = (String)tmp.get("COMMENTS");
/* 151 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 154 */     return map;
/*     */   }
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/* 162 */     tableName = tableName.toUpperCase();
/* 163 */     TableModel model = getTableModel(tableName);
/*     */ 
/* 165 */     List columnList = getColumnsByTableName(tableName);
/* 166 */     model.setColumnList(columnList);
/* 167 */     return model;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 173 */     String sql = this.sqlAllTables;
/*     */ 
/* 175 */     if (StringUtil.isNotEmpty(tableName)) {
/* 176 */       sql = sql + " AND  LOWER(TABLE_NAME) LIKE '%" + tableName.toLowerCase() + "%'";
/*     */     }
/* 178 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 182 */         TableModel tableModel = new TableModel();
/* 183 */         tableModel.setName(rs.getString("TABLE_NAME"));
/* 184 */         tableModel.setComment(rs.getString("COMMENTS"));
/* 185 */         return tableModel;
/*     */       }
/*     */     };
/* 188 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "dm");
/* 189 */     List tableNames = new ArrayList();
/*     */ 
/* 191 */     for (TableModel model : tableModels) {
/* 192 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 195 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 197 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
				Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 199 */       for (TableModel model : tableModels)
/* 200 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 201 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 206 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private String getPkColumn(String tableName)
/*     */   {
/* 216 */     tableName = tableName.toUpperCase();
/*     */ 
/* 221 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 222 */     Object rtn = this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 226 */         return rs.getString("COLUMN_NAME");
/*     */       }
/*     */     });
/* 229 */     if (rtn == null) {
/* 230 */       return "";
/*     */     }
/* 232 */     return rtn.toString();
/*     */   }
/*     */ 
/*     */   private List<String> getPkColumns(String tableName)
/*     */   {
/* 243 */     tableName = tableName.toUpperCase();
/*     */ 
/* 248 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 249 */     List rtn = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 252 */         return rs.getString("column_name");
/*     */       }
/*     */     });
/* 255 */     return rtn;
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(final String tableName)
/*     */   {
/* 269 */     String sql = String.format(this.sqlTableComment, new Object[] { tableName });
/* 270 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 275 */         TableModel tableModel = new TableModel();
/* 276 */         tableModel.setName(tableName);
/* 277 */         tableModel.setComment(rs.getString("comments"));
/* 278 */         return tableModel;
/*     */       }
/*     */     });
/* 281 */     if (BeanUtils.isEmpty(tableModel)) {
/* 282 */       tableModel = new TableModel();
/*     */     }
/* 284 */     return tableModel;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 294 */     String sql = String.format("SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S'  ORDER BY COLUMN_ID ", new Object[] { tableName });
/*     */ 
/* 299 */     Map map = new HashMap();
/* 300 */     List columnList = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new DmColumnMap());
/* 301 */     return columnList;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 311 */     String sql = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T WHERE 1=1 ";
/* 312 */     Map map = new HashMap();
/* 313 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 314 */       return map;
/*     */     }
/* 316 */     StringBuffer buf = new StringBuffer();
/* 317 */     for (String str : tableNames) {
/* 318 */       buf.append("'" + str + "',");
/*     */     }
/* 320 */     buf.deleteCharAt(buf.length() - 1);
/* 321 */     sql = sql + " AND T.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 326 */     Long b = Long.valueOf(System.currentTimeMillis());
/* 327 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new DmColumnMap());
/* 328 */     this.logger.info(String.valueOf(System.currentTimeMillis() - b.longValue()));
/* 329 */     for (ColumnModel columnModel : columnModels) {
/* 330 */       String tableName = columnModel.getTableName();
/* 331 */       if (map.containsKey(tableName)) {
/* 332 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 334 */         List cols = new ArrayList();
/* 335 */         cols.add(columnModel);
/* 336 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 339 */     return map;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 344 */     return this.sqlAllTables;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.DmTableMeta
 * JD-Core Version:    0.6.2
 */