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
		checkbox:true
	}, 
	
		{
			field : 'name',
			title : '名称',
			align : 'left',
			sortable : true,
			width : 250
		},
		{
			field : 'shortName',
			title : '简写',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=build_unit-sql&s=searchList',
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
			//回调函数 
			getParentWindow().selectBuildunit_callBack(rowData);
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
