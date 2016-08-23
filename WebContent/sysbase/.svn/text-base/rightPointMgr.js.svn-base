var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ 
	{
		field : '主键',
		title : 'rightId',
		hidden:true
	}, 
	
		{
			field : 'rightName',
			title : '权限名称',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'rightCode',
			title : '权限编码',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=right-sql&s=rightPointList&pCode='+pCode,
		queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后',
		pagination : true,
		border : true,
		singleSelect : true,
		checkOnSelect : true,
		toolbar : '#toolbar',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		}
	});
}); // $(document).ready--end

//信息查询
function search() {
	grid.datagrid('reload', serializeObject($('#searchForm')));
}

//添加-窗口
function add() {
	var url = basePath+"/sysbase/rightPointAddEdit.c?pCode="+pCode;
	openDialog(300,300,"添加",url);
}

//编辑-窗口
function edit() {
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
	var url = basePath+"/sysbase/rightPointAddEdit.c?rightId="+selectRow.rightId;
	openDialog(300,300,"编辑",url);
}
//删除
function del() {
	alert('未提供删除功能');
	return;
	var selectRows = grid.datagrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert('请择你要删除的记录');
		return;
	}
	var selectRows_arr = [];
	$(selectRows).each(function(i, row) {
		selectRows_arr.push(row.userId);
	});
	var url = basePath + "/sysbase/delUsers.c?delIds="+selectRows_arr;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除",function(r) {
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
	});
}
//设置角色
function setRole(userId){
	var url = basePath+"/sysbase/setUserRole.c?userId="+userId;
	openDialog(300,600,"设置角色",url);
}