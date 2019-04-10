/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.db.datasource.JdbcTemplateUtil;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.BaseDbView;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.IDbView;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.table.colmap.SqlServerColumnMap;
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
/*     */ public class SqlserverDbView extends BaseDbView
/*     */   implements IDbView
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JdbcTemplate jdbcTemplate;
/*  38 */   private final String sqlAllView = "select name from sysobjects where xtype='V'";
/*  39 */   private final String SQL_GET_COLUMNS = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE,  (SELECT COUNT(*) FROM SYS.IDENTITY_COLUMNS WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN, (SELECT CAST(VALUE AS VARCHAR) FROM SYS.EXTENDED_PROPERTIES WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID) AS DESCRIPTION,  0 AS IS_PK FROM  SYS.COLUMNS A, SYS.VIEWS B, SYS.TYPES C WHERE  A.OBJECT_ID = B.OBJECT_ID  AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND B.NAME='%s' AND C.NAME<>'SYSNAME' ORDER BY A.COLUMN_ID";
/*     */ 
/*  53 */   private final String SQL_GET_COLUMNS_BATCH = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE,  (SELECT COUNT(*) FROM SYS.IDENTITY_COLUMNS WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN, (SELECT CAST(VALUE AS VARCHAR) FROM SYS.EXTENDED_PROPERTIES WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID) AS DESCRIPTION,  0 AS IS_PK FROM  SYS.COLUMNS A, SYS.VIEWS B, SYS.TYPES C WHERE  A.OBJECT_ID = B.OBJECT_ID  AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND C.NAME<>'SYSNAME' ";
/*     */ 
/*     */   public List<String> getViews(String viewName)
/*     */   {
/*  67 */     String sql = "select name from sysobjects where xtype='V'";
/*  68 */     if (StringUtil.isNotEmpty(viewName)) {
/*  69 */       sql = sql + " and name like '" + viewName + "%'";
/*     */     }
/*  71 */     return this.jdbcTemplate.queryForList(sql, String.class);
/*     */   }
/*     */ 
/*     */   public String getType(String type)
/*     */   {
/*  79 */     if ((type.indexOf("int") > -1) || (type.equals("real")) || (type.equals("numeric")) || (type.indexOf("money") > -1))
/*  80 */       return "number";
/*  81 */     if (type.indexOf("date") > -1) {
/*  82 */       return "date";
/*     */     }
/*  84 */     return "varchar";
/*     */   }
/*     */ 
/*     */   public List<String> getViews(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  91 */     String sql = "select name from sysobjects where xtype='V'";
/*  92 */     if (StringUtil.isNotEmpty(viewName)) {
/*  93 */       sql = sql + " AND NAME LIKE '" + viewName + "%'";
/*     */     }
/*  95 */     return getForList(sql, pageBean, String.class, "mssql");
/*     */   }
/*     */ 
/*     */   public List<TableModel> getViewsByName(String viewName, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/* 103 */     String sql = "select name from sysobjects where xtype='V'";
/* 104 */     if (StringUtil.isNotEmpty(viewName)) {
/* 105 */       sql = sql + " AND NAME LIKE '" + viewName + "%'";
/*     */     }
/*     */ 
/* 108 */     RowMapper rowMapper = new RowMapper()
/*     */     {
/*     */       public TableModel mapRow(ResultSet rs, int row) throws SQLException
/*     */       {
/* 112 */         TableModel tableModel = new TableModel();
/* 113 */         tableModel.setName(rs.getString("NAME"));
/* 114 */         tableModel.setComment(tableModel.getName());
/* 115 */         return tableModel;
/*     */       }
/*     */     };
/* 118 */     List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, "mssql");
/*     */ 
/* 120 */     List tableNames = new ArrayList();
/*     */ 
/* 122 */     for (TableModel model : tableModels) {
/* 123 */       tableNames.add(model.getName());
/*     */     }
/*     */ 
/* 126 */     Map tableColumnsMap = getColumnsByTableName(tableNames);
/*     */ 
/* 128 */     for (Iterator i$ = tableColumnsMap.entrySet().iterator(); i$.hasNext(); ) { 
	Map.Entry entry = (Map.Entry)i$.next();
/*     */ 
/* 130 */       for (TableModel model : tableModels)
/* 131 */         if (model.getName().equalsIgnoreCase((String)entry.getKey()))
/* 132 */           model.setColumnList((List)entry.getValue());
/*     */     }
/*     */     Map.Entry entry;
/* 136 */     return tableModels;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getColumnsByTableName(String tableName)
/*     */   {
/* 147 */     String sql = String.format("SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE,  (SELECT COUNT(*) FROM SYS.IDENTITY_COLUMNS WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN, (SELECT CAST(VALUE AS VARCHAR) FROM SYS.EXTENDED_PROPERTIES WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID) AS DESCRIPTION,  0 AS IS_PK FROM  SYS.COLUMNS A, SYS.VIEWS B, SYS.TYPES C WHERE  A.OBJECT_ID = B.OBJECT_ID  AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND B.NAME='%s' AND C.NAME<>'SYSNAME' ORDER BY A.COLUMN_ID", new Object[] { tableName });
/*     */ 
/* 150 */     Map map = new HashMap();
/* 151 */     List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, map, new SqlServerColumnMap());
/* 152 */     for (ColumnModel model : list) {
/* 153 */       model.setTableName(tableName);
/*     */     }
/* 155 */     return list;
/*     */   }
/*     */ 
/*     */   private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames)
/*     */   {
/* 165 */     String sql = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE,  (SELECT COUNT(*) FROM SYS.IDENTITY_COLUMNS WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN, (SELECT CAST(VALUE AS VARCHAR) FROM SYS.EXTENDED_PROPERTIES WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID) AS DESCRIPTION,  0 AS IS_PK FROM  SYS.COLUMNS A, SYS.VIEWS B, SYS.TYPES C WHERE  A.OBJECT_ID = B.OBJECT_ID  AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND C.NAME<>'SYSNAME' ";
/* 166 */     Map map = new HashMap();
/* 167 */     if ((tableNames != null) && (tableNames.size() == 0)) {
/* 168 */       return map;
/*     */     }
/* 170 */     StringBuffer buf = new StringBuffer();
/* 171 */     for (String str : tableNames) {
/* 172 */       buf.append("'" + str + "',");
/*     */     }
/* 174 */     buf.deleteCharAt(buf.length() - 1);
/* 175 */     sql = sql + " AND B.NAME IN (" + buf.toString() + ") ";
/*     */ 
/* 179 */     List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(this.jdbcTemplate).query(sql, new HashMap(), new SqlServerColumnMap());
/* 180 */     for (ColumnModel columnModel : columnModels) {
/* 181 */       String tableName = columnModel.getTableName();
/* 182 */       if (map.containsKey(tableName)) {
/* 183 */         ((List)map.get(tableName)).add(columnModel);
/*     */       } else {
/* 185 */         List cols = new ArrayList();
/* 186 */         cols.add(columnModel);
/* 187 */         map.put(tableName, cols);
/*     */       }
/*     */     }
/* 190 */     return map;
/*     */   }
/*     */ 
/*     */   public void createOrRep(String viewName, String sql)
/*     */     throws Exception
/*     */   {
/* 197 */     String sql_drop_view = "IF EXISTS (SELECT * FROM sysobjects WHERE xtype='V' AND name = '" + viewName + "')" + " DROP VIEW " + viewName;
/*     */ 
/* 203 */     String viewSql = "CREATE VIEW " + viewName + " AS " + sql;
/* 204 */     this.jdbcTemplate.execute(sql_drop_view);
/* 205 */     this.jdbcTemplate.execute(viewSql);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.SqlserverDbView
 * JD-Core Version:    0.6.2
 */