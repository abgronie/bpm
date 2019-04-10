/*     */ package com.hotent.core.soap;
/*     */ 
/*     */ import com.hotent.core.engine.GroovyScriptEngine;
/*     */ import com.hotent.core.soap.type.SoapType;
/*     */ import com.hotent.core.soap.type.SoapTypes;
/*     */ import com.hotent.core.util.AppConfigUtil;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.xml.soap.MessageFactory;
/*     */ import javax.xml.soap.MimeHeaders;
/*     */ import javax.xml.soap.SOAPBody;
/*     */ import javax.xml.soap.SOAPConnection;
/*     */ import javax.xml.soap.SOAPConnectionFactory;
/*     */ import javax.xml.soap.SOAPElement;
/*     */ import javax.xml.soap.SOAPEnvelope;
/*     */ import javax.xml.soap.SOAPException;
/*     */ import javax.xml.soap.SOAPFactory;
/*     */ import javax.xml.soap.SOAPFault;
/*     */ import javax.xml.soap.SOAPMessage;
/*     */ import javax.xml.soap.SOAPPart;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ public class SoapUtil
/*     */ {
/*  63 */   private static Logger logger = LoggerFactory.getLogger(SoapUtil.class);
/*     */ 
/*  65 */   private static Integer _connTimeout = Integer.valueOf(0);
/*     */ 
/*  67 */   private static Integer _readTimeout = Integer.valueOf(0);
/*     */ 
/*  69 */   private static GroovyScriptEngine engine = new GroovyScriptEngine();
/*     */ 
/*     */   private static Object getWholeObject(Map variables, String binding)
/*     */     throws Exception
/*     */   {
/* 115 */     Object obj = null;
/* 116 */     Pattern regex = Pattern.compile("(\\w*)\\..*");
/* 117 */     Matcher regexMatcher = regex.matcher(binding);
/* 118 */     if (regexMatcher.find()) {
/* 119 */       String varKey = regexMatcher.group(1);
/* 120 */       obj = variables.get(varKey);
/*     */     }
/*     */     else
/*     */     {
/* 124 */       obj = PropertyUtils.getProperty(variables, binding);
/*     */     }
/* 126 */     return obj;
/*     */   }
/*     */ 
/*     */   private static void checkFault(SOAPMessage message)
/*     */     throws SOAPException, SoapUtil.InvokeException
/*     */   {
/* 615 */     SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
/* 616 */     SOAPBody body = envelope.getBody();
/* 617 */     SOAPFault fault = body.getFault();
/* 618 */     if ((fault != null) && (fault.getFaultCode() != null))
/* 619 */       throw new InvokeException(fault.getFaultCode(), fault.getFaultString());
/*     */   }
/*     */ 
/*     */   private static String getAttribute(Node node, String name)
/*     */   {
/* 631 */     Node tmp = node.getAttributes().getNamedItem(name);
/* 632 */     return tmp != null ? tmp.getTextContent() : null;
/*     */   }
/*     */ 
/*     */   private static SOAPMessage invoke(URL invokeURL, SOAPMessage request)
/*     */     throws Exception
/*     */   {
/* 648 */     SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
/* 649 */     SOAPConnection connection = null;
/*     */     try {
/* 651 */       URL endPoint = new URL(null, invokeURL.toString(), new URLStreamHandler()
/*     */       {
/*     */         protected URLConnection openConnection(URL u) throws IOException {
/* 654 */           URL clone_url = new URL(u.toString());
/* 655 */           HttpURLConnection clone_urlconnection = (HttpURLConnection)clone_url.openConnection();
/*     */ 
/* 657 */           if (SoapUtil._connTimeout.intValue() == 0) {
/* 658 */             _connTimeout = Integer.parseInt(AppConfigUtil.get("webservice.connTimeout"));
/* 659 */             if (SoapUtil._connTimeout.intValue() == 0)
/* 660 */               _connTimeout = Integer.valueOf(3000);
/*     */           }
/* 668 */           clone_urlconnection.setConnectTimeout(SoapUtil._connTimeout.intValue());
/* 669 */           clone_urlconnection.setReadTimeout(SoapUtil._readTimeout.intValue());
/* 670 */           return clone_urlconnection;
/*     */         }
/*     */       });
/* 673 */       connection = soapConnFactory.createConnection();
/*     */ 
/* 675 */       SOAPMessage reply = connection.call(request, endPoint);
/*     */ 
/* 678 */       return reply;
/*     */     } catch (Exception ex) {
/* 680 */       throw ex;
/*     */     }
/*     */     finally {
/* 683 */       if (connection != null)
/* 684 */         connection.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static SOAPMessage execute(Map variables, JSONObject jObject) throws Exception {
/* 689 */     JSONArray inputs = jObject.getJSONArray("inputs");
/* 690 */     JSONArray inputParams = null;
/* 691 */     if (jObject.containsKey("inputParams"))
/* 692 */       inputParams = jObject.getJSONArray("inputParams");
/* 693 */     String url = jObject.getString("url");
/* 694 */     String namespace = jObject.getString("namespace");
/* 695 */     String method = jObject.getString("method");
/* 696 */     Boolean needPrefix = Boolean.valueOf(false);
/* 697 */     if (jObject.containsKey("needPrefix")) {
/* 698 */       needPrefix = Boolean.valueOf(jObject.getBoolean("needPrefix"));
/*     */     }
/* 700 */     if ((StringUtil.isEmpty(url)) || (StringUtil.isEmpty(namespace)) || (StringUtil.isEmpty(method))) {
/* 701 */       throw new Exception("没有获取到webservice的调用地址、名称空间或调用方法.");
/*     */     }
/* 703 */     SOAPMessage requestMessage = RequestBuilder.build(inputs, inputParams, namespace, method, variables, needPrefix);
/*     */ 
/* 705 */     SOAPMessage responseMessage = invoke(new URL(url), requestMessage);
/*     */ 
/* 707 */     checkFault(responseMessage);
/*     */ 
/* 709 */     return responseMessage;
/*     */   }
/*     */ 
/*     */   public static void invoke(Map variables, JSONArray jArray)
/*     */     throws Exception
/*     */   {
/* 719 */     if (jArray.size() == 0) {
/* 720 */       logger.warn("没有找到webservice的调用配置.", jArray);
/* 721 */       return;
/*     */     }
/*     */     try
/*     */     {
	 Iterator i$;
/* 725 */       for (i$ = jArray.iterator(); i$.hasNext(); ) { 
				Object obj = i$.next();
/* 726 */         JSONObject jObject = (JSONObject)obj;
/* 727 */         JSONArray inputs = jObject.getJSONArray("inputs");
/* 728 */         JSONArray outputs = jObject.getJSONArray("outputs");
/* 729 */         JSONArray inputParams = null;
/* 730 */         if (jObject.containsKey("inputParams")) {
/* 731 */           inputParams = jObject.getJSONArray("inputParams");
/*     */         }
/* 733 */         String url = jObject.getString("url");
/* 734 */         String namespace = jObject.getString("namespace");
/* 735 */         String method = jObject.getString("method");
/* 736 */         Boolean needPrefix = Boolean.valueOf(false);
/* 737 */         if (jObject.containsKey("needPrefix")) {
/* 738 */           needPrefix = Boolean.valueOf(jObject.getBoolean("needPrefix"));
/*     */         }
/* 740 */         if ((StringUtil.isEmpty(url)) || (StringUtil.isEmpty(namespace)) || (StringUtil.isEmpty(method))) {
/* 741 */           logger.warn("没有获取到webservice的调用地址、名称空间或调用方法.", jObject);
/*     */         }
/*     */         else {
/* 744 */           SOAPMessage requestMessage = RequestBuilder.build(inputs, inputParams, namespace, method, variables, needPrefix);
/*     */ 
/* 746 */           SOAPMessage responseMessage = invoke(new URL(url), requestMessage);
/*     */ 
/* 748 */           ResponseBuilder.build(variables, outputs, responseMessage);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */      
/* 751 */       logger.error("调用webservice出错.", e);
/* 752 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String getXFDLString() throws IOException {
/* 757 */     File file = new File("D:\\dev\\bpmx3\\contract.xfdl");
/* 758 */     FileInputStream fis = new FileInputStream(file);
/* 759 */     ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 760 */     byte[] temp = new byte[1024];
/* 761 */     byte[] buf = null;
/* 762 */     int length = 0;
/* 763 */     while ((length = fis.read(temp, 0, 1024)) != -1) {
/* 764 */       bout.write(temp, 0, length);
/*     */     }
/* 766 */     buf = bout.toByteArray();
/* 767 */     BASE64Encoder sunEncoder = new BASE64Encoder();
/* 768 */     String str = sunEncoder.encodeBuffer(buf).toString();
/*     */ 
/* 770 */     return str;
/*     */   }
/*     */ 
/*     */   private static class ResponseBuilder
/*     */   {
/*     */     public static void build(Map variables, JSONArray jarray, SOAPMessage message)
/*     */       throws Exception
/*     */     {
/* 479 */       SoapUtil.checkFault(message);
/*     */ 
/* 481 */       NodeList nodeList = message.getSOAPBody().getChildNodes();
/*     */ 
/* 483 */       if ((nodeList == null) || (nodeList.getLength() < 1)) {
/* 484 */         return;
/*     */       }
/*     */ 
/* 488 */       SOAPElement[] elements = new SOAPElement[nodeList.getLength()];
/* 489 */       for (int i = 0; i < elements.length; i++) {
/* 490 */         elements[i] = ((SOAPElement)nodeList.item(i));
/*     */       }
/*     */ 
/* 493 */       for (Iterator i$ = jarray.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 494 */         JSONObject jobject = (JSONObject)obj;
/* 495 */         build(variables, elements, jobject); }
/*     */     }
/*     */ 
/*     */     private static SOAPElement getElementByPath(SOAPElement[] elements, String fullpath)
/*     */     {
/* 500 */       if (StringUtil.isEmpty(fullpath)) {
/* 501 */         return elements[0];
/*     */       }
/* 503 */       String[] names = fullpath.split("\\.");
/* 504 */       int size = names.length;
/*     */ 
/* 506 */       SOAPElement root = null;
/* 507 */       Node node = elements[0].getFirstChild();
/* 508 */       if (node != null) {
/* 509 */         root = (SOAPElement)node;
/*     */       }
/*     */ 
/* 512 */       if (root == null) {
/* 513 */         return null;
/*     */       }
/*     */ 
/* 516 */       for (int i = 1; i < size; i++) {
/* 517 */         String name = names[i];
/* 518 */         root = getElement(root.getChildElements(), name);
/*     */       }
/* 520 */       return root;
/*     */     }
/*     */ 
/*     */     private static SOAPElement getElement(Iterator<SOAPElement> it, String name) {
/* 524 */       while (it.hasNext()) {
/* 525 */         SOAPElement element = (SOAPElement)it.next();
/* 526 */         String tagName = element.getTagName();
/* 527 */         if (tagName.equals(name)) {
/* 528 */           return element;
/*     */         }
/*     */       }
/* 531 */       return null;
/*     */     }
/*     */ 
/*     */     private static void build(Map variables, SOAPElement[] roots, JSONObject jobject) throws Exception {
/* 535 */       if (jobject == null) return;
/*     */ 
/* 537 */       String binding = jobject.getString("bindingVal");
/* 538 */       String soapType = jobject.getString("soapType");
/* 539 */       String beanType = jobject.getString("javaType");
/* 540 */       Integer bindingType = Integer.valueOf(jobject.getInt("bindingType"));
/* 541 */       String fullpath = "";
/* 542 */       if (jobject.containsKey("fullpath")) {
/* 543 */         fullpath = jobject.getString("fullpath");
/*     */       }
/* 545 */       SOAPElement elements = getElementByPath(roots, fullpath);
/* 546 */       binding = StringUtil.jsonUnescape(binding);
/*     */ 
/* 548 */       if (StringUtil.isEmpty(binding)) return;
/*     */ 
/* 550 */       Object obj = null;
/*     */ 		SoapType converter;
/* 553 */       if (StringUtil.isNotEmpty(soapType))
/*     */       {
/*     */         try
/*     */         {
/*     */           Class kclass;
/* 556 */           if (soapType.matches("List\\{\\w*\\}")) {
/* 557 */             kclass = List.class;
/*     */           }
/*     */           else {
/* 560 */             kclass = Class.forName(soapType);
/*     */           }
/* 562 */           converter = SoapTypes.getTypeBySoap(soapType);
/* 563 */           obj = converter.convertToBean(kclass, new SOAPElement[] { elements });
/*     */         }
/*     */         catch (Exception ex) {
/* 566 */           converter = SoapTypes.getTypeBySoap("string");
/* 567 */           obj = converter.convertToBean(new SOAPElement[] { elements });
/*     */         }
/*     */ 
/*     */       }
/* 571 */       else if ((StringUtil.isNotEmpty(beanType)) && (bindingType.intValue() == 2)) {
/* 572 */         Class klass = Class.forName(beanType);
/* 573 */         converter = SoapTypes.getTypeByBean(klass);
/* 574 */         obj = converter.convertToBean(klass, new SOAPElement[] { elements });
/*     */       }
/*     */       else
/*     */       {
/* 578 */         obj = elements.getTextContent();
/*     */       }
/*     */ 
/* 581 */       switch (bindingType.intValue())
/*     */       {
/*     */       case 2:
/* 584 */         if (obj != null) {
/* 585 */           if ((!(obj instanceof List)) && 
/* 586 */             (binding.indexOf("[i]") > -1)) {
/* 587 */             List list = new ArrayList();
/* 588 */             list.add(obj);
/* 589 */             obj = list;
/*     */           }
/*     */ 
/* 592 */           PropertyUtils.setProperty(variables, binding, obj); } break;
/*     */       case 3:
/* 598 */         variables.put("returnObj", obj);
/* 599 */         SoapUtil.engine.executeObject(binding, variables);
/*     */ 
/* 601 */         variables.remove("returnObj");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class RequestBuilder
/*     */   {
/*     */     public static SOAPMessage build(JSONArray jarray, JSONArray inputParams, String namespace, String method, Map variables, Boolean needPrefix)
/*     */       throws Exception
/*     */     {
/* 137 */       return buildRequest(createRequest(jarray, inputParams, namespace, method, variables, needPrefix), namespace);
/*     */     }
/*     */ 
/*     */     private static SOAPMessage buildRequest(SOAPElement element, String namespace) throws SOAPException
/*     */     {
/* 142 */       MessageFactory messageFactory = MessageFactory.newInstance();
/*     */ 
/* 144 */       SOAPMessage message = messageFactory.createMessage();
/*     */ 
/* 146 */       if (StringUtil.isNotEmpty(namespace)) {
/* 147 */         MimeHeaders mHers = message.getMimeHeaders();
/* 148 */         mHers.setHeader("SOAPAction", element.getElementQName().getNamespaceURI() + element.getElementQName().getLocalPart());
/*     */       }
/*     */ 
/* 152 */       SOAPPart soapPart = message.getSOAPPart();
/* 153 */       SOAPEnvelope envelope = soapPart.getEnvelope();
/*     */ 
/* 155 */       SOAPBody body = envelope.getBody();
/*     */ 
/* 157 */       body.addChildElement(element);
/*     */ 
/* 159 */       message.saveChanges();
/* 160 */       return message;
/*     */     }
/*     */ 
/*     */     private static void buildSoapElementValue(SOAPElement soapElement, JSONObject jobject, Map variables)
/*     */       throws Exception
/*     */     {
/* 166 */       if (jobject == null) return;
/*     */ 
/* 168 */       String binding = jobject.getString("bindingVal");
/* 169 */       String soapType = jobject.getString("soapType");
/* 170 */       String javaType = jobject.getString("javaType");
/* 171 */       Integer bindingType = Integer.valueOf(jobject.getInt("bindingType"));
/* 172 */       binding = StringUtil.jsonUnescape(binding);
/*     */ 
/* 174 */       String listObj = "";
/* 175 */       String elementStr = "";
/* 176 */       Pattern regex = Pattern.compile("^.*\\.(\\w+)\\[i\\](\\.\\w+)?$");
/* 177 */       Matcher regexMatcher = regex.matcher(binding);
/* 178 */       if (regexMatcher.find()) {
/* 179 */         listObj = regexMatcher.group(1);
/* 180 */         elementStr = regexMatcher.group(2);
/*     */       }
/*     */       try {
/* 183 */         Object obj = null;
/* 184 */         switch (bindingType.intValue())
/*     */         {
/*     */         case 1:
/* 187 */           soapElement.setTextContent(binding);
/* 188 */           break;
/*     */         case 2:
/* 191 */           if (StringUtil.isNotEmpty(binding))
/* 192 */             obj = PropertyUtils.getProperty(variables, binding); break;
/*     */         case 3:
/* 197 */           GroovyScriptEngine scriptEngine = new GroovyScriptEngine();
/* 198 */           String scriptContent = binding;
/* 199 */           obj = scriptEngine.executeObject(scriptContent, variables);
/*     */         }
/*     */ 
/* 203 */         if (obj != null) {
/* 204 */           SoapType converter = null;
/* 205 */           Class klass = null;
/*     */ 
/* 207 */           if (soapType != null) {
/* 208 */             converter = SoapTypes.getTypeBySoap(soapType);
/*     */           }
/* 211 */           else if (javaType != null) {
/* 212 */             klass = Class.forName(javaType);
/* 213 */             converter = SoapTypes.getTypeByBean(klass);
/*     */           }
/*     */ 
/* 216 */           if (StringUtil.isNotEmpty(listObj)) {
/* 217 */             if ((obj instanceof List)) {
/* 218 */               List list = (List)obj;
/* 219 */               String elementName = soapElement.getLocalName();
/* 220 */               SOAPElement parentElement = soapElement;
/*     */ 
/* 222 */               if (StringUtil.isNotEmpty(elementStr)) {
/* 223 */                 parentElement = soapElement.getParentElement();
/*     */               }
/* 225 */               if (list.size() == 0) {
/* 226 */                 parentElement.detachNode();
/* 227 */                 return;
/*     */               }
/*     */ 
/* 230 */               SOAPElement grandpaElement = parentElement.getParentElement();
/* 231 */               listObj = parentElement.getTagName();
/* 232 */               NodeList fieldNodeList = grandpaElement.getElementsByTagName(listObj);
/* 233 */               if (fieldNodeList == null) return;
/* 234 */               int nodeCount = fieldNodeList.getLength();
/* 235 */               int listSize = list.size();
/* 236 */               int diffCount = listSize - nodeCount;
/*     */ 
/* 238 */               for (int i = 0; i < diffCount; i++) {
/* 239 */                 SOAPElement cloneElement = (SOAPElement)parentElement.cloneNode(true);
/* 240 */                 grandpaElement.addChildElement(cloneElement);
/*     */               }
/* 242 */               fieldNodeList = grandpaElement.getElementsByTagName(listObj);
/* 243 */               for (int i = 0; i < listSize; i++) {
/* 244 */                 Object item = list.get(i);
/* 245 */                 SOAPElement listElement = (SOAPElement)fieldNodeList.item(i);
/* 246 */                 SOAPElement itemElement = listElement;
/* 247 */                 if (StringUtil.isNotEmpty(elementStr))
/* 248 */                   itemElement = (SOAPElement)listElement.getElementsByTagName(elementName).item(0);
/* 249 */                 if (item == null) {
/* 250 */                   itemElement.detachNode();
/*     */                 }
/* 253 */                 else if (converter != null) {
/* 254 */                   converter.setValue(itemElement, item, klass);
/*     */                 }
/*     */                 else {
/* 257 */                   itemElement.setTextContent(item.toString());
/*     */                 }
/*     */               }
/*     */             }
/*     */ 
/*     */           }
/* 263 */           else if (converter != null) {
/* 264 */             converter.setValue(soapElement, obj, klass);
/*     */           }
/*     */           else {
/* 267 */             soapElement.setTextContent(obj.toString());
/*     */           }
/*     */         }
/*     */ 
/* 271 */         String textContext = soapElement.getTextContent();
/* 272 */         boolean hasChild = soapElement.hasChildNodes();
/*     */ 
/* 274 */         if ((StringUtil.isEmpty(textContext)) && (!hasChild))
/* 275 */           soapElement.detachNode();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 279 */         SoapUtil.logger.error("动态设值出错.", e);
/* 280 */         throw e;
/*     */       }
/*     */     }
/*     */ 
/*     */     private static void groupParams(JSONArray inputParams) {
/* 285 */       Collections.sort(inputParams, new Comparator<JSONObject>() {
/*     */         public int compare(JSONObject o1, JSONObject o2) {
/* 287 */           String name1 = o1.getString("name");
/* 288 */           String name2 = o2.getString("name");
/* 289 */           Pattern regex = Pattern.compile("^arg(\\d+)");
/* 290 */           Matcher matcher1 = regex.matcher(name1);
/* 291 */           Matcher matcher2 = regex.matcher(name2);
/* 292 */           if ((matcher1.matches()) && (matcher2.matches())) {
/* 293 */             String find1 = matcher1.group(1);
/* 294 */             String find2 = matcher2.group(1);
/* 295 */             return Integer.parseInt(find1) - Integer.parseInt(find2);
/*     */           }
/*     */ 
/* 298 */           return 0;
/*     */         }
/*     */       });
/*     */     }
/*     */ 
/*     */     private static SOAPElement createRequest(JSONArray jarray, JSONArray inputParams, String namespace, String method, Map variables, Boolean needPrefix)
/*     */       throws Exception
/*     */     {
/* 320 */       String prefix = "api";
/* 321 */       if (StringUtil.isEmpty(namespace)) {
/* 322 */         prefix = "";
/*     */       }
/* 324 */       SOAPFactory factory = SOAPFactory.newInstance();
/* 325 */       SOAPElement bodyElement = factory.createElement(method, prefix, namespace);
/*     */       Map map;
/*     */       Iterator it;
/*     */       Iterator i$;
/* 327 */       if (BeanUtils.isNotEmpty(inputParams)) {
/* 328 */         groupParams(inputParams);
/* 329 */         map = new HashMap();
/* 330 */         for (i$ = inputParams.iterator(); i$.hasNext(); ) { 
					Object obj = i$.next();
/* 331 */           JSONObject jobject = (JSONObject)obj;
/* 332 */           if (jobject != null)
/*     */           {
/* 334 */             String rootName = jobject.getString("name");
/* 335 */             SOAPElement rootElement = bodyElement.addChildElement(rootName);
/*     */ 
/* 337 */             setRequestStruct(jobject, rootElement, 1);
/*     */ 
/* 339 */             setBindingValue(jarray, rootElement, 1, rootName, variables, map);
/*     */           }
/*     */         }
/* 342 */         for (it = map.keySet().iterator(); it.hasNext(); ) {
/* 343 */           JSONObject bindingJobject = (JSONObject)it.next();
/* 344 */           SOAPElement soapElement = (SOAPElement)map.get(bindingJobject);
/* 345 */           buildSoapElementValue(soapElement, bindingJobject, variables);
/*     */         }
/*     */       }
/*     */       else {
/* 349 */         for (i$ = jarray.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 350 */           JSONObject jobject = (JSONObject)obj;
/* 351 */           if (jobject != null)
/*     */           {
/* 353 */             String paramName = jobject.getString("name");
/* 354 */             SOAPElement element = bodyElement.addChildElement(paramName);
/*     */ 
/* 356 */             buildSoapElementValue(element, jobject, variables);
/*     */           } }
/*     */       }
/* 359 */       return bodyElement;
/*     */     }
/*     */ 
/*     */     private static void setRequestStruct(JSONObject jobject, SOAPElement soapElement, int level)
/*     */       throws SOAPException
/*     */     {
/* 370 */       String paramName = jobject.getString("name");
/* 371 */       String type = jobject.getString("type");
/* 372 */       SOAPElement element = null;
/*     */ 
/* 374 */       if (level == 1) {
/* 375 */         element = soapElement;
/*     */       }
/*     */       else
/* 378 */         element = soapElement.addChildElement(paramName);
/*     */       Iterator i$;
/* 381 */       if (("bean".equals(type)) && 
/* 382 */         (jobject.containsKey("children"))) {
/* 383 */         JSONArray children = jobject.getJSONArray("children");
/* 384 */         level++;
/* 385 */         for (i$ = children.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 386 */           JSONObject childObject = (JSONObject)obj;
/* 387 */           if (childObject != null)
/*     */           {
/* 389 */             setRequestStruct(childObject, element, level);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private static void setBindingValue(JSONArray jarray, SOAPElement soapElment, int level, String rootName, Map variables, Map<JSONObject, SOAPElement> map)
/*     */       throws Exception
/*     */     {
/* 404 */       String nodeName = soapElment.getNodeName();
/* 405 */       JSONObject bindingJobject = getBindingJObject(jarray, level, rootName, nodeName);
/* 406 */       Iterator it = soapElment.getChildElements();
/* 407 */       level++;
/* 408 */       if (bindingJobject == null) {
/* 409 */         if (!it.hasNext()) {
/* 410 */           soapElment.detachNode();
/*     */         }
/*     */         else {
/* 413 */           while (it.hasNext()) {
/* 414 */             SOAPElement child = (SOAPElement)it.next();
/* 415 */             setBindingValue(jarray, child, level, rootName + "." + child.getNodeName(), variables, map);
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/* 420 */         map.put(bindingJobject, soapElment);
/*     */     }
/*     */ 
/*     */     private static JSONObject getBindingJObject(JSONArray jarray, int level, String rootName, String nodeName)
/*     */     {
/* 433 */       JSONObject reJobject = null;
/* 434 */       for (Iterator i$ = jarray.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 435 */         JSONObject jobject = (JSONObject)obj;
/* 436 */         if (jobject != null)
/*     */         {
/* 438 */           String paramName = jobject.getString("name");
/* 439 */           if (paramName.equals(nodeName)) {
/* 440 */             String fullpath = "";
/* 441 */             if (jobject.containsKey("fullpath")) {
/* 442 */               fullpath = jobject.getString("fullpath");
/*     */             }
/* 444 */             if (StringUtil.isNotEmpty(fullpath))
/*     */             {
/* 446 */               if (fullpath.equals(rootName))
/* 447 */                 reJobject = jobject;
/*     */             }
/*     */             else
/*     */             {
/* 451 */               List pathAry = Arrays.asList(jobject.getString("bindingVal").split("\\."));
/*     */ 
/* 453 */               if (level == pathAry.size())
/* 454 */                 reJobject = jobject;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 459 */       return reJobject;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class InvokeException extends Exception
/*     */   {
/*     */     private String code;
/*     */     private String msg;
/*     */ 
/*     */     public InvokeException(String code, String msg)
/*     */     {
/*  89 */       this(code, msg, null);
/*     */     }
/*     */ 
/*     */     public InvokeException(String code, String msg, Throwable e) {
/*  93 */       super(e);
/*  94 */       this.code = code;
/*  95 */       this.msg = msg;
/*     */     }
/*     */ 
/*     */     public String getCode() {
/*  99 */       return this.code;
/*     */     }
/*     */ 
/*     */     public String getMsg() {
/* 103 */       return this.msg;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.SoapUtil
 * JD-Core Version:    0.6.2
 */