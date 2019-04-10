package com.hotent.core.bpmn20.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="tEventDefinition")
@XmlSeeAlso({TimerEventDefinition.class, CancelEventDefinition.class, MessageEventDefinition.class, ErrorEventDefinition.class, ConditionalEventDefinition.class, TerminateEventDefinition.class, LinkEventDefinition.class, EscalationEventDefinition.class, CompensateEventDefinition.class, SignalEventDefinition.class})
public abstract class EventDefinition extends RootElement
{
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.EventDefinition
 * JD-Core Version:    0.6.2
 */