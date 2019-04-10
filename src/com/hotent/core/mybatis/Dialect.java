/*    */ package com.hotent.core.mybatis;
/*    */ 
/*    */ import com.hotent.core.web.query.entity.FieldSort;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Dialect
/*    */ {
/*    */   public boolean supportsLimit()
/*    */   {
/* 19 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimitOffset() {
/* 23 */     return supportsLimit();
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, int limit)
/*    */   {
/* 39 */     return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 52 */     throw new UnsupportedOperationException("paged queries not supported");
/*    */   }
/*    */ 
/*    */   public String getCountSql(String sql)
/*    */   {
/* 61 */     return "select count(1) from (" + sql + ") tmp_count";
/*    */   }
/*    */ 
/*    */   public String getSortString(String sql, List<FieldSort> orders)
/*    */   {
/* 75 */     if ((orders == null) || (orders.isEmpty())) {
/* 76 */       return sql;
/*    */     }
/*    */ 
/* 79 */     StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
/* 80 */     for (FieldSort order : orders) {
/* 81 */       if (order != null) {
/* 82 */         buffer.append(order.toString()).append(", ");
/*    */       }
/*    */     }
/*    */ 
/* 86 */     buffer.delete(buffer.length() - 2, buffer.length());
/* 87 */     return buffer.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.Dialect
 * JD-Core Version:    0.6.2
 */