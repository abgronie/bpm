/*    */ package com.hotent.core.fulltext;
/*    */ 
/*    */ import com.hotent.core.fulltext.impl.PdfImpl;
/*    */ import com.hotent.core.fulltext.impl.TextImpl;
/*    */ import com.hotent.core.fulltext.impl.Word2007Impl;
/*    */ import com.hotent.core.fulltext.impl.WordImpl;
/*    */ 
/*    */ public class DocFactory
/*    */ {
/*    */   public IDocument getDoc(String fileName)
/*    */   {
/* 11 */     IDocument doc = null;
/* 12 */     fileName = fileName.toLowerCase();
/* 13 */     if (fileName.endsWith("doc")) {
/* 14 */       doc = new WordImpl();
/*    */     }
/* 16 */     else if (fileName.endsWith("docx")) {
/* 17 */       doc = new Word2007Impl();
/*    */     }
/* 19 */     else if (fileName.endsWith("txt")) {
/* 20 */       doc = new TextImpl();
/*    */     }
/* 22 */     else if (fileName.endsWith("pdf")) {
/* 23 */       doc = new PdfImpl();
/*    */     }
/* 25 */     if (doc != null) {
/* 26 */       doc.setFileName(fileName);
/*    */     }
/* 28 */     return doc;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.DocFactory
 * JD-Core Version:    0.6.2
 */