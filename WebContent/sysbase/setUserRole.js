
// 保存
function save() {
	var checkedRoleCode_arr = [];
	$("input[name='roleCode']:checked").each(function(i, o) {
		checkedRoleCode_arr.push($(o).val());
	});
	var url = basePath + "/sysbase/setUserRoleSave.c";
	$('#sumbitForm').form('submit', {
		url : url,
		onSubmit: function(param){    
	        param.roleCode_str = checkedRoleCode_arr;    
	    },
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.isSuccess) {
				alert(result.msg);
				Dialog.close();
			} else {
				alert(result.msg);
			}
		}
	});
}