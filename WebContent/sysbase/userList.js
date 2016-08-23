var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ 
	{
		field : '主键',
		title : 'userId',
		hidden:true
	}, 
	
		{
			field : 'username',
			title : '用户名',
			align : 'left',
			sortable : true,
			width : 150
		},
		{
			field : 'realname',
			title : '真实姓名',
			align : 'left',
			sortable : true,
			width : 150
		}, 
		{
			field : 'depName',
			title : '所在部门',
			align : 'left',
			sortable : true,
			width : 150
		}, 
		{
			title : '操作',
			field : 'action',
			width : '200',
			formatter : function (value, row){
				var buttonStr = "";
				buttonStr += createButtonStr('edit',"edit('"+row.userId+"');","编辑");
				buttonStr += createButtonStr('edit',"del('"+row.userId+"');","删除");
				buttonStr += createButtonStr('edit',"setRole('"+row.userId+"');","设置角色");
				return buttonStr;
			}
		} 
] ]
};

$(document).ready(function() {
	leftDepTree();
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=sys_user-sql&s=searchList&searchType=sys_user',
		queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后',
		pagination : true,
		border : true,
		singleSelect : true,
		idField : 'userId',
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			grid.datagrid('clearChecked');// 避免easyUI多行删除bug
		},
		onDblClickRow: function(rowIndex, rowData){
			var url = basePath+"/sysbase/editUser.c?id="+rowData.userId;
			openDialog(1000,1000,"编辑",url);
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
//添加-窗口
function add() {
	var url = basePath+"/sysbase/addUser.c?depId="+$('#depId').val();
	openDialog(800,800,"添加",url);
}

//编辑-窗口
function edit(userId) {
	var url = basePath+"/sysbase/editUser.c?id="+userId;
	openDialog(800,800,"编辑",url);
}
//删除
function del(userId) {
	var url = basePath + "/sysbase/delUser.c?userId="+userId;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除",function(r) {
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
}
//设置角色
function setRole(userId){
	var url = basePath+"/sysbase/setUserRole.c?userId="+userId;
	openDialog(300,600,"设置角色",url);
}

function leftDepTree() {
	var url = basePath + "/sysbase/leftDepTree.c";
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
		callback : {
			onClick : function(event, treeId, node) {
				$('#searchForm input').val('');
				$('#depId').val(node.id);
				grid.datagrid('reload', serializeObject($('#searchForm')));
			}
		}
	};
	$.fn.zTree.init($("#leftDepTree"), setting);
}