/*     */ package com.hotent.core.bpm.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FlowNode
/*     */ {
/*     */   public static final String TYPE_USERTASK = "userTask";
/*     */   public static final String TYPE_SUBPROCESS = "subProcess";
/*     */   public static final String TYPE_START_EVENT = "startEvent";
/*     */   public static final String TYPE_EXCLUSIVEGATEWAY = "exclusiveGateway";
/*  20 */   private String nodeId = null;
/*     */ 
/*  22 */   private String nodeName = null;
/*     */ 
/*  24 */   private String nodeType = null;
/*     */ 
/*  26 */   private Boolean isMultiInstance = Boolean.valueOf(false);
/*     */ 
/*  29 */   private FlowNode parentNode = null;
/*     */ 
/*  31 */   private boolean isFirstNode = false;
/*     */ 
/*  34 */   private Map<String, String> attrMap = new HashMap();
/*     */ 
/*  37 */   private Map<String, FlowNode> subProcessNodes = new HashMap();
/*     */ 
/*  41 */   private List<FlowNode> preFlowNodes = new ArrayList();
/*     */ 
/*  43 */   private List<FlowNode> nextFlowNodes = new ArrayList();
/*     */ 
/*  46 */   private List<FlowNode> subFlowNodes = new ArrayList();
/*     */ 
/*  48 */   private FlowNode subFirstNode = null;
/*     */ 
/*     */   public String getNodeId()
/*     */   {
/*  52 */     return this.nodeId;
/*     */   }
/*     */ 
/*     */   public FlowNode()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FlowNode(String nodeId, String nodeName, String nodeType) {
/*  60 */     this.nodeId = nodeId;
/*  61 */     this.nodeName = nodeName;
/*  62 */     this.nodeType = nodeType;
/*     */   }
/*     */ 
/*     */   public FlowNode(String nodeId, String nodeName, String nodeType, List<FlowNode> subFlowNodes) {
/*  66 */     this.nodeId = nodeId;
/*  67 */     this.nodeName = nodeName;
/*  68 */     this.nodeType = nodeType;
/*  69 */     this.subFlowNodes = subFlowNodes;
/*     */   }
/*     */ 
/*     */   public FlowNode(String nodeId, String nodeName, String nodeType, boolean isMultiInstance) {
/*  73 */     this.nodeId = nodeId;
/*  74 */     this.nodeName = nodeName;
/*  75 */     this.nodeType = nodeType;
/*  76 */     this.isMultiInstance = Boolean.valueOf(isMultiInstance);
/*     */   }
/*     */ 
/*     */   public FlowNode(String nodeId, String nodeName, String nodeType, boolean isMultiInstance, FlowNode parentNode) {
/*  80 */     this.nodeId = nodeId;
/*  81 */     this.nodeName = nodeName;
/*  82 */     this.nodeType = nodeType;
/*  83 */     this.isMultiInstance = Boolean.valueOf(isMultiInstance);
/*  84 */     this.parentNode = parentNode;
/*     */   }
/*     */ 
/*     */   public FlowNode(String nodeId, String nodeName, String nodeType, List<FlowNode> subFlowNodes, boolean isMultiInstance) {
/*  88 */     this.nodeId = nodeId;
/*  89 */     this.nodeName = nodeName;
/*  90 */     this.nodeType = nodeType;
/*  91 */     this.subFlowNodes = subFlowNodes;
/*  92 */     this.isMultiInstance = Boolean.valueOf(isMultiInstance);
/*     */   }
/*     */ 
/*     */   public void setNodeId(String nodeId) {
/*  96 */     this.nodeId = nodeId;
/*     */   }
/*     */ 
/*     */   public String getNodeName() {
/* 100 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */   public void setNodeName(String nodeName) {
/* 104 */     this.nodeName = nodeName;
/*     */   }
/*     */ 
/*     */   public String getNodeType() {
/* 108 */     return this.nodeType;
/*     */   }
/*     */ 
/*     */   public void setNodeType(String nodeType) {
/* 112 */     this.nodeType = nodeType;
/*     */   }
/*     */ 
/*     */   public List<FlowNode> getPreFlowNodes() {
/* 116 */     return this.preFlowNodes;
/*     */   }
/*     */ 
/*     */   public void setPreFlowNodes(List<FlowNode> preFlowNodes) {
/* 120 */     this.preFlowNodes = preFlowNodes;
/*     */   }
/*     */ 
/*     */   public List<FlowNode> getNextFlowNodes() {
/* 124 */     return this.nextFlowNodes;
/*     */   }
/*     */ 
/*     */   public void setNextFlowNodes(List<FlowNode> nextFlowNodes) {
/* 128 */     this.nextFlowNodes = nextFlowNodes;
/*     */   }
/*     */ 
/*     */   public List<FlowNode> getSubFlowNodes() {
/* 132 */     return this.subFlowNodes;
/*     */   }
/*     */ 
/*     */   public void setSubFlowNodes(List<FlowNode> subFlowNodes) {
/* 136 */     this.subFlowNodes = subFlowNodes;
/*     */   }
/*     */ 
/*     */   public Boolean getIsMultiInstance() {
/* 140 */     return this.isMultiInstance;
/*     */   }
/*     */ 
/*     */   public void setIsMultiInstance(Boolean isMultiInstance) {
/* 144 */     this.isMultiInstance = isMultiInstance;
/*     */   }
/*     */ 
/*     */   public boolean isFirstNode() {
/* 148 */     return this.isFirstNode;
/*     */   }
/*     */ 
/*     */   public void setFirstNode(boolean isFirstNode) {
/* 152 */     this.isFirstNode = isFirstNode;
/*     */   }
/*     */ 
/*     */   public FlowNode getSubFirstNode() {
/* 156 */     return this.subFirstNode;
/*     */   }
/*     */ 
/*     */   public void setSubFirstNode(FlowNode subFirstNode) {
/* 160 */     this.subFirstNode = subFirstNode;
/*     */   }
/*     */ 
/*     */   public FlowNode getParentNode() {
/* 164 */     return this.parentNode;
/*     */   }
/*     */ 
/*     */   public void setParentNode(FlowNode parentNode) {
/* 168 */     this.parentNode = parentNode;
/*     */   }
/*     */ 
/*     */   public boolean getIsSubProcess() {
/* 172 */     return this.nodeType.equalsIgnoreCase("subProcess");
/*     */   }
/*     */ 
/*     */   public boolean getIsCallActivity() {
/* 176 */     return this.nodeType.equalsIgnoreCase("callActivity");
/*     */   }
/*     */ 
/*     */   public boolean getIsSignNode()
/*     */   {
/* 184 */     if ((this.nodeType.equalsIgnoreCase("userTask")) && (getIsMultiInstance().booleanValue())) {
/* 185 */       return true;
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean getIsFirstNode() {
/* 191 */     if (getPreFlowNodes() != null) {
/* 192 */       List<FlowNode> list = getPreFlowNodes();
/* 193 */       for (FlowNode node : list) {
/* 194 */         if (node.getNodeType().equalsIgnoreCase("startEvent")) {
/* 195 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   public void setAttribute(String name, String value)
/*     */   {
/* 210 */     this.attrMap.put(name, value);
/*     */   }
/*     */ 
/*     */   public String getAttribute(String name)
/*     */   {
/* 219 */     if (this.attrMap.containsKey(name)) {
/* 220 */       return (String)this.attrMap.get(name);
/*     */     }
/* 222 */     return "";
/*     */   }
/*     */ 
/*     */   public Map<String, FlowNode> getSubProcessNodes() {
/* 226 */     return this.subProcessNodes;
/*     */   }
/*     */ 
/*     */   public void setSubProcessNodes(Map<String, FlowNode> subProcessNodes) {
/* 230 */     this.subProcessNodes = subProcessNodes;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.FlowNode
 * JD-Core Version:    0.6.2
 */