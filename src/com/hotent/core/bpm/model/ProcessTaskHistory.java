/*     */ package com.hotent.core.bpm.model;
/*     */ 
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import java.util.Date;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*     */ 
/*     */ public class ProcessTaskHistory
/*     */ {
/*     */   private String id;
/*     */   private String processDefinitionId;
/*     */   private String processInstanceId;
/*     */   private String executionId;
/*     */   private String name;
/*     */   private String parentTaskId;
/*     */   private String description;
/*     */   private String owner;
/*     */   private String assignee;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Long durationInMillis;
/*     */   private String deleteReason;
/*     */   private String taskDefinitionKey;
/*     */   private Integer priority;
/*     */   private Date dueDate;
/*     */ 
/*     */   public ProcessTaskHistory()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProcessTaskHistory(TaskEntity taskEntity)
/*     */   {
/*  36 */     this.processDefinitionId = taskEntity.getId();
/*  37 */     this.processInstanceId = taskEntity.getProcessInstanceId();
/*  38 */     this.executionId = taskEntity.getExecutionId();
/*  39 */     this.name = taskEntity.getName();
/*  40 */     this.parentTaskId = taskEntity.getParentTaskId();
/*  41 */     this.description = taskEntity.getDescription();
/*  42 */     this.owner = taskEntity.getOwner();
/*  43 */     this.assignee = taskEntity.getAssignee();
/*  44 */     this.startTime = taskEntity.getCreateTime();
/*     */ 
/*  46 */     this.dueDate = taskEntity.getDueDate();
/*     */     try {
/*  48 */       this.durationInMillis = Long.valueOf(taskEntity.getDueDate() == null ? 0L : taskEntity.getDueDate().getTime() - taskEntity.getCreateTime().getTime()); } catch (Exception ex) {
/*     */     }
/*  50 */     this.taskDefinitionKey = taskEntity.getTaskDefinitionKey();
/*  51 */     this.priority = Integer.valueOf(taskEntity.getPriority());
/*     */   }
/*     */ 
/*     */   public ProcessTaskHistory(HistoricTaskInstanceEntity historyTask)
/*     */   {
/*  56 */     BeanUtils.copyProperties(this, historyTask);
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getProcessDefinitionId() {
/*  69 */     return this.processDefinitionId;
/*     */   }
/*     */ 
/*     */   public void setProcessDefinitionId(String processDefinitionId) {
/*  73 */     this.processDefinitionId = processDefinitionId;
/*     */   }
/*     */ 
/*     */   public String getProcessInstanceId() {
/*  77 */     return this.processInstanceId;
/*     */   }
/*     */ 
/*     */   public void setProcessInstanceId(String processInstanceId) {
/*  81 */     this.processInstanceId = processInstanceId;
/*     */   }
/*     */ 
/*     */   public String getExecutionId() {
/*  85 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/*  89 */     this.executionId = executionId;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  93 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  97 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getParentTaskId() {
/* 101 */     return this.parentTaskId;
/*     */   }
/*     */ 
/*     */   public void setParentTaskId(String parentTaskId) {
/* 105 */     this.parentTaskId = parentTaskId;
/*     */   }
/*     */ 
/*     */   public String getDescription() {
/* 109 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 113 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getOwner() {
/* 117 */     return this.owner;
/*     */   }
/*     */ 
/*     */   public void setOwner(String owner) {
/* 121 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */   public String getAssignee() {
/* 125 */     return this.assignee;
/*     */   }
/*     */ 
/*     */   public void setAssignee(String assignee) {
/* 129 */     this.assignee = assignee;
/*     */   }
/*     */ 
/*     */   public Date getStartTime() {
/* 133 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 137 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime() {
/* 141 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/* 145 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Long getDurationInMillis() {
/* 149 */     return this.durationInMillis;
/*     */   }
/*     */ 
/*     */   public void setDurationInMillis(Long durationInMillis) {
/* 153 */     this.durationInMillis = durationInMillis;
/*     */   }
/*     */ 
/*     */   public String getDeleteReason() {
/* 157 */     return this.deleteReason;
/*     */   }
/*     */ 
/*     */   public void setDeleteReason(String deleteReason) {
/* 161 */     this.deleteReason = deleteReason;
/*     */   }
/*     */ 
/*     */   public String getTaskDefinitionKey() {
/* 165 */     return this.taskDefinitionKey;
/*     */   }
/*     */ 
/*     */   public void setTaskDefinitionKey(String taskDefinitionKey) {
/* 169 */     this.taskDefinitionKey = taskDefinitionKey;
/*     */   }
/*     */ 
/*     */   public Integer getPriority() {
/* 173 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 177 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Date getDueDate() {
/* 181 */     return this.dueDate;
/*     */   }
/*     */ 
/*     */   public void setDueDate(Date dueDate) {
/* 185 */     this.dueDate = dueDate;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.model.ProcessTaskHistory
 * JD-Core Version:    0.6.2
 */