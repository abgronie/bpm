package com.hotent.core.ldap.dao;

import com.hotent.core.ldap.model.LdapGroup;
import java.util.List;
import org.springframework.ldap.core.DistinguishedName;

public abstract interface LdapGroupDao
{
  public abstract List<LdapGroup> getAll();

  public abstract List<LdapGroup> getByDN(DistinguishedName paramDistinguishedName);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.dao.LdapGroupDao
 * JD-Core Version:    0.6.2
 */