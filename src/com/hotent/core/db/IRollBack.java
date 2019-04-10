package com.hotent.core.db;

import java.util.Map;

public abstract interface IRollBack
{
  public abstract Object execute(String paramString, Map<String, Object> paramMap);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.IRollBack
 * JD-Core Version:    0.6.2
 */