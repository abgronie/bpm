/*     */ package com.hotent.core.web.query.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FilterJsonStruct
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int RULE_TYPE_NORMAL = 1;
/*     */   public static final int RULE_TYPE_SCRIPT = 2;
/*  22 */   private Boolean branch = Boolean.valueOf(false);
/*     */   private List<FilterJsonStruct> sub;
/*     */   private Integer ruleType;
/*     */   private Integer optType;
/*     */   private String flowvarKey;
/*  42 */   private String compType = "";
/*     */   private String judgeCon1;
/*     */   private String judgeVal1;
/*     */   private String judgeCon2;
/*     */   private String judgeVal2;
/*     */   private String conDesc;
/*     */   private String tables;
/*     */   private String script;
/*  84 */   private String source = "1";
/*     */ 
/*  86 */   protected Integer isHidden = Integer.valueOf(0);
/*     */ 
/*  89 */   private String datefmt = "yyyy-MM-dd";
/*     */ 
/*  94 */   private String table = "";
/*     */ 
/*  98 */   private String mainTable = "";
/*     */ 
/* 102 */   private String relation = "";
/*     */ 
/*     */   public Integer getRuleType() {
/* 105 */     return this.ruleType;
/*     */   }
/*     */   public void setRuleType(Integer ruleType) {
/* 108 */     this.ruleType = ruleType;
/*     */   }
/*     */   public Integer getOptType() {
/* 111 */     return this.optType;
/*     */   }
/*     */   public void setOptType(Integer optType) {
/* 114 */     this.optType = optType;
/*     */   }
/*     */   public String getFlowvarKey() {
/* 117 */     return this.flowvarKey;
/*     */   }
/*     */   public void setFlowvarKey(String flowvarKey) {
/* 120 */     this.flowvarKey = flowvarKey;
/*     */   }
/*     */   public String getCompType() {
/* 123 */     return this.compType;
/*     */   }
/*     */   public void setCompType(String compType) {
/* 126 */     this.compType = compType;
/*     */   }
/*     */   public String getJudgeCon1() {
/* 129 */     return this.judgeCon1;
/*     */   }
/*     */   public void setJudgeCon1(String judgeCon1) {
/* 132 */     this.judgeCon1 = judgeCon1;
/*     */   }
/*     */   public String getJudgeVal1() {
/* 135 */     return this.judgeVal1;
/*     */   }
/*     */   public void setJudgeVal1(String judgeVal1) {
/* 138 */     this.judgeVal1 = judgeVal1;
/*     */   }
/*     */   public String getJudgeCon2() {
/* 141 */     return this.judgeCon2;
/*     */   }
/*     */   public void setJudgeCon2(String judgeCon2) {
/* 144 */     this.judgeCon2 = judgeCon2;
/*     */   }
/*     */   public String getJudgeVal2() {
/* 147 */     return this.judgeVal2;
/*     */   }
/*     */   public void setJudgeVal2(String judgeVal2) {
/* 150 */     this.judgeVal2 = judgeVal2;
/*     */   }
/*     */   public String getConDesc() {
/* 153 */     return this.conDesc;
/*     */   }
/*     */   public void setConDesc(String conDesc) {
/* 156 */     this.conDesc = conDesc;
/*     */   }
/*     */   public String getScript() {
/* 159 */     return this.script;
/*     */   }
/*     */   public void setScript(String script) {
/* 162 */     this.script = script;
/*     */   }
/*     */ 
/*     */   public Boolean getBranch() {
/* 166 */     return this.branch;
/*     */   }
/*     */   public void setBranch(Boolean branch) {
/* 169 */     this.branch = branch;
/*     */   }
/*     */   public List<FilterJsonStruct> getSub() {
/* 172 */     return this.sub;
/*     */   }
/*     */   public void setSub(List<FilterJsonStruct> sub) {
/* 175 */     this.sub = sub;
/*     */   }
/*     */   public String getSource() {
/* 178 */     return this.source;
/*     */   }
/*     */   public void setSource(String source) {
/* 181 */     this.source = source;
/*     */   }
/*     */ 
/*     */   public String getDatefmt() {
/* 185 */     return this.datefmt;
/*     */   }
/*     */   public void setDatefmt(String datefmt) {
/* 188 */     this.datefmt = datefmt;
/*     */   }
/*     */ 
/*     */   public String getTable() {
/* 192 */     return this.table;
/*     */   }
/*     */   public void setTable(String table) {
/* 195 */     this.table = table;
/*     */   }
/*     */ 
/*     */   public String getMainTable() {
/* 199 */     return this.mainTable;
/*     */   }
/*     */   public void setMainTable(String mainTable) {
/* 202 */     this.mainTable = mainTable;
/*     */   }
/*     */   public String getRelation() {
/* 205 */     return this.relation;
/*     */   }
/*     */   public void setRelation(String relation) {
/* 208 */     this.relation = relation;
/*     */   }
/*     */   public String getTables() {
/* 211 */     return this.tables;
/*     */   }
/*     */   public void setTables(String tables) {
/* 214 */     this.tables = tables;
/*     */   }
/*     */ 
/*     */   public Integer getIsHidden() {
/* 218 */     return this.isHidden;
/*     */   }
/*     */   public void setIsHidden(Integer isHidden) {
/* 221 */     this.isHidden = isHidden;
/*     */   }
/*     */   public String toString() {
/* 224 */     return "ConditionJsonStruct [ruleType=" + this.ruleType + ", optType=" + this.optType + ", flowvarKey=" + this.flowvarKey + ", compType=" + this.compType + ", judgeCon1=" + this.judgeCon1 + ", judgeVal1=" + this.judgeVal1 + ", judgeCon2=" + this.judgeCon2 + ", judgeVal2=" + this.judgeVal2 + ", conDesc=" + this.conDesc + ", script=" + this.script + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.FilterJsonStruct
 * JD-Core Version:    0.6.2
 */