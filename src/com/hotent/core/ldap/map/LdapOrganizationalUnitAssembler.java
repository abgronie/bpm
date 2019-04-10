/*    */ package com.hotent.core.ldap.map;
/*    */ 
/*    */ import com.hotent.core.ldap.model.LdapOrganizationalUnit;
/*    */ import com.hotent.core.ldap.model.LdapUser;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.springframework.ldap.core.ContextAssembler;
/*    */ import org.springframework.ldap.core.DirContextOperations;
/*    */ 
/*    */ public class LdapOrganizationalUnitAssembler
/*    */   implements ContextAssembler
/*    */ {
/*    */   public Object mapFromContext(Object context)
/*    */   {
/* 20 */     DirContextOperations ctx = (DirContextOperations)context;
/* 21 */     LdapOrganizationalUnit org = new LdapOrganizationalUnit();
/* 22 */     org.setBusinessCategory(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_BUSINESSCATEGORY));
/* 23 */     org.setDescription(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_DESCRIPTION));
/* 24 */     org.setDistinguishedName(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_DISTINGUISHEDNAME));
/* 25 */     org.setFacsimileTelephoneNumber(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_FACSIMILETELEPHONENUMBER));
/* 26 */     org.setName(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_NAME));
/* 27 */     org.setOu(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_OU));
/* 28 */     org.setPostalAddress(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_POSTALADDRESS));
/* 29 */     org.setPostalCode(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_POSTALCODE));
/* 30 */     org.setRegisteredAddress(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_REGISTEREDADDRESS));
/* 31 */     org.setSt(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_ST));
/* 32 */     org.setStreet(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_STREET));
/* 33 */     org.setTelephoneNumber(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_TELEPHONENUMBER));
/* 34 */     org.setTelexNumber(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_TELEXNUMBER));
/* 35 */     org.setuSNChanged(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_USNCHANGED));
/* 36 */     org.setuSNCreated(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_USNCREATED));
/* 37 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
/* 38 */     Date createDate = null;
/* 39 */     Date changeDate = null;
/*    */     try {
/* 41 */       changeDate = dateFormat.parse(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_WHENCHANGED));
/* 42 */       createDate = dateFormat.parse(ctx.getStringAttribute(LdapOrganizationalUnit.KEY_WHENCREATED));
/*    */     } catch (ParseException e) {
/* 44 */       e.printStackTrace();
/*    */     }
/* 46 */     org.setWhenChanged(changeDate);
/* 47 */     org.setWhenCreated(createDate);
/* 48 */     return org;
/*    */   }
/*    */ 
/*    */   public void mapToContext(Object obj, Object context)
/*    */   {
/* 53 */     LdapUser user = (LdapUser)obj;
/* 54 */     DirContextOperations ctx = (DirContextOperations)context;
/* 55 */     ctx.setAttributeValues("objectclass", new String[] { "top", LdapUser.OBJECTCLASS });
/* 56 */     ctx.setAttributeValue(LdapUser.KEY_CN, user.getCn());
/* 57 */     ctx.setAttributeValue(LdapUser.KEY_C, user.getC());
/* 58 */     ctx.setAttributeValue(LdapUser.KEY_NAME, user.getName());
/* 59 */     ctx.setAttributeValue(LdapUser.KEY_DESCRIPTION, user.getDescription());
/* 60 */     ctx.setAttributeValue(LdapUser.KEY_TELEPHONENUMBER, user.getTelephoneNumber());
/* 61 */     ctx.setAttributeValue(LdapUser.KEY_MAIL, user.getMail());
/* 62 */     ctx.setAttributeValue(LdapUser.KEY_HOMEPHONE, user.getHomePhone());
/* 63 */     ctx.setAttributeValue(LdapUser.KEY_SAMACCOUNTNAME, user.getsAMAccountName());
/* 64 */     ctx.setAttributeValue(LdapUser.KEY_USERACCOUNTCONTROL, user.getUserAccountControl());
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.ldap.map.LdapOrganizationalUnitAssembler
 * JD-Core Version:    0.6.2
 */