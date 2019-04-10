package com.hotent.core.db;

import com.hotent.core.page.PageBean;
import com.hotent.core.web.query.QueryFilter;
import java.io.Serializable;
import java.util.List;

public abstract interface IEntityDao<E, PK extends Serializable>
{
  public abstract void add(E paramE);

  public abstract int delById(PK paramPK);

  public abstract int update(E paramE);

  public abstract Object getById(PK paramPK);

  public abstract List<E> getList(String paramString, Object paramObject);

  public abstract List<E> getList(String paramString, Object paramObject, PageBean paramPageBean);

  public abstract List<E> getAll();

  public abstract List<E> getAll(QueryFilter paramQueryFilter);

  public abstract E getUnique(String paramString, Object paramObject);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.IEntityDao
 * JD-Core Version:    0.6.2
 */