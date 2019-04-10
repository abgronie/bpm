/*     */ package com.hotent.core.sms.impl;
/*     */ 
/*     */ import gnu.io.CommPortIdentifier;
/*     */ import gnu.io.PortInUseException;
/*     */ import gnu.io.SerialPort;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ModemMessagePort
/*     */ {
/*     */   public static final int PACKET_LENGTH = 500;
/*     */   private static ModemMessagePort port;
/*  23 */   private static Lock lock = new ReentrantLock();
/*  24 */   protected Logger logger = LoggerFactory.getLogger(ModemMessagePort.class);
/*     */   SerialPort serialPort;
/*     */   CommPortIdentifier identifier;
/*     */   String PortName;
/*     */   OutputStream out;
/*     */   InputStream in;
/*  35 */   String appname = "SerialBean";
/*     */   int timeOut;
/*     */   int baudrate;
/*     */   int dataBits;
/*     */   int stopBits;
/*     */   int parity;
/*     */ 
/*     */   public static ModemMessagePort getInstance()
/*     */   {
/*  52 */     if (port == null) {
/*  53 */       lock.lock();
/*     */       try {
/*  55 */         if (port == null)
/*  56 */           port = new ModemMessagePort();
/*     */       } finally {
/*  58 */         lock.unlock();
/*     */       }
/*     */     }
/*  61 */     return port;
/*     */   }
/*     */ 
/*     */   public void setAppname(String appname)
/*     */   {
/*  72 */     this.appname = appname;
/*     */   }
/*     */ 
/*     */   public void initialize(int timeOut, int baudrate, int dataBits, int stopBits, int parity)
/*     */   {
/*  90 */     this.timeOut = timeOut;
/*  91 */     this.baudrate = baudrate;
/*  92 */     this.dataBits = dataBits;
/*  93 */     this.stopBits = stopBits;
/*  94 */     this.parity = parity;
/*     */   }
/*     */ 
/*     */   public boolean openPort(String portName)
/*     */   {
/* 103 */     boolean rsBool = false;
/* 104 */     this.PortName = portName;
/*     */     try
/*     */     {
/* 107 */       this.identifier = getCommPort();
/* 108 */       if (this.identifier != null)
/*     */       {
/* 110 */         if (!this.identifier.isCurrentlyOwned())
/*     */         {
/* 115 */           this.serialPort = ((SerialPort)this.identifier.open(this.appname, this.timeOut));
/*     */ 
/* 117 */           this.in = this.serialPort.getInputStream();
/* 118 */           this.out = this.serialPort.getOutputStream();
/*     */ 
/* 120 */           this.serialPort.setSerialPortParams(this.baudrate, this.dataBits, this.stopBits, this.parity);
/* 121 */           this.serialPort.setDTR(true);
/* 122 */           this.serialPort.setRTS(true);
/* 123 */           rsBool = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (PortInUseException e)
/*     */     {
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 132 */     return rsBool;
/*     */   }
/*     */ 
/*     */   public CommPortIdentifier getCommPort()
/*     */     throws Exception
/*     */   {
/* 140 */     CommPortIdentifier portIdRs = null;
/* 141 */     portIdRs = CommPortIdentifier.getPortIdentifier(this.PortName);
/* 142 */     return portIdRs;
/*     */   }
/*     */ 
/*     */   public char[] readPackData()
/*     */     throws Exception
/*     */   {
/* 150 */     byte[] readBuffer = new byte[500];
/* 151 */     char[] msgPack = null;
/* 152 */     int numBytes = 0;
/* 153 */     while (this.in.available() > 0) {
/* 154 */       numBytes = this.in.read(readBuffer);
/* 155 */       msgPack = null;
/* 156 */       msgPack = new char[numBytes];
/* 157 */       for (int i = 0; i < numBytes; i++) {
/* 158 */         msgPack[i] = ((char)(readBuffer[i] & 0xFF));
/*     */       }
/*     */     }
/* 161 */     return msgPack;
/*     */   }
/*     */ 
/*     */   public void writePort(char[] bytes)
/*     */     throws IOException
/*     */   {
/* 169 */     for (char b : bytes)
/* 170 */       writePort(b);
/*     */   }
/*     */ 
/*     */   public void writePort(char b)
/*     */     throws IOException
/*     */   {
/* 179 */     this.out.write(b);
/* 180 */     this.out.flush();
/*     */   }
/*     */ 
/*     */   public void closePort()
/*     */   {
/* 188 */     if (this.out != null)
/*     */       try {
/* 190 */         this.out.close();
/* 191 */         this.in.close();
/* 192 */         this.out = null;
/* 193 */         this.in = null;
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/*     */       }
/* 198 */     if (this.serialPort != null) {
/* 199 */       this.serialPort.close();
/* 200 */       this.serialPort = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<String> getAllComPorts()
/*     */   {
/* 209 */     List comList = new ArrayList();
/* 210 */     this.logger.info("[sms]准备获取所有端口…");
/* 211 */     Enumeration en = CommPortIdentifier.getPortIdentifiers();
/* 212 */     CommPortIdentifier portIdRs = null;
/* 213 */     while (en.hasMoreElements()) {
/* 214 */       portIdRs = (CommPortIdentifier)en.nextElement();
/* 215 */       if (portIdRs.getPortType() == 1) {
/* 216 */         comList.add(portIdRs.getName());
/*     */       }
/*     */     }
/* 219 */     this.logger.info("[sms]获取到:" + comList.size() + "个端口");
/* 220 */     return comList;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.sms.impl.ModemMessagePort
 * JD-Core Version:    0.6.2
 */