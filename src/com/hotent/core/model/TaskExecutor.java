/*     */ package com.hotent.core.model;
/*     */ 
/*     */ import com.hotent.core.api.org.ISysUserService;
/*     */ import com.hotent.core.api.org.model.ISysUser;
/*     */ import com.hotent.core.util.AppUtil;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.UnsatisfiedDependencyException;
/*     */ 
/*     */ public class TaskExecutor
/*     */   implements Serializable
/*     */ {
/*     */   public static final int EXACT_NOEXACT = 0;
/*     */   public static final int EXACT_EXACT_USER = 1;
/*     */   public static final int EXACT_EXACT_SECOND = 2;
/*     */   public static final int EXACT_USER_GROUP = 3;
/*     */   private static final long serialVersionUID = 10001L;
/*     */   public static final String USER_TYPE_USER = "user";
/*     */   public static final String USER_TYPE_ORG = "org";
/*     */   public static final String USER_TYPE_ROLE = "role";
/*     */   public static final String USER_TYPE_POS = "pos";
/*     */   public static final String USER_TYPE_JOB = "job";
/*     */   public static final String USER_TYPE_USERGROUP = "group";
/*  85 */   private String type = "user";
/*     */   public String mainOrgName;
/*  93 */   private String executeId = "";
/*     */ 
/*  98 */   private String executor = "";
/*     */ 
/* 107 */   private int exactType = 0;
/*     */ 
/*     */   public TaskExecutor()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TaskExecutor(String executeId)
/*     */   {
/* 116 */     Long userId = Long.valueOf(Long.parseLong(executeId));
/* 117 */     ISysUserService sysUserService = (ISysUserService)AppUtil.getBean(ISysUserService.class);
/* 118 */     ISysUser sysUser = sysUserService.getById(userId);
/* 119 */     this.executeId = executeId;
/* 120 */     this.executor = sysUser.getFullname();
/*     */   }
/*     */ 
/*     */   public TaskExecutor(String type, String executeId, String name)
/*     */   {
/* 130 */     this.type = type;
/* 131 */     this.executeId = executeId;
/* 132 */     this.executor = name;
/* 133 */     if ("group".equalsIgnoreCase(type))
/* 134 */       this.exactType = 3;
/*     */   }
/*     */ 
/*     */   public static TaskExecutor getTaskUser(String executeId, String name)
/*     */   {
/* 144 */     return new TaskExecutor("user", executeId, name);
/*     */   }
/*     */ 
/*     */   public static TaskExecutor getTaskOrg(String executeId, String name)
/*     */   {
/* 153 */     return new TaskExecutor("org", executeId, name);
/*     */   }
/*     */ 
/*     */   public static TaskExecutor getTaskRole(String executeId, String name)
/*     */   {
/* 162 */     return new TaskExecutor("role", executeId, name);
/*     */   }
/*     */ 
/*     */   public static TaskExecutor getTaskJob(String executeId, String name)
/*     */   {
/* 173 */     return new TaskExecutor("job", executeId, name);
/*     */   }
/*     */ 
/*     */   public static TaskExecutor getTaskPos(String executeId, String name)
/*     */   {
/* 183 */     return new TaskExecutor("pos", executeId, name);
/*     */   }
/*     */ 
/*     */   public static TaskExecutor getTaskUserGroup(String executeId, String name)
/*     */   {
/* 194 */     TaskExecutor ex = new TaskExecutor("group", executeId, name);
/* 195 */     ex.setExactType(3);
/* 196 */     return ex;
/*     */   }
/*     */ 
/*     */   public String getType() {
/* 200 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/* 204 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getExecuteId() {
/* 208 */     return this.executeId;
/*     */   }
/*     */ 
/*     */   public void setExecuteId(String executeId) {
/* 212 */     this.executeId = executeId;
/*     */   }
/*     */ 
/*     */   public String getExecutor() {
/* 216 */     return this.executor;
/*     */   }
/*     */ 
/*     */   public void setExecutor(String executor) {
/* 220 */     this.executor = executor;
/*     */   }
/*     */ 
/*     */   public int getExactType()
/*     */   {
/* 226 */     return this.exactType;
/*     */   }
/*     */ 
/*     */   public void setExactType(int exactType) {
/* 230 */     this.exactType = exactType;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 235 */     if (!(obj instanceof TaskExecutor)) {
/* 236 */       return false;
/*     */     }
/* 238 */     TaskExecutor tmp = (TaskExecutor)obj;
/* 239 */     if ((this.type.equals(tmp.getType())) && (this.executeId.equals(tmp.getExecuteId()))) {
/* 240 */       return true;
/*     */     }
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 247 */     String tmp = this.type + this.executeId;
/* 248 */     return tmp.hashCode();
/*     */   }
/*     */ 
/*     */   public Set<ISysUser> getSysUser() throws UnsatisfiedDependencyException {
/* 252 */     Set sysUsers = new HashSet();
/* 253 */     if (AppUtil.getContext() == null) {
/* 254 */       throw new UnsatisfiedDependencyException("Convert Executor to SysUser dependency ApplicationContext", "applicationContext", "", "Convert Executor to SysUser dependency ApplicationContext");
/*     */     }
/* 256 */     ISysUserService sysUserService = (ISysUserService)AppUtil.getBean(ISysUserService.class);
/* 257 */     if ("user".equals(this.type)) {
/* 258 */       ISysUser sysUser = sysUserService.getById(Long.valueOf(this.executeId));
/* 259 */       sysUsers.add(sysUser);
/* 260 */     } else if (("org".equals(this.type)) && ("pos".equals(this.type)) && ("role".equals(this.type)))
/*     */     {
/* 263 */       List users = sysUserService.getByGroup(Long.valueOf(this.executeId), this.type);
/* 264 */       sysUsers.addAll(users);
/*     */     }
/* 266 */     return sysUsers;
/*     */   }
/*     */ 
/*     */   public String getMainOrgName() {
/* 270 */     return this.mainOrgName;
/*     */   }
/*     */ 
/*     */   public void setMainOrgName(String mainOrgName) {
/* 274 */     this.mainOrgName = mainOrgName;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.model.TaskExecutor
 * JD-Core Version:    0.6.2
 */