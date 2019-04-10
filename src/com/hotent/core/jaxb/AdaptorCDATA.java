/*    */ package com.hotent.core.jaxb;
/*    */ 
/*    */ import javax.xml.bind.annotation.adapters.XmlAdapter;
/*    */ 
/*    */ public class AdaptorCDATA extends XmlAdapter<String, String>
/*    */ {
/*    */   public String marshal(String source)
/*    */     throws Exception
/*    */   {
/*  9 */     return "<![CDATA[" + source + "]]>";
/*    */   }
/*    */ 
/*    */   public String unmarshal(String arg) throws Exception
/*    */   {
/* 14 */     return arg;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.jaxb.AdaptorCDATA
 * JD-Core Version:    0.6.2
 */