package com.hotent.platform.service.bpm.skipimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.task.Task;

import com.hotent.core.api.org.ISysUserService;
import com.hotent.core.api.org.model.ISysUser;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.setting.ISkipCondition;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.thread.TaskThreadService;


/**
 * 如果执行人为空，这自动执行。
 * @author Administrator
 *
 */
public class IsEmptyUserSkipCondition implements ISkipCondition {
	
	@Resource
	TaskOpinionService taskOpinionService;
	@Resource 
	ISysUserService sysUserServiceImpl;

	@Override
	public boolean canSkip(Task task) {
		//获取流程变量是否标记了执行人为空跳过。
		ProcessCmd cmd = TaskThreadService.getProcessCmd();
		Map<String, Object> var = cmd.getVariables();
		String taskUserIsNull = (String)var.get("taskUserIsNull"); 
		if(StringUtil.isNotEmpty(taskUserIsNull) && taskUserIsNull.equals("Y")){
			return true;
		}
		return false;
	}
	
	@Override
	public ISysUser getExecutor() {
		ProcessCmd cmd=TaskThreadService.getProcessCmd();
		ISysUser user =(ISysUser) cmd.getTransientVar("appproveUser");
		return user;
	}
	
	

	@Override
	public String getTitle() {
		return "执行人为空跳过";
	}





	

}
