<%--
	time:2011-11-26 18:19:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/getById.jsp" %>
<c:set var="imageUrl" value="${ctx}/platform/system/sysFile/getFileById.ht?type=${type}&fileId=${id}"></c:set>
<html>
<head>
	<title>图片:<e:forHtml value="${title}" />明细</title>	
</head>
<body>
<div class="panel">
		<div class="panel-body">
			<div class="panel-detail">
				<img alt="<e:forHtmlAttribute value="${title}"/>" src="<e:forUri value="${imageUrl}"/>" width="100%" height="100%">	
			</div>
		</div>
</div>
</body>
</html>
