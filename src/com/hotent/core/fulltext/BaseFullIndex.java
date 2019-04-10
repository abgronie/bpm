/*     */ package com.hotent.core.fulltext;
/*     */ 
/*     */ import com.hotent.core.page.PageBean;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.TokenStream;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.IndexWriterConfig;
/*     */ import org.apache.lucene.index.IndexWriterConfig.OpenMode;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.queryParser.ParseException;
/*     */ import org.apache.lucene.queryParser.QueryParser;
/*     */ import org.apache.lucene.search.IndexSearcher;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.ScoreDoc;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.search.highlight.Highlighter;
/*     */ import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
/*     */ import org.apache.lucene.search.highlight.QueryScorer;
/*     */ import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.FSDirectory;
/*     */ import org.apache.lucene.store.LockObtainFailedException;
/*     */ import org.apache.lucene.store.RAMDirectory;
/*     */ import org.apache.lucene.util.Version;
/*     */ import org.wltea.analyzer.lucene.IKAnalyzer;
/*     */ import org.wltea.analyzer.lucene.IKQueryParser;
/*     */ import org.wltea.analyzer.lucene.IKSimilarity;
/*     */ 
/*     */ public class BaseFullIndex
/*     */ {
/*  51 */   private Log log = LogFactory.getLog(BaseFullIndex.class);
/*     */ 
/*  53 */   private String indexDir = "E:/temp/index";
/*  54 */   private String pkName = "id";
/*  55 */   private Analyzer analyzer = new IKAnalyzer();
/*     */   private IOperator indexOperator;
/*  62 */   private int maxResult = 400;
/*     */ 
/*     */   public void setMaxResult(int size)
/*     */   {
/*  70 */     this.maxResult = size;
/*     */   }
/*     */ 
/*     */   public void setIndexDir(String dir)
/*     */   {
/*  78 */     this.indexDir = dir;
/*     */   }
/*     */ 
/*     */   public void setPkName(String pkName)
/*     */   {
/*  87 */     this.pkName = pkName;
/*     */   }
/*     */ 
/*     */   public void setIndexOperator(IOperator indexOperator) {
/*  91 */     this.indexOperator = indexOperator;
/*     */   }
/*     */ 
/*     */   public void indexAll()
/*     */     throws IOException
/*     */   {
/* 103 */     Directory fsDir = FSDirectory.open(new File(this.indexDir));
/*     */ 
/* 106 */     Directory ramDir = new RAMDirectory(fsDir);
/*     */ 
/* 108 */     IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33, this.analyzer);
/*     */ 
/* 110 */     config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
/* 111 */     IndexWriter ramWrite = new IndexWriter(ramDir, config);
/*     */ 
/* 113 */     this.indexOperator.addDocument(ramWrite);
/* 114 */     ramWrite.close();
/*     */ 
/* 116 */     IndexWriterConfig fsConfig = new IndexWriterConfig(Version.LUCENE_33, this.analyzer);
/*     */ 
/* 118 */     fsConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
/* 119 */     IndexWriter fsWriter = new IndexWriter(fsDir, fsConfig);
/*     */ 
/* 121 */     fsWriter.addIndexes(new Directory[] { ramDir });
/* 122 */     fsWriter.commit();
/*     */ 
/* 124 */     fsWriter.optimize();
/* 125 */     fsWriter.close();
/* 126 */     fsDir.close();
/*     */   }
/*     */ 
/*     */   public void delById(String id)
/*     */     throws CorruptIndexException, IOException
/*     */   {
/* 137 */     File file = new File(this.indexDir);
/* 138 */     Directory fsDir = FSDirectory.open(file);
/*     */ 
/* 140 */     IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33, this.analyzer);
/* 141 */     config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
/* 142 */     IndexWriter write = new IndexWriter(fsDir, config);
/* 143 */     Term term = new Term(this.pkName, id);
/* 144 */     write.deleteDocuments(term);
/* 145 */     write.close();
/*     */   }
/*     */ 
/*     */   public void updDoc(String pkId, Document doc)
/*     */     throws CorruptIndexException, LockObtainFailedException, IOException
/*     */   {
/* 160 */     File file = new File(this.indexDir);
/* 161 */     Directory fsDir = FSDirectory.open(file);
/*     */ 
/* 163 */     IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33, this.analyzer);
/* 164 */     config.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
/* 165 */     IndexWriter write = new IndexWriter(fsDir, config);
/* 166 */     Term term = new Term(this.pkName, pkId);
/* 167 */     write.updateDocument(term, doc);
/* 168 */     write.close();
/*     */   }
/*     */ 
/*     */   public void addDoc(Document doc)
/*     */     throws IOException
/*     */   {
/* 178 */     Directory fsDir = FSDirectory.open(new File(this.indexDir));
/* 179 */     IndexWriterConfig fsConfig = new IndexWriterConfig(Version.LUCENE_33, this.analyzer);
/* 180 */     fsConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
/* 181 */     IndexWriter fsWriter = new IndexWriter(fsDir, fsConfig);
/* 182 */     fsWriter.addDocument(doc);
/* 183 */     fsWriter.close();
/*     */   }
/*     */ 
/*     */   public String heightLight(String q, String content)
/*     */     throws ParseException, IOException, InvalidTokenOffsetsException
/*     */   {
/* 200 */     QueryParser parser = new QueryParser(Version.LUCENE_30, "field", this.analyzer);
/*     */ 
/* 202 */     Query query = parser.parse(q);
/* 203 */     SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
/* 204 */     Highlighter highlight = new Highlighter(formatter, new QueryScorer(query));
/* 205 */     TokenStream tokens = this.analyzer.tokenStream("field", new StringReader(content));
/* 206 */     String str = highlight.getBestFragment(tokens, content);
/* 207 */     if (str == null)
/*     */     {
/* 209 */       if (content.length() > 50)
/* 210 */         return content.substring(0, 50);
/* 211 */       return content;
/*     */     }
/*     */ 
/* 214 */     return str;
/*     */   }
/*     */ 
/*     */   public List getPage(String q, int currentPage, int pageSize)
/*     */   {
/*     */     try
/*     */     {
/* 230 */       PageBean pageBean = new PageBean(currentPage, pageSize);
/* 231 */       File file = new File(this.indexDir);
/* 232 */       Directory fsdir = FSDirectory.open(file);
/* 233 */       Directory dir = new RAMDirectory(fsdir);
/* 234 */       IndexSearcher search = new IndexSearcher(dir);
/* 235 */       search.setSimilarity(new IKSimilarity());
/* 236 */       String[] fields = this.indexOperator.getFields();
/* 237 */       Query query = IKQueryParser.parseMultiField(fields, q);
/* 238 */       TopDocs hits = search.search(query, this.maxResult);
/* 239 */       int totalRecord = hits.totalHits;
/* 240 */       int amount = hits.scoreDocs.length;
/*     */ 
/* 243 */       pageBean.setTotalCount(totalRecord);
/*     */ 
/* 245 */       if (hits == null) {
/* 246 */         return null;
/*     */       }
/* 248 */       int start = (currentPage - 1) * pageSize;
/* 249 */       int end = currentPage * pageSize;
/* 250 */       List list = new ArrayList();
/* 251 */       for (int i = 0; i < totalRecord; i++) {
/* 252 */         if ((i >= start) && (i < end)) {
/* 253 */           Document doc = search.doc(hits.scoreDocs[i].doc);
/* 254 */           list.add(doc);
/*     */         }
/*     */       }
/*     */ 
/* 258 */       return list;
/*     */     } catch (Exception e) {
/* 260 */       this.log.error(e.getMessage());
/* 261 */     }return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.fulltext.BaseFullIndex
 * JD-Core Version:    0.6.2
 */