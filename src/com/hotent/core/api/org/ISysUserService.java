package com.hotent.core.api.org;

import com.hotent.core.api.org.model.ISysUser;
import java.util.List;

public abstract interface ISysUserService
{
  public abstract ISysUser getById(Long paramLong);

  public abstract ISysUser getByAccount(String paramString);

  public abstract List<ISysUser> getByGroup(Long paramLong, String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.org.ISysUserService
 * JD-Core Version:    0.6.2
 */