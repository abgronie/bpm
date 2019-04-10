package com.hotent.platform.service.system.impl.handler;

import javax.annotation.Resource;

import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.api.util.PropertyUtil;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.system.IOrgHandler;
import com.hotent.platform.service.system.SysOrgService;

public class OrgHandlerLeader implements IOrgHandler{

	@Resource
	SysOrgService orgService;
	
	@Override
	public SysOrg getByType(String type) {
//		SysOrg sysOrg=(SysOrg) ContextUtil.getCurrentOrg();
//		if (sysOrg.getOrgSupId()!=1) {
			//读取配置文件app.properties
		Long leaderId = PropertyUtil.getLongByAlias("leaderId");
		SysOrg sysOrg = orgService.getById(leaderId);
//		}
		return sysOrg;
	}
}
