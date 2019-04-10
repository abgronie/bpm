/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class OracleDialect extends Dialect
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
/* 23 */     sql = sql.trim();
/* 24 */     boolean isForUpdate = false;
/* 25 */     if (sql.toLowerCase().endsWith(" for update"))
/*    */     {
/* 27 */       sql = sql.substring(0, sql.length() - 11);
/* 28 */       isForUpdate = true;
/*    */     }
/* 30 */     StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
/* 31 */     if (offset > 0)
/*    */     {
/* 33 */       pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
/*    */     }
/*    */     else
/*    */     {
/* 37 */       pagingSelect.append("select * from ( ");
/*    */     }
/* 39 */     pagingSelect.append(sql);
/* 40 */     if (offset > 0)
/*    */     {
/* 43 */       String endString = offsetPlaceholder + "+" + limitPlaceholder;
/* 44 */       pagingSelect.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
/*    */     }
/*    */     else
/*    */     {
/* 48 */       pagingSelect.append(" ) where rownum <= " + limitPlaceholder);
/*    */     }
/* 50 */     if (isForUpdate)
/*    */     {
/* 52 */       pagingSelect.append(" for update");
/*    */     }
/* 54 */     return pagingSelect.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.OracleDialect
 * JD-Core Version:    0.6.2
 */