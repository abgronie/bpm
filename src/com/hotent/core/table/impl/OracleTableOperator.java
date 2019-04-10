/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.model.TableIndex;
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.AbstractTableOperator;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class OracleTableOperator extends AbstractTableOperator
/*     */ {
/*  29 */   protected int BATCHSIZE = 100;
/*     */ 
/*     */   public void createTable(TableModel model)
/*     */     throws SQLException
/*     */   {
/*  36 */     List columnList = model.getColumnList();
/*     */ 
/*  39 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  41 */     String pkColumn = null;
/*     */ 
/*  45 */     List<String> columnCommentList = new ArrayList<String>();
/*     */ 
/*  47 */     sb.append("CREATE TABLE " + model.getName() + " (\n");
/*  48 */     for (int i = 0; i < columnList.size(); i++)
/*     */     {
/*  50 */       ColumnModel cm = (ColumnModel)columnList.get(i);
/*  51 */       sb.append("    ").append(cm.getName()).append("    ");
/*  52 */       sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
/*  53 */       sb.append(" ");
/*     */ 
/*  56 */       if (cm.getIsPk()) {
/*  57 */         if (pkColumn == null)
/*  58 */           pkColumn = cm.getName();
/*     */         else {
/*  60 */           pkColumn = pkColumn + "," + cm.getName();
/*     */         }
/*     */       }
/*     */ 
/*  64 */       if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
/*  65 */         sb.append(" DEFAULT " + cm.getDefaultValue());
/*     */       }
/*     */ 
/*  74 */       if ((cm.getComment() != null) && (cm.getComment().length() > 0))
/*     */       {
/*  76 */         columnCommentList.add("COMMENT ON COLUMN " + model.getName() + "." + cm.getName() + " IS '" + cm.getComment() + "'\n");
/*     */       }
/*  78 */       sb.append(",\n");
/*     */     }
/*     */ 
/*  81 */     if (pkColumn != null) {
/*  82 */       sb.append("    CONSTRAINT PK_").append(model.getName()).append(" PRIMARY KEY (").append(pkColumn).append(")");
/*     */     }
/*     */ 
/*  87 */     sb.append("\n)");
/*     */ 
/*  89 */     this.jdbcTemplate.execute(sb.toString());
/*  90 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/*  91 */       this.jdbcTemplate.execute("COMMENT ON TABLE " + model.getName() + " IS '" + model.getComment() + "'\n");
/*     */     }
/*  93 */     for (String columnComment : columnCommentList)
/*  94 */       this.jdbcTemplate.execute(columnComment);
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/* 100 */     StringBuffer sb = new StringBuffer();
/* 101 */     sb.append("COMMENT ON TABLE ");
/* 102 */     sb.append(tableName);
/* 103 */     sb.append(" IS '");
/* 104 */     sb.append(comment);
/* 105 */     sb.append("'\n");
/* 106 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 113 */     StringBuffer sb = new StringBuffer();
/* 114 */     sb.append("ALTER TABLE ").append(tableName);
/* 115 */     sb.append(" ADD ");
/* 116 */     sb.append(model.getName()).append(" ");
/* 117 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 120 */     if (StringUtil.isNotEmpty(model.getDefaultValue())) {
/* 121 */       sb.append(" DEFAULT " + model.getDefaultValue());
/*     */     }
/*     */ 
/* 127 */     sb.append("\n");
/* 128 */     this.jdbcTemplate.execute(sb.toString());
/* 129 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 130 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS '" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 139 */     if (!columnName.equals(model.getName())) {
/* 140 */       StringBuffer modifyName = new StringBuffer("ALTER TABLE ").append(tableName);
/* 141 */       modifyName.append(" RENAME COLUMN ").append(columnName).append(" TO ").append(model.getName());
/* 142 */       this.jdbcTemplate.execute(modifyName.toString());
/*     */     }
/*     */ 
/* 146 */     StringBuffer sb = new StringBuffer();
/*     */ 
/* 148 */     sb.append("ALTER TABLE ").append(tableName);
/* 149 */     sb.append(" MODIFY(" + model.getName()).append(" ");
/* 150 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 152 */     if (!model.getIsNull())
/*     */     {
/* 154 */       sb.append(" NOT NULL ");
/*     */     }
/* 156 */     sb.append(")");
/*     */ 
/* 158 */     this.jdbcTemplate.execute(sb.toString());
/*     */ 
/* 161 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 162 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS'" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
/*     */   {
/* 175 */     if ("varchar".equals(columnType))
/* 176 */       return "VARCHAR2(" + charLen + ')';
/* 177 */     if ("number".equals(columnType))
/* 178 */       return "NUMBER(" + (intLen + decimalLen) + "," + decimalLen + ")";
/* 179 */     if ("date".equals(columnType))
/* 180 */       return "DATE";
/* 181 */     if ("int".equals(columnType))
/* 182 */       return "NUMBER(" + intLen + ")";
/* 183 */     if ("clob".equals(columnType)) {
/* 184 */       return "CLOB";
/*     */     }
/* 186 */     return "VARCHAR2(50)";
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/* 195 */     String selSql = "select count(*) amount from user_objects where object_name = upper('" + tableName + "')";
/* 196 */     int rtn = this.jdbcTemplate.queryForInt(selSql);
/* 197 */     if (rtn > 0) {
/* 198 */       String sql = "drop table " + tableName;
/* 199 */       this.jdbcTemplate.execute(sql);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/* 208 */     String regex = "(?im)" + TableModel.CUSTOMER_TABLE_PREFIX;
/* 209 */     String shortTableName = fkTableName.replaceFirst(regex, "");
/* 210 */     String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_" + shortTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
/* 211 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   private Boolean isForeignKeyExist(String tableName, String keyName)
/*     */   {
/* 216 */     String sql = "select count(1) from user_constraints t where t.table_name = upper('" + tableName + "') and t.constraint_type = 'R' and t.constraint_name = upper('" + keyName + "')";
/* 217 */     int result = this.jdbcTemplate.queryForInt(sql);
/* 218 */     return Boolean.valueOf(result > 0);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 223 */     if (isForeignKeyExist(tableName, keyName).booleanValue()) {
/* 224 */       String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  " + keyName;
/* 225 */       this.jdbcTemplate.execute(sql);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index)
/*     */   {
/* 231 */     String sql = generateIndexDDL(index);
/* 232 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 241 */     StringBuffer sql = new StringBuffer();
/* 242 */     sql.append("CREATE ");
/*     */ 
/* 248 */     sql.append("INDEX ");
/* 249 */     sql.append(index.getIndexName());
/* 250 */     sql.append(" ON ");
/* 251 */     sql.append(index.getIndexTable());
/* 252 */     sql.append("(");
/* 253 */     for (String field : index.getIndexFields()) {
/* 254 */       sql.append(field);
/* 255 */       sql.append(",");
/*     */     }
/* 257 */     sql.deleteCharAt(sql.length() - 1);
/* 258 */     sql.append(")");
/* 259 */     return sql.toString();
/*     */   }
/*     */ 
/*     */   public String getDbType() {
/* 263 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName) {
/* 267 */     String sql = "DROP INDEX " + indexName;
/* 268 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName) {
/* 272 */     String sql = "ALTER INDEX " + indexName + " REBUILD";
/* 273 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName) {
/* 277 */     String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM USER_INDEXES IDX JOIN USER_IND_COLUMNS IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  WHERE IDX.INDEX_NAME=UPPER('" + indexName + "')";
/*     */ 
/* 282 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 286 */         TableIndex index = new TableIndex();
/* 287 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 288 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 289 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 290 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 291 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 292 */         index.setIndexStatus(rs.getString("STATUS"));
/* 293 */         index.setIndexDdl(rs.getString("DDL"));
/* 294 */         List indexFields = new ArrayList();
/* 295 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 296 */         index.setIndexFields(indexFields);
/* 297 */         return index;
/*     */       }
/*     */     });
/* 303 */     List indexList = mergeIndex(indexes);
/* 304 */     if (indexList.size() > 0) {
/* 305 */       return dedicatePKIndex((TableIndex)indexList.get(0));
/*     */     }
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 312 */     String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM USER_INDEXES IDX JOIN USER_IND_COLUMNS IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  WHERE IDX.TABLE_NAME=UPPER('" + tableName + "')";
/*     */ 
/* 317 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 320 */         TableIndex index = new TableIndex();
/* 321 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 322 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 323 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 324 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 325 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 326 */         index.setIndexStatus(rs.getString("STATUS"));
/* 327 */         index.setIndexDdl(rs.getString("DDL"));
/*     */ 
/* 330 */         List indexFields = new ArrayList();
/* 331 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 332 */         index.setIndexFields(indexFields);
/* 333 */         return index;
/*     */       }
/*     */     });
/* 339 */     List indexList = mergeIndex(indexes);
/*     */ 
/* 341 */     dedicatePKIndex(indexList);
/* 342 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 347 */     return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/*     */     String sql;
/* 354 */     if (getDDL.booleanValue()) {
/* 355 */       sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM USER_INDEXES IDX JOIN USER_IND_COLUMNS IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME WHERE 1=1";
/*     */     }
/*     */     else
/*     */     {
/* 361 */       sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME FROM USER_INDEXES IDX JOIN USER_IND_COLUMNS IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME WHERE 1=1";
/*     */     }
/*     */ 
/* 366 */     if (!StringUtil.isEmpty(tableName)) {
/* 367 */       sql = sql + " AND UPPER(IDX.TABLE_NAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 370 */     if (!StringUtil.isEmpty(indexName)) {
/* 371 */       sql = sql + " AND UPPER(IDX.INDEX_NAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 374 */     if (pageBean != null) {
/* 375 */       int currentPage = pageBean.getCurrentPage();
/* 376 */       int pageSize = pageBean.getPageSize();
/* 377 */       int offset = (currentPage - 1) * pageSize;
/* 378 */       String totalSql = this.dialect.getCountSql(sql);
/* 379 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 380 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 381 */       pageBean.setTotalCount(total);
/*     */     }
/*     */ 
/* 384 */     this.logger.debug(sql);
/*     */ 
/* 386 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 390 */         TableIndex index = new TableIndex();
/* 391 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 392 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 393 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 394 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 395 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 396 */         index.setIndexStatus(rs.getString("STATUS"));
/*     */ 
/* 399 */         List indexFields = new ArrayList();
/* 400 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 401 */         index.setIndexFields(indexFields);
/* 402 */         return index;
/*     */       }
/*     */     });
/* 408 */     List indexList = mergeIndex(indexes);
/*     */ 
/* 410 */     dedicatePKIndex(indexList);
/* 411 */     return indexList;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> mergeIndex(List<TableIndex> indexes) {
/* 415 */     List<TableIndex> indexList = new ArrayList();
/* 416 */     for (TableIndex index : indexes) {
/* 417 */       boolean found = false;
/* 418 */       for (TableIndex index1 : indexList) {
/* 419 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 421 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 422 */           found = true;
/* 423 */           break;
/*     */         }
/*     */       }
/* 426 */       if (!found) {
/* 427 */         indexList.add(index);
/*     */       }
/*     */     }
/* 430 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<String> getPKColumns(String tableName) throws SQLException
/*     */   {
/* 435 */     String sql = "SELECT cols.column_name FROM USER_CONSTRAINTS CONS, USER_CONS_COLUMNS COLS WHERE UPPER(cols.table_name) = UPPER('" + tableName + "')" + " AND cons.constraint_type = 'P'" + " AND cons.constraint_name = cols.constraint_name" + " AND CONS.OWNER = COLS.OWNER";
/*     */ 
/* 441 */     List columns = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 444 */         String column = rs.getString(1);
/* 445 */         return column;
/*     */       }
/*     */     });
/* 448 */     return columns;
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getPKColumns(List<String> tableNames)
/*     */     throws SQLException
/*     */   {
/* 455 */     StringBuffer sb = new StringBuffer();
/* 456 */     for (String name : tableNames) {
/* 457 */       sb.append("'");
/* 458 */       sb.append(name);
/* 459 */       sb.append("',");
/*     */     }
/* 461 */     sb.deleteCharAt(sb.length() - 1);
/*     */ 
/* 463 */     String sql = "SELECT cols.table_name,cols.column_name FROM USER_CONSTRAINTS CONS, USER_CONS_COLUMNS COLS WHERE UPPER(cols.table_name) in (" + sb.toString().toUpperCase() + ")" + " AND cons.constraint_type = 'P'" + " AND cons.constraint_name = cols.constraint_name" + " AND CONS.OWNER = COLS.OWNER";
/*     */ 
/* 470 */     Map columnsMap = new HashMap();
/*     */ 
/* 472 */     List<Map> maps = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 476 */         String table = rs.getString(1);
/* 477 */         String column = rs.getString(2);
/* 478 */         Map map = new HashMap();
/* 479 */         map.put("name", table);
/* 480 */         map.put("column", column);
/* 481 */         return map;
/*     */       }
/*     */     });
/* 485 */     for (Map map : maps) {
/* 486 */       if (columnsMap.containsKey(map.get("name"))) {
/* 487 */         ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
/*     */       } else {
/* 489 */         List cols = new ArrayList();
/* 490 */         cols.add(map.get("column"));
/* 491 */         columnsMap.put(map.get("name"), cols);
/*     */       }
/*     */     }
/*     */ 
/* 495 */     return columnsMap;
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect)
/*     */   {
/* 500 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getTablesPKColsByNames(List<String> tableNames)
/*     */   {
/* 510 */     Map tableMaps = new HashMap();
/* 511 */     List names = new ArrayList();
/* 512 */     for (int i = 1; i <= tableNames.size(); i++) {
/* 513 */       names.add(tableNames.get(i - 1));
/* 514 */       if ((i % this.BATCHSIZE == 0) || (i == tableNames.size())) {
/*     */         try
/*     */         {
/* 517 */           Map map = getPKColumns(names);
/* 518 */           tableMaps.putAll(map);
/* 519 */           names.clear();
/*     */         } catch (SQLException e) {
/* 521 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 525 */     return tableMaps;
/*     */   }
/*     */ 
/*     */   private boolean isListEqual(List list1, List list2)
/*     */   {
/* 536 */     if (list1 == null) {
/* 537 */       if (list2 == null) {
/* 538 */         return true;
/*     */       }
/* 540 */       return false;
/*     */     }
/* 542 */     if (list2 == null) {
/* 543 */       return false;
/*     */     }
/*     */ 
/* 547 */     if (list1.size() != list2.size()) {
/* 548 */       return false;
/*     */     }
/* 550 */     if (list1.containsAll(list2)) {
/* 551 */       return true;
/*     */     }
/* 553 */     return false;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> dedicatePKIndex(List<TableIndex> indexList)
/*     */   {
/* 563 */     List tableNames = new ArrayList();
/* 564 */     for (TableIndex index : indexList)
/*     */     {
/* 566 */       if (!tableNames.contains(index.getIndexTable())) {
/* 567 */         tableNames.add(index.getIndexTable());
/*     */       }
/*     */     }
/* 570 */     Map tablePKColsMaps = getTablesPKColsByNames(tableNames);
/* 571 */     for (TableIndex index : indexList) {
/* 572 */       if (isListEqual(index.getIndexFields(), (List)tablePKColsMaps.get(index.getIndexTable())))
/* 573 */         index.setPkIndex(true);
/*     */       else {
/* 575 */         index.setPkIndex(false);
/*     */       }
/*     */     }
/*     */ 
/* 579 */     return indexList;
/*     */   }
/*     */ 
/*     */   private TableIndex dedicatePKIndex(TableIndex index)
/*     */   {
/*     */     try
/*     */     {
/* 590 */       List pkCols = getPKColumns(index.getIndexName());
/* 591 */       if (isListEqual(index.getIndexFields(), pkCols))
/* 592 */         index.setPkIndex(true);
/*     */       else
/* 594 */         index.setPkIndex(false);
/*     */     }
/*     */     catch (SQLException e) {
/* 597 */       e.printStackTrace();
/*     */     }
/* 599 */     return index;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 604 */     StringBuffer sql = new StringBuffer();
/* 605 */     sql.append("select COUNT(1) from user_tables t where t.TABLE_NAME='").append(tableName.toUpperCase()).append("'");
/* 606 */     return this.jdbcTemplate.queryForInt(sql.toString()) > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.OracleTableOperator
 * JD-Core Version:    0.6.2
 */