/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tCallActivity")
/*    */ public class CallActivity extends Activity
/*    */ {
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String calledElement;
/*    */ 
/*    */   public String getCalledElement()
/*    */   {
/* 53 */     return this.calledElement;
/*    */   }
/*    */ 
/*    */   public void setCalledElement(String value)
/*    */   {
/* 65 */     this.calledElement = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.CallActivity
 * JD-Core Version:    0.6.2
 */