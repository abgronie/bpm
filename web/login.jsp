<%@page import="com.hotent.core.api.util.PropertyUtil"%>
<%@page import="com.hotent.core.encrypt.RSAUtils"%>
<%@page import="org.springframework.security.authentication.AuthenticationServiceException"%>
<%@page import="org.springframework.security.authentication.AccountExpiredException"%>
<%@page import="org.springframework.security.authentication.DisabledException"%>
<%@page import="org.springframework.security.authentication.LockedException"%>
<%@page import="org.springframework.security.authentication.BadCredentialsException"%>
<%@page import="java.util.Enumeration"%>
<%@ page pageEncoding="UTF-8" %>
<%@page import="org.springframework.security.web.WebAttributes"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String appName=PropertyUtil.getByAlias("appName");

	pageContext.setAttribute("validCodeEnabled", PropertyUtil.getByAlias("validCodeEnabled"));
	
	java.util.Map<String, Object> map = RSAUtils.getKeys();  
	//生成公钥和私钥    
	java.security.interfaces.RSAPublicKey publicKey = (java.security.interfaces.RSAPublicKey) map.get("public");  
	java.security.interfaces.RSAPrivateKey privateKey = (java.security.interfaces.RSAPrivateKey) map.get("private");  
	//私钥保存在session中，用于解密  
	session.setAttribute("privateKey", privateKey);  
	//公钥信息保存在页面，用于加密  
	String publicKeyExponent = publicKey.getPublicExponent().toString(16);  
	String publicKeyModulus = publicKey.getModulus().toString(16);  
	request.setAttribute("publicKeyExponent", publicKeyExponent);  
	request.setAttribute("publicKeyModulus", publicKeyModulus);  

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=appName %></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     <link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/RSA.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/login/login.css"></link>
	
	<script type="text/javascript">
		if(top!=this){//当这个窗口出现在iframe里，表示其目前已经timeout，需要把外面的框架窗口也重定向登录页面
			  top.location='${ctx}/login.jsp';
		}
		
		function reload(){
			var url="${ctx}/servlet/ValidCode?rand=" +new Date().getTime();
			document.getElementById("validImg").src=url;
		}
		
		$(function(){
			
			RSAUtils.setMaxDigits(200);  
			var key = new RSAUtils.getKeyPair("${publicKeyExponent}", "", "${publicKeyModulus}");  
			
			$('.main_login').find('input').keydown(function(event){
				if(event.keyCode==13){
					$('#submit_link').click();
				}
			});

            $('#submit_link').click(function(){
                var prePwd = $('input[name="password"]').val();
                var html = "";
                html += "<span style=\"font-size:15px;\">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;:</span>";
                html += "<input class=\"password\" type=\"password\" name=\"password\" value=\""+prePwd+"\"/>";
                html += "<img onclick=\"hideShowPwd(0)\" src=\"image/visible.png\"/>";
                $("#my_pwd").html(html);
                var encrypedPwd = RSAUtils.encryptedString(key,prePwd.split("").reverse().join(""));
                $('input[name="password"]').val(encrypedPwd);
                $('#form-login').submit();
            });
        });

        function hideShowPwd(num){
            var pwd = $('input[name="password"]').val();
            var html = "";
            if(1 == num){
                html += "<span style=\"font-size:15px;\">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;:</span>";
                html += "<input class=\"password\" type=\"password\" name=\"password\" value=\""+pwd+"\"/>";
                html += "<img onclick=\"hideShowPwd(0)\" src=\"image/visible.png\"/>";
            }else if(0 == num){
                html += "<span style=\"font-size:15px;\">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;:</span>";
                html += "<input class=\"password\" type=\"text\" name=\"password\" value=\""+pwd+"\"/>";
                html += "<img onclick=\"hideShowPwd(1)\" src=\"image/hidden.png\"/>";
            }
            $("#my_pwd").html(html);
        }
</script>
</head>
<body>
<div class="form_background">
<form id="form-login" action="${ctx}/login.ht" method="post">
	<span id="officeSpan" style="color:red" width="100%";height="50px"></span>
	<center>
		<div class="main_login">
			<span class="bpmlogo_login"></span>
			<div class="content_login">
			<div class="column">
			<span class="logo">
			</span>
			</div>	
				<div class="column">
					<span style="font-size:15px;">用户名&nbsp;&nbsp;:</span>
			        <input class="username" type="text" name="username" class="login" /><br>	
				</div>
				<%--<div class="column">
					<span style="font-size:15px;">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;:</span>
					<input class="password" type="password" name="password"/>
				</div>--%>
				<div class="column" id="my_pwd">
					<span style="font-size:15px;">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;:</span>
					<input class="password" type="password" id="showPassword" name="password"/>
					<img onclick="hideShowPwd(0)" src="image/visible.png"/>
				</div>
				<c:if test="${validCodeEnabled=='true'}">
					<div class="column">
						<span style="font-size:15px;">验证码&nbsp;&nbsp;:</span>
						<input type="text" name="validCode"  class="verifycode"/>
						<img title="" id="validImg" src="${ctx}/servlet/ValidCode" onclick="reload()" style="cursor:pointer; margin: 12px 0; position: absolute;padding-left: 10px;"/>
<!-- 						<input type="hidden" name="validCodeEnabled" value="true"/> -->
					</div>
				</c:if>
				<div class="confirm">
<!-- 					<input id="checkbox" type="checkbox" name="_spring_security_remember_me" value="1"/> -->
<!-- 					<span><img src="styles/login/images/5c5d5f.png" alt="" /></span> -->
					<a class="reset_btn btn r" onclick="document.getElementById('form-login').reset();">重置</a>
					<a class="login_btn btn r" id="submit_link">登录</a>
					<%-- <div>
						<a href='${ctx}/media/office/NtkoOfficeControlSetup.msi'>下载Office控件</a>
					</div> --%>
				</div>
				<%
				Object loginError=(Object)request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
				
				if(loginError!=null ){
					String msg="";
					if(loginError instanceof BadCredentialsException){
						msg="密码输入错误";
					}
					else if(loginError instanceof AuthenticationServiceException){
						AuthenticationServiceException ex=(AuthenticationServiceException)loginError;
						msg=ex.getMessage();
					}
					else{
						msg=loginError.toString();
					}
				%>
				<div class="confirm"><span style="color:#ff0000;"><%=msg %></span></div>
				<%
				//request.getSession().removeAttribute("validCodeEnabled");//删除需要验证码的SESSION
				request.getSession().removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);//删除登录失败信息
				}
				%>
			</div>
		</div>
	</center>
	
</form>
</div>
</body>
</html>
