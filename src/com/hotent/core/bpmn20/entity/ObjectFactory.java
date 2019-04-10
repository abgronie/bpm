/*      */ package com.hotent.core.bpmn20.entity;
/*      */ 
/*      */ import javax.xml.bind.JAXBElement;
/*      */ import javax.xml.bind.annotation.XmlElementDecl;
/*      */ import javax.xml.bind.annotation.XmlIDREF;
/*      */ import javax.xml.bind.annotation.XmlRegistry;
/*      */ import javax.xml.namespace.QName;
/*      */ 
/*      */ @XmlRegistry
/*      */ public class ObjectFactory
/*      */ {
/*   35 */   private static final QName _ExtensionElements_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "extensionElements");
/*   36 */   private static final QName _HumanPerformer_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "humanPerformer");
/*   37 */   private static final QName _Collaboration_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "collaboration");
/*   38 */   private static final QName _ParticipantMultiplicity_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "participantMultiplicity");
/*   39 */   private static final QName _ScriptTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "scriptTask");
/*   40 */   private static final QName _SequenceFlow_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "sequenceFlow");
/*   41 */   private static final QName _GlobalBusinessRuleTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalBusinessRuleTask");
/*   42 */   private static final QName _DataAssociation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataAssociation");
/*   43 */   private static final QName _InputSet_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "inputSet");
/*   44 */   private static final QName _DataInputAssociation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataInputAssociation");
/*   45 */   private static final QName _IntermediateThrowEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "intermediateThrowEvent");
/*   46 */   private static final QName _ErrorEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "errorEventDefinition");
/*   47 */   private static final QName _ReceiveTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "receiveTask");
/*   48 */   private static final QName _Conversation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "conversation");
/*   49 */   private static final QName _ImplicitThrowEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "implicitThrowEvent");
/*   50 */   private static final QName _InclusiveGateway_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "inclusiveGateway");
/*   51 */   private static final QName _OutputSet_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "outputSet");
/*   52 */   private static final QName _IntermediateCatchEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "intermediateCatchEvent");
/*   53 */   private static final QName _LoopCharacteristics_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "loopCharacteristics");
/*   54 */   private static final QName _Relationship_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "relationship");
/*   55 */   private static final QName _Process_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "process");
/*   56 */   private static final QName _Extension_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "extension");
/*   57 */   private static final QName _ConditionalEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "conditionalEventDefinition");
/*   58 */   private static final QName _Assignment_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "assignment");
/*   59 */   private static final QName _Artifact_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "artifact");
/*   60 */   private static final QName _FlowNode_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "flowNode");
/*   61 */   private static final QName _EndPoint_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "endPoint");
/*   62 */   private static final QName _MessageFlow_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "messageFlow");
/*   63 */   private static final QName _SubProcess_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "subProcess");
/*   64 */   private static final QName _EndEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "endEvent");
/*   65 */   private static final QName _Documentation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "documentation");
/*   66 */   private static final QName _BaseElement_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "baseElement");
/*   67 */   private static final QName _TerminateEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "terminateEventDefinition");
/*   68 */   private static final QName _EventBasedGateway_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "eventBasedGateway");
/*   69 */   private static final QName _GlobalScriptTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalScriptTask");
/*   70 */   private static final QName _TimerEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "timerEventDefinition");
/*   71 */   private static final QName _ComplexGateway_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "complexGateway");
/*   72 */   private static final QName _ManualTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "manualTask");
/*   73 */   private static final QName _CallableElement_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "callableElement");
/*   74 */   private static final QName _CancelEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "cancelEventDefinition");
/*   75 */   private static final QName _ServiceTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "serviceTask");
/*   76 */   private static final QName _Operation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "operation");
/*   77 */   private static final QName _SubChoreography_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "subChoreography");
/*   78 */   private static final QName _CorrelationSubscription_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "correlationSubscription");
/*   79 */   private static final QName _ChoreographyActivity_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "choreographyActivity");
/*   80 */   private static final QName _Event_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "event");
/*   81 */   private static final QName _GlobalConversation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalConversation");
/*   82 */   private static final QName _Import_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "import");
/*   83 */   private static final QName _ResourceAssignmentExpression_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "resourceAssignmentExpression");
/*   84 */   private static final QName _EventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "eventDefinition");
/*   85 */   private static final QName _Monitoring_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "monitoring");
/*   86 */   private static final QName _ThrowEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "throwEvent");
/*   87 */   private static final QName _ItemDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "itemDefinition");
/*   88 */   private static final QName _AdHocSubProcess_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "adHocSubProcess");
/*   89 */   private static final QName _GlobalUserTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalUserTask");
/*   90 */   private static final QName _Category_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "category");
/*   91 */   private static final QName _StartEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "startEvent");
/*   92 */   private static final QName _Participant_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "participant");
/*   93 */   private static final QName _Performer_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "performer");
/*   94 */   private static final QName _FormalExpression_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "formalExpression");
/*   95 */   private static final QName _MessageEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "messageEventDefinition");
/*   96 */   private static final QName _CatchEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "catchEvent");
/*   97 */   private static final QName _DataOutputAssociation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataOutputAssociation");
/*   98 */   private static final QName _DataObjectReference_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataObjectReference");
/*   99 */   private static final QName _BoundaryEvent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "boundaryEvent");
/*  100 */   private static final QName _ParticipantAssociation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "participantAssociation");
/*  101 */   private static final QName _SendTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "sendTask");
/*  102 */   private static final QName _CategoryValue_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "categoryValue");
/*  103 */   private static final QName _Choreography_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "choreography");
/*  104 */   private static final QName _GlobalChoreographyTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalChoreographyTask");
/*  105 */   private static final QName _CallChoreography_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "callChoreography");
/*  106 */   private static final QName _MultiInstanceLoopCharacteristics_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "multiInstanceLoopCharacteristics");
/*  107 */   private static final QName _PotentialOwner_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "potentialOwner");
/*  108 */   private static final QName _CorrelationPropertyBinding_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "correlationPropertyBinding");
/*  109 */   private static final QName _Signal_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "signal");
/*  110 */   private static final QName _UserTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "userTask");
/*  111 */   private static final QName _ParallelGateway_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "parallelGateway");
/*  112 */   private static final QName _Lane_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "lane");
/*  113 */   private static final QName _SubConversation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "subConversation");
/*  114 */   private static final QName _BaseElementWithMixedContent_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "baseElementWithMixedContent");
/*  115 */   private static final QName _DataStore_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataStore");
/*  116 */   private static final QName _SignalEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "signalEventDefinition");
/*  117 */   private static final QName _RootElement_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "rootElement");
/*  118 */   private static final QName _DataState_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataState");
/*  119 */   private static final QName _CorrelationPropertyRetrievalExpression_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "correlationPropertyRetrievalExpression");
/*  120 */   private static final QName _IoSpecification_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "ioSpecification");
/*  121 */   private static final QName _ConversationAssociation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "conversationAssociation");
/*  122 */   private static final QName _LaneSet_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "laneSet");
/*  123 */   private static final QName _Activity_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "activity");
/*  124 */   private static final QName _GlobalTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalTask");
/*  125 */   private static final QName _Error_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "error");
/*  126 */   private static final QName _Task_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "task");
/*  127 */   private static final QName _Resource_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "resource");
/*  128 */   private static final QName _Interface_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "interface");
/*  129 */   private static final QName _CorrelationKey_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "correlationKey");
/*  130 */   private static final QName _Rendering_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "rendering");
/*  131 */   private static final QName _ResourceParameterBinding_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "resourceParameterBinding");
/*  132 */   private static final QName _ExclusiveGateway_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "exclusiveGateway");
/*  133 */   private static final QName _CorrelationProperty_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "correlationProperty");
/*  134 */   private static final QName _Message_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "message");
/*  135 */   private static final QName _DataStoreReference_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataStoreReference");
/*  136 */   private static final QName _GlobalManualTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "globalManualTask");
/*  137 */   private static final QName _Escalation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "escalation");
/*  138 */   private static final QName _CallActivity_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "callActivity");
/*  139 */   private static final QName _TextAnnotation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "textAnnotation");
/*  140 */   private static final QName _Group_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "group");
/*  141 */   private static final QName _Auditing_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "auditing");
/*  142 */   private static final QName _DataOutput_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataOutput");
/*  143 */   private static final QName _Expression_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "expression");
/*  144 */   private static final QName _Transaction_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "transaction");
/*  145 */   private static final QName _ChoreographyTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "choreographyTask");
/*  146 */   private static final QName _Gateway_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "gateway");
/*  147 */   private static final QName _ResourceRole_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "resourceRole");
/*  148 */   private static final QName _PartnerEntity_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "partnerEntity");
/*  149 */   private static final QName _PartnerRole_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "partnerRole");
/*  150 */   private static final QName _BusinessRuleTask_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "businessRuleTask");
/*  151 */   private static final QName _IoBinding_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "ioBinding");
/*  152 */   private static final QName _FlowElement_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "flowElement");
/*  153 */   private static final QName _DataObject_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataObject");
/*  154 */   private static final QName _LinkEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "linkEventDefinition");
/*  155 */   private static final QName _ResourceParameter_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "resourceParameter");
/*  156 */   private static final QName _Text_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "text");
/*  157 */   private static final QName _Association_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "association");
/*  158 */   private static final QName _CallConversation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "callConversation");
/*  159 */   private static final QName _EscalationEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "escalationEventDefinition");
/*  160 */   private static final QName _Definitions_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "definitions");
/*  161 */   private static final QName _Property_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "property");
/*  162 */   private static final QName _Script_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "script");
/*  163 */   private static final QName _DataInput_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataInput");
/*  164 */   private static final QName _ConversationNode_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "conversationNode");
/*  165 */   private static final QName _CompensateEventDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "compensateEventDefinition");
/*  166 */   private static final QName _ComplexBehaviorDefinition_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "complexBehaviorDefinition");
/*  167 */   private static final QName _ConversationLink_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "conversationLink");
/*  168 */   private static final QName _MessageFlowAssociation_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "messageFlowAssociation");
/*  169 */   private static final QName _StandardLoopCharacteristics_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "standardLoopCharacteristics");
/*  170 */   private static final QName _DataAssociationSourceRef_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "sourceRef");
/*  171 */   private static final QName _OutputSetDataOutputRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataOutputRefs");
/*  172 */   private static final QName _OutputSetOptionalOutputRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "optionalOutputRefs");
/*  173 */   private static final QName _OutputSetWhileExecutingOutputRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "whileExecutingOutputRefs");
/*  174 */   private static final QName _OutputSetInputSetRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "inputSetRefs");
/*  175 */   private static final QName _InputSetWhileExecutingInputRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "whileExecutingInputRefs");
/*  176 */   private static final QName _InputSetOutputSetRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "outputSetRefs");
/*  177 */   private static final QName _InputSetOptionalInputRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "optionalInputRefs");
/*  178 */   private static final QName _InputSetDataInputRefs_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "dataInputRefs");
/*  179 */   private static final QName _LaneFlowNodeRef_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/MODEL", "flowNodeRef");
/*      */ 
/*      */   public DataObjectReference createDataObjectReference()
/*      */   {
/*  193 */     return new DataObjectReference();
/*      */   }
/*      */ 
/*      */   public SubProcess createSubProcess()
/*      */   {
/*  201 */     return new SubProcess();
/*      */   }
/*      */ 
/*      */   public Signal createSignal()
/*      */   {
/*  209 */     return new Signal();
/*      */   }
/*      */ 
/*      */   public MessageEventDefinition createMessageEventDefinition()
/*      */   {
/*  217 */     return new MessageEventDefinition();
/*      */   }
/*      */ 
/*      */   public SubChoreography createSubChoreography()
/*      */   {
/*  225 */     return new SubChoreography();
/*      */   }
/*      */ 
/*      */   public ResourceParameter createResourceParameter()
/*      */   {
/*  233 */     return new ResourceParameter();
/*      */   }
/*      */ 
/*      */   public DataObject createDataObject()
/*      */   {
/*  241 */     return new DataObject();
/*      */   }
/*      */ 
/*      */   public DataAssociation createDataAssociation()
/*      */   {
/*  249 */     return new DataAssociation();
/*      */   }
/*      */ 
/*      */   public DataInput createDataInput()
/*      */   {
/*  257 */     return new DataInput();
/*      */   }
/*      */ 
/*      */   public ResourceAssignmentExpression createResourceAssignmentExpression()
/*      */   {
/*  265 */     return new ResourceAssignmentExpression();
/*      */   }
/*      */ 
/*      */   public ManualTask createManualTask()
/*      */   {
/*  273 */     return new ManualTask();
/*      */   }
/*      */ 
/*      */   public CorrelationPropertyRetrievalExpression createCorrelationPropertyRetrievalExpression()
/*      */   {
/*  281 */     return new CorrelationPropertyRetrievalExpression();
/*      */   }
/*      */ 
/*      */   public Documentation createDocumentation()
/*      */   {
/*  289 */     return new Documentation();
/*      */   }
/*      */ 
/*      */   public DataOutput createDataOutput()
/*      */   {
/*  297 */     return new DataOutput();
/*      */   }
/*      */ 
/*      */   public Conversation createConversation()
/*      */   {
/*  305 */     return new Conversation();
/*      */   }
/*      */ 
/*      */   public CallChoreography createCallChoreography()
/*      */   {
/*  313 */     return new CallChoreography();
/*      */   }
/*      */ 
/*      */   public Assignment createAssignment()
/*      */   {
/*  321 */     return new Assignment();
/*      */   }
/*      */ 
/*      */   public GlobalScriptTask createGlobalScriptTask()
/*      */   {
/*  329 */     return new GlobalScriptTask();
/*      */   }
/*      */ 
/*      */   public SignalEventDefinition createSignalEventDefinition()
/*      */   {
/*  337 */     return new SignalEventDefinition();
/*      */   }
/*      */ 
/*      */   public GlobalManualTask createGlobalManualTask()
/*      */   {
/*  345 */     return new GlobalManualTask();
/*      */   }
/*      */ 
/*      */   public EndPoint createEndPoint()
/*      */   {
/*  353 */     return new EndPoint();
/*      */   }
/*      */ 
/*      */   public MultiInstanceLoopCharacteristics createMultiInstanceLoopCharacteristics()
/*      */   {
/*  361 */     return new MultiInstanceLoopCharacteristics();
/*      */   }
/*      */ 
/*      */   public CategoryValue createCategoryValue()
/*      */   {
/*  369 */     return new CategoryValue();
/*      */   }
/*      */ 
/*      */   public Transaction createTransaction()
/*      */   {
/*  377 */     return new Transaction();
/*      */   }
/*      */ 
/*      */   public ResourceRole createResourceRole()
/*      */   {
/*  385 */     return new ResourceRole();
/*      */   }
/*      */ 
/*      */   public ConditionalEventDefinition createConditionalEventDefinition()
/*      */   {
/*  393 */     return new ConditionalEventDefinition();
/*      */   }
/*      */ 
/*      */   public ChoreographyTask createChoreographyTask()
/*      */   {
/*  401 */     return new ChoreographyTask();
/*      */   }
/*      */ 
/*      */   public ExclusiveGateway createExclusiveGateway()
/*      */   {
/*  409 */     return new ExclusiveGateway();
/*      */   }
/*      */ 
/*      */   public Choreography createChoreography()
/*      */   {
/*  417 */     return new Choreography();
/*      */   }
/*      */ 
/*      */   public Resource createResource()
/*      */   {
/*  425 */     return new Resource();
/*      */   }
/*      */ 
/*      */   public Group createGroup()
/*      */   {
/*  433 */     return new Group();
/*      */   }
/*      */ 
/*      */   public Monitoring createMonitoring()
/*      */   {
/*  441 */     return new Monitoring();
/*      */   }
/*      */ 
/*      */   public CorrelationProperty createCorrelationProperty()
/*      */   {
/*  449 */     return new CorrelationProperty();
/*      */   }
/*      */ 
/*      */   public DataStore createDataStore()
/*      */   {
/*  457 */     return new DataStore();
/*      */   }
/*      */ 
/*      */   public ItemDefinition createItemDefinition()
/*      */   {
/*  465 */     return new ItemDefinition();
/*      */   }
/*      */ 
/*      */   public Error createError()
/*      */   {
/*  473 */     return new Error();
/*      */   }
/*      */ 
/*      */   public ConversationLink createConversationLink()
/*      */   {
/*  481 */     return new ConversationLink();
/*      */   }
/*      */ 
/*      */   public CancelEventDefinition createCancelEventDefinition()
/*      */   {
/*  489 */     return new CancelEventDefinition();
/*      */   }
/*      */ 
/*      */   public PartnerRole createPartnerRole()
/*      */   {
/*  497 */     return new PartnerRole();
/*      */   }
/*      */ 
/*      */   public Association createAssociation()
/*      */   {
/*  505 */     return new Association();
/*      */   }
/*      */ 
/*      */   public MessageFlowAssociation createMessageFlowAssociation()
/*      */   {
/*  513 */     return new MessageFlowAssociation();
/*      */   }
/*      */ 
/*      */   public GlobalChoreographyTask createGlobalChoreographyTask()
/*      */   {
/*  521 */     return new GlobalChoreographyTask();
/*      */   }
/*      */ 
/*      */   public MessageFlow createMessageFlow()
/*      */   {
/*  529 */     return new MessageFlow();
/*      */   }
/*      */ 
/*      */   public LaneSet createLaneSet()
/*      */   {
/*  537 */     return new LaneSet();
/*      */   }
/*      */ 
/*      */   public PartnerEntity createPartnerEntity()
/*      */   {
/*  545 */     return new PartnerEntity();
/*      */   }
/*      */ 
/*      */   public Lane createLane()
/*      */   {
/*  553 */     return new Lane();
/*      */   }
/*      */ 
/*      */   public ErrorEventDefinition createErrorEventDefinition()
/*      */   {
/*  561 */     return new ErrorEventDefinition();
/*      */   }
/*      */ 
/*      */   public TimerEventDefinition createTimerEventDefinition()
/*      */   {
/*  569 */     return new TimerEventDefinition();
/*      */   }
/*      */ 
/*      */   public BoundaryEvent createBoundaryEvent()
/*      */   {
/*  577 */     return new BoundaryEvent();
/*      */   }
/*      */ 
/*      */   public Category createCategory()
/*      */   {
/*  585 */     return new Category();
/*      */   }
/*      */ 
/*      */   public Definitions createDefinitions()
/*      */   {
/*  593 */     return new Definitions();
/*      */   }
/*      */ 
/*      */   public ParallelGateway createParallelGateway()
/*      */   {
/*  601 */     return new ParallelGateway();
/*      */   }
/*      */ 
/*      */   public DataOutputAssociation createDataOutputAssociation()
/*      */   {
/*  609 */     return new DataOutputAssociation();
/*      */   }
/*      */ 
/*      */   public DataState createDataState()
/*      */   {
/*  617 */     return new DataState();
/*      */   }
/*      */ 
/*      */   public SendTask createSendTask()
/*      */   {
/*  625 */     return new SendTask();
/*      */   }
/*      */ 
/*      */   public TextAnnotation createTextAnnotation()
/*      */   {
/*  633 */     return new TextAnnotation();
/*      */   }
/*      */ 
/*      */   public GlobalBusinessRuleTask createGlobalBusinessRuleTask()
/*      */   {
/*  641 */     return new GlobalBusinessRuleTask();
/*      */   }
/*      */ 
/*      */   public ComplexGateway createComplexGateway()
/*      */   {
/*  649 */     return new ComplexGateway();
/*      */   }
/*      */ 
/*      */   public ReceiveTask createReceiveTask()
/*      */   {
/*  657 */     return new ReceiveTask();
/*      */   }
/*      */ 
/*      */   public ExtensionElements createExtensionElements()
/*      */   {
/*  665 */     return new ExtensionElements();
/*      */   }
/*      */ 
/*      */   public Task createTask()
/*      */   {
/*  673 */     return new Task();
/*      */   }
/*      */ 
/*      */   public CorrelationPropertyBinding createCorrelationPropertyBinding()
/*      */   {
/*  681 */     return new CorrelationPropertyBinding();
/*      */   }
/*      */ 
/*      */   public CompensateEventDefinition createCompensateEventDefinition()
/*      */   {
/*  689 */     return new CompensateEventDefinition();
/*      */   }
/*      */ 
/*      */   public Interface createInterface()
/*      */   {
/*  697 */     return new Interface();
/*      */   }
/*      */ 
/*      */   public EndEvent createEndEvent()
/*      */   {
/*  705 */     return new EndEvent();
/*      */   }
/*      */ 
/*      */   public DataInputAssociation createDataInputAssociation()
/*      */   {
/*  713 */     return new DataInputAssociation();
/*      */   }
/*      */ 
/*      */   public Escalation createEscalation()
/*      */   {
/*  721 */     return new Escalation();
/*      */   }
/*      */ 
/*      */   public AdHocSubProcess createAdHocSubProcess()
/*      */   {
/*  729 */     return new AdHocSubProcess();
/*      */   }
/*      */ 
/*      */   public PotentialOwner createPotentialOwner()
/*      */   {
/*  737 */     return new PotentialOwner();
/*      */   }
/*      */ 
/*      */   public Property createProperty()
/*      */   {
/*  745 */     return new Property();
/*      */   }
/*      */ 
/*      */   public ParticipantMultiplicity createParticipantMultiplicity()
/*      */   {
/*  753 */     return new ParticipantMultiplicity();
/*      */   }
/*      */ 
/*      */   public OutputSet createOutputSet()
/*      */   {
/*  761 */     return new OutputSet();
/*      */   }
/*      */ 
/*      */   public InclusiveGateway createInclusiveGateway()
/*      */   {
/*  769 */     return new InclusiveGateway();
/*      */   }
/*      */ 
/*      */   public FormalExpression createFormalExpression()
/*      */   {
/*  777 */     return new FormalExpression();
/*      */   }
/*      */ 
/*      */   public Extension createExtension()
/*      */   {
/*  785 */     return new Extension();
/*      */   }
/*      */ 
/*      */   public SequenceFlow createSequenceFlow()
/*      */   {
/*  793 */     return new SequenceFlow();
/*      */   }
/*      */ 
/*      */   public Auditing createAuditing()
/*      */   {
/*  801 */     return new Auditing();
/*      */   }
/*      */ 
/*      */   public Expression createExpression()
/*      */   {
/*  809 */     return new Expression();
/*      */   }
/*      */ 
/*      */   public Gateway createGateway()
/*      */   {
/*  817 */     return new Gateway();
/*      */   }
/*      */ 
/*      */   public ServiceTask createServiceTask()
/*      */   {
/*  825 */     return new ServiceTask();
/*      */   }
/*      */ 
/*      */   public ConversationAssociation createConversationAssociation()
/*      */   {
/*  833 */     return new ConversationAssociation();
/*      */   }
/*      */ 
/*      */   public LinkEventDefinition createLinkEventDefinition()
/*      */   {
/*  841 */     return new LinkEventDefinition();
/*      */   }
/*      */ 
/*      */   public EscalationEventDefinition createEscalationEventDefinition()
/*      */   {
/*  849 */     return new EscalationEventDefinition();
/*      */   }
/*      */ 
/*      */   public IntermediateThrowEvent createIntermediateThrowEvent()
/*      */   {
/*  857 */     return new IntermediateThrowEvent();
/*      */   }
/*      */ 
/*      */   public IntermediateCatchEvent createIntermediateCatchEvent()
/*      */   {
/*  865 */     return new IntermediateCatchEvent();
/*      */   }
/*      */ 
/*      */   public UserTask createUserTask()
/*      */   {
/*  873 */     return new UserTask();
/*      */   }
/*      */ 
/*      */   public GlobalUserTask createGlobalUserTask()
/*      */   {
/*  881 */     return new GlobalUserTask();
/*      */   }
/*      */ 
/*      */   public StartEvent createStartEvent()
/*      */   {
/*  889 */     return new StartEvent();
/*      */   }
/*      */ 
/*      */   public CallActivity createCallActivity()
/*      */   {
/*  897 */     return new CallActivity();
/*      */   }
/*      */ 
/*      */   public HumanPerformer createHumanPerformer()
/*      */   {
/*  905 */     return new HumanPerformer();
/*      */   }
/*      */ 
/*      */   public ResourceParameterBinding createResourceParameterBinding()
/*      */   {
/*  913 */     return new ResourceParameterBinding();
/*      */   }
/*      */ 
/*      */   public EventBasedGateway createEventBasedGateway()
/*      */   {
/*  921 */     return new EventBasedGateway();
/*      */   }
/*      */ 
/*      */   public CorrelationSubscription createCorrelationSubscription()
/*      */   {
/*  929 */     return new CorrelationSubscription();
/*      */   }
/*      */ 
/*      */   public Collaboration createCollaboration()
/*      */   {
/*  937 */     return new Collaboration();
/*      */   }
/*      */ 
/*      */   public Message createMessage()
/*      */   {
/*  945 */     return new Message();
/*      */   }
/*      */ 
/*      */   public GlobalTask createGlobalTask()
/*      */   {
/*  953 */     return new GlobalTask();
/*      */   }
/*      */ 
/*      */   public CorrelationKey createCorrelationKey()
/*      */   {
/*  961 */     return new CorrelationKey();
/*      */   }
/*      */ 
/*      */   public CallableElement createCallableElement()
/*      */   {
/*  969 */     return new CallableElement();
/*      */   }
/*      */ 
/*      */   public CallConversation createCallConversation()
/*      */   {
/*  977 */     return new CallConversation();
/*      */   }
/*      */ 
/*      */   public ImplicitThrowEvent createImplicitThrowEvent()
/*      */   {
/*  985 */     return new ImplicitThrowEvent();
/*      */   }
/*      */ 
/*      */   public ComplexBehaviorDefinition createComplexBehaviorDefinition()
/*      */   {
/*  993 */     return new ComplexBehaviorDefinition();
/*      */   }
/*      */ 
/*      */   public SubConversation createSubConversation()
/*      */   {
/* 1001 */     return new SubConversation();
/*      */   }
/*      */ 
/*      */   public StandardLoopCharacteristics createStandardLoopCharacteristics()
/*      */   {
/* 1009 */     return new StandardLoopCharacteristics();
/*      */   }
/*      */ 
/*      */   public Participant createParticipant()
/*      */   {
/* 1017 */     return new Participant();
/*      */   }
/*      */ 
/*      */   public Relationship createRelationship()
/*      */   {
/* 1025 */     return new Relationship();
/*      */   }
/*      */ 
/*      */   public Script createScript()
/*      */   {
/* 1033 */     return new Script();
/*      */   }
/*      */ 
/*      */   public Process createProcess()
/*      */   {
/* 1041 */     return new Process();
/*      */   }
/*      */ 
/*      */   public GlobalConversation createGlobalConversation()
/*      */   {
/* 1049 */     return new GlobalConversation();
/*      */   }
/*      */ 
/*      */   public Import createImport()
/*      */   {
/* 1057 */     return new Import();
/*      */   }
/*      */ 
/*      */   public InputSet createInputSet()
/*      */   {
/* 1065 */     return new InputSet();
/*      */   }
/*      */ 
/*      */   public Text createText()
/*      */   {
/* 1073 */     return new Text();
/*      */   }
/*      */ 
/*      */   public Rendering createRendering()
/*      */   {
/* 1081 */     return new Rendering();
/*      */   }
/*      */ 
/*      */   public ScriptTask createScriptTask()
/*      */   {
/* 1089 */     return new ScriptTask();
/*      */   }
/*      */ 
/*      */   public BusinessRuleTask createBusinessRuleTask()
/*      */   {
/* 1097 */     return new BusinessRuleTask();
/*      */   }
/*      */ 
/*      */   public ParticipantAssociation createParticipantAssociation()
/*      */   {
/* 1105 */     return new ParticipantAssociation();
/*      */   }
/*      */ 
/*      */   public TerminateEventDefinition createTerminateEventDefinition()
/*      */   {
/* 1113 */     return new TerminateEventDefinition();
/*      */   }
/*      */ 
/*      */   public InputOutputSpecification createInputOutputSpecification()
/*      */   {
/* 1121 */     return new InputOutputSpecification();
/*      */   }
/*      */ 
/*      */   public Performer createPerformer()
/*      */   {
/* 1129 */     return new Performer();
/*      */   }
/*      */ 
/*      */   public DataStoreReference createDataStoreReference()
/*      */   {
/* 1137 */     return new DataStoreReference();
/*      */   }
/*      */ 
/*      */   public Operation createOperation()
/*      */   {
/* 1145 */     return new Operation();
/*      */   }
/*      */ 
/*      */   public InputOutputBinding createInputOutputBinding()
/*      */   {
/* 1153 */     return new InputOutputBinding();
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="extensionElements")
/*      */   public JAXBElement<ExtensionElements> createExtensionElements(ExtensionElements value)
/*      */   {
/* 1162 */     return new JAXBElement(_ExtensionElements_QNAME, ExtensionElements.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="humanPerformer", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="performer")
/*      */   public JAXBElement<HumanPerformer> createHumanPerformer(HumanPerformer value)
/*      */   {
/* 1171 */     return new JAXBElement(_HumanPerformer_QNAME, HumanPerformer.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="collaboration", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Collaboration> createCollaboration(Collaboration value)
/*      */   {
/* 1180 */     return new JAXBElement(_Collaboration_QNAME, Collaboration.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="participantMultiplicity")
/*      */   public JAXBElement<ParticipantMultiplicity> createParticipantMultiplicity(ParticipantMultiplicity value)
/*      */   {
/* 1189 */     return new JAXBElement(_ParticipantMultiplicity_QNAME, ParticipantMultiplicity.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="scriptTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ScriptTask> createScriptTask(ScriptTask value)
/*      */   {
/* 1198 */     return new JAXBElement(_ScriptTask_QNAME, ScriptTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="sequenceFlow", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<SequenceFlow> createSequenceFlow(SequenceFlow value)
/*      */   {
/* 1207 */     return new JAXBElement(_SequenceFlow_QNAME, SequenceFlow.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalBusinessRuleTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<GlobalBusinessRuleTask> createGlobalBusinessRuleTask(GlobalBusinessRuleTask value)
/*      */   {
/* 1216 */     return new JAXBElement(_GlobalBusinessRuleTask_QNAME, GlobalBusinessRuleTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataAssociation")
/*      */   public JAXBElement<DataAssociation> createDataAssociation(DataAssociation value)
/*      */   {
/* 1225 */     return new JAXBElement(_DataAssociation_QNAME, DataAssociation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="inputSet")
/*      */   public JAXBElement<InputSet> createInputSet(InputSet value)
/*      */   {
/* 1234 */     return new JAXBElement(_InputSet_QNAME, InputSet.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataInputAssociation")
/*      */   public JAXBElement<DataInputAssociation> createDataInputAssociation(DataInputAssociation value)
/*      */   {
/* 1243 */     return new JAXBElement(_DataInputAssociation_QNAME, DataInputAssociation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="intermediateThrowEvent", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<IntermediateThrowEvent> createIntermediateThrowEvent(IntermediateThrowEvent value)
/*      */   {
/* 1252 */     return new JAXBElement(_IntermediateThrowEvent_QNAME, IntermediateThrowEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="errorEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<ErrorEventDefinition> createErrorEventDefinition(ErrorEventDefinition value)
/*      */   {
/* 1261 */     return new JAXBElement(_ErrorEventDefinition_QNAME, ErrorEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="receiveTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ReceiveTask> createReceiveTask(ReceiveTask value)
/*      */   {
/* 1270 */     return new JAXBElement(_ReceiveTask_QNAME, ReceiveTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="conversation", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="conversationNode")
/*      */   public JAXBElement<Conversation> createConversation(Conversation value)
/*      */   {
/* 1279 */     return new JAXBElement(_Conversation_QNAME, Conversation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="implicitThrowEvent", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ImplicitThrowEvent> createImplicitThrowEvent(ImplicitThrowEvent value)
/*      */   {
/* 1288 */     return new JAXBElement(_ImplicitThrowEvent_QNAME, ImplicitThrowEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="inclusiveGateway", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<InclusiveGateway> createInclusiveGateway(InclusiveGateway value)
/*      */   {
/* 1297 */     return new JAXBElement(_InclusiveGateway_QNAME, InclusiveGateway.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="outputSet")
/*      */   public JAXBElement<OutputSet> createOutputSet(OutputSet value)
/*      */   {
/* 1306 */     return new JAXBElement(_OutputSet_QNAME, OutputSet.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="intermediateCatchEvent", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<IntermediateCatchEvent> createIntermediateCatchEvent(IntermediateCatchEvent value)
/*      */   {
/* 1315 */     return new JAXBElement(_IntermediateCatchEvent_QNAME, IntermediateCatchEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="loopCharacteristics")
/*      */   public JAXBElement<LoopCharacteristics> createLoopCharacteristics(LoopCharacteristics value)
/*      */   {
/* 1324 */     return new JAXBElement(_LoopCharacteristics_QNAME, LoopCharacteristics.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="relationship")
/*      */   public JAXBElement<Relationship> createRelationship(Relationship value)
/*      */   {
/* 1333 */     return new JAXBElement(_Relationship_QNAME, Relationship.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="process", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Process> createProcess(Process value)
/*      */   {
/* 1342 */     return new JAXBElement(_Process_QNAME, Process.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="extension")
/*      */   public JAXBElement<Extension> createExtension(Extension value)
/*      */   {
/* 1351 */     return new JAXBElement(_Extension_QNAME, Extension.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="conditionalEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<ConditionalEventDefinition> createConditionalEventDefinition(ConditionalEventDefinition value)
/*      */   {
/* 1360 */     return new JAXBElement(_ConditionalEventDefinition_QNAME, ConditionalEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="assignment")
/*      */   public JAXBElement<Assignment> createAssignment(Assignment value)
/*      */   {
/* 1369 */     return new JAXBElement(_Assignment_QNAME, Assignment.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="artifact")
/*      */   public JAXBElement<Artifact> createArtifact(Artifact value)
/*      */   {
/* 1378 */     return new JAXBElement(_Artifact_QNAME, Artifact.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="flowNode")
/*      */   public JAXBElement<FlowNode> createFlowNode(FlowNode value)
/*      */   {
/* 1387 */     return new JAXBElement(_FlowNode_QNAME, FlowNode.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="endPoint", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<EndPoint> createEndPoint(EndPoint value)
/*      */   {
/* 1396 */     return new JAXBElement(_EndPoint_QNAME, EndPoint.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="messageFlow")
/*      */   public JAXBElement<MessageFlow> createMessageFlow(MessageFlow value)
/*      */   {
/* 1405 */     return new JAXBElement(_MessageFlow_QNAME, MessageFlow.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="subProcess", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<SubProcess> createSubProcess(SubProcess value)
/*      */   {
/* 1414 */     return new JAXBElement(_SubProcess_QNAME, SubProcess.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="endEvent", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<EndEvent> createEndEvent(EndEvent value)
/*      */   {
/* 1423 */     return new JAXBElement(_EndEvent_QNAME, EndEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="documentation")
/*      */   public JAXBElement<Documentation> createDocumentation(Documentation value)
/*      */   {
/* 1432 */     return new JAXBElement(_Documentation_QNAME, Documentation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="baseElement")
/*      */   public JAXBElement<BaseElement> createBaseElement(BaseElement value)
/*      */   {
/* 1441 */     return new JAXBElement(_BaseElement_QNAME, BaseElement.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="terminateEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<TerminateEventDefinition> createTerminateEventDefinition(TerminateEventDefinition value)
/*      */   {
/* 1450 */     return new JAXBElement(_TerminateEventDefinition_QNAME, TerminateEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="eventBasedGateway", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<EventBasedGateway> createEventBasedGateway(EventBasedGateway value)
/*      */   {
/* 1459 */     return new JAXBElement(_EventBasedGateway_QNAME, EventBasedGateway.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalScriptTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<GlobalScriptTask> createGlobalScriptTask(GlobalScriptTask value)
/*      */   {
/* 1468 */     return new JAXBElement(_GlobalScriptTask_QNAME, GlobalScriptTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="timerEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<TimerEventDefinition> createTimerEventDefinition(TimerEventDefinition value)
/*      */   {
/* 1477 */     return new JAXBElement(_TimerEventDefinition_QNAME, TimerEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="complexGateway", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ComplexGateway> createComplexGateway(ComplexGateway value)
/*      */   {
/* 1486 */     return new JAXBElement(_ComplexGateway_QNAME, ComplexGateway.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="manualTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ManualTask> createManualTask(ManualTask value)
/*      */   {
/* 1495 */     return new JAXBElement(_ManualTask_QNAME, ManualTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="callableElement")
/*      */   public JAXBElement<CallableElement> createCallableElement(CallableElement value)
/*      */   {
/* 1504 */     return new JAXBElement(_CallableElement_QNAME, CallableElement.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="cancelEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<CancelEventDefinition> createCancelEventDefinition(CancelEventDefinition value)
/*      */   {
/* 1513 */     return new JAXBElement(_CancelEventDefinition_QNAME, CancelEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="serviceTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ServiceTask> createServiceTask(ServiceTask value)
/*      */   {
/* 1522 */     return new JAXBElement(_ServiceTask_QNAME, ServiceTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="operation")
/*      */   public JAXBElement<Operation> createOperation(Operation value)
/*      */   {
/* 1531 */     return new JAXBElement(_Operation_QNAME, Operation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="subChoreography", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<SubChoreography> createSubChoreography(SubChoreography value)
/*      */   {
/* 1540 */     return new JAXBElement(_SubChoreography_QNAME, SubChoreography.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="correlationSubscription")
/*      */   public JAXBElement<CorrelationSubscription> createCorrelationSubscription(CorrelationSubscription value)
/*      */   {
/* 1549 */     return new JAXBElement(_CorrelationSubscription_QNAME, CorrelationSubscription.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="choreographyActivity")
/*      */   public JAXBElement<ChoreographyActivity> createChoreographyActivity(ChoreographyActivity value)
/*      */   {
/* 1558 */     return new JAXBElement(_ChoreographyActivity_QNAME, ChoreographyActivity.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="event", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<Event> createEvent(Event value)
/*      */   {
/* 1567 */     return new JAXBElement(_Event_QNAME, Event.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalConversation", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="collaboration")
/*      */   public JAXBElement<GlobalConversation> createGlobalConversation(GlobalConversation value)
/*      */   {
/* 1576 */     return new JAXBElement(_GlobalConversation_QNAME, GlobalConversation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="import")
/*      */   public JAXBElement<Import> createImport(Import value)
/*      */   {
/* 1585 */     return new JAXBElement(_Import_QNAME, Import.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="resourceAssignmentExpression")
/*      */   public JAXBElement<ResourceAssignmentExpression> createResourceAssignmentExpression(ResourceAssignmentExpression value)
/*      */   {
/* 1594 */     return new JAXBElement(_ResourceAssignmentExpression_QNAME, ResourceAssignmentExpression.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="eventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<EventDefinition> createEventDefinition(EventDefinition value)
/*      */   {
/* 1603 */     return new JAXBElement(_EventDefinition_QNAME, EventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="monitoring")
/*      */   public JAXBElement<Monitoring> createMonitoring(Monitoring value)
/*      */   {
/* 1612 */     return new JAXBElement(_Monitoring_QNAME, Monitoring.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="throwEvent")
/*      */   public JAXBElement<ThrowEvent> createThrowEvent(ThrowEvent value)
/*      */   {
/* 1621 */     return new JAXBElement(_ThrowEvent_QNAME, ThrowEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="itemDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<ItemDefinition> createItemDefinition(ItemDefinition value)
/*      */   {
/* 1630 */     return new JAXBElement(_ItemDefinition_QNAME, ItemDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="adHocSubProcess", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<AdHocSubProcess> createAdHocSubProcess(AdHocSubProcess value)
/*      */   {
/* 1639 */     return new JAXBElement(_AdHocSubProcess_QNAME, AdHocSubProcess.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalUserTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<GlobalUserTask> createGlobalUserTask(GlobalUserTask value)
/*      */   {
/* 1648 */     return new JAXBElement(_GlobalUserTask_QNAME, GlobalUserTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="category", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Category> createCategory(Category value)
/*      */   {
/* 1657 */     return new JAXBElement(_Category_QNAME, Category.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="startEvent", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<StartEvent> createStartEvent(StartEvent value)
/*      */   {
/* 1666 */     return new JAXBElement(_StartEvent_QNAME, StartEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="participant")
/*      */   public JAXBElement<Participant> createParticipant(Participant value)
/*      */   {
/* 1675 */     return new JAXBElement(_Participant_QNAME, Participant.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="performer", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="resourceRole")
/*      */   public JAXBElement<Performer> createPerformer(Performer value)
/*      */   {
/* 1684 */     return new JAXBElement(_Performer_QNAME, Performer.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="formalExpression", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="expression")
/*      */   public JAXBElement<FormalExpression> createFormalExpression(FormalExpression value)
/*      */   {
/* 1693 */     return new JAXBElement(_FormalExpression_QNAME, FormalExpression.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="messageEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<MessageEventDefinition> createMessageEventDefinition(MessageEventDefinition value)
/*      */   {
/* 1702 */     return new JAXBElement(_MessageEventDefinition_QNAME, MessageEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="catchEvent")
/*      */   public JAXBElement<CatchEvent> createCatchEvent(CatchEvent value)
/*      */   {
/* 1711 */     return new JAXBElement(_CatchEvent_QNAME, CatchEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataOutputAssociation")
/*      */   public JAXBElement<DataOutputAssociation> createDataOutputAssociation(DataOutputAssociation value)
/*      */   {
/* 1720 */     return new JAXBElement(_DataOutputAssociation_QNAME, DataOutputAssociation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataObjectReference", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<DataObjectReference> createDataObjectReference(DataObjectReference value)
/*      */   {
/* 1729 */     return new JAXBElement(_DataObjectReference_QNAME, DataObjectReference.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="boundaryEvent", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<BoundaryEvent> createBoundaryEvent(BoundaryEvent value)
/*      */   {
/* 1738 */     return new JAXBElement(_BoundaryEvent_QNAME, BoundaryEvent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="participantAssociation")
/*      */   public JAXBElement<ParticipantAssociation> createParticipantAssociation(ParticipantAssociation value)
/*      */   {
/* 1747 */     return new JAXBElement(_ParticipantAssociation_QNAME, ParticipantAssociation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="sendTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<SendTask> createSendTask(SendTask value)
/*      */   {
/* 1756 */     return new JAXBElement(_SendTask_QNAME, SendTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="categoryValue")
/*      */   public JAXBElement<CategoryValue> createCategoryValue(CategoryValue value)
/*      */   {
/* 1765 */     return new JAXBElement(_CategoryValue_QNAME, CategoryValue.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="choreography", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="collaboration")
/*      */   public JAXBElement<Choreography> createChoreography(Choreography value)
/*      */   {
/* 1774 */     return new JAXBElement(_Choreography_QNAME, Choreography.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalChoreographyTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="choreography")
/*      */   public JAXBElement<GlobalChoreographyTask> createGlobalChoreographyTask(GlobalChoreographyTask value)
/*      */   {
/* 1783 */     return new JAXBElement(_GlobalChoreographyTask_QNAME, GlobalChoreographyTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="callChoreography", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<CallChoreography> createCallChoreography(CallChoreography value)
/*      */   {
/* 1792 */     return new JAXBElement(_CallChoreography_QNAME, CallChoreography.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="multiInstanceLoopCharacteristics", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="loopCharacteristics")
/*      */   public JAXBElement<MultiInstanceLoopCharacteristics> createMultiInstanceLoopCharacteristics(MultiInstanceLoopCharacteristics value)
/*      */   {
/* 1801 */     return new JAXBElement(_MultiInstanceLoopCharacteristics_QNAME, MultiInstanceLoopCharacteristics.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="potentialOwner", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="performer")
/*      */   public JAXBElement<PotentialOwner> createPotentialOwner(PotentialOwner value)
/*      */   {
/* 1810 */     return new JAXBElement(_PotentialOwner_QNAME, PotentialOwner.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="correlationPropertyBinding")
/*      */   public JAXBElement<CorrelationPropertyBinding> createCorrelationPropertyBinding(CorrelationPropertyBinding value)
/*      */   {
/* 1819 */     return new JAXBElement(_CorrelationPropertyBinding_QNAME, CorrelationPropertyBinding.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="signal", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Signal> createSignal(Signal value)
/*      */   {
/* 1828 */     return new JAXBElement(_Signal_QNAME, Signal.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="userTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<UserTask> createUserTask(UserTask value)
/*      */   {
/* 1837 */     return new JAXBElement(_UserTask_QNAME, UserTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="parallelGateway", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ParallelGateway> createParallelGateway(ParallelGateway value)
/*      */   {
/* 1846 */     return new JAXBElement(_ParallelGateway_QNAME, ParallelGateway.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="lane")
/*      */   public JAXBElement<Lane> createLane(Lane value)
/*      */   {
/* 1855 */     return new JAXBElement(_Lane_QNAME, Lane.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="subConversation", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="conversationNode")
/*      */   public JAXBElement<SubConversation> createSubConversation(SubConversation value)
/*      */   {
/* 1864 */     return new JAXBElement(_SubConversation_QNAME, SubConversation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="baseElementWithMixedContent")
/*      */   public JAXBElement<BaseElementWithMixedContent> createBaseElementWithMixedContent(BaseElementWithMixedContent value)
/*      */   {
/* 1873 */     return new JAXBElement(_BaseElementWithMixedContent_QNAME, BaseElementWithMixedContent.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataStore", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<DataStore> createDataStore(DataStore value)
/*      */   {
/* 1882 */     return new JAXBElement(_DataStore_QNAME, DataStore.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="signalEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<SignalEventDefinition> createSignalEventDefinition(SignalEventDefinition value)
/*      */   {
/* 1891 */     return new JAXBElement(_SignalEventDefinition_QNAME, SignalEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="rootElement")
/*      */   public JAXBElement<RootElement> createRootElement(RootElement value)
/*      */   {
/* 1900 */     return new JAXBElement(_RootElement_QNAME, RootElement.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataState")
/*      */   public JAXBElement<DataState> createDataState(DataState value)
/*      */   {
/* 1909 */     return new JAXBElement(_DataState_QNAME, DataState.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="correlationPropertyRetrievalExpression")
/*      */   public JAXBElement<CorrelationPropertyRetrievalExpression> createCorrelationPropertyRetrievalExpression(CorrelationPropertyRetrievalExpression value)
/*      */   {
/* 1918 */     return new JAXBElement(_CorrelationPropertyRetrievalExpression_QNAME, CorrelationPropertyRetrievalExpression.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="ioSpecification")
/*      */   public JAXBElement<InputOutputSpecification> createIoSpecification(InputOutputSpecification value)
/*      */   {
/* 1927 */     return new JAXBElement(_IoSpecification_QNAME, InputOutputSpecification.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="conversationAssociation")
/*      */   public JAXBElement<ConversationAssociation> createConversationAssociation(ConversationAssociation value)
/*      */   {
/* 1936 */     return new JAXBElement(_ConversationAssociation_QNAME, ConversationAssociation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="laneSet")
/*      */   public JAXBElement<LaneSet> createLaneSet(LaneSet value)
/*      */   {
/* 1945 */     return new JAXBElement(_LaneSet_QNAME, LaneSet.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="activity")
/*      */   public JAXBElement<Activity> createActivity(Activity value)
/*      */   {
/* 1954 */     return new JAXBElement(_Activity_QNAME, Activity.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<GlobalTask> createGlobalTask(GlobalTask value)
/*      */   {
/* 1963 */     return new JAXBElement(_GlobalTask_QNAME, GlobalTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="error", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Error> createError(Error value)
/*      */   {
/* 1972 */     return new JAXBElement(_Error_QNAME, Error.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="task", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<Task> createTask(Task value)
/*      */   {
/* 1981 */     return new JAXBElement(_Task_QNAME, Task.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="resource", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Resource> createResource(Resource value)
/*      */   {
/* 1990 */     return new JAXBElement(_Resource_QNAME, Resource.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="interface", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Interface> createInterface(Interface value)
/*      */   {
/* 1999 */     return new JAXBElement(_Interface_QNAME, Interface.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="correlationKey")
/*      */   public JAXBElement<CorrelationKey> createCorrelationKey(CorrelationKey value)
/*      */   {
/* 2008 */     return new JAXBElement(_CorrelationKey_QNAME, CorrelationKey.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="rendering")
/*      */   public JAXBElement<Rendering> createRendering(Rendering value)
/*      */   {
/* 2017 */     return new JAXBElement(_Rendering_QNAME, Rendering.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="resourceParameterBinding")
/*      */   public JAXBElement<ResourceParameterBinding> createResourceParameterBinding(ResourceParameterBinding value)
/*      */   {
/* 2026 */     return new JAXBElement(_ResourceParameterBinding_QNAME, ResourceParameterBinding.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="exclusiveGateway", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ExclusiveGateway> createExclusiveGateway(ExclusiveGateway value)
/*      */   {
/* 2035 */     return new JAXBElement(_ExclusiveGateway_QNAME, ExclusiveGateway.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="correlationProperty", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<CorrelationProperty> createCorrelationProperty(CorrelationProperty value)
/*      */   {
/* 2044 */     return new JAXBElement(_CorrelationProperty_QNAME, CorrelationProperty.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="message", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Message> createMessage(Message value)
/*      */   {
/* 2053 */     return new JAXBElement(_Message_QNAME, Message.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataStoreReference", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<DataStoreReference> createDataStoreReference(DataStoreReference value)
/*      */   {
/* 2062 */     return new JAXBElement(_DataStoreReference_QNAME, DataStoreReference.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="globalManualTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<GlobalManualTask> createGlobalManualTask(GlobalManualTask value)
/*      */   {
/* 2071 */     return new JAXBElement(_GlobalManualTask_QNAME, GlobalManualTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="escalation", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<Escalation> createEscalation(Escalation value)
/*      */   {
/* 2080 */     return new JAXBElement(_Escalation_QNAME, Escalation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="callActivity", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<CallActivity> createCallActivity(CallActivity value)
/*      */   {
/* 2089 */     return new JAXBElement(_CallActivity_QNAME, CallActivity.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="textAnnotation", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="artifact")
/*      */   public JAXBElement<TextAnnotation> createTextAnnotation(TextAnnotation value)
/*      */   {
/* 2098 */     return new JAXBElement(_TextAnnotation_QNAME, TextAnnotation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="group", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="artifact")
/*      */   public JAXBElement<Group> createGroup(Group value)
/*      */   {
/* 2107 */     return new JAXBElement(_Group_QNAME, Group.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="auditing")
/*      */   public JAXBElement<Auditing> createAuditing(Auditing value)
/*      */   {
/* 2116 */     return new JAXBElement(_Auditing_QNAME, Auditing.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataOutput")
/*      */   public JAXBElement<DataOutput> createDataOutput(DataOutput value)
/*      */   {
/* 2125 */     return new JAXBElement(_DataOutput_QNAME, DataOutput.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="expression")
/*      */   public JAXBElement<Expression> createExpression(Expression value)
/*      */   {
/* 2134 */     return new JAXBElement(_Expression_QNAME, Expression.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="transaction", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<Transaction> createTransaction(Transaction value)
/*      */   {
/* 2143 */     return new JAXBElement(_Transaction_QNAME, Transaction.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="choreographyTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<ChoreographyTask> createChoreographyTask(ChoreographyTask value)
/*      */   {
/* 2152 */     return new JAXBElement(_ChoreographyTask_QNAME, ChoreographyTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="gateway")
/*      */   public JAXBElement<Gateway> createGateway(Gateway value)
/*      */   {
/* 2161 */     return new JAXBElement(_Gateway_QNAME, Gateway.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="resourceRole")
/*      */   public JAXBElement<ResourceRole> createResourceRole(ResourceRole value)
/*      */   {
/* 2170 */     return new JAXBElement(_ResourceRole_QNAME, ResourceRole.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="partnerEntity", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<PartnerEntity> createPartnerEntity(PartnerEntity value)
/*      */   {
/* 2179 */     return new JAXBElement(_PartnerEntity_QNAME, PartnerEntity.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="partnerRole", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="rootElement")
/*      */   public JAXBElement<PartnerRole> createPartnerRole(PartnerRole value)
/*      */   {
/* 2188 */     return new JAXBElement(_PartnerRole_QNAME, PartnerRole.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="businessRuleTask", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<BusinessRuleTask> createBusinessRuleTask(BusinessRuleTask value)
/*      */   {
/* 2197 */     return new JAXBElement(_BusinessRuleTask_QNAME, BusinessRuleTask.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="ioBinding")
/*      */   public JAXBElement<InputOutputBinding> createIoBinding(InputOutputBinding value)
/*      */   {
/* 2206 */     return new JAXBElement(_IoBinding_QNAME, InputOutputBinding.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="flowElement")
/*      */   public JAXBElement<FlowElement> createFlowElement(FlowElement value)
/*      */   {
/* 2215 */     return new JAXBElement(_FlowElement_QNAME, FlowElement.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataObject", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="flowElement")
/*      */   public JAXBElement<DataObject> createDataObject(DataObject value)
/*      */   {
/* 2224 */     return new JAXBElement(_DataObject_QNAME, DataObject.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="linkEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<LinkEventDefinition> createLinkEventDefinition(LinkEventDefinition value)
/*      */   {
/* 2233 */     return new JAXBElement(_LinkEventDefinition_QNAME, LinkEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="resourceParameter")
/*      */   public JAXBElement<ResourceParameter> createResourceParameter(ResourceParameter value)
/*      */   {
/* 2242 */     return new JAXBElement(_ResourceParameter_QNAME, ResourceParameter.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="text")
/*      */   public JAXBElement<Text> createText(Text value)
/*      */   {
/* 2251 */     return new JAXBElement(_Text_QNAME, Text.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="association", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="artifact")
/*      */   public JAXBElement<Association> createAssociation(Association value)
/*      */   {
/* 2260 */     return new JAXBElement(_Association_QNAME, Association.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="callConversation", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="conversationNode")
/*      */   public JAXBElement<CallConversation> createCallConversation(CallConversation value)
/*      */   {
/* 2269 */     return new JAXBElement(_CallConversation_QNAME, CallConversation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="escalationEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<EscalationEventDefinition> createEscalationEventDefinition(EscalationEventDefinition value)
/*      */   {
/* 2278 */     return new JAXBElement(_EscalationEventDefinition_QNAME, EscalationEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="definitions")
/*      */   public JAXBElement<Definitions> createDefinitions(Definitions value)
/*      */   {
/* 2287 */     return new JAXBElement(_Definitions_QNAME, Definitions.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="property")
/*      */   public JAXBElement<Property> createProperty(Property value)
/*      */   {
/* 2296 */     return new JAXBElement(_Property_QNAME, Property.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="script")
/*      */   public JAXBElement<Script> createScript(Script value)
/*      */   {
/* 2305 */     return new JAXBElement(_Script_QNAME, Script.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataInput")
/*      */   public JAXBElement<DataInput> createDataInput(DataInput value)
/*      */   {
/* 2314 */     return new JAXBElement(_DataInput_QNAME, DataInput.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="conversationNode")
/*      */   public JAXBElement<ConversationNode> createConversationNode(ConversationNode value)
/*      */   {
/* 2323 */     return new JAXBElement(_ConversationNode_QNAME, ConversationNode.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="compensateEventDefinition", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="eventDefinition")
/*      */   public JAXBElement<CompensateEventDefinition> createCompensateEventDefinition(CompensateEventDefinition value)
/*      */   {
/* 2332 */     return new JAXBElement(_CompensateEventDefinition_QNAME, CompensateEventDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="complexBehaviorDefinition")
/*      */   public JAXBElement<ComplexBehaviorDefinition> createComplexBehaviorDefinition(ComplexBehaviorDefinition value)
/*      */   {
/* 2341 */     return new JAXBElement(_ComplexBehaviorDefinition_QNAME, ComplexBehaviorDefinition.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="conversationLink")
/*      */   public JAXBElement<ConversationLink> createConversationLink(ConversationLink value)
/*      */   {
/* 2350 */     return new JAXBElement(_ConversationLink_QNAME, ConversationLink.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="messageFlowAssociation")
/*      */   public JAXBElement<MessageFlowAssociation> createMessageFlowAssociation(MessageFlowAssociation value)
/*      */   {
/* 2359 */     return new JAXBElement(_MessageFlowAssociation_QNAME, MessageFlowAssociation.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="standardLoopCharacteristics", substitutionHeadNamespace="http://www.omg.org/spec/BPMN/20100524/MODEL", substitutionHeadName="loopCharacteristics")
/*      */   public JAXBElement<StandardLoopCharacteristics> createStandardLoopCharacteristics(StandardLoopCharacteristics value)
/*      */   {
/* 2368 */     return new JAXBElement(_StandardLoopCharacteristics_QNAME, StandardLoopCharacteristics.class, null, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="sourceRef", scope=DataAssociation.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createDataAssociationSourceRef(Object value)
/*      */   {
/* 2378 */     return new JAXBElement(_DataAssociationSourceRef_QNAME, Object.class, DataAssociation.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataOutputRefs", scope=OutputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createOutputSetDataOutputRefs(Object value)
/*      */   {
/* 2388 */     return new JAXBElement(_OutputSetDataOutputRefs_QNAME, Object.class, OutputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="optionalOutputRefs", scope=OutputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createOutputSetOptionalOutputRefs(Object value)
/*      */   {
/* 2398 */     return new JAXBElement(_OutputSetOptionalOutputRefs_QNAME, Object.class, OutputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="whileExecutingOutputRefs", scope=OutputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createOutputSetWhileExecutingOutputRefs(Object value)
/*      */   {
/* 2408 */     return new JAXBElement(_OutputSetWhileExecutingOutputRefs_QNAME, Object.class, OutputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="inputSetRefs", scope=OutputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createOutputSetInputSetRefs(Object value)
/*      */   {
/* 2418 */     return new JAXBElement(_OutputSetInputSetRefs_QNAME, Object.class, OutputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="whileExecutingInputRefs", scope=InputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createInputSetWhileExecutingInputRefs(Object value)
/*      */   {
/* 2428 */     return new JAXBElement(_InputSetWhileExecutingInputRefs_QNAME, Object.class, InputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="outputSetRefs", scope=InputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createInputSetOutputSetRefs(Object value)
/*      */   {
/* 2438 */     return new JAXBElement(_InputSetOutputSetRefs_QNAME, Object.class, InputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="optionalInputRefs", scope=InputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createInputSetOptionalInputRefs(Object value)
/*      */   {
/* 2448 */     return new JAXBElement(_InputSetOptionalInputRefs_QNAME, Object.class, InputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="dataInputRefs", scope=InputSet.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createInputSetDataInputRefs(Object value)
/*      */   {
/* 2458 */     return new JAXBElement(_InputSetDataInputRefs_QNAME, Object.class, InputSet.class, value);
/*      */   }
/*      */ 
/*      */   @XmlElementDecl(namespace="http://www.omg.org/spec/BPMN/20100524/MODEL", name="flowNodeRef", scope=Lane.class)
/*      */   @XmlIDREF
/*      */   public JAXBElement<Object> createLaneFlowNodeRef(Object value)
/*      */   {
/* 2468 */     return new JAXBElement(_LaneFlowNodeRef_QNAME, Object.class, Lane.class, value);
/*      */   }
/*      */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.ObjectFactory
 * JD-Core Version:    0.6.2
 */