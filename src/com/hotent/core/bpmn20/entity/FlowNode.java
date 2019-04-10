/*     */ package com.hotent.core.bpmn20.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlSeeAlso;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tFlowNode", propOrder={"incoming", "outgoing"})
/*     */ @XmlSeeAlso({Event.class, ChoreographyActivity.class, Gateway.class, Activity.class})
/*     */ public abstract class FlowNode extends FlowElement
/*     */ {
/*     */   protected List<QName> incoming;
/*     */   protected List<QName> outgoing;
/*     */ 
/*     */   public List<QName> getIncoming()
/*     */   {
/*  82 */     if (this.incoming == null) {
/*  83 */       this.incoming = new ArrayList();
/*     */     }
/*  85 */     return this.incoming;
/*     */   }
/*     */ 
/*     */   public List<QName> getOutgoing()
/*     */   {
/* 111 */     if (this.outgoing == null) {
/* 112 */       this.outgoing = new ArrayList();
/*     */     }
/* 114 */     return this.outgoing;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.bpmn20.entity.FlowNode
 * JD-Core Version:    0.6.2
 */