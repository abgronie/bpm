/*     */ package com.hotent.core.web.query.scan;
/*     */ 
/*     */ import com.hotent.core.annotion.query.Table;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import com.hotent.core.web.query.QueryFilter;
/*     */ import java.text.Collator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class TableEntity
/*     */ {
/*     */   private String tableName;
/*     */   private String var;
/*     */   private String displayTagId;
/*     */   private String pk;
/*     */   private String comment;
/*  53 */   private boolean isPrimary = true;
/*     */ 
/*  58 */   private String relation = "";
/*     */ 
/*  63 */   private String primaryTable = "";
/*     */ 
/*  68 */   private List<TableField> tableFieldList = new ArrayList();
/*     */ 
/*  73 */   private List<TableEntity> subTableList = new ArrayList();
/*     */ 
/* 120 */   private static ThreadLocal<Map<String, Object>> queryFilterLocal = new ThreadLocal();
/*     */ 
/*     */   public TableEntity()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TableEntity(String claName, Table table)
/*     */   {
/*  80 */     this.tableName = table.name();
/*  81 */     this.var = (StringUtils.isNotEmpty(table.var()) ? table.var() : StringUtil.makeFirstLetterLowerCase(claName));
/*     */ 
/*  83 */     this.displayTagId = (table.var() + "Item");
/*     */ 
/*  85 */     this.pk = table.pk();
/*  86 */     this.comment = table.comment();
/*  87 */     this.isPrimary = table.isPrimary();
/*  88 */     this.relation = table.relation();
/*  89 */     this.primaryTable = table.primaryTable();
/*     */   }
/*     */ 
/*     */   public String getTableName() {
/*  93 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public String getVar() {
/*  97 */     return this.var;
/*     */   }
/*     */ 
/*     */   public String getDisplayTagId() {
/* 101 */     return this.displayTagId;
/*     */   }
/*     */ 
/*     */   public String getPk() {
/* 105 */     return this.pk;
/*     */   }
/*     */ 
/*     */   public String getComment() {
/* 109 */     return this.comment;
/*     */   }
/*     */ 
/*     */   public List<TableField> getTableFieldList() {
/* 113 */     return this.tableFieldList;
/*     */   }
/*     */ 
/*     */   public void setTableFieldList(List<TableField> tableFieldList) {
/* 117 */     this.tableFieldList = tableFieldList;
/*     */   }
/*     */ 
/*     */   public static void setQueryFilterLocal(Map<String, Object> map)
/*     */   {
/* 123 */     queryFilterLocal.set(map);
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getQueryFilterLocal() {
/* 127 */     return (Map)queryFilterLocal.get();
/*     */   }
/*     */ 
/*     */   public static void removeQueryFilterLocal() {
/* 131 */     queryFilterLocal.remove();
/*     */   }
/*     */ 
/*     */   public static List<TableEntity> getAll(QueryFilter queryFilter)
/*     */   {
/* 141 */     List tableEntitylist = new ArrayList(SearchCache.getTableEntityMap().values());
/*     */ 
/* 143 */     List queryList = new ArrayList(tableEntitylist);
/*     */ 
/* 145 */     if (BeanUtils.isNotEmpty(queryFilter.getFilters())) {
/* 146 */       queryList = getQueryList(queryList, tableEntitylist, queryFilter);
/*     */ 
/* 148 */       queryList = getSortList(queryList, queryFilter);
/*     */     }
/*     */ 
/* 152 */     PageBean pageBean = queryFilter.getPageBean();
/*     */ 
/* 154 */     int total = queryList.size();
/*     */ 
/* 156 */     int pageSize = pageBean.getPageSize();
/*     */ 
/* 158 */     int currentPage = pageBean.getCurrentPage();
/*     */ 
/* 160 */     int fromIndex = pageSize * (currentPage - 1);
/* 161 */     int toIndex = pageSize * currentPage > total ? total : pageSize * currentPage;
/*     */ 
/* 163 */     List list = queryList.subList(fromIndex, toIndex);
/* 164 */     pageBean.setTotalCount(total);
/* 165 */     queryFilter.setForWeb();
/* 166 */     return list;
/*     */   }
/*     */ 
/*     */   private static List<TableEntity> getSortList(List<TableEntity> queryList, QueryFilter queryFilter)
/*     */   {
/* 178 */     Object orderField = queryFilter.getFilters().get("orderField");
/* 179 */     Object orderSeq = queryFilter.getFilters().get("orderSeq");
/*     */ 
/* 181 */     if ((BeanUtils.isEmpty(orderField)) || (BeanUtils.isEmpty(orderSeq)))
/* 182 */       return queryList;
/* 183 */     setQueryFilterLocal(queryFilter.getFilters());
/*     */ 
/* 185 */     Comparator<TableEntity> comparator = new Comparator<TableEntity>()
/*     */     {
/*     */       public int compare(TableEntity o1, TableEntity o2) {
/* 188 */         Map m = TableEntity.getQueryFilterLocal();
/* 189 */         Object field = m.get("orderField");
/* 190 */         Object seq = m.get("orderSeq");
/* 191 */         String orderField = "";
/* 192 */         if ("tableName".equalsIgnoreCase((String)field))
/* 193 */           orderField = "tableName";
/* 194 */         boolean order = true;
/* 195 */         if ("asc".equalsIgnoreCase((String)seq)) {
/* 196 */           order = false;
/*     */         }
/* 198 */         String s1 = BeanUtils.isNotEmpty(orderField) ? o1.getTableName() : o1.getComment();
/*     */ 
/* 200 */         String s2 = BeanUtils.isNotEmpty(orderField) ? o2.getTableName() : o2.getComment();
/*     */ 
/* 203 */         return compare(s1, s2, order);
/*     */       }
/*     */ 
/*     */       public int compare(String s1, String s2, boolean order)
/*     */       {
/* 208 */         Comparator cmp = Collator.getInstance(Locale.CHINA);
/*     */ 
/* 210 */         if (cmp.compare(s1, s2) < 0)
/* 211 */           return order ? -1 : 1;
/* 212 */         if (cmp.compare(s1, s2) > 0) {
/* 213 */           return order ? 1 : -1;
/*     */         }
/* 215 */         return 0;
/*     */       }
/*     */     };
/* 219 */     Collections.sort(queryList, comparator);
/* 220 */     removeQueryFilterLocal();
/* 221 */     return queryList;
/*     */   }
/*     */ 
/*     */   private static List<TableEntity> getQueryList(List<TableEntity> queryList, List<TableEntity> list, QueryFilter queryFilter)
/*     */   {
/* 234 */     Object tableName = queryFilter.getFilters().get("tableName");
/* 235 */     Object description = queryFilter.getFilters().get("comment");
/* 236 */     if ((BeanUtils.isEmpty(tableName)) && (BeanUtils.isEmpty(description)))
/* 237 */       return queryList;
/* 238 */     queryList = new ArrayList();
/* 239 */     int type = getQueryType(tableName, description);
/*     */ 
/* 241 */     for (TableEntity tableEntity : list) {
/* 242 */       boolean flag = isContainsQuery(tableEntity, tableName, description, type);
/*     */ 
/* 244 */       if (flag)
/* 245 */         queryList.add(tableEntity);
/*     */     }
/* 247 */     return queryList;
/*     */   }
/*     */ 
/*     */   private static boolean isContainsQuery(TableEntity tableEntity, Object tableName, Object description, int type)
/*     */   {
/* 252 */     switch (type) {
/*     */     case 1:
/* 254 */       return isContainsQuery(tableEntity, tableName, description);
/*     */     case 2:
/* 256 */       return StringUtils.containsIgnoreCase(tableEntity.getTableName(), tableName.toString());
/*     */     case 3:
/* 259 */       return StringUtils.containsIgnoreCase(tableEntity.getComment(), description.toString());
/*     */     }
/*     */ 
/* 262 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean isContainsQuery(TableEntity tableEntity, Object tableName, Object description)
/*     */   {
/* 275 */     if ((isContainsQuery(tableEntity, tableName, Integer.valueOf(2))) && (isContainsQuery(tableEntity, description, Integer.valueOf(3))))
/*     */     {
/* 277 */       return true;
/* 278 */     }return false;
/*     */   }
/*     */ 
/*     */   private static int getQueryType(Object tableName, Object description)
/*     */   {
/* 289 */     int type = 0;
/* 290 */     if ((BeanUtils.isNotEmpty(tableName)) && (BeanUtils.isNotEmpty(description)))
/*     */     {
/* 292 */       type = 1;
/* 293 */     } else if ((BeanUtils.isNotEmpty(tableName)) && (BeanUtils.isEmpty(description)))
/*     */     {
/* 295 */       type = 2;
/* 296 */     } else if ((BeanUtils.isEmpty(tableName)) && (BeanUtils.isNotEmpty(description)))
/*     */     {
/* 298 */       type = 3;
/*     */     }
/* 300 */     return type;
/*     */   }
/*     */ 
/*     */   public boolean isPrimary() {
/* 304 */     return this.isPrimary;
/*     */   }
/*     */ 
/*     */   public void setPrimary(boolean isPrimary) {
/* 308 */     this.isPrimary = isPrimary;
/*     */   }
/*     */ 
/*     */   public String getRelation() {
/* 312 */     return this.relation;
/*     */   }
/*     */ 
/*     */   public void setRelation(String relation) {
/* 316 */     this.relation = relation;
/*     */   }
/*     */ 
/*     */   public String getPrimaryTable() {
/* 320 */     return this.primaryTable;
/*     */   }
/*     */ 
/*     */   public void setPrimaryTable(String primaryTable) {
/* 324 */     this.primaryTable = primaryTable;
/*     */   }
/*     */ 
/*     */   public List<TableEntity> getSubTableList() {
/* 328 */     return this.subTableList;
/*     */   }
/*     */ 
/*     */   public void setSubTableList(List<TableEntity> subTableList) {
/* 332 */     this.subTableList = subTableList;
/*     */   }
/*     */ 
/*     */   public void addSubTable(TableEntity tableEnt)
/*     */   {
/* 341 */     this.subTableList.add(tableEnt);
/*     */   }
/*     */ 
/*     */   public static Map<String, TableEntity> getSubTableMap(TableEntity tableEntity)
/*     */   {
/* 350 */     List<TableEntity> subTableList = tableEntity.getSubTableList();
/* 351 */     Map map = new HashMap();
/* 352 */     if (BeanUtils.isEmpty(subTableList))
/* 353 */       return map;
/* 354 */     for (TableEntity table : subTableList) {
/* 355 */       map.put(table.getTableName(), table);
/*     */     }
/* 357 */     return map;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 363 */     return new ToStringBuilder(this).append("tableName", this.tableName).append("pk", this.pk).append("comment", this.comment).append("tableFieldList.size", this.tableFieldList.size()).toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.scan.TableEntity
 * JD-Core Version:    0.6.2
 */