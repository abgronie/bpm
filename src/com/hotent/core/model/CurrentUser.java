/*    */ package com.hotent.core.model;
/*    */ 
/*    */ public class CurrentUser
/*    */ {
/* 12 */   private Long userId = Long.valueOf(0L);
/*    */ 
/* 16 */   private String account = "";
/*    */ 
/* 20 */   private String name = "";
/*    */ 
/* 24 */   private Long orgId = Long.valueOf(0L);
/*    */ 
/* 28 */   private Long posId = Long.valueOf(0L);
/*    */ 
/*    */   public Long getUserId()
/*    */   {
/* 32 */     return this.userId;
/*    */   }
/*    */   public void setUserId(Long userId) {
/* 35 */     this.userId = userId;
/*    */   }
/*    */   public String getAccount() {
/* 38 */     return this.account;
/*    */   }
/*    */   public void setAccount(String account) {
/* 41 */     this.account = account;
/*    */   }
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 47 */     this.name = name;
/*    */   }
/*    */   public Long getOrgId() {
/* 50 */     return this.orgId;
/*    */   }
/*    */   public void setOrgId(Long orgId) {
/* 53 */     this.orgId = orgId;
/*    */   }
/*    */   public Long getPosId() {
/* 56 */     return this.posId;
/*    */   }
/*    */   public void setPosId(Long posId) {
/* 59 */     this.posId = posId;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.CurrentUser
 * JD-Core Version:    0.6.2
 */