package com.hotent.core.ldap.dao;

import com.hotent.core.ldap.model.LdapOrganization;
import java.util.List;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.Filter;

public abstract interface LdapOrganizationDao
{
  public abstract List<LdapOrganization> getAll();

  public abstract List<LdapOrganization> get(DistinguishedName paramDistinguishedName);

  public abstract List<LdapOrganization> get(Filter paramFilter);

  public abstract List<LdapOrganization> get(Filter paramFilter, DistinguishedName paramDistinguishedName);

  public abstract List<LdapOrganization> getByName(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.LdapOrganizationDao
 * JD-Core Version:    0.6.2
 */