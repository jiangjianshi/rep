var ctypeCode = "";
var ctypeName = "";

var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ {
		field : '主键',
		title : 'code_id',
		hidden : true
	},{
		field : 'code_name',
		title : '参数名称',
		width : 120
	}, {
		field : 'code_value',
		title : '参数编码值',
		sortable : true
	}] ]
};


var gridCtype;
// 数据加载参数
var dataCtypeGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ {
		field : '主键',
		title : 'ctype_id',
		hidden : true
	},{
		field : 'ctype_name',
		title : '参数类型名称',
		width : 100
	}, {
		field : 'ctype_code',
		title : '参数类型编码',
		width : 100,
		sortable : true
	}] ]
};

$(document).ready(function() {
	gridCtype = $("#dataCtypeGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=ctype-sql&s=ctypeList',
		queryParams : dataCtypeGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : true,
		checkOnSelect : true,
		idField : 'ctype_id',
		pageSize : dataCtypeGridParams.pageSize,
		pageList : dataCtypeGridParams.pageList,
		columns : dataCtypeGridParams.columns,
		toolbar : '#toolbarCtype',
		onLoadSuccess : function(data) {
			gridCtype.datagrid('clearChecked');// 避免easyUI多行删除bug
		},
		onClickRow : function(rowIndex, rowData) {
			ctypeName = rowData['ctype_name'];
			ctypeCode = rowData['ctype_code'];
			changeCode(ctypeCode,ctypeName);
		}
	});

}); // $(document).ready--end

function changeCode(ctypeCode,ctypeName){
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=code-sql&s=codeList&ctype_code='+ctypeCode,
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
		idField : 'code_id',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		}
	});
}

//信息查询
function searchCode() {
	grid.datagrid('reload', {ctype_code : ctypeCode});
}

// 信息查询
function search() {
	gridCtype.datagrid('reload', serializeObject($('#searchForm')));
	changeCode("");
	ctypeCode="";
	ctypeName="";
}
// 重置查询条件
function resetSearch() {
	$('#searchForm input').val('');
	gridCtype.datagrid('reload', dataCtypeGridParams.queryParams);
}

//参数编码【-------------------------------------】
//添加-窗口
function add() {
	if(ctypeCode==""){
		alert("请先选择参数类型！");
		return;
	}
	$('#addDiv').dialog('open').dialog('setTitle', '[新增]');
	$('#addForm').form('clear');
	document.getElementById('addForm').reset();
}
// 添加-保存
function save() {
	var url = basePath+"/commonCtrl/saveOrUpate.c";
	var t, s;
	if ($('#addForm input[name="code_id"]').val() != '') {
		t = 'code-sql';
		s = 'updateCode';
	} else {
		t = 'code-sql';
		s = 'addCode';
	}

	$('#addForm input[name="ctype_code"]').val(ctypeCode);
	$('#addForm input[name="ctype_name"]').val(ctypeName);
	$('#addForm').form('submit', {
		url : url,
		onSubmit : function(param) {
			param.t = t;
			param.s = s;
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.isSuccess) {
				alert(result.msg);
				$('#addDiv').dialog('close');
				searchCode();
			} else {
				alert(result.msg);
			}
		}
	});
}
// 编辑-窗口
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
	var editRow = selectRows[0];
	$('#addDiv').dialog('open').dialog('setTitle', '[编辑]');
	$('#addForm').form('clear');
	document.getElementById('addForm').reset();
	$('#addForm').form('load', editRow);
}
// 删除
function del() {
	var selectRows_arr = [];
	var selectRows = grid.datagrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	$(selectRows).each(function(i, row) {
		selectRows_arr.push(row.code_id);
	});
	var url = basePath+"/commonCtrl/saveOrUpate.c?delIds="+selectRows_arr;
	$.messager.confirm("删除提示", "确认删除？", function(r) {
		if (r) {
			$.get(url, {
				t : "code-sql",
				s : "delCode"
			}, function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					alert(result.msg);
					searchCode();
				} else {
					alert(result.msg);
				}
			});
		}
	})
}