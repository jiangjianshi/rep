var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 20, 20, 30, 50, 100 ],
	columns : [ [ 
	{
		title : '主键',
		field : 'pkId',
		hidden:true
	}, 
	{
		title : '合同编号',
		field : 'contNo',
		hidden:true
	}, 
	
		{
			field : 'contAmt',
			title : '合同金额',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'price',
			title : '单价',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'contSdate',
			title : '开始日期',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'contEdate',
			title : '结束日期',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'contRealAmt',
			title : '合同实际金额',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=contract_manage_sql&s=searchList',
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
		},
		onDblClickRow: function(rowIndex, rowData){
			//回调函数 
			getParentWindow().selectKcdwunit_callBack(rowData);
			//关闭本弹出窗口
			Dialog.close();
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

//合同管理-编辑-窗口
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
	var url = basePath+"/oa/contract/editContract.c?id="+selectRow.pkId;
	openDialog(1000,1000,"编辑",url);
}