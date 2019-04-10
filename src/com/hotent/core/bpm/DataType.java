/*    */ package com.hotent.core.bpm;
/*    */ 
/*    */ public enum DataType
/*    */ {
/* 15 */   STRING("string", "字符串"), 
/*    */ 
/* 17 */   NUMBER("number", "数字"), 
/*    */ 
/* 19 */   DATE("date", "日期");
/*    */ 
/* 22 */   private String key = "";
/*    */ 
/* 24 */   private String value = "";
/*    */ 
/*    */   private DataType(String key, String value)
/*    */   {
/* 28 */     this.key = key;
/* 29 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String getKey()
/*    */   {
/* 34 */     return this.key;
/*    */   }
/*    */ 
/*    */   public void setKey(String key) {
/* 38 */     this.key = key;
/*    */   }
/*    */ 
/*    */   public String getValue() {
/* 42 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 46 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 51 */     return this.key;
/*    */   }
/*    */ 
/*    */   public static DataType fromKey(String key)
/*    */   {
/* 61 */     for (DataType c : values()) {
/* 62 */       if (c.getKey().equalsIgnoreCase(key))
/* 63 */         return c;
/*    */     }
/* 65 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.DataType
 * JD-Core Version:    0.6.2
 */