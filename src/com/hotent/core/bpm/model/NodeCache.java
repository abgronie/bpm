/*     */ package com.hotent.core.bpm.model;
/*     */ 
/*     */ import com.hotent.core.api.bpm.IBpmDao;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.Dom4jUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class NodeCache
/*     */ {
/*  30 */   private static final Log logger = LogFactory.getLog(NodeCache.class);
/*     */ 
/*  34 */   private static Map<String, Map<String, FlowNode>> actNodesMap = new HashMap();
/*     */ 
/*     */   public static synchronized Map<String, FlowNode> getByActDefId(String actDefId)
/*     */   {
/*  51 */     if (actNodesMap.containsKey(actDefId)) {
/*  52 */       return (Map)actNodesMap.get(actDefId);
/*     */     }
/*  54 */     Map map = readFromXml(actDefId);
/*  55 */     actNodesMap.put(actDefId, map);
/*  56 */     return map;
/*     */   }
/*     */ 
/*     */   public static FlowNode getStartNode(String actDefId)
/*     */   {
/*  69 */     getByActDefId(actDefId);
/*  70 */     Map nodeMap = (Map)actNodesMap.get(actDefId);
/*  71 */     return getStartNode(nodeMap);
/*     */   }
/*     */ 
/*     */   public static FlowNode getStartNode(Map<String, FlowNode> nodeMap)
/*     */   {
/*  81 */     for (FlowNode flowNode : nodeMap.values())
/*     */     {
/*  83 */       if ("startEvent".equals(flowNode.getNodeType())) {
/*  84 */         FlowNode parentNode = flowNode.getParentNode();
/*  85 */         if ((parentNode == null) || ("callActivity".equals(parentNode.getNodeType()))) {
/*  86 */           return flowNode;
/*     */         }
/*     */       }
/*     */     }
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<FlowNode> getFirstNode(String actDefId)
/*     */   {
/*  99 */     FlowNode startNode = getStartNode(actDefId);
/* 100 */     if (startNode == null) return new ArrayList();
/* 101 */     return startNode.getNextFlowNodes();
/*     */   }
/*     */ 
/*     */   public static FlowNode getFirstNodeId(String actDefId)
/*     */     throws Exception
/*     */   {
/* 112 */     FlowNode startNode = getStartNode(actDefId);
/* 113 */     if (startNode == null) return null;
/* 114 */     List list = startNode.getNextFlowNodes();
/* 115 */     if (list.size() > 1) {
/* 116 */       throw new Exception("流程定义:" + actDefId + ",起始节点后续节点超过1个，请检查流程图设置!");
/*     */     }
/* 118 */     if (list.size() == 0) {
/* 119 */       throw new Exception("流程定义:" + actDefId + ",起始节点没有后续节点，请检查流程图设置!");
/*     */     }
/* 121 */     return (FlowNode)list.get(0);
/*     */   }
/*     */ 
/*     */   public static boolean isMultipleFirstNode(String actDefId)
/*     */     throws Exception
/*     */   {
/* 132 */     FlowNode startNode = getStartNode(actDefId);
/* 133 */     if (startNode == null) return false;
/* 134 */     List list = startNode.getNextFlowNodes();
/* 135 */     if (list.size() == 0) {
/* 136 */       throw new Exception("流程定义:" + actDefId + ",起始节点没有后续节点，请检查流程图设置!");
/*     */     }
/* 138 */     if (list.size() > 1) {
/* 139 */       return true;
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isFirstNode(String actDefId, String nodeId)
/*     */   {
/* 151 */     List<FlowNode> list = getFirstNode(actDefId);
/* 152 */     for (FlowNode flowNode : list) {
/* 153 */       if (nodeId.equals(flowNode.getNodeId()))
/* 154 */         return true;
/*     */     }
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isSignTaskNode(String actDefId, String nodeId)
/*     */   {
/* 166 */     getByActDefId(actDefId);
/* 167 */     Map nodeMap = (Map)actNodesMap.get(actDefId);
/* 168 */     FlowNode flowNode = (FlowNode)nodeMap.get(nodeId);
/* 169 */     if ((flowNode.getIsMultiInstance().booleanValue()) && (flowNode.getNodeType().equals("userTask"))) {
/* 170 */       return true;
/*     */     }
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<FlowNode> getEndNode(String actDefId)
/*     */   {
/* 185 */     getByActDefId(actDefId);
/* 186 */     Map nodeMap = (Map)actNodesMap.get(actDefId);
/* 187 */     return getEndNode(nodeMap);
/*     */   }
/*     */ 
/*     */   public static FlowNode getNodeByActNodeId(String actDefId, String nodeId)
/*     */   {
/* 197 */     getByActDefId(actDefId);
/* 198 */     Map nodeMap = (Map)actNodesMap.get(actDefId);
/* 199 */     return (FlowNode)nodeMap.get(nodeId);
/*     */   }
/*     */ 
/*     */   private static List<FlowNode> getEndNode(Map<String, FlowNode> nodeMap)
/*     */   {
/* 209 */     List flowNodeList = new ArrayList();
/* 210 */     for (FlowNode flowNode : nodeMap.values()) {
/* 211 */       if (("endEvent".equals(flowNode.getNodeType())) && (BeanUtils.isEmpty(flowNode.getNextFlowNodes())))
/*     */       {
/* 213 */         flowNodeList.add(flowNode);
/*     */       }
/*     */     }
/* 216 */     return flowNodeList;
/*     */   }
/*     */ 
/*     */   public static boolean hasExternalSubprocess(String actDefId)
/*     */   {
/* 227 */     getByActDefId(actDefId);
/* 228 */     Map<Object, FlowNode> nodeMap = (Map)actNodesMap.get(actDefId);
/* 229 */     for (FlowNode flowNode : nodeMap.values()) {
/* 230 */       if ("callActivity".equals(flowNode.getNodeType())) {
/* 231 */         return true;
/*     */       }
/*     */     }
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */   public static void clear(String actDefId)
/*     */   {
/* 243 */     actNodesMap.remove(actDefId);
/*     */   }
/*     */ 
/*     */   private static Map<String, FlowNode> readFromXml(String actDefId)
/*     */   {
/* 253 */     IBpmDao dao = (IBpmDao)AppUtil.getBean(IBpmDao.class);
/*     */ 
/* 255 */     String deployId = dao.getDeployIdByActdefId(actDefId);
/* 256 */     String xml = dao.getDefXmlByDeployId(deployId.toString());
/* 257 */     return parseXml(xml, null);
/*     */   }
/*     */ 
/*     */   private static String getXmlByDefKey(String actDefkey)
/*     */   {
/* 267 */     IBpmDao dao = (IBpmDao)AppUtil.getBean(IBpmDao.class);
/*     */ 
/* 269 */     String xml = dao.getXmlByDefKey(actDefkey);
/* 270 */     return xml;
/*     */   }
/*     */ 
/*     */   private static Map<String, FlowNode> parseXml(String xml, FlowNode parentNode)
/*     */   {
/* 280 */     xml = xml.replace("xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
/*     */ 
/* 282 */     Document document = Dom4jUtil.loadXml(xml);
/* 283 */     Element el = document.getRootElement();
/* 284 */     Map map = new HashMap();
/* 285 */     Element processEl = (Element)el.selectSingleNode("./process");
/* 286 */     Iterator its = processEl.elementIterator();
/*     */ 
/* 288 */     while (its.hasNext()) {
/* 289 */       Element nodeEl = (Element)its.next();
/* 290 */       String nodeType = nodeEl.getName();
/*     */ 
/* 292 */       String nodeId = nodeEl.attributeValue("id");
/* 293 */       String nodeName = nodeEl.attributeValue("name");
/*     */ 
/* 296 */       boolean isMultiInstance = nodeEl.selectSingleNode("./multiInstanceLoopCharacteristics") != null;
/*     */ 
/* 301 */       if (("startEvent".equals(nodeType)) || ("userTask".equals(nodeType)) || ("parallelGateway".equals(nodeType)) || ("inclusiveGateway".equals(nodeType)) || ("exclusiveGateway".equals(nodeType)) || ("endEvent".equals(nodeType)) || ("serviceTask".equals(nodeType)))
/*     */       {
/* 307 */         FlowNode flowNode = new FlowNode(nodeId, nodeName, nodeType, isMultiInstance);
/*     */ 
/* 309 */         if ("startEvent".equalsIgnoreCase(nodeType)) {
/* 310 */           flowNode.setParentNode(parentNode);
/*     */         }
/* 312 */         map.put(nodeId, flowNode);
/*     */       }
/* 315 */       else if ("subProcess".equals(nodeType)) {
/* 316 */         map = getSubInnerProFlowNode(map, nodeId, nodeName, nodeType, isMultiInstance, nodeEl);
/*     */       }
/* 319 */       else if ("callActivity".equals(nodeType)) {
/* 320 */         String flowKey = nodeEl.attributeValue("calledElement");
/*     */ 
/* 322 */         String subProcessXml = getXmlByDefKey(flowKey);
/*     */ 
/* 324 */         FlowNode flowNode = new FlowNode(nodeId, nodeName, nodeType, isMultiInstance);
/*     */ 
/* 326 */         Map subChildNodes = parseXml(subProcessXml, flowNode);
/*     */ 
/* 328 */         flowNode.setAttribute("subFlowKey", flowKey);
/* 329 */         map.put(nodeId, flowNode);
/*     */ 
/* 331 */         flowNode.setSubProcessNodes(subChildNodes);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 336 */     List seqFlowList = document.selectNodes("/definitions/process//sequenceFlow");
/*     */ 
/* 338 */     for (int i = 0; i < seqFlowList.size(); i++) {
/* 339 */       Element seqFlow = (Element)seqFlowList.get(i);
/* 340 */       String sourceRef = seqFlow.attributeValue("sourceRef");
/* 341 */       String targetRef = seqFlow.attributeValue("targetRef");
/*     */ 
/* 343 */       FlowNode sourceFlowNode = (FlowNode)map.get(sourceRef);
/* 344 */       FlowNode targetFlowNode = (FlowNode)map.get(targetRef);
/* 345 */       if ((sourceFlowNode != null) && (targetFlowNode != null)) {
/* 346 */         logger.debug("sourceRef:" + sourceFlowNode.getNodeName() + " targetRef:" + targetFlowNode.getNodeName());
/*     */ 
/* 348 */         if (targetFlowNode.getParentNode() != null) {
/* 349 */           logger.debug("parentNode:" + targetFlowNode.getParentNode().getNodeName());
/*     */         }
/*     */ 
/* 354 */         if (("startEvent".equals(sourceFlowNode.getNodeType())) && (sourceFlowNode.getParentNode() != null))
/*     */         {
/* 356 */           sourceFlowNode.setFirstNode(true);
/* 357 */           sourceFlowNode.getParentNode().setSubFirstNode(sourceFlowNode);
/*     */ 
/* 360 */           if ((targetFlowNode.getParentNode() != null) && (targetFlowNode.getParentNode().getIsMultiInstance().booleanValue())) {
/* 361 */             targetFlowNode.setIsMultiInstance(Boolean.valueOf(true));
/*     */           }
/*     */         }
/* 364 */         sourceFlowNode.getNextFlowNodes().add(targetFlowNode);
/* 365 */         targetFlowNode.getPreFlowNodes().add(sourceFlowNode);
/*     */       }
/*     */     }
/* 368 */     return map;
/*     */   }
/*     */ 
/*     */   private static Map<String, FlowNode> getSubInnerProFlowNode(Map<String, FlowNode> map, String nodeId, String nodeName, String nodeType, boolean isMultiInstance, Element nodeEl)
/*     */   {
/* 375 */     FlowNode subProcessNode = new FlowNode(nodeId, nodeName, nodeType, new ArrayList(), isMultiInstance);
/*     */ 
/* 378 */     map.put(nodeId, subProcessNode);
/*     */ 
/* 380 */     List<Element> subElements = nodeEl.elements();
/* 381 */     for (Element subEl : subElements) {
/* 382 */       String subNodeType = subEl.getName();
/*     */ 
/* 384 */       boolean subIsMultiInstance = nodeEl.selectSingleNode("./multiInstanceLoopCharacteristics") != null;
/*     */ 
/* 387 */       String subNodeName = subEl.attributeValue("name");
/* 388 */       String subNodeId = subEl.attributeValue("id");
/* 389 */       if (("startEvent".equals(subNodeType)) || ("userTask".equals(subNodeType)) || ("parallelGateway".equals(subNodeType)) || ("inclusiveGateway".equals(subNodeType)) || ("exclusiveGateway".equals(subNodeType)) || ("endEvent".equals(subNodeType)) || ("serviceTask".equals(subNodeType)))
/*     */       {
/* 396 */         FlowNode flowNode = new FlowNode(subNodeId, subNodeName, subNodeType, false, subProcessNode);
/*     */ 
/* 398 */         subProcessNode.getSubFlowNodes().add(flowNode);
/* 399 */         map.put(subNodeId, flowNode);
/*     */       }
/* 401 */       else if ("callActivity".equals(subNodeType)) {
/* 402 */         String flowKey = subEl.attributeValue("calledElement");
/*     */ 
/* 404 */         String subProcessXml = getXmlByDefKey(flowKey);
/*     */ 
/* 406 */         FlowNode flowNode = new FlowNode(subNodeId, subNodeName, subNodeType, subIsMultiInstance);
/* 407 */         subProcessNode.getSubFlowNodes().add(flowNode);
/* 408 */         flowNode.setAttribute("subFlowKey", flowKey);
/*     */ 
/* 410 */         Map subChildNodes = parseXml(subProcessXml, flowNode);
/* 411 */         flowNode.setSubProcessNodes(subChildNodes);
/* 412 */         map.put(subNodeId, flowNode);
/* 413 */       } else if ("subProcess".equals(subNodeType)) {
/* 414 */         map = getSubInnerProFlowNode(map, subNodeId, subNodeName, subNodeType, subIsMultiInstance, subEl);
/*     */       }
/*     */     }
/*     */ 
/* 418 */     return map;
/*     */   }
/*     */ 
/*     */   public static Set<String> getSubKeysByXml(String xml) {
/* 422 */     Set keySet = new HashSet();
/* 423 */     getSubKeysByXml(xml, keySet);
/* 424 */     return keySet;
/*     */   }
/*     */ 
/*     */   private static void getSubKeysByXml(String xml, Set<String> keySet)
/*     */   {
/* 435 */     xml = xml.replace("xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
/*     */ 
/* 437 */     Document document = Dom4jUtil.loadXml(xml);
/* 438 */     Element el = document.getRootElement();
/* 439 */     Element processEl = (Element)el.selectSingleNode("./process");
/* 440 */     if (BeanUtils.isEmpty(processEl))
/* 441 */       return;
/* 442 */     Iterator its = processEl.elementIterator();
/*     */ 
/* 444 */     while (its.hasNext()) {
/* 445 */       Element nodeEl = (Element)its.next();
/* 446 */       String nodeType = nodeEl.getName();
/*     */ 
/* 448 */       if ("callActivity".equals(nodeType)) {
/* 449 */         String flowKey = nodeEl.attributeValue("calledElement");
/* 450 */         keySet.add(flowKey);
/*     */ 
/* 452 */         String subProcessXml = getXmlByDefKey(flowKey);
/*     */ 
/* 454 */         getSubKeysByXml(subProcessXml, keySet);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.NodeCache
 * JD-Core Version:    0.6.2
 */