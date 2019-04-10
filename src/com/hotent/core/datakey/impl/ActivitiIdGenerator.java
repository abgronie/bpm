/*    */ package com.hotent.core.datakey.impl;
/*    */ 
/*    */ import com.hotent.core.util.UniqueIdUtil;
/*    */ import org.activiti.engine.impl.cfg.IdGenerator;
/*    */ 
/*    */ public class ActivitiIdGenerator
/*    */   implements IdGenerator
/*    */ {
/*    */   public String getNextId()
/*    */   {
/* 16 */     return String.valueOf(UniqueIdUtil.genId());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.datakey.impl.ActivitiIdGenerator
 * JD-Core Version:    0.6.2
 */