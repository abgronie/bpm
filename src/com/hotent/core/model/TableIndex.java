/*     */ package com.hotent.core.model;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class TableIndex extends BaseModel
/*     */   implements Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  17 */   public static String INDEX_TYPE_BITMAP = "BITMAP";
/*  18 */   public static String INDEX_TYPE_BTREE = "BTREE";
/*  19 */   public static String INDEX_TYPE_FUNCTION = "FUNCTION";
/*  20 */   public static String INDEX_TYPE_HEAP = "HEAP";
/*  21 */   public static String INDEX_TYPE_CLUSTERED = "CLUSTERED";
/*  22 */   public static String INDEX_TYPE_NONCLUSTERED = "NONCLUSTERED";
/*  23 */   public static String INDEX_TYPE_XML = "XML";
/*  24 */   public static String INDEX_TYPE_SPATIAL = "SPATIAL";
/*  25 */   public static String INDEX_TYPE_REG = "REGULAR";
/*  26 */   public static String INDEX_TYPE_DIM = "DIMENSIONBLOCK";
/*  27 */   public static String INDEX_TYPE_BLOK = "BLOCK";
/*     */ 
/*  29 */   public static String TABLE_TYPE_TABLE = "TABLE";
/*  30 */   public static String TABLE_TYPE_VIEW = "VIEW";
/*     */ 
/*  32 */   public static String INDEX_STATUS_VALIDATE = "VALIDATE";
/*  33 */   public static String INDEX_STATUS_INVALIDATE = "INVALIDATE";
/*     */   private String indexTable;
/*     */   private String tableType;
/*     */   private String indexName;
/*     */   private String indexType;
/*     */   private String indexStatus;
/*     */   private List<String> indexFields;
/*     */   private boolean unique;
/*     */   private String indexDdl;
/*     */   private String indexComment;
/*     */   private boolean pkIndex;
/*     */ 
/*     */   public String getIndexName()
/*     */   {
/*  46 */     return this.indexName;
/*     */   }
/*     */   public void setIndexName(String indexName) {
/*  49 */     this.indexName = indexName;
/*     */   }
/*     */   public String getIndexType() {
/*  52 */     return this.indexType;
/*     */   }
/*     */   public void setIndexType(String indexType) {
/*  55 */     this.indexType = indexType;
/*     */   }
/*     */   public List<String> getIndexFields() {
/*  58 */     return this.indexFields;
/*     */   }
/*     */   public void setIndexFields(List<String> indexFields) {
/*  61 */     this.indexFields = indexFields;
/*     */   }
/*     */   public String getIndexComment() {
/*  64 */     return this.indexComment;
/*     */   }
/*     */   public void setIndexComment(String indexComment) {
/*  67 */     this.indexComment = indexComment;
/*     */   }
/*     */   public String getIndexTable() {
/*  70 */     return this.indexTable;
/*     */   }
/*     */   public void setIndexTable(String indexTable) {
/*  73 */     this.indexTable = indexTable;
/*     */   }
/*     */   public String getIndexStatus() {
/*  76 */     return this.indexStatus;
/*     */   }
/*     */   public void setIndexStatus(String indexStatus) {
/*  79 */     this.indexStatus = indexStatus;
/*     */   }
/*     */   public String getTableType() {
/*  82 */     return this.tableType;
/*     */   }
/*     */   public void setTableType(String tableType) {
/*  85 */     this.tableType = tableType;
/*     */   }
/*     */   public String getIndexDdl() {
/*  88 */     return this.indexDdl;
/*     */   }
/*     */   public void setIndexDdl(String indexDdl) {
/*  91 */     this.indexDdl = indexDdl;
/*     */   }
/*     */   public boolean isUnique() {
/*  94 */     return this.unique;
/*     */   }
/*     */   public void setUnique(boolean unique) {
/*  97 */     this.unique = unique;
/*     */   }
/*     */   public boolean isPkIndex() {
/* 100 */     return this.pkIndex;
/*     */   }
/*     */   public void setPkIndex(boolean pkIndex) {
/* 103 */     this.pkIndex = pkIndex;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 108 */     return "BpmFormTableIndex [indexTable=" + this.indexTable + ", tableType=" + this.tableType + ", indexName=" + this.indexName + ", indexType=" + this.indexType + ", indexStatus=" + this.indexStatus + ", indexFields=" + this.indexFields + ", unique=" + this.unique + ", indexDdl=" + this.indexDdl + ", indexComment=" + this.indexComment + ", pkIndex=" + this.pkIndex + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.TableIndex
 * JD-Core Version:    0.6.2
 */