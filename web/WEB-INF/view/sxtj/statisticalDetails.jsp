<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="hotent" uri="http://www.jee-soft.cn/paging" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=10; IE=9; IE=8; IE=7; IE=EDGE" />
<f:js pre="js/lang/common" ></f:js>
<f:js pre="js/lang/js" ></f:js>
<html>
<head>
	<meta charset="UTF-8">
	<title>办事统计详情</title>
	<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
	<link href="${ctx}/bstj/css/bootstrap.min.css" rel="stylesheet">
	<script src="${ctx}/bstj/js/jquery-1.11.1-min.js"></script>
	<script src="${ctx}/bstj/js/bootstrap.min.js"></script>
	<!--bootsrap-table-->
	<link href="${ctx}/bstj/css/bootstrap-table.min.css" rel="stylesheet">
	<script src="${ctx}/bstj/js/bootstrap-table.min.js"></script>
	<script src="${ctx}/bstj/js/bootstrap-table-zh-CN.min.js"></script>
	<!--daterangepicker-->
	<link href="${ctx}/bstj/css/daterangepicker.min.css" rel="stylesheet" />
	<script src="${ctx}/bstj/js/moment.min.js"></script>
	<script src="${ctx}/bstj/js/daterangepicker.min.js"></script>
	<!--echart-->
	<script type="text/javascript" src="${ctx}/bstj/js/echarts.js"></script>
	<!--自定义css-->
	<link rel="stylesheet" href="${ctx}/bstj/css/style.css" />
	<style>
	   .back-btn{
	           background-color: #549fea;
			   border-color: #549fea;
			   border-radius: 4px;
			   padding: 4px 15px;
			   margin: 15px;
	   }
	</style>
