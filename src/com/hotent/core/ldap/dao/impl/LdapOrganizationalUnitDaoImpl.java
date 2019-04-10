/*    */ package com.hotent.core.ldap.dao.impl;
/*    */ 
/*    */ import com.hotent.core.ldap.dao.LdapOrganizationalUnitDao;
/*    */ import com.hotent.core.ldap.map.LdapOrganizationalUnitAssembler;
/*    */ import com.hotent.core.ldap.model.LdapOrganizationalUnit;
/*    */ import com.hotent.core.util.StringUtil;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import javax.naming.InvalidNameException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.ldap.core.ContextMapper;
/*    */ import org.springframework.ldap.core.DistinguishedName;
/*    */ import org.springframework.ldap.core.LdapTemplate;
/*    */ import org.springframework.ldap.filter.AndFilter;
/*    */ import org.springframework.ldap.filter.EqualsFilter;
/*    */ import org.springframework.ldap.filter.Filter;
/*    */ import org.springframework.ldap.filter.LikeFilter;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LdapOrganizationalUnitDaoImpl
/*    */   implements LdapOrganizationalUnitDao
/*    */ {
/* 26 */   Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   @Resource
/*    */   LdapTemplate ldapTemplate;
/*    */ 
/* 32 */   public List<LdapOrganizationalUnit> getAll() { return get(getDn("")); }
/*    */ 
/*    */ 
/*    */   public List<LdapOrganizationalUnit> get(DistinguishedName dn)
/*    */   {
/* 38 */     EqualsFilter filter = new EqualsFilter("objectcategory", LdapOrganizationalUnit.OBJECTCLASS);
/* 39 */     logLdapQuey(filter.encode(), new Object[0]);
/* 40 */     return this.ldapTemplate.search(dn, filter.encode(), getContextMapper());
/*    */   }
/*    */ 
/*    */   public List<LdapOrganizationalUnit> getByName(String orgName)
/*    */   {
/* 48 */     AndFilter filter = new AndFilter();
/* 49 */     filter.and(new EqualsFilter("objectcategory", LdapOrganizationalUnit.OBJECTCLASS));
/* 50 */     filter.and(new LikeFilter(LdapOrganizationalUnit.KEY_NAME, orgName));
/* 51 */     logLdapQuey(filter.encode(), new Object[0]);
/* 52 */     return this.ldapTemplate.search(getDn(""), filter.encode(), getContextMapper());
/*    */   }
/*    */ 
/*    */   public List<LdapOrganizationalUnit> get(Filter filter, DistinguishedName dn)
/*    */   {
/* 62 */     AndFilter andFilter = new AndFilter();
/* 63 */     andFilter.and(new EqualsFilter("objectcategory", LdapOrganizationalUnit.OBJECTCLASS));
/* 64 */     andFilter.and(filter);
/* 65 */     logLdapQuey(andFilter.encode(), new Object[0]);
/*    */     try {
/* 67 */       return this.ldapTemplate.search(dn, andFilter.encode(), getContextMapper());
/*    */     } catch (Exception e) {
/* 69 */       e.printStackTrace();
/* 70 */     }return null;
/*    */   }
/*    */ 
/*    */   public List<LdapOrganizationalUnit> get(Filter filter)
/*    */   {
/* 76 */     return get(filter, getDn(null));
/*    */   }
/*    */ 
/*    */   private ContextMapper getContextMapper() {
/* 80 */     return new LdapOrganizationalUnitAssembler();
/*    */   }
/*    */ 
/*    */   private DistinguishedName getDn(String dnstr) {
/* 84 */     DistinguishedName dn = new DistinguishedName();
/*    */     try {
/* 86 */       if (!StringUtil.isEmpty(dnstr))
/* 87 */         dn.add(dnstr);
/*    */     }
/*    */     catch (InvalidNameException e) {
/* 90 */       e.printStackTrace();
/*    */     }
/* 92 */     return dn;
/*    */   }
/*    */ 
/*    */   private void logLdapQuey(String message, Object[] args) {
/* 96 */     String formatStr = String.format(message, args);
/* 97 */     this.logger.info("LDAP query statement:" + formatStr);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.impl.LdapOrganizationalUnitDaoImpl
 * JD-Core Version:    0.6.2
 */