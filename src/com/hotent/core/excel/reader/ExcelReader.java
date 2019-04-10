/*     */ package com.hotent.core.excel.reader;
/*     */ 
/*     */ import com.hotent.core.excel.util.ExcelUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ExcelReader
/*     */ {
/*     */   private ExcelReaderConfig excelReaderConfig;
/*  32 */   private BufferedReader reader = null;
/*     */ 
/*  36 */   private InputStream is = null;
/*     */   private int currSheet;
/*     */   private int currPosittion;
/*     */   private int numOfSheets;
/*  52 */   private HSSFWorkbook workbook = null;
/*     */ 
/*     */   private void initExcelReader(InputStream inputFile)
/*     */     throws IOException, Exception
/*     */   {
/*  64 */     if (inputFile == null) {
/*  65 */       throw new IOException("文件输入流为空");
/*     */     }
/*  67 */     this.currPosittion = 0;
/*     */ 
/*  69 */     this.currSheet = 0;
/*     */ 
/*  72 */     this.is = inputFile;
/*     */ 
/*  74 */     this.workbook = new HSSFWorkbook(this.is);
/*     */ 
/*  76 */     this.numOfSheets = this.workbook.getNumberOfSheets();
/*     */   }
/*     */ 
/*     */   private String[] readExcelTitle()
/*     */   {
/*  84 */     HSSFSheet sheet = getCurrSheet();
/*  85 */     HSSFRow row = sheet.getRow(0);
/*  86 */     if (row == null) {
/*  87 */       return null;
/*     */     }
/*  89 */     int colNum = row.getLastCellNum();
/*     */ 
/*  91 */     String[] title = new String[colNum];
/*  92 */     for (int i = 0; i < colNum; i++) {
/*  93 */       title[i] = ExcelUtil.getCellFormatValue(row.getCell(i));
/*     */     }
/*  95 */     return title;
/*     */   }
/*     */ 
/*     */   private List<DataEntity> readSheet()
/*     */   {
/* 107 */     HSSFSheet sheet = getCurrSheet();
/* 108 */     int lastRowNum = sheet.getLastRowNum();
/*     */ 
/* 110 */     List dataEntityList = new ArrayList();
/* 111 */     for (int i = 1; i <= lastRowNum; i++) {
/* 112 */       DataEntity dataEntity = new DataEntity();
/*     */ 
/* 114 */       List fieldEntityList = getLine(sheet, i);
/* 115 */       if (fieldEntityList != null) {
/* 116 */         FieldEntity fieldEntity = getFieldEntityKey(fieldEntityList);
/* 117 */         dataEntity.setPkName(fieldEntity.getName());
/* 118 */         dataEntity.setPkVal(fieldEntity.getValue());
/* 119 */         dataEntity.setFieldEntityList(fieldEntityList);
/*     */       }
/*     */ 
/* 122 */       dataEntityList.add(dataEntity);
/*     */     }
/* 124 */     return dataEntityList;
/*     */   }
/*     */ 
/*     */   private FieldEntity getFieldEntityKey(List<FieldEntity> fieldEntityList) {
/* 128 */     for (FieldEntity fieldEntity : fieldEntityList) {
/* 129 */       if (fieldEntity.getIsKey().shortValue() == FieldEntity.IS_KEY.shortValue())
/* 130 */         return fieldEntity;
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */   private List<FieldEntity> getLine(HSSFSheet sheet, int row)
/*     */   {
/* 146 */     HSSFRow rowline = sheet.getRow(row);
/* 147 */     if (rowline == null) {
/* 148 */       return null;
/*     */     }
/*     */ 
/* 151 */     int filledColumns = rowline.getLastCellNum();
/* 152 */     HSSFCell cell = null;
/*     */ 
/* 154 */     int colStart = this.excelReaderConfig.getColStartPosittion();
/* 155 */     List list = new ArrayList();
/*     */ 
/* 157 */     int colEnd = this.excelReaderConfig.getColumns().length;
/*     */ 
/* 159 */     for (int i = colStart; i < colEnd; i++)
/*     */     {
/* 161 */       cell = rowline.getCell(i);
/* 162 */       String column = this.excelReaderConfig.getColumns()[(i - colStart)];
/* 163 */       String cellValue = ExcelUtil.getCellFormatValue(cell);
/* 164 */       FieldEntity fieldEntity = new FieldEntity();
/* 165 */       fieldEntity.setName(column);
/* 166 */       fieldEntity.setValue(cellValue);
/* 167 */       fieldEntity.setIsKey(i == colStart ? FieldEntity.IS_KEY : FieldEntity.NOT_KEY);
/* 168 */       list.add(fieldEntity);
/*     */     }
/*     */ 
/* 171 */     return list;
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/* 181 */     if (this.is != null) {
/*     */       try {
/* 183 */         this.is.close();
/*     */       } catch (IOException e) {
/* 185 */         this.is = null;
/* 186 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 190 */     if (this.reader != null)
/*     */       try {
/* 192 */         this.reader.close();
/*     */       } catch (IOException e) {
/* 194 */         this.reader = null;
/* 195 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private HSSFSheet getCurrSheet()
/*     */   {
/* 201 */     return this.workbook.getSheetAt(this.currSheet);
/*     */   }
/*     */ 
/*     */   public ExcelReaderConfig getExcelReaderConfig()
/*     */   {
/* 206 */     return this.excelReaderConfig;
/*     */   }
/*     */ 
/*     */   public void setExcelReaderConfig(ExcelReaderConfig excelReaderConfig) {
/* 210 */     this.excelReaderConfig = excelReaderConfig;
/*     */   }
/*     */ 
/*     */   public int getNumOfSheets() {
/* 214 */     return this.numOfSheets;
/*     */   }
/*     */ 
/*     */   public void setNumOfSheets(int numOfSheets) {
/* 218 */     this.numOfSheets = numOfSheets;
/*     */   }
/*     */ 
/*     */   public void setCurrSheet(int currSheet) {
/* 222 */     this.currSheet = currSheet;
/*     */   }
/*     */ 
/*     */   public int getCurrPosittion() {
/* 226 */     return this.currPosittion;
/*     */   }
/*     */ 
/*     */   public void setCurrPosittion(int currPosittion) {
/* 230 */     this.currPosittion = currPosittion;
/*     */   }
/*     */ 
/*     */   private TableEntity getTableEntity(ExcelReaderConfig config, Short isMain)
/*     */   {
/* 241 */     HSSFSheet sheet = getCurrSheet();
/* 242 */     String[] columns = readExcelTitle();
/* 243 */     if (columns == null)
/* 244 */       return null;
/* 245 */     config.setColumns(columns);
/* 246 */     setExcelReaderConfig(config);
/*     */ 
/* 248 */     List dataEntityList = readSheet();
/*     */ 
/* 250 */     TableEntity tableEntity = new TableEntity();
/* 251 */     tableEntity.setName(sheet.getSheetName());
/* 252 */     tableEntity.setIsMain(isMain);
/* 253 */     tableEntity.setDataEntityList(dataEntityList);
/* 254 */     return tableEntity;
/*     */   }
/*     */ 
/*     */   public TableEntity readFile(InputStream input)
/*     */     throws Exception
/*     */   {
/* 266 */     initExcelReader(input);
/* 267 */     ExcelReaderConfig config = new ExcelReaderConfig();
/* 268 */     TableEntity tableEntity = getTableEntity(config, TableEntity.IS_MAIN);
/*     */ 
/* 270 */     int numOfSheets = getNumOfSheets();
/* 271 */     if (numOfSheets > 0) {
/* 272 */       List subList = new ArrayList();
/* 273 */       for (int i = 1; i < numOfSheets; i++) {
/* 274 */         setCurrSheet(i);
/* 275 */         TableEntity table = getTableEntity(config, TableEntity.NOT_MAIN);
/* 276 */         subList.add(table);
/*     */       }
/* 278 */       if (subList.size() > 0) {
/* 279 */         tableEntity.setSubTableEntityList(subList);
/*     */       }
/*     */     }
/* 282 */     close();
/* 283 */     return tableEntity;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 288 */     ExcelReader excel = new ExcelReader();
/*     */ 
/* 290 */     File file = new File("d:\\test2.xls");
/* 291 */     InputStream input = new FileInputStream(file);
/*     */ 
/* 293 */     TableEntity excelEntity = excel.readFile(input);
/* 294 */     Logger logger = LoggerFactory.getLogger(ExcelReader.class);
/*     */ 
/* 296 */     logger.info(excelEntity.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.reader.ExcelReader
 * JD-Core Version:    0.6.2
 */