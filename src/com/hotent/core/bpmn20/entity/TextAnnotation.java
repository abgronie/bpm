/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tTextAnnotation", propOrder={"text"})
/*    */ public class TextAnnotation extends Artifact
/*    */ {
/*    */   protected Text text;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String textFormat;
/*    */ 
/*    */   public Text getText()
/*    */   {
/* 59 */     return this.text;
/*    */   }
/*    */ 
/*    */   public void setText(Text value)
/*    */   {
/* 71 */     this.text = value;
/*    */   }
/*    */ 
/*    */   public String getTextFormat()
/*    */   {
/* 83 */     if (this.textFormat == null) {
/* 84 */       return "text/plain";
/*    */     }
/* 86 */     return this.textFormat;
/*    */   }
/*    */ 
/*    */   public void setTextFormat(String value)
/*    */   {
/* 99 */     this.textFormat = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.TextAnnotation
 * JD-Core Version:    0.6.2
 */