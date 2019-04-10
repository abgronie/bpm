/*    */ package com.hotent.core.page;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PageList<E> extends ArrayList<E>
/*    */ {
/* 27 */   private PageBean pageBean = new PageBean();
/*    */ 
/*    */   public PageBean getPageBean()
/*    */   {
/* 32 */     return this.pageBean;
/*    */   }
/*    */ 
/*    */   public void setPageBean(PageBean pageBean)
/*    */   {
/* 39 */     this.pageBean = pageBean;
/*    */   }
/*    */ 
/*    */   public int getTotalPage()
/*    */   {
/* 44 */     return this.pageBean.getTotalPage();
/*    */   }
/*    */ 
/*    */   public int getTotalCount() {
/* 48 */     return this.pageBean.getTotalCount();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.page.PageList
 * JD-Core Version:    0.6.2
 */