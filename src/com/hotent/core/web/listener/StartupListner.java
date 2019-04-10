/*    */ package com.hotent.core.web.listener;
/*    */ 
/*    */ import com.hotent.core.util.ClassLoadUtil;
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import org.springframework.web.context.ContextLoaderListener;
/*    */ 
/*    */ public class StartupListner extends ContextLoaderListener
/*    */ {
/*    */   public void contextInitialized(ServletContextEvent event)
/*    */   {
/* 25 */     super.contextInitialized(event);
/* 26 */     ClassLoadUtil.contextInitialized(event);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.listener.StartupListner
 * JD-Core Version:    0.6.2
 */