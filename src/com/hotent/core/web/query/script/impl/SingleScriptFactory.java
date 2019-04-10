/*    */ package com.hotent.core.web.query.script.impl;
/*    */ 
/*    */ import com.hotent.core.web.query.script.ISingleScript;
/*    */ 
/*    */ public class SingleScriptFactory
/*    */ {
/*    */   public static final int OPT_TYPE_NUMBER = 1;
/*    */   public static final int OPT_TYPE_STRING = 2;
/*    */   public static final int OPT_TYPE_DATE = 3;
/*    */   public static final int OPT_TYPE_DIC = 4;
/*    */   public static final int OPT_TYPE_SELECTOR = 5;
/*    */ 
/*    */   public static ISingleScript getQueryScript(Integer optType)
/*    */   {
/* 19 */     switch (optType.intValue()) {
/*    */     case 1:
/*    */     case 3:
/* 22 */       return new NumberScript();
/*    */     case 2:
/*    */     case 4:
/* 25 */       return new StringScript();
/*    */     case 5:
/* 27 */       return new InScript();
/*    */     }
/* 29 */     return new NumberScript();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.script.impl.SingleScriptFactory
 * JD-Core Version:    0.6.2
 */