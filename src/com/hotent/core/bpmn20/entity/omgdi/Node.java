package com.hotent.core.bpmn20.entity.omgdi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Node")
@XmlSeeAlso({Plane.class, Shape.class, Label.class})
public abstract class Node extends DiagramElement
{
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.Node
 * JD-Core Version:    0.6.2
 */