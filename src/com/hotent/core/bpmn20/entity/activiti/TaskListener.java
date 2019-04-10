/*     */ package com.hotent.core.bpmn20.entity.activiti;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="", propOrder={"field"})
/*     */ @XmlRootElement(name="taskListener")
/*     */ public class TaskListener
/*     */ {
/*     */   protected List<Field> field;
/*     */ 
/*     */   @XmlAttribute(name="class")
/*     */   protected String clazz;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String expression;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String delegateExpression;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String event;
/*     */ 
/*     */   public List<Field> getField()
/*     */   {
/*  91 */     if (this.field == null) {
/*  92 */       this.field = new ArrayList();
/*     */     }
/*  94 */     return this.field;
/*     */   }
/*     */ 
/*     */   public String getClazz()
/*     */   {
/* 106 */     return this.clazz;
/*     */   }
/*     */ 
/*     */   public void setClazz(String value)
/*     */   {
/* 118 */     this.clazz = value;
/*     */   }
/*     */ 
/*     */   public String getExpression()
/*     */   {
/* 130 */     return this.expression;
/*     */   }
/*     */ 
/*     */   public void setExpression(String value)
/*     */   {
/* 142 */     this.expression = value;
/*     */   }
/*     */ 
/*     */   public String getDelegateExpression()
/*     */   {
/* 154 */     return this.delegateExpression;
/*     */   }
/*     */ 
/*     */   public void setDelegateExpression(String value)
/*     */   {
/* 166 */     this.delegateExpression = value;
/*     */   }
/*     */ 
/*     */   public String getEvent()
/*     */   {
/* 178 */     return this.event;
/*     */   }
/*     */ 
/*     */   public void setEvent(String value)
/*     */   {
/* 190 */     this.event = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.activiti.TaskListener
 * JD-Core Version:    0.6.2
 */