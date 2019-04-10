if (typeof Export == 'undefined') {
	Export = {};
}

/**
 * 初始化导出
 */
Export.initExportMenu = function (){
    var menu1 = { width: 150, 
    	items:[{ text: '导出全部数据',click:function(){
    				Export.exportExcel(this,0);
    			}},
        		{ text: '导出选中数据',click:function(){
    				Export.exportExcel(this,1);
    			}},
        		{ text: '导出当前页数据',click:function(){
    				Export.exportExcel(this,2);
    			}}
        	]
    };
	
    if($(".pk").length<=0){
    	menu1.items.splice(1,1);
    }
    
    $("div.exportMenu").ligerMenuBar({
			items: [
     			  { text: '<a href="javascript:;" class="link export"><span></span>导出&nbsp;&nbsp;</a>', menu: menu1 }
                  ]});
  $("div.exportMenu").removeClass('l-menubar');
  $("div.l-menubar-item-down").css("right","6px");
  $("div.l-menubar-item").css("padding-right","0");
};

/**
 * 初始化导出2
 * 为了青岛科技学生社保登记流程导出照片
 */
Export.initExportMenu2 = function (){
    var menu1 = { width: 150, 
    	items:[{ text: '导出全部数据',click:function(){
    				Export.exportExcel(this,0);
    			}},
    			{ text: '导出全部数据的照片',click:function(){
    				Export.exportExcel(this,0,2);
    			}},
        		{ text: '导出选中数据',click:function(){
    				Export.exportExcel(this,1);
    			}},
    			{ text: '导出选中数据的照片',click:function(){
    				Export.exportExcel(this,1,2);
    			}},
        		{ text: '导出当前页数据',click:function(){
    				Export.exportExcel(this,2);
    			}},
    			{ text: '导出当前页数据的照片',click:function(){
    				Export.exportExcel(this,2,2);
    			}}
        	]
    };
	
    if($(".pk").length<=0){
    	menu1.items.splice(1,1);
    }
    
    $("div.exportMenu").ligerMenuBar({
			items: [
     			  { text: '<a href="javascript:;" class="link export"><span></span>导出&nbsp;&nbsp;</a>', menu: menu1 }
                  ]});
  $("div.exportMenu").removeClass('l-menubar');
  $("div.l-menubar-item-down").css("right","6px");
  $("div.l-menubar-item").css("padding-right","0");
};


var exportDialog=null;

/**
 * 设置导出字段
 */
Export.setExportField = function (obj,exportType){
	var exportIds = "";
	
	$('input[type="checkbox"][class="pk"]:checked').each(function() {
			exportIds += $(this).val() + ",";
	});
	if (exportIds != "")
		exportIds = exportIds.substring(0, exportIds.length - 1);
	if(exportType == 1 && exportIds==""){
		alert("请选择导出的！");
		return;
	}
		
	if(exportDialog==null){
		Export.initExportDialog();
		exportDialog=$.ligerDialog.open(
			{title:'导出字段',
			target:$("#exportField"),
			width:400,height:250,
			buttons:
				[ {text :'导出',onclick: function(){
					Export.exportExcel(obj,exportType,exportIds);
				}},
				{text :'取消',onclick: function (item, dialog) {
						dialog.hide();
					}
				}]});	
	}
	exportDialog.show();
}

/**
 * 导出初始化
 */
Export.initExportDialog =function (){
	Export.handlerCheckAll();
}

Export.handlerCheckAll =function(){
	$("#checkFieldAll").click(function(){
		var state=$(this).attr("checked");
		if(state==undefined)
			Export.checkFieldAll (false);
		else
			Export.checkFieldAll (true);
	});
}

Export.checkFieldAll =function (checked){
	$("input[type='checkbox'][class='field']").each(function(){
		$(this).attr("checked", checked);
	});
}

/**
 * 导出Excel
 * @param {} obj
 * @param {} exportType
 * @param {} exportIds
 */
