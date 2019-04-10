/*    */ package com.hotent.core.util;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.FontFormatException;
/*    */ import java.awt.GraphicsEnvironment;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class FontUtil
/*    */ {
/*    */   private static final String FONT_PATH = "fonts/simsun.ttf";
/* 21 */   private static Font dynamicFont = null;
/*    */ 
/*    */   public static Boolean isExist(String fontName)
/*    */   {
/* 29 */     if (StringUtil.isEmpty(fontName)) return Boolean.valueOf(false);
/* 30 */     GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 31 */     String[] fontNames = e.getAvailableFontFamilyNames();
/* 32 */     for (int i = 0; i < fontNames.length; i++) {
/* 33 */       String curFontName = fontNames[i];
/* 34 */       if (fontName.equals(curFontName)) return Boolean.valueOf(true);
/*    */     }
/* 36 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */   public static Font getFont(String fontName, Integer style, Integer size) {
/* 40 */     if (isExist(fontName).booleanValue())
/* 41 */       return new Font(fontName, style.intValue(), size.intValue());
/*    */     try
/*    */     {
/* 44 */       if (BeanUtils.isEmpty(dynamicFont)) {
/* 45 */         InputStream resourceAsStream = FontUtil.class.getClassLoader().getResourceAsStream("fonts/simsun.ttf");
/* 46 */         if (BeanUtils.isEmpty(resourceAsStream)) {
/* 47 */           throw new IOException("未加载到字体文件：fonts/simsun.ttf");
/*    */         }
/*    */ 
/* 50 */         dynamicFont = Font.createFont(0, resourceAsStream);
/* 51 */         float parseFloat = Float.parseFloat(size.toString());
/* 52 */         dynamicFont = dynamicFont.deriveFont(parseFloat);
/*    */       }
/* 54 */       return dynamicFont;
/*    */     } catch (FontFormatException ex) {
/* 56 */       ex.printStackTrace();
/*    */     } catch (IOException ex) {
/* 58 */       ex.printStackTrace();
/*    */     }
/* 60 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.util.FontUtil
 * JD-Core Version:    0.6.2
 */