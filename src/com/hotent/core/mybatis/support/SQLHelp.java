/*    */ package com.hotent.core.mybatis.support;
/*    */ 
/*    */ import com.hotent.core.mybatis.Dialect;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import javax.sql.DataSource;
/*    */ import org.apache.ibatis.mapping.BoundSql;
/*    */ import org.apache.ibatis.mapping.Environment;
/*    */ import org.apache.ibatis.mapping.MappedStatement;
/*    */ import org.apache.ibatis.session.Configuration;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class SQLHelp
/*    */ {
/* 19 */   private static Logger logger = LoggerFactory.getLogger(SQLHelp.class);
/*    */ 
/*    */   public static int getCount(String sql, MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql, Dialect dialect)
/*    */     throws SQLException
/*    */   {
/* 35 */     String count_sql = dialect.getCountSql(sql);
/* 36 */     logger.debug("Total count SQL [{}] ", count_sql);
/* 37 */     logger.debug("Total count Parameters: {} ", parameterObject);
/*    */ 
/* 39 */     Connection connection = null;
/* 40 */     PreparedStatement countStmt = null;
/* 41 */     ResultSet rs = null;
/*    */     try {
/* 43 */       connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
/* 44 */       countStmt = connection.prepareStatement(count_sql);
/*    */ 
/* 46 */       DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
/* 47 */       handler.setParameters(countStmt);
/*    */ 
/* 49 */       rs = countStmt.executeQuery();
/* 50 */       int count = 0;
/* 51 */       if (rs.next()) {
/* 52 */         count = rs.getInt(1);
/*    */       }
/* 54 */       logger.debug("Total count: {}", Integer.valueOf(count));
/* 55 */       return count;
/*    */     } finally {
/*    */       try {
/* 58 */         if (rs != null)
/* 59 */           rs.close();
/*    */       }
/*    */       finally {
/*    */         try {
/* 63 */           if (countStmt != null)
/* 64 */             countStmt.close();
/*    */         }
/*    */         finally {
/* 67 */           if ((connection != null) && (!connection.isClosed()))
/* 68 */             connection.close();
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.support.SQLHelp
 * JD-Core Version:    0.6.2
 */