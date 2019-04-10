/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class H2Dialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimit()
/*    */   {
/* 13 */     return true;
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 18 */     return new StringBuffer(sql.length() + 40).append(sql).append(" limit " + limitPlaceholder).toString();
/*    */   }
/*    */ 
/*    */   public boolean supportsLimitOffset()
/*    */   {
/* 24 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.H2Dialect
 * JD-Core Version:    0.6.2
 */