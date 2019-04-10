/*    */ package com.hotent.core.api.org.model;
/*    */ 
/*    */ public abstract interface ISysOrg
/*    */ {
/*  5 */   public static final Long BEGIN_DEMID = Long.valueOf(1L);
/*  6 */   public static final Long BEGIN_ORGID = Long.valueOf(1L);
/*  7 */   public static final Integer BEGIN_DEPTH = Integer.valueOf(0);
/*    */   public static final String BEGIN_PATH = "1";
/*  9 */   public static final Short BEGIN_TYPE = Short.valueOf((short)1);
/* 10 */   public static final Long BEGIN_ORGSUPID = Long.valueOf(-1L);
/* 11 */   public static final Short BEGIN_SN = Short.valueOf((short)1);
/* 12 */   public static final Short BEGIN_FROMTYPE = Short.valueOf((short)0);
/*    */ 
/* 14 */   public static final Short FROMTYPE_AD = Short.valueOf((short)1);
/* 15 */   public static final Short FROMTYPE_DB = Short.valueOf((short)0);
/*    */ 
/* 17 */   public static final Integer IS_LEAF_N = Integer.valueOf(1);
/* 18 */   public static final Integer IS_LEAF_Y = Integer.valueOf(0);
/*    */   public static final String IS_PARENT_N = "false";
/*    */   public static final String IS_PARENT_Y = "true";
/*    */ 
/*    */   public abstract void setOrgId(Long paramLong);
/*    */ 
/*    */   public abstract Long getOrgId();
/*    */ 
/*    */   public abstract void setDemId(Long paramLong);
/*    */ 
/*    */   public abstract Long getDemId();
/*    */ 
/*    */   public abstract String getDemName();
/*    */ 
/*    */   public abstract void setDemName(String paramString);
/*    */ 
/*    */   public abstract void setOrgName(String paramString);
/*    */ 
/*    */   public abstract String getOrgName();
/*    */ 
/*    */   public abstract void setOrgDesc(String paramString);
/*    */ 
/*    */   public abstract String getOrgDesc();
/*    */ 
/*    */   public abstract void setOrgPathname(String paramString);
/*    */ 
/*    */   public abstract String getOrgPathname();
/*    */ 
/*    */   public abstract void setOrgSupId(Long paramLong);
/*    */ 
/*    */   public abstract Long getOrgSupId();
/*    */ 
/*    */   public abstract String getOrgSupName();
/*    */ 
/*    */   public abstract void setOrgSupName(String paramString);
/*    */ 
/*    */   public abstract void setPath(String paramString);
/*    */ 
/*    */   public abstract String getPath();
/*    */ 
/*    */   public abstract void setDepth(Integer paramInteger);
/*    */ 
/*    */   public abstract Integer getDepth();
/*    */ 
/*    */   public abstract void setOrgType(Long paramLong);
/*    */ 
/*    */   public abstract Long getOrgType();
/*    */ 
/*    */   public abstract Short getFromType();
/*    */ 
/*    */   public abstract void setFromType(Short paramShort);
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.org.model.ISysOrg
 * JD-Core Version:    0.6.2
 */