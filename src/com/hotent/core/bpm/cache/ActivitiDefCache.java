/*     */ package com.hotent.core.bpm.cache;
/*     */ 
/*     */ import com.hotent.core.cache.ICache;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import com.hotent.core.util.FileUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ 
/*     */ public class ActivitiDefCache
/*     */   implements DeploymentCache<ProcessDefinitionEntity>
/*     */ {
/*  52 */   private ThreadLocal<Map<String, ProcessDefinitionEntity>> processDefinitionCacheLocal = new ThreadLocal();
/*     */ 
/*     */   @Resource
/*     */   ICache iCache;
/*     */ 
/*     */   public static void clearLocal()
/*     */   {
/*  38 */     ActivitiDefCache cache = (ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
/*  39 */     cache.clearProcessCache();
/*     */   }
/*     */ 
/*     */   public static void clearByDefId(String actDefId)
/*     */   {
/*  47 */     ActivitiDefCache cache = (ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
/*  48 */     cache.clearProcessDefinitionEntity(actDefId);
/*  49 */     cache.clearProcessCache();
/*     */   }
/*     */ 
/*     */   private void clearProcessDefinitionEntity(String defId)
/*     */   {
/*  55 */     remove(defId);
/*  56 */     this.processDefinitionCacheLocal.remove();
/*     */   }
/*     */ 
/*     */   private void clearProcessCache() {
/*  60 */     this.processDefinitionCacheLocal.remove();
/*     */   }
/*     */ 
/*     */   private void setThreadLocalDef(ProcessDefinitionEntity processEnt) {
/*  64 */     if (this.processDefinitionCacheLocal.get() == null) {
/*  65 */       Map map = new HashMap();
/*  66 */       map.put(processEnt.getId(), processEnt);
/*  67 */       this.processDefinitionCacheLocal.set(map);
/*     */     }
/*     */     else {
/*  70 */       Map map = (Map)this.processDefinitionCacheLocal.get();
/*  71 */       map.put(processEnt.getId(), processEnt);
/*     */     }
/*     */   }
/*     */ 
/*     */   private ProcessDefinitionEntity getThreadLocalDef(String processDefinitionId)
/*     */   {
/*  77 */     if (this.processDefinitionCacheLocal.get() == null) {
/*  78 */       return null;
/*     */     }
/*  80 */     Map map = (Map)this.processDefinitionCacheLocal.get();
/*  81 */     if (!map.containsKey(processDefinitionId)) {
/*  82 */       return null;
/*     */     }
/*     */ 
/*  85 */     return (ProcessDefinitionEntity)map.get(processDefinitionId);
/*     */   }
/*     */ 
/*     */   public ProcessDefinitionEntity get(String id)
/*     */   {
/*  94 */     ProcessDefinitionEntity ent = (ProcessDefinitionEntity)this.iCache.getByKey(id);
/*  95 */     if (ent == null) return null;
/*  96 */     ProcessDefinitionEntity cloneEnt = null;
/*     */     try
/*     */     {
/*  99 */       cloneEnt = (ProcessDefinitionEntity)FileUtil.cloneObject(ent);
/*     */     }
/*     */     catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     }
/* 104 */     ProcessDefinitionEntity p = getThreadLocalDef(id);
/* 105 */     if (p == null) {
/* 106 */       setThreadLocalDef(cloneEnt);
/*     */     }
/* 108 */     p = getThreadLocalDef(id);
/*     */ 
/* 110 */     return p;
/*     */   }
/*     */ 
/*     */   public void add(String id, ProcessDefinitionEntity object)
/*     */   {
/* 115 */     this.iCache.add(id, object);
/*     */   }
/*     */ 
/*     */   public void remove(String id)
/*     */   {
/* 121 */     this.iCache.delByKey(id);
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 127 */     this.iCache.clearAll();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpm.cache.ActivitiDefCache
 * JD-Core Version:    0.6.2
 */