/*    */ package com.hotent.core.table.colmap;
/*    */ 
/*    */ import com.hotent.core.table.ColumnModel;
/*    */ import com.hotent.core.util.StringUtil;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.springframework.jdbc.core.RowMapper;
/*    */ 
/*    */ public class DB2ColumnMap
/*    */   implements RowMapper<ColumnModel>
/*    */ {
/*    */   public ColumnModel mapRow(ResultSet rs, int rowNum)
/*    */     throws SQLException
/*    */   {
/* 19 */     ColumnModel column = new ColumnModel();
/* 20 */     String tabName = rs.getString("TAB_NAME");
/* 21 */     String colName = rs.getString("COL_NAME");
/* 22 */     String colType = rs.getString("COL_TYPE");
/* 23 */     String colComment = rs.getString("COL_COMMENT");
/* 24 */     String nullable = rs.getString("IS_NULLABLE");
/* 25 */     String length = rs.getString("LENGTH");
/* 26 */     String scale = rs.getString("SCALE");
/* 27 */     String keySeq = rs.getString("KEYSEQ");
/* 28 */     int iLength = string2Int(length, 0);
/* 29 */     int iPrecision = iLength;
/* 30 */     int iScale = string2Int(scale, 0);
/* 31 */     int iKeySeq = string2Int(keySeq, 0);
/*    */ 
/* 33 */     column.setTableName(tabName);
/* 34 */     column.setName(colName);
/* 35 */     column.setComment(colComment);
/*    */ 
/* 37 */     column.setIsNull("Y".equalsIgnoreCase(nullable));
/*    */ 
/* 39 */     column.setIsPk(iKeySeq > 0);
/*    */ 
/* 41 */     setType(colType, iLength, iPrecision, iScale, column);
/* 42 */     return column;
/*    */   }
/*    */ 
/*    */   private int string2Int(String str, int def)
/*    */   {
/* 52 */     int retval = def;
/* 53 */     if (StringUtil.isNotEmpty(str)) {
/*    */       try {
/* 55 */         retval = Integer.parseInt(str);
/*    */       } catch (Exception e) {
/* 57 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 60 */     return retval;
/*    */   }
/*    */ 
/*    */   private void setType(String type, int length, int precision, int scale, ColumnModel columnModel)
/*    */   {
/* 73 */     String dbtype = type.toLowerCase();
/* 74 */     if (dbtype.endsWith("bigint")) {
/* 75 */       columnModel.setColumnType("number");
/* 76 */       columnModel.setIntLen(19);
/* 77 */       columnModel.setDecimalLen(0);
/* 78 */     } else if (dbtype.endsWith("blob")) {
/* 79 */       columnModel.setColumnType("clob");
/* 80 */     } else if (dbtype.endsWith("character")) {
/* 81 */       columnModel.setColumnType("varchar");
/* 82 */       columnModel.setCharLen(length);
/* 83 */       columnModel.setDecimalLen(0);
/* 84 */     } else if (dbtype.endsWith("clob")) {
/* 85 */       columnModel.setColumnType("clob");
/* 86 */     } else if (dbtype.endsWith("date")) {
/* 87 */       columnModel.setColumnType("date");
/* 88 */     } else if (dbtype.endsWith("dbclob")) {
/* 89 */       columnModel.setColumnType("clob");
/* 90 */     } else if (dbtype.endsWith("decimal")) {
/* 91 */       columnModel.setColumnType("number");
/* 92 */       columnModel.setIntLen(precision - scale);
/* 93 */       columnModel.setDecimalLen(scale);
/* 94 */     } else if (dbtype.endsWith("double")) {
/* 95 */       columnModel.setColumnType("number");
/* 96 */       columnModel.setIntLen(precision - scale);
/* 97 */       columnModel.setDecimalLen(scale);
/* 98 */     } else if (dbtype.endsWith("graphic")) {
/* 99 */       columnModel.setColumnType("clob");
/* 100 */     } else if (dbtype.endsWith("integer")) {
/* 101 */       columnModel.setColumnType("number");
/* 102 */       columnModel.setIntLen(10);
/* 103 */       columnModel.setDecimalLen(0);
/* 104 */     } else if (dbtype.endsWith("long varchar")) {
/* 105 */       columnModel.setColumnType("varchar");
/* 106 */       columnModel.setCharLen(length);
/* 107 */     } else if (dbtype.endsWith("long vargraphic")) {
/* 108 */       columnModel.setColumnType("clob");
/* 109 */     } else if (dbtype.endsWith("real")) {
/* 110 */       columnModel.setColumnType("number");
/* 111 */       columnModel.setIntLen(length);
/* 112 */       columnModel.setDecimalLen(scale);
/* 113 */     } else if (dbtype.endsWith("smallint")) {
/* 114 */       columnModel.setColumnType("number");
/* 115 */       columnModel.setIntLen(5);
/* 116 */       columnModel.setDecimalLen(0);
/* 117 */     } else if (dbtype.endsWith("time")) {
/* 118 */       columnModel.setColumnType("date");
/* 119 */     } else if (dbtype.endsWith("timestamp")) {
/* 120 */       columnModel.setColumnType("date");
/* 121 */     } else if (dbtype.endsWith("varchar")) {
/* 122 */       columnModel.setColumnType("varchar");
/* 123 */       columnModel.setCharLen(length);
/* 124 */     } else if (dbtype.endsWith("vargraphic")) {
/* 125 */       columnModel.setColumnType("clob");
/* 126 */     } else if (dbtype.endsWith("xml")) {
/* 127 */       columnModel.setColumnType("clob");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.colmap.DB2ColumnMap
 * JD-Core Version:    0.6.2
 */