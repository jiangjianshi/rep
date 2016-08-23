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
		title : 'qualifyGradeCode',
		field : 'qualifyGradeCode',
		hidden:true
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
		url : basePath + '/commonCtrl/loadByPage.c?t=kcdw_unit-sql&s=searchList',
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
