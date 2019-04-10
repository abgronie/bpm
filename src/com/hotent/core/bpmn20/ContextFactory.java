/*    */ package com.hotent.core.bpmn20;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.xml.bind.JAXBContext;
/*    */ import javax.xml.bind.JAXBException;
/*    */ 
/*    */ public class ContextFactory
/*    */ {
/* 15 */   private static Map<String, JAXBContext> contexts = Collections.synchronizedMap(new LinkedHashMap());
/*    */ 
/*    */   public static JAXBContext newInstance(Class<? extends Object>[] classes) throws JAXBException {
/* 18 */     JAXBContext jAXBContext = null;
/* 19 */     List classeNames = new ArrayList();
/* 20 */     String newKey = "";
/* 21 */     for (Class cls : classes) {
/* 22 */       newKey = newKey + cls.getName() + ",";
/* 23 */       classeNames.add(cls.getName());
/*    */     }
/* 25 */     newKey = newKey.substring(0, newKey.length() - 1);
/*    */ 
/* 27 */     for (String key : contexts.keySet()) {
/* 28 */       String[] keyAry = key.split(",");
/* 29 */       List clss = Arrays.asList(keyAry);
/* 30 */       if (clss.equals(clss)) {
/* 31 */         jAXBContext = (JAXBContext)contexts.get(key);
/* 32 */         break;
/*    */       }
/*    */     }
/* 35 */     if (jAXBContext == null) {
/* 36 */       jAXBContext = JAXBContext.newInstance(classes);
/* 37 */       contexts.put(newKey, jAXBContext);
/*    */     }
/*    */ 
/* 40 */     return jAXBContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.ContextFactory
 * JD-Core Version:    0.6.2
 */