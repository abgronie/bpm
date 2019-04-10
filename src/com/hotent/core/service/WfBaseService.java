/*    */ package com.hotent.core.service;
/*    */ 
/*    */ import com.hotent.core.db.WfBaseDao;
/*    */ import com.hotent.core.page.PageList;
/*    */ import com.hotent.core.web.query.QueryFilter;
/*    */ 
/*    */ public abstract class WfBaseService<E> extends BaseService<E>
/*    */ {
/*    */   public PageList getMyDraft(Long userId, QueryFilter queryFilter)
/*    */   {
/* 26 */     return ((WfBaseDao)getEntityDao()).getDraftByUser(userId, queryFilter);
/*    */   }
/*    */ 
/*    */   public PageList getMyEnd(Long userId, QueryFilter queryFilter)
/*    */   {
/* 36 */     return ((WfBaseDao)getEntityDao()).getEndByUser(userId, queryFilter);
/*    */   }
/*    */ 
/*    */   public PageList getMyTodoTask(Long userId, QueryFilter queryFilter)
/*    */   {
/* 46 */     return ((WfBaseDao)getEntityDao()).getMyTodoTask(userId, queryFilter);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.service.WfBaseService
 * JD-Core Version:    0.6.2
 */