/*    */ package com.hotent.core.bpmn20.entity;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlSchemaType;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="tGlobalScriptTask", propOrder={"script"})
/*    */ public class GlobalScriptTask extends GlobalTask
/*    */ {
/*    */   protected Script script;
/*    */ 
/*    */   @XmlAttribute
/*    */   @XmlSchemaType(name="anyURI")
/*    */   protected String scriptLanguage;
/*    */ 
/*    */   public Script getScript()
/*    */   {
/* 61 */     return this.script;
/*    */   }
/*    */ 
/*    */   public void setScript(Script value)
/*    */   {
/* 73 */     this.script = value;
/*    */   }
/*    */ 
/*    */   public String getScriptLanguage()
/*    */   {
/* 85 */     return this.scriptLanguage;
/*    */   }
/*    */ 
/*    */   public void setScriptLanguage(String value)
/*    */   {
/* 97 */     this.scriptLanguage = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.GlobalScriptTask
 * JD-Core Version:    0.6.2
 */