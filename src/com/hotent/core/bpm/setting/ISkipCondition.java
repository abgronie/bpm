package com.hotent.core.bpm.setting;

import com.hotent.core.api.org.model.ISysUser;
import org.activiti.engine.task.Task;

public abstract interface ISkipCondition
{
  public abstract boolean canSkip(Task paramTask);

  public abstract ISysUser getExecutor();

  public abstract String getTitle();
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.setting.ISkipCondition
 * JD-Core Version:    0.6.2
 */