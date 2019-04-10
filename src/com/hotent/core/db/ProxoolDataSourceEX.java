/*    */ package com.hotent.core.db;
/*    */ 
/*    */ import com.hotent.core.encrypt.EncryptUtil;
/*    */ import org.logicalcobwebs.proxool.ProxoolDataSource;
/*    */ 
/*    */ public class ProxoolDataSourceEX extends ProxoolDataSource
/*    */ {
/* 21 */   private String password = "";
/*    */ 
/*    */   public void setPassword(String password)
/*    */   {
/* 27 */     String pwd = "";
/*    */     try {
/* 29 */       pwd = EncryptUtil.decrypt(password);
/*    */     }
/*    */     catch (Exception e) {
/* 32 */       e.printStackTrace();
/*    */     }
/* 34 */     this.password = pwd;
/*    */   }
/*    */ 
/*    */   public String getPassword() {
/* 38 */     return this.password;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.ProxoolDataSourceEX
 * JD-Core Version:    0.6.2
 */