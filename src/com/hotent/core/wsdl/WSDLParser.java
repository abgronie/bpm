/*     */ package com.hotent.core.wsdl;
/*     */ 
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import com.ibm.wsdl.ImportImpl;
/*     */ import com.ibm.wsdl.ServiceImpl;
/*     */ import com.ibm.wsdl.extensions.schema.SchemaImpl;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import javax.wsdl.Binding;
/*     */ import javax.wsdl.BindingOperation;
/*     */ import javax.wsdl.Definition;
/*     */ import javax.wsdl.Input;
/*     */ import javax.wsdl.Message;
/*     */ import javax.wsdl.Operation;
/*     */ import javax.wsdl.Output;
/*     */ import javax.wsdl.Part;
/*     */ import javax.wsdl.Port;
/*     */ import javax.wsdl.Types;
/*     */ import javax.wsdl.WSDLException;
/*     */ import javax.wsdl.extensions.ExtensibilityElement;
/*     */ import javax.wsdl.extensions.http.HTTPAddress;
/*     */ import javax.wsdl.extensions.soap.SOAPAddress;
/*     */ import javax.wsdl.extensions.soap.SOAPOperation;
/*     */ import javax.wsdl.factory.WSDLFactory;
/*     */ import javax.wsdl.xml.WSDLReader;
/*     */ import javax.xml.namespace.QName;
/*     */ import org.apache.ws.commons.schema.XmlSchema;
/*     */ import org.apache.ws.commons.schema.XmlSchemaCollection;
/*     */ import org.apache.ws.commons.schema.XmlSchemaComplexContentExtension;
/*     */ import org.apache.ws.commons.schema.XmlSchemaComplexType;
/*     */ import org.apache.ws.commons.schema.XmlSchemaContent;
/*     */ import org.apache.ws.commons.schema.XmlSchemaContentModel;
/*     */ import org.apache.ws.commons.schema.XmlSchemaElement;
/*     */ import org.apache.ws.commons.schema.XmlSchemaForm;
/*     */ import org.apache.ws.commons.schema.XmlSchemaImport;
/*     */ import org.apache.ws.commons.schema.XmlSchemaObject;
/*     */ import org.apache.ws.commons.schema.XmlSchemaParticle;
/*     */ import org.apache.ws.commons.schema.XmlSchemaSequence;
/*     */ import org.apache.ws.commons.schema.XmlSchemaSequenceMember;
/*     */ import org.apache.ws.commons.schema.XmlSchemaType;
/*     */ import org.apache.ws.commons.schema.utils.XmlSchemaRef;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ public class WSDLParser
/*     */ {
/*  61 */   protected static Logger logger = LoggerFactory.getLogger(WSDLParser.class);
/*     */ 
/* 126 */   private Map<String, XmlSchemaType> complexTypes = new HashMap();
/*     */ 
/* 128 */   private String currentNameSpace = "";
/*     */ 
/* 130 */   private String elementFormDefault = XmlSchemaForm.QUALIFIED.toString();
/*     */ 
/* 132 */   private Map<String, ServiceInfo> services = new HashMap();
/*     */ 
/*     */   private static void getParam(ParameterInfo parameterInfo)
/*     */   {
/*  70 */     if (parameterInfo.getIsComplext() == ParameterInfo.COMPLEX_YES) {
/*  71 */       if (!"parameters".equals(parameterInfo.getName())) {
/*  72 */         logger.info("--" + parameterInfo.getType() + "复杂类型开始:--");
/*     */       }
/*  74 */       Map tempMap = parameterInfo.getComplextParams();
/*  75 */       Set keys = tempMap.keySet();
/*  76 */       for (Iterator otheriterator = keys.iterator(); otheriterator.hasNext(); ) {
/*  77 */         Object key = otheriterator.next();
/*  78 */         ParameterInfo parameter = (ParameterInfo)tempMap.get(key);
/*  79 */         getParam(parameter);
/*     */       }
/*  81 */       if (!"parameters".equals(parameterInfo.getName()))
/*  82 */         logger.info("--" + parameterInfo.getType() + "复杂类型end:--");
/*     */     }
/*     */     else {
/*  85 */       logger.info("  --inputparamName:" + parameterInfo.getName() + "  --inputparamType:" + parameterInfo.getType());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  97 */     String wsdlURI = "http://hotent.yicp.io:9080/bpm/service/UserDetailsService?wsdl";
/*     */     try {
/*  99 */       WSDLParser parser = new WSDLParser(wsdlURI);
/*     */ 
/* 101 */       Collection serviceInfos = parser.getServices().values();
/* 102 */       Iterator it = serviceInfos.iterator();
/*     */       Map operationList;
/*     */       Iterator iterator;
/* 103 */       while (it.hasNext()) {
/* 104 */         ServiceInfo serviceInfo = (ServiceInfo)it.next();
/* 105 */         operationList = serviceInfo.getOperations();
/* 106 */         Set keys = operationList.keySet();
/* 107 */         for (iterator = keys.iterator(); iterator.hasNext(); ) {
/* 108 */           Object key = iterator.next();
/* 109 */           System.out.println(key);
/* 110 */           OperationInfo info = (OperationInfo)operationList.get(key);
/*     */ 
/* 112 */           List inputParams = info.getInputParams();
/* 113 */           Iterator it1 = inputParams.iterator();
/* 114 */           while (it1.hasNext()) {
/* 115 */             ParameterInfo tempinfo = (ParameterInfo)it1.next();
/* 116 */             getParam(tempinfo);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e1) {
/* 121 */       e1.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public WSDLParser(String wsdlURI)
/*     */     throws WSDLException
/*     */   {
/* 135 */     parseWSDL(wsdlURI);
/*     */   }
/*     */ 
/*     */   private boolean containOperation(ServiceInfo serviceInfo, OperationInfo operationInfo)
/*     */   {
/* 146 */     Map operations = serviceInfo.getOperations();
/* 147 */     if (operations.get(operationInfo.getOperationName()) != null) {
/* 148 */       return true;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean containService(String serviceName)
/*     */   {
/* 161 */     if (this.services.get(serviceName) != null) {
/* 162 */       return true;
/*     */     }
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean containType(XmlSchemaType type)
/*     */   {
/* 175 */     String name = type.getName();
/* 176 */     if ((name != null) && (this.complexTypes.get(name) != null)) {
/* 177 */       return true;
/*     */     }
/* 179 */     return false;
/*     */   }
/*     */ 
/*     */   public Map<String, XmlSchemaType> getComplexTypes()
/*     */   {
/* 184 */     return this.complexTypes;
/*     */   }
/*     */ 
/*     */   public String getCurrentNameSpace() {
/* 188 */     return this.currentNameSpace;
/*     */   }
/*     */ 
/*     */   public Map<String, ServiceInfo> getServices() {
/* 192 */     return this.services;
/*     */   }
/*     */ 
/*     */   private void parseWSDL(String wsdlURI)
/*     */     throws WSDLException
/*     */   {
/* 202 */     WSDLFactory wsdlFactory = WSDLFactory.newInstance();
/* 203 */     WSDLReader reader = wsdlFactory.newWSDLReader();
/* 204 */     Definition defintion = reader.readWSDL(wsdlURI);
/* 205 */     processImport(defintion);
/* 206 */     processTypes(defintion);
/* 207 */     Map servicesMap = defintion.getAllServices();
/* 208 */     Set serviceKeys = servicesMap.keySet();
/* 209 */     for (Iterator iterator = serviceKeys.iterator(); iterator.hasNext(); ) {
/* 210 */       Object key = iterator.next();
/* 211 */       ServiceImpl service = (ServiceImpl)servicesMap.get(key);
/* 212 */       processService(service, wsdlURI);
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean processComplex(ParameterInfo parameterInfo, String typeName, String partName, String attrName, Boolean isList)
/*     */   {
/* 224 */     if (typeName == null)
/* 225 */       return false;
/* 226 */     XmlSchemaType xmlSchemaType = (XmlSchemaType)this.complexTypes.get(typeName);
/* 227 */     if (xmlSchemaType != null) {
/* 228 */       ParameterInfo complexType = new ParameterInfo();
/* 229 */       complexType.setType(typeName);
/* 230 */       complexType.setIsComplext(ParameterInfo.COMPLEX_YES);
/* 231 */       complexType.setIsList(isList);
/* 232 */       if ((typeName.contains("Response")) || (partName.equals(typeName))) {
/* 233 */         complexType.setIsShow(ParameterInfo.SHOW_NO);
/*     */       }
/*     */ 
/* 236 */       XmlSchemaParticle xmlSchemaParticle = ((XmlSchemaComplexType)xmlSchemaType).getParticle();
/* 237 */       if (xmlSchemaParticle == null)
/* 238 */         return false;
/* 239 */       List xmlSchemaObjectCollection = ((XmlSchemaSequence)xmlSchemaParticle).getItems();
/*     */ 
/* 241 */       int count = xmlSchemaObjectCollection.size();
/* 242 */       for (int j = 0; j < count; j++) {
/* 243 */         XmlSchemaSequenceMember xmlSchemaObject = (XmlSchemaSequenceMember)xmlSchemaObjectCollection.get(j);
/* 244 */         if ((xmlSchemaObject instanceof XmlSchemaElement)) {
/* 245 */           XmlSchemaElement xmlSchemaElement = (XmlSchemaElement)xmlSchemaObject;
/*     */ 
/* 247 */           long max = xmlSchemaElement.getMaxOccurs();
/* 248 */           String elementName = xmlSchemaElement.getName();
/*     */ 
/* 250 */           XmlSchemaType xmlSType = xmlSchemaElement.getSchemaType();
/* 251 */           if ((xmlSType instanceof XmlSchemaComplexType)) {
/* 252 */             String xmlSTypeName = xmlSType.getName();
/*     */ 
/* 255 */             complexType.setParentComplext(parameterInfo.getParentComplext());
/* 256 */             if (StringUtil.isNotEmpty(xmlSTypeName)) {
/* 257 */               if (parameterInfo.getParentComplext().get(xmlSType.getName()) != null)
/*     */                 break;
/* 259 */               processComplex(complexType, xmlSType.getName(), partName, elementName, Boolean.valueOf(max > 1L));
/*     */             } else {
/* 261 */               complexType.getParentComplext().put(elementName, elementName);
/* 262 */               xmlSType.setName(elementName);
/* 263 */               processComplexType(xmlSType);
/* 264 */               processComplex(complexType, elementName, partName, elementName, Boolean.valueOf(false));
/*     */             }
/* 266 */           } else if (xmlSchemaElement.isRef()) {
/* 267 */             XmlSchemaRef xmlSchemaRef = xmlSchemaElement.getRef();
/* 268 */             String type = xmlSchemaRef.getTargetQName().getLocalPart();
/* 269 */             processComplex(complexType, type, partName, elementName, Boolean.valueOf(false));
/*     */           } else {
/* 271 */             ParameterInfo simpleType = new ParameterInfo();
/* 272 */             simpleType.setName(elementName);
/* 273 */             String t = xmlSType.getName();
/* 274 */             if (max > 1L) {
/* 275 */               t = "List{" + t + "}";
/*     */             }
/* 277 */             simpleType.setType(t);
/* 278 */             complexType.getComplextParams().put(elementName, simpleType);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 283 */       if (StringUtil.isEmpty(attrName)) {
/* 284 */         parameterInfo.getComplextParams().put(typeName, complexType);
/*     */       }
/*     */       else {
/* 287 */         parameterInfo.getComplextParams().put(attrName, complexType);
/*     */       }
/* 289 */       return true;
/*     */     }
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */   private void processComplexType(XmlSchemaType type)
/*     */   {
/* 300 */     String typeName = type.getName();
/* 301 */     if (!(type instanceof XmlSchemaComplexType))
/* 302 */       return;
/* 303 */     if (containType(type))
/* 304 */       return;
/* 305 */     this.complexTypes.put(typeName, type);
/* 306 */     XmlSchemaComplexType xmlSchemaComplexType = (XmlSchemaComplexType)type;
/* 307 */     XmlSchemaContentModel xmlSchemaContentModel = xmlSchemaComplexType.getContentModel();
/* 308 */     XmlSchemaParticle xmlSchemaParticle = xmlSchemaComplexType.getParticle();
/* 309 */     if ((xmlSchemaParticle == null) && (xmlSchemaContentModel != null)) {
/* 310 */       XmlSchemaContent xmlSchemaContent = xmlSchemaContentModel.getContent();
/* 311 */       if ((xmlSchemaContent instanceof XmlSchemaComplexContentExtension)) {
/* 312 */         XmlSchemaComplexContentExtension xmlSchemaComplexContentExtension = (XmlSchemaComplexContentExtension)xmlSchemaContent;
/* 313 */         XmlSchemaParticle xmlSchemaParticleExtion = xmlSchemaComplexContentExtension.getParticle();
/* 314 */         xmlSchemaComplexType.setParticle(xmlSchemaParticleExtion);
/* 315 */         type = xmlSchemaComplexType;
/*     */       }
/*     */     }
/* 318 */     if (!(xmlSchemaParticle instanceof XmlSchemaSequence))
/* 319 */       return;
/* 320 */     XmlSchemaSequence xmlSchemaSequence = (XmlSchemaSequence)xmlSchemaParticle;
/* 321 */     List xmlSchemaObjectCollection = xmlSchemaSequence.getItems();
/* 322 */     int count = xmlSchemaObjectCollection.size();
/* 323 */     for (int i = 0; i < count; i++) {
/* 324 */       XmlSchemaSequenceMember xmlSchemaObject = (XmlSchemaSequenceMember)xmlSchemaObjectCollection.get(i);
/* 325 */       if ((xmlSchemaObject instanceof XmlSchemaElement))
/*     */       {
/* 328 */         XmlSchemaElement xmlSchemaElement = (XmlSchemaElement)xmlSchemaObject;
/* 329 */         XmlSchemaType xmlSchemaType = xmlSchemaElement.getSchemaType();
/* 330 */         if ((xmlSchemaType != null) && ((xmlSchemaType instanceof XmlSchemaComplexType)))
/* 331 */           processComplexType(xmlSchemaType);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void processImport(Definition defintion)
/*     */     throws WSDLException
/*     */   {
/* 344 */     this.currentNameSpace = defintion.getTargetNamespace();
/* 345 */     Map impMap = defintion.getImports();
/* 346 */     Iterator keys = impMap.keySet().iterator();
/* 347 */     Types types = defintion.getTypes();
/* 348 */     List extensibilityElements = types.getExtensibilityElements();
/* 349 */     if (BeanUtils.isNotEmpty(extensibilityElements)) {
/* 350 */       Object object = extensibilityElements.get(0);
/* 351 */       SchemaImpl schema = (SchemaImpl)object;
/* 352 */       if (BeanUtils.isNotEmpty(schema)) {
/* 353 */         Element element = schema.getElement();
/* 354 */         if (BeanUtils.isNotEmpty(element))
/*     */         {
/* 356 */           this.elementFormDefault = element.getAttribute("elementFormDefault");
/*     */         }
/*     */       }
/*     */     }
/* 360 */     while (keys.hasNext()) {
/* 361 */       Object key = keys.next();
/*     */ 
/* 363 */       Vector importImpls = (Vector)impMap.get(key);
/* 364 */       ImportImpl imp = (ImportImpl)importImpls.elementAt(0);
/* 365 */       this.currentNameSpace = imp.getNamespaceURI();
/* 366 */       parseWSDL(imp.getLocationURI());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void processInputParam(OperationInfo operationInfo, Input input)
/*     */   {
/* 378 */     List inputParams = operationInfo.getInputParams();
/* 379 */     Message message = input.getMessage();
/* 380 */     Map partMap = message.getParts();
/* 381 */     processParam(operationInfo, partMap, inputParams);
/*     */   }
/*     */ 
/*     */   private void processOperation(ServiceInfo serviceInfo, BindingOperation bindingOperation)
/*     */   {
/* 392 */     Operation operation = bindingOperation.getOperation();
/* 393 */     String operationName = operation.getName();
/* 394 */     OperationInfo operationInfo = new OperationInfo();
/* 395 */     operationInfo.setOperationName(operationName);
/*     */ 
/* 397 */     if (!containOperation(serviceInfo, operationInfo)) {
/* 398 */       serviceInfo.getOperations().put(operationName, operationInfo);
/*     */     }
/* 400 */     List extensions = bindingOperation.getExtensibilityElements();
/* 401 */     if (extensions != null) {
/* 402 */       for (int i = 0; i < extensions.size(); i++) {
/* 403 */         ExtensibilityElement extElement = (ExtensibilityElement)extensions.get(i);
/* 404 */         if ((extElement instanceof SOAPOperation)) {
/* 405 */           SOAPOperation soapOp = (SOAPOperation)extElement;
/* 406 */           String soapUri = soapOp.getSoapActionURI();
/* 407 */           operationInfo.setInputAction(soapUri);
/*     */         }
/*     */       }
/*     */     }
/* 411 */     Input input = operation.getInput();
/* 412 */     processInputParam(operationInfo, input);
/* 413 */     Output output = operation.getOutput();
/* 414 */     processOutputParam(operationInfo, output);
/*     */   }
/*     */ 
/*     */   private void processOutputParam(OperationInfo operationInfo, Output output)
/*     */   {
/* 424 */     if (output == null) return;
/*     */ 
/* 426 */     List outputParams = operationInfo.getOutputParams();
/* 427 */     Message message = output.getMessage();
/* 428 */     Map partMap = message.getParts();
/* 429 */     processParam(operationInfo, partMap, outputParams);
/*     */   }
/*     */ 
/*     */   private void processParam(OperationInfo operationInfo, Map<?, ?> partMap, List<ParameterInfo> params)
/*     */   {
/* 440 */     Collection parts = partMap.values();
/* 441 */     for (Iterator iterator = parts.iterator(); iterator.hasNext(); ) {
/* 442 */       Part part = (Part)iterator.next();
/* 443 */       String partName = part.getName();
/* 444 */       ParameterInfo parameterInfo = new ParameterInfo();
/* 445 */       String typeName = null;
/* 446 */       QName qName = part.getTypeName();
/* 447 */       if (qName != null) {
/* 448 */         typeName = qName.getLocalPart();
/*     */       }
/*     */       else {
/* 451 */         typeName = part.getElementName().getLocalPart();
/*     */       }
/*     */ 
/* 454 */       parameterInfo.setName(partName);
/* 455 */       parameterInfo.setType(typeName);
/* 456 */       if ("parameters".equals(partName)) {
/* 457 */         parameterInfo.setIsShow(ParameterInfo.SHOW_NO);
/*     */       }
/* 459 */       String operationName = operationInfo.getOperationName();
/* 460 */       if (processComplex(parameterInfo, typeName, operationName, null, Boolean.valueOf(false))) {
/* 461 */         parameterInfo.setIsComplext(ParameterInfo.COMPLEX_YES);
/*     */       }
/*     */ 
/* 464 */       params.add(parameterInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void processService(ServiceImpl service, String wsdlUrl)
/*     */   {
/* 474 */     String serviceName = service.getQName().getLocalPart();
/* 475 */     if (containService(serviceName))
/* 476 */       return;
/* 477 */     ServiceInfo serviceInfo = new ServiceInfo();
/* 478 */     serviceInfo.setWsdlUrl(wsdlUrl);
/* 479 */     String invokeUrl = wsdlUrl;
/* 480 */     if (wsdlUrl.matches(".*\\?(?i)wsdl$"))
/* 481 */       invokeUrl = wsdlUrl.substring(0, wsdlUrl.lastIndexOf("?"));
/* 482 */     serviceInfo.setHttpAddress(invokeUrl);
/* 483 */     serviceInfo.setName(serviceName);
/* 484 */     serviceInfo.setTargetNamespace(this.currentNameSpace);
/* 485 */     serviceInfo.setNeedPrefix(Boolean.valueOf(XmlSchemaForm.QUALIFIED.toString().equals(this.elementFormDefault)));
/* 486 */     this.services.put(serviceName, serviceInfo);
/* 487 */     Collection ports = service.getPorts().values();
/* 488 */     for (Iterator iterator = ports.iterator(); iterator.hasNext(); ) {
/* 489 */       Port port = (Port)iterator.next();
/* 490 */       List list = port.getExtensibilityElements();
/* 491 */       for (Iterator i$ = list.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 492 */         if ((obj instanceof HTTPAddress)) {
/* 493 */           HTTPAddress address = (HTTPAddress)obj;
/* 494 */           String location = address.getLocationURI();
/* 495 */           serviceInfo.setHttpAddress(location);
/*     */         }
/* 497 */         if ((obj instanceof SOAPAddress)) {
/* 498 */           SOAPAddress address = (SOAPAddress)obj;
/* 499 */           String location = address.getLocationURI();
/* 500 */           serviceInfo.setHttpAddress(location);
/*     */         }
/*     */       }
/* 503 */       Binding binding = port.getBinding();
/* 504 */       List operations = binding.getBindingOperations();
Iterator iterator2;
/* 505 */       for (iterator2 = operations.iterator(); iterator2.hasNext(); ) {
/* 506 */         BindingOperation bindingOperation = (BindingOperation)iterator2.next();
/* 507 */         processOperation(serviceInfo, bindingOperation);
/*     */       }
/*     */     }
/*     */    
/*     */   }
/*     */ 
/*     */   private void processTypes(Definition defintion)
/*     */   {
/* 518 */     XmlSchemaCollection xmlSchemaCollection = new XmlSchemaCollection();
/* 519 */     Types types = defintion.getTypes();
/* 520 */     if (types == null)
/* 521 */       return;
/* 522 */     List list = types.getExtensibilityElements();
/* 523 */     for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
/* 524 */       SchemaImpl schemaImpl = (SchemaImpl)iterator.next();
/* 525 */       Element element = schemaImpl.getElement();
/* 526 */       XmlSchema xmlSchema = xmlSchemaCollection.read(element);
/* 527 */       processXmlSchema(xmlSchema);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void processXmlSchema(XmlSchema xmlSchema)
/*     */   {
/* 539 */     if (BeanUtils.isEmpty(xmlSchema)) return;
/* 540 */     Map smlSchemaObjectTable = xmlSchema.getSchemaTypes();
/* 541 */     List<XmlSchemaObject> xmlSchemaObjs = xmlSchema.getItems();
/* 542 */     Set smlSchemaKeys = smlSchemaObjectTable.keySet();
/* 543 */     for (Iterator otheriterator = smlSchemaKeys.iterator(); otheriterator.hasNext(); ) {
/* 544 */       Object key = otheriterator.next();
/* 545 */       XmlSchemaType typevalue = (XmlSchemaType)smlSchemaObjectTable.get(key);
/* 546 */       processComplexType(typevalue);
/*     */     }
/* 548 */     for (XmlSchemaObject xmlSchemaObj : xmlSchemaObjs) {
/* 549 */       if ((xmlSchemaObj instanceof XmlSchemaImport)) {
/* 550 */         XmlSchemaImport xmlSchemaImport = (XmlSchemaImport)xmlSchemaObj;
/* 551 */         XmlSchema importXmlSchema = xmlSchemaImport.getSchema();
/* 552 */         processXmlSchema(importXmlSchema);
/*     */       }
/* 554 */       if ((xmlSchemaObj instanceof XmlSchemaElement)) {
/* 555 */         XmlSchemaElement xmlSchemaElement = (XmlSchemaElement)xmlSchemaObj;
/* 556 */         String schemaName = xmlSchemaElement.getName();
/* 557 */         XmlSchemaType typevalue = xmlSchemaElement.getSchemaType();
/* 558 */         typevalue.setName(schemaName);
/* 559 */         processComplexType(typevalue);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.wsdl.WSDLParser
 * JD-Core Version:    0.6.2
 */