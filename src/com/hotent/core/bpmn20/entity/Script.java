/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAnyElement;
/*    */ import javax.xml.bind.annotation.XmlMixed;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tScript", propOrder={"content"})
/*    */ public class Script
/*    */ {
/*    */ 
/*    */   @XmlMixed
/*    */   @XmlAnyElement(lax=true)
/*    */   protected List<Object> content;
/*    */ 
/*    */   public List<Object> getContent()
/*    */   {
/* 75 */     if (this.content == null) {
/* 76 */       this.content = new ArrayList();
/*    */     }
/* 78 */     return this.content;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.Script
 * JD-Core Version:    0.6.2
 */