/*    */ package com.hotent.core.db;
/*    */ 
/*    */ import com.alibaba.druid.pool.DruidDataSource;
/*    */ import com.hotent.core.encrypt.EncryptUtil;
/*    */ 
/*    */ public class CustomDruidDataSource extends DruidDataSource
/*    */ {
/*    */   private static final long serialVersionUID = 7472503562887812863L;
/* 19 */   private volatile String decPwd = "";
/*    */ 
/*    */   public String getPassword()
/*    */   {
/* 25 */     if ("".equals(this.decPwd)) {
/*    */       try
/*    */       {
/* 28 */         String encPwd = super.getPassword();
/* 29 */         this.decPwd = EncryptUtil.decrypt(encPwd);
/*    */       } catch (Exception e) {
/* 31 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 35 */     return this.decPwd;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.CustomDruidDataSource
 * JD-Core Version:    0.6.2
 */