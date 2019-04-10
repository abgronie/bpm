/*     */ package com.hotent.core.scheduler;
/*     */ 
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import com.hotent.core.util.TimeUtil;
/*     */ import com.hotent.core.web.ResultMessage;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.quartz.CalendarIntervalScheduleBuilder;
/*     */ import org.quartz.CronScheduleBuilder;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.ScheduleBuilder;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.quartz.Trigger;
/*     */ import org.quartz.Trigger.TriggerState;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.TriggerKey;
/*     */ import org.quartz.impl.matchers.GroupMatcher;
/*     */ 
/*     */ public class SchedulerService
/*     */ {
/*     */   Scheduler scheduler;
/*  88 */   private static HashMap<String, String> mapWeek = new HashMap();
/*     */   private static final String schedGroup = "group1";
/*     */ 
/*     */   public void setScheduler(Scheduler s)
/*     */   {
/*  78 */     this.scheduler = s;
/*     */   }
/*     */ 
/*     */   public boolean addJob(String jobName, String className, String parameterJson, String description)
/*     */     throws SchedulerException, ClassNotFoundException
/*     */   {
/* 113 */     if (this.scheduler == null) return false;
/* 114 */     Class cls = Class.forName(className);
/* 115 */     JobBuilder jb = JobBuilder.newJob(cls);
/* 116 */     jb.withIdentity(jobName, "group1");
/* 117 */     if (StringUtil.isNotEmpty(parameterJson)) {
/* 118 */       setJobMap(parameterJson, jb);
/*     */     }
/* 120 */     jb.storeDurably();
/* 121 */     jb.withDescription(description);
/* 122 */     JobDetail jobDetail = jb.build();
/* 123 */     this.scheduler.addJob(jobDetail, true);
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   public ResultMessage addJob(String jobName, String className, Map parameterMap, String description)
/*     */     throws SchedulerException
/*     */   {
/* 143 */     if (this.scheduler == null) return new ResultMessage(0, "scheduler 没有配置!");
/*     */ 
/* 145 */     ResultMessage resultMsg = null;
/* 146 */     boolean isJobExist = isJobExists(jobName);
/* 147 */     if (isJobExist) {
/* 148 */       resultMsg = new ResultMessage(0, "任务已存在");
/* 149 */       return resultMsg;
/*     */     }
/*     */     Class cls;
/*     */     try {
/* 153 */       cls = Class.forName(className);
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/* 156 */       return new ResultMessage(0, "指定的任务类不存在，或者没有实现JOB接口");
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 161 */       return addJob(jobName, cls, parameterMap, description);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 165 */       resultMsg = new ResultMessage(0, e.getMessage());
/* 166 */     }return resultMsg;
/*     */   }
/*     */ 
/*     */   public ResultMessage addJob(String jobName, Class cls, Map parameterMap, String description)
/*     */     throws SchedulerException, ClassNotFoundException
/*     */   {
/* 179 */     if (this.scheduler == null) return new ResultMessage(0, "scheduler 没有配置!");
/*     */ 
/* 181 */     ResultMessage resultMsg = null;
/*     */ 
/* 183 */     JobBuilder jb = JobBuilder.newJob(cls);
/* 184 */     jb.withIdentity(jobName, "group1");
/* 185 */     if (parameterMap != null) {
/* 186 */       JobDataMap map = new JobDataMap();
/* 187 */       map.putAll(parameterMap);
/* 188 */       jb.usingJobData(map);
/*     */     }
/* 190 */     jb.storeDurably();
/* 191 */     jb.withDescription(description);
/* 192 */     JobDetail jobDetail = jb.build();
/* 193 */     this.scheduler.addJob(jobDetail, true);
/* 194 */     resultMsg = new ResultMessage(1, "添加任务成功!");
/* 195 */     return resultMsg;
/*     */   }
/*     */ 
/*     */   public boolean isJobExists(String jobName)
/*     */     throws SchedulerException
/*     */   {
/* 207 */     if (this.scheduler == null) return false;
/* 208 */     JobKey key = new JobKey(jobName, "group1");
/* 209 */     return this.scheduler.checkExists(key);
/*     */   }
/*     */ 
/*     */   public List<JobDetail> getJobList()
/*     */     throws SchedulerException
/*     */   {
/* 218 */     if (this.scheduler == null) {
/* 219 */       return new ArrayList();
/*     */     }
/* 221 */     List list = new ArrayList();
/* 222 */     GroupMatcher matcher = GroupMatcher.groupEquals("group1");
/* 223 */     Set<JobKey> set = this.scheduler.getJobKeys(matcher);
/* 224 */     for (JobKey jobKey : set) {
/* 225 */       JobDetail detail = this.scheduler.getJobDetail(jobKey);
/* 226 */       list.add(detail);
/*     */     }
/* 228 */     return list;
/*     */   }
/*     */ 
/*     */   public List<Trigger> getTriggersByJob(String jobName)
/*     */     throws SchedulerException
/*     */   {
/* 238 */     if (this.scheduler == null) {
/* 239 */       return new ArrayList();
/*     */     }
/* 241 */     JobKey key = new JobKey(jobName, "group1");
/* 242 */     return (List<Trigger>)this.scheduler.getTriggersOfJob(key);
/*     */   }
/*     */ 
/*     */   public HashMap<String, Trigger.TriggerState> getTriggerStatus(List<Trigger> list)
/*     */     throws SchedulerException
/*     */   {
/* 252 */     if (this.scheduler == null) {
/* 253 */       return new HashMap();
/*     */     }
/* 255 */     HashMap map = new HashMap();
/* 256 */     for (Iterator it = list.iterator(); it.hasNext(); ) {
/* 257 */       Trigger trigger = (Trigger)it.next();
/* 258 */       TriggerKey key = trigger.getKey();
/* 259 */       Trigger.TriggerState state = this.scheduler.getTriggerState(key);
/* 260 */       map.put(key.getName(), state);
/*     */     }
/* 262 */     return map;
/*     */   }
/*     */ 
/*     */   public boolean isTriggerExists(String trigName)
/*     */     throws SchedulerException
/*     */   {
/* 272 */     if (this.scheduler == null) {
/* 273 */       return false;
/*     */     }
/* 275 */     TriggerKey triggerKey = new TriggerKey(trigName, "group1");
/* 276 */     return this.scheduler.checkExists(triggerKey);
/*     */   }
/*     */ 
/*     */   public void addTrigger(String jobName, String trigName, String planJson)
/*     */     throws SchedulerException, ParseException
/*     */   {
/* 289 */     if (this.scheduler == null) {
/* 290 */       return;
/*     */     }
/* 292 */     JobKey jobKey = new JobKey(jobName, "group1");
/* 293 */     TriggerBuilder tb = TriggerBuilder.newTrigger();
/* 294 */     tb.withIdentity(trigName, "group1");
/*     */ 
/* 296 */     setTrigBuilder(planJson, tb);
/* 297 */     tb.forJob(jobKey);
/* 298 */     Trigger trig = tb.build();
/* 299 */     this.scheduler.scheduleJob(trig);
/*     */   }
/*     */ 
/*     */   public void addTrigger(String jobName, String trigName, int minute)
/*     */     throws SchedulerException
/*     */   {
/* 310 */     if (this.scheduler == null) {
/* 311 */       return;
/*     */     }
/* 313 */     JobKey jobKey = new JobKey(jobName, "group1");
/* 314 */     TriggerBuilder tb = TriggerBuilder.newTrigger();
/* 315 */     tb.withIdentity(trigName, "group1");
/* 316 */     ScheduleBuilder sb = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMinutes(minute);
/* 317 */     tb.startNow();
/* 318 */     tb.withSchedule(sb);
/* 319 */     tb.withDescription("每:" + minute + "分钟执行!");
/*     */ 
/* 321 */     tb.forJob(jobKey);
/* 322 */     Trigger trig = tb.build();
/* 323 */     this.scheduler.scheduleJob(trig);
/*     */   }
/*     */ 
/*     */   private void setTrigBuilder(String planJson, TriggerBuilder<Trigger> tb) throws ParseException {
/* 327 */     JSONObject jsonObject = JSONObject.fromObject(planJson);
/*     */ 
/* 329 */     PlanObject planObject = (PlanObject)JSONObject.toBean(jsonObject, PlanObject.class);
/* 330 */     int type = planObject.getType();
/* 331 */     String value = planObject.getTimeInterval();
/* 332 */     switch (type)
/*     */     {
/*     */     case 1:
/* 335 */       Date date = TimeUtil.convertString(value);
/* 336 */       tb.startAt(date);
/* 337 */       tb.withDescription("执行一次,执行时间:" + date.toLocaleString());
/* 338 */       break;
/*     */     case 2:
/* 341 */       int minute = Integer.parseInt(value);
/* 342 */       ScheduleBuilder sb = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMinutes(minute);
/*     */ 
/* 344 */       tb.startNow();
/* 345 */       tb.withSchedule(sb);
/* 346 */       tb.withDescription("每:" + minute + "分钟执行!");
/* 347 */       break;
/*     */     case 3:
/* 350 */       String[] aryTime = value.split(":");
/* 351 */       int hour = Integer.parseInt(aryTime[0]);
/* 352 */       int m = Integer.parseInt(aryTime[1]);
/* 353 */       ScheduleBuilder sb1 = CronScheduleBuilder.dailyAtHourAndMinute(hour, m);
/* 354 */       tb.startNow();
/* 355 */       tb.withSchedule(sb1);
/* 356 */       tb.withDescription("每天：" + hour + ":" + m + "执行!");
/* 357 */       break;
/*     */     case 4:
/* 361 */       String[] aryExpression = value.split("[|]");
/* 362 */       String week = aryExpression[0];
/* 363 */       String[] aryTime1 = aryExpression[1].split(":");
/* 364 */       String h1 = aryTime1[0];
/* 365 */       String m1 = aryTime1[1];
/* 366 */       String cronExperssion = "0 " + m1 + " " + h1 + " ? * " + week;
/* 367 */       ScheduleBuilder sb4 = CronScheduleBuilder.cronSchedule(cronExperssion);
/* 368 */       tb.startNow();
/* 369 */       tb.withSchedule(sb4);
/* 370 */       String weekName = getWeek(week);
/* 371 */       tb.withDescription("每周：" + weekName + "," + h1 + ":" + m1 + "执行!");
/* 372 */       break;
/*     */     case 5:
/* 376 */       String[] aryExpression5 = value.split("[|]");
/* 377 */       String day = aryExpression5[0];
/* 378 */       String[] aryTime2 = aryExpression5[1].split(":");
/* 379 */       String h2 = aryTime2[0];
/* 380 */       String m2 = aryTime2[1];
/* 381 */       String cronExperssion1 = "0 " + m2 + " " + h2 + " " + day + " * ?";
/* 382 */       ScheduleBuilder sb5 = CronScheduleBuilder.cronSchedule(cronExperssion1);
/* 383 */       tb.startNow();
/* 384 */       tb.withSchedule(sb5);
/* 385 */       String dayName = getDay(day);
/* 386 */       tb.withDescription("每月:" + dayName + "," + h2 + ":" + m2 + "执行!");
/* 387 */       break;
/*     */     case 6:
/* 390 */       ScheduleBuilder sb6 = CronScheduleBuilder.cronSchedule(value);
/* 391 */       tb.startNow();
/* 392 */       tb.withSchedule(sb6);
/* 393 */       tb.withDescription("CronTrigger表达式:" + value);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getDay(String day)
/*     */   {
/* 399 */     String[] aryDay = day.split(",");
/* 400 */     int len = aryDay.length;
/* 401 */     String str = "";
/* 402 */     for (int i = 0; i < len; i++) {
/* 403 */       String tmp = aryDay[i];
/* 404 */       tmp = tmp.equals("L") ? "最后一天" : tmp;
/* 405 */       if (i < len - 1) {
/* 406 */         str = str + tmp + ",";
/*     */       }
/*     */       else {
/* 409 */         str = str + tmp;
/*     */       }
/*     */     }
/* 412 */     return str;
/*     */   }
/*     */ 
/*     */   private String getWeek(String week)
/*     */   {
/* 421 */     String[] aryWeek = week.split(",");
/* 422 */     int len = aryWeek.length;
/* 423 */     String str = "";
/* 424 */     for (int i = 0; i < len; i++) {
/* 425 */       if (i < len - 1)
/* 426 */         str = str + (String)mapWeek.get(aryWeek[i]) + ",";
/*     */       else
/* 428 */         str = str + (String)mapWeek.get(aryWeek[i]);
/*     */     }
/* 430 */     return str;
/*     */   }
/*     */ 
/*     */   private void setJobMap(String json, JobBuilder jb)
/*     */   {
/* 441 */     JSONArray aryJson = JSONArray.fromObject(json);
/* 442 */     ParameterObj[] list = (ParameterObj[])JSONArray.toArray(aryJson, ParameterObj.class);
/* 443 */     for (int i = 0; i < list.length; i++) {
/* 444 */       ParameterObj obj = list[0];
/* 445 */       String type = obj.getType();
/* 446 */       String name = obj.getName();
/* 447 */       String value = obj.getValue();
/* 448 */       if (type.equals("int")) {
/* 449 */         if (StringUtil.isEmpty(value))
/* 450 */           jb.usingJobData(name, Integer.valueOf(0));
/*     */         else {
/* 452 */           jb.usingJobData(name, Integer.valueOf(Integer.parseInt(value)));
/*     */         }
/*     */       }
/* 455 */       else if (type.equals("long")) {
/* 456 */         if (StringUtil.isEmpty(value))
/* 457 */           jb.usingJobData(name, Integer.valueOf(0));
/*     */         else {
/* 459 */           jb.usingJobData(name, Long.valueOf(Long.parseLong(value)));
/*     */         }
/*     */       }
/* 462 */       else if (type.equals("float")) {
/* 463 */         if (StringUtil.isEmpty(value))
/* 464 */           jb.usingJobData(name, Double.valueOf(0.0D));
/*     */         else {
/* 466 */           jb.usingJobData(name, Float.valueOf(Float.parseFloat(value)));
/*     */         }
/*     */       }
/* 469 */       else if (type.equals("boolean")) {
/* 470 */         if (StringUtil.isEmpty(value))
/* 471 */           jb.usingJobData(name, Boolean.valueOf(false));
/*     */         else {
/* 473 */           jb.usingJobData(name, Boolean.valueOf(Boolean.parseBoolean(value)));
/*     */         }
/*     */       }
/*     */       else
/* 477 */         jb.usingJobData(name, value);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void delJob(String jobName)
/*     */     throws SchedulerException
/*     */   {
/* 489 */     if (this.scheduler == null) {
/* 490 */       return;
/*     */     }
/* 492 */     JobKey key = new JobKey(jobName, "group1");
/* 493 */     this.scheduler.deleteJob(key);
/*     */   }
/*     */ 
/*     */   public Trigger getTrigger(String triggerName) throws SchedulerException {
/* 497 */     if (this.scheduler == null) {
/* 498 */       return null;
/*     */     }
/* 500 */     TriggerKey key = new TriggerKey(triggerName, "group1");
/* 501 */     Trigger trigger = this.scheduler.getTrigger(key);
/* 502 */     return trigger;
/*     */   }
/*     */ 
/*     */   public void delTrigger(String triggerName)
/*     */     throws SchedulerException
/*     */   {
/* 512 */     if (this.scheduler == null) {
/* 513 */       return;
/*     */     }
/* 515 */     TriggerKey key = new TriggerKey(triggerName, "group1");
/* 516 */     this.scheduler.unscheduleJob(key);
/*     */   }
/*     */ 
/*     */   public void toggleTriggerRun(String triggerName)
/*     */     throws SchedulerException
/*     */   {
/* 525 */     if (this.scheduler == null) {
/* 526 */       return;
/*     */     }
/* 528 */     TriggerKey key = new TriggerKey(triggerName, "group1");
/* 529 */     Trigger.TriggerState state = this.scheduler.getTriggerState(key);
/* 530 */     if (state == Trigger.TriggerState.PAUSED) {
/* 531 */       this.scheduler.resumeTrigger(key);
/*     */     }
/* 533 */     else if (state == Trigger.TriggerState.NORMAL)
/* 534 */       this.scheduler.pauseTrigger(key);
/*     */   }
/*     */ 
/*     */   public void executeJob(String jobName)
/*     */     throws SchedulerException
/*     */   {
/* 546 */     if (this.scheduler == null) {
/* 547 */       return;
/*     */     }
/* 549 */     JobKey key = new JobKey(jobName, "group1");
/* 550 */     this.scheduler.triggerJob(key);
/*     */   }
/*     */ 
/*     */   public void start()
/*     */     throws SchedulerException
/*     */   {
/* 559 */     this.scheduler.start();
/*     */   }
/*     */ 
/*     */   public void shutdown()
/*     */     throws SchedulerException
/*     */   {
/* 568 */     this.scheduler.standby();
/*     */   }
/*     */ 
/*     */   public boolean isStarted()
/*     */     throws SchedulerException
/*     */   {
/* 578 */     return this.scheduler.isStarted();
/*     */   }
/*     */ 
/*     */   public boolean isInStandbyMode()
/*     */     throws SchedulerException
/*     */   {
/* 587 */     return this.scheduler.isInStandbyMode();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  89 */     mapWeek.put("MON", "星期一");
/*  90 */     mapWeek.put("TUE", "星期二");
/*  91 */     mapWeek.put("WED", "星期三");
/*  92 */     mapWeek.put("THU", "星期四");
/*  93 */     mapWeek.put("FRI", "星期五");
/*  94 */     mapWeek.put("SAT", "星期六");
/*  95 */     mapWeek.put("SUN", "星期日");
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.scheduler.SchedulerService
 * JD-Core Version:    0.6.2
 */