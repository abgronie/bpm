/*    */ package com.hotent.core.web.query.script.impl;
/*    */ 
/*    */ import com.hotent.core.web.query.entity.JudgeScript;
/*    */ import com.hotent.core.web.query.script.ISqlScript;
/*    */ 
/*    */ public class SqlScript
/*    */   implements ISqlScript
/*    */ {
/*    */   public String getSQL(JudgeScript judgeScript)
/*    */   {
/* 15 */     if (judgeScript == null) {
/* 16 */       return "";
/*    */     }
/* 18 */     return judgeScript.getValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.script.impl.SqlScript
 * JD-Core Version:    0.6.2
 */