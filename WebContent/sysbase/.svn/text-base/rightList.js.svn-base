var grid;
// 数据加载参数
var dataGridParams = {
	columns : [ [
			{
				width : '200',
				title : 'pkId',
				field : 'pkId',hidden:true
			},
			{
				width : '200',
				title : '资源名称',
				field : 'rightCode',hidden:true
			},
			{
				width : '200',
				title : 'pCode',
				field : 'pCode',hidden:true
			},
			{
				width : '200',
				title : '资源名称',
				field : 'rightName'
			},
			{
				title : '资源路径',
				field : 'rightUrl'
			},
			{
				width : '200',
				title : '资源类型',
				field : 'rightType',hidden:true
			},
			{
				width : '50',
				title : '排序',
				field : 'orderCode'
			}, {
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var buttonStr = "";
					if(row.pCode=='0'){
						buttonStr += "<a href=\"javascript:addSub('"+row.rightCode+"');\">添加子菜单</a>";
					}
					if(row.pCode!='0' && row.rightType=='0'){
						buttonStr += "<a href=\"javascript:rightPointMgr('"+row.rightCode+"');\">权限点</a>";
					}
					return buttonStr;
				}
			} ] ]
};
$(function() {
	grid = $('#dataGrid').treegrid({
		title : '',
		url :basePath + '/sysbase/rightList_datagird.c',
		idField : 'rightCode',
		treeField : 'rightName',
		parentField : 'pCode',
		animate:true,
		rownumbers : true,
		pagination : false,
		sortName : 'seq',
		sortOrder : 'asc',
		columns :dataGridParams.columns,
		toolbar : '#toolbar',
		onDblClickRow : function(row) {
		},
		onLoadSuccess : function(row, data) {
		}
	});
});

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
	var editRow = selectRows[0];
	$('#addDiv').dialog('open').dialog('setTitle', '[新增]');
	$('#addForm').form('clear');
	document.getElementById('addForm').reset();
	$('#addForm').form('load', editRow);
	$('#addForm input[name="saveType"]').val('update');
}
//添加-窗口
function addSub(rightCode) {
	$('#addDiv').dialog('open').dialog('setTitle', '[新增]');
	$('#addForm').form('clear');
	document.getElementById('addForm').reset();
	$('#addForm input[name="pCode"]').val(rightCode);
	$('#addForm input[name="saveType"]').val('add');
}
//添加-窗口
function addTop() {
	$('#addDiv').dialog('open').dialog('setTitle', '[新增]');
	$('#addForm').form('clear');
	document.getElementById('addForm').reset();
	$('#addForm input[name="pCode"]').val(0);
	$('#addForm input[name="pkId"]').val('');
	$('#addForm input[name="saveType"]').val('add');
}

//保存
function save() {
	if ($('#addForm').form('validate')) {
		var url = basePath + "/sysbase/saveRight.c";
		$('#addForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					$.messager.show({
						title:'操作提示',
						msg:result.msg,
						timeout:2000,
						showType:'show',
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
					grid.treegrid('reload');
					$('#addDiv').dialog('close');
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
	if (selectRows.length == 0) {
		alert("请选择一条记录！");
		return;
	}
	var selectRow = selectRows[0];
	var url = basePath + "/sysbase/delRight.c?rightId="+selectRow.pkId;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除",function(r) {
		if (r) {
			$.get(url,function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					alert(result.msg);
					grid.treegrid('reload');
				} else {
					alert(result.msg);
				}
			});
		}
	});
}

function rightPointMgr(rightCode){
	var url = basePath+"/sysbase/rightPointMgr.c?pCode="+rightCode;
	openDialog(500,500,"权限点管理",url);
}