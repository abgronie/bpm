
package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.api.util.PropertyUtil;
import com.hotent.core.cache.ICache;
import com.hotent.core.util.AppUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.annotion.Action;
import com.hotent.platform.model.system.SysProperty;
import com.hotent.platform.model.system.WeChatParameter;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.weixin.util.TokenUtil;

/**
 * 对象功能:webservice数据模板展示 控制器类
 */
@Controller
@RequestMapping("/platform/system/weChatParameter/")
public class WeChatParameterController extends BaseController
{

	@Resource
	private SysPropertyService sysPropertyService;
	@Resource
	private SysUserService sysUserService;
	@RequestMapping("save")
	@Action(description="提交微信相关参数")
	public void save(HttpServletRequest request, HttpServletResponse response , WeChatParameter weChatParameter) throws Exception{
		String resultMsg="";
		try {
			sysPropertyService.updateProperty(weChatParameter);
			resultMsg="更新相关参数成功";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		} catch (Exception e) {
			resultMsg="更新相关参数失败";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
		}
	}
	
	@RequestMapping("edit")
	@Action(description="编辑微信相关参数")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = getAutoView();
		WeChatParameter weChatParameter = new WeChatParameter();
		weChatParameter.setCorpid(PropertyUtil.getByAlias(WeChatParameter.WeChat_Corpid));
		weChatParameter.setCorpsecret(PropertyUtil.getByAlias(WeChatParameter.WeChat_CorpSecret));
		weChatParameter.setAgentid(PropertyUtil.getByAlias(WeChatParameter.WeChat_AgentId));
		weChatParameter.setAgentSecret(PropertyUtil.getByAlias(WeChatParameter.WeChat_AgentSecret));
		weChatParameter.setServerUrl(PropertyUtil.getByAlias(WeChatParameter.WeChat_ServerUrl));
		weChatParameter.setSupportWeiXin(PropertyUtil.getByAlias(WeChatParameter.WeChat_SupportWeiXin));
		return getAutoView().addObject("weChatParameter", weChatParameter);
	}
	/**
	 * 根据微信相关参数测试接口是否对接成功
	 * @param request
	 * @param response
	 * @param weChatParameter
	 * @throws Exception
	 */
	@RequestMapping("test")
	@Action(description="测试微信相关参数")
	public void test(HttpServletRequest request, HttpServletResponse response , WeChatParameter weChatParameter) throws Exception{
		String resultMsg="";
		//先更新相关参数，并更新缓存
		sysPropertyService.updateProperty(weChatParameter);
		String agentToken = TokenUtil.getToKen();
		String corpToken = TokenUtil.getToken();
		if(agentToken.equals("-1")){
			resultMsg="请检查[企业corpid/应用的凭证密钥]是否填写正确！！";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
		}else if(corpToken.equals("-1")){
			resultMsg +="请检查[企业corpid/通讯录管理Secret]是否填写正确！！";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
		}else{
			resultMsg = "验证成功！";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("reload")
	@Action(description = "同步用户至微信")
	public void reload(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "userId");
			sysUserService.syncUserToWx(lAryId);
			message = new ResultMessage(ResultMessage.Success, "同步用户成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "同步用户失败",e.getMessage());
			e.printStackTrace();
		}
		writeResultMessage(response.getWriter(), message);
	
	}
	
}
