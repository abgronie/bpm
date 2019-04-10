/*    */ package com.hotent.core.db;
/*    */ 
/*    */ import com.hotent.core.page.PageList;
/*    */ import com.hotent.core.web.query.QueryFilter;
/*    */ 
/*    */ public abstract class WfBaseDao<E> extends BaseDao<E>
/*    */ {
/*    */   public PageList getMyTodoTask(Long userId, QueryFilter queryFilter)
/*    */   {
/* 20 */     queryFilter.addFilter("userId", userId);
/* 21 */     return (PageList)getBySqlKey("getMyTodoTask", queryFilter);
/*    */   }
/*    */ 
/*    */   public PageList getDraftByUser(Long userId, QueryFilter queryFilter)
/*    */   {
/* 31 */     queryFilter.addFilter("userId", userId);
/* 32 */     return (PageList)getBySqlKey("getDraftByUser", queryFilter);
/*    */   }
/*    */ 
/*    */   public PageList getEndByUser(Long userId, QueryFilter queryFilter)
/*    */   {
/* 42 */     queryFilter.addFilter("userId", userId);
/* 43 */     return (PageList)getBySqlKey("getEndByUser", queryFilter);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.WfBaseDao
 * JD-Core Version:    0.6.2
 */