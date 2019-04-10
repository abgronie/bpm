/*      */ package com.hotent.core.util;
/*      */ 
/*      */ import com.hotent.core.encrypt.Base64;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileFilter;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutput;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.net.URLEncoder;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.List;
/*      */ import java.util.Properties;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ 
/*      */ public class FileUtil
/*      */ {
/*      */   public static void writeFile(String fileName, String content)
/*      */   {
/*   49 */     writeFile(fileName, content, "utf-8");
/*      */   }
/*      */ 
/*      */   public static void writeFile(String fileName, String content, String charset)
/*      */   {
/*      */     try
/*      */     {
/*   61 */       createFolder(fileName, true);
/*   62 */       Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), charset));
/*      */ 
/*   64 */       out.write(content);
/*   65 */       out.close();
/*      */     } catch (IOException e) {
/*   67 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void writeFile(String fileName, InputStream is)
/*      */     throws IOException
/*      */   {
/*   81 */     if (!isExistFile(fileName)) {
/*   82 */       boolean createFile = createFile(fileName);
/*   83 */       if (!createFile) {
/*   84 */         throw new IOException("创建文件失败");
/*      */       }
/*      */     }
/*   87 */     FileOutputStream fos = new FileOutputStream(fileName);
/*   88 */     byte[] bs = new byte[512];
/*   89 */     int n = 0;
/*   90 */     while ((n = is.read(bs)) != -1) {
/*   91 */       fos.write(bs, 0, n);
/*      */     }
/*   93 */     is.close();
/*   94 */     fos.close();
/*      */   }
/*      */ 
/*      */   public static boolean createFile(String fileName)
/*      */   {
/*  103 */     File file = new File(fileName);
/*  104 */     if (file.exists()) {
/*  105 */       return false;
/*      */     }
/*  107 */     if (fileName.endsWith(File.separator)) {
/*  108 */       return false;
/*      */     }
/*      */ 
/*  111 */     if (!file.getParentFile().exists())
/*      */     {
/*  113 */       if (!file.getParentFile().mkdirs()) {
/*  114 */         return false;
/*      */       }
/*      */     }
/*      */     try
/*      */     {
/*  119 */       if (file.createNewFile()) {
/*  120 */         return true;
/*      */       }
/*  122 */       return false;
/*      */     }
/*      */     catch (IOException e) {
/*  125 */       e.printStackTrace();
/*  126 */     }return false;
/*      */   }
/*      */ 
/*      */   public static String readFile(String fileName)
/*      */   {
/*      */     try
/*      */     {
/*  139 */       File file = new File(fileName);
/*  140 */       String charset = getCharset(file);
/*  141 */       StringBuffer sb = new StringBuffer();
/*  142 */       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
/*      */       String str;
/*  145 */       while ((str = in.readLine()) != null) {
/*  146 */         sb.append(str + "\r\n");
/*      */       }
/*  148 */       in.close();
/*  149 */       return sb.toString();
/*      */     } catch (IOException e) {
/*  151 */       e.printStackTrace();
/*  152 */     }return "";
/*      */   }
/*      */ 
/*      */   public static boolean isExistFile(String dir)
/*      */   {
/*  162 */     boolean isExist = false;
/*  163 */     File fileDir = new File(dir);
/*  164 */     if (fileDir.isDirectory()) {
/*  165 */       File[] files = fileDir.listFiles();
/*  166 */       if ((files != null) && (files.length != 0)) {
/*  167 */         isExist = true;
/*      */       }
/*      */     }
/*  170 */     return isExist;
/*      */   }
/*      */ 
/*      */   public static String getCharset(File file)
/*      */   {
/*  181 */     String charset = "GBK";
/*  182 */     byte[] first3Bytes = new byte[3];
/*      */     try {
/*  184 */       boolean checked = false;
/*  185 */       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
/*      */ 
/*  187 */       bis.mark(0);
/*  188 */       int read = bis.read(first3Bytes, 0, 3);
/*  189 */       if (read == -1)
/*  190 */         return charset;
/*  191 */       if ((first3Bytes[0] == -1) && (first3Bytes[1] == -2)) {
/*  192 */         charset = "UTF-16LE";
/*  193 */         checked = true;
/*  194 */       } else if ((first3Bytes[0] == -2) && (first3Bytes[1] == -1))
/*      */       {
/*  196 */         charset = "UTF-16BE";
/*  197 */         checked = true;
/*  198 */       } else if ((first3Bytes[0] == -17) && (first3Bytes[1] == -69) && (first3Bytes[2] == -65))
/*      */       {
/*  201 */         charset = "UTF-8";
/*  202 */         checked = true;
/*      */       }
/*  204 */       bis.reset();
/*      */ 
/*  206 */       if (!checked) {
/*  207 */         int loc = 0;
/*  208 */         while ((read = bis.read()) != -1) {
/*  209 */           loc++;
/*  210 */           if (read < 240)
/*      */           {
/*  213 */             if ((128 > read) || (read > 191))
/*      */             {
/*  215 */               if ((192 <= read) && (read <= 223)) {
/*  216 */                 read = bis.read();
/*  217 */                 if ((128 > read) || (read > 191))
/*      */                 {
/*      */                   break;
/*      */                 }
/*      */ 
/*      */               }
/*  224 */               else if ((224 <= read) && (read <= 239)) {
/*  225 */                 read = bis.read();
/*  226 */                 if ((128 <= read) && (read <= 191)) {
/*  227 */                   read = bis.read();
/*  228 */                   if ((128 <= read) && (read <= 191)) {
/*  229 */                     charset = "UTF-8";
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  239 */       bis.close();
/*      */     } catch (Exception e) {
/*  241 */       e.printStackTrace();
/*      */     }
/*  243 */     return charset;
/*      */   }
/*      */ 
/*      */   public static byte[] readByte(InputStream is)
/*      */   {
/*      */     try
/*      */     {
/*  254 */       byte[] r = new byte[is.available()];
/*  255 */       is.read(r);
/*  256 */       return r;
/*      */     } catch (Exception e) {
/*  258 */       e.printStackTrace();
/*      */     }
/*  260 */     return null;
/*      */   }
/*      */ 
/*      */   public static byte[] readByte(String fileName)
/*      */   {
/*      */     try
/*      */     {
/*  271 */       FileInputStream fis = new FileInputStream(fileName);
/*  272 */       byte[] r = new byte[fis.available()];
/*  273 */       fis.read(r);
/*  274 */       fis.close();
/*  275 */       return r;
/*      */     } catch (Exception e) {
/*  277 */       e.printStackTrace();
/*      */     }
/*  279 */     return null;
/*      */   }
/*      */ 
/*      */   public static boolean writeByte(String fileName, byte[] b)
/*      */   {
/*      */     try
/*      */     {
/*  290 */       BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName));
/*      */ 
/*  292 */       fos.write(b);
/*  293 */       fos.close();
/*  294 */       return true;
/*      */     } catch (Exception e) {
/*  296 */       e.printStackTrace();
/*      */     }
/*  298 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean deleteDir(File dir)
/*      */   {
/*  308 */     if (dir.isDirectory()) {
/*  309 */       String[] children = dir.list();
/*  310 */       for (int i = 0; i < children.length; i++) {
/*  311 */         boolean success = deleteDir(new File(dir, children[i]));
/*  312 */         if (!success) {
/*  313 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*  317 */     return dir.delete();
/*      */   }
/*      */ 
/*      */   public static void serializeToFile(Object obj, String fileName)
/*      */   {
/*      */     try
/*      */     {
/*  328 */       ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
/*      */ 
/*  330 */       out.writeObject(obj);
/*  331 */       out.close();
/*      */     } catch (IOException e) {
/*  333 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static Object deserializeFromFile(String fileName)
/*      */   {
/*      */     try
/*      */     {
/*  345 */       File file = new File(fileName);
/*  346 */       ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
/*      */ 
/*  348 */       Object obj = in.readObject();
/*  349 */       in.close();
/*  350 */       return obj;
/*      */     } catch (Exception e) {
/*  352 */       e.printStackTrace();
/*  353 */     }return null;
/*      */   }
/*      */ 
/*      */   public static String inputStream2String(InputStream input, String charset)
/*      */     throws IOException
/*      */   {
/*  367 */     BufferedReader in = new BufferedReader(new InputStreamReader(input, charset));
/*      */ 
/*  369 */     StringBuffer buffer = new StringBuffer();
/*  370 */     String line = "";
/*  371 */     while ((line = in.readLine()) != null) {
/*  372 */       buffer.append(line + "\n");
/*      */     }
/*  374 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */   public static String inputStream2String(InputStream input)
/*      */     throws IOException
/*      */   {
/*  386 */     return inputStream2String(input, "utf-8");
/*      */   }
/*      */ 
/*      */   public static File[] getFiles(String path)
/*      */   {
/*  397 */     File file = new File(path);
/*  398 */     return file.listFiles();
/*      */   }
/*      */ 
/*      */   public static void createFolderFile(String path)
/*      */   {
/*  407 */     createFolder(path, true);
/*      */   }
/*      */ 
/*      */   public static void createFolder(String path, boolean isFile)
/*      */   {
/*  417 */     if (isFile) {
/*  418 */       path = path.substring(0, path.lastIndexOf(File.separator));
/*      */     }
/*  420 */     File file = new File(path);
/*  421 */     if (!file.exists())
/*  422 */       file.mkdirs();
/*      */   }
/*      */ 
/*      */   public static void createFolder(String dirstr, String name)
/*      */   {
/*  435 */     dirstr = StringUtil.trimSufffix(dirstr, File.separator) + File.separator + name;
/*      */ 
/*  437 */     File dir = new File(dirstr);
/*  438 */     dir.mkdir();
/*      */   }
/*      */ 
/*      */   public static void renameFolder(String path, String newName)
/*      */   {
/*  450 */     File file = new File(path);
/*  451 */     if (file.exists())
/*  452 */       file.renameTo(new File(newName));
/*      */   }
/*      */ 
/*      */   public static ArrayList<File> getDiretoryOnly(File dir)
/*      */   {
/*  463 */     ArrayList dirs = new ArrayList();
/*  464 */     if ((dir != null) && (dir.exists()) && (dir.isDirectory())) {
/*  465 */       File[] files = dir.listFiles(new FileFilter()
/*      */       {
/*      */         public boolean accept(File file)
/*      */         {
/*  469 */           if (file.isDirectory())
/*  470 */             return true;
/*  471 */           return false;
/*      */         }
/*      */       });
/*  474 */       for (int i = 0; i < files.length; i++) {
/*  475 */         dirs.add(files[i]);
/*      */       }
/*      */     }
/*  478 */     return dirs;
/*      */   }
/*      */ 
/*      */   public ArrayList<File> getFileOnly(File dir)
/*      */   {
/*  489 */     ArrayList dirs = new ArrayList();
/*  490 */     File[] files = dir.listFiles(new FileFilter()
/*      */     {
/*      */       public boolean accept(File file) {
/*  493 */         if (file.isFile())
/*  494 */           return true;
/*  495 */         return false;
/*      */       }
/*      */     });
/*  498 */     for (int i = 0; i < files.length; i++) {
/*  499 */       dirs.add(files[i]);
/*      */     }
/*  501 */     return dirs;
/*      */   }
/*      */ 
/*      */   public static boolean deleteFile(String path)
/*      */   {
/*  511 */     File file = new File(path);
/*  512 */     return file.delete();
/*      */   }
/*      */ 
/*      */   public static boolean copyFile(String from, String to)
/*      */   {
/*  523 */     File fromFile = new File(from);
/*  524 */     File toFile = new File(to);
/*  525 */     FileInputStream fis = null;
/*  526 */     FileOutputStream fos = null;
/*      */     try
/*      */     {
/*  529 */       fis = new FileInputStream(fromFile);
/*  530 */       fos = new FileOutputStream(toFile);
/*      */ 
/*  532 */       byte[] buf = new byte[4096];
/*      */       int bytesRead;
/*  533 */       while ((bytesRead = fis.read(buf)) != -1) {
/*  534 */         fos.write(buf, 0, bytesRead);
/*      */       }
/*      */ 
/*  537 */       fos.flush();
/*  538 */       fos.close();
/*  539 */       fis.close();
/*      */     } catch (IOException e) {
/*  541 */       e.printStackTrace();
/*  542 */       return false;
/*      */     }
/*  544 */     return true;
/*      */   }
/*      */ 
/*      */   public static void backupFile(String filePath)
/*      */   {
/*  554 */     String backupName = filePath + ".bak";
/*  555 */     File file = new File(backupName);
/*  556 */     if (file.exists()) {
/*  557 */       file.delete();
/*      */     }
/*  559 */     copyFile(filePath, backupName);
/*      */   }
/*      */ 
/*      */   public static String getFileExt(File file)
/*      */   {
/*  569 */     if (file.isFile()) {
/*  570 */       return getFileExt(file.getName());
/*      */     }
/*  572 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getFileExt(String fileName)
/*      */   {
/*  581 */     int pos = fileName.lastIndexOf(".");
/*  582 */     if (pos > -1) {
/*  583 */       return fileName.substring(pos + 1).toLowerCase();
/*      */     }
/*  585 */     return "";
/*      */   }
/*      */ 
/*      */   public static void copyDir(String fromDir, String toDir)
/*      */     throws IOException
/*      */   {
/*  599 */     new File(toDir).mkdirs();
/*  600 */     File[] file = new File(fromDir).listFiles();
/*  601 */     for (int i = 0; i < file.length; i++) {
/*  602 */       if (file[i].isFile()) {
/*  603 */         String fromFile = file[i].getAbsolutePath();
/*  604 */         String toFile = toDir + "/" + file[i].getName();
/*      */ 
/*  606 */         copyFile(fromFile, toFile);
/*      */       }
/*  608 */       if (file[i].isDirectory())
/*  609 */         copyDirectiory(fromDir + "/" + file[i].getName(), toDir + "/" + file[i].getName());
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void copyDirectiory(String fromDir, String toDir)
/*      */     throws IOException
/*      */   {
/*  625 */     new File(toDir).mkdirs();
/*  626 */     File[] file = new File(fromDir).listFiles();
/*  627 */     for (int i = 0; i < file.length; i++) {
/*  628 */       if (file[i].isFile()) {
/*  629 */         String fromName = file[i].getAbsolutePath();
/*  630 */         String toFile = toDir + "/" + file[i].getName();
/*  631 */         copyFile(fromName, toFile);
/*      */       }
/*  633 */       if (file[i].isDirectory())
/*  634 */         copyDirectiory(fromDir + "/" + file[i].getName(), toDir + "/" + file[i].getName());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static String getFileSize(File file)
/*      */     throws IOException
/*      */   {
/*  646 */     if (file.isFile()) {
/*  647 */       FileInputStream fis = new FileInputStream(file);
/*  648 */       int size = fis.available();
/*  649 */       fis.close();
/*  650 */       return getSize(size);
/*      */     }
/*  652 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getSize(double size)
/*      */   {
/*  661 */     DecimalFormat df = new DecimalFormat("0.00");
/*  662 */     if (size > 1048576.0D) {
/*  663 */       double ss = size / 1048576.0D;
/*  664 */       return df.format(ss) + " M";
/*  665 */     }if (size > 1024.0D) {
/*  666 */       double ss = size / 1024.0D;
/*  667 */       return df.format(ss) + " KB";
/*      */     }
/*  669 */     return size + " bytes";
/*      */   }
/*      */ 
/*      */   public static void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fullPath, String fileName)
/*      */     throws IOException
/*      */   {
/*  681 */     OutputStream outp = response.getOutputStream();
/*  682 */     File file = new File(fullPath);
/*  683 */     if (file.exists()) {
/*  684 */       response.setContentType("APPLICATION/OCTET-STREAM");
/*  685 */       String filedisplay = fileName;
/*  686 */       String agent = request.getHeader("USER-AGENT");
/*      */ 
/*  688 */       if (agent.toUpperCase().indexOf("MSIE") > 0)
/*  689 */         filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
/*      */       else {
/*  691 */         filedisplay = new String(filedisplay.getBytes("UTF-8"), "ISO8859-1");
/*      */       }
/*  693 */       response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
/*      */ 
/*  695 */       FileInputStream in = null;
/*      */       try {
/*  697 */         outp = response.getOutputStream();
/*  698 */         in = new FileInputStream(fullPath);
/*  699 */         byte[] b = new byte[1024];
/*  700 */         int i = 0;
/*  701 */         while ((i = in.read(b)) > 0) {
/*  702 */           outp.write(b, 0, i);
/*      */         }
/*  704 */         outp.flush();
/*      */       } catch (Exception e) {
/*  706 */         e.printStackTrace();
/*      */       } finally {
/*  708 */         if (in != null) {
/*  709 */           in.close();
/*  710 */           in = null;
/*      */         }
/*  712 */         if (outp != null) {
/*  713 */           outp.close();
/*  714 */           outp = null;
/*  715 */           response.flushBuffer();
/*      */         }
/*      */       }
/*      */     } else {
/*  719 */       outp.write("文件不存在!".getBytes("utf-8"));
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void downLoad(HttpServletRequest request, HttpServletResponse response, String content, String fileName)
/*      */     throws IOException
/*      */   {
/*  732 */     response.setContentType("APPLICATION/OCTET-STREAM");
/*  733 */     String filedisplay = fileName;
/*  734 */     String agent = request.getHeader("USER-AGENT");
/*      */ 
/*  736 */     if (agent.toUpperCase().indexOf("MSIE") > 0)
/*  737 */       filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
/*      */     else {
/*  739 */       filedisplay = new String(filedisplay.getBytes("UTF-8"), "ISO8859-1");
/*      */     }
/*  741 */     response.setHeader("Content-Disposition", "attachment;filename=" + filedisplay);
/*  742 */     PrintWriter out = response.getWriter();
/*  743 */     out.write(content);
/*  744 */     out.flush();
/*  745 */     out.close();
/*      */   }
/*      */ 
/*      */   public static String getParentDir(String baseDir, String currentFile)
/*      */   {
/*  760 */     File f = new File(currentFile);
/*  761 */     String parentPath = f.getParent();
/*  762 */     String path = parentPath.replace(baseDir, "");
/*  763 */     return path.replace(File.separator, "/");
/*      */   }
/*      */ 
/*      */   public static String getClassesPath()
/*      */   {
/*  772 */     String path = StringUtil.trimSufffix(AppUtil.getRealPath("/"), File.separator) + File.separator + "WEB-INF" + File.separator + "classes" + File.separator;
/*      */ 
/*  774 */     return path;
/*      */   }
/*      */ 
/*      */   public static String getRootPath()
/*      */   {
/*  782 */     String rootPath = StringUtil.trimSufffix(AppUtil.getRealPath("/"), File.separator) + File.separator;
/*  783 */     return rootPath;
/*      */   }
/*      */ 
/*      */   public static String readFromProperties(String fileName, String key)
/*      */   {
/*  793 */     String value = "";
/*  794 */     InputStream stream = null;
/*      */     try {
/*  796 */       stream = new BufferedInputStream(new FileInputStream(fileName));
/*  797 */       Properties prop = new Properties();
/*  798 */       prop.load(stream);
/*  799 */       value = prop.getProperty(key);
/*      */     }
/*      */     catch (Exception e) {
/*  802 */       e.printStackTrace();
/*      */     }
/*      */     finally {
/*  805 */       if (stream != null) {
/*      */         try {
/*  807 */           stream.close();
/*      */         } catch (IOException e) {
/*  809 */           e.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  813 */     return value;
/*      */   }
/*      */ 
/*      */   public static boolean saveProperties(String fileName, String key, String value)
/*      */   {
/*  824 */     StringBuffer sb = new StringBuffer();
/*  825 */     boolean isFound = false;
/*  826 */     BufferedReader in = null;
/*      */     try {
/*  828 */       in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
/*      */       String str;
/*  830 */       while ((str = in.readLine()) != null) {
/*  831 */         if (str.startsWith(key)) {
/*  832 */           sb.append(key + "=" + value + "\r\n");
/*  833 */           isFound = true;
/*      */         }
/*      */         else {
/*  836 */           sb.append(str + "\r\n");
/*      */         }
/*      */       }
/*      */ 
/*  840 */       if (!isFound) {
/*  841 */         sb.append(key + "=" + value + "\r\n");
/*      */       }
/*  843 */       writeFile(fileName, sb.toString(), "utf-8");
/*  844 */       return true;
/*      */     }
/*      */     catch (Exception ex)
/*      */     {
/*      */       boolean bool1;
/*  847 */       ex.printStackTrace();
/*  848 */       return false;
/*      */     }
/*      */     finally {
/*  851 */       if (in != null)
/*      */         try {
/*  853 */           in.close();
/*      */         } catch (IOException e) {
/*  855 */           e.printStackTrace();
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean delProperties(String fileName, String key)
/*      */   {
/*  868 */     StringBuffer sb = new StringBuffer();
/*      */ 
/*  870 */     BufferedReader in = null;
/*      */     try {
/*  872 */       in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
/*      */       String str;
/*  874 */       while ((str = in.readLine()) != null) {
/*  875 */         if (!str.startsWith(key)) {
/*  876 */           sb.append(str + "\r\n");
/*      */         }
/*      */       }
/*  879 */       writeFile(fileName, sb.toString(), "utf-8");
/*  880 */       return true;
/*      */     }
/*      */     catch (Exception ex)
/*      */     {
/*      */       boolean bool;
/*  883 */       ex.printStackTrace();
/*  884 */       return false;
/*      */     }
/*      */     finally {
/*  887 */       if (in != null)
/*      */         try {
/*  889 */           in.close();
/*      */         } catch (IOException e) {
/*  891 */           e.printStackTrace();
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static List<Class<?>> getAllClassesByInterface(Class<?> interfaceClass, boolean samePackage)
/*      */     throws IOException, ClassNotFoundException, IllegalStateException
/*      */   {
/*  908 */     if (!interfaceClass.isInterface()) {
/*  909 */       throw new IllegalStateException("Class not a interface.");
/*      */     }
/*      */ 
/*  912 */     ClassLoader $loader = interfaceClass.getClassLoader();
/*      */ 
/*  914 */     String packageName = samePackage ? interfaceClass.getPackage().getName() : "/";
/*  915 */     return findClasses(interfaceClass, $loader, packageName);
/*      */   }
/*      */ 
/*      */   private static List<Class<?>> findClasses(Class<?> interfaceClass, ClassLoader loader, String packageName)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  930 */     List allClasses = new ArrayList();
/*      */ 
/*  932 */     String packagePath = packageName.replace(".", "/");
/*  933 */     if (!packagePath.equals("/")) {
/*  934 */       Enumeration resources = loader.getResources(packagePath);
/*  935 */       while (resources.hasMoreElements()) {
/*  936 */         URL $url = (URL)resources.nextElement();
/*  937 */         allClasses.addAll(findResources(interfaceClass, new File($url.getFile()), packageName));
/*      */       }
/*      */     } else {
/*  940 */       String path = loader.getResource("").getPath();
/*  941 */       allClasses.addAll(findResources(interfaceClass, new File(path), packageName));
/*      */     }
/*  943 */     return allClasses;
/*      */   }
/*      */ 
/*      */   private static List<Class<?>> findResources(Class<?> interfaceClass, File directory, String packageName)
/*      */     throws ClassNotFoundException
/*      */   {
/*  958 */     List $results = new ArrayList();
/*  959 */     if (!directory.exists()) return Collections.EMPTY_LIST;
/*  960 */     File[] files = directory.listFiles();
/*  961 */     for (File file : files) {
/*  962 */       if (file.isDirectory())
/*      */       {
/*  964 */         if (!file.getName().contains(".")) {
/*  965 */           if (!packageName.equals("/"))
/*  966 */             $results.addAll(findResources(interfaceClass, file, packageName + "." + file.getName()));
/*      */           else
/*  968 */             $results.addAll(findResources(interfaceClass, file, file.getName()));
/*      */         }
/*      */       }
/*  971 */       else if (file.getName().endsWith(".class")) {
/*  972 */         Class clazz = null;
/*  973 */         if (!packageName.equals("/"))
/*  974 */           clazz = Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6));
/*      */         else {
/*  976 */           clazz = Class.forName(file.getName().substring(0, file.getName().length() - 6));
/*      */         }
/*  978 */         if ((interfaceClass.isAssignableFrom(clazz)) && (!interfaceClass.equals(clazz))) {
/*  979 */           $results.add(clazz);
/*      */         }
/*      */       }
/*      */     }
/*  983 */     return $results;
/*      */   }
/*      */ 
/*      */   public static Object cloneObject(Object obj)
/*      */     throws Exception
/*      */   {
/*  993 */     ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
/*  994 */     ObjectOutputStream out = new ObjectOutputStream(byteOut);
/*  995 */     out.writeObject(obj);
/*      */ 
/*  997 */     ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
/*  998 */     ObjectInputStream in = new ObjectInputStream(byteIn);
/*      */ 
/* 1000 */     return in.readObject();
/*      */   }
/*      */ 
/*      */   public static void downLoadFileByByte(HttpServletRequest request, HttpServletResponse response, byte[] b, String fileName)
/*      */     throws IOException
/*      */   {
/* 1011 */     OutputStream outp = response.getOutputStream();
/* 1012 */     if (b.length > 0) {
/* 1013 */       response.setContentType("APPLICATION/OCTET-STREAM");
/* 1014 */       String filedisplay = fileName;
/* 1015 */       String agent = request.getHeader("USER-AGENT");
/*      */ 
/* 1017 */       if ((agent != null) && (agent.indexOf("MSIE") == -1)) {
/* 1018 */         String enableFileName = "=?UTF-8?B?" + new String(Base64.getBase64(filedisplay)) + "?=";
/* 1019 */         response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
/*      */       }
/*      */       else {
/* 1022 */         filedisplay = URLEncoder.encode(filedisplay, "utf-8");
/* 1023 */         response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
/*      */       }
/* 1025 */       outp.write(b);
/*      */     } else {
/* 1027 */       outp.write("文件不存在!".getBytes("utf-8"));
/*      */     }
/* 1029 */     if (outp != null) {
/* 1030 */       outp.close();
/* 1031 */       outp = null;
/* 1032 */       response.flushBuffer();
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.FileUtil
 * JD-Core Version:    0.6.2
 */