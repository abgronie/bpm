/*     */ package com.hotent.core.util;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.JPEGCodec;
/*     */ import com.sun.image.codec.jpeg.JPEGEncodeParam;
/*     */ import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*     */ import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import javax.swing.ImageIcon;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ImageUtil
/*     */ {
/*  54 */   public static Log log = LogFactory.getLog(ImageUtil.class);
/*     */ 
/*     */   public static Image loadImage(byte[] imagedata)
/*     */   {
/*  63 */     Image image = Toolkit.getDefaultToolkit().createImage(imagedata);
/*  64 */     return image;
/*     */   }
/*     */ 
/*     */   public static Image loadImage(String filename)
/*     */   {
/*  74 */     return Toolkit.getDefaultToolkit().getImage(filename);
/*     */   }
/*     */ 
/*     */   public static BufferedImage loadImage(File file) {
/*  78 */     BufferedImage bufferedImage = null;
/*     */     try
/*     */     {
/*  81 */       bufferedImage = ImageIO.read(file);
/*     */     } catch (IOException e) {
/*  83 */       e.printStackTrace();
/*  84 */       throw new RuntimeException(e);
/*     */     }
/*  86 */     return bufferedImage;
/*     */   }
/*     */ 
/*     */   public static ImageReader getImageReader(InputStream is, String formatName) throws IOException {
/*  90 */     Iterator readers = ImageIO.getImageReadersByFormatName(formatName);
/*     */ 
/*  92 */     ImageReader reader = (ImageReader)readers.next();
/*  93 */     ImageInputStream iis = null;
/*     */     try {
/*  95 */       iis = ImageIO.createImageInputStream(is);
/*     */     } catch (IOException e) {
/*  97 */       e.printStackTrace();
/*  98 */       throw new RuntimeException(e);
/*     */     }
/* 100 */     reader.setInput(iis, true);
/* 101 */     return reader;
/*     */   }
/*     */ 
/*     */   public static ImageReader getImageReader(File file) {
/* 105 */     String formatName = getFileSuffix(file);
/* 106 */     Iterator readers = ImageIO.getImageReadersByFormatName(formatName);
/*     */ 
/* 108 */     ImageReader reader = (ImageReader)readers.next();
/* 109 */     ImageInputStream iis = null;
/*     */     try {
/* 111 */       iis = ImageIO.createImageInputStream(file);
/*     */     } catch (IOException e) {
/* 113 */       e.printStackTrace();
/* 114 */       throw new RuntimeException(e);
/*     */     }
/* 116 */     reader.setInput(iis, true);
/* 117 */     return reader;
/*     */   }
/*     */ 
/*     */   private static String getFileSuffix(File file)
/*     */   {
/* 126 */     String fileName = file.getName();
/* 127 */     int index = fileName.indexOf(".");
/* 128 */     String formatName = fileName.substring(index + 1);
/* 129 */     return formatName;
/*     */   }
/*     */ 
/*     */   public static void cutImage(int x, int y, int width, int height, File file, File output)
/*     */   {
/* 143 */     String formatName = getFileSuffix(file);
/* 144 */     Iterator readers = ImageIO.getImageReadersByFormatName(formatName);
/*     */ 
/* 146 */     ImageReader reader = (ImageReader)readers.next();
/* 147 */     ImageInputStream iis = null;
/*     */     try {
/* 149 */       iis = ImageIO.createImageInputStream(file);
/*     */     } catch (IOException e) {
/* 151 */       e.printStackTrace();
/* 152 */       throw new RuntimeException(e);
/*     */     }
/* 154 */     reader.setInput(iis, true);
/* 155 */     ImageReadParam param = reader.getDefaultReadParam();
/* 156 */     Rectangle sourceRegion = new Rectangle(x, y, width, height);
/* 157 */     param.setSourceRegion(sourceRegion);
/*     */     try {
/* 159 */       BufferedImage bufferedImage = reader.read(0, param);
/* 160 */       ImageIO.write(bufferedImage, getFileSuffix(file), output);
/*     */     } catch (IOException e) {
/* 162 */       e.printStackTrace();
/* 163 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static InputStream cutImage(int x, int y, int width, int height, ImageReader reader)
/*     */   {
/* 169 */     ImageReadParam param = reader.getDefaultReadParam();
/* 170 */     Rectangle sourceRegion = new Rectangle(x, y, width, height);
/* 171 */     param.setSourceRegion(sourceRegion);
/*     */     try {
/* 173 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 174 */       BufferedImage bufferedImage = reader.read(0, param);
/* 175 */       ImageIO.write(bufferedImage, "png", baos);
/* 176 */       return new ByteArrayInputStream(baos.toByteArray());
/*     */     }
/*     */     catch (IOException e) {
/* 179 */       e.printStackTrace();
/* 180 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static ImageIcon getImageIcon(File file)
/*     */   {
/* 186 */     String filename = file.getAbsolutePath();
/* 187 */     return new ImageIcon(filename);
/*     */   }
/*     */ 
/*     */   public static void createWaterMark(File srcFile, File waterFile, File compositeFile)
/*     */     throws IOException
/*     */   {
/* 198 */     Image theImg = new ImageIcon(srcFile.getAbsolutePath()).getImage();
/* 199 */     Image waterImg = new ImageIcon(waterFile.getAbsolutePath()).getImage();
/* 200 */     int width = theImg.getWidth(null);
/* 201 */     int height = theImg.getHeight(null);
/* 202 */     int w = waterImg.getWidth(null);
/* 203 */     int h = waterImg.getHeight(null);
/*     */ 
/* 205 */     BufferedImage bimage = new BufferedImage(width, height, 1);
/* 206 */     Graphics2D g = bimage.createGraphics();
/* 207 */     g.setColor(Color.WHITE);
/* 208 */     g.fillRect(0, 0, width, height);
/* 209 */     g.setColor(Color.BLACK);
/*     */ 
/* 211 */     g.drawImage(theImg, 0, 0, null);
/* 212 */     g.setComposite(AlphaComposite.getInstance(3, 0.4F));
/* 213 */     width -= w;
/* 214 */     height -= h;
/* 215 */     g.drawImage(waterImg, width, height, null);
/* 216 */     g.dispose();
/* 217 */     FileOutputStream fos = null;
/* 218 */     ImageOutputStream ios = null;
/* 219 */     JPEGImageWriter imageWriter = null;
/*     */     try {
/* 221 */       fos = new FileOutputStream(compositeFile);
/* 222 */       imageWriter = (JPEGImageWriter)ImageIO.getImageWritersBySuffix("jpeg").next();
/*     */ 
/* 224 */       ios = ImageIO.createImageOutputStream(fos);
/* 225 */       imageWriter.setOutput(ios);
/*     */ 
/* 228 */       IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(bimage), null);
/*     */ 
/* 230 */       JPEGImageWriteParam jpegParams = (JPEGImageWriteParam)imageWriter.getDefaultWriteParam();
/* 231 */       jpegParams.setCompressionMode(2);
/* 232 */       jpegParams.setCompressionQuality(1.0F);
/*     */ 
/* 234 */       imageWriter.write(imageMetaData, new IIOImage(bimage, null, null), null);
/*     */     }
/*     */     catch (Exception e) {
/* 237 */       e.printStackTrace();
/* 238 */       throw new RuntimeException(e);
/*     */     } finally {
/* 240 */       ios.close();
/* 241 */       fos.close();
/* 242 */       imageWriter.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static InputStream createRectangle(InputStream inputStream, int x, int y, int w, int h)
/*     */   {
/* 251 */     BufferedImage bimage = null;
/*     */     try {
/* 253 */       bimage = ImageIO.read(inputStream);
/*     */     } catch (IOException e) {
/* 255 */       e.printStackTrace();
/* 256 */       throw new RuntimeException(e);
/*     */     }
/* 258 */     Graphics2D g = bimage.createGraphics();
/* 259 */     g.setComposite(AlphaComposite.getInstance(3, 0.4F));
/* 260 */     g.setColor(Color.RED);
/*     */ 
/* 263 */     g.drawRect(x, y, w, h);
/* 264 */     g.dispose();
/*     */     try {
/* 266 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 267 */       ImageIO.write(bimage, "PNG", baos);
/* 268 */       return new ByteArrayInputStream(baos.toByteArray());
/*     */     }
/*     */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 325 */     testCompositeFile();
/*     */   }
/*     */ 
/*     */   public static void testCompositeFile() throws IOException
/*     */   {
/* 330 */     File srcFile = new File("e:\\origin.jpg");
/* 331 */     File waterFile = new File("e:\\logo.jpg");
/* 332 */     File compositeFile = new File("e:\\temp.jpg");
/* 333 */     createWaterMark(srcFile, waterFile, compositeFile);
/*     */   }
/*     */ 
/*     */   public static void tsetReaderImageIconTime() {
/* 337 */     String dir = "F:\\picture\\";
/* 338 */     long start = System.currentTimeMillis();
/* 339 */     File[] files = new File(dir).listFiles();
/* 340 */     for (File item : files) {
/* 341 */       ImageIcon imageIcon = getImageIcon(item);
/* 342 */       int width = imageIcon.getIconWidth();
/* 343 */       int height = imageIcon.getIconHeight();
/* 344 */       log.info("图片的宽度：" + width);
/* 345 */       log.info("图片的高度：" + height);
/*     */     }
/* 347 */     long end = System.currentTimeMillis();
/* 348 */     log.info("所花时间：" + (end - start) / 1000L + "秒");
/*     */   }
/*     */ 
/*     */   public static void testCutImage() throws IOException {
/* 352 */     File file = new File("e:\\vehicle_examine_info.png");
/* 353 */     File output = new File("e:\\vehicle_examine_info.png");
/* 354 */     ImageReader reader = getImageReader(file);
/* 355 */     int imageIndex = 0;
/* 356 */     int width = reader.getWidth(imageIndex) / 2;
/* 357 */     int height = reader.getHeight(imageIndex);
/* 358 */     cutImage(0, 0, width, height, file, output);
/*     */   }
/*     */ 
/*     */   public static void testReaderImageTime()
/*     */     throws IOException
/*     */   {
/* 364 */     String dir = "F:\\picture\\";
/* 365 */     long start = System.currentTimeMillis();
/* 366 */     File[] files = new File(dir).listFiles();
/* 367 */     for (File item : files) {
/* 368 */       ImageReader reader = getImageReader(item);
/* 369 */       int imageIndex = 0;
/* 370 */       int width = reader.getWidth(imageIndex);
/* 371 */       int height = reader.getHeight(imageIndex);
/* 372 */       log.info("图片的宽度：" + width);
/* 373 */       log.info("图片的高度：" + height);
/*     */     }
/* 375 */     long end = System.currentTimeMillis();
/* 376 */     log.info("所花时间：" + (end - start) / 1000L + "秒");
/*     */   }
/*     */ 
/*     */   public static String doCompressByByte(byte[] b, String newFile, int width, int height, float quality, boolean percentage)
/*     */   {
/* 400 */     if ((b.length > 0) && (width > 0)) {
/* 401 */       InputStream in_nocode = new ByteArrayInputStream(b);
/* 402 */       String newImage = null;
/*     */       try
/*     */       {
/* 405 */         Image srcFile = ImageIO.read(in_nocode);
/*     */ 
/* 407 */         int new_w = srcFile.getWidth(null);
/* 408 */         int new_h = srcFile.getHeight(null);
/*     */ 
/* 410 */         if (percentage)
/*     */         {
/* 413 */           double rate = srcFile.getWidth(null) / width + 0.1D;
/*     */ 
/* 418 */           new_w = (int)(srcFile.getWidth(null) / rate);
/* 419 */           new_h = (int)(srcFile.getHeight(null) / rate);
/*     */         }
/*     */         else {
/* 422 */           if (height <= 0) {
/* 423 */             return null;
/*     */           }
/*     */ 
/* 426 */           int num = new_w / 40;
/* 427 */           if (width > 0) {
/* 428 */             new_w = width;
/*     */           } else {
/* 430 */             new_w /= 40;
/* 431 */             if (new_w <= 0) {
/* 432 */               new_w = 40;
/*     */             }
/*     */           }
/* 435 */           if (new_h > 0) {
/* 436 */             new_h = height;
/*     */           } else {
/* 438 */             new_h = height / num * 30;
/* 439 */             if (new_h <= 0) {
/* 440 */               new_h = 30;
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 445 */         BufferedImage tag = new BufferedImage(new_w, new_h, 1);
/* 446 */         tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
/*     */ 
/* 449 */         newImage = newFile;
/*     */ 
/* 452 */         FileOutputStream out = new FileOutputStream(newImage);
/*     */ 
/* 454 */         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
/* 455 */         JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
/*     */ 
/* 458 */         jep.setQuality(quality, true);
/* 459 */         encoder.encode(tag, jep);
/*     */ 
/* 461 */         out.close();
/* 462 */         srcFile.flush();
/*     */       }
/*     */       catch (FileNotFoundException e) {
/* 465 */         e.printStackTrace();
/*     */       } catch (IOException e) {
/* 467 */         e.printStackTrace();
/*     */       }
/* 469 */       return newImage;
/*     */     }
/* 471 */     return null;
/*     */   }
/*     */ 
/*     */   public static String doCompressByPath(String oldFile, String newFile, int width, int height, float quality, boolean percentage)
/*     */   {
/* 494 */     if ((oldFile != null) && (width > 0) && (height > 0)) {
/* 495 */       String newImage = null;
/*     */       try {
/* 497 */         File file = new File(oldFile);
/*     */ 
/* 499 */         if (!file.exists()) {
/* 500 */           return null;
/*     */         }
/*     */ 
/* 503 */         Image srcFile = ImageIO.read(file);
/* 504 */         int new_w = width;
/* 505 */         int new_h = height;
/* 506 */         if (percentage)
/*     */         {
/* 508 */           double rate1 = srcFile.getWidth(null) / width + 0.1D;
/*     */ 
/* 510 */           double rate2 = srcFile.getHeight(null) / height + 0.1D;
/*     */ 
/* 512 */           double rate = rate1 > rate2 ? rate1 : rate2;
/* 513 */           new_w = (int)(srcFile.getWidth(null) / rate);
/* 514 */           new_h = (int)(srcFile.getHeight(null) / rate);
/*     */         }
/*     */ 
/* 517 */         BufferedImage tag = new BufferedImage(new_w, new_h, 1);
/* 518 */         tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
/*     */ 
/* 521 */         newImage = newFile;
/*     */ 
/* 524 */         FileOutputStream out = new FileOutputStream(newImage);
/*     */ 
/* 526 */         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
/* 527 */         JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
/*     */ 
/* 530 */         jep.setQuality(quality, true);
/* 531 */         encoder.encode(tag, jep);
/*     */ 
/* 533 */         out.close();
/* 534 */         srcFile.flush();
/*     */       }
/*     */       catch (FileNotFoundException e) {
/* 537 */         e.printStackTrace();
/*     */       } catch (IOException e) {
/* 539 */         e.printStackTrace();
/*     */       }
/* 541 */       return newImage;
/*     */     }
/* 543 */     return null;
/*     */   }
/*     */ 
/*     */   public static File getFileFromBytes(byte[] b, String outputFile)
/*     */   {
/* 553 */     BufferedOutputStream stream = null;
/* 554 */     File file = null;
/*     */     try {
/* 556 */       file = new File(outputFile);
/* 557 */       FileOutputStream fstream = new FileOutputStream(file);
/* 558 */       stream = new BufferedOutputStream(fstream);
/* 559 */       stream.write(b);
/*     */     } catch (Exception e) {
/* 561 */       e.printStackTrace();
/*     */     } finally {
/* 563 */       if (stream != null) {
/*     */         try {
/* 565 */           stream.close();
/*     */         } catch (IOException e1) {
/* 567 */           e1.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 571 */     return file;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.ImageUtil
 * JD-Core Version:    0.6.2
 */