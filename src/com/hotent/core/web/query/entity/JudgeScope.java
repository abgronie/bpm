/*    */ package com.hotent.core.web.query.entity;
/*    */ 
/*    */ import com.hotent.core.util.BeanUtils;
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class JudgeScope extends JudgeSingle
/*    */ {
/*    */   private String compareEnd;
/*    */   private String valueEnd;
/*    */   private String relation;
/*    */   private Integer optType;
/*    */ 
/*    */   public JudgeSingle getBeginJudge()
/*    */   {
/* 33 */     JudgeSingle judge = new JudgeSingle();
/* 34 */     judge.setFieldName(this.fieldName);
/* 35 */     judge.setCompare(this.compare);
/* 36 */     judge.setValue(this.value);
/* 37 */     return judge;
/*    */   }
/*    */ 
/*    */   public JudgeSingle getEndJudge() {
/* 41 */     JudgeSingle judge = new JudgeSingle();
/* 42 */     judge.setFieldName(this.fieldName);
/* 43 */     judge.setCompare(this.compareEnd);
/* 44 */     judge.setValue(this.valueEnd);
/* 45 */     return judge;
/*    */   }
/*    */ 
/*    */   public void setCompareEnd(String compareEnd) {
/* 49 */     this.compareEnd = compareEnd;
/*    */   }
/*    */ 
/*    */   public void setValueEnd(String valueEnd) {
/* 53 */     this.valueEnd = valueEnd;
/*    */   }
/*    */ 
/*    */   public void setRelation(String relation) {
/* 57 */     this.relation = relation;
/*    */   }
/*    */ 
/*    */   public String getRelation() {
/* 61 */     if (BeanUtils.isEmpty(this.relation))
/*    */     {
/* 63 */       return "AND";
/* 64 */     }return this.relation;
/*    */   }
/*    */ 
/*    */   public Integer getOptType()
/*    */   {
/* 71 */     return this.optType;
/*    */   }
/*    */ 
/*    */   public void setOptType(Integer optType) {
/* 75 */     this.optType = optType;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 80 */     return new ToStringBuilder(this).append("fieldName", this.fieldName).append("compare", this.compare).append("value", this.value).append("compareEnd", this.compareEnd).append("valueEnd", this.valueEnd).append("relation", this.relation).toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.JudgeScope
 * JD-Core Version:    0.6.2
 */