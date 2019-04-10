/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class KingBaseDialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimit()
/*    */   {
/* 12 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimitOffset() {
/* 16 */     return true;
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 21 */     sql = sql.trim();
/* 22 */     boolean isForUpdate = false;
/* 23 */     if (sql.toLowerCase().endsWith(" for update")) {
/* 24 */       sql = sql.substring(0, sql.length() - 11);
/* 25 */       isForUpdate = true;
/*    */     }
/* 27 */     StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
/* 28 */     if (offset > 0) {
/* 29 */       pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
/*    */     }
/*    */     else {
/* 32 */       pagingSelect.append("select * from ( ");
/*    */     }
/* 34 */     pagingSelect.append(sql);
/* 35 */     if (offset > 0)
/*    */     {
/* 37 */       String endString = offsetPlaceholder + "+" + limitPlaceholder;
/* 38 */       pagingSelect.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
/*    */     }
/*    */     else {
/* 41 */       pagingSelect.append(" ) where rownum <= " + limitPlaceholder);
/*    */     }
/* 43 */     if (isForUpdate) {
/* 44 */       pagingSelect.append(" for update");
/*    */     }
/* 46 */     return pagingSelect.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.KingBaseDialect
 * JD-Core Version:    0.6.2
 */