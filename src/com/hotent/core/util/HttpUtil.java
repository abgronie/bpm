/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.ParseException;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ 
/*     */ public class HttpUtil
/*     */ {
/*     */   public static String sendData(String url, String data, String charset)
/*     */   {
/*  53 */     BufferedReader bufferedReader = null;
/*     */     try {
/*  55 */       URL uRL = new URL(url);
/*  56 */       URLConnection conn = uRL.openConnection();
/*  57 */       conn.setDoOutput(true);
/*  58 */       if (StringUtil.isNotEmpty(data)) {
/*  59 */         OutputStream stream = conn.getOutputStream();
/*  60 */         stream.write(data.getBytes(charset));
/*  61 */         stream.flush();
/*  62 */         stream.close();
/*     */       }
/*     */ 
/*  67 */       bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*     */ 
/*  69 */       StringBuffer response = new StringBuffer();
/*     */       String line;
/*  71 */       while ((line = bufferedReader.readLine()) != null) {
/*  72 */         response.append(line);
/*     */       }
/*     */ 
/*  75 */       bufferedReader.close();
/*     */ 
/*  77 */       return response.toString();
/*     */     }
/*     */     catch (MalformedURLException e) {
/*  80 */       e.printStackTrace();
/*  81 */       return "";
/*     */     } catch (IOException e) {
/*  83 */       e.printStackTrace();
/*  84 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String getContentByUrl(String url, String charset)
/*     */     throws ParseException, IOException
/*     */   {
/*  99 */     HttpClient httpclient = new DefaultHttpClient();
/*     */ 
/* 101 */     HttpGet httpget = new HttpGet(url);
/* 102 */     HttpResponse response = httpclient.execute(httpget);
/*     */ 
/* 105 */     if (StringUtil.isEmpty(charset)) {
/* 106 */       String defaultCharset = "iso-8859-1";
/* 107 */       Header contentTypeHeader = response.getFirstHeader("Content-Type");
/* 108 */       String contentType = contentTypeHeader.getValue().toLowerCase();
/* 109 */       if ((contentType.indexOf("gbk") > -1) || (contentType.indexOf("gb2312") > -1) || (contentType.indexOf("gb18030") > -1)) {
/* 110 */         defaultCharset = "gb18030";
/*     */       }
/* 112 */       else if (contentType.indexOf("utf-8") > -1) {
/* 113 */         defaultCharset = "utf-8";
/*     */       }
/* 115 */       else if (contentType.indexOf("big5") > -1) {
/* 116 */         defaultCharset = "big5";
/*     */       }
/* 118 */       charset = defaultCharset;
/*     */     }
/* 120 */     Header contentEncoding = response.getFirstHeader("Content-Encoding");
/* 121 */     StatusLine line = response.getStatusLine();
/* 122 */     if (line.getStatusCode() == 200)
/*     */     {
/* 124 */       HttpEntity entity = response.getEntity();
/*     */       InputStream is;
/* 126 */       if ((contentEncoding != null) && (contentEncoding.getValue().toLowerCase().equals("gzip")))
/*     */       {
/* 128 */         is = new GZIPInputStream(entity.getContent());
/*     */       }
/*     */       else
/*     */       {
/* 132 */         is = entity.getContent();
/*     */       }
/* 134 */       String str = FileUtil.inputStream2String(is, charset);
/* 135 */       is.close();
/* 136 */       return str;
/*     */     }
/*     */ 
/* 139 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getContentByUrl(String url)
/*     */     throws ParseException, IOException
/*     */   {
/* 145 */     return getContentByUrl(url, "");
/*     */   }
/*     */ 
/*     */   public static void saveRemoteFile(String remoteFile, String localFile)
/*     */     throws ParseException, IOException
/*     */   {
/* 157 */     HttpClient httpclient = new DefaultHttpClient();
/*     */ 
/* 159 */     HttpGet httpget = new HttpGet(remoteFile);
/* 160 */     HttpResponse response = httpclient.execute(httpget);
/* 161 */     Header contentEncoding = response.getFirstHeader("Content-Encoding");
/* 162 */     StatusLine line = response.getStatusLine();
/* 163 */     if (line.getStatusCode() == 200)
/*     */     {
/* 165 */       HttpEntity entity = response.getEntity();
/*     */       InputStream is;
/* 167 */       if ((contentEncoding != null) && (contentEncoding.getValue().toLowerCase().equals("gzip"))) {
/* 168 */         is = new GZIPInputStream(entity.getContent());
/*     */       }
/*     */       else {
/* 171 */         is = entity.getContent();
/*     */       }
/* 173 */       FileUtil.createFolder(localFile, true);
/* 174 */       FileOutputStream fs = new FileOutputStream(localFile);
/*     */ 
/* 176 */       int bytesum = 0;
/* 177 */       int byteread = 0;
/* 178 */       byte[] buffer = new byte[30000];
/* 179 */       while ((byteread = is.read(buffer)) != -1) {
/* 180 */         bytesum += byteread;
/* 181 */         fs.write(buffer, 0, byteread);
/*     */       }
/* 183 */       is.close();
/* 184 */       fs.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String sendHttpsRequest(String url, String params, String requestMethod)
/*     */   {
/* 208 */     String str = null;
/*     */     try {
/* 210 */       HttpsURLConnection conn = getHttpsConnection(url);
/* 211 */       conn.setRequestMethod(requestMethod);
/*     */ 		conn.setConnectTimeout(18000);
/*     */		conn.setReadTimeout(18000);
/* 213 */       conn.setDoInput(true);
/* 214 */       conn.setDoOutput(true);
/*     */ 
/* 216 */       if (StringUtil.isNotEmpty(params)) {
/* 217 */         OutputStream outputStream = conn.getOutputStream();
/* 218 */         outputStream.write(params.getBytes("utf-8"));
/* 219 */         outputStream.close();
/*     */       }
/* 221 */       str = getOutPut(conn);
/*     */     } catch (KeyManagementException e) {
/* 223 */       throw new RuntimeException(new StringBuilder().append("远程服务器请求失败！").append(e.getMessage()).toString(), e);
/*     */     } catch (NoSuchAlgorithmException e) {
/* 225 */       throw new RuntimeException(new StringBuilder().append("远程服务器请求失败！").append(e.getMessage()).toString(), e);
/*     */     } catch (NoSuchProviderException e) {
/* 227 */       throw new RuntimeException(new StringBuilder().append("远程服务器请求失败！").append(e.getMessage()).toString(), e);
/*     */     } catch (IOException e) {
/* 229 */       throw new RuntimeException(new StringBuilder().append("远程服务器请求失败！").append(e.getMessage()).toString(), e);
/*     */     }
/*     */ 
/* 232 */     return str;
/*     */   }
/*     */ 
/*     */   public static HttpsURLConnection getHttpsConnection(String accessUrl)
/*     */     throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException
/*     */   {
/* 247 */     URL url = new URL(accessUrl);
/* 248 */     HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
/*     */ 
/* 251 */     TrustManager[] tm = { new MyX509TrustManager() };
/*     */ 
/* 253 */     SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
/* 254 */     sslContext.init(null, tm, new SecureRandom());
/* 255 */     SSLSocketFactory ssf = sslContext.getSocketFactory();
/* 256 */     connection.setSSLSocketFactory(ssf);
/* 257 */     return connection;
/*     */   }
/*     */ 
/*     */   public static String getOutPut(HttpsURLConnection conn)
/*     */     throws IOException
/*     */   {
/* 268 */     InputStream inputStream = conn.getInputStream();
/* 269 */     InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
/*     */ 
/* 271 */     BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
/* 272 */     StringBuffer buffer = new StringBuffer();
/* 273 */     String str = null;
/* 274 */     while ((str = bufferedReader.readLine()) != null) {
/* 275 */       buffer.append(str);
/*     */     }
/* 277 */     bufferedReader.close();
/* 278 */     inputStreamReader.close();
/* 279 */     inputStream.close();
/* 280 */     conn.disconnect();
/* 281 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public static String uploadFile(String url, String filePath)
/*     */     throws IOException
/*     */   {
/* 293 */     String result = null;
/*     */ 
/* 295 */     File file = new File(filePath);
/* 296 */     if ((!file.exists()) || (!file.isFile())) {
/* 297 */       throw new IOException("文件不存在");
/*     */     }
/*     */ 
/* 303 */     URL urlObj = new URL(url);
/*     */ 
/* 305 */     HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
/*     */ 
/* 310 */     con.setRequestMethod("POST");
/* 311 */     con.setDoInput(true);
/* 312 */     con.setDoOutput(true);
/* 313 */     con.setUseCaches(false);
/*     */ 
/* 316 */     con.setRequestProperty("Connection", "Keep-Alive");
/* 317 */     con.setRequestProperty("Charset", "UTF-8");
/*     */ 
/* 320 */     String BOUNDARY = new StringBuilder().append("----------").append(System.currentTimeMillis()).toString();
/* 321 */     con.setRequestProperty("Content-Type", new StringBuilder().append("multipart/form-data; boundary=").append(BOUNDARY).toString());
/*     */ 
/* 327 */     StringBuilder sb = new StringBuilder();
/* 328 */     sb.append("--");
/* 329 */     sb.append(BOUNDARY);
/* 330 */     sb.append("\r\n");
/* 331 */     sb.append(new StringBuilder().append("Content-Disposition: form-data;name=\"file\";filename=\"").append(file.getName()).append("\"\r\n").toString());
/*     */ 
/* 333 */     sb.append("Content-Type:application/octet-stream\r\n\r\n");
/*     */ 
/* 335 */     byte[] head = sb.toString().getBytes("utf-8");
/*     */ 
/* 338 */     OutputStream out = new DataOutputStream(con.getOutputStream());
/*     */ 
/* 340 */     out.write(head);
/*     */ 
/* 344 */     DataInputStream in = new DataInputStream(new FileInputStream(file));
/* 345 */     int bytes = 0;
/* 346 */     byte[] bufferOut = new byte[1024];
/* 347 */     while ((bytes = in.read(bufferOut)) != -1) {
/* 348 */       out.write(bufferOut, 0, bytes);
/*     */     }
/* 350 */     in.close();
/*     */ 
/* 353 */     byte[] foot = new StringBuilder().append("\r\n--").append(BOUNDARY).append("--\r\n").toString().getBytes("utf-8");
/*     */ 
/* 355 */     out.write(foot);
/*     */ 
/* 357 */     out.flush();
/* 358 */     out.close();
/*     */ 
/* 360 */     StringBuffer buffer = new StringBuffer();
/* 361 */     BufferedReader reader = null;
/*     */     try
/*     */     {
/* 364 */       reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
/*     */ 
/* 366 */       String line = null;
/* 367 */       while ((line = reader.readLine()) != null)
/*     */       {
/* 369 */         buffer.append(line);
/*     */       }
/* 371 */       if (result == null)
/* 372 */         result = buffer.toString();
/*     */     }
/*     */     catch (IOException e) {
/* 375 */       System.out.println(new StringBuilder().append("发送POST请求出现异常！").append(e).toString());
/* 376 */       e.printStackTrace();
/* 377 */       throw new IOException("数据读取异常");
/*     */     } finally {
/* 379 */       if (reader != null) {
/* 380 */         reader.close();
/*     */       }
/*     */     }
/*     */ 
/* 384 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.HttpUtil
 * JD-Core Version:    0.6.2
 */