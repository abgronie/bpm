/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.LineNumberReader;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class SystemInfo
/*    */ {
/*    */   public static String getMemoryInfo()
/*    */   {
/* 22 */     return Runtime.getRuntime().totalMemory() / 1024L / 1024L + "M/" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + "M";
/*    */   }
/*    */ 
/*    */   public static String getJdkInfo()
/*    */   {
/* 32 */     return System.getProperty("java.version");
/*    */   }
/*    */ 
/*    */   public static String getMacInfo()
/*    */   {
/* 41 */     String mac = "";
/*    */     try {
/* 43 */       Process process = Runtime.getRuntime().exec("ipconfig /all");
/* 44 */       InputStreamReader ir = new InputStreamReader(process.getInputStream(), "gbk");
/*    */ 
/* 46 */       LineNumberReader input = new LineNumberReader(ir);
/* 47 */       String line = null;
/* 48 */       while ((line = input.readLine()) != null)
/*    */       {
/* 50 */         Pattern regex = Pattern.compile("\\w{2}-\\w{2}-\\w{2}-\\w{2}-\\w{2}-\\w{2}", 106);
/*    */ 
/* 54 */         Matcher regexMatcher = regex.matcher(line);
/* 55 */         if (regexMatcher.find()) {
/* 56 */           String tmp = regexMatcher.group(0);
/* 57 */           if (!tmp.equals("00-00-00-00-00-00"))
/* 58 */             mac = mac + regexMatcher.group(0) + ",";
/*    */         }
/*    */       }
/*    */     } catch (Exception e) {
/* 62 */       return "";
/*    */     }
/* 64 */     if (mac.length() > 0)
/* 65 */       mac = mac.substring(0, mac.length() - 1);
/* 66 */     return mac;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.SystemInfo
 * JD-Core Version:    0.6.2
 */