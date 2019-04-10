/*     */ package com.hotent.core.bpmn20.entity.activiti;
/*     */ 
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlElementDecl;
/*     */ import javax.xml.bind.annotation.XmlRegistry;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlRegistry
/*     */ public class ObjectFactory
/*     */ {
/*  34 */   private static final QName _PotentialStarter_QNAME = new QName("http://activiti.org/bpmn", "potentialStarter");
/*     */ 
/*     */   public Out createOut()
/*     */   {
/*  48 */     return new Out();
/*     */   }
/*     */ 
/*     */   public Field createField()
/*     */   {
/*  56 */     return new Field();
/*     */   }
/*     */ 
/*     */   public FormProperty.Value createFormPropertyValue()
/*     */   {
/*  64 */     return new FormProperty.Value();
/*     */   }
/*     */ 
/*     */   public ExecutionListener createExecutionListener()
/*     */   {
/*  72 */     return new ExecutionListener();
/*     */   }
/*     */ 
/*     */   public FormProperty createFormProperty()
/*     */   {
/*  80 */     return new FormProperty();
/*     */   }
/*     */ 
/*     */   public PotentialStarter createPotentialStarter()
/*     */   {
/*  88 */     return new PotentialStarter();
/*     */   }
/*     */ 
/*     */   public In createIn()
/*     */   {
/*  96 */     return new In();
/*     */   }
/*     */ 
/*     */   public TaskListener createTaskListener()
/*     */   {
/* 104 */     return new TaskListener();
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://activiti.org/bpmn", name="potentialStarter")
/*     */   public JAXBElement<String> createPotentialStarter(String value)
/*     */   {
/* 113 */     return new JAXBElement(_PotentialStarter_QNAME, String.class, null, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.activiti.ObjectFactory
 * JD-Core Version:    0.6.2
 */