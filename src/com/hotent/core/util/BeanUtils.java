/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.beanutils.BeanUtilsBean;
/*     */ import org.apache.commons.beanutils.ConvertUtilsBean;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaClass;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.beanutils.PropertyUtilsBean;
/*     */ import org.apache.commons.beanutils.converters.DateConverter;
/*     */ import org.apache.commons.beanutils.converters.LongConverter;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*     */ import org.springframework.core.io.support.ResourcePatternResolver;
/*     */ import org.springframework.core.type.ClassMetadata;
/*     */ import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
/*     */ import org.springframework.core.type.classreading.MetadataReader;
/*     */ import org.springframework.core.type.classreading.MetadataReaderFactory;
/*     */ import org.springframework.util.ReflectionUtils;
/*     */ import org.springframework.web.context.ContextLoader;
/*     */ 
/*     */ public class BeanUtils
/*     */ {
/*  38 */   private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
/*     */ 
/*  42 */   public static ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
/*     */ 
/*  44 */   private static BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
/*     */ 
/*     */   public static boolean isEmpty(Object o)
/*     */   {
/*  61 */     if (o == null) return true;
/*  62 */     if ((o instanceof String)) {
/*  63 */       if (((String)o).trim().length() == 0) {
/*  64 */         return true;
/*     */       }
/*     */     }
/*  67 */     else if ((o instanceof Collection)) {
/*  68 */       if (((Collection)o).isEmpty()) {
/*  69 */         return true;
/*     */       }
/*     */     }
/*  72 */     else if (o.getClass().isArray()) {
/*  73 */       if (((Object[])o).length == 0) {
/*  74 */         return true;
/*     */       }
/*     */     }
/*  77 */     else if (((o instanceof Map)) && 
/*  78 */       (((Map)o).isEmpty())) {
/*  79 */       return true;
/*     */     }
/*     */ 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isZeroEmpty(Object o)
/*     */   {
/*  98 */     if (o == null) return true;
/*  99 */     if ((o instanceof String)) {
/* 100 */       if (((String)o).trim().length() == 0) {
/* 101 */         return true;
/*     */       }
/*     */     }
/* 104 */     else if ((o instanceof Collection)) {
/* 105 */       if (((Collection)o).isEmpty()) {
/* 106 */         return true;
/*     */       }
/*     */     }
/* 109 */     else if (o.getClass().isArray()) {
/* 110 */       if (((Object[])o).length == 0) {
/* 111 */         return true;
/*     */       }
/*     */     }
/* 114 */     else if ((o instanceof Map)) {
/* 115 */       if (((Map)o).isEmpty()) {
/* 116 */         return true;
/*     */       }
/*     */     }
/* 119 */     else if ((o instanceof Double)) {
/* 120 */       Double lEmpty = Double.valueOf(0.0D);
/* 121 */       if (o == lEmpty) {
/* 122 */         return true;
/*     */       }
/*     */     }
/* 125 */     else if ((o instanceof Float)) {
/* 126 */       Float lEmpty = Float.valueOf(0.0F);
/* 127 */       if (o == lEmpty) {
/* 128 */         return true;
/*     */       }
/*     */     }
/* 131 */     else if ((o instanceof Long)) {
/* 132 */       Long lEmpty = Long.valueOf(0L);
/* 133 */       if (o == lEmpty) {
/* 134 */         return true;
/*     */       }
/*     */     }
/* 137 */     else if ((o instanceof Short)) {
/* 138 */       Short sEmpty = Short.valueOf((short)0);
/* 139 */       if (o == sEmpty) {
/* 140 */         return true;
/*     */       }
/*     */     }
/* 143 */     else if ((o instanceof Integer)) {
/* 144 */       Integer sEmpty = Integer.valueOf(0);
/* 145 */       if (o == sEmpty) {
/* 146 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isNotEmpty(Object o)
/*     */   {
/* 162 */     return !isEmpty(o);
/*     */   }
/*     */ 
/*     */   public static boolean isNotEmpty(Long o)
/*     */   {
/* 167 */     return !isEmpty(o);
/*     */   }
/*     */ 
/*     */   public static boolean isNotIncZeroEmpty(Object o)
/*     */   {
/* 178 */     return !isZeroEmpty(o);
/*     */   }
/*     */ 
/*     */   public static boolean isNumber(Object o)
/*     */   {
/* 189 */     if (o == null) return false;
/*     */ 
/* 191 */     if ((o instanceof Number)) {
/* 192 */       return true;
/*     */     }
/* 194 */     if ((o instanceof String)) {
/*     */       try {
/* 196 */         Double.parseDouble((String)o);
/* 197 */         return true;
/*     */       }
/*     */       catch (NumberFormatException e) {
/* 200 */         return false;
/*     */       }
/*     */     }
/* 203 */     return false;
/*     */   }
/*     */ 
/*     */   public static Object populateEntity(Map map, Object entity)
/*     */     throws IllegalAccessException, InvocationTargetException
/*     */   {
/* 217 */     beanUtilsBean.populate(entity, map);
/* 218 */     return entity;
/*     */   }
/*     */ 
/*     */   public static boolean validClass(String className)
/*     */   {
/*     */     try
/*     */     {
/* 228 */       Class.forName(className);
/* 229 */       return true;
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/*     */     }
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isInherit(Class cls, Class parentClass)
/*     */   {
/* 246 */     return parentClass.isAssignableFrom(cls);
/*     */   }
/*     */ 
/*     */   public static Object cloneBean(Object bean)
/*     */   {
/*     */     try
/*     */     {
/* 256 */       return beanUtilsBean.cloneBean(bean);
/*     */     }
/*     */     catch (Exception e) {
/* 259 */       handleReflectionException(e);
/* 260 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Object getBean(Class cls)
/*     */   {
/* 270 */     ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
/*     */ 
/* 272 */     return ctx.getBean(cls);
/*     */   }
/*     */ 
/*     */   public static List<String> scanPackages(String basePackages)
/*     */     throws IllegalArgumentException
/*     */   {
/* 285 */     ResourcePatternResolver rl = new PathMatchingResourcePatternResolver();
/* 286 */     MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(rl);
/* 287 */     List result = new ArrayList();
/* 288 */     String[] arrayPackages = basePackages.split(",");
/*     */     try {
/* 290 */       for (int j = 0; j < arrayPackages.length; j++) {
/* 291 */         String packageToScan = arrayPackages[j];
/* 292 */         String packagePart = packageToScan.replace('.', '/');
/* 293 */         String classPattern = "classpath*:/" + packagePart + "/**/*.class";
/* 294 */         Resource[] resources = rl.getResources(classPattern);
/* 295 */         for (int i = 0; i < resources.length; i++) {
/* 296 */           Resource resource = resources[i];
/* 297 */           MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
/* 298 */           String className = metadataReader.getClassMetadata().getClassName();
/* 299 */           result.add(className);
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 303 */       new IllegalArgumentException("scan pakcage class error,pakcages:" + basePackages);
/*     */     }
/*     */ 
/* 306 */     return result;
/*     */   }
/*     */ 
/*     */   public static void copyNotNullProperties(Object dest, Object orig)
/*     */   {
/* 320 */     if (dest == null) {
/* 321 */       logger.error("No destination bean specified");
/* 322 */       return;
/*     */     }
/* 324 */     if (orig == null) {
/* 325 */       logger.error("No origin bean specified");
/* 326 */       return;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 331 */       if ((orig instanceof DynaBean)) {
/* 332 */         DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();
/*     */ 
/* 334 */         for (int i = 0; i < origDescriptors.length; i++) {
/* 335 */           String name = origDescriptors[i].getName();
/* 336 */           if ((beanUtilsBean.getPropertyUtils().isReadable(orig, name)) && (beanUtilsBean.getPropertyUtils().isWriteable(dest, name)))
/*     */           {
/* 338 */             Object value = ((DynaBean)orig).get(name);
/* 339 */             beanUtilsBean.copyProperty(dest, name, value);
/*     */           }
/*     */         }
/* 342 */       } else if ((orig instanceof Map)) {
/* 343 */         Iterator entries = ((Map)orig).entrySet().iterator();
/* 344 */         while (entries.hasNext()) {
/* 345 */           Map.Entry entry = (Map.Entry)entries.next();
/* 346 */           String name = (String)entry.getKey();
/* 347 */           if (beanUtilsBean.getPropertyUtils().isWriteable(dest, name))
/* 348 */             beanUtilsBean.copyProperty(dest, name, entry.getValue());
/*     */         }
/*     */       }
/*     */       else {
/* 352 */         PropertyDescriptor[] origDescriptors = beanUtilsBean.getPropertyUtils().getPropertyDescriptors(orig);
/*     */ 
/* 354 */         for (int i = 0; i < origDescriptors.length; i++) {
/* 355 */           String name = origDescriptors[i].getName();
/* 356 */           if (!"class".equals(name))
/*     */           {
/* 359 */             if ((beanUtilsBean.getPropertyUtils().isReadable(orig, name)) && (beanUtilsBean.getPropertyUtils().isWriteable(dest, name)))
/*     */               try
/*     */               {
/* 362 */                 Object value = beanUtilsBean.getPropertyUtils().getSimpleProperty(orig, name);
/* 363 */                 if (value != null)
/* 364 */                   beanUtilsBean.copyProperty(dest, name, value);
/*     */               }
/*     */               catch (NoSuchMethodException e) {
/* 367 */                 e.printStackTrace();
/*     */               }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception ex) {
/* 373 */       handleReflectionException(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <T> T copyProperties(Class<T> destClass, Object orig)
/*     */   {
/* 381 */     T target = null;
/*     */     try
/*     */     {
/* 384 */       target = destClass.newInstance();
/* 385 */       copyProperties(target, orig);
/* 386 */       return target;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 390 */       handleReflectionException(e);
/* 391 */     }return null;
/*     */   }
/*     */ 
/*     */   public static void copyProperties(Object dest, Object orig)
/*     */   {
/*     */     try
/*     */     {
/* 399 */       beanUtilsBean.copyProperties(dest, orig);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 403 */       handleReflectionException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyProperty(Object bean, String name, Object value)
/*     */   {
/*     */     try
/*     */     {
/* 411 */       beanUtilsBean.copyProperty(bean, name, value);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 415 */       handleReflectionException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Map describe(Object bean)
/*     */   {
/*     */     try
/*     */     {
/* 424 */       return beanUtilsBean.describe(bean);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 428 */       handleReflectionException(e);
/* 429 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String[] getArrayProperty(Object bean, String name)
/*     */   {
/*     */     try
/*     */     {
/* 437 */       return beanUtilsBean.getArrayProperty(bean, name);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 441 */       handleReflectionException(e);
/* 442 */     }return null;
/*     */   }
/*     */ 
/*     */   public static ConvertUtilsBean getConvertUtils()
/*     */   {
/* 448 */     return beanUtilsBean.getConvertUtils();
/*     */   }
/*     */ 
/*     */   public static String getIndexedProperty(Object bean, String name, int index)
/*     */   {
/*     */     try
/*     */     {
/* 455 */       return beanUtilsBean.getIndexedProperty(bean, name, index);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 459 */       handleReflectionException(e);
/* 460 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getIndexedProperty(Object bean, String name)
/*     */   {
/*     */     try
/*     */     {
/* 468 */       return beanUtilsBean.getIndexedProperty(bean, name);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 472 */       handleReflectionException(e);
/* 473 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getMappedProperty(Object bean, String name, String key)
/*     */   {
/*     */     try
/*     */     {
/* 481 */       return beanUtilsBean.getMappedProperty(bean, name, key);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 485 */       handleReflectionException(e);
/* 486 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getMappedProperty(Object bean, String name)
/*     */   {
/*     */     try
/*     */     {
/* 494 */       return beanUtilsBean.getMappedProperty(bean, name);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 498 */       handleReflectionException(e);
/* 499 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getNestedProperty(Object bean, String name)
/*     */   {
/*     */     try
/*     */     {
/* 507 */       return beanUtilsBean.getNestedProperty(bean, name);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 511 */       handleReflectionException(e);
/* 512 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getProperty(Object bean, String name)
/*     */   {
/*     */     try
/*     */     {
/* 520 */       return beanUtilsBean.getProperty(bean, name);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 524 */       handleReflectionException(e);
/* 525 */     }return null;
/*     */   }
/*     */ 
/*     */   public static PropertyUtilsBean getPropertyUtils()
/*     */   {
/*     */     try
/*     */     {
/* 533 */       return beanUtilsBean.getPropertyUtils();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 537 */       handleReflectionException(e);
/* 538 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String getSimpleProperty(Object bean, String name)
/*     */   {
/*     */     try
/*     */     {
/* 546 */       return beanUtilsBean.getSimpleProperty(bean, name);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 550 */       handleReflectionException(e);
/* 551 */     }return null;
/*     */   }
/*     */ 
/*     */   public static void populate(Object bean, Map properties)
/*     */   {
/*     */     try
/*     */     {
/* 560 */       beanUtilsBean.populate(bean, properties);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 564 */       handleReflectionException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setProperty(Object bean, String name, Object value)
/*     */   {
/*     */     try
/*     */     {
/* 572 */       beanUtilsBean.setProperty(bean, name, value);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 576 */       handleReflectionException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void handleReflectionException(Exception e)
/*     */   {
/* 582 */     ReflectionUtils.handleReflectionException(e);
/*     */   }
/*     */ 
/*     */   public static Object convertByActType(String typeName, String value)
/*     */   {
/* 594 */     Object o = null;
/* 595 */     if (typeName.equals("int"))
/* 596 */       o = Integer.valueOf(Integer.parseInt(value));
/* 597 */     else if (typeName.equals("short"))
/* 598 */       o = Short.valueOf(Short.parseShort(value));
/* 599 */     else if (typeName.equals("long"))
/* 600 */       o = Long.valueOf(Long.parseLong(value));
/* 601 */     else if (typeName.equals("float"))
/* 602 */       o = Float.valueOf(Float.parseFloat(value));
/* 603 */     else if (typeName.equals("double"))
/* 604 */       o = Double.valueOf(Double.parseDouble(value));
/* 605 */     else if (typeName.equals("boolean"))
/* 606 */       o = Boolean.valueOf(Boolean.parseBoolean(value));
/* 607 */     else if (typeName.equals("java.lang.String")) {
/* 608 */       o = value;
/*     */     }
/*     */     else {
/* 611 */       o = value;
/*     */     }
/* 613 */     return o;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  47 */     convertUtilsBean.register(new DateConverter(), Date.class);
/*  48 */     convertUtilsBean.register(new LongConverter(null), Long.class);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.BeanUtils
 * JD-Core Version:    0.6.2
 */