/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.model.TableIndex;
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.AbstractTableOperator;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.sql.DataSource;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class MysqlTableOperator extends AbstractTableOperator
/*     */ {
/*  32 */   private int BATCHSIZE = 100;
/*     */ 
/*     */   public void createTable(TableModel model)
/*     */     throws SQLException
/*     */   {
/*  41 */     List columnList = model.getColumnList();
/*     */ 
/*  44 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  46 */     String pkColumn = null;
/*     */ 
/*  48 */     sb.append("CREATE TABLE " + model.getName() + " (\n");
/*  49 */     for (int i = 0; i < columnList.size(); i++)
/*     */     {
/*  52 */       ColumnModel cm = (ColumnModel)columnList.get(i);
/*  53 */       sb.append(cm.getName()).append(" ");
/*  54 */       sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
/*  55 */       sb.append(" ");
/*     */ 
/*  57 */       String defaultValue = cm.getDefaultValue();
/*     */ 
/*  60 */       if (StringUtil.isNotEmpty(defaultValue)) {
/*  61 */         sb.append(" default " + defaultValue);
/*     */       }
/*     */ 
/*  69 */       if (cm.getIsPk()) {
/*  70 */         if (pkColumn == null)
/*  71 */           pkColumn = cm.getName();
/*     */         else {
/*  73 */           pkColumn = pkColumn + "," + cm.getName();
/*     */         }
/*     */       }
/*     */ 
/*  77 */       if ((cm.getComment() != null) && (cm.getComment().length() > 0)) {
/*  78 */         sb.append(" COMMENT '" + cm.getComment() + "'");
/*     */       }
/*  80 */       sb.append(",\n");
/*     */     }
/*     */ 
/*  83 */     if (pkColumn != null) {
/*  84 */       sb.append(" PRIMARY KEY (" + pkColumn + ")");
/*     */     }
/*     */ 
/*  87 */     sb.append("\n)");
/*  88 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/*  89 */       sb.append(" COMMENT='" + model.getComment() + "'");
/*     */     }
/*     */ 
/*  92 */     sb.append(";");
/*     */ 
/*  94 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/* 101 */     StringBuffer sb = new StringBuffer();
/* 102 */     sb.append("ALTER TABLE ");
/* 103 */     sb.append(tableName);
/* 104 */     sb.append(" COMMENT '");
/* 105 */     sb.append(comment);
/* 106 */     sb.append("';\n");
/*     */ 
/* 108 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 115 */     StringBuffer sb = new StringBuffer();
/* 116 */     sb.append("ALTER TABLE ").append(tableName);
/* 117 */     sb.append(" ADD (");
/* 118 */     sb.append(model.getName()).append(" ");
/* 119 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 124 */     if (!model.getIsNull())
/*     */     {
/* 126 */       sb.append(" NOT NULL ");
/*     */     }
/* 128 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/*     */     {
/* 130 */       sb.append(" COMMENT '" + model.getComment() + "'");
/*     */     }
/* 132 */     sb.append(");");
/*     */ 
/* 134 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 141 */     StringBuffer sb = new StringBuffer();
/* 142 */     sb.append("ALTER TABLE ").append(tableName);
/* 143 */     sb.append(" CHANGE " + columnName + " " + model.getName()).append(" ");
/* 144 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 146 */     if (!model.getIsNull())
/*     */     {
/* 148 */       sb.append(" NOT NULL ");
/*     */     }
/* 150 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/*     */     {
/* 152 */       sb.append(" COMMENT '" + model.getComment() + "'");
/*     */     }
/* 154 */     sb.append(";");
/*     */ 
/* 156 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
/*     */   {
/* 161 */     if ("varchar".equals(columnType)) {
/* 162 */       return "VARCHAR(" + charLen + ')';
/*     */     }
/* 164 */     if ("number".equals(columnType)) {
/* 165 */       return "DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")";
/*     */     }
/* 167 */     if ("date".equals(columnType))
/* 168 */       return "DATETIME";
/* 169 */     if ("int".equals(columnType))
/*     */     {
/* 171 */       return "BIGINT(" + intLen + ")";
/*     */     }
/* 173 */     if ("clob".equals(columnType))
/*     */     {
/* 175 */       return "TEXT";
/*     */     }
/*     */ 
/* 178 */     return "";
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/* 185 */     String sql = "drop table if exists " + tableName;
/* 186 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/* 192 */     String sql = "ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_" + fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
/* 193 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 198 */     String sql = "ALTER TABLE " + tableName + " DROP FOREIGN KEY " + keyName;
/*     */ 
/* 200 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public String getDbType()
/*     */   {
/* 206 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index)
/*     */   {
/* 216 */     String sql = generateIndexDDL(index);
/* 217 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName)
/*     */   {
/* 222 */     String sql = "drop index " + indexName;
/* 223 */     sql = sql + " on " + tableName;
/* 224 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/* 229 */     if (getIndexesByFuzzyMatching(tableName, indexName, Boolean.valueOf(true)).size() > 0) {
/* 230 */       TableIndex index = (TableIndex)getIndexesByFuzzyMatching(tableName, indexName, Boolean.valueOf(true)).get(0);
/*     */       try {
/* 232 */         return dedicatePKIndex(index);
/*     */       }
/*     */       catch (SQLException e) {
/* 235 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 238 */     return null;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 244 */     List indexList = getIndexesByFuzzyMatching(tableName, null, Boolean.valueOf(true));
/* 245 */     return dedicatePKIndex(indexList);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 251 */     String schema = getSchema();
/*     */ 
/* 253 */     String sql = "SELECT TABLE_NAME,INDEX_NAME,COLUMN_NAME,NULLABLE,INDEX_TYPE,NON_UNIQUE FROM  INFORMATION_SCHEMA.STATISTICS WHERE 1=1";
/*     */ 
/* 256 */     if (!StringUtil.isEmpty(schema)) {
/* 257 */       sql = sql + " AND TABLE_SCHEMA='" + schema + "'";
/*     */     }
/* 259 */     if (!StringUtil.isEmpty(tableName)) {
/* 260 */       sql = sql + " AND UPPER(TABLE_NAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 263 */     if (!StringUtil.isEmpty(indexName)) {
/* 264 */       sql = sql + " AND UPPER(INDEX_NAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 267 */     List<TableIndex> indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 271 */         TableIndex index = new TableIndex();
/* 272 */         List columns = new ArrayList();
/* 273 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 274 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 275 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 276 */         index.setUnique(rs.getInt("NON_UNIQUE") == 0);
/*     */ 
/* 278 */         columns.add(rs.getString("COLUMN_NAME"));
/* 279 */         index.setIndexFields(columns);
/* 280 */         return index;
/*     */       }
/*     */     });
/* 285 */     List<TableIndex> indexList = new ArrayList<TableIndex>();
/* 286 */     for (TableIndex index : indexes) {
/* 287 */       boolean found = false;
/* 288 */       for (TableIndex index1 : indexList) {
/* 289 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 291 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 292 */           found = true;
/* 293 */           break;
/*     */         }
/*     */       }
/* 296 */       if (!found) {
/* 297 */         indexList.add(index);
/*     */       }
/*     */     }
/*     */ 
/* 301 */     if (getDDL.booleanValue()) {
/* 302 */       for (TableIndex index : indexList) {
/* 303 */         index.setIndexDdl(generateIndexDDL(index));
/*     */       }
/*     */     }
/*     */ 
/* 307 */     dedicatePKIndex(indexList);
/* 308 */     return indexList;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> dedicatePKIndex(List<TableIndex> indexList)
/*     */   {
/* 313 */     List tableNames = new ArrayList();
/* 314 */     for (TableIndex index : indexList)
/*     */     {
/* 316 */       if (!tableNames.contains(index.getIndexTable())) {
/* 317 */         tableNames.add(index.getIndexTable());
/*     */       }
/*     */     }
/* 320 */     Map tablePKColsMaps = getTablesPKColsByNames(tableNames);
/* 321 */     for (TableIndex index : indexList) {
/* 322 */       if (isListEqual(index.getIndexFields(), (List)tablePKColsMaps.get(index.getIndexTable())))
/* 323 */         index.setPkIndex(true);
/*     */       else {
/* 325 */         index.setPkIndex(false);
/*     */       }
/*     */     }
/*     */ 
/* 329 */     return indexList;
/*     */   }
/*     */ 
/*     */   private TableIndex dedicatePKIndex(TableIndex index) throws SQLException {
/* 333 */     List pkCols = getPKColumns(index.getIndexName());
/* 334 */     if (isListEqual(index.getIndexFields(), pkCols))
/* 335 */       index.setPkIndex(true);
/*     */     else {
/* 337 */       index.setPkIndex(false);
/*     */     }
/* 339 */     return index;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/* 345 */     String schema = getSchema();
/*     */ 
/* 347 */     String sql = "SELECT TABLE_NAME,INDEX_NAME,COLUMN_NAME,NULLABLE,INDEX_TYPE,NON_UNIQUE FROM  INFORMATION_SCHEMA.STATISTICS WHERE 1=1";
/*     */ 
/* 350 */     if (!StringUtil.isEmpty(schema)) {
/* 351 */       sql = sql + " AND TABLE_SCHEMA='" + schema + "'";
/*     */     }
/* 353 */     if (!StringUtil.isEmpty(tableName)) {
/* 354 */       sql = sql + " AND UPPER(TABLE_NAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 357 */     if (!StringUtil.isEmpty(indexName)) {
/* 358 */       sql = sql + " AND UPPER(INDEX_NAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 362 */     if (pageBean != null) {
/* 363 */       int currentPage = pageBean.getCurrentPage();
/* 364 */       int pageSize = pageBean.getPageSize();
/* 365 */       int offset = (currentPage - 1) * pageSize;
/* 366 */       String totalSql = this.dialect.getCountSql(sql);
/* 367 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 368 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 369 */       pageBean.setTotalCount(total);
/*     */     }
/*     */ 
/* 372 */     List<TableIndex> indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 376 */         TableIndex index = new TableIndex();
/* 377 */         List columns = new ArrayList();
/* 378 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 379 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 380 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 381 */         index.setUnique(rs.getInt("NON_UNIQUE") == 0);
/*     */ 
/* 383 */         columns.add(rs.getString("COLUMN_NAME"));
/* 384 */         index.setIndexFields(columns);
/* 385 */         return index;
/*     */       }
/*     */     });
/* 389 */     List<TableIndex> indexList = new ArrayList<TableIndex>();
/* 390 */     for (TableIndex index : indexes) {
/* 391 */       boolean found = false;
/* 392 */       for (TableIndex index1 : indexList) {
/* 393 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 395 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 396 */           found = true;
/* 397 */           break;
/*     */         }
/*     */       }
/* 400 */       if (!found) {
/* 401 */         indexList.add(index);
/*     */       }
/*     */     }
/*     */ 
/* 405 */     if (getDDL.booleanValue()) {
/* 406 */       for (TableIndex index : indexList) {
/* 407 */         index.setIndexDdl(generateIndexDDL(index));
/*     */       }
/*     */     }
/* 410 */     dedicatePKIndex(indexList);
/* 411 */     return indexList;
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName)
/*     */   {
/* 416 */     String sql = "SHOW CREATE TABLE " + tableName;
/*     */ 
/* 418 */     List ddls = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 421 */         return rs.getString("Create Table");
/*     */       }
/*     */     });
/* 425 */     String ddl = (String)ddls.get(0);
/*     */ 
/* 427 */     Pattern pattern = Pattern.compile("ENGINE\\s*=\\s*\\S+", 2);
/* 428 */     Matcher matcher = pattern.matcher(ddl);
/* 429 */     if (matcher.find()) {
/* 430 */       String str = matcher.group();
/* 431 */       String sql_ = "ALTER TABLE " + tableName + " " + str;
/* 432 */       this.jdbcTemplate.execute(sql_);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> getPKColumns(String tableName)
/*     */     throws SQLException
/*     */   {
/* 440 */     String schema = getSchema();
/* 441 */     String sql = "SELECT k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema='" + schema + "' " + "AND t.table_name='" + tableName + "'";
/*     */ 
/* 448 */     List columns = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum)
/*     */         throws SQLException
/*     */       {
/* 453 */         String column = rs.getString(1);
/* 454 */         return column;
/*     */       }
/*     */     });
/* 457 */     return columns;
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getPKColumns(List<String> tableNames) throws SQLException
/*     */   {
/* 462 */     StringBuffer sb = new StringBuffer();
/* 463 */     for (String name : tableNames) {
/* 464 */       sb.append("'");
/* 465 */       sb.append(name);
/* 466 */       sb.append("',");
/*     */     }
/* 468 */     sb.deleteCharAt(sb.length() - 1);
/*     */ 
/* 470 */     String schema = getSchema();
/* 471 */     String sql = "SELECT t.table_name,k.column_name FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k USING(constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY' AND t.table_schema='" + schema + "' " + "AND t.table_name in (" + sb.toString().toUpperCase() + ")";
/*     */ 
/* 479 */     Map columnsMap = new HashMap();
/*     */ 
/* 481 */     List<Map> maps = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 485 */         String table = rs.getString(1);
/* 486 */         String column = rs.getString(2);
/* 487 */         Map map = new HashMap();
/* 488 */         map.put("name", table);
/* 489 */         map.put("column", column);
/* 490 */         return map;
/*     */       }
/*     */     });
/* 494 */     for (Map map : maps) {
/* 495 */       if (columnsMap.containsKey(map.get("name"))) {
/* 496 */         ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
/*     */       } else {
/* 498 */         List cols = new ArrayList();
/* 499 */         cols.add(map.get("column"));
/* 500 */         columnsMap.put(map.get("name"), cols);
/*     */       }
/*     */     }
/*     */ 
/* 504 */     return columnsMap;
/*     */   }
/*     */ 
/*     */   private String getSchema()
/*     */   {
/* 512 */     String schema = null;
/*     */     try {
/* 514 */       schema = this.jdbcTemplate.getDataSource().getConnection().getCatalog();
/*     */     }
/*     */     catch (SQLException e) {
/* 517 */       e.printStackTrace();
/*     */     }
/* 519 */     return schema;
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 528 */     StringBuffer ddl = new StringBuffer();
/* 529 */     ddl.append("CREATE");
/* 530 */     if (index.isUnique()) {
/* 531 */       ddl.append(" UNIQUE");
/*     */     }
/* 533 */     ddl.append(" INDEX");
/* 534 */     ddl.append(" " + index.getIndexName());
/* 535 */     ddl.append(" USING");
/* 536 */     ddl.append(" " + index.getIndexType());
/* 537 */     ddl.append(" ON " + index.getIndexTable());
/*     */ 
/* 539 */     for (String column : index.getIndexFields()) {
/* 540 */       ddl.append(column + ",");
/*     */     }
/* 542 */     if (!StringUtil.isEmpty(index.getIndexComment())) {
/* 543 */       ddl.append("COMMENT '" + index.getIndexComment() + "'");
/*     */     }
/* 545 */     ddl.replace(ddl.length() - 1, ddl.length(), ")");
/* 546 */     return ddl.toString();
/*     */   }
/*     */ 
/*     */   private boolean isIndexExist(String tableName, String indexName)
/*     */   {
/* 555 */     String schema = getSchema();
/*     */ 
/* 557 */     String sql = "SELECT COUNT(*) FROM  INFORMATION_SCHEMA.STATISTICS WHERE 1=1";
/*     */ 
/* 560 */     if (!StringUtil.isEmpty(schema)) {
/* 561 */       sql = sql + " AND TABLE_SCHEMA='" + schema + "'";
/*     */     }
/* 563 */     if (!StringUtil.isEmpty(tableName)) {
/* 564 */       sql = sql + " AND UPPER(TABLE_NAME) = UPPER('" + tableName + "')";
/*     */     }
/*     */ 
/* 567 */     if (!StringUtil.isEmpty(indexName)) {
/* 568 */       sql = sql + " AND UPPER(INDEX_NAME) = UPPER('" + indexName + "')";
/*     */     }
/* 570 */     int count = this.jdbcTemplate.queryForInt(sql);
/* 571 */     if (count > 0) {
/* 572 */       return true;
/*     */     }
/* 574 */     return false;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getTablesPKColsByNames(List<String> tableNames)
/*     */   {
/* 584 */     Map tableMaps = new HashMap();
/* 585 */     List names = new ArrayList();
/* 586 */     for (int i = 1; i <= tableNames.size(); i++) {
/* 587 */       names.add(tableNames.get(i - 1));
/* 588 */       if ((i % this.BATCHSIZE == 0) || (i == tableNames.size())) {
/*     */         try
/*     */         {
/* 591 */           Map map = getPKColumns(names);
/* 592 */           tableMaps.putAll(map);
/* 593 */           names.clear();
/*     */         }
/*     */         catch (SQLException e) {
/* 596 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 600 */     return tableMaps;
/*     */   }
/*     */ 
/*     */   private boolean isListEqual(List list1, List list2)
/*     */   {
/* 610 */     if (list1 == null) {
/* 611 */       if (list2 == null) {
/* 612 */         return true;
/*     */       }
/* 614 */       return false;
/*     */     }
/* 616 */     if (list2 == null) {
/* 617 */       return false;
/*     */     }
/* 619 */     if (list1.size() != list2.size()) {
/* 620 */       return false;
/*     */     }
/* 622 */     if (list1.containsAll(list2)) {
/* 623 */       return true;
/*     */     }
/* 625 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 632 */     String schema = getSchema();
/* 633 */     String sql = "select count(1) from information_schema.TABLES t where t.TABLE_SCHEMA='" + schema + "' and table_name ='" + tableName.toUpperCase() + "'";
/* 634 */     return this.jdbcTemplate.queryForInt(sql) > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.MysqlTableOperator
 * JD-Core Version:    0.6.2
 */