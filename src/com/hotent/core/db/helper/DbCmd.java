/*    */ package com.hotent.core.db.helper;
/*    */ 
/*    */ import javax.sql.DataSource;
/*    */ import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
/*    */ import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/*    */ import org.springframework.jdbc.core.namedparam.SqlParameterSource;
/*    */ 
/*    */ public class DbCmd<T>
/*    */   implements JdbcCommand
/*    */ {
/*    */   private ObjectHelper<T> helper;
/*    */   private T obj;
/*    */   private OperatorType type;
/*    */ 
/*    */   public void setModel(T obj)
/*    */   {
/* 34 */     this.helper = new ObjectHelper();
/* 35 */     this.helper.setModel(obj);
/* 36 */     this.obj = obj;
/*    */   }
/*    */ 
/*    */   public void setOperatorType(OperatorType type) {
/* 40 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public void execute(DataSource source) throws Exception
/*    */   {
/* 45 */     String sql = "";
/* 46 */     if (this.type == OperatorType.Add)
/* 47 */       sql = this.helper.getAddSql();
/* 48 */     else if (this.type == OperatorType.Upd)
/* 49 */       sql = this.helper.getUpdSql();
/* 50 */     else if (this.type == OperatorType.Del) {
/* 51 */       sql = this.helper.getDelSql();
/*    */     }
/* 53 */     SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(this.obj);
/* 54 */     NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(source);
/* 55 */     template.update(sql, namedParameters);
/*    */   }
/*    */ 
/*    */   public static enum OperatorType
/*    */   {
/* 26 */     Add, Del, Upd, Get;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.helper.DbCmd
 * JD-Core Version:    0.6.2
 */