/*     */ package com.hotent.core.excel.editor;
/*     */ 
/*     */ import com.hotent.core.excel.ExcelContext;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.ss.util.CellRangeAddress;
/*     */ 
/*     */ public class RowEditor extends AbstractRegionEditor<RowEditor>
/*     */ {
/*     */   private HSSFRow row;
/*  17 */   private int startCol = 0;
/*     */ 
/*     */   public RowEditor(int row, int startCol, ExcelContext context) {
/*  20 */     super(context);
/*  21 */     this.row = getRow(row);
/*  22 */     this.startCol = startCol;
/*     */   }
/*     */ 
/*     */   public RowEditor(int row, ExcelContext context) {
/*  26 */     this(row, 0, context);
/*     */   }
/*     */ 
/*     */   public RowEditor value(Object[] rowData)
/*     */   {
/*  39 */     value(rowData, this.startCol);
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */   public RowEditor value(Object[] rowData, int startCol)
/*     */   {
/*  54 */     if (startCol < 0) {
/*  55 */       startCol = 0;
/*     */     }
/*  57 */     insertData(rowData, this.row, startCol, true);
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */   public RowEditor insert(Object[] rowData)
/*     */   {
/*  70 */     return insert(rowData, this.startCol);
/*     */   }
/*     */ 
/*     */   public RowEditor insert(Object[] rowData, int startCol)
/*     */   {
/*  84 */     if (startCol < 0) {
/*  85 */       startCol = 0;
/*     */     }
/*  87 */     insertData(rowData, this.row, startCol, false);
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */   public RowEditor append(Object[] rowData)
/*     */   {
/*  98 */     insertData(rowData, this.row, this.row.getLastCellNum(), true);
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */   public RowEditor height(float h)
/*     */   {
/* 111 */     this.row.setHeightInPoints(h);
/* 112 */     return this;
/*     */   }
/*     */ 
/*     */   public CellEditor cell(int col, int[] cols)
/*     */   {
/* 123 */     CellEditor cellEditor = new CellEditor(this.row.getRowNum(), col, this.ctx);
/* 124 */     for (int c : cols) {
/* 125 */       cellEditor.add(this.row.getRowNum(), c);
/*     */     }
/* 127 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   public HSSFRow toHSSFRow()
/*     */   {
/* 136 */     return this.row;
/*     */   }
/*     */ 
/*     */   public RowEditor width(int[] widths)
/*     */   {
/* 146 */     CellEditor cellEditor = newTopCellEditor();
/* 147 */     cellEditor.width(widths);
/* 148 */     return this;
/*     */   }
/*     */ 
/*     */   private void insertData(Object[] rowData, HSSFRow row, int startCol, boolean overwrite)
/*     */   {
/* 166 */     if (!overwrite) {
/* 167 */       this.workingSheet.shiftRows(row.getRowNum(), this.workingSheet.getLastRowNum(), 1, true, false);
/*     */     }
/*     */ 
/* 170 */     short i = 0;
/* 171 */     for (Object obj : rowData) {
/* 172 */       CellEditor cellEditor = new CellEditor(row.getRowNum(), startCol + i, this.ctx);
/*     */ 
/* 174 */       cellEditor.value(obj);
/* 175 */       i = (short)(i + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected CellEditor newCellEditor()
/*     */   {
/* 181 */     CellEditor cellEditor = new CellEditor(this.ctx);
/* 182 */     short minColIx = 0;
/* 183 */     short maxColIx = 0;
/* 184 */     minColIx = (short)this.startCol;
/* 185 */     maxColIx = this.row.getLastCellNum();
/* 186 */     for (int i = minColIx; i < maxColIx; i++) {
/* 187 */       cellEditor.add(this.row.getRowNum(), i);
/*     */     }
/* 189 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellEditor newBottomCellEditor()
/*     */   {
/* 194 */     return newCellEditor();
/*     */   }
/*     */ 
/*     */   protected CellEditor newLeftCellEditor()
/*     */   {
/* 199 */     CellEditor cellEditor = new CellEditor(this.ctx);
/* 200 */     cellEditor.add(this.row.getRowNum(), this.startCol);
/* 201 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellEditor newRightCellEditor()
/*     */   {
/* 206 */     CellEditor cellEditor = new CellEditor(this.ctx);
/* 207 */     cellEditor.add(this.row.getRowNum(), this.row.getLastCellNum());
/* 208 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellEditor newTopCellEditor()
/*     */   {
/* 213 */     return newCellEditor();
/*     */   }
/*     */ 
/*     */   protected CellRangeAddress getCellRange()
/*     */   {
/* 218 */     return new CellRangeAddress(this.row.getRowNum(), this.row.getRowNum(), this.startCol, this.row.getLastCellNum() - 1);
/*     */   }
/*     */ 
/*     */   protected HSSFRow getHSSFRow()
/*     */   {
/* 223 */     return this.row;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.RowEditor
 * JD-Core Version:    0.6.2
 */