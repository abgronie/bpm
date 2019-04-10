/*    */ package com.hotent.core.table;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ import com.hotent.core.page.PageBean;
/*    */ import com.hotent.core.util.DialectUtil;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.RowMapper;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public abstract class BaseTableMeta
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private JdbcTemplate jdbcTemplate;
/*    */ 
/*    */   public abstract TableModel getTableByName(String paramString);
/*    */ 
/*    */   public abstract Map<String, String> getTablesByName(String paramString);
/*    */ 
/*    */   public abstract List<TableModel> getTablesByName(String paramString, PageBean paramPageBean)
/*    */     throws Exception;
/*    */ 
/*    */   public abstract Map<String, String> getTablesByName(List<String> paramList);
/*    */ 
/*    */   public abstract String getAllTableSql();
/*    */ 
/*    */   protected <T> List<T> getForList(String sql, PageBean pageBean, RowMapper<T> rowMapper, String dbType)
/*    */     throws Exception
/*    */   {
/* 68 */     if (pageBean != null) {
/* 69 */       int pageSize = pageBean.getPageSize();
/* 70 */       int offset = pageBean.getFirst();
/* 71 */       Dialect dialect = DialectUtil.getDialect(dbType);
/* 72 */       String pageSql = dialect.getLimitString(sql, offset, pageSize);
/* 73 */       String totalSql = dialect.getCountSql(sql);
/* 74 */       List list = this.jdbcTemplate.query(pageSql, rowMapper);
/* 75 */       int total = this.jdbcTemplate.queryForInt(totalSql);
/* 76 */       pageBean.setTotalCount(total);
/* 77 */       return list;
/*    */     }
/* 79 */     return this.jdbcTemplate.query(sql, rowMapper);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.BaseTableMeta
 * JD-Core Version:    0.6.2
 */