/*     */ package com.hotent.core.mybatis;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.ibatis.mapping.BoundSql;
/*     */ import org.apache.ibatis.mapping.MappedStatement;
/*     */ import org.apache.ibatis.mapping.ParameterMapping;
/*     */ import org.apache.ibatis.mapping.ParameterMode;
/*     */ import org.apache.ibatis.mapping.ResultMap;
/*     */ import org.apache.ibatis.reflection.MetaObject;
/*     */ import org.apache.ibatis.session.Configuration;
/*     */ import org.apache.ibatis.session.SqlSessionFactory;
/*     */ import org.apache.ibatis.type.TypeHandlerRegistry;
/*     */ import org.mybatis.spring.SqlSessionTemplate;
/*     */ import org.springframework.dao.support.DaoSupport;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public abstract class BaseMyBatisDao extends DaoSupport
/*     */ {
/*  29 */   protected final Log log = LogFactory.getLog(getClass());
/*     */   private SqlSessionFactory sqlSessionFactory;
/*     */   private SqlSessionTemplate sqlSessionTemplate;
/*     */ 
/*     */   protected void checkDaoConfig()
/*     */     throws IllegalArgumentException
/*     */   {
/*  43 */     Assert.notNull(this.sqlSessionFactory, "sqlSessionFactory must be not null");
/*     */   }
/*     */ 
/*     */   public SqlSessionFactory getSqlSessionFactory()
/*     */   {
/*  52 */     return this.sqlSessionFactory;
/*     */   }
/*     */ 
/*     */   public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
/*     */   {
/*  61 */     this.sqlSessionFactory = sqlSessionFactory;
/*  62 */     this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
/*     */   }
/*     */ 
/*     */   public SqlSessionTemplate getSqlSessionTemplate()
/*     */   {
/*  71 */     return this.sqlSessionTemplate;
/*     */   }
/*     */ 
/*     */   public IbatisSql getIbatisSql(String id, Object parameterObject)
/*     */   {
/*  83 */     IbatisSql ibatisSql = new IbatisSql();
/*  84 */     Configuration configuration = this.sqlSessionFactory.getConfiguration();
/*  85 */     Collection coll = configuration.getMappedStatementNames();
/*     */ 
/*  87 */     MappedStatement ms = configuration.getMappedStatement(id);
/*  88 */     BoundSql boundSql = ms.getBoundSql(parameterObject);
/*     */ 
/*  90 */     TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
/*     */ 
/*  92 */     List ResultMaps = ms.getResultMaps();
/*  93 */     if ((ResultMaps != null) && (ResultMaps.size() > 0)) {
/*  94 */       ResultMap ResultMap = (ResultMap)ms.getResultMaps().get(0);
/*  95 */       ibatisSql.setResultClass(ResultMap.getType());
/*     */     }
/*  97 */     ibatisSql.setSql(boundSql.getSql());
/*     */ 
/*  99 */     List parameterMappings = boundSql.getParameterMappings();
/* 100 */     if (parameterMappings != null) {
/* 101 */       Object[] parameterArray = new Object[parameterMappings.size()];
/* 102 */       MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
/*     */ 
/* 104 */       for (int i = 0; i < parameterMappings.size(); i++) {
/* 105 */         ParameterMapping parameterMapping = (ParameterMapping)parameterMappings.get(i);
/* 106 */         if (parameterMapping.getMode() != ParameterMode.OUT)
/*     */         {
/* 109 */           String propertyName = parameterMapping.getProperty();
/*     */           Object value;
/* 110 */           if (boundSql.hasAdditionalParameter(propertyName)) {
/* 111 */             value = boundSql.getAdditionalParameter(propertyName);
/*     */           }
/*     */           else
/*     */           {
/* 112 */             if (parameterObject == null) {
/* 113 */               value = null;
/*     */             }
/*     */             else
/*     */             {
/* 114 */               if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass()))
/* 115 */                 value = parameterObject;
/*     */               else
/* 117 */                 value = metaObject == null ? null : metaObject.getValue(propertyName); 
/*     */             }
/*     */           }
/* 119 */           parameterArray[i] = value;
/*     */         }
/*     */       }
/* 122 */       ibatisSql.setParameters(parameterArray);
/*     */     }
/*     */ 
/* 125 */     return ibatisSql;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.BaseMyBatisDao
 * JD-Core Version:    0.6.2
 */