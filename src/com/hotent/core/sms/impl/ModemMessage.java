/*     */ package com.hotent.core.sms.impl;
/*     */ 
/*     */ import com.hotent.core.sms.IShortMessage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.smslib.AGateway.Protocols;
/*     */ import org.smslib.GatewayException;
/*     */ import org.smslib.Message.MessageEncodings;
/*     */ import org.smslib.OutboundMessage;
/*     */ import org.smslib.Service;
/*     */ import org.smslib.modem.SerialModemGateway;
/*     */ 
/*     */ public class ModemMessage
/*     */   implements IShortMessage
/*     */ {
/*  25 */   private static ModemMessage instance = null;
/*     */ 
/*  27 */   private static Lock lock = new ReentrantLock();
/*     */ 
/*  29 */   private boolean serviceHasOpen = false;
/*     */ 
/*  31 */   private Service srv = null;
/*     */   private SerialModemGateway gateway;
/*  35 */   private static String smsGroup = "smsgruop";
/*  36 */   protected Logger logger = LoggerFactory.getLogger(ModemMessage.class);
/*     */ 
/*     */   public static ModemMessage getInstance()
/*     */   {
/*  43 */     if (instance == null) {
/*  44 */       lock.lock();
/*     */       try {
/*  46 */         if (instance == null)
/*  47 */           instance = new ModemMessage();
/*     */       } finally {
/*  49 */         lock.unlock();
/*     */       }
/*     */     }
/*  52 */     return instance;
/*     */   }
/*     */ 
/*     */   private boolean initial(String com, int baudRate, String pin)
/*     */   {
/*  67 */     boolean rsbool = true;
/*  68 */     this.srv = new Service();
/*  69 */     this.gateway = new SerialModemGateway("SMSLINK", com, baudRate, "", "");
/*  70 */     this.gateway.setOutbound(true);
/*  71 */     this.gateway.setInbound(true);
/*  72 */     this.gateway.setProtocol(Protocols.PDU);
/*  73 */     this.gateway.setSimPin(pin);
/*     */     try {
/*  75 */       this.srv.addGateway(this.gateway);
/*     */     } catch (GatewayException e) {
/*  77 */       rsbool = false;
/*  78 */       e.printStackTrace();
/*     */     }
/*  80 */     if (rsbool)
/*  81 */       rsbool = startService();
/*  82 */     return rsbool;
/*     */   }
/*     */ 
/*     */   private boolean sendMessage(List<String> phoneList, String message)
/*     */   {
/*  93 */     boolean rsbool = true;
/*     */ 
/*  95 */     for (String phone : phoneList) {
/*  96 */       this.srv.addToGroup(smsGroup, phone);
/*     */     }
/*  98 */     OutboundMessage msg = new OutboundMessage(smsGroup, message);
/*  99 */     msg.setEncoding(MessageEncodings.ENCUCS2);
/*     */     try {
/* 101 */       this.srv.sendMessage(msg);
/*     */ 
/* 103 */       for (String phone : phoneList)
/* 104 */         this.srv.removeFromGroup(smsGroup, phone);
/*     */     }
/*     */     catch (Exception e) {
/* 107 */       rsbool = false;
/* 108 */       e.printStackTrace();
/*     */     }
/* 110 */     return rsbool;
/*     */   }
/*     */ 
/*     */   private boolean startService()
/*     */   {
/* 115 */     boolean rsbool = true;
/*     */     try {
/* 117 */       this.srv.startService();
/* 118 */       this.srv.createGroup(smsGroup);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 125 */       rsbool = false;
/* 126 */       e.printStackTrace();
/*     */     }
/* 128 */     return rsbool;
/*     */   }
/*     */ 
/*     */   public boolean stopService()
/*     */   {
/* 137 */     boolean rsbool = true;
/*     */     try {
/* 139 */       if (this.srv != null) {
/* 140 */         this.srv.stopService();
/* 141 */         this.serviceHasOpen = false;
/*     */       }
/*     */     } catch (Exception e) {
/* 144 */       rsbool = false;
/* 145 */       e.printStackTrace();
/*     */     }
/* 147 */     return rsbool;
/*     */   }
/*     */ 
/*     */   public boolean sendSms(List<String> mobiles, String message)
/*     */   {
/* 152 */     if (this.serviceHasOpen)
/* 153 */       return sendMessage(mobiles, message);
/* 154 */     String comStr = ModemMessageOperator.getInstance().getRightComStr();
/* 155 */     if (comStr == null)
/* 156 */       this.logger.info("[SMS]未能获取到可以发送短信的串口。");
/* 157 */     this.logger.info("[SMS]开始使用串口:" + comStr + "发送短信。");
/* 158 */     if (comStr != null) {
/* 159 */       if (initial(comStr, 9600, "0000")) {
/* 160 */         this.serviceHasOpen = true;
/* 161 */         return sendMessage(mobiles, message);
/*     */       }
/* 163 */       return false;
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 170 */     List list = new ArrayList();
/* 171 */     list.add("15992494490");
/* 172 */     ModemMessage msg = getInstance();
/* 173 */     msg.sendSms(list, "hello 庄晓辉");
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.sms.impl.ModemMessage
 * JD-Core Version:    0.6.2
 */