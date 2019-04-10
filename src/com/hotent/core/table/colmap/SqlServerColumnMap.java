/*     */ package com.hotent.core.table.colmap;
/*     */ 
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class SqlServerColumnMap
/*     */   implements RowMapper<ColumnModel>
/*     */ {
/*     */   public ColumnModel mapRow(ResultSet rs, int row)
/*     */     throws SQLException
/*     */   {
/*  21 */     ColumnModel column = new ColumnModel();
/*     */ 
/*  23 */     String name = rs.getString("NAME");
/*  24 */     String is_nullable = rs.getString("IS_NULLABLE");
/*  25 */     String data_type = rs.getString("TYPENAME");
/*  26 */     String length = rs.getString("LENGTH");
/*  27 */     String precisions = rs.getString("PRECISION");
/*  28 */     String scale = rs.getString("SCALE");
/*  29 */     String tableName = rs.getString("TABLE_NAME");
/*  30 */     String comments = rs.getString("DESCRIPTION");
/*  31 */     int isPK = rs.getInt("IS_PK");
/*     */ 
/*  33 */     int iLength = StringUtil.isEmpty(length) ? 0 : Integer.parseInt(length);
/*  34 */     int iPrecisions = StringUtil.isEmpty(precisions) ? 0 : Integer.parseInt(precisions);
/*  35 */     int iScale = StringUtil.isEmpty(scale) ? 0 : Integer.parseInt(scale);
/*     */ 
/*  37 */     column.setName(name);
/*  38 */     boolean isNull = is_nullable.equals("1");
/*  39 */     column.setIsNull(isNull);
/*  40 */     column.setTableName(tableName);
/*  41 */     column.setComment(comments);
/*  42 */     column.setIsPk(isPK == 1);
/*  43 */     setType(data_type, iLength, iPrecisions, iScale, column);
/*     */ 
/*  45 */     return column;
/*     */   }
/*     */ 
/*     */   private void setType(String dbtype, int length, int precision, int scale, ColumnModel columnModel)
/*     */   {
/*  61 */     if (dbtype.equals("int"))
/*     */     {
/*  63 */       columnModel.setColumnType("number");
/*  64 */       columnModel.setIntLen(precision);
/*  65 */       columnModel.setDecimalLen(scale);
/*  66 */       return;
/*     */     }
/*     */ 
/*  69 */     if (dbtype.equals("bigint"))
/*     */     {
/*  71 */       columnModel.setColumnType("number");
/*  72 */       columnModel.setIntLen(precision);
/*  73 */       columnModel.setDecimalLen(scale);
/*  74 */       return;
/*     */     }
/*     */ 
/*  78 */     if (dbtype.equals("smallint"))
/*     */     {
/*  80 */       columnModel.setColumnType("number");
/*  81 */       columnModel.setIntLen(precision);
/*  82 */       columnModel.setDecimalLen(scale);
/*  83 */       return;
/*     */     }
/*     */ 
/*  86 */     if (dbtype.equals("tinyint"))
/*     */     {
/*  88 */       columnModel.setColumnType("number");
/*  89 */       columnModel.setIntLen(precision);
/*  90 */       columnModel.setDecimalLen(scale);
/*  91 */       return;
/*     */     }
/*     */ 
/*  94 */     if (dbtype.equals("bit"))
/*     */     {
/*  96 */       columnModel.setColumnType("number");
/*  97 */       return;
/*     */     }
/*     */ 
/* 100 */     if (dbtype.equals("decimal"))
/*     */     {
/* 102 */       columnModel.setColumnType("number");
/* 103 */       columnModel.setIntLen(precision);
/* 104 */       columnModel.setDecimalLen(scale);
/* 105 */       return;
/*     */     }
/*     */ 
/* 108 */     if (dbtype.equals("numeric"))
/*     */     {
/* 110 */       columnModel.setColumnType("number");
/* 111 */       columnModel.setIntLen(precision);
/* 112 */       columnModel.setDecimalLen(scale);
/* 113 */       return;
/*     */     }
/*     */ 
/* 116 */     if (dbtype.equals("real"))
/*     */     {
/* 118 */       columnModel.setColumnType("number");
/* 119 */       columnModel.setIntLen(precision);
/* 120 */       return;
/*     */     }
/*     */ 
/* 123 */     if (dbtype.equals("float"))
/*     */     {
/* 125 */       columnModel.setColumnType("number");
/* 126 */       columnModel.setIntLen(precision);
/* 127 */       return;
/*     */     }
/*     */ 
/* 131 */     if (dbtype.equals("varchar"))
/*     */     {
/* 133 */       columnModel.setColumnType("varchar");
/* 134 */       columnModel.setCharLen(length);
/*     */ 
/* 136 */       return;
/*     */     }
/*     */ 
/* 139 */     if (dbtype.equals("char"))
/*     */     {
/* 141 */       columnModel.setColumnType("varchar");
/* 142 */       columnModel.setCharLen(length);
/* 143 */       return;
/*     */     }
/*     */ 
/* 147 */     if (dbtype.equals("varchar"))
/*     */     {
/* 149 */       columnModel.setColumnType("varchar");
/* 150 */       columnModel.setCharLen(length);
/*     */ 
/* 152 */       return;
/*     */     }
/*     */ 
/* 155 */     if (dbtype.equals("nchar"))
/*     */     {
/* 157 */       columnModel.setColumnType("varchar");
/* 158 */       columnModel.setCharLen(length);
/* 159 */       return;
/*     */     }
/*     */ 
/* 162 */     if (dbtype.equals("nvarchar"))
/*     */     {
/* 164 */       columnModel.setColumnType("varchar");
/* 165 */       columnModel.setCharLen(length);
/*     */ 
/* 167 */       return;
/*     */     }
/*     */ 
/* 172 */     if (dbtype.startsWith("datetime"))
/*     */     {
/* 174 */       columnModel.setColumnType("date");
/*     */ 
/* 176 */       return;
/*     */     }
/*     */ 
/* 180 */     if (dbtype.endsWith("money"))
/*     */     {
/* 182 */       columnModel.setColumnType("number");
/* 183 */       columnModel.setIntLen(precision);
/* 184 */       columnModel.setDecimalLen(scale);
/* 185 */       return;
/*     */     }
/*     */ 
/* 188 */     if (dbtype.endsWith("smallmoney"))
/*     */     {
/* 190 */       columnModel.setColumnType("clob");
/* 191 */       columnModel.setIntLen(precision);
/* 192 */       columnModel.setDecimalLen(scale);
/* 193 */       return;
/*     */     }
/*     */ 
/* 196 */     if (dbtype.endsWith("text"))
/*     */     {
/* 198 */       columnModel.setColumnType("clob");
/* 199 */       columnModel.setCharLen(length);
/* 200 */       return;
/*     */     }
/*     */ 
/* 203 */     if (dbtype.endsWith("ntext"))
/*     */     {
/* 205 */       columnModel.setColumnType("clob");
/* 206 */       columnModel.setCharLen(length);
/* 207 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.colmap.SqlServerColumnMap
 * JD-Core Version:    0.6.2
 */