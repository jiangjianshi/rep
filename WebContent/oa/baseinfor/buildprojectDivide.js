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
			field : 'sjdw',
			title : '设计单位',
			align : 'left',
			sortable : true
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
		url : basePath + '/commonCtrl/loadByPage.c?t=build_pro-sql&s=searchList&flowStatus=1003',
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
		onClickRow: function(rowIndex, rowData){
			$('#proShowName').html(rowData.proName);
			initDivideGridByProId(rowData.pkId);
		}
	});
	
	divide_grid = $("#divide_dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=major_devide-sql&s=divide_dataGrid',
		queryParams : divide_dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : true,
		toolbar : '#divide_toolbar',
		pageSize : divide_dataGridParams.pageSize,
		pageList : divide_dataGridParams.pageList,
		columns : divide_dataGridParams.columns
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
//分工
function divide() {
	var selectRows = grid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一个要分工的项目记录！");
		return;
	}
	var selectRow = selectRows[0];
	if(selectRow.flowStatus=='分工中'){
		var url = basePath+"/oa/baseinfor/bpDivide.c?proId="+selectRow.pkId;
		openDialog(800,800,"分工",url);
	}else{
		alert("该项目状态为“"+selectRow.flowStatus+"”，不能进行该操作！");
	}
}
function submit(){
	var selectRows = grid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行操作！");
		return;
	}
	var selectRow = selectRows[0];
	var url = basePath + "/oa/check/updateProFlowStatus.c?proId="+selectRow.pkId+"&flowStatus=1004";//更新为“项目一审”
	
	if(selectRow.flowStatus=='分工中'){
		$.messager.confirm("操作提示", "提交后该项目将进入一审阶段，是否确认提交？",function(r) {
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


var divide_grid;
//数据加载参数
var divide_dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为20
	},
	pageList : [ 20,30, 50, 100 ],
	columns : [ [ 
		{
			field : 'majorName',
			title : '专业名称',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'checkUserName',
			title : '审查人',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'proofreadUserName',
			title : '校审人',
			align : 'left',
			sortable : true,
			width : 150
		}
] ]
};

function initDivideGridByProId(proId){
	divide_grid.datagrid('reload',{'proId':proId});
}
