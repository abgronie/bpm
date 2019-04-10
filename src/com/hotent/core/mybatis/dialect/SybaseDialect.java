/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class SybaseDialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimit()
/*    */   {
/*  9 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimitOffset()
/*    */   {
/* 14 */     return false;
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 19 */     throw new UnsupportedOperationException("paged queries not supported");
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.SybaseDialect
 * JD-Core Version:    0.6.2
 */