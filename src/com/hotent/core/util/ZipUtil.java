/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.lingala.zip4j.core.ZipFile;
/*     */ import net.lingala.zip4j.exception.ZipException;
/*     */ import net.lingala.zip4j.model.FileHeader;
/*     */ import net.lingala.zip4j.model.ZipParameters;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ public class ZipUtil
/*     */ {
/*     */   public static void zip(String path)
/*     */   {
/*  30 */     zip(path, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public static void zip(String path, Boolean isDelete)
/*     */   {
/*  42 */     ZipFile zipFile = null;
/*     */     try {
/*  44 */       ZipParameters parameters = new ZipParameters();
/*  45 */       parameters.setCompressionMethod(8);
/*  46 */       parameters.setCompressionLevel(5);
/*  47 */       File file = new File(path);
/*  48 */       if (file.isDirectory()) {
/*  49 */         zipFile = new ZipFile(new File(path + ".zip"));
/*  50 */         zipFile.setFileNameCharset("utf-8");
/*  51 */         zipFile.addFolder(path, parameters);
/*     */       } else {
/*  53 */         zipFile = new ZipFile(new File(path.split(".")[0] + ".zip"));
/*  54 */         zipFile.setFileNameCharset("utf-8");
/*  55 */         zipFile.addFile(file, parameters);
/*     */       }
/*     */ 
/*  58 */       if (isDelete.booleanValue())
/*  59 */         FileUtil.deleteDir(file);
/*     */     } catch (ZipException e) {
/*  61 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void zipSetPass(String path, Boolean isDelete, String password)
/*     */   {
/*  76 */     ZipFile zipFile = null;
/*     */     try {
/*  78 */       ZipParameters parameters = new ZipParameters();
/*  79 */       parameters.setCompressionMethod(8);
/*  80 */       parameters.setCompressionLevel(5);
/*     */ 
/*  82 */       parameters.setEncryptFiles(true);
/*  83 */       parameters.setEncryptionMethod(0);
/*  84 */       parameters.setPassword(password);
/*  85 */       File file = new File(path);
/*  86 */       if (file.isDirectory()) {
/*  87 */         zipFile = new ZipFile(new File(path + ".zip"));
/*  88 */         zipFile.setFileNameCharset("utf-8");
/*  89 */         zipFile.addFolder(path, parameters);
/*     */       } else {
/*  91 */         zipFile = new ZipFile(new File(path.split(".")[0] + ".zip"));
/*  92 */         zipFile.setFileNameCharset("utf-8");
/*  93 */         zipFile.addFile(file, parameters);
/*     */       }
/*  95 */       if (isDelete.booleanValue())
/*  96 */         FileUtil.deleteDir(new File(path));
/*     */     }
/*     */     catch (ZipException e) {
/*  99 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void unZip(String filePath, String toPath, String password)
/*     */   {
/*     */     try
/*     */     {
/* 116 */       unZipFile(new ZipFile(filePath), toPath, password);
/*     */     } catch (ZipException e) {
/* 118 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String unZipFile(MultipartFile multipartFile, String toPath)
/*     */     throws Exception
/*     */   {
/* 133 */     String originalFilename = multipartFile.getOriginalFilename();
/* 134 */     String destPath = toPath + originalFilename;
/*     */ 
/* 136 */     createFilePath(destPath, originalFilename);
/* 137 */     File file = new File(destPath);
/* 138 */     if (file.exists())
/* 139 */       file.delete();
/* 140 */     multipartFile.transferTo(file);
/* 141 */     ZipFile zipFile = new ZipFile(file);
/* 142 */     zipFile.setFileNameCharset("GBK");
/* 143 */     if (zipFile.isEncrypted())
/* 144 */       zipFile.setPassword("");
/* 145 */     if (!zipFile.isValidZipFile())
/* 146 */       throw new ZipException("压缩文件不合法,可能被损坏.");
/* 147 */     String fileDir = "";
/* 148 */     zipFile.extractAll(toPath);
/*     */ 
/* 150 */     List<FileHeader> fileHeaderList = zipFile.getFileHeaders();
/* 151 */     for (FileHeader fileHeader : fileHeaderList) {
/* 152 */       String dirName = fileHeader.getFileName();
/* 153 */       if (fileHeader.isDirectory()) {
/* 154 */         if (dirName.endsWith("\\"))
/* 155 */           fileDir = dirName.substring(0, dirName.lastIndexOf("\\"));
/* 156 */         else if (dirName.endsWith("/"))
/* 157 */           fileDir = dirName.substring(0, dirName.lastIndexOf("/"));
/*     */         else {
/* 159 */           fileDir = dirName.substring(0, dirName.lastIndexOf(File.separator));
/*     */         }
/*     */       }
/*     */     }
/* 163 */     FileUtil.deleteDir(file);
/*     */ 
/* 165 */     return fileDir;
/*     */   }
/*     */ 
/*     */   public static void unZipFile(MultipartFile multipartFile, String toPath, String password)
/*     */   {
/* 177 */     String originalFilename = multipartFile.getOriginalFilename();
/* 178 */     String destPath = toPath + originalFilename;
/*     */     try {
/* 180 */       createFilePath(destPath, originalFilename);
/* 181 */       File file = new File(destPath);
/* 182 */       if (file.exists())
/* 183 */         file.delete();
/* 184 */       multipartFile.transferTo(file);
/* 185 */       unZipFile(new ZipFile(file), toPath, password);
/*     */     } catch (Exception e) {
/* 187 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void unZipFile(ZipFile zipFile, String toPath, String password)
/*     */   {
/*     */     try
/*     */     {
/* 199 */       if (zipFile.isEncrypted())
/* 200 */         zipFile.setPassword(password);
/* 201 */       List fileHeaderList = zipFile.getFileHeaders();
				Iterator i$;
/* 202 */       for (i$ = fileHeaderList.iterator(); i$.hasNext(); ) { 
					Object o = i$.next();
/* 203 */         FileHeader fileHeader = (FileHeader)o;
/* 204 */         zipFile.extractFile(fileHeader, toPath);
/*     */       }
/*     */     }
/*     */     catch (ZipException e)
/*     */     {
/*     */       
/* 207 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String createFilePath(String tempPath, String fileName)
/*     */   {
/* 219 */     File file = new File(tempPath);
/*     */ 
/* 221 */     if (!file.exists())
/* 222 */       file.mkdirs();
/* 223 */     return file.getPath() + File.separator + fileName;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ZipUtil
 * JD-Core Version:    0.6.2
 */