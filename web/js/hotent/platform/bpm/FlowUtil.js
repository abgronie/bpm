if (typeof FlowUtil == 'undefined') {
	FlowUtil = {};
}

/**
 * 启动流程。
 * @param defId 流程定义ID。
 */
FlowUtil.startFlow=function(defId,actDefId){
	var url= __ctx +"/platform/bpm/bpmDefinition/getCanDirectStart.ht";
	var params={defId:defId};
	$.post(url,params,function(data){
		if(data){
			var callBack=function(rtn){
				if(!rtn) return;
				var flowUrl= __ctx +"/platform/bpm/task/startFlow.ht";
				var parameters={actDefId:actDefId};
				$.post(flowUrl,parameters,function(responseText){
					var obj=new com.hotent.form.ResultMessage(responseText);
					if(obj.isSuccess()){//成功
						$.ligerDialog.success("启动流程成功!",'提示信息');
					}
					else{
						$.ligerDialog.error("启动流程失败!",'出错了');
					}
				});
			};
			$.ligerDialog.confirm("需要启动流程吗?",'提示信息',callBack);
		}else{
			var url=__ctx +"/platform/bpm/task/startFlowForm.ht?defId="+defId;
			 var text = "启动流程";
			 var tab = top.$("#framecenter").ligerGetTabManager();
			 tab.addTabItem({text:text,url:url});
			/*jQuery.openFullWindow(url);*/
		}
	});
	return false;
};



/**
 * 流程追回。
 * @param runId
 * @param memo
 */
FlowUtil.recover=function(conf){
	if(!conf) conf={backToStart: 0};	
	var url= __ctx +"/platform/bpm/processRun/checkRecover.ht";
	if(conf.backToStart==1){
		url= __ctx +"/platform/bpm/processRun/checkRedo.ht";
	}	
	var params={runId:conf.runId };
	$.post(url,params,function(data){
		 var obj=new com.hotent.form.ResultMessage(data);
		 if(obj.isSuccess()){
				var url=__ctx + '/platform/bpm/processRun/redoDialog.ht?runId=' + conf.runId +'&backToStart=' + conf.backToStart;
				var dialogWidth=500;
				var dialogHeight=400;
				conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1,reload:true},conf);
				url=url.getNewUrl();
				
				/*KILLDIALOG*/
				DialogUtil.open({
					height:conf.dialogHeight,
					width: conf.dialogWidth,
					/*title : '流程追回',*/
					title : '流程撤回',
					url: url, 
					isResize: true,
					//自定义参数
					sucCall:function(rtn){
						if(rtn && conf.callback){
							conf.callback(this);
						}
					}
				});
		 }
		 else{
			 //$.ligerDialog.err("提示","撤销失败!",obj.getMessage());
			 $.ligerDialog.alert(obj.getMessage());
		 }
	});
};

