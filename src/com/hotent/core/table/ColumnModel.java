/*     */ package com.hotent.core.table;
/*     */ 
/*     */ import com.hotent.core.util.StringUtil;
/*     */ 
/*     */ public class ColumnModel
/*     */ {
/*     */   public static final String COLUMNTYPE_INT = "int";
/*     */   public static final String COLUMNTYPE_VARCHAR = "varchar";
/*     */   public static final String COLUMNTYPE_CLOB = "clob";
/*     */   public static final String COLUMNTYPE_NUMBER = "number";
/*     */   public static final String COLUMNTYPE_DATE = "date";
/*     */   public static final String COLUMNTYPE_TEXT = "text";
/*  17 */   private String name = "";
/*     */ 
/*  19 */   private String comment = "";
/*     */ 
/*  21 */   private boolean isPk = false;
/*     */ 
/*  23 */   private boolean isFk = false;
/*     */ 
/*  25 */   private boolean isNull = true;
/*     */   private String columnType;
/*  29 */   private int charLen = 0;
/*     */ 
/*  31 */   private int decimalLen = 0;
/*     */ 
/*  33 */   private int intLen = 0;
/*     */ 
/*  35 */   private String fkRefTable = "";
/*     */ 
/*  37 */   private String fkRefColumn = "";
/*     */ 
/*  39 */   private String defaultValue = "";
/*     */ 
/*  41 */   private String tableName = "";
/*     */   private String label;
/*     */   private int index;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  48 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  52 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getComment() {
/*  56 */     if (StringUtil.isNotEmpty(this.comment)) {
/*  57 */       this.comment = this.comment.replace("'", "''");
/*     */     }
/*  59 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public void setComment(String comment) {
/*  63 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public boolean getIsPk() {
/*  67 */     return this.isPk;
/*     */   }
/*     */ 
/*     */   public void setIsPk(boolean isPk) {
/*  71 */     this.isPk = isPk;
/*     */   }
/*     */ 
/*     */   public boolean getIsNull() {
/*  75 */     return this.isNull;
/*     */   }
/*     */ 
/*     */   public void setIsNull(boolean isNull) {
/*  79 */     this.isNull = isNull;
/*     */   }
/*     */ 
/*     */   public String getColumnType() {
/*  83 */     return this.columnType;
/*     */   }
/*     */ 
/*     */   public void setColumnType(String columnType) {
/*  87 */     this.columnType = columnType;
/*     */   }
/*     */ 
/*     */   public int getCharLen() {
/*  91 */     return this.charLen;
/*     */   }
/*     */ 
/*     */   public void setCharLen(int charLen) {
/*  95 */     this.charLen = charLen;
/*     */   }
/*     */ 
/*     */   public int getDecimalLen() {
/*  99 */     return this.decimalLen;
/*     */   }
/*     */ 
/*     */   public void setDecimalLen(int decimalLen) {
/* 103 */     this.decimalLen = decimalLen;
/*     */   }
/*     */ 
/*     */   public int getIntLen() {
/* 107 */     return this.intLen;
/*     */   }
/*     */ 
/*     */   public void setIntLen(int intLen) {
/* 111 */     this.intLen = intLen;
/*     */   }
/*     */ 
/*     */   public boolean getIsFk() {
/* 115 */     return this.isFk;
/*     */   }
/*     */ 
/*     */   public void setIsFk(boolean isFk) {
/* 119 */     this.isFk = isFk;
/*     */   }
/*     */ 
/*     */   public String getFkRefTable() {
/* 123 */     return this.fkRefTable;
/*     */   }
/*     */ 
/*     */   public void setFkRefTable(String fkRefTable) {
/* 127 */     this.fkRefTable = fkRefTable;
/*     */   }
/*     */ 
/*     */   public String getFkRefColumn() {
/* 131 */     return this.fkRefColumn;
/*     */   }
/*     */ 
/*     */   public void setFkRefColumn(String fkRefColumn) {
/* 135 */     this.fkRefColumn = fkRefColumn;
/*     */   }
/*     */ 
/*     */   public String getDefaultValue() {
/* 139 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */   public void setDefaultValue(String defaultValue) {
/* 143 */     this.defaultValue = defaultValue;
/*     */   }
/*     */ 
/*     */   public String getTableName() {
/* 147 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String tableName) {
/* 151 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   public String getLabel() {
/* 155 */     return this.label;
/*     */   }
/*     */ 
/*     */   public void setLabel(String label) {
/* 159 */     this.label = label;
/*     */   }
/*     */ 
/*     */   public int getIndex() {
/* 163 */     return this.index;
/*     */   }
/*     */ 
/*     */   public void setIndex(int index) {
/* 167 */     this.index = index;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.ColumnModel
 * JD-Core Version:    0.6.2
 */