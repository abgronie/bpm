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
/*     */ public class KingBaseTableMeta extends BaseTableMeta
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  40 */   protected Logger logger = LoggerFactory.getLogger(KingBaseTableMeta.class);
/*     */ 
/*  44 */   private String sqlPk = "SELECT  CONS_C.COLUMN_NAME FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME='%s'";
/*     */ 
/*  49 */   private String sqlTableComment = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE  TABLE_NAME ='%s'";
/*     */ 
/*  54 */   private final String SQL_GET_COLUMNS = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S'  ORDER BY COLUMN_ID ";
/*     */ 
/*  67 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T WHERE 1=1 ";
/*     */ 
/*  80 */   private String sqlAllTables = "select tablename from sys_tables where schemaname='PUBLIC'";
/*     */ 
/*     */   public Map<String, String> getTablesByName(String tableName)
/*     */   {
/*  89 */     String sql = this.sqlAllTables;
/*  90 */     if (StringUtil.isNotEmpty(tableName))
/*  91 */       sql = this.sqlAllTables + " and  lower(TABLE_NAME) like '%" + tableName.toLowerCase() + "%'";
/*  92 */     Map parameter = new HashMap();
/*  93 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/*  97 */         String tableName = rs.getString("table_name");
/*  98 */         String comments = rs.getString("comments");
/*  99 */         Map map = new HashMap();
/* 100 */         map.put("name", tableName);
/* 101 */         map.put("comments", comments);
/* 102 */         return map;
/*     */       }
/*     */     });
/* 105 */     Map map = new LinkedHashMap();
/* 106 */     for (int i = 0; i < list.size(); i++) {
/* 107 */       Map tmp = (Map)list.get(i);
/* 108 */       String name = (String)tmp.get("name");
/* 109 */       String comments = (String)tmp.get("comments");
/* 110 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 113 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTablesByName(List<String> names)
/*     */   {
/* 118 */     StringBuffer sb = new StringBuffer();
/* 119 */     for (String name : names) {
/* 120 */       sb.append("'");
/* 121 */       sb.append(name);
/* 122 */       sb.append("',");
/*     */     }
/* 124 */     sb.deleteCharAt(sb.length() - 1);
/* 125 */     String sql = this.sqlAllTables + " and  lower(TABLE_NAME) in (" + sb.toString().toLowerCase() + ")";
/*     */ 
/* 130 */     Map parameter = new HashMap();
/* 131 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, parameter, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 135 */         String tableName = rs.getString("TABLE_NAME");
/* 136 */         String comments = rs.getString("COMMENTS");
/* 137 */         Map map = new HashMap();
/* 138 */         map.put("NAME", tableName);
/* 139 */         map.put("COMMENTS", comments);
/* 140 */         return map;
/*     */       }
/*     */     });
/* 143 */     Map map = new LinkedHashMap();
/* 144 */     for (int i = 0; i < list.size(); i++) {
/* 145 */       Map tmp = (Map)list.get(i);
/* 146 */       String name = (String)tmp.get("NAME");
/* 147 */       String comments = (String)tmp.get("COMMENTS");
/* 148 */       map.put(name, comments);
/*     */     }
/*     */ 
/* 151 */     return map;
/*     */   }
/*     */ 
/*     */   public TableModel getTableByName(String tableName)
/*     */   {
/* 159 */     tableName = tableName.toUpperCase();
/* 160 */     TableModel model = getTableModel(tableName);
/*     */ 
/* 162 */     List columnList = getColumnsByTableName(tableName);
/* 163 */     model.setColumnList(columnList);
/* 164 */     return model;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 170 */     String sql = this.sqlAllTables;
/*     */ 
/* 172 */     if (StringUtil.isNotEmpty(tableName)) {
/* 173 */       sql = sql + " AND  LOWER(TABLE_NAME) LIKE '%" + tableName.toLowerCase() + "%'";
/*     */     }
/* 175 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 179 */         TableModel tableModel = new TableModel();
/* 180 */         tableModel.setName(rs.getString("TABLE_NAME"));
/* 181 */         tableModel.setComment(rs.getString("COMMENTS"));
/* 182 */         return tableModel;
/*     */       }
/*     */     };
/* 185 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "kingbase");
/* 186 */     List tableNames = new ArrayList();
/*     */ 
/* 188 */     for (TableModel model : tableModels) {
/* 189 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 192 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 194 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 196 */       for (TableModel model : tableModels)
/* 197 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 198 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 203 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private String getPkColumn(String tableName)
/*     */   {
/* 213 */     tableName = tableName.toUpperCase();
/*     */ 
/* 218 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 219 */     Object rtn = this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 223 */         return rs.getString("COLUMN_NAME");
/*     */       }
/*     */     });
/* 226 */     if (rtn == null) {
/* 227 */       return "";
/*     */     }
/* 229 */     return rtn.toString();
/*     */   }
/*     */ 
/*     */   private List<String> getPkColumns(String tableName)
/*     */   {
/* 240 */     tableName = tableName.toUpperCase();
/* 241 */     String sql = String.format(this.sqlPk, new Object[] { tableName });
/* 242 */     List rtn = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 245 */         return rs.getString("column_name");
/*     */       }
/*     */     });
/* 248 */     return rtn;
/*     */   }
/*     */ 
/*     */   private TableModel getTableModel(final String tableName)
/*     */   {
/* 257 */     String sql = String.format(this.sqlTableComment, new Object[] { tableName });
/* 258 */     TableModel tableModel = (TableModel)this.jdbcTemplate.queryForObject(sql, null, new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row)
/*     */         throws SQLException
/*     */       {
/* 263 */         TableModel tableModel = new TableModel();
/* 264 */         tableModel.setName(tableName);
/* 265 */         tableModel.setComment(rs.getString("comments"));
/* 266 */         return tableModel;
/*     */       }
/*     */     });
/* 269 */     if (BeanUtils.isEmpty(tableModel)) {
/* 270 */       tableModel = new TableModel();
/*     */     }
/* 272 */     return tableModel;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 281 */     String sql = String.format("SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S'  ORDER BY COLUMN_ID ", new Object[] { tableName });
/* 282 */     Map map = new HashMap();
/* 283 */     List columnList = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new DmColumnMap());
/* 284 */     return columnList;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 293 */     String sql = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T WHERE 1=1 ";
/* 294 */     Map map = new HashMap();
/* 295 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 296 */       return map;
/*     */     }
/* 298 */     StringBuffer buf = new StringBuffer();
/* 299 */     for (String str : tableNames) {
/* 300 */       buf.append("'" + str + "',");
/*     */     }
/* 302 */     buf.deleteCharAt(buf.length() - 1);
/* 303 */     sql = sql + " AND T.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 308 */     Long b = Long.valueOf(System.currentTimeMillis());
/* 309 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new DmColumnMap());
/* 310 */     this.logger.info(String.valueOf(System.currentTimeMillis() - b.longValue()));
/* 311 */     for (ColumnModel columnModel : columnModels) {
/* 312 */       String tableName = columnModel.getTableName();
/* 313 */       if (map.containsKey(tableName)) {
/* 314 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 316 */         List cols = new ArrayList();
/* 317 */         cols.add(columnModel);
/* 318 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 321 */     return map;
/*     */   }
/*     */ 
/*     */   public String getAllTableSql()
/*     */   {
/* 326 */     return this.sqlAllTables;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.KingBaseTableMeta
 * JD-Core Version:    0.6.2
 */