/*     */ package com.hotent.core.bpmn20.entity.activiti;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="", propOrder={"string", "expression"})
/*     */ @XmlRootElement(name="field")
/*     */ public class Field
/*     */ {
/*     */   protected String string;
/*     */   protected String expression;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String stringValue;
/*     */ 
/*     */   @XmlAttribute(name="expression")
/*     */   protected String attrExpression;
/*     */ 
/*     */   public String getString()
/*     */   {
/*  67 */     return this.string;
/*     */   }
/*     */ 
/*     */   public void setString(String value)
/*     */   {
/*  79 */     this.string = value;
/*     */   }
/*     */ 
/*     */   public String getExpression()
/*     */   {
/*  91 */     return this.expression;
/*     */   }
/*     */ 
/*     */   public void setExpression(String value)
/*     */   {
/* 103 */     this.expression = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 115 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 127 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public String getStringValue()
/*     */   {
/* 139 */     return this.stringValue;
/*     */   }
/*     */ 
/*     */   public void setStringValue(String value)
/*     */   {
/* 151 */     this.stringValue = value;
/*     */   }
/*     */ 
/*     */   public String getAttrExpression()
/*     */   {
/* 163 */     return this.attrExpression;
/*     */   }
/*     */ 
/*     */   public void setAttrExpression(String value)
/*     */   {
/* 175 */     this.attrExpression = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.activiti.Field
 * JD-Core Version:    0.6.2
 */