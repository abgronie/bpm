/*    */ package com.hotent.core.db.entity;
/*    */ 
/*    */ public class SQLField
/*    */ {
/*    */   public static final String COLUMNTYPE_INT = "int";
/*    */   public static final String COLUMNTYPE_VARCHAR = "varchar";
/*    */   public static final String COLUMNTYPE_CLOB = "clob";
/*    */   public static final String COLUMNTYPE_NUMBER = "number";
/*    */   public static final String COLUMNTYPE_DATE = "date";
/*    */   public static final String COLUMNTYPE_TEXT = "text";
/*    */   private String name;
/*    */   private String qualifiedName;
/*    */   private String label;
/*    */   private int index;
/*    */   private String comment;
/*    */   private String type;
/*    */   private String table;
/*    */   private boolean sortable;
/*    */   private boolean display;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 22 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 25 */     this.name = name;
/*    */   }
/*    */   public String getQualifiedName() {
/* 28 */     return this.qualifiedName;
/*    */   }
/*    */   public void setQualifiedName(String qualifiedName) {
/* 31 */     this.qualifiedName = qualifiedName;
/*    */   }
/*    */   public String getLabel() {
/* 34 */     return this.label;
/*    */   }
/*    */   public void setLabel(String label) {
/* 37 */     this.label = label;
/*    */   }
/*    */   public int getIndex() {
/* 40 */     return this.index;
/*    */   }
/*    */   public void setIndex(int index) {
/* 43 */     this.index = index;
/*    */   }
/*    */   public String getComment() {
/* 46 */     return this.comment;
/*    */   }
/*    */   public void setComment(String comment) {
/* 49 */     this.comment = comment;
/*    */   }
/*    */   public String getType() {
/* 52 */     return this.type;
/*    */   }
/*    */   public void setType(String type) {
/* 55 */     this.type = type;
/*    */   }
/*    */   public String getTable() {
/* 58 */     return this.table;
/*    */   }
/*    */   public void setTable(String table) {
/* 61 */     this.table = table;
/*    */   }
/*    */   public boolean isSortable() {
/* 64 */     return this.sortable;
/*    */   }
/*    */   public void setSortable(boolean sortable) {
/* 67 */     this.sortable = sortable;
/*    */   }
/*    */   public boolean isDisplay() {
/* 70 */     return this.display;
/*    */   }
/*    */   public void setDisplay(boolean display) {
/* 73 */     this.display = display;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.entity.SQLField
 * JD-Core Version:    0.6.2
 */