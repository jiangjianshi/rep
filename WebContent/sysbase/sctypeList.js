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
		title : 'ctype_id',
		hidden : true
	}, {
		field : 'ctype_name',
		title : '参数类型名称',
		width : 120,
		sortable : true
	}, {
		field : 'ctype_code',
		title : '参数类型编码',
		width : 120,
		align : 'center'
	}, {
		field : 'code_len',
		title : '参数长度限制',
		width : 120,
		align : 'center'
	} ] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=ctype-sql&s=ctypeList&ctype_flag=H',
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
		idField : 'pk_id',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		},
		onClickRow : function(rowIndex, rowData) {
			ctypeName = rowData['ctype_name'];
			ctypeCode = rowData['ctype_code'];
			changeCode(ctypeCode,ctypeName);
		}
	});

}); // $(document).ready--end
// 信息查询
function search() {
	grid.datagrid('reload', serializeObject($('#searchForm')));
	changeCode("");
	ctypeCode="";
	ctypeName="";
}
// 重置查询条件
function resetSearch() {
	$('#searchForm input').val('');
	grid.datagrid('reload', dataGridParams.queryParams);
}
// 添加-窗口
function add() {
	$('#addDiv').dialog('open').dialog('setTitle', '[新增]');
	$('#addForm').form('clear');
	$('#addForm input[name="ctype_code"]').attr('readonly',false);
	document.getElementById('addForm').reset();
}
// 添加-保存
function save() {
	var url = basePath+"/commonCtrl/saveOrUpate.c";
	var t, s;
	if ($('#addForm input[name="ctype_id"]').val() != '') {
		t = 'ctype-sql';
		s = 'updateCtype';
	} else {
		t = 'ctype-sql';
		s = 'addCtype';
	}
	$('#addForm input[name="ctype_flag"]').val("H");
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
				search();
				$('#addDiv').dialog('close');
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
	$('#addForm input[name="ctype_code"]').attr('readonly',true);
	$('#addForm input[name="ctype_code"]').css('background-color', '#eee');
}
// 删除
function del() {
	var selectRows_arr = [];
	var typeCode_arr = [];
	var selectRows = grid.datagrid("getChecked");
	if (selectRows == null || selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	$(selectRows).each(function(i, row) {
		selectRows_arr.push(row.ctype_id);
		typeCode_arr.push(row.ctype_code);
	});
	var url = basePath+"/commonCtrl/saveOrUpate.c?delIds="+selectRows_arr;
	$.messager.confirm("删除提示", "确认删除？", function(r) {
		if (r) {
			$.get(url, {
				t : "ctype-sql",
				s : "delCtype"
			}, function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					delCodes_ByTypeCode(typeCode_arr)
				} else {
					alert(result.msg);
				}
			});
		}
	})
}
function delCodes_ByTypeCode(typeCode){
	var url = basePath+"/commonCtrl/saveOrUpate.c?ctype_code="+typeCode;
	$.get(url, {
		t : "ctype-sql",
		s : "delCodes_ByTypeCode"
	}, function(data) {
		var result = eval('(' + data + ')');
		if (result.isSuccess) {
			alert(result.msg);
			search();
		} else {
			alert(result.msg);
		}
	});
}

//参数编码【-------------------------------------】

var gridCode;
// 数据加载参数
var dataGridCodeParams = {
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

function changeCode(ctypeCode,ctypeName){
	gridCode = $("#dataCodeGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=code-sql&s=codeList&ctype_code='+ctypeCode,
		queryParams : dataGridCodeParams.queryParams,
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
		pageSize : dataGridCodeParams.pageSize,
		pageList : dataGridCodeParams.pageList,
		columns : dataGridCodeParams.columns,
		toolbar : '#toolbarCode',
		onLoadSuccess : function(data) {
			gridCode.datagrid('clearChecked');// 避免easyUI多行删除bug
		}
	});
}

//信息查询
function searchCode() {
	gridCode.datagrid('reload', {ctype_code : ctypeCode});
}

//添加-窗口
function addCode() {
	if(ctypeCode==""){
		alert("请先选择参数类型！");
		return;
	}
	$('#addCodeDiv').dialog('open').dialog('setTitle', '[新增]');
	$('#addCodeForm').form('clear');
	document.getElementById('addCodeForm').reset();
}
//添加-保存
function saveCode() {
	var url = basePath+"/commonCtrl/saveOrUpate.c";
	var t, s;
	if ($('#addCodeForm input[name="code_id"]').val() != '') {
		t = 'code-sql';
		s = 'updateCode';
	} else {
		t = 'code-sql';
		s = 'addCode';
	}

	$('#addCodeForm input[name="ctype_code"]').val(ctypeCode);
	$('#addCodeForm input[name="ctype_name"]').val(ctypeName);
	$('#addCodeForm').form('submit', {
		url : url,
		onSubmit : function(param) {
			param.t = t;
			param.s = s;
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.isSuccess) {
				alert(result.msg);
				$('#addCodeDiv').dialog('close');
				searchCode();
			} else {
				alert(result.msg);
			}
		}
	});
}
//编辑-窗口
function editCode() {
	var selectRows = gridCode.datagrid("getChecked");
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	if (selectRows == null || selectRows.length > 1) {
		alert("只能对一条记录进行编辑！");
		return;
	}
	var editRow = selectRows[0];
	$('#addCodeDiv').dialog('open').dialog('setTitle', '[编辑]');
	$('#addCodeForm').form('clear');
	document.getElementById('addCodeForm').reset();
	$('#addCodeForm').form('load', editRow);
}
//删除
function delCode() {
	var selectRows_arr = [];
	var selectRows = gridCode.datagrid("getChecked");
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