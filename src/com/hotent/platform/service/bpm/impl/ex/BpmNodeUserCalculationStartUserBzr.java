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
 * 发起人班主任
 *com.hotent.platform.service.bpm.impl.ex.BpmNodeUserCalculationStartUserBzr
 */
public class BpmNodeUserCalculationStartUserBzr implements IBpmNodeUserCalculation {

	@Resource
	private StudentSysUserService stuSysUserService;
	
	@Override
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> list ;
		Long startUserId = vars.getStartUserId();
		list = stuSysUserService.getBrzByXh(startUserId);
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
		return "发起人所在班班主任";
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
