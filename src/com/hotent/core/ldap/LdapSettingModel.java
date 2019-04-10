/*    */ package com.hotent.core.ldap;
/*    */ 
/*    */ public class LdapSettingModel
/*    */ {
/*  8 */   private String url = "";
/*    */ 
/* 14 */   private String userDn = "";
/*    */ 
/* 18 */   private String password = "";
/*    */ 
/* 23 */   private String ldapBase = "";
/*    */ 
/* 28 */   private String orgFields = "ou,distinguishedName,name";
/*    */ 
/* 32 */   private String userFields = "sAMAccountName,distinguishedName,name";
/*    */ 
/* 35 */   private String orgNames = "";
/*    */ 
/*    */   public String getPassword()
/*    */   {
/* 40 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 43 */     this.password = password;
/*    */   }
/*    */ 
/*    */   public String getOrgFields() {
/* 47 */     return this.orgFields;
/*    */   }
/*    */   public void setOrgFields(String orgFields) {
/* 50 */     this.orgFields = orgFields;
/*    */   }
/*    */   public String getUserFields() {
/* 53 */     return this.userFields;
/*    */   }
/*    */   public void setUserFields(String userFields) {
/* 56 */     this.userFields = userFields;
/*    */   }
/*    */   public String getOrgNames() {
/* 59 */     return this.orgNames;
/*    */   }
/*    */   public void setOrgNames(String orgNames) {
/* 62 */     this.orgNames = orgNames;
/*    */   }
/*    */ 
/*    */   public String getUrl() {
/* 66 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 69 */     this.url = url;
/*    */   }
/*    */   public String getUserDn() {
/* 72 */     return this.userDn;
/*    */   }
/*    */   public void setUserDn(String userDn) {
/* 75 */     this.userDn = userDn;
/*    */   }
/*    */   public String getLdapBase() {
/* 78 */     return this.ldapBase;
/*    */   }
/*    */   public void setLdapBase(String ldapBase) {
/* 81 */     this.ldapBase = ldapBase;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.LdapSettingModel
 * JD-Core Version:    0.6.2
 */