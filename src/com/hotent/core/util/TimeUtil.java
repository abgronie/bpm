/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class TimeUtil
/*     */ {
/*     */   public static final int SECOND = 0;
/*     */   public static final int MINUTE = 1;
/*     */   public static final int HOUR = 2;
/*     */   public static final int DAY = 3;
/*     */   public static final int MONTH = 4;
/*     */
/*     */   public static Date convertString(String value, String format)
/*     */   {
/*  29 */     SimpleDateFormat sdf = new SimpleDateFormat(format);
/*  30 */     if (value == null)
/*  31 */       return null;
/*     */     try {
/*  33 */       return sdf.parse(value); } catch (Exception e) {
/*     */     }
/*  35 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date convertString(String value)
/*     */   {
/*  46 */     return convertString(value, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static Date convertDateString(String value)
/*     */   {
/*  55 */     return convertString(value, "yyyy-MM-dd");
/*     */   }
/*     */ 
/*     */   public static String getCurrentTime()
/*     */   {
/*  64 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  65 */     Date date = new Date();
/*  66 */     return formatter.format(date);
/*     */   }
/*     */ 
/*     */   public static String getDateTimeString(long millseconds)
/*     */   {
/*  77 */     return getDate(millseconds, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static String getDayDate(long time)
/*     */   {
/*  87 */     return getDate(time, "yyyyMMdd");
/*     */   }
/*     */ 
/*     */   public static String getDate(long time, String format) {
/*  91 */     SimpleDateFormat formater = new SimpleDateFormat(format);
/*  92 */     return formater.format(new Date(time));
/*     */   }
/*     */ 
/*     */   public static String getDateTimeString(Date date)
/*     */   {
/* 102 */     return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
/*     */   }
/*     */ 
/*     */   public static String getDateTimeString(Date date, String format)
/*     */   {
/* 113 */     return new SimpleDateFormat(format).format(date);
/*     */   }
/*     */ 
/*     */   public static boolean isTimeLarge(String startTime, String endTime)
/*     */   {
/*     */     try
/*     */     {
/* 125 */       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/* 127 */       ParsePosition pos = new ParsePosition(0);
/* 128 */       ParsePosition pos1 = new ParsePosition(0);
/* 129 */       Date dt1 = formatter.parse(startTime, pos);
/* 130 */       Date dt2 = formatter.parse(endTime, pos1);
/* 131 */       long lDiff = dt2.getTime() - dt1.getTime();
/* 132 */       return lDiff > 0L;
/*     */     } catch (Exception e) {
/*     */     }
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public static long getTime(Date startTime, Date endTime)
/*     */   {
/* 140 */     return endTime.getTime() - startTime.getTime();
/*     */   }
/*     */ 
/*     */   public static String getTimeDiff(String startTime, String endTime)
/*     */   {
/*     */     try
/*     */     {
/* 152 */       String tmp = "";
/* 153 */       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/* 155 */       ParsePosition pos = new ParsePosition(0);
/* 156 */       ParsePosition pos1 = new ParsePosition(0);
/* 157 */       Date dt1 = formatter.parse(startTime, pos);
/* 158 */       Date dt2 = formatter.parse(endTime, pos1);
/* 159 */       long lDiff = dt2.getTime() - dt1.getTime();
/* 160 */       int days = (int)(lDiff / 86400000L);
/* 161 */       if (days > 0) {
/* 162 */         lDiff -= days * 1000 * 60 * 60 * 24;
/* 163 */         tmp = tmp + days + "天";
/*     */       }
/* 165 */       int hours = (int)(lDiff / 3600000L);
/* 166 */       if (hours > 0)
/* 167 */         lDiff -= hours * 1000 * 60 * 60;
/* 168 */       tmp = tmp + hours + "小时";
/* 169 */       int minute = (int)(lDiff / 60000L);
/* 170 */       return tmp + minute + "分钟";
/*     */     }
/*     */     catch (Exception e) {
/* 173 */       e.printStackTrace();
/* 174 */     }return "-1";
/*     */   }
/*     */ 
/*     */   public static String getTime1(Long millseconds)
/*     */   {
/* 185 */     String time = "";
/* 186 */     if (millseconds == null) {
/* 187 */       return "";
/*     */     }
/* 189 */     int days = (int)millseconds.longValue() / 1000 / 60 / 60 / 24;
/* 190 */     if (days > 0) {
/* 191 */       time = time + days + "天";
/*     */     }
/* 193 */     long hourMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24;
/* 194 */     int hours = (int)hourMillseconds / 1000 / 60 / 60;
/* 195 */     if (hours > 0) {
/* 196 */       time = time + hours + "小时";
/*     */     }
/* 198 */     long minuteMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24 - hours * 1000 * 60 * 60;
/*     */ 
/* 200 */     int minutes = (int)minuteMillseconds / 1000 / 60;
/* 201 */     if (minutes > 0) {
/* 202 */       time = time + minutes + "分钟";
/*     */     }
/* 204 */     return time;
/*     */   }
/*     */

            public static String getTime2(Long millseconds) {
                String time = "";
                if (millseconds == null) {
                    return "";
                }
                long days =  millseconds / 1000 / 60 / 60 / 24;
                if (days > 0) {
                    time += days + "天";
                }
                long hourMillseconds = millseconds - days * 1000 * 60 * 60 * 24;
                long hours =  hourMillseconds / 1000 / 60 / 60;
                if (hours > 0) {
                    time += hours + "小时";
                }
                long minuteMillseconds = millseconds - days * 1000 * 60 * 60 * 24
                        - hours * 1000 * 60 * 60;
                long minutes =  minuteMillseconds / 1000 / 60;
                if (minutes > 0) {
                    time += minutes + "分钟";
                }

                if(time.length() == 0){
                    time = "1分钟内";
                }
                return time;
            }

            /**
             * 时间计算
             *
             * @author byq
             * @param millseconds
             * @return
             */
            public static String getTime(Long millseconds){
                Integer ss = 1000;
                Integer mi = ss * 60;
                Integer hh = mi * 60;
                Integer dd = hh * 24;

                Long day = millseconds / dd;
                Long hour = (millseconds - day * dd) / hh;
                Long minute = (millseconds - day * dd - hour * hh) / mi;
                Long second = (millseconds - day * dd - hour * hh - minute * mi) / ss;
                Long milliSecond = millseconds - day * dd - hour * hh - minute * mi - second * ss;

                StringBuffer sb = new StringBuffer();
                if(day > 0) {
                    sb.append(day+"天");
                }
                if(hour > 0) {
                    sb.append(hour+"小时");
                }
                if(minute > 0) {
                    sb.append(minute+"分钟");
                }
                if(second > 0) {
                    sb.append(second+"秒");
                }
                /*if(milliSecond > 0) {
                    sb.append(milliSecond+"毫秒");
                }*/
                return sb.toString();
            }


/*     */   public static String getDateString(Date date)
/*     */   {
/* 214 */     if (date != null) {
/* 215 */       return new SimpleDateFormat("yyyy-MM-dd").format(date);
/*     */     }
/* 217 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate(String format) {
/* 221 */     SimpleDateFormat formatter = new SimpleDateFormat(format);
/* 222 */     Date date = new Date();
/* 223 */     return formatter.format(date);
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate()
/*     */   {
/* 232 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
/* 233 */     Date date = new Date();
/* 234 */     return formatter.format(date);
/*     */   }
/*     */ 
/*     */   public static String getDateString(long millseconds)
/*     */   {
/* 244 */     return new SimpleDateFormat("yyyy-MM-dd").format(new Date(millseconds));
/*     */   }
/*     */ 
/*     */   public static String getDateString(String formater)
/*     */   {
/* 255 */     return new SimpleDateFormat(formater).format(new Date());
/*     */   }
/*     */ 
/*     */   public static long getMillsByToday()
/*     */   {
/* 264 */     String str = getDateString("yyyy-MM-dd");
/* 265 */     str = String.valueOf(getMillsByDateString(str));
/* 266 */     return Long.parseLong(str.substring(0, str.length() - 3) + "000");
/*     */   }
/*     */ 
/*     */   public static long getNextDays(int days)
/*     */   {
/* 276 */     Calendar cal = Calendar.getInstance();
/* 277 */     cal.add(5, days);
/* 278 */     String str = String.valueOf(cal.getTimeInMillis());
/* 279 */     return Long.parseLong(str.substring(0, str.length() - 3) + "000");
/*     */   }
/*     */ 
/*     */   public static Date getNextDays(Date date, int days)
/*     */   {
/* 290 */     Calendar cal = Calendar.getInstance();
/* 291 */     cal.setTime(date);
/* 292 */     cal.add(5, days);
/* 293 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static long getMillsByDateString(String strDate)
/*     */   {
/* 304 */     Calendar cal = Calendar.getInstance();
/* 305 */     if ((strDate != null) && (strDate.trim().length() > 9)) {
/* 306 */       strDate = strDate.trim();
/*     */       try {
/* 308 */         int year = Integer.parseInt(strDate.substring(0, 4));
/* 309 */         int month = Integer.parseInt(strDate.substring(5, 7)) - 1;
/* 310 */         int date = Integer.parseInt(strDate.substring(8, 10));
/* 311 */         cal.set(year, month, date, 0, 0, 0);
/* 312 */         String str = String.valueOf(cal.getTimeInMillis());
/* 313 */         return Long.parseLong(str.substring(0, str.length() - 3) + "000");
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 321 */     return 0L;
/*     */   }
/*     */ 
/*     */   public static long getMillsByDateTimeString(String strDateTime)
/*     */   {
/* 332 */     Calendar cal = Calendar.getInstance();
/* 333 */     if ((strDateTime != null) && (strDateTime.trim().length() > 18)) {
/* 334 */       strDateTime = strDateTime.trim();
/*     */       try {
/* 336 */         int year = Integer.parseInt(strDateTime.substring(0, 4));
/* 337 */         int month = Integer.parseInt(strDateTime.substring(5, 7)) - 1;
/* 338 */         int date = Integer.parseInt(strDateTime.substring(8, 10));
/* 339 */         int hour = Integer.parseInt(strDateTime.substring(11, 13));
/* 340 */         int minute = Integer.parseInt(strDateTime.substring(14, 16));
/* 341 */         int second = Integer.parseInt(strDateTime.substring(17, 19));
/* 342 */         cal.set(year, month, date, hour, minute, second);
/* 343 */         return cal.getTimeInMillis();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */     }
/* 349 */     return 0L;
/*     */   }
/*     */ 
/*     */   public static String getFormatString(long millseconds, String format)
/*     */   {
/* 359 */     if ((format == null) || (format.trim().length() == 0)) {
/* 360 */       format = "yyyy-MM-dd";
/*     */     }
/* 362 */     format = format.trim();
/* 363 */     return new SimpleDateFormat(format).format(new Date(millseconds));
/*     */   }
/*     */ 
/*     */   public static long getCurrentTimeMillis()
/*     */   {
/* 373 */     Calendar c = Calendar.getInstance();
/* 374 */     return c.getTimeInMillis();
/*     */   }
/*     */ 
/*     */   public static String getTimeByMills(long mills)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 386 */       if (mills == 0L)
/* 387 */         return "-";
/* 388 */       Date date = null;
/* 389 */       Calendar ca = Calendar.getInstance();
/* 390 */       ca.setTimeInMillis(mills);
/* 391 */       date = ca.getTime();
/*     */       SimpleDateFormat myformat;
/* 394 */       if ((ca.get(11) == 0) && (ca.get(12) == 0) && (ca.get(13) == 0))
/*     */       {
/* 396 */         myformat = new SimpleDateFormat("yyyy-MM-dd");
/*     */       }
/* 398 */       else myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/* 400 */       return myformat.format(date); } catch (Exception e) {
/*     */     }
/* 402 */     return "-";
/*     */   }
/*     */ 
/*     */   public static String formatDate(long mills)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 415 */       if (mills == 0L)
/* 416 */         return "-";
/* 417 */       Date date = null;
/* 418 */       Calendar ca = Calendar.getInstance();
/* 419 */       ca.setTimeInMillis(mills);
/* 420 */       date = ca.getTime();
/*     */ 
/* 423 */       SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/* 425 */       return myformat.format(date); } catch (Exception e) {
/*     */     }
/* 427 */     return "-";
/*     */   }
/*     */ 
/*     */   public static String formatTime(long mills)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 440 */       if (mills == 0L)
/* 441 */         return "-";
/* 442 */       Date date = null;
/* 443 */       Calendar ca = Calendar.getInstance();
/* 444 */       ca.setTimeInMillis(mills);
/* 445 */       date = ca.getTime();
/*     */ 
/* 448 */       SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/* 450 */       return myformat.format(date); } catch (Exception e) {
/*     */     }
/* 452 */     return "-";
/*     */   }
/*     */ 
/*     */   public static long getMillsByTime(String strTime)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 466 */       if ((strTime.length() != 19) && (strTime.length() != 10)) {
/* 467 */         throw new Exception("the time string is wrong.");
/*     */       }
/*     */ 
/* 470 */       int year = Integer.parseInt(strTime.substring(0, 4));
/* 471 */       int month = Integer.parseInt(strTime.substring(5, 7)) - 1;
/* 472 */       int day = Integer.parseInt(strTime.substring(8, 10));
/*     */ 
/* 474 */       if ((year < 1000) || (year > 3000)) {
/* 475 */         throw new Exception("the year is wrong.");
/*     */       }
/*     */ 
/* 478 */       if ((month < 0) || (month > 12)) {
/* 479 */         throw new Exception("the month is wrong.");
/*     */       }
/*     */ 
/* 482 */       if ((day < 1) || (day > 31)) {
/* 483 */         throw new Exception("the day is wrong");
/*     */       }
/*     */ 
/* 486 */       Calendar ca = Calendar.getInstance();
/* 487 */       if (strTime.length() == 19) {
/* 488 */         int hour = Integer.parseInt(strTime.substring(11, 13));
/* 489 */         int minute = Integer.parseInt(strTime.substring(14, 16));
/* 490 */         int second = Integer.parseInt(strTime.substring(17, 19));
/*     */ 
/* 492 */         if ((hour < 0) || (hour > 24)) {
/* 493 */           throw new Exception("the hour is wrong.");
/*     */         }
/*     */ 
/* 496 */         if ((minute < 0) || (minute > 60)) {
/* 497 */           throw new Exception("the minute is wrong.");
/*     */         }
/*     */ 
/* 500 */         if ((second < 0) || (second > 60)) {
/* 501 */           throw new Exception("the second is wrong.");
/*     */         }
/*     */ 
/* 504 */         ca.set(year, month, day, hour, minute, second);
/* 505 */       } else if (strTime.length() == 10) {
/* 506 */         ca.set(year, month, day, 0, 0, 0);
/*     */       }
/* 508 */       return ca.getTimeInMillis();
/*     */     } catch (Exception e) {
/* 510 */       e.printStackTrace();
/* 511 */     }return -1L;
/*     */   }
/*     */ 
/*     */   public static long getNextTime(int timeUnit, int interval, long timeMill)
/*     */   {
/* 526 */     Calendar ca = Calendar.getInstance();
/* 527 */     ca.setTimeInMillis(timeMill);
/* 528 */     switch (timeUnit) {
/*     */     case 0:
/* 530 */       ca.add(13, interval);
/* 531 */       break;
/*     */     case 1:
/* 533 */       ca.add(12, interval);
/* 534 */       break;
/*     */     case 2:
/* 536 */       ca.add(10, interval);
/* 537 */       break;
/*     */     case 3:
/* 539 */       ca.add(5, interval);
/* 540 */       break;
/*     */     case 4:
/* 542 */       ca.add(2, interval);
/* 543 */       break;
/*     */     default:
/* 545 */       return 0L;
/*     */     }
/* 547 */     return ca.getTimeInMillis();
/*     */   }
/*     */ 
/*     */   public static Date getDateTimeByTimeString(String timeString)
/*     */   {
/* 557 */     Date date = new Date();
/*     */     try {
/* 559 */       date = DateFormatUtil.parse(timeString, "yy-MM-dd HH:mm:ss");
/*     */     } catch (ParseException e) {
/* 561 */       e.printStackTrace();
/*     */     }
/* 563 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date getDateByDateString(String timeString)
/*     */   {
/* 573 */     Date date = new Date();
/*     */     try {
/* 575 */       date = DateFormatUtil.parse(timeString, "yy-MM-dd");
/*     */     } catch (ParseException e) {
/* 577 */       e.printStackTrace();
/*     */     }
/* 579 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date getDate(int year, int month, int date)
/*     */   {
/* 591 */     return getDate(year, month, date, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second)
/*     */   {
/* 606 */     Calendar cal = Calendar.getInstance();
/* 607 */     cal.set(year, month - 1, date, hourOfDay, minute, second);
/* 608 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static int getSecondDiff(Date endTime, Date startTime)
/*     */   {
/* 621 */     long start = startTime.getTime();
/* 622 */     long end = endTime.getTime();
/* 623 */     return (int)((end - start) / 1000L);
/*     */   }
/*     */ 
/*     */   public static int getDaysOfMonth(int year, int mon)
/*     */   {
/* 636 */     Calendar cal = Calendar.getInstance();
/* 637 */     cal.set(1, year);
/* 638 */     cal.set(2, mon - 1);
/* 639 */     return cal.getActualMaximum(5);
/*     */   }
/*     */ 
/*     */   public static int getWeekDayOfMonth(int year, int mon)
/*     */   {
/* 652 */     Calendar cal = Calendar.getInstance();
/* 653 */     cal.set(year, mon - 1, 1);
/* 654 */     return cal.get(7);
/*     */   }
/*     */ 
/*     */   public static String getDurationTime(Date time)
/*     */   {
/* 664 */     if (BeanUtils.isEmpty(time))
/* 665 */       return "";
/* 666 */     Long millseconds = Long.valueOf(getTime(time, new Date()));
/* 667 */     return getTime(millseconds);
/*     */   }


    public static void main(String[] args) {
        System.out.println(getTime(2313121589L));
    }

/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.TimeUtil
 * JD-Core Version:    0.6.2
 */