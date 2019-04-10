/*    */ package com.hotent.core.soap.type;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public enum SoapTypes
/*    */ {
/* 15 */   string("字符串(string,java.lang.String)", new StringSoapType()), 
/*    */ 
/* 17 */   number("数字(number,java.lang.Integer,java.lang.Long,java.math.BigDecimal)", new NumberSoapType()), 
/*    */ 
/* 19 */   date("时间/日期(date,java.util.Date,dateTime)", new DateSoapType()), 
/*    */ 
/* 21 */   bean("复合类型(bean,serializable)", new BeanSoapType()), 
/*    */ 
/* 23 */   list("列表类型(List)", new ListSoapType());
/*    */ 
/*    */   private SoapType soapType;
/*    */   private String name;
/*    */ 
/* 29 */   private SoapTypes(String name, SoapType soapType) { this.name = name;
/* 30 */     this.soapType = soapType; }
/*    */ 
/*    */   public SoapType getSoapType()
/*    */   {
/* 34 */     return this.soapType;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 43 */     return name();
/*    */   }
/*    */ 
/*    */   public static SoapType getTypeByBean(Class<?> klass)
/*    */   {
/* 53 */     if (klass == null) {
/* 54 */       return bean.getSoapType();
/*    */     }
/*    */ 
/* 57 */     for (SoapTypes types : values()) {
/* 58 */       if (Arrays.asList(types.getSoapType().getBeanTypes()).contains(klass)) {
/* 59 */         return types.getSoapType();
/*    */       }
/*    */     }
/*    */ 
/* 63 */     return bean.getSoapType();
/*    */   }
/*    */ 
/*    */   public static SoapType getTypeBySoap(String type)
/*    */   {
/* 73 */     if (type == null) {
/* 74 */       return bean.getSoapType();
/*    */     }
/* 76 */     if (type.matches("List\\{\\w*\\}")) {
/* 77 */       return new ListSoapType();
/*    */     }
/* 79 */     for (SoapTypes types : values()) {
/* 80 */       if (Arrays.asList(types.getSoapType().getSoapTypes()).contains(type)) {
/* 81 */         return types.getSoapType();
/*    */       }
/*    */     }
/* 84 */     return bean.getSoapType();
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.SoapTypes
 * JD-Core Version:    0.6.2
 */