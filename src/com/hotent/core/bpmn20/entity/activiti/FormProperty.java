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
/*     */ @XmlType(name="", propOrder={"value"})
/*     */ @XmlRootElement(name="formProperty")
/*     */ public class FormProperty
/*     */ {
/*     */   protected List<Value> value;
/*     */ 
/*     */   @XmlAttribute(required=true)
/*     */   protected String id;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String name;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String type;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String readable;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String writable;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String required;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String variable;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String expression;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected String datePattern;
/*     */ 
/*     */   @XmlAttribute(name="value")
/*     */   protected String attrValue;
/*     */ 
/*     */   public List<Value> getValue()
/*     */   {
/* 110 */     if (this.value == null) {
/* 111 */       this.value = new ArrayList();
/*     */     }
/* 113 */     return this.value;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 125 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String value)
/*     */   {
/* 137 */     this.id = value;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 149 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String value)
/*     */   {
/* 161 */     this.name = value;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 173 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String value)
/*     */   {
/* 185 */     this.type = value;
/*     */   }
/*     */ 
/*     */   public String getReadable()
/*     */   {
/* 197 */     return this.readable;
/*     */   }
/*     */ 
/*     */   public void setReadable(String value)
/*     */   {
/* 209 */     this.readable = value;
/*     */   }
/*     */ 
/*     */   public String getWritable()
/*     */   {
/* 221 */     return this.writable;
/*     */   }
/*     */ 
/*     */   public void setWritable(String value)
/*     */   {
/* 233 */     this.writable = value;
/*     */   }
/*     */ 
/*     */   public String getRequired()
/*     */   {
/* 245 */     return this.required;
/*     */   }
/*     */ 
/*     */   public void setRequired(String value)
/*     */   {
/* 257 */     this.required = value;
/*     */   }
/*     */ 
/*     */   public String getVariable()
/*     */   {
/* 269 */     return this.variable;
/*     */   }
/*     */ 
/*     */   public void setVariable(String value)
/*     */   {
/* 281 */     this.variable = value;
/*     */   }
/*     */ 
/*     */   public String getExpression()
/*     */   {
/* 293 */     return this.expression;
/*     */   }
/*     */ 
/*     */   public void setExpression(String value)
/*     */   {
/* 305 */     this.expression = value;
/*     */   }
/*     */ 
/*     */   public String getDatePattern()
/*     */   {
/* 317 */     return this.datePattern;
/*     */   }
/*     */ 
/*     */   public void setDatePattern(String value)
/*     */   {
/* 329 */     this.datePattern = value;
/*     */   }
/*     */ 
/*     */   public String getAttrValue()
/*     */   {
/* 341 */     return this.attrValue;
/*     */   }
/*     */ 
/*     */   public void setAttrValue(String value)
/*     */   {
/* 353 */     this.attrValue = value;
/*     */   }
/*     */ 
/*     */   @XmlAccessorType(XmlAccessType.FIELD)
/*     */   @XmlType(name="")
/*     */   public static class Value
/*     */   {
/*     */ 
/*     */     @XmlAttribute
/*     */     protected String id;
/*     */ 
/*     */     @XmlAttribute
/*     */     protected String name;
/*     */ 
/*     */     public String getId()
/*     */     {
/* 393 */       return this.id;
/*     */     }
/*     */ 
/*     */     public void setId(String value)
/*     */     {
/* 405 */       this.id = value;
/*     */     }
/*     */ 
/*     */     public String getName()
/*     */     {
/* 417 */       return this.name;
/*     */     }
/*     */ 
/*     */     public void setName(String value)
/*     */     {
/* 429 */       this.name = value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.activiti.FormProperty
 * JD-Core Version:    0.6.2
 */