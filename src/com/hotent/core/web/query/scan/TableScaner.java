/*    */ package com.hotent.core.web.query.scan;
/*    */ 
/*    */ import com.hotent.core.annotion.query.Table;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.core.io.Resource;
/*    */ import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*    */ import org.springframework.core.io.support.ResourcePatternResolver;
/*    */ import org.springframework.core.type.ClassMetadata;
/*    */ import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
/*    */ import org.springframework.core.type.classreading.MetadataReader;
/*    */ import org.springframework.core.type.classreading.MetadataReaderFactory;
/*    */ 
/*    */ public class TableScaner
/*    */ {
/*    */   public static List<Class<?>> findTableScan(Resource[] basePackage)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 29 */     ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
/* 30 */     MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
/*    */ 
/* 32 */     List candidates = new ArrayList();
/* 33 */     if (basePackage == null)
/* 34 */       return candidates;
/* 35 */     for (Resource resource : basePackage) {
/* 36 */       if (resource.isReadable())
/*    */       {
/* 38 */         MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
/*    */ 
/* 40 */         if (isCandidate(metadataReader))
/* 41 */           candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
/*    */       }
/*    */     }
/* 44 */     return candidates;
/*    */   }
/*    */ 
/*    */   private static boolean isCandidate(MetadataReader metadataReader)
/*    */     throws ClassNotFoundException
/*    */   {
/*    */     try
/*    */     {
/* 58 */       Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
/*    */ 
/* 60 */       if (c.getAnnotation(Table.class) != null)
/* 61 */         return true;
/*    */     } catch (Throwable e) {
/*    */     }
/* 64 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.scan.TableScaner
 * JD-Core Version:    0.6.2
 */