/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class MySQLDialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimitOffset()
/*    */   {
/* 13 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimit()
/*    */   {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 23 */     if (offset > 0)
/*    */     {
/* 25 */       return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
/*    */     }
/*    */ 
/* 29 */     return sql + " limit " + limitPlaceholder;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.MySQLDialect
 * JD-Core Version:    0.6.2
 */