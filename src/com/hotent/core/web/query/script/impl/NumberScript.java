/*    */ package com.hotent.core.web.query.script.impl;
/*    */ 
/*    */ import com.hotent.core.web.query.entity.JudgeSingle;
/*    */ import com.hotent.core.web.query.script.ISingleScript;
/*    */ 
/*    */ public class NumberScript
/*    */   implements ISingleScript
/*    */ {
/*    */   public String getSQL(JudgeSingle judgeSingle)
/*    */   {
/* 16 */     StringBuilder sb = new StringBuilder();
/* 17 */     if ("1".equals(judgeSingle.getCompare())) {
/* 18 */       sb.append(judgeSingle.getFixFieldName()).append("=").append(judgeSingle.getValue());
/*    */     }
/* 20 */     else if ("2".equals(judgeSingle.getCompare())) {
/* 21 */       sb.append(judgeSingle.getFixFieldName()).append("!=").append(judgeSingle.getValue());
/*    */     }
/* 23 */     else if ("3".equals(judgeSingle.getCompare())) {
/* 24 */       sb.append(judgeSingle.getFixFieldName()).append(">").append(judgeSingle.getValue());
/*    */     }
/* 26 */     else if ("4".equals(judgeSingle.getCompare())) {
/* 27 */       sb.append(judgeSingle.getFixFieldName()).append(">=").append(judgeSingle.getValue());
/*    */     }
/* 29 */     else if ("5".equals(judgeSingle.getCompare())) {
/* 30 */       sb.append(judgeSingle.getFixFieldName()).append("<").append(judgeSingle.getValue());
/*    */     }
/* 32 */     else if ("6".equals(judgeSingle.getCompare())) {
/* 33 */       sb.append(judgeSingle.getFixFieldName()).append("<=").append(judgeSingle.getValue());
/*    */     }
/*    */ 
/* 36 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.script.impl.NumberScript
 * JD-Core Version:    0.6.2
 */