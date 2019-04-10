/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class LocaleUtil
/*     */ {
/*  32 */   protected static Map<String, LocaleData> locales = new HashMap();
/*     */ 
/*     */   protected static LocaleData lookupLocaleData(String code)
/*     */   {
/*  38 */     LocaleData localeData = (LocaleData)locales.get(code);
/*  39 */     if (localeData == null) {
/*  40 */       String[] data = decodeLocaleCode(code);
/*  41 */       localeData = new LocaleData(new Locale(data[0], data[1], data[2]));
/*  42 */       locales.put(code, localeData);
/*     */     }
/*  44 */     return localeData;
/*     */   }
/*     */ 
/*     */   protected static LocaleData lookupLocaleData(Locale locale) {
/*  48 */     return lookupLocaleData(resolveLocaleCode(locale));
/*     */   }
/*     */ 
/*     */   public static Locale getLocale(String language, String country, String variant)
/*     */   {
/*  59 */     LocaleData localeData = lookupLocaleData(resolveLocaleCode(language, country, variant));
/*     */ 
/*  61 */     return localeData.locale;
/*     */   }
/*     */ 
/*     */   public static Locale getLocale(String language, String country)
/*     */   {
/*  68 */     return getLocale(language, country, null);
/*     */   }
/*     */ 
/*     */   public static Locale getLocale(String languageCode)
/*     */   {
/*  77 */     LocaleData localeData = lookupLocaleData(languageCode);
/*  78 */     return localeData.locale;
/*     */   }
/*     */ 
/*     */   public static String resolveLocaleCode(String lang, String country, String variant)
/*     */   {
/*  89 */     StringBuilder code = new StringBuilder(lang);
/*  90 */     if (!StringUtils.isEmpty(country)) {
/*  91 */       code.append('_').append(country);
/*  92 */       if (!StringUtils.isEmpty(variant)) {
/*  93 */         code.append('_').append(variant);
/*     */       }
/*     */     }
/*  96 */     return code.toString();
/*     */   }
/*     */ 
/*     */   public static String resolveLocaleCode(Locale locale)
/*     */   {
/* 103 */     return resolveLocaleCode(locale.getLanguage(), locale.getCountry(), locale.getVariant());
/*     */   }
/*     */ 
/*     */   public static String[] decodeLocaleCode(String localeCode)
/*     */   {
/* 112 */     String[] result = new String[3];
/* 113 */     String[] data = StringUtils.split(localeCode, '_');
/* 114 */     result[0] = data[0];
/*     */     String tmp24_22 = ""; result[2] = tmp24_22; result[1] = tmp24_22;
/* 116 */     if (data.length >= 2) {
/* 117 */       result[1] = data[1];
/* 118 */       if (data.length >= 3) {
/* 119 */         result[2] = data[2];
/*     */       }
/*     */     }
/* 122 */     return result;
/*     */   }
/*     */ 
/*     */   public static NumberFormat getNumberFormat(Locale locale)
/*     */   {
/* 145 */     LocaleData localeData = lookupLocaleData(locale);
/* 146 */     NumberFormat nf = localeData.numberFormat;
/* 147 */     if (nf == null) {
/* 148 */       nf = NumberFormat.getInstance(locale);
/* 149 */       localeData.numberFormat = nf;
/*     */     }
/* 151 */     return nf;
/*     */   }
/*     */ 
/*     */   static class LocaleData
/*     */   {
/*     */     final Locale locale;
/*     */     NumberFormat numberFormat;
/*     */ 
/*     */     LocaleData(Locale locale)
/*     */     {
/*  22 */       this.locale = locale;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.LocaleUtil
 * JD-Core Version:    0.6.2
 */