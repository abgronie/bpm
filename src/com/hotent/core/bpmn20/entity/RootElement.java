package com.hotent.core.bpmn20.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="tRootElement")
@XmlSeeAlso({ItemDefinition.class, Category.class, EndPoint.class, PartnerRole.class, PartnerEntity.class, Collaboration.class, Signal.class, EventDefinition.class, DataStore.class, Error.class, Resource.class, Interface.class, CorrelationProperty.class, Message.class, CallableElement.class, Escalation.class})
public abstract class RootElement extends BaseElement
{
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.RootElement
 * JD-Core Version:    0.6.2
 */