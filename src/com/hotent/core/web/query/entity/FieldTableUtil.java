/*    */ package com.hotent.core.web.query.entity;
/*    */ 
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class FieldTableUtil
/*    */ {
/*    */   public static String fixFieldName(String fieldName, String tableName, boolean isAs)
/*    */   {
/* 19 */     return fixFieldName(fieldName, fieldName, tableName, isAs);
/*    */   }
/*    */ 
/*    */   public static String fixFieldName(String fieldName, String tableName)
/*    */   {
/* 32 */     return fixFieldName(fieldName, tableName, false);
/*    */   }
/*    */ 
/*    */   public static String fixFieldName(String fieldName, String entityName, String tableName, boolean isAs)
/*    */   {
/* 50 */     if (StringUtils.isEmpty(tableName))
/* 51 */       return fieldName;
/* 52 */     StringBuilder sb = new StringBuilder();
/* 53 */     sb.append(tableName.toLowerCase()).append(".").append(fieldName);
/* 54 */     if (isAs)
/* 55 */       sb.append(" AS ").append(entityName);
/* 56 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.entity.FieldTableUtil
 * JD-Core Version:    0.6.2
 */