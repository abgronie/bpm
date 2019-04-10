/*     */ package com.hotent.core.excel;
/*     */ 
/*     */ import com.hotent.core.excel.editor.listener.CellValueListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*     */ import org.apache.poi.hssf.usermodel.HSSFFont;
/*     */ import org.apache.poi.hssf.usermodel.HSSFPatriarch;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ 
/*     */ public final class ExcelContext
/*     */ {
/*  25 */   private Map<Integer, HSSFCellStyle> styleCache = new HashMap();
/*  26 */   private Map<Integer, HSSFFont> fontCache = new HashMap();
/*  27 */   private Map<HSSFSheet, HSSFPatriarch> patriarchCache = new HashMap();
/*     */   private HSSFWorkbook workBook;
/*     */   private HSSFCellStyle tempCellStyle;
/*     */   private HSSFFont tempFont;
/*     */   private Excel excel;
/*     */   private HSSFSheet workingSheet;
/*     */   private DefaultExcelStyle defaultStyle;
/*  43 */   private int workingSheetIndex = 0;
/*     */   private Map<Integer, List<CellValueListener>> cellValueListener;
/*     */ 
/*     */   protected ExcelContext(Excel excel, HSSFWorkbook workBook)
/*     */   {
/*  51 */     this.workBook = workBook;
/*  52 */     short numStyle = workBook.getNumCellStyles();
/*  53 */     for (short i = 0; i < numStyle; i = (short)(i + 1)) {
/*  54 */       HSSFCellStyle style = workBook.getCellStyleAt(i);
/*  55 */       if (style != this.tempCellStyle) {
/*  56 */         this.styleCache.put(Integer.valueOf(style.hashCode() - style.getIndex()), style);
/*     */       }
/*     */     }
/*  59 */     short numFont = workBook.getNumberOfFonts();
/*  60 */     for (short i = 0; i < numFont; i = (short)(i + 1)) {
/*  61 */       HSSFFont font = workBook.getFontAt(i);
/*  62 */       if (font != this.tempFont)
/*  63 */         this.fontCache.put(Integer.valueOf(font.hashCode() - font.getIndex()), font);
/*     */     }
/*     */   }
/*     */ 
/*     */   public HSSFWorkbook getWorkBook()
/*     */   {
/*  69 */     return this.workBook;
/*     */   }
/*     */ 
/*     */   public void setWorkBook(HSSFWorkbook workBook) {
/*  73 */     this.workBook = workBook;
/*     */   }
/*     */ 
/*     */   public HSSFCellStyle getTempCellStyle() {
/*  77 */     return this.tempCellStyle;
/*     */   }
/*     */ 
/*     */   public void setTempCellStyle(HSSFCellStyle tempCellStyle) {
/*  81 */     this.tempCellStyle = tempCellStyle;
/*     */   }
/*     */ 
/*     */   public HSSFFont getTempFont() {
/*  85 */     return this.tempFont;
/*     */   }
/*     */ 
/*     */   public void setTempFont(HSSFFont tempFont) {
/*  89 */     this.tempFont = tempFont;
/*     */   }
/*     */ 
/*     */   public HSSFSheet getWorkingSheet() {
/*  93 */     return this.workingSheet;
/*     */   }
/*     */ 
/*     */   public void setWorkingSheet(HSSFSheet workingSheet) {
/*  97 */     this.workingSheet = workingSheet;
/*  98 */     this.workingSheetIndex = this.workBook.getSheetIndex(workingSheet);
/*     */   }
/*     */ 
/*     */   public HSSFPatriarch getHSSFPatriarch(HSSFSheet sheet)
/*     */   {
/* 107 */     HSSFPatriarch patr = null;
/*     */     try {
/* 109 */       patr = (HSSFPatriarch)this.patriarchCache.get(sheet);
/* 110 */       if (patr == null) {
/* 111 */         patr = sheet.createDrawingPatriarch();
/* 112 */         this.patriarchCache.put(sheet, patr);
/*     */       }
/*     */     } catch (Exception e) {
/* 115 */       patr = sheet.createDrawingPatriarch();
/*     */     }
/* 117 */     return patr;
/*     */   }
/*     */ 
/*     */   public void setDefaultStyle(DefaultExcelStyle defaultStyle) {
/* 121 */     this.defaultStyle = defaultStyle;
/*     */   }
/*     */ 
/*     */   public DefaultExcelStyle getDefaultStyle() {
/* 125 */     return this.defaultStyle;
/*     */   }
/*     */ 
/*     */   public int getWorkingSheetIndex() {
/* 129 */     return this.workingSheetIndex;
/*     */   }
/*     */ 
/*     */   public void setStyleCache(Map<Integer, HSSFCellStyle> styleCache) {
/* 133 */     this.styleCache = styleCache;
/*     */   }
/*     */ 
/*     */   public Map<Integer, HSSFCellStyle> getStyleCache() {
/* 137 */     return this.styleCache;
/*     */   }
/*     */ 
/*     */   public void setFontCache(Map<Integer, HSSFFont> fontCache) {
/* 141 */     this.fontCache = fontCache;
/*     */   }
/*     */ 
/*     */   public Map<Integer, HSSFFont> getFontCache() {
/* 145 */     return this.fontCache;
/*     */   }
/*     */ 
/*     */   private Map<Integer, List<CellValueListener>> getCellValueListener() {
/* 149 */     if (this.cellValueListener == null) {
/* 150 */       this.cellValueListener = new HashMap();
/*     */     }
/* 152 */     return this.cellValueListener;
/*     */   }
/*     */ 
/*     */   public List<CellValueListener> getListenerList(int sheetIndex)
/*     */   {
/* 161 */     Map map = getCellValueListener();
/* 162 */     List listenerList = (List)map.get(Integer.valueOf(sheetIndex));
/* 163 */     if (listenerList == null) {
/* 164 */       listenerList = new ArrayList();
/* 165 */       map.put(Integer.valueOf(sheetIndex), listenerList);
/*     */     }
/* 167 */     return listenerList;
/*     */   }
/*     */ 
/*     */   public Excel getExcel() {
/* 171 */     return this.excel;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.ExcelContext
 * JD-Core Version:    0.6.2
 */