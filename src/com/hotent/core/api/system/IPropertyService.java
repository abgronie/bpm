package com.hotent.core.api.system;

public abstract interface IPropertyService
{
  public abstract String getByAlias(String paramString);

  public abstract String getByAlias(String paramString1, String paramString2);

  public abstract Integer getIntByAlias(String paramString);

  public abstract Integer getIntByAlias(String paramString, Integer paramInteger);

  public abstract Long getLongByAlias(String paramString);

  public abstract boolean getBooleanByAlias(String paramString);

  public abstract boolean getBooleanByAlias(String paramString, boolean paramBoolean);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.system.IPropertyService
 * JD-Core Version:    0.6.2
 */