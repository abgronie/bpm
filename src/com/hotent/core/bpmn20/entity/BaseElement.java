/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAnyAttribute;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlID;
/*     */ import javax.xml.bind.annotation.XmlSchemaType;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
/*     */ import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tBaseElement", propOrder={"documentation", "extensionElements"})
/*     */ @XmlSeeAlso({Operation.class, CorrelationSubscription.class, ResourceAssignmentExpression.class, Monitoring.class, Participant.class, ParticipantMultiplicity.class, InputSet.class, OutputSet.class, Relationship.class, Assignment.class, MessageFlow.class, InputOutputBinding.class, ResourceParameter.class, Property.class, DataInput.class, ComplexBehaviorDefinition.class, MessageFlowAssociation.class, ConversationLink.class, DataAssociation.class, ParticipantAssociation.class, CategoryValue.class, LoopCharacteristics.class, CorrelationPropertyBinding.class, ResourceRole.class, ConversationNode.class, Lane.class, CorrelationPropertyRetrievalExpression.class, DataState.class, LaneSet.class, ConversationAssociation.class, InputOutputSpecification.class, CorrelationKey.class, ResourceParameterBinding.class, Rendering.class, FlowElement.class, RootElement.class, Auditing.class, Artifact.class, DataOutput.class})
/*     */ public abstract class BaseElement
/*     */ {
/*     */   protected List<Documentation> documentation;
/*     */   protected ExtensionElements extensionElements;
/*     */ 
/*     */   @XmlAttribute
/*     */   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
/*     */   @XmlID
/*     */   @XmlSchemaType(name="ID")
/*     */   protected String id;
/*     */ 
/*     */   @XmlAnyAttribute
/* 105 */   private Map<QName, String> otherAttributes = new HashMap();
/*     */ 
/*     */   public List<Documentation> getDocumentation()
/*     */   {
/* 131 */     if (this.documentation == null) {
/* 132 */       this.documentation = new ArrayList();
/*     */     }
/* 134 */     return this.documentation;
/*     */   }
/*     */ 
/*     */   public ExtensionElements getExtensionElements()
/*     */   {
/* 146 */     return this.extensionElements;
/*     */   }
/*     */ 
/*     */   public void setExtensionElements(ExtensionElements value)
/*     */   {
/* 158 */     this.extensionElements = value;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 170 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String value)
/*     */   {
/* 182 */     this.id = value;
/*     */   }
/*     */ 
/*     */   public Map<QName, String> getOtherAttributes()
/*     */   {
/* 200 */     return this.otherAttributes;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.BaseElement
 * JD-Core Version:    0.6.2
 */