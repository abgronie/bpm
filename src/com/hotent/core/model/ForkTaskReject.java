/*    */ package com.hotent.core.model;
/*    */ 
/*    */ import com.hotent.core.util.StringUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ForkTaskReject
/*    */ {
/*    */   private Long userId;
/*    */   private String fullname;
/*    */   private String token;
/*    */   private String nodeId;
/*    */   private String nodeName;
/*    */ 
/*    */   public ForkTaskReject()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ForkTaskReject(Long userId, String fullname, String token, String nodeId, String nodeName)
/*    */   {
/* 23 */     this.userId = userId;
/* 24 */     this.fullname = fullname;
/* 25 */     this.token = token;
/* 26 */     this.nodeId = nodeId;
/* 27 */     this.nodeName = nodeName;
/*    */   }
/*    */ 
/*    */   public Long getUserId() {
/* 31 */     return this.userId;
/*    */   }
/*    */   public void setUserId(Long userId) {
/* 34 */     this.userId = userId;
/*    */   }
/*    */   public String getFullname() {
/* 37 */     return this.fullname;
/*    */   }
/*    */   public void setFullname(String fullname) {
/* 40 */     this.fullname = fullname;
/*    */   }
/*    */   public String getToken() {
/* 43 */     return this.token;
/*    */   }
/*    */   public void setToken(String token) {
/* 46 */     this.token = token;
/*    */   }
/*    */ 
/*    */   public String getNodeId() {
/* 50 */     return this.nodeId;
/*    */   }
/*    */ 
/*    */   public void setNodeId(String nodeId) {
/* 54 */     this.nodeId = nodeId;
/*    */   }
/*    */ 
/*    */   public String getNodeName() {
/* 58 */     return this.nodeName;
/*    */   }
/*    */ 
/*    */   public void setNodeName(String nodeName) {
/* 62 */     this.nodeName = nodeName;
/*    */   }
/*    */ 
/*    */   public static List<ForkTaskReject> parser2List(String rejects)
/*    */   {
/* 74 */     if (StringUtil.isEmpty(rejects)) return null;
/* 75 */     String[] ary = rejects.split("#");
/* 76 */     List list = new ArrayList();
/* 77 */     for (String item : ary) {
/* 78 */       String[] split = item.split("\\^");
/* 79 */       if (split.length == 5) {
/* 80 */         String userIdStr = split[0];
/* 81 */         String fullname = split[1];
/* 82 */         String token = split[2];
/* 83 */         String nodeId = split[3];
/* 84 */         String nodeName = split[4];
/* 85 */         list.add(new ForkTaskReject(Long.valueOf(Long.parseLong(userIdStr)), fullname, token, nodeId, nodeName));
/*    */       }
/*    */     }
/* 87 */     return list;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.ForkTaskReject
 * JD-Core Version:    0.6.2
 */