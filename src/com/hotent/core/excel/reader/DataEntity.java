/*    */ package com.hotent.core.excel.reader;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class DataEntity
/*    */ {
/*    */   private String pkName;
/*    */   private String pkVal;
/*    */   private List<FieldEntity> fieldEntityList;
/*    */ 
/*    */   public String getPkName()
/*    */   {
/* 14 */     return this.pkName;
/*    */   }
/*    */ 
/*    */   public void setPkName(String pkName) {
/* 18 */     this.pkName = pkName;
/*    */   }
/*    */ 
/*    */   public String getPkVal() {
/* 22 */     return this.pkVal;
/*    */   }
/*    */ 
/*    */   public void setPkVal(String pkVal) {
/* 26 */     this.pkVal = pkVal;
/*    */   }
/*    */ 
/*    */   public List<FieldEntity> getFieldEntityList() {
/* 30 */     return this.fieldEntityList;
/*    */   }
/*    */ 
/*    */   public void setFieldEntityList(List<FieldEntity> fieldEntityList) {
/* 34 */     this.fieldEntityList = fieldEntityList;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.reader.DataEntity
 * JD-Core Version:    0.6.2
 */