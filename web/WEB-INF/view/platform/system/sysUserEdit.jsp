<%--
	time:2011-11-28 10:17:09
	desc:edit the 用户表
--%>
<%@page language="java" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser,com.hotent.core.api.util.PropertyUtil"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<html>
<head>
	<title>编辑 用户表</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysUser"></script>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js" ></script>
   <script type="text/javascript"  src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
   <script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
   <script type="text/javascript" src="${ctx}/js/handlebars/handlebars.min.js"></script>
   <script type="text/javascript" src="${ctx}/js/hotent/platform/system/HtmlUploadDialog.js"></script>
   
	<script type="text/javascript">
	
	var orgTree;    //组织树
	var posTree;    //岗位树
	var rolTree;    //角色树
	
	var orgPosTree;    //组织岗位树
	
	var height;
	var expandDepth =2; 
     
	var action="${action}";
	
	
	
    $(function ()
    {   
       
    	//右键菜单,暂时去掉右键菜单
    	height=$('body').height();
    	$("#tabMyInfo").ligerTab({ });
    	function showRequest(formData, jqForm, options)	{ 
			return true;
		}
    	function showResponse(responseText, statusText)  {
    		var self=this;
    		var obj=new com.hotent.form.ResultMessage(responseText);
    		if(obj.isSuccess()){//成功
    			$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息",function(rtn){
    				if(rtn){
    					if(self.isReset==1){
    						window.location.reload(true);
    					}
    				}else {
    					window.location.href="${returnUrl}";
    				}
    			});
    	    }else{//失败
    	    	$.ligerDialog.err("提示信息","用户保存失败!",obj.getMessage());
    	    }
    	};
    	
 

    	if(${sysUser.userId==null}){
			valid(showRequest,showResponse,1);
		}else{
			valid(showRequest,showResponse);
		}
		$("a.save").click(function() {
			$('#sysUserForm').submit(); 
		});
		$("#orgPosAdd").click(function(){
			btnAddRow('orgPosTree');
		});
		$("#orgPosDel").click(function(){
			btnDelRow();
		});
		$("#posAdd").click(function(){
			btnAddRow('posTree');
		});
		$("#posDel").click(function(){
			btnDelRow();
		});
		
		$("#rolAdd").click(function(){
			btnAddRow('rolTree');
		});
		$("#rolDel").click(function(){
			btnDelRow();
		});
		$("#demensionId").change(function(){
			loadorgTree();
		});
		//组织刷新按钮
		$("#treeReFresh").click(function() {
			loadorgTree();
		});
         //组织展开按钮
		$("#treeExpand").click(function() {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			var treeNodes = orgTree.transformToArray(orgTree.getNodes());
			for(var i=1;i<treeNodes.length;i++){
				if(treeNodes[i].children){
					orgTree.expandNode(treeNodes[i], true, false, false);
				}
			}
		});
		$("#treeCollapse").click(function() {
			orgTree.expandAll(false);
		});
    	if("grade"==action){
    		loadorgGradeTree();
    	}else{
    		loadorgTree();
    	}
    	loadrolTree();
    	
   	   var orgIds="${orgIds}";
	   if( orgIds == undefined || orgIds == null || orgIds == ""){
	   }else{
		   //编辑页面才调用此方法
		   loadorgPosTree(orgIds);
	   }
    });//function end

	//添加个人照片
	function picCallBack(array){
		if(!array && array!="") return;
		var fileId=array[0].fileId,
			fileName=array[0].fileName;
		
		var path= __ctx + "/platform/system/sysFile/getFileById.ht?fileId=" + fileId;
		if(/[\s\S]+.(png|gif|jpg)/gi.test(fileName)){
			$("#picture").val("/platform/system/sysFile/getFileById.ht?fileId=" + fileId);
			$("#personPic").attr("src",path);
		}
			
		else
			$.ligerDialog.warn("请选择*png,*gif,*jpg类型图片!");
				
	};
	//上传照片
	function addPic(){
			HtmlUploadDialog({max:1,size:10,callback:picCallBack});
	};
	//删除照片
	function delPic(){
		$("#picture").val("");
		$("#personPic").attr("src","${ctx}/commons/image/default_image_male.jpg");
	};
	
	//生成组织树      		
	function loadorgTree(){
		if(action =='grade'){loadorgGradeTree();return;}
		
		var demId=$("#demensionId").val();
		var setting = {
			data: {
				key : {
					
					name: "orgName",
					title: "orgName"
				},
				simpleData: {
					enable: true,
					idKey: "orgId",
					pIdKey: "orgSupId",
					rootPId: -1
				}
			},
			async: {
				enable: true,
				url:__ctx+"/platform/system/sysOrg/getTreeData.ht?type=system&typeVal=all&demId="+demId,
				autoParam:["orgId","orgSupId"]
			},
			view: {
				selectedMulti: true
			},
			onRightClick: false,
			onClick:false,
			check: {
				enable: true,
				chkboxType: { "Y": "", "N": "" }
			},
			callback:{
				onClick: zTreeOnCheck,
				onCheck:orgPostTreeExpand,
				onAsyncSuccess: orgTreeOnAsyncSuccess
			}
		};
		orgTree=$.fn.zTree.init($("#orgTree"), setting);
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
		var target;
		if(treeId=="rolTree"){
			target = rolTree ;
			if(treeNode.isParent){
				var children=treeNode.children;
				if(children){
					for(var i=0;i<children.length;i++){
						target.checkNode(children[i], !treeNode.checked, false, true);
					}
				}
			}
		} else if(treeId=='orgTree'){
			target = orgTree ;
		} else if(treeId=='orgPosTree'){
			target = orgPosTree ;
		}else{
			target = posTree ;
		}
		target.checkNode(treeNode, '', false, true);
	};
	//判断是否为子结点,以改变图标	
	function orgTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		if(treeNode){
	  	 	var children=treeNode.children;
		  	 if(children.length==0){
		  		treeNode.isParent=true;
		  		orgTree = $.fn.zTree.getZTreeObj("orgTree");
		  		orgTree.updateNode(treeNode);
		  	 }
		}
			var treeNodes = orgTree.transformToArray(orgTree.getNodes());
			if(treeNodes.length>0){
				treeNodes[0].nocheck = true;
				orgTree.updateNode(treeNodes[0]);
			}
	};	
	
	function loadorgGradeTree(){
		var setting = {
				data: {
					key : {
						name: "orgName",
						title: "orgName"
					}
				},
				view : {
					selectedMulti : false
				},
				onRightClick: false,
				onClick:false,
				check: {
					enable: true,
					chkboxType: { "Y": "", "N": "" }
				},
				callback:{
					onClick: zTreeOnCheck,
					onCheck:orgPostTreeExpand 
					}
			};
			var orgId = $("#orgAuth").val(); 
			if(!orgId) orgId =0;
			var url=__ctx + "/platform/system/grade/getOrgJsonByAuthOrgId.ht?orgId="+orgId;
		   //一次性加载
		   $.post(url,function(result){
			   var zNodes=eval("(" +result +")");
			   orgTree=$.fn.zTree.init($("#orgTree"), setting,zNodes);
			   if(expandDepth!=0)
				{
					orgTree.expandAll(false);
					var nodes = orgTree.getNodesByFilter(function(node){
						return (node.level < expandDepth);
					});
					if(nodes.length>0){
						//nodes[0].nocheck = true; 
						orgTree.updateNode(nodes[0]);
						for(var i=0;i<nodes.length;i++){
							orgTree.expandNode(nodes[i],true,false);
						}
					}
				}else {
					orgTree.expandAll(true);
					// justifyMargin(10);
				}
		   });		
	};	
    

	
	
	//勾选组织的时候展开组织岗位树
	function orgPostTreeExpand(){
		var setting = {
				data: {
					key : {
						name: "posName",
						title: "posName"
					},
				
					simpleData: {
						enable: true,
						idKey: "posId",
						pIdKey: "orgId",
						rootPId: -1
					}
				},

				view: {
					selectedMulti: true
				},
				onRightClick: false,
				onClick:false,
				check: {
					enable: true,
					chkboxType: { "Y": "", "N": "" }
				},
				callback:{
					onClick: zTreeOnCheck,
					onAsyncSuccess: zTreeOnAsyncSuccess,
					onRightClick: zTreeOnRightClick
				}
		};
		  //获取组织树的勾选节点
		  var treeObj = $.fn.zTree.getZTreeObj('orgTree');
	        var nodes = treeObj.getCheckedNodes(true);
	        var a=[];
	        for ( var key in nodes ){
	        	a.push(nodes[key].orgId);
	        }
	       var orgIds=a.join();
	       if(!orgIds) return;
		  var orgUrl=__ctx + "/platform/system/position/getOrgPosTreeData.ht?orgIds="+orgIds;
		   //一次性加载
		   $.post(orgUrl,function(result){
			   orgPosTree=$.fn.zTree.init($("#orgPosTree"), setting,result);
			   orgPosTree.expandAll(true);
			   //去掉父节点勾选框,???
			   var treeObj = $.fn.zTree.getZTreeObj("orgPosTree");
               var nodes = treeObj.getNodesByParam("orgId", -1, null);//为啥总是为空
			   for(var key in nodes){
			   			nodes[key].nocheck = true;
			   			orgPosTree.updateNode(nodes[key]);
			   }
		   });	
		   orgPostTree=$.fn.zTree.init($("#orgPostTree"), setting);
	};
	
	
	//生成组织岗位树      		
	  function loadorgPosTree(orgIds) {
		  var setting = {
					data: {
						key : {
							name: "posName",
							title: "posName"
						},
					
						simpleData: {
							enable: true,
							idKey: "posId",
							pIdKey: "orgId",
							rootPId: -1
						}
					},

					view: {
						selectedMulti: true
					},
					onRightClick: false,
					onClick:false,
					check: {
						enable: true,
						chkboxType: { "Y": "s", "N": "ps" }
					},
					callback:{
						onClick: zTreeOnCheck,
						onAsyncSuccess: zTreeOnAsyncSuccess,
						onRightClick: zTreeOnRightClick
					}
			};
		   
		    var orgUrl=__ctx + "/platform/system/position/getOrgPosTreeData.ht?orgIds="+orgIds;
			   //一次性加载
			   $.post(orgUrl,function(result){
				   orgPosTree=$.fn.zTree.init($("#orgPosTree"), setting,result);
				   orgPosTree.expandAll(true);
			   });	
			      orgPosTree = $.fn.zTree.init($("#orgPosTree"), setting);
	};
	
	//生成岗位树      		
	  function loadposTree() {
		  var setting = {
					data: {
						key : {
							name: "posName",
							title: "posName"
						},
					
						simpleData: {
							enable: true,
							idKey: "posId",
							pIdKey: "parentId",
							rootPId: -1
						}
					},
					async: {
						enable: true,
						url:__ctx+"/platform/system/position/getChildTreeData.ht",
						autoParam:["posId","parentId"],
						dataFilter: filter
					},
					view: {
						selectedMulti: true
					},
					onRightClick: false,
					onClick:false,
					check: {
						enable: true,
						chkboxType: { "Y": "", "N": "" }
					},
					callback:{
						onClick: zTreeOnCheck,
						onDblClick: posTreeOnDblClick,
						onAsyncSuccess: zTreeOnAsyncSuccess
					}
			};
		    posTree = $.fn.zTree.init($("#posTree"), setting);
	};	
	
	
	
	//判断是否为子结点,以改变图标	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		if(treeNode){
	  	 var children=treeNode.children;
		  	 if(children.length==0){
		  		treeNode.isParent=true;
		  		pos_Tree = $.fn.zTree.getZTreeObj("SEARCH_BY_POS");
		  		pos_Tree.updateNode(treeNode);
		  	 }
		}
		var treeNodes = posTree.transformToArray(posTree.getNodes());
		if(treeNodes.length>0){
			//显示勾选框
			treeNodes[0].nocheck = true;
			posTree.updateNode(treeNodes[0]);
		}
	};
	
	//过滤节点,默认为父节点,以改变图标	
	function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				if(!childNodes[i].isParent){
					alert(childNodes[i].posName);
					childNodes[i].isParent = true;
				}
			}
			return childNodes;
	};
    
	
	 //生成角色树      		
	  function loadrolTree() {
		var setting = {       				    					
			data: {
				key : {
					name: "roleName",
					title: "roleName"
				},
			
				simpleData: {
					enable: true,
					idKey: "roleId",
					pIdKey: "systemId",
					rootPId: null
				}
			},
			view: {
				selectedMulti: true
			},
			onRightClick: false,
			onClick:false,
			check: {
				enable: true,
				chkboxType: { "Y": "p", "N": "s" }
			},
			callback:{
				onClick: zTreeOnCheck,
				onDblClick: rolTreeOnDblClick}
		   };
			if(action == 'grade'){
				var url="${ctx}/platform/system/sysRole/getGradeTreeData.ht";
			}
			else{
				var url="${ctx}/platform/system/sysRole/getTreeData.ht";
			}
				
			$.post(url,function(result){
				rolTree=$.fn.zTree.init($("#rolTree"), setting,result);
				
			});
	};	
	
	
	 function btnDelRow() {
		 var $aryId = $("input[type='checkbox'][class='pk']:checked");
		 var len=$aryId.length;
		 if(len==0){
			 $.ligerDialog.warn("你还没选择任何记录!");
			 return;
		 }
		 else{			
			 $aryId.each(function(i){
					var obj=$(this);
					delrow(obj.val());
			 });
		 }
	 };
	 
	 function delrow(id)//删除行,用于删除暂时选择的行
	 {
		 $("#"+id).remove();
	 };

	
	
	
	//树按添加按钮
	function btnAddRow(treeName) {
		var treeObj = $.fn.zTree.getZTreeObj(treeName);
        var nodes = treeObj.getCheckedNodes(true);
        if(nodes==null||nodes=="")
        {
        	$.ligerDialog.warn("你还没选择任何节点!");
			return;
        }
        if(treeName.indexOf("orgPos")!=-1) {
	        $.each(nodes,function(i,treeNode){	
        		orgPosAddHtml(treeNode);
			});
	    }
	    else if(treeName.indexOf("pos")!=-1){
		     $.each(nodes,function(i,treeNode){
			 	  posAddHtml(treeNode);
		     });
	    }
	    else if(treeName.indexOf("rol")!=-1){
	    	 $.each(nodes,function(i,treeNode){
				  if(treeNode.roleId>0){
					  if (treeNode.subSystem==null || treeNode.subSystem=="")
					  {
						  treeNode.sysName="";
	    		       }
					  else{
						  treeNode.sysName=treeNode.subSystem.sysName;
					  }
					  rolAddHtml(treeNode);
				  }
	    	 });
	    }
    };
	
	
	 function orgPosAddHtml(treeNode){
		
		 if(treeNode.orgName==null) return;//去掉父节点
		 //添加过的不再添加
		 var obj=$("#" +treeNode.posId); 
         if(obj.length>0) return;
		 //公司名称 
		 if(typeof treeNode.companyId =="undefined" || treeNode.companyId==null){
			 treeNode.companyId = 0;
		 } 
		 if(typeof treeNode.company =="undefined" || treeNode.company==null){
			 treeNode.company = '';
		 } 
		    //用jquery获取模板
		    var tpl = $("#orgPosAddHtml-template").html();
		   
		    var content = {treeNode:treeNode};
		    //预编译模板
		    var template = Handlebars.compile(tpl);
		    //匹配json内容
		    var html = template(content);
		    //输入模板
		 $("#orgItem").append(html);
		 
	 };
	 
	//岗位树左键双击
	 function posTreeOnDblClick(event, treeId, treeNode){   
		 posAddHtml(treeNode);
		 
	 };
	 
	 function posAddHtml(treeNode){
		 if(treeNode.parentId==-1) return;
		 var obj=$("#" +treeNode.posId);
		 if(obj.length>0) return;
		 //用jquery获取模板
		 var tpl = $("#posAddHtml-template").html();
		 //json数据
		 var content = {treeNode:treeNode};
		 //预编译模板
		 var template = Handlebars.compile(tpl);
		 //匹配json内容
		 var html = template(content);
		 //输入模板
		 $("#posItem").append(html);
		 
	 };
	//角色树左键双击
	 function rolTreeOnDblClick(event, treeId, treeNode){   
		 if(treeNode.subSystem!=null&&treeNode.subSystem!=""){
			 treeNode.sysName=treeNode.subSystem.sysName;
		 }else{
			 treeNode.sysName=" ";
		 }
		 rolAddHtml(treeNode);
	 };
	 
	 function rolAddHtml(treeNode){
		// if( systemId==0) return;
		 var obj=$("#" +treeNode.roleId);
		 if(obj.length>0) return;
		 //用jquery获取模板
		 var tpl = $("#rolAddHtml-template").html();
		 var content = {treeNode:treeNode};
		 //预编译模板
		 var template = Handlebars.compile(tpl);
		 //匹配json内容
		 var html = template(content);
		 //输入模板
		 $("#rolItem").append(html);
		
	 };	 
	//右键菜单
	 function zTreeOnRightClick(e, treeId, treeNode) {
		// alert(treeNode.orgId);
			if (treeNode.orgId=="-1") {
				orgPostTree.cancelSelectedNode();//取消节点选中状态
				menu_root.show({
					top : e.pageY,
					left : e.pageX
				});
			} else  {
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
			}
		};

	//右键菜单
		function getMenu() {
			menu = $.ligerMenu({
				top : 100,
				left : 100,
				width : 100,
				items:<f:menu>
					[ {
						text : '删除',
						click : 'delNode',
						alias:'delOrg'
					}]
					</f:menu>       
			});

			menu_root = $.ligerMenu({
				top : 100,
				left : 100,
				width : 100,
				items : [ {
					text : '增加',
					click : addNode
				}]
			});
		};
		
		//新增节点
		function addNode() {
			var treeNode=getSelectNode();
			var orgId = treeNode.posId;
			//var demId = treeNode.demId;
			var url = __ctx + "/platform/system/position/edit.ht?orgId="+ orgId;
			var url = "edit.ht?orgId=" + orgId + "&demId=" + demId + "&action=add";
			$("#viewFrame").attr("src", url);
		};
         //删除节点
		function delNode() {
			var treeNode=getSelectNode();
			var callback = function(rtn) {
				if (!rtn) return;
				var params = "orgId=" + treeNode.orgId;
				$.post("orgdel.ht", params, function() {
					orgTree.removeNode(treeNode);
				});
			};
			$.ligerDialog.confirm("确认要删除此组织吗，其下组织也将被删除？", '提示信息', callback);

		};
		
		function showUserDlg(){
			var superior=$("#superior").val();
			var superiorId=$("#superiorId").val();
			var topOrgId= "${param.topOrgId}";
			var scope;
			if(topOrgId!=null && topOrgId!=""){
				var script="return " + topOrgId +";";
				scope="{type:\"script\",value:\""+script+"\"}";
			}
			else{
				scope="{type:\"system\",value:\"all\"}";
			}
			var script="return "+topOrgId; 
			 UserDialog({
 	        	selectUserIds:superiorId,
 	        	selectUserNames:superior,
 	        	scope:scope,
 	        	callback:function(userIds,userNames){
 		        	$('#superior').val(userNames);
 		        	$('#superiorId').val(userIds);
 		        }
 	        });
		};
	</script>
	<script id="orgPosAddHtml-template" type="text/x-handlebars-template" >
    	<tr id="{{treeNode.posId}}" style="cursor: pointer;">
			<td style="text-align: center;">
				{{treeNode.company}}<input type="hidden" name="orgId" value="{{treeNode.companyId}}" />
			</td>
			<td style="text-align: center;">
				{{treeNode.orgName}}<input type="hidden" name="orgId" value="{{treeNode.orgId}}" />
			</td>
			<td style="text-align: center;">
				{{treeNode.posName}}<input type="hidden" name="posId" value="{{treeNode.posId}}" />
			</td>
			<td style="text-align: center;">
				<input type="radio" name="posIdPrimary" value="{{treeNode.posId}}" />
			</td>
			<td style="text-align: center;">
				<input type="checkbox" name="posIdCharge" value="{{treeNode.posId}}" />
			</td>
			<td style="text-align: center;">
				<a href="#" onclick="delrow('{{treeNode.posId}}')" class="link del">移除</a>
			</td>
		</tr>
    </script>
    <script id="rolAddHtml-template"  type="text/x-handlebars-template" >
		<tr id="{{treeNode.roleId}}" style="cursor: pointer;">
			<td style="text-align: center;">
				{{treeNode.roleName}}<input type="hidden"  name="roleId" value="{{treeNode.roleId}}" />
			</td>
			<td style="text-align: center;">
				{{treeNode.sysName}}
			</td>
			<td style="text-align: center;">
				<a href="#" onclick="delrow('{{treeNode.roleId}}')" class="link del">移除</a>
			</td>
		</tr>
    </script>
    <script id="posAddHtml-template" type="text/x-handlebars-template" >
		<tr id="{{treeNode.posId}}" style="cursor: pointer;">
			<td style="text-align: center;">
				{{treeNode.posName}}<input type="hidden"  name="posId" value="{{treeNode.posId}}" />
			</td>
			<td style="text-align: center;">
				<input type='radio' name="posIdPrimary" value="{{treeNode.posId}}" />
			</td>
			<td style="text-align: center;">
				<a href="#" onclick="delrow('{{treeNode.posId}}')" class="link del">移除</a>
			</td>
		</tr>
	</script>
	<style type="text/css">
		html{height:100%}
		body {overflow:auto;}
		div.panel-body {margin: 0px;}
		.table-detail th {height:40px;}
		div#rMenu {
			background: none repeat scroll 0 0 #F5F5F5;
			border: 1px solid #979797;
			overflow: hidden;
			padding-bottom: 2px;
			position: absolute;
			z-index:9999;
		}
		div#rMenu ul li{
			cursor: pointer;
			height: 23px;
			line-height: 23px;
			position: relative;
			width: 100%;
		}
	</style>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:if test="${sysUser.userId==null }">添加用户信息</c:if>
				<c:if test="${sysUser.userId!=null }">编辑【${sysUser.fullname}】用户信息</c:if>  
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${returnUrl}"><span></span>返回</a></div>
				</div>
			</div>
		</div>
	   <form id="sysUserForm" method="post" action="save.ht?selectedOrgId=${param.selectedOrgId}">
		  		
            <div  id="tabMyInfo" class="panel-nav" style="overflow:hidden; position:relative;">  
	           <div title="基本信息" tabid="userdetail" icon="${ctx}/styles/default/images/resicon/user.gif">
			         
			           		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<th>头像:</th>
									<td>
										<div class="person_pic_div">
											<p><img id="personPic" src="${ctx}/${pictureLoad}" alt="相片" /></p>
										</div>
										<div class="personPic_btn" style="background: none; height: 24px; display:inline-block;">
											<a class="link uploadPhoto" href="javascript:;" onclick="addPic();">上传照片</a>
											<a class="link del" href="javascript:;" onclick="delPic();">删除照片</a>
										</div>
									</td>
								</tr>
								<tr>
									<th width="18%">帐   号: <span class="required red">*</span></th>
									<td ><c:if test="${bySelf==1}"><input type="hidden" name="bySelf" value="1"></c:if><input type="text" <c:if test="${bySelf==1}">disabled="disabled"</c:if> id="account" name="account" value="${sysUser.account}" style="width:240px !important" class="inputText"/></td>
								</tr>						
																				
								<tr style="<c:if test="${not empty sysUser.userId}">display:none</c:if>">
									<th>密   码: <span class="required red">*</span></th>
									<td><input type="password" name="password" autocomplete="new-password" value="${sysUser.password}" style="width:240px !important" class="inputText"/></td>
								</tr>
								
								<tr>
								    <th>用户姓名: <span class="required red">*</span></th>
									<td ><input type="text" id="fullname" name="fullname" value="${sysUser.fullname}" style="width:240px !important" class="inputText" /></td>
								</tr>
								<tr>
									<th>用户性别: </th>
									<td>
									<select name="sex" class="select" style="width:245px !important">
										<option value="1" <c:if test="${sysUser.sex==1}">selected</c:if> >男</option>
										<option value="0" <c:if test="${sysUser.sex==0}">selected</c:if> >女</option>
									</select>						
									</td>
								</tr>						
								<tr>
									<th>入职时间: </th>
									<td>
										<input type="text" id="entryDate" name="entryDate" value="<fmt:formatDate value='${sysUser.entryDate}' pattern='yyyy-MM-dd'/>" class="inputText date" validate="{date:true}" />
									</td>
								</tr>						
								<tr>
									<th>员工状态: </th>
									<td>
										<select name="userStatus" class="select" style="width:245px !important" >
											<option value="正式员工" <c:if test="${sysUser.userStatus=='正式员工'}">selected</c:if> >正式员工</option>
											<option value="试用员工" <c:if test="${sysUser.userStatus=='试用员工'}">selected</c:if> >试用员工</option>
											<option value="停薪留职" <c:if test="${sysUser.userStatus=='停薪留职'}">selected</c:if> >停薪留职</option>
											<option value="返聘" <c:if test="${sysUser.userStatus=='返聘'}">selected</c:if> >返聘</option>
											<option value="中转" <c:if test="${sysUser.userStatus=='中转'}">selected</c:if> >中转</option>
										</select>	
									</td>
								</tr>						
								<tr>
									<th>是否锁定: </th>
									<td >								
										<select name="isLock" class="select" style="width:245px !important" <c:if test="${bySelf==1}">disabled="disabled"</c:if>>
											<option value="<%=SysUser.UN_LOCKED %>" <c:if test="${sysUser.isLock==0}">selected</c:if> >未锁定</option>
											<option value="<%=SysUser.LOCKED %>" <c:if test="${sysUser.isLock==1}">selected</c:if> >已锁定</option>
										</select>	
									</td>				  
								</tr>
								
								<tr>
								    <th>是否过期: </th>
									<td >
										<select name="isExpired" class="select" style="width:245px !important" <c:if test="${bySelf==1}">disabled="disabled"</c:if>>
											<option value="<%=SysUser.UN_EXPIRED %>" <c:if test="${sysUser.isExpired==0}">selected</c:if> >未过期</option>
											<option value="<%=SysUser.EXPIRED %>" <c:if test="${sysUser.isExpired==1}">selected</c:if> >已过期</option>
										</select>
									</td>
								</tr>
								
								<tr>
								   <th>当前状态: </th>
									<td>
										<select name="status"  class="select" style="width:245px !important" <c:if test="${bySelf==1}">disabled="disabled"</c:if>>
											<option value="<%=SysUser.STATUS_OK %>" <c:if test="${sysUser.status==1}">selected</c:if> >激活</option>
											<option value="<%=SysUser.STATUS_NO %>" <c:if test="${sysUser.status==0}">selected</c:if> >禁用</option>
											<option value="<%=SysUser.STATUS_Del %>" <c:if test="${sysUser.status==-1}">selected</c:if>>删除</option>
										</select>
									</td>								
								</tr>						
								<tr>
								   <th>邮箱地址: </th>
								   <td ><input type="text" id="email" name="email" value="${sysUser.email}" style="width:240px !important" class="inputText"/></td>
								</tr>
								<tr>
							    	<th>微   信: </th>
									<td ><input type="text" id="weixinid" name="weixinid" value="${sysUser.weixinid}"  style="width:240px !important" class="inputText"/></td>
								</tr>
								<tr>
									<th>手   机: </th>
									<td ><input type="text" id="mobile" name="mobile" value="${sysUser.mobile}" style="width:240px !important" class="inputText"/></td>						   
								</tr>
								
								<tr>
								    <th>电   话: </th>
									<td ><input type="text" id="phone" name="phone" value="${sysUser.phone}"  style="width:240px !important" class="inputText"/></td>
								</tr>
														
							</table>
							<input type="hidden" name="userId" value="${sysUser.userId}" />
							<input type="hidden" id="picture" name="picture" value="${sysUser.picture}" />
					
	           </div>
	           <%--不是修改本人信息则--%>
	           <c:if test="${bySelf!=1}">
	           <div title="组织岗位选择" tabid="orgdetail" icon="${ctx}/styles/default/images/icon/home.png" >		         	         
			           
			          <table  style="margin-top:-5px;border-top: 6px solid #A0BDBB;" align="center"  cellpadding="0" cellspacing="0" class="table-grid table-list">
					    <tr>
				        <td width="28%" valign="top"  style="padding-left:2px !important;">
							<div class="tbar-title">
									<span class="tbar-label">所有组织</span> 
					        </div>
						  <div class="panel-body" style="height:520px;overflow-y:auto;">	 
								<div style="width: 100%;">
								<c:choose>
								 	<c:when test="${action=='global' }">
										<select id="demensionId" style="width: 99.8% !important;">
											<c:forEach var="dem" items="${demensionList}">
												<option value="${dem.demId}" <c:if test="${dem.demId==1}">selected="selected"</c:if> >${dem.demName}</option>
											</c:forEach>
										</select>
								 	</c:when>
								 	<c:otherwise>
								 	<select id="orgAuth" style="width:99.8% !important;" onchange="javascript:loadorgGradeTree();">  
							              <c:forEach var="orgAuth" items="${orgAuthList}">  
							         			<option value="${orgAuth.orgId}" dimId="${orgAuth.dimId}" orgPerms="${orgAuth.orgPerms}" <c:if test="${orgAuth.dimId==1}">selected="selected"</c:if>>${orgAuth.orgName}—[${orgAuth.dimName}]</option>  
							        	  </c:forEach> 
			       					 </select>
								 	</c:otherwise>
								 </c:choose>
								</div>
								<div class="tree-toolbar" id="pToolbar">
									<div class="toolBar"
										style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
										<div class="group">
											<a class="link reload" id="treeReFresh">刷新</a>
										</div>
										<div class="l-bar-separator"></div>
										<div class="group">
											<a class="link expand" id="treeExpand">展开</a>
										</div>
										<div class="l-bar-separator"></div>
										<div class="group">
											<a class="link collapse" id="treeCollapse">收起</a>
										</div>
									</div>
			                   </div> 		  
				            <ul id="orgTree" class="ztree" style="min-width:200px;margin:-2; padding:-2;" >         
				            </ul>    
						</div>
						</td>
						<%--组织下的岗位列表 --%>
						<td width="28%" valign="top" style="padding-left:2px !important;">
				        <div class="tbar-title">
								<span class="tbar-label">所有岗位</span>
						</div>				         
						<div class="panel-body" style="height:520px;overflow-y:auto;">	 
							<div id="orgPosTree" class="ztree" style="min-width:200px;margin:-2; padding:-2;" >         
				            </div>
						</div>
						</td>
						<%--组织下的岗位列表 end--%>
						<td width="3%" valign="middle"  style="padding-left:2px !important;">
						<input type="button" id="orgPosAdd" value="添加>>" />
						<br/>
						<br/>
						<br/>
						</td>
					    <td valign="top" style="padding-left:2px !important;">
			            <div class="tbar-title">
								<span class="tbar-label">已选组织</span>
				         </div>
						<div style="overflow-y:auto;">
						      <table id="orgItem" class="table-grid table-list"  cellpadding="1" cellspacing="1">
						   		<thead>
						   			
						   			<th style="text-align:center !important;">公司名称</th>
						   			<th style="text-align:center !important;">组织名称</th>
						   			<th style="text-align:center !important;">岗位名称</th>
						    		<th style="text-align:center !important;">是否主岗位</th>
						    		<th style="text-align:center !important;">主要负责人</th>
						    		
						    		<th style="text-align:center !important;">操作</th>
						    	</thead>
						    	<c:forEach items="${userPosList}" var="orgItem">
						    		<tr trName="${orgItem.orgName}"  id="${orgItem.posId}" style='cursor:pointer'>
							    		
							    		<td style="text-align: center;">
						    				${orgItem.company}<input type="hidden" name="companyId" value="${orgItem.companyId}">					    				
						    			</td>
						    			
							    		<td style="text-align: center;">
						    				${orgItem.orgName}<input type="hidden" name="orgId" value="${orgItem.orgId}">					    				
						    			
						    			<td style="text-align: center;">
						    				${orgItem.posName}<input type="hidden" name="posId" value="${orgItem.posId}">					    				
						    			</td>
						    			
						    			<td style="text-align: center;">					    			
						    			 <input type="radio" name="posIdPrimary" value="${orgItem.posId}" <c:if test='${orgItem.isPrimary==1}'>checked</c:if> />
						    			</td>
						    			
						    			<td style="text-align: center;">					    			
						    			 	<input type="checkbox" name="posIdCharge" value="${orgItem.posId}"  <c:if test='${orgItem.isCharge==1}'>checked</c:if>> 
						    			</td>
						    			
						    			<td style="text-align: center;">
						    			 <a href="javascript:;" onclick="delrow('${orgItem.posId}')" class="link del">移除</a>
						    			</td>
						    		</tr>
						    	</c:forEach>
						   	 </table>
						</div>
			            </td>
			            </tr>
					 </table>
					
	        </div>
	      
	         <div  title="角色选择" tabid="roldetail" icon="${ctx}/styles/default/images/resicon/customer.png"  >
		       
			        <table   style="margin-top:-5px;border-top: 6px solid #A0BDBB;" align="center"  cellpadding="0" cellspacing="0" class="table-grid">
					   <tr>
				       <td width="28%" valign="top" style="padding-left:2px !important;">
				        <div class="tbar-title">
							 <span class="tbar-label">所有角色</span>
						</div>	
						<div class="panel-body" style="height:520px;overflow-y:auto;">	
				    	    <div id="rolTree" class="ztree" style="min-width:200px;margin:-2; padding:-2;" >         
				            </div>
				        </div>
						</td>
						<td width="3%" valign="middle"  style="padding-left:2px !important;">
						<input type="button" id="rolAdd" value="添加>>" />
						<br/>
						<br/>
						<br/>
						</td>
					    <td valign="top" style="padding-left:2px !important;">
					   <div class="tbar-title">
							 <span class="tbar-label">已选角色</span>
						</div>	
						<div style="overflow-y:auto;">
						  <table id="rolItem" class="table-grid"  cellpadding="1" cellspacing="1">
						   		<thead>					   			
						   			<th style="text-align:center !important;">角色名称</th>
						    		<th style="text-align:center !important;">子系统名称</th>
						    		<th style="text-align:center !important;">操作</th>
						    	</thead>
						    	<c:forEach items="${roleList}" var="rolItem">
						    		<tr trName="${rolItem.roleName}" id="${rolItem.roleId}" style="cursor:pointer">
							    		<td style="text-align: center;">
						    				${rolItem.roleName}<input type="hidden" name="roleId" value="${rolItem.roleId}">
						    			</td>
						    			<td style="text-align: center;">
						    			    ${rolItem.systemName}
						    			</td>
						    			<td style="text-align: center;">
						    			 <a href="javascript:;" onclick="delrow('${rolItem.roleId}')" class="link del">移除</a>
						    			</td>
						    		</tr>
						    	</c:forEach>
						   	 </table>
						</div>
			            </td>
			            </tr>
					 </table>
			</div>	
			
			<c:if test="${userId != 0}">
			  <div title="所属组织角色">
			    <table   style="margin-top:-5px;border-top: 6px solid #A0BDBB;" align="center"  cellpadding="0" cellspacing="0" class="table-grid">
			    <thead>					   			
					<th style="text-align:center !important;">组织</th>
					<th style="text-align:center !important;">角色</th>
				</thead>
				<c:forEach items="${sysOrgRoles}" var="sysOrgRole">
				<tr>
					<td style="text-align: center;">
					${sysOrgRole.key.orgName}
					</td>
					<td style="text-align: center;">
					<c:forEach items="${sysOrgRole.value}" var="sysRole">
						${sysRole.roleName} 
					</c:forEach>
					</td>
				</tr>
				</c:forEach>
			  	</table>
			  </div>	
		  	</c:if>
			  	
			 <div  title="上级配置" tabid="superior">
			 	<c:set var="ids" value=""></c:set>
			 	<c:set var="names" value=""></c:set>
			 	<c:if test="${!empty userUnders }">
			 		<c:forEach items="${userUnders}" var="user" varStatus="status">
			 			<c:choose>
			 				<c:when test="${!status.last}">
			 					<c:set var="ids" value='${ids }${user.userId  },'></c:set>
			 					<c:set var="names" value='${names}${user.fullname },'></c:set>
			 				</c:when>
			 				<c:otherwise>
			 					<c:set var="ids" value='${ids }${user.userId  }'></c:set>
			 					<c:set var="names" value='${names}${user.fullname }'></c:set>
			 				</c:otherwise>
			 			</c:choose>
			 		</c:forEach>
			 	</c:if>
			 	 <table id="superItem" class="table-grid table-list"  cellpadding="1" cellspacing="1">
			   		<tr>
			   			<th style="text-align:center !important;width:200px">上级</th>
			   			<td >
			   				<input type="text" name="superior" size="80" id="superior" value="${names}">
			   				<input type="hidden" name="superiorId" id="superiorId" value="${ids}">
			   				<a href='#' class='button' onclick="showUserDlg()"><span>...</span></a>
			   			</td>
			   		</tr>
			   	</table>
			 </div>				
	      	</c:if>
	      </div>      
	  </form>
</div>
</body>
</html>
