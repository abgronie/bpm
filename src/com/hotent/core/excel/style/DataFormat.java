package com.hotent.core.excel.style;

public abstract interface DataFormat
{
  public static final String TEXT = "@";
  public static final String GENERAL = "General";
  public static final String NUMBER_1 = "0";
  public static final String NUMBER_2 = "0.00";
  public static final String NUMBER_THOUSANDS = "#,##0";
  public static final String NUMBER_THOUSANDS1 = "#,##0.00";
  public static final String NUMBER_5 = "=$#,##0_);=$#,##0)";
  public static final String NUMBER_6 = "=$#,##0_);[Red]=$#,##0)";
  public static final String NUMBER_7 = "=$#,##0.00);=$#,##0.00)";
  public static final String NUMBER_8 = "=$#,##0.00_);[Red]=$#,##0.00)";
  public static final String NUMBER_9 = "0%";
  public static final String NUMBER_10 = "0.00%";
  public static final String NUMBER_11 = "0.00E+00";
  public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE = "yyyy-MM-dd";
  public static final String DATETIME_NOSECOND = "yyyy-MM-dd HH:mm";
  public static final String TIME = "HH:mm:ss";
  public static final String TIME_NOSECOND = "HH:mm";
  public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.DataFormat
 * JD-Core Version:    0.6.2
 */