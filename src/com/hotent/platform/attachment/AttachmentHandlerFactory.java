package com.hotent.platform.attachment;

import java.util.HashSet;
import java.util.Set;

import com.hotent.core.util.BeanUtils;
import com.hotent.platform.service.util.ServiceUtil;


public class AttachmentHandlerFactory {
	// 附件处理器集合
	private Set<AttachmentHandler> attachmentHandlers = new HashSet<AttachmentHandler>();
	// 当前附件处理器
	private AttachmentHandler currentHandler;
	//附件保存类型
	private String saveType = ServiceUtil.getSaveType();

	public void setAttachmentHandlers(Set<AttachmentHandler> attachmentHandlers) {
		this.attachmentHandlers = attachmentHandlers;
	}
	
	public AttachmentHandler getCurrentHandler() throws Exception{
		if(BeanUtils.isEmpty(currentHandler)){
			for (AttachmentHandler attachmentHandler : attachmentHandlers) {
				if(attachmentHandler.getType().equals(saveType)){
					currentHandler = attachmentHandler;
					break;
				}
			}
			if(BeanUtils.isEmpty(currentHandler)){
				throw new RuntimeException("未找到对应的附件处理器，请检查系统属性中的file.saveType属性");
			}
		}
		return currentHandler;
	}
}
