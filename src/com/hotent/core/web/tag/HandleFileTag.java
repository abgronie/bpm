/*    */ package com.hotent.core.web.tag;
/*    */ 
/*    */ import javax.servlet.jsp.JspException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import net.sf.json.JSONArray;
/*    */ import net.sf.json.JSONObject;
/*    */ 
/*    */ public class HandleFileTag extends TagSupport
/*    */ {
/*    */   private static final long serialVersionUID = 5437113324395505938L;
/*    */   private String fileStr;
/*    */ 
/*    */   public String getFileStr()
/*    */   {
/* 30 */     return this.fileStr;
/*    */   }
/*    */ 
/*    */   public void setFileStr(String fileStr)
/*    */   {
/* 38 */     this.fileStr = fileStr;
/*    */   }
/*    */ 
/*    */   public int doStartTag() throws JspException
/*    */   {
/* 43 */     JspWriter out = this.pageContext.getOut();
/*    */     try {
/* 45 */       if (!"".equals(this.fileStr)) {
/* 46 */         String html = "";
/* 47 */         JSONArray json = JSONArray.fromObject(this.fileStr);
/* 48 */         if (json.size() > 0) {
/* 49 */           for (int i = 0; i < json.size(); i++) {
/* 50 */             JSONObject job = json.getJSONObject(i);
/* 51 */             html = html + "<li style='margin-bottom: 10px;margin-top: 10px;'><span class='attachement-span'>";
/* 52 */             html = html + "<span fileid=' " + job.get("id") + "' name='attach' file='" + job.get("name") + "'>";
/* 53 */             html = html + " <a class='attachment' target='_blank' path='/bpm/platform/system/sysFile/file_" + job.get("id") + ".ht' onclick='AttachMent.handleClickItem(this,&quot;r&quot;)' title='" + job.get("name") + "'>";
/* 54 */             html = html + job.get("name") + "</a></span> <a href='javascript:;' onclick='AttachMent.download(this);' title='下载' class='download'></a></span></li>";
/*    */           }
/*    */         }
/* 57 */         out.println(html);
/*    */       }
/*    */     } catch (Exception ex) {
/* 60 */       ex.printStackTrace();
/*    */     }
/* 62 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.web.tag.HandleFileTag
 * JD-Core Version:    0.6.2
 */