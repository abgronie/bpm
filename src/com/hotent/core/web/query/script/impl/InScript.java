/*    */ package com.hotent.core.web.query.script.impl;
/*    */ 
/*    */ import com.hotent.core.web.query.entity.JudgeSingle;
/*    */ import com.hotent.core.web.query.script.ISingleScript;
/*    */ 
/*    */ public class InScript
/*    */   implements ISingleScript
/*    */ {
/*    */   public String getSQL(JudgeSingle judgeSingle)
/*    */   {
/* 15 */     StringBuilder sb = new StringBuilder();
/* 16 */     if ("1".equals(judgeSingle.getCompare())) {
/* 17 */       sb.append(judgeSingle.getFixFieldName()).append(" in (").append("").append(judgeSingle.getValue()).append(")");
/*    */     }
/* 19 */     else if ("2".equals(judgeSingle.getCompare())) {
/* 20 */       sb.append(judgeSingle.getFixFieldName()).append(" not in (").append("").append(judgeSingle.getValue()).append(")");
/*    */     }
/* 22 */     else if ("3".equals(judgeSingle.getCompare())) {
/* 23 */       sb.append(judgeSingle.getFixFieldName()).append(" = ").append("").append(judgeSingle.getValue());
/*    */     }
/* 25 */     else if ("4".equals(judgeSingle.getCompare())) {
/* 26 */       sb.append(judgeSingle.getFixFieldName()).append(" !=").append("").append(judgeSingle.getValue());
/*    */     }
/*    */ 
/* 29 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.script.impl.InScript
 * JD-Core Version:    0.6.2
 */