var roleCode = $.q_parms.roleCode;
var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ {
		field : '主键',
		title : 'pk_id',
		hidden : true
	}, {
		field : 'username',
		title : '登录账号',
		sortable : true,
		width : 100
	}, {
		field : 'realname',
		title : '真实姓名',
		align : 'center',
		width : 100
	}, {
		field : 'user_ct',
		title : '证件类型',
		align : 'center',
		width : 100
	}, {
		field : 'user_cc',
		title : '证件号码',
		align : 'center',
		width : 100
	} ] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=user-sql&s=selUserList&roleCode='+roleCode,
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
		fitColumns : true,
		idField : 'user_id',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		}
	});

}); // $(document).ready--end
// 用户信息查询
function search() {
	grid.datagrid('reload', serializeObject($('#searchForm')));
}
// 重置查询条件
function resetSearch() {
	$('#searchForm input').val('');
	grid.datagrid('reload', dataGridParams.queryParams);
}

// 用户编辑-窗口
function sel() {
	var selectRows = grid.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行编辑！");
		return;
	}
	var row = selectRows[0];
	window.parent.callBack(row.realname,row.user_id);
}