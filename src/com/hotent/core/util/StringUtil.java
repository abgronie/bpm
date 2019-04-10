/*      */ package com.hotent.core.util;
/*      */ 
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringWriter;
/*      */ import java.security.MessageDigest;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import net.sf.json.JSONArray;
/*      */ import net.sf.json.JSONException;
/*      */ import net.sf.json.JSONObject;
/*      */ import org.apache.commons.codec.binary.Base64;
/*      */ import org.apache.commons.lang.StringEscapeUtils;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.jsoup.Jsoup;
/*      */ import org.jsoup.safety.Whitelist;
import org.owasp.encoder.Encode;
/*      */ 
/*      */ public class StringUtil
/*      */ {
/*      */   public static final char UNDERLINE = '_';
/*      */ 
/*      */   public static String escape(String src)
/*      */   {
/*   41 */     StringBuffer tmp = new StringBuffer();
/*   42 */     tmp.ensureCapacity(src.length() * 6);
/*      */ 
/*   44 */     for (int i = 0; i < src.length(); i++)
/*      */     {
/*   46 */       char j = src.charAt(i);
/*      */ 
/*   48 */       if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || (Character.isUpperCase(j)))
/*      */       {
/*   50 */         tmp.append(j);
/*   51 */       } else if (j < 'Ā') {
/*   52 */         tmp.append("%");
/*   53 */         if (j < '\020')
/*   54 */           tmp.append("0");
/*   55 */         tmp.append(Integer.toString(j, 16));
/*      */       } else {
/*   57 */         tmp.append("%u");
/*   58 */         tmp.append(Integer.toString(j, 16));
/*      */       }
/*      */     }
/*   61 */     return tmp.toString();
/*      */   }
/*      */ 
/*      */   public static String replaceVariable(String template, Map<String, String> map)
/*      */     throws Exception
/*      */   {
/*   75 */     Pattern regex = Pattern.compile("\\{(.*?)\\}");
/*   76 */     Matcher regexMatcher = regex.matcher(template);
/*   77 */     while (regexMatcher.find()) {
/*   78 */       String key = regexMatcher.group(1);
/*   79 */       String toReplace = regexMatcher.group(0);
/*   80 */       String value = (String)map.get(key);
/*   81 */       if (value != null)
/*   82 */         template = template.replace(toReplace, value);
/*      */       else {
/*   84 */         throw new Exception(new StringBuilder().append("没有找到[").append(key).append("]对应的变量值，请检查表变量配置!").toString());
/*      */       }
/*      */     }
/*   87 */     return template;
/*      */   }
/*      */ 
/*      */   public static String unescape(String src)
/*      */   {
/*   97 */     StringBuffer tmp = new StringBuffer();
/*   98 */     tmp.ensureCapacity(src.length());
/*   99 */     int lastPos = 0; int pos = 0;
/*      */ 
/*  101 */     while (lastPos < src.length()) {
/*  102 */       pos = src.indexOf("%", lastPos);
/*  103 */       if (pos == lastPos) {
/*  104 */         if (src.charAt(pos + 1) == 'u') {
/*  105 */           char ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
/*      */ 
/*  107 */           tmp.append(ch);
/*  108 */           lastPos = pos + 6;
/*      */         } else {
/*  110 */           char ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
/*      */ 
/*  112 */           tmp.append(ch);
/*  113 */           lastPos = pos + 3;
/*      */         }
/*      */       }
/*  116 */       else if (pos == -1) {
/*  117 */         tmp.append(src.substring(lastPos));
/*  118 */         lastPos = src.length();
/*      */       } else {
/*  120 */         tmp.append(src.substring(lastPos, pos));
/*  121 */         lastPos = pos;
/*      */       }
/*      */     }
/*      */ 
/*  125 */     return tmp.toString();
/*      */   }
/*      */ 
/*      */   public static boolean isExist(String content, String begin, String end)
/*      */   {
/*  140 */     String tmp = content.toLowerCase();
/*  141 */     begin = begin.toLowerCase();
/*  142 */     end = end.toLowerCase();
/*  143 */     int beginIndex = tmp.indexOf(begin);
/*  144 */     int endIndex = tmp.indexOf(end);
/*  145 */     if ((beginIndex != -1) && (endIndex != -1) && (beginIndex < endIndex))
/*  146 */       return true;
/*  147 */     return false;
/*      */   }
/*      */ 
/*      */   public static String trimPrefix(String toTrim, String trimStr)
/*      */   {
/*  158 */     while (toTrim.startsWith(trimStr)) {
/*  159 */       toTrim = toTrim.substring(trimStr.length());
/*      */     }
/*  161 */     return toTrim;
/*      */   }
/*      */ 
/*      */   public static String trimSufffix(String toTrim, String trimStr)
/*      */   {
/*  172 */     while (toTrim.endsWith(trimStr)) {
/*  173 */       toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
/*      */     }
/*  175 */     return toTrim;
/*      */   }
/*      */ 
/*      */   public static String trim(String toTrim, String trimStr)
/*      */   {
/*  186 */     return trimSufffix(trimPrefix(toTrim, trimStr), trimStr);
/*      */   }
/*      */ 
/*      */   public static String escapeHtml(String content)
/*      */   {
/*  196 */     return StringEscapeUtils.escapeHtml(content);
/*      */   }
/*      */ 
/*      */   public static String unescapeHtml(String content)
/*      */   {
/*  206 */     return StringEscapeUtils.unescapeHtml(content);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(String str)
/*      */   {
/*  216 */     if (str == null)
/*  217 */       return true;
/*  218 */     if (str.trim().equals(""))
/*  219 */       return true;
/*  220 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(String str)
/*      */   {
/*  230 */     return !isEmpty(str);
/*      */   }
/*      */ 
/*      */   public static String replaceVariable(String template, String repaceStr) {
/*  234 */     Pattern regex = Pattern.compile("\\{(.*?)\\}");
/*  235 */     Matcher regexMatcher = regex.matcher(template);
/*  236 */     if (regexMatcher.find()) {
/*  237 */       String toReplace = regexMatcher.group(0);
/*  238 */       template = template.replace(toReplace, repaceStr);
/*      */     }
/*  240 */     return template;
/*      */   }
/*      */ 
/*      */   public static String subString(String str, int len, String chopped)
/*      */   {
/*  251 */     if ((str == null) || ("".equals(str)))
/*  252 */       return "";
/*  253 */     char[] chars = str.toCharArray();
/*  254 */     int cnLen = len * 2;
/*  255 */     String tmp = "";
/*  256 */     boolean isOver = false;
/*  257 */     int iLen = 0;
/*  258 */     for (int i = 0; i < chars.length; i++) {
/*  259 */       int iChar = chars[i];
/*  260 */       if (iChar <= 128)
/*  261 */         iLen += 1;
/*      */       else
/*  263 */         iLen += 2;
/*  264 */       if (iLen >= cnLen) {
/*  265 */         isOver = true;
/*  266 */         break;
/*      */       }
/*      */ 
/*  269 */       tmp = new StringBuilder().append(tmp).append(String.valueOf(chars[i])).toString();
/*      */     }
/*  271 */     if (isOver) {
/*  272 */       tmp = new StringBuilder().append(tmp).append(chopped).toString();
/*      */     }
/*  274 */     return tmp;
/*      */   }
/*      */ 
/*      */   public static String subString(String str)
/*      */   {
/*  284 */     int len = 25;
/*  285 */     String tmp = AppConfigUtil.get("titleLen");
/*  286 */     String regex = "<(?:(?:/([^>]+)>)|(?:!--([\\S|\\s]*?)-->)|(?:([^\\s/>]+)\\s*((?:(?:\"[^\"]*\")|(?:'[^']*')|[^\"'<>])*)/?>))";
/*  287 */     Pattern pattern = Pattern.compile(regex);
/*  288 */     Matcher matcher = pattern.matcher(str);
/*  289 */     if (matcher.find()) {
/*  290 */       return str;
/*      */     }
/*  292 */     if (isNotEmpty(tmp)) {
/*  293 */       len = Integer.parseInt(tmp);
/*      */     }
/*  295 */     return subString(str, len, "...");
/*      */   }
/*      */ 
/*      */   public static boolean isNumberic(String s)
/*      */   {
/*  307 */     if (StringUtils.isEmpty(s))
/*  308 */       return false;
/*  309 */     boolean rtn = validByRegex("^[-+]{0,1}\\d*\\.{0,1}\\d+$", s);
/*  310 */     if (rtn) {
/*  311 */       return true;
/*      */     }
/*  313 */     return validByRegex("^0[x|X][\\da-eA-E]+$", s);
/*      */   }
/*      */ 
/*      */   public static boolean isInteger(String s)
/*      */   {
/*  323 */     boolean rtn = validByRegex("^[-+]{0,1}\\d*$", s);
/*  324 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isEmail(String s)
/*      */   {
/*  335 */     boolean rtn = validByRegex("(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*", s);
/*      */ 
/*  337 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isMobile(String s)
/*      */   {
/*  347 */     boolean rtn = validByRegex("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", s);
/*      */ 
/*  349 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isPhone(String s)
/*      */   {
/*  359 */     boolean rtn = validByRegex("(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?", s);
/*      */ 
/*  361 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isZip(String s)
/*      */   {
/*  371 */     boolean rtn = validByRegex("^[0-9]{6}$", s);
/*  372 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isQq(String s)
/*      */   {
/*  382 */     boolean rtn = validByRegex("^[1-9]\\d{4,9}$", s);
/*  383 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isIp(String s)
/*      */   {
/*  393 */     boolean rtn = validByRegex("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", s);
/*      */ 
/*  396 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isChinese(String s)
/*      */   {
/*  406 */     boolean rtn = validByRegex("^[一-龥]+$", s);
/*  407 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isChrNum(String s)
/*      */   {
/*  417 */     boolean rtn = validByRegex("^([a-zA-Z0-9]+)$", s);
/*  418 */     return rtn;
/*      */   }
/*      */ 
/*      */   public static boolean isUrl(String url)
/*      */   {
/*  428 */     return validByRegex("(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url);
/*      */   }
/*      */ 
/*      */   public static Boolean isJson(String json)
/*      */   {
/*  440 */     if (isEmpty(json))
/*  441 */       return Boolean.valueOf(false);
/*      */     try {
/*  443 */       JSONObject.fromObject(json);
/*  444 */       return Boolean.valueOf(true);
/*      */     } catch (JSONException e) {
/*      */       try {
/*  447 */         JSONArray.fromObject(json);
/*  448 */         return Boolean.valueOf(true); } catch (JSONException ex) {  }
/*      */     }
/*  450 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */   public static boolean validByRegex(String regex, String input)
/*      */   {
/*  463 */     Pattern p = Pattern.compile(regex, 2);
/*  464 */     Matcher regexMatcher = p.matcher(input);
/*  465 */     return regexMatcher.find();
/*      */   }
/*      */ 
/*      */   public static boolean isNumeric(String str) {
				for (int i = str.length(); --i >= 0;) {
					if (!Character.isDigit(str.charAt(i))) {
						return false;
					}
				}
				return true;
}
/*      */ 
/*      */   public static String makeFirstLetterUpperCase(String newStr)
/*      */   {
/*  490 */     if (newStr.length() == 0) {
/*  491 */       return newStr;
/*      */     }
/*  493 */     char[] oneChar = new char[1];
/*  494 */     oneChar[0] = newStr.charAt(0);
/*  495 */     String firstChar = new String(oneChar);
/*  496 */     return new StringBuilder().append(firstChar.toUpperCase()).append(newStr.substring(1)).toString();
/*      */   }
/*      */ 
/*      */   public static String makeFirstLetterLowerCase(String newStr)
/*      */   {
/*  506 */     if (newStr.length() == 0) {
/*  507 */       return newStr;
/*      */     }
/*  509 */     char[] oneChar = new char[1];
/*  510 */     oneChar[0] = newStr.charAt(0);
/*  511 */     String firstChar = new String(oneChar);
/*  512 */     return new StringBuilder().append(firstChar.toLowerCase()).append(newStr.substring(1)).toString();
/*      */   }
/*      */ 
/*      */   public static String formatParamMsg(String message, Object... args)
/*      */   {
/*  523 */     for (int i = 0; i < args.length; i++) {
/*  524 */       message = message.replace(new StringBuilder().append("{").append(i).append("}").toString(), args[i].toString());
/*      */     }
/*  526 */     return message;
/*      */   }
/*      */ 
/*      */   public static String formatParamMsg(String message, Map<String, ?> params)
/*      */   {
/*  536 */     if (params == null)
/*  537 */       return message;
/*  538 */     Iterator keyIts = params.keySet().iterator();
/*  539 */     while (keyIts.hasNext()) {
/*  540 */       String key = (String)keyIts.next();
/*  541 */       Object val = params.get(key);
/*  542 */       if (val != null) {
/*  543 */         message = message.replace(new StringBuilder().append("${").append(key).append("}").toString(), val.toString());
/*      */       }
/*      */     }
/*  546 */     return message;
/*      */   }
/*      */ 
/*      */   public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object[] args)
/*      */   {
/*  561 */     int argsLen = args.length;
/*  562 */     boolean markFound = false;
/*      */ 
/*  564 */     StringBuilder sb = new StringBuilder(msgWithFormat);
/*      */ 
/*  566 */     if (argsLen > 0) {
/*  567 */       for (int i = 0; i < argsLen; i++) {
/*  568 */         String flag = new StringBuilder().append("%").append(i + 1).toString();
/*  569 */         int idx = sb.indexOf(flag);
/*      */ 
/*  571 */         while (idx >= 0) {
/*  572 */           markFound = true;
/*  573 */           sb.replace(idx, idx + 2, toString(args[i], autoQuote));
/*  574 */           idx = sb.indexOf(flag);
/*      */         }
/*      */       }
/*      */ 
/*  578 */       if ((args[(argsLen - 1)] instanceof Throwable)) {
/*  579 */         StringWriter sw = new StringWriter();
/*  580 */         ((Throwable)args[(argsLen - 1)]).printStackTrace(new PrintWriter(sw));
/*      */ 
/*  582 */         sb.append("\n").append(sw.toString());
/*  583 */       } else if ((argsLen == 1) && (!markFound)) {
/*  584 */         sb.append(args[(argsLen - 1)].toString());
/*      */       }
/*      */     }
/*  587 */     return sb;
/*      */   }
/*      */ 
/*      */   public static StringBuilder formatMsg(String msgWithFormat, Object[] args) {
/*  591 */     return formatMsg(new StringBuilder(msgWithFormat), true, args);
/*      */   }
/*      */ 
/*      */   public static String toString(Object obj, boolean autoQuote) {
/*  595 */     StringBuilder sb = new StringBuilder();
/*  596 */     if (obj == null) {
/*  597 */       sb.append("NULL");
/*      */     }
/*  599 */     else if ((obj instanceof Object[])) {
/*  600 */       for (int i = 0; i < ((Object[])obj).length; i++) {
/*  601 */         sb.append(((Object[])(Object[])obj)[i]).append(", ");
/*      */       }
/*  603 */       if (sb.length() > 0)
/*  604 */         sb.delete(sb.length() - 2, sb.length());
/*      */     }
/*      */     else {
/*  607 */       sb.append(obj.toString());
/*      */     }
/*      */ 
/*  610 */     if ((autoQuote) && (sb.length() > 0) && ((sb.charAt(0) != '[') || (sb.charAt(sb.length() - 1) != ']')) && ((sb.charAt(0) != '{') || (sb.charAt(sb.length() - 1) != '}')))
/*      */     {
/*  614 */       sb.insert(0, "[").append("]");
/*      */     }
/*  616 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String returnSpace(String str) {
/*  620 */     String space = "";
/*  621 */     if (!str.isEmpty()) {
/*  622 */       String[] path = str.split("\\.");
/*  623 */       for (int i = 0; i < path.length - 1; i++) {
/*  624 */         space = new StringBuilder().append(space).append("&nbsp;&emsp;").toString();
/*      */       }
/*      */     }
/*  627 */     return space;
/*      */   }
/*      */ 
/*      */   public static synchronized String encryptSha256(String inputStr)
/*      */   {
/*      */     try
/*      */     {
/*  639 */       MessageDigest md = MessageDigest.getInstance("SHA-256");
/*  640 */       byte[] digest = md.digest(inputStr.getBytes("UTF-8"));
/*  641 */       return new String(Base64.encodeBase64(digest)); } catch (Exception e) {
/*      */     }
/*  643 */     return null;
/*      */   }
/*      */ 
/*      */   public static synchronized String encryptMd5(String inputStr)
/*      */   {
/*      */     try {
/*  649 */       MessageDigest md = MessageDigest.getInstance("MD5");
/*  650 */       md.update(inputStr.getBytes());
/*  651 */       byte[] digest = md.digest();
/*  652 */       StringBuffer sb = new StringBuffer();
/*  653 */       for (byte b : digest) {
/*  654 */         sb.append(Integer.toHexString(b & 0xFF));
/*      */       }
/*      */ 
/*  657 */       return sb.toString(); } catch (Exception e) {
/*      */     }
/*  659 */     return null;
/*      */   }
/*      */ 
/*      */   public static String getArrayAsString(List<String> arr)
/*      */   {
/*  670 */     if ((arr == null) || (arr.size() == 0))
/*  671 */       return "";
/*  672 */     StringBuffer sb = new StringBuffer();
/*  673 */     for (int i = 0; i < arr.size(); i++) {
/*  674 */       if (i > 0)
/*  675 */         sb.append(",");
/*  676 */       sb.append((String)arr.get(i));
/*      */     }
/*  678 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String getArrayAsString(String[] arr)
/*      */   {
/*  688 */     if ((arr == null) || (arr.length == 0))
/*  689 */       return "";
/*  690 */     StringBuffer sb = new StringBuffer();
/*  691 */     for (int i = 0; i < arr.length; i++) {
/*  692 */       if (i > 0)
/*  693 */         sb.append("#");
/*  694 */       sb.append(arr[i]);
/*      */     }
/*  696 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String getSetAsString(Set<?> set)
/*      */   {
/*  706 */     if ((set == null) || (set.size() == 0))
/*  707 */       return "";
/*  708 */     StringBuffer sb = new StringBuffer();
/*  709 */     int i = 0;
/*  710 */     Iterator it = set.iterator();
/*  711 */     while (it.hasNext()) {
/*  712 */       if (i++ > 0)
/*  713 */         sb.append(",");
/*  714 */       sb.append(it.next().toString());
/*      */     }
/*  716 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String hangeToBig(double value)
/*      */   {
/*  727 */     char[] hunit = { '拾', '佰', '仟' };
/*  728 */     char[] vunit = { '万', '亿' };
/*  729 */     char[] digit = { 38646, '壹', 36144, '叁', 32902, '伍', 38470, '柒', '捌', '玖' };
/*  730 */     String zheng = "整";
/*  731 */     String jiao = "角";
/*  732 */     String fen = "分";
/*  733 */     char yuan = '圆';
/*  734 */     long midVal = (long)(value * 100.0D);
/*  735 */     String valStr = String.valueOf(midVal);
/*      */ 
/*  737 */     String head = valStr.substring(0, valStr.length() - 2);
/*  738 */     int len = head.length();
/*  739 */     if (len > 12) {
/*  740 */       return "值过大";
/*      */     }
/*  742 */     String rail = valStr.substring(valStr.length() - 2);
/*      */ 
/*  744 */     String prefix = "";
/*  745 */     String suffix = "";
/*      */ 
/*  747 */     if (rail.equals("00"))
/*  748 */       suffix = zheng;
/*      */     else {
/*  750 */       suffix = new StringBuilder().append(digit[(rail.charAt(0) - '0')]).append(jiao).append(digit[(rail.charAt(1) - '0')]).append(fen).toString();
/*      */     }
/*      */ 
/*  754 */     char[] chDig = head.toCharArray();
/*  755 */     char zero = '0';
/*  756 */     byte zeroSerNum = 0;
/*  757 */     for (int i = 0; i < chDig.length; i++) {
/*  758 */       int idx = (chDig.length - i - 1) % 4;
/*  759 */       int vidx = (chDig.length - i - 1) / 4;
/*  760 */       if (chDig[i] == '0') {
/*  761 */         zeroSerNum = (byte)(zeroSerNum + 1);
/*  762 */         if (zero == '0') {
/*  763 */           zero = digit[0];
/*  764 */         } else if ((idx == 0) && (vidx > 0) && (zeroSerNum < 4)) {
/*  765 */           prefix = new StringBuilder().append(prefix).append(vunit[(vidx - 1)]).toString();
/*  766 */           zero = '0';
/*      */         }
/*      */       }
/*      */       else {
/*  770 */         zeroSerNum = 0;
/*  771 */         if (zero != '0') {
/*  772 */           prefix = new StringBuilder().append(prefix).append(zero).toString();
/*  773 */           zero = '0';
/*      */         }
/*  775 */         prefix = new StringBuilder().append(prefix).append(digit[(chDig[i] - '0')]).toString();
/*  776 */         if (idx > 0)
/*  777 */           prefix = new StringBuilder().append(prefix).append(hunit[(idx - 1)]).toString();
/*  778 */         if ((idx == 0) && (vidx > 0)) {
/*  779 */           prefix = new StringBuilder().append(prefix).append(vunit[(vidx - 1)]).toString();
/*      */         }
/*      */       }
/*      */     }
/*  783 */     if (prefix.length() > 0)
/*  784 */       prefix = new StringBuilder().append(prefix).append(yuan).toString();
/*  785 */     return new StringBuilder().append(prefix).append(suffix).toString();
/*      */   }
/*      */ 
/*      */   public static String jsonUnescape(String str)
/*      */   {
/*  796 */     return str.replace("&quot;", "\"").replace("&nuot;", "\n");
/*      */   }
/*      */ 
/*      */   public static String htmlEntityToString(String dataStr)
/*      */   {
/*  806 */     dataStr = dataStr.replace("&apos;", "'").replace("&quot;", "\"").replace("&gt;", ">").replace("&lt;", "<").replace("&amp;", "&");
/*      */ 
/*  810 */     int start = 0;
/*  811 */     int end = 0;
/*  812 */     StringBuffer buffer = new StringBuffer();
/*      */ 
/*  814 */     while (start > -1) {
/*  815 */       int system = 10;
/*  816 */       if (start == 0) {
/*  817 */         int t = dataStr.indexOf("&#");
/*  818 */         if (start != t) {
/*  819 */           start = t;
/*      */         }
/*  821 */         if (start > 0) {
/*  822 */           buffer.append(dataStr.substring(0, start));
/*      */         }
/*      */       }
/*  825 */       end = dataStr.indexOf(";", start + 2);
/*  826 */       String charStr = "";
/*  827 */       if (end != -1) {
/*  828 */         charStr = dataStr.substring(start + 2, end);
/*      */ 
/*  830 */         char s = charStr.charAt(0);
/*  831 */         if ((s == 'x') || (s == 'X')) {
/*  832 */           system = 16;
/*  833 */           charStr = charStr.substring(1);
/*      */         }
/*      */       }
/*      */       try
/*      */       {
/*  838 */         if (isNotEmpty(charStr)) {
/*  839 */           char letter = (char)Integer.parseInt(charStr, system);
/*  840 */           buffer.append(new Character(letter).toString());
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (NumberFormatException e)
/*      */       {
/*      */       }
/*      */ 
/*  848 */       start = dataStr.indexOf("&#", end);
/*  849 */       if (start - end > 1) {
/*  850 */         buffer.append(dataStr.substring(end + 1, start));
/*      */       }
/*      */ 
/*  854 */       if (start == -1) {
/*  855 */         int length = dataStr.length();
/*  856 */         if (end + 1 != length) {
/*  857 */           buffer.append(dataStr.substring(end + 1, length));
/*      */         }
/*      */       }
/*      */     }
/*  861 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */   public static String stringToHtmlEntity(String str)
/*      */   {
/*  871 */     StringBuffer sb = new StringBuffer();
/*  872 */     for (int i = 0; i < str.length(); i++) {
/*  873 */       char c = str.charAt(i);
/*      */ 
/*  875 */       switch (c) {
/*      */       case '\n':
/*  877 */         sb.append(c);
/*  878 */         break;
/*      */       case '<':
/*  881 */         sb.append("&lt;");
/*  882 */         break;
/*      */       case '>':
/*  885 */         sb.append("&gt;");
/*  886 */         break;
/*      */       case '&':
/*  889 */         sb.append("&amp;");
/*  890 */         break;
/*      */       case '\'':
/*  893 */         sb.append("&apos;");
/*  894 */         break;
/*      */       case '"':
/*  897 */         sb.append("&quot;");
/*  898 */         break;
/*      */       default:
/*  901 */         if ((c < ' ') || (c > '~')) {
/*  902 */           sb.append("&#x");
/*  903 */           sb.append(Integer.toString(c, 16));
/*  904 */           sb.append(';');
/*      */         } else {
/*  906 */           sb.append(c);
/*      */         }break;
/*      */       }
/*      */     }
/*  910 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String encodingString(String str, String from, String to)
/*      */   {
/*  925 */     String result = str;
/*      */     try {
/*  927 */       result = new String(str.getBytes(from), to);
/*      */     } catch (Exception e) {
/*  929 */       result = str;
/*      */     }
/*  931 */     return result;
/*      */   }
/*      */ 
/*      */   public static String comdify(String value)
/*      */   {
/*  938 */     DecimalFormat df = null;
/*  939 */     if (value.indexOf(".") > 0) {
/*  940 */       int i = value.length() - value.indexOf(".") - 1;
/*  941 */       switch (i) {
/*      */       case 0:
/*  943 */         df = new DecimalFormat("###,##0");
/*  944 */         break;
/*      */       case 1:
/*  946 */         df = new DecimalFormat("###,##0.0");
/*  947 */         break;
/*      */       case 2:
/*  949 */         df = new DecimalFormat("###,##0.00");
/*  950 */         break;
/*      */       case 3:
/*  952 */         df = new DecimalFormat("###,##0.000");
/*  953 */         break;
/*      */       case 4:
/*  955 */         df = new DecimalFormat("###,##0.0000");
/*  956 */         break;
/*      */       default:
/*  958 */         df = new DecimalFormat("###,##0.00000");
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  963 */       df = new DecimalFormat("###,##0");
/*      */     }
/*  965 */     double number = 0.0D;
/*      */     try {
/*  967 */       number = Double.parseDouble(value);
/*      */     } catch (Exception e) {
/*  969 */       number = 0.0D;
/*      */     }
/*  971 */     return df.format(number);
/*      */   }
/*      */ 
/*      */   public static String convertScriptLine(String arg, Boolean flag)
/*      */   {
/*  984 */     if (StringUtils.isEmpty(arg))
/*  985 */       return arg;
/*  986 */     String origStr = "\n"; String targStr = "/n";
/*  987 */     if (!flag.booleanValue()) {
/*  988 */       origStr = "/n";
/*  989 */       targStr = "\n";
/*      */     }
/*  991 */     String[] args = arg.split(origStr);
/*  992 */     StringBuffer sb = new StringBuffer();
/*  993 */     for (int i = 0; i < args.length; i++) {
/*  994 */       sb.append(args[i]);
/*  995 */       if (args.length != i + 1)
/*  996 */         sb.append(targStr);
/*      */     }
/*  998 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String convertLine(String arg, Boolean flag)
/*      */   {
/* 1011 */     if (StringUtils.isEmpty(arg))
/* 1012 */       return arg;
/* 1013 */     String origStr = "\n"; String targStr = "/n";
/* 1014 */     if (!flag.booleanValue()) {
/* 1015 */       origStr = "/n";
/* 1016 */       targStr = "\n";
/*      */     }
/* 1018 */     String[] args = arg.split(origStr);
/* 1019 */     StringBuffer sb = new StringBuffer();
/* 1020 */     for (int i = 0; i < args.length; i++) {
/* 1021 */       sb.append(StringUtils.deleteWhitespace(args[i]));
/* 1022 */       if (args.length != i + 1)
/* 1023 */         sb.append(targStr);
/*      */     }
/* 1025 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String deleteWhitespaceLine(String arg)
/*      */   {
/* 1036 */     if (StringUtils.isEmpty(arg))
/* 1037 */       return arg;
/* 1038 */     String origStr = "\n";
/* 1039 */     String[] args = arg.split(origStr);
/* 1040 */     StringBuffer sb = new StringBuffer();
/* 1041 */     for (int i = 0; i < args.length; i++) {
/* 1042 */       sb.append(StringUtils.deleteWhitespace(args[i]));
/* 1043 */       if (args.length != i + 1)
/* 1044 */         sb.append(origStr);
/*      */     }
/* 1046 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String parseText(String arg)
/*      */   {
/* 1056 */     if (StringUtils.isEmpty(arg))
/* 1057 */       return arg;
/* 1058 */     String[] args = arg.split("\n");
/* 1059 */     StringBuffer sb = new StringBuffer();
/* 1060 */     for (int i = 0; i < args.length; i++) {
/* 1061 */       sb.append(args[i]);
/* 1062 */       if (args.length != i + 1)
/* 1063 */         sb.append("</br>");
/*      */     }
/* 1065 */     return sb.toString();
/*      */   }
/*      */ 
			public static String parseAndEscapeText(String arg)
/*      */   {
/* 1056 */     if (StringUtils.isEmpty(arg))
/* 1057 */       return arg;
/* 1058 */     String[] args = arg.split("\n");
/* 1059 */     StringBuffer sb = new StringBuffer();
/* 1060 */     for (int i = 0; i < args.length; i++) {
/* 1061 */       sb.append(args[i]);
/* 1062 */       if (args.length != i + 1)
/* 1063 */         sb.append("</br>");
/*      */     }
/* 1065 */     return Encode.forHtmlContent(sb.toString());
/*      */   }

/*      */   public static String replaceNotVisable(String str)
/*      */   {
/* 1075 */     char[] ary = str.toCharArray();
/* 1076 */     List list = new ArrayList();
/* 1077 */     for (int i = 0; i < ary.length; i++) {
/* 1078 */       int c = ary[i];
/* 1079 */       if (isViable(c))
/*      */       {
/* 1081 */         list.add(Character.valueOf((char)c));
/*      */       }
/*      */     }
/* 1083 */     Object[] aryc = (Object[])list.toArray();
/* 1084 */     char[] arycc = new char[aryc.length];
/* 1085 */     for (int i = 0; i < aryc.length; i++) {
/* 1086 */       arycc[i] = ((Character)aryc[i]).charValue();
/*      */     }
/* 1088 */     String out = new String(arycc);
/* 1089 */     return out;
/*      */   }
/*      */ 
/*      */   private static boolean isViable(int i) {
/* 1093 */     if ((i == 0) || (i == 13) || ((i >= 9) && (i <= 10)) || ((i >= 11) && (i <= 12)) || ((i >= 28) && (i <= 126)) || ((i >= 19968) && (i <= 40869)))
/*      */     {
/* 1095 */       return true;
/*      */     }
/* 1097 */     return false;
/*      */   }
/*      */ 
/*      */   public static String replaceAll(String toReplace, String replace, String replaceBy)
/*      */   {
/* 1110 */     replaceBy = replaceBy.replaceAll("\\\\", "&#92");
/* 1111 */     replaceBy = replaceBy.replaceAll("\\$", "\\\\\\$").replaceAll("\"", "&quot");
/* 1112 */     return toReplace.replaceAll(replace, replaceBy);
/*      */   }
/*      */ 
/*      */   public static String stringFormat2Json(String json) {
/* 1116 */     StringBuilder sb = new StringBuilder();
/* 1117 */     int size = json.length();
/* 1118 */     for (int i = 0; i < size; i++) {
/* 1119 */       char c = json.charAt(i);
/* 1120 */       switch (c) {
/*      */       case '\b':
/* 1122 */         sb.append("\\b");
/* 1123 */         break;
/*      */       case '\f':
/* 1125 */         sb.append("\\f");
/* 1126 */         break;
/*      */       case '\n':
/* 1128 */         sb.append("\\n");
/* 1129 */         break;
/*      */       case '\r':
/* 1131 */         sb.append("\\r");
/* 1132 */         break;
/*      */       case '\t':
/* 1134 */         sb.append("\\t");
/* 1135 */         break;
/*      */       case '\013':
/*      */       default:
/* 1137 */         sb.append(c);
/*      */       }
/*      */     }
/*      */ 
/* 1141 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String getNumber(Object value, Object isShowComdify, Object decimalValue, Object coinValue)
/*      */   {
/* 1159 */     if (value == null)
/* 1160 */       return "";
/* 1161 */     String val = value.toString();
/*      */ 
/* 1163 */     if (isShowComdify != null) {
/* 1164 */       int result = 0;
/*      */ 
/* 1166 */       String temp = isShowComdify.toString();
/* 1167 */       boolean isInteger = temp.matches("^[0-9]*$");
/* 1168 */       if (isInteger) {
/* 1169 */         result = Short.parseShort(temp);
/*      */       }
/* 1171 */       else if ("true".equals(temp))
/* 1172 */         result = 1;
/* 1173 */       else if ("false".equals(temp)) {
/* 1174 */         result = 0;
/*      */       }
/*      */ 
/* 1177 */       Double douvalue = Double.valueOf(Double.parseDouble(val));
/* 1178 */       DecimalFormat df = new DecimalFormat("");
/* 1179 */       val = df.format(douvalue);
/* 1180 */       if (result != 1) {
/* 1181 */         val = val.replace(",", "");
/*      */       }
/*      */     }
/*      */ 
/* 1185 */     if (decimalValue != null) {
/* 1186 */       int len = Integer.parseInt(decimalValue.toString());
/*      */ 
/* 1188 */       if (len > 0) {
/* 1189 */         int idx = val.indexOf(".");
/* 1190 */         if (idx == -1) {
/* 1191 */           val = new StringBuilder().append(val).append(".").append(getZeroLen(len)).toString();
/*      */         } else {
/* 1193 */           String intStr = val.substring(0, val.indexOf("."));
/* 1194 */           String decimal = val.substring(val.indexOf(".") + 1);
/* 1195 */           if (decimal.length() > len) {
/* 1196 */             Double douvalue = Double.valueOf(Double.parseDouble(val.replace(",", "")));
/*      */ 
/* 1198 */             DecimalFormat df = new DecimalFormat("");
/* 1199 */             df.setMaximumFractionDigits(len);
/* 1200 */             String tmp = df.format(douvalue);
/* 1201 */             if (tmp.indexOf(".") == -1) {
/* 1202 */               val = new StringBuilder().append(intStr).append(".").append(getZeroLen(len)).toString();
/*      */             } else {
/* 1204 */               decimal = tmp.substring(tmp.indexOf(".") + 1);
/* 1205 */               val = new StringBuilder().append(intStr).append(".").append(decimal).toString();
/*      */             }
/* 1207 */           } else if (decimal.length() < len) {
/* 1208 */             int tmp = len - decimal.length();
/* 1209 */             val = new StringBuilder().append(val).append(getZeroLen(tmp)).toString();
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1216 */     boolean foundPoint = val.matches("^\\.\\d+");
/* 1217 */     if (foundPoint) {
/* 1218 */       val = new StringBuilder().append(0).append(val).toString();
/*      */     }
/*      */ 
/* 1222 */     boolean foundNegativePoint = val.matches("^\\-.\\d+");
/* 1223 */     if (foundNegativePoint) {
/* 1224 */       val = val.replaceFirst("-", "-0");
/*      */     }
/*      */ 
/* 1227 */     if (coinValue != null) {
/* 1228 */       val = new StringBuilder().append(coinValue.toString()).append(val).toString();
/*      */     }
/* 1230 */     return val;
/*      */   }
/*      */ 
/*      */   private static String getZeroLen(int len) {
/* 1234 */     String str = "";
/* 1235 */     for (int i = 0; i < len; i++) {
/* 1236 */       str = new StringBuilder().append(str).append("0").toString();
/*      */     }
/* 1238 */     return str;
/*      */   }
/*      */ 
/*      */   public static String removeHTMLTag(String htmlStr)
/*      */   {
/* 1250 */     if (isEmpty(htmlStr))
/* 1251 */       return "";
/* 1252 */     htmlStr = Jsoup.clean(htmlStr, Whitelist.none());
/* 1253 */     htmlStr = htmlEntityToString(htmlStr);
/* 1254 */     return htmlStr.trim();
/*      */   }
/*      */ 
/*      */   public static boolean contain(String str, String searchStr)
/*      */   {
/* 1264 */     return contain(str, searchStr, ",", true);
/*      */   }
/*      */ 
/*      */   public static boolean contain(String str, String searchStr, String argumentSeparator, boolean isIgnoreCase)
/*      */   {
/* 1274 */     if (isEmpty(str))
/* 1275 */       return false;
/* 1276 */     if (isEmpty(argumentSeparator))
/* 1277 */       argumentSeparator = ",";
/* 1278 */     String[] aryStr = str.split(argumentSeparator);
/* 1279 */     return contain(aryStr, searchStr, isIgnoreCase);
/*      */   }
/*      */ 
/*      */   public static boolean contain(String[] aryStr, String searchStr, boolean isIgnoreCase)
/*      */   {
/* 1290 */     if (BeanUtils.isEmpty(aryStr))
/* 1291 */       return false;
/* 1292 */     for (String str : aryStr) {
/* 1293 */       if (isIgnoreCase) {
/* 1294 */         if (str.equalsIgnoreCase(searchStr))
/* 1295 */           return true;
/*      */       }
/* 1297 */       else if (str.equals(searchStr)) {
/* 1298 */         return true;
/*      */       }
/*      */     }
/*      */ 
/* 1302 */     return false;
/*      */   }
/*      */ 
/*      */   public static int getCount(String str, int type)
/*      */   {
/* 1312 */     int len = str.length();
/* 1313 */     int chineseCount = 0;
/* 1314 */     int letterCount = 0;
/* 1315 */     int blankCount = 0;
/* 1316 */     int numCount = 0;
/* 1317 */     int otherCount = 0;
/* 1318 */     for (int i = 0; i < len; i++) {
/* 1319 */       char tem = str.charAt(i);
/* 1320 */       Character.UnicodeBlock ub = Character.UnicodeBlock.of(tem);
/* 1321 */       if (((tem > 'A') && (tem < 'Z')) || ((tem > 'a') && (tem < 'z')))
/* 1322 */         letterCount++;
/* 1323 */       else if (tem == ' ')
/* 1324 */         blankCount++;
/* 1325 */       else if ((tem > '0') && (tem < '9'))
/* 1326 */         numCount++;
/* 1327 */       else if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) || (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) || (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) || (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) || (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) || (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) || (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION))
/*      */       {
/* 1331 */         chineseCount++;
/*      */       }
/* 1333 */       else otherCount++;
/*      */     }
/*      */ 
/* 1336 */     switch (type) { case -1:
/* 1337 */       return chineseCount;
/*      */     case 0:
/* 1338 */       return letterCount;
/*      */     case 1:
/* 1339 */       return blankCount;
/*      */     case 2:
/* 1340 */       return numCount;
/*      */     case 3:
/* 1341 */       return otherCount;
/*      */     }
/* 1343 */     return otherCount;
/*      */   }
/*      */ 
/*      */   public static int getTotalSize(String str) {
/* 1347 */     int chineseCount = getCount(str, -1);
/* 1348 */     int letterCount = getCount(str, 0);
/* 1349 */     int blankCount = getCount(str, 1);
/* 1350 */     int numCount = getCount(str, 2);
/* 1351 */     int otherCount = getCount(str, 3);
/* 1352 */     return chineseCount + (letterCount + numCount) / 3 + blankCount / 4 + otherCount * 3 / 4;
/*      */   }
/*      */ 
/*      */   public static String getUrl(String url, String params)
/*      */   {
/* 1362 */     if (isEmpty(url))
/* 1363 */       return url;
/* 1364 */     if (url.indexOf("?") > 0) {
/* 1365 */       if (isNotEmpty(params))
/* 1366 */         url = new StringBuilder().append(url).append("&").append(params).toString();
/*      */       else
/* 1368 */         url = new StringBuilder().append(url).append("?").append(params).toString();
/* 1369 */     } else if (isNotEmpty(params))
/* 1370 */       url = new StringBuilder().append(url).append("?").append(params).toString();
/* 1371 */     return url;
/*      */   }
/*      */ 
/*      */   public static String camelToUnderline(String param)
/*      */   {
/* 1382 */     if ((param == null) || ("".equals(param.trim()))) {
/* 1383 */       return "";
/*      */     }
/* 1385 */     int len = param.length();
/* 1386 */     StringBuilder sb = new StringBuilder(len);
/* 1387 */     for (int i = 0; i < len; i++) {
/* 1388 */       char c = param.charAt(i);
/* 1389 */       if (Character.isUpperCase(c)) {
/* 1390 */         sb.append('_');
/* 1391 */         sb.append(Character.toLowerCase(c));
/*      */       } else {
/* 1393 */         sb.append(c);
/*      */       }
/*      */     }
/* 1396 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String underlineToCamel(String param)
/*      */   {
/* 1404 */     if ((param == null) || ("".equals(param.trim()))) {
/* 1405 */       return "";
/*      */     }
/* 1407 */     int len = param.length();
/* 1408 */     StringBuilder sb = new StringBuilder(len);
/* 1409 */     for (int i = 0; i < len; i++) {
/* 1410 */       char c = param.charAt(i);
/* 1411 */       if (c == '_') {
/* 1412 */         i++; if (i < len)
/* 1413 */           sb.append(Character.toUpperCase(param.charAt(i)));
/*      */       }
/*      */       else {
/* 1416 */         sb.append(c);
/*      */       }
/*      */     }
/* 1419 */     return sb.toString();
/*      */   }
/*      */   public static List<String> stringToList(String str) {
/* 1422 */     List list = new ArrayList();
/* 1423 */     if ((str != null) && (!str.equals(""))) {
/* 1424 */       if ((str.contains("[")) || (str.contains("]"))) {
/* 1425 */         String[] strs = str.split(",");
/* 1426 */         for (String str1 : strs) {
/* 1427 */           if (str1.contains("[")) {
/* 1428 */             str1 = str1.replace("[", "");
/*      */           }
/* 1430 */           if (str1.contains("]")) {
/* 1431 */             str1 = str1.replace("]", "");
/*      */           }
/* 1433 */           str1 = str1.replaceAll("\"", "");
/* 1434 */           list.add(str1);
/*      */         }
/* 1436 */         return list;
/*      */       }
/* 1438 */       list.add(str);
/*      */     }
/*      */ 
/* 1441 */     return list;
/*      */   }
/*      */ 
/*      */   public static String getParam(String mrthor)
/*      */   {
/* 1447 */     Pattern p = Pattern.compile("\\(.*?\\)");
/* 1448 */     Matcher m = p.matcher(mrthor);
/* 1449 */     String param = null;
/* 1450 */     while (m.find()) {
/* 1451 */       param = m.group().replaceAll("\\(\\)", "");
/* 1452 */       param = param.replace("(", "");
/* 1453 */       param = param.replace(")", "");
/* 1454 */       param = param.replace("\"", "");
/*      */     }
/* 1456 */     return param;
/*      */   }
/*      */ 
/*      */   public static Object parserObject(Object obj, String type)
/*      */   {
/* 1466 */     if (BeanUtils.isEmpty(obj))
/* 1467 */       return null;
/* 1468 */     Object val = obj;
/*      */     try {
/* 1470 */       String str = obj.toString();
/* 1471 */       if (type.equalsIgnoreCase("string")) {
/* 1472 */         val = str;
/* 1473 */       } else if (type.equalsIgnoreCase("int")) {
/* 1474 */         val = Integer.valueOf(Integer.parseInt(str));
/* 1475 */       } else if (type.equalsIgnoreCase("float")) {
/* 1476 */         val = Float.valueOf(Float.parseFloat(str));
/* 1477 */       } else if (type.equalsIgnoreCase("double")) {
/* 1478 */         val = Double.valueOf(Double.parseDouble(str));
/* 1479 */       } else if (type.equalsIgnoreCase("byte")) {
/* 1480 */         val = Byte.valueOf(Byte.parseByte(str));
/* 1481 */       } else if (type.equalsIgnoreCase("short")) {
/* 1482 */         val = Short.valueOf(Short.parseShort(str));
/* 1483 */       } else if (type.equalsIgnoreCase("long")) {
/* 1484 */         val = Long.valueOf(Long.parseLong(str));
/* 1485 */       } else if (type.equalsIgnoreCase("boolean")) {
/* 1486 */         if (StringUtils.isNumeric(str))
/* 1487 */           val = Boolean.valueOf(Integer.parseInt(str) == 1);
/* 1488 */         val = Boolean.valueOf(Boolean.parseBoolean(str));
/* 1489 */       } else if (type.equalsIgnoreCase("date")) {
/* 1490 */         val = DateFormatUtil.parse(str);
/*      */       } else {
/* 1492 */         val = str;
/*      */       }
/*      */     }
/*      */     catch (Exception e) {
/*      */     }
/* 1497 */     return val;
/*      */   }
/*      */ 
/*      */   public static String join(String[] aryStr, String separator)
/*      */   {
/* 1507 */     if (aryStr == null) return null;
/* 1508 */     if (aryStr.length == 1) {
/* 1509 */       return aryStr[0];
/*      */     }
/* 1511 */     String str = "";
/* 1512 */     for (int i = 0; i < aryStr.length; i++) {
/* 1513 */       if (i == 0) {
/* 1514 */         str = new StringBuilder().append(str).append(aryStr[i]).toString();
/*      */       }
/*      */       else {
/* 1517 */         str = new StringBuilder().append(str).append(separator).append(aryStr[i]).toString();
/*      */       }
/*      */     }
/* 1520 */     return str;
/*      */   }
/*      */ 
/*      */   public static String toString(Object obj)
/*      */   {
/* 1532 */     if (obj == null) {
/* 1533 */       return "";
/*      */     }
/* 1535 */     return obj.toString();
/*      */   }
/*      */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.StringUtil
 * JD-Core Version:    0.6.2
 */