/*     */ package com.hotent.core.ldap.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class LdapOrganization
/*     */ {
/*   7 */   public static String KEY_O = "o";
/*   8 */   public static String KEY_BUSINESSCATEGORY = "businessCategory";
/*   9 */   public static String KEY_DESCRIPTION = "description";
/*  10 */   public static String KEY_DISTINGUISHEDNAME = "distinguishedName";
/*  11 */   public static String KEY_FACSIMILETELEPHONENUMBER = "facsimileTelephoneNumber";
/*  12 */   public static String KEY_NAME = "name";
/*  13 */   public static String KEY_POSTALADDRESS = "postalAddress";
/*  14 */   public static String KEY_POSTALCODE = "postalCode";
/*  15 */   public static String KEY_REGISTEREDADDRESS = "registeredAddress";
/*  16 */   public static String KEY_ST = "st";
/*  17 */   public static String KEY_STREET = "street";
/*  18 */   public static String KEY_TELEPHONENUMBER = "telephoneNumber";
/*  19 */   public static String KEY_TELEXNUMBER = "telexNumber";
/*  20 */   public static String KEY_USNCHANGED = "uSNChanged";
/*  21 */   public static String KEY_USNCREATED = "uSNCreated";
/*  22 */   public static String KEY_WHENCHANGED = "whenChanged";
/*  23 */   public static String KEY_WHENCREATED = "whenCreated";
/*  24 */   public static String OBJECTCATEGORY = "organization";
/*  25 */   public static String OBJECTCLASS = "organization";
/*     */   private String o;
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
/*     */   public String getO()
/*     */   {
/*  46 */     return this.o;
/*     */   }
/*     */   public void setO(String o) {
/*  49 */     this.o = o;
/*     */   }
/*     */   public String getBusinessCategory() {
/*  52 */     return this.businessCategory;
/*     */   }
/*     */   public void setBusinessCategory(String businessCategory) {
/*  55 */     this.businessCategory = businessCategory;
/*     */   }
/*     */   public String getDescription() {
/*  58 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  61 */     this.description = description;
/*     */   }
/*     */   public String getDistinguishedName() {
/*  64 */     return this.distinguishedName;
/*     */   }
/*     */   public void setDistinguishedName(String distinguishedName) {
/*  67 */     this.distinguishedName = distinguishedName;
/*     */   }
/*     */   public String getFacsimileTelephoneNumber() {
/*  70 */     return this.facsimileTelephoneNumber;
/*     */   }
/*     */   public void setFacsimileTelephoneNumber(String facsimileTelephoneNumber) {
/*  73 */     this.facsimileTelephoneNumber = facsimileTelephoneNumber;
/*     */   }
/*     */   public String getName() {
/*  76 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  79 */     this.name = name;
/*     */   }
/*     */   public String getPostalAddress() {
/*  82 */     return this.postalAddress;
/*     */   }
/*     */   public void setPostalAddress(String postalAddress) {
/*  85 */     this.postalAddress = postalAddress;
/*     */   }
/*     */   public String getPostalCode() {
/*  88 */     return this.postalCode;
/*     */   }
/*     */   public void setPostalCode(String postalCode) {
/*  91 */     this.postalCode = postalCode;
/*     */   }
/*     */   public String getRegisteredAddress() {
/*  94 */     return this.registeredAddress;
/*     */   }
/*     */   public void setRegisteredAddress(String registeredAddress) {
/*  97 */     this.registeredAddress = registeredAddress;
/*     */   }
/*     */   public String getSt() {
/* 100 */     return this.st;
/*     */   }
/*     */   public void setSt(String st) {
/* 103 */     this.st = st;
/*     */   }
/*     */   public String getStreet() {
/* 106 */     return this.street;
/*     */   }
/*     */   public void setStreet(String street) {
/* 109 */     this.street = street;
/*     */   }
/*     */   public String getTelephoneNumber() {
/* 112 */     return this.telephoneNumber;
/*     */   }
/*     */   public void setTelephoneNumber(String telephoneNumber) {
/* 115 */     this.telephoneNumber = telephoneNumber;
/*     */   }
/*     */   public String getTelexNumber() {
/* 118 */     return this.telexNumber;
/*     */   }
/*     */   public void setTelexNumber(String telexNumber) {
/* 121 */     this.telexNumber = telexNumber;
/*     */   }
/*     */   public String getuSNChanged() {
/* 124 */     return this.uSNChanged;
/*     */   }
/*     */   public void setuSNChanged(String uSNChanged) {
/* 127 */     this.uSNChanged = uSNChanged;
/*     */   }
/*     */   public String getuSNCreated() {
/* 130 */     return this.uSNCreated;
/*     */   }
/*     */   public void setuSNCreated(String uSNCreated) {
/* 133 */     this.uSNCreated = uSNCreated;
/*     */   }
/*     */   public Date getWhenChanged() {
/* 136 */     return this.whenChanged;
/*     */   }
/*     */   public void setWhenChanged(Date whenChanged) {
/* 139 */     this.whenChanged = whenChanged;
/*     */   }
/*     */   public Date getWhenCreated() {
/* 142 */     return this.whenCreated;
/*     */   }
/*     */   public void setWhenCreated(Date whenCreated) {
/* 145 */     this.whenCreated = whenCreated;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 149 */     return "Organization [o=" + this.o + ", businessCategory=" + this.businessCategory + ", description=" + this.description + ", distinguishedName=" + this.distinguishedName + ", facsimileTelephoneNumber=" + this.facsimileTelephoneNumber + ", name=" + this.name + ", postalAddress=" + this.postalAddress + ", postalCode=" + this.postalCode + ", registeredAddress=" + this.registeredAddress + ", st=" + this.st + ", street=" + this.street + ", telephoneNumber=" + this.telephoneNumber + ", telexNumber=" + this.telexNumber + ", uSNChanged=" + this.uSNChanged + ", uSNCreated=" + this.uSNCreated + ", whenChanged=" + this.whenChanged + ", whenCreated=" + this.whenCreated + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.model.LdapOrganization
 * JD-Core Version:    0.6.2
 */