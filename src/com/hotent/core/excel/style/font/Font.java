/*     */ package com.hotent.core.excel.style.font;
/*     */ 
/*     */ import com.hotent.core.excel.style.Color;
/*     */ import org.apache.poi.hssf.usermodel.HSSFFont;
/*     */ 
/*     */ public class Font
/*     */ {
/*     */   private HSSFFont font;
/*     */ 
/*     */   public Font(HSSFFont font)
/*     */   {
/*  15 */     this.font = font;
/*     */   }
/*     */ 
/*     */   public Font boldweight(BoldWeight boldweight)
/*     */   {
/*  24 */     this.font.setBoldweight(boldweight.getWeight());
/*  25 */     return this;
/*     */   }
/*     */ 
/*     */   public BoldWeight boldweight()
/*     */   {
/*  33 */     return BoldWeight.instance(this.font.getBoldweight());
/*     */   }
/*     */ 
/*     */   public Font charSet(CharSet charset)
/*     */   {
/*  42 */     this.font.setCharSet(charset.getCharset());
/*  43 */     return this;
/*     */   }
/*     */ 
/*     */   public CharSet charSet()
/*     */   {
/*  51 */     return CharSet.instance(this.font.getCharSet());
/*     */   }
/*     */ 
/*     */   public Font color(Color color)
/*     */   {
/*  60 */     if (color.equals(Color.AUTOMATIC))
/*  61 */       this.font.setColor((short)32767);
/*     */     else {
/*  63 */       this.font.setColor(color.getIndex());
/*     */     }
/*  65 */     return this;
/*     */   }
/*     */ 
/*     */   public Color color()
/*     */   {
/*  73 */     return Color.instance(this.font.getColor());
/*     */   }
/*     */ 
/*     */   public Font fontHeight(int height) {
/*  77 */     this.font.setFontHeight((short)height);
/*  78 */     return this;
/*     */   }
/*     */ 
/*     */   public short fontHeight() {
/*  82 */     return this.font.getFontHeight();
/*     */   }
/*     */ 
/*     */   public Font fontHeightInPoints(int height) {
/*  86 */     this.font.setFontHeightInPoints((short)height);
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */   public short fontHeightInPoints()
/*     */   {
/*  95 */     return this.font.getFontHeightInPoints();
/*     */   }
/*     */ 
/*     */   public Font fontName(String name)
/*     */   {
/* 104 */     this.font.setFontName(name);
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   public String fontName()
/*     */   {
/* 113 */     return this.font.getFontName();
/*     */   }
/*     */ 
/*     */   public Font italic(boolean italic)
/*     */   {
/* 122 */     this.font.setItalic(italic);
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   public boolean italic()
/*     */   {
/* 131 */     return this.font.getItalic();
/*     */   }
/*     */ 
/*     */   public Font strikeout(boolean strikeout)
/*     */   {
/* 140 */     this.font.setStrikeout(strikeout);
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */   public boolean strikeout()
/*     */   {
/* 149 */     return this.font.getStrikeout();
/*     */   }
/*     */ 
/*     */   public Font typeOffset(TypeOffset offset)
/*     */   {
/* 158 */     this.font.setTypeOffset(offset.getOffset());
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */   public TypeOffset typeOffset()
/*     */   {
/* 167 */     return TypeOffset.instance(this.font.getTypeOffset());
/*     */   }
/*     */ 
/*     */   public Font underline(Underline underline)
/*     */   {
/* 176 */     this.font.setUnderline(underline.getLine());
/* 177 */     return this;
/*     */   }
/*     */ 
/*     */   public Underline underline()
/*     */   {
/* 185 */     return Underline.instance(this.font.getUnderline());
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.font.Font
 * JD-Core Version:    0.6.2
 */