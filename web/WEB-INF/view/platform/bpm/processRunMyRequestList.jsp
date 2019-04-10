<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title>我的请求</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/SelectUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js"></script>
<script type="text/javascript">

	function showDetail(obj){
		var url = $(obj).attr("action");
		jQuery.openFullWindow(url);
	};
	
	//重新提交
	function executeTask(procInstId){
		 var url= "${ctx}/platform/bpm/task/toStart.ht?instanceId="+procInstId;
		 jQuery.openFullWindow(url);
	};
	
	//追回
	function recover(runId){
		// FlowUtil.recover({runId:runId,backToStart:0,callback:function(){
		// 	window.location.href="myRequestList.ht";
		// }});

        $("#TC").css('top', '25%');
        $("#TC").css('left', '25%');
        $("#TC").show();
        $("#MASK").show();
        $("#RId").val(runId);
	}

    // 浏览器兼容 取得浏览器可视区高度
    function getWindowInnerHeight() {
        return $("#defLayout").height();
    }

    // 浏览器兼容 取得浏览器可视区宽度
    function getWindowInnerWidth() {
        return $("#defLayout").width();
    }
    function postRecover(){
        var json=$("#frmRecover").serialize() ;
        alert(json)
        var url=__ctx +"/platform/bpm/processRun/recover.ht";
        $.post(url,json,function(responseText){
            var obj=new com.hotent.form.ResultMessage(responseText);
            if(obj.isSuccess()){
                $.ligerDialog.success(obj.getMessage(),'提示',function(){
                    document.location.reload();
                });
                closeTC();
            }
            else{
                $.ligerDialog.err('提示','保存失败!',obj.getMessage());
            }
        });
    }

    function closeTC(){
        $("#TC").hide();
        $("#MASK").hide();
    }
</script>

