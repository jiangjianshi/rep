function loadBuildProStatType(){  
	//交费情况
	$('#statRep').combobox({
		data : auto_js_codes_imp['payment_status_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//审图意见
	$('#statExam').combobox({
		data : auto_js_codes_imp['is_receives_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//合格书
	$('#statElig').combobox({
		data : auto_js_codes_imp['is_receives_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//委托情况
	$('#statEntr').combobox({
		data : auto_js_codes_imp['is_entrusts_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//合格书
	$('#statElig').combobox({
		data : auto_js_codes_imp['is_receives_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//可见性
	$('#statLook').combobox({
		data : auto_js_codes_imp['yes_nos_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//施工图
	$('#statShdr').combobox({
		data : auto_js_codes_imp['is_qualifys_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
}
//保存
function buildProStat_save() {
	if ($('#buildProStat_submitForm').form('validate')) {
		var url = basePath + '/oa/baseinfor/editBuildProStatSave.c';
		$('#buildProStat_submitForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					alert(result.msg);
				} else {
					alert(result.msg);
				}
			}
		});
	}
}