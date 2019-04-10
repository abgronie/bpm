package com.hotent.core.api.system.model;

public abstract interface ISysDataSource
{
  public abstract void setId(Long paramLong);

  public abstract Long getId();

  public abstract void setName(String paramString);

  public abstract String getName();

  public abstract void setAlias(String paramString);

  public abstract String getAlias();

  public abstract void setDbType(String paramString);

  public abstract String getDbType();

  public abstract void setSettingJson(String paramString);

  public abstract String getSettingJson();

  public abstract Boolean getInitOnStart();

  public abstract void setInitOnStart(Boolean paramBoolean);

  public abstract Boolean getEnabled();

  public abstract void setEnabled(Boolean paramBoolean);

  public abstract void setClassPath(String paramString);

  public abstract String getClassPath();

  public abstract void setInitMethod(String paramString);

  public abstract String getInitMethod();

  public abstract void setCloseMethod(String paramString);

  public abstract String getCloseMethod();

  public abstract Long getRunId();

  public abstract void setRunId(Long paramLong);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.system.model.ISysDataSource
 * JD-Core Version:    0.6.2
 */