/**
 * 组织选择框
 * data:[要回显的数据]
 * conf:{data,single:false,call:function,initData:{}/[]}
 * */
var OrgDialog = function(conf){
	conf.title="组织选择";
	conf.url = __ctx+'/weixin/assets/js/dialog/orgDialog.html';
	createPopupDialog(conf,'orgDialog');
}

/**
 * data:[要回显的数据]
 * conf:{data,single:false,call:function,initData:{}/[]}
 * */
var UserDialog = function(conf){
	conf.title="人员选择";
	conf.url =__ctx+'/weixin/assets/js/dialog/userDialog.html';
	createPopupDialog(conf,'userDialog');
}

/**
 * 岗位选择框
 * data:[要回显的数据]
 * conf:{data,single:false,call:function,initData:{}/[]}
 * */
var PositionDialog = function(conf){
	conf.title="岗位选择";
	conf.url = __ctx+'/weixin/assets/js/dialog/positionDialog.html';
	createPopupDialog(conf,'positionDialog');
}

/***
 * 上传弹出框
 * conf{type:"",size:"",max"",call"function"}
 */
var UploadDialog = function(conf){
	var type = conf.type||"",
	max = conf.max||100,
	size = conf.size||4000;
	conf.title="文件上传";
	conf.url=__ctx + "/weixin/assets/js/dialog/UploadDialog.html";
	createPopupDialog(conf,'uploadDialog');
}
/**
 * 自定义对话框
 * 	isCombine:true 是否组合对话框 非必须
 *  conf = {dialog_alias_:'org',isCombine:true,callBack:function(data){alert(data)}}
 *  conf.param{参数}
 * 	var aa = CustomerDialog(conf);
 */
var CustomerDialog = function(conf){
	if(!conf.title)conf.title="自定义对话框";
	conf.url = __ctx+'/platform/form/bpmFormDialog/mobileCustom.ht?dialog_alias_='+conf.dialog_alias_+"&isCombine="+conf.isCombine;
	createPopupDialog(conf,'customerDialog');
}
/**
 * 启动流程对话框。
 */
var FlowStartDialog=function(actDefId){
	conf.title="流程选择";
	var url=__ctx +"/bpmImage?definitionId="+actDefId;
	conf.url=url;
	createPopupDialog(conf,'flowStartDialog');
}




