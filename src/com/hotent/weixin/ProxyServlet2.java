package com.hotent.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.api.util.PropertyUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.HttpUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.system.SysPropertyConstants;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.PwdStrategyService;
import com.hotent.platform.service.system.SecurityUtil;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.weixin.api.WeixinConsts;

/**
 * 实现微信OATH2.0协议，微信跳转到应用的页面。
 * 
 * <pre>
 * 
 * 1.判定用户是否登录，如果已经登录，那么直接跳转到指定的页面。
 * 
 * 2.没有登录的情况。
 * 	首先从上下文获取code。
 *  1.如果没有获取到则跳转到微信服务器获取会话code。
 * 	2.获取code后，微信服务器再次跳转回此代理页面。
 * 	3.代理页面根据code和token提交数据到微信服务器获取，当前用户帐号。
 * 	4.获取帐号后，系统让此账户自动登录。
 * 	5.在跳转回指定页面，页面验证成功。
 *  
 * 在微信菜单URL做如下配置：
 * 
 * http://平台域名/bpmx3/proxy?from=wx&redirect=需要跳转到的页面地址。
 * http://hotent.eicp.net/bpmx3/proxy?redirect=http://hotent.eicp.net/bpmx3/weixin/index.html
 * </pre>
 * 
 * @author ray
 *
 */
public class ProxyServlet2  extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8382507359838493519L;
	
	protected Logger log = LoggerFactory.getLogger(ProxyServlet.class);
	
	//MD5解密
	public static String Encrypt(String inStr) {
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
	
	public static void main(String[] args) {
//		String str = "010123220023zfsoft_bsdt";
//		System.out.println(Encrypt(str));
		
		String userUrl=WeixinConsts.getWxUserInfo("LkoHqRRxfzUmAxpaSgOLvFqS3EFjmusgdyRL0UySDJw");
		String json=HttpUtil.sendHttpsRequest(userUrl, "" , WeixinConsts.METHOD_GET);
		System.out.println(json);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String redirect=req.getParameter("redirect");
		SysUser sysUser=(SysUser) ContextUtil.getCurrentUser();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间doGet001:"+df.format(new Date()));
        PrintWriter out = resp.getWriter();
		//没有登录
		if(sysUser==null){
			System.out.println("当前时间doGet002:"+df.format(new Date()));
			//加入公司app端应用接入入口
			String choice = req.getParameter("choice");
			String procode = req.getParameter("procode");
			String uid = req.getParameter("uid");
			String strkey = "zfsoft_bsdt";
			String str = procode + choice + uid + strkey;
			String keystr = this.Encrypt(str);
			String key = req.getParameter("key");
			if (!StringUtil.isEmpty(key) && !keystr.equals(key)) {
		        out.println("登陆私钥错误！");    
		        out.flush();    
		        out.close();   
				return;
			}else if(!StringUtil.isEmpty(key) && keystr.equals(key)){
				SecurityUtil.login(req, uid, "", true);
				//第一次使用流程平台改变为已使用微信登陆
				SysUser user = (SysUser) ContextUtil.getCurrentUser();
				System.out.println("HasSyncToWx="+user.getHasSyncToWx());
				if(user.getHasSyncToWx() ==1){
					user.setHasSyncToWx(2);
					AppUtil.getBean(SysUserService.class).update(user);
				}
				redirect = java.net.URLDecoder.decode(redirect, "UTF-8");
				System.out.println("redirect:"+redirect);
				System.out.println("当前时间doGet003:"+df.format(new Date()));
				resp.sendRedirect(redirect);
				System.out.println("当前时间doGet012:"+df.format(new Date()));
				return;
			}
			
			System.out.println("当前时间doGet004:"+df.format(new Date()));
			String code=req.getParameter("code");
			if(StringUtil.isEmpty(code)){
				String byqurl= PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL);
				System.out.println("byqurl="+byqurl);
				String rdUrl= PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL) +"/proxy?redirect="+PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL)+"/weixin/proxy.html";
				String redirectUrl=WeixinConsts.getWxAuthorize(rdUrl);
				System.out.println("当前时间doGet005:"+df.format(new Date()));
				resp.sendRedirect(redirectUrl);
				System.out.println("当前时间doGet006:"+df.format(new Date()));
			}else if(StringUtil.isNotEmpty(code)){
				//String rdUrl= PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL) +"/weixin/proxy.html?code="+code;
				//resp.sendRedirect(rdUrl);
				
				try{
					System.out.println("当前时间doGet007:"+df.format(new Date()));
					System.out.println("code="+code);
					String userUrl=WeixinConsts.getWxUserInfo(code);
					String json=HttpUtil.sendHttpsRequest(userUrl, "" , WeixinConsts.METHOD_GET);
					log.error("------------json---------------"+json);
					JSONObject jsonObj=JSONObject.fromObject(json);
					//{"UserId":"USERID", "DeviceId":"DEVICEID"}
					String userId=jsonObj.getString("UserId");
					log.error("------------weixinId---------------"+userId);
					SysUserDao sysUserDao = (SysUserDao)AppUtil.getBean("sysUserDao");
					SysUser userTemp =sysUserDao.getByWeixinid(userId);
					log.error("------------userTemp.getAccount()---------------"+userTemp.getAccount());
					
					//让系统登录
					SecurityUtil.login(req, userTemp.getAccount(), "", true);
					//第一次使用流程平台改变为已使用微信登陆
					SysUser user = (SysUser) ContextUtil.getCurrentUser();
					if(user.getHasSyncToWx() ==1){
						user.setHasSyncToWx(2);
						AppUtil.getBean(SysUserService.class).update(user);
					}
					System.out.println("当前时间doGet008:"+df.format(new Date()));
//					resp.sendRedirect(redirect);
					String rdUrl= PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL) +"/proxy?redirect="+PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL)+"/weixin/index.html";
					resp.sendRedirect(rdUrl);
					System.out.println("当前时间doGet009:"+df.format(new Date()));
				}
				catch(Exception ex){
					log.error(ex.getMessage());
					ex.printStackTrace();
//					try {
//						JSONObject jsonResult=new JSONObject();
//						jsonResult.put("result", false);
//						out.println(jsonResult);
//		            } catch (Exception e) {
//		                e.printStackTrace();
//		            }finally{
//		                out.close();
//		            }
					String rdUrl= PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL) +"/proxy?redirect="+PropertyUtil.getByAlias(SysPropertyConstants.PLATFORM_URL)+"/weixin/proxy.html?errorText=1";
					resp.sendRedirect(rdUrl);
				}
			}
		}else{
			System.out.println("当前时间doGet010:"+df.format(new Date()));
			resp.sendRedirect(redirect);
			System.out.println("当前时间doGet011:"+df.format(new Date()));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}