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
			title : '项目状态',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'action',
			title : '归档',
			align : 'center',
			width : 150,
			formatter : function(value, row){
				var buttonStr = "--";
				if(row.flowStatus=='1005'){
					buttonStr += createButtonStr('edit',"archive('"+row.pkId+"');","归档");
				}
				return buttonStr;
			}
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=project_archived_sql&s=archivedList',
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

function archive(proId){
	var url = basePath + "/oa/check/archive.c?proId="+proId;
	$.messager.confirm("操作提示", "确定要归档此项目吗？",function(r) {
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

