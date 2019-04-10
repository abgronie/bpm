/*    */ package com.hotent.core.datahandler;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.context.ApplicationListener;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ 
/*    */ public class UpdateDataListener
/*    */   implements ApplicationListener<UpdDataEvent>
/*    */ {
/*    */ 
/*    */   @Resource(name="jdbcTemplate")
/*    */   JdbcTemplate jdbcTemplate;
/* 37 */   private Map<String, List<String>> sqlMap = new HashMap();
/*    */ 
/*    */   public void onApplicationEvent(UpdDataEvent event)
/*    */   {
/* 41 */     DataModel dataModel = (DataModel)event.getSource();
/* 42 */     String id = dataModel.getPk();
/* 43 */     String tableName = dataModel.getTableName().toLowerCase();
/* 44 */     List<String> sqlList = (List<String>)this.sqlMap.get(tableName);
/* 45 */     for (String sql : sqlList)
/* 46 */       this.jdbcTemplate.update(sql, new Object[] { id });
/*    */   }
/*    */ 
/*    */   public void setSqlMap(Map<String, List<String>> map)
/*    */   {
/* 55 */     for (String key : map.keySet())
/*    */     {
/* 57 */       this.sqlMap.put(key.toLowerCase(), map.get(key));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.datahandler.UpdateDataListener
 * JD-Core Version:    0.6.2
 */