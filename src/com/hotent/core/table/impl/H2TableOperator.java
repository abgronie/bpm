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
/*     */ public class H2TableOperator extends AbstractTableOperator
/*     */ {
/*  21 */   protected int BATCHSIZE = 100;
/*     */ 
/*  24 */   private final String SQL_GET_ALL_INDEX = "SELECT A.TABLE_NAME  , A.INDEX_NAME  , A.NON_UNIQUE  , A.COLUMN_NAME  , A.INDEX_TYPE_NAME  , A.REMARKS , A.SQL FROM INFORMATION_SCHEMA.INDEXES  A WHERE 1=1 ";
/*     */ 
/* 369 */   private RowMapper<TableIndex> indexRowMapper = new RowMapper()
/*     */   {
/*     */     public TableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 372 */       TableIndex index = new TableIndex();
/* 373 */       index.setIndexTable(rs.getString("TABLE_NAME"));
/* 374 */       index.setTableType(TableIndex.TABLE_TYPE_TABLE);
/* 375 */       index.setIndexName(rs.getString("INDEX_NAME"));
/*     */ 
/* 377 */       String non_unique = rs.getString("NON_UNIQUE").trim();
/* 378 */       String index_type_name = rs.getString("INDEX_TYPE_NAME").trim();
/* 379 */       if ("TRUE".equalsIgnoreCase(non_unique)) {
/* 380 */         index.setUnique(true);
/*     */       }
/*     */ 
/* 383 */       if ("PRIMARY KEY".equalsIgnoreCase(index_type_name)) {
/* 384 */         index.setPkIndex(true);
/*     */       }
/*     */ 
/* 387 */       index.setIndexType(TableIndex.INDEX_TYPE_BTREE);
/*     */ 
/* 389 */       index.setIndexComment(rs.getString("REMARKS"));
/* 390 */       List indexFields = new ArrayList();
/* 391 */       indexFields.add(rs.getString("COLUMN_NAME"));
/* 392 */       index.setIndexFields(indexFields);
/*     */ 
/* 394 */       index.setIndexDdl(rs.getString("SQL"));
/* 395 */       return index;
/*     */     }
/* 369 */   };
/*     */ 
/*     */   public void createTable(TableModel model)
/*     */     throws SQLException
/*     */   {
/*  41 */     List columnList = model.getColumnList();
/*     */ 
/*  43 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  45 */     String pkColumn = null;
/*     */ 
/*  47 */     List<String> columnCommentList = new ArrayList<String>();
/*     */ 
/*  49 */     sb.append("CREATE TABLE " + model.getName() + " (\n");
/*  50 */     for (int i = 0; i < columnList.size(); i++)
/*     */     {
/*  52 */       ColumnModel cm = (ColumnModel)columnList.get(i);
/*  53 */       sb.append("    ").append(cm.getName()).append("    ");
/*  54 */       sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
/*  55 */       sb.append(" ");
/*     */ 
/*  57 */       if (cm.getIsPk()) {
/*  58 */         if (pkColumn == null)
/*  59 */           pkColumn = cm.getName();
/*     */         else {
/*  61 */           pkColumn = pkColumn + "," + cm.getName();
/*     */         }
/*     */       }
/*     */ 
/*  65 */       String defVal = getDefaultValueSQL(cm);
/*  66 */       if (StringUtil.isNotEmpty(defVal)) {
/*  67 */         sb.append(defVal);
/*     */       }
/*     */ 
/*  71 */       if ((!cm.getIsNull()) || (cm.getIsPk())) {
/*  72 */         sb.append(" NOT NULL ");
/*     */       }
/*     */ 
/*  76 */       if ((cm.getComment() != null) && (cm.getComment().length() > 0))
/*     */       {
/*  78 */         columnCommentList.add("COMMENT ON COLUMN " + model.getName() + "." + cm.getName() + " IS '" + cm.getComment() + "'\n");
/*     */       }
/*  80 */       sb.append(",\n");
/*     */     }
/*     */ 
/*  83 */     if (pkColumn != null)
/*  84 */       sb.append("    CONSTRAINT PK_").append(model.getName()).append(" PRIMARY KEY (").append(pkColumn).append(")");
/*     */     else {
/*  86 */       sb = new StringBuffer(sb.substring(0, sb.length() - ",\n".length()));
/*     */     }
/*     */ 
/*  90 */     sb.append("\n)");
/*     */ 
/*  92 */     this.jdbcTemplate.execute(sb.toString());
/*  93 */     if ((model.getComment() != null) && (model.getComment().length() > 0)) {
/*  94 */       this.jdbcTemplate.execute("COMMENT ON TABLE " + model.getName() + " IS '" + model.getComment() + "'\n");
/*     */     }
/*  96 */     for (String columnComment : columnCommentList)
/*  97 */       this.jdbcTemplate.execute(columnComment);
/*     */   }
/*     */ 
/*     */   public void updateTableComment(String tableName, String comment)
/*     */     throws SQLException
/*     */   {
/* 103 */     StringBuffer sb = new StringBuffer();
/* 104 */     sb.append("COMMENT ON TABLE ");
/* 105 */     sb.append(tableName);
/* 106 */     sb.append(" IS '");
/* 107 */     sb.append(comment);
/* 108 */     sb.append("'\n");
/* 109 */     this.jdbcTemplate.execute(sb.toString());
/*     */   }
/*     */ 
/*     */   public void addColumn(String tableName, ColumnModel model) throws SQLException
/*     */   {
/* 114 */     StringBuffer sb = new StringBuffer();
/* 115 */     sb.append("ALTER TABLE ").append(tableName);
/* 116 */     sb.append(" ADD ");
/* 117 */     sb.append(model.getName()).append(" ");
/* 118 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/*     */ 
/* 122 */     String defVal = getDefaultValueSQL(model);
/* 123 */     if (StringUtil.isNotEmpty(defVal)) {
/* 124 */       sb.append(defVal);
/*     */     }
/* 126 */     sb.append("\n");
/* 127 */     this.jdbcTemplate.execute(sb.toString());
/* 128 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 129 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS '" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   public void updateColumn(String tableName, String columnName, ColumnModel model)
/*     */     throws SQLException
/*     */   {
/* 136 */     if (!columnName.equals(model.getName())) {
/* 137 */       StringBuffer modifyName = new StringBuffer("ALTER TABLE ").append(tableName);
/* 138 */       modifyName.append(" ALTER COLUMN ").append(columnName).append(" RENAME TO ").append(model.getName());
/* 139 */       this.jdbcTemplate.execute(modifyName.toString());
/*     */     }
/* 141 */     StringBuffer sb = new StringBuffer();
/* 142 */     sb.append("ALTER TABLE ").append(tableName);
/* 143 */     sb.append(" ALTER COLUMN ").append(model.getName());
/* 144 */     sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
/* 145 */     if (!model.getIsNull()) {
/* 146 */       sb.append(" NOT NULL ");
/*     */     }
/* 148 */     this.jdbcTemplate.execute(sb.toString());
/*     */ 
/* 150 */     if ((model.getComment() != null) && (model.getComment().length() > 0))
/* 151 */       this.jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS'" + model.getComment() + "'");
/*     */   }
/*     */ 
/*     */   public void dropTable(String tableName)
/*     */   {
/* 157 */     String selSql = "SELECT COUNT(*) AMOUNT FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_SCHEMA = SCHEMA() AND TABLE_NAME = UPPER('" + tableName + "')";
/*     */ 
/* 165 */     int rtn = this.jdbcTemplate.queryForInt(selSql);
/* 166 */     if (rtn > 0) {
/* 167 */       String sql = "DROP TABLE " + tableName;
/* 168 */       this.jdbcTemplate.execute(sql);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField)
/*     */   {
/* 174 */     String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT FK_" + fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES " + pkTableName + " (" + pkField + ") ON DELETE CASCADE";
/* 175 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropForeignKey(String tableName, String keyName)
/*     */   {
/* 180 */     String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  " + keyName;
/* 181 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public void dropIndex(String tableName, String indexName)
/*     */   {
/* 187 */     String sql = "DROP INDEX " + indexName;
/* 188 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public TableIndex getIndex(String tableName, String indexName)
/*     */   {
/* 193 */     String sql = "SELECT A.TABLE_NAME  , A.INDEX_NAME  , A.NON_UNIQUE  , A.COLUMN_NAME  , A.INDEX_TYPE_NAME  , A.REMARKS , A.SQL FROM INFORMATION_SCHEMA.INDEXES  A WHERE 1=1 ";
/*     */ 
/* 195 */     sql = sql + " AND A.INDEX_NAME = '" + indexName + "' ";
/* 196 */     List indexes = this.jdbcTemplate.query(sql, this.indexRowMapper);
/* 197 */     List indexList = mergeIndex(indexes);
/* 198 */     if (BeanUtils.isEmpty(indexList)) {
/* 199 */       return null;
/*     */     }
/* 201 */     TableIndex index = (TableIndex)indexList.get(0);
/* 202 */     return index;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByTable(String tableName)
/*     */   {
/* 208 */     String sql = "SELECT A.TABLE_NAME  , A.INDEX_NAME  , A.NON_UNIQUE  , A.COLUMN_NAME  , A.INDEX_TYPE_NAME  , A.REMARKS , A.SQL FROM INFORMATION_SCHEMA.INDEXES  A WHERE 1=1 ";
/* 209 */     sql = sql + " AND UPPER(A.TABLE_NAME) = UPPER('" + tableName + "')";
/* 210 */     List indexes = this.jdbcTemplate.query(sql, this.indexRowMapper);
/*     */ 
/* 212 */     List<TableIndex> indexList = mergeIndex(indexes);
/* 213 */     for (TableIndex index : indexList) {
/* 214 */       index.setIndexDdl(generateIndexDDL(index));
/*     */     }
/* 216 */     return indexList;
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL)
/*     */   {
/* 221 */     return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
/*     */   }
/*     */ 
/*     */   public List<TableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean)
/*     */   {
/* 226 */     String sql = "SELECT A.TABLE_NAME  , A.INDEX_NAME  , A.NON_UNIQUE  , A.COLUMN_NAME  , A.INDEX_TYPE_NAME  , A.REMARKS , A.SQL FROM INFORMATION_SCHEMA.INDEXES  A WHERE 1=1 ";
/*     */ 
/* 228 */     if (!StringUtil.isEmpty(tableName)) {
/* 229 */       sql = sql + " AND UPPER(A.TABLE_NAME) LIKE UPPER('%" + tableName + "%')";
/*     */     }
/*     */ 
/* 232 */     if (!StringUtil.isEmpty(indexName)) {
/* 233 */       sql = sql + " AND UPPER(A.INDEX_NAME) like UPPER('%" + indexName + "%')";
/*     */     }
/*     */ 
/* 236 */     if (pageBean != null) {
/* 237 */       int currentPage = pageBean.getCurrentPage();
/* 238 */       int pageSize = pageBean.getPageSize();
/* 239 */       int offset = (currentPage - 1) * pageSize;
/* 240 */       String totalSql = this.dialect.getCountSql(sql);
/* 241 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 242 */       sql = this.dialect.getLimitString(sql, offset, pageSize);
/* 243 */       pageBean.setTotalCount(total);
/*     */     }
/* 245 */     this.logger.debug(sql);
/* 246 */     List indexes = this.jdbcTemplate.query(sql, this.indexRowMapper);
/*     */ 
/* 248 */     List<TableIndex> indexList = mergeIndex(indexes);
/*     */ 
/* 250 */     for (TableIndex index : indexList) {
/* 251 */       index.setIndexDdl(generateIndexDDL(index));
/*     */     }
/* 253 */     return indexList;
/*     */   }
/*     */ 
/*     */   public void rebuildIndex(String tableName, String indexName)
/*     */   {
/* 264 */     throw new UnsupportedOperationException("h2 不支持通过JDBC进行索引重建！");
/*     */   }
/*     */ 
/*     */   public void createIndex(TableIndex index) throws SQLException
/*     */   {
/* 269 */     String sql = generateIndexDDL(index);
/* 270 */     this.jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   private List<TableIndex> mergeIndex(List<TableIndex> indexes) {
/* 274 */     List<TableIndex> indexList = new ArrayList();
/* 275 */     for (TableIndex index : indexes) {
/* 276 */       boolean found = false;
/* 277 */       for (TableIndex index1 : indexList) {
/* 278 */         if ((index.getIndexName().equals(index1.getIndexName())) && (index.getIndexTable().equals(index1.getIndexTable())))
/*     */         {
/* 280 */           index1.getIndexFields().add(index.getIndexFields().get(0));
/* 281 */           found = true;
/* 282 */           break;
/*     */         }
/*     */       }
/* 285 */       if (!found) {
/* 286 */         indexList.add(index);
/*     */       }
/*     */     }
/* 289 */     return indexList;
/*     */   }
/*     */ 
/*     */   private String generateIndexDDL(TableIndex index)
/*     */   {
/* 298 */     StringBuffer sql = new StringBuffer();
/* 299 */     sql.append("CREATE ");
/* 300 */     sql.append("INDEX ");
/* 301 */     sql.append(index.getIndexName());
/* 302 */     sql.append(" ON ");
/* 303 */     sql.append(index.getIndexTable());
/* 304 */     sql.append("(");
/* 305 */     for (String field : index.getIndexFields()) {
/* 306 */       sql.append(field);
/* 307 */       sql.append(",");
/*     */     }
/* 309 */     sql.deleteCharAt(sql.length() - 1);
/* 310 */     sql.append(")");
/* 311 */     return sql.toString();
/*     */   }
/*     */ 
/*     */   private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
/*     */   {
/* 328 */     if ("varchar".equals(columnType))
/* 329 */       return "VARCHAR(" + charLen + ')';
/* 330 */     if ("number".equals(columnType))
/* 331 */       return "DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")";
/* 332 */     if ("date".equals(columnType))
/* 333 */       return "DATE";
/* 334 */     if ("int".equals(columnType)) {
/* 335 */       if ((intLen > 0) && (intLen <= 5))
/* 336 */         return "SMALLINT";
/* 337 */       if ((intLen > 5) && (intLen <= 10)) {
/* 338 */         return "INTEGER";
/*     */       }
/* 340 */       return "BIGINT";
/*     */     }
/* 342 */     if ("clob".equals(columnType)) {
/* 343 */       return "CLOB";
/*     */     }
/* 345 */     return "VARCHAR(50)";
/*     */   }
/*     */ 
/*     */   private String getDefaultValueSQL(ColumnModel cm)
/*     */   {
/* 350 */     String sql = null;
/* 351 */     if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
/* 352 */       if ("int".equalsIgnoreCase(cm.getColumnType()))
/* 353 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/* 354 */       else if ("number".equalsIgnoreCase(cm.getColumnType()))
/* 355 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/* 356 */       else if ("varchar".equalsIgnoreCase(cm.getColumnType()))
/* 357 */         sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
/* 358 */       else if ("clob".equalsIgnoreCase(cm.getColumnType()))
/* 359 */         sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
/* 360 */       else if ("date".equalsIgnoreCase(cm.getColumnType()))
/* 361 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/*     */       else {
/* 363 */         sql = " DEFAULT " + cm.getDefaultValue() + " ";
/*     */       }
/*     */     }
/* 366 */     return sql;
/*     */   }
/*     */ 
/*     */   public boolean isTableExist(String tableName)
/*     */   {
/* 405 */     String selSql = "SELECT COUNT(*) AMOUNT FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_SCHEMA = SCHEMA() AND TABLE_NAME = UPPER('" + tableName + "')";
/*     */ 
/* 413 */     int rtn = this.jdbcTemplate.queryForInt(selSql);
/* 414 */     return rtn > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.impl.H2TableOperator
 * JD-Core Version:    0.6.2
 */