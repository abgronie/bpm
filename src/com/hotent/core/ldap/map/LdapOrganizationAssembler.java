/*    */ package com.hotent.core.ldap.map;
/*    */ 
/*    */ import com.hotent.core.ldap.model.LdapOrganization;
/*    */ import com.hotent.core.ldap.model.LdapUser;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.springframework.ldap.core.ContextAssembler;
/*    */ import org.springframework.ldap.core.DirContextOperations;
/*    */ 
/*    */ public class LdapOrganizationAssembler
/*    */   implements ContextAssembler
/*    */ {
/*    */   public Object mapFromContext(Object context)
/*    */   {
/* 19 */     DirContextOperations ctx = (DirContextOperations)context;
/* 20 */     LdapOrganization org = new LdapOrganization();
/* 21 */     org.setBusinessCategory(ctx.getStringAttribute(LdapOrganization.KEY_BUSINESSCATEGORY));
/* 22 */     org.setDescription(ctx.getStringAttribute(LdapOrganization.KEY_DESCRIPTION));
/* 23 */     org.setDistinguishedName(ctx.getStringAttribute(LdapOrganization.KEY_DISTINGUISHEDNAME));
/* 24 */     org.setFacsimileTelephoneNumber(ctx.getStringAttribute(LdapOrganization.KEY_FACSIMILETELEPHONENUMBER));
/* 25 */     org.setName(ctx.getStringAttribute(LdapOrganization.KEY_NAME));
/* 26 */     org.setO(ctx.getStringAttribute(LdapOrganization.KEY_O));
/* 27 */     org.setPostalAddress(ctx.getStringAttribute(LdapOrganization.KEY_POSTALADDRESS));
/* 28 */     org.setPostalCode(ctx.getStringAttribute(LdapOrganization.KEY_POSTALCODE));
/* 29 */     org.setRegisteredAddress(ctx.getStringAttribute(LdapOrganization.KEY_REGISTEREDADDRESS));
/* 30 */     org.setSt(ctx.getStringAttribute(LdapOrganization.KEY_ST));
/* 31 */     org.setStreet(ctx.getStringAttribute(LdapOrganization.KEY_STREET));
/* 32 */     org.setTelephoneNumber(ctx.getStringAttribute(LdapOrganization.KEY_TELEPHONENUMBER));
/* 33 */     org.setTelexNumber(ctx.getStringAttribute(LdapOrganization.KEY_TELEXNUMBER));
/* 34 */     org.setuSNChanged(ctx.getStringAttribute(LdapOrganization.KEY_USNCHANGED));
/* 35 */     org.setuSNCreated(ctx.getStringAttribute(LdapOrganization.KEY_USNCREATED));
/* 36 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
/* 37 */     Date createDate = null;
/* 38 */     Date changeDate = null;
/*    */     try {
/* 40 */       changeDate = dateFormat.parse(ctx.getStringAttribute(LdapOrganization.KEY_WHENCHANGED));
/* 41 */       createDate = dateFormat.parse(ctx.getStringAttribute(LdapOrganization.KEY_WHENCREATED));
/*    */     } catch (ParseException e) {
/* 43 */       e.printStackTrace();
/*    */     }
/* 45 */     org.setWhenChanged(changeDate);
/* 46 */     org.setWhenCreated(createDate);
/* 47 */     return org;
/*    */   }
/*    */ 
/*    */   public void mapToContext(Object obj, Object context)
/*    */   {
/* 52 */     LdapUser user = (LdapUser)obj;
/* 53 */     DirContextOperations ctx = (DirContextOperations)context;
/* 54 */     ctx.setAttributeValues("objectclass", new String[] { "top", LdapUser.OBJECTCLASS });
/* 55 */     ctx.setAttributeValue(LdapUser.KEY_CN, user.getCn());
/* 56 */     ctx.setAttributeValue(LdapUser.KEY_C, user.getC());
/* 57 */     ctx.setAttributeValue(LdapUser.KEY_NAME, user.getName());
/* 58 */     ctx.setAttributeValue(LdapUser.KEY_DESCRIPTION, user.getDescription());
/* 59 */     ctx.setAttributeValue(LdapUser.KEY_TELEPHONENUMBER, user.getTelephoneNumber());
/* 60 */     ctx.setAttributeValue(LdapUser.KEY_MAIL, user.getMail());
/* 61 */     ctx.setAttributeValue(LdapUser.KEY_HOMEPHONE, user.getHomePhone());
/* 62 */     ctx.setAttributeValue(LdapUser.KEY_SAMACCOUNTNAME, user.getsAMAccountName());
/* 63 */     ctx.setAttributeValue(LdapUser.KEY_USERACCOUNTCONTROL, user.getUserAccountControl());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.map.LdapOrganizationAssembler
 * JD-Core Version:    0.6.2
 */