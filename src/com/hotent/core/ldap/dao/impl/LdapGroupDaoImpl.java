/*    */ package com.hotent.core.ldap.dao.impl;
/*    */ 
/*    */ import com.hotent.core.ldap.dao.LdapGroupDao;
/*    */ import com.hotent.core.ldap.map.LdapGroupAssemabller;
/*    */ import com.hotent.core.ldap.model.LdapGroup;
/*    */ import com.hotent.core.util.StringUtil;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import javax.naming.InvalidNameException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.ldap.core.ContextMapper;
/*    */ import org.springframework.ldap.core.DistinguishedName;
/*    */ import org.springframework.ldap.core.LdapTemplate;
/*    */ import org.springframework.ldap.filter.EqualsFilter;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LdapGroupDaoImpl
/*    */   implements LdapGroupDao
/*    */ {
/* 23 */   Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   @Resource
/*    */   LdapTemplate ldapTemplate;
/*    */ 
/* 29 */   public List<LdapGroup> getAll() { return getByDN(getDn("")); }
/*    */ 
/*    */ 
/*    */   public List<LdapGroup> getByDN(DistinguishedName dn)
/*    */   {
/* 35 */     EqualsFilter filter = new EqualsFilter("objectcategory", LdapGroup.OBJECTCLASS);
/* 36 */     logLdapQuey(filter.encode(), new Object[0]);
/*    */     try {
/* 38 */       return this.ldapTemplate.search(dn, filter.encode(), getContextMapper());
/*    */     } catch (Exception e) {
/* 40 */       e.printStackTrace();
/* 41 */     }return null;
/*    */   }
/*    */ 
/*    */   private ContextMapper getContextMapper()
/*    */   {
/* 48 */     return new LdapGroupAssemabller();
/*    */   }
/*    */ 
/*    */   private DistinguishedName getDn(String dnstr) {
/* 52 */     DistinguishedName dn = new DistinguishedName();
/*    */     try {
/* 54 */       if (!StringUtil.isEmpty(dnstr))
/* 55 */         dn.add(dnstr);
/*    */     }
/*    */     catch (InvalidNameException e) {
/* 58 */       e.printStackTrace();
/*    */     }
/* 60 */     return dn;
/*    */   }
/*    */ 
/*    */   private void logLdapQuey(String message, Object[] args) {
/* 64 */     String formatStr = String.format(message, args);
/* 65 */     this.logger.info("LDAP query statement:" + formatStr);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.impl.LdapGroupDaoImpl
 * JD-Core Version:    0.6.2
 */