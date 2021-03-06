<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/ligerui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_msg.js"></script>
<html>
	<head>
		<script type="text/javascript">
			var editor = [];
			$(function() {
				$("textarea[name^='m:'].myeditor").each(function(num) {
					var ck=ckeditor(this);
					editor.push(ck);
				});
			});
			function getEditorData(obj){
				return obj.getData();
			}
		</script>
	</head>
</html>