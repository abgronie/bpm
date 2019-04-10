/*    */ package com.hotent.core.jms;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.jms.JMSException;
/*    */ import javax.jms.Message;
/*    */ import javax.jms.ObjectMessage;
/*    */ import javax.jms.Session;
/*    */ import org.springframework.jms.support.converter.MessageConverter;
/*    */ 
/*    */ public class ActiveMqMessageConverter
/*    */   implements MessageConverter
/*    */ {
/*    */   public Message toMessage(Object object, Session session)
/*    */     throws JMSException
/*    */   {
/* 30 */     if ((object != null) && (object.getClass() != null)) {
/* 31 */       ObjectMessage objMsg = session.createObjectMessage();
/* 32 */       objMsg.setObject((Serializable)object);
/* 33 */       return objMsg;
/*    */     }
/* 35 */     throw new JMSException("Object:[" + object + "] is not legal message");
/*    */   }
/*    */ 
/*    */   public Object fromMessage(Message msg)
/*    */     throws JMSException
/*    */   {
/* 45 */     if ((msg instanceof ObjectMessage)) {
/* 46 */       ObjectMessage objMsg = (ObjectMessage)msg;
/* 47 */       Object object = objMsg.getObject();
/* 48 */       if ((object != null) && (object.getClass() != null)) {
/* 49 */         return object;
/*    */       }
/* 51 */       throw new JMSException("Object:[" + msg + "] is not legal message");
/*    */     }
/*    */ 
/* 55 */     throw new JMSException("Msg:[" + msg + "] is not ObjectMessage");
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.ActiveMqMessageConverter
 * JD-Core Version:    0.6.2
 */