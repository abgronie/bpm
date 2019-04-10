/*    */ package com.hotent.core.consts;
/*    */ 
/*    */ import org.springframework.security.access.ConfigAttribute;
/*    */ import org.springframework.security.access.SecurityConfig;
/*    */ import org.springframework.security.core.GrantedAuthority;
/*    */ import org.springframework.security.core.authority.GrantedAuthorityImpl;
/*    */ 
/*    */ public class SystemConst
/*    */ {
/*    */   private static final String ROLE_SUPER = "ROLE_SUPER";
/*    */   private static final String ROLE_PUBLIC = "ROLE_PUBLIC";
/*    */   private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
/* 14 */   public static final GrantedAuthority ROLE_GRANT_SUPER = new GrantedAuthorityImpl("ROLE_SUPER");
/* 15 */   public static final ConfigAttribute ROLE_CONFIG_PUBLIC = new SecurityConfig("ROLE_PUBLIC");
/* 16 */   public static final ConfigAttribute ROLE_CONFIG_ANONYMOUS = new SecurityConfig("ROLE_ANONYMOUS");
/*    */   private static final long serialVersionUID = 1L;
/* 20 */   public static final Long BEGIN_DEMID = Long.valueOf(1L);
/* 21 */   public static final Long BEGIN_ORGID = Long.valueOf(1L);
/* 22 */   public static final Integer BEGIN_DEPTH = Integer.valueOf(0);
/*    */   public static final String BEGIN_PATH = "1";
/* 24 */   public static final Short BEGIN_TYPE = Short.valueOf((short)1);
/* 25 */   public static final Long BEGIN_ORGSUPID = Long.valueOf(-1L);
/* 26 */   public static final Short BEGIN_SN = Short.valueOf((short)1);
/* 27 */   public static final Short BEGIN_FROMTYPE = Short.valueOf((short)0);
/*    */   public static final String SEARCH_BY_ROL = "1";
/*    */   public static final String SEARCH_BY_ORG = "2";
/*    */   public static final String SEARCH_BY_POS = "3";
/*    */   public static final String SEARCH_BY_ONL = "4";
/* 38 */   public static final Short UN_LOCKED = Short.valueOf((short)0);
/*    */ 
/* 42 */   public static final Short LOCKED = Short.valueOf((short)1);
/*    */ 
/* 47 */   public static final Short UN_EXPIRED = Short.valueOf((short)0);
/*    */ 
/* 52 */   public static final Short EXPIRED = Short.valueOf((short)1);
/*    */ 
/* 57 */   public static final Short STATUS_OK = Short.valueOf((short)1);
/*    */ 
/* 62 */   public static final Short STATUS_NO = Short.valueOf((short)0);
/*    */ 
/* 66 */   public static final Short STATUS_Del = Short.valueOf((short)-1);
/*    */ 
/* 71 */   public static final Short FROMTYPE_DB = Short.valueOf((short)0);
/*    */ 
/* 75 */   public static final Short FROMTYPE_AD = Short.valueOf((short)1);
/*    */ 
/* 79 */   public static final Short FROMTYPE_AD_SET = Short.valueOf((short)2);
/*    */ 
/* 83 */   public static final Long SYSTEMUSERID = Long.valueOf(0L);
/*    */   public static final String SYSTEMUSERNAME = "系统";
/*    */   public static final String CGLIB_PREFIX = "$cglib_prop_";
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.consts.SystemConst
 * JD-Core Version:    0.6.2
 */