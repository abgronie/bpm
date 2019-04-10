/*    */ package com.hotent.core.wsdl;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OperationInfo
/*    */ {
/*  8 */   private String operationName = null;
/*  9 */   private List<?> inputParams = new ArrayList();
/* 10 */   private ParameterInfo returnType = null;
/* 11 */   private List<?> outputParams = new ArrayList();
/*    */ 
/* 13 */   private String inputAction = "api";
/*    */ 
/*    */   public String getOperationName() {
/* 16 */     return this.operationName;
/*    */   }
/*    */ 
/*    */   public void setOperationName(String operationName) {
/* 20 */     this.operationName = operationName;
/*    */   }
/*    */ 
/*    */   public List<?> getInputParams() {
/* 24 */     return this.inputParams;
/*    */   }
/*    */ 
/*    */   public void setInputParams(List<?> inputParams) {
/* 28 */     this.inputParams = inputParams;
/*    */   }
/*    */ 
/*    */   public ParameterInfo getReturnType() {
/* 32 */     return this.returnType;
/*    */   }
/*    */ 
/*    */   public void setReturnType(ParameterInfo returnType) {
/* 36 */     this.returnType = returnType;
/*    */   }
/*    */ 
/*    */   public List<?> getOutputParams() {
/* 40 */     return this.outputParams;
/*    */   }
/*    */ 
/*    */   public void setOutputParams(List<?> outputParams) {
/* 44 */     this.outputParams = outputParams;
/*    */   }
/*    */ 
/*    */   public String getInputAction() {
/* 48 */     return this.inputAction;
/*    */   }
/*    */ 
/*    */   public void setInputAction(String inputAction) {
/* 52 */     this.inputAction = inputAction;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.wsdl.OperationInfo
 * JD-Core Version:    0.6.2
 */