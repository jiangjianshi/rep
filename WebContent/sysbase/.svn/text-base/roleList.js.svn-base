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
			title : 'pkId',
			hidden:true
		},{
			field : 'roleCode',
			title : '编码',
			hidden : true
		}, 
		{
			field : 'roleName',
			title : '角色名称',
			align : 'left',
			sortable : true
		}, 
		{
			title : '操作',
			field : 'action',
			width : '200',
			formatter : function (value, row){
				var buttonStr = "";
				buttonStr += createButtonStr('edit',"edit('"+row.pkId+"');","编辑");
				buttonStr += createButtonStr('edit',"del('"+row.roleCode+"');","删除");
				return buttonStr;
			}
		} 
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=sys_role-sql&s=searchList',
		queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : true,
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
			var url = basePath+"/sysbase/editRole.c?id="+rowData.pkId;
			openDialog(1000,1000,"编辑",url);
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
	var url = basePath+"/sysbase/addRole.c";
	openDialog(500,500,"添加",url);
}

//编辑-窗口
function edit(roleId) {
	var url = basePath+"/sysbase/editRole.c?id="+roleId;
	openDialog(500,500,"编辑",url);
}
//删除
function del(roleCode) {
	var url = basePath + "/sysbase/delRole.c?roleCode="+roleCode;
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