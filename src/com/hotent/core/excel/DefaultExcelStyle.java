/*     */ package com.hotent.core.excel;
/*     */ 
/*     */ import com.hotent.core.excel.style.Align;
/*     */ import com.hotent.core.excel.style.BorderStyle;
/*     */ import com.hotent.core.excel.style.Color;
/*     */ import com.hotent.core.excel.style.FillPattern;
/*     */ import com.hotent.core.excel.style.VAlign;
/*     */ 
/*     */ public class DefaultExcelStyle
/*     */ {
/*  19 */   private short fontSize = 12;
/*     */ 
/*  21 */   private String fontName = "宋体";
/*     */ 
/*  23 */   private Color backgroundColor = Color.AUTOMATIC;
/*     */ 
/*  25 */   private FillPattern fillPattern = FillPattern.NO_FILL;
/*     */ 
/*  27 */   private Align align = Align.GENERAL;
/*     */ 
/*  29 */   private VAlign vAlign = VAlign.CENTER;
/*     */ 
/*  31 */   private Color fontColor = Color.AUTOMATIC;
/*     */ 
/*  33 */   private String defaultDatePattern = "yyyy-mm-dd hh:mm：ss";
/*     */ 
/*  35 */   private Color borderColor = Color.AUTOMATIC;
/*     */ 
/*  37 */   private BorderStyle borderStyle = BorderStyle.NONE;
/*     */ 
/*     */   public Color getBorderColor()
/*     */   {
/*  44 */     return this.borderColor;
/*     */   }
/*     */ 
/*     */   public void setBorderColor(Color borderColor)
/*     */   {
/*  51 */     this.borderColor = borderColor;
/*     */   }
/*     */ 
/*     */   public BorderStyle getBorderStyle()
/*     */   {
/*  59 */     return this.borderStyle;
/*     */   }
/*     */ 
/*     */   public void setBorderStyle(BorderStyle borderStyle)
/*     */   {
/*  67 */     this.borderStyle = borderStyle;
/*     */   }
/*     */ 
/*     */   public void setFontSize(int fontSize)
/*     */   {
/*  75 */     this.fontSize = ((short)fontSize);
/*     */   }
/*     */ 
/*     */   public short getFontSize()
/*     */   {
/*  83 */     return this.fontSize;
/*     */   }
/*     */ 
/*     */   public void setFontName(String fontName)
/*     */   {
/*  91 */     this.fontName = fontName;
/*     */   }
/*     */ 
/*     */   public String getFontName()
/*     */   {
/*  98 */     return this.fontName;
/*     */   }
/*     */ 
/*     */   public void setBackgroundColor(Color backgroundColor)
/*     */   {
/* 106 */     this.backgroundColor = backgroundColor;
/*     */   }
/*     */ 
/*     */   public Color getBackgroundColor()
/*     */   {
/* 113 */     return this.backgroundColor;
/*     */   }
/*     */ 
/*     */   public void setFillPattern(FillPattern fillPattern)
/*     */   {
/* 122 */     this.fillPattern = fillPattern;
/*     */   }
/*     */ 
/*     */   public FillPattern getFillPattern()
/*     */   {
/* 130 */     return this.fillPattern;
/*     */   }
/*     */ 
/*     */   public void setAlign(Align align)
/*     */   {
/* 138 */     this.align = align;
/*     */   }
/*     */ 
/*     */   public Align getAlign()
/*     */   {
/* 145 */     return this.align;
/*     */   }
/*     */ 
/*     */   public void setVAlign(VAlign vAlign)
/*     */   {
/* 153 */     this.vAlign = vAlign;
/*     */   }
/*     */ 
/*     */   public VAlign getVAlign()
/*     */   {
/* 161 */     return this.vAlign;
/*     */   }
/*     */ 
/*     */   public void setFontColor(Color fontColor)
/*     */   {
/* 169 */     this.fontColor = fontColor;
/*     */   }
/*     */ 
/*     */   public Color getFontColor()
/*     */   {
/* 176 */     return this.fontColor;
/*     */   }
/*     */ 
/*     */   public void setDefaultDatePattern(String defaultDatePattern)
/*     */   {
/* 184 */     this.defaultDatePattern = defaultDatePattern;
/*     */   }
/*     */ 
/*     */   public String getDefaultDatePattern()
/*     */   {
/* 192 */     return this.defaultDatePattern;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.DefaultExcelStyle
 * JD-Core Version:    0.6.2
 */