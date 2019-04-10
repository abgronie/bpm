/*    */ package com.hotent.core.wsdl;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import org.apache.ws.commons.schema.XmlSchemaType;
/*    */ 
/*    */ public class ServiceInfo
/*    */ {
/*    */   private String name;
/*    */   private String wsdlUrl;
/*    */   private String httpAddress;
/* 16 */   private Map<String, OperationInfo> operations = new TreeMap();
/*    */ 
/* 18 */   private List<XmlSchemaType> complexTypes = new ArrayList();
/*    */   private String targetNamespace;
/* 22 */   private Boolean needPrefix = Boolean.valueOf(false);
/*    */ 
/*    */   public String getTargetNamespace() {
/* 25 */     return this.targetNamespace;
/*    */   }
/*    */   public void setTargetNamespace(String targetNamespace) {
/* 28 */     this.targetNamespace = targetNamespace;
/*    */   }
/*    */   public void addComplexTypes(XmlSchemaType complexType) {
/* 31 */     this.complexTypes.add(complexType);
/*    */   }
/*    */   public List<XmlSchemaType> getComplexTypes() {
/* 34 */     return this.complexTypes;
/*    */   }
/*    */ 
/*    */   public void setComplexTypes(List<XmlSchemaType> complexTypes) {
/* 38 */     this.complexTypes = complexTypes;
/*    */   }
/*    */ 
/*    */   public String getWsdlUrl() {
/* 42 */     return this.wsdlUrl;
/*    */   }
/*    */   public void setWsdlUrl(String wsdlUrl) {
/* 45 */     this.wsdlUrl = wsdlUrl;
/*    */   }
/*    */   public String toString() {
/* 48 */     return getName();
/*    */   }
/*    */ 
/*    */   public Map<String, OperationInfo> getOperations() {
/* 52 */     return this.operations;
/*    */   }
/*    */   public void setOperations(Map<String, OperationInfo> operations) {
/* 55 */     this.operations = operations;
/*    */   }
/*    */   public String getName() {
/* 58 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 62 */     this.name = name;
/*    */   }
/*    */   public String getHttpAddress() {
/* 65 */     return this.httpAddress;
/*    */   }
/*    */   public void setHttpAddress(String httpAddress) {
/* 68 */     this.httpAddress = httpAddress;
/*    */   }
/*    */   public Boolean getNeedPrefix() {
/* 71 */     return this.needPrefix;
/*    */   }
/*    */   public void setNeedPrefix(Boolean needPrefix) {
/* 74 */     this.needPrefix = needPrefix;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.wsdl.ServiceInfo
 * JD-Core Version:    0.6.2
 */