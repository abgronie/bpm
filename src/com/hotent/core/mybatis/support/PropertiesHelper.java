/*     */ package com.hotent.core.mybatis.support;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.InvalidPropertiesFormatException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class PropertiesHelper
/*     */ {
/*     */   public static final int SYSTEM_PROPERTIES_MODE_NEVER = 0;
/*     */   public static final int SYSTEM_PROPERTIES_MODE_FALLBACK = 1;
/*     */   public static final int SYSTEM_PROPERTIES_MODE_OVERRIDE = 2;
/*  46 */   private int systemPropertiesMode = 0;
/*     */   private Properties p;
/*     */ 
/*     */   public PropertiesHelper(Properties p)
/*     */   {
/*  50 */     setProperties(p);
/*     */   }
/*     */ 
/*     */   public PropertiesHelper(Properties p, int systemPropertiesMode) {
/*  54 */     setProperties(p);
/*  55 */     if ((systemPropertiesMode != 0) && (systemPropertiesMode != 1) && (systemPropertiesMode != 2)) {
/*  56 */       throw new IllegalArgumentException("error systemPropertiesMode mode:" + systemPropertiesMode);
/*     */     }
/*  58 */     this.systemPropertiesMode = systemPropertiesMode;
/*     */   }
/*     */ 
/*     */   public Properties getProperties() {
/*  62 */     return this.p;
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties props) {
/*  66 */     if (props == null) throw new IllegalArgumentException("properties must be not null");
/*  67 */     this.p = props;
/*     */   }
/*     */ 
/*     */   public String getRequiredString(String key) {
/*  71 */     String value = getProperty(key);
/*  72 */     if (isBlankString(value)) {
/*  73 */       throw new IllegalStateException("required property is blank by key=" + key);
/*     */     }
/*  75 */     return value;
/*     */   }
/*     */ 
/*     */   public String getNullIfBlank(String key) {
/*  79 */     String value = getProperty(key);
/*  80 */     if (isBlankString(value)) {
/*  81 */       return null;
/*     */     }
/*  83 */     return value;
/*     */   }
/*     */ 
/*     */   public String getNullIfEmpty(String key) {
/*  87 */     String value = getProperty(key);
/*  88 */     if ((value == null) || ("".equals(value))) {
/*  89 */       return null;
/*     */     }
/*  91 */     return value;
/*     */   }
/*     */ 
/*     */   public String getAndTryFromSystem(String key)
/*     */   {
/*  99 */     String value = getProperty(key);
/* 100 */     if (isBlankString(value)) {
/* 101 */       value = getSystemProperty(key);
/*     */     }
/* 103 */     return value;
/*     */   }
/*     */ 
/*     */   private String getSystemProperty(String key)
/*     */   {
/* 108 */     String value = System.getProperty(key);
/* 109 */     if (isBlankString(value)) {
/* 110 */       value = System.getenv(key);
/*     */     }
/* 112 */     return value;
/*     */   }
/*     */ 
/*     */   public Integer getInteger(String key) {
/* 116 */     String v = getProperty(key);
/* 117 */     if (v == null) {
/* 118 */       return null;
/*     */     }
/* 120 */     return Integer.valueOf(Integer.parseInt(v));
/*     */   }
/*     */ 
/*     */   public int getInt(String key, int defaultValue) {
/* 124 */     if (getProperty(key) == null) {
/* 125 */       return defaultValue;
/*     */     }
/* 127 */     return Integer.parseInt(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public int getRequiredInt(String key) {
/* 131 */     return Integer.parseInt(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public Long getLong(String key) {
/* 135 */     if (getProperty(key) == null) {
/* 136 */       return null;
/*     */     }
/* 138 */     return Long.valueOf(Long.parseLong(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public long getLong(String key, long defaultValue) {
/* 142 */     if (getProperty(key) == null) {
/* 143 */       return defaultValue;
/*     */     }
/* 145 */     return Long.parseLong(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public Long getRequiredLong(String key) {
/* 149 */     return Long.valueOf(Long.parseLong(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public Boolean getBoolean(String key) {
/* 153 */     if (getProperty(key) == null) {
/* 154 */       return null;
/*     */     }
/* 156 */     return Boolean.valueOf(Boolean.parseBoolean(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public boolean getBoolean(String key, boolean defaultValue) {
/* 160 */     if (getProperty(key) == null) {
/* 161 */       return defaultValue;
/*     */     }
/* 163 */     return Boolean.parseBoolean(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public boolean getRequiredBoolean(String key) {
/* 167 */     return Boolean.parseBoolean(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public Float getFloat(String key) {
/* 171 */     if (getProperty(key) == null) {
/* 172 */       return null;
/*     */     }
/* 174 */     return Float.valueOf(Float.parseFloat(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public float getFloat(String key, float defaultValue) {
/* 178 */     if (getProperty(key) == null) {
/* 179 */       return defaultValue;
/*     */     }
/* 181 */     return Float.parseFloat(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public Float getRequiredFloat(String key) {
/* 185 */     return Float.valueOf(Float.parseFloat(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public Double getDouble(String key) {
/* 189 */     if (getProperty(key) == null) {
/* 190 */       return null;
/*     */     }
/* 192 */     return Double.valueOf(Double.parseDouble(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public double getDouble(String key, double defaultValue) {
/* 196 */     if (getProperty(key) == null) {
/* 197 */       return defaultValue;
/*     */     }
/* 199 */     return Double.parseDouble(getRequiredString(key));
/*     */   }
/*     */ 
/*     */   public Double getRequiredDouble(String key) {
/* 203 */     return Double.valueOf(Double.parseDouble(getRequiredString(key)));
/*     */   }
/*     */ 
/*     */   public Object setProperty(String key, int value)
/*     */   {
/* 209 */     return setProperty(key, String.valueOf(value));
/*     */   }
/*     */ 
/*     */   public Object setProperty(String key, long value) {
/* 213 */     return setProperty(key, String.valueOf(value));
/*     */   }
/*     */ 
/*     */   public Object setProperty(String key, float value) {
/* 217 */     return setProperty(key, String.valueOf(value));
/*     */   }
/*     */ 
/*     */   public Object setProperty(String key, double value) {
/* 221 */     return setProperty(key, String.valueOf(value));
/*     */   }
/*     */ 
/*     */   public Object setProperty(String key, boolean value) {
/* 225 */     return setProperty(key, String.valueOf(value));
/*     */   }
/*     */ 
/*     */   public String[] getStringArray(String key) {
/* 229 */     String v = getProperty(key);
/* 230 */     if (v == null) {
/* 231 */       return new String[0];
/*     */     }
/* 233 */     return tokenizeToStringArray(v, ", \t\n\r\f");
/*     */   }
/*     */ 
/*     */   public int[] getIntArray(String key)
/*     */   {
/* 238 */     return toIntArray(getStringArray(key));
/*     */   }
/*     */ 
/*     */   public Properties getStartsWithProperties(String prefix) {
/* 242 */     if (prefix == null) throw new IllegalArgumentException("'prefix' must be not null");
/*     */ 
/* 244 */     Properties props = getProperties();
/* 245 */     Properties result = new Properties();
/* 246 */     for (Map.Entry entry : props.entrySet()) {
/* 247 */       String key = (String)entry.getKey();
/* 248 */       if ((key != null) && (key.startsWith(prefix))) {
/* 249 */         result.put(key.substring(prefix.length()), entry.getValue());
/*     */       }
/*     */     }
/* 252 */     return result;
/*     */   }
/*     */ 
/*     */   public String getProperty(String key, String defaultValue)
/*     */   {
/* 258 */     String value = getProperty(key);
/* 259 */     if (isBlankString(value)) {
/* 260 */       return defaultValue;
/*     */     }
/* 262 */     return value;
/*     */   }
/*     */ 
/*     */   public String getProperty(String key) {
/* 266 */     String propVal = null;
/* 267 */     if (this.systemPropertiesMode == 2) {
/* 268 */       propVal = getSystemProperty(key);
/*     */     }
/* 270 */     if (propVal == null) {
/* 271 */       propVal = this.p.getProperty(key);
/*     */     }
/* 273 */     if ((propVal == null) && (this.systemPropertiesMode == 1)) {
/* 274 */       propVal = getSystemProperty(key);
/*     */     }
/* 276 */     return propVal;
/*     */   }
/*     */ 
/*     */   public Object setProperty(String key, String value) {
/* 280 */     return this.p.setProperty(key, value);
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 284 */     this.p.clear();
/*     */   }
/*     */ 
/*     */   public Set<Map.Entry<Object, Object>> entrySet() {
/* 288 */     return this.p.entrySet();
/*     */   }
/*     */ 
/*     */   public Enumeration<?> propertyNames() {
/* 292 */     return this.p.propertyNames();
/*     */   }
/*     */ 
/*     */   public boolean contains(Object value) {
/* 296 */     return this.p.contains(value);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object key) {
/* 300 */     return this.p.containsKey(key);
/*     */   }
/*     */ 
/*     */   public boolean containsValue(Object value) {
/* 304 */     return this.p.containsValue(value);
/*     */   }
/*     */ 
/*     */   public Enumeration<Object> elements() {
/* 308 */     return this.p.elements();
/*     */   }
/*     */ 
/*     */   public Object get(Object key) {
/* 312 */     return this.p.get(key);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 316 */     return this.p.isEmpty();
/*     */   }
/*     */ 
/*     */   public Enumeration<Object> keys() {
/* 320 */     return this.p.keys();
/*     */   }
/*     */ 
/*     */   public Set<Object> keySet() {
/* 324 */     return this.p.keySet();
/*     */   }
/*     */ 
/*     */   public void list(PrintStream out) {
/* 328 */     this.p.list(out);
/*     */   }
/*     */ 
/*     */   public void list(PrintWriter out) {
/* 332 */     this.p.list(out);
/*     */   }
/*     */ 
/*     */   public void load(InputStream inStream) throws IOException {
/* 336 */     this.p.load(inStream);
/*     */   }
/*     */ 
/*     */   public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException
/*     */   {
/* 341 */     this.p.loadFromXML(in);
/*     */   }
/*     */ 
/*     */   public Object put(Object key, Object value) {
/* 345 */     return this.p.put(key, value);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Object, ? extends Object> t) {
/* 349 */     this.p.putAll(t);
/*     */   }
/*     */ 
/*     */   public Object remove(Object key) {
/* 353 */     return this.p.remove(key);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public void save(OutputStream out, String comments) {
/* 358 */     this.p.save(out, comments);
/*     */   }
/*     */ 
/*     */   public int size() {
/* 362 */     return this.p.size();
/*     */   }
/*     */ 
/*     */   public void store(OutputStream out, String comments) throws IOException {
/* 366 */     this.p.store(out, comments);
/*     */   }
/*     */ 
/*     */   public void storeToXML(OutputStream os, String comment, String encoding) throws IOException
/*     */   {
/* 371 */     this.p.storeToXML(os, comment, encoding);
/*     */   }
/*     */ 
/*     */   public void storeToXML(OutputStream os, String comment) throws IOException {
/* 375 */     this.p.storeToXML(os, comment);
/*     */   }
/*     */ 
/*     */   public Collection<Object> values() {
/* 379 */     return this.p.values();
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 383 */     return this.p.toString();
/*     */   }
/*     */ 
/*     */   private static boolean isBlankString(String value) {
/* 387 */     return (value == null) || ("".equals(value.trim()));
/*     */   }
/*     */ 
/*     */   private static String[] tokenizeToStringArray(String str, String seperators) {
/* 391 */     StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
/* 392 */     List result = new ArrayList();
/*     */ 
/* 394 */     while (tokenlizer.hasMoreElements()) {
/* 395 */       Object s = tokenlizer.nextElement();
/* 396 */       result.add(s);
/*     */     }
/* 398 */     return (String[])result.toArray(new String[result.size()]);
/*     */   }
/*     */ 
/*     */   private static int[] toIntArray(String[] array) {
/* 402 */     int[] result = new int[array.length];
/* 403 */     for (int i = 0; i < array.length; i++) {
/* 404 */       result[i] = Integer.parseInt(array[i]);
/*     */     }
/* 406 */     return result;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 410 */     Properties p = new Properties();
/*     */ 
/* 418 */     p.put("Dialect.oracle", "com.hotent.base.db.mybatis.dialect.OracleDialect");
/* 419 */     p.put("Dialect.mssql", "com.hotent.base.db.mybatis.dialect.SQLServer2005Dialect");
/* 420 */     p.put("Dialect.mysql", "com.hotent.base.db.mybatis.dialect.MySQLDialect");
/* 421 */     p.put("Dialect.db2", "com.hotent.base.db.mybatis.dialect.DB2Dialect");
/* 422 */     p.put("hello", "word");
/*     */ 
/* 424 */     PropertiesHelper help = new PropertiesHelper(p);
/* 425 */     Properties p1 = help.getStartsWithProperties("Dialect.");
/* 426 */     System.out.println(p1.getProperty("oracle"));
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.support.PropertiesHelper
 * JD-Core Version:    0.6.2
 */