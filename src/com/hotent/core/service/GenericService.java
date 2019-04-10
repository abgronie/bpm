/*     */ package com.hotent.core.service;
/*     */ 
/*     */ import com.hotent.core.db.IEntityDao;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.web.query.QueryFilter;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public abstract class GenericService<E, PK extends Serializable>
/*     */ {
/*  20 */   protected Logger logger = LoggerFactory.getLogger(GenericService.class);
/*     */ 
/*     */   protected abstract IEntityDao<E, PK> getEntityDao();
/*     */ 
/*     */   public void add(E entity)
/*     */   {
/*  33 */     getEntityDao().add(entity);
/*     */   }
/*     */ 
/*     */   public void delById(PK id)
/*     */   {
/*  42 */     getEntityDao().delById(id);
/*     */   }
/*     */ 
/*     */   public void delByIds(PK[] ids)
/*     */   {
/*  50 */     if (BeanUtils.isEmpty(ids)) return;
/*  51 */     for (PK p : ids)
/*  52 */       delById(p);
/*     */   }
/*     */ 
/*     */   public void update(E entity)
/*     */   {
/*  62 */     getEntityDao().update(entity);
/*     */   }
/*     */ 
/*     */   public E getById(PK id)
/*     */   {
/*  73 */     return (E)getEntityDao().getById(id);
/*     */   }
/*     */ 
/*     */   public List<E> getList(String statatementName, PageBean pb)
/*     */   {
/*  84 */     List list = getEntityDao().getList(statatementName, pb);
/*  85 */     return list;
/*     */   }
/*     */ 
/*     */   public List<E> getAll()
/*     */   {
/*  93 */     return getEntityDao().getAll();
/*     */   }
/*     */ 
/*     */   public List<E> getAll(QueryFilter queryFilter)
/*     */   {
/* 102 */     return getEntityDao().getAll(queryFilter);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.service.GenericService
 * JD-Core Version:    0.6.2
 */