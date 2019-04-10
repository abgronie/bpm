/*     */ package com.hotent.core.ldap.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class LdapGroup
/*     */ {
/*     */   public static final String KEY_ADMINCOUNT = "adminCount";
/*     */   public static final String KEY_CN = "cn";
/*     */   public static final String KEY_DESCRIPTIONT = "description";
/*     */   public static final String KEY_DISTINGUISHEDNAME = "distinguishedName";
/*     */   public static final String KEY_INFO = "info";
/*     */   public static final String KEY_MAIL = "mail";
/*     */   public static final String KEY_MEMBER = "member";
/*     */   public static final String KEY_NAME = "name";
/*     */   public static final String KEY_SAMACCOUNTNAME = "sAMAccountName";
/*     */   public static final String KEY_SAMACCOUNTTYPE = "sAMAccountType";
/*     */   public static final String KEY_WHENCHANGED = "whenChanged";
/*     */   public static final String KEY_WHENCREATED = "whenCreated";
/*  18 */   public static String OBJECTCATEGORY = "group";
/*  19 */   public static String OBJECTCLASS = "group";
/*     */   private String adminCount;
/*     */   private String cn;
/*     */   private String description;
/*     */   private String distinguishedName;
/*     */   private String info;
/*     */   private String mail;
/*     */   private String[] members;
/*     */   private String name;
/*     */   private String sAMAccountName;
/*     */   private String sAMAccountType;
/*     */   private Date whenChanged;
/*     */   private Date whenCreated;
/*     */ 
/*     */   public String getAdminCount()
/*     */   {
/*  36 */     return this.adminCount;
/*     */   }
/*     */   public void setAdminCount(String adminCount) {
/*  39 */     this.adminCount = adminCount;
/*     */   }
/*     */   public String getCn() {
/*  42 */     return this.cn;
/*     */   }
/*     */   public void setCn(String cn) {
/*  45 */     this.cn = cn;
/*     */   }
/*     */   public String getDescription() {
/*  48 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  51 */     this.description = description;
/*     */   }
/*     */   public String getDistinguishedName() {
/*  54 */     return this.distinguishedName;
/*     */   }
/*     */   public void setDistinguishedName(String distinguishedName) {
/*  57 */     this.distinguishedName = distinguishedName;
/*     */   }
/*     */   public String getInfo() {
/*  60 */     return this.info;
/*     */   }
/*     */   public void setInfo(String info) {
/*  63 */     this.info = info;
/*     */   }
/*     */   public String getMail() {
/*  66 */     return this.mail;
/*     */   }
/*     */   public void setMail(String mail) {
/*  69 */     this.mail = mail;
/*     */   }
/*     */   public String[] getMembers() {
/*  72 */     return this.members;
/*     */   }
/*     */   public void setMembers(String[] members) {
/*  75 */     this.members = members;
/*     */   }
/*     */   public String getName() {
/*  78 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  81 */     this.name = name;
/*     */   }
/*     */   public String getsAMAccountName() {
/*  84 */     return this.sAMAccountName;
/*     */   }
/*     */   public void setsAMAccountName(String sAMAccountName) {
/*  87 */     this.sAMAccountName = sAMAccountName;
/*     */   }
/*     */   public String getsAMAccountType() {
/*  90 */     return this.sAMAccountType;
/*     */   }
/*     */   public void setsAMAccountType(String sAMAccountType) {
/*  93 */     this.sAMAccountType = sAMAccountType;
/*     */   }
/*     */   public Date getWhenChanged() {
/*  96 */     return this.whenChanged;
/*     */   }
/*     */   public void setWhenChanged(Date whenChanged) {
/*  99 */     this.whenChanged = whenChanged;
/*     */   }
/*     */   public Date getWhenCreated() {
/* 102 */     return this.whenCreated;
/*     */   }
/*     */   public void setWhenCreated(Date whenCreated) {
/* 105 */     this.whenCreated = whenCreated;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.model.LdapGroup
 * JD-Core Version:    0.6.2
 */