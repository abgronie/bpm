/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLException;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.TrustManagerFactory;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class CertUtil
/*     */ {
/*  30 */   public static Log logger = LogFactory.getLog(CertUtil.class);
/*     */ 
/* 148 */   private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  33 */     File file = get("smtp.gmail.com", 465);
/*  34 */     logger.info(file.getAbsolutePath());
/*     */   }
/*     */ 
/*     */   public static File get(String host, int port) {
/*  38 */     InputStream in = null;
/*  39 */     SSLSocket socket = null;
/*  40 */     OutputStream out = null;
/*     */     try {
/*  42 */       char[] passphrase = "changeit".toCharArray();
/*  43 */       File file = new File("jssecacerts");
/*  44 */       if (!file.isFile()) {
/*  45 */         char SEP = File.separatorChar;
/*  46 */         File dir = new File(new StringBuilder().append(System.getProperty("java.home")).append(SEP).append("lib").append(SEP).append("security").toString());
/*     */ 
/*  48 */         file = new File(dir, "jssecacerts");
/*  49 */         if (!file.isFile()) {
/*  50 */           file = new File(dir, "cacerts");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  57 */       in = new FileInputStream(file);
/*  58 */       KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
/*  59 */       ks.load(in, passphrase);
/*     */ 
/*  65 */       SSLContext context = SSLContext.getInstance("TLS");
/*  66 */       TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
/*  67 */       tmf.init(ks);
/*  68 */       X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
/*  69 */       SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
/*  70 */       context.init(null, new TrustManager[] { tm }, null);
/*  71 */       SSLSocketFactory factory = context.getSocketFactory();
/*     */ 
/*  76 */       socket = (SSLSocket)factory.createSocket(host, port);
/*     */ 
/*  78 */       socket.setSoTimeout(10000);
/*  79 */       if (socket != null) {
/*  80 */         socket.startHandshake();
/*     */       }
/*     */ 
/*  85 */       X509Certificate[] chain = tm.chain;
/*  86 */       if (chain == null) {
/*  87 */         return null;
/*     */       }
/*     */ 
/*  90 */       MessageDigest sha1 = MessageDigest.getInstance("SHA1");
/*  91 */       MessageDigest md5 = MessageDigest.getInstance("MD5");
/*  92 */       for (int i = 0; i < chain.length; i++) {
/*  93 */         X509Certificate cert = chain[i];
/*  94 */         sha1.update(cert.getEncoded());
/*  95 */         md5.update(cert.getEncoded());
/*     */       }
/*     */ 
/*  98 */       int k = 0;
/*     */ 
/* 100 */       X509Certificate cert = chain[k];
/* 101 */       String alias = new StringBuilder().append(host).append("-").append(k + 1).toString();
/* 102 */       ks.setCertificateEntry(alias, cert);
/*     */ 
/* 104 */       File cafile = new File("jssecacerts");
/* 105 */       out = new FileOutputStream(cafile);
/* 106 */       ks.store(out, passphrase);
/*     */ 
/* 109 */       logger.debug(new StringBuilder().append(">>>>   Added certificate to keystore 'jssecacerts' using alias '").append(alias).append("'").toString());
/* 110 */       return cafile;
/*     */     } catch (SSLException e) {
/* 112 */       logger.debug(new StringBuilder().append("明文连接,javax.net.ssl.SSLException:").append(e.getMessage()).toString());
/* 113 */       return null;
/*     */     } catch (KeyStoreException e) {
/* 115 */       e.printStackTrace();
/* 116 */       return null;
/*     */     } catch (FileNotFoundException e) {
/* 118 */       e.printStackTrace();
/* 119 */       return null;
/*     */     } catch (NoSuchAlgorithmException e) {
/* 121 */       e.printStackTrace();
/* 122 */       return null;
/*     */     } catch (SocketTimeoutException e) {
/* 124 */       logger.debug("邮件发送超时");
/* 125 */       return null;
/*     */     } catch (CertificateException e) {
/* 127 */       e.printStackTrace();
/* 128 */       return null;
/*     */     } catch (IOException e) {
/* 130 */       e.printStackTrace();
/* 131 */       return null;
/*     */     }
/*     */     catch (KeyManagementException e)
/*     */     {
/*     */       File file;
/* 133 */       e.printStackTrace();
/* 134 */       return null;
/*     */     } finally {
/* 136 */       logger.info("关闭连接...");
/*     */       try {
/* 138 */         if (in != null) in.close();
/* 139 */         if (socket != null) socket.close();
/* 140 */         if (out != null) out.close(); 
/*     */       }
/* 142 */       catch (IOException e) { e.printStackTrace();
/* 143 */         logger.info("关闭连接失败!");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String toHexString(byte[] bytes)
/*     */   {
/* 151 */     StringBuilder sb = new StringBuilder(bytes.length * 3);
/* 152 */     for (int b : bytes) {
/* 153 */       b &= 255;
/* 154 */       sb.append(HEXDIGITS[(b >> 4)]);
/* 155 */       sb.append(HEXDIGITS[(b & 0xF)]);
/* 156 */       sb.append(' ');
/*     */     }
/* 158 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private static class SavingTrustManager implements X509TrustManager {
/*     */     private final X509TrustManager tm;
/*     */     private X509Certificate[] chain;
/*     */ 
/*     */     SavingTrustManager(X509TrustManager tm) {
/* 167 */       this.tm = tm;
/*     */     }
/*     */ 
/*     */     public X509Certificate[] getAcceptedIssuers() {
/* 171 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
/*     */     {
/* 176 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
/*     */     {
/* 181 */       this.chain = chain;
/* 182 */       this.tm.checkServerTrusted(chain, authType);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.CertUtil
 * JD-Core Version:    0.6.2
 */