/*    */ package com.hotent.core.table.colmap;
/*    */ 
/*    */ import com.hotent.core.table.ColumnModel;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.springframework.jdbc.core.RowMapper;
/*    */ 
/*    */ public class OracleColumnMap
/*    */   implements RowMapper<ColumnModel>
/*    */ {
/*    */   public ColumnModel mapRow(ResultSet rs, int row)
/*    */     throws SQLException
/*    */   {
/* 20 */     ColumnModel column = new ColumnModel();
/* 21 */     String name = rs.getString("NAME");
/* 22 */     String typeName = rs.getString("TYPENAME");
/* 23 */     int length = rs.getInt("LENGTH");
/* 24 */     int precision = rs.getInt("PRECISION");
/* 25 */     int scale = rs.getInt("SCALE");
/* 26 */     boolean isNull = rs.getString("NULLABLE").equals("Y");
/* 27 */     String comments = rs.getString("DESCRIPTION");
/* 28 */     String tableName = rs.getString("TABLE_NAME");
/* 29 */     int isPK = rs.getInt("IS_PK");
/*    */ 
/* 31 */     column.setName(name);
/* 32 */     column.setComment(comments);
/* 33 */     column.setIsNull(isNull);
/* 34 */     column.setTableName(tableName);
/* 35 */     column.setIsPk(isPK == 1);
/* 36 */     setType(typeName, length, precision, scale, column);
/* 37 */     return column;
/*    */   }
/*    */ 
/*    */   private void setType(String dbtype, int length, int precision, int scale, ColumnModel column)
/*    */   {
/* 50 */     if (dbtype.indexOf("CHAR") > -1) {
/* 51 */       column.setColumnType("varchar");
/* 52 */       column.setCharLen(length);
/* 53 */       return;
/*    */     }
/* 55 */     if (dbtype.equals("NUMBER")) {
/* 56 */       column.setColumnType("number");
/* 57 */       column.setIntLen(precision - scale);
/* 58 */       column.setDecimalLen(scale);
/* 59 */       int i = precision - scale;
/* 60 */       if ((i == 0) && (scale == 0)) {
/* 61 */         column.setIntLen(38);
/*    */       }
/* 63 */       return;
/*    */     }
/* 65 */     if (dbtype.equals("INTEGER")) {
/* 66 */       column.setColumnType("number");
/* 67 */       column.setIntLen(10);
/* 68 */       column.setDecimalLen(0);
/*    */ 
/* 70 */       return;
/*    */     }
/* 72 */     if ((dbtype.equals("DATE")) || (dbtype.indexOf("TIMESTAMP") != -1)) {
/* 73 */       column.setColumnType("date");
/* 74 */       return;
/*    */     }
/*    */ 
/* 77 */     if (dbtype.equals("CLOB")) {
/* 78 */       column.setColumnType("clob");
/* 79 */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.colmap.OracleColumnMap
 * JD-Core Version:    0.6.2
 */