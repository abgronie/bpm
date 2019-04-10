var  app = angular.module("approveFlow", ['flowServiceModule','formDirective']);
app.controller('ctrl', ['$scope', '$window','flowService', function($scope, $window,flowService){
	
	//获取流程定义ID
	var taskId=HtUtil.getParameter("taskId");
	var defId=HtUtil.getParameter("defId");
	var runId=HtUtil.getParameter("runId");
	
	
	
	
	/**
	 * 添加子表行
	 */
	$scope.addRow=function(tableName){
		flowService.addRow($scope,tableName);
		return false;
	};
	/**弹出框编辑子表行*/
	$scope.editRow=function(tableName,index){
		flowService.editRow($scope,tableName,index);
		return false;
	};
	/**将弹出框子表清空**/
	$scope.cleansubTempData=function(tableName){
		$scope.subTempData[tableName] ={};
	}
	/**将弹出框子表数据填充至子表**/ 
	$scope.pushTempDataToForm=function(tableName){
		if($(".ng-invalid","#"+tableName+"_editDialog").length>0){
			Alert("提示信息","当前子表表单未校验通过！");
			return;
		} 
		
		var tempData =$.extend({},$scope.subTempData[tableName]);
		var index = tempData.$index;
		if(!index)index=0;
		$scope.data.sub[tableName].rows[index]=tempData;
		$('#'+tableName+'_editDialog').modal('close');
	}
	
	/**
	 * 删除行
	 */
	$scope.removeRow=function(tableName,idx){
		flowService.removeRow($scope,tableName,idx);
	};
	
	/**
	 * 初始化任务表单。
	 */
	$scope.initTaskForm=function(){
		var json=HtUtil.getJSON("form_" +defId);
		var defer=null;
		if(json==null){
			defer=flowService.getTaskForm(taskId);
		}
		else{
			defer=flowService.getTaskForm(taskId,json.formKey,json.version);
		}
		defer.then(function(data){
			flowService.handForm($scope,data,defId);
		},
		function(status){
			console.info(status);
		});
	};
	
	
	//添加规则。
	flowService.addRule();
	//初始化表单。
	$scope.initTaskForm();
	/**
	 * 返回结果。
	 */
	var confirmFn=function(e){
		var approveAction = $scope.approveAction;
		var def=flowService.approveFlow(taskId,approveAction.voteAgree,e.data,approveAction.back,$scope.data);
		def.then(function(data){
			if(data.result==1){
				Alert("提示信息",approveAction.voteMsg+"操作成功!",function(){
					location.href=__ctx+"/weixin/index.html";
				});
			}
			else{
				Alert("提示信息",approveAction.voteMsg+"操作失败:"+ data.message);
			}
		},function(status){
			
		});
	}
	
	
	
	$scope.approveFlow=function(action){
		
		if($scope.myform.$invalid){ 
			Alert("提示信息","表单未校验通过！");
			return;
		}
		
		if(!action)action ='agree';
		 var approveAction={};
		 approveAction.back=0;
		switch (action) {
			case 'agree':
				approveAction.voteMsg ="同意"
				approveAction.voteAgree = 1;
				break;
			case 'notAgree':
				approveAction.voteAgree = 2;
				approveAction.voteMsg ="反对"
				break;
			case 'abandon':
				approveAction.voteAgree = 0;
				approveAction.voteMsg ="弃权"
				break;
			case 'reject':
				approveAction.voteAgree = 3;
				approveAction.back=1;
				approveAction.voteMsg ="驳回"
				break;
			case 'rejectToStart':
				approveAction.voteAgree = 3;
				approveAction.back=2;
				approveAction.voteMsg ="驳回到发起人"
			break;
			case 'agree_sign':
				approveAction.voteMsg ="同意";
				approveAction.voteAgree = 1;
				break;
			case 'notAgree_sign':
				approveAction.voteAgree = 2;
				approveAction.voteMsg ="反对"
				break;
		}
		$scope.approveAction = approveAction;
		//是否意见框显示填写意见
		var isHideOption = $scope.flowParam.isHideOption;
		Prompt("确认"+approveAction.voteMsg+"吗?","请输入审批意见:",confirmFn,"",isHideOption);
	}
	
	
	/**
	 * 显示审批历史。
	 */
	$scope.showOpinion=function(runId){
		var url=__ctx +"/weixin/bpm/opinionsHtml.html?taskId="+taskId;
		//var url="/opinions.html?runId="+runId;
		//debugger;
		$window.location.href = url;
		//var conf={};
		//conf.title="审批历史" ;
		//conf.url=url;
		//createPopupDialog(conf,'flowStartDialog');
	}
	
	/**
	 * 手机流程图
	 */
	$scope.showFlowPic=function(runId){
		var url=__ctx +"/weixin/bpm/bpmImage.html?taskId="+taskId;
		//var conf={};
		//conf.title="流程图"
		//conf.url=url;
		//createPopupDialog(conf,'flowApproveDialog');
		
		
		$window.location.href = url;
	}
	
	/**
	 * 显示审批历史。
	 */
//	$scope.showOpinion=function(){
//		var url=__ctx +"/weixin/bpm/opinions.html?taskId="+taskId;
//		var conf={};
//		conf.title="审批历史" ;
//		conf.url=url;
//		createPopupDialog(conf,'flowStartDialog');
//	};
	
	/**
	 * 手机流程图
	 */
//	$scope.showFlowPic=function(){
//		var url=__ctx +"/bpmImage?taskId="+taskId;
//		var conf={};
//		conf.title="流程图"
//		conf.url=url;
//		createPopupDialog(conf,'flowApproveDialog');
//	}
	
}]);