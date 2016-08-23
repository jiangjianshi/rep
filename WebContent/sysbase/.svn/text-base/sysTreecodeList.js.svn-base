var treeGrid;
// 数据加载参数
var treeGridParams = {
	columns : [ [
			{
				title : 'cId',
				field : 'cId',hidden:true
			},
			{
				width : '200',
				title : '名称',
				field : 'cName'
			},
			{
				width : '100',
				title : '排序',
				field : 'cOrder'
			}, {
				title : '操作',
				field : 'action',
				width : '100'
			} ] ]
};


var grid;
//数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 20, 20, 30, 50, 100 ],
	columns : [ [ 
		{
			field : 'tId',
			title : '主键',
			hidden:true
		}, 
		{
			field : 'tName',
			title : '名称',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'tOrder',
			title : '排序',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'tCode',
			title : '编码',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};

var curTId;

$(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=sys_tree_type-sql&s=searchList',
		queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : true,
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#type_toolbar',
		onClickRow: function(rowIndex, rowData){
			$("#tId").val(rowData.tId);
			search();
			$('#addCodeBtn').linkbutton('enable');
			$('#addSubBtn').linkbutton('enable');
			$('#editCodeBtn').linkbutton('enable');
			$('#delCodeBtn').linkbutton('enable');
		}
	});
	
	treeGrid = $('#treeGrid').treegrid({
		title : '',
		url :basePath + '/sysbase/treecodeList_datagird.c',
		idField : 'cId',
		queryParams : {},
		treeField : 'cName',
		parentField : 'pId',
		animate:true,
		rownumbers : true,
		pagination : false,
		sortName : 'depOrder',
		sortOrder : 'asc',
		columns :treeGridParams.columns,
		toolbar : '#toolbar'
	});
});// $(document).ready--end

//信息查询
function search() {
	treeGrid.treegrid('reload', serializeObject($('#searchForm')));
}

//添加-窗口
function add() {
	var selectTypeRow = grid.datagrid("getSelected");
	if(selectTypeRow){
		var url = basePath+"/sysbase/addSysTreecode.c?tId="+$("#tId").val();
		openDialog(400,180,"添加",url);
	}else{
		alert("请选择一条字典类型记录");
	}
}
//添加-窗口
function addSub() {
	var selectRows = treeGrid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条父级记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行操作！");
		return;
	}
	var selectRow = selectRows[0];
	var url = basePath+"/sysbase/addSysTreecode.c?parentId="+selectRow.cId+"&tId="+$("#tId").val();
	openDialog(400,180,"添加",url);
}

//编辑-窗口
function edit() {
	var selectRows = treeGrid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行编辑！");
		return;
	}
	var selectRow = selectRows[0];
	var url = basePath+"/sysbase/editSysTreecode.c?id="+selectRow.cId;
	openDialog(400,180,"编辑",url);
}
//删除
function del() {
	var selectRows = treeGrid.treegrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert("请选择你要删除的记录！");
		return;
	}
	var selectRow = selectRows[0];
	var children = treeGrid.treegrid("getChildren",selectRow.cId);
	if(children.length>0){
		alert("请先删除子项！");
		return;
	}
	var url = basePath + "/sysbase/delSysTreecodes.c?delIds="+selectRow.cId;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除？",function(r) {
		if (r) {
			$.get(url,function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					alert(result.msg);
					search();
				} else {
					alert(result.msg);
				}
			});
		}
	})
}


//信息查询
function type_search() {
	grid.datagrid('reload', serializeObject($('#type_searchForm')));
}
//重置查询条件
function type_resetSearch() {
	$('#type_searchForm input').val('');
	grid.datagrid('reload', treeGridParams.queryParams);
}
//添加-窗口
function type_add() {
	var url = basePath+"/sysbase/addSysTreeType.c";
	openDialog(1000,1000,"添加",url);
}

//编辑-窗口
function type_edit() {
	var selectRows = grid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行编辑！");
		return;
	}
	var selectRow = selectRows[0];
	var url = basePath+"/sysbase/editSysTreeType.c?id="+selectRow.tId;
	openDialog(1000,1000,"编辑",url);
}
//删除
function type_del() {
	alert("删除功能关闭！");
	return;
	var selectRows = grid.datagrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert("请选择你要删除的记录！");
		return;
	}
	var selectRows_arr = [];
	$(selectRows).each(function(i, row) {
		selectRows_arr.push(row.tId);
	});
	var url = basePath + "/sysbase/delSysTreeTypes.c?delIds="+selectRows_arr;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除？",function(r) {
		if (r) {
			$.get(url,function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					alert(result.msg);
					type_search();
				} else {
					alert(result.msg);
				}
			});
		}
	})
}