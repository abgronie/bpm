/*    */ package com.hotent.core.bpmn20.entity.omgdc;
/*    */ 
/*    */ import javax.xml.bind.JAXBElement;
/*    */ import javax.xml.bind.annotation.XmlElementDecl;
/*    */ import javax.xml.bind.annotation.XmlRegistry;
/*    */ import javax.xml.namespace.QName;
/*    */ 
/*    */ @XmlRegistry
/*    */ public class ObjectFactory
/*    */ {
/* 34 */   private static final QName _Bounds_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DC", "Bounds");
/* 35 */   private static final QName _Point_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DC", "Point");
/* 36 */   private static final QName _Font_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DC", "Font");
/*    */ 
/*    */   public Font createFont()
/*    */   {
/* 50 */     return new Font();
/*    */   }
/*    */ 
/*    */   public Bounds createBounds()
/*    */   {
/* 58 */     return new Bounds();
/*    */   }
/*    */ 
/*    */   public Point createPoint()
/*    */   {
/* 66 */     return new Point();
/*    */   }
/*    */ 
/*    */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DC", name="Bounds")
/*    */   public JAXBElement<Bounds> createBounds(Bounds value)
/*    */   {
/* 75 */     return new JAXBElement(_Bounds_QNAME, Bounds.class, null, value);
/*    */   }
/*    */ 
/*    */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DC", name="Point")
/*    */   public JAXBElement<Point> createPoint(Point value)
/*    */   {
/* 84 */     return new JAXBElement(_Point_QNAME, Point.class, null, value);
/*    */   }
/*    */ 
/*    */   @XmlElementDecl(namespace="http://www.omg.org/spec/DD/20100524/DC", name="Font")
/*    */   public JAXBElement<Font> createFont(Font value)
/*    */   {
/* 93 */     return new JAXBElement(_Font_QNAME, Font.class, null, value);
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.omgdc.ObjectFactory
 * JD-Core Version:    0.6.2
 */