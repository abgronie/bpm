/*     */ package com.hotent.core.table.impl;
/*     */ 
/*     */ import com.hotent.core.model.TableIndex;
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.table.AbstractTableOperator;
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class Db2TableOperator extends AbstractTableOperator
/*     */ {
/*  26 */   protected int BATCHSIZE = 100;
/*     */ 
/*  29 */   private final String SQL_GET_ALL_INDEX = "SELECT A.TABNAME, A.INDNAME, B.COLNAME, A.UNIQUERULE, A.COLCOUNT, A.INDEXTYPE, A.REMARKS FROM SYSCAT.INDEXES A JOIN SYSCAT.INDEXCOLUSE B ON A.INDNAME=B.INDNAME WHERE 1=1 ";
/*     */ 
/* 419 */   private RowMapper<TableIndex> indexRowMapper = new RowMapper()
/*     */   {
/*     */     public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 422 */       TableIndex index = new TableIndex();
/* 423 */       index.setIndexTable(rs.getString("TABNAME"));
/* 424 */       index.setTableType(TableIndex.TABLE_TYPE_TABLE);
/* 425 */       index.setIndexName(rs.getString("INDNAME"));
/*     */ 
/* 427 */       String uniqueRule = rs.getString("UNIQUERULE").trim();
/* 428 */       if (("U".equalsIgnoreCase(uniqueRule)) || ("P".equalsIgnoreCase(uniqueRule))) {
/* 429 */         index.setUnique(true);
/*     */       }
/*     */ 
/* 432 */       if ("P".equalsIgnoreCase(uniqueRule)) {
/* 433 */         index.setPkIndex(true);
/*     */       }
/*     */ 
/* 436 */       String indexType = rs.getString("INDEXTYPE").trim();
/* 437 */       if ("CLUS".equalsIgnoreCase(indexType))
/* 438 */         index.setIndexType(TableIndex.INDEX_TYPE_CLUSTERED);
/* 439 */       else if ("REG".equalsIgnoreCase(indexType))
/* 440 */         index.setIndexType(TableIndex.INDEX_TYPE_REG);
/* 441 */       else if ("DIM".equalsIgnoreCase(indexType))
/* 442 */         index.setIndexType(TableIndex.INDEX_TYPE_DIM);
/* 443 */       else if ("BLOK".equalsIgnoreCase(indexType)) {
/* 444 */         index.setIndexType(TableIndex.INDEX_TYPE_BLOK);
/*     */       }
/* 446 */       index.setIndexComment(rs.getString("REMARKS"));
/* 447 */       List indexFields = new ArrayList();
/* 448 */       indexFields.add(rs.getString("COLNAME"));
/* 449 */       index.setIndexFields(indexFields);
/*     */ 
/* 451 */       index.setIndexDdl(Db2TableOperator.this.generateIndexDDL(index));
/* 452 */       return index;
/*     */     }
/* 419 */   };
/*     */ 
/*     */   public void createTable(TableModel model)
/*     */     throws SQLException
/*     */   {
/*  47 */     List columnList = model.getColumnList();
/*     */ 
/*  49 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  51 */     String pkColumn = null;
/*     */ 
/*  53 */     List<String> columnCommentList = new ArrayList();
/*     */ 
/*  55 */     sb.append("CREATE TABLE " + model.getName() + " (\n");
/*  56 */     for (int i = 0; i < columnList.size(); i++)
/*     */     {
/*  58 */       ColumnModel cm = (ColumnModel)columnList.get(i);
/*  59 */       sb.append("    ").append(cm.getName()).append("    ");
/*  60 */       sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
/*  61 */       sb.append(" ");
/*     */ 
/*  63 */       if (cm.getIsPk()) {
/*  64 */         if (pkColumn == null)
/*  65 */           pkColumn = cm.getName();
/*     */         else {
/*  67 */           pkColumn = pkColumn + "," + cm.getName();
/*     */         }
/*     */       }
/*     */ 
/*  71 */       String defVal = getDefaultValueSQL(cm);
/*  72 */       if (StringUtil.isNotEmpty(defVal)) {
/*  73 */         sb.append(defVal);
/*     */       }
/*     */ 
/*  77 */       if ((!cm.getIsNull()) || (cm.getIsPk())) {
/*  78 */         sb.append(" NOT NULL ");
/*     */       }
/*     */ 
/*  82 */       if ((cm.getComment() != null) && (cm.getComment().length() > 0))
/*     */       {
/*  84 */         columnCommentList.add("COMMENT ON COLUMN " + model.getName() + "." + cm.getName() + " IS '" + cm.getComment() + "'\n");
/*     */       }
/*  86 */       sb.append(",\n");
/*     */     }
/*     */ 
/*  89 */     if (pkColumn != null)
/*  90 */       sb.append("    CONSTRAINT PK_").append(model.getName()).append(" PRIMARY KEY (").append(pkColumn).append(")");
/*     */     else {
/*  92 */       sb = new StringBuffer(sb.substring(0, sb.length() - ",\n".length()));
/*     */     }
/*     */ 
/*  96 */     sb.append("\n)");
/*     */ 
/*  98 */     this.jdbcTemplate.execute(sb.toString());
/*  99 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/* 100 */       this.jdbcTemplate.execute("COMMENT ON TABLE " + model.getName() + " IS '" + model.getComment() + "'\n");
/*     */     }
/* 102 */     for (String columnComment : columnCommentList)
/* 103 */       this.jdbcTemplate.execute(columnComment);
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/* 109 */     StringBuffer sb = new StringBuffer();
/* 110 */     sb.append("COMMENT ON TABLE ");
/* 111 */     sb.append(tableName);
/* 112 */     sb.append(" IS '");
/* 113 */     sb.append(comment);
/* 114 */     sb.append("'\n");
/* 115 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model) throws SQLException
/*     */   {
/* 120 */     StringBuffer sb = new StringBuffer();
/* 121 */     sb.append("ALTER TABLE ").append(tableName);
/* 122 */     sb.append(" ADD ");
/* 123 */     sb.append(model.getName()).append(" ");
/* 124 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 128 */     String defVal = getDefaultValueSQL(model);
/* 129 */     if (StringUtil.isNotEmpty(defVal)) {
/* 130 */       sb.append(defVal);
/*     */     }
/*     */ 
/* 136 */     sb.append("\n");
/* 137 */     this.jdbcTemplate.execute(sb.toString());
/* 138 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 139 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS '" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 151 */     if (!columnName.equalsIgnoreCase(model.getName()))
/*     */     {
/* 153 */       StringBuffer addColumn = new StringBuffer();
/* 154 */       addColumn.append("alter table ");
/* 155 */       addColumn.append(tableName);
/* 156 */       addColumn.append(" add column ");
/* 157 */       addColumn.append("    ").append(model.getName()).append("    ");
/* 158 */       addColumn.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/* 159 */       addColumn.append(" ");
/*     */ 
/* 161 */       String defVal = getDefaultValueSQL(model);
/* 162 */       if (StringUtil.isNotEmpty(defVal)) {
/* 163 */         addColumn.append(defVal);
/*     */       }
/* 165 */       this.jdbcTemplate.execute(addColumn.toString());
/*     */ 
/* 167 */       String copyValue = "update table " + tableName + " set " + model.getName() + "=" + columnName;
/* 168 */       this.jdbcTemplate.execute(copyValue);
/*     */ 
/* 170 */       String dropColumn = "alter table " + tableName + " drop column " + columnName;
/* 171 */       this.jdbcTemplate.execute(dropColumn);
/*     */     }
/*     */ 
/* 176 */     StringBuffer sb = new StringBuffer();
/*     */ 
/* 178 */     sb.append("ALTER TABLE ").append(tableName);
/* 179 */     sb.append("  ALTER " + model.getName()).append(" ");
/* 180 */     sb.append(" SET\tDATA TYPE ");
/* 181 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 183 */     this.jdbcTemplate.execute(sb.toString());
/*     */ 
/* 186 */     if (!model.getIsNull()) {
/* 187 */       String nullable = "ALTER TABLE " + tableName + " ALTER " + model.getName() + " DROP NOT NULL";
/* 188 */       this.jdbcTemplate.execute(nullable);
/*     */     } else {
/* 190 */       String notnull = "ALTER TABLE " + tableName + " ALTER " + model.getName() + " SET NOT NULL";
/* 191 */       this.jdbcTemplate.execute(notnull);
/*     */     }
/*     */ 
/* 195 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 196 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS'" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/* 202 */     String selSql = "SELECT COUNT(*) AMOUNT FROM SYSCAT.TABLES  WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND TABNAME = UPPER('" + tableName + "')";
/*     */ 
/* 208 */     int rtn = this.jdbcTemplate.queryForInt(selSql);
/* 209 */     if (rtn > 0) {
/* 210 */       String sql = "drop table " + tableName;
/* 211 */       this.jdbcTemplate.execute(sql);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/* 217 */     String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT FK_" + fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
/* 218 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 223 */     String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  " + keyName;
/* 224 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName)
/*     */   {
/* 230 */     String sql = "DROP INDEX " + indexName;
/* 231 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/* 236 */     String sql = "SELECT A.TABNAME, A.INDNAME, B.COLNAME, A.UNIQUERULE, A.COLCOUNT, A.INDEXTYPE, A.REMARKS FROM SYSCAT.INDEXES A JOIN SYSCAT.INDEXCOLUSE B ON A.INDNAME=B.INDNAME WHERE 1=1 ";
/* 237 */     sql = sql + " AND A.INDNAME = '" + indexName + "' ";
/* 238 */     List indexes = this.jdbcTemplate.query(sql, this.indexRowMapper);
/* 239 */     List indexList = mergeIndex(indexes);
/* 240 */     if (BeanUtils.isEmpty(indexList)) {
/* 241 */       return null;
/*     */     }
/* 243 */     TableIndex index = (TableIndex)indexList.get(0);
/* 244 */     index.setIndexDdl(generateIndexDDL(index));
/* 245 */     return index;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 251 */     String sql = "SELECT A.TABNAME, A.INDNAME, B.COLNAME, A.UNIQUERULE, A.COLCOUNT, A.INDEXTYPE, A.REMARKS FROM SYSCAT.INDEXES A JOIN SYSCAT.INDEXCOLUSE B ON A.INDNAME=B.INDNAME WHERE 1=1 ";
/* 252 */     sql = sql + " AND UPPER(A.TABNAME) = UPPER('" + tableName + "')";
/* 253 */     List indexes = this.jdbcTemplate.query(sql, this.indexRowMapper);
/*     */ 
/* 255 */     List<TableIndex> indexList = mergeIndex(indexes);
/* 256 */     for (TableIndex index : indexList) {
/* 257 */       index.setIndexDdl(generateIndexDDL(index));
/*     */     }
/* 259 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 264 */     return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/* 269 */     String sql = "SELECT A.TABNAME, A.INDNAME, B.COLNAME, A.UNIQUERULE, A.COLCOUNT, A.INDEXTYPE, A.REMARKS FROM SYSCAT.INDEXES A JOIN SYSCAT.INDEXCOLUSE B ON A.INDNAME=B.INDNAME WHERE 1=1 ";
/*     */ 
/* 271 */     if (!StringUtil.isEmpty(tableName)) {
/* 272 */       sql = sql + " AND UPPER(A.TABNAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 275 */     if (!StringUtil.isEmpty(indexName)) {
/* 276 */       sql = sql + " AND UPPER(A.INDNAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 279 */     if (pageBean != null) {
/* 280 */       int currentPage = pageBean.getCurrentPage();
/* 281 */       int pageSize = pageBean.getPageSize();
/* 282 */       int offset = (currentPage - 1) * pageSize;
/* 283 */       String totalSql = this.dialect.getCountSql(sql);
/* 284 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 285 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 286 */       pageBean.setTotalCount(total);
/*     */     }
/* 288 */     this.logger.debug(sql);
/* 289 */     List indexes = this.jdbcTemplate.query(sql, this.indexRowMapper);
/*     */ 
/* 291 */     List<TableIndex> indexList = mergeIndex(indexes);
/*     */ 
/* 293 */     for (TableIndex index : indexList) {
/* 294 */       index.setIndexDdl(generateIndexDDL(index));
/*     */     }
/* 296 */     return indexList;
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName)
/*     */   {
/* 307 */     throw new UnsupportedOperationException("DB2 不支持通过JDBC进行索引重建！");
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index) throws SQLException
/*     */   {
/* 312 */     String sql = generateIndexDDL(index);
/* 313 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   private List<TableIndex> mergeIndex(List<TableIndex> indexes) {
/* 317 */     List<TableIndex> indexList = new ArrayList();
/* 318 */     for (TableIndex index : indexes) {
/* 319 */       boolean found = false;
/* 320 */       for (TableIndex index1 : indexList) {
/* 321 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 323 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 324 */           found = true;
/* 325 */           break;
/*     */         }
/*     */       }
/* 328 */       if (!found) {
/* 329 */         indexList.add(index);
/*     */       }
/*     */     }
/* 332 */     return indexList;
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 341 */     StringBuffer sql = new StringBuffer();
/* 342 */     sql.append("CREATE ");
/* 343 */     sql.append("INDEX ");
/* 344 */     sql.append(index.getIndexName());
/* 345 */     sql.append(" ON ");
/* 346 */     sql.append(index.getIndexTable());
/* 347 */     sql.append("(");
/* 348 */     for (String field : index.getIndexFields()) {
/* 349 */       sql.append(field);
/* 350 */       sql.append(",");
/*     */     }
/* 352 */     sql.deleteCharAt(sql.length() - 1);
/* 353 */     sql.append(")");
/*     */ 
/* 355 */     if ((!StringUtil.isEmpty(index.getIndexType())) && 
/* 356 */       (TableIndex.INDEX_TYPE_CLUSTERED.equalsIgnoreCase(index.getIndexType()))) {
/* 357 */       sql.append(" CLUSTER ");
/*     */     }
/*     */ 
/* 361 */     return sql.toString();
/*     */   }
/*     */ 
/*     */   private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
/*     */   {
/* 378 */     if ("varchar".equals(columnType))
/* 379 */       return "VARCHAR(" + charLen + ')';
/* 380 */     if ("number".equals(columnType))
/* 381 */       return "DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")";
/* 382 */     if ("date".equals(columnType))
/* 383 */       return "DATE";
/* 384 */     if ("int".equals(columnType)) {
/* 385 */       if ((intLen > 0) && (intLen <= 5))
/* 386 */         return "SMALLINT";
/* 387 */       if ((intLen > 5) && (intLen <= 10)) {
/* 388 */         return "INTEGER";
/*     */       }
/* 390 */       return "BIGINT";
/*     */     }
/* 392 */     if ("clob".equals(columnType)) {
/* 393 */       return "CLOB";
/*     */     }
/* 395 */     return "VARCHAR(50)";
/*     */   }
/*     */ 
/*     */   private String getDefaultValueSQL(ColumnModel cm)
/*     */   {
/* 400 */     String sql = null;
/* 401 */     if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
/* 402 */       if ("int".equalsIgnoreCase(cm.getColumnType()))
/* 403 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/* 404 */       else if ("number".equalsIgnoreCase(cm.getColumnType()))
/* 405 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/* 406 */       else if ("varchar".equalsIgnoreCase(cm.getColumnType()))
/* 407 */         sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
/* 408 */       else if ("clob".equalsIgnoreCase(cm.getColumnType()))
/* 409 */         sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
/* 410 */       else if ("date".equalsIgnoreCase(cm.getColumnType()))
/* 411 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/*     */       else {
/* 413 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/*     */       }
/*     */     }
/* 416 */     return sql;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 461 */     String selSql = "SELECT COUNT(*) AMOUNT FROM SYSCAT.TABLES  WHERE TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND TABNAME = UPPER('" + tableName + "')";
/*     */ 
/* 467 */     int rtn = this.jdbcTemplate.queryForInt(selSql);
/* 468 */     return rtn > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.Db2TableOperator
 * JD-Core Version:    0.6.2
 */