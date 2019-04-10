/*    */ package com.hotent.core.fulltext.impl;
/*    */ 
/*    */ import com.hotent.core.fulltext.IDocument;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.pdfparser.PDFParser;
/*    */ import org.apache.pdfbox.pdmodel.PDDocument;
/*    */ import org.apache.pdfbox.util.PDFTextStripper;
/*    */ 
/*    */ public class PdfImpl
/*    */   implements IDocument
/*    */ {
/* 17 */   private String fileName = "";
/*    */ 
/*    */   public void setFileName(String fileName)
/*    */   {
/* 21 */     this.fileName = fileName;
/*    */   }
/*    */ 
/*    */   public String extract()
/*    */   {
/* 26 */     String result = null;
/* 27 */     FileInputStream is = null;
/* 28 */     PDDocument document = null;
/*    */     try {
/* 30 */       is = new FileInputStream(this.fileName);
/* 31 */       PDFParser parser = new PDFParser(is);
/* 32 */       parser.parse();
/* 33 */       document = parser.getPDDocument();
/* 34 */       PDFTextStripper stripper = new PDFTextStripper();
/* 35 */       result = stripper.getText(document);
/*    */     }
/*    */     catch (FileNotFoundException e) {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */     catch (IOException e) {
/* 41 */       e.printStackTrace();
/*    */     } finally {
/* 43 */       if (is != null) {
/*    */         try {
/* 45 */           is.close();
/*    */         }
/*    */         catch (IOException e) {
/* 48 */           e.printStackTrace();
/*    */         }
/*    */       }
/* 51 */       if (document != null) {
/*    */         try {
/* 53 */           document.close();
/*    */         }
/*    */         catch (IOException e) {
/* 56 */           e.printStackTrace();
/*    */         }
/*    */       }
/*    */     }
/* 60 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.impl.PdfImpl
 * JD-Core Version:    0.6.2
 */