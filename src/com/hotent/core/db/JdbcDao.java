/*     */ package com.hotent.core.db;
/*     */ 
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.ResultSetExtractor;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/*     */ 
/*     */ public class JdbcDao
/*     */ {
/*     */   private JdbcTemplate jdbcTemplate;
/*     */   private Dialect dialect;
/*     */ 
/*     */   public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
/*     */   {
/*  22 */     this.jdbcTemplate = jdbcTemplate;
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect) {
/*  26 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   public List getPage(int currentPage, int pageSize, String sql, Map paraMap, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  32 */     int offset = (currentPage - 1) * pageSize;
/*  33 */     String pageSql = this.dialect.getLimitString(sql, offset, pageSize);
/*  34 */     String totalSql = this.dialect.getCountSql(sql);
/*  35 */     List list = queryForList(pageSql, paraMap);
/*  36 */     int total = queryForInt(totalSql, paraMap);
/*  37 */     pageBean.setTotalCount(total);
/*     */ 
/*  39 */     return list;
/*     */   }
/*     */ 
/*     */   public List getPage(PageBean pageBean, String sql, RowMapper rowMap)
/*     */     throws Exception
/*     */   {
/*  69 */     int pageSize = pageBean.getPageSize();
/*     */ 
/*  71 */     int offset = pageBean.getFirst();
/*     */ 
/*  73 */     Map map = new HashMap();
/*     */ 
/*  76 */     String pageSql = this.dialect.getLimitString(sql, offset, pageSize);
/*  77 */     String totalSql = this.dialect.getCountSql(sql);
/*  78 */     List list = queryForList(pageSql, map, rowMap);
/*  79 */     int total = queryForInt(totalSql, map);
/*     */ 
/*  81 */     pageBean.setTotalCount(total);
/*  82 */     return list;
/*     */   }
/*     */ 
/*     */   public <T> T getPage(String sql, ResultSetExtractor<T> rse, PageBean pageBean)
/*     */     throws Exception
/*     */   {
/*  88 */     int pageSize = pageBean.getPageSize();
/*  89 */     int offset = pageBean.getFirst();
/*  90 */     String pageSql = this.dialect.getLimitString(sql, offset, pageSize);
/*  91 */     String totalSql = this.dialect.getCountSql(sql);
/*  92 */     T result = this.jdbcTemplate.query(pageSql, rse);
/*     */ 
/*  94 */     int total = this.jdbcTemplate.queryForInt(totalSql);
/*  95 */     pageBean.setTotalCount(total);
/*  96 */     return result;
/*     */   }
/*     */ 
/*     */   public List queryForList(String sql, Map parameter, RowMapper rowMap)
/*     */   {
/* 120 */     NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
/* 121 */     return template.query(sql, parameter, rowMap);
/*     */   }
/*     */ 
/*     */   public List queryForList(String sql, Map parameter)
/*     */   {
/* 127 */     NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
/* 128 */     return template.queryForList(sql, parameter);
/*     */   }
/*     */ 
/*     */   public int queryForInt(String sql, Map parameter)
/*     */   {
/* 134 */     NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
/* 135 */     return template.queryForInt(sql, parameter);
/*     */   }
/*     */ 
/*     */   public int upd(String sql) {
/* 139 */     return this.jdbcTemplate.update(sql);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.JdbcDao
 * JD-Core Version:    0.6.2
 */