</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">我的请求</span>
			</div>
			<%----%>
			<!-- 遮罩层 -->
			<div id="MASK" class="l-window-mask" height="100%" style="display: none; "></div>
			<!-- 撤回弹窗 -->
			<div class="panel" id="TC" style="z-index: 10003; position: fixed; width: 600px; height: 360px; display: none; background: white;">
					<div class="panel-top">
						<div class="tbar-title" style="display:block;background:#2e7ccb;">
							<span class="tbar-label">撤回原因</span>
						</div>
						<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>撤回</a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link close" href="javascript:;" onclick="closeTC()"><span></span>关闭</a></div>
							</div>
						</div>
					</div>

					<div class="panel-body">
						<form id="frmRecover">
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<th nowrap="nowrap">发送方式:</th>
									<td>
										<input type="checkbox" name="informType" value="1" checked="checked">邮件
										<input type="checkbox" name="informType" value="2" checked="checked">短信
										<input type="checkbox" name="informType" value="3" checked="checked">站内消息</label>
										<input type="checkbox" name="informType" value="4">微信消息</label>
										<%--
                                        <c:forEach items="${handlersMap}" var="item">
                                           <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
                                            ${item.value.title }
                                        </c:forEach>
                                        --%>
									</td>
								</tr>
								<tr>
									<th>撤回原因:</th>
									<td>
										<textarea rows="7" cols="61" style="width:350px;" id="opinion" name="opinion"></textarea>
										<input type="hidden" name="runId" value="${runId }"/>
										<input type="hidden" name="backToStart" value="${backToStart}"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
			</div>

			<div class="panel-toolbar">
				<div class="toolBar">
					<%-- 
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>
						<div class="l-bar-separator"></div>
						<div class="group"><a href="javascript:;" class="link reset" onclick="$.clearQueryForm();"><span></span>重置</a></div>
					</div> 
					--%>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="myRequestList.ht">
						<ul class="row">
						<input type="hidden" name="nodePath" value="${param['nodePath']}" title="流程分类节点路径"></input>
						<li>
							<span class="label">事项名称:</span>
							<input type="text" name="Q_subject_SUPL" size="18" class="inputText"  value="${param['Q_subject_SUPL']}"/>
						</li>
						<li>
							<span class="label">流程名称:</span>
							<input type="text" name="Q_processName_SUPL" size="18" class="inputText"  value="${param['Q_processName_SUPL']}" />
							<input type="hidden"   id="orgId" name="Q_orgId_L" value="${param['Q_orgId_L']}" />
						</li>
						<li>
							<span class="label">状态:</span>
							<select name="Q_status_SN">
								<option value="">所有</option>
								<option value="1" <c:if test="${param['Q_status_SN'] == '1'}">selected</c:if>>正在运行</option>
								<option value="5" <c:if test="${param['Q_status_SN'] == '5'}">selected</c:if>>撤销</option>
								<option value="6" <c:if test="${param['Q_status_SN'] == '6'}">selected</c:if>>驳回</option>
								<%-- <option value="7" <c:if test="${param['Q_status_SN'] == '7'}">selected</c:if>>追回</option> --%>
							</select>
						</li>
						<%-- 是否有全局流水号 
						<c:if test="${hasGlobalFlowNo }">
							<li><span class="label">工单号:</span><input type="text" name="Q_globalFlowNo_SL"  class="inputText"  value="${param['Q_globalFlowNo_SL']}"/></li>
						</c:if>
						--%>
						<div class="row_date">
						<li>
							<span class="label">申请时间&nbsp;从:</span>
							<input name="Q_begincreatetime_DL" id="Q_begincreatetime_DL" size="18" class="inputText datePicker" datetype="1"  value="${param['Q_begincreatetime_DL']}" />
						</li>
						<li>
							<span class="label">至: </span>
							<input name="Q_endcreatetime_DG" id="Q_endcreatetime_DG" size="18" class="inputText datePicker" datetype="2" value="${param['Q_endcreatetime_DG']}"  />
						</li>
						</div>
						
						</ul>
					</form>
			</div>
		</div>
		<div class="panel-body">
			<display:table name="processRunList" id="processRunItem" requestURI="myRequestList.ht" sort="external" cellpadding="1"
				cellspacing="1" class="table-grid">
				<display:column titleKey="序号" media="html" style="width:20px;">${processRunItem_rowNum}</display:column>
				<%-- 是否有全局流水号 
				<c:if test="${hasGlobalFlowNo }">
					<display:column property="globalFlowNo" title="工单号" sortable="true"  sortName="globalFlowNo" style="text-align:left;"></display:column>
				</c:if>
				--%>
				<display:column  titleKey="事项名称" sortable="true" sortName="subject" style="text-align:left">
						<c:choose>
							<c:when test="${!processRunItem.allowBackToStart and (processRunItem.status==4 or processRunItem.status==5)}">
								<a href="#${processRunItem.actInstId}" onclick="javascript:executeTask('${processRunItem.actInstId}')" title='${processRunItem.subject}'>${f:subString(processRunItem.subject)}</a>
							</c:when>
							<c:otherwise>
								<a name="processDetail" onclick="showDetail(this)" href="#${processRunItem.runId}"  action="info.ht?prePage=myRequest&link=1&runId=${processRunItem.runId}" title='${processRunItem.subject}'>${f:subString(processRunItem.subject)}</a>								
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column property="processName" titleKey="流程名称" sortable="true" sortName="processName" style="text-align:left"></display:column>
				<display:column titleKey="申请时间" sortable="true" sortName="createtime">
					<fmt:formatDate value="${processRunItem.createtime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</display:column>
				<display:column titleKey="持续时间" sortable="true" sortName="duration">
								${f:getDurationTime(processRunItem.createtime)}
				</display:column>
				<display:column titleKey="类型" >
						<c:out value="${processRunItem.typeName}"></c:out>
				</display:column>		
				<display:column titleKey="状态" sortable="true" sortName="status" style="width:50px;" >
						<f:processStatus status="${processRunItem.status}"></f:processStatus>
				</display:column>
				<%--  --%>
				<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">			
					<c:if test="${processRunItem.status!=2 and processRunItem.status!=5}">
						<a href="javascript:;" onclick="recover(${processRunItem.runId})" class="link back">撤回</a>
					</c:if>
				</display:column>
			</display:table>
			<hotent:paging tableId="processRunItem"></hotent:paging>
		</div>
	</div>
	</div>
</body>
</html>


