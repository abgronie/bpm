/*     */ package com.hotent.core.script;
/*     */ 
/*     */ import com.hotent.core.engine.IScript;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.DateFormatUtil;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class CommonScript
/*     */   implements IScript
/*     */ {
/*     */   public boolean equalsIgnoreCase(String str1, String str2)
/*     */   {
/*  27 */     return StringUtils.equalsIgnoreCase(str1, str2);
/*     */   }
/*     */ 
/*     */   public boolean equals(String str1, String str2)
/*     */   {
/*  37 */     return StringUtils.equals(str1, str2);
/*     */   }
/*     */ 
/*     */   public short parseShort(String str)
/*     */   {
/*  48 */     return parseShort(str, (short)0);
/*     */   }
/*     */ 
/*     */   public short parseShort(String str, short defaultValue)
/*     */   {
/*  61 */     if (StringUtils.isEmpty(str))
/*  62 */       return defaultValue;
/*  63 */     return Short.parseShort(str);
/*     */   }
/*     */ 
/*     */   public int parseInt(String str)
/*     */   {
/*  75 */     return parseInt(str, 0);
/*     */   }
/*     */ 
/*     */   public int parseInt(String str, int defaultValue)
/*     */   {
/*  88 */     if (StringUtils.isEmpty(str))
/*  89 */       return defaultValue;
/*  90 */     return Integer.parseInt(str);
/*     */   }
/*     */ 
/*     */   public long parseLong(String str)
/*     */   {
/* 101 */     return parseLong(str, 0L);
/*     */   }
/*     */ 
/*     */   public long parseLong(String str, long defaultValue)
/*     */   {
/* 114 */     if (StringUtils.isEmpty(str))
/* 115 */       return defaultValue;
/* 116 */     return Long.parseLong(str);
/*     */   }
/*     */ 
/*     */   public float parseFloat(String str)
/*     */   {
/* 128 */     return parseFloat(str, 0.0F);
/*     */   }
/*     */ 
/*     */   public float parseFloat(String str, float defaultValue)
/*     */   {
/* 141 */     if (StringUtils.isEmpty(str))
/* 142 */       return defaultValue;
/* 143 */     return Float.parseFloat(str);
/*     */   }
/*     */ 
/*     */   public double parseDouble(String str)
/*     */   {
/* 154 */     return parseDouble(str, 0.0D);
/*     */   }
/*     */ 
/*     */   public double parseDouble(String str, double defaultValue)
/*     */   {
/* 167 */     if (StringUtils.isEmpty(str))
/* 168 */       return defaultValue;
/* 169 */     return Double.parseDouble(str);
/*     */   }
/*     */ 
/*     */   public boolean parseBoolean(String str)
/*     */   {
/* 180 */     return parseBoolean(str, Boolean.valueOf(false)).booleanValue();
/*     */   }
/*     */ 
/*     */   public Boolean parseBoolean(String str, Boolean defaultValue)
/*     */   {
/* 193 */     if (StringUtils.isEmpty(str))
/* 194 */       return defaultValue;
/* 195 */     if (StringUtils.isNumeric(str))
/* 196 */       return Boolean.valueOf(Integer.parseInt(str) == 1);
/* 197 */     return Boolean.valueOf(Boolean.parseBoolean(str));
/*     */   }
/*     */ 
/*     */   public String parseString(Object obj)
/*     */   {
/* 206 */     if (obj == null)
/* 207 */       return "";
/* 208 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public String parseString(Object obj, String style)
/*     */   {
/* 217 */     if (obj == null)
/* 218 */       return "";
/* 219 */     if ((obj instanceof Date))
/* 220 */       return DateFormatUtil.format((Date)obj, style);
/* 221 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public Date parseDate(String str, String style)
/*     */     throws Exception
/*     */   {
/* 234 */     if (StringUtils.isEmpty(str))
/* 235 */       return null;
/* 236 */     if (StringUtils.isEmpty(style))
/* 237 */       style = "yyyy-MM-dd HH:mm:ss";
/* 238 */     return DateFormatUtil.parse(str, style);
/*     */   }
/*     */ 
/*     */   public Date parseDate(String str, Date defaultValue, String style)
/*     */     throws Exception
/*     */   {
/* 249 */     if (StringUtils.isEmpty(str))
/* 250 */       return defaultValue;
/* 251 */     if (StringUtils.isEmpty(style))
/* 252 */       style = "yyyy-MM-dd HH:mm:ss";
/* 253 */     return DateFormatUtil.parse(str, style);
/*     */   }
/*     */ 
/*     */   public int compareTo(Date date1, Date date2)
/*     */   {
/* 268 */     if ((date1 == null) && (date2 == null))
/* 269 */       return 0;
/* 270 */     if (date1 == null)
/* 271 */       return 10;
/* 272 */     if (date2 == null)
/* 273 */       return -10;
/* 274 */     return date1.compareTo(date2);
/*     */   }
/*     */ 
/*     */   public int compareTo(String str1, String str2)
/*     */   {
/* 289 */     if ((str1 == null) && (str2 == null))
/* 290 */       return 0;
/* 291 */     if (str1 == null)
/* 292 */       return 10;
/* 293 */     if (str2 == null)
/* 294 */       return -10;
/* 295 */     return str1.compareTo(str2);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty(String str)
/*     */   {
/* 304 */     return StringUtils.isEmpty(str);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty(Object obj)
/*     */   {
/* 313 */     return BeanUtils.isEmpty(obj);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.script.CommonScript
 * JD-Core Version:    0.6.2
 */