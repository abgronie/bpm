package com.hotent.core.api.log;

import java.util.Date;

public abstract interface ISysJobLogService
{
  public abstract void addLog(ISysJobLog paramISysJobLog);

  public abstract ISysJobLog getJobLog(String paramString1, String paramString2, Date paramDate1, Date paramDate2, long paramLong, String paramString3, int paramInt);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.log.ISysJobLogService
 * JD-Core Version:    0.6.2
 */