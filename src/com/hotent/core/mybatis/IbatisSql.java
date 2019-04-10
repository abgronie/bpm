/*    */ package com.hotent.core.mybatis;
/*    */ 
/*    */ import com.hotent.core.table.ITableOperator;
/*    */ import com.hotent.core.util.AppUtil;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class IbatisSql
/*    */ {
/*    */   private String sql;
/*    */   private Object[] parameters;
/*    */   private Class resultClass;
/*    */ 
/*    */   public Class getResultClass()
/*    */   {
/* 16 */     return this.resultClass;
/*    */   }
/*    */ 
/*    */   public void setResultClass(Class resultClass) {
/* 20 */     this.resultClass = resultClass;
/*    */   }
/*    */ 
/*    */   public void setSql(String sql) {
/* 24 */     this.sql = sql;
/*    */   }
/*    */ 
/*    */   public String getSql() {
/* 28 */     return this.sql;
/*    */   }
/*    */ 
/*    */   public void setParameters(Object[] parameters) {
/* 32 */     this.parameters = parameters;
/*    */   }
/*    */ 
/*    */   public Object[] getParameters() {
/* 36 */     return this.parameters;
/*    */   }
/*    */ 
/*    */   public String getCountSql()
/*    */   {
/* 45 */     String sqlCount = this.sql;
/* 46 */     ITableOperator tableOperator = (ITableOperator)AppUtil.getBean(ITableOperator.class);
/*    */ 
/* 48 */     if (tableOperator.getDbType().equals("mssql")) {
/* 49 */       sqlCount = sqlCount.trim();
/* 50 */       Pattern pattern = Pattern.compile("^SELECT(\\s+(ALL|DISTINCT))?", 2);
/*    */ 
/* 52 */       Matcher matcher = pattern.matcher(sqlCount);
/* 53 */       if (matcher.find()) {
/* 54 */         String matStr = matcher.group();
/* 55 */         sqlCount = sqlCount.toUpperCase().replaceFirst(matStr.toUpperCase(), matStr.toUpperCase() + " TOP 100 PERCENT");
/*    */       }
/*    */       else {
/* 58 */         throw new UnsupportedOperationException("SQL语句拼接出现错误！");
/*    */       }
/*    */     }
/* 61 */     sqlCount = "select count(*) amount from (" + sqlCount + ") A";
/* 62 */     return sqlCount;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.IbatisSql
 * JD-Core Version:    0.6.2
 */