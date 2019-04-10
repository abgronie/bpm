/*    */ package com.hotent.core.bpm.util;
/*    */ 
/*    */ import com.hotent.core.util.AppUtil;
/*    */ import org.activiti.engine.ProcessEngine;
/*    */ import org.activiti.engine.impl.ProcessEngineImpl;
/*    */ import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
/*    */ import org.activiti.engine.impl.interceptor.CommandExecutor;
/*    */ 
/*    */ public class ActivitiUtil
/*    */ {
/*    */   public static CommandExecutor getCommandExecutor()
/*    */   {
/* 21 */     ProcessEngineImpl engine = (ProcessEngineImpl)AppUtil.getBean(ProcessEngine.class);
/* 22 */     CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
/* 23 */     return cmdExecutor;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.util.ActivitiUtil
 * JD-Core Version:    0.6.2
 */