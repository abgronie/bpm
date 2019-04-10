package com.hotent.core.ldap.dao;

import com.hotent.core.ldap.model.LdapOrganizationalUnit;
import java.util.List;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.Filter;

public abstract interface LdapOrganizationalUnitDao
{
  public abstract List<LdapOrganizationalUnit> getAll();

  public abstract List<LdapOrganizationalUnit> get(DistinguishedName paramDistinguishedName);

  public abstract List<LdapOrganizationalUnit> get(Filter paramFilter, DistinguishedName paramDistinguishedName);

  public abstract List<LdapOrganizationalUnit> get(Filter paramFilter);

  public abstract List<LdapOrganizationalUnit> getByName(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.LdapOrganizationalUnitDao
 * JD-Core Version:    0.6.2
 */