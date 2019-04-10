/*    */ package com.hotent.core.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BaseModel
/*    */   implements Serializable
/*    */ {
/*    */   protected Long createBy;
/*    */   protected Date createtime;
/*    */   protected Date updatetime;
/*    */   protected Long updateBy;
/*    */ 
/*    */   public Long getCreateBy()
/*    */   {
/* 43 */     return this.createBy;
/*    */   }
/*    */ 
/*    */   public void setCreateBy(Long createBy)
/*    */   {
/* 48 */     this.createBy = createBy;
/*    */   }
/*    */ 
/*    */   public Date getCreatetime()
/*    */   {
/* 53 */     return this.createtime;
/*    */   }
/*    */ 
/*    */   public void setCreatetime(Date createtime)
/*    */   {
/* 58 */     this.createtime = createtime;
/*    */   }
/*    */ 
/*    */   public Date getUpdatetime()
/*    */   {
/* 63 */     return this.updatetime;
/*    */   }
/*    */ 
/*    */   public void setUpdatetime(Date updatetime)
/*    */   {
/* 68 */     this.updatetime = updatetime;
/*    */   }
/*    */ 
/*    */   public Long getUpdateBy()
/*    */   {
/* 73 */     return this.updateBy;
/*    */   }
/*    */ 
/*    */   public void setUpdateBy(Long updateBy)
/*    */   {
/* 78 */     this.updateBy = updateBy;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.BaseModel
 * JD-Core Version:    0.6.2
 */