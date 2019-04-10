/*     */ package com.hotent.core.ldap;
/*     */ 
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.naming.NamingEnumeration;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.directory.Attribute;
/*     */ import javax.naming.directory.Attributes;
/*     */ import javax.naming.directory.SearchControls;
/*     */ import javax.naming.directory.SearchResult;
/*     */ import javax.naming.ldap.InitialLdapContext;
/*     */ import javax.naming.ldap.LdapContext;
/*     */ 
/*     */ public class LdapService
/*     */ {
/*     */   private LdapSettingModel ldapSettingModel;
/*     */ 
/*     */   public LdapSettingModel getLdapSettingModel()
/*     */   {
/*  29 */     return this.ldapSettingModel;
/*     */   }
/*     */ 
/*     */   public void setLdapSettingModel(LdapSettingModel ldapSettingModel) {
/*  33 */     this.ldapSettingModel = ldapSettingModel;
/*     */   }
/*     */ 
/*     */   public boolean login(String account, String password)
/*     */   {
/*     */     try
/*     */     {
/*  44 */       Hashtable env = getEnvironment(account, password);
/*  45 */       LdapContext ctx = new InitialLdapContext(env, null);
/*  46 */       ctx.close();
/*  47 */       return true;
/*     */     } catch (NamingException err) {
/*  49 */       err.printStackTrace();
/*  50 */     }return false;
/*     */   }
/*     */ 
/*     */   public void syncUser()
/*     */   {
/*  58 */     List list = getLdapObjects(true);
/*  59 */     LdapObjectEvent events = new LdapObjectEvent(list);
/*  60 */     events.setUser(true);
/*  61 */     AppUtil.publishEvent(events);
/*     */   }
/*     */ 
/*     */   public void syncOrg()
/*     */   {
/*  68 */     List list = getLdapObjects(false);
/*  69 */     LdapObjectEvent events = new LdapObjectEvent(list);
/*  70 */     events.setUser(false);
/*  71 */     AppUtil.publishEvent(events);
/*     */   }
/*     */ 
/*     */   private List getLdapObjects(boolean isUser) {
/*  75 */     Hashtable env = getEnvironment();
/*  76 */     List list = new ArrayList();
/*     */     try {
/*  78 */       LdapContext ctx = new InitialLdapContext(env, null);
/*     */ 
/*  80 */       List<String> arySearchScope = getSearchScope();
/*  81 */       for (String scope : arySearchScope) {
/*  82 */         List tmplist = getByScope(isUser, ctx, scope);
/*  83 */         list.addAll(tmplist);
/*     */       }
/*     */ 
/*  86 */       ctx.close();
/*     */     } catch (NamingException e) {
/*  88 */       e.printStackTrace();
/*  89 */       System.err.println("Throw Exception : " + e);
/*     */     }
/*  91 */     return list;
/*     */   }
/*     */ 
/*     */   private List<String> getSearchScope()
/*     */   {
/*  96 */     String str = this.ldapSettingModel.getOrgNames();
/*  97 */     String domain = this.ldapSettingModel.getLdapBase();
/*  98 */     List list = new ArrayList();
/*  99 */     if (StringUtil.isEmpty(str)) {
/* 100 */       list.add(domain);
/*     */     }
/*     */     else {
/* 103 */       String[] aryOrgs = str.split("[,]");
/* 104 */       for (String tmp : aryOrgs) {
/* 105 */         String ou = "OU=" + tmp + "," + domain;
/* 106 */         list.add(ou);
/*     */       }
/*     */     }
/* 109 */     return list;
/*     */   }
/*     */ 
/*     */   private List<Map<String, String>> getByScope(boolean isUser, LdapContext ctx, String searchBase)
/*     */     throws NamingException
/*     */   {
/* 117 */     List list = new ArrayList();
/*     */ 
/* 119 */     String searchFilter = isUser ? "(&(objectClass=user))" : "(&(objectClass=organizationalUnit))";
/*     */ 
/* 122 */     SearchControls searchCtls = new SearchControls();
/*     */ 
/* 124 */     searchCtls.setSearchScope(2);
/*     */ 
/* 126 */     String fields = isUser ? this.ldapSettingModel.getUserFields() : this.ldapSettingModel.getOrgFields();
/* 127 */     String[] returnedAtts = fields.split("[.]");
/*     */ 
/* 129 */     searchCtls.setReturningAttributes(returnedAtts);
/*     */ 
/* 131 */     NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);
/* 132 */     while (answer.hasMoreElements()) {
/* 133 */       SearchResult sr = (SearchResult)answer.next();
/*     */ 
/* 135 */       Attributes attrs = sr.getAttributes();
/* 136 */       if (attrs != null) {
/* 137 */         Map entMap = new HashMap();
/*     */ 
/* 139 */         for (NamingEnumeration ne = attrs.getAll(); ne.hasMore(); ) {
/* 140 */           Attribute attr = (Attribute)ne.next();
/* 141 */           String key = attr.getID();
/* 142 */           String val = attr.get().toString();
/* 143 */           entMap.put(key, val);
/*     */         }
/* 145 */         list.add(entMap);
/*     */       }
/*     */     }
/* 147 */     return list;
/*     */   }
/*     */ 
/*     */   private Hashtable getEnvironment()
/*     */   {
/* 152 */     String account = this.ldapSettingModel.getUserDn();
/* 153 */     String url = new String(this.ldapSettingModel.getUrl());
/* 154 */     Hashtable env = new Hashtable();
/* 155 */     env.put("java.naming.security.authentication", "simple");
/* 156 */     env.put("java.naming.security.principal", account);
/* 157 */     env.put("java.naming.security.credentials", this.ldapSettingModel.getPassword());
/* 158 */     env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
/* 159 */     env.put("java.naming.provider.url", url);
/* 160 */     return env;
/*     */   }
/*     */ 
/*     */   private Hashtable getEnvironment(String account, String password) {
/* 164 */     int idx = this.ldapSettingModel.getUserDn().indexOf("@");
/* 165 */     account = account + this.ldapSettingModel.getUserDn().substring(idx);
/* 166 */     String url = new String(this.ldapSettingModel.getUrl());
/* 167 */     Hashtable env = new Hashtable();
/* 168 */     env.put("java.naming.security.authentication", "simple");
/* 169 */     env.put("java.naming.security.principal", account);
/* 170 */     env.put("java.naming.security.credentials", password);
/* 171 */     env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
/* 172 */     env.put("java.naming.provider.url", url);
/* 173 */     return env;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.LdapService
 * JD-Core Version:    0.6.2
 */