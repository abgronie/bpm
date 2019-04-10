/*     */ package com.hotent.core.ldap.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class LdapUser
/*     */   implements Cloneable
/*     */ {
/*   8 */   public static String KEY_CN = "cn";
/*   9 */   public static String KEY_C = "c";
/*  10 */   public static String KEY_CO = "co";
/*  11 */   public static String KEY_COMPANY = "company";
/*  12 */   public static String KEY_COUNTRYCODE = "countryCode";
/*  13 */   public static String KEY_DEPARTMENT = "department";
/*  14 */   public static String KEY_DESCRIPTION = "description";
/*  15 */   public static String KEY_DISPLAYNAME = "displayName";
/*  16 */   public static String KEY_DISTINGUISHEDNAME = "distinguishedName";
/*  17 */   public static String KEY_FACSIMILETELEPHONENUMBER = "facsimileTelephoneNumber";
/*  18 */   public static String KEY_HOMEPHONE = "homePhone";
/*  19 */   public static String KEY_INITIALS = "initials";
/*  20 */   public static String KEY_L = "l";
/*  21 */   public static String KEY_MAIL = "mail";
/*  22 */   public static String KEY_NAME = "name";
/*  23 */   public static String KEY_POSTALADDRESS = "postalAddress";
/*  24 */   public static String KEY_POSTALCODE = "postalCode";
/*  25 */   public static String KEY_POSTOFFICEBOX = "postOfficeBox";
/*  26 */   public static String KEY_SAMACCOUNTNAME = "sAMAccountName";
/*  27 */   public static String KEY_SN = "sn";
/*  28 */   public static String KEY_ST = "st";
/*  29 */   public static String KEY_STREETADDRESS = "streetAddress";
/*  30 */   public static String KEY_TELEPHONENUMBER = "telephoneNumber";
/*  31 */   public static String KEY_TITLE = "title";
/*  32 */   public static String KEY_USERPRINCIPALNAME = "userPrincipalName";
/*  33 */   public static String KEY_WHENCHANGED = "whenChanged";
/*  34 */   public static String KEY_WHENCREATED = "whenCreated";
/*  35 */   public static String KEY_WWWHOMEPAGE = "wWWHomePage";
/*  36 */   public static String KEY_USERACCOUNTCONTROL = "userAccountControl";
/*  37 */   public static String KEY_GIVENNAME = "givenName";
/*  38 */   public static String OBJECTCATEGORY = "organizationalPerson";
/*  39 */   public static String OBJECTCLASS = "organizationalPerson";
/*     */   private String cn;
/*     */   private String c;
/*     */   private String co;
/*     */   private String company;
/*     */   private String countryCode;
/*     */   private String department;
/*     */   private String description;
/*     */   private String displayName;
/*     */   private String distinguishedName;
/*     */   private String facsimileTelephoneNumber;
/*     */   private String homePhone;
/*     */   private String initials;
/*     */   private String l;
/*     */   private String mail;
/*     */   private String name;
/*     */   private String givenName;
/*     */   private String postalAddress;
/*     */   private String postalCode;
/*     */   private String postOfficeBox;
/*     */   private String sAMAccountName;
/*     */   private String sn;
/*     */   private String st;
/*     */   private String streetAddress;
/*     */   private String telephoneNumber;
/*     */   private String title;
/*     */   private String userPrincipalName;
/*     */   private Date whenChanged;
/*     */   private Date whenCreated;
/*     */   private String wWWHomePage;
/*     */   private String userAccountControl;
/*     */ 
/*     */   public static String getKEY_CN()
/*     */   {
/*  73 */     return KEY_CN;
/*     */   }
/*     */   public static void setKEY_CN(String kEY_CN) {
/*  76 */     KEY_CN = kEY_CN;
/*     */   }
/*     */   public static String getKEY_C() {
/*  79 */     return KEY_C;
/*     */   }
/*     */   public static void setKEY_C(String kEY_C) {
/*  82 */     KEY_C = kEY_C;
/*     */   }
/*     */   public static String getKEY_CO() {
/*  85 */     return KEY_CO;
/*     */   }
/*     */   public static void setKEY_CO(String kEY_CO) {
/*  88 */     KEY_CO = kEY_CO;
/*     */   }
/*     */   public static String getKEY_COMPANY() {
/*  91 */     return KEY_COMPANY;
/*     */   }
/*     */   public static void setKEY_COMPANY(String kEY_COMPANY) {
/*  94 */     KEY_COMPANY = kEY_COMPANY;
/*     */   }
/*     */   public static String getKEY_COUNTRYCODE() {
/*  97 */     return KEY_COUNTRYCODE;
/*     */   }
/*     */   public static void setKEY_COUNTRYCODE(String kEY_COUNTRYCODE) {
/* 100 */     KEY_COUNTRYCODE = kEY_COUNTRYCODE;
/*     */   }
/*     */   public static String getKEY_DEPARTMENT() {
/* 103 */     return KEY_DEPARTMENT;
/*     */   }
/*     */   public static void setKEY_DEPARTMENT(String kEY_DEPARTMENT) {
/* 106 */     KEY_DEPARTMENT = kEY_DEPARTMENT;
/*     */   }
/*     */   public static String getKEY_DESCRIPTION() {
/* 109 */     return KEY_DESCRIPTION;
/*     */   }
/*     */   public static void setKEY_DESCRIPTION(String kEY_DESCRIPTION) {
/* 112 */     KEY_DESCRIPTION = kEY_DESCRIPTION;
/*     */   }
/*     */   public static String getKEY_DISPLAYNAME() {
/* 115 */     return KEY_DISPLAYNAME;
/*     */   }
/*     */   public static void setKEY_DISPLAYNAME(String kEY_DISPLAYNAME) {
/* 118 */     KEY_DISPLAYNAME = kEY_DISPLAYNAME;
/*     */   }
/*     */   public static String getKEY_DISTINGUISHEDNAME() {
/* 121 */     return KEY_DISTINGUISHEDNAME;
/*     */   }
/*     */   public static void setKEY_DISTINGUISHEDNAME(String kEY_DISTINGUISHEDNAME) {
/* 124 */     KEY_DISTINGUISHEDNAME = kEY_DISTINGUISHEDNAME;
/*     */   }
/*     */   public static String getKEY_FACSIMILETELEPHONENUMBER() {
/* 127 */     return KEY_FACSIMILETELEPHONENUMBER;
/*     */   }
/*     */ 
/*     */   public static void setKEY_FACSIMILETELEPHONENUMBER(String kEY_FACSIMILETELEPHONENUMBER) {
/* 131 */     KEY_FACSIMILETELEPHONENUMBER = kEY_FACSIMILETELEPHONENUMBER;
/*     */   }
/*     */   public static String getKEY_HOMEPHONE() {
/* 134 */     return KEY_HOMEPHONE;
/*     */   }
/*     */   public static void setKEY_HOMEPHONE(String kEY_HOMEPHONE) {
/* 137 */     KEY_HOMEPHONE = kEY_HOMEPHONE;
/*     */   }
/*     */   public static String getKEY_INITIALS() {
/* 140 */     return KEY_INITIALS;
/*     */   }
/*     */   public static void setKEY_INITIALS(String kEY_INITIALS) {
/* 143 */     KEY_INITIALS = kEY_INITIALS;
/*     */   }
/*     */   public static String getKEY_L() {
/* 146 */     return KEY_L;
/*     */   }
/*     */   public static void setKEY_L(String kEY_L) {
/* 149 */     KEY_L = kEY_L;
/*     */   }
/*     */   public static String getKEY_MAIL() {
/* 152 */     return KEY_MAIL;
/*     */   }
/*     */   public static void setKEY_MAIL(String kEY_MAIL) {
/* 155 */     KEY_MAIL = kEY_MAIL;
/*     */   }
/*     */   public static String getKEY_NAME() {
/* 158 */     return KEY_NAME;
/*     */   }
/*     */   public static void setKEY_NAME(String kEY_NAME) {
/* 161 */     KEY_NAME = kEY_NAME;
/*     */   }
/*     */   public static String getKEY_POSTALADDRESS() {
/* 164 */     return KEY_POSTALADDRESS;
/*     */   }
/*     */   public static void setKEY_POSTALADDRESS(String kEY_POSTALADDRESS) {
/* 167 */     KEY_POSTALADDRESS = kEY_POSTALADDRESS;
/*     */   }
/*     */   public static String getKEY_POSTALCODE() {
/* 170 */     return KEY_POSTALCODE;
/*     */   }
/*     */   public static void setKEY_POSTALCODE(String kEY_POSTALCODE) {
/* 173 */     KEY_POSTALCODE = kEY_POSTALCODE;
/*     */   }
/*     */   public static String getKEY_POSTOFFICEBOX() {
/* 176 */     return KEY_POSTOFFICEBOX;
/*     */   }
/*     */   public static void setKEY_POSTOFFICEBOX(String kEY_POSTOFFICEBOX) {
/* 179 */     KEY_POSTOFFICEBOX = kEY_POSTOFFICEBOX;
/*     */   }
/*     */   public static String getKEY_SAMACCOUNTNAME() {
/* 182 */     return KEY_SAMACCOUNTNAME;
/*     */   }
/*     */   public static void setKEY_SAMACCOUNTNAME(String kEY_SAMACCOUNTNAME) {
/* 185 */     KEY_SAMACCOUNTNAME = kEY_SAMACCOUNTNAME;
/*     */   }
/*     */   public static String getKEY_SN() {
/* 188 */     return KEY_SN;
/*     */   }
/*     */   public static void setKEY_SN(String kEY_SN) {
/* 191 */     KEY_SN = kEY_SN;
/*     */   }
/*     */   public static String getKEY_ST() {
/* 194 */     return KEY_ST;
/*     */   }
/*     */   public static void setKEY_ST(String kEY_ST) {
/* 197 */     KEY_ST = kEY_ST;
/*     */   }
/*     */   public static String getKEY_STREETADDRESS() {
/* 200 */     return KEY_STREETADDRESS;
/*     */   }
/*     */   public static void setKEY_STREETADDRESS(String kEY_STREETADDRESS) {
/* 203 */     KEY_STREETADDRESS = kEY_STREETADDRESS;
/*     */   }
/*     */   public static String getKEY_TELEPHONENUMBER() {
/* 206 */     return KEY_TELEPHONENUMBER;
/*     */   }
/*     */   public static void setKEY_TELEPHONENUMBER(String kEY_TELEPHONENUMBER) {
/* 209 */     KEY_TELEPHONENUMBER = kEY_TELEPHONENUMBER;
/*     */   }
/*     */   public static String getKEY_TITLE() {
/* 212 */     return KEY_TITLE;
/*     */   }
/*     */   public static void setKEY_TITLE(String kEY_TITLE) {
/* 215 */     KEY_TITLE = kEY_TITLE;
/*     */   }
/*     */   public static String getKEY_USERPRINCIPALNAME() {
/* 218 */     return KEY_USERPRINCIPALNAME;
/*     */   }
/*     */   public static void setKEY_USERPRINCIPALNAME(String kEY_USERPRINCIPALNAME) {
/* 221 */     KEY_USERPRINCIPALNAME = kEY_USERPRINCIPALNAME;
/*     */   }
/*     */   public static String getKEY_WHENCHANGED() {
/* 224 */     return KEY_WHENCHANGED;
/*     */   }
/*     */   public static void setKEY_WHENCHANGED(String kEY_WHENCHANGED) {
/* 227 */     KEY_WHENCHANGED = kEY_WHENCHANGED;
/*     */   }
/*     */   public static String getKEY_WHENCREATED() {
/* 230 */     return KEY_WHENCREATED;
/*     */   }
/*     */   public static void setKEY_WHENCREATED(String kEY_WHENCREATED) {
/* 233 */     KEY_WHENCREATED = kEY_WHENCREATED;
/*     */   }
/*     */   public static String getKEY_WWWHOMEPAGE() {
/* 236 */     return KEY_WWWHOMEPAGE;
/*     */   }
/*     */   public static void setKEY_WWWHOMEPAGE(String kEY_WWWHOMEPAGE) {
/* 239 */     KEY_WWWHOMEPAGE = kEY_WWWHOMEPAGE;
/*     */   }
/*     */   public static String getOBJECTCATEGORY() {
/* 242 */     return OBJECTCATEGORY;
/*     */   }
/*     */   public static void setOBJECTCATEGORY(String oBJECTCATEGORY) {
/* 245 */     OBJECTCATEGORY = oBJECTCATEGORY;
/*     */   }
/*     */   public static String getOBJECTCLASS() {
/* 248 */     return OBJECTCLASS;
/*     */   }
/*     */   public static void setOBJECTCLASS(String oBJECTCLASS) {
/* 251 */     OBJECTCLASS = oBJECTCLASS;
/*     */   }
/*     */   public String getCn() {
/* 254 */     return this.cn;
/*     */   }
/*     */   public void setCn(String cn) {
/* 257 */     this.cn = cn;
/*     */   }
/*     */   public String getC() {
/* 260 */     return this.c;
/*     */   }
/*     */   public void setC(String c) {
/* 263 */     this.c = c;
/*     */   }
/*     */   public String getCo() {
/* 266 */     return this.co;
/*     */   }
/*     */   public void setCo(String co) {
/* 269 */     this.co = co;
/*     */   }
/*     */   public String getCompany() {
/* 272 */     return this.company;
/*     */   }
/*     */   public void setCompany(String company) {
/* 275 */     this.company = company;
/*     */   }
/*     */   public String getCountryCode() {
/* 278 */     return this.countryCode;
/*     */   }
/*     */   public void setCountryCode(String countryCode) {
/* 281 */     this.countryCode = countryCode;
/*     */   }
/*     */   public String getDepartment() {
/* 284 */     return this.department;
/*     */   }
/*     */   public void setDepartment(String department) {
/* 287 */     this.department = department;
/*     */   }
/*     */   public String getDescription() {
/* 290 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 293 */     this.description = description;
/*     */   }
/*     */   public String getDisplayName() {
/* 296 */     return this.displayName;
/*     */   }
/*     */   public void setDisplayName(String displayName) {
/* 299 */     this.displayName = displayName;
/*     */   }
/*     */   public String getDistinguishedName() {
/* 302 */     return this.distinguishedName;
/*     */   }
/*     */   public void setDistinguishedName(String distinguishedName) {
/* 305 */     this.distinguishedName = distinguishedName;
/*     */   }
/*     */   public String getFacsimileTelephoneNumber() {
/* 308 */     return this.facsimileTelephoneNumber;
/*     */   }
/*     */   public void setFacsimileTelephoneNumber(String facsimileTelephoneNumber) {
/* 311 */     this.facsimileTelephoneNumber = facsimileTelephoneNumber;
/*     */   }
/*     */   public String getHomePhone() {
/* 314 */     return this.homePhone;
/*     */   }
/*     */   public void setHomePhone(String homePhone) {
/* 317 */     this.homePhone = homePhone;
/*     */   }
/*     */   public String getInitials() {
/* 320 */     return this.initials;
/*     */   }
/*     */   public void setInitials(String initials) {
/* 323 */     this.initials = initials;
/*     */   }
/*     */   public String getL() {
/* 326 */     return this.l;
/*     */   }
/*     */   public void setL(String l) {
/* 329 */     this.l = l;
/*     */   }
/*     */   public String getMail() {
/* 332 */     return this.mail;
/*     */   }
/*     */   public void setMail(String mail) {
/* 335 */     this.mail = mail;
/*     */   }
/*     */   public String getName() {
/* 338 */     return this.name;
/*     */   }
/*     */   public String getGivenName() {
/* 341 */     return this.givenName;
/*     */   }
/*     */   public void setGivenName(String givenName) {
/* 344 */     this.givenName = givenName;
/*     */   }
/*     */   public void setName(String name) {
/* 347 */     this.name = name;
/*     */   }
/*     */   public String getPostalAddress() {
/* 350 */     return this.postalAddress;
/*     */   }
/*     */   public void setPostalAddress(String postalAddress) {
/* 353 */     this.postalAddress = postalAddress;
/*     */   }
/*     */   public String getPostalCode() {
/* 356 */     return this.postalCode;
/*     */   }
/*     */   public void setPostalCode(String postalCode) {
/* 359 */     this.postalCode = postalCode;
/*     */   }
/*     */   public String getPostOfficeBox() {
/* 362 */     return this.postOfficeBox;
/*     */   }
/*     */   public void setPostOfficeBox(String postOfficeBox) {
/* 365 */     this.postOfficeBox = postOfficeBox;
/*     */   }
/*     */   public String getsAMAccountName() {
/* 368 */     return this.sAMAccountName;
/*     */   }
/*     */   public void setsAMAccountName(String sAMAccountName) {
/* 371 */     this.sAMAccountName = sAMAccountName;
/*     */   }
/*     */   public String getSn() {
/* 374 */     return this.sn;
/*     */   }
/*     */   public void setSn(String sn) {
/* 377 */     this.sn = sn;
/*     */   }
/*     */   public String getSt() {
/* 380 */     return this.st;
/*     */   }
/*     */   public void setSt(String st) {
/* 383 */     this.st = st;
/*     */   }
/*     */   public String getStreetAddress() {
/* 386 */     return this.streetAddress;
/*     */   }
/*     */   public void setStreetAddress(String streetAddress) {
/* 389 */     this.streetAddress = streetAddress;
/*     */   }
/*     */   public String getTelephoneNumber() {
/* 392 */     return this.telephoneNumber;
/*     */   }
/*     */   public void setTelephoneNumber(String telephoneNumber) {
/* 395 */     this.telephoneNumber = telephoneNumber;
/*     */   }
/*     */   public String getTitle() {
/* 398 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/* 401 */     this.title = title;
/*     */   }
/*     */   public String getUserPrincipalName() {
/* 404 */     return this.userPrincipalName;
/*     */   }
/*     */   public void setUserPrincipalName(String userPrincipalName) {
/* 407 */     this.userPrincipalName = userPrincipalName;
/*     */   }
/*     */   public Date getWhenChanged() {
/* 410 */     return this.whenChanged;
/*     */   }
/*     */   public void setWhenChanged(Date whenChanged) {
/* 413 */     this.whenChanged = whenChanged;
/*     */   }
/*     */   public Date getWhenCreated() {
/* 416 */     return this.whenCreated;
/*     */   }
/*     */   public void setWhenCreated(Date whenCreated) {
/* 419 */     this.whenCreated = whenCreated;
/*     */   }
/*     */   public String getwWWHomePage() {
/* 422 */     return this.wWWHomePage;
/*     */   }
/*     */   public void setwWWHomePage(String wWWHomePage) {
/* 425 */     this.wWWHomePage = wWWHomePage;
/*     */   }
/*     */   public String getUserAccountControl() {
/* 428 */     return this.userAccountControl;
/*     */   }
/*     */   public void setUserAccountControl(String userAccountControl) {
/* 431 */     this.userAccountControl = userAccountControl;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 436 */     return "LdapUser [cn=" + this.cn + ", c=" + this.c + ", co=" + this.co + ", company=" + this.company + ", countryCode=" + this.countryCode + ", department=" + this.department + ", description=" + this.description + ", displayName=" + this.displayName + ", distinguishedName=" + this.distinguishedName + ", facsimileTelephoneNumber=" + this.facsimileTelephoneNumber + ", homePhone=" + this.homePhone + ", initials=" + this.initials + ", l=" + this.l + ", mail=" + this.mail + ", name=" + this.name + ", postalAddress=" + this.postalAddress + ", postalCode=" + this.postalCode + ", postOfficeBox=" + this.postOfficeBox + ", sAMAccountName=" + this.sAMAccountName + ", sn=" + this.sn + ", st=" + this.st + ", streetAddress=" + this.streetAddress + ", telephoneNumber=" + this.telephoneNumber + ", title=" + this.title + ", userPrincipalName=" + this.userPrincipalName + ", whenChanged=" + this.whenChanged + ", whenCreated=" + this.whenCreated + ", wWWHomePage=" + this.wWWHomePage + "]";
/*     */   }
/*     */ 
/*     */   public boolean isAccountDisable()
/*     */   {
/* 458 */     int accountControl = Integer.parseInt(this.userAccountControl);
/* 459 */     int num = getDigitHex(accountControl, 0);
/* 460 */     num -= 2;
/* 461 */     boolean result = num == 9;
/* 462 */     result = (result) || (num == 8);
/* 463 */     result = (result) || (num == 1);
/* 464 */     result = (result) || (num == 0);
/* 465 */     return result;
/*     */   }
/*     */ 
/*     */   private int getDigitHex(int value, int offset) {
/* 469 */     char[] arr = Integer.toHexString(value).toCharArray();
/* 470 */     offset = arr.length - 1 - offset;
/* 471 */     int intValue = Integer.parseInt(String.valueOf(arr[offset]));
/* 472 */     return intValue;
/*     */   }
/*     */ 
/*     */   public Object clone() throws CloneNotSupportedException
/*     */   {
/* 477 */     LdapUser obj = null;
/*     */     try {
/* 479 */       obj = (LdapUser)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 481 */       e.printStackTrace();
/*     */     }
/* 483 */     return obj;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.model.LdapUser
 * JD-Core Version:    0.6.2
 */