/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tScriptTask", propOrder={"script"})
/*    */ public class ScriptTask extends Task
/*    */ {
/*    */   protected Script script;
/*    */ 
/*    */   @XmlAttribute
/*    */   protected String scriptFormat;
/*    */ 
/*    */   public Script getScript()
/*    */   {
/* 59 */     return this.script;
/*    */   }
/*    */ 
/*    */   public void setScript(Script value)
/*    */   {
/* 71 */     this.script = value;
/*    */   }
/*    */ 
/*    */   public String getScriptFormat()
/*    */   {
/* 83 */     return this.scriptFormat;
/*    */   }
/*    */ 
/*    */   public void setScriptFormat(String value)
/*    */   {
/* 95 */     this.scriptFormat = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ScriptTask
 * JD-Core Version:    0.6.2
 */