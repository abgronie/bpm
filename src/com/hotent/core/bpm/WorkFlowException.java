/*    */ package com.hotent.core.bpm;
/*    */ 
/*    */ public class WorkFlowException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -7289238698048230824L;
/*    */ 
/*    */   public WorkFlowException(String msg)
/*    */   {
/* 19 */     super(msg);
/*    */   }
/*    */ 
/*    */   public WorkFlowException(String msg, Throwable throwable)
/*    */   {
/* 24 */     super(msg, throwable);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.WorkFlowException
 * JD-Core Version:    0.6.2
 */