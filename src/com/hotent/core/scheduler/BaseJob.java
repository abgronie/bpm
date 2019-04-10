/*    */ package com.hotent.core.scheduler;
/*    */ 
/*    */ import com.hotent.core.api.log.ISysJobLog;
/*    */ import com.hotent.core.api.log.ISysJobLogService;
/*    */ import com.hotent.core.util.AppUtil;
/*    */ import com.hotent.core.util.UniqueIdUtil;
/*    */ import java.util.Date;
/*    */ import org.quartz.DisallowConcurrentExecution;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.JobKey;
/*    */ import org.quartz.Trigger;
/*    */ import org.quartz.TriggerKey;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ @DisallowConcurrentExecution
/*    */ public abstract class BaseJob
/*    */   implements Job
/*    */ {
/* 33 */   private final Logger log = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   public abstract void executeJob(JobExecutionContext paramJobExecutionContext) throws Exception;
/*    */ 
/*    */   public void execute(JobExecutionContext context) throws JobExecutionException
/*    */   {
/* 39 */     String jobName = context.getJobDetail().getKey().getName();
/*    */ 
/* 41 */     String trigName = "directExec";
/* 42 */     Trigger trig = context.getTrigger();
/* 43 */     if (trig != null)
/* 44 */       trigName = trig.getKey().getName();
/* 45 */     Date strStartTime = new Date();
/* 46 */     long startTime = System.currentTimeMillis();
/*    */     try
/*    */     {
/* 49 */       executeJob(context);
/* 50 */       long endTime = System.currentTimeMillis();
/* 51 */       Date strEndTime = new Date();
/*    */ 
/* 53 */       long runTime = (endTime - startTime) / 1000L;
/* 54 */       addLog(jobName, trigName, strStartTime, strEndTime, runTime, "任务执行成功!", 1);
/*    */     }
/*    */     catch (Exception ex)
/*    */     {
/* 58 */       long endTime = System.currentTimeMillis();
/* 59 */       Date strEndTime = new Date();
/* 60 */       long runTime = (endTime - startTime) / 1000L;
/*    */       try {
/* 62 */         addLog(jobName, trigName, strStartTime, strEndTime, runTime, ex.toString(), 0);
/*    */       }
/*    */       catch (Exception e) {
/* 65 */         e.printStackTrace();
/* 66 */         this.log.error("执行任务出错:" + e.getMessage());
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   private void addLog(String jobName, String trigName, Date strStartTime, Date strEndTime, long runTime, String content, int state)
/*    */     throws Exception
/*    */   {
/* 74 */     ISysJobLogService logService = (ISysJobLogService)AppUtil.getBean(ISysJobLogService.class);
/*    */ 
/* 76 */     ISysJobLog jobLog = logService.getJobLog(jobName, trigName, strStartTime, strEndTime, runTime, content, state);
/*    */ 
/* 80 */     Long id = Long.valueOf(UniqueIdUtil.genId());
/*    */ 
/* 82 */     jobLog.setLogId(id);
/*    */ 
/* 85 */     logService.addLog(jobLog);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.scheduler.BaseJob
 * JD-Core Version:    0.6.2
 */