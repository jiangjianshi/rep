var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 30,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [30, 50, 100 ],
	columns : [ [ 
		{
			title : '主键',
			field : 'proFileId',
			checkbox:true
		}, 
		{
			field : 'fileName',
			title : '资料名称',
			align : 'left',
			sortable : true,
			width : 700
		}
] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=project_file-sql&s=searchList&proType='+proType,
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
		idField : 'proFileId',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		},
		onDblClickRow: function(rowIndex, rowData){
			var url = basePath + "/commonCtrl/loadSingleData.c?t=project_file-sql&s=getObjInfor&id="+rowData.proFileId;
			$.get(url,function(data) {
				var singleData = eval('(' + data + ')');
				$('#submitForm').form('load',singleData[0]);
			});
		}
	});
}); // $(document).ready--end

//信息查询
function search() {
	grid.datagrid('reload');
}
function save() {
	if ($('#submitForm').form('validate')) {
		if($("#proFileId").val()==''){
			var url = basePath + '/oa/baseinfor/saveProjectFile.c';
		}else{
			var url = basePath + '/oa/baseinfor/editProjectFileSave.c';
		}
		$('#submitForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					$('#submitForm input[name!="proType"]').val('');
					//刷新datagrid
					search();
				} else {
					alert(result.msg);
				}
			}
		});
	}
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
		selectRows_arr.push(row.proFileId);
	});
	var url = basePath + "/oa/baseinfor/delProjectFiles.c?delIds="+selectRows_arr;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除？",function(r) {
		if (r) {
			$.get(url,function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					alert(result.msg);
					search();
					$('#submitForm input[name!="proType"]').val('');
				} else {
					alert(result.msg);
				}
			});
		}
	})
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
	var url = basePath + "/commonCtrl/loadSingleData.c?t=project_file-sql&s=getObjInfor&id="+selectRow.proFileId;
	$.get(url,function(data) {
		var singleData = eval('(' + data + ')');
		$('#submitForm').form('load',singleData[0]);
	});
}