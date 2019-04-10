/*     */ package com.hotent.core.util;
/*     */ 
/*     */ public class SqlDateFormat
/*     */ {
/*     */   public static String convertDateFormat(String format, String value, String dbType)
/*     */   {
/*  20 */     if ("oracle".equals(dbType)) {
/*  21 */       return convertToOracle(format, value);
/*     */     }
/*  23 */     if ("mysql".equals(dbType)) {
/*  24 */       return convertToMySql(format, value);
/*     */     }
/*  26 */     if ("mssql".equals(dbType)) {
/*  27 */       return convertToMsSql(format, value);
/*     */     }
/*  29 */     return value;
/*     */   }
/*     */ 
/*     */   private static String convertToOracle(String format, String value)
/*     */   {
/*  39 */     if (StringUtil.isEmpty(format)) {
/*  40 */       format = "yyyy-MM-dd";
/*     */     }
/*  42 */     format = format.replace("HH", "hh24");
/*  43 */     format = format.replace("mm", "mi");
/*  44 */     String rtn = " TO_DATE('" + value + "','" + format + "')";
/*     */ 
/*  46 */     return rtn;
/*     */   }
/*     */ 
/*     */   private static String convertToMySql(String format, String value)
/*     */   {
/*  82 */     if (StringUtil.isEmail(format)) {
/*  83 */       format = "%Y-%m-%d";
/*     */     }
/*  85 */     format = format.replace("yyyy", "%Y");
/*  86 */     format = format.replace("MM", "%m");
/*  87 */     format = format.replace("dd", "%d");
/*  88 */     format = format.replace("HH", "%H");
/*  89 */     format = format.replace("hh", "%h");
/*  90 */     format = format.replace("mm", "%i");
/*  91 */     format = format.replace("ss", "%s");
/*  92 */     String rtn = " STR_TO_DATE('" + value + "','" + format + "')";
/*     */ 
/*  94 */     return rtn;
/*     */   }
/*     */ 
/*     */   private static String convertToMsSql(String format, String value)
/*     */   {
/* 104 */     String rtn = " cast('" + value + "' as datetime) ";
/* 105 */     return rtn;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.SqlDateFormat
 * JD-Core Version:    0.6.2
 */