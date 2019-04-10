/*     */ package com.hotent.core.bpm.model;
/*     */ 
/*     */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*     */ 
/*     */ public class ProcessExecution
/*     */ {
/*     */   private String id;
/*     */   private Integer revision;
/*     */   private String processInstanceId;
/*     */   private String businessKey;
/*     */   private String processDefinitionId;
/*     */   private String activityId;
/*     */   private Short isActive;
/*     */   private Short isConcurrent;
/*     */   private Short isScope;
/*     */   private String parentId;
/*     */   private String superExecutionId;
/*     */   private Short isEventScope;
/*     */   private Integer suspensionState;
/*     */   private Integer cachedEntityState;
/*     */ 
/*     */   public ProcessExecution()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProcessExecution(ExecutionEntity executionEntity)
/*     */   {
/*  39 */     this.revision = Integer.valueOf(executionEntity.getRevision());
/*  40 */     this.processInstanceId = executionEntity.getProcessInstanceId();
/*  41 */     this.processDefinitionId = executionEntity.getProcessDefinitionId();
/*  42 */     this.activityId = executionEntity.getActivityId();
/*  43 */     this.isActive = Short.valueOf((short)(executionEntity.isActive() ? 1 : 0));
/*  44 */     this.isConcurrent = Short.valueOf((short)(executionEntity.isConcurrent() ? 1 : 0));
/*  45 */     this.isScope = Short.valueOf((short)(executionEntity.isScope() ? 1 : 0));
/*  46 */     this.parentId = executionEntity.getParentId();
/*  47 */     this.superExecutionId = executionEntity.getSuperExecutionId();
/*  48 */     this.isEventScope = Short.valueOf((short)(executionEntity.isEventScope() ? 1 : 0));
/*  49 */     this.suspensionState = Integer.valueOf(executionEntity.getSuspensionState());
/*  50 */     this.cachedEntityState = Integer.valueOf(executionEntity.getCachedEntityState());
/*     */   }
/*     */ 
/*     */   public Integer getRevision()
/*     */   {
/*  55 */     return this.revision;
/*     */   }
/*     */ 
/*     */   public void setRevision(Integer revision)
/*     */   {
/*  60 */     this.revision = revision;
/*     */   }
/*     */ 
/*     */   public String getProcessInstanceId()
/*     */   {
/*  65 */     return this.processInstanceId;
/*     */   }
/*     */ 
/*     */   public void setProcessInstanceId(String processInstanceId)
/*     */   {
/*  70 */     this.processInstanceId = processInstanceId;
/*     */   }
/*     */ 
/*     */   public String getBusinessKey()
/*     */   {
/*  75 */     return this.businessKey;
/*     */   }
/*     */ 
/*     */   public void setBusinessKey(String businessKey) {
/*  79 */     this.businessKey = businessKey;
/*     */   }
/*     */ 
/*     */   public String getParentId() {
/*  83 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(String parentId) {
/*  87 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public String getProcessDefinitionId() {
/*  91 */     return this.processDefinitionId;
/*     */   }
/*     */ 
/*     */   public void setProcessDefinitionId(String processDefinitionId) {
/*  95 */     this.processDefinitionId = processDefinitionId;
/*     */   }
/*     */ 
/*     */   public String getSuperExecutionId() {
/*  99 */     return this.superExecutionId;
/*     */   }
/*     */ 
/*     */   public void setSuperExecutionId(String superExecutionId) {
/* 103 */     this.superExecutionId = superExecutionId;
/*     */   }
/*     */ 
/*     */   public String getActivityId() {
/* 107 */     return this.activityId;
/*     */   }
/*     */ 
/*     */   public void setActivityId(String activityId) {
/* 111 */     this.activityId = activityId;
/*     */   }
/*     */ 
/*     */   public Short getIsActive() {
/* 115 */     return this.isActive;
/*     */   }
/*     */ 
/*     */   public void setIsActive(Short isActive) {
/* 119 */     this.isActive = isActive;
/*     */   }
/*     */ 
/*     */   public Short getIsConcurrent() {
/* 123 */     return this.isConcurrent;
/*     */   }
/*     */ 
/*     */   public void setIsConcurrent(Short isConcurrent) {
/* 127 */     this.isConcurrent = isConcurrent;
/*     */   }
/*     */ 
/*     */   public Short getIsScope() {
/* 131 */     return this.isScope;
/*     */   }
/*     */ 
/*     */   public void setIsScope(Short isScope) {
/* 135 */     this.isScope = isScope;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 140 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 145 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Short getIsEventScope() {
/* 149 */     return this.isEventScope;
/*     */   }
/*     */ 
/*     */   public void setIsEventScope(Short isEventScope) {
/* 153 */     this.isEventScope = isEventScope;
/*     */   }
/*     */ 
/*     */   public Integer getSuspensionState() {
/* 157 */     return this.suspensionState;
/*     */   }
/*     */ 
/*     */   public void setSuspensionState(Integer suspensionState) {
/* 161 */     this.suspensionState = suspensionState;
/*     */   }
/*     */ 
/*     */   public Integer getCachedEntityState() {
/* 165 */     return this.cachedEntityState;
/*     */   }
/*     */ 
/*     */   public void setCachedEntityState(Integer cachedEntityState) {
/* 169 */     this.cachedEntityState = cachedEntityState;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.ProcessExecution
 * JD-Core Version:    0.6.2
 */