var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 20, 20, 30, 50, 100 ],
	columns : [ [ 
		{
			field : 'opin_id',
			title : '主键',
			hidden:true
		}, 
		{
			field : 'proId',
			title : 'proId',
			hidden:true
		}, 
		{
			field : 'proName',
			title : '工程名称',
			align : 'left',
			sortable : true
		},
		{
			field : 'opin_file_name',
			title : '政府审批意见-附件',
			align : 'left',
			sortable : true
		},
		{
			field : 'update_user',
			title : '操作人',
			align : 'center',
			sortable : true,
			width : 150,
			formatter : function(value, row){
				if(row.opin_id){
					return value;
				}else{
					return "-";
				}
			}
		},
		{
			field : 'update_time',
			title : '最近更新时间',
			align : 'center',
			sortable : true,
			width : 150,
			formatter : function(value, row){
				if(row.opin_id){
					return value;
				}else{
					return "-";
				}
			}
		},
		{
			field : 'flowStatus',
			title : '审查进度',
			align : 'center',
			sortable : true,
			width : 150
		},
		{
			field : 'action',
			title : '操作',
			align : 'center',
			sortable : true,
			width : 150,
			formatter : function(value, row){
				var buttonStr = "";
				buttonStr += createButtonStr('edit',"setGovOpinion('"+row.proId+"');","设置");
				buttonStr += createButtonStr('edit',"downloadOpinion('"+row.proId+"');","下载");
				return buttonStr;
			}
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=fn_govopinion_sql&s=govopinionList',
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
	$("#isHaveOpinion").val('all'); 
	grid.datagrid('reload', dataGridParams.queryParams);
}
//下载 政府审批意见
function downloadOpinion(proId){
	var downloadUrl = basePath+"/oa/govopinion/download.c?proId="+proId;
	window.location.href=downloadUrl;
}

//政府审批意见设置-窗口
function setGovOpinion(proId) {
	var url = basePath+"/oa/govopinion/setGovOpinion.c?proId="+proId;
	openDialog(500,300,"政府审批意见设置",url,function(){
		search();
	});
}
