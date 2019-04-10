/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
import java.util.TimeZone;

/*     */ import org.apache.commons.lang.time.DateUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DateUtil
/*     */ {
/*  18 */   private static final Log logger = LogFactory.getLog(DateUtil.class);
/*     */ 
/*     */   public static Calendar setStartDay(Calendar cal)
/*     */   {
/*  27 */     cal.set(11, 0);
/*  28 */     cal.set(12, 0);
/*  29 */     cal.set(13, 0);
/*  30 */     return cal;
/*     */   }
/*     */ 
/*     */   public static Calendar setEndDay(Calendar cal) {
/*  34 */     cal.set(11, 23);
/*  35 */     cal.set(12, 59);
/*  36 */     cal.set(13, 59);
/*  37 */     return cal;
/*     */   }
/*     */ 
/*     */   public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal)
/*     */   {
/*  47 */     destCal.set(1, sourceCal.get(1));
/*  48 */     destCal.set(2, sourceCal.get(2));
/*  49 */     destCal.set(5, sourceCal.get(5));
/*     */   }
/*     */ 
/*     */   public static String formatEnDate(Date date)
/*     */   {
/*  58 */     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
/*     */ 
/*  60 */     return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
/*     */   }
/*     */ 
/*     */   public static Date parseDate(String dateString) {
/*  64 */     Date date = null;
/*     */     try {
/*  66 */       date = DateUtils.parseDate(dateString, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "HH:mm:ss", "HH:mm", "yyyy-MM-dd HH:mm:ss.SSS" });
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*  74 */       logger.error(new StringBuilder().append("Pase the Date(").append(dateString).append(") occur errors:").append(ex.getMessage()).toString());
/*     */     }
/*     */ 
/*  77 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date addDay(Date date, int day)
/*     */   {
/*  88 */     Calendar ca = Calendar.getInstance();
/*  89 */     ca.setTime(date);
/*  90 */     ca.add(5, day);
/*  91 */     return ca.getTime();
/*     */   }
/*     */ 
/*     */   public static Date addOneDay(Date date)
/*     */   {
/* 102 */     return addDay(date, 1);
/*     */   }
/*     */ 
/*     */   public static String addOneDay(String date)
/*     */   {
/* 112 */     DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 113 */     Calendar calendar = Calendar.getInstance();
/*     */     try {
/* 115 */       Date dd = format.parse(date);
/* 116 */       calendar.setTime(dd);
/* 117 */       calendar.add(5, 1);
/*     */     } catch (ParseException e) {
/* 119 */       e.printStackTrace();
/*     */     }
/* 121 */     String tmpDate = format.format(calendar.getTime());
/* 122 */     return new StringBuilder().append(tmpDate.substring(5, 7)).append("/").append(tmpDate.substring(8, 10)).append("/").append(tmpDate.substring(0, 4)).toString();
/*     */   }
/*     */ 
/*     */   public static String addOneHour(String date)
/*     */   {
/* 134 */     String amPm = date.substring(20, 22);
/*     */ 
/* 136 */     DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/* 138 */     Calendar calendar = Calendar.getInstance();
/*     */ 
/* 140 */     int hour = Integer.parseInt(date.substring(11, 13));
/*     */     try {
/* 142 */       if (amPm.equals("PM")) {
/* 143 */         hour += 12;
/*     */       }
/* 145 */       date = new StringBuilder().append(date.substring(0, 11)).append(hour >= 10 ? Integer.valueOf(hour) : new StringBuilder().append("0").append(hour).toString()).append(date.substring(13, 19)).toString();
/*     */ 
/* 147 */       Date dd = format.parse(date);
/* 148 */       calendar.setTime(dd);
/* 149 */       calendar.add(11, 1);
/*     */     } catch (ParseException e) {
/* 151 */       e.printStackTrace();
/*     */     }
/* 153 */     String tmpDate = format.format(calendar.getTime());
/*     */ 
/* 155 */     hour = Integer.parseInt(tmpDate.substring(11, 13));
/* 156 */     amPm = (hour >= 12) && (hour != 0) ? "PM" : "AM";
/* 157 */     if (amPm.equals("PM")) {
/* 158 */       hour -= 12;
/*     */     }
/* 160 */     tmpDate = new StringBuilder().append(tmpDate.substring(5, 7)).append("/").append(tmpDate.substring(8, 10)).append("/").append(tmpDate.substring(0, 4)).append(" ").append(hour >= 10 ? Integer.valueOf(hour) : new StringBuilder().append("0").append(hour).toString()).append(tmpDate.substring(13, tmpDate.length())).append(" ").append(amPm).toString();
/*     */ 
/* 165 */     return tmpDate;
/*     */   }
/*     */ 
/*     */   public static String timeStrToDateStr(String timeStr)
/*     */   {
/* 177 */     String dateStr = new StringBuilder().append(timeStr.substring(24, 28)).append("-").toString();
/*     */ 
/* 179 */     String mon = timeStr.substring(4, 7);
/* 180 */     if (mon.equals("Jan"))
/* 181 */       dateStr = new StringBuilder().append(dateStr).append("01").toString();
/* 182 */     else if (mon.equals("Feb"))
/* 183 */       dateStr = new StringBuilder().append(dateStr).append("02").toString();
/* 184 */     else if (mon.equals("Mar"))
/* 185 */       dateStr = new StringBuilder().append(dateStr).append("03").toString();
/* 186 */     else if (mon.equals("Apr"))
/* 187 */       dateStr = new StringBuilder().append(dateStr).append("04").toString();
/* 188 */     else if (mon.equals("May"))
/* 189 */       dateStr = new StringBuilder().append(dateStr).append("05").toString();
/* 190 */     else if (mon.equals("Jun"))
/* 191 */       dateStr = new StringBuilder().append(dateStr).append("06").toString();
/* 192 */     else if (mon.equals("Jul"))
/* 193 */       dateStr = new StringBuilder().append(dateStr).append("07").toString();
/* 194 */     else if (mon.equals("Aug"))
/* 195 */       dateStr = new StringBuilder().append(dateStr).append("08").toString();
/* 196 */     else if (mon.equals("Sep"))
/* 197 */       dateStr = new StringBuilder().append(dateStr).append("09").toString();
/* 198 */     else if (mon.equals("Oct"))
/* 199 */       dateStr = new StringBuilder().append(dateStr).append("10").toString();
/* 200 */     else if (mon.equals("Nov"))
/* 201 */       dateStr = new StringBuilder().append(dateStr).append("11").toString();
/* 202 */     else if (mon.equals("Dec")) {
/* 203 */       dateStr = new StringBuilder().append(dateStr).append("12").toString();
/*     */     }
/*     */ 
/* 206 */     dateStr = new StringBuilder().append(dateStr).append("-").append(timeStr.substring(8, 10)).toString();
/*     */ 
/* 208 */     return dateStr;
/*     */   }
/*     */ 
/*     */   public static int getExtraDayOfWeek(String sDate)
/*     */   {
/*     */     try
/*     */     {
/* 219 */       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/* 221 */       Date date = format.parse(sDate);
/* 222 */       String weekday = date.toString().substring(0, 3);
/* 223 */       if (weekday.equals("Mon"))
/* 224 */         return 1;
/* 225 */       if (weekday.equals("Tue"))
/* 226 */         return 2;
/* 227 */       if (weekday.equals("Wed"))
/* 228 */         return 3;
/* 229 */       if (weekday.equals("Thu"))
/* 230 */         return 4;
/* 231 */       if (weekday.equals("Fri"))
/* 232 */         return 5;
/* 233 */       if (weekday.equals("Sat")) {
/* 234 */         return 6;
/*     */       }
/* 236 */       return 0;
/*     */     }
/*     */     catch (Exception ex) {
/*     */     }
/* 240 */     return 0;
/*     */   }
/*     */ 
/*     */   public static String getDateWeekDay(String sDate)
/*     */   {
/*     */     try
/*     */     {
/* 252 */       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/* 254 */       Date date = format.parse(sDate);
/* 255 */       return date.toString().substring(0, 3);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*     */     }
/* 260 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<String> getUpDownFiveYear(Calendar cal)
/*     */   {
/* 271 */     List yearlist = new ArrayList();
/*     */ 
/* 273 */     int curyear = cal.get(1);
/* 274 */     yearlist.add(String.valueOf(curyear - 2));
/* 275 */     yearlist.add(String.valueOf(curyear - 1));
/* 276 */     yearlist.add(String.valueOf(curyear));
/* 277 */     yearlist.add(String.valueOf(curyear + 1));
/* 278 */     yearlist.add(String.valueOf(curyear + 2));
/*     */ 
/* 280 */     return yearlist;
/*     */   }
/*     */ 
/*     */   public static List<String> getTwelveMonth()
/*     */   {
/* 290 */     List monthlist = new ArrayList();
/*     */ 
/* 292 */     for (int idx = 1; idx <= 12; idx++) {
/* 293 */       monthlist.add(String.valueOf(idx));
/*     */     }
/*     */ 
/* 296 */     return monthlist;
/*     */   }
/*     */ 
/*     */   public static String[] getDaysBetweenDate(Date startTime, Date endTime)
/*     */   {
/* 308 */     String[] dateArr = null;
/*     */     try {
/* 310 */       Integer day = Integer.valueOf(daysBetween(startTime, endTime));
/*     */ 
/* 312 */       dateArr = new String[day.intValue() + 1];
/* 313 */       for (int i = 0; i < dateArr.length; i++)
/* 314 */         if (i == 0) {
/* 315 */           dateArr[i] = DateFormatUtil.formatDate(startTime);
/*     */         } else {
/* 317 */           Calendar calendar = Calendar.getInstance();
/* 318 */           calendar.setTime(startTime);
/* 319 */           calendar.add(5, i);
/* 320 */           dateArr[i] = DateFormatUtil.formatDate(calendar.getTime());
/*     */         }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 325 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 328 */     return dateArr;
/*     */   }
/*     */ 
/*     */   public static String[] getDaysBetweenDate(String startTime, String endTime)
/*     */   {
/* 342 */     String[] dateArr = null;
/*     */     try
/*     */     {
/* 345 */       String stime = timeStrToDateStr(startTime);
/* 346 */       String etime = timeStrToDateStr(endTime);
/*     */ 
/* 349 */       Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(stime);
/*     */ 
/* 351 */       Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(etime);
/*     */ 
/* 354 */       long day = (date1.getTime() - date2.getTime()) / 86400000L > 0L ? (date1.getTime() - date2.getTime()) / 86400000L : (date2.getTime() - date1.getTime()) / 86400000L;
/*     */ 
/* 360 */       dateArr = new String[Integer.valueOf(String.valueOf(day + 1L)).intValue()];
/* 361 */       for (int idx = 0; idx < dateArr.length; idx++)
/* 362 */         if (idx == 0) {
/* 363 */           dateArr[idx] = stime;
/*     */         } else {
/* 365 */           stime = addOneDay(stime);
/* 366 */           stime = new StringBuilder().append(stime.substring(6, 10)).append("-").append(stime.substring(0, 2)).append("-").append(stime.substring(3, 5)).toString();
/*     */ 
/* 369 */           dateArr[idx] = stime;
/*     */         }
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 374 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 377 */     return dateArr;
/*     */   }
/*     */ 
/*     */   public static Date getMonthStartDate(Date date)
/*     */   {
/* 387 */     Calendar calendar = Calendar.getInstance();
/* 388 */     calendar.setTime(date);
/*     */ 
/* 390 */     int dayOfMonth = calendar.get(5);
/*     */ 
/* 392 */     calendar.add(5, -(dayOfMonth - 1));
/* 393 */     Date firstDateOfMonth = calendar.getTime();
/* 394 */     return firstDateOfMonth;
/*     */   }
/*     */ 
/*     */   public static Date getMonthEndDate(Date date)
/*     */   {
/* 404 */     Calendar calendar = Calendar.getInstance();
/* 405 */     calendar.setTime(date);
/*     */ 
/* 407 */     int dayOfMonth = calendar.get(5);
/*     */ 
/* 409 */     calendar.add(5, calendar.getActualMaximum(5) - dayOfMonth);
/*     */ 
/* 411 */     Date lastDateOfMonth = calendar.getTime();
/* 412 */     return lastDateOfMonth;
/*     */   }
/*     */ 
/*     */   public static Date getMonthStartDateTime(Date date)
/*     */   {
/* 422 */     Date firstDateOfMonth = getMonthStartDate(date);
/* 423 */     Calendar calendar = Calendar.getInstance();
/* 424 */     calendar.setTime(firstDateOfMonth);
/* 425 */     calendar = setStartDay(calendar);
/* 426 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getMonthEndDateTime(Date date)
/*     */   {
/* 436 */     Date endDateOfMonth = getMonthEndDate(date);
/* 437 */     Calendar calendar = Calendar.getInstance();
/* 438 */     calendar.setTime(endDateOfMonth);
/* 439 */     calendar = setEndDay(calendar);
/* 440 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getWeekStartDate(Date date)
/*     */   {
/* 450 */     Calendar calendar = Calendar.getInstance();
/* 451 */     calendar.setTime(date);
/* 452 */     calendar.set(7, 2);
/* 453 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getWeekEndDate(Date date)
/*     */   {
/* 463 */     Calendar calendar = Calendar.getInstance();
/* 464 */     calendar.setTime(date);
/*     */ 
/* 466 */     calendar.set(7, 1);
/*     */ 
/* 468 */     calendar.add(3, 1);
/* 469 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getWeekStartDateTime(Date date)
/*     */   {
/* 479 */     Calendar calendar = Calendar.getInstance();
/* 480 */     calendar.setTime(date);
/* 481 */     calendar.set(7, 2);
/* 482 */     calendar = setStartDay(calendar);
/* 483 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getWeekEndDateTime(Date date)
/*     */   {
/* 493 */     Calendar calendar = Calendar.getInstance();
/* 494 */     calendar.setTime(date);
/*     */ 
/* 496 */     calendar.set(7, 1);
/*     */ 
/* 498 */     calendar.add(3, 1);
/* 499 */     calendar = setEndDay(calendar);
/* 500 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static String getWeekOfDate(Date date)
/*     */   {
/* 510 */     String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
/* 511 */     Calendar cal = Calendar.getInstance();
/* 512 */     cal.setTime(date);
/* 513 */     int w = cal.get(7) - 1;
/* 514 */     if (w < 0)
/* 515 */       w = 0;
/* 516 */     return weekDays[w];
/*     */   }
/*     */ 
/*     */   public static int daysBetween(Date startDate, Date endDate)
/*     */   {
/* 527 */     Calendar cal = Calendar.getInstance();
/* 528 */     cal.setTime(startDate);
/* 529 */     long startTime = cal.getTimeInMillis();
/* 530 */     cal.setTime(endDate);
/* 531 */     long endTime = cal.getTimeInMillis();
/* 532 */     long between_days = (endTime - startTime) / 86400000L;
/* 533 */     return Integer.parseInt(String.valueOf(between_days));
/*     */   }
/*     */ 
/*     */   public static Date getNextTime(Date date, int field, int amount)
/*     */   {
/* 545 */     Calendar ca = Calendar.getInstance();
/* 546 */     ca.setTime(date);
/* 547 */     ca.add(field, amount);
/* 548 */     return ca.getTime();
/*     */   }
/*     */ 
/*     */   public static Date addHour(Date date, int hour)
/*     */   {
/* 559 */     return getNextTime(date, 11, hour);
/*     */   }
/*     */ 
/*     */   public static Date addHour(Date date, double time)
/*     */   {
/* 570 */     int hour = (int)time;
/* 571 */     double minute1 = (time - hour) * 60.0D;
/* 572 */     int minute = (int)minute1;
/* 573 */     int second = (int)((minute1 - minute) * 60.0D);
/* 574 */     Date dateHour = getNextTime(date, 11, hour);
/* 575 */     Date dateMinute = getNextTime(dateHour, 12, minute);
/* 576 */     return getNextTime(dateMinute, 13, second);
/*     */   }
/*     */ 
/*     */   public static Date addMinute(Date date, int minute)
/*     */   {
/* 587 */     return getNextTime(date, 12, minute);
/*     */   }
/*     */ 
/*     */   public static boolean isBetween(Date startTime, Date endTime, Date date) {
/* 591 */     if ((date.after(startTime)) && (date.before(endTime)))
/* 592 */       return true;
/* 593 */     return false;
/*     */   }
/*     */ 
/*     */   public static double betweenMinute(Date startTime, Date endTime) {
/* 597 */     Long seconds = Long.valueOf((endTime.getTime() - startTime.getTime()) / 1000L);
/* 598 */     double s = seconds.longValue() % 60.0D / 60.0D;
/* 599 */     DecimalFormat df = new DecimalFormat("0.00");
/* 600 */     double sec = Double.parseDouble(df.format(s));
/* 601 */     Long minute = Long.valueOf(seconds.longValue() / 60L);
/* 602 */     return minute.longValue() + sec;
/*     */   }
/*     */ 
/*     */   public static double betweenHour(Date startTime, Date endTime) {
/* 606 */     Long minutes = Long.valueOf((endTime.getTime() - startTime.getTime()) / 1000L / 60L);
/* 607 */     double m = minutes.longValue() % 60.0D / 60.0D;
/* 608 */     DecimalFormat df = new DecimalFormat("0.00");
/* 609 */     double min = Double.parseDouble(df.format(m));
/*     */ 
/* 611 */     Long hour = Long.valueOf(minutes.longValue() / 60L);
/* 612 */     return hour.longValue() + min;
/*     */   }
/*     */ 
/*     */   public static double betweenHour(Date startTime, Date endTime, Double restMinutes)
/*     */   {
/* 617 */     Long minutes = Long.valueOf((endTime.getTime() - startTime.getTime()) / 1000L / 60L);
/* 618 */     Long ms = (long) (minutes - restMinutes);
/*     */ 


/* 620 */     double m = ms.longValue() % 60.0D / 60.0D;
/* 621 */     DecimalFormat df = new DecimalFormat("0.00");
/* 622 */     double min = Double.parseDouble(df.format(m));
/*     */ 
/* 624 */     Long hour = Long.valueOf(ms.longValue() / 60L);
/* 625 */     return hour.longValue() + min;
/*     */   }
/*     */ 
/*     */   public static Date getTime(Date date, Date time) {
/* 629 */     Calendar cal = Calendar.getInstance();
/* 630 */     cal.setTime(date);
/* 631 */     Calendar ca = Calendar.getInstance();
/* 632 */     ca.setTime(time);
/* 633 */     ca.set(cal.get(1), cal.get(2), cal.get(5));
/*     */ 
/* 635 */     return ca.getTime();
/*     */   }

			/**
			 * 格式化成日期字符串
			 * @param date
			 * @return
			 */
			public static String formatDate(Date date) {
			    if(date==null){
			        return null;
			    }
			    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			    return sdf.format(date);
			}
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.DateUtil
 * JD-Core Version:    0.6.2
 */