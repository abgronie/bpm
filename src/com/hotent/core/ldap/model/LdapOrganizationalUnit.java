/*     */ package com.hotent.core.ldap.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class LdapOrganizationalUnit
/*     */ {
/*   6 */   public static String KEY_OU = "ou";
/*   7 */   public static String KEY_BUSINESSCATEGORY = "businessCategory";
/*   8 */   public static String KEY_DESCRIPTION = "description";
/*   9 */   public static String KEY_DISTINGUISHEDNAME = "distinguishedName";
/*  10 */   public static String KEY_FACSIMILETELEPHONENUMBER = "facsimileTelephoneNumber";
/*  11 */   public static String KEY_NAME = "name";
/*  12 */   public static String KEY_POSTALADDRESS = "postalAddress";
/*  13 */   public static String KEY_POSTALCODE = "postalCode";
/*  14 */   public static String KEY_REGISTEREDADDRESS = "registeredAddress";
/*  15 */   public static String KEY_ST = "st";
/*  16 */   public static String KEY_STREET = "street";
/*  17 */   public static String KEY_TELEPHONENUMBER = "telephoneNumber";
/*  18 */   public static String KEY_TELEXNUMBER = "telexNumber";
/*  19 */   public static String KEY_USNCHANGED = "uSNChanged";
/*  20 */   public static String KEY_USNCREATED = "uSNCreated";
/*  21 */   public static String KEY_WHENCHANGED = "whenChanged";
/*  22 */   public static String KEY_WHENCREATED = "whenCreated";
/*  23 */   public static String OBJECTCATEGORY = "organizationalUnit";
/*  24 */   public static String OBJECTCLASS = "organizationalUnit";
/*     */   private String ou;
/*     */   private String businessCategory;
/*     */   private String description;
/*     */   private String distinguishedName;
/*     */   private String facsimileTelephoneNumber;
/*     */   private String name;
/*     */   private String postalAddress;
/*     */   private String postalCode;
/*     */   private String registeredAddress;
/*     */   private String st;
/*     */   private String street;
/*     */   private String telephoneNumber;
/*     */   private String telexNumber;
/*     */   private String uSNChanged;
/*     */   private String uSNCreated;
/*     */   private Date whenChanged;
/*     */   private Date whenCreated;
/*     */ 
/*     */   public String getOu()
/*     */   {
/*  45 */     return this.ou;
/*     */   }
/*     */   public void setOu(String ou) {
/*  48 */     this.ou = ou;
/*     */   }
/*     */   public String getBusinessCategory() {
/*  51 */     return this.businessCategory;
/*     */   }
/*     */   public void setBusinessCategory(String businessCategory) {
/*  54 */     this.businessCategory = businessCategory;
/*     */   }
/*     */   public String getDescription() {
/*  57 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  60 */     this.description = description;
/*     */   }
/*     */   public String getDistinguishedName() {
/*  63 */     return this.distinguishedName;
/*     */   }
/*     */   public void setDistinguishedName(String distinguishedName) {
/*  66 */     this.distinguishedName = distinguishedName;
/*     */   }
/*     */   public String getFacsimileTelephoneNumber() {
/*  69 */     return this.facsimileTelephoneNumber;
/*     */   }
/*     */   public void setFacsimileTelephoneNumber(String facsimileTelephoneNumber) {
/*  72 */     this.facsimileTelephoneNumber = facsimileTelephoneNumber;
/*     */   }
/*     */   public String getName() {
/*  75 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  78 */     this.name = name;
/*     */   }
/*     */   public String getPostalAddress() {
/*  81 */     return this.postalAddress;
/*     */   }
/*     */   public void setPostalAddress(String postalAddress) {
/*  84 */     this.postalAddress = postalAddress;
/*     */   }
/*     */   public String getPostalCode() {
/*  87 */     return this.postalCode;
/*     */   }
/*     */   public void setPostalCode(String postalCode) {
/*  90 */     this.postalCode = postalCode;
/*     */   }
/*     */   public String getRegisteredAddress() {
/*  93 */     return this.registeredAddress;
/*     */   }
/*     */   public void setRegisteredAddress(String registeredAddress) {
/*  96 */     this.registeredAddress = registeredAddress;
/*     */   }
/*     */   public String getSt() {
/*  99 */     return this.st;
/*     */   }
/*     */   public void setSt(String st) {
/* 102 */     this.st = st;
/*     */   }
/*     */   public String getStreet() {
/* 105 */     return this.street;
/*     */   }
/*     */   public void setStreet(String street) {
/* 108 */     this.street = street;
/*     */   }
/*     */   public String getTelephoneNumber() {
/* 111 */     return this.telephoneNumber;
/*     */   }
/*     */   public void setTelephoneNumber(String telephoneNumber) {
/* 114 */     this.telephoneNumber = telephoneNumber;
/*     */   }
/*     */   public String getTelexNumber() {
/* 117 */     return this.telexNumber;
/*     */   }
/*     */   public void setTelexNumber(String telexNumber) {
/* 120 */     this.telexNumber = telexNumber;
/*     */   }
/*     */   public String getuSNChanged() {
/* 123 */     return this.uSNChanged;
/*     */   }
/*     */   public void setuSNChanged(String uSNChanged) {
/* 126 */     this.uSNChanged = uSNChanged;
/*     */   }
/*     */   public String getuSNCreated() {
/* 129 */     return this.uSNCreated;
/*     */   }
/*     */   public void setuSNCreated(String uSNCreated) {
/* 132 */     this.uSNCreated = uSNCreated;
/*     */   }
/*     */   public Date getWhenChanged() {
/* 135 */     return this.whenChanged;
/*     */   }
/*     */   public void setWhenChanged(Date whenChanged) {
/* 138 */     this.whenChanged = whenChanged;
/*     */   }
/*     */   public Date getWhenCreated() {
/* 141 */     return this.whenCreated;
/*     */   }
/*     */   public void setWhenCreated(Date whenCreated) {
/* 144 */     this.whenCreated = whenCreated;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 148 */     return "OrganizationalUnit [ou=" + this.ou + ", businessCategory=" + this.businessCategory + ", description=" + this.description + ", distinguishedName=" + this.distinguishedName + ", facsimileTelephoneNumber=" + this.facsimileTelephoneNumber + ", name=" + this.name + ", postalAddress=" + this.postalAddress + ", postalCode=" + this.postalCode + ", registeredAddress=" + this.registeredAddress + ", st=" + this.st + ", street=" + this.street + ", telephoneNumber=" + this.telephoneNumber + ", telexNumber=" + this.telexNumber + ", uSNChanged=" + this.uSNChanged + ", uSNCreated=" + this.uSNCreated + ", whenChanged=" + this.whenChanged + ", whenCreated=" + this.whenCreated + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.model.LdapOrganizationalUnit
 * JD-Core Version:    0.6.2
 */