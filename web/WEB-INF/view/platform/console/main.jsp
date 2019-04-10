<%@page import="com.hotent.core.api.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
  pageEncoding="UTF-8" 
  import="com.hotent.platform.model.system.Resources,
  		com.hotent.core.util.AppUtil,
  		java.util.Properties"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="skinStyle" value="${skinStyle}" />
<%
	String appName=PropertyUtil.getByAlias("appName");
%>
<head>
    <title><%=appName%></title>
    <link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<f:link href="Aqua/css/ligerui-all-index.css"></f:link>
	<f:link href="indexNew.css"></f:link>
	<f:link href="select.css"></f:link>
	<f:link href="tree/zTreeIndexStyle.css"></f:link>

	<f:js pre="js/lang/common" ></f:js>
	<f:js pre="js/lang/js" ></f:js>
    
	<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
   	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
   	<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
    <script type="text/javascript" src="${ctx}/js/lg/base.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDrag.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayoutIndex.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js"></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerAccordion.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysPopupRemind/SysPopupRemindUtil.js"></script>
    <script type="text/javascript">
		    if(top!=this){//当这个窗口出现在iframe里，表示其目前已经timeout，需要把外面的框架窗口也重定向登录页面
				  top.location='<%=request.getContextPath()%>/platform/console/main.ht';
			}
    		var mian_layout_;
            var tab = null;
            var tree = null;
            var ctxPath=__ctx;
            var accordion = null;
            var currTabId='home';
          
            var setting = {
            		view: {showLine: true,nameIsHTML: true,showIcon: showIconForTree,dblClickExpand: false},
            		data: {
						key : {name: "resName"},
						simpleData: {enable: true,idKey: "resId",pIdKey: "parentId"}
					},
            		callback: {onClick: zTreeOnClick,
            				   onNodeCreated: zTreeOnNodeCreated	
            		}
            };

            $(function (){
                //布局
                mian_layout_ =$("#layoutMain").ligerLayout({
                	topHeight :80,
                	leftWidth: 180, 
                	height: '100%',
                	onHeightChanged: heightChanged });
				//取得layout的高度
                var height = $(".l-layout-center").height();
                $("#leftTree").height(height-45);
                
                //$("div.l-layout-collapse-left,div.l-layout-left").css("margin","2px 0 0 0");
                //Tab
                $("#framecenter").ligerTab({ height: height,onBeforeSelectTabItem:function(tabid){ currTabId=tabid; }});
				$(".l-tab-links").width($("#framecenter").width()-185);
                //面板
                $("#accordion1").ligerAccordion({ height: height, speed: null });

				//获取tab的引用
                tab = $("#framecenter").ligerGetTabManager();
                accordion = $("#accordion1").ligerGetAccordionManager();
				//加载菜单
				loadMenu();
                //隐藏加载对话框
                $("#pageloading").hide();
 
                $("#menuPanel").delegate("a.menuItem", "click", function(){
                    var id=$(this).attr("id");
                    loadTree(id);
                    $(this).siblings().removeClass("menuItem_hover").end().addClass("menuItem_hover");
                    jQuery.setCookie("selectTab",id);
                    $(".l-layout-header-inner").text($(".menuItem_hover span").text());
                });
                $(".l-layout-collapse-left").delegate("span.menu_icon", "click", function(){
                	$(".current").removeClass("current");
                    $(this).parent(".menu_list").addClass("current");
                    $("#"+$(this).parent(".menu_list").attr("tId"),".ztree").addClass("current");
                    if($(this).attr("defaultUrl") != "null"){
	                    addToTab(__ctx+$(this).attr("defaultUrl"),$(this).attr("resName"),$(this).parent(".menu_list").attr("id"),$(this).attr("icon"))
                    }
                });
                $(".l-layout-collapse-left").delegate("span.menu_name", "click", function(){
                	$(".current").removeClass("current");
                    $(this).closest(".menu_list").addClass("current");
                    $("#"+$(this).closest(".menu_list").attr("tId"),".ztree").addClass("current");
                    if($(this).attr("defaultUrl") != "null"){
	                    addToTab(__ctx+$(this).attr("defaultUrl"),$(this).attr("resName"),$(this).closest(".menu_list").attr("id"),$(this).attr("icon"))
                    }
                });
                $(".l-layout-collapse-left").delegate("p.abs_p", "click", function(){
                	$(".current").removeClass("current");
                    $(this).closest(".menu_list").addClass("current");
                    $("#"+$(this).closest(".menu_list").attr("tId"),".ztree").addClass("current");
                    if($(this).attr("defaultUrl") != "null"){
	                    addToTab(__ctx+$(this).attr("defaultUrl"),$(this).attr("resName"),$(this).attr("id"),$(this).attr("icon"))
                    }
                });
                $(".l-layout-collapse-left").delegate("li.menu_list", "hover", function(){
                	$(".menu_abs_nextArr").each(function(){
                      var len = $(this).children().children("dd").length;
                      if(len == 0){
                    	  $(this).children().remove();
                    	  $(this).removeClass("menu_abs_nextArr");
                      }
                    });
                });
                $(".l-layout-collapse-left").delegate("dl[class$='_Site']", "hover", function(e){
                	var htop = $(this).closest(".menu_abs").position().top;
                	if(e.type =='mouseenter'){
	                	var dlHeight = $(window).height()-$(this).height()-[$(this).offset().top-$(document).scrollTop()];
	                	if(dlHeight != $(window).height() && dlHeight < -1){
	                		var ht =htop +dlHeight;
	                		$(this).closest(".menu_abs").attr("style","top:"+ht+"px;");
	                	}
                	}else{
                		$(this).closest(".menu_abs").attr("style","top:"+htop+"px;");
                	}
                });
                //用户名后下拉箭头覆盖事件
                /*
                var button = $('#loginButton');
        	    var box = $('#loginBox');
        	    var shade=$('#shadeEm');
        	    button.mouseover(function(login) {
        	        box.toggle();
        	        shade.toggle();
        	        button.toggleClass('active');   
        	    	t= setTimeout(function(){button_mouseout();},2000);
        		});
        		$(this).mouseup(function(login) {
        			if ($(login.target).hasClass("more")){
        				var prehref=$(login.target).attr('prehref');
        				if(prehref)
       						addToTab(prehref,$(login.target).text(),$(login.target).attr('resid'));
        				button.removeClass('active');
        				box.hide();
        				shade.hide();
        			}
        		});
        		var button_mouseout=function(){
    				box.hide();
    				shade.hide();
    				button.removeClass('active');	
    			}
        		box.mouseenter(function(){
        			clearTimeout(t);
        		});
        		box.mouseleave(function(){
        			box.hide();
        			shade.hide();
    				button.removeClass('active');
        		});*/
        		/*
        		button.mouseout(function(){
        			setTimeout(function(){
        				box.hide();
        				shade.hide();
        				button.removeClass('active');	
        			},200);
        		});*/
                window.onresize = function(){initRollButton()};
                
                //右下角提醒框
                SysPopupRemindUtil.show("",null,"300");
                // 顶部的未读信息
    			$(".index_message").click(function(){
    				var url2 = "/platform/system/messageReceiver/list.ht";
        			$.post(__ctx + "/platform/console/getResourceNode.ht?columnUrl="
        					+ url2, function(data) {
        				if (data == null | data == "") {
        					alert("更多路径配置有误", '提示信息');
        				} else {
        					addToTab(__ctx + url2, data.resName, data.resId,
        							__ctx + data.icon);
        				}
        			});
           		});
    			$(".index_link").click(function(){
    				$("li[tabid='home']").click();
           		});
                // 修改密码
    			$(".user_set_mima").click(function(){
    				var url2 = "/platform/system/sysUser/list.ht";
        			$.post(__ctx + "/platform/console/getResourceNode.ht?columnUrl="
        					+ url2, function(data) {
        				if (data == null | data == "") {
        					alert("更多路径配置有误", '提示信息');
        				} else {
        					addToTab("${ctx}/platform/system/sysUser/modifyPwdView.ht?userId=${userId}", "修改密码", "-001",
        							__ctx + data.icon);
        				}
        			});
           		});
                // 个人信息
    			$(".user_set_xinxi").click(function(){
    				var url2 = "/platform/system/sysUser/list.ht";
        			$.post(__ctx + "/platform/console/getResourceNode.ht?columnUrl="
        					+ url2, function(data) {
        				if (data == null | data == "") {
        					alert("更多路径配置有误", '提示信息');
        				} else {
        					addToTab("${ctx}/platform/system/sysUser/get.ht?userId=${userId}&canReturn=1", "个人信息", "-002",
        							__ctx + data.icon);
        				}
        			});
           		});
                $(".l-layout-left").css({ "width": "190"});
            });
        	

			//布局大小改变的时候通知tab，面板改变大小
            function heightChanged(options){
				
            	$("iframe").each(function(){
            		var tabName=$(this).attr("name");
            		if(tabName!=undefined){
            			$(this).height(options.middleHeight-35);
            		}
            		if(tabName=="home"){
            			$(this).attr('src', $(this).attr('src'));
            		}
            	});
				$("#leftTree").height(options.middleHeight-40);
                if (tab){
                	var tabContent =$(".l-tab-content"),
                		h =tabContent.height();
                	tabContent.height(h+options.diff+15);
                }
                //    tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 25 > 0)
                    accordion.setHeight(options.middleHeight - 25);
                
            }
		      
            var aryTreeData=null;
            //返回根节点
            function getRootNodes(){
            	var nodes=new Array();
            	for(var i=0;i<aryTreeData.length;i++){
            		var node=aryTreeData[i];
            		if(node.parentId==0){
            			nodes.push(node);
            		}
            	}
            	return nodes;
            };
            //初始化菜单滚动按钮
            function initRollButton(){
            	// 滚动 按钮宽度  =  窗体宽度 - 320 - div.welcome
            	//var welcomeWidth = 390; 
        		//$("div.menuParent").width( parseInt($(window).width())-welcomeWidth-320);
            	
        		var pWidth = $("div.menuParent").width(),sWidth = parseInt($("div.menuPanel").css("padding-left")+$("div.menuPanel").css("padding-left"));
        		$("a.menuItem").each(function() {
					sWidth += $(this).outerWidth(true);;
				});
        		if(sWidth<=0)return;		
        		var left = pWidth - sWidth;
        		if (left <= 0) {
        			$(".nav_left").show();
        			$(".nav_right").show();
        		}else{
        			$(".nav_left").hide();
        			$(".nav_right").hide();
        		}
        		$("div.menuPanel").css("left",0);
        	};
            //加载菜单面板
            function loadMenu(){
                $("#leftTree").empty();
            	//一次性加载
				$.post("${ctx}/platform/console/getSysRolResTreeData.ht",
					 function(result){
						aryTreeData=result;
						for(var i=0;i<result.length;i++){
            				var node=result[i];
            			}
						//获取根节点，加载顶部按钮菜单。
						var headers=getRootNodes();
						var len=headers.length;
						var menuContainer=$("#menuPanel");
						for(var i=0;i<len;i++){
	            			var head=headers[i];
	            			var menuItemHtml=getMenuItem(head);
	            			menuContainer.append($(menuItemHtml));
	            		}
						initRollButton();
						if(len>0){
							var selectTab=jQuery.getCookie("selectTab");
							var obj= $("#" +selectTab);
							if(selectTab && obj.length>0){
								$("#" +selectTab).addClass("menuItem_hover");
								loadTree(selectTab);
							}
							else{
								var head=headers[0];
								var resId=head.resId;
								$("#" +resId).addClass("menuItem_hover");
								loadTree(resId);
							}
							$(".l-layout-header-inner").text($(".menuItem_hover span").text());
						}	
					});
            }
            
            //加载资源树
            function loadTree(resId){
            	$("#rightTree").detach();
            	var targetRes=$("#"+resId);
            	var defaultUrl = targetRes.attr("url");
           		if(defaultUrl && defaultUrl.length>8){
            		if(!defaultUrl.startWith("http",false)) defaultUrl=ctxPath +defaultUrl; 
            		addToTab(defaultUrl,targetRes.text().trim(),resId,targetRes.attr("icon"));
            	}
            	
            	var nodes=new Array();
    			getChildByParentId(resId,nodes);
    			var zTreeObj =$.fn.zTree.init($("#leftTree"), setting, nodes);
    			// 加载右边栏
    			var zTreeNodes = zTreeObj.getNodes();
    			$(".l-layout-collapse-left").append('<ul id="rightTree" class="menu_cont"></ul>');
    			for(var i=0; i < zTreeNodes.length; i++){
    				$("#rightTree").append("<li id="+zTreeNodes[i].resId+" tId="+zTreeNodes[i].tId+" class='menu_list'><span class='menu_icon' style='background-image:url("+zTreeNodes[i].icon+");' defaultUrl="+zTreeNodes[i].defaultUrl+" resName="+zTreeNodes[i].resName+" icon="+zTreeNodes[i].icon+"></span><div class='menu_abs' id="+zTreeNodes[i].resId+"abs"+"><span class='menu_name' defaultUrl="+zTreeNodes[i].defaultUrl+" resName="+zTreeNodes[i].resName+" icon="+zTreeNodes[i].icon+">"+zTreeNodes[i].resName+"</span></div></li>")
	    			getRightChildByParentId(zTreeNodes[i].resId,1)
    			}
    			//根据配置的是否展开
				if(nodes.length>0){
					mian_layout_.setLeftCollapse(false);
					for(var idx=0;idx<nodes.length;idx++){
						zTreeObj.expandNode(nodes[idx],nodes[idx].isOpen==0?true:false,false);
					}
				}else{
					mian_layout_.setLeftCollapse(true);
				}
            }
           
            
            //加载菜单项
            function getMenuItem(node){
            	var url = node.defaultUrl? node.defaultUrl:"";
            	var str='<a class="menuItem" id="'+node.resId+'" url="'+url+'" icon="'+node.icon+'">';
           		if(node.icon!="null" && node.icon!=""){
           			str+='<img src="'+node.icon+'" />';
           		}
           		str+='<span >'+node.resName+'</span></a>';
           		return str;
			}
            
            function getChildByParentId(parentId,nodes){
            	for(var i=0;i<aryTreeData.length;i++){
            		var node=aryTreeData[i];
            		if(node.parentId==parentId){
            			nodes.push(node);
            			getChildByParentId(node.resId,nodes);
            		}
            	}
            };
            function getRightChildByParentId(parentId,level){
            	var id = parentId;
            	if(level == 1){
            		id += "abs";
            	}
            	var tree = $("#"+id,"#rightTree");
            	var dl =$('<dl></dl>');
            	dl.addClass("menu_abs_next0"+level+"_Site");
            	tree.append(dl);
            	for(var i=0;i<aryTreeData.length;i++){
            		var node=aryTreeData[i];
            		if(node.parentId==parentId){
            			var dd = $("<dd></dd>");
            			dd.addClass("menu_abs_next0"+level);
            			var p = $('<p class="abs_p" id='+node.resId+'  defaultUrl='+node.defaultUrl+' resName='+node.resName+' icon='+node.icon+'>'+node.resName+'</p>');
            			if(node.isFolder == 1){
	            			p.addClass("menu_abs_nextArr");
            			}
            			dd.append(p);
            			dl.append(dd);
            			// todo 这里加入代码，可以实现菜单显示
            			var level2 = level+1
            			if(node.isFolder == 1){
	            			getRightChildByParentId(node.resId,level2);
            			}
            		}
            	}
            };
            
            //处理点击事件
            function zTreeOnClick(event, treeId, treeNode) {
            	// 单击展开
            	var zTree = $.fn.zTree.getZTreeObj("leftTree");  
                zTree.expandNode(treeNode); 	
            	var url= treeNode.defaultUrl;
            	if(url!=null && url!='' && url!='null'){
	            	if(!url.startWith("http",false)) url=ctxPath +url;
	            	if(treeNode.newOpen=="true"){
	            		$.openFullWindow(url);
	            	}
	            	else{
	            		//扩展了tab方法。
		            	addToTab(url,treeNode.resName,treeNode.resId,treeNode.icon);
	            	}
            	}
            	$(".current").removeClass("current");
 
            	while(treeNode){
            		if(treeNode.level == 0 || !treeNode.isParent){
	            		$("#"+treeNode.tId).addClass("current");
	            		if(treeNode.level == 0){
	            			$("#"+treeNode.resId,".l-layout-collapse-left").addClass("current");
	            		}
            		}
            		treeNode = treeNode.getParentNode();
            	}

            };
            
            //添加到tab或者刷新
            function addToTab(url,txt,id,icon){
            	if(tab.isTabItemExist(id)){
            		tab.selectTabItem(id);
            		tab.reload(id);
            	}
            	else{
            		tab.addTabItem({ tabid:id,text:txt,url:url,icon:icon});
            	}
            };

          	//切换系统
          	function saveCurrentSys(systemId){
            	//var systemId=$("#setSubSystem").val();
        		var form=new com.hotent.form.Form();
        		form.creatForm("form", "${ctx}/platform/console/saveCurrSys.ht");
        		form.addFormEl("systemId", systemId);
        		form.submit();
			}
          	function showIconForTree(treeId, treeNode) {
          		return treeNode.level == 0 || !treeNode.isParent;
          	};
          	function zTreeOnNodeCreated(event, treeId, treeNode) {
          		if(treeNode.level != 0 && !treeNode.isParent){
          			$("#"+treeNode.tId+"_ico").removeAttr("style");
	          		$("#"+treeNode.tId+"_ico").addClass("menu_end");
          		}
          		if(treeNode.level == 0){
          			var style = $("#"+treeNode.tId+"_ico").attr("style")+"background-position: left center;width:22px;";
          			$("#"+treeNode.tId+"_ico").attr("style",style);
          			$("#"+treeNode.tId+"_a").attr("style","display:inline-block;padding-left:15px;margin-left:-15px;");
          			$("#"+treeNode.tId+"_switch").attr("style","position:absolute; right:15px; top:0;");
          		}
          		if($("#"+treeNode.tId+"_switch").hasClass("bottom_open")){
          			$("#"+treeNode.tId+"_switch").removeClass("bottom_open");
          			$("#"+treeNode.tId+"_switch").addClass("center_open");
          		}
          		if($("#"+treeNode.tId+"_switch").hasClass("bottom_close")){
          			$("#"+treeNode.tId+"_switch").removeClass("bottom_close");
          			$("#"+treeNode.tId+"_switch").addClass("center_close");
          		}
          		if(treeNode.level == 0 && !treeNode.isParent){
          			$("#"+treeNode.tId+"_switch").remove();
          		}
          		if(treeNode.level != 0 && treeNode.isParent){
          			$("#"+treeNode.tId+"_a").append("<span style='width: 16px; height: 40px; float: left; background-position: left center; background-repeat: no-repeat; position: relative;z-index: 50;'></span>");
          		}
          	};
          	// firefox下切换tab的高度处置
			//展开收起
			function treeExpandAll(type){
				resourcesTree = $.fn.zTree.getZTreeObj("leftTree");
				resourcesTree.expandAll(type);
			};
     </script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    #pageloading{position:absolute; left:0px; top:0px; background:white url('${ctx}/styles/${skinStyle}/images/loading.gif') no-repeat center; width:100%; height:100%; height:700px; z-index:99999;}
    #top{color:White;height: 80px;}
	#top a{color:white;}
	
 </style>
</head>
<body style="padding:0px;">  
	<div id="pageloading"></div>
	<%@include file="main_top.jspf" %>
 	<div id="layoutMain" style="margin:0px 1px 0px 1px;">
      	<div position="left" id="accordion1"   title=" ${currentSystem.sysName }"> 
      		<ul id='leftTree' class='ztree' style="overflow:auto;height: 100%" ></ul>
       	</div>	 
       
       	<div position="center" id="framecenter"> 
       		<div tabid="home" title="首页">
       		<!-- <div tabid="home" title="我的工作台"> -->
        	<c:if test="${not empty currentSystem.homePage }">
           		<c:choose>
           			<c:when test="${currentSystem.isLocal==1}">
           				<iframe frameborder="0" name="home" src="${ctx}${currentSystem.homePage}"></iframe>
           			</c:when>
           			<c:otherwise>
           				<iframe frameborder="0" name="home" src="${currentSystem.defaultUrl}${currentSystem.homePage}"></iframe>
           			</c:otherwise>
           		</c:choose>
           	</c:if>
           	</div>
       	</div>
   	</div>
</body>
</html>
