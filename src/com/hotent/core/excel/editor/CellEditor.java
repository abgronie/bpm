/*     */ package com.hotent.core.excel.editor;
/*     */ 
/*     */ import com.hotent.core.excel.DefaultExcelStyle;
/*     */ import com.hotent.core.excel.ExcelContext;
/*     */ import com.hotent.core.excel.editor.font.BoldFontEditor;
/*     */ import com.hotent.core.excel.editor.font.FontColorEditor;
/*     */ import com.hotent.core.excel.editor.font.FontHeightEditor;
/*     */ import com.hotent.core.excel.editor.font.ItalicFontEditor;
/*     */ import com.hotent.core.excel.editor.listener.CellValueListener;
/*     */ import com.hotent.core.excel.style.Align;
/*     */ import com.hotent.core.excel.style.BorderStyle;
/*     */ import com.hotent.core.excel.style.Color;
/*     */ import com.hotent.core.excel.style.FillPattern;
/*     */ import com.hotent.core.excel.style.VAlign;
/*     */ import com.hotent.core.excel.style.font.Font;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.poi.hssf.usermodel.DVConstraint;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*     */ import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
/*     */ import org.apache.poi.hssf.usermodel.HSSFComment;
/*     */ import org.apache.poi.hssf.usermodel.HSSFDataFormat;
/*     */ import org.apache.poi.hssf.usermodel.HSSFDataValidation;
/*     */ import org.apache.poi.hssf.usermodel.HSSFDateUtil;
/*     */ import org.apache.poi.hssf.usermodel.HSSFFont;
/*     */ import org.apache.poi.hssf.usermodel.HSSFPatriarch;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRichTextString;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.util.CellRangeAddressList;
/*     */ 
/*     */ public class CellEditor extends AbstractEditor
/*     */ {
/*  43 */   private static ItalicFontEditor italicFont = new ItalicFontEditor();
/*  44 */   private static BoldFontEditor boldFont = new BoldFontEditor();
/*  45 */   private static FontColorEditor fontColor = new FontColorEditor();
/*  46 */   private static FontHeightEditor fontHeight = new FontHeightEditor();
/*     */ 
/*  48 */   private List<HSSFCell> workingCell = new ArrayList(2);
/*     */ 
/*     */   public CellEditor(int row, int col, ExcelContext context) {
/*  51 */     this(context);
/*  52 */     add(row, col);
/*     */   }
/*     */ 
/*     */   public CellEditor(ExcelContext context) {
/*  56 */     super(context);
/*     */   }
/*     */ 
/*     */   public CellEditor value(Object value)
/*     */   {
/*  67 */     for (HSSFCell cell : this.workingCell) {
/*  68 */       setCellValue(cell, value, null);
/*     */     }
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor value(Object value, String pattern)
/*     */   {
/*  83 */     for (HSSFCell cell : this.workingCell) {
/*  84 */       setCellValue(cell, value, pattern);
/*     */     }
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */   public Object value()
/*     */   {
/*  95 */     if (this.workingCell.size() == 1) {
/*  96 */       return getCellValue((HSSFCell)this.workingCell.get(0));
/*     */     }
/*  98 */     Object[] vals = new Object[this.workingCell.size()];
/*  99 */     int i = 0;
/* 100 */     for (HSSFCell cell : this.workingCell) {
/* 101 */       vals[(i++)] = getCellValue(cell);
/*     */     }
/* 103 */     return vals;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 112 */     StringBuilder str = new StringBuilder();
/* 113 */     for (HSSFCell cell : this.workingCell) {
/* 114 */       str.append(cell.toString()).append("\t");
/*     */     }
/* 116 */     if (str.length() > 0) {
/* 117 */       str.deleteCharAt(str.length() - 1);
/*     */     }
/* 119 */     return str.toString();
/*     */   }
/*     */ 
/*     */   protected CellEditor add(int row, int col)
/*     */   {
/* 132 */     HSSFCell cell = getCell(row, col);
/* 133 */     this.workingCell.add(cell);
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */   protected CellEditor add(RowEditor row, int col)
/*     */   {
/* 147 */     HSSFCell cell = getCell(row.getHSSFRow(), col);
/* 148 */     this.workingCell.add(cell);
/* 149 */     return this;
/*     */   }
/*     */ 
/*     */   protected CellEditor add(int row, ColumnEditor col)
/*     */   {
/* 162 */     return add(row, col.getCol());
/*     */   }
/*     */ 
/*     */   protected CellEditor add(CellEditor cell)
/*     */   {
/* 175 */     this.workingCell.addAll(cell.getWorkingCell());
/* 176 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor border(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 189 */     for (HSSFCell cell : this.workingCell) {
/* 190 */       HSSFCellStyle style = cell.getCellStyle();
/* 191 */       this.tempCellStyle.cloneStyleFrom(style);
/*     */ 
/* 193 */       this.tempCellStyle.setBorderBottom(borderStyle.getBorderType());
/* 194 */       this.tempCellStyle.setBorderTop(borderStyle.getBorderType());
/* 195 */       this.tempCellStyle.setBorderLeft(borderStyle.getBorderType());
/* 196 */       this.tempCellStyle.setBorderRight(borderStyle.getBorderType());
/* 197 */       this.tempCellStyle.setBottomBorderColor(borderColor.getIndex());
/* 198 */       this.tempCellStyle.setTopBorderColor(borderColor.getIndex());
/* 199 */       this.tempCellStyle.setLeftBorderColor(borderColor.getIndex());
/* 200 */       this.tempCellStyle.setRightBorderColor(borderColor.getIndex());
/* 201 */       updateCellStyle(cell);
/*     */     }
/* 203 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor borderLeft(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 216 */     for (HSSFCell cell : this.workingCell) {
/* 217 */       HSSFCellStyle style = cell.getCellStyle();
/* 218 */       this.tempCellStyle.cloneStyleFrom(style);
/* 219 */       this.tempCellStyle.setBorderLeft(borderStyle.getBorderType());
/* 220 */       this.tempCellStyle.setLeftBorderColor(borderColor.getIndex());
/* 221 */       updateCellStyle(cell);
/*     */     }
/* 223 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor borderRight(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 236 */     for (HSSFCell cell : this.workingCell) {
/* 237 */       HSSFCellStyle style = cell.getCellStyle();
/* 238 */       this.tempCellStyle.cloneStyleFrom(style);
/* 239 */       this.tempCellStyle.setBorderRight(borderStyle.getBorderType());
/* 240 */       this.tempCellStyle.setRightBorderColor(borderColor.getIndex());
/* 241 */       updateCellStyle(cell);
/*     */     }
/* 243 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor borderTop(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 256 */     for (HSSFCell cell : this.workingCell) {
/* 257 */       HSSFCellStyle style = cell.getCellStyle();
/* 258 */       this.tempCellStyle.cloneStyleFrom(style);
/* 259 */       this.tempCellStyle.setBorderTop(borderStyle.getBorderType());
/* 260 */       this.tempCellStyle.setTopBorderColor(borderColor.getIndex());
/* 261 */       updateCellStyle(cell);
/*     */     }
/* 263 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor borderBottom(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 276 */     for (HSSFCell cell : this.workingCell) {
/* 277 */       HSSFCellStyle style = cell.getCellStyle();
/* 278 */       this.tempCellStyle.cloneStyleFrom(style);
/* 279 */       this.tempCellStyle.setBorderBottom(borderStyle.getBorderType());
/* 280 */       this.tempCellStyle.setBottomBorderColor(borderColor.getIndex());
/* 281 */       updateCellStyle(cell);
/*     */     }
/* 283 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor font(IFontEditor fontEditor)
/*     */   {
/* 294 */     Map fontCache = this.ctx.getFontCache();
/* 295 */     for (HSSFCell cell : this.workingCell)
/*     */     {
/* 299 */       HSSFFont font = cell.getCellStyle().getFont(this.workBook);
/* 300 */       copyFont(font, this.tempFont);
/* 301 */       fontEditor.updateFont(new Font(this.tempFont));
/* 302 */       int fontHash = this.tempFont.hashCode() - this.tempFont.getIndex();
/*     */ 
/* 305 */       this.tempCellStyle.cloneStyleFrom(cell.getCellStyle());
/* 306 */       if (fontCache.containsKey(Integer.valueOf(fontHash)))
/*     */       {
/* 309 */         this.tempCellStyle.setFont((HSSFFont)fontCache.get(Integer.valueOf(fontHash)));
/*     */       }
/*     */       else {
/* 312 */         HSSFFont newFont = this.workBook.createFont();
/* 313 */         copyFont(this.tempFont, newFont);
/*     */ 
/* 315 */         this.tempCellStyle.setFont(newFont);
/* 316 */         int newFontHash = newFont.hashCode() - newFont.getIndex();
/* 317 */         fontCache.put(Integer.valueOf(newFontHash), newFont);
/*     */       }
/* 319 */       updateCellStyle(cell);
/*     */     }
/* 321 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor bold()
/*     */   {
/* 330 */     font(boldFont);
/* 331 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor fontHeightInPoint(int height)
/*     */   {
/* 342 */     fontHeight.setHeight(height);
/* 343 */     font(fontHeight);
/* 344 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor color(Color color)
/*     */   {
/* 354 */     fontColor.setColor(color);
/* 355 */     font(fontColor);
/* 356 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor italic()
/*     */   {
/* 365 */     font(italicFont);
/* 366 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor bgColor(Color bg)
/*     */   {
/* 377 */     return bgColor(bg, FillPattern.SOLID_FOREGROUND);
/*     */   }
/*     */ 
/*     */   public CellEditor bgColor(Color bg, FillPattern fillPattern)
/*     */   {
/* 390 */     for (HSSFCell cell : this.workingCell) {
/* 391 */       HSSFCellStyle style = cell.getCellStyle();
/* 392 */       this.tempCellStyle.cloneStyleFrom(style);
/*     */ 
/* 394 */       this.tempCellStyle.setFillPattern(fillPattern.getFillPattern());
/* 395 */       this.tempCellStyle.setFillForegroundColor(bg.getIndex());
/* 396 */       updateCellStyle(cell);
/*     */     }
/* 398 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor align(Align align)
/*     */   {
/* 409 */     for (HSSFCell cell : this.workingCell) {
/* 410 */       HSSFCellStyle style = cell.getCellStyle();
/* 411 */       this.tempCellStyle.cloneStyleFrom(style);
/*     */ 
/* 413 */       this.tempCellStyle.setAlignment(align.getAlignment());
/* 414 */       updateCellStyle(cell);
/*     */     }
/* 416 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor vAlign(VAlign align)
/*     */   {
/* 427 */     for (HSSFCell cell : this.workingCell) {
/* 428 */       HSSFCellStyle style = cell.getCellStyle();
/* 429 */       this.tempCellStyle.cloneStyleFrom(style);
/*     */ 
/* 431 */       this.tempCellStyle.setVerticalAlignment(align.getAlignment());
/* 432 */       updateCellStyle(cell);
/*     */     }
/* 434 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor warpText(boolean autoWarp)
/*     */   {
/* 445 */     for (HSSFCell cell : this.workingCell) {
/* 446 */       HSSFCellStyle style = cell.getCellStyle();
/* 447 */       this.tempCellStyle.cloneStyleFrom(style);
/*     */ 
/* 449 */       this.tempCellStyle.setWrapText(autoWarp);
/* 450 */       updateCellStyle(cell);
/*     */     }
/* 452 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor hidden(boolean hidden)
/*     */   {
/* 463 */     for (HSSFCell cell : this.workingCell) {
/* 464 */       HSSFCellStyle style = cell.getCellStyle();
/* 465 */       this.tempCellStyle.cloneStyleFrom(style);
/* 466 */       this.tempCellStyle.setHidden(hidden);
/* 467 */       updateCellStyle(cell);
/*     */     }
/* 469 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor indent(int indent)
/*     */   {
/* 479 */     for (HSSFCell cell : this.workingCell) {
/* 480 */       HSSFCellStyle style = cell.getCellStyle();
/* 481 */       this.tempCellStyle.cloneStyleFrom(style);
/* 482 */       this.tempCellStyle.setIndention((short)indent);
/* 483 */       updateCellStyle(cell);
/*     */     }
/* 485 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor lock(boolean locked)
/*     */   {
/* 496 */     for (HSSFCell cell : this.workingCell) {
/* 497 */       HSSFCellStyle style = cell.getCellStyle();
/* 498 */       this.tempCellStyle.cloneStyleFrom(style);
/* 499 */       this.tempCellStyle.setLocked(locked);
/* 500 */       updateCellStyle(cell);
/*     */     }
/* 502 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor rotate(int rotation)
/*     */   {
/* 513 */     for (HSSFCell cell : this.workingCell) {
/* 514 */       HSSFCellStyle style = cell.getCellStyle();
/* 515 */       this.tempCellStyle.cloneStyleFrom(style);
/* 516 */       this.tempCellStyle.setRotation((short)rotation);
/* 517 */       updateCellStyle(cell);
/*     */     }
/* 519 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor comment(String content)
/*     */   {
/* 530 */     HSSFPatriarch patr = this.ctx.getHSSFPatriarch(this.workingSheet);
/* 531 */     for (HSSFCell cell : this.workingCell) {
/* 532 */       HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)cell.getColumnIndex(), cell.getRowIndex(), (short)(cell.getColumnIndex() + 3), cell.getRowIndex() + 4));
/*     */ 
/* 537 */       comment.setString(new HSSFRichTextString(content));
/* 538 */       cell.setCellComment(comment);
/*     */     }
/* 540 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor style(HSSFCellStyle style)
/*     */   {
/* 550 */     for (HSSFCell cell : this.workingCell) {
/* 551 */       cell.setCellStyle(style);
/*     */     }
/* 553 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor dataFormat(String format)
/*     */   {
/* 564 */     short index = HSSFDataFormat.getBuiltinFormat(format);
/* 565 */     for (HSSFCell cell : this.workingCell) {
/* 566 */       HSSFCellStyle style = cell.getCellStyle();
/* 567 */       this.tempCellStyle.cloneStyleFrom(style);
/* 568 */       if (index == -1) {
/* 569 */         HSSFDataFormat dataFormat = this.ctx.getWorkBook().createDataFormat();
/*     */ 
/* 571 */         index = dataFormat.getFormat(format);
/*     */       }
/* 573 */       this.tempCellStyle.setDataFormat(index);
/* 574 */       updateCellStyle(cell);
/*     */     }
/* 576 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor width(int width)
/*     */   {
/* 591 */     return width(new int[] { width });
/*     */   }
/*     */ 
/*     */   protected CellEditor width(int[] widths)
/*     */   {
/* 602 */     int i = -1;
/* 603 */     for (HSSFCell cell : this.workingCell) {
/* 604 */       if (i >= widths.length - 1) {
/*     */         break;
/*     */       }
/* 607 */       this.workingSheet.setColumnWidth(cell.getColumnIndex(), widths[(++i)]);
/*     */     }
/* 609 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor addWidth(int width)
/*     */   {
/* 620 */     for (HSSFCell cell : this.workingCell) {
/* 621 */       int w = this.workingSheet.getColumnWidth(cell.getColumnIndex());
/* 622 */       this.workingSheet.setColumnWidth(cell.getColumnIndex(), width + w);
/*     */     }
/* 624 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor height(float height)
/*     */   {
/* 635 */     for (HSSFCell cell : this.workingCell) {
/* 636 */       HSSFRow row = getRow(cell.getRowIndex());
/* 637 */       row.setHeightInPoints(height);
/*     */     }
/* 639 */     return this;
/*     */   }
/*     */ 
/*     */   protected CellEditor height(float[] heights)
/*     */   {
/* 649 */     int i = -1;
/* 650 */     for (HSSFCell cell : this.workingCell) {
/* 651 */       if (i >= heights.length - 1) {
/*     */         break;
/*     */       }
/* 654 */       HSSFRow row = getRow(cell.getRowIndex());
/* 655 */       row.setHeightInPoints(heights[(++i)]);
/*     */     }
/* 657 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor addHeight(float height)
/*     */   {
/* 668 */     for (HSSFCell cell : this.workingCell) {
/* 669 */       HSSFRow row = getRow(cell.getRowIndex());
/* 670 */       float h = row.getHeightInPoints();
/* 671 */       row.setHeightInPoints(height + h);
/*     */     }
/* 673 */     return this;
/*     */   }
/*     */ 
/*     */   public RowEditor row()
/*     */   {
/* 682 */     return new RowEditor(((HSSFCell)this.workingCell.get(0)).getRowIndex(), this.ctx);
/*     */   }
/*     */ 
/*     */   public ColumnEditor colunm()
/*     */   {
/* 691 */     return new ColumnEditor(((HSSFCell)this.workingCell.get(0)).getColumnIndex(), this.ctx);
/*     */   }
/*     */ 
/*     */   public SheetEditor sheet()
/*     */   {
/* 700 */     return new SheetEditor(((HSSFCell)this.workingCell.get(0)).getSheet(), this.ctx);
/*     */   }
/*     */ 
/*     */   public HSSFCell toHSSFCell()
/*     */   {
/* 709 */     if (this.workingCell.size() > 0) {
/* 710 */       return (HSSFCell)this.workingCell.get(0);
/*     */     }
/* 712 */     return null;
/*     */   }
/*     */ 
/*     */   public CellEditor activeCell()
/*     */   {
/* 721 */     if (this.workingCell.size() > 0) {
/* 722 */       ((HSSFCell)this.workingCell.get(0)).setAsActiveCell();
/*     */     }
/* 724 */     return this;
/*     */   }
/*     */ 
/*     */   private void updateCellStyle(HSSFCell cell)
/*     */   {
/* 733 */     Map styleCache = this.ctx.getStyleCache();
/* 734 */     int tempStyleHash = this.tempCellStyle.hashCode() - this.tempCellStyle.getIndex();
/*     */ 
/* 736 */     if (styleCache.containsKey(Integer.valueOf(tempStyleHash)))
/*     */     {
/* 738 */       cell.setCellStyle((HSSFCellStyle)styleCache.get(Integer.valueOf(tempStyleHash)));
/*     */     } else {
/* 740 */       HSSFCellStyle newStyle = this.workBook.createCellStyle();
/* 741 */       newStyle.cloneStyleFrom(this.tempCellStyle);
/* 742 */       cell.setCellStyle(newStyle);
/* 743 */       int newStyleHash = newStyle.hashCode() - newStyle.getIndex();
/*     */ 
/* 745 */       styleCache.put(Integer.valueOf(newStyleHash), newStyle);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void copyFont(HSSFFont src, HSSFFont dest)
/*     */   {
/* 758 */     dest.setBoldweight(src.getBoldweight());
/* 759 */     dest.setCharSet(src.getCharSet());
/* 760 */     dest.setColor(src.getColor());
/* 761 */     dest.setFontHeight(src.getFontHeight());
/* 762 */     dest.setFontHeightInPoints(src.getFontHeightInPoints());
/* 763 */     dest.setFontName(src.getFontName());
/* 764 */     dest.setItalic(src.getItalic());
/* 765 */     dest.setStrikeout(src.getStrikeout());
/* 766 */     dest.setTypeOffset(src.getTypeOffset());
/* 767 */     dest.setUnderline(src.getUnderline());
/*     */   }
/*     */ 
/*     */   private void setCellValue(HSSFCell cell, Object value, String pattern)
/*     */   {
/* 781 */     if (value == null)
/* 782 */       return;
/* 783 */     if (((value instanceof Long)) || ((value instanceof Integer)) || ((value instanceof Short)) || ((value instanceof Byte)))
/*     */     {
/* 785 */       cell.setCellValue(((Long)value).longValue());
/* 786 */       cell.setCellType(0);
/* 787 */       dataFormat("0");
/* 788 */     } else if (((value instanceof Double)) || ((value instanceof Float)) || ((value instanceof BigDecimal))) {
/* 789 */       cell.setCellValue(Double.valueOf(value.toString()).doubleValue());
/* 790 */       cell.setCellType(0);
/* 791 */       dataFormat("#.##");
/* 792 */     } else if ((value instanceof Boolean)) {
/* 793 */       cell.setCellValue(((Boolean)value).booleanValue());
/* 794 */       cell.setCellType(4);
/*     */     }
/* 796 */     else if ((value != null) && (value.toString().startsWith("="))) {
/* 797 */       cell.setCellFormula(value.toString().substring(1));
/* 798 */       cell.setCellType(2);
/*     */     }
/* 800 */     else if ((value instanceof Date)) {
/* 801 */       if ((pattern == null) || (pattern.trim().equals(""))) {
/* 802 */         pattern = this.ctx.getDefaultStyle().getDefaultDatePattern();
/*     */       }
/* 804 */       cell.setCellValue((Date)value);
/*     */     } else {
/* 806 */       cell.setCellValue(new HSSFRichTextString(value == null ? "" : value.toString()));
/*     */ 
/* 808 */       cell.setCellType(1);
/* 809 */       if (pattern == null) {
/* 810 */         dataFormat("@");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 815 */     if (pattern != null)
/* 816 */       dataFormat(pattern);
/*     */   }
/*     */ 
/*     */   private Object getCellValue(HSSFCell cell)
/*     */   {
/* 829 */     int cellType = cell.getCellType();
/* 830 */     switch (cellType) {
/*     */     case 3:
/* 832 */       return "";
/*     */     case 4:
/* 834 */       return Boolean.valueOf(cell.getBooleanCellValue());
/*     */     case 5:
/* 836 */       return Byte.valueOf(cell.getErrorCellValue());
/*     */     case 2:
/* 838 */       return cell.getCellFormula();
/*     */     case 0:
/* 840 */       if (HSSFDateUtil.isCellDateFormatted(cell)) {
/* 841 */         return cell.getDateCellValue();
/*     */       }
/* 843 */       return Double.valueOf(cell.getNumericCellValue());
/*     */     case 1:
/* 846 */       return cell.getRichStringCellValue().toString();
/*     */     }
/* 848 */     return "";
/*     */   }
/*     */ 
/*     */   private void invokeListener(HSSFCell cell, Object value)
/*     */   {
/* 861 */     StackTraceElement[] st = new Throwable().getStackTrace();
/*     */     try {
/* 863 */       for (StackTraceElement e : st) {
/* 864 */         Class[] interfacesList = Class.forName(e.getClassName()).getInterfaces();
/*     */ 
/* 866 */         for (Class clazz : interfacesList) {
/* 867 */           if (clazz.getSimpleName().equals("CellValueListener")) {
/* 868 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (ClassNotFoundException e)
/*     */     {
/*     */     }
/* 876 */     int sheetIndex = this.workBook.getSheetIndex(cell.getSheet());
/* 877 */     List<CellValueListener>  listeners = this.ctx.getListenerList(sheetIndex);
/* 878 */     for (CellValueListener l : listeners)
/* 879 */       l.onValueChange(this, value, cell.getRowIndex(), cell.getColumnIndex(), this.ctx.getExcel());
/*     */   }
/*     */ 
/*     */   protected List<HSSFCell> getWorkingCell()
/*     */   {
/* 885 */     return this.workingCell;
/*     */   }
/*     */ 
/*     */   private double null2Double(Object s)
/*     */   {
/* 896 */     double v = 0.0D;
/* 897 */     if (s == null)
/* 898 */       return v;
/*     */     try {
/* 900 */       v = Double.parseDouble(s.toString());
/*     */     } catch (Exception e) {
/*     */     }
/* 903 */     return v;
/*     */   }
/*     */ 
/*     */   public void addValidationData(CellRangeAddressList regions, String[] explicitListValues)
/*     */   {
/* 915 */     DVConstraint constraint = DVConstraint.createExplicitListConstraint(explicitListValues);
/*     */ 
/* 918 */     HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);
/*     */ 
/* 921 */     this.workingSheet.addValidationData(data_validation);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.CellEditor
 * JD-Core Version:    0.6.2
 */