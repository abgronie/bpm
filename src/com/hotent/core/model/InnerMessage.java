/*     */ package com.hotent.core.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class InnerMessage
/*     */   implements Serializable
/*     */ {
/*     */   private String subject;
/*     */   private String from;
/*     */   private String fromName;
/*     */   private String to;
/*     */   private String toName;
/*     */   private Date sendDate;
/*     */   private String content;
/*     */   private short canReply;
/*     */ 
/*     */   public short getCanReply()
/*     */   {
/*  48 */     return this.canReply;
/*     */   }
/*     */ 
/*     */   public void setCanReply(short canReply) {
/*  52 */     this.canReply = canReply;
/*     */   }
/*     */ 
/*     */   public String getSubject() {
/*  56 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public String getFromName() {
/*  60 */     return this.fromName;
/*     */   }
/*     */ 
/*     */   public void setFromName(String fromName) {
/*  64 */     this.fromName = fromName;
/*     */   }
/*     */ 
/*     */   public String getToName() {
/*  68 */     return this.toName;
/*     */   }
/*     */ 
/*     */   public void setToName(String toName) {
/*  72 */     this.toName = toName;
/*     */   }
/*     */ 
/*     */   public void setSubject(String subject) {
/*  76 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */   public String getFrom() {
/*  80 */     return this.from;
/*     */   }
/*     */ 
/*     */   public void setFrom(String from) {
/*  84 */     this.from = from;
/*     */   }
/*     */ 
/*     */   public String getTo() {
/*  88 */     return this.to;
/*     */   }
/*     */ 
/*     */   public void setTo(String to) {
/*  92 */     this.to = to;
/*     */   }
/*     */ 
/*     */   public Date getSendDate() {
/*  96 */     return this.sendDate;
/*     */   }
/*     */ 
/*     */   public void setSendDate(Date sendDate) {
/* 100 */     this.sendDate = sendDate;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/* 104 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 108 */     this.content = content;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.InnerMessage
 * JD-Core Version:    0.6.2
 */