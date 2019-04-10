/*    */ package com.hotent.core.web.query.script.impl;
/*    */ 
/*    */ import com.hotent.core.web.query.entity.JudgeScope;
/*    */ import com.hotent.core.web.query.script.IScopeScript;
/*    */ import com.hotent.core.web.query.script.ISingleScript;
/*    */ 
/*    */ public class ScopeScript
/*    */   implements IScopeScript
/*    */ {
/*    */   public String getSQL(JudgeScope judgeScope)
/*    */   {
/* 16 */     StringBuilder sb = new StringBuilder();
/*    */ 
/* 18 */     ISingleScript queryScript = SingleScriptFactory.getQueryScript(judgeScope.getOptType());
/*    */ 
/* 21 */     String scriptBegin = queryScript.getSQL(judgeScope.getBeginJudge());
/*    */ 
/* 23 */     String scriptEnd = queryScript.getSQL(judgeScope.getEndJudge());
/*    */ 
/* 25 */     sb.append(" (").append(scriptBegin).append(" ").append(judgeScope.getRelation()).append(" ").append(scriptEnd).append(") ");
/*    */ 
/* 28 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.script.impl.ScopeScript
 * JD-Core Version:    0.6.2
 */