package com.hotent.core.api.bpm;

public abstract interface IBpmDao
{
  public abstract String getActDefIdByDeployId(String paramString);

  public abstract String getDefXmlByDeployId(String paramString);

  public abstract void wirteDefXml(String paramString1, String paramString2);

  public abstract String getDeployIdByActdefId(String paramString);

  public abstract String getXmlByDefKey(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.bpm.IBpmDao
 * JD-Core Version:    0.6.2
 */