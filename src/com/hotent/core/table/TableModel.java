/*     */ package com.hotent.core.table;
/*     */ 
/*     */ import com.hotent.core.util.AppConfigUtil;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TableModel
/*     */ {
/*     */   public static final String PK_COLUMN_NAME = "ID";
/*     */   public static final String FK_COLUMN_NAME = "REFID";
/*  80 */   public static String CUSTOMER_COLUMN_PREFIX = "F_";
/*     */ 
/*  29 */   public static String CUSTOMER_TABLE_PREFIX = "W_";
/*     */   public static final String CUSTOMER_INDEX_PREFIX = "IDX_";
/*     */   public static final String CUSTOMER_TABLE_HIS_SUFFIX = "_HISTORY";
/*     */   public static final String CUSTOMER_TABLE_COMM_PREFIX = "TT_";
/*     */   public static final String CUSTOMER_COLUMN_CURRENTUSERID = "curentUserId_";
/*     */   public static final String CUSTOMER_COLUMN_CURRENTORGID = "curentOrgId_";
/*     */   public static final String CUSTOMER_COLUMN_FLOWRUNID = "flowRunId_";
/*     */   public static final String CUSTOMER_COLUMN_DEFID = "defId_";
/*     */   public static final String CUSTOMER_COLUMN_CREATETIME = "CREATETIME";
/*  69 */   private String name = "";
/*     */ 
/*  71 */   private String comment = "";
/*     */ 
/*  73 */   private List<ColumnModel> columnList = new ArrayList();
/*     */ 
/*     */   public String getName()
/*     */   {
/*  83 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  86 */     this.name = name;
/*     */   }
/*     */   public String getComment() {
/*  89 */     if (StringUtil.isNotEmpty(this.comment)) {
/*  90 */       this.comment = this.comment.replace("'", "''");
/*     */     }
/*  92 */     return this.comment;
/*     */   }
/*     */   public void setComment(String comment) {
/*  95 */     this.comment = comment;
/*     */   }
/*     */ 
/*     */   public void addColumnModel(ColumnModel model)
/*     */   {
/* 102 */     this.columnList.add(model);
/*     */   }
/*     */ 
/*     */   public List<ColumnModel> getColumnList() {
/* 106 */     return this.columnList;
/*     */   }
/*     */   public void setColumnList(List<ColumnModel> columnList) {
/* 109 */     this.columnList = columnList;
/*     */   }
/*     */ 
/*     */   public List<ColumnModel> getPrimayKey()
/*     */   {
/* 114 */     List pks = new ArrayList();
/* 115 */     for (ColumnModel column : this.columnList) {
/* 116 */       if (column.getIsPk()) {
/* 117 */         pks.add(column);
/*     */       }
/*     */     }
/* 120 */     return pks;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 124 */     return "TableModel [name=" + this.name + ", comment=" + this.comment + ", columnList=" + this.columnList + "]";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  76 */     String customerTablePrefix = AppConfigUtil.get("CUSTOMER_TABLE_PREFIX");
/*  77 */     CUSTOMER_TABLE_PREFIX = StringUtil.isEmpty(customerTablePrefix) ? CUSTOMER_TABLE_PREFIX : customerTablePrefix;
/*     */ 
/*  79 */     String customerColumnPrefix = AppConfigUtil.get("CUSTOMER_COLUMN_PREFIX");
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.TableModel
 * JD-Core Version:    0.6.2
 */