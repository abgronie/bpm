/*     */ package com.hotent.core.sms.impl;
/*     */ 
/*     */ import com.hotent.core.api.util.PropertyUtil;
/*     */ import com.hotent.core.sms.IShortMessage;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public class ShortMessageImpl
/*     */   implements IShortMessage
/*     */ {
/*     */   private static ShortMessageImpl instance;
/*  25 */   private static Lock lock = new ReentrantLock();
/*     */   String url;
/*     */   String userID;
/*     */   String account;
/*     */   String password;
/*     */   String content;
/*     */   String sendTime;
/*     */   int sendType;
/*     */   String postFixNumber;
/*     */   int sign;
/*     */ 
/*     */   public void initial()
/*     */   {
/*  42 */     this.url = PropertyUtil.getByAlias("smsUrl");
/*  43 */     this.userID = PropertyUtil.getByAlias("smsUserID");
/*  44 */     this.account = PropertyUtil.getByAlias("smsAccount");
/*  45 */     this.password = PropertyUtil.getByAlias("smsPassword");
/*  46 */     this.content = PropertyUtil.getByAlias("smsContent");
/*  47 */     this.sendTime = PropertyUtil.getByAlias("smsSendTime", "");
/*  48 */     this.sendType = PropertyUtil.getIntByAlias("smssendType", Integer.valueOf(0)).intValue();
/*  49 */     this.postFixNumber = PropertyUtil.getByAlias("smsPostFixNumber");
/*  50 */     this.sign = PropertyUtil.getIntByAlias("smsSign", Integer.valueOf(0)).intValue();
/*     */   }
/*     */ 
/*     */   public boolean sendSms(List<String> mobiles, String message)
/*     */   {
/*  61 */     initial();
/*  62 */     String envelop = generateEnvelop(mobiles, message);
/*     */ 
/*  67 */     OutputStreamWriter outputStreamWriter = null;
/*  68 */     BufferedReader bufferedReader = null;
/*     */     try {
/*  70 */       URL uRL = new URL(this.url);
/*  71 */       URLConnection conn = uRL.openConnection();
/*  72 */       conn.setDoOutput(true);
/*  73 */       outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
/*  74 */       outputStreamWriter.write(envelop);
/*  75 */       outputStreamWriter.flush();
/*     */ 
/*  78 */       bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*     */ 
/*  80 */       StringBuffer response = new StringBuffer();
/*     */       String line;
/*  82 */       while ((line = bufferedReader.readLine()) != null) {
/*  83 */         response.append(line);
/*     */       }
/*     */ 
/*  86 */       outputStreamWriter.close();
/*  87 */       bufferedReader.close();
/*     */     }
/*     */     catch (MalformedURLException e) {
/*  90 */       e.printStackTrace();
/*  91 */       return false;
/*     */     } catch (IOException e) {
/*  93 */       e.printStackTrace();
/*  94 */       return false;
/*     */     }
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   private String generateEnvelop(List<String> mobiles, String content)
/*     */   {
/* 109 */     StringBuffer phones = new StringBuffer();
/* 110 */     for (String mobile : mobiles) {
/* 111 */       phones.append(mobile);
/* 112 */       phones.append(",");
/*     */     }
/* 114 */     phones.deleteCharAt(phones.length() - 1);
/* 115 */     String wsdlStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.sms.com\"><soapenv:Header/><soapenv:Body><soap:directSend><soap:userID>" + this.userID + "</soap:userID>" + "<soap:account>" + this.account + "</soap:account>" + "<soap:password>" + this.password + "</soap:password>" + "<soap:phones>" + phones + "</soap:phones>" + "<soap:content>" + content + "</soap:content>" + "<soap:sendTime>" + this.sendTime + "</soap:sendTime>" + "<soap:sendType>" + this.sendType + "</soap:sendType>" + "<soap:postFixNumber>" + this.postFixNumber + "</soap:postFixNumber>" + "<soap:sign>" + this.sign + "</soap:sign>" + "</soap:directSend>" + "</soapenv:Body>" + "</soapenv:Envelope>";
/*     */ 
/* 149 */     return wsdlStr;
/*     */   }
/*     */ 
/*     */   public static ShortMessageImpl getInstance()
/*     */   {
/* 158 */     if (instance == null) {
/* 159 */       lock.lock();
/*     */       try {
/* 161 */         if (instance == null)
/* 162 */           instance = new ShortMessageImpl();
/*     */       } finally {
/* 164 */         lock.unlock();
/*     */       }
/*     */     }
/* 167 */     return instance;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.sms.impl.ShortMessageImpl
 * JD-Core Version:    0.6.2
 */