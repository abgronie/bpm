/*    */ package com.hotent.core.db;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.transaction.TransactionStatus;
/*    */ import org.springframework.transaction.support.TransactionCallback;
/*    */ import org.springframework.transaction.support.TransactionTemplate;
/*    */ 
/*    */ public class RollbackJdbcTemplate
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private TransactionTemplate txTemplate;
/*    */ 
/*    */   public Object executeRollBack(final IRollBack rollBack, final String script, final Map<String, Object> map)
/*    */   {
/* 17 */     return this.txTemplate.execute(new TransactionCallback()
/*    */     {
/*    */       public Object doInTransaction(TransactionStatus status) {
/*    */         try {
/* 21 */           return rollBack.execute(script, map);
/*    */         } catch (Exception ex) {
/* 23 */           throw new RuntimeException(ex.getMessage());
/*    */         } finally {
/* 25 */           status.setRollbackOnly();
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.RollbackJdbcTemplate
 * JD-Core Version:    0.6.2
 */