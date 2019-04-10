/*    */ package com.hotent.core.jms;
/*    */ 
/*    */ import com.hotent.core.model.MessageModel;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public class MessageHandler
/*    */   implements IJmsHandler
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   MessageHandlerContainer messageHandlerContainer;
/*    */ 
/*    */   public void handMessage(Object model)
/*    */   {
/* 14 */     MessageModel msgModel = (MessageModel)model;
/* 15 */     String type = msgModel.getInformType();
/* 16 */     IMessageHandler handler = this.messageHandlerContainer.getHandler(type);
/* 17 */     handler.handMessage(msgModel);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.MessageHandler
 * JD-Core Version:    0.6.2
 */