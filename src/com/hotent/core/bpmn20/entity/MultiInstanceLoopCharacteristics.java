/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tMultiInstanceLoopCharacteristics", propOrder={"loopCardinality", "loopDataInputRef", "loopDataOutputRef", "inputDataItem", "outputDataItem", "complexBehaviorDefinition", "completionCondition"})
/*     */ public class MultiInstanceLoopCharacteristics extends LoopCharacteristics
/*     */ {
/*     */   protected Expression loopCardinality;
/*     */   protected QName loopDataInputRef;
/*     */   protected QName loopDataOutputRef;
/*     */   protected DataInput inputDataItem;
/*     */   protected DataOutput outputDataItem;
/*     */   protected List<ComplexBehaviorDefinition> complexBehaviorDefinition;
/*     */   protected Expression completionCondition;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected Boolean isSequential;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected MultiInstanceFlowCondition behavior;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName oneBehaviorEventRef;
/*     */ 
/*     */   @XmlAttribute
/*     */   protected QName noneBehaviorEventRef;
/*     */ 
/*     */   public Expression getLoopCardinality()
/*     */   {
/*  89 */     return this.loopCardinality;
/*     */   }
/*     */ 
/*     */   public void setLoopCardinality(Expression value)
/*     */   {
/* 101 */     this.loopCardinality = value;
/*     */   }
/*     */ 
/*     */   public QName getLoopDataInputRef()
/*     */   {
/* 113 */     return this.loopDataInputRef;
/*     */   }
/*     */ 
/*     */   public void setLoopDataInputRef(QName value)
/*     */   {
/* 125 */     this.loopDataInputRef = value;
/*     */   }
/*     */ 
/*     */   public QName getLoopDataOutputRef()
/*     */   {
/* 137 */     return this.loopDataOutputRef;
/*     */   }
/*     */ 
/*     */   public void setLoopDataOutputRef(QName value)
/*     */   {
/* 149 */     this.loopDataOutputRef = value;
/*     */   }
/*     */ 
/*     */   public DataInput getInputDataItem()
/*     */   {
/* 161 */     return this.inputDataItem;
/*     */   }
/*     */ 
/*     */   public void setInputDataItem(DataInput value)
/*     */   {
/* 173 */     this.inputDataItem = value;
/*     */   }
/*     */ 
/*     */   public DataOutput getOutputDataItem()
/*     */   {
/* 185 */     return this.outputDataItem;
/*     */   }
/*     */ 
/*     */   public void setOutputDataItem(DataOutput value)
/*     */   {
/* 197 */     this.outputDataItem = value;
/*     */   }
/*     */ 
/*     */   public List<ComplexBehaviorDefinition> getComplexBehaviorDefinition()
/*     */   {
/* 223 */     if (this.complexBehaviorDefinition == null) {
/* 224 */       this.complexBehaviorDefinition = new ArrayList();
/*     */     }
/* 226 */     return this.complexBehaviorDefinition;
/*     */   }
/*     */ 
/*     */   public Expression getCompletionCondition()
/*     */   {
/* 238 */     return this.completionCondition;
/*     */   }
/*     */ 
/*     */   public void setCompletionCondition(Expression value)
/*     */   {
/* 250 */     this.completionCondition = value;
/*     */   }
/*     */ 
/*     */   public boolean isIsSequential()
/*     */   {
/* 262 */     if (this.isSequential == null) {
/* 263 */       return false;
/*     */     }
/* 265 */     return this.isSequential.booleanValue();
/*     */   }
/*     */ 
/*     */   public void setIsSequential(Boolean value)
/*     */   {
/* 278 */     this.isSequential = value;
/*     */   }
/*     */ 
/*     */   public MultiInstanceFlowCondition getBehavior()
/*     */   {
/* 290 */     if (this.behavior == null) {
/* 291 */       return MultiInstanceFlowCondition.ALL;
/*     */     }
/* 293 */     return this.behavior;
/*     */   }
/*     */ 
/*     */   public void setBehavior(MultiInstanceFlowCondition value)
/*     */   {
/* 306 */     this.behavior = value;
/*     */   }
/*     */ 
/*     */   public QName getOneBehaviorEventRef()
/*     */   {
/* 318 */     return this.oneBehaviorEventRef;
/*     */   }
/*     */ 
/*     */   public void setOneBehaviorEventRef(QName value)
/*     */   {
/* 330 */     this.oneBehaviorEventRef = value;
/*     */   }
/*     */ 
/*     */   public QName getNoneBehaviorEventRef()
/*     */   {
/* 342 */     return this.noneBehaviorEventRef;
/*     */   }
/*     */ 
/*     */   public void setNoneBehaviorEventRef(QName value)
/*     */   {
/* 354 */     this.noneBehaviorEventRef = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.MultiInstanceLoopCharacteristics
 * JD-Core Version:    0.6.2
 */