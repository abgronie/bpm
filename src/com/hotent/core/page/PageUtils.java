/*    */ package com.hotent.core.page;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PageUtils
/*    */ {
/*    */   public static int getFirstNumber(int currentPage, int pageSize)
/*    */   {
/* 25 */     if (pageSize <= 0)
/* 26 */       throw new IllegalArgumentException("[pageSize] must great than zero");
/* 27 */     return (currentPage - 1) * pageSize;
/*    */   }
/*    */ 
/*    */   public static int getLastNumber(int currentPage, int pageSize, int totalCount)
/*    */   {
/* 32 */     int last = currentPage * pageSize;
/* 33 */     if (last > totalCount) return totalCount;
/* 34 */     return last;
/*    */   }
/*    */ 
/*    */   public static List<Integer> getPageNumbers(int currentPage, int totalPage, int count)
/*    */   {
/* 46 */     int avg = count / 2;
/* 47 */     int startPage = currentPage - avg;
/* 48 */     if (startPage <= 0) {
/* 49 */       startPage = 1;
/*    */     }
/* 51 */     int endPage = startPage + count - 1;
/* 52 */     if (endPage > totalPage) {
/* 53 */       endPage = totalPage;
/*    */     }
/* 55 */     if (endPage - startPage < count) {
/* 56 */       startPage = endPage - count;
/* 57 */       if (startPage <= 0) {
/* 58 */         startPage = 1;
/*    */       }
/*    */     }
/* 61 */     List result = new ArrayList();
/* 62 */     for (int i = startPage; i <= endPage; i++) {
/* 63 */       result.add(new Integer(i));
/*    */     }
/* 65 */     return result;
/*    */   }
/*    */ 
/*    */   public static int getTotalPage(int totalCount, int pageSize)
/*    */   {
/* 76 */     int result = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
/* 77 */     if (result <= 1)
/* 78 */       result = 1;
/* 79 */     return result;
/*    */   }
/*    */ 
/*    */   public static int getPageNumber(int currentPage, int pageSize, int totalCount)
/*    */   {
/* 91 */     if (currentPage <= 1)
/*    */     {
/* 93 */       return 1;
/*    */     }
/* 95 */     if ((2147483647 == currentPage) || (currentPage > getTotalPage(totalCount, pageSize)))
/*    */     {
/* 97 */       return getTotalPage(totalCount, pageSize);
/*    */     }
/* 99 */     return currentPage;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.page.PageUtils
 * JD-Core Version:    0.6.2
 */