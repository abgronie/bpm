package com.hotent.core.bpmn20.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="tTask")
@XmlSeeAlso({ManualTask.class, ServiceTask.class, ScriptTask.class, ReceiveTask.class, BusinessRuleTask.class, SendTask.class, UserTask.class})
public class Task extends Activity
{
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Task
 * JD-Core Version:    0.6.2
 */