/*    */ package com.hotent.core.fulltext.impl;
/*    */ 
/*    */ import com.hotent.core.fulltext.IDocument;
/*    */ import java.io.IOException;
/*    */ import org.apache.poi.POIXMLDocument;
/*    */ import org.apache.poi.POIXMLTextExtractor;
/*    */ import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
/*    */ import org.apache.poi.openxml4j.opc.OPCPackage;
/*    */ import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
/*    */ import org.apache.xmlbeans.XmlException;
/*    */ 
/*    */ public class Word2007Impl
/*    */   implements IDocument
/*    */ {
/* 18 */   private String fileName = "";
/*    */ 
/*    */   public void setFileName(String fileName) {
/* 21 */     this.fileName = fileName;
/*    */   }
/*    */ 
/*    */   public String extract()
/*    */   {
/* 27 */     String str = "";
/*    */     try {
/* 29 */       OPCPackage opcPackage = POIXMLDocument.openPackage(this.fileName);
/*    */       try
/*    */       {
/* 32 */         POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
/* 33 */         str = extractor.getText();
/*    */       }
/*    */       catch (XmlException e) {
/* 36 */         e.printStackTrace();
/*    */       }
/*    */       catch (OpenXML4JException e) {
/* 39 */         e.printStackTrace();
/*    */       }
/*    */ 
/* 42 */       return str;
/*    */     }
/*    */     catch (IOException e) {
/* 45 */       e.printStackTrace();
/*    */     }
/* 47 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.impl.Word2007Impl
 * JD-Core Version:    0.6.2
 */