/*    */ package com.hotent.core.valid;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ValidField
/*    */ {
/* 13 */   private String formName = "";
/*    */ 
/* 15 */   private String displayName = "";
/*    */ 
/* 17 */   private List<Rule> ruleList = new ArrayList();
/*    */ 
/*    */   public String getFormName()
/*    */   {
/* 26 */     return this.formName;
/*    */   }
/*    */ 
/*    */   public void setFormName(String formName) {
/* 30 */     this.formName = formName;
/*    */   }
/*    */ 
/*    */   public String getDisplayName()
/*    */   {
/* 39 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   public void setDisplayName(String displayName) {
/* 43 */     this.displayName = displayName;
/*    */   }
/*    */ 
/*    */   public List<Rule> getRuleList()
/*    */   {
/* 52 */     return this.ruleList;
/*    */   }
/*    */ 
/*    */   public void setRuleList(List<Rule> ruleList) {
/* 56 */     this.ruleList = ruleList;
/*    */   }
/*    */ 
/*    */   public void addRule(Rule rule)
/*    */   {
/* 65 */     this.ruleList.add(rule);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.valid.ValidField
 * JD-Core Version:    0.6.2
 */