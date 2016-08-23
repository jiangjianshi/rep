function loadBuildProMinType(){  
	//建筑等级
	$('#minoBule').combobox({
		data : auto_js_codes_imp['min_jzdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//结构类型
	$('#minoStty').combobox({
		data : auto_js_codes_imp['min_jglxs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//基础形式
	$('#minoBafo').combobox({
		data : auto_js_codes_imp['min_jcxss_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//人防等级
	$('#minoPele').combobox({
		data : auto_js_codes_imp['min_rfdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//耐火等级
	$('#minoFrle').combobox({
		data : auto_js_codes_imp['min_rfdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//抗震设防烈度
	$('#minoAsit').combobox({
		data : auto_js_codes_imp['min_kzsfs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
	//抗震设防类别
	$('#minoAsty').combobox({
		data : auto_js_codes_imp['min_rfdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto',editable:false
	});
}
//保存
function buildProMin_save() {
	if ($('#buildProMin_submitForm').form('validate')) {
		if($("#buildProMin_pkId").val()==''){
			var url = basePath + '/oa/baseinfor/saveBuildProMin.c';
		}else{
			var url = basePath + '/oa/baseinfor/editBuildProMinSave.c';
		}
		$('#buildProMin_submitForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					$.messager.show({
						title:'操作提示',
						msg:result.msg,
						timeout:600,
						showType:'show',
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
					$('#buildProMin_submitForm input[name!="repCode"]').val('');
					$('#buildProMin_submitForm textarea').val('');
					//刷新datagrid
					buildProMin_search();
				} else {
					alert(result.msg);
				}
			}
		});
	}
}