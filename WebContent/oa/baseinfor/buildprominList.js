var buildProMin_grid;
// 数据加载参数
var buildProMin_dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ 
	{
		title : '主键',
		field : 'pkId',
		checkbox:true
	}, 
	
		{
			field : 'repCode',
			title : '项目编号',
			hidden : true
		},
		{
			field : 'minoName',
			title : '单体名称',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'minoUpar',
			title : '地上面积',
			align : 'left',
			sortable : true,
			width : 80
		},
		{
			field : 'minoDoar',
			title : '地下面积',
			align : 'left',
			sortable : true,
			width : 80
		},
		{
			field : 'minoUpti',
			title : '地上层数',
			align : 'left',
			sortable : true,
			width : 80
		},
		{
			field : 'minoDoti',
			title : '地下层数',
			align : 'left',
			sortable : true,
			width : 80
		},
		{
			field : 'minoMemo',
			title : '备注',
			align : 'left',
			sortable : true,
			width : 150
		},
	{
		field : '',
		title : '',
		align : 'left',
		width : 1
	}
] ]
};
$(document).ready(function() {
	buildProMin_grid = $("#buildProMin_dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=build_pro_min-sql&s=searchList&proId='+proId,
		queryParams : buildProMin_dataGridParams.queryParams,
		rownumbers : true,
		height : '367',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : false,
		checkOnSelect : true,
		idField : 'pkId',
		pageSize : buildProMin_dataGridParams.pageSize,
		pageList : buildProMin_dataGridParams.pageList,
		columns : buildProMin_dataGridParams.columns,
		toolbar : '#buildProMin_toolbar',
		onLoadSuccess : function(data) {
			buildProMin_grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		},
		onDblClickRow: function(rowIndex, rowData){
			var url = basePath + "/commonCtrl/loadSingleData.c?t=build_pro_min-sql&s=getObjInfor&id="+rowData.pkId;
			$.get(url,function(data) {
				var singleData = eval('(' + data + ')');
				$('#buildProMin_submitForm').form('load',singleData[0]);
			});
		}
	});
}); // $(document).ready--end

//信息查询
function buildProMin_search() {
	buildProMin_grid.datagrid('reload', serializeObject($('#searchForm')));
}
//重置查询条件
function buildProMin_resetSearch() {
	$('#searchForm input').val('');
	buildProMin_grid.datagrid('reload', buildProMin_dataGridParams.queryParams);
}

//编辑-窗口
function edit() {
	var selectRows = buildProMin_grid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行编辑！");
		return;
	}
	var selectRow = selectRows[0];
	var url = basePath+"/oa/baseinfor/editBuildProMin.c?id="+selectRow.pkId;
	openDialog(1000,1000,"编辑",url);
}
//删除
function del() {
	var selectRows = buildProMin_grid.datagrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert("请选择你要删除的记录！");
		return;
	}
	var selectRows_arr = [];
	$(selectRows).each(function(i, row) {
		selectRows_arr.push(row.pkId);
	});
	var url = basePath + "/oa/baseinfor/delBuildProMins.c?delIds="+selectRows_arr;
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