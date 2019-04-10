package com.hotent.platform.job;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.bpm.BpmResult;
import com.hotent.core.bpm.DataType;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.system.SysUserService;

public class MyJob extends BaseJob {
	protected Logger logger = LoggerFactory.getLogger(MyJob.class);	
	
	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		logger.info("com.hotent.platform.job.MyJob");
		Map<String,Object> map = context.getJobDetail().getJobDataMap();
		String flowKey=map.get("newFlow").toString();
		String sql = "select m.F_sqrID,m.id,m.F_hytzsj from w_meeting_notice m where m.type!=1 or m.type is NUll;";
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		List<Map<String,Object>> lists = jdbcTemplate.queryForList(sql);  
		if(BeanUtils.isNotEmpty(lists)){
			for(Map<String,Object> data : lists){
				Timestamp time = (Timestamp) data.get("F_hytzsj");
				int type = time.compareTo(new Timestamp(System.currentTimeMillis()));
				if(type<=0){
					String businessKey = data.get("id").toString();
					ProcessRunService processRunService = AppUtil.getBean(ProcessRunService.class);
					ProcessRun processRun = processRunService.getByBusinessKey(businessKey);
					
					if(BeanUtils.isNotEmpty(processRun)){
						SysUserService sysUserService =AppUtil.getBean(SysUserService.class);
						SysUser adminUser = sysUserService.getByAccount("admin");
						ContextUtil.setCurrentUser(adminUser);
						ProcessCmd cmd = new ProcessCmd();
						BpmFormHandlerService bpmFormHandlerService=  AppUtil.getBean(BpmFormHandlerService.class);
						String bpmFormData =  bpmFormHandlerService.getBpmFormDataJson(processRun, businessKey, null);
						bpmFormData = getNewFlowFormData(bpmFormData);
						cmd.setFormData(bpmFormData);
						cmd.setBusinessKey(businessKey);
						cmd.setFlowKey(flowKey);
						SysUser startFlowUser = sysUserService.getById(Long.parseLong(data.get("F_sqrID").toString()));
						cmd.setUserAccount(startFlowUser.getAccount());
						cmd.addTransientVar("startFlowUser", startFlowUser);
						
						BpmResult bpmResult = new BpmResult();
						bpmResult.setDataType(DataType.STRING);
						bpmResult.setBusinessKey(businessKey);
						cmd.addTransientVar("bpmResult",bpmResult);
						cmd.addTransientVar(BpmConst.OPINION_SUPPORTHTML,Short.valueOf("0"));
						
						processRunService.startProcess(cmd);
						
					}
				}
			}
		}
		
	}
	

	private String getNewFlowFormData(String formDataStr) {
		JSONObject newFlowFormData = JSONObject.parseObject("{main:{},sub:[]}");	
		JSONObject fromFlowData = JSONObject.parseObject(formDataStr);
		
		JSONObject fieldData = fromFlowData.getJSONObject("main");
		 
		 newFlowFormData.getJSONObject("main").put("fields", fieldData);
		 newFlowFormData.put("opinion", new JSONArray()); 
		 
		 return  newFlowFormData.toJSONString();
		 
	}


}
