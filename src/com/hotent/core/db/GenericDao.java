/*     */ package com.hotent.core.db;
/*     */ 
/*     */ import com.hotent.core.model.BaseModel;
/*     */ import com.hotent.core.mybatis.BaseMyBatisDao;
/*     */ import com.hotent.core.mybatis.IbatisSql;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.page.PageList;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.web.query.QueryFilter;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.ibatis.session.RowBounds;
/*     */ import org.mybatis.spring.SqlSessionTemplate;
/*     */ import org.springframework.beans.factory.NoSuchBeanDefinitionException;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public abstract class GenericDao<E, PK extends Serializable> extends BaseMyBatisDao
/*     */   implements IEntityDao<E, PK>
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   protected JdbcTemplate jdbcTemplate;
/*     */ 
/*     */   @Resource
/*     */   Properties configproperties;
/*     */ 
/*     */   public abstract Class getEntityClass();
/*     */ 
/*     */   protected String getDbType()
/*     */   {
/*  64 */     return this.configproperties.getProperty("jdbc.dbType");
/*     */   }
/*     */ 
/*     */   public E getById(PK primaryKey)
/*     */   {
/*  75 */     String getStatement = getIbatisMapperNamespace() + ".getById";
/*  76 */     E object = getSqlSessionTemplate().selectOne(getStatement, primaryKey);
/*     */ 
/*  78 */     return object;
/*     */   }
/*     */ 
/*     */   public E getUnique(String sqlKey, Object params)
/*     */   {
/*  88 */     String getStatement = getIbatisMapperNamespace() + "." + sqlKey;
/*  89 */     E object = getSqlSessionTemplate().selectOne(getStatement, params);
/*  90 */     return object;
/*     */   }
/*     */ 
/*     */   public Object getOne(String sqlKey, Object params)
/*     */   {
/* 103 */     String statement = getIbatisMapperNamespace() + "." + sqlKey;
/* 104 */     Object object = getSqlSessionTemplate().selectOne(statement, params);
/* 105 */     return object;
/*     */   }
/*     */ 
/*     */   public List<E> getBySqlKey(String sqlKey, Object params, PageBean pageBean)
/*     */   {
/* 117 */     String statement = getIbatisMapperNamespace() + "." + sqlKey;
/*     */ 
/* 119 */     return getList(statement, params, pageBean);
/*     */   }
/*     */ 
/*     */   public List<E> getBySqlKey(String sqlKey, QueryFilter queryFilter)
/*     */   {
/* 130 */     List list = new ArrayList();
/* 131 */     if (queryFilter.getPageBean() == null) {
/* 132 */       list = getBySqlKey(sqlKey, queryFilter.getFilters());
/*     */     }
/*     */     else {
/* 135 */       list = getBySqlKey(sqlKey, queryFilter.getFilters(), queryFilter.getPageBean());
/*     */     }
/*     */ 
/* 139 */     queryFilter.setForWeb();
/* 140 */     return list;
/*     */   }
/*     */ 
/*     */   public List getListBySqlKey(String sqlKey, QueryFilter queryFilter)
/*     */   {
/* 152 */     List list = new ArrayList();
/* 153 */     if (queryFilter.getPageBean() == null) {
/* 154 */       list = getBySqlKey(sqlKey, queryFilter.getFilters());
/*     */     }
/*     */     else {
/* 157 */       list = getBySqlKey(sqlKey, queryFilter.getFilters(), queryFilter.getPageBean());
/*     */     }
/*     */ 
/* 160 */     queryFilter.setForWeb();
/* 161 */     return list;
/*     */   }
/*     */ 
/*     */   public List getBySqlKeyGenericity(String sqlKey, Object params)
/*     */   {
/* 167 */     String statement = getIbatisMapperNamespace() + "." + sqlKey;
/*     */ 
/* 169 */     return getSqlSessionTemplate().selectList(statement, params);
/*     */   }
/*     */ 
/*     */   public List<E> getBySqlKey(String sqlKey, Object params)
/*     */   {
/* 180 */     String statement = getIbatisMapperNamespace() + "." + sqlKey;
/*     */ 
/* 182 */     return getSqlSessionTemplate().selectList(statement, params);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public List<?> getListBySqlKey(String sqlKey, Object params)
/*     */   {
/* 194 */     String statement = getIbatisMapperNamespace() + "." + sqlKey;
/*     */ 
/* 196 */     return getSqlSessionTemplate().selectList(statement, params);
/*     */   }
/*     */ 
/*     */   public List<E> getBySqlKey(String sqlKey)
/*     */   {
/* 207 */     String statement = getIbatisMapperNamespace() + "." + sqlKey;
/*     */ 
/* 209 */     List list = getSqlSessionTemplate().selectList(statement);
/*     */ 
/* 211 */     return list;
/*     */   }
/*     */ 
/*     */   public int delById(PK id)
/*     */   {
/* 222 */     String delStatement = getIbatisMapperNamespace() + ".delById";
/* 223 */     int affectCount = getSqlSessionTemplate().delete(delStatement, id);
/* 224 */     return affectCount;
/*     */   }
/*     */ 
/*     */   public int delBySqlKey(String sqlKey, Object params)
/*     */   {
/* 235 */     String delStatement = getIbatisMapperNamespace() + "." + sqlKey;
/* 236 */     int affectCount = getSqlSessionTemplate().delete(delStatement, params);
/* 237 */     return affectCount;
/*     */   }
/*     */ 
/*     */   public void add(E entity)
/*     */   {
/* 245 */     String addStatement = getIbatisMapperNamespace() + ".add";
/*     */ 
/* 247 */     if ((entity instanceof BaseModel))
/*     */     {
/* 250 */       BaseModel baseModel = (BaseModel)entity;
/* 251 */       if (baseModel.getCreatetime() == null) {
/* 252 */         baseModel.setCreatetime(new Date());
/*     */       }
/*     */       try
/*     */       {
/* 256 */         IDbSetModel iDbSetModel = (IDbSetModel)AppUtil.getBean(IDbSetModel.class);
/* 257 */         if (iDbSetModel != null)
/* 258 */           iDbSetModel.setAdd(baseModel);
/*     */       }
/*     */       catch (NoSuchBeanDefinitionException ex)
/*     */       {
/* 262 */         this.logger.debug("add:NoSuchBeanDefinitionException" + ex.getMessage());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 267 */     getSqlSessionTemplate().insert(addStatement, entity);
/*     */   }
/*     */ 
/*     */   public int update(E entity)
/*     */   {
/* 276 */     String updStatement = getIbatisMapperNamespace() + ".update";
/*     */ 
/* 278 */     if ((entity instanceof BaseModel))
/*     */     {
/* 280 */       BaseModel baseModel = (BaseModel)entity;
/* 281 */       baseModel.setUpdatetime(new Date());
/*     */       try
/*     */       {
/* 285 */         IDbSetModel iDbSetModel = (IDbSetModel)AppUtil.getBean(IDbSetModel.class);
/* 286 */         if (iDbSetModel != null)
/* 287 */           iDbSetModel.setUpd(baseModel);
/*     */       }
/*     */       catch (NoSuchBeanDefinitionException ex)
/*     */       {
/* 291 */         this.logger.debug("UPDATE:NoSuchBeanDefinitionException" + ex.getMessage());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 296 */     int affectCount = getSqlSessionTemplate().update(updStatement, entity);
/* 297 */     return affectCount;
/*     */   }
/*     */ 
/*     */   public int update(String sqlKey, Object params)
/*     */   {
/* 308 */     String updStatement = getIbatisMapperNamespace() + "." + sqlKey;
/* 309 */     int affectCount = getSqlSessionTemplate().update(updStatement, params);
/* 310 */     return affectCount;
/*     */   }
/*     */ 
/*     */   public String getIbatisMapperNamespace()
/*     */   {
/* 321 */     return getEntityClass().getName();
/*     */   }
/*     */ 
/*     */   public List<E> getList(String statementName, Object params, PageBean pageBean)
/*     */   {
/* 333 */     if (pageBean == null) return getList(statementName, params);
/*     */ 
/* 335 */     Map filters = new HashMap();
/*     */ 
/* 337 */     if (params != null) {
/* 338 */       if ((params instanceof Map)) {
/* 339 */         filters.putAll((Map)params);
/*     */       }
/*     */       else {
/* 342 */         Map parameterObject = BeanUtils.describe(params);
/* 343 */         filters.putAll(parameterObject);
/*     */       }
/*     */     }
/* 346 */     if (pageBean.isShowTotal())
/*     */     {
/* 348 */       IbatisSql ibatisSql = getIbatisSql(statementName, filters);
/*     */ 
/* 350 */       this.log.info(ibatisSql.getSql());
/*     */ 
/* 352 */       int totalCount = this.jdbcTemplate.queryForInt(ibatisSql.getCountSql(), ibatisSql.getParameters());
/*     */ 
/* 354 */       pageBean.setTotalCount(totalCount);
/*     */     }
/* 356 */     RowBounds rowBounds = new RowBounds(pageBean.getFirst(), pageBean.getPageSize());
/*     */ 
/* 358 */     List list = getSqlSessionTemplate().selectList(statementName, filters, rowBounds);
/*     */ 
/* 360 */     PageList pageList = new PageList();
/* 361 */     pageList.addAll(list);
/* 362 */     pageList.setPageBean(pageBean);
/*     */ 
/* 364 */     return pageList;
/*     */   }
/*     */ 
/*     */   public List<E> getList(String statementName, Object params)
/*     */   {
/* 377 */     Map filters = new HashMap();
/* 378 */     if (params != null) {
/* 379 */       if ((params instanceof Map)) {
/* 380 */         filters.putAll((Map)params);
/*     */       }
/*     */       else {
/* 383 */         Map parameterObject = BeanUtils.describe(params);
/* 384 */         filters.putAll(parameterObject);
/*     */       }
/*     */     }
/*     */ 
/* 388 */     if (this.log.isDebugEnabled()) {
/* 389 */       IbatisSql ibatisSql = getIbatisSql(statementName, filters);
/* 390 */       this.log.debug(ibatisSql.getSql());
/*     */     }
/* 392 */     return getSqlSessionTemplate().selectList(statementName, filters);
/*     */   }
/*     */ 
/*     */   public List<E> getList(String statementName, QueryFilter queryFilter)
/*     */   {
/* 407 */     List list = null;
/* 408 */     PageBean pageBean = queryFilter.getPageBean();
/* 409 */     Object filters = queryFilter.getFilters();
/* 410 */     if (pageBean != null)
/* 411 */       list = getList(statementName, queryFilter.getFilters(), pageBean);
/*     */     else {
/* 413 */       list = getList(statementName, filters);
/*     */     }
/* 415 */     return list;
/*     */   }
/*     */ 
/*     */   public List<E> getAll()
/*     */   {
/* 426 */     String statementName = getIbatisMapperNamespace() + ".getAll";
/* 427 */     return getSqlSessionTemplate().selectList(statementName, null);
/*     */   }
/*     */ 
/*     */   public List<E> getAll(QueryFilter queryFilter)
/*     */   {
/* 440 */     String statementName = getIbatisMapperNamespace() + ".getAll";
/* 441 */     List list = getList(statementName, queryFilter);
/*     */ 
/* 443 */     queryFilter.setForWeb();
/* 444 */     return list;
/*     */   }
/*     */ 
/*     */   public int insert(String sqlKey, Object params)
/*     */   {
/* 457 */     String updStatement = getIbatisMapperNamespace() + "." + sqlKey;
/* 458 */     int affectCount = getSqlSessionTemplate().insert(updStatement, params);
/* 459 */     return affectCount;
/*     */   }
/*     */ 
/*     */   static enum SortBy
/*     */   {
/*  41 */     ASC, 
/*  42 */     DESC;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.GenericDao
 * JD-Core Version:    0.6.2
 */