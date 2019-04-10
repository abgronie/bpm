package com.hotent.core.ldap.dao;

import com.hotent.core.ldap.model.LdapUser;
import java.util.List;
import java.util.Map;
import org.springframework.ldap.control.PagedResult;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.Filter;

public abstract interface LdapUserDao
{
  public abstract List<LdapUser> getAll();

  public abstract List<LdapUser> get();

  public abstract List<LdapUser> get(Filter paramFilter);

  public abstract List<LdapUser> get(DistinguishedName paramDistinguishedName);

  public abstract List<LdapUser> get(Filter paramFilter, DistinguishedName paramDistinguishedName);

  public abstract PagedResult get(PagedResultsCookie paramPagedResultsCookie, int paramInt);

  public abstract boolean authenticate(String paramString1, String paramString2);

  public abstract void addUser(LdapUser paramLdapUser);

  public abstract List<LdapUser> getAll(Map<String, Object> paramMap);

  public abstract PagedResult get(Filter paramFilter, DistinguishedName paramDistinguishedName, PagedResultsCookie paramPagedResultsCookie, int paramInt);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.LdapUserDao
 * JD-Core Version:    0.6.2
 */