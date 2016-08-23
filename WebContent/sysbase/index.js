//退出 
function logout() {
	$.ajax({
		url : basePath + "/sysbase/logout.c",
		type : 'post',
		success : function(res) {
			document.location.href = basePath + "/sysbase/login.c";
		}
	});
}
// 修改密码
function chPwd() {
}

//加载菜单
function loadLeftMenu() {
	var url = basePath + "/sysbase/leftZtree.c";
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
			beforeClick : function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("mainMenu");
				if (treeNode.isParent) {
//					var nodes = zTree.getNodesByParam("level", 0, null);
//					$(nodes).each(function(i,o){
//						zTree.expandNode(o,false);
//					});
					zTree.expandNode(treeNode);
					return false;
				}
			}
		}
	};
	function zTreeOnClick(event, treeId, node) {
		addTab(node.name, basePath+"/"+node.menuUrl);
	};
	addTab("首页", basePath+"/oa/baseinfor/main.c");
	$.fn.zTree.init($("#mainMenu"), setting);
}

$(function() {
	loadLeftMenu(); // 动态加载 导航条，根据后台菜单类型配置
});
