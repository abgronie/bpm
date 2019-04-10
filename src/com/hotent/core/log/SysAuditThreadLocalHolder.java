/*    */ package com.hotent.core.log;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SysAuditThreadLocalHolder
/*    */ {
/*    */   public static final short RESULT_SUCCESS = 0;
/*    */   public static final short RESULT_FAIL = 0;
/* 19 */   private static ThreadLocal<String> detailLocal = new ThreadLocal();
/*    */ 
/* 23 */   private static ThreadLocal<Short> resultLocal = new ThreadLocal();
/*    */ 
/* 27 */   private static ThreadLocal<Map<String, Object>> paramertersLocal = new ThreadLocal();
/*    */ 
/* 31 */   private static ThreadLocal<Boolean> shouldLogLocal = new ThreadLocal();
/*    */ 
/*    */   public static String getDetail() {
/* 34 */     return (String)detailLocal.get();
/*    */   }
/*    */ 
/*    */   public static void setDatail(String detail) {
/* 38 */     detailLocal.set(detail);
/*    */   }
/*    */ 
/*    */   public static void clearDetail()
/*    */   {
/* 43 */     detailLocal.remove();
/*    */   }
/*    */ 
/*    */   public static Short getResult() {
/* 47 */     return (Short)resultLocal.get();
/*    */   }
/*    */ 
/*    */   public static void setResult(Short result) {
/* 51 */     resultLocal.set(result);
/*    */   }
/*    */   public static void clearResult() {
/* 54 */     resultLocal.remove();
/*    */   }
/*    */ 
/*    */   public static Boolean getShouldLog() {
/* 58 */     return (Boolean)shouldLogLocal.get();
/*    */   }
/*    */   public static void setShouldLog(Boolean shouldLog) {
/* 61 */     shouldLogLocal.set(shouldLog);
/*    */   }
/*    */   public static void clearShouldLog() {
/* 64 */     shouldLogLocal.remove();
/*    */   }
/*    */ 
/*    */   public static Map<String, Object> getParamerters() {
/* 68 */     if (paramertersLocal.get() == null) {
/* 69 */       paramertersLocal.set(new HashMap());
/*    */     }
/* 71 */     return (Map)paramertersLocal.get();
/*    */   }
/*    */ 
/*    */   public static Object getParamerter(String key) {
/* 75 */     if (paramertersLocal.get() == null) {
/* 76 */       paramertersLocal.set(new HashMap());
/*    */     }
/* 78 */     return ((Map)paramertersLocal.get()).get(key);
/*    */   }
/*    */ 
/*    */   public static void putParamerter(String key, Object value) {
/* 82 */     if (paramertersLocal.get() == null) {
/* 83 */       paramertersLocal.set(new HashMap());
/*    */     }
/* 85 */     ((Map)paramertersLocal.get()).put(key, value);
/*    */   }
/*    */   public static void clearParameters() {
/* 88 */     paramertersLocal.remove();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.log.SysAuditThreadLocalHolder
 * JD-Core Version:    0.6.2
 */