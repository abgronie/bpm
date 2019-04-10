package com.hotent.core.excel.editor.listener;

import com.hotent.core.excel.Excel;
import com.hotent.core.excel.editor.CellEditor;

public abstract interface CellValueListener
{
  public abstract void onValueChange(CellEditor paramCellEditor, Object paramObject, int paramInt1, int paramInt2, Excel paramExcel);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.listener.CellValueListener
 * JD-Core Version:    0.6.2
 */