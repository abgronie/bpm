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
/*     */ import java.util.List;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class SqlserverTableOperator extends AbstractTableOperator
/*     */ {
/*     */   public void createTable(TableModel model)
/*     */     throws SQLException
/*     */   {
/*  26 */     List columnList = model.getColumnList();
/*     */ 
/*  29 */     StringBuffer createTableSql = new StringBuffer();
/*     */ 
/*  31 */     String pkColumn = null;
/*     */ 
/*  35 */     List<String> columnCommentList = new ArrayList<String>();
/*     */ 
/*  37 */     createTableSql.append("CREATE TABLE " + model.getName() + " (\n");
/*  38 */     for (int i = 0; i < columnList.size(); i++)
/*     */     {
/*  40 */       ColumnModel cm = (ColumnModel)columnList.get(i);
/*  41 */       createTableSql.append("    ").append(cm.getName()).append("    ");
/*  42 */       createTableSql.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
/*     */ 
/*  44 */       createTableSql.append(" ");
/*     */ 
/*  47 */       if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
/*  48 */         createTableSql.append(" DEFAULT " + cm.getDefaultValue());
/*     */       }
/*     */ 
/*  52 */       if (!cm.getIsNull()) {
/*  53 */         createTableSql.append(" NOT NULL ");
/*     */       }
/*     */ 
/*  56 */       if (cm.getIsPk()) {
/*  57 */         if (pkColumn == null)
/*  58 */           pkColumn = cm.getName();
/*     */         else {
/*  60 */           pkColumn = pkColumn + "," + cm.getName();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  69 */       if ((cm.getComment() != null) && (cm.getComment().length() > 0)) {
/*  70 */         StringBuffer comment = new StringBuffer("EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'");
/*     */ 
/*  72 */         comment.append(cm.getComment()).append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'").append(model.getName()).append("', @level2type=N'COLUMN', @level2name=N'").append(cm.getName()).append("'");
/*     */ 
/*  77 */         columnCommentList.add(comment.toString());
/*     */       }
/*  79 */       createTableSql.append(",\n");
/*     */     }
/*     */ 
/*  82 */     if (pkColumn != null) {
/*  83 */       createTableSql.append("    CONSTRAINT PK_").append(model.getName()).append(" PRIMARY KEY (").append(pkColumn).append(")");
/*     */     }
/*     */ 
/*  92 */     createTableSql.append("\n)");
/*  93 */     this.jdbcTemplate.execute(createTableSql.toString());
/*     */ 
/*  96 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/*  97 */       StringBuffer tableComment = new StringBuffer("EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'");
/*     */ 
/*  99 */       tableComment.append(model.getComment()).append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'").append(model.getName()).append("'");
/*     */ 
/* 103 */       this.jdbcTemplate.execute(tableComment.toString());
/*     */     }
/* 105 */     for (String columnComment : columnCommentList)
/* 106 */       this.jdbcTemplate.execute(columnComment);
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/* 115 */     StringBuffer commentSql = new StringBuffer("EXEC sys.sp_updateextendedproperty @name=N'MS_Description', @value=N'");
/*     */ 
/* 117 */     commentSql.append(comment).append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'").append(tableName).append("'");
/*     */ 
/* 122 */     this.jdbcTemplate.execute(commentSql.toString());
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 129 */     StringBuffer alterSql = new StringBuffer();
/* 130 */     alterSql.append("ALTER TABLE ").append(tableName);
/* 131 */     alterSql.append(" ADD ");
/* 132 */     alterSql.append(model.getName()).append(" ");
/* 133 */     alterSql.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 136 */     if (StringUtil.isNotEmpty(model.getDefaultValue())) {
/* 137 */       alterSql.append(" DEFAULT " + model.getDefaultValue());
/*     */     }
/*     */ 
/* 143 */     alterSql.append("\n");
/* 144 */     this.jdbcTemplate.execute(alterSql.toString());
/* 145 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/* 146 */       StringBuffer comment = new StringBuffer("EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'");
/*     */ 
/* 148 */       comment.append(model.getComment()).append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'").append(tableName).append("', @level2type=N'COLUMN', @level2name=N'").append(model.getName()).append("'");
/*     */ 
/* 153 */       this.jdbcTemplate.execute(comment.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 162 */     if (!columnName.equals(model.getName())) {
/* 163 */       StringBuffer modifyName = new StringBuffer("EXEC sp_rename '");
/* 164 */       modifyName.append(tableName).append(".[").append(columnName).append("]','").append(model.getName()).append("', 'COLUMN'");
/*     */ 
/* 167 */       this.jdbcTemplate.execute(modifyName.toString());
/*     */     }
/*     */ 
/* 171 */     StringBuffer alterSql = new StringBuffer();
/* 172 */     alterSql.append("ALTER TABLE ").append(tableName);
/* 173 */     alterSql.append(" ALTER COLUMN " + model.getName()).append(" ");
/* 174 */     alterSql.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 176 */     if (!model.getIsNull()) {
/* 177 */       alterSql.append(" NOT NULL ");
/*     */     }
/*     */ 
/* 180 */     this.jdbcTemplate.execute(alterSql.toString());
/*     */ 
/* 183 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/*     */     {
/* 185 */       StringBuffer comment = new StringBuffer("EXEC sys.sp_updateextendedproperty @name=N'MS_Description', @value=N'");
/*     */ 
/* 187 */       comment.append(model.getComment()).append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'").append(tableName).append("', @level2type=N'COLUMN', @level2name=N'").append(model.getName()).append("'");
/*     */ 
/* 192 */       this.jdbcTemplate.execute(comment.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
/*     */   {
/* 198 */     if ("varchar".equals(columnType))
/* 199 */       return "VARCHAR(" + charLen + ')';
/* 200 */     if ("number".equals(columnType))
/* 201 */       return "NUMERIC(" + (intLen + decimalLen) + "," + decimalLen + ")";
/* 202 */     if ("date".equals(columnType))
/* 203 */       return "DATETIME";
/* 204 */     if ("int".equals(columnType))
/* 205 */       return "NUMERIC(" + intLen + ")";
/* 206 */     if ("clob".equals(columnType)) {
/* 207 */       return "TEXT";
/*     */     }
/* 209 */     return "";
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/* 215 */     String sql = "IF OBJECT_ID(N'" + tableName + "', N'U') IS NOT NULL  DROP TABLE " + tableName;
/*     */ 
/* 217 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/* 225 */     String sql = "  ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_" + fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ")   ON DELETE CASCADE";
/*     */ 
/* 228 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 233 */     String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  " + keyName;
/*     */ 
/* 235 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public String getDbType()
/*     */   {
/* 241 */     return this.dbType;
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index) throws SQLException
/*     */   {
/* 246 */     String sql = generateIndexDDL(index);
/* 247 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName)
/*     */   {
/* 252 */     String sql = "DROP INDEX " + indexName + " ON " + tableName;
/* 253 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/* 258 */     String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, COL.NAME AS COLUMN_NAME FROM  SYS.INDEXES  IDX  JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID WHERE OBJ.NAME ='" + tableName + "' " + "AND IDX.NAME ='" + indexName + "'";
/*     */ 
/* 269 */     List<TableIndex> indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum)
/*     */         throws SQLException
/*     */       {
/* 275 */         TableIndex index = new TableIndex();
/* 276 */         List columns = new ArrayList();
/* 277 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 278 */         index.setIndexType(SqlserverTableOperator.this.mapIndexType(rs.getInt("INDEX_TYPE")));
/* 279 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 280 */         index.setTableType(SqlserverTableOperator.this.mapTableType(rs.getString("TABLE_TYPE")));
/* 281 */         index.setUnique(SqlserverTableOperator.this.mapIndexUnique(rs.getInt("IS_UNIQUE")));
/* 282 */         index.setPkIndex(SqlserverTableOperator.this.mapPKIndex(rs.getInt("IS_PK_INDEX")));
/* 283 */         columns.add(rs.getString("COLUMN_NAME"));
/* 284 */         index.setIndexStatus(SqlserverTableOperator.this.mapIndexStatus(rs.getInt("IS_DISABLED")));
/* 285 */         index.setIndexFields(columns);
/* 286 */         return index;
/*     */       }
/*     */     });
/* 291 */     List<TableIndex> indexList = new ArrayList<TableIndex>();
/* 292 */     for (TableIndex index : indexes) {
/* 293 */       for (TableIndex index1 : indexList) {
/* 294 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 296 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/*     */         }
/*     */       }
/*     */ 
/* 300 */       indexList.add(index);
/*     */     }
/*     */ 
/* 303 */     for (TableIndex index : indexList) {
/* 304 */       index.setIndexDdl(generateIndexDDL(index));
/*     */     }
/*     */ 
/* 307 */     if (indexList.size() > 0) {
/* 308 */       return (TableIndex)indexList.get(0);
/*     */     }
/* 310 */     return null;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 316 */     String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, COL.NAME AS COLUMN_NAME FROM  SYS.INDEXES  IDX  JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID WHERE OBJ.NAME ='" + tableName + "'";
/*     */ 
/* 326 */     List<TableIndex> indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum)
/*     */         throws SQLException
/*     */       {
/* 332 */         TableIndex index = new TableIndex();
/* 333 */         List columns = new ArrayList();
/* 334 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 335 */         index.setIndexType(SqlserverTableOperator.this.mapIndexType(rs.getInt("INDEX_TYPE")));
/* 336 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 337 */         index.setTableType(SqlserverTableOperator.this.mapTableType(rs.getString("TABLE_TYPE")));
/* 338 */         index.setUnique(SqlserverTableOperator.this.mapIndexUnique(rs.getInt("IS_UNIQUE")));
/* 339 */         index.setPkIndex(SqlserverTableOperator.this.mapPKIndex(rs.getInt("IS_PK_INDEX")));
/* 340 */         columns.add(rs.getString("COLUMN_NAME"));
/* 341 */         index.setIndexStatus(SqlserverTableOperator.this.mapIndexStatus(rs.getInt("IS_DISABLED")));
/* 342 */         index.setIndexFields(columns);
/* 343 */         return index;
/*     */       }
/*     */     });
/* 348 */     List<TableIndex> indexList = new ArrayList<TableIndex>();
/* 349 */     for (TableIndex index : indexes) {
/* 350 */       boolean found = false;
/* 351 */       for (TableIndex index1 : indexList) {
/* 352 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 354 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 355 */           found = true;
/* 356 */           break;
/*     */         }
/*     */       }
/* 359 */       if (!found) {
/* 360 */         indexList.add(index);
/*     */       }
/*     */     }
/*     */ 
/* 364 */     for (TableIndex index : indexList) {
/* 365 */       index.setIndexDdl(generateIndexDDL(index));
/*     */     }
/*     */ 
/* 368 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 374 */     return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/* 380 */     String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, COL.NAME AS COLUMN_NAME FROM  SYS.INDEXES  IDX  JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID WHERE 1=1";
/*     */ 
/* 390 */     if (!StringUtil.isEmpty(indexName)) {
/* 391 */       sql = sql + " AND IDX.NAME LIKE '%" + indexName + "%'";
/*     */     }
/* 393 */     if (!StringUtil.isEmpty(tableName)) {
/* 394 */       sql = sql + " AND OBJ.NAME LIKE '%" + tableName + "%' ";
/*     */     }
/*     */ 
/* 397 */     if (pageBean != null) {
/* 398 */       int currentPage = pageBean.getCurrentPage();
/* 399 */       int pageSize = pageBean.getPageSize();
/* 400 */       int offset = (currentPage - 1) * pageSize;
/* 401 */       String totalSql = this.dialect.getCountSql(sql);
/* 402 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 403 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 404 */       pageBean.setTotalCount(total);
/*     */     }
/*     */ 
/* 407 */     List<TableIndex> indexes = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public TableIndex mapRow(ResultSet rs, int rowNum)
/*     */         throws SQLException
/*     */       {
/* 412 */         TableIndex index = new TableIndex();
/* 413 */         List columns = new ArrayList();
/* 414 */         index.setIndexName(rs.getString("INDEX_NAME"));
/* 415 */         index.setIndexType(SqlserverTableOperator.this.mapIndexType(rs.getInt("INDEX_TYPE")));
/* 416 */         index.setIndexTable(rs.getString("TABLE_NAME"));
/* 417 */         index.setTableType(SqlserverTableOperator.this.mapTableType(rs.getString("TABLE_TYPE")));
/*     */ 
/* 419 */         index.setUnique(SqlserverTableOperator.this.mapIndexUnique(rs.getInt("IS_UNIQUE")));
/* 420 */         index.setPkIndex(SqlserverTableOperator.this.mapPKIndex(rs.getInt("IS_PK_INDEX")));
/* 421 */         columns.add(rs.getString("COLUMN_NAME"));
/* 422 */         index.setIndexFields(columns);
/* 423 */         index.setIndexStatus(SqlserverTableOperator.this.mapIndexStatus(rs.getInt("IS_DISABLED")));
/* 424 */         return index;
/*     */       }
/*     */     });
/* 429 */     List<TableIndex> indexList = new ArrayList<TableIndex>();
/* 430 */     for (TableIndex index : indexes) {
/* 431 */       for (TableIndex index1 : indexList) {
/* 432 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 434 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/*     */         }
/*     */       }
/*     */ 
/* 438 */       indexList.add(index);
/*     */     }
/* 440 */     if (getDDL.booleanValue()) {
/* 441 */       for (TableIndex index : indexList) {
/* 442 */         index.setIndexDdl(generateIndexDDL(index));
/*     */       }
/*     */     }
/* 445 */     return indexList;
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName)
/*     */   {
/* 450 */     String sql = "DBCC DBREINDEX ('" + tableName + "','" + indexName + "',80)";
/* 451 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public List<String> getPKColumns(String tableName)
/*     */     throws SQLException
/*     */   {
/* 457 */     String sql = "SELECT C.COLUMN_NAME COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS PK ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE C WHERE \tPK.TABLE_NAME = '%S' AND\tCONSTRAINT_TYPE = 'PRIMARY KEY' AND\tC.TABLE_NAME = PK.TABLE_NAME AND\tC.CONSTRAINT_NAME = PK.CONSTRAINT_NAME ";
/*     */ 
/* 462 */     sql = String.format(sql, new Object[] { tableName });
/*     */ 
/* 464 */     List columns = this.jdbcTemplate.query(sql, new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet rs, int rowNum) throws SQLException
/*     */       {
/* 468 */         String column = rs.getString(1);
/* 469 */         return column;
/*     */       }
/*     */     });
/* 472 */     return columns;
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 482 */     StringBuffer sql = new StringBuffer();
/* 483 */     sql.append("CREATE ");
/*     */ 
/* 485 */     if (index.isUnique()) {
/* 486 */       sql.append(" UNIQUE ");
/*     */     }
/*     */ 
/* 489 */     if ((!StringUtil.isEmpty(index.getIndexType())) && 
/* 490 */       (index.getIndexType().equalsIgnoreCase("CLUSTERED"))) {
/* 491 */       sql.append(" CLUSTERED ");
/*     */     }
/*     */ 
/* 494 */     sql.append(" INDEX ");
/*     */ 
/* 496 */     sql.append(index.getIndexName());
/*     */ 
/* 498 */     sql.append(" ON ");
/* 499 */     sql.append(index.getIndexTable());
/*     */ 
/* 501 */     sql.append(" (");
/* 502 */     for (String field : index.getIndexFields()) {
/* 503 */       sql.append(field);
/* 504 */       sql.append(",");
/*     */     }
/* 506 */     sql.deleteCharAt(sql.length() - 1);
/* 507 */     sql.append(")");
/*     */ 
/* 509 */     return sql.toString();
/*     */   }
/*     */ 
/*     */   private String mapTableType(String type) {
/* 513 */     type = type.trim();
/* 514 */     String tableType = null;
/* 515 */     if (type.equalsIgnoreCase("U"))
/* 516 */       tableType = TableIndex.TABLE_TYPE_TABLE;
/* 517 */     else if (type.equalsIgnoreCase("V")) {
/* 518 */       tableType = TableIndex.TABLE_TYPE_VIEW;
/*     */     }
/* 520 */     return tableType;
/*     */   }
/*     */ 
/*     */   private String mapIndexType(int type)
/*     */   {
/* 531 */     String indexType = null;
/* 532 */     switch (type) {
/*     */     case 0:
/* 534 */       indexType = TableIndex.INDEX_TYPE_HEAP;
/* 535 */       break;
/*     */     case 1:
/* 537 */       indexType = TableIndex.INDEX_TYPE_CLUSTERED;
/* 538 */       break;
/*     */     case 2:
/* 540 */       indexType = TableIndex.INDEX_TYPE_NONCLUSTERED;
/* 541 */       break;
/*     */     case 3:
/* 543 */       indexType = TableIndex.INDEX_TYPE_XML;
/* 544 */       break;
/*     */     case 4:
/* 546 */       indexType = TableIndex.INDEX_TYPE_SPATIAL;
/* 547 */       break;
/*     */     }
/*     */ 
/* 551 */     return indexType;
/*     */   }
/*     */ 
/*     */   private boolean mapIndexUnique(int type) {
/* 555 */     boolean indexUnique = false;
/* 556 */     switch (type) {
/*     */     case 0:
/* 558 */       indexUnique = false;
/* 559 */       break;
/*     */     case 1:
/* 561 */       indexUnique = true;
/* 562 */       break;
/*     */     }
/*     */ 
/* 566 */     return indexUnique;
/*     */   }
/*     */ 
/*     */   private boolean mapPKIndex(int type) {
/* 570 */     boolean pkIndex = false;
/* 571 */     switch (type) {
/*     */     case 0:
/* 573 */       pkIndex = false;
/* 574 */       break;
/*     */     case 1:
/* 576 */       pkIndex = true;
/* 577 */       break;
/*     */     }
/*     */ 
/* 581 */     return pkIndex;
/*     */   }
/*     */ 
/*     */   private String mapIndexStatus(int type) {
/* 585 */     String tableType = null;
/* 586 */     switch (type) {
/*     */     case 0:
/* 588 */       tableType = TableIndex.INDEX_STATUS_VALIDATE;
/* 589 */       break;
/*     */     case 1:
/* 591 */       tableType = TableIndex.INDEX_STATUS_INVALIDATE;
/*     */     }
/*     */ 
/* 594 */     return tableType;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 602 */     String sql = "select count(1) from sysobjects where name='" + tableName.toUpperCase() + "'";
/* 603 */     return this.jdbcTemplate.queryForInt(sql) > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.SqlserverTableOperator
 * JD-Core Version:    0.6.2
 */