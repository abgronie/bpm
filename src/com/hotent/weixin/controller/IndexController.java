package com.hotent.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.platform.annotion.Action;
import com.hotent.platform.model.system.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotent.core.api.org.model.ISysUser;
import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.system.SysBulletin;
import com.hotent.platform.service.bpm.ITaskService;
import com.hotent.platform.service.system.SecurityUtil;
import com.hotent.platform.service.system.SysBulletinService;
import com.hotent.platform.service.system.SysUserService;

import freemarker.template.TemplateException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/weixin/")
public class IndexController {
	
	@Resource
	private SysBulletinService sysBulletinService;
	@Resource
	private ITaskService taskServiceImpl;
	@Resource
	private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

	@RequestMapping("login.ht")
	@ResponseBody
	public ResultMessage login(HttpServletRequest request, HttpServletResponse response,
							   @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) throws IOException, TemplateException, InterruptedException{
		String rememberMe=RequestUtil.getString(request, "rememberMe","0");
		ResultMessage message=new ResultMessage(ResultMessage.Success, "");
		try{
			String encrptPassword = EncryptUtil.encryptSha256(password);
			Authentication auth= SecurityUtil.login(request, username, password, false);
			//设置从微信端登录，方便退出时退出到指定的登录页面。
			CookieUitl.addCookie("loginAction", "weixin", request, response);
			sessionStrategy.onAuthentication(auth, request, response);
			if("1" .equals(rememberMe)){
				SecurityUtil.writeRememberMeCookie(request, response, username, encrptPassword);
			}
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, ex.getMessage());
		}
		return message;


	}

	/**
	 * 移动app登录
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("indexApp")
	@Action(description = "移动app登录")
	public ModelAndView indexApp(HttpServletRequest request, HttpServletResponse response){
		String todo = "";
		SysUser user = (SysUser) ContextUtil.getCurrentUser();
		try {
			if (user == null) {
				String key = request.getParameter("keyapp");
				String key2 = request.getParameter("key");
				System.out.println("key="+key);
				System.out.println("key2="+key2);
				String choice = request.getParameter("choice");
				System.out.println("choice="+choice);
				String procode = request.getParameter("procode");
				System.out.println("procode="+procode);
				String uid = request.getParameter("uid");
				System.out.println("uid="+uid);
				String strkey = "zfsoft_bsdt";
				System.out.println("strkey="+strkey);
				String str = procode + choice + uid + strkey;
				System.out.println("str="+str);
				String keystr = this.Encrypt(str);
				System.out.println("keystr="+keystr);
				String time = request.getParameter("time");
				System.out.println("time="+time);
				//手机端跳转的页面
				todo = request.getParameter("todo");
				System.out.println(todo);

				//if (!StringUtil.isEmpty(key) && !keystr.equals(key)) {
					//System.err.println("登陆私钥错误！");
					//todo = "登陆私钥错误！";
				//} else if (!StringUtil.isEmpty(key) && keystr.equals(key)) {
					SecurityUtil.login(request, uid, "", true);
					user = (SysUser) ContextUtil.getCurrentUser();
					if (user != null) {
						System.out.println(user.getAccount());
						System.out.println(user.getFullname());
					} else {
						todo = "用户未登录！";
					}
				//} else {
					//todo = "用户未登录！";
				//}
			} else {
				todo = request.getParameter("todo");
				System.out.println(todo);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("weixin/indexApp.jsp");
		mv.addObject("todo",todo);
		return mv;
	}

	@RequestMapping("index")
	@ResponseBody
	public JSONObject index(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, InterruptedException{
		ISysUser user=ContextUtil.getCurrentUser();
		JSONObject data=getData(request,user);
		data.put("curUser", user.getFullname());
		System.out.println(data.toString());
		return data;
	}

	/*@RequestMapping("index")
	@ResponseBody
	public JSONObject index(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, InterruptedException, ServletException {
	  ISysUser user = ContextUtil.getCurrentUser();
	  String indexPath = "index.html";
	  if (user == null) {
	    JSONObject data = new JSONObject();
	    String loginUserBSDT = request.getParameter("loginUserBSDT");
	    loginUserBSDT = new String(Base64.decodeBase64(loginUserBSDT), "UTF-8");
	    if (StringUtils.isNotBlank(loginUserBSDT)) {
	      SecurityUtil.login(request, loginUserBSDT, "", true);
	      user = ContextUtil.getCurrentUser();
	      System.err.println("user=" + user);
	      if (user == null) {
	        data.put("error", "获取不到用户信息!!!请检查系统是否有该账号!!! &loginUser=" + loginUserBSDT);
	        return data;
	      }
	      response.sendRedirect(indexPath);
	    } else {
	      data.put("error", "获取不到客户端传递的用户信息!!!请检查!!!&loginUser=" + loginUserBSDT);
	      return data;
	    }
	  } else {
	    response.sendRedirect(indexPath);
	  }
	  JSONObject data = getData(request, user);
	  data.put("curUser", user.getFullname());
	  System.out.println(data.toString());
	  return data;
	}

	@RequestMapping("index_data")
	@ResponseBody
	public JSONObject index_data(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, InterruptedException, ServletException {
	  ISysUser user = ContextUtil.getCurrentUser();
	  if (user == null) {
	    JSONObject data = new JSONObject();
	    String loginUserBSDT = request.getParameter("loginUserBSDT");
	    loginUserBSDT = new String(Base64.decodeBase64(loginUserBSDT), "UTF-8");
	    if (StringUtils.isNotBlank(loginUserBSDT)) {
	      SecurityUtil.login(request, loginUserBSDT, "", true);
	      user = ContextUtil.getCurrentUser();
	      System.err.println("user=" + user);
	      if (user == null) {
	        data.put("error", "获取不到用户信息!!!请检查系统是否有该账号!!! &loginUser=" + loginUserBSDT);
	        return data;
	      }
	    } else {
	      data.put("error", "获取不到客户端传递的用户信息!!!请检查!!!&loginUser=" + loginUserBSDT);
	      return data;
	    }
	  }
	  JSONObject data = getData(request, user);
	  data.put("curUser", user.getFullname());
	  System.out.println(data.toString());
	  return data;
	}*/

	private JSONObject getData(HttpServletRequest request,ISysUser user){
		JSONObject jsonObj=new JSONObject();
		
		List<JSONObject> taskModel=getTaskModel(request,user.getUserId());
		List<JSONObject> bulletin=getBulletinModel();
		
		jsonObj.put("task", taskModel);
		jsonObj.put("bulletin", bulletin);
		
		return jsonObj;
	}
	
	/**
	 * 获取任务列表。
	 * @param userId
	 * @return
	 */
	private List<JSONObject> getTaskModel(HttpServletRequest request,Long userId){
		List<JSONObject> taskList=new ArrayList<JSONObject>();
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userId",userId);
		QueryFilter queryFilter=new QueryFilter(request, false);
		List<ProcessTask> list= taskServiceImpl.getMyMobileTasks(queryFilter);
		int taskAmount=0;
		for(ProcessTask task:list){
			taskAmount++;
			if(taskAmount>5) break;
			JSONObject obj=new JSONObject();
			obj.put("id", task.getId());
			obj.put("subject", task.getSubject());
			obj.put("createTime", TimeUtil.getDateString(task.getCreateTime()));
			obj.put("defId", task.getDefId());
			obj.put("runId", task.getRunId());
			obj.put("creator", task.getCreator());
			obj.put("nodename", task.getName());
			
			taskList.add(obj);
			
		}
		return taskList;
	}
	
	/**
	 * 获取公告列表。
	 * @return
	 */
	private List<JSONObject> getBulletinModel(){
		List<JSONObject> bulletinList=new ArrayList<JSONObject>();
	
		List<SysBulletin> sysBulletins= sysBulletinService.getTopBulletin(5);
		for(SysBulletin bulletin:sysBulletins){
			String date=TimeUtil.getDateString(bulletin.getCreatetime() );
			JSONObject object=new JSONObject();
			
			object.put("subject", bulletin.getSubject());
			object.put("id", bulletin.getId());
			object.put("date", date);
			
			bulletinList.add(object);
		}
		return bulletinList;
	}

	//MD5解密
	private String Encrypt(String inStr) {
		StringBuffer hexValue = new StringBuffer();
		try {
			java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(inStr.getBytes("UTF-8"));
			byte[] md5Bytes = md5.digest();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hexValue.toString();
	}
}
