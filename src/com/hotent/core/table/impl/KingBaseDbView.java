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
/*     */ public class KingBaseDbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private static final String sqlAllView = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*  41 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT  A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  0 AS IS_PK  FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME AND    A.TABLE_NAME = B.TABLE_NAME ";
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */     throws SQLException
/*     */   {
/*  57 */     String sql = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*  58 */     if (StringUtil.isNotEmpty(viewName)) {
/*  59 */       sql = sql + " and lower(view_name) like '" + viewName.toLowerCase() + "%'";
/*     */     }
/*  61 */     return this.jdbcTemplate.queryForList(sql, String.class);
/*     */   }
/*     */ 
/*     */   public String getType(String type)
/*     */   {
/*  68 */     type = type.toLowerCase();
/*  69 */     if (type.indexOf("number") > -1)
/*  70 */       return "number";
/*  71 */     if (type.indexOf("date") > -1) {
/*  72 */       return "date";
/*     */     }
/*  74 */     if (type.indexOf("char") > -1) {
/*  75 */       return "varchar";
/*     */     }
/*  77 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  83 */     String sql = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*  84 */     if (StringUtil.isNotEmpty(viewName)) {
/*  85 */       sql = sql + " and lower(view_name) like '" + viewName.toLowerCase() + "%'";
/*     */     }
/*  87 */     return getForList(sql, pageBean, String.class, "kingbase");
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean) throws Exception
/*     */   {
/*  92 */     String sql = "select  view_name  from \"SYS\".\"DBA_VIEWS\" where owner=user() ";
/*  93 */     if (StringUtil.isNotEmpty(viewName)) {
/*  94 */       sql = sql + " and UPPER(VIEW_NAME) LIKE '%" + viewName.toUpperCase() + "%'";
/*     */     }
/*  96 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 100 */         TableModel tableModel = new TableModel();
/* 101 */         tableModel.setName(rs.getString("VIEW_NAME"));
/* 102 */         return tableModel;
/*     */       }
/*     */     };
/* 105 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "kingbase");
/*     */ 
/* 107 */     List tableNames = new ArrayList();
/*     */ 
/* 109 */     for (TableModel model : tableModels) {
/* 110 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 113 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 115 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 117 */       for (TableModel model : tableModels)
/* 118 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 119 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     
/* 124 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 133 */     String sql = "SELECT  A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE, DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION,  0 AS IS_PK  FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.COLUMN_NAME=B.COLUMN_NAME AND    A.TABLE_NAME = B.TABLE_NAME ";
/* 134 */     Map map = new HashMap();
/* 135 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 136 */       return map;
/*     */     }
/* 138 */     StringBuffer buf = new StringBuffer();
/* 139 */     for (String str : tableNames) {
/* 140 */       buf.append("'" + str + "',");
/*     */     }
/* 142 */     buf.deleteCharAt(buf.length() - 1);
/* 143 */     sql = sql + " AND A.TABLE_NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 145 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new DmColumnMap());
/* 146 */     for (ColumnModel columnModel : columnModels) {
/* 147 */       String tableName = columnModel.getTableName();
/* 148 */       if (map.containsKey(tableName)) {
/* 149 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 151 */         List cols = new ArrayList();
/* 152 */         cols.add(columnModel);
/* 153 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 156 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.KingBaseDbView
 * JD-Core Version:    0.6.2
 */