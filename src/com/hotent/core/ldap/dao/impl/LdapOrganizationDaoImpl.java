/*     */ package com.hotent.core.ldap.dao.impl;
/*     */ 
/*     */ import com.hotent.core.ldap.dao.LdapOrganizationDao;
/*     */ import com.hotent.core.ldap.map.LdapOrganizationAssembler;
/*     */ import com.hotent.core.ldap.model.LdapOrganization;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.naming.InvalidNameException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.ldap.core.ContextMapper;
/*     */ import org.springframework.ldap.core.DistinguishedName;
/*     */ import org.springframework.ldap.core.LdapTemplate;
/*     */ import org.springframework.ldap.filter.AndFilter;
/*     */ import org.springframework.ldap.filter.EqualsFilter;
/*     */ import org.springframework.ldap.filter.Filter;
/*     */ import org.springframework.ldap.filter.LikeFilter;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class LdapOrganizationDaoImpl
/*     */   implements LdapOrganizationDao
/*     */ {
/*  26 */   Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   @Resource
/*     */   LdapTemplate ldapTemplate;
/*     */ 
/*     */   public List<LdapOrganization> getAll() {
/*  33 */     return get(getDn(""));
/*     */   }
/*     */ 
/*     */   public List<LdapOrganization> get(DistinguishedName dn)
/*     */   {
/*  38 */     EqualsFilter filter = new EqualsFilter("objectcategory", LdapOrganization.OBJECTCLASS);
/*  39 */     logLdapQuey(filter.encode(), new Object[0]);
/*     */     try {
/*  41 */       return this.ldapTemplate.search(dn, filter.encode(), getContextMapper());
/*     */     } catch (Exception e) {
/*  43 */       e.printStackTrace();
/*  44 */     }return null;
/*     */   }
/*     */ 
/*     */   public List<LdapOrganization> get(Filter filter)
/*     */   {
/*  50 */     return get(filter, getDn(null));
/*     */   }
/*     */ 
/*     */   public List<LdapOrganization> get(Filter filter, DistinguishedName dn)
/*     */   {
/*  56 */     AndFilter andFilter = new AndFilter();
/*  57 */     andFilter.and(new EqualsFilter("objectcategory", LdapOrganization.OBJECTCLASS));
/*  58 */     andFilter.and(filter);
/*  59 */     logLdapQuey(andFilter.encode(), new Object[0]);
/*     */     try {
/*  61 */       return this.ldapTemplate.search(dn, andFilter.encode(), getContextMapper());
/*     */     } catch (Exception e) {
/*  63 */       e.printStackTrace();
/*  64 */     }return null;
/*     */   }
/*     */ 
/*     */   public List<LdapOrganization> getByName(String orgName)
/*     */   {
/*  73 */     AndFilter filter = new AndFilter();
/*  74 */     filter.and(new EqualsFilter("objectcategory", LdapOrganization.OBJECTCLASS));
/*  75 */     filter.and(new LikeFilter(LdapOrganization.KEY_NAME, orgName));
/*  76 */     logLdapQuey(filter.encode(), new Object[0]);
/*  77 */     return this.ldapTemplate.search(getDn(""), filter.encode(), getContextMapper());
/*     */   }
/*     */ 
/*     */   private ContextMapper getContextMapper()
/*     */   {
/*  85 */     return new LdapOrganizationAssembler();
/*     */   }
/*     */ 
/*     */   private DistinguishedName getDn(String dnstr) {
/*  89 */     DistinguishedName dn = new DistinguishedName();
/*     */     try {
/*  91 */       if (!StringUtil.isEmpty(dnstr))
/*  92 */         dn.add(dnstr);
/*     */     }
/*     */     catch (InvalidNameException e) {
/*  95 */       e.printStackTrace();
/*     */     }
/*  97 */     return dn;
/*     */   }
/*     */ 
/*     */   private void logLdapQuey(String message, Object[] args) {
/* 101 */     String formatStr = String.format(message, args);
/* 102 */     this.logger.info("LDAP query statement:" + formatStr);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.impl.LdapOrganizationDaoImpl
 * JD-Core Version:    0.6.2
 */