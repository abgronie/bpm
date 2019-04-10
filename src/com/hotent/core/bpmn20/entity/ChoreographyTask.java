/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tChoreographyTask", propOrder={"messageFlowRef"})
/*    */ public class ChoreographyTask extends ChoreographyActivity
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected List<QName> messageFlowRef;
/*    */ 
/*    */   public List<QName> getMessageFlowRef()
/*    */   {
/* 74 */     if (this.messageFlowRef == null) {
/* 75 */       this.messageFlowRef = new ArrayList();
/*    */     }
/* 77 */     return this.messageFlowRef;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ChoreographyTask
 * JD-Core Version:    0.6.2
 */