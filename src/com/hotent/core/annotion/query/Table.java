package com.hotent.core.annotion.query;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Table
{
  public abstract String name();

  public abstract String var();

  public abstract String displayTagId();

  public abstract String pk();

  public abstract String comment();

  public abstract boolean isPrimary();

  public abstract String relation();

  public abstract String primaryTable();
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.annotion.query.Table
 * JD-Core Version:    0.6.2
 */