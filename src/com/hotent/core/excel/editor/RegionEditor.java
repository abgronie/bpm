/*     */ package com.hotent.core.excel.editor;
/*     */ 
/*     */ import com.hotent.core.excel.ExcelContext;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
/*     */ import org.apache.poi.hssf.usermodel.HSSFPatriarch;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.util.CellRangeAddress;
/*     */ 
/*     */ public class RegionEditor extends AbstractRegionEditor<RegionEditor>
/*     */ {
/*     */   private CellRangeAddress cellRange;
/*     */ 
/*     */   public RegionEditor(int beginRow, int beginCol, int endRow, int endCol, ExcelContext context)
/*     */   {
/*  31 */     super(context);
/*  32 */     this.cellRange = new CellRangeAddress(beginRow, endRow, beginCol, endCol);
/*     */   }
/*     */ 
/*     */   public RegionEditor(CellRangeAddress cellRange, ExcelContext context) {
/*  36 */     super(context);
/*  37 */     this.cellRange = cellRange;
/*     */   }
/*     */ 
/*     */   public RegionEditor image(String imgPath)
/*     */   {
/*  48 */     ByteArrayOutputStream byteArrayOut = null;
/*  49 */     BufferedImage bufferImg = null;
/*     */     try {
/*  51 */       if (imgPath.startsWith("http")) {
/*  52 */         URL url = new URL(imgPath);
/*  53 */         URLConnection conn = url.openConnection();
/*  54 */         bufferImg = ImageIO.read(conn.getInputStream());
/*     */       } else {
/*  56 */         bufferImg = ImageIO.read(new File(imgPath));
/*     */       }
/*  58 */       HSSFClientAnchor anchor = new HSSFClientAnchor(10, 10, 0, 0, (short)this.cellRange.getFirstColumn(), this.cellRange.getFirstRow(), (short)(this.cellRange.getLastColumn() + 1), this.cellRange.getLastRow() + 1);
/*     */ 
/*  63 */       anchor.setAnchorType(3);
/*  64 */       HSSFPatriarch patr = this.ctx.getHSSFPatriarch(this.workingSheet);
/*  65 */       byteArrayOut = new ByteArrayOutputStream();
/*  66 */       ImageIO.write(bufferImg, "JPEG", byteArrayOut);
/*  67 */       int imageIndex = this.workBook.addPicture(byteArrayOut.toByteArray(), 5);
/*     */ 
/*  69 */       patr.createPicture(anchor, imageIndex);
/*     */     } catch (IOException e) {
/*  71 */       e.printStackTrace();
/*     */     } catch (Exception e) {
/*  73 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  76 */         byteArrayOut.close();
/*     */       } catch (Exception e) {
/*     */       }
/*     */     }
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */   protected CellEditor newCellEditor()
/*     */   {
/*  90 */     CellEditor cellEditor = new CellEditor(this.ctx);
/*  91 */     for (int i = this.cellRange.getFirstRow(); i <= this.cellRange.getLastRow(); i++) {
/*  92 */       for (int j = this.cellRange.getFirstColumn(); j <= this.cellRange.getLastColumn(); 
/*  93 */         j++) {
/*  94 */         cellEditor.add(i, j);
/*     */       }
/*     */     }
/*  97 */     return cellEditor;
/*     */   }
/*     */ 
/*     */   protected CellEditor newBottomCellEditor()
/*     */   {
/* 103 */     CellEditor cellEditorBottom = new CellEditor(this.ctx);
/* 104 */     for (int i = this.cellRange.getFirstColumn(); i <= this.cellRange.getLastColumn(); i++) {
/* 105 */       cellEditorBottom.add(this.cellRange.getLastRow(), i);
/*     */     }
/* 107 */     return cellEditorBottom;
/*     */   }
/*     */ 
/*     */   protected CellEditor newLeftCellEditor()
/*     */   {
/* 113 */     CellEditor cellEditorLeft = new CellEditor(this.ctx);
/* 114 */     for (int i = this.cellRange.getFirstRow(); i <= this.cellRange.getLastRow(); i++) {
/* 115 */       cellEditorLeft.add(i, this.cellRange.getFirstColumn());
/*     */     }
/* 117 */     return cellEditorLeft;
/*     */   }
/*     */ 
/*     */   protected CellEditor newRightCellEditor()
/*     */   {
/* 123 */     CellEditor cellEditorRight = new CellEditor(this.ctx);
/* 124 */     for (int i = this.cellRange.getFirstRow(); i <= this.cellRange.getLastRow(); i++) {
/* 125 */       cellEditorRight.add(i, this.cellRange.getLastColumn());
/*     */     }
/* 127 */     return cellEditorRight;
/*     */   }
/*     */ 
/*     */   protected CellEditor newTopCellEditor()
/*     */   {
/* 133 */     CellEditor cellEditorTop = new CellEditor(this.ctx);
/* 134 */     for (int i = this.cellRange.getFirstColumn(); i <= this.cellRange.getLastColumn(); i++) {
/* 135 */       cellEditorTop.add(this.cellRange.getFirstRow(), i);
/*     */     }
/* 137 */     return cellEditorTop;
/*     */   }
/*     */ 
/*     */   protected CellRangeAddress getCellRange()
/*     */   {
/* 142 */     return this.cellRange;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.editor.RegionEditor
 * JD-Core Version:    0.6.2
 */