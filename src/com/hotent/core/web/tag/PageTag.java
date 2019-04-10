/*     */ package com.hotent.core.web.tag;
/*     */ 
/*     */ import com.hotent.core.engine.FreemarkEngine;
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ import org.displaytag.util.ParamEncoder;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PageTag extends TagSupport
/*     */ {
/*  45 */   private static Logger logger = LoggerFactory.getLogger(PageTag.class);
/*     */   private String tableId;
/*  55 */   private boolean showExplain = true;
/*     */ 
/*  60 */   private boolean showPageSize = true;
/*     */ 
/*     */   public String getTableId() {
/*  63 */     return this.tableId;
/*     */   }
/*     */ 
/*     */   public void setTableId(String tableId)
/*     */   {
/*  71 */     this.tableId = tableId;
/*     */   }
/*     */ 
/*     */   public boolean isShowExplain() {
/*  75 */     return this.showExplain;
/*     */   }
/*     */ 
/*     */   public void setShowExplain(boolean showExplain) {
/*  79 */     this.showExplain = showExplain;
/*     */   }
/*     */ 
/*     */   public boolean isShowPageSize()
/*     */   {
/*  84 */     return this.showPageSize;
/*     */   }
/*     */ 
/*     */   public void setShowPageSize(boolean showPageSize) {
/*  88 */     this.showPageSize = showPageSize;
/*     */   }
/*     */ 
/*     */   public int doStartTag() throws JspException
/*     */   {
/*  93 */     JspWriter out = this.pageContext.getOut();
/*  94 */     HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
/*     */     try
/*     */     {
/*  97 */       FreemarkEngine freemarkEngine = (FreemarkEngine)AppUtil.getBean("freemarkEngine");
/*  98 */       Map model = new HashedMap();
/*  99 */       PageBean pb = null;
/* 100 */       logger.debug("table id:" + this.tableId);
/*     */ 
/* 102 */       String url = null;
/*     */ 
/* 104 */       if (this.tableId != null) {
/* 105 */         pb = (PageBean)request.getAttribute("pageBean" + this.tableId);
/* 106 */         url = (String)request.getAttribute("requestURI" + this.tableId);
/* 107 */         ParamEncoder paramEncoder = new ParamEncoder(this.tableId);
/* 108 */         model.put("tableIdCode", paramEncoder.encodeParameterName(""));
/*     */       } else {
/* 110 */         pb = (PageBean)request.getAttribute("pageBean");
/* 111 */         url = url + request.getRequestURI();
/* 112 */         model.put("tableIdCode", "");
/*     */       }
/* 114 */       if (pb == null) {
/* 115 */         throw new RuntimeException("pagingBean can't no be null");
/*     */       }
/* 117 */       model.put("pageBean", pb);
/*     */ 
/* 119 */       String params = getQueryParameters(request);
/* 120 */       if (url.indexOf("?") > 0) {
/* 121 */         if (!"".equals(params))
/* 122 */           url = url + "&" + params;
/*     */         else
/* 124 */           url = url + "?" + params;
/*     */       }
/* 126 */       else if (!"".equals(params)) {
/* 127 */         url = url + "?" + params;
/*     */       }
/* 129 */       logger.info("current url:" + url);
/* 130 */       model.put("showExplain", Boolean.valueOf(this.showExplain));
/* 131 */       model.put("showPageSize", Boolean.valueOf(this.showPageSize));
/* 132 */       model.put("baseHref", url);
/* 133 */       String html = freemarkEngine.mergeTemplateIntoString("page.ftl", model);
/* 134 */       out.println(html);
/*     */     } catch (Exception ex) {
/* 136 */       ex.printStackTrace();
/*     */     }
/* 138 */     return 0;
/*     */   }
/*     */ 
/*     */   private String getQueryParameters(HttpServletRequest request) {
/* 142 */     Enumeration names = request.getParameterNames();
/* 143 */     StringBuffer sb = new StringBuffer();
/* 144 */     int i = 0;
/* 145 */     while (names.hasMoreElements()) {
/* 146 */       if (i++ > 0) {
/* 147 */         sb.append("&");
/*     */       }
/* 149 */       String name = (String)names.nextElement();
/* 150 */       String value = request.getParameter(name);
/* 151 */       sb.append(name).append("=").append(value);
/*     */     }
/* 153 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.tag.PageTag
 * JD-Core Version:    0.6.2
 */