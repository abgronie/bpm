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
/*     */ public class KingBaseTableOperator extends AbstractTableOperator
/*     */ {
/*  28 */   protected int BATCHSIZE = 100;
/*     */ 
/*     */   public void createTable(TableModel model)
/*     */     throws SQLException
/*     */   {
/*  34 */     List columnList = model.getColumnList();
/*     */ 
/*  37 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  39 */     String pkColumn = null;
/*     */ 
/*  43 */     List<String> columnCommentList = new ArrayList<String>();
/*     */ 
/*  45 */     sb.append("CREATE TABLE " + model.getName() + " (\n");
/*  46 */     for (int i = 0; i < columnList.size(); i++)
/*     */     {
/*  48 */       ColumnModel cm = (ColumnModel)columnList.get(i);
/*  49 */       sb.append("    ").append(cm.getName()).append("    ");
/*  50 */       sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
/*  51 */       sb.append(" ");
/*     */ 
/*  54 */       if (cm.getIsPk()) {
/*  55 */         if (pkColumn == null)
/*  56 */           pkColumn = cm.getName();
/*     */         else {
/*  58 */           pkColumn = pkColumn + "," + cm.getName();
/*     */         }
/*     */       }
/*     */ 
/*  62 */       if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
/*  63 */         sb.append(" DEFAULT " + cm.getDefaultValue());
/*     */       }
/*     */ 
/*  72 */       if ((cm.getComment() != null) && (cm.getComment().length() > 0))
/*     */       {
/*  74 */         columnCommentList.add("COMMENT ON COLUMN " + model.getName() + "." + cm.getName() + " IS '" + cm.getComment() + "'\n");
/*     */       }
/*  76 */       sb.append(",\n");
/*     */     }
/*     */ 
/*  79 */     if (pkColumn != null) {
/*  80 */       sb.append("    CONSTRAINT PK_").append(model.getName()).append(" PRIMARY KEY (").append(pkColumn).append(")");
/*     */     }
/*     */ 
/*  85 */     sb.append("\n)");
/*     */ 
/*  87 */     this.jdbcTemplate.execute(sb.toString());
/*  88 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/*  89 */       this.jdbcTemplate.execute("COMMENT ON TABLE " + model.getName() + " IS '" + model.getComment() + "'\n");
/*     */     }
/*  91 */     for (String columnComment : columnCommentList)
/*  92 */       this.jdbcTemplate.execute(columnComment);
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/*  98 */     StringBuffer sb = new StringBuffer();
/*  99 */     sb.append("COMMENT ON TABLE ");
/* 100 */     sb.append(tableName);
/* 101 */     sb.append(" IS '");
/* 102 */     sb.append(comment);
/* 103 */     sb.append("'\n");
/* 104 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 111 */     StringBuffer sb = new StringBuffer();
/* 112 */     sb.append("ALTER TABLE ").append(tableName);
/* 113 */     sb.append(" ADD ");
/* 114 */     sb.append(model.getName()).append(" ");
/* 115 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 118 */     if (StringUtil.isNotEmpty(model.getDefaultValue())) {
/* 119 */       sb.append(" DEFAULT " + model.getDefaultValue());
/*     */     }
/*     */ 
/* 125 */     sb.append("\n");
/* 126 */     this.jdbcTemplate.execute(sb.toString());
/* 127 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 128 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS '" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 137 */     if (!columnName.equals(model.getName())) {
/* 138 */       StringBuffer modifyName = new StringBuffer("ALTER TABLE ").append(tableName);
/* 139 */       modifyName.append(" RENAME COLUMN ").append(columnName).append(" TO ").append(model.getName());
/* 140 */       this.jdbcTemplate.execute(modifyName.toString());
/*     */     }
/*     */ 
/* 144 */     StringBuffer sb = new StringBuffer();
/*     */ 
/* 146 */     sb.append("ALTER TABLE ").append(tableName);
/* 147 */     sb.append(" MODIFY(" + model.getName()).append(" ");
/* 148 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 150 */     if (!model.getIsNull())
/*     */     {
/* 152 */       sb.append(" NOT NULL ");
/*     */     }
/* 154 */     sb.append(")");
/*     */ 
/* 156 */     this.jdbcTemplate.execute(sb.toString());
/*     */ 
/* 159 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 160 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS'" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
/*     */   {
/* 173 */     if ("varchar".equals(columnType))
/* 174 */       return "VARCHAR2(" + charLen + ')';
/* 175 */     if ("number".equals(columnType))
/* 176 */       return "NUMBER(" + (intLen + decimalLen) + "," + decimalLen + ")";
/* 177 */     if ("date".equals(columnType))
/* 178 */       return "DATE";
/* 179 */     if ("int".equals(columnType))
/* 180 */       return "NUMBER(" + intLen + ")";
/* 181 */     if ("clob".equals(columnType)) {
/* 182 */       return "CLOB";
/*     */     }
/* 184 */     return "VARCHAR2(50)";
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/* 193 */     String selSql = "select count(*) amount from user_objects where object_name = upper('" + tableName + "')";
/* 194 */     int rtn = this.jdbcTemplate.queryForInt(selSql);
/* 195 */     if (rtn > 0) {
/* 196 */       String sql = "drop table " + tableName;
/* 197 */       this.jdbcTemplate.execute(sql);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/* 207 */     String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_" + fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
/* 208 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 216 */     String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  " + keyName;
/* 217 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index)
/*     */   {
/* 225 */     String sql = generateIndexDDL(index);
/* 226 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 235 */     StringBuffer sql = new StringBuffer();
/* 236 */     sql.append("CREATE ");
/* 237 */     if ((!StringUtil.isEmpty(index.getIndexType())) && 
/* 238 */       (index.getIndexType().equalsIgnoreCase("BITMAP"))) {
/* 239 */       sql.append("BITMAP ");
/*     */     }
/*     */ 
/* 242 */     sql.append("INDEX ");
/* 243 */     sql.append(index.getIndexName());
/* 244 */     sql.append(" ON ");
/* 245 */     sql.append(index.getIndexTable());
/* 246 */     sql.append("(");
/* 247 */     for (String field : index.getIndexFields()) {
/* 248 */       sql.append(field);
/* 249 */       sql.append(",");
/*     */     }
/* 251 */     sql.deleteCharAt(sql.length() - 1);
/* 252 */     sql.append(")");
/* 253 */     return sql.toString();
/*     */   }
/*     */ 
/*     */   public String getDbType()
/*     */   {
/* 260 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName) {
/* 264 */     String sql = "DROP INDEX " + indexName;
/* 265 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName) {
/* 269 */     String sql = "ALTER INDEX " + indexName + " REBUILD";
/* 270 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/* 277 */     String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  WHERE IDX.INDEX_NAME=UPPER('" + indexName + "')";
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
/* 312 */     String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  WHERE IDX.TABLE_NAME=UPPER('" + tableName + "')";
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
/* 341 */     dedicateFKIndex(indexList);
/* 342 */     dedicatePKIndex(indexList);
/* 343 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 348 */     return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/*     */     String sql;
/* 355 */     if (getDDL.booleanValue()) {
/* 356 */       sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME WHERE 1=1";
/*     */     }
/*     */     else
/*     */     {
/* 362 */       sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME WHERE 1=1";
/*     */     }
/*     */ 
/* 367 */     if (!StringUtil.isEmpty(tableName)) {
/* 368 */       sql = sql + " AND UPPER(IDX.TABLE_NAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 371 */     if (!StringUtil.isEmpty(indexName)) {
/* 372 */       sql = sql + " AND UPPER(IDX.INDEX_NAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 375 */     if (pageBean != null) {
/* 376 */       int currentPage = pageBean.getCurrentPage();
/* 377 */       int pageSize = pageBean.getPageSize();
/* 378 */       int offset = (currentPage - 1) * pageSize;
/* 379 */       String totalSql = this.dialect.getCountSql(sql);
/* 380 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 381 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 382 */       pageBean.setTotalCount(total);
/*     */     }
/*     */ 
/* 385 */     this.logger.debug(sql);
/*     */ 
/* 387 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 391 */         TableIndex index = new TableIndex();
/* 392 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 393 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 394 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 395 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 396 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 397 */         index.setIndexStatus(rs.getString("STATUS"));
/*     */ 
/* 400 */         List indexFields = new ArrayList();
/* 401 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 402 */         index.setIndexFields(indexFields);
/* 403 */         return index;
/*     */       }
/*     */     });
/* 409 */     List indexList = mergeIndex(indexes);
/*     */ 
/* 413 */     dedicatePKIndex(indexList);
/* 414 */     return indexList;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> mergeIndex(List<TableIndex> indexes)
/*     */   {
/* 420 */     List<TableIndex> indexList = new ArrayList<TableIndex>();
/* 421 */     for (TableIndex index : indexes) {
/* 422 */       boolean found = false;
/* 423 */       for (TableIndex index1 : indexList) {
/* 424 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 426 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 427 */           found = true;
/* 428 */           break;
/*     */         }
/*     */       }
/* 431 */       if (!found) {
/* 432 */         indexList.add(index);
/*     */       }
/*     */     }
/* 435 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<String> getPKColumns(String tableName) throws SQLException
/*     */   {
/* 440 */     String sql = "SELECT cols.column_name FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS WHERE UPPER(cols.table_name) = UPPER('" + tableName + "')" + " AND cons.constraint_type = 'P'  AND cols.position=1" + " AND cons.constraint_name = cols.constraint_name" + " AND cons.owner = cols.owner";
/*     */ 
/* 446 */     List columns = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 449 */         String column = rs.getString(1);
/* 450 */         return column;
/*     */       }
/*     */     });
/* 453 */     return columns;
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getPKColumns(List<String> tableNames)
/*     */     throws SQLException
/*     */   {
/* 460 */     StringBuffer sb = new StringBuffer();
/* 461 */     for (String name : tableNames) {
/* 462 */       sb.append("'");
/* 463 */       sb.append(name);
/* 464 */       sb.append("',");
/*     */     }
/* 466 */     sb.deleteCharAt(sb.length() - 1);
/*     */ 
/* 468 */     String sql = "SELECT cols.table_name,cols.column_name FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS WHERE UPPER(cols.table_name) in (" + sb.toString().toUpperCase() + ")" + " AND cons.constraint_type = 'P' AND COLS.POSITION=1" + " AND cons.constraint_name = cols.constraint_name" + " AND CONS.OWNER = COLS.OWNER";
/*     */ 
/* 475 */     Map columnsMap = new HashMap();
/*     */ 
/* 477 */     List<Map> maps = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 480 */         String table = rs.getString(1);
/* 481 */         String column = rs.getString(2);
/* 482 */         Map map = new HashMap();
/* 483 */         map.put("name", table);
/* 484 */         map.put("column", column);
/* 485 */         return map;
/*     */       }
/*     */     });
/* 489 */     for (Map map : maps) {
/* 490 */       if (columnsMap.containsKey(map.get("name"))) {
/* 491 */         ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
/*     */       } else {
/* 493 */         List cols = new ArrayList();
/* 494 */         cols.add(map.get("column"));
/* 495 */         columnsMap.put(map.get("name"), cols);
/*     */       }
/*     */     }
/*     */ 
/* 499 */     return columnsMap;
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect)
/*     */   {
/* 504 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getTablesPKColsByNames(List<String> tableNames)
/*     */   {
/* 514 */     Map tableMaps = new HashMap();
/* 515 */     List names = new ArrayList();
/* 516 */     for (int i = 1; i <= tableNames.size(); i++) {
/* 517 */       names.add(tableNames.get(i - 1));
/* 518 */       if ((i % this.BATCHSIZE == 0) || (i == tableNames.size())) {
/*     */         try
/*     */         {
/* 521 */           Map map = getPKColumns(names);
/* 522 */           tableMaps.putAll(map);
/* 523 */           names.clear();
/*     */         } catch (SQLException e) {
/* 525 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 529 */     return tableMaps;
/*     */   }
/*     */ 
/*     */   private boolean isListEqual(List<?> list1, List<?> list2)
/*     */   {
/* 539 */     if (list1 == null) {
/* 540 */       if (list2 == null) {
/* 541 */         return true;
/*     */       }
/* 543 */       return false;
/*     */     }
/* 545 */     if (list2 == null) {
/* 546 */       return false;
/*     */     }
/*     */ 
/* 550 */     if (list1.size() != list2.size()) {
/* 551 */       return false;
/*     */     }
/* 553 */     if (list1.containsAll(list2)) {
/* 554 */       return true;
/*     */     }
/* 556 */     return false;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> dedicatePKIndex(List<TableIndex> indexList)
/*     */   {
/* 566 */     List tableNames = new ArrayList();
/* 567 */     for (TableIndex index : indexList)
/*     */     {
/* 569 */       if (!tableNames.contains(index.getIndexTable())) {
/* 570 */         tableNames.add(index.getIndexTable());
/*     */       }
/*     */     }
/* 573 */     Map tablePKColsMaps = getTablesPKColsByNames(tableNames);
/* 574 */     for (TableIndex index : indexList) {
/* 575 */       if (isListEqual(index.getIndexFields(), (List)tablePKColsMaps.get(index.getIndexTable())))
/* 576 */         index.setPkIndex(true);
/*     */       else {
/* 578 */         index.setPkIndex(false);
/*     */       }
/*     */     }
/*     */ 
/* 582 */     return indexList;
/*     */   }
/*     */ 
/*     */   private TableIndex dedicatePKIndex(TableIndex index)
/*     */   {
/*     */     try
/*     */     {
/* 593 */       List pkCols = getPKColumns(index.getIndexName());
/* 594 */       if (isListEqual(index.getIndexFields(), pkCols))
/* 595 */         index.setPkIndex(true);
/*     */       else
/* 597 */         index.setPkIndex(false);
/*     */     }
/*     */     catch (SQLException e) {
/* 600 */       e.printStackTrace();
/*     */     }
/* 602 */     return index;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> dedicateFKIndex(List<TableIndex> indexList)
/*     */   {
/* 611 */     List tableNames = new ArrayList();
/* 612 */     for (TableIndex index : indexList)
/*     */     {
/* 614 */       if (!tableNames.contains(index.getIndexTable())) {
/* 615 */         tableNames.add(index.getIndexTable());
/*     */       }
/*     */     }
/* 618 */     Map tableFKColsMaps = getTablesFKColsByNames(tableNames);
/* 619 */     for (TableIndex index : indexList) {
/* 620 */       if (isListEqual(index.getIndexFields(), (List)tableFKColsMaps.get(index.getIndexTable()))) {
/* 621 */         indexList.remove(index);
/*     */       }
/*     */     }
/* 624 */     return indexList;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getTablesFKColsByNames(List<String> tableNames)
/*     */   {
/* 629 */     Map tableMaps = new HashMap();
/* 630 */     List names = new ArrayList();
/* 631 */     for (int i = 1; i <= tableNames.size(); i++) {
/* 632 */       names.add(tableNames.get(i - 1));
/* 633 */       if ((i % this.BATCHSIZE == 0) || (i == tableNames.size())) {
/*     */         try
/*     */         {
/* 636 */           Map map = getFKColumns(names);
/* 637 */           tableMaps.putAll(map);
/* 638 */           names.clear();
/*     */         } catch (SQLException e) {
/* 640 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 644 */     return tableMaps;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getFKColumns(List<String> tableNames)
/*     */     throws SQLException
/*     */   {
/* 654 */     StringBuffer sb = new StringBuffer();
/* 655 */     for (String name : tableNames) {
/* 656 */       sb.append("'");
/* 657 */       sb.append(name);
/* 658 */       sb.append("',");
/*     */     }
/* 660 */     sb.deleteCharAt(sb.length() - 1);
/*     */ 
/* 662 */     String sql = "SELECT cols.table_name,cols.column_name FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS WHERE UPPER(cols.table_name) in (" + sb.toString().toUpperCase() + ")" + " AND cons.constraint_type = 'F' AND COLS.POSITION=1" + " AND cons.constraint_name = cols.constraint_name" + " AND CONS.OWNER = COLS.OWNER";
/*     */ 
/* 669 */     Map columnsMap = new HashMap();
/*     */ 
/* 671 */     List<Map> maps = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 674 */         String table = rs.getString(1);
/* 675 */         String column = rs.getString(2);
/* 676 */         Map map = new HashMap();
/* 677 */         map.put("name", table);
/* 678 */         map.put("column", column);
/* 679 */         return map;
/*     */       }
/*     */     });
/* 683 */     for (Map map : maps) {
/* 684 */       if (columnsMap.containsKey(map.get("name"))) {
/* 685 */         ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
/*     */       } else {
/* 687 */         List cols = new ArrayList();
/* 688 */         cols.add(map.get("column"));
/* 689 */         columnsMap.put(map.get("name"), cols);
/*     */       }
/*     */     }
/*     */ 
/* 693 */     return columnsMap;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 700 */     StringBuffer sql = new StringBuffer();
/* 701 */     sql.append("select COUNT(1) from user_tables t where t.TABLE_NAME='").append(tableName.toUpperCase()).append("'");
/* 702 */     return this.jdbcTemplate.queryForInt(sql.toString()) > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.KingBaseTableOperator
 * JD-Core Version:    0.6.2
 */