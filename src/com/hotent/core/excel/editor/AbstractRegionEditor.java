/*     */ package com.hotent.core.excel.editor;
/*     */ 
/*     */ import com.hotent.core.excel.ExcelContext;
/*     */ import com.hotent.core.excel.style.Align;
/*     */ import com.hotent.core.excel.style.BorderStyle;
/*     */ import com.hotent.core.excel.style.Color;
/*     */ import com.hotent.core.excel.style.FillPattern;
/*     */ import com.hotent.core.excel.style.VAlign;
/*     */ import java.util.List;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.ss.util.CellRangeAddress;
/*     */ import org.apache.poi.ss.util.CellRangeAddressList;
/*     */ 
/*     */ public abstract class AbstractRegionEditor<T> extends AbstractEditor
/*     */ {
/*     */   protected AbstractRegionEditor(ExcelContext context)
/*     */   {
/*  24 */     super(context);
/*     */   }
/*     */ 
/*     */   protected abstract CellEditor newCellEditor();
/*     */ 
/*     */   protected abstract CellEditor newTopCellEditor();
/*     */ 
/*     */   protected abstract CellEditor newBottomCellEditor();
/*     */ 
/*     */   protected abstract CellEditor newLeftCellEditor();
/*     */ 
/*     */   protected abstract CellEditor newRightCellEditor();
/*     */ 
/*     */   protected abstract CellRangeAddress getCellRange();
/*     */ 
/*     */   public T borderOuter(BorderStyle borderStyle, Color borderColor)
/*     */   {
/*  81 */     borderBottom(borderStyle, borderColor);
/*  82 */     borderLeft(borderStyle, borderColor);
/*  83 */     borderRight(borderStyle, borderColor);
/*  84 */     borderTop(borderStyle, borderColor);
/*  85 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T borderFull(BorderStyle borderStyle, Color borderColor)
/*     */   {
/*  99 */     CellEditor cellEditor = newCellEditor();
/* 100 */     cellEditor.border(borderStyle, borderColor);
/* 101 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T borderLeft(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 116 */     CellEditor cellEditorLeft = newLeftCellEditor();
/* 117 */     cellEditorLeft.borderLeft(borderStyle, borderColor);
/* 118 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T borderRight(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 133 */     CellEditor cellEditorRight = newRightCellEditor();
/* 134 */     cellEditorRight.borderRight(borderStyle, borderColor);
/* 135 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T borderTop(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 150 */     CellEditor cellEditorTop = newTopCellEditor();
/* 151 */     cellEditorTop.borderTop(borderStyle, borderColor);
/* 152 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T borderBottom(BorderStyle borderStyle, Color borderColor)
/*     */   {
/* 167 */     CellEditor cellEditorBottom = newBottomCellEditor();
/* 168 */     cellEditorBottom.borderBottom(borderStyle, borderColor);
/* 169 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T font(IFontEditor fontEditor)
/*     */   {
/* 181 */     CellEditor cellEditor = newCellEditor();
/* 182 */     cellEditor.font(fontEditor);
/* 183 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T bgColor(Color bg)
/*     */   {
/* 195 */     CellEditor cellEditor = newCellEditor();
/* 196 */     cellEditor.bgColor(bg);
/* 197 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T bgColor(Color bg, FillPattern fillPattern)
/*     */   {
/* 215 */     CellEditor cellEditor = newCellEditor();
/* 216 */     cellEditor.bgColor(bg, fillPattern);
/* 217 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T align(Align align)
/*     */   {
/* 231 */     CellEditor cellEditor = newCellEditor();
/* 232 */     cellEditor.align(align);
/* 233 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T vAlign(VAlign align)
/*     */   {
/* 246 */     CellEditor cellEditor = newCellEditor();
/* 247 */     cellEditor.vAlign(align);
/* 248 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T warpText(boolean autoWarp)
/*     */   {
/* 260 */     CellEditor cellEditor = newCellEditor();
/* 261 */     cellEditor.warpText(autoWarp);
/* 262 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T merge()
/*     */   {
/* 272 */     this.workingSheet.addMergedRegion(getCellRange());
/* 273 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T style(HSSFCellStyle style)
/*     */   {
/* 285 */     CellEditor cellEditor = newCellEditor();
/* 286 */     cellEditor.style(style);
/* 287 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T hidden(boolean hidden)
/*     */   {
/* 299 */     CellEditor cellEditor = newCellEditor();
/* 300 */     cellEditor.hidden(hidden);
/* 301 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T indent(int indent)
/*     */   {
/* 312 */     CellEditor cellEditor = newCellEditor();
/* 313 */     cellEditor.indent(indent);
/* 314 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T lock(boolean locked)
/*     */   {
/* 326 */     CellEditor cellEditor = newCellEditor();
/* 327 */     cellEditor.lock(locked);
/* 328 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T rotate(int rotation)
/*     */   {
/* 340 */     CellEditor cellEditor = newCellEditor();
/* 341 */     cellEditor.rotate(rotation);
/* 342 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T width(int width)
/*     */   {
/* 354 */     CellEditor cellEditor = newTopCellEditor();
/* 355 */     cellEditor.width(width);
/* 356 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T addWidth(int width)
/*     */   {
/* 368 */     CellEditor cellEditor = newTopCellEditor();
/* 369 */     cellEditor.addWidth(width);
/* 370 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T height(float height)
/*     */   {
/* 382 */     CellEditor cellEditor = newLeftCellEditor();
/* 383 */     cellEditor.height(height);
/* 384 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T addHeight(float height)
/*     */   {
/* 396 */     CellEditor cellEditor = newLeftCellEditor();
/* 397 */     cellEditor.addHeight(height);
/* 398 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T dataFormat(String format)
/*     */   {
/* 410 */     CellEditor cellEditor = newLeftCellEditor();
/* 411 */     cellEditor.dataFormat(format);
/* 412 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public Object[] value()
/*     */   {
/* 421 */     CellEditor cellEditor = newCellEditor();
/* 422 */     if (cellEditor.getWorkingCell().size() == 1) {
/* 423 */       return new Object[] { cellEditor.value() };
/*     */     }
/* 425 */     return (Object[])cellEditor.value();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 434 */     CellEditor cellEditor = newCellEditor();
/* 435 */     return cellEditor.toString();
/*     */   }
/*     */ 
/*     */   public T bold()
/*     */   {
/* 445 */     CellEditor cellEditor = newCellEditor();
/* 446 */     cellEditor.bold();
/* 447 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T color(Color color)
/*     */   {
/* 458 */     CellEditor cellEditor = newCellEditor();
/* 459 */     cellEditor.color(color);
/* 460 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T italic()
/*     */   {
/* 470 */     CellEditor cellEditor = newCellEditor();
/* 471 */     cellEditor.italic();
/* 472 */     return (T) this;
/*     */   }
/*     */ 
/*     */   public T addValidationData(String[] explicitListValues)
/*     */   {
/* 482 */     CellEditor cellEditor = newCellEditor();
/* 483 */     CellRangeAddress cellRange = getCellRange();
/* 484 */     CellRangeAddressList regions = new CellRangeAddressList(cellRange.getFirstRow(), cellRange.getLastRow(), cellRange.getFirstColumn(), cellRange.getLastColumn());
/* 485 */     cellEditor.addValidationData(regions, explicitListValues);
/* 486 */     return (T) this;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.AbstractRegionEditor
 * JD-Core Version:    0.6.2
 */