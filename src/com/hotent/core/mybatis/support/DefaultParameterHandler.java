/*    */ package com.hotent.core.mybatis.support;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ import org.apache.ibatis.executor.ErrorContext;
/*    */ import org.apache.ibatis.executor.ExecutorException;
/*    */ import org.apache.ibatis.executor.parameter.ParameterHandler;
/*    */ import org.apache.ibatis.mapping.BoundSql;
/*    */ import org.apache.ibatis.mapping.MappedStatement;
/*    */ import org.apache.ibatis.mapping.ParameterMap;
/*    */ import org.apache.ibatis.mapping.ParameterMapping;
/*    */ import org.apache.ibatis.mapping.ParameterMode;
/*    */ import org.apache.ibatis.reflection.MetaObject;
/*    */ import org.apache.ibatis.session.Configuration;
/*    */ import org.apache.ibatis.type.JdbcType;
/*    */ import org.apache.ibatis.type.TypeHandler;
/*    */ import org.apache.ibatis.type.TypeHandlerRegistry;
/*    */ 
/*    */ public class DefaultParameterHandler
/*    */   implements ParameterHandler
/*    */ {
/*    */   private final TypeHandlerRegistry typeHandlerRegistry;
/*    */   private final MappedStatement mappedStatement;
/*    */   private final Object parameterObject;
/*    */   private BoundSql boundSql;
/*    */   private Configuration configuration;
/*    */ 
/*    */   public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql)
/*    */   {
/* 46 */     this.mappedStatement = mappedStatement;
/* 47 */     this.configuration = mappedStatement.getConfiguration();
/* 48 */     this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
/* 49 */     this.parameterObject = parameterObject;
/* 50 */     this.boundSql = boundSql;
/*    */   }
/*    */ 
/*    */   public Object getParameterObject() {
/* 54 */     return this.parameterObject;
/*    */   }
/*    */ 
/*    */   public void setParameters(PreparedStatement ps) throws SQLException {
/* 58 */     ErrorContext.instance().activity("setting parameters").object(this.mappedStatement.getParameterMap().getId());
/* 59 */     List parameterMappings = this.boundSql.getParameterMappings();
/* 60 */     if (parameterMappings != null) {
/* 61 */       MetaObject metaObject = this.parameterObject == null ? null : this.configuration.newMetaObject(this.parameterObject);
/* 62 */       for (int i = 0; i < parameterMappings.size(); i++) {
/* 63 */         ParameterMapping parameterMapping = (ParameterMapping)parameterMappings.get(i);
/* 64 */         if (parameterMapping.getMode() != ParameterMode.OUT)
/*    */         {
/* 66 */           String propertyName = parameterMapping.getProperty();
/*    */           Object value;
/* 67 */           if (this.boundSql.hasAdditionalParameter(propertyName)) {
/* 68 */             value = this.boundSql.getAdditionalParameter(propertyName);
/*    */           }
/*    */           else
/*    */           {
/* 69 */             if (this.parameterObject == null) {
/* 70 */               value = null;
/*    */             }
/*    */             else
/*    */             {
/* 71 */               if (this.typeHandlerRegistry.hasTypeHandler(this.parameterObject.getClass()))
/* 72 */                 value = this.parameterObject;
/*    */               else
/* 74 */                 value = metaObject == null ? null : metaObject.getValue(propertyName); 
/*    */             }
/*    */           }
/* 76 */           TypeHandler typeHandler = parameterMapping.getTypeHandler();
/* 77 */           if (typeHandler == null) {
/* 78 */             throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + this.mappedStatement.getId());
/*    */           }
/* 80 */           JdbcType jdbcType = parameterMapping.getJdbcType();
/* 81 */           if ((value == null) && (jdbcType == null)) jdbcType = this.configuration.getJdbcTypeForNull();
/* 82 */           typeHandler.setParameter(ps, i + 1, value, jdbcType);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.mybatis.support.DefaultParameterHandler
 * JD-Core Version:    0.6.2
 */