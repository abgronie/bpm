package com.hotent.core.jms;

import com.hotent.core.model.MessageModel;

public abstract interface IMessageHandler
{
  public abstract String getTitle();

  public abstract boolean getIsDefaultChecked();

  public abstract void handMessage(MessageModel paramMessageModel);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jms.IMessageHandler
 * JD-Core Version:    0.6.2
 */