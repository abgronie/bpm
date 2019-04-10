/*     */ package com.hotent.core.db.helper;
/*     */ 
/*     */ import com.hotent.core.annotion.ClassDescription;
/*     */ import com.hotent.core.annotion.FieldDescription;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ObjectHelper<T>
/*     */ {
/*     */   private T obj;
/*     */ 
/*     */   public void setModel(T obj)
/*     */   {
/*  25 */     this.obj = obj;
/*     */   }
/*     */ 
/*     */   public String getTableName()
/*     */   {
/*  35 */     Class cls = this.obj.getClass();
/*  36 */     ClassDescription clsDesc = (ClassDescription)cls.getAnnotation(ClassDescription.class);
/*  37 */     if (clsDesc == null)
/*  38 */       return cls.getSimpleName();
/*  39 */     return clsDesc.tableName();
/*     */   }
/*     */ 
/*     */   public List<ColumnModel> getColumns()
/*     */   {
/*  48 */     List list = new ArrayList();
/*  49 */     Class cls = this.obj.getClass();
/*  50 */     Field[] fields = cls.getDeclaredFields();
/*  51 */     for (int i = 0; i < fields.length; i++)
/*     */     {
/*  53 */       Field fld = fields[i];
/*  54 */       ColumnModel column = new ColumnModel();
/*  55 */       column.setPropery(fld.getName());
/*     */ 
/*  57 */       FieldDescription fldDesc = (FieldDescription)fld.getAnnotation(FieldDescription.class);
/*  58 */       if (fldDesc == null)
/*     */       {
/*  60 */         column.setColumnName(fld.getName());
/*  61 */         column.setPk(false);
/*     */       }
/*     */       else
/*     */       {
/*  65 */         column.setColumnName(fldDesc.columnName());
/*  66 */         column.setPk(fldDesc.pk());
/*  67 */         column.setCanUpd(fldDesc.canUpd());
/*     */       }
/*  69 */       list.add(column);
/*     */     }
/*  71 */     return list;
/*     */   }
/*     */ 
/*     */   public ColumnModel getPk(List<ColumnModel> list)
/*     */   {
/*  81 */     ColumnModel columnModel = null;
/*  82 */     int len = list.size();
/*  83 */     for (int i = 0; i < len; i++)
/*     */     {
/*  85 */       ColumnModel model = (ColumnModel)list.get(i);
/*  86 */       if (model.getPk())
/*  87 */         return model;
/*     */     }
/*  89 */     return columnModel;
/*     */   }
/*     */ 
/*     */   private List<ColumnModel> getCommonCols(List<ColumnModel> list)
/*     */   {
/*  94 */     List cols = new ArrayList();
/*  95 */     int len = list.size();
/*  96 */     for (int i = 0; i < len; i++)
/*     */     {
/*  98 */       ColumnModel model = (ColumnModel)list.get(i);
/*  99 */       if (!model.getPk()) {
/* 100 */         cols.add(model);
/*     */       }
/*     */     }
/* 103 */     return cols;
/*     */   }
/*     */ 
/*     */   private String[] getInsertColumns()
/*     */   {
/* 108 */     List list = getColumns();
/* 109 */     String cols = "";
/* 110 */     String vals = "";
/* 111 */     int len = list.size();
/* 112 */     String[] aryStr = new String[2];
/* 113 */     for (int i = 0; i < len; i++)
/*     */     {
/* 115 */       ColumnModel column = (ColumnModel)list.get(i);
/* 116 */       if (i < len - 1)
/*     */       {
/* 118 */         cols = cols + column.getColumnName() + ",";
/* 119 */         vals = vals + ":" + column.getPropery() + ",";
/*     */       }
/*     */       else
/*     */       {
/* 123 */         cols = cols + column.getColumnName();
/* 124 */         vals = vals + ":" + column.getPropery();
/*     */       }
/*     */     }
/* 127 */     aryStr[0] = cols;
/* 128 */     aryStr[1] = vals;
/* 129 */     return aryStr;
/*     */   }
/*     */ 
/*     */   public String getUpdSql()
/*     */   {
/* 138 */     List list = getColumns();
/* 139 */     List commonList = getCommonCols(list);
/* 140 */     ColumnModel pk = getPk(list);
/* 141 */     String tableName = getTableName();
/* 142 */     String sql = "update ";
/*     */ 
/* 144 */     sql = sql + tableName + " set ";
/*     */ 
/* 146 */     String tmp = "";
/* 147 */     int len = commonList.size();
/* 148 */     for (int i = 0; i < len; i++)
/*     */     {
/* 150 */       ColumnModel model = (ColumnModel)list.get(i);
/* 151 */       if (model.getCanUpd()) {
/* 152 */         tmp = tmp + model.getColumnName() + "=:" + model.getPropery() + ",";
/*     */       }
/*     */     }
/* 155 */     if (tmp.length() > 0) {
/* 156 */       tmp = tmp.substring(0, tmp.length() - 1);
/*     */     }
/* 158 */     sql = sql + tmp;
/*     */ 
/* 160 */     sql = sql + " where " + pk.getColumnName() + "=:" + pk.getPropery();
/*     */ 
/* 162 */     return sql;
/*     */   }
/*     */ 
/*     */   public String getDelSql()
/*     */   {
/* 171 */     List list = getColumns();
/* 172 */     String tableName = getTableName();
/* 173 */     ColumnModel column = getPk(list);
/* 174 */     String sql = "delete from " + tableName + " where " + column.getColumnName() + "=:" + column.getPropery();
/* 175 */     return sql;
/*     */   }
/*     */ 
/*     */   public String getDetailSql()
/*     */   {
/* 184 */     List list = getColumns();
/* 185 */     String tableName = getTableName();
/* 186 */     ColumnModel column = getPk(list);
/* 187 */     String sql = "select a.* from " + tableName + " a where " + column.getColumnName() + "=:" + column.getPropery();
/* 188 */     return sql;
/*     */   }
/*     */ 
/*     */   public String getAddSql()
/*     */   {
/* 199 */     String tableName = getTableName();
/* 200 */     String[] aryCol = getInsertColumns();
/* 201 */     StringBuffer sb = new StringBuffer();
/* 202 */     sb.append("insert into ");
/* 203 */     sb.append(tableName);
/* 204 */     sb.append("(");
/* 205 */     sb.append(aryCol[0]);
/* 206 */     sb.append(")");
/* 207 */     sb.append(" values (");
/* 208 */     sb.append(aryCol[1]);
/* 209 */     sb.append(")");
/* 210 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.db.helper.ObjectHelper
 * JD-Core Version:    0.6.2
 */