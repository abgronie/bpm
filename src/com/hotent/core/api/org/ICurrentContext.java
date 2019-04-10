package com.hotent.core.api.org;

import com.hotent.core.api.org.model.IPosition;
import com.hotent.core.api.org.model.ISysOrg;
import com.hotent.core.api.org.model.ISysUser;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

public abstract interface ICurrentContext
{
  public abstract ISysUser getCurrentUser();

  public abstract Locale getLocale();

  public abstract void setLocale(Locale paramLocale);

  public abstract Long getCurrentUserId();

  public abstract void setCurrentUserAccount(String paramString);

  public abstract void setCurrentUser(ISysUser paramISysUser);

  public abstract void setCurrentPos(Long paramLong);

  public abstract ISysOrg getCurrentOrg();

  public abstract ISysOrg getCurrentCompany();

  public abstract Long getCurrentCompanyId();

  public abstract IPosition getCurrentPos();

  public abstract Long getCurrentPosId();

  public abstract Long getCurrentOrgId();

  public abstract String getCurrentUserSkin(HttpServletRequest paramHttpServletRequest);

  public abstract void cleanCurUser();

  public abstract void removeCurrentOrg();

  public abstract void clearAll();

  public abstract void removeCurrentUser();

  public abstract boolean isSuperAdmin();

  public abstract boolean isSuperAdmin(ISysUser paramISysUser);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.org.ICurrentContext
 * JD-Core Version:    0.6.2
 */