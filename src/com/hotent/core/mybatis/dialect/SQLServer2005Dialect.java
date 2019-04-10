/*     */ package com.hotent.core.mybatis.dialect;
/*     */ 
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.datasource.DataSourceUtils;
/*     */ 
/*     */ public class SQLServer2005Dialect extends Dialect
/*     */ {
/*     */   public boolean supportsLimit()
/*     */   {
/*  28 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean supportsLimitOffset()
/*     */   {
/*  34 */     return true;
/*     */   }
/*     */ 
/*     */   public String getLimitString(String querySqlString, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
/*     */   {
/*  54 */     int start = offset + 1;
/*  55 */     StringBuffer pagingBuilder = new StringBuffer();
/*  56 */     querySqlString = querySqlString.toLowerCase().trim();
/*  57 */     String orderby = getOrderByPart(querySqlString);
/*  58 */     String distinctStr = "";
/*     */ 
/*  60 */     String sqlPartString = querySqlString;
/*  61 */     if (querySqlString.startsWith("select"))
/*     */     {
/*  63 */       if (sqlPartString.indexOf("distinct") != -1) {
/*  64 */         distinctStr = " DISTINCT ";
/*  65 */         sqlPartString = sqlPartString.replaceFirst("^select(\\s+(ALL|distinct))?", "");
/*     */       }
/*     */       else {
/*  68 */         sqlPartString = sqlPartString.replaceFirst("^select", "");
/*     */       }
/*     */     }
/*  71 */     pagingBuilder.append(sqlPartString);
/*     */ 
/*  74 */     String fields = getFields(querySqlString);
/*     */ 
/*  76 */     if ((orderby == null) || (orderby.length() == 0))
/*     */     {
/*  78 */       orderby = "ORDER BY CURRENT_TIMESTAMP";
/*     */     }
/*  80 */     StringBuffer result = new StringBuffer();
/*  81 */     result.append("WITH query AS (SELECT ").append("TOP 100 PERCENT ").append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder).append(") SELECT  " + distinctStr + fields + " FROM query WHERE __row_number__ BETWEEN ").append(start).append(" AND ").append(offset + limit);
/*     */ 
/*  87 */     return result.toString();
/*     */   }
/*     */ 
/*     */   static String getOrderByPart(String sql)
/*     */   {
/*  92 */     String loweredString = sql.toLowerCase();
/*  93 */     loweredString = loweredString.replaceAll("(?i)order\\s*by", "order by");
/*  94 */     int orderByIndex = loweredString.indexOf("order by");
/*  95 */     if (orderByIndex != -1)
/*     */     {
/*  99 */       return sql.substring(orderByIndex);
/*     */     }
/*     */ 
/* 103 */     return "";
/*     */   }
/*     */ 
/*     */   private static int getParameterCount(String sql)
/*     */   {
/* 109 */     Pattern regex = Pattern.compile("\\?", 66);
/* 110 */     int i = 0;
/* 111 */     Matcher regexMatcher = regex.matcher(sql);
/* 112 */     while (regexMatcher.find()) {
/* 113 */       i++;
/*     */     }
/* 115 */     return i;
/*     */   }
/*     */ 
/*     */   private static String getFields(String sql)
/*     */   {
/* 125 */     Connection conn = null;
/* 126 */     JdbcTemplate jdbcTemplate = null;
/*     */     try {
/* 128 */       sql = sql.replaceAll("where", "where 1=0 and ");
/* 129 */       int count = getParameterCount(sql);
/* 130 */       jdbcTemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
/*     */ 
/* 132 */       conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
				PreparedStatement ps=conn.prepareStatement(sql);
/* 133 */       ps = conn.prepareStatement(sql);
/* 134 */       for (int i = 1; i <= count; i++) {
/* 135 */         ps.setObject(1, null);
/*     */       }
/* 137 */       ResultSetMetaData rs = ps.getMetaData();
/* 138 */       int colCount = rs.getColumnCount();
/* 139 */       String str = "";
/* 140 */       for (int i = 1; i <= colCount; i++) {
/* 141 */         if (i == 1) {
/* 142 */           str = rs.getColumnName(i);
/*     */         }
/*     */         else {
/* 145 */           str = str + "," + rs.getColumnName(i);
/*     */         }
/*     */       }
/*     */ 
/* 149 */       return str;
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 152 */       return " * ";
/*     */     }
/*     */     finally {
/* 155 */       DataSourceUtils.releaseConnection(conn, jdbcTemplate.getDataSource());
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getCountSql(String sql)
/*     */   {
/* 164 */     String sqlCount = sql;
/*     */ 
/* 169 */     sqlCount = sqlCount.trim();
/* 170 */     Pattern pattern = Pattern.compile("^SELECT(\\s+(ALL|DISTINCT))?", 2);
/*     */ 
/* 172 */     Matcher matcher = pattern.matcher(sqlCount);
/* 173 */     if (matcher.find()) {
/* 174 */       String matStr = matcher.group();
/* 175 */       sqlCount = sqlCount.replaceFirst(matStr, matStr + " TOP 100 PERCENT");
/*     */     }
/*     */     else {
/* 178 */       throw new UnsupportedOperationException("SQL语句拼接出现错误！");
/*     */     }
/*     */ 
/* 181 */     sqlCount = "select count(*) amount from (" + sqlCount + ") A";
/* 182 */     return sqlCount;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.dialect.SQLServer2005Dialect
 * JD-Core Version:    0.6.2
 */