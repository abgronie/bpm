/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringReader;
/*     */ import javax.xml.bind.JAXBContext;
/*     */ import javax.xml.bind.JAXBException;
/*     */ import javax.xml.bind.Marshaller;
/*     */ import javax.xml.bind.PropertyException;
/*     */ import javax.xml.bind.Unmarshaller;
/*     */ import javax.xml.transform.sax.SAXResult;
/*     */ import org.apache.xml.serialize.OutputFormat;
/*     */ import org.apache.xml.serialize.XMLSerializer;
/*     */ 
/*     */ public class XmlBeanUtil
/*     */ {
/*     */   public static Object unmarshall(String xml, Class clsToUnbound)
/*     */     throws JAXBException
/*     */   {
/*  33 */     JAXBContext jc = JAXBContext.newInstance(new Class[] { clsToUnbound });
/*  34 */     return unmarshall(jc, xml);
/*     */   }
/*     */ 
/*     */   public static String marshall(Object serObj, Class clsToBound)
/*     */     throws JAXBException
/*     */   {
/*  45 */     JAXBContext jc = JAXBContext.newInstance(new Class[] { clsToBound });
/*  46 */     return marshall(jc, serObj);
/*     */   }
/*     */ 
/*     */   public static String marshall(Object serObj, Class clsToBound, String[] cdataEls) throws JAXBException {
/*  50 */     JAXBContext jc = JAXBContext.newInstance(new Class[] { clsToBound });
/*  51 */     return marshall(jc, serObj, cdataEls);
/*     */   }
/*     */ 
/*     */   public static Object unmarshall(InputStream is, Class<?> clsToUnbound)
/*     */     throws JAXBException
/*     */   {
/*  62 */     JAXBContext jc = JAXBContext.newInstance(new Class[] { clsToUnbound });
/*  63 */     return unmarshall(jc, is);
/*     */   }
/*     */ 
/*     */   private static Object unmarshall(JAXBContext jc, InputStream is)
/*     */     throws JAXBException
/*     */   {
/*  75 */     Unmarshaller u = jc.createUnmarshaller();
/*  76 */     return u.unmarshal(is);
/*     */   }
/*     */ 
/*     */   private static Object unmarshall(JAXBContext jc, String xml)
/*     */     throws JAXBException
/*     */   {
/*  88 */     Unmarshaller u = jc.createUnmarshaller();
/*  89 */     return u.unmarshal(new StringReader(xml));
/*     */   }
/*     */ 
/*     */   private static String marshall(JAXBContext jc, Object serObj)
/*     */     throws JAXBException, PropertyException
/*     */   {
/* 101 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 102 */     Marshaller m = jc.createMarshaller();
/*     */ 
/* 104 */     m.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/* 105 */     m.setProperty("jaxb.encoding", System.getProperty("file.encoding"));
/* 106 */     m.marshal(serObj, out);
/*     */ 
/* 109 */     return out.toString();
/*     */   }
/*     */ 
/*     */   private static String marshall(JAXBContext jc, Object serObj, String[] cdataEls) throws JAXBException, PropertyException
/*     */   {
/* 114 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 115 */     Marshaller m = jc.createMarshaller();
/*     */ 
/* 117 */     m.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/* 118 */     m.setProperty("jaxb.encoding", System.getProperty("file.encoding"));
/*     */ 
/* 121 */     XMLSerializer serializer = getXMLSerializer(cdataEls);
/* 122 */     serializer.setOutputByteStream(out);
/*     */ 
/* 126 */     SAXResult result = null;
/*     */     try {
/* 128 */       result = new SAXResult(serializer.asContentHandler());
/*     */     } catch (IOException e) {
/* 130 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 133 */     m.marshal(serObj, result);
/*     */ 
/* 135 */     return out.toString();
/*     */   }
/*     */ 
/*     */   private static XMLSerializer getXMLSerializer(String[] aryProperty) {
/* 139 */     OutputFormat of = new OutputFormat();
/* 140 */     of.setCDataElements(aryProperty);
/*     */ 
/* 142 */     of.setPreserveSpace(true);
/* 143 */     of.setIndenting(true);
/*     */ 
/* 145 */     XMLSerializer serializer = new XMLSerializer(of);
/*     */ 
/* 147 */     return serializer;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.XmlBeanUtil
 * JD-Core Version:    0.6.2
 */