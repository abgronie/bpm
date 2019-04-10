package com.hotent.platform.web.filter;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hotent.core.api.org.model.ISysUser;
import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.platform.service.system.SecurityUtil;

/**
 * 	
 * @author Administrator
 *
 */

public class ZfLoginFilter  implements Filter {
	
	 /**具有匿名访问权限的url*/
	private  HashSet<String> anonymousUrls=new HashSet<String>();

	public HashSet<String> getAnonymousUrls() {
		return anonymousUrls;
	}

	public void setAnonymousUrls(HashSet<String> anonymousUrls) {
		this.anonymousUrls = anonymousUrls;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String username = req.getParameter("username");
		String strSysDatetime = request.getParameter("strSysDatetime");
		String userType = request.getParameter("userType");
		String verify = request.getParameter("verify");
		String uri = request.getRequestURI();
		//System.out.println(uri);
		String ctx = request.getContextPath();
		uri = uri.substring(ctx.length());
		if(uri.contains("logout")) {
			chain.doFilter(request, response);
			return;
		}
		// TODO Auto-generated method stub
		//weixin/index.html  platform/console/main.ht
		//   获取ticket cas  认证
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 匿名不执行认证  anonymousUrls.contains(uri) && 
		if((authentication ==null ||   !authentication.isAuthenticated()) ) {
			// 正式时需要从header 中获取token 来登陆
			if((username==""||username==null)||(strSysDatetime==""||strSysDatetime==null)||(userType==""||userType==null)||(verify==""||verify==null)) {
				//System.out.println("*************参数无效*************");
				chain.doFilter(request, response);
				return;
			};
			MD5 md5 = new MD5();
			String str = md5.getMD5ofStr(username + "zfly_zfsoft_2860" + strSysDatetime + userType);
			if(str.equalsIgnoreCase(verify)) {
				try {
					Authentication auth= SecurityUtil.login(request, username, "", true);
					//ISysUser user=ContextUtil.getCurrentUser();
					//System.out.println("user1:"+user.getFullname()+","+user.getUserId());
					//CookieUitl.addCookie("loginAction", "weixin", request, response);
					//sessionStrategy.onAuthentication(auth, request, response);
					//response.sendRedirect(redirect);
					chain.doFilter(request, response);
					return;
				}catch(BadCredentialsException be) {
					be.printStackTrace();
					System.out.println("*************票据无效*************");
					chain.doFilter(request, response);
					return;
				}catch(Exception ex) {
					ex.printStackTrace();
					System.out.println("*************异常*************");
					chain.doFilter(request, response);
					return;
				}
			}else {
				System.out.println("*************票据匹配失败*************");
				chain.doFilter(request, response);
				return;
			}
		}else {
			//System.out.println("*************111*************");
			chain.doFilter(request, response);
			return;
		}	
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

}
