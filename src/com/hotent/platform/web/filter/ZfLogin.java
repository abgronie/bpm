package com.hotent.platform.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hotent.core.api.org.model.ISysUser;
import com.hotent.core.api.util.ContextUtil;
import com.hotent.platform.service.system.SecurityUtil;

/**
 * 	
 * @author Administrator
 *
 */

public class ZfLogin  implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		// TODO Auto-generated method stub
		String redirect=req.getParameter("redirect");
		redirect="/bpmx/platform/console/main.ht";//weixin/index.html  platform/console/main.ht
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if( authentication ==null ||   !authentication.isAuthenticated() ) {
			Authentication auth= SecurityUtil.login(request, "admin", "1", false);
			
			ISysUser user=ContextUtil.getCurrentUser();
			System.out.println("user1:"+user.getFullname()+","+user.getUserId());
			
			response.sendRedirect(redirect);
			return;
		}else {
			chain.doFilter(request, response);
		}
		
//		CookieUitl.addCookie("loginAction", "weixin", req, resp);
//		sessionStrategy.onAuthentication(auth, req, resp);
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
