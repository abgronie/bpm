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
	<title>办事统计</title>
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
	<!-- 流程服务节点平均时间 -->

</head>
	<body>
		<input type="hidden" id="sxtjIndex">
		<div class="bstj-content">
			<div class="site-data" id="tab_zdsjtj">
				<h4 class="sm-title">站点数据统计</h4>
				<div class="time-tabs">
					<label>今日</label>
					<label>本周</label>
					<label>本月</label>
					<label class="active">全部</label>
				</div>
				<div class="tab-content" id="zdsjtj">
					<ul>
						<li>
							<a href="#">
								<div class="col-md-4"><img src="${ctx}/bstj/images/data-icon01.png"></div>
								<div class="col-md-8">
									<p class="num">${zdsjtjMap.allservice}</p>
									<p class="text">接入的服务数</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#">
								<div class="col-md-4"><img src="${ctx}/bstj/images/data-icon02.png"></div>
								<div class="col-md-8">
									<p class="num">${zdsjtjMap.apply}</p>
									<p class="text">发起的流程总数</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#">
								<div class="col-md-4"><img src="${ctx}/bstj/images/data-icon03.png"></div>
								<div class="col-md-8">
									<p class="num">${zdsjtjMap.done}</p>
									<p class="text">流程办结总数</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#">
								<div class="col-md-4"><img src="${ctx}/bstj/images/data-icon04.png"></div>
								<div class="col-md-8">
									<p class="num">${zdsjtjMap.timeout}</p>
									<p class="text">超时未办结总数</p>
								</div>
							</a>
						</li>
						<li>
							<a href="#">
								<div class="col-md-4"><img src="${ctx}/bstj/images/data-icon05.png"></div>
								<div class="col-md-8">
									<p class="num">${zdsjtjMap.todo}</p>
									<p class="text">待办流程总数</p>
								</div>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="statistics-div">
				<div class="col-md-8 p-l0">
					<div class="left-div">
						<ul id="myTab" class="nav nav-pills">
							<li class="active">
								<a href="#tab01" data-toggle="tab">事项办理统计</a>
							</li>
							<li>
								<a href="#tab03" data-toggle="tab">审批人员超时统计</a>
							</li>
						</ul>

						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="tab01">
								<div class="row">
									<div class="col-md-4 p-r0">
										<div class="classfiy">
											<label>今日</label>
											<label>本周</label>
											<label>本月</label>
											<label class="active">全部</label>
										</div>
									</div>
									<div class="col-md-8 p-l0 text-right">
										<div class="input-search">
											<input type="text" id="sxbltjfw" class="form-control" placeholder="请输入服务关键字" />
										</div>
										<div class="input-prepend">
											<input type="text" id="date01" name="dateClean" class="form-control" />
											<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
										</div>
										<button onclick="sxbltjcx()" type="button" class="btn btn-primary search-btn">查询</button>
									</div>
								</div>
								<table id="infoTable"></table>
							</div>
							<div class="tab-pane fade" id="tab03">
								<div class="row">
									<div class="col-md-4 p-r0">
										<div class="classfiy" >
											<label>今日</label>
											<label>本周</label>
											<label>本月</label>
											<label class="active">全部</label>
										</div>
									</div>
									<div class="col-md-8 p-l0 text-right">
										<select class="form-control" id="ssyx_rycs">
											<option value="">所属院系</option>
											<c:forEach items="${positionsList}" var="position">
												<option value="${position.posId}">
													${position.posName}
												</option>
											</c:forEach>
										</select>
										<div class="input-prepend">
											<input type="text" id="date03" name="dateClean" class="form-control" />
											<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
										</div>
										<button type="button" onclick="rycstjcx()" class="btn btn-primary search-btn">查询</button>
									</div>
								</div>
								<table id="infoTabletwo"></table>
								<!-- <div class="text-center">
									<ul class="pagination interval-pg paginatio">
										<li>
											<p>共50条记录</p>
										</li>
										<li>
											<a href="#"><span class="glyphicon glyphicon-menu-left"></span></a>
										</li>
										<li class="active">
											<a href="#">1</a>
										</li>
										<li>
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li><span>...</span></li>
										<li>
											<a href="#">20</a>
										</li>
										<li>
											<a href="#"><span class="glyphicon glyphicon-menu-right"></span></a>
										</li>
									</ul>
								</div> -->
							</div>

						</div>

					</div>
				</div>
				<div class="col-md-4 p-0">
					<div class="right-div" id="tab_ten">
						<h4 class="sm-title">Top10事项统计</h4>
						<div class="time-tabs">
							<label>今日</label>
							<label>本周</label>
							<label>本月</label>
							<label class="active">全部</label>
						</div>
						<div class="tab-content">
							<table id="rankingTable"></table>
						</div>
					</div>
				</div>
			</div>
			
		</div>

		<script type="text/javascript" src="${ctx}/bstj/js/base.js"></script>
	</body>
</html>
