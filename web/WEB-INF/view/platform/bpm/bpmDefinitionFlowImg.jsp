
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript">
	var _height=${shapeMeta.height};
	var _width=${shapeMeta.width};
	function setIframeHeight(){
		var mainIFrame = window.parent.document.getElementById("flowchart");
		console.log(mainIFrame.outerHTML);
		if(!mainIFrame)return;
		mainIFrame.height=_height;
		mainIFrame.width=_width;
	};
	
	$(function(){
		if(self!=top){
			setIframeHeight();
		}
	});
	</script>
	<title>流程示意图</title>
</head>
<body>
	<div style="padding-top:40px;background-color: white;">
		<div style="position: relative;background:url('${ctx}/bpmImage?definitionId=${actDefId}') no-repeat;width:${shapeMeta.width}px;height:${shapeMeta.height}px;">
			 
		</div>
	</div>
</body>
</html>