/*    */ package com.hotent.core.excel.reader;
/*    */ 
/*    */ public class FieldEntity
/*    */ {
/*  6 */   public static final Short IS_KEY = Short.valueOf((short)1);
/*    */ 
/*  8 */   public static final Short NOT_KEY = Short.valueOf((short)0);
/*    */   private String name;
/*    */   private String value;
/* 20 */   private Short isKey = NOT_KEY;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 24 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 27 */     this.name = name;
/*    */   }
/*    */   public String getValue() {
/* 30 */     return this.value;
/*    */   }
/*    */   public void setValue(String value) {
/* 33 */     this.value = value;
/*    */   }
/*    */   public Short getIsKey() {
/* 36 */     return this.isKey;
/*    */   }
/*    */   public void setIsKey(Short isKey) {
/* 39 */     this.isKey = isKey;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.reader.FieldEntity
 * JD-Core Version:    0.6.2
 */