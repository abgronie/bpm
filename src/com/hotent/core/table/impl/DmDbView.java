/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseDbView;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.DmColumnMap;
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
/*     */ public class DmDbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private static final String sqlAllView = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*     */   private static final String SQL_GET_COLUMNS = "SELECT A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,  A.DATA_LENGTH LENGTH,A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE,A.DATA_DEFAULT, A.NULLABLE NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, 0 AS IS_PK FROM  USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME  AND  A.TABLE_NAME = B.TABLE_NAME  AND  UPPER(A.TABLE_NAME) =UPPER('%s') ORDER BY A.COLUMN_ID";
/*  56 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT  A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  0 AS IS_PK  FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME AND    A.TABLE_NAME = B.TABLE_NAME ";
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */     throws SQLException
/*     */   {
/*  72 */     String sql = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*  73 */     if (StringUtil.isNotEmpty(viewName)) {
/*  74 */       sql = sql + " and lower(view_name) like '" + viewName.toLowerCase() + "%'";
/*     */     }
/*  76 */     return this.jdbcTemplate.queryForList(sql, String.class);
/*     */   }
/*     */ 
/*     */   public String getType(String type)
/*     */   {
/*  83 */     type = type.toLowerCase();
/*  84 */     if (type.indexOf("number") > -1)
/*  85 */       return "number";
/*  86 */     if (type.indexOf("date") > -1) {
/*  87 */       return "date";
/*     */     }
/*  89 */     if (type.indexOf("char") > -1) {
/*  90 */       return "varchar";
/*     */     }
/*  92 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  98 */     String sql = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*  99 */     if (StringUtil.isNotEmpty(viewName)) {
/* 100 */       sql = sql + " and lower(view_name) like '" + viewName.toLowerCase() + "%'";
/*     */     }
/* 102 */     return getForList(sql, pageBean, String.class, "dm");
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean) throws Exception
/*     */   {
/* 107 */     String sql = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/* 108 */     if (StringUtil.isNotEmpty(viewName)) {
/* 109 */       sql = sql + " and UPPER(VIEW_NAME) LIKE '%" + viewName.toUpperCase() + "%'";
/*     */     }
/* 111 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 115 */         TableModel tableModel = new TableModel();
/* 116 */         tableModel.setName(rs.getString("VIEW_NAME"));
/* 117 */         return tableModel;
/*     */       }
/*     */     };
/* 120 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "dm");
/*     */ 
/* 122 */     List tableNames = new ArrayList();
/*     */ 
/* 124 */     for (TableModel model : tableModels) {
/* 125 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 128 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 130 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
				Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 132 */       for (TableModel model : tableModels)
/* 133 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 134 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */    
/* 139 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 150 */     String sql = String.format("SELECT A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,  A.DATA_LENGTH LENGTH,A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE,A.DATA_DEFAULT, A.NULLABLE NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, 0 AS IS_PK FROM  USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME  AND  A.TABLE_NAME = B.TABLE_NAME  AND  UPPER(A.TABLE_NAME) =UPPER('%s') ORDER BY A.COLUMN_ID", new Object[] { tableName });
/*     */ 
/* 153 */     Map map = new HashMap();
/* 154 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new DmColumnMap());
/* 155 */     for (ColumnModel model : list) {
/* 156 */       model.setTableName(tableName);
/*     */     }
/* 158 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 168 */     String sql = "SELECT  A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  0 AS IS_PK  FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME AND    A.TABLE_NAME = B.TABLE_NAME ";
/* 169 */     Map map = new HashMap();
/* 170 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 171 */       return map;
/*     */     }
/* 173 */     StringBuffer buf = new StringBuffer();
/* 174 */     for (String str : tableNames) {
/* 175 */       buf.append("'" + str + "',");
/*     */     }
/* 177 */     buf.deleteCharAt(buf.length() - 1);
/* 178 */     sql = sql + " AND A.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 182 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new DmColumnMap());
/* 183 */     for (ColumnModel columnModel : columnModels) {
/* 184 */       String tableName = columnModel.getTableName();
/* 185 */       if (map.containsKey(tableName)) {
/* 186 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 188 */         List cols = new ArrayList();
/* 189 */         cols.add(columnModel);
/* 190 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 193 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.DmDbView
 * JD-Core Version:    0.6.2
 */