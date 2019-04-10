/*    */ package com.hotent.core.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import net.sf.json.util.JSONStringer;
/*    */ 
/*    */ public class ResultMessage
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7102370526170507252L;
/*    */   public static final int Success = 1;
/*    */   public static final int Fail = 0;
/* 25 */   private int result = 1;
/*    */ 
/* 27 */   private String message = "";
/*    */ 
/* 29 */   private String cause = "";
/*    */ 
/*    */   public ResultMessage()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ResultMessage(int result, String message) {
/* 36 */     this.result = result;
/* 37 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public ResultMessage(int result, String message, String cause) {
/* 41 */     this.result = result;
/* 42 */     this.message = message;
/* 43 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */   public int getResult() {
/* 47 */     return this.result;
/*    */   }
/*    */ 
/*    */   public void setResult(int result) {
/* 51 */     this.result = result;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 55 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 59 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public String getCause() {
/* 63 */     return this.cause;
/*    */   }
/*    */ 
/*    */   public void setCause(String cause) {
/* 67 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 71 */     JSONStringer stringer = new JSONStringer();
/* 72 */     stringer.object();
/* 73 */     stringer.key("result");
/* 74 */     stringer.value(this.result);
/* 75 */     stringer.key("message");
/* 76 */     stringer.value(this.message);
/* 77 */     stringer.key("cause");
/* 78 */     stringer.value(this.cause);
/* 79 */     stringer.endObject();
/* 80 */     return stringer.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.ResultMessage
 * JD-Core Version:    0.6.2
 */