/*    */ package com.hotent.core.ldap;
/*    */ 
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class LdapObjectEvent extends ApplicationEvent
/*    */ {
/*  7 */   private boolean isUser = true;
/*    */   private static final long serialVersionUID = 6370522848066687821L;
/*    */ 
/*    */   public LdapObjectEvent(Object source)
/*    */   {
/* 13 */     super(source);
/*    */   }
/*    */ 
/*    */   public boolean isUser() {
/* 17 */     return this.isUser;
/*    */   }
/*    */ 
/*    */   public void setUser(boolean isUser) {
/* 21 */     this.isUser = isUser;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.LdapObjectEvent
 * JD-Core Version:    0.6.2
 */