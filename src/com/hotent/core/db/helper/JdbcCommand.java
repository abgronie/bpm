package com.hotent.core.db.helper;

import javax.sql.DataSource;

public abstract interface JdbcCommand
{
  public abstract void execute(DataSource paramDataSource)
    throws Exception;
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.helper.JdbcCommand
 * JD-Core Version:    0.6.2
 */