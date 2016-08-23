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
		field : 'roleCode',
		title : '编码',
		hidden : true
	}, {
		field : 'roleName',
		title : '角色名称',
		sortable : true,
		width : 100
	}] ]
};
$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=role-sql&s=listData',
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
			$("#roleCode").val(rowData.roleCode);
			loadRightsByRightCode(rowData.roleCode);
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

// 加载菜单
function loadRightsByRightCode(roleCode) {
	var url = basePath + "/sysbase/roleRightTree.c?roleCode="+roleCode;
	var setting = {
		check : {
			enable : true,
			chkboxType : { "Y" : "p", "N" : "s" }
		},
		view : {
			showLine : true,
			expandSpeed : ($.browser.msie && parseInt($.browser.version) <= 6) ? ""
					: "fast"
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : "0"
			}
		},
		async : {
			enable : true,
			url : url
		},
		callback : {}
	};
	$.fn.zTree.init($("#rightTree"), setting);
}

// 保存
function saveRoleRight() {
	$.messager.progress({text:'系统处理中...'}); 
	var treeObj = $.fn.zTree.getZTreeObj("rightTree");
	var checkedNodes = treeObj.getCheckedNodes(true);
	var rightCodes_arr =[];
	$.each(checkedNodes,function(i,node){
		rightCodes_arr.push(node.id);
	});
	var url = basePath + "/sysbase/saveRoleRight.c";
	$('#saveForm').form('submit', {
		url : url,
		onSubmit : function(param) {
			param.rightCodes = rightCodes_arr
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.isSuccess) {
				$.messager.progress('close');
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
			} else {
				alert(result.msg);
			}
		}
	});
}