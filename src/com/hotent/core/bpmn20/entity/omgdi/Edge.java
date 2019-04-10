/*    */ package com.hotent.core.bpmn20.entity.omgdi;
/*    */ 
/*    */ import com.hotent.core.bpmn20.entity.omgdc.Point;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlSeeAlso;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="Edge", propOrder={"waypoint"})
/*    */ @XmlSeeAlso({LabeledEdge.class})
/*    */ public abstract class Edge extends DiagramElement
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected List<Point> waypoint;
/*    */ 
/*    */   public List<Point> getWaypoint()
/*    */   {
/* 79 */     if (this.waypoint == null) {
/* 80 */       this.waypoint = new ArrayList();
/*    */     }
/* 82 */     return this.waypoint;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdi.Edge
 * JD-Core Version:    0.6.2
 */