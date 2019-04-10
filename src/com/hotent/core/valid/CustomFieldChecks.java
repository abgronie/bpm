/*     */ package com.hotent.core.valid;
/*     */ 
/*     */ import com.hotent.core.util.DateUtil;
/*     */ import com.hotent.core.util.StringUtil;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.validator.Field;
/*     */ import org.apache.commons.validator.GenericValidator;
/*     */ import org.apache.commons.validator.ValidatorAction;
/*     */ import org.apache.commons.validator.util.ValidatorUtils;
/*     */ import org.springframework.validation.Errors;
/*     */ import org.springmodules.validation.commons.FieldChecks;
/*     */ 
/*     */ public class CustomFieldChecks extends FieldChecks
/*     */ {
/*     */   public static boolean validateEqual(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/*  48 */     String value = extractValue(bean, field);
/*  49 */     String sProperty2 = field.getVarValue("equalTo");
/*  50 */     String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
/*     */ 
/*  52 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/*  54 */         if (!value.equals(value2)) {
/*  55 */           FieldChecks.rejectValue(errors, field, va);
/*  56 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/*  59 */         FieldChecks.rejectValue(errors, field, va);
/*  60 */         return false;
/*     */       }
/*     */     }
/*     */ 
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateDateTime(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean validateRegx(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 117 */     String mask = field.getVarValue("regex");
/* 118 */     String value = extractValue(bean, field);
/*     */     try {
/* 120 */       if ((!GenericValidator.isBlankOrNull(value)) && (!StringUtil.validByRegex(mask, value)))
/*     */       {
/* 122 */         rejectValue(errors, field, va);
/* 123 */         return false;
/*     */       }
/* 125 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 128 */       FieldChecks.rejectValue(errors, field, va);
/* 129 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean validateIsNumber(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 145 */     String value = extractValue(bean, field);
/*     */ 
/* 147 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/* 149 */         if (!StringUtil.isNumberic(value)) {
/* 150 */           FieldChecks.rejectValue(errors, field, va);
/* 151 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 154 */         FieldChecks.rejectValue(errors, field, va);
/* 155 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 159 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateIsDigits(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 173 */     String value = extractValue(bean, field);
/*     */ 
/* 175 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/* 177 */         if (!StringUtil.isInteger(value)) {
/* 178 */           FieldChecks.rejectValue(errors, field, va);
/* 179 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 182 */         FieldChecks.rejectValue(errors, field, va);
/* 183 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateEmail(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 201 */     String value = extractValue(bean, field);
/*     */ 
/* 203 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/* 205 */         if (!StringUtil.isEmail(value)) {
/* 206 */           FieldChecks.rejectValue(errors, field, va);
/* 207 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 210 */         FieldChecks.rejectValue(errors, field, va);
/* 211 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateMax(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 236 */     String value = extractValue(bean, field);
/* 237 */     String max = field.getVarValue("max");
/* 238 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/* 240 */         long lMax = Long.parseLong(max);
/* 241 */         long lValue = Long.parseLong(value);
/* 242 */         if (lValue >= lMax) {
/* 243 */           FieldChecks.rejectValue(errors, field, va);
/* 244 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 247 */         FieldChecks.rejectValue(errors, field, va);
/* 248 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateMin(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 273 */     String value = extractValue(bean, field);
/* 274 */     String min = field.getVarValue("min");
/* 275 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/* 277 */         long lMin = Long.parseLong(min);
/* 278 */         long lValue = Long.parseLong(value);
/* 279 */         if (lValue <= lMin) {
/* 280 */           FieldChecks.rejectValue(errors, field, va);
/* 281 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 284 */         FieldChecks.rejectValue(errors, field, va);
/* 285 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 289 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateRangelength(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 314 */     String value = extractValue(bean, field);
/* 315 */     int len = value.length();
/*     */ 
/* 317 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try {
/* 319 */         int minlength = Integer.parseInt(field.getVarValue("minlength"));
/*     */ 
/* 321 */         int maxlength = Integer.parseInt(field.getVarValue("maxlength"));
/*     */ 
/* 323 */         if ((len < minlength) || (len > maxlength)) {
/* 324 */           FieldChecks.rejectValue(errors, field, va);
/* 325 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 328 */         FieldChecks.rejectValue(errors, field, va);
/* 329 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateUrl(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 347 */     String value = extractValue(bean, field);
/*     */ 
/* 349 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 352 */         if (!StringUtil.isUrl(value)) {
/* 353 */           FieldChecks.rejectValue(errors, field, va);
/* 354 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 357 */         FieldChecks.rejectValue(errors, field, va);
/* 358 */         return false;
/*     */       }
/*     */     }
/* 361 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateMobile(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 375 */     String value = extractValue(bean, field);
/*     */ 
/* 377 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 380 */         if (!StringUtil.isMobile(value)) {
/* 381 */           FieldChecks.rejectValue(errors, field, va);
/* 382 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 385 */         FieldChecks.rejectValue(errors, field, va);
/* 386 */         return false;
/*     */       }
/*     */     }
/* 389 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validatePhone(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 403 */     String value = extractValue(bean, field);
/*     */ 
/* 405 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 408 */         if (!StringUtil.isPhone(value)) {
/* 409 */           FieldChecks.rejectValue(errors, field, va);
/* 410 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 413 */         FieldChecks.rejectValue(errors, field, va);
/* 414 */         return false;
/*     */       }
/*     */     }
/* 417 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateZip(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 431 */     String value = extractValue(bean, field);
/*     */ 
/* 433 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 436 */         if (!StringUtil.isZip(value)) {
/* 437 */           FieldChecks.rejectValue(errors, field, va);
/* 438 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 441 */         FieldChecks.rejectValue(errors, field, va);
/* 442 */         return false;
/*     */       }
/*     */     }
/* 445 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateQq(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 459 */     String value = extractValue(bean, field);
/*     */ 
/* 461 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 464 */         if (!StringUtil.isQq(value)) {
/* 465 */           FieldChecks.rejectValue(errors, field, va);
/* 466 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 469 */         FieldChecks.rejectValue(errors, field, va);
/* 470 */         return false;
/*     */       }
/*     */     }
/* 473 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateIp(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 487 */     String value = extractValue(bean, field);
/*     */ 
/* 489 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 492 */         if (!StringUtil.isIp(value)) {
/* 493 */           FieldChecks.rejectValue(errors, field, va);
/* 494 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 497 */         FieldChecks.rejectValue(errors, field, va);
/* 498 */         return false;
/*     */       }
/*     */     }
/* 501 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateChinese(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 515 */     String value = extractValue(bean, field);
/*     */ 
/* 517 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 520 */         if (!StringUtil.isChinese(value)) {
/* 521 */           FieldChecks.rejectValue(errors, field, va);
/* 522 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 525 */         FieldChecks.rejectValue(errors, field, va);
/* 526 */         return false;
/*     */       }
/*     */     }
/* 529 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean validateChrnum(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 543 */     String value = extractValue(bean, field);
/*     */ 
/* 545 */     if (!GenericValidator.isBlankOrNull(value)) {
/*     */       try
/*     */       {
/* 548 */         if (!StringUtil.isChrNum(value)) {
/* 549 */           FieldChecks.rejectValue(errors, field, va);
/* 550 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 553 */         FieldChecks.rejectValue(errors, field, va);
/* 554 */         return false;
/*     */       }
/*     */     }
/* 557 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean compStartEndTime(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 570 */     String sTimevalue = extractValue(bean, field);
/* 571 */     String sProperty2 = field.getVarValue("compStartEndTime");
/* 572 */     String eTimevalue = ValidatorUtils.getValueAsString(bean, sProperty2);
/*     */     try
/*     */     {
/* 576 */       DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
/* 577 */       Date sTime = null;
/* 578 */       Date eTime = null;
/* 579 */       if (sProperty2.toLowerCase().indexOf("end") != -1) {
/* 580 */         sTime = dateformat.parse(DateUtil.timeStrToDateStr(sTimevalue));
/* 581 */         eTime = dateformat.parse(DateUtil.timeStrToDateStr(eTimevalue));
/*     */       } else {
/* 583 */         sTime = dateformat.parse(DateUtil.timeStrToDateStr(eTimevalue));
/* 584 */         eTime = dateformat.parse(DateUtil.timeStrToDateStr(sTimevalue));
/*     */       }
/*     */ 
/* 587 */       if (!sTime.before(eTime)) {
/* 588 */         FieldChecks.rejectValue(errors, field, va);
/* 589 */         return false;
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 593 */       FieldChecks.rejectValue(errors, field, va);
/* 594 */       return false;
/*     */     }
/*     */ 
/* 597 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean digitsSum(Object bean, ValidatorAction va, Field field, Errors errors)
/*     */   {
/* 611 */     int sum = 0;
/* 612 */     int valLimit = 0;
/*     */     try {
/* 614 */       Map vars = field.getVars();
/* 615 */       valLimit = Integer.parseInt(vars.toString().split("  ")[1].split("=")[1]);
/* 616 */       String key = field.getKey();
/* 617 */       String methodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
/* 618 */       Method method = bean.getClass().getMethod(methodName, new Class[0]);
/* 619 */       String value = String.valueOf(method.invoke(bean, new Object[0]));
/* 620 */       String[] arrVal = value.split("[,]");
/* 621 */       for (String val : arrVal) {
/* 622 */         sum += Integer.parseInt(val);
/*     */       }
/*     */     }
/*     */     catch (SecurityException e)
/*     */     {
/* 627 */       e.printStackTrace();
/*     */     }
/*     */     catch (IllegalArgumentException e) {
/* 630 */       e.printStackTrace();
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 633 */       e.printStackTrace();
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 636 */       e.printStackTrace();
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 639 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 642 */     return sum <= valLimit;
/*     */   }
/*     */ }

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.valid.CustomFieldChecks
 * JD-Core Version:    0.6.2
 */