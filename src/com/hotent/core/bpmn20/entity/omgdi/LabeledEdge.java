package com.hotent.core.bpmn20.entity.omgdi;

import com.hotent.core.bpmn20.entity.bpmndi.BPMNEdge;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="LabeledEdge")
@XmlSeeAlso({BPMNEdge.class})
public abstract class LabeledEdge extends Edge
{
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.LabeledEdge
 * JD-Core Version:    0.6.2
 */