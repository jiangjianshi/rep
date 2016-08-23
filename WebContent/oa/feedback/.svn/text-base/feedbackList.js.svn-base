var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 20, 20, 30, 50, 100 ],
	columns : [ [ 
		{
			field : 'pkId',
			title : '主键',
			hidden:true
		}, 
		{
			field : 'proName',
			title : '工程名称',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'buildUnit',
			title : '建设单位',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'kcdw',
			title : '勘察单位',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'sjdw',
			title : '设计单位',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'totalArea',
			title : '合计面积',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'totalAmount',
			title : '总投资额',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'flowStatus',
			title : '审查进度',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/oa/feedback/feedbackListGridData.c',
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
		toolbar : '#toolbar',
		onDblClickRow: function(rowIndex, rowData){
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

//意见回复详细 -窗口
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
	var url = basePath+"/oa/feedback/feedBackDeatil.c?proId="+selectRow.pkId;
	openDialog(1000,1000,"审查意见回复",url,function(){
		search();
	});
}
