/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tInputOutputSpecification", propOrder={"dataInput", "dataOutput", "inputSet", "outputSet"})
/*     */ public class InputOutputSpecification extends BaseElement
/*     */ {
/*     */   protected List<DataInput> dataInput;
/*     */   protected List<DataOutput> dataOutput;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<InputSet> inputSet;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<OutputSet> outputSet;
/*     */ 
/*     */   public List<DataInput> getDataInput()
/*     */   {
/*  83 */     if (this.dataInput == null) {
/*  84 */       this.dataInput = new ArrayList();
/*     */     }
/*  86 */     return this.dataInput;
/*     */   }
/*     */ 
/*     */   public List<DataOutput> getDataOutput()
/*     */   {
/* 112 */     if (this.dataOutput == null) {
/* 113 */       this.dataOutput = new ArrayList();
/*     */     }
/* 115 */     return this.dataOutput;
/*     */   }
/*     */ 
/*     */   public List<InputSet> getInputSet()
/*     */   {
/* 141 */     if (this.inputSet == null) {
/* 142 */       this.inputSet = new ArrayList();
/*     */     }
/* 144 */     return this.inputSet;
/*     */   }
/*     */ 
/*     */   public List<OutputSet> getOutputSet()
/*     */   {
/* 170 */     if (this.outputSet == null) {
/* 171 */       this.outputSet = new ArrayList();
/*     */     }
/* 173 */     return this.outputSet;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.InputOutputSpecification
 * JD-Core Version:    0.6.2
 */