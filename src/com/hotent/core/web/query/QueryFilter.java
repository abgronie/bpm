/*     */ package com.hotent.core.web.query;
/*     */ 
/*     */ import com.hotent.core.page.PageBean;
/*     */ import com.hotent.core.page.PageUtils;
/*     */ import com.hotent.core.table.TableModel;
/*     */ import com.hotent.core.util.BeanUtils;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import com.hotent.core.util.jsonobject.JSONObjectUtil;
/*     */ import com.hotent.core.web.util.RequestUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.displaytag.util.ParamEncoder;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class QueryFilter
/*     */ {
/*  34 */   private Logger logger = LoggerFactory.getLogger(QueryFilter.class);
/*     */ 
/*  39 */   private Map<String, Object> filters = new HashMap();
/*     */ 
/*  43 */   private String sortColumns = "";
/*     */   private ParamEncoder paramEncoder;
/*     */   public static final String ORDER_ASC = "1";
/*     */   public static final String ORDER_DESC = "2";
/*  53 */   private String tableId = "";
/*     */   private HttpServletRequest request;
/*  57 */   private PageBean pageBean = null;
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, String tableId)
/*     */   {
/*  67 */     this(request, tableId, true);
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, Boolean isLg, String tableId)
/*     */   {
/*  79 */     this.tableId = tableId;
/*  80 */     this.request = request;
/*     */ 
/*  82 */     this.paramEncoder = new ParamEncoder(tableId);
/*     */     try
/*     */     {
/*  85 */       String orderField = RequestUtil.getString(request, "sortname");
/*  86 */       String orderSeq = RequestUtil.getString(request, "sortorder");
/*     */ 
/*  88 */       Map map = RequestUtil.getQueryMap(request);
/*  89 */       if (StringUtil.isNotEmpty(orderField)) {
/*  90 */         orderField = TableModel.CUSTOMER_COLUMN_PREFIX + orderField;
/*  91 */         map.put("orderField", orderField);
/*  92 */         map.put("orderSeq", orderSeq);
/*     */       }
/*  94 */       this.filters = map;
/*  95 */       if (isLg.booleanValue())
/*     */       {
/*  97 */         Integer page = Integer.valueOf(RequestUtil.getInt(request, "page", 1));
/*  98 */         Integer pageSize = Integer.valueOf(RequestUtil.getInt(request, "pagesize", PageBean.DEFAULT_PAGE_SIZE.intValue()));
/*  99 */         this.pageBean = new PageBean(page.intValue(), pageSize.intValue());
/* 100 */         this.pageBean.setShowTotal(true);
/*     */       }
/*     */     }
/*     */     catch (Exception ex) {
/* 104 */       this.logger.error(ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request)
/*     */   {
/* 115 */     this(request, true);
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, String tableId, boolean needPage)
/*     */   {
/* 130 */     this.tableId = tableId;
/* 131 */     this.request = request;
/*     */ 
/* 133 */     this.paramEncoder = new ParamEncoder(tableId);
/* 134 */     String tableIdCode = this.paramEncoder.encodeParameterName("");
/*     */     try
/*     */     {
/* 137 */       String orderField = request.getParameter(tableIdCode + "s");
/* 138 */       String orderSeqNum = request.getParameter(tableIdCode + "o");
/*     */ 
/* 140 */       String orderSeq = "desc";
/* 141 */       if ((orderSeqNum != null) && ("1".equals(orderSeqNum))) {
/* 142 */         orderSeq = "asc";
/*     */       }
/* 144 */       Map map = RequestUtil.getQueryMap(request);
/* 145 */       if (orderField != null) {
/* 146 */         map.put("orderField", orderField);
/* 147 */         map.put("orderSeq", orderSeq);
/*     */       }
/* 149 */       this.filters = map;
/*     */ 
/* 151 */       if ((BeanUtils.isNotEmpty(map.get(tableIdCode + "e"))) && ("1".equals(map.get("exportAll")))) {
/* 152 */         needPage = false;
/*     */       }
/*     */ 
/* 155 */       if (needPage) {
/* 156 */         int page = RequestUtil.getInt(request, tableIdCode + "p", 1);
/* 157 */         int pageSize = RequestUtil.getInt(request, tableIdCode + "z", PageBean.DEFAULT_PAGE_SIZE.intValue());
/* 158 */         int oldPageSize = RequestUtil.getInt(request, tableIdCode + "oz", PageBean.DEFAULT_PAGE_SIZE.intValue());
/* 159 */         if (pageSize != oldPageSize) {
/* 160 */           int first = PageUtils.getFirstNumber(page, oldPageSize);
/* 161 */           page = first / pageSize + 1;
/*     */         }
/* 163 */         this.pageBean = new PageBean(page, pageSize);
/*     */       }
/*     */     }
/*     */     catch (Exception ex) {
/* 167 */       this.logger.error(ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, String tableId, String showTotal)
/*     */   {
/* 181 */     this(request, tableId, true);
/* 182 */     if ("false".equals(showTotal))
/* 183 */       this.pageBean.setShowTotal(false);
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, boolean needPage)
/*     */   {
/* 199 */     this.request = request;
/*     */     try {
/* 201 */       if (needPage) {
/* 202 */         int page = RequestUtil.getInt(request, "page", 1);
/* 203 */         int pageSize = RequestUtil.getInt(request, "pageSize", 15);
/* 204 */         this.pageBean = new PageBean(page, pageSize);
/*     */       }
/* 206 */       Map map = RequestUtil.getQueryMap(request);
/* 207 */       this.filters = map;
/*     */     } catch (Exception ex) {
/* 209 */       this.logger.error(ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, String tableId, int pageSize)
/*     */   {
/* 224 */     this(request, tableId, true, pageSize);
/*     */   }
/*     */ 
/*     */   public QueryFilter(HttpServletRequest request, String tableId, boolean needPage, int pageSize)
/*     */   {
/* 241 */     this.tableId = tableId;
/* 242 */     this.request = request;
/*     */ 
/* 244 */     this.paramEncoder = new ParamEncoder(tableId);
/* 245 */     String tableIdCode = this.paramEncoder.encodeParameterName("");
/*     */     try
/*     */     {
/* 248 */       String orderField = request.getParameter(tableIdCode + "s");
/* 249 */       String orderSeqNum = request.getParameter(tableIdCode + "o");
/* 250 */       String orderSeq = "desc";
/* 251 */       if ((orderSeqNum != null) && ("1".equals(orderSeqNum))) {
/* 252 */         orderSeq = "asc";
/*     */       }
/* 254 */       Map map = RequestUtil.getQueryMap(request);
/* 255 */       if (orderField != null) {
/* 256 */         map.put("orderField", orderField);
/* 257 */         map.put("orderSeq", orderSeq);
/*     */       }
/* 259 */       this.filters = map;
/* 260 */       if (needPage) {
/* 261 */         int page = RequestUtil.getInt(request, tableIdCode + "p", 1);
/* 262 */         if (page <= 0)
/* 263 */           page = 1;
/* 264 */         int size = RequestUtil.getInt(request, tableIdCode + "z", 0);
/* 265 */         if (size > 0)
/* 266 */           pageSize = size;
/* 267 */         int oldPageSize = RequestUtil.getInt(request, tableIdCode + "oz", PageBean.DEFAULT_PAGE_SIZE.intValue());
/* 268 */         if (pageSize != oldPageSize) {
/* 269 */           int first = PageUtils.getFirstNumber(page, oldPageSize);
/* 270 */           page = first / pageSize + 1;
/*     */         }
/* 272 */         this.pageBean = new PageBean(page, pageSize);
/*     */       }
/*     */     }
/*     */     catch (Exception ex) {
/* 276 */       this.logger.error(ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public QueryFilter(JSONObject json)
/*     */   {
/*     */     try
/*     */     {
/* 314 */       String currentPageStr = json.get("currentPage") != null ? json.getString("currentPage") : "";
/* 315 */       String pageSizeStr = json.get("pageSize") != null ? json.getString("pageSize") : "";
/* 316 */       Integer pageSize = Integer.valueOf(StringUtil.isNotEmpty(pageSizeStr) ? Integer.parseInt(pageSizeStr) : PageBean.DEFAULT_PAGE_SIZE.intValue());
/* 317 */       Integer currentPage = Integer.valueOf(StringUtil.isNotEmpty(currentPageStr) ? Integer.parseInt(currentPageStr) : 1);
/* 318 */       if (StringUtil.isNotEmpty(currentPageStr)) {
/* 319 */         this.pageBean = new PageBean(currentPage.intValue(), pageSize.intValue());
/*     */       }
/*     */ 
/* 322 */       Map map = JSONObjectUtil.getQueryMap(json);
/* 323 */       this.filters = map;
/*     */     } catch (Exception ex) {
/* 325 */       ex.printStackTrace();
/* 326 */       this.logger.error(ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public HttpServletRequest getRequest() {
/* 331 */     return this.request;
/*     */   }
/*     */ 
/*     */   public PageBean getPageBean() {
/* 335 */     return this.pageBean;
/*     */   }
/*     */ 
/*     */   public void setPageBean(PageBean pageBean) {
/* 339 */     this.pageBean = pageBean;
/*     */   }
/*     */ 
/*     */   public void setForWeb()
/*     */   {
/* 348 */     String pbName = "pageBean";
/* 349 */     String href = "requestURI";
/* 350 */     if (this.tableId != null) {
/* 351 */       pbName = pbName + this.tableId;
/* 352 */       href = href + this.tableId;
/*     */     }
/* 354 */     if (this.request == null) {
/* 355 */       return;
/*     */     }
/* 357 */     this.request.setAttribute(href, this.request.getRequestURI());
/* 358 */     this.request.setAttribute(pbName, this.pageBean);
/*     */   }
/*     */ 
/*     */   public String encodeParameter(String parameterName)
/*     */   {
/* 368 */     if (this.paramEncoder == null)
/*     */     {
/* 370 */       this.paramEncoder = new ParamEncoder(this.tableId);
/*     */     }
/*     */ 
/* 373 */     return this.paramEncoder.encodeParameterName(parameterName);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getFilters()
/*     */   {
/* 382 */     return this.filters;
/*     */   }
/*     */ 
/*     */   public void addFilter(String filterName, Object params)
/*     */   {
/* 394 */     this.filters.put(filterName, params);
/*     */   }
/*     */ 
/*     */   public void setFilters(Map<String, Object> filters)
/*     */   {
/* 403 */     this.filters = filters;
/*     */   }
/*     */ 
/*     */   public String getSortColumns()
/*     */   {
/* 412 */     return this.sortColumns;
/*     */   }
/*     */ 
/*     */   public void setSortColumns(String sortColumns)
/*     */   {
/* 427 */     this.sortColumns = sortColumns;
/*     */   }
/*     */ 
/*     */   public String getTableId()
/*     */   {
/* 436 */     return this.tableId;
/*     */   }
/*     */ 
/*     */   public ParamEncoder getParamEncoder() {
/* 440 */     return this.paramEncoder;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.query.QueryFilter
 * JD-Core Version:    0.6.2
 */