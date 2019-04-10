/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class HSQLDialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimit()
/*    */   {
/* 13 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimitOffset()
/*    */   {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 23 */     boolean hasOffset = offset > 0;
/* 24 */     return new StringBuffer(sql.length() + 10).append(sql).insert(sql.toLowerCase().indexOf("select") + 6, " top " + limitPlaceholder).toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.HSQLDialect
 * JD-Core Version:    0.6.2
 */