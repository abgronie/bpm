/*    */ package com.hotent.core.bpm.model;
/*    */ 
/*    */ public class NodeCondition
/*    */ {
/* 16 */   private String nodeName = "";
/*    */ 
/* 21 */   private String nodeId = "";
/*    */ 
/* 25 */   private String condition = "";
/*    */ 
/*    */   public NodeCondition(String nodeName, String nodeId, String condition)
/*    */   {
/* 29 */     this.nodeName = nodeName;
/* 30 */     this.nodeId = nodeId;
/* 31 */     this.condition = condition;
/*    */   }
/*    */ 
/*    */   public String getNodeName()
/*    */   {
/* 39 */     return this.nodeName;
/*    */   }
/*    */ 
/*    */   public void setNodeName(String nodeName) {
/* 43 */     this.nodeName = nodeName;
/*    */   }
/*    */ 
/*    */   public String getNodeId()
/*    */   {
/* 51 */     return this.nodeId;
/*    */   }
/*    */ 
/*    */   public void setNodeId(String nodeId) {
/* 55 */     this.nodeId = nodeId;
/*    */   }
/*    */ 
/*    */   public String getCondition()
/*    */   {
/* 63 */     return this.condition;
/*    */   }
/*    */ 
/*    */   public void setCondition(String condition) {
/* 67 */     this.condition = condition;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.NodeCondition
 * JD-Core Version:    0.6.2
 */