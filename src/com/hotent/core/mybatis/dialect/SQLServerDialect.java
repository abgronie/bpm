/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class SQLServerDialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimitOffset()
/*    */   {
/* 12 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimit()
/*    */   {
/* 17 */     return true;
/*    */   }
/*    */ 
/*    */   static int getAfterSelectInsertPoint(String sql)
/*    */   {
/* 22 */     int selectIndex = sql.toLowerCase().indexOf("select");
/* 23 */     int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
/* 24 */     return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, int limit)
/*    */   {
/* 29 */     return getLimitString(sql, offset, null, limit, null);
/*    */   }
/*    */ 
/*    */   public String getLimitString(String querySelect, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 34 */     if (offset > 0)
/*    */     {
/* 36 */       throw new UnsupportedOperationException("sql server has no offset");
/*    */     }
/*    */ 
/* 39 */     return new StringBuffer(querySelect.length() + 8).append(querySelect).insert(getAfterSelectInsertPoint(querySelect), " top " + limit).toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.SQLServerDialect
 * JD-Core Version:    0.6.2
 */