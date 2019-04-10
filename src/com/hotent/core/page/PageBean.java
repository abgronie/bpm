/*     */ package com.hotent.core.page;
/*     */ 
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
/*     */ public class PageBean
/*     */   implements Serializable
/*     */ {
/*  19 */   public static Integer SHOW_PAGES = Integer.valueOf(6);
/*     */ 
/*  23 */   public static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(20);
/*     */ 
/*  25 */   private int pageSize = DEFAULT_PAGE_SIZE.intValue();
/*     */ 
/*  27 */   private int currentPage = 1;
/*     */ 
/*  29 */   private int totalCount = 0;
/*     */ 
/*  32 */   private String pageName = "page";
/*  33 */   private String pageSizeName = "pageSize";
/*     */ 
/*  38 */   private boolean showTotal = true;
/*     */ 
/*     */   public PageBean()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PageBean(int currentPage, int pageSize)
/*     */   {
/*  47 */     this.currentPage = currentPage;
/*  48 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public void setCurrentPage(int currentPage)
/*     */   {
/*  58 */     this.currentPage = currentPage;
/*     */   }
/*     */ 
/*     */   public void setPagesize(int pageSize)
/*     */   {
/*  63 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageName(String pageName)
/*     */   {
/*  68 */     this.pageName = pageName;
/*     */   }
/*     */ 
/*     */   public String getPageName()
/*     */   {
/*  73 */     if (StringUtil.isEmpty(this.pageName))
/*  74 */       return "page";
/*  75 */     return this.pageName;
/*     */   }
/*     */ 
/*     */   public String getPageSizeName()
/*     */   {
/*  80 */     if (StringUtil.isEmpty(this.pageSizeName))
/*  81 */       return "pageSize";
/*  82 */     return this.pageSizeName;
/*     */   }
/*     */ 
/*     */   public boolean isFirstPage()
/*     */   {
/*  91 */     return getCurrentPage() == 1;
/*     */   }
/*     */ 
/*     */   public boolean isLastPage()
/*     */   {
/* 100 */     return getCurrentPage() >= getLastPage();
/*     */   }
/*     */ 
/*     */   public boolean isHasNextPage()
/*     */   {
/* 109 */     return getLastPage() > getCurrentPage();
/*     */   }
/*     */ 
/*     */   public boolean isHasPreviousPage()
/*     */   {
/* 118 */     return getCurrentPage() > 1;
/*     */   }
/*     */ 
/*     */   public int getLastPage()
/*     */   {
/* 127 */     return PageUtils.getTotalPage(this.totalCount, this.pageSize);
/*     */   }
/*     */ 
/*     */   public int getTotalCount()
/*     */   {
/* 136 */     return this.totalCount;
/*     */   }
/*     */ 
/*     */   public void setTotalCount(int count)
/*     */   {
/* 145 */     this.totalCount = count;
/*     */   }
/*     */ 
/*     */   public int getTotalPage()
/*     */   {
/* 154 */     int page = this.totalCount / this.pageSize;
/* 155 */     int tmp = this.totalCount % this.pageSize;
/* 156 */     return page + (tmp == 0 ? 0 : 1);
/*     */   }
/*     */ 
/*     */   public int getThisPageFirstElementNumber()
/*     */   {
/* 165 */     return (getCurrentPage() - 1) * getPageSize() + 1;
/*     */   }
/*     */ 
/*     */   public int getThisPageLastElementNumber()
/*     */   {
/* 174 */     int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
/* 175 */     return getTotalCount() < fullPage ? getTotalCount() : fullPage;
/*     */   }
/*     */ 
/*     */   public int getNextPage()
/*     */   {
/* 184 */     return getCurrentPage() + 1;
/*     */   }
/*     */ 
/*     */   public int getPreviousPage()
/*     */   {
/* 193 */     return getCurrentPage() - 1;
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/* 202 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public boolean isShowTotal() {
/* 206 */     return this.showTotal;
/*     */   }
/*     */ 
/*     */   public void setShowTotal(boolean showTotal) {
/* 210 */     this.showTotal = showTotal;
/*     */   }
/*     */ 
/*     */   public int getCurrentPage()
/*     */   {
/* 219 */     return this.currentPage;
/*     */   }
/*     */ 
/*     */   public List<Integer> getLinkPageNumbers()
/*     */   {
/* 228 */     return PageUtils.getPageNumbers(getCurrentPage(), getLastPage(), 10);
/*     */   }
/*     */ 
/*     */   public int getFirst()
/*     */   {
/* 237 */     return PageUtils.getFirstNumber(this.currentPage, this.pageSize);
/*     */   }
/*     */ 
/*     */   public int getLast()
/*     */   {
/* 245 */     return PageUtils.getLastNumber(this.currentPage, this.pageSize, this.totalCount);
/*     */   }
/*     */ 
/*     */   public Integer[] getPageArr()
/*     */   {
/* 253 */     int totalPages = getTotalPage();
/* 254 */     int minPage = 1;
/* 255 */     int maxPage = totalPages;
/* 256 */     if (this.currentPage == 1) {
/* 257 */       maxPage = SHOW_PAGES.intValue();
/*     */     } else {
/* 259 */       minPage = this.currentPage - (SHOW_PAGES.intValue() / 2 + SHOW_PAGES.intValue() % 2);
/* 260 */       if (minPage <= 0) {
/* 261 */         minPage = 1;
/*     */       }
/* 263 */       maxPage = minPage + SHOW_PAGES.intValue() - 1;
/*     */     }
/* 265 */     if (maxPage > totalPages) {
/* 266 */       maxPage = totalPages;
/*     */     }
/* 268 */     Integer[] arrs = new Integer[maxPage - minPage + 1];
/* 269 */     for (int i = 0; i < arrs.length; i++) {
/* 270 */       arrs[i] = Integer.valueOf(minPage + i);
/*     */     }
/* 272 */     return arrs;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.page.PageBean
 * JD-Core Version:    0.6.2
 */