/*     */ package com.hotent.core.table.colmap;
/*     */ 
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.table.impl.MySqlTableMeta;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class MySqlColumnMap
/*     */   implements RowMapper<ColumnModel>
/*     */ {
/*     */   public ColumnModel mapRow(ResultSet rs, int row)
/*     */     throws SQLException
/*     */   {
/*  22 */     ColumnModel column = new ColumnModel();
/*     */ 
/*  24 */     String name = rs.getString("column_name");
/*  25 */     String is_nullable = rs.getString("is_nullable");
/*  26 */     String data_type = rs.getString("data_type");
/*  27 */     String length = rs.getString("length");
/*  28 */     String precisions = rs.getString("precisions");
/*  29 */     String scale = rs.getString("scale");
/*  30 */     String column_key = rs.getString("column_key");
/*  31 */     String column_comment = rs.getString("column_comment");
/*  32 */     String table_name = rs.getString("table_name");
/*  33 */     column_comment = MySqlTableMeta.getComments(column_comment, name);
/*     */     int iLength;
/*     */     try
/*     */     {
/*  36 */       iLength = StringUtil.isEmpty(length) ? 0 : Integer.parseInt(length);
/*     */     } catch (NumberFormatException e) {
/*  38 */       iLength = 0;
/*     */     }
/*  40 */     int iPrecisions = StringUtil.isEmpty(precisions) ? 0 : Integer.parseInt(precisions);
/*  41 */     int iScale = StringUtil.isEmpty(scale) ? 0 : Integer.parseInt(scale);
/*     */ 
/*  43 */     column.setName(name);
/*  44 */     column.setTableName(table_name);
/*  45 */     column.setComment(column_comment);
/*  46 */     if ((StringUtil.isNotEmpty(column_key)) && ("PRI".equals(column_key)))
/*  47 */       column.setIsPk(true);
/*  48 */     boolean isNull = is_nullable.equals("YES");
/*  49 */     column.setIsNull(isNull);
/*     */ 
/*  51 */     setType(data_type, iLength, iPrecisions, iScale, column);
/*     */ 
/*  53 */     return column;
/*     */   }
/*     */ 
/*     */   private void setType(String dbtype, int length, int precision, int scale, ColumnModel columnModel)
/*     */   {
/*  67 */     if (dbtype.equals("bigint"))
/*     */     {
/*  69 */       columnModel.setColumnType("number");
/*  70 */       columnModel.setIntLen(19);
/*  71 */       columnModel.setDecimalLen(0);
/*  72 */       return;
/*     */     }
/*     */ 
/*  75 */     if (dbtype.equals("int"))
/*     */     {
/*  77 */       columnModel.setColumnType("number");
/*  78 */       columnModel.setIntLen(10);
/*  79 */       columnModel.setDecimalLen(0);
/*  80 */       return;
/*     */     }
/*     */ 
/*  83 */     if (dbtype.equals("mediumint"))
/*     */     {
/*  85 */       columnModel.setColumnType("number");
/*  86 */       columnModel.setIntLen(7);
/*  87 */       columnModel.setDecimalLen(0);
/*  88 */       return;
/*     */     }
/*     */ 
/*  92 */     if (dbtype.equals("smallint"))
/*     */     {
/*  94 */       columnModel.setColumnType("number");
/*  95 */       columnModel.setIntLen(5);
/*  96 */       columnModel.setDecimalLen(0);
/*  97 */       return;
/*     */     }
/*     */ 
/* 100 */     if (dbtype.equals("tinyint"))
/*     */     {
/* 102 */       columnModel.setColumnType("number");
/* 103 */       columnModel.setIntLen(3);
/* 104 */       columnModel.setDecimalLen(0);
/* 105 */       return;
/*     */     }
/*     */ 
/* 108 */     if (dbtype.equals("decimal"))
/*     */     {
/* 110 */       columnModel.setColumnType("number");
/* 111 */       columnModel.setIntLen(precision - scale);
/* 112 */       columnModel.setDecimalLen(scale);
/* 113 */       return;
/*     */     }
/*     */ 
/* 116 */     if (dbtype.equals("double"))
/*     */     {
/* 118 */       columnModel.setColumnType("number");
/* 119 */       columnModel.setIntLen(18);
/* 120 */       columnModel.setDecimalLen(4);
/* 121 */       return;
/*     */     }
/*     */ 
/* 124 */     if (dbtype.equals("float"))
/*     */     {
/* 126 */       columnModel.setColumnType("number");
/* 127 */       columnModel.setIntLen(8);
/* 128 */       columnModel.setDecimalLen(4);
/* 129 */       return;
/*     */     }
/*     */ 
/* 133 */     if (dbtype.equals("varchar"))
/*     */     {
/* 135 */       columnModel.setColumnType("varchar");
/* 136 */       columnModel.setCharLen(length);
/*     */ 
/* 138 */       return;
/*     */     }
/*     */ 
/* 141 */     if (dbtype.equals("char"))
/*     */     {
/* 143 */       columnModel.setColumnType("varchar");
/* 144 */       columnModel.setCharLen(length);
/* 145 */       return;
/*     */     }
/*     */ 
/* 150 */     if (dbtype.startsWith("date"))
/*     */     {
/* 152 */       columnModel.setColumnType("date");
/*     */ 
/* 154 */       return;
/*     */     }
/*     */ 
/* 157 */     if (dbtype.startsWith("time"))
/*     */     {
/* 159 */       columnModel.setColumnType("date");
/*     */ 
/* 161 */       return;
/*     */     }
/*     */ 
/* 165 */     if (dbtype.endsWith("text"))
/*     */     {
/* 167 */       columnModel.setColumnType("clob");
/* 168 */       columnModel.setCharLen(65535);
/* 169 */       return;
/*     */     }
/* 171 */     if (dbtype.endsWith("blob"))
/*     */     {
/* 173 */       columnModel.setColumnType("clob");
/* 174 */       columnModel.setCharLen(65535);
/* 175 */       return;
/*     */     }
/* 177 */     if (dbtype.endsWith("clob"))
/*     */     {
/* 179 */       columnModel.setColumnType("clob");
/* 180 */       columnModel.setCharLen(65535);
/* 181 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.colmap.MySqlColumnMap
 * JD-Core Version:    0.6.2
 */