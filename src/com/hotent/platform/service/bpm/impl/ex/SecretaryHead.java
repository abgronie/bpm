package com.hotent.platform.service.bpm.impl.ex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.StudentSysUserService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * 
 * 申请部门行政秘书（计量）
 *com.hotent.platform.service.bpm.impl.ex.SecretaryHead
 */
public class SecretaryHead implements IBpmNodeUserCalculation {

	@Resource
	private StudentSysUserService stuSysUserService;
	@Resource
	private SysOrgService sysOrgService;
	
	@Override
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> list ;
		Long preOrgId = (Long) vars.getVars().get("preOrgId");
		String szbm = (String) vars.getVars().get("szbm");
		String posname = "";
		/*if(preOrgId != null){
			SysOrg sysOrg = sysOrgService.getById(preOrgId);
			if(sysOrg != null && !"".equals(sysOrg)){
				posname = "%"+sysOrg.getOrgName()+"%_行政秘书%";
			}
		}*/
		posname = "%"+szbm+"%_行政秘书%";
		list = stuSysUserService.getSprByUserId(posname);
		if(list == null || list.isEmpty()){
			list = new ArrayList<SysUser>();
		}
		return list;
	}

	@Override
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Set<TaskExecutor> uIdSet = new LinkedHashSet<TaskExecutor>();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		for (SysUser sysUser : sysUsers) {
			uIdSet.add(TaskExecutor.getTaskUser(sysUser.getUserId().toString(),sysUser.getFullname()));
		}
		return uIdSet;
	}

	@Override
	public String getTitle() {
		return "申请部门行政秘书";
	}

	@Override
	public boolean supportMockModel() {
		return false;
	}

	@Override
	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		return null;
	}

	@Override
	public boolean supportPreView() {
		return false;
	}

}
