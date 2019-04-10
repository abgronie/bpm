/*     */ package com.hotent.core.engine;
/*     */ 
/*     */ import com.hotent.core.service.BaseService;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import groovy.lang.GroovyShell;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.config.BeanPostProcessor;
/*     */ 
/*     */ public class GroovyScriptEngine
/*     */   implements BeanPostProcessor
/*     */ {
/*  42 */   private Log logger = LogFactory.getLog(GroovyScriptEngine.class);
/*  43 */   private GroovyBinding binding = new GroovyBinding();
/*     */ 
/*     */   public void execute(String script, Map<String, Object> vars)
/*     */   {
/*  57 */     executeObject(script, vars);
/*     */   }
/*     */ 
/*     */   private void setParameters(GroovyShell shell, Map<String, Object> vars)
/*     */   {
/*  67 */     if (vars == null) return;
/*  68 */     Set set = vars.entrySet();
/*  69 */     for (Iterator it = set.iterator(); it.hasNext(); ) {
/*  70 */       Map.Entry entry = (Map.Entry)it.next();
/*  71 */       shell.setVariable((String)entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean executeBoolean(String script, Map<String, Object> vars)
/*     */   {
/*  87 */     Boolean rtn = (Boolean)executeObject(script, vars);
/*  88 */     return rtn.booleanValue();
/*     */   }
/*     */ 
/*     */   public String executeString(String script, Map<String, Object> vars)
/*     */   {
/*  99 */     String str = (String)executeObject(script, vars);
/* 100 */     return str;
/*     */   }
/*     */ 
/*     */   public int executeInt(String script, Map<String, Object> vars)
/*     */   {
/* 112 */     Integer rtn = (Integer)executeObject(script, vars);
/* 113 */     return rtn.intValue();
/*     */   }
/*     */ 
/*     */   public float executeFloat(String script, Map<String, Object> vars)
/*     */   {
/* 125 */     Float rtn = (Float)executeObject(script, vars);
/* 126 */     return rtn.floatValue();
/*     */   }
/*     */ 
/*     */   public Object executeObject(String script, Map<String, Object> vars)
/*     */   {
/* 141 */     this.logger.debug("执行:" + script);
/* 142 */     GroovyShell shell = new GroovyShell(this.binding);
/* 143 */     setParameters(shell, vars);
/*     */ 
/* 145 */     script = script.replace("&apos;", "'").replace("&quot;", "\"").replace("&gt;", ">").replace("&lt;", "<").replace("&nuot;", "\n").replace("&amp;", "&");
/*     */ 
/* 152 */     Object rtn = shell.evaluate(script);
/* 153 */     this.binding.clearVariables();
/* 154 */     return rtn;
/*     */   }
/*     */ 
/*     */   public Object getVariable(String key)
/*     */   {
/* 187 */     return this.binding.getVariable(key);
/*     */   }
/*     */ 
/*     */   public Object postProcessAfterInitialization(Object bean, String beanName)
/*     */     throws BeansException
/*     */   {
/* 195 */     boolean rtn = BeanUtils.isInherit(bean.getClass(), BaseService.class);
/* 196 */     boolean isImplScript = BeanUtils.isInherit(bean.getClass(), IScript.class);
/* 197 */     if ((rtn) || (isImplScript)) {
/* 198 */       this.binding.setProperty(beanName, bean);
/*     */     }
/*     */ 
/* 201 */     return bean;
/*     */   }
/*     */ 
/*     */   public Object postProcessBeforeInitialization(Object bean, String beanName)
/*     */     throws BeansException
/*     */   {
/* 207 */     return bean;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.engine.GroovyScriptEngine
 * JD-Core Version:    0.6.2
 */