var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ 
	{
		title : '主键',
		field: 'pkId',
		checkbox:true
	}, 
	
		{
			field : 'name',
			title : '名称',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'shortName',
			title : '简写',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'qualifyGrade',
			title : '资质等级',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'certNo',
			title : '证书编号',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'phone',
			title : '电话',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'fax',
			title : '传真',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'remark',
			title : '备注',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=scdw_unit-sql&s=searchList',
		queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : false,
		checkOnSelect : true,
		idField : 'pkId',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		},
		onDblClickRow: function(rowIndex, rowData){
			var url = basePath+"/oa/baseinfor/editScdwUnit.c?id="+rowData.pkId;
			openDialog(500,500,"编辑",url);
		}
	});
}); // $(document).ready--end

//信息查询
function search() {
	grid.datagrid('reload', serializeObject($('#searchForm')));
}
//重置查询条件
function resetSearch() {
	$('#searchForm input').val('');
	grid.datagrid('reload', dataGridParams.queryParams);
}
//添加-窗口
function add() {
	var url = basePath+"/oa/baseinfor/addScdwUnit.c";
	openDialog(500,500,"添加",url);
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
	var url = basePath+"/oa/baseinfor/editScdwUnit.c?id="+selectRow.pkId;
	openDialog(500,500,"编辑",url);
}
//删除
function del() {
	var selectRows = grid.datagrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert("请选择你要删除的记录！");
		return;
	}
	var selectRows_arr = [];
	$(selectRows).each(function(i, row) {
		selectRows_arr.push(row.pkId);
	});
	var url = basePath + "/oa/baseinfor/delScdwUnits.c?delIds="+selectRows_arr;
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