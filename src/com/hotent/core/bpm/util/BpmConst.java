/*     */ package com.hotent.core.bpm.util;
/*     */ 
/*     */ public class BpmConst
/*     */ {
/*     */   public static final String PRE_ORG_ID = "preOrgId";
/*     */   public static final String START_ORG_ID = "startOrgId";
/*     */   public static final String START_POS_ID = "startPosId";
/*     */   public static final String START_JOB_ID = "startJobId";
/*     */   public static final String StartUser = "startUser";
/*     */   public static final String PrevUser = "prevUser";
/*     */   public static final String StartEvent = "start";
/*     */   public static final String EndEvent = "end";
/*     */   public static final String CreateEvent = "create";
/*     */   public static final String CompleteEvent = "complete";
/*     */   public static final String AssignmentEvent = "assignment";
/*     */   public static final String PROCESS_EXT_VARNAME = "outPassVars";
/*     */   public static final String PROCESS_INNER_VARNAME = "innerPassVars";
/*     */   public static final String FLOW_RUN_SUBJECT = "subject_";
/*     */   public static final String FLOW_SERIALNO = "serialNo_";
/*     */   public static final String FLOW_INFORM_TYPE = "informType";
/*     */   public static final String IS_EXTERNAL_CALL = "isExtCall";
/*  68 */   public static final Integer StartScript = Integer.valueOf(1);
/*     */ 
/*  73 */   public static final Integer EndScript = Integer.valueOf(2);
/*     */ 
/*  78 */   public static final Integer ScriptNodeScript = Integer.valueOf(3);
/*     */ 
/*  82 */   public static final Integer AssignScript = Integer.valueOf(4);
/*     */ 
/*  87 */   public static final Integer StartBeforeScript = Integer.valueOf(5);
/*     */ 
/*  92 */   public static final Integer EndBeforeScript = Integer.valueOf(6);
/*     */ 
/*  97 */   public static final Integer SignCompleteScript = Integer.valueOf(7);
/*     */   public static final String NODE_APPROVAL_STATUS = "approvalStatus";
/*     */   public static final String NODE_APPROVAL_CONTENT = "approvalContent";
/* 111 */   public static final Integer TASK_BACK = Integer.valueOf(1);
/*     */ 
/* 116 */   public static final Integer TASK_BACK_TOSTART = Integer.valueOf(2);
/*     */ 
/* 123 */   public static final Short OnLineForm = Short.valueOf((short)0);
/*     */ 
/* 128 */   public static final Short UrlForm = Short.valueOf((short)1);
/*     */   public static final String FORM_PK_REGEX = "\\{pk\\}";
/*     */   public static final String FLOW_BUSINESSKEY = "businessKey";
/*     */   public static final String FLOW_RUNID = "flowRunId";
/*     */   public static final String FLOW_PARENT_ACTDEFID = "parentActDefId";
/*     */   public static final String FLOW_MAIN_ACTDEFID = "mainActDefId";
/*     */   public static final String SIGN_USERIDS = "signUsers";
/*     */   public static final String SUBPRO_MULTI_USERIDS = "subAssignIds";
/*     */   public static final String SUBPRO_EXT_MULTI_USERIDS = "subExtAssignIds";
/*     */   public static final String MESSAGE_TYPE_MAIL = "1";
/*     */   public static final String MESSAGE_TYPE_SMS = "2";
/*     */   public static final String MESSAGE_TYPE_INNER = "3";
/*     */   public static final String MESSAGE_TYPE_WEIXIN = "4";
/*     */   public static final String EMPTY_USER = "0";
/* 199 */   public static String PREVIEW_FORMUSER = "__formUserId__";
/*     */ 
/* 201 */   public static String PREVIEW_FORMORG = "__formOrgId__";
/*     */ 
/* 204 */   public static String PREVIEW_FORMPOS = "__formPosId__";
/*     */ 
/* 206 */   public static String PREVIEW_FORMROLE = "__formRoleId__";
/*     */ 
/* 211 */   public static String EXECUTION_ID_ = "executionId";
/*     */   public static final String BPM_DEF = "bpm_definition";
/*     */   public static final String BPM_DEFKEY = "flowKey";
/*     */   public static final String OPINION_FIELD = "opinionField";
/*     */   public static final String OPINION_FIELDS = "opinionFields";
/*     */   public static final String OPINION_SUPPORTHTML = "optionHtml";
/* 239 */   public static String LOCAL_DATASOURCE = "LOCAL";
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.util.BpmConst
 * JD-Core Version:    0.6.2
 */