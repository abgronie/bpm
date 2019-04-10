/**
 * 日期计算
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="datecalculate"
 */
var datecalculate ={
	execCommand : function(cmdName) {
		
	},
	queryCommandState : function() {
		var node = this.selection.getStart();
		if(editor.isMoble_){
			if(node.hasAttribute("ht-date")) return -1
		}
		
		if(node && "INPUT"==node.nodeName.toUpperCase()){
		   var flag	 = getRealObj(node);
			if(node)
				return 0;
			else
				return -1;
		}
		else
			return -1;
	}
}
UE.commands['datecalculate'] = datecalculate;
UE.commands['datecalculate_mb'] = datecalculate;
/**
 * 查找实际存放 自定义查询 数据的标签元素
 * @param obj
 * @returns
 */
function getRealObj(obj){
	var fieldName=obj.getAttribute("name");
	var parent=obj.parentNode;
	if(fieldName){
		 if(obj && "WDATE"==obj.className.toUpperCase()){
			 return true ;
		 }else{
			 return false ;
		 }
	}
	if(parent){
		 if(parent && "DATEPICKER"==parent.className.toUpperCase()){
			 return true ;
		 }else{
			 return false ;
		 }
	}
	return false;
};