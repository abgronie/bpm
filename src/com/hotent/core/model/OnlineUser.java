/*    */ package com.hotent.core.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class OnlineUser
/*    */   implements Serializable
/*    */ {
/*    */   private String sessionId;
/*    */   private Long userId;
/*    */   private String username;
/*    */   private String fullname;
/*    */   private Long orgId;
/*    */   protected String orgName;
/*    */   private Short title;
/*    */ 
/*    */   public String getSessionId()
/*    */   {
/* 44 */     return this.sessionId;
/*    */   }
/*    */ 
/*    */   public void setSessionId(String sessionId) {
/* 48 */     this.sessionId = sessionId;
/*    */   }
/*    */ 
/*    */   public Long getUserId() {
/* 52 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(Long userId) {
/* 56 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   public String getUsername() {
/* 60 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 64 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public String getFullname() {
/* 68 */     return this.fullname;
/*    */   }
/*    */ 
/*    */   public void setFullname(String fullname) {
/* 72 */     this.fullname = fullname;
/*    */   }
/*    */ 
/*    */   public Long getOrgId() {
/* 76 */     return this.orgId;
/*    */   }
/*    */ 
/*    */   public void setOrgId(Long orgId) {
/* 80 */     this.orgId = orgId;
/*    */   }
/*    */ 
/*    */   public String getOrgName() {
/* 84 */     return this.orgName;
/*    */   }
/*    */ 
/*    */   public void setOrgName(String orgName) {
/* 88 */     this.orgName = orgName;
/*    */   }
/*    */ 
/*    */   public Short getTitle() {
/* 92 */     return this.title;
/*    */   }
/*    */ 
/*    */   public void setTitle(Short title) {
/* 96 */     this.title = title;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.OnlineUser
 * JD-Core Version:    0.6.2
 */