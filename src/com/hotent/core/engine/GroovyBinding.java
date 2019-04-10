/*    */ package com.hotent.core.engine;
/*    */ 
/*    */ import groovy.lang.Binding;
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GroovyBinding extends Binding
/*    */ {
/*    */   private Map variables;
/* 11 */   private static ThreadLocal<Map<String, Object>> localVars = new ThreadLocal();
/*    */ 
/* 13 */   private static Map<String, Object> propertyMap = new HashMap();
/*    */ 
/*    */   public GroovyBinding()
/*    */   {
/*    */   }
/*    */ 
/*    */   public GroovyBinding(Map variables)
/*    */   {
/* 21 */     localVars.set(variables);
/*    */   }
/*    */ 
/*    */   public GroovyBinding(String[] args)
/*    */   {
/* 27 */     this();
/* 28 */     setVariable("args", args);
/*    */   }
/*    */ 
/*    */   public Object getVariable(String name)
/*    */   {
/* 33 */     Map map = (Map)localVars.get();
/* 34 */     Object result = null;
/* 35 */     if ((map != null) && (map.containsKey(name))) {
/* 36 */       result = map.get(name);
/*    */     }
/*    */     else {
/* 39 */       result = propertyMap.get(name);
/*    */     }
/*    */ 
/* 42 */     return result;
/*    */   }
/*    */ 
/*    */   public void setVariable(String name, Object value)
/*    */   {
/* 47 */     if (localVars.get() == null) {
/* 48 */       Map vars = new LinkedHashMap();
/* 49 */       vars.put(name, value);
/* 50 */       localVars.set(vars);
/*    */     }
/*    */     else {
/* 53 */       ((Map)localVars.get()).put(name, value);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Map getVariables() {
/* 58 */     if (localVars.get() == null) {
/* 59 */       return new LinkedHashMap();
/*    */     }
/*    */ 
/* 62 */     return (Map)localVars.get();
/*    */   }
/*    */ 
/*    */   public void clearVariables() {
/* 66 */     localVars.remove();
/*    */   }
/*    */ 
/*    */   public Object getProperty(String property)
/*    */   {
/* 71 */     return propertyMap.get(property);
/*    */   }
/*    */ 
/*    */   public void setProperty(String property, Object newValue)
/*    */   {
/* 76 */     propertyMap.put(property, newValue);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.engine.GroovyBinding
 * JD-Core Version:    0.6.2
 */