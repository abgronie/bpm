/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.JAXBElement;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElementRef;
/*    */ import javax.xml.bind.annotation.XmlSeeAlso;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tGlobalTask", propOrder={"resourceRole"})
/*    */ @XmlSeeAlso({GlobalUserTask.class, GlobalBusinessRuleTask.class, GlobalScriptTask.class, GlobalManualTask.class})
/*    */ public class GlobalTask extends CallableElement
/*    */ {
/*    */ 
/*    */   @XmlElementRef(name="resourceRole", namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", type=JAXBElement.class)
/*    */   protected List<JAXBElement<? extends ResourceRole>> resourceRole;
/*    */ 
/*    */   public List<JAXBElement<? extends ResourceRole>> getResourceRole()
/*    */   {
/* 84 */     if (this.resourceRole == null) {
/* 85 */       this.resourceRole = new ArrayList();
/*    */     }
/* 87 */     return this.resourceRole;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.GlobalTask
 * JD-Core Version:    0.6.2
 */