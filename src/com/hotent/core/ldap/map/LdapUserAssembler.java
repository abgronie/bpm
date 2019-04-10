/*    */ package com.hotent.core.ldap.map;
/*    */ 
/*    */ import com.hotent.core.ldap.model.LdapUser;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.springframework.ldap.core.ContextAssembler;
/*    */ import org.springframework.ldap.core.DirContextOperations;
/*    */ 
/*    */ public class LdapUserAssembler
/*    */   implements ContextAssembler
/*    */ {
/*    */   public Object mapFromContext(Object context)
/*    */   {
/* 17 */     DirContextOperations ctx = (DirContextOperations)context;
/* 18 */     LdapUser user = new LdapUser();
/* 19 */     user.setC(ctx.getStringAttribute(LdapUser.KEY_C));
/* 20 */     user.setCn(ctx.getStringAttribute(LdapUser.KEY_CN));
/* 21 */     user.setCo(ctx.getStringAttribute(LdapUser.KEY_CN));
/* 22 */     user.setCompany(ctx.getStringAttribute(LdapUser.KEY_COMPANY));
/* 23 */     user.setCountryCode(ctx.getStringAttribute(LdapUser.KEY_COUNTRYCODE));
/* 24 */     user.setDepartment(ctx.getStringAttribute(LdapUser.KEY_DEPARTMENT));
/* 25 */     user.setDescription(ctx.getStringAttribute(LdapUser.KEY_DESCRIPTION));
/* 26 */     user.setDisplayName(ctx.getStringAttribute(LdapUser.KEY_DISPLAYNAME));
/* 27 */     user.setDistinguishedName(ctx.getStringAttribute(LdapUser.KEY_DISTINGUISHEDNAME));
/* 28 */     user.setFacsimileTelephoneNumber(ctx.getStringAttribute(LdapUser.KEY_FACSIMILETELEPHONENUMBER));
/* 29 */     user.setHomePhone(ctx.getStringAttribute(LdapUser.KEY_HOMEPHONE));
/* 30 */     user.setInitials(ctx.getStringAttribute(LdapUser.KEY_INITIALS)); user.setName(ctx.getStringAttribute(LdapUser.KEY_NAME));
/* 31 */     user.setL(ctx.getStringAttribute(LdapUser.KEY_L));
/* 32 */     user.setMail(ctx.getStringAttribute(LdapUser.KEY_MAIL));
/* 33 */     user.setName(ctx.getStringAttribute(LdapUser.KEY_NAME));
/* 34 */     user.setGivenName(ctx.getStringAttribute(LdapUser.KEY_GIVENNAME));
/* 35 */     user.setPostalAddress(ctx.getStringAttribute(LdapUser.KEY_POSTALADDRESS));
/* 36 */     user.setPostalCode(ctx.getStringAttribute(LdapUser.KEY_POSTALCODE));
/* 37 */     user.setPostOfficeBox(ctx.getStringAttribute(LdapUser.KEY_POSTOFFICEBOX));
/* 38 */     user.setsAMAccountName(ctx.getStringAttribute(LdapUser.KEY_SAMACCOUNTNAME));
/* 39 */     user.setSt(ctx.getStringAttribute(LdapUser.KEY_ST));
/* 40 */     user.setStreetAddress(ctx.getStringAttribute(LdapUser.KEY_STREETADDRESS));
/* 41 */     user.setTelephoneNumber(ctx.getStringAttribute(LdapUser.KEY_TELEPHONENUMBER));
/* 42 */     user.setSn(ctx.getStringAttribute(LdapUser.KEY_SN));
/* 43 */     user.setTelephoneNumber(ctx.getStringAttribute(LdapUser.KEY_TELEPHONENUMBER));
/* 44 */     user.setTitle(ctx.getStringAttribute(LdapUser.KEY_TITLE));
/* 45 */     user.setUserPrincipalName(ctx.getStringAttribute(LdapUser.KEY_USERPRINCIPALNAME));
/* 46 */     user.setUserAccountControl(ctx.getStringAttribute(LdapUser.KEY_USERACCOUNTCONTROL));
/* 47 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
/* 48 */     Date createDate = null;
/* 49 */     Date changeDate = null;
/*    */     try {
/* 51 */       changeDate = dateFormat.parse(ctx.getStringAttribute(LdapUser.KEY_WHENCHANGED));
/* 52 */       createDate = dateFormat.parse(ctx.getStringAttribute(LdapUser.KEY_WHENCREATED));
/*    */     } catch (ParseException e) {
/* 54 */       e.printStackTrace();
/*    */     }
/* 56 */     user.setWhenChanged(changeDate);
/* 57 */     user.setWhenCreated(createDate);
/* 58 */     user.setwWWHomePage(ctx.getStringAttribute(LdapUser.KEY_WWWHOMEPAGE));
/* 59 */     return user;
/*    */   }
/*    */ 
/*    */   public void mapToContext(Object obj, Object context)
/*    */   {
/* 64 */     LdapUser user = (LdapUser)obj;
/* 65 */     DirContextOperations ctx = (DirContextOperations)context;
/* 66 */     ctx.setAttributeValues("objectclass", new String[] { "top", LdapUser.OBJECTCLASS });
/* 67 */     ctx.setAttributeValue(LdapUser.KEY_CN, user.getCn());
/* 68 */     ctx.setAttributeValue(LdapUser.KEY_C, user.getC());
/* 69 */     ctx.setAttributeValue(LdapUser.KEY_NAME, user.getName());
/* 70 */     ctx.setAttributeValue(LdapUser.KEY_DESCRIPTION, user.getDescription());
/* 71 */     ctx.setAttributeValue(LdapUser.KEY_TELEPHONENUMBER, user.getTelephoneNumber());
/* 72 */     ctx.setAttributeValue(LdapUser.KEY_MAIL, user.getMail());
/* 73 */     ctx.setAttributeValue(LdapUser.KEY_HOMEPHONE, user.getHomePhone());
/* 74 */     ctx.setAttributeValue(LdapUser.KEY_SAMACCOUNTNAME, user.getsAMAccountName());
/* 75 */     ctx.setAttributeValue(LdapUser.KEY_USERACCOUNTCONTROL, user.getUserAccountControl());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.map.LdapUserAssembler
 * JD-Core Version:    0.6.2
 */