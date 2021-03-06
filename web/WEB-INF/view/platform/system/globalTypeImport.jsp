
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>系统分类导入</title>
<%@include file="/commons/include/form.jsp" %>
<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" /> --%>
<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	//var obj=window.dialogArguments;
	var obj=dialog.get("obj");
	
	$(function(){
		var option={success:showResponse};
		$("#importForm").ajaxForm(option);
		if(obj!=null){
			$("#typeId").val(obj.typeId);
			$("#catId").val(obj.catId);
		}
		$("#btnSave").click(function(){
			var path = $('#xmlFile').val();
			var extName = path.substring(path.length-3, path.length);
			if(extName!='xml'){
				$.ligerDialog.warn("请选择 *.xml文件进行导入!");
			}else{
				$("#importForm").submit();
			}
		});		
	});
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.success(obj.getMessage(),'提示信息',function(){
				dialog.close();
			});
	    }else{//失败
	    	$.ligerDialog.err('出错信息',"系统分类导入失败",obj.getMessage());
	    }
	}
	
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统分类导入</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSave"><span></span>导入</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="importForm" name="importForm" method="post" target="win" action="importXml.ht" enctype="multipart/form-data">
			<div class="row">
			 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="22%">选择文件：</th>
					<td width="78%"><input type="file" size="40" name="xmlFile" id="xmlFile"/></td>						
				</tr>
			</table>				
			</div>
			<input type="hidden" name="typeId" id="typeId" value=""/>
			<input type="hidden" name="catId" id="catId" value=""/>
	    </form>
	</div><!-- end of panel-body -->				
</div> 
</body>
</html>