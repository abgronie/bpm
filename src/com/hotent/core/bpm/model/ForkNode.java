/*    */ package com.hotent.core.bpm.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ForkNode
/*    */ {
/* 22 */   private boolean isMulti = false;
/*    */ 
/* 26 */   private String forkNodeId = "";
/*    */ 
/* 30 */   private String preNodeId = "";
/*    */ 
/* 35 */   private List<NodeCondition> list = new ArrayList();
/*    */ 
/*    */   public String getPreNodeId() {
/* 38 */     return this.preNodeId;
/*    */   }
/*    */ 
/*    */   public void setPreNodeId(String preNodeId) {
/* 42 */     this.preNodeId = preNodeId;
/*    */   }
/*    */ 
/*    */   public void addNode(NodeCondition condition)
/*    */   {
/* 48 */     this.list.add(condition);
/*    */   }
/*    */ 
/*    */   public boolean getIsMulti() {
/* 52 */     return this.isMulti;
/*    */   }
/*    */ 
/*    */   public void setMulti(boolean isMulti) {
/* 56 */     this.isMulti = isMulti;
/*    */   }
/*    */ 
/*    */   public String getForkNodeId() {
/* 60 */     return this.forkNodeId;
/*    */   }
/*    */ 
/*    */   public void setForkNodeId(String forkNodeId) {
/* 64 */     this.forkNodeId = forkNodeId;
/*    */   }
/*    */ 
/*    */   public List<NodeCondition> getList() {
/* 68 */     return this.list;
/*    */   }
/*    */ 
/*    */   public void setList(List<NodeCondition> list) {
/* 72 */     this.list = list;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.ForkNode
 * JD-Core Version:    0.6.2
 */