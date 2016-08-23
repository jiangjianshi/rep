$(function() {
	loadType();
});
function loadType(){  
//	$('#isTj').combobox({
//		data : auto_js_codes_imp['yes_nos_js'],
//		valueField : 'code_value',
//		textField : 'code_name',
//		panelHeight:'auto'
//	});
}
//保存
function save() {
	if ($('#submitForm').form('validate')) {
		var url = basePath + '/oa/baseinfor/saveBuildUnit.c';
		$('#submitForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					alert(result.msg);
					//回调父页面查询方法，刷新datagrid
					getParentWindow().search();
					//关闭本弹出窗口
					Dialog.close();
				} else {
					alert(result.msg);
				}
			}
		});
	}
}