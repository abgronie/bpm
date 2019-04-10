/*    */ package com.hotent.core.valid;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ValidForm
/*    */ {
/* 16 */   private String formName = "";
/*    */ 
/* 21 */   private List<ValidField> listField = new ArrayList();
/*    */ 
/*    */   public String getFormName()
/*    */   {
/* 27 */     return this.formName;
/*    */   }
/*    */ 
/*    */   public void setFormName(String formName)
/*    */   {
/* 35 */     this.formName = formName;
/*    */   }
/*    */ 
/*    */   public List<ValidField> getListField() {
/* 39 */     return this.listField;
/*    */   }
/*    */ 
/*    */   public void setListField(List<ValidField> listField) {
/* 43 */     this.listField = listField;
/*    */   }
/*    */ 
/*    */   public void addField(ValidField field)
/*    */   {
/* 52 */     this.listField.add(field);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.valid.ValidForm
 * JD-Core Version:    0.6.2
 */