/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlSeeAlso;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tEvent", propOrder={"property"})
/*    */ @XmlSeeAlso({ThrowEvent.class, CatchEvent.class})
/*    */ public abstract class Event extends FlowNode
/*    */ {
/*    */   protected List<Property> property;
/*    */ 
/*    */   public List<Property> getProperty()
/*    */   {
/* 76 */     if (this.property == null) {
/* 77 */       this.property = new ArrayList();
/*    */     }
/* 79 */     return this.property;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Event
 * JD-Core Version:    0.6.2
 */