/*     */ package com.hotent.core.db.entity;
/*     */ 
/*     */ public class SQLClause
/*     */ {
/*   7 */   public static short VALUEFROM_INPUT = 1;
/*     */ 
/*  11 */   public static short VALUEFROM_FIX = 2;
/*     */ 
/*  15 */   public static short VALUEFROM_SCRIPT = 3;
/*     */ 
/*  20 */   public static short VALUEFROM_FORM = 4;
/*     */   private String name;
/*     */   private String qualifiedName;
/*     */   private String label;
/*     */   private String comment;
/*     */   private String type;
/*     */   private String joinType;
/*     */   private String operate;
/*     */   private Object value;
/*     */   private int valueFrom;
/*     */   private String controlType;
/*     */   private String queryType;
/*     */   private String datefmt;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  43 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  46 */     this.name = name;
/*     */   }
/*     */   public String getQualifiedName() {
/*  49 */     return this.qualifiedName;
/*     */   }
/*     */   public void setQualifiedName(String qualifiedName) {
/*  52 */     this.qualifiedName = qualifiedName;
/*     */   }
/*     */   public String getLabel() {
/*  55 */     return this.label;
/*     */   }
/*     */   public void setLabel(String label) {
/*  58 */     this.label = label;
/*     */   }
/*     */   public String getComment() {
/*  61 */     return this.comment;
/*     */   }
/*     */   public void setComment(String comment) {
/*  64 */     this.comment = comment;
/*     */   }
/*     */   public String getType() {
/*  67 */     return this.type;
/*     */   }
/*     */   public void setType(String type) {
/*  70 */     this.type = type;
/*     */   }
/*     */   public String getJoinType() {
/*  73 */     return this.joinType;
/*     */   }
/*     */   public void setJoinType(String joinType) {
/*  76 */     this.joinType = joinType;
/*     */   }
/*     */   public String getOperate() {
/*  79 */     return this.operate;
/*     */   }
/*     */   public void setOperate(String operate) {
/*  82 */     this.operate = operate;
/*     */   }
/*     */   public Object getValue() {
/*  85 */     return this.value;
/*     */   }
/*     */   public void setValue(Object value) {
/*  88 */     this.value = value;
/*     */   }
/*     */   public int getValueFrom() {
/*  91 */     return this.valueFrom;
/*     */   }
/*     */   public void setValueFrom(int valueFrom) {
/*  94 */     this.valueFrom = valueFrom;
/*     */   }
/*     */   public String getControlType() {
/*  97 */     return this.controlType;
/*     */   }
/*     */   public void setControlType(String controlType) {
/* 100 */     this.controlType = controlType;
/*     */   }
/*     */   public String getQueryType() {
/* 103 */     return this.queryType;
/*     */   }
/*     */   public void setQueryType(String queryType) {
/* 106 */     this.queryType = queryType;
/*     */   }
/*     */   public String getDatefmt() {
/* 109 */     return this.datefmt;
/*     */   }
/*     */   public void setDatefmt(String datefmt) {
/* 112 */     this.datefmt = datefmt;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.entity.SQLClause
 * JD-Core Version:    0.6.2
 */