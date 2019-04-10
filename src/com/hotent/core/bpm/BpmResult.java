/*    */ package com.hotent.core.bpm;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BpmResult
/*    */ {
/* 24 */   private String businessKey = "";
/*    */ 
/* 29 */   private String tableName = "";
/*    */ 
/* 34 */   private DataType dataType = DataType.NUMBER;
/*    */ 
/* 39 */   private Map<String, Object> vars = new HashMap();
/*    */ 
/*    */   public String getBusinessKey() {
/* 42 */     return this.businessKey;
/*    */   }
/*    */ 
/*    */   public void setBusinessKey(String businessKey) {
/* 46 */     this.businessKey = businessKey;
/*    */   }
/*    */ 
/*    */   public DataType getDataType() {
/* 50 */     return this.dataType;
/*    */   }
/*    */ 
/*    */   public void setDataType(DataType dataType) {
/* 54 */     this.dataType = dataType;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getVars() {
/* 58 */     return this.vars;
/*    */   }
/*    */ 
/*    */   public void setVars(Map<String, Object> vars) {
/* 62 */     this.vars = vars;
/*    */   }
/*    */ 
/*    */   public void addVariable(String name, Object value) {
/* 66 */     this.vars.put(name, value);
/*    */   }
/*    */ 
/*    */   public String getTableName() {
/* 70 */     return this.tableName;
/*    */   }
/*    */ 
/*    */   public void setTableName(String tableName) {
/* 74 */     this.tableName = tableName;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.BpmResult
 * JD-Core Version:    0.6.2
 */