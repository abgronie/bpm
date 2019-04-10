package com.hotent.platform.controller.system;

import com.hotent.core.api.util.ContextUtil;
import com.hotent.core.web.util.CookieUitl;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.service.system.SecurityUtil;
import com.zfsoft.util.encrypt.MD5;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wy
 */
@Controller
@RequestMapping({"/platform/system/sso/"})
public class SsoController
{

  @Resource
  private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

  @RequestMapping({"zfssologin"})
  public void login(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    String verify = request.getParameter("verify");
    String userName = request.getParameter("userName");
    String strSysDatetime = request.getParameter("strSysDatetime");
    String url = request.getParameter("url");
    String str_zfKey = "ZFSSOKEY";
    String pwd = "123123.";

    String jmcs = URLEncoder.encode(userName, "utf-8") + str_zfKey + strSysDatetime + "teacher";
    MD5 md5 = new MD5();
    String strMd5 = md5.getMD5ofStr(jmcs);
    if (!strMd5.equals(verify)) {
      return;
    }

    boolean loged = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

    System.out.println("####: " + userName + " login success!");
    Authentication auth = SecurityUtil.login(request, userName, pwd, true);

    request.getSession().removeAttribute("isAdmin");

    this.sessionStrategy.onAuthentication(auth, request, response);

    ContextUtil.removeCurrentOrg();

    request.getSession().removeAttribute(SubSystem.CURRENT_SYSTEM);
    request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", userName);

    CookieUitl.delCookie("loginAction", request, response);

    CookieUitl.delCookie("origSwitch", request, response);

    SecurityUtil.writeRememberMeCookie(request, response, userName, pwd);

    response.sendRedirect(request.getContextPath() + url);
  }
}