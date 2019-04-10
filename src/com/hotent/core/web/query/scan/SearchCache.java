/*     */ package com.hotent.core.web.query.scan;
/*     */ 
/*     */ import com.hotent.core.annotion.query.Table;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public class SearchCache
/*     */   implements InitializingBean
/*     */ {
/*  30 */   public Logger logger = LoggerFactory.getLogger(SearchCache.class);
/*     */ 
/*  35 */   private static Map<String, TableEntity> tableEntityMap = new HashMap();
/*     */ 
/*  40 */   private static Map<String, TableEntity> displayTagIdMap = new HashMap();
/*     */ 
/*  45 */   private static Map<String, TableEntity> tableVarMap = new HashMap();
/*     */   private Resource[] basePackage;
/*     */ 
/*     */   public void constractSearchTableList()
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  51 */     this.logger.debug("开始扫描元数据..");
/*  52 */     Long start = Long.valueOf(System.currentTimeMillis());
/*     */ 
/*  54 */     List<Class<?>> tableList = TableScaner.findTableScan(this.basePackage);
/*     */ 
/*  56 */     this.logger.debug("扫描结束,共耗时:" + (System.currentTimeMillis() - start.longValue()) + "毫秒");
/*     */ 
/*  58 */     for (Class cls : tableList) {
/*  59 */       Table table = (Table)cls.getAnnotation(Table.class);
/*  60 */       TableEntity tableEntity = new TableEntity(cls.getSimpleName(), table);
/*  61 */       java.lang.reflect.Field[] fields = cls.getDeclaredFields();
/*  62 */       for (java.lang.reflect.Field field : fields) {
/*  63 */         com.hotent.core.annotion.query.Field qField = (com.hotent.core.annotion.query.Field)field.getAnnotation(com.hotent.core.annotion.query.Field.class);
/*     */ 
/*  65 */         if (qField != null) {
/*  66 */           TableField tableField = new TableField(field, qField);
/*  67 */           tableEntity.getTableFieldList().add(tableField);
/*     */         }
/*     */       }
/*  70 */       tableEntityMap.put(tableEntity.getTableName(), tableEntity);
/*     */ 
/*  72 */       if (StringUtils.isNotEmpty(tableEntity.getDisplayTagId())) {
/*  73 */         displayTagIdMap.put(tableEntity.getDisplayTagId(), tableEntity);
/*     */       }
/*  75 */       if (StringUtils.isNotEmpty(tableEntity.getVar())) {
/*  76 */         tableVarMap.put(tableEntity.getVar(), tableEntity);
/*     */       }
/*     */     }
/*  79 */     Collection<TableEntity> list = tableEntityMap.values();
/*  80 */     for (TableEntity sub : list)
/*  81 */       if (!sub.isPrimary())
/*     */       {
/*  84 */         String mainTable = sub.getPrimaryTable();
/*  85 */         if (StringUtils.isNotEmpty(mainTable)) {
/*  86 */           TableEntity primaryTable = (TableEntity)tableEntityMap.get(mainTable);
/*  87 */           primaryTable.addSubTable(sub);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet() throws Exception
/*     */   {
/*  94 */     constractSearchTableList();
/*     */   }
/*     */ 
/*     */   public Resource[] getBasePackage() {
/*  98 */     return this.basePackage;
/*     */   }
/*     */ 
/*     */   public void setBasePackage(Resource[] basePackage) {
/* 102 */     this.basePackage = basePackage;
/*     */   }
/*     */ 
/*     */   public static Map<String, TableEntity> getTableEntityMap() {
/* 106 */     return tableEntityMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, TableEntity> getDisplayTagIdMap() {
/* 110 */     return displayTagIdMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, TableEntity> getTableVarMap() {
/* 114 */     return tableVarMap;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.scan.SearchCache
 * JD-Core Version:    0.6.2
 */