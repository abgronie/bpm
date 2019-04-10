/*     */ package com.hotent.core.db.datasource;
/*     */ 
/*     */ import com.hotent.core.bpm.util.BpmConst;
/*     */ import com.hotent.core.db.IRollBack;
/*     */ import com.hotent.core.db.RollbackJdbcTemplate;
/*     */ import com.hotent.core.mybatis.Dialect;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.page.PageList;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import com.hotent.core.util.DialectUtil;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.sql.DataSource;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.ResultSetExtractor;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/*     */ 
/*     */ public class JdbcTemplateUtil
/*     */ {
/*     */   public static void execute(String sql)
/*     */   {
/*  52 */     JdbcTemplate jdbcTemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
/*  53 */     jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public static void execute(String dsName, String sql)
/*     */     throws Exception
/*     */   {
/*  66 */     DataSource dataSource = DataSourceUtil.getDataSourceByAlias(dsName);
/*  67 */     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
/*  68 */     jdbcTemplate.execute(sql);
/*     */   }
/*     */ 
/*     */   public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(JdbcTemplate jdbcTemplate)
/*     */   {
/*  86 */     return new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
/*     */   }
/*     */ 
/*     */   public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String alias)
/*     */   {
/*     */     try
/*     */     {
/*  98 */       return new NamedParameterJdbcTemplate(DataSourceUtil.getDataSourceByAlias(alias)); } catch (Exception e) {
/*     */     }
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   public static PageList getPage(String alias, PageBean pageBean, String sql, RowMapper rowMap)
/*     */   {
/* 119 */     int pageSize = pageBean.getPageSize();
/* 120 */     int offset = pageBean.getFirst();
/*     */ 
/* 122 */     Map map = new HashMap();
/*     */ 
/* 124 */     Dialect dialect = null;
/*     */     try {
/* 126 */       dialect = DialectUtil.getDialectByDataSourceAlias(alias);
/*     */     } catch (Exception e) {
/* 128 */       return null;
/*     */     }
/* 130 */     String pageSql = dialect.getLimitString(sql, offset, pageSize);
/* 131 */     String totalSql = dialect.getCountSql(sql);
/* 132 */     NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
/* 133 */     namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(alias);
/* 134 */     List list = namedParameterJdbcTemplate.query(pageSql, map, rowMap);
/* 135 */     int total = namedParameterJdbcTemplate.queryForInt(totalSql, map);
/*     */ 
/* 137 */     pageBean.setTotalCount(total);
/* 138 */     PageList pageList = new PageList();
/* 139 */     pageList.setPageBean(pageBean);
/* 140 */     pageList.addAll(list);
/*     */ 
/* 142 */     return pageList;
/*     */   }
/*     */ 
/*     */   public static <T> T getPage(String alias, String sql, ResultSetExtractor<T> rse, PageBean pageBean, Map<String, Object> params)
/*     */   {
/* 147 */     T result = null;
/* 148 */     NamedParameterJdbcTemplate template = null;
/* 149 */     template = getNamedParameterJdbcTemplate(alias);
/* 150 */     if (pageBean != null) {
/* 151 */       int pageSize = pageBean.getPageSize();
/* 152 */       int offset = pageBean.getFirst();
/* 153 */       Dialect dialect = null;
/*     */       try {
/* 155 */         dialect = DialectUtil.getDialectByDataSourceAlias(alias);
/*     */       } catch (Exception e) {
/* 157 */         return null;
/*     */       }
/*     */ 
/* 160 */       String pageSql = dialect.getLimitString(sql, offset, pageSize);
/* 161 */       String totalSql = dialect.getCountSql(sql);
/* 162 */       result = template.query(pageSql, params, rse);
/* 163 */       int total = template.queryForInt(totalSql, params);
/* 164 */       pageBean.setTotalCount(total);
/*     */     } else {
/* 166 */       result = template.query(sql, params, rse);
/*     */     }
/* 168 */     return result;
/*     */   }
/*     */ 
/*     */   public static PageList getPage(String alias, int currentPage, int pageSize, String sql, Map paraMap)
/*     */   {
/* 173 */     int offset = (currentPage - 1) * pageSize;
/*     */ 
/* 175 */     Dialect dialect = null;
/*     */     try {
/* 177 */       dialect = DialectUtil.getDialectByDataSourceAlias(alias);
/*     */     } catch (Exception e) {
/* 179 */       return null;
/*     */     }
/*     */ 
/* 182 */     String pageSql = dialect.getLimitString(sql, offset, pageSize);
/* 183 */     String totalSql = dialect.getCountSql(sql);
/* 184 */     NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
/* 185 */     namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(alias);
/* 186 */     List list = namedParameterJdbcTemplate.queryForList(pageSql, paraMap);
/* 187 */     int total = namedParameterJdbcTemplate.queryForInt(totalSql, paraMap);
/*     */ 
/* 189 */     PageBean pageBean = new PageBean(currentPage, pageSize);
/*     */ 
/* 191 */     pageBean.setTotalCount(total);
/*     */ 
/* 193 */     PageList pageList = new PageList();
/*     */ 
/* 195 */     pageList.addAll(list);
/*     */ 
/* 197 */     pageList.setPageBean(pageBean);
/*     */ 
/* 199 */     return pageList;
/*     */   }
/*     */ 
/*     */   public static List getPage(String alias, String sql, Map<?, ?> paraMap, PageBean pageBean) {
/* 203 */     int currentPage = pageBean.getCurrentPage();
/* 204 */     int pageSize = pageBean.getPageSize();
/* 205 */     return getPage(alias, currentPage, pageSize, sql, paraMap);
/*     */   }
/*     */ 
/*     */   public static JdbcTemplate getNewJdbcTemplate(String alias)
/*     */     throws Exception
/*     */   {
/* 220 */     if ((StringUtil.isEmpty(alias)) || (alias.equals("dataSource_Default")) || (alias.equals(BpmConst.LOCAL_DATASOURCE))) {
/* 221 */       return (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
/*     */     }
/* 223 */     Map map = DataSourceUtil.getDataSources();
/* 224 */     DataSource ds = (DataSource)map.get(alias);
/* 225 */     return new JdbcTemplate(ds);
/*     */   }
/*     */ 
/*     */   public static Boolean executeSql(String sql, boolean rollback)
/*     */   {
/* 237 */     final JdbcTemplate jdbcTemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
/* 238 */     RollbackJdbcTemplate rollbackJdbcTemplate = (RollbackJdbcTemplate)AppUtil.getBean("rollbackJdbcTemplate");
/* 239 */     if (!rollback) {
/* 240 */       jdbcTemplate.execute(sql);
/* 241 */       return Boolean.valueOf(true);
/*     */     }
/*     */ 
/* 244 */     Map param = new HashMap();
/* 245 */     Boolean b = (Boolean)rollbackJdbcTemplate.executeRollBack(new IRollBack()
/*     */     {
/*     */       public Object execute(String script, Map<String, Object> map)
/*     */       {
/*     */         try {
/* 250 */           jdbcTemplate.execute(script);
/*     */         } catch (Exception e) {
/* 252 */           e.printStackTrace();
/* 253 */           return Boolean.valueOf(false);
/*     */         }
/*     */ 
/* 256 */         return Boolean.valueOf(true);
/*     */       }
/*     */     }
/*     */     , sql, param);
/*     */ 
/* 260 */     return b;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.datasource.JdbcTemplateUtil
 * JD-Core Version:    0.6.2
 */