<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>终止意见</title>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

 function save(){
	var message = '${message}';
	if(message == 1){
		 $.ligerDialog.error('此任务已经完成!','提示');
		 return;
	}else{
		var opinion=$("#opinion").val();
		if(opinion==""){
		 $.ligerDialog.error('终止意见不能为空!','提示');
		 return;
		}
		$.ligerDialog.confirm('确认终止吗？','提示',function(rtn) {
			if(rtn) {
			 	//window.returnValue=opinion;
				dialog.get("sucCall")(opinion);
			  	dialog.close();
			}
		});
	}
 }
 
 function setValue(val) {
	$("#opinion").val(val);
 }
</script>
</head>

<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">终止意见</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>确定</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close" href="javascript:;" onclick="dialog.close();"><span></span>关闭</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th>
						终止原因:
					</th>
					<td>
						<textarea rows="4" cols="50" style="width: 300px" id="opinion" name="opinion" maxLength="500"></textarea>
					</td>
				</tr>
				<tr>
					<th>常用语:</th>
					<td>
						<div style="word-break: break-all; overflow: auto; width: 300px">
							<c:forEach var="item" items="${taskAppItems}">
								<a class="link reload" onclick="setValue('${item}')"><span></span>${item}</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>