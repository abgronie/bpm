/*    */ package com.hotent.core.db.datasource;
/*    */ 
/*    */ public class DataSourceException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 3148019938789322656L;
/*    */ 
/*    */   public DataSourceException(String msg)
/*    */   {
/* 22 */     super(msg);
/*    */   }
/*    */ 
/*    */   public DataSourceException(String msg, Throwable throwable)
/*    */   {
/* 27 */     super(msg, throwable);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.datasource.DataSourceException
 * JD-Core Version:    0.6.2
 */