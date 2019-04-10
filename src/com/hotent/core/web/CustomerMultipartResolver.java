/*    */ package com.hotent.core.web;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

/*    */ import javax.servlet.http.HttpServletRequest;

/*    */ import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
/*    */ import org.apache.commons.fileupload.FileUploadException;
/*    */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*    */ import org.springframework.web.multipart.MultipartException;
/*    */ import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/*    */ 
/*    */ public class CustomerMultipartResolver extends CommonsMultipartResolver
/*    */ {
/* 28 */   public static String SizeLimitExceededException = "SizeLimitExceededException";
/*    */ 
/*    */   protected MultipartParsingResult parseRequest(HttpServletRequest request)
/*    */     throws MultipartException
/*    */   {
/* 34 */     String encoding = determineEncoding(request);
/* 35 */     FileUpload fileUpload = prepareFileUpload(encoding);
/*    */     try {
/* 37 */       List fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
/* 38 */       return parseFileItems(fileItems, encoding);
/*    */     } catch (FileUploadBase.SizeLimitExceededException ex) {
/* 40 */       request.setAttribute(SizeLimitExceededException, "true");
/* 41 */       return parseFileItems(new ArrayList(), encoding);
/*    */     } catch (FileUploadException ex) {
/* 43 */       throw new MultipartException("Could not parse multipart servlet request", ex);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.CustomerMultipartResolver
 * JD-Core Version:    0.6.2
 */