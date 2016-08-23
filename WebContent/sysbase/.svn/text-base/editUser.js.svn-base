$(function() {
	deptTree();
});
//加载菜单
function deptTree() {
	var url = basePath + "/sysbase/leftDepTree.c";
	var setting = {
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
			onClick : zTreeOnClick,
//			beforeClick :beforeClick
		}
	};
	function zTreeOnClick(event, treeId, treeNode) {
		$("#depId").val(treeNode.id);
		$("#depName").val(treeNode.name);
		hideMenu();
	};
	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("不能选择父级栏目...");
		return check;
	};
	$.fn.zTree.init($("#depTree"), setting);
}
function showMenu() {
	var cityObj = $("#depName");
	var cityOffset = $("#depName").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
};
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
};
function onBodyDown(event) {
	if (!(event.target.id == "depName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
};
//保存
function save() {
	if ($('#submitForm').form('validate')) {
		var url = basePath + '/sysbase/editUserSave.c';
		$('#submitForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					alert(result.msg);
					//回调父页面查询方法，刷新datagrid
					getParentWindow().search();
					//关闭本弹出窗
					Dialog.close();
				} else {
					alert(result.msg);
				}
			}
		});
	}
}