package com.hotent.core.api.bpm.model;

import java.util.Date;
import java.util.Map;

public abstract interface IBpmDefinition
{
  public abstract void setDefId(Long paramLong);

  public abstract Long getDefId();

  public abstract Short getIsUseOutForm();

  public abstract void setIsUseOutForm(Short paramShort);

  public abstract void setTypeId(Long paramLong);

  public abstract String getFormDetailUrl();

  public abstract void setFormDetailUrl(String paramString);

  public abstract Long getTypeId();

  public abstract void setSubject(String paramString);

  public abstract String getSubject();

  public abstract void setDefKey(String paramString);

  public abstract String getDefKey();

  public abstract void setTaskNameRule(String paramString);

  public abstract String getTaskNameRule();

  public abstract void setDescp(String paramString);

  public abstract String getDescp();

  public abstract void setCreatetime(Date paramDate);

  public abstract Date getCreatetime();

  public abstract void setStatus(Short paramShort);

  public abstract String getTestStatusTag();

  public abstract void setTestStatusTag(String paramString);

  public abstract Short getStatus();

  public abstract void setDefXml(String paramString);

  public abstract String getDefXml();

  public abstract void setActDeployId(Long paramLong);

  public abstract Long getActDeployId();

  public abstract void setActDefKey(String paramString);

  public abstract String getActDefKey();

  public abstract void setActDefId(String paramString);

  public abstract String getActDefId();

  public abstract void setCreateBy(Long paramLong);

  public abstract Long getCreateBy();

  public abstract void setUpdateBy(Long paramLong);

  public abstract Long getUpdateBy();

  public abstract void setReason(String paramString);

  public abstract String getReason();

  public abstract void setVersionNo(Integer paramInteger);

  public abstract Integer getVersionNo();

  public abstract void setParentDefId(Long paramLong);

  public abstract Long getParentDefId();

  public abstract void setIsMain(Short paramShort);

  public abstract Short getIsMain();

  public abstract void setUpdatetime(Date paramDate);

  public abstract Date getUpdatetime();

  public abstract String getTypeName();

  public abstract void setTypeName(String paramString);

  public abstract Short getShowFirstAssignee();

  public abstract void setShowFirstAssignee(Short paramShort);

  public abstract Short getToFirstNode();

  public abstract void setToFirstNode(Short paramShort);

  public abstract String getCanChoicePath();

  public abstract void setCanChoicePath(String paramString);

  public abstract Short getIsPrintForm();

  public abstract void setIsPrintForm(Short paramShort);

  public abstract Integer getAllowFinishedDivert();

  public abstract void setAllowFinishedDivert(Integer paramInteger);

  public abstract Integer getAllowFinishedCc();

  public abstract void setAllowFinishedCc(Integer paramInteger);

  public abstract Integer getAllowDivert();

  public abstract void setAllowDivert(Integer paramInteger);

  public abstract Long getAttachment();

  public abstract void setAttachment(Long paramLong);

  public abstract String getInformType();

  public abstract void setInformType(String paramString);

  public abstract Short getSameExecutorJump();

  public abstract void setSameExecutorJump(Short paramShort);

  public abstract void setCanChoicePathNodeMap(Map paramMap);

  public abstract Integer getSubmitConfirm();

  public abstract void setSubmitConfirm(Integer paramInteger);

  public abstract String getInformStart();

  public abstract void setInformStart(String paramString);

  public abstract Integer getAllowRefer();

  public abstract void setAllowRefer(Integer paramInteger);

  public abstract Integer getInstanceAmount();

  public abstract void setInstanceAmount(Integer paramInteger);

  public abstract Integer getDirectstart();

  public abstract void setDirectstart(Integer paramInteger);

  public abstract String getCcMessageType();

  public abstract void setCcMessageType(String paramString);

  public abstract Integer getAllowMobile();

  public abstract void setAllowMobile(Integer paramInteger);

  public abstract Integer getAllowRevert();

  public abstract void setAllowRevert(Integer paramInteger);

  public abstract String getSkipSetting();

  public abstract void setSkipSetting(String paramString);
}

/* Location:           C:\Users\zfsoft\Desktop\工作＠bpmx\v2\bpmx\web\WEB-INF\lib\hotentcore-1.3.6.9.jar
 * Qualified Name:     com.hotent.core.api.bpm.model.IBpmDefinition
 * JD-Core Version:    0.6.2
 */