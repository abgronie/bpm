package com.hotent.core.soap.type;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public abstract interface SoapType
{
  public abstract Object convertToBean(Class<?> paramClass, SOAPElement[] paramArrayOfSOAPElement)
    throws SOAPException;

  public abstract Object convertToBean(SOAPElement[] paramArrayOfSOAPElement)
    throws SOAPException;

  public abstract void setValue(SOAPElement paramSOAPElement, Object paramObject, Class<?> paramClass)
    throws SOAPException;

  public abstract void setValue(SOAPElement paramSOAPElement, Object paramObject)
    throws SOAPException;

  public abstract Class<?>[] getBeanTypes();

  public abstract String[] getSoapTypes();
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.soap.type.SoapType
 * JD-Core Version:    0.6.2
 */