/*Export.exportExcel =function (obj,exportType){
	var me = $(obj), params = {};
	var exportIds = "";
	
	$('input[type="checkbox"][class="pk"]:checked').each(function() {
			exportIds += $(this).val() + ",";
	});
	if (exportIds != "")
		exportIds = exportIds.substring(0, exportIds.length - 1);
	if(exportType == 1 && exportIds==""){
		alert("请选择导出的！");
		return;
	}
		//导出的分页
	var page = $('.panel-page');
	var tableIdCode =page.find("#tableIdCode").val(),
		p = page.find("#currentPage"+tableIdCode),
		z = page.find("#oldPageSize"+tableIdCode),
		totalPage = page.find("#totalPage"+tableIdCode);
	if(exportType == 2 && totalPage.val()<=0){
		alert("当前页没有记录！");
		return;
	}
	
	//查询的条件
	var searchForm = $("div.panel-top").find("form[name='searchForm']");
	if(searchForm.length==0)
		searchForm = $("div.panel-top").find("form[id='searchForm']")
	params = serializeObject(searchForm);
	//自定义显示的最外层Div
	var container = $("div[ajax='ajax']");
	//自定义显示组件的ID
	var displayId=container.attr("displayId");
	var filterKey=container.attr("filterKey");
	
	params.__displayId__=displayId;
	params.__filterKey__ = filterKey;


	params.p= p.val();
	params.z= z.val();
	params.oz= z.val();
	//导出的选项
	params.__exportIds__= exportIds;
	params.__exportType__= exportType;

	var form = $('#exportForm');
	form.empty();
	for(var key in params){
		var input = $("<input type='hidden' name='"+key+"' value='"+params[key]+"'/>");
		form.append(input);
    }
	form.attr("action","exportData_"+displayId+".ht");
	$.ligerDialog.waitting("导出数据中");
	$(document).on("submit", "#exportForm", function (e) {
		setTimeout(Export.checkFileDownloadComplete, 500);
    });
	form.submit();
	
},*/

/**
 * 导出Excel2
 * 为了青岛科技学生社保登记流程导出照片
 * @param {} obj
 * @param {} exportType
 * @param {} exportIds
 */
Export.exportExcel =function (obj,exportType,num){
	var me = $(obj), params = {};
	var exportIds = "";
	
	$('input[type="checkbox"][class="pk"]:checked').each(function() {
			exportIds += $(this).val() + ",";
	});
	if (exportIds != "")
		exportIds = exportIds.substring(0, exportIds.length - 1);
	if(exportType == 1 && exportIds==""){
		alert("请选择导出的！");
		return;
	}
		//导出的分页
	var page = $('.panel-page');
	var tableIdCode =page.find("#tableIdCode").val(),
		p = page.find("#currentPage"+tableIdCode),
		z = page.find("#oldPageSize"+tableIdCode),
		totalPage = page.find("#totalPage"+tableIdCode);
	if(exportType == 2 && totalPage.val()<=0){
		alert("当前页没有记录！");
		return;
	}
	
	//查询的条件
	var searchForm = $("div.panel-top").find("form[name='searchForm']");
	if(searchForm.length==0)
		searchForm = $("div.panel-top").find("form[id='searchForm']")
	params = serializeObject(searchForm);
	//自定义显示的最外层Div
	var container = $("div[ajax='ajax']");
	//自定义显示组件的ID
	var displayId=container.attr("displayId");
	var filterKey=container.attr("filterKey");
	
	params.__displayId__=displayId;
	params.__filterKey__ = filterKey;


	params.p= p.val();
	params.z= z.val();
	params.oz= z.val();
	//导出的选项
	params.__exportIds__= exportIds;
	params.__exportType__= exportType;

	var form = $('#exportForm');
	form.empty();
	for(var key in params){
		var input = $("<input type='hidden' name='"+key+"' value='"+params[key]+"'/>");
		form.append(input);
    }
	if(num != null && num != ""){
		form.attr("action","exportPic_"+displayId+".ht");
		//$.ligerDialog.waitting("导出数据中");
	}else{
		form.attr("action","exportData_"+displayId+".ht");
		$.ligerDialog.waitting("导出数据中");
	}
	$(document).on("submit", "#exportForm", function (e) {
		setTimeout(Export.checkFileDownloadComplete, 500);
    });
	form.submit();
	
},

Export.checkFileDownloadComplete = function(){
	var exportData = document.cookie.indexOf("downloadExcel");
	console.log(document.cookie)
	if(exportData != -1){
		$.ligerDialog.closeWaitting();
		return;
	};
	setTimeout(Export.checkFileDownloadComplete, 500);
}