/*    */ package com.hotent.core.datakey.impl;
/*    */ 
/*    */ import com.hotent.core.datakey.IKeyGenerator;
/*    */ import com.hotent.core.util.UniqueIdUtil;
/*    */ 
/*    */ public class TimeGenerator
/*    */   implements IKeyGenerator
/*    */ {
/*    */   public Object nextId()
/*    */     throws Exception
/*    */   {
/* 16 */     return Long.valueOf(UniqueIdUtil.genId());
/*    */   }
/*    */ 
/*    */   public void setAlias(String alias)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.datakey.impl.TimeGenerator
 * JD-Core Version:    0.6.2
 */