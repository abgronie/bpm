package com.hotent.platform.service.bpm.impl.ex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.StudentSysUserService;

/**
 * 
 * 岗位（人事处）负责人（计量）
 *com.hotent.platform.service.bpm.impl.ex.PersonnelHead
 */
public class PersonnelHead implements IBpmNodeUserCalculation {

	@Resource
	private StudentSysUserService stuSysUserService;
	
	@Override
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> list ;
		//String posname = (String) vars.getVars().get("szqx");
		String posname = "%人事处%";
		list = stuSysUserService.getPersonnelHead(posname);
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
		return "岗位负责人";
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
