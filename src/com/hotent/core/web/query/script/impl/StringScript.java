/*    */ package com.hotent.core.web.query.script.impl;
/*    */ 
/*    */ import com.hotent.core.web.query.entity.JudgeSingle;
/*    */ import com.hotent.core.web.query.script.ISingleScript;
/*    */ 
/*    */ public class StringScript
/*    */   implements ISingleScript
/*    */ {
/*    */   public String getSQL(JudgeSingle judgeSingle)
/*    */   {
/* 17 */     StringBuilder sb = new StringBuilder();
/* 18 */     if ("1".equals(judgeSingle.getCompare())) {
/* 19 */       sb.append(judgeSingle.getFixFieldName()).append("=").append("'").append(judgeSingle.getValue()).append("'");
/*    */     }
/* 21 */     else if ("2".equals(judgeSingle.getCompare())) {
/* 22 */       sb.append(judgeSingle.getFixFieldName()).append("!=").append("'").append(judgeSingle.getValue()).append("'");
/*    */     }
/* 24 */     else if ("3".equals(judgeSingle.getCompare())) {
/* 25 */       sb.append("UPPER(").append(judgeSingle.getFixFieldName()).append(") =").append(" UPPER('").append(judgeSingle.getValue()).append("')");
/*    */     }
/* 28 */     else if ("4".equals(judgeSingle.getCompare())) {
/* 29 */       sb.append(judgeSingle.getFixFieldName()).append(" LIKE ").append("'%").append(judgeSingle.getValue()).append("%'");
/*    */     }
/* 31 */     else if ("5".equals(judgeSingle.getCompare())) {
/* 32 */       sb.append(judgeSingle.getFixFieldName()).append(" LIKE ").append("'").append(judgeSingle.getValue()).append("%'");
/*    */     }
/* 34 */     else if ("6".equals(judgeSingle.getCompare())) {
/* 35 */       sb.append(judgeSingle.getFixFieldName()).append(" LIKE ").append("'%").append(judgeSingle.getValue()).append("'");
/*    */     }
/*    */ 
/* 38 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.script.impl.StringScript
 * JD-Core Version:    0.6.2
 */