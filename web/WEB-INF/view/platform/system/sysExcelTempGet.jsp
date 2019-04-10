<%--
	time:2015-06-13 14:36:35
	desc:edit the Excel模板
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>Excel模板明细</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript">

	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysExcelTempForm" >
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0" type="main">
					<tr>
						<th width="20%">模板名称:</th>
						<td>${sysExcelTemp.tempName}</td>
					</tr>
					<tr>
						<th width="20%">模板代码: </th>
						<td>${sysExcelTemp.tempCode}</td>
					</tr>
					<tr>
						<th width="20%">填写说明行高:</th>
						<td >${sysExcelTemp.tempDesHeight}</td>
					</tr>
					<tr>
						<th width="20%">模板填写说明:</th>
						<td >
							${sysExcelTemp.tempDes}
						</td>
					</tr>
					<tr>
						<th width="20%">备注:</th>
						<td >${sysExcelTemp.memo}</td>
					</tr>
					
				
				</table>
				<table class="table-grid table-list" cellpadding="1" cellspacing="1"
					id="sysExcelTempDetail" formtype="form" type="subtable">
					<tr>
						<td colspan="5">
							
							<div align="center">Excel模板明细</div></td>
					</tr>
					<tr>
						<th>列名称</th>
						<th>数据类型</th>
						<th>列长</th>
						<th>显示顺序</th>
					</tr>
					<c:forEach items="${sysExcelTempDetailList}"
						var="sysExcelTempDetailItem" varStatus="status">
						<tr type="subdata">
							<td style="text-align: center" name="columnName">${sysExcelTempDetailItem.columnName}</td>
							<td style="text-align: center" name="columnType">
								<c:choose>
									<c:when test="${sysExcelTempDetailItem.columnType eq 0}">文本</c:when>
								</c:choose>
							</td>
							<td style="text-align: center" name="columnLen">${sysExcelTempDetailItem.columnLen}</td>
							<td style="text-align: center" name="showIndex">${sysExcelTempDetailItem.showIndex}</td>
							<input type="hidden" name="columnName"
								value="${sysExcelTempDetailItem.columnName}" />
							<input type="hidden" name="columnType"
								value="${sysExcelTempDetailItem.columnType}" />
							<input type="hidden" name="columnLen"
								value="${sysExcelTempDetailItem.columnLen}" />
							<input type="hidden" name="showIndex"
								value="${sysExcelTempDetailItem.showIndex}" />
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="id" value="${sysExcelTemp.id}" /> 
				
			</form>
		</div>
		
	</div>
	
</body>
</html>
