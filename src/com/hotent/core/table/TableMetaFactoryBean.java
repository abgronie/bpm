/*    */ package com.hotent.core.table;
/*    */ 
/*    */ import com.hotent.core.table.impl.TableMetaFactory;
/*    */ import org.springframework.beans.factory.FactoryBean;
/*    */ 
/*    */ public class TableMetaFactoryBean
/*    */   implements FactoryBean<BaseTableMeta>
/*    */ {
/*    */   private BaseTableMeta tableMeta;
/* 27 */   private String dbType = "mysql";
/*    */ 
/*    */   public BaseTableMeta getObject()
/*    */     throws Exception
/*    */   {
/* 33 */     this.tableMeta = TableMetaFactory.getMetaData("dataSource_Default");
/*    */ 
/* 35 */     return this.tableMeta;
/*    */   }
/*    */ 
/*    */   public void setDbType(String dbType)
/*    */   {
/* 45 */     this.dbType = dbType;
/*    */   }
/*    */ 
/*    */   public Class<?> getObjectType()
/*    */   {
/* 56 */     return BaseTableMeta.class;
/*    */   }
/*    */ 
/*    */   public boolean isSingleton()
/*    */   {
/* 62 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.TableMetaFactoryBean
 * JD-Core Version:    0.6.2
 */