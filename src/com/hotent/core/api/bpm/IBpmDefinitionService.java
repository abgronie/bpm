package com.hotent.core.api.bpm;

import com.hotent.core.api.bpm.model.IBpmDefinition;

public abstract interface IBpmDefinitionService
{
  public abstract IBpmDefinition getMainDefByActDefKey(String paramString);

  public abstract IBpmDefinition getByActDefId(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.bpm.IBpmDefinitionService
 * JD-Core Version:    0.6.2
 */