/*    */ package com.hotent.core.jms;
/*    */ 
/*    */ import com.hotent.core.util.BeanUtils;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class MessageConsumer
/*    */ {
/* 22 */   private Map<String, IJmsHandler> handlers = new HashMap();
/*    */ 
/* 24 */   protected Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
/*    */ 
/* 26 */   public void setHandlers(Map<String, IJmsHandler> handlers) { this.handlers = handlers; }
/*    */ 
/*    */ 
/*    */   public void sendMessage(Object model)
/*    */     throws Exception
/*    */   {
/* 37 */     if ((BeanUtils.isNotEmpty(this.handlers)) && (BeanUtils.isNotEmpty(model))) {
/* 38 */       if (model.getClass().isAssignableFrom(String.class)) {
/* 39 */         String modelStr = model.toString();
/* 40 */         System.out.println(modelStr);
/*    */       }
/*    */       else {
/* 43 */         IJmsHandler jmsHandler = (IJmsHandler)this.handlers.get(model.getClass().getName());
/* 44 */         if (jmsHandler != null) {
/* 45 */           jmsHandler.handMessage(model);
/*    */         }
/*    */         else
/* 48 */           this.logger.info(model.toString());
/*    */       }
/*    */     }
/*    */     else
/* 52 */       throw new Exception("Object:[" + model + "] is not  entity Object ");
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.MessageConsumer
 * JD-Core Version:    0.6.2
 */