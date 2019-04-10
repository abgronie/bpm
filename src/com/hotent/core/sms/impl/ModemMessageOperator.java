/*     */ package com.hotent.core.sms.impl;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ModemMessageOperator
/*     */ {
/*     */   private static ModemMessageOperator instance;
/*     */   private static ModemMessagePort messagePort;
/*  23 */   private static Lock lock = new ReentrantLock();
/*  24 */   protected static Logger logger = LoggerFactory.getLogger(ModemMessageOperator.class);
/*     */   int portId;
/*     */   int baudrate;
/*     */   int timeOut;
/*     */   int dataBits;
/*     */   int stopBits;
/*     */   int parity;
/*     */   int funCode;
/*     */   int dataLen;
/*     */   int appendMillsec;
/*     */   int bytes;
/*     */   int frameInterval;
/*     */ 
/*     */   private ModemMessageOperator()
/*     */   {
/*  52 */     messagePort = ModemMessagePort.getInstance();
/*  53 */     this.timeOut = 60;
/*  54 */     this.baudrate = 9600;
/*  55 */     this.dataBits = 8;
/*  56 */     this.stopBits = 1;
/*  57 */     this.parity = 0;
/*  58 */     this.funCode = 3;
/*  59 */     this.dataLen = 4;
/*  60 */     this.appendMillsec = 38;
/*  61 */     this.bytes = 20;
/*     */   }
/*     */ 
/*     */   public static ModemMessageOperator getInstance() {
/*  65 */     if (instance == null) {
/*  66 */       lock.lock();
/*     */       try {
/*  68 */         if (instance == null)
/*  69 */           instance = new ModemMessageOperator();
/*     */       } catch (Exception ex) {
/*  71 */         logger.info("[sms]error:" + ex.getMessage());
/*     */       } finally {
/*  73 */         lock.unlock();
/*     */       }
/*     */     }
/*  76 */     return instance;
/*     */   }
/*     */ 
/*     */   public boolean openPort(String portStr, int baudrate, String appName)
/*     */   {
/*  90 */     boolean rsBool = false;
/*     */ 
/*  93 */     messagePort.initialize(this.timeOut, baudrate, this.dataBits, this.stopBits, this.parity);
/*  94 */     messagePort.setAppname(appName.toUpperCase());
/*     */ 
/*  96 */     if (messagePort.openPort(portStr)) {
/*  97 */       rsBool = true;
/*     */ 
/*  99 */       this.frameInterval = getFrameInterval(this.appendMillsec, this.bytes, baudrate);
/*     */     }
/* 101 */     return rsBool;
/*     */   }
/*     */ 
/*     */   public void writeByte(char[] rs)
/*     */     throws Exception
/*     */   {
/* 110 */     messagePort.writePort(rs);
/*     */ 
/* 116 */     Thread.sleep(this.frameInterval);
/*     */   }
/*     */ 
/*     */   public boolean readByte(String portStr)
/*     */     throws Exception
/*     */   {
/* 124 */     boolean rsbool = false;
/* 125 */     String rsStr = "";
/*     */ 
/* 127 */     char[] rsByte = messagePort.readPackData();
/* 128 */     if (rsByte != null)
/*     */     {
/* 130 */       for (char c : rsByte) {
/* 131 */         rsStr = rsStr + c;
/*     */       }
/* 133 */       if (rsStr.indexOf("OK") > 0) {
/* 134 */         logger.info("找到" + portStr + ":短信模块串口");
/* 135 */         rsbool = true;
/*     */       }
/*     */     } else {
/* 138 */       logger.info(portStr + ":不是短信模块串口");
/*     */     }
/*     */ 
/* 141 */     return rsbool;
/*     */   }
/*     */ 
/*     */   public String getRightComStr()
/*     */   {
/* 149 */     String rightCom = null;
/*     */ 
/* 151 */     List<String> portList = messagePort.getAllComPorts();
/* 152 */     if (portList.size() > 0)
/*     */     {
/* 155 */       for (String portStr : portList)
/*     */       {
/* 157 */         if (testSms(portStr)) {
/* 158 */           rightCom = portStr;
/* 159 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 163 */     return rightCom;
/*     */   }
/*     */ 
/*     */   private boolean testSms(String portStr)
/*     */   {
/* 173 */     boolean rsBool = false;
/*     */     try {
/* 175 */       rsBool = instance.openPort(portStr, this.baudrate, "sms_port");
/* 176 */       String atCommand = "AT\r";
/* 177 */       char[] atOrder = atCommand.toCharArray();
/* 178 */       if (rsBool)
/* 179 */         instance.writeByte(atOrder);
/* 180 */       if (rsBool)
/*     */       {
/* 182 */         rsBool = instance.readByte(portStr);
/* 183 */         instance.closePort();
/*     */       }
/*     */     } catch (Exception e) {
/* 186 */       rsBool = false;
/* 187 */       e.printStackTrace();
/*     */     }
/* 189 */     return rsBool;
/*     */   }
/*     */ 
/*     */   public void closePort()
/*     */   {
/* 197 */     messagePort.closePort();
/*     */   }
/*     */ 
/*     */   public static int getFrameInterval(int appendMillsec, int dataLen, int baudrate)
/*     */   {
/* 214 */     int rsInt = (int)Math.ceil(12 * (dataLen + 4) * 1000 / baudrate) + appendMillsec;
/* 215 */     return rsInt;
/*     */   }
/*     */ 
/*     */   public static final String bytesToHexString(char[] bArray)
/*     */   {
/* 225 */     StringBuffer sb = new StringBuffer(bArray.length);
/*     */ 
/* 228 */     for (int i = 0; i < bArray.length; i++) {
/* 229 */       String sTemp = Integer.toHexString(0xFF & bArray[i]);
/* 230 */       if (sTemp.length() < 2) {
/* 231 */         sb.append(0);
/*     */       }
/* 233 */       sb.append(sTemp.toUpperCase() + " ");
/*     */     }
/* 235 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.sms.impl.ModemMessageOperator
 * JD-Core Version:    0.6.2
 */