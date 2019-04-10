/*    */ package com.hotent.core.model;
/*    */ 
/*    */ public class WfBaseModel extends BaseModel
/*    */ {
/* 12 */   private Long taskId = Long.valueOf(0L);
/*    */ 
/* 16 */   private Long runId = Long.valueOf(0L);
/*    */ 
/* 20 */   private Long actInstId = Long.valueOf(0L);
/*    */ 
/* 24 */   private String nodeId = "";
/*    */ 
/* 28 */   private String nodeName = "";
/*    */ 
/*    */   public Long getTaskId() {
/* 31 */     return this.taskId;
/*    */   }
/*    */ 
/*    */   public void setTaskId(Long taskId) {
/* 35 */     this.taskId = taskId;
/*    */   }
/*    */ 
/*    */   public Long getRunId() {
/* 39 */     return this.runId;
/*    */   }
/*    */ 
/*    */   public void setRunId(Long runId) {
/* 43 */     this.runId = runId;
/*    */   }
/*    */ 
/*    */   public Long getActInstId() {
/* 47 */     return this.actInstId;
/*    */   }
/*    */ 
/*    */   public void setActInstId(Long actInstId) {
/* 51 */     this.actInstId = actInstId;
/*    */   }
/*    */ 
/*    */   public String getNodeId() {
/* 55 */     return this.nodeId;
/*    */   }
/*    */ 
/*    */   public void setNodeId(String nodeId) {
/* 59 */     this.nodeId = nodeId;
/*    */   }
/*    */ 
/*    */   public String getNodeName() {
/* 63 */     return this.nodeName;
/*    */   }
/*    */ 
/*    */   public void setNodeName(String nodeName) {
/* 67 */     this.nodeName = nodeName;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.WfBaseModel
 * JD-Core Version:    0.6.2
 */