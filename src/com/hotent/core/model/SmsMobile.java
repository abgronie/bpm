/*     */ package com.hotent.core.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class SmsMobile
/*     */   implements Serializable
/*     */ {
/*  21 */   public static final Short STATUS_SENDED = Short.valueOf((short)1);
/*     */ 
/*  25 */   public static final Short STATUS_NOT_SENDED = Short.valueOf((short)0);
/*     */   protected Long smsId;
/*     */   protected Date sendTime;
/*     */   protected String recipients;
/*     */   protected String phoneNumber;
/*     */   protected Long userId;
/*     */   protected String userName;
/*     */   protected String smsContent;
/*     */   protected Short status;
/*     */   protected String fromUser;
/*     */ 
/*     */   public SmsMobile()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SmsMobile(Long in_smsId)
/*     */   {
/*  48 */     setSmsId(in_smsId);
/*     */   }
/*     */ 
/*     */   public Long getSmsId()
/*     */   {
/*  58 */     return this.smsId;
/*     */   }
/*     */ 
/*     */   public void setSmsId(Long aValue)
/*     */   {
/*  65 */     this.smsId = aValue;
/*     */   }
/*     */ 
/*     */   public Date getSendTime()
/*     */   {
/*  75 */     return this.sendTime;
/*     */   }
/*     */ 
/*     */   public void setSendTime(Date aValue)
/*     */   {
/*  84 */     this.sendTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecipients()
/*     */   {
/*  94 */     return this.recipients;
/*     */   }
/*     */ 
/*     */   public void setRecipients(String aValue)
/*     */   {
/* 101 */     this.recipients = aValue;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber()
/*     */   {
/* 111 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String aValue)
/*     */   {
/* 120 */     this.phoneNumber = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 130 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 137 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public String getUserName()
/*     */   {
/* 147 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String aValue)
/*     */   {
/* 154 */     this.userName = aValue;
/*     */   }
/*     */ 
/*     */   public String getSmsContent()
/*     */   {
/* 164 */     return this.smsContent;
/*     */   }
/*     */ 
/*     */   public void setSmsContent(String aValue)
/*     */   {
/* 173 */     this.smsContent = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 183 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 192 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public String getFromUser() {
/* 196 */     return this.fromUser;
/*     */   }
/*     */ 
/*     */   public void setFromUser(String fromUser) {
/* 200 */     this.fromUser = fromUser;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.SmsMobile
 * JD-Core Version:    0.6.2
 */