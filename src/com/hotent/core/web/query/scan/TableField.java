/*     */ package com.hotent.core.web.query.scan;
/*     */ 
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class TableField
/*     */ {
/*     */   private String fieldType;
/*     */   private String name;
/*     */   private String var;
/*     */   private String desc;
/*     */   private String options;
/*     */   private short controlType;
/*     */   private String style;
/*     */   private String dataType;
/*     */ 
/*     */   public TableField()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TableField(java.lang.reflect.Field field, com.hotent.core.annotion.query.Field qField)
/*     */   {
/*  56 */     this.fieldType = field.getType().getName();
/*  57 */     this.var = field.getName();
/*  58 */     this.name = qField.name();
/*  59 */     this.desc = qField.desc();
/*  60 */     if (StringUtil.isNotEmpty(qField.options()))
/*  61 */       this.options = qField.options();
/*  62 */     this.controlType = qField.controlType();
/*  63 */     this.style = qField.style();
/*  64 */     this.dataType = qField.dataType();
/*     */   }
/*     */ 
/*     */   public String getFieldType() {
/*  68 */     return this.fieldType;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  72 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getVar() {
/*  76 */     return this.var;
/*     */   }
/*     */ 
/*     */   public String getDesc() {
/*  80 */     return this.desc;
/*     */   }
/*     */ 
/*     */   public String getOptions()
/*     */   {
/*  87 */     return this.options;
/*     */   }
/*     */ 
/*     */   public short getControlType() {
/*  91 */     return this.controlType;
/*     */   }
/*     */ 
/*     */   public String getStyle() {
/*  95 */     return this.style;
/*     */   }
/*     */ 
/*     */   public String getDataType() {
/*  99 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 105 */     return new ToStringBuilder(this).append("fieldType", this.fieldType).append("name", this.name).append("desc", this.desc).append("dataType", this.dataType).toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.scan.TableField
 * JD-Core Version:    0.6.2
 */