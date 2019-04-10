package com.hotent.core.cache;

public abstract interface ICache
{
  public abstract void add(String paramString, Object paramObject, int paramInt);

  public abstract void add(String paramString, Object paramObject);

  public abstract void delByKey(String paramString);

  public abstract void clearAll();

  public abstract Object getByKey(String paramString);

  public abstract boolean containKey(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.cache.ICache
 * JD-Core Version:    0.6.2
 */