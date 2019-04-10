/*     */ package com.hotent.core.model;
/*     */ 
/*     */ import com.hotent.core.api.org.model.ISysUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MessageModel
/*     */   implements Serializable
/*     */ {
            //by byq 发起人
            private String startUser;
            // 节点名称
            private String nodeName;
/*     */   private ISysUser receiveUser;
/*     */   private ISysUser sendUser;
/*     */   private String informType;
/*     */   private Map<String, String> templateMap;
/*     */   private String content;
/*     */   private String subject;
/*     */   private String opinion;
/*     */   private Date sendDate;
/*     */   private Long extId;
/*     */   private boolean isTask;
/*     */   private Map vars;
/*     */   private String[] to;
/*     */   private String[] cc;
/*     */   private String[] bcc;
/*     */ 
/*     */   public MessageModel(String informType)
/*     */   {
/*  54 */     this.informType = informType;
/*     */   }
/*     */ 
/*     */   public boolean getIsTask()
/*     */   {
/*  59 */     return this.isTask;
/*     */   }
/*     */ 
/*     */   public void setIsTask(boolean isTask) {
/*  63 */     this.isTask = isTask;
/*     */   }
/*     */ 
/*     */   public Date getSendDate()
/*     */   {
/*  69 */     if (this.sendDate == null)
/*  70 */       return new Date();
/*  71 */     return this.sendDate;
/*     */   }
/*     */ 
/*     */   public void setSendDate(Date sendDate)
/*     */   {
/*  78 */     this.sendDate = sendDate;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getTemplateMap()
/*     */   {
/*  85 */     return this.templateMap;
/*     */   }
/*     */ 
/*     */   public void setTemplateMap(Map<String, String> templateMap)
/*     */   {
/*  92 */     this.templateMap = templateMap;
/*     */   }
/*     */ 
/*     */   public ISysUser getReceiveUser()
/*     */   {
/*  99 */     return this.receiveUser;
/*     */   }
/*     */ 
/*     */   public void setReceiveUser(ISysUser receiveUser)
/*     */   {
/* 106 */     this.receiveUser = receiveUser;
/*     */   }
/*     */ 
/*     */   public ISysUser getSendUser()
/*     */   {
/* 113 */     return this.sendUser;
/*     */   }
/*     */ 
/*     */   public void setSendUser(ISysUser sendUser)
/*     */   {
/* 120 */     this.sendUser = sendUser;
/*     */   }
/*     */ 
/*     */   public String getInformType()
/*     */   {
/* 127 */     return this.informType;
/*     */   }
/*     */ 
/*     */   public void setInformType(String informType)
/*     */   {
/* 138 */     this.informType = informType;
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 146 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String subject)
/*     */   {
/* 153 */     this.subject = subject;
/*     */   }
/*     */ 
/*     */   public String getOpinion()
/*     */   {
/* 160 */     return this.opinion;
/*     */   }
/*     */ 
/*     */   public void setOpinion(String opinion)
/*     */   {
/* 167 */     this.opinion = opinion;
/*     */   }
/*     */ 
/*     */   public Long getExtId()
/*     */   {
/* 173 */     return this.extId;
/*     */   }
/*     */ 
/*     */   public void setExtId(Long extId) {
/* 177 */     this.extId = extId;
/*     */   }
/*     */ 
/*     */   public Map getVars()
/*     */   {
/* 184 */     return this.vars;
/*     */   }
/*     */ 
/*     */   public void setVars(Map vars)
/*     */   {
/* 191 */     this.vars = vars;
/*     */   }
/*     */ 
/*     */   public String[] getTo()
/*     */   {
/* 198 */     return this.to;
/*     */   }
/*     */ 
/*     */   public void setTo(String[] to)
/*     */   {
/* 205 */     this.to = to;
/*     */   }
/*     */ 
/*     */   public String[] getCc()
/*     */   {
/* 212 */     return this.cc;
/*     */   }
/*     */ 
/*     */   public void setCc(String[] cc)
/*     */   {
/* 219 */     this.cc = cc;
/*     */   }
/*     */ 
/*     */   public String[] getBcc()
/*     */   {
/* 226 */     return this.bcc;
/*     */   }
/*     */ 
/*     */   public void setBcc(String[] bcc)
/*     */   {
/* 233 */     this.bcc = bcc;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/* 237 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 241 */     this.content = content;
/*     */   }

            public String getStartUser() {
                return startUser;
            }

            public void setStartUser(String startUser) {
                this.startUser = startUser;
            }

            public String getNodeName() {
                return nodeName;
            }

            public void setNodeName(String nodeName) {
                this.nodeName = nodeName;
            }
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.MessageModel
 * JD-Core Version:    0.6.2
 */