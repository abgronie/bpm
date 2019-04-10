/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import javax.xml.validation.Schema;
/*     */ import javax.xml.validation.SchemaFactory;
/*     */ import javax.xml.validation.Validator;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.DocumentResult;
/*     */ import org.dom4j.io.DocumentSource;
/*     */ import org.dom4j.io.OutputFormat;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.dom4j.io.SAXValidator;
/*     */ import org.dom4j.io.XMLWriter;
/*     */ import org.dom4j.util.XMLErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class Dom4jUtil
/*     */ {
/*  55 */   private static final Log logger = LogFactory.getLog(Dom4jUtil.class);
/*     */ 
/*     */   public static Document loadXml(String s)
/*     */   {
/*  63 */     Document document = null;
/*     */     try
/*     */     {
/*  66 */       document = DocumentHelper.parseText(s);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*  70 */       ex.printStackTrace();
/*     */     }
/*  72 */     return document;
/*     */   }
/*     */ 
/*     */   public static Document load(String filename, String encode)
/*     */   {
/*  81 */     Document document = null;
/*     */     try {
/*  83 */       SAXReader saxReader = new SAXReader();
/*  84 */       saxReader.setEncoding(encode);
/*  85 */       document = saxReader.read(new File(filename));
/*     */     }
/*     */     catch (Exception ex) {
/*     */     }
/*  89 */     return document;
/*     */   }
/*     */ 
/*     */   public static Document loadXml(String xml, String encode)
/*     */     throws UnsupportedEncodingException
/*     */   {
/*  99 */     ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes(encode));
/* 100 */     return loadXml(inputStream, encode);
/*     */   }
/*     */ 
/*     */   public static Document loadXml(InputStream is)
/*     */   {
/* 110 */     return loadXml(is, "utf-8");
/*     */   }
/*     */ 
/*     */   public static Document loadXml(InputStream is, String charset) {
/* 114 */     Document document = null;
/*     */     try {
/* 116 */       SAXReader reader = new SAXReader();
/* 117 */       reader.setEncoding(charset);
/* 118 */       document = reader.read(is);
/*     */     } catch (Exception ex) {
/* 120 */       ex.printStackTrace();
/*     */     }
/* 122 */     return document;
/*     */   }
/*     */ 
/*     */   public static void write(Document document, String fileName)
/*     */     throws IOException
/*     */   {
/* 134 */     String xml = document.asXML();
/* 135 */     FileUtil.writeFile(fileName, xml);
/*     */   }
/*     */ 
/*     */   public static void write(String str, String fileName)
/*     */     throws IOException, DocumentException
/*     */   {
/* 146 */     Document document = DocumentHelper.parseText(str);
/* 147 */     write(document, fileName);
/*     */   }
/*     */ 
/*     */   public Document load(URL url)
/*     */     throws DocumentException
/*     */   {
/* 157 */     SAXReader reader = new SAXReader();
/* 158 */     Document document = reader.read(url);
/* 159 */     return document;
/*     */   }
/*     */ 
/*     */   public static Document load(String filename)
/*     */   {
/* 168 */     Document document = null;
/*     */     try {
/* 170 */       SAXReader reader = new SAXReader();
/* 171 */       document = reader.read(new File(filename));
/* 172 */       document.normalize();
/*     */     } catch (Exception ex) {
/* 174 */       ex.printStackTrace();
/*     */     }
/* 176 */     return document;
/*     */   }
/*     */ 
/*     */   public static String transFormXsl(String xml, String xsl, Map<String, String> map)
/*     */     throws Exception
/*     */   {
/* 190 */     StringReader xmlReader = new StringReader(xml);
/* 191 */     StringReader xslReader = new StringReader(xsl);
/* 192 */     System.setProperty("javax.xml.transform.TransformerFactory", "org.apache.xalan.processor.TransformerFactoryImpl");
/*     */ 
/* 194 */     TransformerFactory factory = TransformerFactory.newInstance();
/* 195 */     Transformer transformer = factory.newTransformer(new StreamSource(xslReader));
/* 196 */     if (map != null)
/*     */     {
/* 198 */       Iterator it = map.entrySet().iterator();
/* 199 */       while (it.hasNext()) {
/* 200 */         Map.Entry obj = (Map.Entry)it.next();
/* 201 */         transformer.setParameter((String)obj.getKey(), obj.getValue());
/*     */       }
/*     */     }
/* 204 */     StreamSource xmlSource = new StreamSource(xmlReader);
/*     */ 
/* 206 */     StringWriter writer = new StringWriter();
/* 207 */     Result result = new StreamResult(writer);
/* 208 */     transformer.transform(xmlSource, result);
/*     */ 
/* 210 */     return writer.toString();
/*     */   }
/*     */ 
/*     */   public static String transXmlByXslt(String xml, String xslPath, Map<String, String> map) throws Exception {
/* 214 */     Document document = loadXml(xml);
/* 215 */     document.setXMLEncoding("UTF-8");
/*     */ 
/* 217 */     Document result = styleDocument(document, xslPath, map);
/*     */ 
/* 219 */     return docToString(result);
/*     */   }
/*     */ 
/*     */   public static String transFileXmlByXslt(String xmlPath, String xslPath, Map<String, String> map) throws Exception {
/* 223 */     Document document = load(xmlPath);
/* 224 */     document.setXMLEncoding("UTF-8");
/*     */ 
/* 226 */     Document result = styleDocument(document, xslPath, map);
/*     */ 
/* 228 */     return docToString(result);
/*     */   }
/*     */ 
/*     */   public static String docToString(Document document)
/*     */   {
/* 238 */     String s = "";
/*     */     try {
/* 240 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 241 */       OutputFormat format = new OutputFormat("  ", true, "UTF-8");
/* 242 */       XMLWriter writer = new XMLWriter(out, format);
/* 243 */       writer.write(document);
/* 244 */       s = out.toString("UTF-8");
/*     */     } catch (Exception ex) {
/* 246 */       logger.error("docToString error:" + ex.getMessage());
/*     */     }
/* 248 */     return s;
/*     */   }
/*     */ 
/*     */   public static String docToPrettyString(Document document)
/*     */   {
/* 257 */     return docToPrettyString(document, true);
/*     */   }
/*     */ 
/*     */   public static String docToPrettyString(Document document, boolean removeHead)
/*     */   {
/* 267 */     String result = "";
/*     */     try {
/* 269 */       Writer writer = new StringWriter();
/* 270 */       OutputFormat format = OutputFormat.createPrettyPrint();
/* 271 */       format.setSuppressDeclaration(removeHead);
/* 272 */       XMLWriter xmlWriter = new XMLWriter(writer, format);
/* 273 */       xmlWriter.write(document);
/* 274 */       result = writer.toString();
/*     */     }
/*     */     catch (Exception ex) {
/* 277 */       logger.error("docToString error:" + ex.getMessage());
/*     */     }
/* 279 */     return result;
/*     */   }
/*     */ 
/*     */   public static Document styleDocument(Document document, String stylesheet, Map<String, String> map)
/*     */     throws Exception
/*     */   {
/* 292 */     System.setProperty("javax.xml.transform.TransformerFactory", "org.apache.xalan.processor.TransformerFactoryImpl");
/* 293 */     TransformerFactory factory = TransformerFactory.newInstance();
/* 294 */     Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
/*     */ 
/* 297 */     if (map != null)
/*     */     {
/* 299 */       Iterator it = map.entrySet().iterator();
/* 300 */       while (it.hasNext()) {
/* 301 */         Map.Entry obj = (Map.Entry)it.next();
/* 302 */         transformer.setParameter((String)obj.getKey(), obj.getValue());
/*     */       }
/*     */     }
/*     */ 
/* 306 */     DocumentSource source = new DocumentSource(document);
/* 307 */     DocumentResult result = new DocumentResult();
/* 308 */     transformer.transform(source, result);
/*     */ 
/* 311 */     Document transformedDoc = result.getDocument();
/* 312 */     return transformedDoc;
/*     */   }
/*     */ 
/*     */   public static String validXmlBySchema(String xml, String schema)
/*     */   {
/* 324 */     String result = "";
/*     */     try
/*     */     {
/* 327 */       XMLErrorHandler errorHandler = new XMLErrorHandler();
/*     */ 
/* 329 */       SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */ 
/* 331 */       factory.setValidating(true);
/*     */ 
/* 333 */       factory.setNamespaceAware(true);
/*     */ 
/* 335 */       SAXParser parser = factory.newSAXParser();
/*     */ 
/* 337 */       SAXReader xmlReader = new SAXReader();
/*     */ 
/* 339 */       Document xmlDocument = xmlReader.read(new File(xml));
/*     */ 
/* 342 */       parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
/* 343 */       parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + schema);
/*     */ 
/* 345 */       SAXValidator validator = new SAXValidator(parser.getXMLReader());
/*     */ 
/* 347 */       validator.setErrorHandler(errorHandler);
/*     */ 
/* 349 */       validator.validate(xmlDocument);
/* 350 */       XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
/*     */ 
/* 352 */       if (errorHandler.getErrors().hasContent()) {
/* 353 */         result = "<result success='0'>XML文件通过XSD文件校验失败,请检查xml是否符合指定格式!</result>";
/*     */       }
/*     */       else
/* 356 */         result = "<result success='1'>XML文件通过XSD文件校验成功!</result>";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 360 */       result = "<result success='0'>XML文件通过XSD文件校验失败:" + ex.getMessage() + "</result>";
/*     */     }
/* 362 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean validByXsd(String xsdPath, InputStream xmlData)
/*     */   {
/* 373 */     SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
/*     */ 
/* 377 */     File schemaFile = new File(xsdPath);
/*     */ 
/* 380 */     Schema schema = null;
/*     */     try {
/* 382 */       schema = schemaFactory.newSchema(schemaFile);
/*     */     } catch (SAXException e) {
/* 384 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 387 */     Validator validator = schema.newValidator();
/*     */ 
/* 390 */     Source source = new StreamSource(xmlData);
/*     */     try
/*     */     {
/* 394 */       validator.validate(source);
/*     */     } catch (Exception ex) {
/* 396 */       logger.info(ex.getMessage());
/* 397 */       return false;
/*     */     }
/* 399 */     return true;
/*     */   }
/*     */ 
/*     */   public static String getString(Element element, String attrName)
/*     */   {
/* 409 */     return getString(element, attrName, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public static String getString(Element element, String attrName, Boolean fuzzy)
/*     */   {
/* 420 */     if (BeanUtils.isEmpty(element)) return null;
/* 421 */     String val = element.attributeValue(attrName);
/* 422 */     if (StringUtil.isEmpty(val)) return null;
/* 423 */     if (fuzzy.booleanValue()) {
/* 424 */       val = "%" + val + "%";
/*     */     }
/* 426 */     return val;
/*     */   }
/*     */ 
/*     */   public static void addAttribute(Element element, String attrName, Object val)
/*     */   {
/* 436 */     addAttribute(element, attrName, val, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static void addAttribute(Element element, String attrName, Object val, String format)
/*     */   {
/* 447 */     if (BeanUtils.isEmpty(val)) return;
/* 448 */     if ((val instanceof Date)) {
/* 449 */       String dateStr = TimeUtil.getDateTimeString((Date)val, format);
/* 450 */       element.addAttribute(attrName, dateStr);
/*     */     }
/*     */     else {
/* 453 */       element.addAttribute(attrName, val.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.Dom4jUtil
 * JD-Core Version:    0.6.2
 */