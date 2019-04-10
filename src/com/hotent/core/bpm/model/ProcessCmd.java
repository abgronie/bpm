/*     */ package com.hotent.core.bpm.model;
/*     */ 
/*     */ import com.hotent.core.api.bpm.model.IProcessRun;
/*     */ import com.hotent.core.api.bpm.model.ITaskOpinion;
/*     */ import com.hotent.core.bpm.util.BpmUtil;
/*     */ import com.hotent.core.model.ForkTaskReject;
/*     */ import com.hotent.core.model.TaskExecutor;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
/*     */ @XmlRootElement
/*     */ public class ProcessCmd
/*     */ {
/*     */   private String actDefId;
/*     */   private String flowKey;
/*     */   private String taskId;
/*  44 */   private Long runId = Long.valueOf(0L);
/*     */ 
/*  48 */   private String subject = "";
/*     */   private String destTask;
/*     */   private String[] lastDestTaskIds;
/*     */   private String[] lastDestTaskUids;
/*  75 */   private List<TaskExecutor> taskExecutors = new ArrayList();
/*     */ 
/*  80 */   private String businessKey = "";
/*     */   private Long stackId;
/*  89 */   private boolean skipPreHandler = false;
/*     */ 
/*  93 */   private boolean skipAfterHandler = false;
/*     */ 
/* 104 */   private Integer isBack = Integer.valueOf(0);
/*     */ 
/* 108 */   private boolean isRecover = false;
/*     */ 
/* 118 */   private boolean isOnlyCompleteTask = false;
/*     */ 
/* 130 */   private Short voteAgree = Short.valueOf((short)1);
/*     */ 
/* 135 */   private String voteContent = "";
/*     */ 
/* 139 */   private String voteFieldName = "";
/*     */ 
/* 144 */   private Map<String, Object> variables = new HashMap();
/*     */ 
/* 151 */   private String formData = "";
/*     */ 
/* 156 */   private Map formDataMap = new HashMap();
/*     */ 
/* 161 */   private String currentUserId = "";
/*     */ 
/* 164 */   private IProcessRun processRun = null;
/*     */ 
/* 168 */   private String userAccount = null;
/*     */ 
/* 174 */   private boolean invokeExternal = false;
/*     */ 
/* 183 */   private String informType = "";
/*     */ 
/* 192 */   private String informStart = "";
/*     */ 
/* 198 */   private Short isManage = Short.valueOf((short)0);
/*     */ 
/* 203 */   private String appCode = "";
/*     */   private String dynamicTask;
/*     */   private Short jumpType;
/*     */   private String startNode;
/* 223 */   private Long relRunId = Long.valueOf(0L);
/*     */ 
/* 228 */   private Map<String, Object> transientVars = new HashMap();
/*     */ 
/* 233 */   private boolean localApi = false;
/*     */ 
/* 239 */   private boolean skip = false;
/*     */ 
/* 244 */   private boolean fromMobile = false;
/*     */ 
/* 249 */   private boolean startFlow = false;
/*     */   private List<ForkTaskReject> forkTaskRejects;
/*     */ 
/*     */   public ProcessCmd()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProcessCmd(String flowKey)
/*     */   {
/* 260 */     this.flowKey = flowKey;
/*     */   }
/*     */ 
/*     */   public ProcessCmd(String flowKey, boolean skipPreHandler, boolean skipAfterHandler) {
/* 264 */     this.flowKey = flowKey;
/* 265 */     this.skipPreHandler = skipPreHandler;
/* 266 */     this.skipAfterHandler = skipAfterHandler;
/*     */   }
/*     */ 
/*     */   public String getActDefId()
/*     */   {
/* 271 */     return this.actDefId;
/*     */   }
/*     */ 
/*     */   public void setActDefId(String actDefId)
/*     */   {
/* 276 */     this.actDefId = actDefId;
/*     */   }
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/* 281 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId)
/*     */   {
/* 286 */     this.taskId = taskId;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getVariables()
/*     */   {
/* 291 */     return this.variables;
/*     */   }
/*     */ 
/*     */   public void setVariables(Map<String, Object> variables)
/*     */   {
/* 296 */     this.variables = variables;
/*     */   }
/*     */ 
/*     */   public void putVariables(Map<String, Object> variables)
/*     */   {
/* 304 */     this.variables.putAll(variables);
/*     */   }
/*     */ 
/*     */   public void addVariable(String key, Object obj)
/*     */   {
/* 313 */     this.variables.put(key, obj);
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 318 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String subject)
/*     */   {
/* 323 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */   public String getDestTask()
/*     */   {
/* 328 */     return this.destTask;
/*     */   }
/*     */ 
/*     */   public void setDestTask(String destTask)
/*     */   {
/* 333 */     this.destTask = destTask;
/*     */   }
/*     */ 
/*     */   public String getBusinessKey()
/*     */   {
/* 338 */     return this.businessKey;
/*     */   }
/*     */ 
/*     */   public void setBusinessKey(String businessKey)
/*     */   {
/* 343 */     this.businessKey = businessKey;
/*     */   }
/*     */ 
/*     */   public String getFlowKey()
/*     */   {
/* 350 */     return this.flowKey;
/*     */   }
/*     */ 
/*     */   public void setFlowKey(String flowKey)
/*     */   {
/* 355 */     this.flowKey = flowKey;
/*     */   }
/*     */ 
/*     */   public Integer isBack()
/*     */   {
/* 360 */     return this.isBack;
/*     */   }
/*     */ 
/*     */   public void setBack(Integer isBack)
/*     */   {
/* 365 */     this.isBack = isBack;
/*     */   }
/*     */ 
/*     */   public boolean isRecover() {
/* 369 */     return this.isRecover;
/*     */   }
/*     */ 
/*     */   public void setRecover(boolean isRecover)
/*     */   {
/* 377 */     this.isRecover = isRecover;
/*     */   }
/*     */ 
/*     */   public Short getVoteAgree() {
/* 381 */     return this.voteAgree;
/*     */   }
/*     */ 
/*     */   public void setVoteAgree(Short voteAgree)
/*     */   {
/* 397 */     if (ITaskOpinion.STATUS_RECOVER.equals(voteAgree)) {
/* 398 */       setRecover(true);
/*     */     }
/* 400 */     this.voteAgree = voteAgree;
/*     */   }
/*     */ 
/*     */   public String getVoteContent()
/*     */   {
/* 405 */     return this.voteContent;
/*     */   }
/*     */ 
/*     */   public void setVoteContent(String voteContent)
/*     */   {
/* 410 */     this.voteContent = voteContent;
/*     */   }
/*     */ 
/*     */   public Long getStackId()
/*     */   {
/* 417 */     return this.stackId;
/*     */   }
/*     */ 
/*     */   public void setStackId(Long stackId)
/*     */   {
/* 422 */     this.stackId = stackId;
/*     */   }
/*     */ 
/*     */   public String getFormData()
/*     */   {
/* 428 */     return this.formData;
/*     */   }
/*     */ 
/*     */   public void setFormData(String formData) {
/* 432 */     this.formData = formData;
/*     */   }
/*     */ 
/*     */   public Map getFormDataMap()
/*     */   {
/* 437 */     return this.formDataMap;
/*     */   }
/*     */ 
/*     */   public void setFormDataMap(Map formDataMap) {
/* 441 */     this.formDataMap = formDataMap;
/*     */   }
/*     */ 
/*     */   public String getCurrentUserId() {
/* 445 */     return this.currentUserId;
/*     */   }
/*     */ 
/*     */   public void setCurrentUserId(String currentUserId) {
/* 449 */     this.currentUserId = currentUserId;
/*     */   }
/*     */ 
/*     */   public IProcessRun getProcessRun()
/*     */   {
/* 454 */     return this.processRun;
/*     */   }
/*     */ 
/*     */   public void setProcessRun(IProcessRun processRun)
/*     */   {
/* 459 */     this.businessKey = processRun.getBusinessKey();
/* 460 */     this.processRun = processRun;
/*     */   }
/*     */ 
/*     */   public String getUserAccount() {
/* 464 */     return this.userAccount;
/*     */   }
/*     */ 
/*     */   public void setUserAccount(String userAccount) {
/* 468 */     this.userAccount = userAccount;
/*     */   }
/*     */ 
/*     */   public String[] getLastDestTaskIds()
/*     */   {
/* 475 */     return this.lastDestTaskIds;
/*     */   }
/*     */ 
/*     */   public void setLastDestTaskIds(String[] lastDestTaskIds)
/*     */   {
/* 484 */     this.lastDestTaskIds = lastDestTaskIds;
/*     */   }
/*     */ 
/*     */   public String[] getLastDestTaskUids() {
/* 488 */     return this.lastDestTaskUids;
/*     */   }
/*     */ 
/*     */   public void setLastDestTaskUids(String[] lastDestTaskUids)
/*     */   {
/* 496 */     this.lastDestTaskUids = lastDestTaskUids;
/*     */   }
/*     */ 
/*     */   public boolean isOnlyCompleteTask() {
/* 500 */     return this.isOnlyCompleteTask;
/*     */   }
/*     */ 
/*     */   public void setOnlyCompleteTask(boolean isOnlyCompleteTask) {
/* 504 */     this.isOnlyCompleteTask = isOnlyCompleteTask;
/*     */   }
/*     */ 
/*     */   public boolean isInvokeExternal()
/*     */   {
/* 511 */     return this.invokeExternal;
/*     */   }
/*     */ 
/*     */   public void setInvokeExternal(boolean invokeExternal) {
/* 515 */     this.invokeExternal = invokeExternal;
/*     */   }
/*     */ 
/*     */   public String getInformType()
/*     */   {
/* 520 */     return this.informType;
/*     */   }
/*     */ 
/*     */   public void setInformType(String informType) {
/* 524 */     this.informType = informType;
/*     */   }
/*     */ 
/*     */   public boolean isSkipPreHandler() {
/* 528 */     return this.skipPreHandler;
/*     */   }
/*     */ 
/*     */   public void setSkipPreHandler(boolean skipPreHandler) {
/* 532 */     this.skipPreHandler = skipPreHandler;
/*     */   }
/*     */ 
/*     */   public boolean isSkipAfterHandler() {
/* 536 */     return this.skipAfterHandler;
/*     */   }
/*     */ 
/*     */   public void setSkipAfterHandler(boolean skipAfterHandler) {
/* 540 */     this.skipAfterHandler = skipAfterHandler;
/*     */   }
/*     */ 
/*     */   public Map<String, List<TaskExecutor>> getTaskExecutor()
/*     */   {
/* 549 */     Map map = new HashMap();
/* 550 */     if (BeanUtils.isEmpty(this.lastDestTaskIds)) return map;
/* 551 */     for (int i = 0; i < this.lastDestTaskIds.length; i++) {
/* 552 */       String nodeId = this.lastDestTaskIds[i];
/* 553 */       String executor = this.lastDestTaskUids[i];
/* 554 */       if (!StringUtil.isEmpty(executor))
/*     */       {
/* 556 */         List list = BpmUtil.getTaskExecutors(executor);
/* 557 */         map.put(nodeId, list);
/*     */       }
/*     */     }
/* 559 */     return map;
/*     */   }
/*     */   public List<TaskExecutor> getTaskExecutors() {
/* 562 */     return this.taskExecutors;
/*     */   }
/*     */   public void setTaskExecutors(List<TaskExecutor> taskExecutors) {
/* 565 */     this.taskExecutors = taskExecutors;
/*     */   }
/*     */ 
/*     */   public Long getRunId()
/*     */   {
/* 570 */     return this.runId;
/*     */   }
/*     */   public void setRunId(Long runId) {
/* 573 */     this.runId = runId;
/*     */   }
/*     */   public Short getIsManage() {
/* 576 */     return this.isManage;
/*     */   }
/*     */   public void setIsManage(Short isManage) {
/* 579 */     this.isManage = isManage;
/*     */   }
/*     */   public String getAppCode() {
/* 582 */     return this.appCode;
/*     */   }
/*     */   public void setAppCode(String appCode) {
/* 585 */     this.appCode = appCode;
/*     */   }
/*     */   public String getDynamicTask() {
/* 588 */     return this.dynamicTask;
/*     */   }
/*     */   public void setDynamicTask(String dynamicTask) {
/* 591 */     this.dynamicTask = dynamicTask;
/*     */   }
/*     */   public Short getJumpType() {
/* 594 */     return this.jumpType;
/*     */   }
/*     */   public void setJumpType(Short jumpType) {
/* 597 */     this.jumpType = jumpType;
/*     */   }
/*     */ 
/*     */   public String getInformStart() {
/* 601 */     return this.informStart;
/*     */   }
/*     */   public void setInformStart(String informStart) {
/* 604 */     this.informStart = informStart;
/*     */   }
/*     */ 
/*     */   public String getStartNode()
/*     */   {
/* 609 */     return this.startNode;
/*     */   }
/*     */   public void setStartNode(String startNode) {
/* 612 */     this.startNode = startNode;
/*     */   }
/*     */   public String getVoteFieldName() {
/* 615 */     return this.voteFieldName;
/*     */   }
/*     */   public void setVoteFieldName(String voteFieldName) {
/* 618 */     this.voteFieldName = voteFieldName;
/*     */   }
/*     */   public Long getRelRunId() {
/* 621 */     if (this.relRunId == null) return Long.valueOf(0L);
/* 622 */     return this.relRunId;
/*     */   }
/*     */   public void setRelRunId(Long relRunId) {
/* 625 */     this.relRunId = relRunId;
/*     */   }
/*     */   public Map<String, Object> getTransientVars() {
/* 628 */     return this.transientVars;
/*     */   }
/*     */   public void setTransientVars(Map<String, Object> transientVars) {
/* 631 */     this.transientVars = transientVars;
/*     */   }
/*     */ 
/*     */   public void addTransientVar(String key, Object object)
/*     */   {
/* 640 */     this.transientVars.put(key, object);
/*     */   }
/*     */ 
/*     */   public Object getTransientVar(String key)
/*     */   {
/* 649 */     return this.transientVars.get(key);
/*     */   }
/*     */ 
/*     */   public boolean isLocalApi()
/*     */   {
/* 654 */     return this.localApi;
/*     */   }
/*     */ 
/*     */   public void setLocalApi(boolean localApi) {
/* 658 */     this.localApi = localApi;
/*     */   }
/*     */ 
/*     */   public boolean isSkip()
/*     */   {
/* 663 */     return this.skip;
/*     */   }
/*     */ 
/*     */   public void setSkip(boolean skip) {
/* 667 */     this.skip = skip;
/*     */   }
/*     */ 
/*     */   public boolean isFromMobile() {
/* 671 */     return this.fromMobile;
/*     */   }
/*     */ 
/*     */   public void setFromMobile(boolean fromMobile) {
/* 675 */     this.fromMobile = fromMobile;
/*     */   }
/*     */ 
/*     */   public boolean isStartFlow()
/*     */   {
/* 685 */     return this.startFlow;
/*     */   }
/*     */ 
/*     */   public void setStartFlow(boolean startFlow)
/*     */   {
/* 692 */     this.startFlow = startFlow;
/*     */   }
/*     */ 
/*     */   public List<ForkTaskReject> getForkTaskRejects() {
/* 696 */     return this.forkTaskRejects;
/*     */   }
/*     */ 
/*     */   public void setForkTaskRejects(List<ForkTaskReject> forkTaskRejects) {
/* 700 */     this.forkTaskRejects = forkTaskRejects;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 705 */     return "ProcessCmd [actDefId=" + this.actDefId + ", flowKey=" + this.flowKey + ", taskId=" + this.taskId + ", runId=" + this.runId + ", destTask=" + this.destTask + ", isBack=" + this.isBack + ", isRecover=" + this.isRecover + ", isOnlyCompleteTask=" + this.isOnlyCompleteTask + ", voteAgree=" + this.voteAgree + ", informType=" + this.informType + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.ProcessCmd
 * JD-Core Version:    0.6.2
 */