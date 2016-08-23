var grid;
// 数据加载参数
var dataGridParams = {
	columns : [ [
			{
				title : 'depId',
				field : 'depId',hidden:true
			},
			{
				width : '200',
				title : '部门名称',
				field : 'depName'
			},
			{
				width : '100',
				title : '排序',
				field : 'depOrder'
			}, {
				title : '操作',
				field : 'action',
				width : '400',
				formatter : function(value, row){
					var buttonStr = "";
					buttonStr += createButtonStr('add',"edit('"+row.depId+"');","编辑");
					buttonStr += createButtonStr('add',"del('"+row.depId+"');","删除");
					buttonStr += createButtonStr('add',"addSubDep('"+row.depId+"');","添加子部门");
					return buttonStr;
				}
			} ] ]
};
$(function() {
	grid = $('#dataGrid').treegrid({
		title : '',
		url :basePath + '/sysbase/depList_datagird.c',
		idField : 'depId',
		treeField : 'depName',
		parentField : 'pId',
		animate:true,
		singleSelect : true,
		rownumbers : true,
		pagination : false,
		sortName : 'depOrder',
		sortOrder : 'asc',
		columns :dataGridParams.columns,
		toolbar : '#toolbar',
		onDblClickRow : function(row) {
		},
		onLoadSuccess : function(row, data) {
		}
	});
});// $(document).ready--end

//信息查询
function search() {
	grid.treegrid('reload', serializeObject($('#searchForm')));
}
//重置查询条件
function resetSearch() {
	$('#searchForm input').val('');
	grid.treegrid('reload', dataGridParams.queryParams);
}
//添加-窗口
function add() {
	var url = basePath+"/sysbase/addSysDep.c";
	openDialog(400,180,"添加",url);
}
//添加-窗口
function addSubDep(depId) {
	var url = basePath+"/sysbase/addSysDep.c?parentDep="+depId;
	openDialog(400,180,"添加",url);
}

//编辑-窗口
function edit(depId) {
	var url = basePath+"/sysbase/editSysDep.c?id="+depId;
	openDialog(400,180,"编辑",url);
}
//删除
function del(depId) {
	var children = grid.treegrid("getChildren",depId);
	if(children.length>0){
		$.messager.alert("提示","请先删除子部门！");
		return;
	}
	var url = basePath + "/sysbase/delSysDeps.c?depId="+depId;
	$.messager.confirm("删除提示", "删除后不可恢复，确定继续执行删除？",function(r) {
		if (r) {
			$.get(url,function(data) {
				var result = eval('(' + data + ')');
				if (result.isSuccess) {
					$.messager.show({
						title:'操作提示',
						msg:result.msg,
						timeout:4000,
						showType:'show',
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
					search();
				} else {
					$.messager.alert("提示",result.msg);
				}
			});
		}
	})
}