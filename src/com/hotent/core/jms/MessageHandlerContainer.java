/*    */ package com.hotent.core.jms;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class MessageHandlerContainer
/*    */ {
/* 16 */   protected Logger logger = LoggerFactory.getLogger(MessageHandlerContainer.class);
/*    */ 
/* 19 */   private Map<String, IMessageHandler> handlersMap = new LinkedHashMap();
/*    */ 
/*    */   public Map<String, IMessageHandler> getHandlerMap() {
/* 22 */     return this.handlersMap;
/*    */   }
/*    */ 
/*    */   public void setHandlersMap(Map<String, IMessageHandler> map) {
/* 26 */     this.handlersMap = map;
/*    */   }
/*    */ 
/*    */   public IMessageHandler getHandler(String key) {
/* 30 */     return (IMessageHandler)this.handlersMap.get(key);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.MessageHandlerContainer
 * JD-Core Version:    0.6.2
 */