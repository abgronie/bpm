/*    */ package com.hotent.core.fulltext.impl;
/*    */ 
/*    */ import com.hotent.core.fulltext.IDocument;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import org.apache.poi.hwpf.extractor.WordExtractor;
/*    */ 
/*    */ public class WordImpl
/*    */   implements IDocument
/*    */ {
/* 17 */   private String fileName = "";
/*    */ 
/*    */   public void setFileName(String fileName) {
/* 20 */     this.fileName = fileName;
/*    */   }
/*    */ 
/*    */   public String extract()
/*    */   {
/* 25 */     String str = "";
/* 26 */     FileInputStream in = null;
/*    */     try {
/* 28 */       in = new FileInputStream(this.fileName);
/* 29 */       WordExtractor extractor = new WordExtractor(in);
/* 30 */       str = extractor.getText();
/*    */     }
/*    */     catch (FileNotFoundException e) {
/* 33 */       e.printStackTrace();
/*    */     } catch (Exception e) {
/*    */     }
/*    */     finally {
/* 37 */       if (in != null) {
/*    */         try {
/* 39 */           in.close();
/*    */         }
/*    */         catch (IOException e) {
/* 42 */           e.printStackTrace();
/*    */         }
/*    */       }
/*    */     }
/* 46 */     return str;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.impl.WordImpl
 * JD-Core Version:    0.6.2
 */