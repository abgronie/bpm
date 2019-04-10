/*     */ package com.hotent.core.bpmn20.entity.activiti;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="")
/*     */ @XmlRootElement(name="out")
/*     */ public class Out
/*     */ {
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String source;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String sourceExpression;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String target;
/*     */ 
/*     */   public String getSource()
/*     */   {
/*  58 */     return this.source;
/*     */   }
/*     */ 
/*     */   public void setSource(String value)
/*     */   {
/*  70 */     this.source = value;
/*     */   }
/*     */ 
/*     */   public String getSourceExpression()
/*     */   {
/*  82 */     return this.sourceExpression;
/*     */   }
/*     */ 
/*     */   public void setSourceExpression(String value)
/*     */   {
/*  94 */     this.sourceExpression = value;
/*     */   }
/*     */ 
/*     */   public String getTarget()
/*     */   {
/* 106 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void setTarget(String value)
/*     */   {
/* 118 */     this.target = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.activiti.Out
 * JD-Core Version:    0.6.2
 */