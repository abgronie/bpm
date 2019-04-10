/*     */ package com.hotent.core.bpmn20.util;
/*     */ 
/*     */ import com.hotent.core.bpmn20.ContextFactory;
/*     */ import com.hotent.core.bpmn20.entity.Definitions;
/*     */ import com.hotent.core.bpmn20.entity.ExtensionElements;
/*     */ import com.hotent.core.bpmn20.entity.FlowElement;
/*     */ import com.hotent.core.bpmn20.entity.Process;
/*     */ import com.hotent.core.bpmn20.entity.RootElement;
/*     */ import com.hotent.core.bpmn20.entity.SubProcess;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.JAXBContext;
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.JAXBException;
/*     */ import javax.xml.bind.Marshaller;
/*     */ import javax.xml.bind.Unmarshaller;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ public class BPMN20Util
/*     */ {
/*     */   public static List<FlowElement> getFlowElementByType(Process process, boolean deepIntoSub, Class<? extends FlowElement>[] flowTypes)
/*     */   {
/*  37 */     List flowElements = new ArrayList();
/*  38 */     List<JAXBElement<? extends FlowElement>>  jaxbElementFlowElements = process.getFlowElement();
/*  39 */     for (JAXBElement jAXBElement : jaxbElementFlowElements) {
/*  40 */       FlowElement flowElement = (FlowElement)jAXBElement.getValue();
/*  41 */       for (Class flowType : flowTypes) {
/*  42 */         if (flowType.isInstance(flowElement)) {
/*  43 */           flowElements.add(flowElement);
/*  44 */           break;
/*     */         }
/*     */       }
/*     */ 
/*  48 */       if ((deepIntoSub) && 
/*  49 */         ((flowElement instanceof SubProcess))) {
/*  50 */         flowElements.addAll(getFlowElementByTypeInSubProcess((SubProcess)flowElement, true, flowTypes));
/*     */       }
/*     */     }
/*     */ 
/*  54 */     return flowElements;
/*     */   }
/*     */ 
/*     */   public static List<FlowElement> getFlowElementByTypeInSubProcess(SubProcess subProcess, boolean deepIntoSub, Class<? extends FlowElement>[] flowTypes)
/*     */   {
/*  66 */     List flowElements = new ArrayList();
/*  67 */     List<JAXBElement<? extends FlowElement>> jaxbElementFlowElements = subProcess.getFlowElement();
/*  68 */     for (JAXBElement jAXBElement : jaxbElementFlowElements) {
/*  69 */       FlowElement flowElement = (FlowElement)jAXBElement.getValue();
/*  70 */       for (Class flowType : flowTypes) {
/*  71 */         if (flowType.isInstance(flowElement)) {
/*  72 */           flowElements.add(flowElement);
/*  73 */           break;
/*     */         }
/*     */       }
/*  76 */       if ((deepIntoSub) && 
/*  77 */         ((flowElement instanceof SubProcess))) {
/*  78 */         flowElements.addAll(getFlowElementByTypeInSubProcess((SubProcess)flowElement, true, flowTypes));
/*     */       }
/*     */     }
/*     */ 
/*  82 */     return flowElements;
/*     */   }
/*     */ 
/*     */   public static OutputStream marshall(Object jaxbElement, OutputStream os) throws JAXBException {
/*  86 */     JAXBContext jctx = JAXBContext.newInstance(new Class[] { com.hotent.core.bpmn20.entity.ObjectFactory.class });
/*  87 */     Marshaller marshaller = jctx.createMarshaller();
/*  88 */     marshaller.marshal(jaxbElement, os);
/*  89 */     return os;
/*     */   }
/*     */ 
/*     */   public static Object unmarshall(InputStream is, Class<? extends Object>[] classes) throws JAXBException, IOException {
/*  93 */     JAXBContext jctx = ContextFactory.newInstance(classes);
/*  94 */     Unmarshaller unmarshaller = jctx.createUnmarshaller();
/*  95 */     Object obj = unmarshaller.unmarshal(is);
/*  96 */     return obj;
/*     */   }
/*     */ 
/*     */   public static Object unmarshall(String bpmnxml, Class<? extends Object> classes) throws JAXBException, IOException {
/* 100 */     InputStream is = new ByteArrayInputStream(bpmnxml.getBytes());
/*     */ 
/* 102 */     return unmarshall(is, new Class[] { classes });
/*     */   }
/*     */ 
/*     */   public static Definitions createDefinitions(InputStream is)
/*     */     throws JAXBException, IOException
/*     */   {
/* 114 */     JAXBElement jAXBElement = (JAXBElement)unmarshall(is, new Class[] { com.hotent.core.bpmn20.entity.ObjectFactory.class, com.hotent.core.bpmn20.entity.activiti.ObjectFactory.class, com.hotent.core.bpmn20.entity.omgdc.ObjectFactory.class, com.hotent.core.bpmn20.entity.omgdi.ObjectFactory.class, com.hotent.core.bpmn20.entity.ht.ObjectFactory.class, com.hotent.core.bpmn20.entity.bpmndi.ObjectFactory.class });
/*     */ 
/* 121 */     Definitions definitions = (Definitions)jAXBElement.getValue();
/* 122 */     return definitions;
/*     */   }
/*     */ 
/*     */   public static Definitions createDefinitions(String bpmnxml)
/*     */     throws JAXBException, IOException
/*     */   {
/* 132 */     InputStream is = new ByteArrayInputStream(bpmnxml.getBytes());
/* 133 */     return createDefinitions(is);
/*     */   }
/*     */ 
/*     */   public static List<Process> getProcess(InputStream is)
/*     */     throws JAXBException, IOException
/*     */   {
/* 145 */     List processes = new ArrayList();
/* 146 */     Definitions definitions = createDefinitions(is);
/* 147 */     List<JAXBElement<? extends RootElement>> bPMNElements = definitions.getRootElement();
/* 148 */     for (JAXBElement jAXBe : bPMNElements) {
/* 149 */       RootElement element = (RootElement)jAXBe.getValue();
/* 150 */       if ((element instanceof Process)) {
/* 151 */         processes.add((Process)element);
/*     */       }
/*     */     }
/* 154 */     return processes;
/*     */   }
/*     */ 
/*     */   public static List<Process> getProcess(String bpmnxml)
/*     */     throws JAXBException, IOException
/*     */   {
/* 165 */     InputStream is = new ByteArrayInputStream(bpmnxml.getBytes("UTF-8"));
/* 166 */     return getProcess(is);
/*     */   }
/*     */ 
/*     */   public static List<Object> getFlowElementExtension(FlowElement flowElement, QName qname)
/*     */   {
/* 176 */     List extensions = new ArrayList();
/* 177 */     ExtensionElements extensionElements = flowElement.getExtensionElements();
/* 178 */     if (extensionElements == null) {
/* 179 */       return extensions;
/*     */     }
/* 181 */     List objects = extensionElements.getAny();
/* 182 */     for (Iterator i$ = objects.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 183 */       if ((obj instanceof JAXBElement)) {
/* 184 */         JAXBElement extension = (JAXBElement)obj;
/* 185 */         if (extension.getName().equals(qname)) {
/* 186 */           extensions.add(extension.getValue());
/*     */         }
/*     */       }
/*     */     }
/* 190 */     return extensions;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.util.BPMN20Util
 * JD-Core Version:    0.6.2
 */