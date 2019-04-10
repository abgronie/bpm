/*     */ package com.hotent.core.table.colmap;
/*     */ 
/*     */ import com.hotent.core.table.ColumnModel;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class H2ColumnMap
/*     */   implements RowMapper<ColumnModel>
/*     */ {
/*     */   public ColumnModel mapRow(ResultSet rs, int row)
/*     */     throws SQLException
/*     */   {
/*  21 */     ColumnModel column = new ColumnModel();
/*     */ 
/*  23 */     String name = rs.getString("COLUMN_NAME");
/*  24 */     String is_nullable = rs.getString("IS_NULLABLE");
/*  25 */     String data_type = rs.getString("TYPE_NAME");
/*  26 */     String length = rs.getString("LENGTH");
/*  27 */     String precisions = rs.getString("PRECISIONS");
/*  28 */     String scale = rs.getString("SCALE");
/*  29 */     String column_list = rs.getString("COLUMN_LIST");
/*  30 */     String column_comment = rs.getString("REMARKS");
/*  31 */     String table_name = rs.getString("TABLE_NAME");
/*     */     int iLength;
/*     */     try
/*     */     {
/*  34 */       iLength = StringUtil.isEmpty(length) ? 0 : Integer.parseInt(length);
/*     */     } catch (NumberFormatException e) {
/*  36 */       iLength = 0;
/*     */     }
/*  38 */     int iPrecisions = StringUtil.isEmpty(precisions) ? 0 : Integer.parseInt(precisions);
/*  39 */     int iScale = StringUtil.isEmpty(scale) ? 0 : Integer.parseInt(scale);
/*     */ 
/*  41 */     column.setName(name);
/*  42 */     column.setTableName(table_name);
/*  43 */     column.setComment(column_comment);
/*     */ 
/*  45 */     boolean isPkColumn = false;
/*  46 */     if (StringUtil.isNotEmpty(column_list)) {
/*  47 */       String[] pkColumns = column_list.split(",");
/*  48 */       for (String pkColumn : pkColumns) {
/*  49 */         if (name.trim().equalsIgnoreCase(pkColumn.trim())) {
/*  50 */           isPkColumn = true;
/*  51 */           break;
/*     */         }
/*     */       }
/*     */     }
/*  55 */     column.setIsPk(isPkColumn);
/*     */ 
/*  57 */     boolean isNull = is_nullable.equals("YES");
/*  58 */     column.setIsNull(isNull);
/*     */ 
/*  60 */     setType(data_type, iLength, iPrecisions, iScale, column);
/*     */ 
/*  62 */     return column;
/*     */   }
/*     */ 
/*     */   private void setType(String dbtype, int length, int precision, int scale, ColumnModel columnModel)
/*     */   {
/*  75 */     dbtype = dbtype.toUpperCase();
/*  76 */     if (dbtype.equals("BIGINT")) {
/*  77 */       columnModel.setColumnType("int");
/*  78 */       columnModel.setIntLen(19);
/*  79 */       columnModel.setDecimalLen(0);
/*  80 */       return;
/*  81 */     }if (dbtype.equals("INT8")) {
/*  82 */       columnModel.setColumnType("int");
/*  83 */       columnModel.setIntLen(19);
/*  84 */       columnModel.setDecimalLen(0);
/*  85 */       return;
/*  86 */     }if (dbtype.equals("INT")) {
/*  87 */       columnModel.setColumnType("int");
/*  88 */       columnModel.setIntLen(10);
/*  89 */       columnModel.setDecimalLen(0);
/*  90 */       return;
/*  91 */     }if (dbtype.equals("INTEGER")) {
/*  92 */       columnModel.setColumnType("int");
/*  93 */       columnModel.setIntLen(10);
/*  94 */       columnModel.setDecimalLen(0);
/*  95 */       return;
/*  96 */     }if (dbtype.equals("MEDIUMINT")) {
/*  97 */       columnModel.setColumnType("int");
/*  98 */       columnModel.setIntLen(7);
/*  99 */       columnModel.setDecimalLen(0);
/* 100 */       return;
/* 101 */     }if (dbtype.equals("INT4")) {
/* 102 */       columnModel.setColumnType("int");
/* 103 */       columnModel.setIntLen(5);
/* 104 */       columnModel.setDecimalLen(0);
/* 105 */       return;
/* 106 */     }if (dbtype.equals("SIGNED")) {
/* 107 */       columnModel.setColumnType("int");
/* 108 */       columnModel.setIntLen(3);
/* 109 */       columnModel.setDecimalLen(0);
/* 110 */       return;
/* 111 */     }if (dbtype.equals("TINYINT")) {
/* 112 */       columnModel.setColumnType("int");
/* 113 */       columnModel.setIntLen(2);
/* 114 */       columnModel.setDecimalLen(0);
/* 115 */       return;
/* 116 */     }if (dbtype.equals("SMALLINT")) {
/* 117 */       columnModel.setColumnType("int");
/* 118 */       columnModel.setIntLen(5);
/* 119 */       columnModel.setDecimalLen(0);
/* 120 */       return;
/* 121 */     }if (dbtype.equals("INT2")) {
/* 122 */       columnModel.setColumnType("int");
/* 123 */       columnModel.setIntLen(5);
/* 124 */       columnModel.setDecimalLen(0);
/* 125 */       return;
/* 126 */     }if (dbtype.equals("YEAR")) {
/* 127 */       columnModel.setColumnType("int");
/* 128 */       columnModel.setIntLen(5);
/* 129 */       columnModel.setDecimalLen(0);
/* 130 */       return;
/* 131 */     }if (dbtype.equals("IDENTITY")) {
/* 132 */       columnModel.setColumnType("int");
/* 133 */       columnModel.setIntLen(5);
/* 134 */       columnModel.setDecimalLen(0);
/* 135 */       return;
/* 136 */     }if (dbtype.equals("DECIMAL")) {
/* 137 */       columnModel.setColumnType("number");
/* 138 */       columnModel.setIntLen(precision - scale);
/* 139 */       columnModel.setDecimalLen(scale);
/* 140 */       return;
/* 141 */     }if (dbtype.equals("DOUBLE")) {
/* 142 */       columnModel.setColumnType("number");
/* 143 */       return;
/* 144 */     }if (dbtype.equals("FLOAT")) {
/* 145 */       columnModel.setColumnType("number");
/* 146 */       return;
/* 147 */     }if (dbtype.equals("FLOAT4")) {
/* 148 */       columnModel.setColumnType("number");
/* 149 */       return;
/* 150 */     }if (dbtype.equals("FLOAT8")) {
/* 151 */       columnModel.setColumnType("number");
/* 152 */       return;
/* 153 */     }if (dbtype.equals("REAL")) {
/* 154 */       columnModel.setColumnType("number");
/* 155 */       return;
/* 156 */     }if (dbtype.equals("TIME")) {
/* 157 */       columnModel.setColumnType("date");
/* 158 */       return;
/* 159 */     }if (dbtype.equals("DATE")) {
/* 160 */       columnModel.setColumnType("date");
/* 161 */       return;
/* 162 */     }if (dbtype.equals("DATETIME")) {
/* 163 */       columnModel.setColumnType("date");
/* 164 */       return;
/* 165 */     }if (dbtype.equals("SMALLDATETIME")) {
/* 166 */       columnModel.setColumnType("date");
/* 167 */       return;
/* 168 */     }if (dbtype.equals("TIMESTAMP")) {
/* 169 */       columnModel.setColumnType("date");
/* 170 */       return;
/* 171 */     }if (dbtype.equals("BINARY")) {
/* 172 */       columnModel.setColumnType("clob");
/* 173 */       return;
/* 174 */     }if (dbtype.equals("VARBINARY")) {
/* 175 */       columnModel.setColumnType("clob");
/* 176 */       return;
/* 177 */     }if (dbtype.equals("LONGVARBINARY")) {
/* 178 */       columnModel.setColumnType("clob");
/* 179 */       return;
/* 180 */     }if (dbtype.equals("RAW")) {
/* 181 */       columnModel.setColumnType("clob");
/* 182 */       return;
/* 183 */     }if (dbtype.equals("BYTEA")) {
/* 184 */       columnModel.setColumnType("clob");
/* 185 */       return;
/* 186 */     }if (dbtype.equals("TINYBLOB")) {
/* 187 */       columnModel.setColumnType("clob");
/* 188 */       return;
/* 189 */     }if (dbtype.equals("MEDIUMBLOB")) {
/* 190 */       columnModel.setColumnType("clob");
/* 191 */       return;
/* 192 */     }if (dbtype.equals("LONGBLOB")) {
/* 193 */       columnModel.setColumnType("clob");
/* 194 */       return;
/* 195 */     }if (dbtype.equals("IMAGE")) {
/* 196 */       columnModel.setColumnType("clob");
/* 197 */       return;
/* 198 */     }if (dbtype.equals("OID")) {
/* 199 */       columnModel.setColumnType("clob");
/* 200 */       return;
/* 201 */     }if (dbtype.equals("CLOB")) {
/* 202 */       columnModel.setColumnType("clob");
/* 203 */       return;
/* 204 */     }if (dbtype.equals("TINYTEXT")) {
/* 205 */       columnModel.setColumnType("clob");
/* 206 */       return;
/* 207 */     }if (dbtype.equals("TEXT")) {
/* 208 */       columnModel.setColumnType("clob");
/* 209 */       return;
/* 210 */     }if (dbtype.equals("MEDIUMTEXT")) {
/* 211 */       columnModel.setColumnType("clob");
/* 212 */       return;
/* 213 */     }if (dbtype.equals("LONGTEXT")) {
/* 214 */       columnModel.setColumnType("clob");
/* 215 */       return;
/* 216 */     }if (dbtype.equals("NTEXT")) {
/* 217 */       columnModel.setColumnType("clob");
/* 218 */       return;
/* 219 */     }if (dbtype.equals("NCLOB")) {
/* 220 */       columnModel.setColumnType("clob");
/* 221 */       return;
/*     */     }
/* 223 */     columnModel.setColumnType("varchar");
/* 224 */     columnModel.setCharLen(length);
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.colmap.H2ColumnMap
 * JD-Core Version:    0.6.2
 */