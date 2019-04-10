/*    */ package com.hotent.core.jms;
/*    */ 
/*    */ import javax.annotation.Resource;
/*    */ import javax.jms.Queue;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.jms.core.JmsTemplate;
/*    */ 
/*    */ public class MessageProducer
/*    */ {
/* 20 */   private static final Log logger = LogFactory.getLog(MessageProducer.class);
/*    */ 
/*    */   @Resource(name="messageQueue")
/*    */   private Queue messageQueue;
/*    */ 
/*    */   @Resource
/*    */   private JmsTemplate jmsTemplate;
/*    */ 
/* 29 */   public void send(Object model) { logger.debug("procduce the message");
/*    */ 
/* 32 */     this.jmsTemplate.convertAndSend(this.messageQueue, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.MessageProducer
 * JD-Core Version:    0.6.2
 */