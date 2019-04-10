/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAnyElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tExtensionElements", propOrder={"any"})
/*    */ public class ExtensionElements
/*    */ {
/*    */ 
/*    */   @XmlAnyElement(lax=true)
/*    */   protected List<Object> any;
/*    */ 
/*    */   public List<Object> getAny()
/*    */   {
/* 72 */     if (this.any == null) {
/* 73 */       this.any = new ArrayList();
/*    */     }
/* 75 */     return this.any;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ExtensionElements
 * JD-Core Version:    0.6.2
 */