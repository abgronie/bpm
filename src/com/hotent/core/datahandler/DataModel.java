/*    */ package com.hotent.core.datahandler;
/*    */ 
/*    */ import com.hotent.core.util.StringUtil;
/*    */ 
/*    */ public class DataModel
/*    */ {
/*  7 */   private String pk = "";
/*  8 */   private String tableName = "";
/*  9 */   private String action = "";
/*    */ 
/* 12 */   public static String ACTION_UPDATE = "upd";
/* 13 */   public static String ACTION_DELETE = "del";
/*    */ 
/*    */   public DataModel()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DataModel(String pk, String tableName, String action) {
/* 20 */     this.pk = pk;
/* 21 */     this.tableName = tableName;
/* 22 */     this.action = action;
/*    */   }
/*    */   public String getPk() {
/* 25 */     return this.pk;
/*    */   }
/*    */   public void setPk(String pk) {
/* 28 */     this.pk = pk;
/*    */   }
/*    */   public String getTableName() {
/* 31 */     return this.tableName;
/*    */   }
/*    */   public void setTableName(String tableName) {
/* 34 */     this.tableName = tableName;
/*    */   }
/*    */ 
/*    */   public String getAction() {
/* 38 */     if (StringUtil.isEmpty(this.action)) {
/* 39 */       return ACTION_UPDATE;
/*    */     }
/* 41 */     return this.action;
/*    */   }
/*    */ 
/*    */   public void setAction(String action)
/*    */   {
/* 47 */     this.action = action;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.datahandler.DataModel
 * JD-Core Version:    0.6.2
 */