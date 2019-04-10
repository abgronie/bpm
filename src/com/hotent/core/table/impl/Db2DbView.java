/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseDbView;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.DB2ColumnMap;
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
/*     */ public class Db2DbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private static final String SQL_GET_ALL_VIEW = "SELECT VIEWNAME FROM SYSCAT.VIEWS WHERE  VIEWSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/*     */   private static final String SQL_GET_COLUMNS = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) = UPPER('%s') ";
/*  62 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/*     */ 
/* 250 */   RowMapper<TableModel> tableModelRowMapper = new RowMapper()
/*     */   {
/*     */     public TableModel mapRow(ResultSet rs, int row) throws SQLException {
/* 253 */       TableModel tableModel = new TableModel();
/* 254 */       String tabName = rs.getString("VIEWNAME");
/* 255 */       tableModel.setName(tabName);
/* 256 */       tableModel.setComment(tabName);
/* 257 */       return tableModel;
/*     */     }
/* 250 */   };
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */     throws SQLException
/*     */   {
/*  81 */     String sql = "SELECT VIEWNAME FROM SYSCAT.VIEWS WHERE  VIEWSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/*  82 */     if (StringUtil.isNotEmpty(viewName)) {
/*  83 */       sql = sql + " AND UPPER(VIEWNAME) like '" + viewName.toUpperCase() + "%'";
/*     */     }
/*  85 */     return this.jdbcTemplate.queryForList(sql, String.class);
/*     */   }
/*     */ 
/*     */   public String getType(String type)
/*     */   {
/*  93 */     String dbtype = type.toLowerCase();
/*  94 */     if (dbtype.endsWith("bigint"))
/*  95 */       return "number";
/*  96 */     if (dbtype.endsWith("blob"))
/*  97 */       return "clob";
/*  98 */     if (dbtype.endsWith("character"))
/*  99 */       return "varchar";
/* 100 */     if (dbtype.endsWith("clob"))
/* 101 */       return "clob";
/* 102 */     if (dbtype.endsWith("date"))
/* 103 */       return "date";
/* 104 */     if (dbtype.endsWith("dbclob"))
/* 105 */       return "clob";
/* 106 */     if (dbtype.endsWith("decimal"))
/* 107 */       return "number";
/* 108 */     if (dbtype.endsWith("double"))
/* 109 */       return "number";
/* 110 */     if (dbtype.endsWith("graphic"))
/* 111 */       return "clob";
/* 112 */     if (dbtype.endsWith("integer"))
/* 113 */       return "number";
/* 114 */     if (dbtype.endsWith("long varchar"))
/* 115 */       return "varchar";
/* 116 */     if (dbtype.endsWith("long vargraphic"))
/* 117 */       return "clob";
/* 118 */     if (dbtype.endsWith("real"))
/* 119 */       return "number";
/* 120 */     if (dbtype.endsWith("smallint"))
/* 121 */       return "number";
/* 122 */     if (dbtype.endsWith("time"))
/* 123 */       return "date";
/* 124 */     if (dbtype.endsWith("timestamp"))
/* 125 */       return "date";
/* 126 */     if (dbtype.endsWith("varchar"))
/* 127 */       return "varchar";
/* 128 */     if (dbtype.endsWith("vargraphic"))
/* 129 */       return "clob";
/* 130 */     if (dbtype.endsWith("xml")) {
/* 131 */       return "clob";
/*     */     }
/* 133 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 140 */     String sql = "SELECT VIEWNAME FROM SYSCAT.VIEWS WHERE  VIEWSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/* 141 */     if (StringUtil.isNotEmpty(viewName)) {
/* 142 */       sql = sql + " AND UPPER(VIEWNAME) LIKE '%" + viewName.toUpperCase() + "%'";
/*     */     }
/* 144 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 147 */         return rs.getString("VIEWNAME");
/*     */       }
/*     */     };
/* 150 */     return getForList(sql, pageBean, rowMapper, "db2");
/*     */   }
/*     */ 
/*     */   public TableModel getModelByViewName(String viewName) throws SQLException
/*     */   {
/* 155 */     String sql = "SELECT VIEWNAME FROM SYSCAT.VIEWS WHERE  VIEWSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/* 156 */     sql = sql + " AND UPPER(VIEWNAME) = '" + viewName.toUpperCase() + "'";
/*     */ 
/* 158 */     TableModel tableModel = null;
/* 159 */     List tableModels = this.jdbcTemplate.query(sql, this.tableModelRowMapper);
/* 160 */     if (BeanUtils.isEmpty(tableModels)) {
/* 161 */       return null;
/*     */     }
/* 163 */     tableModel = (TableModel)tableModels.get(0);
/*     */ 
/* 166 */     List columnList = getColumnsByTableName(viewName);
/* 167 */     tableModel.setColumnList(columnList);
/* 168 */     return tableModel;
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean) throws Exception
/*     */   {
/* 173 */     String sql = "SELECT VIEWNAME FROM SYSCAT.VIEWS WHERE  VIEWSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/* 174 */     if (StringUtil.isNotEmpty(viewName)) {
/* 175 */       sql = sql + " AND UPPER(VIEWNAME) LIKE '%" + viewName.toUpperCase() + "%'";
/*     */     }
/*     */ 
/* 178 */     List<TableModel> tableModels = getForList(sql, pageBean, this.tableModelRowMapper, "db2");
/*     */ 
/* 180 */     List tableNames = new ArrayList();
/*     */ 
/* 182 */     for (TableModel model : tableModels) {
/* 183 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 186 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 188 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
				Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 190 */       for (TableModel model : tableModels)
/* 191 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 192 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 196 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 207 */     String sql = String.format("SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) = UPPER('%s') ", new Object[] { tableName });
/* 208 */     Map map = new HashMap();
/*     */ 
/* 210 */     List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new DB2ColumnMap());
/* 211 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 221 */     String sql = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, TYPENAME COL_TYPE, REMARKS COL_COMMENT, NULLS IS_NULLABLE, LENGTH LENGTH, SCALE SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
/* 222 */     Map map = new HashMap();
/* 223 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 224 */       return map;
/*     */     }
/* 226 */     StringBuffer buf = new StringBuffer();
/* 227 */     for (String str : tableNames) {
/* 228 */       buf.append("'" + str + "',");
/*     */     }
/* 230 */     buf.deleteCharAt(buf.length() - 1);
/* 231 */     sql = sql + " AND UPPER(TABNAME) IN (" + buf.toString().toUpperCase() + ") ";
/*     */ 
/* 236 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new DB2ColumnMap());
/* 237 */     for (ColumnModel columnModel : columnModels) {
/* 238 */       String tableName = columnModel.getTableName();
/* 239 */       if (map.containsKey(tableName)) {
/* 240 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 242 */         List cols = new ArrayList();
/* 243 */         cols.add(columnModel);
/* 244 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 247 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.Db2DbView
 * JD-Core Version:    0.6.2
 */