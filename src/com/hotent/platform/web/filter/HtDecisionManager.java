package com.hotent.platform.web.filter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.hotent.core.encrypt.Base64;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.system.SecurityUtil;
import com.hotent.platform.service.system.SysUserService;

/**
 * 系统访问权限决策器，系统有权限则直接返回，没有权限则抛出AccessDeniedException错误。
 * 
 * <pre>
 * 系统根据当前页面的权限判断用户是否能够访问系统页面。
 * 		1.首先判断是否匿名访问，如果配置了匿名访问则直接通过。
 * 		2.判断用户是否已经登录，如果没有登录则返回没有权限页面。
 * 		3.如果是超级管理员直接通过。
 * 		4.页面的角色包括公开访问的角色直接通过。
 * 		5.判断是否有权限访问系统。
 * 		6.判断页面是否有角色授权，如果当前用户有访问页面的角色则直接通过。
 * 		7.没有满足的条件，则抛出错误。
 * </pre>
 * 
 * @author ray
 * 
 */
public class HtDecisionManager implements AccessDecisionManager {

	public Logger logger = LoggerFactory.getLogger(HtDecisionManager.class);
	
	@Resource
	private SysUserService sysUserService;
	
	@SuppressWarnings({ "unused", "unchecked" })
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		// @by byq 自定义账号密码登陆
		FilterInvocation filterInvocation=((FilterInvocation)object);
		HttpServletRequest request=filterInvocation.getRequest();
		String queryStr = request.getQueryString();
		if(StringUtils.isNotBlank(queryStr)){
    		try {
	    		System.out.println("queryStr="+queryStr);
	    		if(queryStr.contains("username") && queryStr.contains("password")){
	    			String username = "";
	    			String password = "";
	    			String[] strs = queryStr.split("&");
	    			for (int i = 0; i < strs.length; i++) {
	    				queryStr = strs[i];
	    				System.out.println(queryStr);
	    				String[] strs2 = queryStr.split("=");
	    				String str1 = strs2[0];
	    				if("username".equals(str1)){
	    					username = strs2[1];
	    					System.out.println(username);
	    				}
	    				if("password".equals(str1)){
	    					password = strs2[1];
	    					System.out.println(password);
	    				}
	    			}
    			
    				String usernamedec = EncryptUtil.decrypt(username);
    				String passworddec = EncryptUtil.decrypt(password);
    				String password256 = EncryptUtil.encryptSha256(passworddec);
    				SysUser sysUser = sysUserService.getByAccount(usernamedec);
    				if(sysUser != null){
    					if(password256.equals(sysUser.getPassword())){
    						SecurityUtil.login(request, usernamedec, passworddec, true);
    						return;
    					}
    				}
	    		}
			} catch (Exception e) {
				e.printStackTrace();
				throw new AccessDeniedException("登录系统错误");
			}
    	}
		
		// 匿名访问
		if (configAttributes.contains(SystemConst.ROLE_CONFIG_ANONYMOUS)) {
			return;
		}
		
		// 登陆访问
		if (authentication == null) {
			throw new AccessDeniedException("没有登录系统");
		}
		Object principal = authentication.getPrincipal();
		if (principal == null) {
			throw new AccessDeniedException("登录对象为空");
		}
		if (!(principal instanceof SysUser)) {
			throw new AccessDeniedException("登录对象必须为SysUser实现类");
		}
		
		SysUser user = (SysUser) principal;
		
		//判断是否用户查看自己的信息@author byq
		/*System.out.println(object.toString());
		String nowUrl = "/platform/system/sysUser/get.ht";
		String[] urls = object.toString().split(":");
		String url = urls[urls.length-1];
		System.out.println("urls="+urls+";url="+url);
		if(url.contains(nowUrl)){
			String[] ids = url.split("\\?");
			String userId = ids[ids.length-1];
			ids = userId.split("&");
			userId = ids[0];
			ids = userId.split("=");
			userId = ids[1];
			if(user.getUserId().toString().equals(userId)){
				System.out.println("userId="+userId);
				System.out.println("id="+user.getUserId());
				return;
			}
			return;
		}*/
		
		// 获取当前用户的角色。
		Collection<GrantedAuthority> roles = user.getAuthorities();
		// 超级访问
		if (roles.contains(SystemConst.ROLE_GRANT_SUPER)) {
			return;
		}
		// 公开访问
		if (configAttributes.contains(SystemConst.ROLE_CONFIG_PUBLIC)) {
			return;
		}
		// 授权访问
		// 遍历当前用户的角色，如果当前页面对应的角色包含当前用户的某个角色则有权限访问。
		for (GrantedAuthority hadRole : roles) {
			if (configAttributes.contains(new SecurityConfig(hadRole.getAuthority()))) {
				return;
			}
		}
		throw new AccessDeniedException("对不起,你没有访问该页面的权限!");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {

		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return true;
	}

}
