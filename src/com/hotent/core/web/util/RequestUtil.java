/*      */ package com.hotent.core.web.util;
/*      */ 
/*      */ import com.hotent.core.util.ArrayUtil;
/*      */ import com.hotent.core.util.BeanUtils;
/*      */ import com.hotent.core.util.DateFormatUtil;
/*      */ import com.hotent.core.util.StringUtil;
/*      */ import com.hotent.core.util.TimeUtil;
/*      */ import com.hotent.core.web.query.QueryFilter;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.ParseException;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ public class RequestUtil
/*      */ {
/*   33 */   private static Logger logger = LoggerFactory.getLogger(QueryFilter.class);
/*      */   public static final String RETURNURL = "returnUrl";
/*      */ 
/*      */   public static String getString(HttpServletRequest request, String key, String defaultValue, boolean b)
/*      */   {
/*   52 */     String value = request.getParameter(key);
/*   53 */     if (StringUtil.isEmpty(value))
/*   54 */       return defaultValue;
/*   55 */     if (b) {
/*   56 */       return value.replace("'", "''").trim();
/*      */     }
/*   58 */     return value.trim();
/*      */   }
/*      */ 
/*      */   public static String getString(HttpServletRequest request, String key)
/*      */   {
/*   70 */     return getString(request, key, "", false);
/*      */   }
/*      */ 
/*      */   public static String getString(HttpServletRequest request, String key, boolean b)
/*      */   {
/*   83 */     return getString(request, key, "", b);
/*      */   }
/*      */ 
/*      */   public static String getString(HttpServletRequest request, String key, String defaultValue)
/*      */   {
/*   95 */     return getString(request, key, defaultValue, true);
/*      */   }
/*      */ 
/*      */   public static String getStringAry(HttpServletRequest request, String key)
/*      */   {
/*  106 */     String[] aryValue = request.getParameterValues(key);
/*  107 */     if ((aryValue == null) || (aryValue.length == 0)) {
/*  108 */       return "";
/*      */     }
/*  110 */     String tmp = "";
/*  111 */     for (String v : aryValue) {
/*  112 */       if ("".equals(tmp)) {
/*  113 */         tmp = new StringBuilder().append(tmp).append(v).toString();
/*      */       }
/*      */       else {
/*  116 */         tmp = new StringBuilder().append(tmp).append(",").append(v).toString();
/*      */       }
/*      */     }
/*  119 */     return tmp;
/*      */   }
/*      */ 
/*      */   public static String getSecureString(HttpServletRequest request, String key, String defaultValue)
/*      */   {
/*  131 */     String value = request.getParameter(key);
/*  132 */     if (StringUtil.isEmpty(value))
/*  133 */       return defaultValue;
/*  134 */     return filterInject(value);
/*      */   }
/*      */ 
/*      */   public static String getSecureString(HttpServletRequest request, String key)
/*      */   {
/*  146 */     return getSecureString(request, key, "");
/*      */   }
/*      */ 
/*      */   public static String filterInject(String str)
/*      */   {
/*  157 */     String injectstr = "\\||;|exec|insert|select|delete|update|count|chr|truncate|char";
/*  158 */     Pattern regex = Pattern.compile(injectstr, 226);
/*      */ 
/*  161 */     Matcher matcher = regex.matcher(str);
/*  162 */     str = matcher.replaceAll("");
/*  163 */     str = str.replace("'", "''");
/*  164 */     str = str.replace("-", "—");
/*  165 */     str = str.replace("(", "（");
/*  166 */     str = str.replace(")", "）");
/*  167 */     str = str.replace("%", "％");
/*      */ 
/*  169 */     return str;
/*      */   }
/*      */ 
/*      */   public static String getLowercaseString(HttpServletRequest request, String key)
/*      */   {
/*  182 */     return getString(request, key).toLowerCase();
/*      */   }
/*      */ 
/*      */   public static int getInt(HttpServletRequest request, String key)
/*      */   {
/*  194 */     return getInt(request, key, 0);
/*      */   }
/*      */ 
/*      */   public static int getInt(HttpServletRequest request, String key, int defaultValue)
/*      */   {
/*  207 */     String str = request.getParameter(key);
/*  208 */     if (StringUtil.isEmpty(str))
/*  209 */       return defaultValue;
/*  210 */     return Integer.parseInt(str);
/*      */   }
/*      */ 
/*      */   public static long getLong(HttpServletRequest request, String key)
/*      */   {
/*  223 */     return getLong(request, key, 0L);
/*      */   }
/*      */ 
/*      */   public static Long[] getLongAry(HttpServletRequest request, String key)
/*      */   {
/*  234 */     String[] aryKey = request.getParameterValues(key);
/*  235 */     if (BeanUtils.isEmpty(aryKey))
/*  236 */       return null;
/*  237 */     Long[] aryLong = new Long[aryKey.length];
/*  238 */     for (int i = 0; i < aryKey.length; i++) {
/*  239 */       aryLong[i] = Long.valueOf(Long.parseLong(aryKey[i]));
/*      */     }
/*  241 */     return aryLong;
/*      */   }
/*      */ 
/*      */   public static Long[] getLongAryByStr(HttpServletRequest request, String key)
/*      */   {
/*  252 */     String str = request.getParameter(key);
/*  253 */     if (StringUtil.isEmpty(str)) return null;
/*  254 */     String[] aryId = str.split(",");
/*  255 */     Long[] lAryId = new Long[aryId.length];
/*  256 */     for (int i = 0; i < aryId.length; i++) {
/*  257 */       lAryId[i] = Long.valueOf(Long.parseLong(aryId[i]));
/*      */     }
/*  259 */     return lAryId;
/*      */   }
/*      */ 
/*      */   public static String[] getStringAryByStr(HttpServletRequest request, String key)
/*      */   {
/*  271 */     String str = request.getParameter(key);
/*  272 */     if (StringUtil.isEmpty(str)) return null;
/*  273 */     String[] aryId = str.split(",");
/*  274 */     String[] lAryId = new String[aryId.length];
/*  275 */     for (int i = 0; i < aryId.length; i++) {
/*  276 */       lAryId[i] = aryId[i];
/*      */     }
/*  278 */     return lAryId;
/*      */   }
/*      */ 
/*      */   public static Integer[] getIntAry(HttpServletRequest request, String key)
/*      */   {
/*  289 */     String[] aryKey = request.getParameterValues(key);
/*  290 */     if (BeanUtils.isEmpty(aryKey)) return null;
/*  291 */     Integer[] aryInt = new Integer[aryKey.length];
/*  292 */     for (int i = 0; i < aryKey.length; i++) {
/*  293 */       aryInt[i] = Integer.valueOf(Integer.parseInt(aryKey[i]));
/*      */     }
/*  295 */     return aryInt;
/*      */   }
/*      */ 
/*      */   public static Float[] getFloatAry(HttpServletRequest request, String key) {
/*  299 */     String[] aryKey = request.getParameterValues(key);
/*  300 */     if (BeanUtils.isEmpty(aryKey)) return null;
/*  301 */     Float[] fAryId = new Float[aryKey.length];
/*  302 */     for (int i = 0; i < aryKey.length; i++) {
/*  303 */       fAryId[i] = Float.valueOf(Float.parseFloat(aryKey[i]));
/*      */     }
/*  305 */     return fAryId;
/*      */   }
/*      */ 
/*      */   public static long getLong(HttpServletRequest request, String key, long defaultValue)
/*      */   {
/*  318 */     String str = request.getParameter(key);
/*  319 */     if (StringUtil.isEmpty(str))
/*  320 */       return defaultValue;
/*  321 */     return Long.parseLong(str.trim());
/*      */   }
/*      */ 
/*      */   public static Long getLong(HttpServletRequest request, String key, Long defaultValue)
/*      */   {
/*  335 */     String str = request.getParameter(key);
/*  336 */     if (StringUtil.isEmpty(str))
/*  337 */       return defaultValue;
/*  338 */     return Long.valueOf(Long.parseLong(str.trim()));
/*      */   }
/*      */ 
/*      */   public static float getFloat(HttpServletRequest request, String key)
/*      */   {
/*  349 */     return getFloat(request, key, 0.0F);
/*      */   }
/*      */ 
/*      */   public static float getFloat(HttpServletRequest request, String key, float defaultValue)
/*      */   {
/*  362 */     String str = request.getParameter(key);
/*  363 */     if (StringUtil.isEmpty(str))
/*  364 */       return defaultValue;
/*  365 */     return Float.parseFloat(request.getParameter(key));
/*      */   }
/*      */ 
/*      */   public static boolean getBoolean(HttpServletRequest request, String key)
/*      */   {
/*  376 */     return getBoolean(request, key, false);
/*      */   }
/*      */ 
/*      */   public static boolean getBoolean(HttpServletRequest request, String key, boolean defaultValue)
/*      */   {
/*  389 */     String str = request.getParameter(key);
/*  390 */     if (StringUtil.isEmpty(str))
/*  391 */       return defaultValue;
/*  392 */     if (StringUtils.isNumeric(str))
/*  393 */       return Integer.parseInt(str) == 1;
/*  394 */     return Boolean.parseBoolean(str);
/*      */   }
/*      */ 
/*      */   public static Short getShort(HttpServletRequest request, String key)
/*      */   {
/*  405 */     return getShort(request, key, Short.valueOf((short)0));
/*      */   }
/*      */ 
/*      */   public static Short getShort(HttpServletRequest request, String key, Short defaultValue)
/*      */   {
/*  418 */     String str = request.getParameter(key);
/*  419 */     if (StringUtil.isEmpty(str))
/*  420 */       return defaultValue;
/*  421 */     return Short.valueOf(Short.parseShort(str));
/*      */   }
/*      */ 
/*      */   public static Date getDate(HttpServletRequest request, String key, String style)
/*      */     throws ParseException
/*      */   {
/*  435 */     String str = request.getParameter(key);
/*  436 */     if (StringUtil.isEmpty(str))
/*  437 */       return null;
/*  438 */     if (StringUtil.isEmpty(style))
/*  439 */       style = "yyyy-MM-dd HH:mm:ss";
/*  440 */     return DateFormatUtil.parse(str, style);
/*      */   }
/*      */ 
/*      */   public static Date getDate(HttpServletRequest request, String key)
/*      */     throws ParseException
/*      */   {
/*  453 */     String str = request.getParameter(key);
/*  454 */     if (StringUtil.isEmpty(str))
/*  455 */       return null;
/*  456 */     return DateFormatUtil.parseDate(str);
/*      */   }
/*      */ 
/*      */   public static Date getTimestamp(HttpServletRequest request, String key)
/*      */     throws ParseException
/*      */   {
/*  470 */     String str = request.getParameter(key);
/*  471 */     if (StringUtil.isEmpty(str))
/*  472 */       return null;
/*  473 */     return DateFormatUtil.parseDateTime(str);
/*      */   }
/*      */ 
/*      */   public static String getUrl(HttpServletRequest request)
/*      */   {
/*  484 */     StringBuffer urlThisPage = new StringBuffer();
/*  485 */     urlThisPage.append(request.getRequestURI());
/*  486 */     Enumeration e = request.getParameterNames();
/*  487 */     String para = "";
/*  488 */     String values = "";
/*  489 */     urlThisPage.append("?");
/*  490 */     while (e.hasMoreElements()) {
/*  491 */       para = (String)e.nextElement();
/*  492 */       values = request.getParameter(para);
/*  493 */       urlThisPage.append(para);
/*  494 */       urlThisPage.append("=");
/*  495 */       urlThisPage.append(values);
/*  496 */       urlThisPage.append("&");
/*      */     }
/*  498 */     return urlThisPage.substring(0, urlThisPage.length() - 1);
/*      */   }
/*      */ 
/*      */   public static String getPrePage(HttpServletRequest request)
/*      */   {
/*  508 */     if (StringUtil.isEmpty(request.getParameter("returnUrl"))) {
/*  509 */       return request.getHeader("Referer");
/*      */     }
/*  511 */     return request.getParameter("returnUrl");
/*      */   }
/*      */ 
/*      */   public static Map<String, Object> getQueryParams(HttpServletRequest request)
/*      */   {
/*  523 */     Map map = new HashMap();
/*  524 */     Enumeration params = request.getParameterNames();
/*  525 */     while (params.hasMoreElements()) {
/*  526 */       String key = params.nextElement().toString();
/*  527 */       String[] values = request.getParameterValues(key);
/*  528 */       if ((values.length > 0) && (StringUtils.isNotEmpty(values[0])) && 
/*  529 */         (key.startsWith("Q_"))) {
/*  530 */         String[] aryParaKey = key.split("_");
/*  531 */         if (aryParaKey.length >= 3)
/*      */         {
/*  533 */           if (values.length == 1) {
/*  534 */             String val = values[0].trim();
/*  535 */             if (StringUtil.isNotEmpty(val))
/*  536 */               map.put(key, values[0].trim());
/*      */           }
/*      */           else {
/*  539 */             map.put(key, values);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  543 */     return map;
/*      */   }
/*      */ 
/*      */   public static Map<String, Object> getQueryParamater(HttpServletRequest request)
/*      */   {
/*  552 */     Map map = new HashMap();
/*  553 */     Enumeration params = request.getParameterNames();
/*  554 */     while (params.hasMoreElements()) {
/*  555 */       String key = params.nextElement().toString();
/*  556 */       String[] values = request.getParameterValues(key);
/*  557 */       if (key.startsWith("Q_")) {
/*  558 */         key = key.replaceFirst("Q_", "");
/*  559 */         if ((values.length > 0) && (StringUtils.isNotEmpty(values[0])))
/*  560 */           if (values.length == 1) {
/*  561 */             String val = values[0].trim();
/*  562 */             if (StringUtil.isNotEmpty(val))
/*  563 */               map.put(key, values[0].trim());
/*      */           }
/*      */           else
/*      */           {
/*  567 */             String str = ArrayUtil.contact(values, ",");
/*  568 */             map.put(key, str);
/*      */           }
/*      */       }
/*      */     }
/*  572 */     return map;
/*      */   }
/*      */ 
/*      */   public static Map<String, Object> getQueryMap(HttpServletRequest request)
/*      */   {
/*  607 */     Map map = new HashMap();
/*  608 */     Enumeration params = request.getParameterNames();
/*  609 */     while (params.hasMoreElements()) {
/*  610 */       String key = params.nextElement().toString();
/*  611 */       String[] values = request.getParameterValues(key);
/*  612 */       if ((key.equals("sortField")) || (key.equals("orderSeq")) || (key.equals("sortColumns")))
/*      */       {
/*  614 */         String val = values[0].trim();
/*  615 */         if (StringUtil.isNotEmpty(val)) {
/*  616 */           if (key.equals("sortField")) {
/*  617 */             String[] aryValue = val.split(",");
/*  618 */             String v = aryValue[(aryValue.length - 1)];
/*  619 */             map.put(key, v.trim());
/*      */           }
/*      */           else {
/*  622 */             map.put(key, values[0].trim());
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*  627 */       else if ((values.length > 0) && (StringUtils.isNotEmpty(values[0]))) {
/*  628 */         if (key.startsWith("Q_")) {
/*  629 */           addParameter(key, values, map);
/*      */         }
/*  631 */         else if (values.length == 1) {
/*  632 */           String val = values[0].trim();
/*  633 */           if (StringUtil.isNotEmpty(val))
/*  634 */             map.put(key, values[0].trim());
/*      */         }
/*      */         else {
/*  637 */           map.put(key, values);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  642 */     return map;
/*      */   }
/*      */ 
/*      */   public static void addParameter(String key, String[] values, Map<String, Object> map)
/*      */   {
/*  647 */     String[] aryParaKey = key.split("_");
/*  648 */     if (aryParaKey.length < 3)
/*  649 */       return;
/*  650 */     String paraName = key.substring(2, key.lastIndexOf("_"));
/*  651 */     String type = key.substring(key.lastIndexOf("_") + 1);
/*  652 */     if (values.length == 1) {
/*  653 */       String value = values[0].trim();
/*  654 */       if (StringUtil.isNotEmpty(value))
/*      */         try {
/*  656 */           if (value.indexOf("_") != -1) {
/*  657 */             value = value.replaceAll("_", "\\_");
/*      */           }
/*  659 */           Object obj = getObjValue(type, value);
/*  660 */           map.put(paraName, obj);
/*      */         } catch (Exception e) {
/*  662 */           logger.debug(e.getMessage());
/*      */         }
/*      */     }
/*      */     else {
/*  666 */       Object[] aryObj = getObjValue(type, values);
/*  667 */       map.put(paraName, aryObj);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static Object getObjValue(String type, String paramValue)
/*      */   {
/*  681 */     Object value = null;
/*      */ 
/*  683 */     if ("S".equals(type)) {
/*  684 */       value = paramValue;
/*      */     }
/*  687 */     else if ("SL".equals(type)) {
/*  688 */       value = new StringBuilder().append("%").append(paramValue).append("%").toString();
/*      */     }
/*  691 */     else if ("SLR".equals(type)) {
/*  692 */       value = new StringBuilder().append(paramValue).append("%").toString();
/*      */     }
/*  695 */     else if ("SLL".equals(type)) {
/*  696 */       value = new StringBuilder().append("%").append(paramValue).toString();
/*      */     }
/*  699 */     else if ("SUPL".equals(type)) {
/*  700 */       value = new StringBuilder().append("%").append(paramValue.toUpperCase()).append("%").toString();
/*      */     }
/*  703 */     else if ("SUPLR".equals(type)) {
/*  704 */       value = new StringBuilder().append(paramValue.toUpperCase()).append("%").toString();
/*      */     }
/*  707 */     else if ("SUPLL".equals(type)) {
/*  708 */       value = new StringBuilder().append("%").append(paramValue.toUpperCase()).toString();
/*      */     }
/*  711 */     else if ("SLOL".equals(type)) {
/*  712 */       value = new StringBuilder().append("%").append(paramValue.toLowerCase()).append("%").toString();
/*      */     }
/*  715 */     else if ("SLOLR".equals(type)) {
/*  716 */       value = new StringBuilder().append(paramValue.toLowerCase()).append("%").toString();
/*      */     }
/*  719 */     else if ("SLOLL".equals(type)) {
/*  720 */       value = new StringBuilder().append("%").append(paramValue.toLowerCase()).toString();
/*      */     }
/*  723 */     else if ("L".equals(type)) {
/*  724 */       value = new Long(paramValue);
/*      */     }
/*  727 */     else if ("N".equals(type))
/*  728 */       value = new Integer(paramValue);
/*  729 */     else if ("DB".equals(type)) {
/*  730 */       value = new Double(paramValue);
/*      */     }
/*  733 */     else if ("BD".equals(type)) {
/*  734 */       value = new BigDecimal(paramValue);
/*      */     }
/*  737 */     else if ("FT".equals(type)) {
/*  738 */       value = new Float(paramValue);
/*      */     }
/*  741 */     else if ("SN".equals(type)) {
/*  742 */       value = new Short(paramValue);
/*      */     }
/*  745 */     else if ("DL".equals(type)) {
/*  746 */       value = TimeUtil.convertString(paramValue, "yyyy-MM-dd");
/*      */     }
/*  749 */     else if ("DG".equals(type)) {
/*  750 */       value = TimeUtil.getNextDays(TimeUtil.convertString(paramValue, "yyyy-MM-dd"), 1);
/*      */     }
/*  752 */     else if ("DE".equals(type))
/*  753 */       value = TimeUtil.convertString(paramValue, "yyyy-MM-dd");
/*      */     else {
/*  755 */       value = paramValue;
/*      */     }
/*  757 */     return value;
/*      */   }
/*      */ 
/*      */   private static Object[] getObjValue(String type, String[] value) {
/*  761 */     Object[] aryObj = new Object[value.length];
/*  762 */     for (int i = 0; i < aryObj.length; i++) {
/*  763 */       String v = "";
/*  764 */       if (value[i] != null)
/*  765 */         v = value[i].toString();
/*  766 */       aryObj[i] = getObjValue(type, v);
/*      */     }
/*  768 */     return aryObj;
/*      */   }
/*      */ 
/*      */   public static Map<String, Object> getParameterValueMap(HttpServletRequest request) {
/*  772 */     Map map = new HashMap();
/*  773 */     map.put("__ctx", request.getContextPath());
/*  774 */     Enumeration params = request.getParameterNames();
/*  775 */     while (params.hasMoreElements()) {
/*  776 */       String key = params.nextElement().toString();
/*  777 */       String[] values = request.getParameterValues(key);
/*  778 */       if (values != null)
/*      */       {
/*  780 */         if (values.length == 1) {
/*  781 */           String tmpValue = values[0];
/*  782 */           if (tmpValue != null)
/*      */           {
/*  784 */             tmpValue = tmpValue.trim();
/*  785 */             if (!tmpValue.equals(""))
/*      */             {
/*  787 */               tmpValue = filterInject(tmpValue);
/*  788 */               if (!tmpValue.equals(""))
/*      */               {
/*  790 */                 map.put(key, tmpValue); } 
/*      */             }
/*      */           } } else { String rtn = getByAry(values, true);
/*  793 */           if (rtn.length() > 0)
/*  794 */             map.put(key, rtn); }
/*      */       }
/*      */     }
/*  797 */     return map;
/*      */   }
/*      */ 
/*      */   public static Map<String, Object> getParameterValueMap(HttpServletRequest request, boolean remainArray, boolean isSecure)
/*      */   {
/*  812 */     Map map = new HashMap();
/*  813 */     Enumeration params = request.getParameterNames();
/*  814 */     while (params.hasMoreElements()) {
/*  815 */       String key = params.nextElement().toString();
/*  816 */       String[] values = request.getParameterValues(key);
/*  817 */       if (values != null)
/*      */       {
/*  819 */         if (values.length == 1) {
/*  820 */           String tmpValue = values[0];
/*  821 */           if (tmpValue != null)
/*      */           {
/*  823 */             tmpValue = tmpValue.trim();
/*  824 */             if (!tmpValue.equals(""))
/*      */             {
/*  826 */               if (isSecure)
/*  827 */                 tmpValue = filterInject(tmpValue);
/*  828 */               if (!tmpValue.equals(""))
/*      */               {
/*  830 */                 map.put(key, tmpValue); } 
/*      */             }
/*      */           } } else { String rtn = getByAry(values, isSecure);
/*  833 */           if (rtn.length() > 0)
/*  834 */             if (remainArray)
/*  835 */               map.put(key, rtn.split(","));
/*      */             else
/*  837 */               map.put(key, rtn);
/*      */         }
/*      */       }
/*      */     }
/*  841 */     return map;
/*      */   }
/*      */ 
/*      */   private static String getByAry(String[] aryTmp, boolean isSecure)
/*      */   {
/*  851 */     String rtn = "";
/*  852 */     for (int i = 0; i < aryTmp.length; i++) {
/*  853 */       String str = aryTmp[i].trim();
/*  854 */       if (!str.equals("")) {
/*  855 */         if (isSecure) {
/*  856 */           str = filterInject(str);
/*  857 */           if (!str.equals(""))
/*  858 */             rtn = new StringBuilder().append(rtn).append(str).append(",").toString();
/*      */         } else {
/*  860 */           rtn = new StringBuilder().append(rtn).append(str).append(",").toString();
/*      */         }
/*      */       }
/*      */     }
/*  864 */     if (rtn.length() > 0)
/*  865 */       rtn = rtn.substring(0, rtn.length() - 1);
/*  866 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static String getStringValues(HttpServletRequest request, String paramName)
/*      */   {
/*  883 */     String[] values = request.getParameterValues(paramName);
/*  884 */     if (BeanUtils.isEmpty(values))
/*  885 */       return "";
/*  886 */     String tmp = "";
/*  887 */     for (int i = 0; i < values.length; i++) {
/*  888 */       if (i == 0)
/*  889 */         tmp = new StringBuilder().append(tmp).append(values[i]).toString();
/*      */       else {
/*  891 */         tmp = new StringBuilder().append(tmp).append(",").append(values[i]).toString();
/*      */       }
/*      */     }
/*  894 */     return tmp;
/*      */   }
/*      */ 
/*      */   public static Locale getLocal(HttpServletRequest request)
/*      */   {
/*  904 */     Locale local = request.getLocale();
/*  905 */     if (local == null)
/*  906 */       local = Locale.CHINA;
/*  907 */     return local;
/*      */   }
/*      */ 
/*      */   public static final String getErrorUrl(HttpServletRequest request)
/*      */   {
/*  917 */     String errorUrl = (String)request.getAttribute("javax.servlet.error.request_uri");
/*      */ 
/*  919 */     if (errorUrl == null) {
/*  920 */       errorUrl = (String)request.getAttribute("javax.servlet.forward.request_uri");
/*      */     }
/*      */ 
/*  923 */     if (errorUrl == null) {
/*  924 */       errorUrl = (String)request.getAttribute("javax.servlet.include.request_uri");
/*      */     }
/*      */ 
/*  927 */     if (errorUrl == null) {
/*  928 */       errorUrl = request.getRequestURL().toString();
/*      */     }
/*  930 */     return errorUrl;
/*      */   }
/*      */ 
/*      */   public static String getIpAddr(HttpServletRequest request)
/*      */   {
/*  938 */     String ip = request.getHeader("x-forwarded-for");
/*  939 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/*  940 */       ip = request.getHeader("Proxy-Client-IP");
/*      */     }
/*  942 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/*  943 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*      */     }
/*  945 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/*  946 */       ip = request.getRemoteAddr();
/*      */     }
/*  948 */     return ip;
/*      */   }
/*      */ 
/*      */   public static final StringBuilder getErrorInfoFromRequest(HttpServletRequest request, boolean isInfoEnabled)
/*      */   {
/*  959 */     StringBuilder sb = new StringBuilder();
/*  960 */     String errorUrl = getErrorUrl(request);
/*  961 */     sb.append(StringUtil.formatMsg("Error processing url: %1, Referrer: %2, Error message: %3.\n", new Object[] { errorUrl, request.getHeader("REFERER"), request.getAttribute("javax.servlet.error.message") }));
/*      */ 
/*  966 */     Throwable ex = (Throwable)request.getAttribute("exception");
/*  967 */     if (ex == null) {
/*  968 */       ex = (Throwable)request.getAttribute("javax.servlet.error.exception");
/*      */     }
/*      */ 
/*  972 */     if (ex != null) {
/*  973 */       sb.append(StringUtil.formatMsg("Exception stack trace: \n", new Object[] { ex }));
/*      */     }
/*  975 */     return sb;
/*      */   }
/*      */ 
/*      */   public static final StringBuilder getRequestInfo(HttpServletRequest request)
/*      */   {
/*  986 */     StringBuilder sb = new StringBuilder();
/*  987 */     sb.append("--------------Debuging request.getParameterNames()---------\n");
/*  988 */     Enumeration enumeration = request.getParameterNames();
/*  989 */     while (enumeration.hasMoreElements()) {
/*  990 */       String paramName = (String)enumeration.nextElement();
/*  991 */       sb.append(StringUtil.formatMsg("Request Parameter - %1 = %2.\n", new Object[] { paramName, request.getParameter(paramName) }));
/*      */     }
/*      */ 
/*  995 */     sb.append("-----------Debuging request.getAttributeNames()---------\n");
/*  996 */     enumeration = request.getAttributeNames();
/*  997 */     while (enumeration.hasMoreElements()) {
/*  998 */       String attrName = (String)enumeration.nextElement();
/*  999 */       if (!attrName.endsWith("exception")) {
/* 1000 */         sb.append(StringUtil.formatMsg("Request Attribute - %1 = %2.\n", new Object[] { attrName, request.getAttribute(attrName) }));
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1006 */     sb.append("-----------Debuging request.getHeaderNames()---------------\n");
/* 1007 */     enumeration = request.getHeaderNames();
/* 1008 */     while (enumeration.hasMoreElements()) {
/* 1009 */       String headerName = (String)enumeration.nextElement();
/* 1010 */       sb.append(StringUtil.formatMsg("Request Header - %1 = %2.\n", new Object[] { headerName, request.getHeader(headerName) }));
/*      */     }
/*      */ 
/* 1014 */     return sb;
/*      */   }
/*      */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.util.RequestUtil
 * JD-Core Version:    0.6.2
 */