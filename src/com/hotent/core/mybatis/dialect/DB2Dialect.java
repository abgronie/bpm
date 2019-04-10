/*    */ package com.hotent.core.mybatis.dialect;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ 
/*    */ public class DB2Dialect extends Dialect
/*    */ {
/*    */   public boolean supportsLimit()
/*    */   {
/* 14 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean supportsLimitOffset()
/*    */   {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   private static String getRowNumber(String sql)
/*    */   {
/* 24 */     StringBuffer rownumber = new StringBuffer(50).append("rownumber() over(");
/* 25 */     int orderByIndex = sql.toLowerCase().indexOf("order by");
/* 26 */     if ((orderByIndex > 0) && (!hasDistinct(sql)))
/*    */     {
/* 28 */       rownumber.append(sql.substring(orderByIndex));
/*    */     }
/* 30 */     rownumber.append(") as rownumber_,");
/* 31 */     return rownumber.toString();
/*    */   }
/*    */ 
/*    */   private static boolean hasDistinct(String sql)
/*    */   {
/* 36 */     return sql.toLowerCase().indexOf("select distinct") >= 0;
/*    */   }
/*    */ 
/*    */   public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*    */   {
/* 41 */     int startOfSelect = sql.toLowerCase().indexOf("select");
/* 42 */     StringBuffer pagingSelect = new StringBuffer(sql.length() + 100).append(sql.substring(0, startOfSelect)).append("select * from ( select ").append(getRowNumber(sql));
/*    */ 
/* 47 */     if (hasDistinct(sql))
/*    */     {
/* 49 */       pagingSelect.append(" row_.* from ( ").append(sql.substring(startOfSelect)).append(" ) as row_");
/*    */     }
/*    */     else
/*    */     {
/* 55 */       pagingSelect.append(sql.substring(startOfSelect + 6));
/*    */     }
/* 57 */     pagingSelect.append(" ) as temp_ where rownumber_ ");
/*    */ 
/* 59 */     if (offset > 0)
/*    */     {
/* 62 */       String endString = offsetPlaceholder + "+" + limitPlaceholder;
/* 63 */       pagingSelect.append("between " + offsetPlaceholder + "+1 and " + endString);
/*    */     }
/*    */     else
/*    */     {
/* 67 */       pagingSelect.append("<= " + limitPlaceholder);
/*    */     }
/* 69 */     return pagingSelect.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.DB2Dialect
 * JD-Core Version:    0.6.2
 */