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
			width :80
		},
		{
			field : 'realname',
			title : '真实姓名',
			align : 'left',
			sortable : true
		}, 
		{
			field : 'depName',
			title : '所在部门',
			align : 'left',
			sortable : true,
			width : 80
		}, 
		{
			title : '操作',
			field : 'action',
			width : 80,
			formatter : function (value, row){
				var buttonStr = "";
				buttonStr += createButtonStr('edit',"selectP('"+row.userId+"','"+row.realname+"');","选取");
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
			//回调父页面方法
			if(type=='proofreadUser'){
				getParentWindow().$("#proofreadUserId_"+majorId).val(rowData.userId);
				getParentWindow().$("#proofreadUserShow_"+majorId).html(rowData.realname);
			}else if(type=='checkUser'){
				getParentWindow().$("#checkUserId_"+majorId).val(rowData.userId);
				getParentWindow().$("#checkUserShow_"+majorId).html(rowData.realname);
			}			
			Dialog.close();
		}
	});
}); // $(document).ready--end
function selectP(userId,realName){
	//回调父页面方法
	if(type=='proofreadUser'){
		getParentWindow().$("#proofreadUserId_"+majorId).val(userId);
		getParentWindow().$("#proofreadUserShow_"+majorId).html(realName);
	}else if(type=='checkUser'){
		getParentWindow().$("#checkUserId_"+majorId).val(userId);
		getParentWindow().$("#checkUserShow_"+majorId).html(realName);
	}			
	Dialog.close();
}
//信息查询
function search() {
	grid.datagrid('reload', serializeObject($('#searchForm')));
}
//重置查询条件
function resetSearch() {
	$('#searchForm input').val('');
	grid.datagrid('reload', dataGridParams.queryParams);
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
