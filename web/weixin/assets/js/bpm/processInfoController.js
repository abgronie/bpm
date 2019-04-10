var  app = angular.module("app", ['flowServiceModule','formDirective']);
app.controller('ctrl', ['$scope','flowService',function($scope,flowService){
	
	
	var runId=HtUtil.getParameter("runId");
	var defId=HtUtil.getParameter("defId");
	/**
	 * 用于查看抄送转发消息时，更新是否已读
	 */
	var isReaded = HtUtil.getParameter("isReaded");
	/**
	 * 抄送转发时
	 */
	var copyId = HtUtil.getParameter("copyId");
	/**
	 * 显示审批历史。
	 */
	$scope.showOpinion=function(){
		flowService.showOpinion(runId);
	};
	
	/**
	 * 手机流程图
	 */
	$scope.showFlowPic=function(){
		flowService.showFlowPic(runId);
	}
	
	/**
	 * 初始化表单。
	 */
	$scope.initForm=function(){
		var json=HtUtil.getJSON("form_" +defId);
		var defer=null;
		if(json==null){
			defer=flowService.getInstForm(runId);
		}
		else{
			defer=flowService.getInstForm(runId,json.formKey,json.version);
		}
		/**
		 * 判断是否为抄送转发实例，如果是更新消息是否已读状态
		 */
		if(isReaded!=null && isReaded==0){
			flowService.updReaded(copyId);
		}
		defer.then(function(data){
			flowService.handForm($scope,data,defId);
		},
		function(status){
			console.info(status);
		});
	};
	//初始化表单。
	$scope.initForm();
}]);