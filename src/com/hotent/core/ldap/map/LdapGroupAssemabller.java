/*    */ package com.hotent.core.ldap.map;
/*    */ 
/*    */ import com.hotent.core.ldap.model.LdapGroup;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.springframework.ldap.core.ContextAssembler;
/*    */ import org.springframework.ldap.core.DirContextOperations;
/*    */ 
/*    */ public class LdapGroupAssemabller
/*    */   implements ContextAssembler
/*    */ {
/*    */   public Object mapFromContext(Object context)
/*    */   {
/* 20 */     DirContextOperations ctx = (DirContextOperations)context;
/* 21 */     LdapGroup group = new LdapGroup();
/* 22 */     group.setAdminCount(ctx.getStringAttribute("adminCount"));
/* 23 */     group.setCn(ctx.getStringAttribute("cn"));
/* 24 */     group.setDescription(ctx.getStringAttribute("description"));
/* 25 */     group.setDistinguishedName(ctx.getStringAttribute("distinguishedName"));
/* 26 */     group.setInfo(ctx.getStringAttribute("info"));
/* 27 */     group.setMail(ctx.getStringAttribute("mail"));
/* 28 */     group.setMembers(ctx.getStringAttributes("member"));
/* 29 */     group.setName(ctx.getStringAttribute("name"));
/* 30 */     group.setsAMAccountName(ctx.getStringAttribute("sAMAccountName"));
/* 31 */     group.setsAMAccountType(ctx.getStringAttribute("sAMAccountType"));
/* 32 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
/* 33 */     Date createDate = null;
/* 34 */     Date changeDate = null;
/*    */     try {
/* 36 */       changeDate = dateFormat.parse(ctx.getStringAttribute("whenChanged"));
/* 37 */       createDate = dateFormat.parse(ctx.getStringAttribute("whenCreated"));
/*    */     } catch (ParseException e) {
/* 39 */       e.printStackTrace();
/*    */     }
/* 41 */     group.setWhenChanged(changeDate);
/* 42 */     group.setWhenCreated(createDate);
/* 43 */     return group;
/*    */   }
/*    */ 
/*    */   public void mapToContext(Object obj, Object ctx)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.map.LdapGroupAssemabller
 * JD-Core Version:    0.6.2
 */