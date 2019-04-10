/*     */ package com.hotent.core.ldap.dao.impl;
/*     */ 
/*     */ import com.hotent.core.ldap.dao.LdapUserDao;
/*     */ import com.hotent.core.ldap.map.LdapUserAssembler;
/*     */ import com.hotent.core.ldap.model.LdapUser;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.naming.InvalidNameException;
/*     */ import javax.naming.directory.SearchControls;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.ldap.control.PagedResult;
/*     */ import org.springframework.ldap.control.PagedResultsCookie;
/*     */ import org.springframework.ldap.control.PagedResultsDirContextProcessor;
/*     */ import org.springframework.ldap.core.ContextAssembler;
/*     */ import org.springframework.ldap.core.DirContextAdapter;
/*     */ import org.springframework.ldap.core.DistinguishedName;
/*     */ import org.springframework.ldap.core.LdapTemplate;
/*     */ import org.springframework.ldap.filter.AndFilter;
/*     */ import org.springframework.ldap.filter.EqualsFilter;
/*     */ import org.springframework.ldap.filter.Filter;
/*     */ import org.springframework.ldap.filter.LikeFilter;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class LdapUserDaoImpl
/*     */   implements LdapUserDao
/*     */ {
/*  35 */   Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   @Resource
/*     */   LdapTemplate ldapTemplate;
/*     */ 
/*  41 */   public List<LdapUser> getAll() { return get(getDn("")); }
/*     */ 
/*     */ 
/*     */   public List<LdapUser> getAll(Map<String, Object> params)
/*     */   {
/*  46 */     AndFilter filter = new AndFilter();
/*  47 */     if (BeanUtils.isNotEmpty(params)) {
/*  48 */       Iterator keys = params.keySet().iterator();
/*  49 */       while (keys.hasNext()) {
/*  50 */         String key = (String)keys.next();
/*  51 */         filter.and(new LikeFilter(key, params.get(key) + "*"));
/*     */       }
/*     */     }
/*     */ 
/*  55 */     logLdapQuey(filter.encode(), new Object[0]);
/*  56 */     return get(filter, getDn(""));
/*     */   }
/*     */ 
/*     */   public List<LdapUser> get()
/*     */   {
/*  67 */     List ldapUserList = new ArrayList();
/*  68 */     int pageSize = 100;
/*  69 */     PagedResult pagedResult = get(null, pageSize);
/*  70 */     List users = pagedResult.getResultList();
/*  71 */     ldapUserList.addAll(users);
/*  72 */     byte[] bytesCookie = pagedResult.getCookie().getCookie();
/*  73 */     while ((users.size() == pageSize) && (bytesCookie != null)) {
/*  74 */       pagedResult = get(pagedResult.getCookie(), pageSize);
/*  75 */       users = pagedResult.getResultList();
/*  76 */       ldapUserList.addAll(users);
/*  77 */       bytesCookie = pagedResult.getCookie().getCookie();
/*     */     }
/*  79 */     return ldapUserList;
/*     */   }
/*     */ 
/*     */   public PagedResult get(PagedResultsCookie cookie, int pageSize)
/*     */   {
/*  90 */     PagedResultsDirContextProcessor pagePagedResultsDirContextProcessor = new PagedResultsDirContextProcessor(pageSize, cookie);
/*     */ 
/*  92 */     SearchControls searchControls = new SearchControls();
/*  93 */     searchControls.setSearchScope(2);
/*  94 */     AndFilter filter = new AndFilter();
/*  95 */     filter.and(new EqualsFilter("objectclass", LdapUser.OBJECTCLASS));
/*     */ 
/*  97 */     List list = this.ldapTemplate.search(getDn(null), filter.encode(), searchControls, getContextMapper(), pagePagedResultsDirContextProcessor);
/*     */ 
/*  99 */     PagedResult pageResult = new PagedResult(list, pagePagedResultsDirContextProcessor.getCookie());
/*     */ 
/* 101 */     return pageResult;
/*     */   }
/*     */ 
/*     */   public List<LdapUser> get(Filter filter) {
/* 105 */     return get(filter, getDn(null));
/*     */   }
/*     */ 
/*     */   public List<LdapUser> get(DistinguishedName dn)
/*     */   {
/* 110 */     EqualsFilter filter = new EqualsFilter("objectcategory", LdapUser.OBJECTCLASS);
/* 111 */     logLdapQuey(filter.encode(), new Object[0]);
/* 112 */     return get(filter, dn);
/*     */   }
/*     */ 
/*     */   public List<LdapUser> get(Filter filter, DistinguishedName dn)
/*     */   {
/* 120 */     List ldapUserList = new ArrayList();
/* 121 */     int pageSize = 2;
/* 122 */     logLdapQuey(filter.encode(), new Object[0]);
/* 123 */     PagedResult pagedResult = get(filter, getDn(null), null, pageSize);
/* 124 */     List users = pagedResult.getResultList();
/* 125 */     ldapUserList.addAll(users);
/* 126 */     while (users.size() == pageSize) {
/* 127 */       pagedResult = get(filter, getDn(null), pagedResult.getCookie(), pageSize);
/* 128 */       users = pagedResult.getResultList();
/* 129 */       ldapUserList.addAll(users);
/*     */     }
/* 131 */     return ldapUserList;
/*     */   }
/*     */ 
/*     */   public PagedResult get(Filter filter, DistinguishedName dn, PagedResultsCookie cookie, int pageSize)
/*     */   {
/* 138 */     PagedResultsDirContextProcessor pagePagedResultsDirContextProcessor = new PagedResultsDirContextProcessor(pageSize, cookie);
/*     */ 
/* 140 */     SearchControls searchControls = new SearchControls();
/* 141 */     searchControls.setSearchScope(2);
/* 142 */     AndFilter andfilter = new AndFilter();
/* 143 */     andfilter.and(filter);
/* 144 */     andfilter.and(new EqualsFilter("objectclass", LdapUser.OBJECTCLASS));
/*     */ 
/* 146 */     List list = this.ldapTemplate.search(dn, andfilter.encode(), searchControls, getContextMapper(), pagePagedResultsDirContextProcessor);
/*     */ 
/* 148 */     PagedResult pageResult = new PagedResult(list, pagePagedResultsDirContextProcessor.getCookie());
/*     */ 
/* 150 */     return pageResult;
/*     */   }
/*     */ 
/*     */   public void addUser(LdapUser user)
/*     */   {
/* 155 */     DirContextAdapter ctx = new DirContextAdapter(getDn("ou=HR,ou=hotent"));
/* 156 */     getContextMapper().mapToContext(user, ctx);
/* 157 */     this.ldapTemplate.bind(ctx);
/*     */   }
/*     */ 
/*     */   public boolean authenticate(String userId, String password)
/*     */   {
/* 162 */     AndFilter filter = new AndFilter();
/* 163 */     filter.and(new EqualsFilter("objectcategory", LdapUser.OBJECTCLASS)).and(new EqualsFilter(LdapUser.KEY_SAMACCOUNTNAME, userId));
/*     */ 
/* 165 */     boolean authenticated = this.ldapTemplate.authenticate(getDn(null), filter.encode(), password);
/* 166 */     return authenticated;
/*     */   }
/*     */ 
/*     */   private ContextAssembler getContextMapper()
/*     */   {
/* 172 */     return new LdapUserAssembler();
/*     */   }
/*     */ 
/*     */   private DistinguishedName getDn(String dnstr) {
/* 176 */     DistinguishedName dn = new DistinguishedName();
/*     */     try {
/* 178 */       if (!StringUtil.isEmpty(dnstr))
/* 179 */         dn.addAll(new DistinguishedName(dnstr));
/*     */     }
/*     */     catch (InvalidNameException e) {
/* 182 */       e.printStackTrace();
/*     */     }
/* 184 */     this.logger.info(dn.encode());
/* 185 */     return dn;
/*     */   }
/*     */ 
/*     */   private void logLdapQuey(String message, Object[] args)
/*     */   {
/* 191 */     String formatStr = String.format(message, args);
/* 192 */     this.logger.info("LDAP query statement:" + formatStr);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.impl.LdapUserDaoImpl
 * JD-Core Version:    0.6.2
 */