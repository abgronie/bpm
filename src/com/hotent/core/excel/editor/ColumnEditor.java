/*     */ package com.hotent.core.excel.editor;
/*     */ 
/*     */ import com.hotent.core.excel.ExcelContext;
/*     */ import com.hotent.core.excel.util.ExcelUtil;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.ss.util.CellRangeAddress;
/*     */ 
/*     */ public class ColumnEditor extends AbstractRegionEditor<ColumnEditor>
/*     */ {
/*  17 */   private int col = 0;
/*  18 */   private int startRow = 0;
/*     */ 
/*     */   public ColumnEditor(int col, int startRow, ExcelContext context) {
/*  21 */     super(context);
/*  22 */     this.col = col;
/*  23 */     this.startRow = startRow;
/*     */   }
/*     */ 
/*     */   public ColumnEditor(int col, ExcelContext context) {
/*  27 */     this(col, 0, context);
/*     */   }
/*     */ 
/*     */   public ColumnEditor value(Object[] rowData)
/*     */   {
/*  39 */     value(rowData, this.startRow);
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */   public ColumnEditor value(Object[] rowData, int startRow)
/*     */   {
/*  54 */     if (startRow < 0) {
/*  55 */       startRow = 0;
/*     */     }
/*  57 */     insertData(rowData, this.col, startRow);
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */   public ColumnEditor autoWidth()
/*     */   {
/*  65 */     this.workingSheet.autoSizeColumn((short)this.col, false);
/*  66 */     this.workingSheet.setColumnWidth(this.col, this.workingSheet.getColumnWidth(this.col) + 1000);
/*     */ 
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */   public ColumnEditor height(float[] heights)
/*     */   {
/*  78 */     CellEditor cellEditorLeft = newLeftCellEditor();
/*  79 */     cellEditorLeft.height(heights);
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor cell(int row, int[] rows)
/*     */   {
/*  91 */     CellEditor cellEditor = new CellEditor(row, this.col, this.ctx);
/*  92 */     for (int r : rows) {
/*  93 */       cellEditor.add(r, this.col);
/*     */     }
/*  95 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   private void insertData(Object[] rowData, int col, int startRow)
/*     */   {
/* 110 */     short i = 0;
/* 111 */     for (Object obj : rowData) {
/* 112 */       CellEditor cellEditor = new CellEditor(startRow + i, col, this.ctx);
/* 113 */       cellEditor.value(obj);
/* 114 */       i = (short)(i + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected CellEditor newBottomCellEditor()
/*     */   {
/* 120 */     int lastRowNum = ExcelUtil.getLastRowNum(this.workingSheet);
/* 121 */     CellEditor cellEditor = new CellEditor(this.ctx);
/* 122 */     cellEditor.add(lastRowNum, this.col);
/* 123 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellEditor newCellEditor()
/*     */   {
/* 128 */     CellEditor cellEditor = new CellEditor(this.ctx);
/* 129 */     int lastRowNum = ExcelUtil.getLastRowNum(this.workingSheet);
/* 130 */     int firstRowNum = this.startRow;
/* 131 */     for (int i = firstRowNum; i <= lastRowNum; i++) {
/* 132 */       HSSFRow row = getRow(i);
/* 133 */       cellEditor.add(row.getRowNum(), this.col);
/*     */     }
/* 135 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellEditor newLeftCellEditor()
/*     */   {
/* 140 */     return newCellEditor();
/*     */   }
/*     */ 
/*     */   protected CellEditor newRightCellEditor()
/*     */   {
/* 145 */     return newCellEditor();
/*     */   }
/*     */ 
/*     */   protected CellEditor newTopCellEditor()
/*     */   {
/* 150 */     int firstRowNum = this.startRow;
/* 151 */     CellEditor cellEditor = new CellEditor(this.ctx);
/* 152 */     cellEditor.add(firstRowNum, this.col);
/* 153 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellRangeAddress getCellRange()
/*     */   {
/* 158 */     int firstRowNum = this.startRow;
/* 159 */     int lastRowNum = ExcelUtil.getLastRowNum(this.workingSheet);
/* 160 */     return new CellRangeAddress(firstRowNum, lastRowNum, this.col, this.col);
/*     */   }
/*     */ 
/*     */   protected int getCol() {
/* 164 */     return this.col;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.ColumnEditor
 * JD-Core Version:    0.6.2
 */