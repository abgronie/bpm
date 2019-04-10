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
/*     */ public class DmTableOperator extends AbstractTableOperator
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
/* 209 */     String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_" + fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
/* 210 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 218 */     String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  " + keyName;
/* 219 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index)
/*     */   {
/* 227 */     String sql = generateIndexDDL(index);
/* 228 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 237 */     StringBuffer sql = new StringBuffer();
/* 238 */     sql.append("CREATE ");
/* 239 */     if ((!StringUtil.isEmpty(index.getIndexType())) && 
/* 240 */       (index.getIndexType().equalsIgnoreCase("BITMAP"))) {
/* 241 */       sql.append("BITMAP ");
/*     */     }
/*     */ 
/* 244 */     sql.append("INDEX ");
/* 245 */     sql.append(index.getIndexName());
/* 246 */     sql.append(" ON ");
/* 247 */     sql.append(index.getIndexTable());
/* 248 */     sql.append("(");
/* 249 */     for (String field : index.getIndexFields()) {
/* 250 */       sql.append(field);
/* 251 */       sql.append(",");
/*     */     }
/* 253 */     sql.deleteCharAt(sql.length() - 1);
/* 254 */     sql.append(")");
/* 255 */     return sql.toString();
/*     */   }
/*     */ 
/*     */   private boolean isIndexExist(String index) {
/* 259 */     String sql = "SELECT COUNT(*) FROM \"SYS\".\"USER_INDEXES\" WHERE INDEX_NAME = '" + index + "'";
/* 260 */     int count = this.jdbcTemplate.queryForInt(sql);
/* 261 */     if (count > 0) {
/* 262 */       return true;
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   public String getDbType()
/*     */   {
/* 271 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName) {
/* 275 */     String sql = "DROP INDEX " + indexName;
/* 276 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName) {
/* 280 */     String sql = "ALTER INDEX " + indexName + " REBUILD";
/* 281 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/* 288 */     String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  WHERE IDX.INDEX_NAME=UPPER('" + indexName + "')";
/*     */ 
/* 293 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 297 */         TableIndex index = new TableIndex();
/* 298 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 299 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 300 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 301 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 302 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 303 */         index.setIndexStatus(rs.getString("STATUS"));
/* 304 */         index.setIndexDdl(rs.getString("DDL"));
/* 305 */         List indexFields = new ArrayList();
/* 306 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 307 */         index.setIndexFields(indexFields);
/* 308 */         return index;
/*     */       }
/*     */     });
/* 314 */     List indexList = mergeIndex(indexes);
/* 315 */     if (indexList.size() > 0) {
/* 316 */       return dedicatePKIndex((TableIndex)indexList.get(0));
/*     */     }
/* 318 */     return null;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 323 */     String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  WHERE IDX.TABLE_NAME=UPPER('" + tableName + "')";
/*     */ 
/* 328 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 331 */         TableIndex index = new TableIndex();
/* 332 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 333 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 334 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 335 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 336 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 337 */         index.setIndexStatus(rs.getString("STATUS"));
/* 338 */         index.setIndexDdl(rs.getString("DDL"));
/*     */ 
/* 341 */         List indexFields = new ArrayList();
/* 342 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 343 */         index.setIndexFields(indexFields);
/* 344 */         return index;
/*     */       }
/*     */     });
/* 350 */     List indexList = mergeIndex(indexes);
/*     */ 
/* 352 */     dedicateFKIndex(indexList);
/* 353 */     dedicatePKIndex(indexList);
/* 354 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 359 */     return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/*     */     String sql;
/* 366 */     if (getDDL.booleanValue()) {
/* 367 */       sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME WHERE 1=1";
/*     */     }
/*     */     else
/*     */     {
/* 373 */       sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME WHERE 1=1";
/*     */     }
/*     */ 
/* 378 */     if (!StringUtil.isEmpty(tableName)) {
/* 379 */       sql = sql + " AND UPPER(IDX.TABLE_NAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 382 */     if (!StringUtil.isEmpty(indexName)) {
/* 383 */       sql = sql + " AND UPPER(IDX.INDEX_NAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 386 */     if (pageBean != null) {
/* 387 */       int currentPage = pageBean.getCurrentPage();
/* 388 */       int pageSize = pageBean.getPageSize();
/* 389 */       int offset = (currentPage - 1) * pageSize;
/* 390 */       String totalSql = this.dialect.getCountSql(sql);
/* 391 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 392 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 393 */       pageBean.setTotalCount(total);
/*     */     }
/*     */ 
/* 396 */     this.logger.debug(sql);
/*     */ 
/* 398 */     List indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 402 */         TableIndex index = new TableIndex();
/* 403 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 404 */         index.setTableType(rs.getString("TABLE_TYPE"));
/* 405 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 406 */         index.setIndexType(rs.getString("INDEX_TYPE"));
/* 407 */         index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE"));
/* 408 */         index.setIndexStatus(rs.getString("STATUS"));
/*     */ 
/* 411 */         List indexFields = new ArrayList();
/* 412 */         indexFields.add(rs.getString("COLUMN_NAME"));
/* 413 */         index.setIndexFields(indexFields);
/* 414 */         return index;
/*     */       }
/*     */     });
/* 420 */     List indexList = mergeIndex(indexes);
/*     */ 
/* 424 */     dedicatePKIndex(indexList);
/* 425 */     return indexList;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> mergeIndex(List<TableIndex> indexes)
/*     */   {
/* 431 */     List<TableIndex> indexList = new ArrayList();
/* 432 */     for (TableIndex index : indexes) {
/* 433 */       boolean found = false;
/* 434 */       for (TableIndex index1 : indexList) {
/* 435 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 437 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 438 */           found = true;
/* 439 */           break;
/*     */         }
/*     */       }
/* 442 */       if (!found) {
/* 443 */         indexList.add(index);
/*     */       }
/*     */     }
/* 446 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<String> getPKColumns(String tableName) throws SQLException
/*     */   {
/* 451 */     String sql = "SELECT cols.column_name FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS WHERE UPPER(cols.table_name) = UPPER('" + tableName + "')" + " AND cons.constraint_type = 'P'  AND cols.position=1" + " AND cons.constraint_name = cols.constraint_name" + " AND cons.owner = cols.owner";
/*     */ 
/* 457 */     List columns = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 460 */         String column = rs.getString(1);
/* 461 */         return column;
/*     */       }
/*     */     });
/* 464 */     return columns;
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getPKColumns(List<String> tableNames)
/*     */     throws SQLException
/*     */   {
/* 471 */     StringBuffer sb = new StringBuffer();
/* 472 */     for (String name : tableNames) {
/* 473 */       sb.append("'");
/* 474 */       sb.append(name);
/* 475 */       sb.append("',");
/*     */     }
/* 477 */     sb.deleteCharAt(sb.length() - 1);
/*     */ 
/* 479 */     String sql = "SELECT cols.table_name,cols.column_name FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS WHERE UPPER(cols.table_name) in (" + sb.toString().toUpperCase() + ")" + " AND cons.constraint_type = 'P' AND COLS.POSITION=1" + " AND cons.constraint_name = cols.constraint_name" + " AND CONS.OWNER = COLS.OWNER";
/*     */ 
/* 486 */     Map columnsMap = new HashMap();
/*     */ 
/* 488 */     List<Map> maps = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 492 */         String table = rs.getString(1);
/* 493 */         String column = rs.getString(2);
/* 494 */         Map map = new HashMap();
/* 495 */         map.put("name", table);
/* 496 */         map.put("column", column);
/* 497 */         return map;
/*     */       }
/*     */     });
/* 501 */     for (Map map : maps) {
/* 502 */       if (columnsMap.containsKey(map.get("name"))) {
/* 503 */         ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
/*     */       } else {
/* 505 */         List cols = new ArrayList();
/* 506 */         cols.add(map.get("column"));
/* 507 */         columnsMap.put(map.get("name"), cols);
/*     */       }
/*     */     }
/*     */ 
/* 511 */     return columnsMap;
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect)
/*     */   {
/* 516 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getTablesPKColsByNames(List<String> tableNames)
/*     */   {
/* 526 */     Map tableMaps = new HashMap();
/* 527 */     List names = new ArrayList();
/* 528 */     for (int i = 1; i <= tableNames.size(); i++) {
/* 529 */       names.add(tableNames.get(i - 1));
/* 530 */       if ((i % this.BATCHSIZE == 0) || (i == tableNames.size())) {
/*     */         try
/*     */         {
/* 533 */           Map map = getPKColumns(names);
/* 534 */           tableMaps.putAll(map);
/* 535 */           names.clear();
/*     */         } catch (SQLException e) {
/* 537 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 541 */     return tableMaps;
/*     */   }
/*     */ 
/*     */   private boolean isListEqual(List<?> list1, List<?> list2)
/*     */   {
/* 551 */     if (list1 == null) {
/* 552 */       if (list2 == null) {
/* 553 */         return true;
/*     */       }
/* 555 */       return false;
/*     */     }
/* 557 */     if (list2 == null) {
/* 558 */       return false;
/*     */     }
/*     */ 
/* 562 */     if (list1.size() != list2.size()) {
/* 563 */       return false;
/*     */     }
/* 565 */     if (list1.containsAll(list2)) {
/* 566 */       return true;
/*     */     }
/* 568 */     return false;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> dedicatePKIndex(List<TableIndex> indexList)
/*     */   {
/* 578 */     List tableNames = new ArrayList();
/* 579 */     for (TableIndex index : indexList)
/*     */     {
/* 581 */       if (!tableNames.contains(index.getIndexTable())) {
/* 582 */         tableNames.add(index.getIndexTable());
/*     */       }
/*     */     }
/* 585 */     Map tablePKColsMaps = getTablesPKColsByNames(tableNames);
/* 586 */     for (TableIndex index : indexList) {
/* 587 */       if (isListEqual(index.getIndexFields(), (List)tablePKColsMaps.get(index.getIndexTable())))
/* 588 */         index.setPkIndex(true);
/*     */       else {
/* 590 */         index.setPkIndex(false);
/*     */       }
/*     */     }
/*     */ 
/* 594 */     return indexList;
/*     */   }
/*     */ 
/*     */   private TableIndex dedicatePKIndex(TableIndex index)
/*     */   {
/*     */     try
/*     */     {
/* 605 */       List pkCols = getPKColumns(index.getIndexName());
/* 606 */       if (isListEqual(index.getIndexFields(), pkCols))
/* 607 */         index.setPkIndex(true);
/*     */       else
/* 609 */         index.setPkIndex(false);
/*     */     }
/*     */     catch (SQLException e) {
/* 612 */       e.printStackTrace();
/*     */     }
/* 614 */     return index;
/*     */   }
/*     */ 
/*     */   private List<TableIndex> dedicateFKIndex(List<TableIndex> indexList)
/*     */   {
/* 623 */     List tableNames = new ArrayList();
/* 624 */     for (TableIndex index : indexList)
/*     */     {
/* 626 */       if (!tableNames.contains(index.getIndexTable())) {
/* 627 */         tableNames.add(index.getIndexTable());
/*     */       }
/*     */     }
/* 630 */     Map tableFKColsMaps = getTablesFKColsByNames(tableNames);
/* 631 */     for (TableIndex index : indexList) {
/* 632 */       if (isListEqual(index.getIndexFields(), (List)tableFKColsMaps.get(index.getIndexTable()))) {
/* 633 */         indexList.remove(index);
/*     */       }
/*     */     }
/* 636 */     return indexList;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getTablesFKColsByNames(List<String> tableNames)
/*     */   {
/* 641 */     Map tableMaps = new HashMap();
/* 642 */     List names = new ArrayList();
/* 643 */     for (int i = 1; i <= tableNames.size(); i++) {
/* 644 */       names.add(tableNames.get(i - 1));
/* 645 */       if ((i % this.BATCHSIZE == 0) || (i == tableNames.size())) {
/*     */         try
/*     */         {
/* 648 */           Map map = getFKColumns(names);
/* 649 */           tableMaps.putAll(map);
/* 650 */           names.clear();
/*     */         } catch (SQLException e) {
/* 652 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 656 */     return tableMaps;
/*     */   }
/*     */ 
/*     */   private Map<String, List<String>> getFKColumns(List<String> tableNames)
/*     */     throws SQLException
/*     */   {
/* 666 */     StringBuffer sb = new StringBuffer();
/* 667 */     for (String name : tableNames) {
/* 668 */       sb.append("'");
/* 669 */       sb.append(name);
/* 670 */       sb.append("',");
/*     */     }
/* 672 */     sb.deleteCharAt(sb.length() - 1);
/*     */ 
/* 674 */     String sql = "SELECT cols.table_name,cols.column_name FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS WHERE UPPER(cols.table_name) in (" + sb.toString().toUpperCase() + ")" + " AND cons.constraint_type = 'F' AND COLS.POSITION=1" + " AND cons.constraint_name = cols.constraint_name" + " AND CONS.OWNER = COLS.OWNER";
/*     */ 
/* 681 */     Map columnsMap = new HashMap();
/*     */ 
/* 683 */     List<Map> maps = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 686 */         String table = rs.getString(1);
/* 687 */         String column = rs.getString(2);
/* 688 */         Map map = new HashMap();
/* 689 */         map.put("name", table);
/* 690 */         map.put("column", column);
/* 691 */         return map;
/*     */       }
/*     */     });
/* 695 */     for (Map map : maps) {
/* 696 */       if (columnsMap.containsKey(map.get("name"))) {
/* 697 */         ((List)columnsMap.get(map.get("name"))).add(map.get("column"));
/*     */       } else {
/* 699 */         List cols = new ArrayList();
/* 700 */         cols.add(map.get("column"));
/* 701 */         columnsMap.put(map.get("name"), cols);
/*     */       }
/*     */     }
/*     */ 
/* 705 */     return columnsMap;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 712 */     StringBuffer sql = new StringBuffer();
/* 713 */     sql.append("select COUNT(1) from user_tables t where t.TABLE_NAME='").append(tableName.toUpperCase()).append("'");
/* 714 */     return this.jdbcTemplate.queryForInt(sql.toString()) > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.DmTableOperator
 * JD-Core Version:    0.6.2
 */