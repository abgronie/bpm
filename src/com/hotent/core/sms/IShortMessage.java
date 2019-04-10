package com.hotent.core.sms;

import java.util.List;

public abstract interface IShortMessage
{
  public abstract boolean sendSms(List<String> paramList, String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.sms.IShortMessage
 * JD-Core Version:    0.6.2
 */