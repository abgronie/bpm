package com.hotent.core.table;

import com.hotent.core.page.PageBean;
import java.sql.SQLException;
import java.util.List;

public abstract interface IDbView
{
  public abstract void createOrRep(String paramString1, String paramString2)
    throws Exception;

  public abstract List<String> getViews(String paramString)
    throws SQLException;

  public abstract List<String> getViews(String paramString, PageBean paramPageBean)
    throws SQLException, Exception;

  public abstract TableModel getModelByViewName(String paramString)
    throws SQLException;

  public abstract List<TableModel> getViewsByName(String paramString, PageBean paramPageBean)
    throws Exception;
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.IDbView
 * JD-Core Version:    0.6.2
 */