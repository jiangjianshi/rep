// 重新加载sql文件
function reloadSqlFile() {
	var url = basePath + "/sysbase/reloadSqlFile.c";
	$.get(url, {
		user_id : ''
	}, function(data) {
		var result = eval('(' + data + ')');
		if (result.isSuccess) {
			$.messager.show({
				title:'操作提示',
				msg:'更新成功',
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
	});
}
