/*     */ package com.hotent.core.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringStyle;
/*     */ 
/*     */ public class MailModel
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String subject;
/*     */   private String from;
/*     */   private String[] to;
/*     */   private String[] cc;
/*     */   private String[] bcc;
/*     */   private Date sendDate;
/*     */   private String content;
/*     */   private String mailTemplate;
/*  55 */   private Map mailData = null;
/*     */ 
/*     */   public String getSubject() {
/*  58 */     return this.subject;
/*     */   }
/*     */   public void setSubject(String subject) {
/*  61 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */   public String getFrom() {
/*  65 */     return this.from;
/*     */   }
/*     */   public void setFrom(String from) {
/*  68 */     this.from = from;
/*     */   }
/*     */   public String[] getTo() {
/*  71 */     return this.to;
/*     */   }
/*     */   public void setTo(String[] to) {
/*  74 */     this.to = to;
/*     */   }
/*     */ 
/*     */   public String[] getCc() {
/*  78 */     return this.cc;
/*     */   }
/*     */ 
/*     */   public void setCc(String[] cc) {
/*  82 */     this.cc = cc;
/*     */   }
/*     */ 
/*     */   public String[] getBcc() {
/*  86 */     return this.bcc;
/*     */   }
/*     */ 
/*     */   public void setBcc(String[] bcc) {
/*  90 */     this.bcc = bcc;
/*     */   }
/*     */   public Date getSendDate() {
/*  93 */     return this.sendDate;
/*     */   }
/*     */   public void setSendDate(Date sendDate) {
/*  96 */     this.sendDate = sendDate;
/*     */   }
/*     */   public String getContent() {
/*  99 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/* 102 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getMailTemplate()
/*     */   {
/* 109 */     return this.mailTemplate;
/*     */   }
/*     */   public void setMailTemplate(String mailTemplate) {
/* 112 */     this.mailTemplate = mailTemplate;
/*     */   }
/*     */   public Map getMailData() {
/* 115 */     return this.mailData;
/*     */   }
/*     */   public void setMailData(Map mailData) {
/* 118 */     this.mailData = mailData;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 126 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.MailModel
 * JD-Core Version:    0.6.2
 */