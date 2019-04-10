package com.hotent.core.datakey;

public abstract interface IKeyGenerator
{
  public abstract Object nextId()
    throws Exception;

  public abstract void setAlias(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.datakey.IKeyGenerator
 * JD-Core Version:    0.6.2
 */