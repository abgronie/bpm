/*     */ package com.hotent.core.excel.style;
/*     */ 
/*     */ public enum Color
/*     */ {
/*  13 */   AUTOMATIC((short)64), 
/*     */ 
/*  18 */   AQUA((short)49), 
/*     */ 
/*  23 */   BLACK((short)8), 
/*     */ 
/*  28 */   BLUE((short)12), 
/*     */ 
/*  33 */   BLUE_GREY((short)54), 
/*     */ 
/*  38 */   BRIGHT_GREEN((short)11), 
/*     */ 
/*  43 */   BROWN((short)60), 
/*     */ 
/*  48 */   CORAL((short)29), 
/*     */ 
/*  53 */   CORNFLOWER_BLUE((short)24), 
/*     */ 
/*  58 */   DARK_BLUE((short)18), 
/*     */ 
/*  63 */   DARK_GREEN((short)58), 
/*     */ 
/*  68 */   DARK_RED((short)16), 
/*     */ 
/*  73 */   DARK_TEAL((short)56), 
/*     */ 
/*  78 */   DARK_YELLOW((short)19), 
/*     */ 
/*  83 */   GOLD((short)51), 
/*     */ 
/*  88 */   GREEN((short)17), 
/*     */ 
/*  93 */   GREY_25_PERCENT((short)22), 
/*     */ 
/*  98 */   GREY_40_PERCENT((short)55), 
/*     */ 
/* 103 */   GREY_50_PERCENT((short)23), 
/*     */ 
/* 108 */   GREY_80_PERCENT((short)63), 
/*     */ 
/* 113 */   INDIGO((short)62), 
/*     */ 
/* 118 */   LAVENDER((short)46), 
/*     */ 
/* 123 */   LEMON_CHIFFON((short)26), 
/*     */ 
/* 128 */   LIGHT_BLUE((short)48), 
/*     */ 
/* 133 */   LIGHT_CORNFLOWER_BLUE((short)31), 
/*     */ 
/* 138 */   LIGHT_GREEN((short)42), 
/*     */ 
/* 143 */   LIGHT_ORANGE((short)52), 
/*     */ 
/* 148 */   LIGHT_TURQUOISE((short)41), 
/*     */ 
/* 153 */   LIGHT_YELLOW((short)43), 
/*     */ 
/* 158 */   LIME((short)50), 
/*     */ 
/* 163 */   MAROON((short)25), 
/*     */ 
/* 168 */   OLIVE_GREEN((short)59), 
/*     */ 
/* 173 */   ORANGE((short)53), 
/*     */ 
/* 178 */   ORCHID((short)28), 
/*     */ 
/* 183 */   PALE_BLUE((short)44), 
/*     */ 
/* 188 */   PINK((short)14), 
/*     */ 
/* 193 */   PLUM((short)61), 
/*     */ 
/* 198 */   RED((short)10), 
/*     */ 
/* 203 */   ROSE((short)45), 
/*     */ 
/* 208 */   ROYAL_BLUE((short)30), 
/*     */ 
/* 213 */   SEA_GREEN((short)57), 
/*     */ 
/* 218 */   SKY_BLUE((short)40), 
/*     */ 
/* 223 */   TAN((short)47), 
/*     */ 
/* 228 */   TEAL((short)21), 
/*     */ 
/* 233 */   TURQUOISE((short)15), 
/*     */ 
/* 238 */   VIOLET((short)20), 
/*     */ 
/* 243 */   WHITE((short)9), 
/*     */ 
/* 248 */   YELLOW((short)13);
/*     */ 
/*     */   private short index;
/*     */ 
/*     */   private Color(short index) {
/* 253 */     this.index = index;
/*     */   }
/*     */ 
/*     */   public short getIndex() {
/* 257 */     return this.index;
/*     */   }
/*     */ 
/*     */   public static Color instance(short index)
/*     */   {
/* 266 */     for (Color e : values()) {
/* 267 */       if (e.getIndex() == index) {
/* 268 */         return e;
/*     */       }
/*     */     }
/* 271 */     return AUTOMATIC;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.excel.style.Color
 * JD-Core Version:    0.6.2
 */