
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑企业微信相关参数</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
	$(function() {
		$("a.save").click(function() {
			$("#weChatParameter").attr("action","save.ht");
			submitForm();
		});
		$("a.test").click(function(){
			$("#weChatParameter").attr("action","test.ht");
			submitForm();
		});
		$("a.reload").click(function(){
			$("#weChatParameter").attr("action","reload.ht");
			submitForm();
		});
	});
	
	function submitForm(){
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
		var frm=$('#weChatParameter').form();
		frm.ajaxForm(options);
		frm.submit();
	}
	
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					window.location.href = "${ctx}/platform/system/weChatParameter/edit.ht";
			});
		} else {
			$.ligerDialog.err("提示信息","操作失败!",obj.getMessage());
		}
	}
	
	</script>
</head>
<body>
<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">
					编辑企业微信相关参数
					</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					</div>
					<div class="l-bar-separator"></div>
					<div class="toolBar">
						<div class="group"><a class="link test" id="dataFormTest" href="javascript:;"><span></span>验证</a></div>
					</div>
					<div class="l-bar-separator"></div>
					<div class="toolBar">
						<div class="group"><a class="link reload" id="dataFormReload" href="javascript:;"><span></span>同步微信通讯录</a></div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">	
				<form id="weChatParameter" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">是否支持微信: </th>
							<td>
								<span>
									<label><input type="radio" id="supportWeiXin" name="supportWeiXin" value="1" checked="checked" <c:if test="${weChatParameter.supportWeiXin==1}"> checked="checked" </c:if> />开启</label>
									<label><input type="radio" id="supportWeiXin" name="supportWeiXin" value="0" <c:if test="${weChatParameter.supportWeiXin==0}"> checked="checked" </c:if> />关闭</label>
								</span>
							</td>
						</tr>
						<tr>
							<th width="20%">平台地址</th>
							<td><input style="width: 30%" type="text" id="serverUrl" name="serverUrl" value="${weChatParameter.serverUrl}"  class="inputText"/>
							<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>规则：http://ip:端口/服务名</span></a></div>
							</td>
						</tr>	
						<tr>
							<th width="20%">企业corpid</th>
							<td><input style="width: 30%" type="text" id="corpid" name="corpid" value="${weChatParameter.corpid}"  class="inputText"/></td>
						</tr>	
						<tr>
							<th width="20%">通讯录管理Secret</th>
							<td><input style="width: 30%" type="text" id="corpsecret" name="corpsecret" value="${weChatParameter.corpsecret}"  class="inputText"/></td>
						</tr>	
						<tr>
							<th width="20%">应用ID</th>
							<td><input style="width: 30%" type="text" id="agentid" name="agentid" value="${weChatParameter.agentid}"  class="inputText"/></td>
						</tr>	
						<tr>
							<th width="20%">应用的凭证密钥</th>
							<td><input style="width: 30%" type="text" id="agentSecret" name="agentSecret" value="${weChatParameter.agentSecret}"  class="inputText"/></td>
						</tr>	
					</table>		
				</form>			
		</div>
</div>

</body>
</html>