/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseDbView;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.OracleColumnMap;
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
/*     */ public class OracleDbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private static final String sqlAllView = "select view_name from user_views ";
/*     */   private static final String SQL_GET_COLUMNS = "SELECT A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,  A.DATA_LENGTH LENGTH,A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE,A.DATA_DEFAULT, A.NULLABLE NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, 0 AS IS_PK FROM  USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME  AND  A.TABLE_NAME = B.TABLE_NAME  AND  UPPER(A.TABLE_NAME) =UPPER('%s') ORDER BY A.COLUMN_ID";
/*  57 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT  A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  0 AS IS_PK  FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME AND    A.TABLE_NAME = B.TABLE_NAME ";
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */     throws SQLException
/*     */   {
/*  73 */     String sql = "select view_name from user_views ";
/*  74 */     if (StringUtil.isNotEmpty(viewName)) {
/*  75 */       sql = sql + " where lower(view_name) like '" + viewName.toLowerCase() + "%'";
/*     */     }
/*  77 */     return this.jdbcTemplate.queryForList(sql, String.class);
/*     */   }
/*     */ 
/*     */   public String getType(String type)
/*     */   {
/*  84 */     type = type.toLowerCase();
/*  85 */     if (type.indexOf("number") > -1)
/*  86 */       return "number";
/*  87 */     if (type.indexOf("date") > -1) {
/*  88 */       return "date";
/*     */     }
/*  90 */     if (type.indexOf("char") > -1) {
/*  91 */       return "varchar";
/*     */     }
/*  93 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  99 */     String sql = "select view_name from user_views ";
/* 100 */     if (StringUtil.isNotEmpty(viewName)) {
/* 101 */       sql = sql + " where lower(view_name) like '" + viewName.toLowerCase() + "%'";
/*     */     }
/* 103 */     return getForList(sql, pageBean, String.class, "oracle");
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean) throws Exception
/*     */   {
/* 108 */     String sql = "select view_name from user_views ";
/* 109 */     if (StringUtil.isNotEmpty(viewName)) {
/* 110 */       sql = sql + " WHERE UPPER(VIEW_NAME) LIKE '%" + viewName.toUpperCase() + "%'";
/*     */     }
/* 112 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 116 */         TableModel tableModel = new TableModel();
/* 117 */         tableModel.setName(rs.getString("VIEW_NAME"));
/* 118 */         return tableModel;
/*     */       }
/*     */     };
/* 121 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "oracle");
/*     */ 
/* 123 */     List tableNames = new ArrayList();
/*     */ 
/* 125 */     for (TableModel model : tableModels) {
/* 126 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 129 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 131 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 133 */       for (TableModel model : tableModels)
/* 134 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 135 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 140 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 151 */     String sql = String.format("SELECT A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,  A.DATA_LENGTH LENGTH,A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE,A.DATA_DEFAULT, A.NULLABLE NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, 0 AS IS_PK FROM  USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME  AND  A.TABLE_NAME = B.TABLE_NAME  AND  UPPER(A.TABLE_NAME) =UPPER('%s') ORDER BY A.COLUMN_ID", new Object[] { tableName });
/*     */ 
/* 154 */     Map map = new HashMap();
/* 155 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new OracleColumnMap());
/* 156 */     for (ColumnModel model : list) {
/* 157 */       model.setTableName(tableName);
/*     */     }
/* 159 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 169 */     String sql = "SELECT  A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  0 AS IS_PK  FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME AND    A.TABLE_NAME = B.TABLE_NAME ";
/* 170 */     Map map = new HashMap();
/* 171 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 172 */       return map;
/*     */     }
/* 174 */     StringBuffer buf = new StringBuffer();
/* 175 */     for (String str : tableNames) {
/* 176 */       buf.append("'" + str + "',");
/*     */     }
/* 178 */     buf.deleteCharAt(buf.length() - 1);
/* 179 */     sql = sql + " AND A.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 183 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new OracleColumnMap());
/* 184 */     for (ColumnModel columnModel : columnModels) {
/* 185 */       String tableName = columnModel.getTableName();
/* 186 */       if (map.containsKey(tableName)) {
/* 187 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 189 */         List cols = new ArrayList();
/* 190 */         cols.add(columnModel);
/* 191 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 194 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql) throws Exception
/*     */   {
/* 199 */     String getSql = "CREATE OR REPLACE VIEW " + viewName + " as (" + sql + ")";
/*     */ 
/* 201 */     this.jdbcTemplate.execute(getSql);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.OracleDbView
 * JD-Core Version:    0.6.2
 */