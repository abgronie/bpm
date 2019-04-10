/*     */ package com.hotent.core.bpm.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
/*     */ public class ProcessTask
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private String subject;
/*     */   private String parentTaskId;
/*     */   private String description;
/*     */   protected int priority;
/*     */   private Date createTime;
/*     */   private String owner;
/*     */   private String assignee;
/*     */   private String delegationState;
/*     */   private String executionId;
/*     */   private String processInstanceId;
/*     */   private String processDefinitionId;
/*     */   private String taskDefinitionKey;
/*     */   private Date dueDate;
/*     */   private Integer revision;
/*     */   private String processName;
/*     */   private String taskUrl;
/*     */   private String status;
/*     */   private String type;
/*     */   private Integer allowDivert;
/*     */   private Integer ischeck;
/*     */   private Long defId;
/*     */   private Integer allowBatchApprove;
/*     */   private Long runId;
/*     */   private Long typeId;
/*     */   private String typeName;
/*     */   private String orgName;
/*     */   private String tagIds;
/*     */   private Long creatorId;
/*     */   private String creator;
/*  90 */   private Boolean isAgent = Boolean.valueOf(false);
/*     */ 
/*  95 */   private Boolean isDivert = Boolean.valueOf(false);
/*     */   private Short taskStatus;
/*     */   private String codebefore;
/* 106 */   private Integer hasRead = Integer.valueOf(0);
/*     */   private Integer reminderLv;
/*     */   private Date createDate;
/*     */   private String globalFlowNo;
/*     */ 
/*     */   public String getId()
/*     */   {
/* 121 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/* 125 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 129 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 133 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getParentTaskId() {
/* 137 */     return this.parentTaskId;
/*     */   }
/*     */ 
/*     */   public void setParentTaskId(String parentTaskId) {
/* 141 */     this.parentTaskId = parentTaskId;
/*     */   }
/*     */ 
/*     */   public String getDescription() {
/* 145 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 149 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime() {
/* 153 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 157 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public String getOwner() {
/* 161 */     return this.owner;
/*     */   }
/*     */ 
/*     */   public void setOwner(String owner) {
/* 165 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */   public String getAssignee() {
/* 169 */     return this.assignee;
/*     */   }
/*     */ 
/*     */   public void setAssignee(String assignee) {
/* 173 */     this.assignee = assignee;
/*     */   }
/*     */ 
/*     */   public String getDelegationState() {
/* 177 */     return this.delegationState;
/*     */   }
/*     */ 
/*     */   public void setDelegationState(String delegationState) {
/* 181 */     this.delegationState = delegationState;
/*     */   }
/*     */ 
/*     */   public String getExecutionId() {
/* 185 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/* 189 */     this.executionId = executionId;
/*     */   }
/*     */ 
/*     */   public String getProcessInstanceId() {
/* 193 */     return this.processInstanceId;
/*     */   }
/*     */ 
/*     */   public void setProcessInstanceId(String processInstanceId) {
/* 197 */     this.processInstanceId = processInstanceId;
/*     */   }
/*     */ 
/*     */   public String getProcessDefinitionId() {
/* 201 */     return this.processDefinitionId;
/*     */   }
/*     */ 
/*     */   public void setProcessDefinitionId(String processDefinitionId) {
/* 205 */     this.processDefinitionId = processDefinitionId;
/*     */   }
/*     */ 
/*     */   public String getTaskDefinitionKey() {
/* 209 */     return this.taskDefinitionKey;
/*     */   }
/*     */ 
/*     */   public void setTaskDefinitionKey(String taskDefinitionKey) {
/* 213 */     this.taskDefinitionKey = taskDefinitionKey;
/*     */   }
/*     */ 
/*     */   public Date getDueDate() {
/* 217 */     return this.dueDate;
/*     */   }
/*     */ 
/*     */   public void setDueDate(Date dueDate) {
/* 221 */     this.dueDate = dueDate;
/*     */   }
/*     */ 
/*     */   public String getSubject() {
/* 225 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String subject) {
/* 229 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */   public Integer getRevision() {
/* 233 */     return this.revision;
/*     */   }
/*     */ 
/*     */   public void setRevision(Integer revision) {
/* 237 */     this.revision = revision;
/*     */   }
/*     */ 
/*     */   public String getProcessName() {
/* 241 */     return this.processName;
/*     */   }
/*     */ 
/*     */   public void setProcessName(String processName) {
/* 245 */     this.processName = processName;
/*     */   }
/*     */ 
/*     */   public String getTaskUrl() {
/* 249 */     return this.taskUrl;
/*     */   }
/*     */ 
/*     */   public String getStatus() {
/* 253 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status) {
/* 257 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getType() {
/* 261 */     return this.type;
/*     */   }
/*     */ 
/*     */   public int getPriority() {
/* 265 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(int priority) {
/* 269 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/* 273 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public Integer getAllowDivert() {
/* 277 */     return this.allowDivert;
/*     */   }
/*     */ 
/*     */   public void setAllowDivert(Integer allowDivert) {
/* 281 */     this.allowDivert = allowDivert;
/*     */   }
/*     */ 
/*     */   public Integer getIscheck() {
/* 285 */     return this.ischeck;
/*     */   }
/*     */ 
/*     */   public void setIscheck(Integer ischeck) {
/* 289 */     this.ischeck = ischeck;
/*     */   }
/*     */ 
/*     */   public Long getDefId() {
/* 293 */     return this.defId;
/*     */   }
/*     */ 
/*     */   public void setDefId(Long defId) {
/* 297 */     this.defId = defId;
/*     */   }
/*     */ 
/*     */   public Integer getAllowBatchApprove() {
/* 301 */     return this.allowBatchApprove;
/*     */   }
/*     */ 
/*     */   public void setAllowBatchApprove(Integer allowBatchApprove) {
/* 305 */     this.allowBatchApprove = allowBatchApprove;
/*     */   }
/*     */ 
/*     */   public Long getRunId() {
/* 309 */     return this.runId;
/*     */   }
/*     */ 
/*     */   public void setRunId(Long runId) {
/* 313 */     this.runId = runId;
/*     */   }
/*     */ 
/*     */   public Long getTypeId() {
/* 317 */     return this.typeId;
/*     */   }
/*     */ 
/*     */   public void setTypeId(Long typeId) {
/* 321 */     this.typeId = typeId;
/*     */   }
/*     */ 
/*     */   public String getTypeName() {
/* 325 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String typeName) {
/* 329 */     this.typeName = typeName;
/*     */   }
/*     */ 
/*     */   public String getOrgName() {
/* 333 */     return this.orgName;
/*     */   }
/*     */ 
/*     */   public void setOrgName(String orgName) {
/* 337 */     this.orgName = orgName;
/*     */   }
/*     */ 
/*     */   public String getTagIds() {
/* 341 */     return this.tagIds;
/*     */   }
/*     */ 
/*     */   public void setTagIds(String tagIds) {
/* 345 */     this.tagIds = tagIds;
/*     */   }
/*     */ 
/*     */   public Long getCreatorId() {
/* 349 */     return this.creatorId;
/*     */   }
/*     */ 
/*     */   public void setCreatorId(Long creatorId) {
/* 353 */     this.creatorId = creatorId;
/*     */   }
/*     */ 
/*     */   public String getCreator() {
/* 357 */     return this.creator;
/*     */   }
/*     */ 
/*     */   public void setCreator(String creator) {
/* 361 */     this.creator = creator;
/*     */   }
/*     */ 
/*     */   public Boolean getIsAgent() {
/* 365 */     return this.isAgent;
/*     */   }
/*     */ 
/*     */   public void setIsAgent(Boolean isAgent) {
/* 369 */     this.isAgent = isAgent;
/*     */   }
/*     */ 
/*     */   public Boolean getIsDivert() {
/* 373 */     return this.isDivert;
/*     */   }
/*     */ 
/*     */   public void setIsDivert(Boolean isDivert) {
/* 377 */     this.isDivert = isDivert;
/*     */   }
/*     */ 
/*     */   public Short getTaskStatus() {
/* 381 */     return this.taskStatus;
/*     */   }
/*     */ 
/*     */   public void setTaskStatus(Short taskStatus) {
/* 385 */     this.taskStatus = taskStatus;
/*     */   }
/*     */ 
/*     */   public String getCodebefore() {
/* 389 */     return this.codebefore;
/*     */   }
/*     */ 
/*     */   public void setCodebefore(String codebefore) {
/* 393 */     this.codebefore = codebefore;
/*     */   }
/*     */ 
/*     */   public Integer getHasRead() {
/* 397 */     return this.hasRead;
/*     */   }
/*     */ 
/*     */   public void setHasRead(Integer hasRead) {
/* 401 */     this.hasRead = hasRead;
/*     */   }
/*     */ 
/*     */   public void setTaskUrl(String taskUrl) {
/* 405 */     this.taskUrl = taskUrl;
/*     */   }
/*     */ 
/*     */   public Integer getReminderLv()
/*     */   {
/* 415 */     return this.reminderLv;
/*     */   }
/*     */ 
/*     */   public void setReminderLv(Integer reminderLv)
/*     */   {
/* 423 */     this.reminderLv = reminderLv;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 433 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 441 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getGlobalFlowNo() {
/* 445 */     return this.globalFlowNo;
/*     */   }
/*     */ 
/*     */   public void setGlobalFlowNo(String globalFlowNo) {
/* 449 */     this.globalFlowNo = globalFlowNo;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.ProcessTask
 * JD-Core Version:    0.6.2
 */