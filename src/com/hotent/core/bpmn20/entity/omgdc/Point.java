/*    */ package com.hotent.core.bpmn20.entity.omgdc;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlAttribute;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="Point")
/*    */ public class Point
/*    */ {
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected double x;
/*    */ 
/*    */   @XmlAttribute(required=true)
/*    */   protected double y;
/*    */ 
/*    */   public double getX()
/*    */   {
/* 49 */     return this.x;
/*    */   }
/*    */ 
/*    */   public void setX(double value)
/*    */   {
/* 57 */     this.x = value;
/*    */   }
/*    */ 
/*    */   public double getY()
/*    */   {
/* 65 */     return this.y;
/*    */   }
/*    */ 
/*    */   public void setY(double value)
/*    */   {
/* 73 */     this.y = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdc.Point
 * JD-Core Version:    0.6.2
 */