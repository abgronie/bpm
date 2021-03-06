<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>日程交流</title>
<style type="text/css">
	.comments {  
	 width:100%;/*自动适应父布局宽度*/  
	 overflow:auto;  
	 word-break:break-all;  
	/*在ie中解决断行问题(防止自动变为在一行显示，主要解决ie兼容问题，ie8中当设宽度为100%时，文本域类容超过一行时，  
	当我们双击文本内容就会自动变为一行显示，所以只能用ie的专有断行属性“word-break或word-wrap”控制其断行)*/  
	}  
</style>
<f:link href="form.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysPlanScript.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysPlan"></script>

<script type="text/javascript" src="${ctx}/js/hotent/platform/form/AttachMent.js" ></script>

<script type="text/javascript">

	$(function(){
		//初始化选择器
		initData();
		
		//初始化点击人员事件
	    openDetailEvent();
		
	    //初始化删除日程按键
	    initDelete();
	    
	    //初始化增加日程交流信息按键
	    initAddSysPlanExchange();
	    
	    //初始化删除日程交流信息按键
	    initDeleteExchang();
	    
	    //初始化完成任务按键
	    initFinishSysPlan();
	    
	    AttachMent.init("r");
	    
	});
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日程交流</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:if test="${sysPlan.rate!=100}">
						<div class="group">
							<a class="link edit" id="editSysPlan" href="${ctx}/platform/system/sysPlan/edit.ht?id=${sysPlan.id}&currentViweDate=${currentViweDate}&type=${type}" ><span></span>编辑</a>
						</div>
						
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link ok" id="finishSysPlan" ><span></span>完成任务</a>
						</div>
					</c:if>
					<c:if test="${type ne 'myPlan'}">
						<div class="l-bar-separator"></div>
						<div class="group">
								<a class="link del" id='delSysPlan'  href="#"><span></span>删除</a>
						</div>
					</c:if>
					<div class="l-bar-separator"></div>
					<div class="group">
						<c:choose>
							<c:when test="${type eq 'myPlan'}">
								<a class="link back" href="${ctx}/platform/system/sysPlan/myList.ht"><span></span>返回</a>
							</c:when>
							<c:otherwise>
								<a class="link back" href="${ctx}/platform/system/sysPlan/charge.ht?currentViweDate=${currentViweDate}"><span></span>返回</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysPlanExchange" method="post" action="">
				<input type="hidden" name="id" value="${sysPlan.id}"/>
				<input type="hidden" name="currentViweDate" value="${currentViweDate}"/>
				<input type="hidden" name="type" value="${type }"/>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
					
					<tr>
						<th width="20%">任务名称:</th>
						<td>
							${sysPlan.taskName}
						</td>
						<th width="20%">提交人:</th>
						<td>
							<input type="hidden" name="submitId" value="${sysPlan.submitId}"  />
							<input type="hidden" name="submitor" value="${sysPlan.submitor}"  />
							<div id='submitDiv'></div>
						</td>
					</tr>
					
					<tr>
						<th width="20%">负责人:</th>
						<td>
							<input type="hidden" name="chargeId" value="${sysPlan.chargeId}" />
							<input type="hidden" name="charge" value="${sysPlan.charge}"  />
							<div id='chargeDiv'></div>
						</td>
						<th width="20%">参与人:</th>
						<td>
							<input type="hidden" name="participantIds" value="${participantIds}"  />
							<input type="hidden" name="participants" value="${participants}"  />
							<div id='participantDiv'></div>
						</td>
					</tr>
					
					<tr>
						<th width="20%">开始时间:</th>
						<td>
							<fmt:formatDate value='${sysPlan.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
						</td>
						<th width="20%">结束时间:</th>
						<td>
							<fmt:formatDate value='${sysPlan.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
						</td>
					</tr>
					
					<tr>
						<th width="20%">项目名称:</th>
						<td>
							${sysPlan.projectName}
						</td>
                        <th width="20%">日程进度:</th>
						<td>
							${sysPlan.rate}%
						</td>

					</tr>
					
					<tr>
						<th width="20%">相关客户:</th>
						<td colspan="3">
							<input type="hidden" name="customerId" value="${sysPlan.customerId}" />
							<input type="hidden" name="customer" value="${sysPlan.customer}"  />
							<div id='customerDiv'></div>
						</td>
					</tr>
					
					<tr>
						<th width="20%">相关文档: </th>
						<td colspan="3">
							<div name="div_attachment_container">
								<div class="attachement"></div>
								<textarea style="display: none" controltype="attachment"
									id="doc" name="doc" lablename="主表附件" validate="{}">${sysPlan.doc}</textarea>
							</div> 
						</td>
					</tr>
					
					<tr>
						<th width="20%">工单:</th>
						<td colspan="3">
							<input type="hidden" name="runId" value="${sysPlan.runId}"  />
							<input type="hidden" name="runName" value="${sysPlan.runName}"  />
							<div id='runDiv'></div>
						</td>

					</tr>
					
					<tr>
						<th width="20%">内容:</th>
						<td colspan="3">
							<textarea disabled="disabled" rows="16" style="width: 96%;overflow:scroll;" >${sysPlan.description}</textarea>  
						</td>
					</tr>
					
					<tr>
						<th width="20%">交流信息:</th>
						<td colspan="3">
							<c:if test="${sysPlan.rate!=100}">
								<br/>
								
								<div class="group">
									<a id="addSysPlanExchange" class="link add" href="#" ><span></span>添加</a>
								</div>
								
								<div class="l-bar-separator"></div>
								<div class="group">
									<a id="delSysPlanExchange" class="link del"  href="#"><span></span>删除</a>
								</div>
							</c:if>
							
						    <div>
								<table id="exchangeTable" style="float: left;"  class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
									
									<thead>
										<tr>
											<th width="10%;" style="text-align: center;"><input type="checkbox" id="all_exchangeId" onclick="selectCheckbox('all_exchangeId')"/></th>
											<th width="35%;" style="text-align: center;">提交内容</th>
											<th width="20%;" style="text-align: center;">提交人</th>
											<th width="20%;" style="text-align: center;">提交时间</th>
											<th width="15%;" style="text-align: center;">管理</th>
										</tr> 
									</thead>
									
								    <c:choose>
										
										<c:when test="${fn:length(sysPlanExchangeList)>0 }">
											<c:forEach items="${sysPlanExchangeList}" var="exchange">
												<tr>
													<td style="text-align: center;">
													     <input type="checkbox" name="exchangeId" value="${exchange.id}" parentId="${exchange.planId}">
													</td>
													<td style="text-align: center;" >
													    <c:choose>
															<c:when test="${fn:length(exchange.content)>21}">
																 <c:out value="${fn:substring(exchange.content, 0, 20)}..." /> 
															</c:when>
															<c:otherwise>
																<c:out value="${exchange.content}" /> 
															</c:otherwise>
														</c:choose>	
													</td>
													<td>
														<c:if test="${fn:length(exchange.submitor)>0}">
															<span class="owner-span">
																<a class="moreinfo" ownerid="${exchange.submitId}" hrefstr="${ctx}/platform/system/sysUser/get.ht?openType=detail&userId=${exchange.submitId}" href="#">
																	${exchange.submitor}
																</a>
															</span>
														</c:if>
													</td>
													<td style="text-align: center;" >
													    <fmt:formatDate value='${exchange.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
													</td>
													<td style="text-align: center;" >
													    <c:if test="${sysPlan.rate!=100}">
													    	<a class="link" onclick="editSysPlanExchange('${exchange.id}','${exchange.planId}')" href="#">编辑</a>
													    </c:if>
													    <a class="link" onclick="openSysPlanExchange('${exchange.id}','${exchange.planId}')" href="#">查看</a>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										
										<c:otherwise>
											<tr class="empty-div" >
												<td colspan="5" style="text-align: center;"> 
													暂没有交流信息
												</td>
											</tr>
										</c:otherwise>
										
									</c:choose>							 
							   			
								</table>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<p>
		<br>
	</p>
	<p>
		<br>
	</p>
	<p>
		<br>
	</p>
	
</body>
</html>

