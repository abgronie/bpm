/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ public class DateFormatUtil
/*     */ {
/*  22 */   public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/*  27 */   public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/*  32 */   public static final DateFormat DATETIME_NOSECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/*     */ 
/*  37 */   public static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
/*     */ 
/*  42 */   public static final DateFormat TIME_NOSECOND_FORMAT = new SimpleDateFormat("HH:mm");
/*     */ 
/*  47 */   public static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
/*     */ 
/*     */   public static java.util.Date parse(String dateString)
/*     */     throws ParseException
/*     */   {
/*  63 */     if ((dateString.trim().indexOf(" ") > 0) && (dateString.trim().indexOf(".") > 0))
/*     */     {
/*  65 */       return new Timestamp(TIMESTAMP_FORMAT.parse(dateString).getTime());
/*     */     }
/*  67 */     if (dateString.trim().indexOf(" ") > 0)
/*     */     {
/*  69 */       if (dateString.trim().indexOf(":") != dateString.trim().lastIndexOf(":"))
/*     */       {
/*  71 */         return new Timestamp(DATETIME_FORMAT.parse(dateString).getTime());
/*     */       }
/*     */ 
/*  74 */       return new Timestamp(DATETIME_NOSECOND_FORMAT.parse(dateString).getTime());
/*     */     }
/*     */ 
/*  77 */     if (dateString.indexOf(":") > 0)
/*     */     {
/*  79 */       if (dateString.trim().indexOf(":") != dateString.trim().lastIndexOf(":"))
/*     */       {
/*  81 */         return new Time(TIME_FORMAT.parse(dateString).getTime());
/*     */       }
/*     */ 
/*  84 */       return new Time(TIME_NOSECOND_FORMAT.parse(dateString).getTime());
/*     */     }
/*     */ 
/*  88 */     return new java.sql.Date(DATE_FORMAT.parse(dateString).getTime());
/*     */   }
/*     */ 
/*     */   public static java.util.Date parseTime(String dateString) throws ParseException {
/*  92 */     if (dateString.trim().indexOf(" ") > 0) {
/*  93 */       String[] d = dateString.trim().split(" ");
/*  94 */       if (dateString.trim().indexOf(":") != dateString.trim().lastIndexOf(":"))
/*     */       {
/*  96 */         return new Timestamp(TIME_FORMAT.parse(d[1]).getTime());
/*     */       }
/*     */ 
/*  99 */       return new Timestamp(TIME_NOSECOND_FORMAT.parse(d[1]).getTime());
/*     */     }
/*     */ 
/* 102 */     if (dateString.indexOf(":") > 0)
/*     */     {
/* 104 */       if (dateString.trim().indexOf(":") != dateString.trim().lastIndexOf(":"))
/*     */       {
/* 106 */         return new Time(TIME_FORMAT.parse(dateString).getTime());
/*     */       }
/*     */ 
/* 109 */       return new Time(TIME_NOSECOND_FORMAT.parse(dateString).getTime());
/*     */     }
/*     */ 
/* 113 */     return new java.sql.Date(DATETIME_FORMAT.parse(dateString).getTime());
/*     */   }
/*     */ 
/*     */   public static String format(java.util.Date date)
/*     */   {
/* 125 */     if ((date instanceof Timestamp))
/* 126 */       return TIMESTAMP_FORMAT.format(date);
/* 127 */     if ((date instanceof Time))
/* 128 */       return TIME_FORMAT.format(date);
/* 129 */     if ((date instanceof java.sql.Date)) {
/* 130 */       return DATE_FORMAT.format(date);
/*     */     }
/* 132 */     return DATETIME_FORMAT.format(date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date parse(String dateString, String style)
/*     */     throws ParseException
/*     */   {
/* 147 */     DateFormat dateFormat = new SimpleDateFormat(style);
/* 148 */     return dateFormat.parse(dateString);
/*     */   }
/*     */ 
/*     */   public static String format(java.util.Date date, String style)
/*     */   {
/* 160 */     DateFormat dateFormat = new SimpleDateFormat(style);
/* 161 */     return dateFormat.format(date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date parseDate(String dateString)
/*     */     throws ParseException
/*     */   {
/* 172 */     return DATE_FORMAT.parse(dateString);
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date)
/*     */   {
/* 182 */     return DATE_FORMAT.format(date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date parseDateTime(String dateString)
/*     */     throws ParseException
/*     */   {
/* 193 */     return DATETIME_FORMAT.parse(dateString);
/*     */   }
/*     */ 
/*     */   public static String formaDatetTime(java.util.Date date)
/*     */   {
/* 203 */     return DATETIME_FORMAT.format(date);
/*     */   }
/*     */ 
/*     */   public static String formatTimeNoSecond(java.util.Date date)
/*     */   {
/* 213 */     return DATETIME_NOSECOND_FORMAT.format(date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date parseTimeNoSecond(String dateString)
/*     */     throws ParseException
/*     */   {
/* 225 */     return DATETIME_NOSECOND_FORMAT.parse(dateString);
/*     */   }
/*     */ 
/*     */   public static String getNowByString(String style)
/*     */   {
/* 235 */     if ((null == style) || ("".equals(style))) {
/* 236 */       style = "yyyy-MM-dd HH:mm:ss";
/*     */     }
/* 238 */     return format(new java.util.Date(), style);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.DateFormatUtil
 * JD-Core Version:    0.6.2
 */