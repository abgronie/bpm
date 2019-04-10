/*    */ package com.hotent.core.util.jsonobject.support;
/*    */ 
/*    */ import com.google.gson.ExclusionStrategy;
/*    */ import com.google.gson.FieldAttributes;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public class SuperclassExclusionStrategy
/*    */   implements ExclusionStrategy
/*    */ {
/*    */   public boolean shouldSkipClass(Class<?> arg0)
/*    */   {
/* 10 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean shouldSkipField(FieldAttributes fieldAttributes) {
/* 14 */     String fieldName = fieldAttributes.getName();
/* 15 */     Class theClass = fieldAttributes.getDeclaringClass();
/* 16 */     return isFieldInSuperclass(theClass, fieldName);
/*    */   }
/*    */ 
/*    */   private boolean isFieldInSuperclass(Class<?> subclass, String fieldName) {
/* 20 */     Class superclass = subclass.getSuperclass();
/*    */ 
/* 23 */     while (superclass != null) {
/* 24 */       Field field = getField(superclass, fieldName);
/* 25 */       if (field != null)
/* 26 */         return true;
/* 27 */       superclass = superclass.getSuperclass();
/*    */     }
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   private Field getField(Class<?> theClass, String fieldName) {
/*    */     try {
/* 34 */       return theClass.getDeclaredField(fieldName); } catch (Exception e) {
/*    */     }
/* 36 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.jsonobject.support.SuperclassExclusionStrategy
 * JD-Core Version:    0.6.2
 */