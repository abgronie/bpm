package com.hotent.platform.service.util;

import java.util.Map;

import com.ctc.wstx.util.StringUtil;
import com.hotent.core.api.util.PropertyUtil;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.model.MessageModel;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysPropertyConstants;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.bpm.ProcessRunService;
import org.apache.commons.lang3.StringUtils;

/**
 * MessageModel 公共方法，用户系统中的的消息发送。
 * 
 * @author ray
 * 
 */
public class MessageUtil {

	/**
	 * 获取主题。
	 * 
	 * @param model
	 * @return
	 */
	public static String getSubject(MessageModel model) {
		if (model.getTemplateMap() == null)
			return model.getSubject();
		String title = model.getTemplateMap().get(SysTemplate.TEMPLATE_TITLE);
		title = ServiceUtil.replaceTitleTag(title, model.getReceiveUser()
				.getFullname(), model.getSendUser().getFullname(), model
				.getSubject(), model.getOpinion());
		return title;
	}

	/**
	 * 内容要用模版进行替换， 如果有模版
	 */
	public static String getContent(MessageModel model, boolean isHtml) {
		String content = "";
		if (model.getTemplateMap() == null)
			return model.getContent();

		Long id = model.getExtId();// 流程运行ID或任务ID（由isTask决定）
		boolean isTask = model.getIsTask(); // 是否是任务（taskId 是true,runId 是false）
		// 链接地址

		String url = "";
		if (BeanUtils.isNotIncZeroEmpty(id)) {
			url = ServiceUtil.getUrl(id.toString(), isTask);
		}
		Map vars = model.getVars();
		if (BeanUtils.isNotEmpty(vars)) {
			if (BeanUtils.isNotEmpty(vars.get("copyId"))) {
				Long copuId = (Long) vars.get("copyId");
				url = url + "&copyId=" + copuId;
			}
		}
		String opinion = model.getOpinion() == null ? "" : model.getOpinion();

		if(StringUtils.isNotBlank(model.getStartUser())){
			// 发送内容 by byq
			content = ServiceUtil.replaceTemplateTag(getTemplate(model, isHtml),
					model.getReceiveUser().getFullname(), getSendUserName(model),
					model.getSubject(), url, opinion, true, model.getNodeName(), model.getStartUser());
		}else{
			// 发送内容
			content = ServiceUtil.replaceTemplateTag(getTemplate(model, isHtml),
				model.getReceiveUser().getFullname(), getSendUserName(model),
				model.getSubject(), url, opinion, true);
		}

		try {
			content = content.replace("${html表单}",
					model.getTemplateMap().get("htmlDefForm")).replace(
					"${text表单}", model.getTemplateMap().get("textDefForm"));

		} catch (Exception e) {
		}
		return content.replaceAll("<br/>", "\n").replaceAll("<br />", "\n");
	}

	
	
	public static String getContentForWx(MessageModel model) {
		String content = "";
		if (model.getTemplateMap() == null)
			return model.getContent();
		Long id = model.getExtId();// 流程运行ID或任务ID（由isTask决定）
		boolean isTask = model.getIsTask(); // 是否是任务（taskId 是true,runId 是false）
		
		if(BeanUtils.isEmpty(id)) return content;
		String url = PropertyUtil.getByAlias("serverUrl");
		if(isTask){
			Map var = model.getVars();
			url += "/weixin/bpm/task.html?taskId="+id+"&defId="+var.get("defId")+"&runId="+var.get("runId");
		}else{
			url+= "/weixin/bpm/getProcessRun.html?runId="+id;
		}
		String wxlj = PropertyUtil.getByAlias("wxlj");
		String rdUrl="";
		if(wxlj!=null&&wxlj.equals("1")) {
			 rdUrl=PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL) +"/proxy?redirect="+url;
		}else {
			 rdUrl="";
		}
				
		String opinion = model.getOpinion() == null ? "" : model.getOpinion();
		content = ServiceUtil.replaceTemplateTag(getTemplate(model, false),
				model.getReceiveUser().getFullname(), getSendUserName(model),
				model.getSubject(), rdUrl, opinion, false);
		return content.replaceAll("<br/>", "\n").replaceAll("<br />", "\n");
	}
	
	
	/**
	 * 获取短信的模版内容
	 */
	private static String getTemplate(MessageModel model, boolean isHtml) {
		String smsTemplate = "";
		try {
			System.out.println();
			String type = isHtml ? SysTemplate.TEMPLATE_TYPE_HTML: SysTemplate.TEMPLATE_TYPE_PLAIN;
			smsTemplate = model.getTemplateMap().get(type);
			if (!smsTemplate.contains("${html表单}") && BeanUtils.isNotEmpty(model.getTemplateMap().get("htmlDefForm"))) {
				smsTemplate=smsTemplate+"<br />${html表单}";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsTemplate;
	}

	/**
	 * 获取发送者用户名称。
	 * 
	 * @param model
	 * @return
	 */
	public static String getSendUserName(MessageModel model) {
		String sendUserName = "";
		if (model.getSendUser() == null) {
			sendUserName = "系统消息";
		} else {
			sendUserName = model.getSendUser().getFullname();
		}
		return sendUserName;
	}
}
