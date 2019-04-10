/*    */ package com.hotent.core.table;
/*    */ 
/*    */ public class SqlTypeConst
/*    */ {
/*    */   public static final String ORACLE = "oracle";
/*    */   public static final String MYSQL = "mysql";
/*    */   public static final String SQLSERVER = "mssql";
/*    */   public static final String KINGBASE = "kingbase";
/*    */   public static final String DB2 = "db2";
/*    */   public static final String DERBY = "derby";
/*    */   public static final String HBASE = "hbase";
/*    */   public static final String HIVE = "hive";
/*    */   public static final String H2 = "h2";
/*    */   public static final String JTDS = "jtds";
/*    */   public static final String MOCK = "mock";
/*    */   public static final String HSQL = "hsql";
/*    */   public static final String POSTGRESQL = "postgresql";
/*    */   public static final String SYBASE = "sybase";
/*    */   public static final String SQLITE = "sqlite";
/*    */   public static final String MCKOI = "mckoi";
/*    */   public static final String CLOUDSCAPE = "cloudscape";
/*    */   public static final String INFORMIX = "informix";
/*    */   public static final String TIMESTEN = "timesten";
/*    */   public static final String AS400 = "as400";
/*    */   public static final String SAPDB = "sapdb";
/*    */   public static final String JSQLCONNECT = "JSQLConnect";
/*    */   public static final String JTURBO = "JTurbo";
/*    */   public static final String FIREBIRDSQL = "firebirdsql";
/*    */   public static final String INTERBASE = "interbase";
/*    */   public static final String POINTBASE = "pointbase";
/*    */   public static final String EDBC = "edbc";
/*    */   public static final String MIMER = "mimer";
/*    */   public static final String DM = "dm";
/*    */   private static final String INGRES = "ingres";
/*    */ 
/*    */   public static String getDbType(String rawUrl)
/*    */   {
/* 41 */     if (rawUrl == null) {
/* 42 */       return null;
/*    */     }
/* 44 */     if (rawUrl.startsWith("jdbc:derby:"))
/* 45 */       return "derby";
/* 46 */     if (rawUrl.startsWith("jdbc:mysql:"))
/* 47 */       return "mysql";
/* 48 */     if (rawUrl.startsWith("jdbc:oracle:"))
/* 49 */       return "oracle";
/* 50 */     if ((rawUrl.startsWith("jdbc:microsoft:")) || (rawUrl.startsWith("jdbc:sqlserver:")))
/* 51 */       return "mssql";
/* 52 */     if (rawUrl.startsWith("jdbc:sybase:Tds:"))
/* 53 */       return "sybase";
/* 54 */     if (rawUrl.startsWith("jdbc:jtds:"))
/* 55 */       return "jtds";
/* 56 */     if ((rawUrl.startsWith("jdbc:fake:")) || (rawUrl.startsWith("jdbc:mock:")))
/* 57 */       return "mock";
/* 58 */     if (rawUrl.startsWith("jdbc:postgresql:"))
/* 59 */       return "postgresql";
/* 60 */     if (rawUrl.startsWith("jdbc:hsqldb:"))
/* 61 */       return "hsql";
/* 62 */     if (rawUrl.startsWith("jdbc:db2:"))
/* 63 */       return "db2";
/* 64 */     if (rawUrl.startsWith("jdbc:sqlite:"))
/* 65 */       return "sqlite";
/* 66 */     if (rawUrl.startsWith("jdbc:ingres:"))
/* 67 */       return "ingres";
/* 68 */     if (rawUrl.startsWith("jdbc:h2:"))
/* 69 */       return "h2";
/* 70 */     if (rawUrl.startsWith("jdbc:mckoi:"))
/* 71 */       return "mckoi";
/* 72 */     if (rawUrl.startsWith("jdbc:cloudscape:"))
/* 73 */       return "cloudscape";
/* 74 */     if (rawUrl.startsWith("jdbc:informix-sqli:"))
/* 75 */       return "informix";
/* 76 */     if (rawUrl.startsWith("jdbc:timesten:"))
/* 77 */       return "timesten";
/* 78 */     if (rawUrl.startsWith("jdbc:as400:"))
/* 79 */       return "as400";
/* 80 */     if (rawUrl.startsWith("jdbc:sapdb:"))
/* 81 */       return "sapdb";
/* 82 */     if (rawUrl.startsWith("jdbc:JSQLConnect:"))
/* 83 */       return "JSQLConnect";
/* 84 */     if (rawUrl.startsWith("jdbc:JTurbo:"))
/* 85 */       return "JTurbo";
/* 86 */     if (rawUrl.startsWith("jdbc:firebirdsql:"))
/* 87 */       return "firebirdsql";
/* 88 */     if (rawUrl.startsWith("jdbc:interbase:"))
/* 89 */       return "interbase";
/* 90 */     if (rawUrl.startsWith("jdbc:pointbase:"))
/* 91 */       return "pointbase";
/* 92 */     if (rawUrl.startsWith("jdbc:edbc:"))
/* 93 */       return "edbc";
/* 94 */     if (rawUrl.startsWith("jdbc:mimer:multi1:"))
/* 95 */       return "mimer";
/* 96 */     if (rawUrl.startsWith("jdbc:dm:")) {
/* 97 */       return "dm";
/*    */     }
/* 99 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.table.SqlTypeConst
 * JD-Core Version:    0.6.2
 */