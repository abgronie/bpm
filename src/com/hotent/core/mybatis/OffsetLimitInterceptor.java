/*     */ package com.hotent.core.mybatis;
/*     */ 
/*     */ import com.hotent.core.mybatis.support.PropertiesHelper;
/*     */ import java.util.Properties;
/*     */ import org.apache.ibatis.mapping.BoundSql;
/*     */ import org.apache.ibatis.mapping.MappedStatement;
/*     */ import org.apache.ibatis.mapping.MappedStatement.Builder;
/*     */ import org.apache.ibatis.mapping.ParameterMapping;
/*     */ import org.apache.ibatis.mapping.SqlSource;
/*     */ import org.apache.ibatis.plugin.Interceptor;
/*     */ import org.apache.ibatis.plugin.Intercepts;
/*     */ import org.apache.ibatis.plugin.Invocation;
/*     */ import org.apache.ibatis.plugin.Plugin;
/*     */ import org.apache.ibatis.session.RowBounds;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ @Intercepts({@org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
/*     */ public class OffsetLimitInterceptor
/*     */   implements Interceptor
/*     */ {
/*  41 */   private static Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
/*  42 */   static int MAPPED_STATEMENT_INDEX = 0;
/*  43 */   static int PARAMETER_INDEX = 1;
/*  44 */   static int ROWBOUNDS_INDEX = 2;
/*  45 */   static int RESULT_HANDLER_INDEX = 3;
/*     */   Dialect dialect;
/*     */ 
/*     */   private static String getSql(String sql)
/*     */   {
/*  52 */     return sql.trim().replaceAll("(?si)\\s+", " ");
/*     */   }
/*     */ 
/*     */   public Object intercept(Invocation invocation) throws Throwable
/*     */   {
/*  57 */     Object[] queryArgs = invocation.getArgs();
/*  58 */     MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
/*  59 */     Object parameter = queryArgs[PARAMETER_INDEX];
/*  60 */     RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
/*     */ 
/*  63 */     int offset = rowBounds.getOffset();
/*     */ 
/*  65 */     int limit = rowBounds.getLimit();
/*     */ 
/*  69 */     BoundSql boundSql = ms.getBoundSql(parameter);
/*  70 */     StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
/*  71 */     if (bufferSql.lastIndexOf(";") == bufferSql.length() - 1) {
/*  72 */       bufferSql.deleteCharAt(bufferSql.length() - 1);
/*     */     }
/*     */ 
/*  75 */     String sql = getSql(bufferSql.toString().trim());
/*     */ 
/*  79 */     if ((this.dialect.supportsLimit()) && ((offset != 0) || (limit != 2147483647)))
/*     */     {
/*  81 */       if (this.dialect.supportsLimitOffset())
/*  82 */         sql = this.dialect.getLimitString(sql, offset, limit);
/*     */       else {
/*  84 */         sql = this.dialect.getLimitString(sql, 0, limit);
/*     */       }
/*  86 */       queryArgs[ROWBOUNDS_INDEX] = new RowBounds(0, 2147483647);
/*     */     }
/*     */ 
/*  89 */     queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, sql);
/*  90 */     return invocation.proceed();
/*     */   }
/*     */ 
/*     */   private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql, String sql) {
/*  94 */     BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
/*  95 */     return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
/*     */   }
/*     */ 
/*     */   private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql)
/*     */   {
/* 100 */     BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
/* 101 */     for (ParameterMapping mapping : boundSql.getParameterMappings()) {
/* 102 */       String prop = mapping.getProperty();
/* 103 */       if (boundSql.hasAdditionalParameter(prop)) {
/* 104 */         newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
/*     */       }
/*     */     }
/* 107 */     return newBoundSql;
/*     */   }
/*     */ 
/*     */   private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource)
/*     */   {
/* 112 */     MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
/*     */ 
/* 114 */     builder.resource(ms.getResource());
/* 115 */     builder.fetchSize(ms.getFetchSize());
/* 116 */     builder.statementType(ms.getStatementType());
/* 117 */     builder.keyGenerator(ms.getKeyGenerator());
/* 118 */     if ((ms.getKeyProperties() != null) && (ms.getKeyProperties().length != 0)) {
/* 119 */       StringBuffer keyProperties = new StringBuffer();
/* 120 */       for (String keyProperty : ms.getKeyProperties()) {
/* 121 */         keyProperties.append(keyProperty).append(",");
/*     */       }
/* 123 */       keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
/* 124 */       builder.keyProperty(keyProperties.toString());
/*     */     }
/*     */ 
/* 128 */     builder.timeout(ms.getTimeout());
/*     */ 
/* 131 */     builder.parameterMap(ms.getParameterMap());
/*     */ 
/* 134 */     builder.resultMaps(ms.getResultMaps());
/* 135 */     builder.resultSetType(ms.getResultSetType());
/*     */ 
/* 138 */     builder.cache(ms.getCache());
/* 139 */     builder.flushCacheRequired(ms.isFlushCacheRequired());
/* 140 */     builder.useCache(ms.isUseCache());
/*     */ 
/* 142 */     return builder.build();
/*     */   }
/*     */ 
/*     */   public Object plugin(Object target) {
/* 146 */     return Plugin.wrap(target, this);
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties properties) {
/* 150 */     PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
/*     */ 
/* 152 */     String dbType = propertiesHelper.getProperty("jdbc.dbType");
/*     */ 
/* 154 */     Properties p = propertiesHelper.getStartsWithProperties("Dialect.");
/* 155 */     String dialectClass = p.getProperty(dbType);
/*     */     try {
/* 157 */       setDialect((Dialect)Class.forName(dialectClass).newInstance());
/*     */     } catch (Exception e) {
/* 159 */       throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setDialect(Dialect dialect)
/*     */   {
/* 174 */     logger.debug("dialectClass: {} ", dialect.getClass().getName());
/* 175 */     this.dialect = dialect;
/*     */   }
/*     */ 
/*     */   public static class BoundSqlSqlSource
/*     */     implements SqlSource
/*     */   {
/*     */     BoundSql boundSql;
/*     */ 
/*     */     public BoundSqlSqlSource(BoundSql boundSql)
/*     */     {
/* 166 */       this.boundSql = boundSql;
/*     */     }
/*     */     public BoundSql getBoundSql(Object parameterObject) {
/* 169 */       return this.boundSql;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.OffsetLimitInterceptor
 * JD-Core Version:    0.6.2
 */