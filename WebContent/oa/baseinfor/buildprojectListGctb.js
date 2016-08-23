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
			sortable : true
		},
		{
			field : 'buildUnit',
			title : '建设单位',
			align : 'left',
			sortable : true
		},
		{
			field : 'kcdw',
			title : '勘察单位',
			align : 'left',
			sortable : true
		},
		{
			field : 'sjdw',
			title : '设计单位',
			align : 'left',
			sortable : true
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
function loadType(){  
//	//审查状态
//	comboboxEx({
//		inputId:'#flowStatus',
//		ctype_code:'project_flow_status',
//		panelHeight:'auto',
//		editable:false,
//		onChange:function(newValue,oldValue){
//			search();
//		}
//	});
}
$(document).ready(function() {
	loadType();
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=build_pro-sql&s=searchList&flowStatus=1001',
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

//工程填报 -窗口
function add() {
	var url = basePath+"/oa/baseinfor/addBuildProject.c";
	openDialog(1000,1000,"添加",url);
}

//工程填报-编辑-窗口
function updateBuildProject() {
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
	var url = basePath+"/oa/baseinfor/updateBuildProject.c?id="+selectRow.pkId;
	openDialog(1000,1000,"编辑",url);
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
	var url = basePath + "/oa/baseinfor/delBuildProjects.c?delIds="+selectRows_arr;
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
function submit(){
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
	var url = basePath + "/oa/check/updateProFlowStatus.c?proId="+selectRow.pkId+"&flowStatus=1002";//更新为“受理中”
	if(selectRow.flowStatus=='填报中'){
		$.messager.confirm("操作提示", "提交后项目资料将变为不可更改，确认资料已填写完整？",function(r) {
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
	}else{
		alert("该项目状态为“"+selectRow.flowStatus+"”，不能进行该操作！");
	}
	
}