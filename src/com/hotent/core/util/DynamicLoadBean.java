/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.beans.factory.support.BeanDefinitionRegistry;
/*    */ import org.springframework.beans.factory.xml.ResourceEntityResolver;
/*    */ import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.ApplicationContextAware;
/*    */ import org.springframework.context.ConfigurableApplicationContext;
/*    */ 
/*    */ public class DynamicLoadBean
/*    */   implements ApplicationContextAware
/*    */ {
/* 28 */   private ConfigurableApplicationContext applicationContext = null;
/*    */ 
/* 30 */   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { this.applicationContext = ((ConfigurableApplicationContext)applicationContext); }
/*    */ 
/*    */   public ConfigurableApplicationContext getApplicationContext() {
/* 33 */     return this.applicationContext;
/*    */   }
/*    */ 
/*    */   public void loadBean(String configLocationString)
/*    */   {
/* 42 */     XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)getApplicationContext().getBeanFactory());
/* 43 */     beanDefinitionReader.setResourceLoader(getApplicationContext());
/* 44 */     beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(getApplicationContext()));
/*    */     try {
/* 46 */       String[] configLocations = { configLocationString };
/* 47 */       for (int i = 0; i < configLocations.length; i++)
/* 48 */         beanDefinitionReader.loadBeanDefinitions(getApplicationContext().getResources(configLocations[i]));
/*    */     }
/*    */     catch (BeansException e) {
/* 51 */       e.printStackTrace();
/*    */     }
/*    */     catch (IOException e) {
/* 54 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.DynamicLoadBean
 * JD-Core Version:    0.6.2
 */