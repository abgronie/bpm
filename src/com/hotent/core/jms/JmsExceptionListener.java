/*    */ package com.hotent.core.jms;
/*    */ 
/*    */ import com.hotent.core.util.ExceptionUtil;
/*    */ import javax.jms.ExceptionListener;
/*    */ import javax.jms.JMSException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class JmsExceptionListener
/*    */   implements ExceptionListener
/*    */ {
/* 21 */   protected Logger logger = LoggerFactory.getLogger(JmsExceptionListener.class);
/*    */ 
/*    */   public void onException(JMSException ex)
/*    */   {
/* 25 */     ex.printStackTrace();
/* 26 */     String message = ExceptionUtil.getExceptionMessage(ex);
/* 27 */     this.logger.error(message);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.JmsExceptionListener
 * JD-Core Version:    0.6.2
 */