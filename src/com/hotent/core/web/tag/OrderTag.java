/*     */ package com.hotent.core.web.tag;
/*     */ 
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Enumeration;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.jsp.JspTagException;
/*     */ import javax.servlet.jsp.JspWriter;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.BodyContent;
/*     */ import javax.servlet.jsp.tagext.BodyTagSupport;
/*     */ 
/*     */ public class OrderTag extends BodyTagSupport
/*     */ {
/*     */   private static final String Asc = "ASC";
/*     */   private static final String Desc = "DESC";
/*  26 */   private String field = "";
/*  27 */   private String order = "DESC";
/*     */ 
/*  29 */   private String ascImg = "/themes/img/commons/asc.png";
/*  30 */   private String descImg = "/themes/img/commons/desc.png";
/*     */ 
/*  52 */   private String[] aryAvoid = { "sortField", "orderSeq" };
/*     */ 
/*     */   public String getField()
/*     */   {
/*  35 */     return this.field;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/*  39 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public String getOrder() {
/*  43 */     return this.order;
/*     */   }
/*     */ 
/*     */   public void setOrder(String order) {
/*  47 */     this.order = order;
/*     */   }
/*     */ 
/*     */   public int doStartTag()
/*     */     throws JspTagException
/*     */   {
/*  57 */     return 2;
/*     */   }
/*     */ 
/*     */   private String getOutput(HttpServletRequest request) throws Exception
/*     */   {
/*  62 */     String body = getBodyContent().getString();
/*  63 */     if ((this.field == null) || (this.field.equals("")))
/*     */     {
/*  65 */       return "<th>" + body + "</th>";
/*     */     }
/*  67 */     String img = "";
/*  68 */     String orderSeq = request.getParameter("orderSeq");
/*  69 */     String sortField = request.getParameter("sortField");
/*  70 */     if ((orderSeq == null) || (!sortField.equals(this.field)))
/*     */     {
/*  72 */       orderSeq = this.order;
/*     */     }
/*  76 */     else if (orderSeq.equals("DESC"))
/*     */     {
/*  78 */       orderSeq = "ASC";
/*     */     }
/*     */     else
/*     */     {
/*  82 */       orderSeq = "DESC";
/*     */     }
/*     */ 
/*  85 */     if (orderSeq.equals("DESC"))
/*     */     {
/*  87 */       img = request.getContextPath() + this.descImg;
/*     */     }
/*     */     else
/*     */     {
/*  91 */       img = request.getContextPath() + this.ascImg;
/*     */     }
/*     */ 
/*  94 */     String url = getUrl(request);
/*     */ 
/*  96 */     String para = "sortField=" + this.field + "&orderSeq=" + orderSeq;
/*  97 */     if (url.indexOf("?") > -1)
/*  98 */       url = url + "&" + para;
/*     */     else {
/* 100 */       url = url + "?" + para;
/*     */     }
/*     */ 
/* 103 */     StringBuffer sb = new StringBuffer();
/* 104 */     sb.append("<th  >");
/*     */ 
/* 106 */     sb.append("<a href='" + url + "'>" + body + "<span style='vertical-align:middle;'><img border='0' src='" + img + "'/></span></a>");
/*     */ 
/* 109 */     sb.append("</th>");
/* 110 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private String getUrl(HttpServletRequest request)
/*     */     throws Exception
/*     */   {
/* 116 */     StringBuffer urlThisPage = new StringBuffer();
/* 117 */     String url = request.getAttribute("currentPath").toString();
/* 118 */     if (url == null)
/*     */     {
/* 120 */       throw new Exception("请在控制器中设置currentPath(当前路径)!");
/*     */     }
/*     */ 
/* 123 */     urlThisPage.append(url);
/* 124 */     Enumeration e = request.getParameterNames();
/* 125 */     String para = "";
/* 126 */     String values = "";
/* 127 */     urlThisPage.append("?");
/* 128 */     while (e.hasMoreElements())
/*     */     {
/* 130 */       para = (String)e.nextElement();
/* 131 */       boolean rtn = isExists(para);
/* 132 */       if (!rtn)
/*     */       {
/* 134 */         values = URLEncoder.encode(getValueByKey(request, para), "utf-8");
/* 135 */         urlThisPage.append(para);
/* 136 */         urlThisPage.append("=");
/* 137 */         urlThisPage.append(values);
/* 138 */         urlThisPage.append("&");
/*     */       }
/*     */     }
/* 141 */     return urlThisPage.substring(0, urlThisPage.length() - 1);
/*     */   }
/*     */ 
/*     */   private boolean isExists(String key)
/*     */   {
/* 146 */     for (String str : this.aryAvoid)
/*     */     {
/* 148 */       if (key.equals(str))
/* 149 */         return true;
/*     */     }
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   private String getValueByKey(HttpServletRequest request, String key)
/*     */   {
/* 156 */     String rtn = "";
/* 157 */     String[] values = request.getParameterValues(key);
/* 158 */     for (String str : values)
/*     */     {
/* 160 */       if ((str != null) && (!str.trim().equals("")))
/*     */       {
/* 162 */         rtn = rtn + str + ",";
/*     */       }
/*     */     }
/* 165 */     if (rtn.length() > 0)
/* 166 */       rtn = rtn.substring(0, rtn.length() - 1);
/* 167 */     return rtn;
/*     */   }
/*     */ 
/*     */   public int doEndTag() throws JspTagException
/*     */   {
/* 172 */     String body = getBodyContent().getString();
/* 173 */     HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
/*     */     try
/*     */     {
/* 176 */       JspWriter writer = this.pageContext.getOut();
/* 177 */       String str = getOutput(request);
/* 178 */       this.pageContext.getOut().print(str);
/*     */     } catch (Exception e) {
/* 180 */       throw new JspTagException(e.getMessage());
/*     */     }
/* 182 */     return 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.tag.OrderTag
 * JD-Core Version:    0.6.2
 */