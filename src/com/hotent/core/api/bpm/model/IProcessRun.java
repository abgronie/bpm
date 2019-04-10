package com.hotent.core.api.bpm.model;

import java.util.Date;

public abstract interface IProcessRun
{
  public abstract Long getRunId();

  public abstract void setRunId(Long paramLong);

  public abstract Long getDefId();

  public abstract void setDefId(Long paramLong);

  public abstract String getSubject();

  public abstract void setSubject(String paramString);

  public abstract Long getCreatorId();

  public abstract void setCreatorId(Long paramLong);

  public abstract String getCreator();

  public abstract void setCreator(String paramString);

  public abstract Date getCreatetime();

  public abstract void setCreatetime(Date paramDate);

  public abstract String getBusDescp();

  public abstract void setBusDescp(String paramString);

  public abstract Short getStatus();

  public abstract void setStatus(Short paramShort);

  public abstract String getActInstId();

  public abstract void setActInstId(String paramString);

  public abstract String getActDefId();

  public abstract void setActDefId(String paramString);

  public abstract String getBusinessKey();

  public abstract void setBusinessKey(String paramString);

  public abstract Long getBusinessKeyNum();

  public abstract void setBusinessKeyNum(Long paramLong);

  public abstract String getBusinessUrl();

  public abstract void setBusinessUrl(String paramString);

  public abstract Date getEndTime();

  public abstract void setEndTime(Date paramDate);

  public abstract Long getDuration();

  public abstract void setDuration(Long paramLong);

  public abstract String getProcessName();

  public abstract void setProcessName(String paramString);

  public abstract String getPkName();

  public abstract void setPkName(String paramString);

  public abstract String getTableName();

  public abstract void setTableName(String paramString);

  public abstract Long getParentId();

  public abstract void setParentId(Long paramLong);

  public abstract String getStartOrgName();

  public abstract void setStartOrgName(String paramString);

  public abstract Long getStartOrgId();

  public abstract void setStartOrgId(Long paramLong);

  public abstract Short getRecover();

  public abstract void setRecover(Short paramShort);

  public abstract Long getFormDefId();

  public abstract void setFormDefId(Long paramLong);

  public abstract String getDsAlias();

  public abstract void setDsAlias(String paramString);

  public abstract String getFormKeyUrl();

  public abstract void setFormKeyUrl(String paramString);

  public abstract Short getFormType();

  public abstract void setFormType(Short paramShort);

  public abstract String getFlowKey();

  public abstract void setFlowKey(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.bpm.model.IProcessRun
 * JD-Core Version:    0.6.2
 */