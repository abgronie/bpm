/*    */ package com.hotent.core.bpmn20.entity.bpmndi;
/*    */ 
/*    */ import com.hotent.core.bpmn20.entity.omgdc.Font;
/*    */ import com.hotent.core.bpmn20.entity.omgdi.Style;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="BPMNLabelStyle", propOrder={"font"})
/*    */ public class BPMNLabelStyle extends Style
/*    */ {
/*    */ 
/*    */   @XmlElement(name="Font", namespace="http://www.omg.org/spec/DD/20100524/DC", required=true)
/*    */   protected Font font;
/*    */ 
/*    */   public Font getFont()
/*    */   {
/* 59 */     return this.font;
/*    */   }
/*    */ 
/*    */   public void setFont(Font value)
/*    */   {
/* 71 */     this.font = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.bpmndi.BPMNLabelStyle
 * JD-Core Version:    0.6.2
 */