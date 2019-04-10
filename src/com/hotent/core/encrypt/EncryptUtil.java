/*     */ package com.hotent.core.encrypt;
/*     */ 
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESKeySpec;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ 
/*     */ public class EncryptUtil
/*     */ {
/*     */   private static final String key = "@#$%^6a7";
/*     */ 
/*     */   public static String encryptMd5(String inStr)
/*     */     throws Exception
/*     */   {
/*  30 */     MessageDigest md = null;
/*  31 */     String out = null;
/*     */     try {
/*  33 */       md = MessageDigest.getInstance("MD5");
/*  34 */       byte[] digest = md.digest(inStr.getBytes());
/*  35 */       return new String(Base64.encodeBase64(digest));
/*     */     } catch (NoSuchAlgorithmException e) {
/*  37 */       e.printStackTrace();
/*  38 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static synchronized String encryptSha256(String inputStr)
/*     */   {
/*     */     try
/*     */     {
/*  53 */       MessageDigest md = MessageDigest.getInstance("SHA-256");
/*  54 */       byte[] digest = md.digest(inputStr.getBytes("UTF-8"));
/*  55 */       return new String(Base64.encodeBase64(digest)); } catch (Exception e) {
/*     */     }
/*  57 */     return null;
/*     */   }
/*     */ 
/*     */   private static String byte2hex(byte[] b)
/*     */   {
/*  64 */     String hs = "";
/*  65 */     String stmp = "";
/*     */ 
/*  67 */     for (int n = 0; n < b.length; n++) {
/*  68 */       stmp = Integer.toHexString(b[n] & 0xFF);
/*  69 */       if (stmp.length() == 1)
/*  70 */         hs = hs + "0" + stmp;
/*     */       else {
/*  72 */         hs = hs + stmp;
/*     */       }
/*     */     }
/*     */ 
/*  76 */     return hs.toLowerCase();
/*     */   }
/*     */ 
/*     */   public static String decrypt(String message)
/*     */     throws Exception
/*     */   {
/*  92 */     byte[] bytesrc = stringToBytes(message);
/*  93 */     Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
/*  94 */     DESKeySpec desKeySpec = new DESKeySpec("@#$%^6a7".getBytes("UTF-8"));
/*  95 */     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/*  96 */     SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
/*  97 */     IvParameterSpec iv = new IvParameterSpec("@#$%^6a7".getBytes("UTF-8"));
/*     */ 
/*  99 */     cipher.init(2, secretKey, iv);
/*     */ 
/* 101 */     byte[] retByte = cipher.doFinal(bytesrc);
/* 102 */     return new String(retByte, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String encrypt(String message)
/*     */     throws Exception
/*     */   {
/* 112 */     Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
/*     */ 
/* 114 */     DESKeySpec desKeySpec = new DESKeySpec("@#$%^6a7".getBytes("UTF-8"));
/*     */ 
/* 116 */     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/* 117 */     SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
/* 118 */     IvParameterSpec iv = new IvParameterSpec("@#$%^6a7".getBytes("UTF-8"));
/* 119 */     cipher.init(1, secretKey, iv);
/*     */ 
/* 121 */     String str = bytesToString(cipher.doFinal(message.getBytes("UTF-8")));
/* 122 */     return str;
/*     */   }
/*     */ 
/*     */   private static byte[] stringToBytes(String temp)
/*     */   {
/* 131 */     byte[] digest = new byte[temp.length() / 2];
/* 132 */     for (int i = 0; i < digest.length; i++) {
/* 133 */       String byteString = temp.substring(2 * i, 2 * i + 2);
/* 134 */       int byteValue = Integer.parseInt(byteString, 16);
/* 135 */       digest[i] = ((byte)byteValue);
/*     */     }
/*     */ 
/* 138 */     return digest;
/*     */   }
/*     */ 
/*     */   private static String bytesToString(byte[] b)
/*     */   {
/* 147 */     StringBuffer hexString = new StringBuffer();
/* 148 */     for (int i = 0; i < b.length; i++) {
/* 149 */       String plainText = Integer.toHexString(0xFF & b[i]);
/* 150 */       if (plainText.length() < 2)
/* 151 */         plainText = "0" + plainText;
/* 152 */       hexString.append(plainText);
/*     */     }
/*     */ 
/* 155 */     return hexString.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.encrypt.EncryptUtil
 * JD-Core Version:    0.6.2
 */