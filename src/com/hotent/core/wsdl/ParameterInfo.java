/*    */ package com.hotent.core.wsdl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ParameterInfo
/*    */ {
/* 12 */   public static final Short COMPLEX_YES = Short.valueOf((short)1);
/*    */ 
/* 16 */   public static final Short COMPLEX_NO = Short.valueOf((short)0);
/*    */ 
/* 20 */   public static final Short SHOW_YES = Short.valueOf((short)0);
/*    */ 
/* 24 */   public static final Short SHOW_NO = Short.valueOf((short)1);
/*    */   private String name;
/*    */   private String type;
/* 27 */   private Short isComplext = Short.valueOf((short)0);
/* 28 */   private Short isShow = Short.valueOf((short)0);
/*    */   private String deep;
/* 30 */   private Boolean isList = Boolean.valueOf(false);
/*    */ 
/* 33 */   private Map<String, ParameterInfo> complextParams = new HashMap();
/*    */ 
/* 36 */   private Map<String, String> parentComplext = new HashMap();
/*    */ 
/*    */   public Short getIsComplext() {
/* 39 */     return this.isComplext;
/*    */   }
/*    */ 
/*    */   public void setIsComplext(Short isComplext) {
/* 43 */     this.isComplext = isComplext;
/*    */   }
/*    */ 
/*    */   public String getType() {
/* 47 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 51 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 55 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 59 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public Map<String, ParameterInfo> getComplextParams() {
/* 63 */     return this.complextParams;
/*    */   }
/*    */ 
/*    */   public void setComplextParams(Map<String, ParameterInfo> complextParams) {
/* 67 */     this.complextParams = complextParams;
/*    */   }
/*    */ 
/*    */   public Map<String, String> getParentComplext() {
/* 71 */     return this.parentComplext;
/*    */   }
/*    */ 
/*    */   public void setParentComplext(Map<String, String> parentComplext) {
/* 75 */     this.parentComplext = parentComplext;
/*    */   }
/*    */ 
/*    */   public Short getIsShow() {
/* 79 */     return this.isShow;
/*    */   }
/*    */ 
/*    */   public void setIsShow(Short isShow) {
/* 83 */     this.isShow = isShow;
/*    */   }
/*    */ 
/*    */   public String getDeep() {
/* 87 */     return this.deep;
/*    */   }
/*    */ 
/*    */   public void setDeep(String deep) {
/* 91 */     this.deep = deep;
/*    */   }
/*    */ 
/*    */   public Boolean isList() {
/* 95 */     return this.isList;
/*    */   }
/*    */ 
/*    */   public void setIsList(Boolean isList) {
/* 99 */     this.isList = isList;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.wsdl.ParameterInfo
 * JD-Core Version:    0.6.2
 */