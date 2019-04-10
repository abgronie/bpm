/*     */ package com.hotent.core.fulltext.impl;
/*     */ 
/*     */ import com.hotent.core.fulltext.IDocument;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public class TextImpl
/*     */   implements IDocument
/*     */ {
/*  17 */   private String fileName = "";
/*     */ 
/*     */   public void setFileName(String fileName) {
/*  20 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   public String extract()
/*     */   {
/*  25 */     StringBuffer sb = new StringBuffer();
/*     */     try {
/*  27 */       File file = new File(this.fileName);
/*  28 */       String charset = getCharset(file);
/*     */ 
/*  30 */       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), charset));
/*     */       String str;
/*  34 */       while ((str = in.readLine()) != null) {
/*  35 */         sb.append(str + "\r\n");
/*     */       }
/*  37 */       in.close();
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/*  40 */       e.printStackTrace();
/*     */     } catch (FileNotFoundException e) {
/*  42 */       e.printStackTrace();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*  47 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getCharset(File file)
/*     */   {
/*  52 */     String charset = "GBK";
/*  53 */     byte[] first3Bytes = new byte[3];
/*     */     try {
/*  55 */       boolean checked = false;
/*  56 */       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
/*     */ 
/*  58 */       bis.mark(0);
/*  59 */       int read = bis.read(first3Bytes, 0, 3);
/*  60 */       if (read == -1)
/*  61 */         return charset;
/*  62 */       if ((first3Bytes[0] == -1) && (first3Bytes[1] == -2)) {
/*  63 */         charset = "UTF-16LE";
/*  64 */         checked = true;
/*  65 */       } else if ((first3Bytes[0] == -2) && (first3Bytes[1] == -1))
/*     */       {
/*  67 */         charset = "UTF-16BE";
/*  68 */         checked = true;
/*  69 */       } else if ((first3Bytes[0] == -17) && (first3Bytes[1] == -69) && (first3Bytes[2] == -65))
/*     */       {
/*  72 */         charset = "UTF-8";
/*  73 */         checked = true;
/*     */       }
/*  75 */       bis.reset();
/*     */ 
/*  77 */       if (!checked) {
/*  78 */         int loc = 0;
/*  79 */         while ((read = bis.read()) != -1) {
/*  80 */           loc++;
/*  81 */           if (read < 240)
/*     */           {
/*  84 */             if ((128 > read) || (read > 191))
/*     */             {
/*  86 */               if ((192 <= read) && (read <= 223)) {
/*  87 */                 read = bis.read();
/*  88 */                 if ((128 > read) || (read > 191))
/*     */                 {
/*     */                   break;
/*     */                 }
/*     */ 
/*     */               }
/*  95 */               else if ((224 <= read) && (read <= 239)) {
/*  96 */                 read = bis.read();
/*  97 */                 if ((128 <= read) && (read <= 191)) {
/*  98 */                   read = bis.read();
/*  99 */                   if ((128 <= read) && (read <= 191)) {
/* 100 */                     charset = "UTF-8";
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 110 */       bis.close();
/*     */     } catch (Exception e) {
/* 112 */       e.printStackTrace();
/*     */     }
/* 114 */     return charset;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.impl.TextImpl
 * JD-Core Version:    0.6.2
 */