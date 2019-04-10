/*    */ package com.hotent.core.datahandler;
/*    */ 
/*    */ import com.hotent.core.util.AppUtil;
/*    */ 
/*    */ public class DataPublishUtil
/*    */ {
/*    */   public static void publishData(DataModel model)
/*    */   {
/* 19 */     AppUtil.publishEvent(new UpdDataEvent(model));
/*    */   }
/*    */ 
/*    */   public static void publishData(String tableName, String pk)
/*    */   {
/* 28 */     DataModel model = new DataModel();
/* 29 */     model.setPk(pk);
/* 30 */     model.setTableName(tableName);
/* 31 */     AppUtil.publishEvent(new UpdDataEvent(model));
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.datahandler.DataPublishUtil
 * JD-Core Version:    0.6.2
 */