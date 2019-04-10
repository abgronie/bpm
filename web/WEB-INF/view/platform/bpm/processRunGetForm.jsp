<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>流程实例业务表单明细</title>
		<%@include file="/commons/include/customForm.jsp" %>
		<script type="text/javascript">
			var isExtForm=${isExtForm};
			var isFormEmpty=${isFormEmpty};
			var status = ${processRun.status==1};
			$(function(){
				if(!isFormEmpty){
					if(isExtForm){
						var formUrl=$('#divExternalForm').attr("formUrl");
						$('#divExternalForm').load(formUrl, function() {
							hasLoadComplete=true;
							//动态执行第三方表单指定执行的js
							try{
								afterOnload();
							}catch(e){}
						});
					}
				}
				
				if(status){
					var add = $("tr.toolBar").find("a.add");
					add.hide();
				}
				
				// 生成查看流程实例的二维码(无实际用处，且脚本报错先去掉)
				//createQRCode();
			});
			
			// 生成查看流程实例的二维码
			function createQRCode(){
				var qrcode = $("#qrcode");
				if(!qrcode) return;
				// 设置参数方式
				var qrcode = new QRCode('qrcode', {
				  text: window.location.origin + __ctx + "/platform/bpm/processRun/getForm.ht?tab=1&runId="+${processRun.runId},
				  width: 128,
				  height: 128,
				  colorDark : '#000000',
				  colorLight : '#ffffff',
				  correctLevel : QRCode.CorrectLevel.H
				});
			}
		</script>
	</head>
	<body >
		<div class="panel">
		<div class="panel-top">
			<div class="l-layout-header">流程实例-【<i>${processRun.subject}</i>】业务表单。</div>
		</div>
		<c:choose>
			<c:when test="${processRun.status==2  || processRun.status==3}">
				<f:tab curTab="taskForm" tabName="process"/>
			</c:when>
			<c:otherwise>
				<f:tab curTab="taskForm" tabName="process"  hideTabs="copyUser"/>
			</c:otherwise>
		</c:choose>
		
		<div class="panel-body">
			<c:choose>
				<c:when test="${!isFormEmpty}">
					<c:choose>
						<c:when test="${isExtForm}">
							<div id="divExternalForm" formUrl="${form}"></div>
						</c:when>
						<c:otherwise>
							${form}
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					未设置流程实例业务表单
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</body>
	<script type="text/javascript">
	window.onload = function() {
		//给已办加只读
		$('input, select, textarea').attr("disabled","disabled");
		//隐藏选择器
		$('.formTable .link').attr("style","display:none");
		//隐藏附件删除
		$('.formTable .attachement-span .cancel').hide();
		$(".l-trigger").hide();
	}
</script>
</html>