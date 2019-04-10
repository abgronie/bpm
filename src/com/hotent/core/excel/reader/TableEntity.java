/*    */ package com.hotent.core.excel.reader;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class TableEntity
/*    */ {
/*  8 */   public static final Short IS_MAIN = Short.valueOf((short)1);
/*    */ 
/* 10 */   public static final Short NOT_MAIN = Short.valueOf((short)0);
/*    */   private String name;
/* 18 */   private Short isMain = IS_MAIN;
/*    */   private List<DataEntity> dataEntityList;
/*    */   private List<TableEntity> subTableEntityList;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 31 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 35 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public Short getIsMain() {
/* 39 */     return this.isMain;
/*    */   }
/*    */ 
/*    */   public void setIsMain(Short isMain) {
/* 43 */     this.isMain = isMain;
/*    */   }
/*    */ 
/*    */   public List<DataEntity> getDataEntityList() {
/* 47 */     return this.dataEntityList;
/*    */   }
/*    */ 
/*    */   public void setDataEntityList(List<DataEntity> dataEntityList) {
/* 51 */     this.dataEntityList = dataEntityList;
/*    */   }
/*    */ 
/*    */   public List<TableEntity> getSubTableEntityList() {
/* 55 */     return this.subTableEntityList;
/*    */   }
/*    */ 
/*    */   public void setSubTableEntityList(List<TableEntity> subTableEntityList) {
/* 59 */     this.subTableEntityList = subTableEntityList;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.reader.TableEntity
 * JD-Core Version:    0.6.2
 */