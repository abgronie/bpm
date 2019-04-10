/*     */ package com.hotent.core.excel.util;
/*     */ 
/*     */ import com.hotent.core.excel.Excel;
/*     */ import com.hotent.core.excel.editor.CellEditor;
/*     */ import com.hotent.core.excel.editor.IFontEditor;
/*     */ import com.hotent.core.excel.editor.SheetEditor;
/*     */ import com.hotent.core.excel.style.Align;
/*     */ import com.hotent.core.excel.style.BorderStyle;
/*     */ import com.hotent.core.excel.style.Color;
/*     */ import com.hotent.core.excel.style.font.BoldWeight;
/*     */ import com.hotent.core.excel.style.font.Font;
/*     */ import com.hotent.core.util.DateFormatUtil;
/*     */ import com.hotent.core.web.util.CookieUitl;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFDateUtil;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRichTextString;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ 
/*     */ public class ExcelUtil
/*     */ {
/*     */   public static int getLastRowNum(HSSFSheet sheet)
/*     */   {
/*  46 */     int lastRowNum = sheet.getLastRowNum();
/*  47 */     if (lastRowNum == 0) {
/*  48 */       lastRowNum = sheet.getPhysicalNumberOfRows() - 1;
/*     */     }
/*  50 */     return lastRowNum;
/*     */   }
/*     */ 
/*     */   public static int getFirstCellNum(HSSFRow row)
/*     */   {
/*  61 */     return row.getFirstCellNum();
/*     */   }
/*     */ 
/*     */   public static int getLastCellNum(HSSFRow row)
/*     */   {
/*  72 */     return row.getLastCellNum();
/*     */   }
/*     */ 
/*     */   public static HSSFRow getHSSFRow(HSSFSheet sheet, int row)
/*     */   {
/*  85 */     if (row < 0) {
/*  86 */       row = 0;
/*     */     }
/*  88 */     HSSFRow r = sheet.getRow(row);
/*  89 */     if (r == null) {
/*  90 */       r = sheet.createRow(row);
/*     */     }
/*  92 */     return r;
/*     */   }
/*     */ 
/*     */   public static HSSFCell getHSSFCell(HSSFSheet sheet, int row, int col)
/*     */   {
/* 107 */     HSSFRow r = getHSSFRow(sheet, row);
/* 108 */     return getHSSFCell(r, col);
/*     */   }
/*     */ 
/*     */   public static HSSFCell getHSSFCell(HSSFRow row, int col)
/*     */   {
/* 121 */     if (col < 0) {
/* 122 */       col = 0;
/*     */     }
/* 124 */     HSSFCell c = row.getCell(col);
/* 125 */     c = c == null ? row.createCell(col) : c;
/* 126 */     return c;
/*     */   }
/*     */ 
/*     */   public static HSSFSheet getHSSFSheet(HSSFWorkbook workbook, int index)
/*     */   {
/* 139 */     if (index < 0) {
/* 140 */       index = 0;
/*     */     }
/* 142 */     if (index > workbook.getNumberOfSheets() - 1) {
/* 143 */       workbook.createSheet();
/* 144 */       return workbook.getSheetAt(workbook.getNumberOfSheets() - 1);
/*     */     }
/* 146 */     return workbook.getSheetAt(index);
/*     */   }
/*     */ 
/*     */   public static String getCellFormatValue(HSSFCell cell)
/*     */   {
/* 157 */     if (cell == null)
/* 158 */       return "";
/* 159 */     String cellvalue = "";
/*     */ 
/* 161 */     switch (cell.getCellType())
/*     */     {
/*     */     case 1:
/* 164 */       cellvalue = cell.getRichStringCellValue().getString();
/* 165 */       break;
/*     */     case 0:
/*     */     case 2:
/* 171 */       if (HSSFDateUtil.isCellDateFormatted(cell))
/*     */       {
/* 173 */         Date date = cell.getDateCellValue();
/* 174 */         cellvalue = DateFormatUtil.format(date);
/*     */       }
/*     */       else
/*     */       {
/* 179 */         cell.setCellType(1);
/* 180 */         cellvalue = cell.getStringCellValue();
/*     */       }
/* 182 */       break;
/*     */     case 4:
/* 184 */       cellvalue = String.valueOf(cell.getBooleanCellValue());
/* 185 */       break;
/*     */     case 3:
/*     */     default:
/* 187 */       cellvalue = "";
/*     */     }
/* 189 */     return cellvalue;
/*     */   }
/*     */ 
/*     */   public static void downloadExcel(HSSFWorkbook workBook, String fileName, HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/* 202 */     response.setContentType("application/x-download");
/* 203 */     if (System.getProperty("file.encoding").equals("GBK"))
/* 204 */       response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls" + "\"");
/*     */     else {
/* 206 */       response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + ".xls" + "\"");
/*     */     }
/* 208 */     OutputStream os = null;
/*     */     try {
/* 210 */       os = response.getOutputStream();
/* 211 */       workBook.write(os);
/* 212 */       os.flush();
/* 213 */       os.close();
/*     */     } catch (Exception e) {
/* 215 */       e.printStackTrace();
/*     */     } finally {
/* 217 */       if (os != null)
/* 218 */         os.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void downloadExcel(HSSFWorkbook workBook, String fileName, HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/* 231 */     response.setContentType("application/x-download");
/* 232 */     if (System.getProperty("file.encoding").equals("GBK"))
/* 233 */       response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls" + "\"");
/*     */     else {
/* 235 */       response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + ".xls" + "\"");
/*     */     }
/* 237 */     OutputStream os = null;
/* 238 */     CookieUitl.addCookie("downloadExcel", "1", 3, request, response);
/*     */     try {
/* 240 */       os = response.getOutputStream();
/* 241 */       workBook.write(os);
/* 242 */       os.flush();
/* 243 */       os.close();
/*     */     } catch (Exception e) {
/* 245 */       e.printStackTrace();
/*     */     } finally {
/* 247 */       if (os != null)
/* 248 */         os.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static HSSFWorkbook exportExcel(String title, int rowHeight, Map<String, String> fieldMap, List data)
/*     */     throws Exception
/*     */   {
/* 264 */     int size = fieldMap.size();
/* 265 */     Excel excel = new Excel();
/*     */ 
/* 267 */     int titleCols = size;
/*     */ 
/* 269 */     if (titleCols == 0) {
/* 270 */       throw new Exception("请设置列！");
/*     */     }
/*     */ 
/* 274 */     excel.sheet().sheetName(title);
/*     */ 
/* 276 */     int i = 0;
/*     */ 
/* 278 */     for (String name : fieldMap.values())
/*     */     {
/* 280 */       excel.cell(0, i).value(name).align(Align.CENTER).bgColor(Color.GREY_25_PERCENT).fontHeightInPoint(14).width(12800).border(BorderStyle.THIN, Color.BLACK).font(new IFontEditor()
/*     */       {
/*     */         public void updateFont(Font font)
/*     */         {
/* 288 */           font.boldweight(BoldWeight.BOLD);
/* 289 */           font.color(Color.BLACK);
/*     */         }
/*     */       });
/* 292 */       i++;
/*     */     }
/*     */ 
/* 296 */     int rows = 1;
/* 297 */     for (Iterator i$ = data.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 298 */       Map rowObj = (Map)obj;
/* 299 */       int col = 0;
/* 300 */       for (String key : fieldMap.keySet()) {
/* 301 */         String val = rowObj.get(key) == null ? "" : rowObj.get(key).toString();
/* 302 */         excel.cell(rows, col).value(val).border(BorderStyle.MEDIUM, Color.BLACK).fontHeightInPoint(14).warpText(true).align(Align.LEFT);
/*     */ 
/* 307 */         col++;
/*     */       }
/* 309 */       rows++;
/*     */     }
/*     */ 
/* 312 */     return excel.getWorkBook();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.util.ExcelUtil
 * JD-Core Version:    0.6.2
 */