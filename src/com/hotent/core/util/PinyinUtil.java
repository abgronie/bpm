/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import net.sourceforge.pinyin4j.PinyinHelper;
/*     */ import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
/*     */ import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
/*     */ import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
/*     */ import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
/*     */ import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PinyinUtil
/*     */ {
/*     */   public static String getPinyin(String chinese)
/*     */   {
/*  37 */     return getPinyinZh_CN(convertStringByChinese(chinese));
/*     */   }
/*     */ 
/*     */   public static String getPinyinToUpperCase(String chinese)
/*     */   {
/*  48 */     return getPinyinZh_CN(convertStringByChinese(chinese)).toUpperCase();
/*     */   }
/*     */ 
/*     */   public static String getPinyinToLowerCase(String chinese)
/*     */   {
/*  59 */     return getPinyinZh_CN(convertStringByChinese(chinese)).toLowerCase();
/*     */   }
/*     */ 
/*     */   public static String getPinyinFirstToUpperCase(String chinese)
/*     */   {
/*  70 */     return getPinyin(chinese);
/*     */   }
/*     */ 
/*     */   private static HanyuPinyinOutputFormat getDefaultFormat()
/*     */   {
/*  81 */     HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
/*  82 */     format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
/*  83 */     format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
/*  84 */     format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
/*  85 */     return format;
/*     */   }
/*     */ 
/*     */   private static Set<String> convertStringByChinese(String chinese)
/*     */   {
/*  96 */     char[] chars = chinese.toCharArray();
/*  97 */     if ((chinese != null) && (!chinese.trim().equalsIgnoreCase(""))) {
/*  98 */       char[] srcChar = chinese.toCharArray();
/*  99 */       String[][] temp = new String[chinese.length()][];
/* 100 */       for (int i = 0; i < srcChar.length; i++) {
/* 101 */         char c = srcChar[i];
/*     */ 
/* 103 */         if ((String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) || (String.valueOf(c).matches("[\\u3007]")))
/*     */           try
/*     */           {
/* 106 */             temp[i] = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultFormat());
/*     */           }
/*     */           catch (BadHanyuPinyinOutputFormatCombination e)
/*     */           {
/* 110 */             e.printStackTrace();
/*     */           }
/*     */         else {
					temp[i] = new String[] { String.valueOf(srcChar[i]) };
/*     */         }
/*     */       }
/* 116 */       String[] pingyinArray = exchange(temp);
/* 117 */       Set pinyin = new HashSet();
/* 118 */       for (int i = 0; i < pingyinArray.length; i++) {
/* 119 */         pinyin.add(pingyinArray[i]);
/*     */       }
/* 121 */       return pinyin;
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   private static String[] exchange(String[][] strJaggedArray)
/*     */   {
/* 135 */     String[][] temp = doExchange(strJaggedArray);
/* 136 */     return temp[0];
/*     */   }
/*     */ 
/*     */   private static String[][] doExchange(String[][] strJaggedArray)
/*     */   {
/* 146 */     int len = strJaggedArray.length;
/* 147 */     if (len >= 2) {
/* 148 */       int len1 = strJaggedArray[0].length;
/* 149 */       int len2 = strJaggedArray[1].length;
/* 150 */       int newlen = len1 * len2;
/* 151 */       String[] temp = new String[newlen];
/* 152 */       int index = 0;
/* 153 */       for (int i = 0; i < len1; i++) {
/* 154 */         for (int j = 0; j < len2; j++) {
/* 155 */           temp[index] = new StringBuilder().append(capitalize(strJaggedArray[0][i])).append(capitalize(strJaggedArray[1][j])).toString();
/*     */ 
/* 157 */           index++;
/*     */         }
/*     */       }
/* 160 */       String[][] newArray = new String[len - 1][];
/* 161 */       for (int i = 2; i < len; i++) {
/* 162 */         newArray[(i - 1)] = strJaggedArray[i];
/*     */       }
/* 164 */       newArray[0] = temp;
/* 165 */       return doExchange(newArray);
/*     */     }
/* 167 */     return strJaggedArray;
/*     */   }
/*     */ 
/*     */   private static String capitalize(String s)
/*     */   {
/* 178 */     char[] ch = s.toCharArray();
/* 179 */     if ((ch != null) && (ch.length > 0) && 
/* 180 */       (ch[0] >= 'a') && (ch[0] <= 'z')) {
/* 181 */       ch[0] = ((char)(ch[0] - ' '));
/*     */     }
/* 183 */     return new String(ch);
/*     */   }
/*     */ 
/*     */   private static String getPinyinZh_CN(Set<String> stringSet)
/*     */   {
/* 193 */     StringBuilder str = new StringBuilder();
/* 194 */     int i = 0;
/* 195 */     for (String s : stringSet) {
/* 196 */       if (i == stringSet.size() - 1)
/* 197 */         str.append(s);
/*     */       else {
/* 199 */         str.append(new StringBuilder().append(s).append(",").toString());
/*     */       }
/* 201 */       i++;
/*     */     }
/* 203 */     return str.toString();
/*     */   }
/*     */ 
/*     */   public static String getPinYinHeadChar(String chinese)
/*     */   {
/* 214 */     StringBuffer pinyin = new StringBuffer();
/* 215 */     if ((chinese != null) && (!chinese.trim().equalsIgnoreCase(""))) {
/* 216 */       for (int j = 0; j < chinese.length(); j++) {
/* 217 */         char word = chinese.charAt(j);
/* 218 */         String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
/*     */ 
/* 220 */         if (pinyinArray != null)
/* 221 */           pinyin.append(pinyinArray[0].charAt(0));
/*     */         else {
/* 223 */           pinyin.append(word);
/*     */         }
/*     */       }
/*     */     }
/* 227 */     return pinyin.toString();
/*     */   }
/*     */ 
/*     */   public static String strFilter(String str)
/*     */     throws PatternSyntaxException
/*     */   {
/* 239 */     String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";
/* 240 */     Pattern p = Pattern.compile(regEx);
/* 241 */     Matcher m = p.matcher(str);
/* 242 */     return m.replaceAll("").trim();
/*     */   }
/*     */ 
/*     */   public static String getPinYinHeadCharFilter(String chinese)
/*     */   {
/* 251 */     return strFilter(getPinYinHeadChar(chinese));
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 260 */     Logger logger = LoggerFactory.getLogger(PinyinUtil.class);
/* 261 */     String str = "〇的输￥￥#s，ldsa";
/* 262 */     logger.info(new StringBuilder().append("小写输出：").append(getPinyinToLowerCase(str)).toString());
/* 263 */     logger.info(new StringBuilder().append("大写输出：").append(getPinyinToUpperCase(str)).toString());
/* 264 */     logger.info(new StringBuilder().append("首字母大写输出：").append(getPinyinFirstToUpperCase(str)).toString());
/* 265 */     logger.info(new StringBuilder().append("返回中文的首字母输出：").append(getPinYinHeadChar(str)).toString());
/* 266 */     logger.info(new StringBuilder().append("返回中文的首字母并过滤特殊字符输出：").append(getPinYinHeadCharFilter(str)).toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.PinyinUtil
 * JD-Core Version:    0.6.2
 */