</head>
	<body>
		<input type="hidden" id="defId" value="${defId}">
		<button onclick="history.go(-1);" type="button" class="btn btn-primary back-btn">返回</button>
		<!-- <div class="drop"><a class="" href="javascript:void(0);" onclick="history.go(-1);">返回</a></div> -->
		<div class="qjsq-content">
			<div class="row">
				<div class="col-md-3 p-r0">
					<div class="classfiy" id="tab_xq">
						<label>今日</label>
						<label>本周</label>
						<label>本月</label>
						<label class="active">全部</label>
					</div>
				</div>
				<div class="col-md-9 p-l0 text-right">
					<select class="form-control" id="spzt_xq">
						<option value="">审批状态</option>
						<option value="1">未办结</option>
						<option value="2">已办结</option>
					</select>
					<select class="form-control" id="ssyx_xq">
						<option value="">所属院系</option>
						<c:forEach items="${positionsList}" var="position">
							<option value="${position.posId}">
								${position.posName}
							</option>
						</c:forEach>
					</select>
					<select class="form-control" id="sfcq_xq">
						<option value="">是否超期</option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
					<div class="input-prepend">
						<input type="text" id="date04" name="dateClean" class="form-control" />
						<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
					</div>
					<button onclick="sxbltjxqcx()" type="button" class="btn btn-primary search-btn">查询</button>
				</div>
			</div>
			<table id="applyTable"></table>
		</div>
		<script type="text/javascript" src="${ctx}/bstj/js/base.js"></script>
	</body>
	<script type="text/javascript">
		//办事统计详情
		window.onload=function (){

            //双日历插件
            var searchModel = {
                Start: null,
                End: null
            };

			var defId = $("#defId").val();
			$('.qjsq-content #applyTable').bootstrapTable({
				url: 'statisticalDetails.ht?defId='+defId,			//请求后台的URL（*）
				method: 'get',                      //请求方式（*）
				//queryParams: queryParams,			//传递参数（*）
				pageNumber: 1,                      //初始化加载第一页，默认第一页
				pageSize: 10,                       //每页的记录行数（*）
				pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
				showRefresh: true,                  //是否显示刷新按钮
				showColumns: true,                  //是否显示所有的列
				pagination: true,
				paginationPreText:'<',
		        paginationNextText:'>',
		        locale:'zh-CN',                      //中文支持
				columns: [
			          {
			              field: 'id', // 返回json数据中的name
			              title: '序号', // 表格表头显示文字
			              align: 'center', // 左右居中
			              valign: 'middle', // 上下居中
			              width: '5%',
		            	  formatter : function(value, row, index) {
		            			  return index+1;
		            	  }
			          }, {
			              field: 'name',
			              title: '流程名称',
			              align: 'center',
			              valign: 'middle',
			              formatter : function(value, row, index) {
	            			  return '<a href="javascript:void(0);" onclick="showProcessRunInfo('+row.runId+')" title="流程图">'+value+'</a>';
	            	  	 }
			          }, {
			              field: 'service',
			              title: '所属服务',
			              align: 'center',
			              valign: 'middle',
			          }, {
			              field: 'department',
			              title: '所属院系',
			              align: 'center',
			              valign: 'middle',
			              formatter : function(value, row, index) {
			            	  if(value == null){
			            		  return '<span class="red">未设置院系</span>';
			            	  }else{
			            		  return value;
			            	  }
			              }
			          }, {
			              field: 'time',
			              title: '审批总耗时',
			              align: 'center',
			              valign: 'middle',
			          }, {
			              field: 'status',
			              title: '审批状态',
			              align: 'center',
			              valign: 'middle',
			              formatter : function(value, row, index) {
			            	  var html;
			            	  if(value == "1"){
			            		  html = '<span class="red">未办结</span>'
			            	  }else{
			            		  html = '<span class="blue">已办结</span>'
			            	  }
		        			  return html;
			              }
			          }, {
			              field: 'whether',
			              title: '是否超期',
			              align: 'center',
			              valign: 'middle',
			              formatter : function(value, row, index) {
		        			  var html;
			            	  if(value == "1"){
			            		  html = '<span class="blue">是</span>'
			            	  }else{
			            		  html = '<span class="red">否</span>'
			            	  }
		        			  return html;
			              }
			          }],
			        onLoadSuccess: function(){  //加载成功时执行
				        //刷新后，清空输入框、时间框、审批状态、所属院系、是否超期
                        $("#spzt_xq").val('');
                        $("#ssyx_xq").val('');
                        $("#sfcq_xq").val('');
                        $("#date04").val('');
                        $('[name="dateClean"]').val('');
                        searchModel.Start = null;
                        searchModel.End = null;

			            console.info("加载成功");
			        },
			        onLoadError: function(){  //加载失败时执行
			              console.info("加载数据失败");
			        }
			});
			
			// 查看事项办理统计点击详情
			$("#tab_xq label").click(function() {
				$(this).addClass("active").siblings().removeClass("active");
				var defId = $("#defId").val();
				var txt=$(this).context.innerText;
				var type;
				if('今日' == txt){
					type = "1";
				}else if('本周' == txt){
					type = "2"
				}else if('本月' == txt){
					type = "3";
				}else{
					type = "4";
				}
				$.ajax({
		            type: "post",
		            url: "statisticalDetails.ht",
		            data: {
		            	defId: defId,
		            	type: type
					},
		            dataType:"json",
				    success : function(json) {
								$('.qjsq-content #applyTable').bootstrapTable('load', json);
							}
				});
			});


		}
		
		function sxbltjxqcx(){
			var defId = $("#defId").val();
			var ssyx = $("#ssyx_xq").val();
			var spzt = $("#spzt_xq").val();
			var sfcq = $("#sfcq_xq").val();
			var dataTmie = $("#date04").val();
			var starttime = dataTmie.substring(0,10);
			var endtime = dataTmie.substring(13,23);

            if(starttime!="undefined"&&starttime!=null&&starttime!=""&&starttime.length>0){
                starttime = starttime + " 00:00:00";
            }
            if(endtime!="undefined"&&endtime!=null&&endtime!=""&&endtime.length>0){
                endtime = endtime + " 23:59:59";
            }


			$.ajax({
		        type: "post",
		        url: "statisticalDetails.ht",
		        data: {
		        	defId: defId,
		        	starttime: starttime,
		        	endtime: endtime,
		        	ssyx: ssyx,
		        	spzt: spzt,
		        	sfcq: sfcq
				},
		        dataType:"json",
			    success : function(json) {
							$('.qjsq-content #applyTable').bootstrapTable('load', json);
						}
			});
		}
		
		//显示审批历史
		function showProcessRunInfo(runId){
			var url= '${ctx}/platform/bpm/processRun/processImage.ht?runId='+runId,
			title = '流程图';
			DialogUtil.open({
				url:url,
				title:title,
				height:'650',
				width:'800'
			});
		};
	</script>
</html>