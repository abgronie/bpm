/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.JAXBElement;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElementRef;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tSubConversation", propOrder={"conversationNode"})
/*    */ public class SubConversation extends ConversationNode
/*    */ {
/*    */ 
/*    */   @XmlElementRef(name="conversationNode", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*    */   protected List<JAXBElement<? extends ConversationNode>> conversationNode;
/*    */ 
/*    */   public List<JAXBElement<? extends ConversationNode>> getConversationNode()
/*    */   {
/* 77 */     if (this.conversationNode == null) {
/* 78 */       this.conversationNode = new ArrayList();
/*    */     }
/* 80 */     return this.conversationNode;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.SubConversation
 * JD-Core Version:    0.6.2
 */