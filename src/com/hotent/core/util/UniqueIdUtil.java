/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import org.springframework.dao.EmptyResultDataAccessException;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class UniqueIdUtil
/*     */ {
/*  17 */   private static long adjust = 1L;
/*     */ 
/*  21 */   private static long nextId = 0L;
/*     */ 
/*  25 */   private static long lastId = -1L;
/*     */   private static JdbcTemplate jdbcTemplate;
/*  29 */   private static boolean hasError = false;
/*     */ 
/*     */   private static void init()
/*     */   {
/*     */     try
/*     */     {
/*  36 */       jdbcTemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplateSn");
/*  37 */       String path = FileUtil.getClassesPath() + "conf/app.properties".replace("/", File.separator);
/*  38 */       String strAdjust = FileUtil.readFromProperties(path, "genId.adjust");
/*  39 */       if (strAdjust != null)
/*  40 */         adjust = Integer.parseInt(strAdjust);
/*     */     }
/*     */     catch (Exception ex) {
/*  43 */       adjust = 1L;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void getNextIdBlock()
/*     */   {
/*  54 */     if (jdbcTemplate == null) {
/*  55 */       init();
/*     */     }
/*  57 */     Long bound = Long.valueOf(-1L);
/*  58 */     Integer incremental = Integer.valueOf(-1);
/*  59 */     String sql = "SELECT bound,incremental FROM SYS_DB_ID T WHERE T.ID=?";
/*  60 */     String upSql = "UPDATE SYS_DB_ID  SET BOUND=? WHERE ID=?";
/*     */     try {
/*  62 */       Map map = jdbcTemplate.queryForMap(sql, new Object[] { Long.valueOf(adjust) });
/*  63 */       bound = Long.valueOf(Long.parseLong(map.get("bound").toString()));
/*  64 */       incremental = Integer.valueOf(Integer.parseInt(map.get("incremental").toString()));
/*     */ 
/*  66 */       if (hasError) {
/*  67 */         lastId = nextId + incremental.intValue();
/*     */       }
/*     */       else {
/*  70 */         nextId = bound.longValue();
/*  71 */         lastId = bound.longValue() + incremental.intValue();
/*     */       }
/*  73 */       jdbcTemplate.update(upSql, new Object[] { Long.valueOf(lastId), Long.valueOf(adjust) });
/*     */ 
/*  75 */       hasError = false;
/*     */     }
/*     */     catch (EmptyResultDataAccessException e) {
/*  78 */       insertNewComputer();
/*     */     }
/*     */     catch (Exception ex) {
/*  81 */       hasError = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void insertNewComputer()
/*     */   {
/*     */     try
/*     */     {
/*  90 */       lastId = 10000L;
/*  91 */       String sql = "INSERT INTO SYS_DB_ID (id,incremental,bound) VALUES(" + adjust + ",10000," + lastId + ")";
/*  92 */       jdbcTemplate.update(sql);
/*     */     }
/*     */     catch (Exception e) {
/*  95 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static synchronized long genId()
/*     */   {
/* 105 */     if (lastId <= nextId) {
/* 106 */       getNextIdBlock();
/*     */     }
/* 108 */     long _nextId = nextId++;
/* 109 */     return _nextId + adjust * 10000000000000L;
/*     */   }
/*     */ 
/*     */   public static final String getGuid()
/*     */   {
/* 120 */     UUID uuid = UUID.randomUUID();
/* 121 */     return uuid.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.UniqueIdUtil
 * JD-Core Version:    0.6.2
 */