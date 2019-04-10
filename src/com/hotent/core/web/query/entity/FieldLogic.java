/*     */ package com.hotent.core.web.query.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FieldLogic extends FieldTable
/*     */ {
/*     */   private boolean isGroup;
/*  21 */   private List<FieldLogic> groupLogics = new ArrayList();
/*     */   private Integer dataType;
/*     */   private int judgeType;
/*     */   private JudgeSingle judgeSingle;
/*     */   private JudgeScope judgeScope;
/*     */   private JudgeScript judgeScript;
/*     */   protected String fieldRelation;
/*     */ 
/*     */   public boolean isGroup()
/*     */   {
/*  52 */     return this.isGroup;
/*     */   }
/*     */ 
/*     */   public void setGroup(boolean isGroup) {
/*  56 */     this.isGroup = isGroup;
/*     */   }
/*     */ 
/*     */   public List<FieldLogic> getGroupLogics() {
/*  60 */     return this.groupLogics;
/*     */   }
/*     */ 
/*     */   public void setGroupLogics(List<FieldLogic> groupLogics) {
/*  64 */     this.groupLogics = groupLogics;
/*     */   }
/*     */ 
/*     */   public Integer getDataType() {
/*  68 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(Integer dataType) {
/*  72 */     this.dataType = dataType;
/*     */   }
/*     */ 
/*     */   public int getJudgeType() {
/*  76 */     return this.judgeType;
/*     */   }
/*     */ 
/*     */   public void setJudgeType(int judgeType) {
/*  80 */     this.judgeType = judgeType;
/*     */   }
/*     */ 
/*     */   public JudgeSingle getJudgeSingle() {
/*  84 */     return this.judgeSingle;
/*     */   }
/*     */ 
/*     */   public void setJudgeSingle(JudgeSingle judgeSingle) {
/*  88 */     this.judgeSingle = judgeSingle;
/*     */   }
/*     */ 
/*     */   public JudgeScope getJudgeScope() {
/*  92 */     return this.judgeScope;
/*     */   }
/*     */ 
/*     */   public void setJudgeScope(JudgeScope judgeScope) {
/*  96 */     this.judgeScope = judgeScope;
/*     */   }
/*     */ 
/*     */   public JudgeScript getJudgeScript() {
/* 100 */     return this.judgeScript;
/*     */   }
/*     */ 
/*     */   public void setJudgeScript(JudgeScript judgeScript) {
/* 104 */     this.judgeScript = judgeScript;
/*     */   }
/*     */ 
/*     */   public String getFieldRelation() {
/* 108 */     return this.fieldRelation;
/*     */   }
/*     */ 
/*     */   public void setFieldRelation(String fieldRelation) {
/* 112 */     this.fieldRelation = fieldRelation;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 117 */     return new ToStringBuilder(this).append("isGroup", this.isGroup).append("groupLogics.size", this.groupLogics.size()).append("dataType", this.dataType).append("judgeType", this.judgeType).append("fieldRelation", this.fieldRelation).append("judgeSingle", this.judgeSingle).append("JudgeScope", this.judgeScope).toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.FieldLogic
 * JD-Core Version:    0.6.2
 */