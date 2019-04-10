/*    */ package com.hotent.core.sms.impl;
/*    */ 
/*    */ import com.hotent.core.sms.IShortMessage;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class NoneMessageImpl
/*    */   implements IShortMessage
/*    */ {
/*    */   private static NoneMessageImpl instance;
/* 15 */   private static Lock lock = new ReentrantLock();
/* 16 */   protected Logger logger = LoggerFactory.getLogger(NoneMessageImpl.class);
/*    */ 
/* 18 */   public boolean sendSms(List<String> mobiles, String message) { this.logger.info("不支持的短信类型...");
/* 19 */     return false;
/*    */   }
/*    */ 
/*    */   public static NoneMessageImpl getInstance()
/*    */   {
/* 28 */     if (instance == null) {
/* 29 */       lock.lock();
/*    */       try {
/* 31 */         if (instance == null)
/* 32 */           instance = new NoneMessageImpl();
/*    */       }
/*    */       finally {
/* 35 */         lock.unlock();
/*    */       }
/*    */     }
/* 38 */     return instance;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.sms.impl.NoneMessageImpl
 * JD-Core Version:    0.6.2
 */