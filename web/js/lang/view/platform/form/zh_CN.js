var $lang_form={
	bpmFormTemplate	:{
		notSameName:'模板名不能同名!',
		initConfirm:'初始化表单模板将会导致自定义模板信息丢失，确定初始化吗？'
	},
	//自定义查询
	bpmFormQuery:{
		selectTableView:'请先选择数据库表或视图',
		selectTreeField:{
			selectRightInput:'请选择右边的输入框!',
			selectLeftField:'请选择左边的字段!',
			selectLeftOneField:'只能选择一个左边的字段!'
		},
		displayField:'请填写映射树的字段',
		conditionVal:'请填写条件字段的值',
		rtnField:'请选择返回字段',
		get:{
			postParam:'POST参数',
			callMethod:'调用的方法',
			callMethodTip:'该方法定义在{0}中',
			callbackMethod:'回调方法',
			callbackMethodTip:'查询的返回值{0}是{1}类的一个实例',
			otherNotes:'其他说明',
			otherNotes1:'POST的参数中{0}是页码{1}是每页条数，分页查询时需要传递这2个参数，不传表示不进行分页查询；',
			otherNotes2:'{0}是通用表单查询别名，{1}是用作查询的字段名、字段值；',
			otherNotes3:'返回值data中，{0}存放错误信息，没有出错则为空白，{1}存放了查询的结果，{2}表示是否分页(0:不分页,1:分页),{3}和{4}分别记录了查询结果的总数和分页的总页数。'
		}
	},
	bpmFormDialog:{
		selectTableView:'请先选择数据库表或视图',
		notView:'没有找到视图!',
		notSetColumn:'当前选择的表(视图)未设置显示的列!',
		selectTreeField:{
			selectRightInput:'请选择右边的输入框!',
			selectLeftField:'请选择左边的字段!',
			selectLeftOneField:'只能选择一个左边的字段!'
		},
		displayField:'请填写映射树的字段',
		conditionVal:'请填写条件字段的值',
		rtnField:'请选择返回字段'
	},
	bpmFormTable:{
		resetTip:'重置操作将删除物理表，并重置表的状态为未生成。是否重置该表?',
		del:{
			mainTip:'是否删除该表吗?<br/>删除将连同子表一起删除?',
			subTip:'是否删除该表吗?'
		},
		gen:{
			mainTip:'将连同子表一起生成,是否继续?',
			subTip:'是否生成该主表?',
			success:'生成成功',
			failure:'生成失败'
		},
		exportWarn:'请选择主表进行导出!',
		edit:{
			backTip:'自定义表已修改，是否进行保存?',
			generatorSubToMain:'确定要生成子表吗?',
			selectMainTable:'请选择所属主表!',
			notFields:'没有添加字段信息',
			saveFailure:'添加自定义表失败'
		}
	},
	//editTable.js页面
	editTable:{
		control:{
			text:'单行文本框',
			textarea:'多行文本框',
			ckeditor:'富文本框',
			dictionary:'数据字典',
			userSingle:'人员选择器(单选)',
			userMulti:'人员选择器(多选)',
			roleSingle:'角色选择器(单选)',
			roleMulti:'角色选择器(多选)',
			orgSingle:'组织选择器(单选)',
			orgMulti:'组织选择器(多选)',
			posSingle:'岗位选择器(单选)',
			posMulti:'岗位选择器(多选)',
			hidedomaid:'隐藏域',
			attachement:'文件上传',
			decimalToPercent:'小数转百分比',
			select:'下拉选项',
			checkbox:'复选框',
			radio:'单选按钮',
			officeControl:'office控件',
			datepicker:'日期控件',
			processInstance:'流程引用',
			webSignControl:'WebSign控件',
			pictureShowControl:'图片展示控件'
		},
		varFrom:{
			formInput:'表单输入',
			scriptDisplay:'脚本运算(显示)',
			scriptHide:'脚本运算(不显示)',
			serialNumber:'流水号'
		},
		uniqueName:'字段已存在',
		word:'只能为字母开头,允许字母、数字和下划线',
		quotation:'不能有引号',
		tableNameRequired:'表名必填',
		tableNameMaxlength:'表名最多 20 个字符.',
		tableDescMaxlength:'注释 最多200字符.',
		fieldNameRequired:'字段名称必填',
		digits:'填写数字',
		charLenRequired:'文字长度必填',
		intLenRequired:'整数长度必填',
		decimalLenRequired:'小数长度必填'
	},
	//fieldManage.js页面
	fieldManage:{
		notCheckedRow:'还没有选中列!',
		isRequired:'该列为必填列,不能删除!'
	},
	//BpmFormTableTeam.js页面
	bpmFormTableTeam:{
		delTeam:'删除当前分组设置?',
		moveAdd: '该字段已经添加分组,请先移除再添加!',
		selectAddTeam:'请选择要添加的分组！',
		selectTeamField:'请选择分组的字段！'
	},
	index:{
		checkedField:'请选择字段!',
		idxName:'索引名称首字符为字母,最大长度18,只能字母数字或下划线!'
	},
	bpmFormDef:{
		list:{
			newFormDef:'新建表单',
			publish:'确认发布吗？',
			delFormDef:'确定删除该自定义表单吗？',
			newVersion:'确认新建版本吗？',
			setCategory:'设置分类',
			selectCategory:'请选择分类',
			setVersionNo:'设置版本号',
			inputVersion:'请输入版本信息!' 
		},
		newFormDef:{
			select:'请您选择要生成的表'
		},
		edit:{
			sourceModeSave:'不能在源代码模式下保存表单',
			sourceModeActive:'不能在源代码模式下切换页面',
			sourceModeDel:'不能在源代码模式下删除页面',
			newPage:'新页面',
			page1:'页面1'
		}
	},
	//pageTab.js
	pageTab:{
		insertPage:'在当前页后插入',
		delCurPage:'删除当前页',
		newPage:'新页面',
		page:'页'
	},
	//Permission.js
	permission:{
		user:'用户',
		role:'角色',
		org:'组织',
		orgMgr:'组织负责人',
		pos:'岗位',
		everyone:'所有人',
		none:'无'
	},
	rights:{
		initRights:'这个操作删除此表单所有相关的权限,是否确定重置权限设置?'
	},
	imp:{
		selectXml:'请选择 *.xml文件进行导入!',
		importing:'正在导入中,请稍候...',
		result:'导入结果如下:',
		failure:'导入失败!'
	}
};